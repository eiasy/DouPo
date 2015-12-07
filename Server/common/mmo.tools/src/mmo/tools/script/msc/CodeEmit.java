package mmo.tools.script.msc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import mmo.tools.script.msc.Struct.FuncNode;
import mmo.tools.script.msc.Struct.ICodeNode;
import mmo.tools.script.msc.Struct.Op;
import mmo.tools.script.msc.Struct.SymbolNode;

/**
 * Author: cuirongzhou Date: 2007-11-26 Time: 15:32:27
 */
public class CodeEmit extends Const {
	// ---- Include Files -------------------------------------------------------------------------
	// #include "xsc.h"
	// #include "error.h"
	// #include "func_table.h"
	// #include "symbol_table.h"
	// #include "i_code.h"
	// ---- Constants -----------------------------------------------------------------------------

	public static final int   TAB_STOP_WIDTH   = 8; // The width of a tab stop in

	// ---- Function Prototypes -------------------------------------------------------------------
	// ---- Globals -------------------------------------------------------------------------------

	public static PrintWriter g_pOutputFile;       // Pointer to the output file

	// ---- Instruction Mnemonics -------------------------------------------------------------

	// These mnemonics are mapped to each I-code instruction, allowing the emitter to
	// easily translate I-code to XVM assembly

	public static String      ppstrMnemonics[] = { "Mov", "Add", "Sub", "Mul", "Div", "Mod", "Exp", "Neg", "Inc", "Dec", "And", "Or", "XOr", "Not",
	        "ShL", "ShR", "Concat", "GetChar", "SetChar", "Jmp", "JE", "JNE", "JG", "JL", "JGE", "JLE", "Push", "Pop", "Call", "Ret", "CallHost",
	        "Pause", "Exit"                   };

	// ---- Functions -----------------------------------------------------------------------------

	/******************************************************************************************
	 * 
	 * EmitHeader ()
	 * 
	 * Emits the script's header comments.
	 */

	public static void EmitHeader() {
		// Get the current time
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("EEEE-MMMM-dd-yyyy");
		Date date = new Date();

		// Emit the filename
		g_pOutputFile.write("; " + g_pstrOutputFilename + "\n\n");
		// Emit the rest of the header
		g_pOutputFile.write("; Source File: " + g_pstrSourceFilename + "\n");
		g_pOutputFile.write("; XSC Version: " + VERSION_MAJOR + "." + VERSION_MINOR + "\n");
		g_pOutputFile.write(";   Timestamp: " + bartDateFormat.format(date) + "\n");
	}

	/******************************************************************************************
	 * 
	 * EmitDirectives ()
	 * 
	 * Emits the script's directives.
	 */

	public static void EmitDirectives() {
		// If directives were emitted, this is set to TRUE so we remember to insert extra line
		// breaks after them

		int iAddNewline = FALSE;

		// If the stack size has been set, emit a SetStackSize directive

		if (g_ScriptHeader.iStackSize != 0) {
			g_pOutputFile.write("\t SetStackSize " + g_ScriptHeader.iStackSize);
			iAddNewline = TRUE;
		}

		// If the priority has been set, emit a SetPriority directive

		if (g_ScriptHeader.iPriorityType != PRIORITY_NONE) {
			g_pOutputFile.write("\tSetPriority ");
			switch (g_ScriptHeader.iPriorityType) {
				// Low rank

				case PRIORITY_LOW:
					g_pOutputFile.write(PRIORITY_LOW_KEYWORD);
					break;

				// Medium rank

				case PRIORITY_MED:
					g_pOutputFile.write(PRIORITY_MED_KEYWORD);
					break;

				// High rank

				case PRIORITY_HIGH:
					g_pOutputFile.write(PRIORITY_HIGH_KEYWORD);
					break;

				// User-defined timeslice

				case PRIORITY_USER:
					g_pOutputFile.write(g_ScriptHeader.iUserPriority);
					break;
			}
			g_pOutputFile.write("\n");

			iAddNewline = TRUE;
		}

		// If necessary, insert an extra line break

		if (iAddNewline == TRUE)
			g_pOutputFile.write("\n");
	}

	/******************************************************************************************
	 * 
	 * EmitScopeSymbols ()
	 * 
	 * Emits the symbol declarations of the specified scope
	 */

	public static void EmitScopeSymbols(int iScope, int iType) {
		// If declarations were emitted, this is set to TRUE so we remember to insert extra
		// line breaks after them

		int iAddNewline = FALSE;

		// Local symbol node pointer

		SymbolNode pCurrSymbol = new SymbolNode();

		// Loop through each symbol in the table to find the match

		for (int iCurrSymbolIndex = 0; iCurrSymbolIndex < g_SymbolTable.size(); ++iCurrSymbolIndex) {
			// Get the current symbol structure

			pCurrSymbol = Msc.GetSymbolByIndex(iCurrSymbolIndex);

			// If the scopes and parameter flags match, emit the declaration

			if (pCurrSymbol.iScope == iScope && pCurrSymbol.iType == iType) {
				// Print one tab stop for global declarations, and two for locals

				g_pOutputFile.write("\t");
				if (iScope != SCOPE_GLOBAL)
					g_pOutputFile.write("\t");

				// Is the symbol a parameter?

				if (pCurrSymbol.iType == SYMBOL_TYPE_PARAM)
					g_pOutputFile.write("Param " + pCurrSymbol.pstrIdent);

				// Is the symbol a variable?

				if (pCurrSymbol.iType == SYMBOL_TYPE_VAR) {
					g_pOutputFile.write("Var " + pCurrSymbol.pstrIdent);

					// If the variable is an array, add the size declaration

					if (pCurrSymbol.iSize > 1)
						g_pOutputFile.write(" [ " + pCurrSymbol.iSize + " ]");
				}

				g_pOutputFile.write("\n");
				iAddNewline = TRUE;
			}
		}

		// If necessary, insert an extra line break

		if (iAddNewline == TRUE)
			g_pOutputFile.write("\n");
	}

	/******************************************************************************************
	 * 
	 * EmitFunc ()
	 * 
	 * Emits a function, its local declarations, and its code.
	 */

	public static void EmitFunc(FuncNode pFunc) {
		// Emit the function declaration name and opening brace

		g_pOutputFile.write("\tFunc " + pFunc.pstrName + "\n");
		g_pOutputFile.write("\t{\n");

		// Emit parameter declarations

		EmitScopeSymbols(pFunc.iIndex, SYMBOL_TYPE_PARAM);

		// Emit local variable declarations

		EmitScopeSymbols(pFunc.iIndex, SYMBOL_TYPE_VAR);

		// Does the function have an I-code block?

		if (pFunc.ICodeStream.size() > 0) {
			// Used to determine if the current line is the first

			int iIsFirstSourceLine = TRUE;

			// Yes, so loop through each I-code node to emit the code

			for (int iCurrInstrIndex = 0; iCurrInstrIndex < pFunc.ICodeStream.size(); ++iCurrInstrIndex) {
				// Get the I-code instruction structure at the current node

				ICodeNode pCurrNode = Msc.GetICodeNodeByImpIndex(pFunc.iIndex, iCurrInstrIndex);

				// Determine the node type

				switch (pCurrNode.iType) {
					// Source code annotation

					case ICODE_NODE_SOURCE_LINE: {
						// Make a local copy of the source line

						char[] pstrSourceLine = pCurrNode.pstrSourceLine.toCharArray();

						// If the last character of the line is a line break, clip it

						int iLastCharIndex = pstrSourceLine.length - 1;
						if (pstrSourceLine[iLastCharIndex] == '\n')
							pstrSourceLine[iLastCharIndex] = ' ';

						// Emit the comment, but only prepend it with a line break if it's not the
						// first one

						if (iIsFirstSourceLine == FALSE)
							g_pOutputFile.write("\n");

						g_pOutputFile.write("\t\t; " + new String(pstrSourceLine) + "\n\n");

						break;
					}

						// An I-code instruction

					case ICODE_NODE_INSTR: {
						// Emit the opcode

						g_pOutputFile.write("\t\t" + ppstrMnemonics[pCurrNode.Instr.iOpcode]);

						// Determine the number of operands

						int iOpCount = pCurrNode.Instr.OpList.size();

						// If there are operands to emit, follow the instruction with some space

						if (iOpCount != 0) {
							// All instructions get at least one tab

							g_pOutputFile.write("\t");

							// If it's less than a tab stop's width in characters, however, they get a
							// second

							if (ppstrMnemonics[pCurrNode.Instr.iOpcode].length() < TAB_STOP_WIDTH)
								g_pOutputFile.write("\t");
						}

						// Emit each operand

						for (int iCurrOpIndex = 0; iCurrOpIndex < iOpCount; ++iCurrOpIndex) {
							// Get a pointer to the operand structure

							Op pOp = Msc.GetICodeOpByIndex(pCurrNode, iCurrOpIndex);

							// Emit the operand based on its type

							switch (pOp.iType) {
								// Integer literal

								case OP_TYPE_INT:
									g_pOutputFile.write("" + pOp.unionValue);
									break;

								// Float literal

								case OP_TYPE_FLOAT:
									g_pOutputFile.write("" + pOp.fFloatLiteral);
									break;

								// String literal

								case OP_TYPE_STRING_INDEX:

									g_pOutputFile.write("\"" + Msc.GetStringByIndex(g_StringTable, pOp.unionValue) + "\"");
									break;

								// Variable

								case OP_TYPE_VAR:
									g_pOutputFile.write(Msc.GetSymbolByIndex(pOp.unionValue).pstrIdent);
									break;

								// Array index absolute

								case OP_TYPE_ARRAY_INDEX_ABS:
									g_pOutputFile.write(Msc.GetSymbolByIndex(pOp.unionValue).pstrIdent + " [" + pOp.iOffset + " ]");
									break;

								// Array index variable

								case OP_TYPE_ARRAY_INDEX_VAR:
									g_pOutputFile.write(Msc.GetSymbolByIndex(pOp.unionValue).pstrIdent + " ["
									        + Msc.GetSymbolByIndex(pOp.iOffsetSymbolIndex).pstrIdent + " ]");
									break;

								// Function

								case OP_TYPE_FUNC_INDEX:
									g_pOutputFile.write(Msc.GetFuncByIndex(pOp.unionValue).pstrName);
									break;

								// Register (just _RetVal for now)

								case OP_TYPE_REG:
									g_pOutputFile.write("_RetVal");
									break;

								// Jump target index

								case OP_TYPE_JUMP_TARGET_INDEX:
									g_pOutputFile.write("_L" + pOp.unionValue);
									break;
							}

							// If the operand isn't the last one, append it with a comma and space

							if (iCurrOpIndex != iOpCount - 1)
								g_pOutputFile.write(", ");
						}

						// Finish the line

						g_pOutputFile.write("\n");

						break;
					}

						// A jump target

					case ICODE_NODE_JUMP_TARGET: {
						// Emit a label in the format _LX, where X is the jump target

						g_pOutputFile.write("\t_L" + pCurrNode.iJumpTargetIndex + ":\n");
					}
				}

				// Update the first line flag

				if (iIsFirstSourceLine == TRUE)
					iIsFirstSourceLine = FALSE;
			}
		} else {
			// No, so emit a comment saying so

			g_pOutputFile.write("\t\t; (No code)\n");
		}

		// Emit the closing brace

		g_pOutputFile.write("\t}");
	}

	/******************************************************************************************
	 * 
	 * EmitCode ()
	 * 
	 * Translates the I-code representation of the script to an ASCII-foramtted XVM assembly file.
	 */

	public static void EmitCode() {
		// ---- Open the output file
		try {
			g_pOutputFile = new PrintWriter(new BufferedWriter(new FileWriter(g_pstrOutputFilename)));
		} catch (IOException e) {
			Error.ExitOnError("Could not open output file for output");
			e.printStackTrace();
		}

		// ---- Emit the header

		EmitHeader();

		// ---- Emit directives

		g_pOutputFile.write("; ---- Directives -----------------------------------------------------------------------------\n\n");

		EmitDirectives();

		// ---- Emit global variable declarations

		g_pOutputFile.write("; ---- Global Variables -----------------------------------------------------------------------\n\n");

		// Emit the globals by printing all non-parameter symbols in the global scope

		EmitScopeSymbols(SCOPE_GLOBAL, SYMBOL_TYPE_VAR);

		// ---- Emit functions

		g_pOutputFile.write("; ---- Functions ------------------------------------------------------------------------------\n\n");

		// Local node for traversing lists

		// Local function node pointer

		FuncNode pCurrFunc;

		// Pointer to hold the _Main () function, if it's found

		FuncNode pMainFunc = null;

		// Loop through each function and emit its declaration and code, if functions exist
		int iCurreIndex = 0;
		if (g_FuncTable.size() > 0) {
			while (true) {
				// Get a pointer to the node

				pCurrFunc = (FuncNode) g_FuncTable.get(iCurreIndex);

				// Don't emit host API function nodes

				if (pCurrFunc.iIsHostAPI == FALSE) {
					// Is the current function _Main ()?

					if (Tool.stricmp(pCurrFunc.pstrName, MAIN_FUNC_NAME)) {
						// Yes, so save the pointer for later (and don't emit it yet)

						pMainFunc = pCurrFunc;
					} else {
						// No, so emit it

						EmitFunc(pCurrFunc);
						g_pOutputFile.write("\n\n");
					}
				}

				// Move to the next node
				iCurreIndex++;
				if (iCurreIndex >= g_FuncTable.size())
					break;
			}
		}

		// ---- Emit _Main ()

		g_pOutputFile.write("; ---- Main -----------------------------------------------------------------------------------");

		// If the last pass over the functions found a _Main () function. emit it

		if (pMainFunc != null) {
			g_pOutputFile.write("\n\n");
			EmitFunc(pMainFunc);
		}

		// ---- Close output file
		g_pOutputFile.close();
	}
}
