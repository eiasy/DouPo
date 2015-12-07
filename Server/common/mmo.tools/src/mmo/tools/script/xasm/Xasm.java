package mmo.tools.script.xasm;

/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 11:30:57
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import mmo.tools.script.xasm.struct.ConstData;
import mmo.tools.script.xasm.struct.FuncNode;
import mmo.tools.script.xasm.struct.Instr;
import mmo.tools.script.xasm.struct.InstrLookup;
import mmo.tools.script.xasm.struct.LabelNode;
import mmo.tools.script.xasm.struct.Lexer;
import mmo.tools.script.xasm.struct.Opstruct;
import mmo.tools.script.xasm.struct.ScriptHeader;
import mmo.tools.script.xasm.struct.SymbolNode;



public class Xasm {
		// ---- Global Variables ----------------------------------------------------------------------

		// ---- Lexer -----------------------------------------------------------------------------

		static Lexer                g_Lexer;                                                         // The lexer

		// ---- Source Code -----------------------------------------------------------------------

		static char[][]             g_ppstrSourceCode;                                               // Pointer to dynamically allocated
		// array of string pointers.
		static int                  g_iSourceCodeSize;                                               // Number of source lines

		static BufferedReader       g_pSourceFile;                                                   // Source code file pointer

		public static String        g_pstrSourceFilename;
		public static String        g_pstrExecFilename;
		//		char g_pstrSourceFilename [ MAX_FILENAME_SIZE ],	// Source code filename
		//		     g_pstrExecFilename [ MAX_FILENAME_SIZE ];	// Executable filename

		// ---- Script ----------------------------------------------------------------------------

		static ScriptHeader         g_ScriptHeader;                                                  // Script header data

		static int                  g_iIsSetStackSizeFound;                                          // Has the SetStackSize directive been
		static int                  g_iIsSetPriorityFound;                                           // Has the SetPriority directive been
		// found?

		// ---- Instruction Lookup Table ----------------------------------------------------------

		public static InstrLookup[] g_InstrTable = new InstrLookup[ConstData.MAX_INSTR_LOOKUP_COUNT]; // The master instruction
		// lookup table

		// ---- Assembled Instruction Stream ------------------------------------------------------

		public static Instr[]       g_pInstrStream;                                                  // Pointer to a dynamically allocated
		// instruction stream
		static int                  g_iInstrStreamSize;                                              // The number of instructions

		static int                  g_iCurrInstrIndex;                                               // The current instruction's index

		// ---- Function Table --------------------------------------------------------------------

		static LinkedList           g_FuncTable;                                                     // The function table

		// ---- Label Table -----------------------------------------------------------------------

		static LinkedList           g_LabelTable;                                                    // The label table

		// ---- Symbol Table ----------------------------------------------------------------------

		static LinkedList           g_SymbolTable;                                                   // The symbol table

		// ---- String Table ----------------------------------------------------------------------

		static LinkedList           g_StringTable;                                                   // The string table

		// ---- Host API Call Table ---------------------------------------------------------------

		static LinkedList           g_HostAPICallTable;                                              // The host API call table

		// ---- Misc ------------------------------------------------------------------------------
		/**
		 * *************************************************************************************** <p/> PrintLogo ()
		 * <p/> Prints out logo/credits information.
		 */

		private static void PrintLogo() {
				System.out.println("XASM");
				System.out.println("MobileScript Assembler Version " + ConstData.VERSION_MAJOR + "." + ConstData.VERSION_MINOR);
				System.out.println("Written by Cui Rongzhou");
		}

		/**
		 * *************************************************************************************** <p/> PrintUsage ()
		 * <p/> Prints out usage information.
		 */

		private static void PrintUsage() {
				System.out.println("Usage:\tXASM Source.XASM [Executable.XSE]");
				System.out.println("");
				System.out.println("\t- File extensions are not required.");
				System.out.println("\t- Executable name is optional; source name is used by default.");
		}

		// ---- Main ------------------------------------------------------------------------------
		/***************************************************************************************************************
		 * 
		 * Init ()
		 * 
		 * Initializes the assembler.
		 */

		private static void Init() {
				// Initialize the master instruction lookup table

				InitInstrTable();

				// Initialize tables
				g_SymbolTable = new LinkedList();
				g_LabelTable = new LinkedList();
				g_FuncTable = new LinkedList();
				g_StringTable = new LinkedList();
				g_HostAPICallTable = new LinkedList();
		}

		/***************************************************************************************************************
		 * 
		 * ShutDown ()
		 * 
		 * Frees any dynamically allocated resources back to the system.
		 */

		private static void ShutDown() {
				// ---- Free source code array

				// Free each source line individually

				for (int iCurrLineIndex = 0; iCurrLineIndex < g_iSourceCodeSize; ++iCurrLineIndex)
						g_ppstrSourceCode[iCurrLineIndex] = null;

				// Now free the base pointer

				g_ppstrSourceCode = null;

				// ---- Free the assembled instruction stream

				if (g_pInstrStream != null) {
						// Free each instruction's operand list

						for (int iCurrInstrIndex = 0; iCurrInstrIndex < g_iInstrStreamSize; ++iCurrInstrIndex)
								if (g_pInstrStream[iCurrInstrIndex].pOpList != null)
										g_pInstrStream[iCurrInstrIndex].pOpList = null;

						// Now free the stream itself
						g_pInstrStream = null;
				}

				// ---- Free the tables
				g_SymbolTable = null;
				g_LabelTable = null;
				g_FuncTable = null;
				g_StringTable = null;
				g_HostAPICallTable = null;
		}

		/***************************************************************************************************************
		 * 
		 * LoadSourceFile ()
		 * 
		 * Loads the source file into memory.
		 */

		private static void LoadSourceFile() {
				// Open the source file in binary mode
				g_pSourceFile = null;
				try {
						g_pSourceFile = new BufferedReader(new FileReader(g_pstrSourceFilename));
						String s = new String();
						// Count the number of source lines
						while ((s = g_pSourceFile.readLine()) != null) {
								g_iSourceCodeSize++;
						}
						//            ++ g_iSourceCodeSize;
						g_pSourceFile.close();
						// Reopen the source file in ASCII mode
						g_pSourceFile = new BufferedReader(new FileReader(g_pstrSourceFilename));

				} catch (FileNotFoundException e) {
						e.printStackTrace();
						ExitOnError("Could not open source file");
				} catch (IOException e) {
						e.printStackTrace();
						ExitOnError("DataInputStream invailable");
				}

				// Allocate an array of strings to hold each source line
				//
				//        if ( ! ( g_ppstrSourceCode = ( char ** ) malloc ( g_iSourceCodeSize * sizeof ( char * ) ) ) )
				//            ExitOnError ( "Could not allocate space for source code" );
				g_ppstrSourceCode = new char[g_iSourceCodeSize][];
				// Read the source code in from the file
				String line = null;
				for (int iCurrLineIndex = 0; iCurrLineIndex < g_iSourceCodeSize; ++iCurrLineIndex) {
						// Allocate space for the line

						//            if ( ! ( g_ppstrSourceCode [ iCurrLineIndex ] = ( char * ) malloc ( MAX_SOURCE_LINE_SIZE + 1 ) ) )
						//                ExitOnError ( "Could not allocate space for source line" );
						// Read in the current line
						try {
								line = g_pSourceFile.readLine();

								if (line == null)
										continue;
								//                g_ppstrSourceCode [ iCurrLineIndex ] = new char[line.length() + 1 ];
								//                System.arraycopy(line.toCharArray(),0,g_ppstrSourceCode[iCurrLineIndex],0,line.length());
						} catch (IOException e) {
								e.printStackTrace();
						}

						// Strip comments and trim whitespace

						//            Lib.StripComments ( g_ppstrSourceCode [ iCurrLineIndex ] );
						//            Lib.TrimWhitespace ( g_ppstrSourceCode [ iCurrLineIndex ] );
						line = Lib.StripComments(line.toCharArray());
						line = line.trim();
						g_ppstrSourceCode[iCurrLineIndex] = new char[line.length() + 1];
						System.arraycopy(line.toCharArray(), 0, g_ppstrSourceCode[iCurrLineIndex], 0, line.length());
						// Make sure to add a new newline if it was removed by the stripping of the
						// comments and whitespace. We do this by checking the character right before
						// the null terminator to see if it's \n. If not, we move the terminator over
						// by one and add it. We use strlen () to find the position of the newline
						// easily.
						//            System.out.println("Masm.LoadSourceFile line:"+line);
						g_ppstrSourceCode[iCurrLineIndex][line.length()] = '\n';
						//            g_ppstrSourceCode [ iCurrLineIndex ] [ line.length() +1] = '\0';

				}

				// Close the source file
				try {
						g_pSourceFile.close();
				} catch (IOException e) {
						e.printStackTrace();
				}

		}

		/***************************************************************************************************************
		 * 
		 * AssmblSourceFile ()
		 * 
		 * Initializes the master instruction lookup table.
		 */

		private static void AssmblSourceFile() {
				// ---- Initialize the script header
				g_ScriptHeader = new ScriptHeader();
				g_ScriptHeader.iStackSize = 0;
				g_ScriptHeader.iIsMainFuncPresent = ConstData.FALSE;

				// ---- Set some initial variables

				g_iInstrStreamSize = 0;
				g_iIsSetStackSizeFound = ConstData.FALSE;
				g_iIsSetPriorityFound = ConstData.FALSE;
				g_ScriptHeader.iGlobalDataSize = 0;

				// Set the current function's flags and variables

				int iIsFuncActive = ConstData.FALSE;
				FuncNode pCurrFunc = null;
				int iCurrFuncIndex = 0;
				//		char[] pstrCurrFuncName = new char[ ConstData.MAX_IDENT_SIZE ];
				String pstrCurrFuncName = null;// = new char[ ConstData.MAX_IDENT_SIZE ];
				int iCurrFuncParamCount = 0;
				int iCurrFuncLocalDataSize = 0;

				// Create an instruction definition structure to hold instruction information when
				// dealing with instructions.

				InstrLookup CurrInstr;

				// ---- Perform first pass over the source

				// Reset the lexer

				ResetLexer();

				// Loop through each line of code

				while (true) {
						// Get the next token and make sure we aren't at the end of the stream

						if (GetNextToken() == ConstData.END_OF_TOKEN_STREAM)
								break;

						// Check the initial token

						switch (g_Lexer.CurrToken) {
								// ---- Start by checking for directives

								// SetStackSize

								case ConstData.TOKEN_TYPE_SETSTACKSIZE:

										// SetStackSize can only be found in the global scope, so make sure we
										// aren't in a function.

										if (iIsFuncActive == 1)
												ExitOnCodeError(ConstData.ERROR_MSSG_LOCAL_SETSTACKSIZE);

										// It can only be found once, so make sure we haven't already found it

										if (g_iIsSetStackSizeFound == 1)
												ExitOnCodeError(ConstData.ERROR_MSSG_MULTIPLE_SETSTACKSIZES);

										// Read the next lexeme, which should contain the stack size

										if (GetNextToken() != ConstData.TOKEN_TYPE_INT)
												ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_STACK_SIZE);

										// Convert the lexeme to an integer value from its string
										// representation and store it in the script header

										g_ScriptHeader.iStackSize = Integer.parseInt(GetCurrLexeme());

										// Mark the presence of SetStackSize for future encounters

										g_iIsSetStackSizeFound = ConstData.TRUE;

										break;
								// SetPriority

								case ConstData.TOKEN_TYPE_SETPRIORITY:

										// SetPriority can only be found in the global scope, so make sure we
										// aren't in a function.

										if (iIsFuncActive == ConstData.TRUE)
												ExitOnCodeError(ConstData.ERROR_MSSG_LOCAL_SETPRIORITY);

										// It can only be found once, so make sure we haven't already found it

										if (g_iIsSetPriorityFound == ConstData.TRUE)
												ExitOnCodeError(ConstData.ERROR_MSSG_MULTIPLE_SETPRIORITIES);

										GetNextToken();

										// Determin

										switch (g_Lexer.CurrToken) {
												// An integer lexeme means the user is defining a specific priority

												case ConstData.TOKEN_TYPE_INT:

														// Convert the lexeme to an integer value from its string
														// representation and store it in the script header

														g_ScriptHeader.iUserPriority = Integer.parseInt(GetCurrLexeme());

														// Set the user priority flag

														g_ScriptHeader.iStackSize = ConstData.PRIORITY_USER;

														break;

												// An identifier means it must be one of the predefined priority
												// ranks

												case ConstData.TOKEN_TYPE_IDENT:

														// Determine which rank was specified

														if (g_Lexer.pstrCurrLexeme.equals(ConstData.PRIORITY_LOW_KEYWORD))
																g_ScriptHeader.iPriorityType = ConstData.PRIORITY_LOW;
														else if (g_Lexer.pstrCurrLexeme.equals(ConstData.PRIORITY_MED_KEYWORD))
																g_ScriptHeader.iPriorityType = ConstData.PRIORITY_MED;
														else if (g_Lexer.pstrCurrLexeme.equals(ConstData.PRIORITY_HIGH_KEYWORD))
																g_ScriptHeader.iPriorityType = ConstData.PRIORITY_HIGH;
														else
																ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_PRIORITY);

														break;

												// Anything else should cause an error

												default:
														ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_PRIORITY);
										}

										// Mark the presence of SetStackSize for future encounters

										g_iIsSetPriorityFound = ConstData.TRUE;

										break;

								// Var/Var []

								case ConstData.TOKEN_TYPE_VAR: {
										// Get the variable's identifier

										if (GetNextToken() != ConstData.TOKEN_TYPE_IDENT)
												ExitOnCodeError(ConstData.ERROR_MSSG_IDENT_EXPECTED);
										String pstrIdent = GetCurrLexeme();

										// Now determine its size by finding out if it's an array or not, otherwise
										// default to 1.

										int iSize = 1;

										// Find out if an opening bracket lies ahead

										if (GetLookAheadChar() == '[') {
												// Validate and consume the opening bracket

												if (GetNextToken() != ConstData.TOKEN_TYPE_OPEN_BRACKET)
														ExitOnCharExpectedError('[');

												// We're parsing an array, so the next lexeme should be an integer
												// describing the array's size

												if (GetNextToken() != ConstData.TOKEN_TYPE_INT)
														ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_ARRAY_SIZE);

												// Convert the size lexeme to an integer value

												iSize = Integer.parseInt(GetCurrLexeme());

												// Make sure the size is valid, in that it's greater than zero

												if (iSize <= 0)
														ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_ARRAY_SIZE);

												// Make sure the closing bracket is present as well

												if (GetNextToken() != ConstData.TOKEN_TYPE_CLOSE_BRACKET)
														ExitOnCharExpectedError(']');
										}

										// Determine the variable's index into the stack

										// If the variable is local, then its stack index is always the local data
										// size + 2 subtracted from zero

										int iStackIndex;

										if (iIsFuncActive == 1)
												iStackIndex = -(iCurrFuncLocalDataSize + 2);

										// Otherwise it's global, so it's equal to the current global data size

										else
												iStackIndex = g_ScriptHeader.iGlobalDataSize;

										// Attempt to add the symbol to the table

										if (AddSymbol(pstrIdent, iSize, iStackIndex, iCurrFuncIndex) == -1)
												ExitOnCodeError(ConstData.ERROR_MSSG_IDENT_REDEFINITION);

										// Depending on the scope, increment either the local or global data size
										// by the size of the variable

										if (iIsFuncActive == 1)
												iCurrFuncLocalDataSize += iSize;
										else
												g_ScriptHeader.iGlobalDataSize += iSize;

										break;
								}

										// Func

								case ConstData.TOKEN_TYPE_FUNC: {
										// First make sure we aren't in a function already, since nested functions
										// are illegal

										if (iIsFuncActive == 1)
												ExitOnCodeError(ConstData.ERROR_MSSG_NESTED_FUNC);

										// Read the next lexeme, which is the function name

										if (GetNextToken() != ConstData.TOKEN_TYPE_IDENT)
												ExitOnCodeError(ConstData.ERROR_MSSG_IDENT_EXPECTED);

										String pstrFuncName = GetCurrLexeme();

										// Calculate the function's entry point, which is the instruction immediately
										// following the current one, which is in turn equal to the instruction stream
										// size

										int iEntryPoint = g_iInstrStreamSize;

										// Try adding it to the function table, and print an error if it's already
										// been declared

										int iFuncIndex = AddFunc(pstrFuncName, iEntryPoint);
										if (iFuncIndex == -1)
												ExitOnCodeError(ConstData.ERROR_MSSG_FUNC_REDEFINITION);

										// Is this the _Main () function?

										if (pstrFuncName.equals(ConstData.MAIN_FUNC_NAME)) {
												g_ScriptHeader.iIsMainFuncPresent = ConstData.TRUE;
												g_ScriptHeader.iMainFuncIndex = iFuncIndex;
										}

										// Set the function flag to true for any future encounters and re-initialize
										// function tracking variables

										iIsFuncActive = ConstData.TRUE;
										pstrCurrFuncName = pstrFuncName;
										iCurrFuncIndex = iFuncIndex;
										iCurrFuncParamCount = 0;
										iCurrFuncLocalDataSize = 0;

										// Read any number of line breaks until the opening brace is found

										while (GetNextToken() == ConstData.TOKEN_TYPE_NEWLINE)
												;

										// Make sure the lexeme was an opening brace

										if (g_Lexer.CurrToken != ConstData.TOKEN_TYPE_OPEN_BRACE)
												ExitOnCharExpectedError('{');

										// All functions are automatically appended with Ret, so increment the
										// required size of the instruction stream

										++g_iInstrStreamSize;

										break;
								}

										// Closing bracket

								case ConstData.TOKEN_TYPE_CLOSE_BRACE:

										// This should be closing a function, so make sure we're in one

										if (iIsFuncActive == 0)
												ExitOnCharExpectedError('}');

										// Set the fields we've collected

										SetFuncInfo(pstrCurrFuncName, iCurrFuncParamCount, iCurrFuncLocalDataSize);

										// Close the function

										iIsFuncActive = ConstData.FALSE;

										break;

								// Param

								case ConstData.TOKEN_TYPE_PARAM: {
										// If we aren't currently in a function, print an error

										if (iIsFuncActive == 0)
												ExitOnCodeError(ConstData.ERROR_MSSG_GLOBAL_PARAM);

										// _Main () can't accept parameters, so make sure we aren't in it

										if (pstrCurrFuncName.equals(ConstData.MAIN_FUNC_NAME))
												ExitOnCodeError(ConstData.ERROR_MSSG_MAIN_PARAM);

										// The parameter's identifier should follow

										if (GetNextToken() != ConstData.TOKEN_TYPE_IDENT)
												ExitOnCodeError(ConstData.ERROR_MSSG_IDENT_EXPECTED);

										// Increment the current function's local data size

										++iCurrFuncParamCount;

										break;
								}

										// ---- Instructions

								case ConstData.TOKEN_TYPE_INSTR: {
										// Make sure we aren't in the global scope, since instructions
										// can only appear in functions

										if (iIsFuncActive == 0)
												ExitOnCodeError(ConstData.ERROR_MSSG_GLOBAL_INSTR);

										// Increment the instruction stream size

										++g_iInstrStreamSize;

										break;
								}

										// ---- Identifiers (line labels)

								case ConstData.TOKEN_TYPE_IDENT: {
										// Make sure it's a line label

										if (GetLookAheadChar() != ':')
												ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_INSTR);

										// Make sure we're in a function, since labels can only appear there

										if (iIsFuncActive == 0)
												ExitOnCodeError(ConstData.ERROR_MSSG_GLOBAL_LINE_LABEL);

										// The current lexeme is the label's identifier

										String pstrIdent = GetCurrLexeme();

										// The target instruction is always the value of the current
										// instruction count, which is the current size - 1

										int iTargetIndex = g_iInstrStreamSize - 1;

										// Save the label's function index as well

										int iFuncIndex = iCurrFuncIndex;

										// Try adding the label to the label table, and print an error if it
										// already exists

										if (AddLabel(pstrIdent, iTargetIndex, iFuncIndex) == -1)
												ExitOnCodeError(ConstData.ERROR_MSSG_LINE_LABEL_REDEFINITION);

										break;
								}

								default:

										// Anything else should cause an error, minus line breaks

										if (g_Lexer.CurrToken != ConstData.TOKEN_TYPE_NEWLINE)
												ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_INPUT);
						}

						// Skip to the next line, since the initial tokens are all we're really worrid
						// about in this phase

						if (!SkipToNextLine())
								break;
				}

				// We counted the instructions, so allocate the assembled instruction stream array
				// so the next phase can begin

				g_pInstrStream = new Instr[g_iInstrStreamSize];//( Instr * ) malloc ( g_iInstrStreamSize * sizeof ( Instr ) );

				// Initialize every operand list pointer to NULL

				for (int iCurrInstrIndex = 0; iCurrInstrIndex < g_iInstrStreamSize; ++iCurrInstrIndex) {
						g_pInstrStream[iCurrInstrIndex] = new Instr();
						g_pInstrStream[iCurrInstrIndex].pOpList = null;
				}

				// Set the current instruction index to zero

				g_iCurrInstrIndex = 0;

				// ---- Perform the second pass over the source

				// Reset the lexer so we begin at the top of the source again

				ResetLexer();

				// Loop through each line of code

				while (true) {
						// Get the next token and make sure we aren't at the end of the stream

						if (GetNextToken() == ConstData.END_OF_TOKEN_STREAM)
								break;

						// Check the initial token

						switch (g_Lexer.CurrToken) {
								// Func

								case ConstData.TOKEN_TYPE_FUNC: {
										// We've encountered a Func directive, but since we validated the syntax
										// of all functions in the previous phase, we don't need to perform any
										// error handling here and can assume the syntax is perfect.

										// Read the identifier

										GetNextToken();

										// Use the identifier (the current lexeme) to get it's corresponding function
										// from the table

										pCurrFunc = GetFuncByName(GetCurrLexeme());

										// Set the active function flag

										iIsFuncActive = ConstData.TRUE;

										// Set the parameter count to zero, since we'll need to count parameters as
										// we parse Param directives

										iCurrFuncParamCount = 0;

										// Save the function's index

										iCurrFuncIndex = pCurrFunc.iIndex;

										// Read any number of line breaks until the opening brace is found

										while (GetNextToken() == ConstData.TOKEN_TYPE_NEWLINE)
												;

										break;
								}

										// Closing brace

								case ConstData.TOKEN_TYPE_CLOSE_BRACE: {
										// Clear the active function flag

										iIsFuncActive = ConstData.FALSE;

										// If the ending function is _Main (), append an Exit instruction

										if (pCurrFunc.pstrName.equals(ConstData.MAIN_FUNC_NAME)) {
												// First set the opcode

												g_pInstrStream[g_iCurrInstrIndex].iOpcode = ConstData.INSTR_EXIT;

												// Now set the operand count

												g_pInstrStream[g_iCurrInstrIndex].iOpCount = 1;

												// Now set the return code by allocating space for a single operand and
												// setting it to zero

												g_pInstrStream[g_iCurrInstrIndex].pOpList = new Opstruct[1];
												g_pInstrStream[g_iCurrInstrIndex].pOpList[0] = new Opstruct();
												g_pInstrStream[g_iCurrInstrIndex].pOpList[0].iType = ConstData.OP_TYPE_INT;
												g_pInstrStream[g_iCurrInstrIndex].pOpList[0].unionValue = 0;
										}

										// Otherwise append a Ret instruction and make sure to NULLify the operand
										// list pointer

										else {
												g_pInstrStream[g_iCurrInstrIndex].iOpcode = ConstData.INSTR_RET;
												g_pInstrStream[g_iCurrInstrIndex].iOpCount = 0;
												g_pInstrStream[g_iCurrInstrIndex].pOpList = null;
										}

										++g_iCurrInstrIndex;

										break;
								}

										// Param

								case ConstData.TOKEN_TYPE_PARAM: {
										// Read the next token to get the identifier

										if (GetNextToken() != ConstData.TOKEN_TYPE_IDENT)
												ExitOnCodeError(ConstData.ERROR_MSSG_IDENT_EXPECTED);

										// Read the identifier, which is the current lexeme

										String pstrIdent = GetCurrLexeme();

										// Calculate the parameter's stack index

										int iStackIndex = -(pCurrFunc.iLocalDataSize + 2 + (iCurrFuncParamCount + 1));

										// Add the parameter to the symbol table

										if (AddSymbol(pstrIdent, 1, iStackIndex, iCurrFuncIndex) == -1)
												ExitOnCodeError(ConstData.ERROR_MSSG_IDENT_REDEFINITION);

										// Increment the current parameter count
										++iCurrFuncParamCount;

										break;
								}

										// Instructions

								case ConstData.TOKEN_TYPE_INSTR: {
										// Get the instruction's info using the current lexeme (the mnemonic )

										CurrInstr = GetInstrByMnemonic(GetCurrLexeme());

										// Write the opcode to the stream

										g_pInstrStream[g_iCurrInstrIndex].iOpcode = CurrInstr.iOpcode;

										// Write the operand count to the stream

										g_pInstrStream[g_iCurrInstrIndex].iOpCount = CurrInstr.iOpCount;

										// Allocate space to hold the operand list
										Opstruct[] pOpList = new Opstruct[CurrInstr.iOpCount];

										// Loop through each operand, read it from the source and assemble it

										for (int iCurrOpIndex = 0; iCurrOpIndex < CurrInstr.iOpCount; ++iCurrOpIndex) {
												// Read the operand's type bitfield
												pOpList[iCurrOpIndex] = new Opstruct();
												int CurrOpTypes = CurrInstr.OpList[iCurrOpIndex];

												// Read in the next token, which is the initial token of the operand

												int InitOpToken = GetNextToken();
												switch (InitOpToken) {
														// An integer literal

														case ConstData.TOKEN_TYPE_INT:

																// Make sure the operand type is valid

																if ((CurrOpTypes & ConstData.OP_FLAG_TYPE_INT) != 0) {
																		// Set an integer operand type

																		pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_INT;

																		// Copy the value into the operand list from the current
																		// lexeme

																		pOpList[iCurrOpIndex].unionValue = Integer.parseInt(GetCurrLexeme());
																} else
																		ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_OP);

																break;

														// A floating-point literal

														case ConstData.TOKEN_TYPE_FLOAT:

																// Make sure the operand type is valid

																if ((CurrOpTypes & ConstData.OP_FLAG_TYPE_FLOAT) != 0) {
																		// Set a floating-point operand type

																		pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_FLOAT;

																		// Copy the value into the operand list from the current
																		// lexeme

																		pOpList[iCurrOpIndex].fFloatLiteral = (float) Float
																		                .parseFloat(GetCurrLexeme());
																} else
																		ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_OP);

																break;

														// A string literal (since strings always start with quotes)

														case ConstData.TOKEN_TYPE_QUOTE: {
																// Make sure the operand type is valid

																if ((CurrOpTypes & ConstData.OP_FLAG_TYPE_STRING) != 0) {
																		GetNextToken();

																		// Handle the string based on its type

																		switch (g_Lexer.CurrToken) {
																				// If we read another quote, the string is empty

																				case ConstData.TOKEN_TYPE_QUOTE: {
																						// Convert empty strings to the integer value zero

																						pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_INT;
																						pOpList[iCurrOpIndex].unionValue = 0;
																						break;
																				}

																						// It's a normal string

																				case ConstData.TOKEN_TYPE_STRING: {
																						// Get the string literal

																						String pstrString = GetCurrLexeme();

																						// Add the string to the table, or get the index of
																						// the existing copy

																						int iStringIndex = AddString(g_StringTable, pstrString);

																						// Make sure the closing double-quote is present

																						if (GetNextToken() != ConstData.TOKEN_TYPE_QUOTE)
																								ExitOnCharExpectedError('\\');

																						// Set the operand type to string index and set its
																						// data field

																						pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_STRING_INDEX;
																						pOpList[iCurrOpIndex].unionValue = iStringIndex;
																						break;
																				}

																						// The string is invalid

																				default:
																						ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_STRING);
																		}
																} else
																		ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_OP);

																break;
														}

																// _RetVal

														case ConstData.TOKEN_TYPE_REG_RETVAL:

																// Make sure the operand type is valid

																if ((CurrOpTypes & ConstData.OP_FLAG_TYPE_REG) != 0) {
																		// Set a register type

																		pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_REG;
																		pOpList[iCurrOpIndex].unionValue = 0;
																} else
																		ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_OP);

																break;

														// Identifiers

														// These operands can be any of the following
														//      - Variables/Array Indices
														//      - Line Labels
														//      - Function Names
														//      - Host API Calls

														case ConstData.TOKEN_TYPE_IDENT: {
																// Find out which type of identifier is expected. Since no
																// instruction in XVM assebly accepts more than one type
																// of identifier per operand, we can use the operand types
																// alone to determine which type of identifier we're
																// parsing.

																// Parse a memory reference-- a variable or array index

																if ((CurrOpTypes & ConstData.OP_FLAG_TYPE_MEM_REF) != 0) {
																		// Whether the memory reference is a variable or array
																		// index, the current lexeme is the identifier so save a
																		// copy of it for later

																		String pstrIdent = GetCurrLexeme();

																		// Make sure the variable/array has been defined

																		if (GetSymbolByIdent(pstrIdent, iCurrFuncIndex) == null)
																				ExitOnCodeError(ConstData.ERROR_MSSG_UNDEFINED_IDENT);

																		// Get the identifier's index as well; it may either be
																		// an absolute index or a base index

																		int iBaseIndex = GetStackIndexByIdent(pstrIdent, iCurrFuncIndex);

																		// Use the lookahead character to find out whether or not
																		// we're parsing an array

																		if (GetLookAheadChar() != '[') {
																				// It's just a single identifier so the base index we
																				// already saved is the variable's stack index

																				// Make sure the variable isn't an array

																				if (GetSizeByIdent(pstrIdent, iCurrFuncIndex) > 1)
																						ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_ARRAY_NOT_INDEXED);

																				// Set the operand type to stack index and set the data
																				// field

																				pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_ABS_STACK_INDEX;
																				pOpList[iCurrOpIndex].unionValue = iBaseIndex;
																		} else {
																				// It's an array, so lets verify that the identifier is
																				// an actual array

																				if (GetSizeByIdent(pstrIdent, iCurrFuncIndex) == 1)
																						ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_ARRAY);

																				// First make sure the open brace is valid

																				if (GetNextToken() != ConstData.TOKEN_TYPE_OPEN_BRACKET)
																						ExitOnCharExpectedError('[');

																				// The next token is the index, be it an integer literal
																				// or variable identifier

																				int IndexToken = GetNextToken();

																				if (IndexToken == ConstData.TOKEN_TYPE_INT) {
																						// It's an integer, so determine its value by
																						// converting the current lexeme to an integer

																						int iOffsetIndex = Integer.parseInt(GetCurrLexeme());

																						// Add the index to the base index to find the offset
																						// index and set the operand type to absolute stack
																						// index

																						pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_ABS_STACK_INDEX;
																						pOpList[iCurrOpIndex].unionValue = iBaseIndex + iOffsetIndex;
																				} else if (IndexToken == ConstData.TOKEN_TYPE_IDENT) {
																						// It's an identifier, so save the current lexeme

																						String pstrIndexIdent = GetCurrLexeme();

																						// Make sure the index is a valid array index, in
																						// that the identifier represents a single variable
																						// as opposed to another array

																						if (GetSymbolByIdent(pstrIndexIdent, iCurrFuncIndex) == null)
																								ExitOnCodeError(ConstData.ERROR_MSSG_UNDEFINED_IDENT);

																						if (GetSizeByIdent(pstrIndexIdent, iCurrFuncIndex) > 1)
																								ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_ARRAY_INDEX);

																						// Get the variable's stack index and set the operand
																						// type to relative stack index

																						int iOffsetIndex = GetStackIndexByIdent(pstrIndexIdent,
																						                iCurrFuncIndex);

																						pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_REL_STACK_INDEX;
																						pOpList[iCurrOpIndex].unionValue = iBaseIndex;
																						pOpList[iCurrOpIndex].iOffsetIndex = iOffsetIndex;
																				} else {
																						// Whatever it is, it's invalid

																						ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_ARRAY_INDEX);
																				}

																				// Lastly, make sure the closing brace is present as well

																				if (GetNextToken() != ConstData.TOKEN_TYPE_CLOSE_BRACKET)
																						ExitOnCharExpectedError('[');
																		}
																}

																// Parse a line label

																if ((CurrOpTypes & ConstData.OP_FLAG_TYPE_LINE_LABEL) != 0) {
																		// Get the current lexeme, which is the line label

																		String pstrLabelIdent = GetCurrLexeme();

																		// Use the label identifier to get the label's information

																		LabelNode pLabel = GetLabelByIdent(pstrLabelIdent, iCurrFuncIndex);

																		// Make sure the label exists

																		if (pLabel == null)
																				ExitOnCodeError(ConstData.ERROR_MSSG_UNDEFINED_LINE_LABEL);

																		// Set the operand type to instruction index and set the
																		// data field

																		pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_INSTR_INDEX;
																		pOpList[iCurrOpIndex].unionValue = pLabel.iTargetIndex;
																}

																// Parse a function name

																if ((CurrOpTypes & ConstData.OP_FLAG_TYPE_FUNC_NAME) != 0) {
																		// Get the current lexeme, which is the function name

																		String pstrFuncName = GetCurrLexeme();

																		// Use the function name to get the function's information

																		FuncNode pFunc = GetFuncByName(pstrFuncName);

																		// Make sure the function exists

																		if (pFunc == null)
																				ExitOnCodeError(ConstData.ERROR_MSSG_UNDEFINED_FUNC);

																		// Set the operand type to function index and set its data
																		// field

																		pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_FUNC_INDEX;
																		pOpList[iCurrOpIndex].unionValue = pFunc.iIndex;
																}

																// Parse a host API call

																if ((CurrOpTypes & ConstData.OP_FLAG_TYPE_HOST_API_CALL) != 0) {
																		// Get the current lexeme, which is the host API call

																		String pstrHostAPICall = GetCurrLexeme();

																		// Add the call to the table, or get the index of the
																		// existing copy

																		int iIndex = AddString(g_HostAPICallTable, pstrHostAPICall);

																		// Set the operand type to host API call index and set its
																		// data field

																		pOpList[iCurrOpIndex].iType = ConstData.OP_TYPE_HOST_API_CALL_INDEX;
																		pOpList[iCurrOpIndex].unionValue = iIndex;
																}

																break;
														}

																// Anything else

														default:

																ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_OP);
																break;
												}

												// Make sure a comma follows the operand, unless it's the last one

												if (iCurrOpIndex < CurrInstr.iOpCount - 1)
														if (GetNextToken() != ConstData.TOKEN_TYPE_COMMA)
																ExitOnCharExpectedError(',');
										}

										// Make sure there's no extranous stuff ahead

										if (GetNextToken() != ConstData.TOKEN_TYPE_NEWLINE)
												ExitOnCodeError(ConstData.ERROR_MSSG_INVALID_INPUT);

										// Copy the operand list pointer into the assembled stream

										g_pInstrStream[g_iCurrInstrIndex].pOpList = pOpList;

										// Move along to the next instruction in the stream

										++g_iCurrInstrIndex;

										break;
								}
						}

						// Skip to the next line

						if (!SkipToNextLine())
								break;
				}
		}

		/***************************************************************************************************************
		 * 
		 * BuildXSE ()
		 * 
		 * Dumps the assembled executable to an .XSE file.
		 */

		private static void BuildXSE() {
				// ---- Open the output file
				DataOutputStream pExecFile = null;
				try {
						pExecFile = new DataOutputStream(new FileOutputStream(g_pstrExecFilename, false));
				} catch (FileNotFoundException e) {
						e.printStackTrace();
						ExitOnError("Could not open executable file for output");
				}

				// ---- Write the header

				// Write the ID string (4 bytes)
				try {
						pExecFile.writeUTF(ConstData.XSE_ID_STRING);
						//            pExecFile.write(ConstData.XSE_ID_STRING.getBytes());

						// Write the version (1 byte for each component, 2 total)

						pExecFile.write(ConstData.VERSION_MAJOR);
						pExecFile.write(ConstData.VERSION_MINOR);

						// Write the stack size (4 bytes)
						pExecFile.writeInt(g_ScriptHeader.iStackSize);

						// Write the global data size (4 bytes )
						pExecFile.writeInt(g_ScriptHeader.iGlobalDataSize);

						// Write the _Main () flag (1 byte)

						char cIsMainPresent = 0;
						if (g_ScriptHeader.iIsMainFuncPresent == 1)
								cIsMainPresent = 1;
						pExecFile.write(cIsMainPresent);

						// Write the _Main () function index (4 bytes)
						pExecFile.writeInt(g_ScriptHeader.iMainFuncIndex);

						// Write the priority type (1 byte)
						pExecFile.write(g_ScriptHeader.iPriorityType);
						// Write the user-defined priority (4 bytes)
						pExecFile.writeInt(g_ScriptHeader.iUserPriority);
						// ---- Write the instruction stream

						// Output the instruction count (4 bytes)
						pExecFile.writeInt(g_iInstrStreamSize);

						// Loop through each instruction and write its data out

						for (int iCurrInstrIndex = 0; iCurrInstrIndex < g_iInstrStreamSize; ++iCurrInstrIndex) {
								// Write the opcode (2 bytes)

								short sOpcode = (short) g_pInstrStream[iCurrInstrIndex].iOpcode;
								pExecFile.writeShort(sOpcode);

								// Write the operand count (1 byte)

								int iOpCount = g_pInstrStream[iCurrInstrIndex].iOpCount;
								pExecFile.write(iOpCount);

								// Loop through the operand list and print each one out

								for (int iCurrOpIndex = 0; iCurrOpIndex < iOpCount; ++iCurrOpIndex) {
										// Make a copy of the operand pointer for convinience

										Opstruct CurrOp = g_pInstrStream[iCurrInstrIndex].pOpList[iCurrOpIndex];

										// Create a character for holding operand types (1 byte)

										int cOpType = CurrOp.iType;
										pExecFile.write(cOpType);

										// Write the operand depending on its type

										switch (CurrOp.iType) {
												// Integer literal

												case ConstData.OP_TYPE_INT:
														pExecFile.writeInt(CurrOp.unionValue);
														break;

												// Floating-point literal

												case ConstData.OP_TYPE_FLOAT:
														pExecFile.writeFloat(CurrOp.fFloatLiteral);
														break;

												// String index

												case ConstData.OP_TYPE_STRING_INDEX:
														pExecFile.writeInt(CurrOp.unionValue);
														break;

												// Instruction index

												case ConstData.OP_TYPE_INSTR_INDEX:
														pExecFile.writeInt(CurrOp.unionValue);
														break;

												// Absolute stack index

												case ConstData.OP_TYPE_ABS_STACK_INDEX:
														pExecFile.writeInt(CurrOp.unionValue);
														break;

												// Relative stack index

												case ConstData.OP_TYPE_REL_STACK_INDEX:
														//release 0.8
														//                            pExecFile.writeInt(CurrOp.unionValue);
														//                            pExecFile.writeInt(CurrOp.iOffsetIndex);
														//release 0.8.1    
														pExecFile.writeShort(CurrOp.unionValue);
														pExecFile.writeShort(CurrOp.iOffsetIndex);
														break;

												// Function index

												case ConstData.OP_TYPE_FUNC_INDEX:
														pExecFile.writeInt(CurrOp.unionValue);
														break;

												// Host API call index

												case ConstData.OP_TYPE_HOST_API_CALL_INDEX:
														pExecFile.writeInt(CurrOp.unionValue);
														break;

												// Register

												case ConstData.OP_TYPE_REG:
														pExecFile.writeInt(CurrOp.unionValue);
														break;
										}
								}
						}

						// Create a node pointer for traversing the lists

						int iCurrNode;
						// ---- Write the string table

						// Write out the string count (4 bytes)
						pExecFile.writeInt(g_StringTable.size());

						// Set the pointer to the head of the list

						// Loop through each node in the list and write out its string

						for (iCurrNode = 0; iCurrNode < g_StringTable.size(); ++iCurrNode) {
								// Copy the string and calculate its length

								// Write the length (4 bytes), followed by the string data (N bytes)
								pExecFile.writeUTF((String) g_StringTable.get(iCurrNode));

						}

						// ---- Write the function table

						// Write out the function count (4 bytes)
						pExecFile.writeInt(g_FuncTable.size());

						// Set the pointer to the head of the list

						// Loop through each node in the list and write out its function info

						for (iCurrNode = 0; iCurrNode < g_FuncTable.size(); ++iCurrNode) {
								// Create a local copy of the function

								FuncNode pFunc = (FuncNode) g_FuncTable.get(iCurrNode);

								// Write the entry point (4 bytes)
								pExecFile.writeInt(pFunc.iEntryPoint);

								// Write the parameter count (1 byte)

								pExecFile.write(pFunc.iParamCount);

								// Write the local data size (4 bytes)
								pExecFile.writeInt(pFunc.iLocalDataSize);
								//TODO fixed xasm 0.8
								// Write the function name length (1 byte)
								pExecFile.writeUTF(pFunc.pstrName);

						}

						// ---- Write the host API call table

						// Write out the call count (4 bytes)
						pExecFile.writeInt(g_HostAPICallTable.size());

						// Set the pointer to the head of the list

						// Loop through each node in the list and write out its string

						for (iCurrNode = 0; iCurrNode < g_HostAPICallTable.size(); ++iCurrNode) {
								// Copy the string pointer and calculate its length

								String pstrCurrHostAPICall = (String) g_HostAPICallTable.get(iCurrNode);

								// Write the length (1 byte), followed by the string data (N bytes)
								pExecFile.writeUTF(pstrCurrHostAPICall);

						}

						// ---- Close the output file
						pExecFile.close();
				} catch (IOException e) {
						e.printStackTrace();
				}

		}

		/***************************************************************************************************************
		 * 
		 * PrintAssmblStats ()
		 * 
		 * Prints miscellaneous assembly stats.
		 */

		private static void PrintAssmblStats() {
				// ---- Calculate statistics

				// Symbols

				// Create some statistic variables

				int iVarCount = 0, iArrayCount = 0, iGlobalCount = 0;

				// Traverse the list to count each symbol type

				for (int iCurrNode = 0; iCurrNode < g_SymbolTable.size(); ++iCurrNode) {
						// Create a pointer to the current symbol structure

						SymbolNode pCurrSymbol = (SymbolNode) g_SymbolTable.get(iCurrNode);

						// It's an array if the size is greater than 1

						if (pCurrSymbol.iSize > 1)
								++iArrayCount;

						// It's a variable otherwise

						else
								++iVarCount;

						// It's a global if it's stack index is nonnegative

						if (pCurrSymbol.iStackIndex >= 0)
								++iGlobalCount;

				}

				// Print out final calculations

				System.out.println(g_pstrExecFilename + " created successfully!\n");
				System.out.println("Source Lines Processed: " + g_iSourceCodeSize);

				System.out.print("            Stack Size: ");
				if (g_ScriptHeader.iStackSize != 0)
						System.out.println(g_ScriptHeader.iStackSize);
				else
						System.out.println("Default");

				System.out.println("Instructions Assembled: " + g_iInstrStreamSize);
				System.out.println("             Variables: " + iVarCount);
				System.out.println("                Arrays: " + iArrayCount);
				System.out.println("               Globals: " + iGlobalCount);
				System.out.println("       String Literals: " + g_StringTable.size());
				System.out.println("                Labels: " + g_LabelTable.size());
				System.out.println("        Host API Calls: " + g_HostAPICallTable.size());
				System.out.println("             Functions: " + g_FuncTable.size());

				System.out.print("      _Main () Present: ");
				if (g_ScriptHeader.iIsMainFuncPresent != 0)
						System.out.println("Yes (Index " + g_ScriptHeader.iMainFuncIndex + ")");
				else
						System.out.println("No");
		}

		/***************************************************************************************************************
		 * 
		 * Exit ()
		 * 
		 * Exits the program.
		 */

		private static void Exit() {
				// Give allocated resources a chance to be freed

				ShutDown();

				// Exit the program
				System.exit(0);
		}

		/***************************************************************************************************************
		 * 
		 * ExitOnError ()
		 * 
		 * Prints an error message and exits.
		 */

		private static void ExitOnError(String pstrErrorMssg) {
				// Print the message

				System.out.println("Fatal Error: " + pstrErrorMssg);

				// Exit the program

				Exit();
		}

		/***************************************************************************************************************
		 * 
		 * ExitOnCodeError ()
		 * 
		 * Prints an error message relating to the source code and exits.
		 */

		private static void ExitOnCodeError(String pstrErrorMssg) {
				// Print the message

				System.out.println("Error: " + pstrErrorMssg);
				System.out.println("Line " + g_Lexer.iCurrSourceLine);

				// Reduce all of the source line's spaces to tabs so it takes less space and so the
				// karet lines up with the current token properly

				char[] pstrSourceLine = new char[g_ppstrSourceCode[g_Lexer.iCurrSourceLine].length];
				System.arraycopy(g_ppstrSourceCode[g_Lexer.iCurrSourceLine], 0, pstrSourceLine, 0, pstrSourceLine.length);

				// Loop through each character and replace tabs with spaces

				for (int iCurrCharIndex = 0; iCurrCharIndex < pstrSourceLine.length; ++iCurrCharIndex)
						if (pstrSourceLine[iCurrCharIndex] == '\t')
								pstrSourceLine[iCurrCharIndex] = ' ';

				// Print the offending source line

				System.out.println(pstrSourceLine);

				// Print a karet at the start of the (presumably) offending lexeme

				for (int iCurrSpace = 0; iCurrSpace < g_Lexer.iIndex0; ++iCurrSpace)
						System.out.print(" ");
				System.out.println("^\n");

				// Print message indicating that the script could not be assembled

				System.out.println("Could not assemble " + g_pstrExecFilename);

				// Exit the program

				Exit();
		}

		/***************************************************************************************************************
		 * 
		 * ExitOnCharExpectedError ()
		 * 
		 * Exits because a specific character was expected but not found.
		 */

		private static void ExitOnCharExpectedError(char cChar) {

				// Exit on the code error

				ExitOnCodeError("'" + cChar + "'  expected");
		}

		// ---- Lexical Analysis ------------------------------------------------------------------

		/***************************************************************************************************************
		 * 
		 * ResetLexer ()
		 * 
		 * Resets the lexer to the beginning of the source file by setting the current line and indices to zero.
		 */

		private static void ResetLexer() {
				// Set the current line to the start of the file
				g_Lexer = new Lexer();
				g_Lexer.iCurrSourceLine = 0;

				// Set both indices to point to the start of the string

				g_Lexer.iIndex0 = 0;
				g_Lexer.iIndex1 = 0;

				// Set the token type to invalid, since a token hasn't been read yet

				g_Lexer.CurrToken = ConstData.TOKEN_TYPE_INVALID;

				// Set the lexing state to no strings

				g_Lexer.iCurrLexState = ConstData.LEX_STATE_NO_STRING;
		}

		/***************************************************************************************************************
		 * 
		 * GetNextToken ()
		 * 
		 * Extracts and returns the next token from the character stream. Also makes a copy of the current lexeme for
		 * use with GetCurrLexeme ().
		 */

		private static int GetNextToken() {
				// ---- Lexeme Extraction

				// Move the first index (Index0) past the end of the last token, which is marked
				// by the second index (Index1).

				g_Lexer.iIndex0 = g_Lexer.iIndex1;

				// Make sure we aren't past the end of the current line. If a string is 8 characters long,
				// it's indexed from 0 to 7; therefore, indices 8 and beyond lie outside of the string and
				// require us to move to the next line. This is why I use >= for the comparison rather
				// than >. The value returned by strlen () is always one greater than the last valid
				// character index.

				if (g_Lexer.iIndex0 >= (g_ppstrSourceCode[g_Lexer.iCurrSourceLine]).length) {
						// If so, skip to the next line but make sure we don't go past the end of the file.
						// SkipToNextLine () will return FALSE if we hit the end of the file, which is
						// the end of the token stream.

						if (!SkipToNextLine())
								return ConstData.END_OF_TOKEN_STREAM;
				}

				// If we just ended a string, tell the lexer to stop lexing
				// strings and return to the normal state

				if (g_Lexer.iCurrLexState == ConstData.LEX_STATE_END_STRING)
						g_Lexer.iCurrLexState = ConstData.LEX_STATE_NO_STRING;

				// Scan through the potential whitespace preceding the next lexeme, but ONLY if we're
				// not currently parsing a string lexeme (since strings can contain arbitrary whitespace
				// which must be preserved).

				if (g_Lexer.iCurrLexState != ConstData.LEX_STATE_IN_STRING) {
						// Scan through the whitespace and check for the end of the line

						while (true) {
								// If the current character is not whitespace, exit the loop because the lexeme
								// is starting.

								if (!Lib.IsCharWhitespace(g_ppstrSourceCode[g_Lexer.iCurrSourceLine][g_Lexer.iIndex0]))
										break;

								// It is whitespace, however, so move to the next character and continue scanning

								++g_Lexer.iIndex0;
						}
				}

				// Bring the second index (Index1) to the lexeme's starting character, which is marked by
				// the first index (Index0)

				g_Lexer.iIndex1 = g_Lexer.iIndex0;

				// Scan through the lexeme until a delimiter is hit, incrementing Index1 each time

				while (true) {
						// Are we currently scanning through a string?

						if (g_Lexer.iCurrLexState == ConstData.LEX_STATE_IN_STRING) {
								// If we're at the end of the line, return an invalid token since the string has no
								// ending double-quote on the line

								if (g_Lexer.iIndex1 >= (g_ppstrSourceCode[g_Lexer.iCurrSourceLine]).length) {
										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_INVALID;
										return g_Lexer.CurrToken;
								}

								// If the current character is a backslash, move ahead two characters to skip the
								// escape sequence and jump to the next iteration of the loop

								if (g_ppstrSourceCode[g_Lexer.iCurrSourceLine][g_Lexer.iIndex1] == '\\') {
										g_Lexer.iIndex1 += 2;
										continue;
								}

								// If the current character isn't a double-quote, move to the next, otherwise exit
								// the loop, because the string has ended.

								if (g_ppstrSourceCode[g_Lexer.iCurrSourceLine][g_Lexer.iIndex1] == '"')
										break;

								++g_Lexer.iIndex1;
						}

						// We are not currently scanning through a string

						else {
								// If we're at the end of the line, the lexeme has ended so exit the loop

								if (g_Lexer.iIndex1 >= (g_ppstrSourceCode[g_Lexer.iCurrSourceLine]).length)
										break;

								// If the current character isn't a delimiter, move to the next, otherwise exit the loop

								if (Lib.IsCharDelimiter(g_ppstrSourceCode[g_Lexer.iCurrSourceLine][g_Lexer.iIndex1]))
										break;

								++g_Lexer.iIndex1;
						}
				}

				// Single-character lexemes will appear to be zero characters at this point (since Index1
				// will equal Index0), so move Index1 over by one to give it some noticable width

				if (g_Lexer.iIndex1 - g_Lexer.iIndex0 == 0)
						++g_Lexer.iIndex1;

				// The lexeme has been isolated and lies between Index0 and Index1 (inclusive), so make a local
				// copy for the lexer

				//        int iCurrDestIndex = 0;
				g_Lexer.pstrCurrLexeme = "";
				for (int iCurrSourceIndex = g_Lexer.iIndex0; iCurrSourceIndex < g_Lexer.iIndex1; ++iCurrSourceIndex) {
						// If we're parsing a string, check for escape sequences and just copy the character after
						// the backslash

						if (g_Lexer.iCurrLexState == ConstData.LEX_STATE_IN_STRING)
								if (g_ppstrSourceCode[g_Lexer.iCurrSourceLine][iCurrSourceIndex] == '\\')
										++iCurrSourceIndex;

						// Copy the character from the source line to the lexeme

						g_Lexer.pstrCurrLexeme += g_ppstrSourceCode[g_Lexer.iCurrSourceLine][iCurrSourceIndex];

						// Advance the destination index

						//            ++ iCurrDestIndex;
				}

				// Set the null terminator

				//        g_Lexer.pstrCurrLexeme += '\0';

				// Convert it to uppercase if it's not a string

				if (g_Lexer.iCurrLexState != ConstData.LEX_STATE_IN_STRING) {
						//            System.arraycopy((g_Lexer.pstrCurrLexeme).toString().toUpperCase().toCharArray(),0,g_Lexer.pstrCurrLexeme,0,g_Lexer.pstrCurrLexeme.length);
						g_Lexer.pstrCurrLexeme = g_Lexer.pstrCurrLexeme.toUpperCase();
				}

				// ---- Token Identification

				// Let's find out what sort of token our new lexeme is

				// We'll set the type to invalid now just in case the lexer doesn't match any
				// token types

				g_Lexer.CurrToken = ConstData.TOKEN_TYPE_INVALID;

				// The first case is the easiest-- if the string lexeme state is active, we know we're
				// dealing with a string token. However, if the string is the double-quote sign, it
				// means we've read an empty string and should return a double-quote instead

				if ((g_Lexer.pstrCurrLexeme ).length() > 1 || g_Lexer.pstrCurrLexeme.charAt(0) != '"') {
						if (g_Lexer.iCurrLexState == ConstData.LEX_STATE_IN_STRING) {
								g_Lexer.CurrToken = ConstData.TOKEN_TYPE_STRING;
								return ConstData.TOKEN_TYPE_STRING;
						}
				}

				// Now let's check for the single-character tokens

				if ((g_Lexer.pstrCurrLexeme ).length() == 1) {
						switch (g_Lexer.pstrCurrLexeme.charAt(0)) {
								// Double-Quote

								case '"':
										// If a quote is read, advance the lexing state so that strings are lexed
										// properly

										switch (g_Lexer.iCurrLexState) {
												// If we're not lexing strings, tell the lexer we're now
												// in a string

												case ConstData.LEX_STATE_NO_STRING:
														g_Lexer.iCurrLexState = ConstData.LEX_STATE_IN_STRING;
														break;

												// If we're in a string, tell the lexer we just ended a string

												case ConstData.LEX_STATE_IN_STRING:
														g_Lexer.iCurrLexState = ConstData.LEX_STATE_END_STRING;
														break;
										}

										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_QUOTE;
										break;

								// Comma

								case ',':
										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_COMMA;
										break;

								// Colon

								case ':':
										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_COLON;
										break;

								// Opening Bracket

								case '[':
										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_OPEN_BRACKET;
										break;

								// Closing Bracket

								case ']':
										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_CLOSE_BRACKET;
										break;

								// Opening Brace

								case '{':
										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_OPEN_BRACE;
										break;

								// Closing Brace

								case '}':
										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_CLOSE_BRACE;
										break;

								// Newline

								case '\n':
										g_Lexer.CurrToken = ConstData.TOKEN_TYPE_NEWLINE;
										break;
						}
				}

				// Now let's check for the multi-character tokens

				// Is it an integer?

				if (Lib.IsStringInteger(g_Lexer.pstrCurrLexeme.toCharArray()))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_INT;

				// Is it a float?

				if (Lib.IsStringFloat(g_Lexer.pstrCurrLexeme.toCharArray()))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_FLOAT;

				// Is it an identifier (which may also be a line label or instruction)?

				if (Lib.IsStringIdent(g_Lexer.pstrCurrLexeme.toCharArray()))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_IDENT;

				// Check for directives or _RetVal

				// Is it SetStackSize?

				if (g_Lexer.pstrCurrLexeme.equals("SETSTACKSIZE"))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_SETSTACKSIZE;
				// Is it SetPriority?

				if (g_Lexer.pstrCurrLexeme.equals("SETPRIORITY"))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_SETPRIORITY;
				// Is it Var/Var []?

				if (g_Lexer.pstrCurrLexeme.equals("VAR"))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_VAR;

				// Is it Func?

				if (g_Lexer.pstrCurrLexeme.equals("FUNC"))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_FUNC;

				// Is it Param?

				if (g_Lexer.pstrCurrLexeme.equals("PARAM"))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_PARAM;

				// Is it _RetVal?

				if (g_Lexer.pstrCurrLexeme.equals("_RETVAL"))
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_REG_RETVAL;

				// Is it an instruction?

				//		InstrLookup Instr = new InstrLookup();
				if (GetInstrByMnemonic(g_Lexer.pstrCurrLexeme) != null)
						g_Lexer.CurrToken = ConstData.TOKEN_TYPE_INSTR;

				return g_Lexer.CurrToken;
		}

		/***************************************************************************************************************
		 * 
		 * GetCurrLexeme ()
		 * 
		 * Returns a pointer to the current lexeme.
		 */

		private static String GetCurrLexeme() {
				// Simply return the pointer rather than making a copy

				return g_Lexer.pstrCurrLexeme;
		}

		/***************************************************************************************************************
		 * 
		 * GetLookAheadChar ()
		 * 
		 * Returns the look-ahead character. which is the first character of the next lexeme in the stream.
		 */

		private static char GetLookAheadChar() {
				// We don't actually want to move the lexer's indices, so we'll make a copy of them

				int iCurrSourceLine = g_Lexer.iCurrSourceLine;
				int iIndex = g_Lexer.iIndex1;

				// If the next lexeme is not a string, scan past any potential leading whitespace

				if (g_Lexer.iCurrLexState != ConstData.LEX_STATE_IN_STRING) {
						// Scan through the whitespace and check for the end of the line

						while (true) {
								// If we've passed the end of the line, skip to the next line and reset the
								// index to zero

								if (iIndex >= g_ppstrSourceCode[iCurrSourceLine].length) {
										// Increment the source code index

										iCurrSourceLine += 1;

										// If we've passed the end of the source file, just return a null character

										if (iCurrSourceLine >= g_iSourceCodeSize)
												return 0;

										// Otherwise, reset the index to the first character on the new line

										iIndex = 0;
								}

								// If the current character is not whitespace, return it, since it's the first
								// character of the next lexeme and is thus the look-ahead

								if (!Lib.IsCharWhitespace(g_ppstrSourceCode[iCurrSourceLine][iIndex]))
										break;

								// It is whitespace, however, so move to the next character and continue scanning

								++iIndex;
						}
				}

				// Return whatever character the loop left iIndex at

				return g_ppstrSourceCode[iCurrSourceLine][iIndex];
		}

		/***************************************************************************************************************
		 * 
		 * SkipToNextLine ()
		 * 
		 * Skips to the next line in the character stream. Returns FALSE the end of the source code has been reached,
		 * TRUE otherwise.
		 */

		private static boolean SkipToNextLine() {
				// Increment the current line

				++g_Lexer.iCurrSourceLine;

				// Return FALSE if we've gone past the end of the source code

				if (g_Lexer.iCurrSourceLine >= g_iSourceCodeSize)
						return false;

				// Set both indices to point to the start of the string

				g_Lexer.iIndex0 = 0;
				g_Lexer.iIndex1 = 0;

				// Turn off string lexeme mode, since strings can't span multiple lines

				g_Lexer.iCurrLexState = ConstData.LEX_STATE_NO_STRING;

				// Return TRUE to indicate success

				return true;
		}

		// ---- Instructions ----------------------------------------------------------------------

		/***************************************************************************************************************
		 * 
		 * GetInstrByMnemonic ()
		 * 
		 * Returns a pointer to the instruction definition corresponding to the specified mnemonic.
		 */

		private static InstrLookup GetInstrByMnemonic(String pstrMnemonic) {
				// Loop through each instruction in the lookup table

				for (int iCurrInstrIndex = 0; iCurrInstrIndex < ConstData.MAX_INSTR_LOOKUP_COUNT; ++iCurrInstrIndex)

						// Compare the instruction's mnemonic to the specified one

						if (g_InstrTable[iCurrInstrIndex] != null && g_InstrTable[iCurrInstrIndex].pstrMnemonic.equals(pstrMnemonic)) {
								// Set the instruction definition to the user-specified pointer

								return g_InstrTable[iCurrInstrIndex];

						}

				// A match was not found, so return FALSE

				return null;
		}

		/***************************************************************************************************************
		 * 
		 * InitInstrTable ()
		 * 
		 * Initializes the master instruction lookup table.
		 */

		private static void InitInstrTable() {
				// Create a temporary index to use with each instruction

				int iInstrIndex;

				// The following code makes repeated calls to AddInstrLookup () to add a hardcoded
				// instruction set to the assembler's vocabulary. Each AddInstrLookup () call is
				// followed by zero or more calls to SetOpType (), whcih set the supported types of
				// a specific operand. The instructions are grouped by family.

				// ---- Main

				// Mov          Destination, Source

				iInstrIndex = AddInstrLookup("Mov", ConstData.INSTR_MOV, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// ---- Arithmetic

				// Add         Destination, Source

				iInstrIndex = AddInstrLookup("Add", ConstData.INSTR_ADD, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Sub          Destination, Source

				iInstrIndex = AddInstrLookup("Sub", ConstData.INSTR_SUB, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Mul          Destination, Source

				iInstrIndex = AddInstrLookup("Mul", ConstData.INSTR_MUL, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Div          Destination, Source

				iInstrIndex = AddInstrLookup("Div", ConstData.INSTR_DIV, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Mod          Destination, Source

				iInstrIndex = AddInstrLookup("Mod", ConstData.INSTR_MOD, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Exp          Destination, Source

				iInstrIndex = AddInstrLookup("Exp", ConstData.INSTR_EXP, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Neg          Destination

				iInstrIndex = AddInstrLookup("Neg", ConstData.INSTR_NEG, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Inc          Destination

				iInstrIndex = AddInstrLookup("Inc", ConstData.INSTR_INC, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Dec          Destination

				iInstrIndex = AddInstrLookup("Dec", ConstData.INSTR_DEC, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// ---- Bitwise

				// And          Destination, Source

				iInstrIndex = AddInstrLookup("And", ConstData.INSTR_AND, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Or           Destination, Source

				iInstrIndex = AddInstrLookup("Or", ConstData.INSTR_OR, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// XOr          Destination, Source

				iInstrIndex = AddInstrLookup("XOr", ConstData.INSTR_XOR, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Not          Destination

				iInstrIndex = AddInstrLookup("Not", ConstData.INSTR_NOT, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// ShL          Destination, Source

				iInstrIndex = AddInstrLookup("ShL", ConstData.INSTR_SHL, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// ShR          Destination, Source

				iInstrIndex = AddInstrLookup("ShR", ConstData.INSTR_SHR, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// ---- String Manipulation

				// Concat       String0, String1

				iInstrIndex = AddInstrLookup("Concat", ConstData.INSTR_CONCAT, 2);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG | ConstData.OP_FLAG_TYPE_STRING);

				// GetChar      Destination, Source, Index

				iInstrIndex = AddInstrLookup("GetChar", ConstData.INSTR_GETCHAR, 3);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG | ConstData.OP_FLAG_TYPE_STRING);
				SetOpType(iInstrIndex, 2, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG | ConstData.OP_FLAG_TYPE_INT);

				// SetChar      Destination, Index, Source

				iInstrIndex = AddInstrLookup("SetChar", ConstData.INSTR_SETCHAR, 3);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG | ConstData.OP_FLAG_TYPE_INT);
				SetOpType(iInstrIndex, 2, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG | ConstData.OP_FLAG_TYPE_STRING);

				// ---- Conditional Branching

				// Jmp          Label

				iInstrIndex = AddInstrLookup("Jmp", ConstData.INSTR_JMP, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_LINE_LABEL);

				// JE           Op0, Op1, Label

				iInstrIndex = AddInstrLookup("JE", ConstData.INSTR_JE, 3);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 2, ConstData.OP_FLAG_TYPE_LINE_LABEL);

				// JNE          Op0, Op1, Label

				iInstrIndex = AddInstrLookup("JNE", ConstData.INSTR_JNE, 3);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 2, ConstData.OP_FLAG_TYPE_LINE_LABEL);

				// JG           Op0, Op1, Label

				iInstrIndex = AddInstrLookup("JG", ConstData.INSTR_JG, 3);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 2, ConstData.OP_FLAG_TYPE_LINE_LABEL);

				// JL           Op0, Op1, Label

				iInstrIndex = AddInstrLookup("JL", ConstData.INSTR_JL, 3);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 2, ConstData.OP_FLAG_TYPE_LINE_LABEL);

				// JGE          Op0, Op1, Label

				iInstrIndex = AddInstrLookup("JGE", ConstData.INSTR_JGE, 3);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 2, ConstData.OP_FLAG_TYPE_LINE_LABEL);

				// JLE           Op0, Op1, Label

				iInstrIndex = AddInstrLookup("JLE", ConstData.INSTR_JLE, 3);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 1, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
				SetOpType(iInstrIndex, 2, ConstData.OP_FLAG_TYPE_LINE_LABEL);

				// ---- The Stack Interface

				// Push          Source

				iInstrIndex = AddInstrLookup("Push", ConstData.INSTR_PUSH, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Pop           Destination

				iInstrIndex = AddInstrLookup("Pop", ConstData.INSTR_POP, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// ---- The Function Interface

				// Call          FunctionName

				iInstrIndex = AddInstrLookup("Call", ConstData.INSTR_CALL, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_FUNC_NAME);

				// Ret

				iInstrIndex = AddInstrLookup("Ret", ConstData.INSTR_RET, 0);

				// CallHost      FunctionName

				iInstrIndex = AddInstrLookup("CallHost", ConstData.INSTR_CALLHOST, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_HOST_API_CALL);

				// ---- Miscellaneous

				// Pause        Duration

				iInstrIndex = AddInstrLookup("Pause", ConstData.INSTR_PAUSE, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);

				// Exit         Code

				iInstrIndex = AddInstrLookup("Exit", ConstData.INSTR_EXIT, 1);
				SetOpType(iInstrIndex, 0, ConstData.OP_FLAG_TYPE_INT | ConstData.OP_FLAG_TYPE_FLOAT | ConstData.OP_FLAG_TYPE_STRING
				                | ConstData.OP_FLAG_TYPE_MEM_REF | ConstData.OP_FLAG_TYPE_REG);
		}

		/***************************************************************************************************************
		 * 
		 * AddInstrLookup ()
		 * 
		 * Adds an instruction to the instruction lookup table.
		 */
		private static int iInstrIndex = 0;

		private static int AddInstrLookup(String pstrMnemonic, int iOpcode, int iOpCount) {
				// Just use a simple static int to keep track of the next instruction index in the
				// table.

				//        static int iInstrIndex = 0;

				// Make sure we haven't run out of instruction indices

				if (iInstrIndex >= ConstData.MAX_INSTR_LOOKUP_COUNT)
						return -1;

				// Set the mnemonic, opcode and operand count fields
				g_InstrTable[iInstrIndex] = new InstrLookup();
				g_InstrTable[iInstrIndex].pstrMnemonic = pstrMnemonic.toUpperCase();
				g_InstrTable[iInstrIndex].iOpcode = iOpcode;
				g_InstrTable[iInstrIndex].iOpCount = iOpCount;

				// Allocate space for the operand list

				g_InstrTable[iInstrIndex].OpList = new int[iOpCount];

				// Copy the instruction index into another variable so it can be returned to the caller

				int iReturnInstrIndex = iInstrIndex;

				// Increment the index for the next instruction

				++iInstrIndex;

				// Return the used index to the caller

				return iReturnInstrIndex;
		}

		/***************************************************************************************************************
		 * 
		 * SetOpType ()
		 * 
		 * Sets the operand type for the specified operand in the specified instruction.
		 */

		private static void SetOpType(int iInstrIndex, int iOpIndex, int iOpType) {
				g_InstrTable[iInstrIndex].OpList[iOpIndex] = iOpType;
		}

		// ---- Tables ----------------------------------------------------------------------------

		/***************************************************************************************************************
		 * 
		 * AddString ()
		 * 
		 * Adds a string to a linked list, blocking duplicate entries
		 */

		private static int AddString(LinkedList pList, String pstrString) {
				// ---- First check to see if the string is already in the list

				// Loop through each node in the list

				for (int iCurrNode = 0; iCurrNode < pList.size(); ++iCurrNode) {
						// If the current node's string equals the specified string, return its index

						if (((String) pList.get(iCurrNode)).equals(pstrString))
								return iCurrNode;

				}

				// Add the string to the list and return its index

				return AddNode(pList, pstrString);
		}

		/***************************************************************************************************************
		 * 
		 * AddFunc ()
		 * 
		 * Adds a function to the function table.
		 */

		private static int AddFunc(String pstrName, int iEntryPoint) {
				// If a function already exists with the specified name, exit and return an invalid
				// index

				if (GetFuncByName(pstrName) != null)
						return -1;

				// Create a new function node

				FuncNode pNewFunc = new FuncNode();

				// Initialize the new function
				pNewFunc.pstrName = pstrName;
				pNewFunc.iEntryPoint = iEntryPoint;

				// Add the function to the list and get its index

				int iIndex = g_FuncTable.size();

				// Set the function node's index

				pNewFunc.iIndex = iIndex;
				g_FuncTable.add(pNewFunc);
				// Return the new function's index

				return iIndex;
		}

		/***************************************************************************************************************
		 * 
		 * GetFuncByName ()
		 * 
		 * Returns a FuncNode structure pointer corresponding to the specified name.
		 */

		private static FuncNode GetFuncByName(String pstrName) {
				// If the table is empty, return a NULL pointer

				if (g_FuncTable == null)
						return null;

				// Traverse the list until the matching structure is found

				for (int iCurrNode = 0; iCurrNode < g_FuncTable.size(); ++iCurrNode) {
						// Create a pointer to the current function structure

						FuncNode pCurrFunc = (FuncNode) g_FuncTable.get(iCurrNode);

						// If the names match, return the current pointer

						if (pCurrFunc.pstrName.equals(pstrName))
								return pCurrFunc;

				}

				// The structure was not found, so return a NULL pointer

				return null;
		}

		/***************************************************************************************************************
		 * 
		 * SetFuncInfo ()
		 * 
		 * Fills in the remaining fields not initialized by AddFunc ().
		 */

		private static void SetFuncInfo(String pstrName, int iParamCount, int iLocalDataSize) {
				// Based on the function's name, find its node in the list

				FuncNode pFunc = GetFuncByName(pstrName);

				// Set the remaining fields

				pFunc.iParamCount = iParamCount;
				pFunc.iLocalDataSize = iLocalDataSize;
		}

		/***************************************************************************************************************
		 * 
		 * AddLabel ()
		 * 
		 * Adds a label to the label table.
		 */

		private static int AddLabel(String pstrIdent, int iTargetIndex, int iFuncIndex) {
				// If a label already exists, return -1

				if (GetLabelByIdent(pstrIdent, iFuncIndex) != null)
						return -1;

				// Create a new label node

				LabelNode pNewLabel = new LabelNode();

				// Initialize the new label

				pNewLabel.pstrIdent = pstrIdent;
				pNewLabel.iTargetIndex = iTargetIndex;
				pNewLabel.iFuncIndex = iFuncIndex;

				// Add the label to the list and get its index

				int iIndex = g_LabelTable.size();

				// Set the index of the label node

				pNewLabel.iIndex = iIndex;
				g_LabelTable.add(pNewLabel);

				// Return the new label's index

				return iIndex;
		}

		/***************************************************************************************************************
		 * 
		 * GetLabelByIdent ()
		 * 
		 * Returns a pointer to the label structure corresponding to the identifier and function index.
		 */

		private static LabelNode GetLabelByIdent(String pstrIdent, int iFuncIndex) {
				// If the table is empty, return a NULL pointer

				if (g_LabelTable == null)
						return null;

				// Traverse the list until the matching structure is found

				for (int iCurrNode = 0; iCurrNode < g_LabelTable.size(); ++iCurrNode) {
						// Create a pointer to the current label structure

						LabelNode pCurrLabel = (LabelNode) g_LabelTable.get(iCurrNode);

						// If the names and scopes match, return the current pointer

						if (pCurrLabel.pstrIdent.equals(pstrIdent) && pCurrLabel.iFuncIndex == iFuncIndex)
								return pCurrLabel;

				}

				// The structure was not found, so return a NULL pointer

				return null;
		}

		/***************************************************************************************************************
		 * 
		 * AddSymbol ()
		 * 
		 * Adds a symbol to the symbol table.
		 */

		private static int AddSymbol(String pstrIdent, int iSize, int iStackIndex, int iFuncIndex) {
				// If a label already exists

				if (GetSymbolByIdent(pstrIdent, iFuncIndex) != null)
						return -1;

				// Create a new symbol node

				SymbolNode pNewSymbol = new SymbolNode();

				// Initialize the new label
				pNewSymbol.pstrIdent = pstrIdent;
				pNewSymbol.iSize = iSize;
				pNewSymbol.iStackIndex = iStackIndex;
				pNewSymbol.iFuncIndex = iFuncIndex;

				// Add the symbol to the list and get its index

				int iIndex = g_SymbolTable.size();//AddNode ( g_SymbolTable, pNewSymbol );

				// Set the symbol node's index
				pNewSymbol.iIndex = iIndex;
				g_SymbolTable.add(pNewSymbol);
				// Return the new symbol's index

				return iIndex;
		}

		/***************************************************************************************************************
		 * 
		 * AddNode ()
		 * 
		 * Adds a node to a linked list and returns its index.
		 */

		private static int AddNode(LinkedList pList, Object pData) {
				pList.add(pData);
				return pList.size() - 1;
				//        // Create a new node
				//
				//        LinkedListNode * pNewNode = ( LinkedListNode * ) malloc ( sizeof ( LinkedListNode ) );
				//
				//        // Set the node's data to the specified pointer
				//
				//        pNewNode->pData = pData;
				//
				//        // Set the next pointer to NULL, since nothing will lie beyond it
				//
				//        pNewNode->pNext = NULL;
				//
				//        // If the list is currently empty, set both the head and tail pointers to the new node
				//
				//        if ( ! pList->iNodeCount )
				//        {
				//            // Point the head and tail of the list at the node
				//
				//            pList->pHead = pNewNode;
				//            pList->pTail = pNewNode;
				//        }
				//
				//        // Otherwise append it to the list and update the tail pointer
				//
				//        else
				//        {
				//            // Alter the tail's next pointer to point to the new node
				//
				//            pList->pTail->pNext = pNewNode;
				//
				//            // Update the list's tail pointer
				//
				//            pList->pTail = pNewNode;
				//        }
				//
				//        // Increment the node count
				//
				//        ++ pList->iNodeCount;
				//
				//        // Return the new size of the linked list - 1, which is the node's index
				//
				//        return pList->iNodeCount - 1;
		}

		/***************************************************************************************************************
		 * 
		 * GetSymbolByIdent ()
		 * 
		 * Returns a pointer to the symbol structure corresponding to the identifier and function index.
		 */

		private static SymbolNode GetSymbolByIdent(String pstrIdent, int iFuncIndex) {
				// If the table is empty, return a NULL pointer

				if (g_SymbolTable == null)
						return null;

				// Traverse the list until the matching structure is found

				for (int iCurrNode = 0; iCurrNode < g_SymbolTable.size(); ++iCurrNode) {
						// Create a pointer to the current symbol structure

						SymbolNode pCurrSymbol = (SymbolNode) g_SymbolTable.get(iCurrNode);

						// See if the names match

						if (pCurrSymbol.pstrIdent.equals(pstrIdent))

								// If the functions match, or if the existing symbol is global, they match.
								// Return the symbol.

								if (pCurrSymbol.iFuncIndex == iFuncIndex || pCurrSymbol.iStackIndex >= 0)
										return pCurrSymbol;

						// Otherwise move to the next node
				}

				// The structure was not found, so return a NULL pointer

				return null;
		}

		/***************************************************************************************************************
		 * 
		 * GetStackIndexByIdent ()
		 * 
		 * Returns a symbol's stack index based on its identifier and function index.
		 */

		private static int GetStackIndexByIdent(String pstrIdent, int iFuncIndex) {
				// Get the symbol's information

				SymbolNode pSymbol = GetSymbolByIdent(pstrIdent, iFuncIndex);

				// Return its stack index

				return pSymbol.iStackIndex;
		}

		/***************************************************************************************************************
		 * 
		 * GetSizeByIndent ()
		 * 
		 * Returns a variable's size based on its identifier.
		 */

		private static int GetSizeByIdent(String pstrIdent, int iFuncIndex) {
				// Get the symbol's information

				SymbolNode pSymbol = GetSymbolByIdent(pstrIdent, iFuncIndex);

				// Return its size

				return pSymbol.iSize;
		}
		
		// ---- Main ----------------------------------------------------------------------------------
		public static void main(String[] args) {
				if (args == null || args.length < 1) {
						args = new String[1];
						args[0] = System.getProperty("user.dir") + "/hello.e.XASM";
				}
				// Print the logo
				PrintLogo();
				int argc = args.length;
				// Validate the command line argument count
				if (argc < 1) {
						// If at least one filename isn't present, print the usage info and exit
						PrintUsage();
						return;
				}
				// Before going any further, we need to validate the specified filenames. This may
				// include appending file extensions if they aren't present, and possibly copying the
				// source filename to the executable filename if the user didn't provide one.

				// First make a global copy of the source filename and convert it to uppercase

				g_pstrSourceFilename = args[0].toUpperCase();

				// Check for the presence of the .XASM extension and add it if it's not there

				if (!g_pstrSourceFilename.contains(ConstData.SOURCE_FILE_EXT)) {
						// The extension was not found, so add it to string
						g_pstrSourceFilename += ConstData.SOURCE_FILE_EXT;
				}

				// Was an executable filename specified?
				if (argc > 1) {
						// Yes, so repeat the validation process

						g_pstrExecFilename = args[1].toUpperCase();

						// Check for the presence of the .XSE extension and add it if it's not there

						if (!g_pstrExecFilename.contains(ConstData.EXEC_FILE_EXT)) {
								// The extension was not found, so add it to string

								g_pstrExecFilename += ConstData.EXEC_FILE_EXT;
						}
				} else {
						// No, so base it on the source filename

						// First locate the start of the extension, and use pointer subtraction to find the index

						g_pstrExecFilename = g_pstrSourceFilename.replace(ConstData.SOURCE_FILE_EXT, ConstData.EXEC_FILE_EXT);
				}

				// Initialize the assembler

				Init();

				// Load the source file into memory

				LoadSourceFile();

				// Assemble the source file

				System.out.println("Assembling " + g_pstrSourceFilename + "...\n");

				AssmblSourceFile();

				// Dump the assembled executable to an .XSE file

				BuildXSE();

				// Print out assembly statistics

				PrintAssmblStats();

				// Free resources and perform general cleanup

				ShutDown();

		}

}
