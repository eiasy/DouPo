package mmo.tools.script.msc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import mmo.tools.script.msc.Struct.FuncNode;
import mmo.tools.script.msc.Struct.ICodeInstr;
import mmo.tools.script.msc.Struct.ICodeNode;
import mmo.tools.script.msc.Struct.Link;
import mmo.tools.script.msc.Struct.Op;
import mmo.tools.script.msc.Struct.ScriptHeader;
import mmo.tools.script.msc.Struct.SymbolNode;



/**
 * Author: cuirongzhou Date: 2007-11-26 Time: 11:55:02
 */
public class Msc extends Const {

		// ---- Include Files -------------------------------------------------------------------------

		//    #include "xsc.h"
		//
		//    #include "error.h"
		//    #include "func_table.h"
		//    #include "symbol_table.h"
		//
		//    #include "preprocessor.h"
		//    #include "lexer.h"
		//    #include "parser.h"
		//    #include "i_code.h"
		//    #include "code_emit.h"

		// ---- Globals -------------------------------------------------------------------------------

		// ---- Source Code -----------------------------------------------------------------------
		//
		//		public static String g_pstrSourceFilename;// [ MAX_FILENAME_SIZE ],	// Source code filename
		//		public static String g_pstrOutputFilename;// [ MAX_FILENAME_SIZE ];	// Executable filename

		//        Link g_SourceCode;                        // Source code linked list

		public static Parser parser;

		// ---- XASM Invocation -------------------------------------------------------------------

		public static int    g_iPreserveOutputFile;     // Preserve the assembly file?
		public static int    g_iGenerateXSE;            // Generate an .XSE executable?

		// ---- Expression Evaluation -------------------------------------------------------------

		public static int    g_iCurrJumpTargetIndex = 0; // The current target index
		                                                 // ---- Functions -----------------------------------------------------------------------------

		/***********************************************************************
		 * 
		 * PrintLogo ()
		 * 
		 * Prints out logo/credits information.
		 */

		public static void PrintLogo() {
				System.out.print("XSC\n");
				System.out.print("MobileScript Compiler Version " + VERSION_MAJOR + VERSION_MINOR + "\n");
				System.out.print("Written by Cui Rongzhou\n");
				System.out.print("\n");
		}

		/***********************************************************************
		 * 
		 * PrintUsage ()
		 * 
		 * Prints out usage information.
		 */

		public static void PrintUsage() {
				System.out.print("Usage:\tXSC Source.XSS [Output.XASM] [Options]\n");
				System.out.print("\n");
				System.out.print("\t-S:Size      Sets the stack size (must be decimal integer value)\n");
				System.out.print("\t-P:Priority  Sets the thread priority: Low, Med, High or timeslice\n");
				System.out.print("\t             duration (must be decimal integer value)\n");
				System.out.print("\t-A           Preserve assembly output file\n");
				System.out.print("\t-N           Don't generate .XSE (preserves assembly output file)\n");
				System.out.print("\n");
				System.out.print("Notes:\n");
				System.out.print("\t- File extensions are not required.\n");
				System.out.print("\t- Executable name is optional; source name is used by default.\n");
				System.out.print("\n");
		}

		/***********************************************************************
		 * 
		 * VerifyFilenames ()
		 * 
		 * Verifies the input and output filenames.
		 */

		public static void VerifyFilenames(int argc, String argv[]) {
				// First make a global copy of the source filename and convert it to uppercase

				g_pstrSourceFilename = argv[0].toUpperCase();

				// Check for the presence of the .XASM extension and add it if it's not there
				if (!g_pstrSourceFilename.contains(SOURCE_FILE_EXT)) {
						// The extension was not found, so add it to string
						g_pstrSourceFilename += SOURCE_FILE_EXT;
				}

				// Was an executable filename specified?

				if (argv[1] != null && argv[1].charAt(0) != '-') {
						// Yes, so repeat the validation process

						g_pstrOutputFilename = argv[1].toUpperCase();

						// Check for the presence of the .XSE extension and add it if it's not there
						if (!g_pstrOutputFilename.contains(OUTPUT_FILE_EXT)) {
								// The extension was not found, so add it to string
								g_pstrSourceFilename += OUTPUT_FILE_EXT;
						}

				} else {
						// No, so base it on the source filename

						// First locate the start of the extension, and use pointer subtraction to find the index

						g_pstrOutputFilename = g_pstrSourceFilename.replace(SOURCE_FILE_EXT, OUTPUT_FILE_EXT);
				}
		}

		/***********************************************************************
		 * 
		 * ReadCmmndLineParams ()
		 * 
		 * Reads and verifies the command line parameters.
		 */

		public static void ReadCmmndLineParams(int argc, String argv[]) {
				String pstrCurrOption = new String();// [ 32 ];
				String pstrCurrValue = new String();// [ 32 ];
				String pstrErrorMssg = new String();// [ 256 ];

				for (int iCurrOptionIndex = 0; iCurrOptionIndex < argc; ++iCurrOptionIndex) {
						// Convert the argument to uppercase to keep things neat and tidy

						argv[iCurrOptionIndex] = argv[iCurrOptionIndex].toUpperCase();

						// Is this command line argument an option?

						if (argv[iCurrOptionIndex].charAt(0) == '-') {
								// Parse the option and value from the string

								int iCurrCharIndex;
								int iOptionSize;
								char cCurrChar;

								// Read the option up till the colon or the end of the string

								iCurrCharIndex = 1;
								while (true) {
										cCurrChar = argv[iCurrOptionIndex].charAt(iCurrCharIndex);
										if (cCurrChar == ':')
												break;
										else
												pstrCurrOption += cCurrChar;
										++iCurrCharIndex;
										if (iCurrCharIndex >= argv[iCurrOptionIndex].length())
												break;
								}
								//                pstrCurrOption [ iCurrCharIndex - 1 ] = '\0';

								// Read the value till the end of the string, if it has one
								if (argv[iCurrOptionIndex].contains(":")) {
										++iCurrCharIndex;
										iOptionSize = iCurrCharIndex;

										//pstrCurrValue = null;
										while (true) {
												if (iCurrCharIndex >= argv[iCurrOptionIndex].length())
														break;
												else {
														cCurrChar = argv[iCurrOptionIndex].charAt(iCurrCharIndex);
														pstrCurrValue += cCurrChar;
														//                            pstrCurrValue [ iCurrCharIndex - iOptionSize ] = cCurrChar;
												}
												++iCurrCharIndex;
										}
										//                    pstrCurrValue [ iCurrCharIndex - iOptionSize ] = '\0';

										// Make sure the value is valid
										pstrCurrValue = pstrCurrValue.trim();
										if (pstrCurrValue.equals("")) {
												pstrErrorMssg = " Invalid value for -" + pstrCurrOption + " option";
												Error.ExitOnError(pstrErrorMssg);
										}

								}

								// ---- Perform the option's action

								// Set the stack size

								if (pstrCurrOption.equals("S")) {
										// Convert the value to an integer stack size

										g_ScriptHeader.iStackSize = Tool.atoi(pstrCurrValue);
								}

								// Set the priority

								else if (pstrCurrOption.equals("P")) {
										// ---- Determine what type of priority was specified

										// Low rank

										if (pstrCurrValue.equals(PRIORITY_LOW_KEYWORD)) {
												g_ScriptHeader.iPriorityType = PRIORITY_LOW;
										}

										// Medium rank

										else if (pstrCurrValue.equals(PRIORITY_MED_KEYWORD)) {
												g_ScriptHeader.iPriorityType = PRIORITY_MED;
										}

										// High rank

										else if (pstrCurrValue.equals(PRIORITY_HIGH_KEYWORD)) {
												g_ScriptHeader.iPriorityType = PRIORITY_HIGH;
										}

										// User-defined timeslice

										else {
												g_ScriptHeader.iPriorityType = PRIORITY_USER;
												g_ScriptHeader.iUserPriority = Tool.atoi(pstrCurrValue);
										}
								}

								// Preserve the assembly file

								else if (pstrCurrOption.equals("A")) {
										g_iPreserveOutputFile = TRUE;
								}

								// Don't generate an .XSE executable

								else if (pstrCurrOption.equals("N")) {
										g_iGenerateXSE = FALSE;
										g_iPreserveOutputFile = TRUE;
								}

								// Anything else is invalid

								else {
										pstrErrorMssg = "Unrecognized option: " + pstrCurrOption;
										Error.ExitOnError(pstrErrorMssg);
								}
						}
				}
		}

		/***********************************************************************
		 * 
		 * Init ()
		 * 
		 * Initializes the compiler.
		 */

		public static void Init() {
				// ---- Initialize the script header
				g_ScriptHeader = new ScriptHeader();
				g_ScriptHeader.iIsMainFuncPresent = FALSE;
				g_ScriptHeader.iStackSize = 0;
				g_ScriptHeader.iPriorityType = PRIORITY_NONE;

				// ---- Initialize the main settings

				// Mark the assembly file for deletion

				g_iPreserveOutputFile = FALSE;

				// Generate an .XSE executable

				g_iGenerateXSE = TRUE;

				// Initialize the source code list

				g_SourceCode = new Link();
				g_FuncTable = new LinkedList();
				g_SymbolTable = new LinkedList();
				g_StringTable = new Link();

				//initial Class
				parser = new Parser();
				new Error(parser);
				//        new Parser();
		}

		/***********************************************************************
		 * 
		 * ShutDown ()
		 * 
		 * Shuts down the compiler.
		 */

		public static void ShutDown() {
				// Free the source code
				g_SourceCode = null;
				g_FuncTable = null;
				g_SymbolTable = null;
				g_StringTable = null;
		}

		/***********************************************************************
		 * 
		 * LoadSourceFile ()
		 * 
		 * Loads the source file into memory.
		 */

		public static void LoadSourceFile() {
				// ---- Open the input file

				g_pSourceFile = null;
				try {
						g_pSourceFile = new BufferedReader(new FileReader(g_pstrSourceFilename));
						String s = new String();
						// Count the number of source lines
						while ((s = g_pSourceFile.readLine()) != null) {
								g_SourceCode.addNode(s + "\n");
						}
						//            ++ g_iSourceCodeSize;
						g_pSourceFile.close();
						// Reopen the source file in ASCII mode
						g_pSourceFile = new BufferedReader(new FileReader(g_pstrSourceFilename));

				} catch (FileNotFoundException e) {
						e.printStackTrace();
						Error.ExitOnError("Could not open source file for input");
				} catch (IOException e) {
						e.printStackTrace();
						Error.ExitOnError("DataInputStream invailable");
				}

		}

		/***********************************************************************
		 * 
		 * CompileSourceFile ()
		 * 
		 * Compiles the high-level source file to its XVM assembly equivelent.
		 */

		public static void CompileSourceFile() {
				// Add two temporary variables for evaluating expressions

				g_iTempVar_0_SymbolIndex = AddSymbol(TEMP_VAR_0, 1, SCOPE_GLOBAL, SYMBOL_TYPE_VAR);
				g_iTempVar_1_SymbolIndex = AddSymbol(TEMP_VAR_1, 1, SCOPE_GLOBAL, SYMBOL_TYPE_VAR);

				// Parse the source file to create an I-code representation

				parser.ParseSourceCode();

				//        // Hardcode a _Main () function into the table and save its index
				//
				//        int iMainIndex = AddFunc ( "_Main", FALSE );
				//
				//        // Hardcode symbols into the table and save their indices
				//
				//        int iMyGlobalIndex = AddSymbol ( "MyGlobal", 1, SCOPE_GLOBAL, SYMBOL_TYPE_VAR );
				//        int iXIndex = AddSymbol ( "X", 1, iMainIndex, SYMBOL_TYPE_VAR );
				//        int iYIndex = AddSymbol ( "Y", 4, iMainIndex, SYMBOL_TYPE_VAR );
				//
				//        // Allocate strings to hold each line of the high-level script
				//
				//        String pstrLine0 = "MyGlobal = 2;";
				//
				//        String pstrLine1 = "X = 8;";
				//
				//        String pstrLine2 =  "Y [ 1 ] = MyGlobal ^ X;";
				//
				//        // Hardcode the instructions and source annotation into the I-code module
				//
				//        int iInstrIndex;
				//
				//        // MyGlobal = 2;
				//
				//        AddICodeSourceLine ( iMainIndex, pstrLine0 );
				//        iInstrIndex = AddICodeInstr ( iMainIndex, INSTR_MOV );
				//        AddVarICodeOp ( iMainIndex, iInstrIndex, iMyGlobalIndex );
				//        AddIntICodeOp ( iMainIndex, iInstrIndex, 2 );
				//
				//        // X = 8;
				//
				//        AddICodeSourceLine ( iMainIndex, pstrLine1 );
				//        iInstrIndex = AddICodeInstr ( iMainIndex, INSTR_MOV );
				//        AddVarICodeOp ( iMainIndex, iInstrIndex, iXIndex );
				//        AddIntICodeOp ( iMainIndex, iInstrIndex, 8 );
				//
				//        // Y [ 1 ] = MyGlobal ^ X;
				//
				//        AddICodeSourceLine ( iMainIndex, pstrLine2 );
				//
				//        iInstrIndex = AddICodeInstr ( iMainIndex, INSTR_EXP );
				//        AddVarICodeOp ( iMainIndex, iInstrIndex, iMyGlobalIndex );
				//        AddVarICodeOp ( iMainIndex, iInstrIndex, iXIndex );
				//
				//        iInstrIndex = AddICodeInstr ( iMainIndex, INSTR_MOV );
				//        AddArrayIndexAbsICodeOp ( iMainIndex, iInstrIndex, iYIndex, 1 );
				//        AddVarICodeOp ( iMainIndex, iInstrIndex, iMyGlobalIndex );
		}

		/***********************************************************************
		 * 
		 * PrintCompileStats ()
		 * 
		 * Prints miscellaneous compilation stats.
		 */

		public static void PrintCompileStats() {
				// ---- Calculate statistics

				// Symbols

				int iVarCount = 0, iArrayCount = 0, iGlobalCount = 0;

				// Traverse the list to count each symbol type

				for (int iCurrSymbolIndex = 0; iCurrSymbolIndex < g_SymbolTable.size(); ++iCurrSymbolIndex) {
						// Create a pointer to the current symbol structure

						SymbolNode pCurrSymbol = GetSymbolByIndex(iCurrSymbolIndex);

						// It's an array if the size is greater than 1

						if (pCurrSymbol.iSize > 1)
								++iArrayCount;

						// It's a variable otherwise

						else
								++iVarCount;

						// It's a global if it's stack index is nonnegative

						if (pCurrSymbol.iScope == 0)
								++iGlobalCount;
				}

				// Instructions

				int iInstrCount = 0;

				// Host API Calls

				int iHostAPICallCount = 0;

				// Traverse the list to count each symbol type

				for (int iCurrFuncIndex = 0; iCurrFuncIndex < g_FuncTable.size(); ++iCurrFuncIndex) {
						// Create a pointer to the current function structure

						FuncNode pCurrFunc = GetFuncByIndex(iCurrFuncIndex);

						// Determine if the function is part of the host API

						++iHostAPICallCount;

						// Add the function's I-code instructions to the running total

						iInstrCount += pCurrFunc.ICodeStream.size();
				}

				// Print out final calculations

				System.out.print(g_pstrOutputFilename + " created successfully!\n\n");
				System.out.println("Source Lines Processed: " + g_SourceCode.getSize());
				System.out.print("            Stack Size: ");
				if (g_ScriptHeader.iStackSize != 0)
						System.out.print("" + g_ScriptHeader.iStackSize);
				else
						System.out.print("Default");

				System.out.print("\n");

				System.out.print("              Priority: ");
				switch (g_ScriptHeader.iPriorityType) {
						case PRIORITY_USER:
								System.out.print("" + g_ScriptHeader.iUserPriority + " ms Timeslice");
								break;

						case PRIORITY_LOW:
								System.out.print(PRIORITY_LOW_KEYWORD);
								break;

						case PRIORITY_MED:
								System.out.print(PRIORITY_MED_KEYWORD);
								break;

						case PRIORITY_HIGH:
								System.out.print(PRIORITY_HIGH_KEYWORD);
								break;

						default:
								System.out.print("Default");
								break;
				}
				System.out.print("\n");

				System.out.println("  Instructions Emitted: " + iInstrCount);
				System.out.println("             Variables: " + iVarCount);
				System.out.println("                Arrays: " + iArrayCount);
				System.out.println("               Globals: " + iGlobalCount);
				System.out.println("       String Literals: " + g_StringTable.getSize());
				System.out.println("        Host API Calls: " + iHostAPICallCount);
				System.out.println("             Functions: " + g_FuncTable.size());

				System.out.print("      _Main () Present: ");
				if (g_ScriptHeader.iIsMainFuncPresent != 0)
						System.out.print("Yes (Index  " + g_ScriptHeader.iMainFuncIndex + " )");
				else
						System.out.print("No\n");
				System.out.print("\n");
		}

		/***********************************************************************
		 * 
		 * AssmblOutputFile ()
		 * 
		 * Invokes the XASM assembler to create an executable .XSE file from the
		 * resulting .XASM assembly file.
		 */

		public static void AssmblOutputFile() {
				// Command-line parameters to pass to XASM

				String[] ppstrCmmndLineParams = new String[3];

				// Set the first parameter to "XASM" (not that it really matters)

				ppstrCmmndLineParams[0] = "MASM";

				// Copy the .XASM filename into the second parameter

				ppstrCmmndLineParams[1] = g_pstrOutputFilename;

				// Set the third parameter to NULL

				ppstrCmmndLineParams[2] = "";

				// Invoke the assembler
				Runtime run = Runtime.getRuntime();
				try {
						run.exec(ppstrCmmndLineParams);
				} catch (IOException e) {
						e.printStackTrace();
				}

				//        spawnv ( P_WAIT, "XASM.exe", ppstrCmmndLineParams );

				// Free the command-line parameters

				ppstrCmmndLineParams = null;
		}

		/***********************************************************************
		 * 
		 * Exit ()
		 * 
		 * Exits the program.
		 */

		public static void Exit() {
				// Give allocated resources a chance to be freed

				ShutDown();

				// Exit the program

				Runtime.getRuntime().exit(0);
		}

		

		/**
		 * ***************************************************************************************
		 * <p/> GetFuncByIndex () <p/> Returns a FuncNode structure based on the
		 * specified index.
		 */

		public static FuncNode GetFuncByIndex(int iIndex) {
				// If the table is empty, return a NULL pointer

				if (g_FuncTable.size() == 0)
						return null;

				// Create a pointer to traverse the list

				// Traverse the list until the matching structure is found

				for (int iCurrNode = 0; iCurrNode < g_FuncTable.size(); ++iCurrNode) {
						// Create a pointer to the current function structure

						FuncNode pCurrFunc = (FuncNode) g_FuncTable.get(iCurrNode);// (FuncNode *)pCurrNode - > pData;

						// If the indices match, return the current pointer

						if (iIndex == pCurrFunc.iIndex)
								return pCurrFunc;

				}

				// The function was not found, so return a NULL pointer

				return null;
		}

		/**
		 * ***************************************************************************************
		 * <p/> GetFuncByName () <p/> Returns a FuncNode structure pointer
		 * corresponding to the specified name.
		 */

		public static FuncNode GetFuncByName(String pstrName) {
				// Local function node pointer

				FuncNode pCurrFunc;

				// Loop through each function in the table to find the match

				for (int iCurrFuncIndex = 0; iCurrFuncIndex <= g_FuncTable.size(); ++iCurrFuncIndex) {
						// Get the current function structure

						pCurrFunc = GetFuncByIndex(iCurrFuncIndex);

						// Return the function if the name matches

						if (pCurrFunc != null && Tool.stricmp(pCurrFunc.pstrName, pstrName))
								return pCurrFunc;
				}

				// The function was not found, so return a NULL pointer

				return null;
		}

		/**
		 * ***************************************************************************************
		 * <p/> AddFunc () <p/> Adds a function to the function table.
		 */

		public static int AddFunc(String pstrName, int iIsHostAPI) {
				// If a function already exists with the specified name, exit and return an invalid
				// index

				if (GetFuncByName(pstrName) != null)
						return -1;

				// Create a new function node

				FuncNode pNewFunc = new FuncNode();//(FuncNode )  malloc(sizeof(FuncNode));

				// Set the function's name

				pNewFunc.pstrName = pstrName;

				// Add the function to the list and get its index, but add one since the zero index is
				// reserved for the global scope
				int iIndex = g_FuncTable.size();
				//AddNode( & g_FuncTable, pNewFunc)+1;

				// Set the function node's index

				pNewFunc.iIndex = iIndex;

				// Set the host API flag

				pNewFunc.iIsHostAPI = iIsHostAPI;

				// Set the parameter count to zero

				pNewFunc.iParamCount = 0;

				// Clear the function's I-code block

				pNewFunc.ICodeStream = new LinkedList();

				// If the function was _Main (), set its flag and index in the header

				if (Tool.stricmp(pstrName, MAIN_FUNC_NAME)) {
						g_ScriptHeader.iIsMainFuncPresent = TRUE;
						g_ScriptHeader.iMainFuncIndex = iIndex;
				}
				g_FuncTable.add(pNewFunc);
				// Return the new function's index

				return iIndex;
		}

		/**
		 * ***************************************************************************************
		 * <p/> SetFuncParamCount () <p/> Sets a preexisting function's
		 * parameter count
		 */

		public static void SetFuncParamCount(int iIndex, int iParamCount) {
				// Get the function

				FuncNode pFunc = GetFuncByIndex(iIndex);

				// Set the parameter count

				pFunc.iParamCount = iParamCount;
		}

		// ---- Functions -----------------------------------------------------------------------------

		/***********************************************************************
		 * 
		 * GetSymbolByIndex ()
		 * 
		 * Returns a pointer to the symbol structure corresponding to the index.
		 */

		public static SymbolNode GetSymbolByIndex(int iIndex) {
				// If the table is empty, return a NULL pointer

				if (g_SymbolTable.size() == 0)
						return null;

				// Create a pointer to traverse the list

				// Traverse the list until the matching structure is found

				for (int iCurrNode = 0; iCurrNode < g_SymbolTable.size(); ++iCurrNode) {
						// Create a pointer to the current symbol structure

						SymbolNode pCurrSymbol = (SymbolNode) g_SymbolTable.get(iCurrNode);

						// If the indices match, return the symbol

						if (iIndex == pCurrSymbol.iIndex)
								return pCurrSymbol;

						// Otherwise move to the next node

				}

				// The symbol was not found, so return a NULL pointer

				return null;
		}

		/***********************************************************************
		 * 
		 * GetSymbolByIdent ()
		 * 
		 * Returns a pointer to the symbol structure corresponding to the
		 * identifier and scope.
		 */

		public static SymbolNode GetSymbolByIdent(String pstrIdent, int iScope) {
				// Local symbol node pointer

				SymbolNode pCurrSymbol;

				// Loop through each symbol in the table to find the match

				for (int iCurrSymbolIndex = 0; iCurrSymbolIndex < g_SymbolTable.size(); ++iCurrSymbolIndex) {
						// Get the current symbol structure

						pCurrSymbol = GetSymbolByIndex(iCurrSymbolIndex);

						// Return the symbol if the identifier and scope matches

						if (pCurrSymbol != null && Tool.stricmp(pCurrSymbol.pstrIdent, pstrIdent)
						                && (pCurrSymbol.iScope == iScope || pCurrSymbol.iScope == 0))
								return pCurrSymbol;
				}

				// The symbol was not found, so return a NULL pointer

				return null;
		}

		/***********************************************************************
		 * 
		 * GetSizeByIndent ()
		 * 
		 * Returns a variable's size based on its identifier.
		 */

		public static int GetSizeByIdent(String pstrIdent, int iScope) {
				// Get the symbol's information

				SymbolNode pSymbol = GetSymbolByIdent(pstrIdent, iScope);

				// Return its size

				return pSymbol.iSize;
		}

		/***********************************************************************
		 * 
		 * AddSymbol ()
		 * 
		 * Adds a symbol to the symbol table.
		 */

		public static int AddSymbol(String pstrIdent, int iSize, int iScope, int iType) {
				// If a label already exists

				if (GetSymbolByIdent(pstrIdent, iScope) != null)
						return -1;

				// Create a new symbol node

				SymbolNode pNewSymbol = new SymbolNode();

				// Initialize the new label

				pNewSymbol.pstrIdent = pstrIdent;
				pNewSymbol.iSize = iSize;
				pNewSymbol.iScope = iScope;
				pNewSymbol.iType = iType;

				// Add the symbol to the list and get its index

				int iIndex = g_SymbolTable.size();

				// Set the symbol node's index

				pNewSymbol.iIndex = iIndex;

				g_SymbolTable.add(pNewSymbol);
				// Return the new symbol's index

				return iIndex;
		}

		/***********************************************************************
		 * 
		 * GetICodeInstrByImpIndex ()
		 * 
		 * Returns an I-code instruction structure based on its implicit index.
		 */

		public static ICodeNode GetICodeNodeByImpIndex(int iFuncIndex, int iInstrIndex) {
				// Get the function

				FuncNode pFunc = GetFuncByIndex(iFuncIndex);

				// If the stream is empty, return a NULL pointer

				if (pFunc.ICodeStream.size() == 0)
						return null;

				// Create a pointer to traverse the list

				// Traverse the list until the matching index is found

				for (int iCurrNode = 0; iCurrNode < pFunc.ICodeStream.size(); ++iCurrNode) {
						// If the implicit index matches, return the instruction

						if (iInstrIndex == iCurrNode)
								return (ICodeNode) (pFunc.ICodeStream).get(iCurrNode);

						// Otherwise move to the next node

				}

				// The instruction was not found, so return a NULL pointer

				return null;
		}

		/***********************************************************************
		 * 
		 * AddICodeSourceLine ()
		 * 
		 * Adds a line of source code annotation to the I-code stream of the
		 * specified function.
		 */

		public static void AddICodeSourceLine(int iFuncIndex, String pstrSourceLine) {
				// Get the function to which the source line should be added

				FuncNode pFunc = GetFuncByIndex(iFuncIndex);

				// Create an I-code node structure to hold the line

				ICodeNode pSourceLineNode = new ICodeNode();

				// Set the node type to source line

				pSourceLineNode.iType = ICODE_NODE_SOURCE_LINE;

				// Set the source line string pointer

				pSourceLineNode.pstrSourceLine = pstrSourceLine;

				// Add the instruction node to the list and get the index
				pFunc.ICodeStream.add(pSourceLineNode);
		}

		/***********************************************************************
		 * 
		 * AddICodeInstr ()
		 * 
		 * Adds an instruction to the local I-code stream of the specified
		 * function.
		 */

		public static int AddICodeInstr(int iFuncIndex, int iOpcode) {
				// Get the function to which the instruction should be added

				FuncNode pFunc = GetFuncByIndex(iFuncIndex);

				// Create an I-code node structure to hold the instruction

				ICodeNode pInstrNode = new ICodeNode();

				// Set the node type to instruction

				pInstrNode.iType = ICODE_NODE_INSTR;

				// Set the opcode
				pInstrNode.Instr = new ICodeInstr();
				pInstrNode.Instr.iOpcode = iOpcode;

				// Clear the operand list

				pInstrNode.Instr.OpList = new LinkedList();

				// Add the instruction node to the list and get the index

				int iIndex = pFunc.ICodeStream.size();
				pFunc.ICodeStream.add(pInstrNode);
				// Return the index

				return iIndex;
		}

		/***********************************************************************
		 * 
		 * GetICodeOpByIndex ()
		 * 
		 * Returns an I-code instruction's operand at the specified index.
		 */

		public static Op GetICodeOpByIndex(ICodeNode pInstr, int iOpIndex) {
				// If the list is empty, return a NULL pointer

				if (pInstr.Instr.OpList.size() == 0)
						return null;

				// Create a pointer to traverse the list

				// Traverse the list until the matching index is found

				for (int iCurrNode = 0; iCurrNode < pInstr.Instr.OpList.size(); ++iCurrNode) {
						// If the index matches, return the operand

						if (iOpIndex == iCurrNode)
								return (Op) pInstr.Instr.OpList.get(iCurrNode);

						// Otherwise move to the next node

				}

				// The operand was not found, so return a NULL pointer

				return null;
		}

		/***********************************************************************
		 * 
		 * AddICodeOp ()
		 * 
		 * Adds an operand to the specified I-code instruction.
		 */

		public static void AddICodeOp(int iFuncIndex, int iInstrIndex, Op Value) {
				// Get the I-code node

				ICodeNode pInstr = GetICodeNodeByImpIndex(iFuncIndex, iInstrIndex);

				// Make a physical copy of the operand structure

				Op pValue = new Op();
				Tool.memcpy(Value, pValue);

				// Add the instruction
				pInstr.Instr.OpList.add(pValue);
		}

		/***********************************************************************
		 * 
		 * AddIntICodeOp ()
		 * 
		 * Adds an integer literal operand to the specified I-code instruction.
		 */

		public static void AddIntICodeOp(int iFuncIndex, int iInstrIndex, int iValue) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to integer and store the value

				Value.iType = OP_TYPE_INT;
				Value.unionValue = iValue;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * AddFloatICodeOp ()
		 * 
		 * Adds a float literal operand to the specified I-code instruction.
		 */

		public static void AddFloatICodeOp(int iFuncIndex, int iInstrIndex, float fValue) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to float and store the value

				Value.iType = OP_TYPE_FLOAT;
				Value.fFloatLiteral = fValue;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * AddStringICodeOp ()
		 * 
		 * Adds a string literal operand to the specified I-code instruction.
		 */

		public static void AddStringICodeOp(int iFuncIndex, int iInstrIndex, int iStringIndex) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to string index and store the index

				Value.iType = OP_TYPE_STRING_INDEX;
				Value.unionValue = iStringIndex;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * AddVarICodeOp ()
		 * 
		 * Adds a variable operand to the specified I-code instruction.
		 */

		public static void AddVarICodeOp(int iFuncIndex, int iInstrIndex, int iSymbolIndex) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to variable and store the symbol index

				Value.iType = OP_TYPE_VAR;
				Value.unionValue = iSymbolIndex;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * AddArrayIndexAbsICodeOp ()
		 * 
		 * Adds an array indexed with a literal integer value operand to the
		 * specified I-code instruction.
		 */

		public static void AddArrayIndexAbsICodeOp(int iFuncIndex, int iInstrIndex, int iArraySymbolIndex, int iOffset) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to array index absolute and store the indices

				Value.iType = OP_TYPE_ARRAY_INDEX_ABS;
				Value.unionValue = iArraySymbolIndex;
				Value.iOffset = iOffset;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * AddArrayIndexVarICodeOp ()
		 * 
		 * Adds an array indexed with a variable operand to the specified I-code
		 * instruction.
		 */

		public static void AddArrayIndexVarICodeOp(int iFuncIndex, int iInstrIndex, int iArraySymbolIndex,
		                int iOffsetSymbolIndex) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to array index variable and store the indices

				Value.iType = OP_TYPE_ARRAY_INDEX_VAR;
				Value.unionValue = iArraySymbolIndex;
				Value.iOffsetSymbolIndex = iOffsetSymbolIndex;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * AddFuncICodeOp ()
		 * 
		 * Adds a function operand to the specified I-code instruction.
		 */

		public static void AddFuncICodeOp(int iFuncIndex, int iInstrIndex, int iOpFuncIndex) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to function index and store the index

				Value.iType = OP_TYPE_FUNC_INDEX;
				Value.unionValue = iOpFuncIndex;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * AddRegICodeOp ()
		 * 
		 * Adds a register operand to the specified I-code instruction.
		 */

		public static void AddRegICodeOp(int iFuncIndex, int iInstrIndex, int iRegCode) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to register and store the code (even though we'll ignore it)

				Value.iType = OP_TYPE_REG;
				Value.unionValue = iRegCode;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * AddJumpTargetICodeOp ()
		 * 
		 * Adds a jump target operand to the specified I-code instruction.
		 */

		public static void AddJumpTargetICodeOp(int iFuncIndex, int iInstrIndex, int iTargetIndex) {
				// Create an operand structure to hold the new value

				Op Value = new Op();

				// Set the operand type to register and store the code (even though we'll ignore it)

				Value.iType = OP_TYPE_JUMP_TARGET_INDEX;
				Value.unionValue = iTargetIndex;

				// Add the operand to the instruction

				AddICodeOp(iFuncIndex, iInstrIndex, Value);
		}

		/***********************************************************************
		 * 
		 * GetNextJumpTargetIndex ()
		 * 
		 * Returns the next target index.
		 */

		public static int GetNextJumpTargetIndex() {
				// Return and increment the current target index

				return g_iCurrJumpTargetIndex++;
		}

		/***********************************************************************
		 * 
		 * AddICodeJumpTarget ()
		 * 
		 * Adds a jump target to the I-code stream.
		 */

		public static void AddICodeJumpTarget(int iFuncIndex, int iTargetIndex) {
				// Get the function to which the source line should be added

				FuncNode pFunc = GetFuncByIndex(iFuncIndex);

				// Create an I-code node structure to hold the line

				ICodeNode pSourceLineNode = new ICodeNode();

				// Set the node type to jump target

				pSourceLineNode.iType = ICODE_NODE_JUMP_TARGET;

				// Set the jump target

				pSourceLineNode.iJumpTargetIndex = iTargetIndex;

				// Add the instruction node to the list and get the index
				pFunc.ICodeStream.add(pSourceLineNode);
		}

		// ---- Functions -----------------------------------------------------------------------------

		/***********************************************************************
		 * 
		 * PreprocessSourceFile ()
		 * 
		 * Preprocesses the source file to expand preprocessor directives and
		 * strip comments.
		 */

		public static void PreprocessSourceFile() {
				// Are we inside a block comment?

				int iInBlockComment = FALSE;

				// Are we inside a string?

				int iInString = FALSE;

				// Traverse the source code
				int pCurrNode = 0;
				while (true) {
						// Create local copy of the current line
						char[] pstrCurrLine = ((String) g_SourceCode.getNode(pCurrNode)).toCharArray();

						// ---- Scan for comments
						boolean comment = false;
						int len = 0;
						for (int iCurrCharIndex = 0; iCurrCharIndex < pstrCurrLine.length; ++iCurrCharIndex) {
								// If the current character is a quote, toggle the in string flag

								if (pstrCurrLine[iCurrCharIndex] == '"') {
										if (iInString == TRUE)
												iInString = FALSE;
										else
												iInString = TRUE;
								}

								// Check for a single-line comment, and terminate the rest of the line if one is
								// found

								if (pstrCurrLine[iCurrCharIndex] == '/' && pstrCurrLine[iCurrCharIndex + 1] == '/'
								                && iInString == FALSE && iInBlockComment == FALSE) {
										pstrCurrLine[iCurrCharIndex] = '\n';
										//                    pstrCurrLine [ iCurrCharIndex + 1 ] = '\0';
										comment = true;
										len = iCurrCharIndex + 1;
										break;
								}

								// Check for a block comment

								if (pstrCurrLine[iCurrCharIndex] == '/' && pstrCurrLine[iCurrCharIndex + 1] == '*'
								                && iInString == FALSE && iInBlockComment == FALSE) {
										iInBlockComment = TRUE;
								}

								// Check for the end of a block comment

								if (pstrCurrLine[iCurrCharIndex] == '*' && iCurrCharIndex + 1 < pstrCurrLine.length
								                && pstrCurrLine[iCurrCharIndex + 1] == '/' && iInBlockComment == TRUE) {
										pstrCurrLine[iCurrCharIndex] = ' ';
										pstrCurrLine[iCurrCharIndex + 1] = ' ';
										iInBlockComment = FALSE;
								}

								// If we're inside a block comment, replace the current character with
								// whitespace

								if (iInBlockComment == TRUE) {
										if (pstrCurrLine[iCurrCharIndex] != '\n')
												pstrCurrLine[iCurrCharIndex] = ' ';
								}
						}
						if (comment)
								g_SourceCode.setNode(new String(pstrCurrLine, 0, len), pCurrNode);
						else
								g_SourceCode.setNode(new String(pstrCurrLine), pCurrNode);
						pCurrNode++;

						// ---- Move to the next node, and exit the loop if the end of the code is reached

						//            pNode = pNode->pNext;
						if (pCurrNode > g_SourceCode.getSize() - 1)
								break;
				}

				/*
				 * Implementation of the #include and #define preprocessor
				 * directives could go here
				 */
				defineSort();
				//        Hashtable define = new Hashtable ();
				//        try
				//        {
				//            for(pCurrNode = 0;pCurrNode < g_SourceCode.getSize(); ++pCurrNode)
				//            {
				//
				//                String pLine = (String)g_SourceCode.getNode(pCurrNode);
				//                if(pLine.trim() == null ) continue;
				//                if(define.size() > 0){
				//                    Enumeration e = define.keys();
				//                    while (e.hasMoreElements())
				//                    {
				//                        String s = (String) e.nextElement();
				//                    // \\bs\\b
				//                        if(pLine.contains("$"+s)){
				//                            pLine = pLine.replace("$"+s,(String)define.get(s));
				//                            g_SourceCode.setNode(pLine,pCurrNode);
				//                        }
				//                    }
				//                }
				//                String[] params = pLine.split("\\s+",2);
				//                if(params[0].equals("#include")){
				//                    params[1] = (params[1].replace("\"","")).trim();
				//                    fromIncludeFile(ParseUtil.getFullPathFilename(params[1]),g_SourceCode,pCurrNode);
				//                    continue;
				//                }
				//                if(params[0].equals("#define")){
				//                    String[] preStr = pLine.split("\\s+",3);
				//                    define.put(preStr[1].trim(),preStr[2].trim());
				////                    g_SourceCode.setNode("\n",pCurrNode);
				//                    g_SourceCode.delNode(pCurrNode);
				//                    if(pCurrNode > 0) pCurrNode --;
				//                    continue;
				//                }
				//            }
				//            System.out.println("Msc.PreprocessSourceFile");
				//        } catch (Exception e)
				//        {
				//            e.printStackTrace();
				//        }

		}

		/**
		 * 锟斤拷锟斤拷 define 锟斤拷锟斤拷
		 */
		private static void defineSort() {
				int pCurrNode = 0;
				LinkedList define = new LinkedList();

				try {
						for (pCurrNode = 0; pCurrNode < g_SourceCode.getSize(); ++pCurrNode) {

								String pLine = (String) g_SourceCode.getNode(pCurrNode);
								if (pLine.trim() == null)
										continue;
								if (define.size() > 0) {
										String[] buff;
										for (int i = 0; i < define.size(); i++) {
												buff = (String[]) define.get(i);
												if (pLine.contains("$" + buff[0])) {
														pLine = pLine.replace("$" + buff[0], buff[1]);
														g_SourceCode.setNode(pLine, pCurrNode);
												}
										}
								}
								String[] params = pLine.split("\\s+", 2);
								if (params[0].equals("#include")) {
										params[1] = (params[1].replace("\"", "")).trim();
										fromIncludeFile(ParseUtil.getFullPathFilename(params[1]), g_SourceCode,
										                pCurrNode);
										continue;
								}
								if (params[0].equals("#define")) {
										String[] preStr = pLine.split("\\s+", 3);
										addKeyValue(define, preStr[1].trim(), preStr[2].trim());

										g_SourceCode.delNode(pCurrNode);
										if (pCurrNode > 0)
												pCurrNode--;
										continue;
								}
						}
						System.out.println("Msc.PreprocessSourceFile");
				} catch (Exception e) {
						e.printStackTrace();
				}
		}

		private static void addKeyValue(LinkedList list, String key, String value) {
				if (list.size() == 0) {
						list.add(new String[] { key, value });
						return;
				}
				String[] buff;
				for (int i = 0; i < list.size(); i++) {
						buff = (String[]) list.get(i);
						if (key.length() > buff[0].length()) {
								list.add(i, new String[] { key, value });
								return;
						}
				}
				list.add(new String[] { key, value });
		}

		public static void fromIncludeFile(String name, Link sourceCode, int insert) {

				try {
						BufferedReader g_pSourceFile = new BufferedReader(new FileReader(name));
						String s = new String();
						//#include 注锟酵碉拷
						sourceCode.setNode("\n", insert);
						insert++;
						// Count the number of source lines
						while ((s = removeComment(g_pSourceFile.readLine())) != null) {
								sourceCode.insertNode(s + "\n", insert);
								insert++;
						}
						g_pSourceFile.close();

				} catch (FileNotFoundException e) {
						e.printStackTrace();
						Error.ExitOnError("Could not open include file" + name + " for input");
				} catch (IOException e) {
						e.printStackTrace();
						Error.ExitOnError("DataInputStream invailable");
				}

		}

		private static String removeComment(String currLine) {
				// Are we inside a block comment?
				if (currLine == null)
						return null;
				int iInBlockComment = FALSE;

				// Are we inside a string?

				int iInString = FALSE;

				// Create local copy of the current line
				char[] pstrCurrLine = currLine.toCharArray();

				// ---- Scan for comments
				boolean comment = false;
				int len = 0;
				for (int iCurrCharIndex = 0; iCurrCharIndex < pstrCurrLine.length; ++iCurrCharIndex) {
						// If the current character is a quote, toggle the in string flag

						if (pstrCurrLine[iCurrCharIndex] == '"') {
								if (iInString == TRUE)
										iInString = FALSE;
								else
										iInString = TRUE;
						}

						// Check for a single-line comment, and terminate the rest of the line if one is
						// found

						if (pstrCurrLine[iCurrCharIndex] == '/' && pstrCurrLine[iCurrCharIndex + 1] == '/'
						                && iInString == FALSE && iInBlockComment == FALSE) {
								pstrCurrLine[iCurrCharIndex] = '\n';
								//                    pstrCurrLine [ iCurrCharIndex + 1 ] = '\0';
								comment = true;
								len = iCurrCharIndex + 1;
								break;
						}

						// Check for a block comment

						if (pstrCurrLine[iCurrCharIndex] == '/' && pstrCurrLine[iCurrCharIndex + 1] == '*'
						                && iInString == FALSE && iInBlockComment == FALSE) {
								iInBlockComment = TRUE;
						}

						// Check for the end of a block comment

						if (pstrCurrLine[iCurrCharIndex] == '*' && iCurrCharIndex + 1 < pstrCurrLine.length
						                && pstrCurrLine[iCurrCharIndex + 1] == '/' && iInBlockComment == TRUE) {
								pstrCurrLine[iCurrCharIndex] = ' ';
								pstrCurrLine[iCurrCharIndex + 1] = ' ';
								iInBlockComment = FALSE;
						}

						// If we're inside a block comment, replace the current character with
						// whitespace

						if (iInBlockComment == TRUE) {
								if (pstrCurrLine[iCurrCharIndex] != '\n')
										pstrCurrLine[iCurrCharIndex] = ' ';
						}
				}
				if (comment)
						return new String(pstrCurrLine, 0, len);
				else
						return new String(pstrCurrLine);
		}

		/***********************************************************************
		 * 
		 * AddString ()
		 * 
		 * Adds a string to a linked list, blocking duplicate entries
		 */

		public static int AddString(Link pList, String pstrString) {
				// ---- First check to see if the string is already in the list

				// Create a node to traverse the list

				// Loop through each node in the list

				for (int iCurrNode = 0; iCurrNode < pList.getSize(); ++iCurrNode) {
						// If the current node's string equals the specified string, return its index

						if (Tool.strcmp((String) pList.getNode(iCurrNode), pstrString))
								return iCurrNode;

				}

				// ---- Add the new string, since it wasn't added

				// Add the string to the list and return its index

				return pList.addNode(pstrString);
		}

		/***********************************************************************
		 * 
		 * GetStringByIndex ()
		 * 
		 * Returns a string from a linked list based on its index.
		 */

		public static String GetStringByIndex(Link pList, int iIndex) {
				// Create a node to traverse the list

				// Loop through each node in the list

				for (int iCurrNode = 0; iCurrNode < pList.getSize(); ++iCurrNode) {
						// If the current node's string equals the specified string, return its index

						if (iIndex == iCurrNode)
								return (String) pList.getNode(iCurrNode);

				}

				// Return a null string if the index wasn't found

				return null;
		}
		
		// ---- Main ----------------------------------------------------------------------------------

		public static void main(String[] args) {
				
				if(args == null || args .length < 1){
						args = new String[2];
						args[0] = System.getProperty("user.dir")+"/hello.XSS";
						args[1] = "-n";
				}
				
				// Print the logo

				PrintLogo();

				/*
				 * The following section is commented out to allow our hardcoded
				 * script to be added to the I-code modules and tables without
				 * disturbance from other modules. Of course, this will all be
				 * vitally important in chapter 15, when the compiler actually
				 * functions as it should.
				 */

				// Validate the command line argument count
				int argc = args.length;
				if (argc < 1) {
						// If at least one filename isn't present, print the usage info and exit

						PrintUsage();
						return;
				}

				// Verify the filenames

				VerifyFilenames(argc, args);

				// Initialize the compiler

				Init();

				// Read in the command line parameters

				ReadCmmndLineParams(argc, args);

				// ---- Begin the compilation process (front end)

				// Load the source file into memory

				LoadSourceFile();

				// Preprocess the source file

				PreprocessSourceFile();

				// ---- Compile the source code to I-code

				System.out.print("Compiling " + g_pstrSourceFilename + ".\n\n");
				CompileSourceFile();

				// ---- Emit XVM assembly from the I-code representation (back end)

				CodeEmit.EmitCode();

				// Print out compilation statistics

				PrintCompileStats();

				// Free resources and perform general cleanup

				ShutDown();
				if(g_pstrOutputFilename != null){
						FilterInstruction.loadCommands(g_pstrOutputFilename);
				}
				// Invoke XASM to assemble the output file to create the .XSE, unless the user requests
				// otherwise

				if (g_iGenerateXSE != FALSE)
						AssmblOutputFile();

				// Delete the output (assembly) file unless the user requested it to be preserved

				if (g_iPreserveOutputFile == TRUE)
						g_pstrOutputFilename = null;

				return;
		}
}
