package mmo.tools.script.msc;

import mmo.tools.script.msc.Struct.FuncNode;
import mmo.tools.script.msc.Struct.Link;
import mmo.tools.script.msc.Struct.Loop;
import mmo.tools.script.msc.Struct.StackList;
import mmo.tools.script.msc.Struct.SymbolNode;

/**
 * Author: cuirongzhou Date: 2007-12-3 Time: 15:34:26
 */
public class Parser extends Lexer {

		// ---- Include Files -------------------------------------------------------------------------
		//    #include "parser.h"
		//    #include "error.h"
		//    #include "lexer.h"
		//    #include "symbol_table.h"
		//    #include "func_table.h"
		//    #include "i_code.h"

		// ---- Globals -------------------------------------------------------------------------------
		public final static int MAX_FUNC_DECLARE_PARAM_COUNT = 32;
		// ---- Functions -------------------------------------------------------------------------

		int                     g_iCurrScope;                     // The current scope

		// ---- Loops -----------------------------------------------------------------------------

		StackList               g_LoopStack;                      // Loop handling stack
		                                                           //    public static Lexer lexer;
		                                                           //    public Parser(Lexer l)
		                                                           //    {
		                                                           //        lexer = l;
		                                                           //    }
		                                                           // ---- Functions -----------------------------------------------------------------------------

		public Parser() {
				super();
		}

		/***********************************************************************
		 * 
		 * InitStack ()
		 * 
		 * Initializes a stack.
		 */

		public void InitStack(StackList pStack) {
				// Initialize the stack's internal list
				pStack.ElmntList = new Link();
		}

		/***********************************************************************
		 * 
		 * FreeStack ()
		 * 
		 * Frees a stack.
		 */

		public void FreeStack(StackList pStack) {
				// Free the stack's internal list
				pStack.ElmntList.free();
				pStack.ElmntList = null;
		}

		/***********************************************************************
		 * 
		 * IsStackEmpty ()
		 * 
		 * Returns TRUE if the specified stack is empty, FALSE otherwise.
		 */

		int IsStackEmpty(StackList pStack) {
				if (pStack.ElmntList.getSize() > 0)
						return FALSE;
				else
						return TRUE;
		}

		/***********************************************************************
		 * 
		 * Push ()
		 * 
		 * Pushes an element onto a stack.
		 */

		public void Push(StackList pStack, Object pData) {
				// Add a node to the end of the stack's internal list

				pStack.ElmntList.addNode(pData);
		}

		/***********************************************************************
		 * 
		 * Pop ()
		 * 
		 * Pops an element off the top of a stack.
		 */

		public void Pop(StackList pStack) {
				// Free the tail node of the list and it's data
				pStack.ElmntList.delTailNode();
		}

		/***********************************************************************
		 * 
		 * Peek ()
		 * 
		 * Returns the element at the top of a stack.
		 */

		public Object Peek(StackList pStack) {
				// Return the data at the tail node of the list

				return pStack.ElmntList.getTailNode();
		}

		/***********************************************************************
		 * 
		 * ReadToken ()
		 * 
		 * Attempts to read a specific token and prints an error if its not
		 * found.
		 */

		public void ReadToken(int ReqToken) {
				// Determine if the next token is the required one

				if (GetNextToken() != ReqToken) {
						// If not, exit on a specific error

						String pstrErrorMssg = new String();
						switch (ReqToken) {
								case TOKEN_TYPE_INT:
										pstrErrorMssg = "Integer";
										break;

								case TOKEN_TYPE_FLOAT:
										pstrErrorMssg = "Float";
										break;

								case TOKEN_TYPE_IDENT:
										pstrErrorMssg = "Identifier";
										break;

								case TOKEN_TYPE_RSRVD_VAR:
										pstrErrorMssg = "var";
										break;

								case TOKEN_TYPE_RSRVD_TRUE:
										pstrErrorMssg = "true";
										break;

								case TOKEN_TYPE_RSRVD_FALSE:
										pstrErrorMssg = "false";
										break;

								case TOKEN_TYPE_RSRVD_IF:
										pstrErrorMssg = "if";
										break;

								case TOKEN_TYPE_RSRVD_ELSE:
										pstrErrorMssg = "else";
										break;

								case TOKEN_TYPE_RSRVD_BREAK:
										pstrErrorMssg = "break";
										break;

								case TOKEN_TYPE_RSRVD_CONTINUE:
										pstrErrorMssg = "continue";
										break;

								case TOKEN_TYPE_RSRVD_FOR:
										pstrErrorMssg = "for";
										break;

								case TOKEN_TYPE_RSRVD_WHILE:
										pstrErrorMssg = "while";
										break;

								case TOKEN_TYPE_RSRVD_FUNC:
										pstrErrorMssg = "func";
										break;

								case TOKEN_TYPE_RSRVD_RETURN:
										pstrErrorMssg = "return";
										break;

								case TOKEN_TYPE_RSRVD_HOST:
										pstrErrorMssg = "host";
										break;

								case TOKEN_TYPE_OP:
										pstrErrorMssg = "Operator";
										break;

								case TOKEN_TYPE_DELIM_COMMA:
										pstrErrorMssg = ",";
										break;

								case TOKEN_TYPE_DELIM_OPEN_PAREN:
										pstrErrorMssg = "(";
										break;

								case TOKEN_TYPE_DELIM_CLOSE_PAREN:
										pstrErrorMssg = ")";
										break;

								case TOKEN_TYPE_DELIM_OPEN_BRACE:
										pstrErrorMssg = "[";
										break;

								case TOKEN_TYPE_DELIM_CLOSE_BRACE:
										pstrErrorMssg = "]";
										break;

								case TOKEN_TYPE_DELIM_OPEN_CURLY_BRACE:
										pstrErrorMssg = "{";
										break;

								case TOKEN_TYPE_DELIM_CLOSE_CURLY_BRACE:
										pstrErrorMssg = "}";
										break;

								case TOKEN_TYPE_DELIM_SEMICOLON:
										pstrErrorMssg = ";";
										break;

								case TOKEN_TYPE_STRING:
										pstrErrorMssg = "String";
										break;
						}

						pstrErrorMssg += " expected";

						Error.ExitOnCodeError(pstrErrorMssg);
				}
		}

		/***********************************************************************
		 * 
		 * IsOpRelational ()
		 * 
		 * Determines if the specified operator is a relational operator.
		 */

		public int IsOpRelational(int iOpType) {
				if (iOpType != OP_TYPE_EQUAL && iOpType != OP_TYPE_NOT_EQUAL && iOpType != OP_TYPE_LESS
				                && iOpType != OP_TYPE_GREATER && iOpType != OP_TYPE_LESS_EQUAL
				                && iOpType != OP_TYPE_GREATER_EQUAL){
						return FALSE;
				}else{
						return TRUE;
				}
		}

		/***********************************************************************
		 * 
		 * IsOpLogical ()
		 * 
		 * Determines if the specified operator is a logical operator.
		 */

		public int IsOpLogical(int iOpType) {
				if (iOpType != OP_TYPE_LOGICAL_AND && iOpType != OP_TYPE_LOGICAL_OR && iOpType != OP_TYPE_LOGICAL_NOT){
						return FALSE;
				}else{
						return TRUE;
				}
		}

		/***********************************************************************
		 * 
		 * ParseSourceCode ()
		 * 
		 * Parses the source code from start to finish, generating a complete
		 * I-code representation.
		 */

		public void ParseSourceCode() {
				ResetLexer();

				// Initialize the loop stack
				g_LoopStack = new StackList();
				InitStack(g_LoopStack);

				// Set the current scope to global

				g_iCurrScope = SCOPE_GLOBAL;

				// Parse each line of code

				while (true) {
						// Parse the next statement and ignore an end of file marker

						ParseStatement();

						// If we're at the end of the token stream, break the parsing loop

						if (GetNextToken() == TOKEN_TYPE_END_OF_STREAM)
								break;
						else
								RewindTokenStream();
				}

				// Free the loop stack

				FreeStack(g_LoopStack);
		}

		/***********************************************************************
		 * 
		 * ParseStatement ()
		 * 
		 * Parses a statement.
		 */

		public void ParseStatement() {
				// If the next token is a semicolon, the statement is empty so return

				if (GetLookAheadChar() == ';') {
						ReadToken(TOKEN_TYPE_DELIM_SEMICOLON);
						return;
				}

				// Determine the initial token of the statement

				int InitToken = GetNextToken();

				// Branch to a parse function based on the token

				switch (InitToken) {
						// Unexpected end of file

						case TOKEN_TYPE_END_OF_STREAM:
								Error.ExitOnCodeError("Unexpected end of file");
								break;

						// Block

						case TOKEN_TYPE_DELIM_OPEN_CURLY_BRACE:
								ParseBlock();
								break;

						// Variable/array declaration

						case TOKEN_TYPE_RSRVD_VAR:
								ParseVar();
								break;

						// Host API function import

						case TOKEN_TYPE_RSRVD_HOST:
								ParseHost();
								break;

						// Function definition

						case TOKEN_TYPE_RSRVD_FUNC:
								ParseFunc();
								break;

						// if block

						case TOKEN_TYPE_RSRVD_IF:
								ParseIf();
								break;

						// while loop block

						case TOKEN_TYPE_RSRVD_WHILE:
								ParseWhile();
								break;

						// for loop block

						case TOKEN_TYPE_RSRVD_FOR:
								ParseFor();
								break;

						// break

						case TOKEN_TYPE_RSRVD_BREAK:
								ParseBreak();
								break;

						// continue

						case TOKEN_TYPE_RSRVD_CONTINUE:
								ParseContinue();
								break;

						// return

						case TOKEN_TYPE_RSRVD_RETURN:
								ParseReturn();
								break;

						// Assignment or Function Call

						case TOKEN_TYPE_IDENT: {
								// What kind of identifier is it?

								if (Msc.GetSymbolByIdent(GetCurrLexeme(), g_iCurrScope) != null) {
										// It's an identifier, so treat the statement as an assignment

										ParseAssign();
								} else if (Msc.GetFuncByName(GetCurrLexeme()) != null) {
										// It's a function

										// Annotate the line and parse the call

										Msc.AddICodeSourceLine(g_iCurrScope, GetCurrSourceLine());
										ParseFuncCall();

										// Verify the presence of the semicolon

										ReadToken(TOKEN_TYPE_DELIM_SEMICOLON);
								} else {
										// It's invalid

										Error.ExitOnCodeError("Invalid identifier");
								}

								break;
						}

								// Anything else is invalid

						default:
								Error.ExitOnCodeError("Unexpected input");
								break;
				}
		}

		/***********************************************************************
		 * 
		 * ParseBlock ()
		 * 
		 * Parses a code block.
		 *  { <Statement-List> }
		 */

		public void ParseBlock() {
				// Make sure we're not in the global scope

				if (g_iCurrScope == SCOPE_GLOBAL)
						Error.ExitOnCodeError("Code blocks illegal in global scope");

				// Read each statement until the end of the block

				while (GetLookAheadChar() != '}')
						ParseStatement();

				// Read the closing curly brace

				ReadToken(TOKEN_TYPE_DELIM_CLOSE_CURLY_BRACE);
		}

		/***********************************************************************
		 * 
		 * ParseVar ()
		 * 
		 * Parses the declaration of a variable or array and adds it to the
		 * symbol table.
		 * 
		 * var <Identifier>; var <Identifier> [ <Integer> ];
		 */

		public void ParseVar() {
				// Read an identifier token

				ReadToken(TOKEN_TYPE_IDENT);

				// Copy the current lexeme into a local string buffer to save the variable's identifier

				String pstrIdent = CopyCurrLexeme();

				// Set the size to 1 for a variable (an array will update this value)

				int iSize = 1;

				// Is the look-ahead character an open brace?

				if (GetLookAheadChar() == '[') {
						// Verify the open brace

						ReadToken(TOKEN_TYPE_DELIM_OPEN_BRACE);

						// If so, read an integer token

						ReadToken(TOKEN_TYPE_INT);

						// Convert the current lexeme to an integer to get the size

						iSize = Tool.atoi(GetCurrLexeme());

						// Read the closing brace

						ReadToken(TOKEN_TYPE_DELIM_CLOSE_BRACE);
				}

				// Add the identifier and size to the symbol table

				if (Msc.AddSymbol(pstrIdent, iSize, g_iCurrScope, SYMBOL_TYPE_VAR) == -1)
						Error.ExitOnCodeError("Identifier redefinition");

				// Read the semicolon

				ReadToken(TOKEN_TYPE_DELIM_SEMICOLON);
		}

		/***********************************************************************
		 * 
		 * ParseHostAPIFuncImport ()
		 * 
		 * Parses the importing of a host API function.
		 * 
		 * host <Identifier> ();
		 */

		public void ParseHost() {
				// Read the host API function name

				ReadToken(TOKEN_TYPE_IDENT);

				// Add the function to the function table with the host API flag set

				if (Msc.AddFunc(GetCurrLexeme(), TRUE) == -1)
						Error.ExitOnCodeError("Function redefinition");

				// Make sure the function name is followed with ()

				ReadToken(TOKEN_TYPE_DELIM_OPEN_PAREN);
				ReadToken(TOKEN_TYPE_DELIM_CLOSE_PAREN);

				// Read the semicolon

				ReadToken(TOKEN_TYPE_DELIM_SEMICOLON);
		}

		/***********************************************************************
		 * 
		 * ParseFunc ()
		 * 
		 * Parses a function.
		 * 
		 * func <Identifier> ( <Parameter-List> ) <Statement>
		 */

		public void ParseFunc() {
				// Make sure we're not already in a function

				if (g_iCurrScope != SCOPE_GLOBAL)
						Error.ExitOnCodeError("Nested functions illegal");

				// Read the function name

				ReadToken(TOKEN_TYPE_IDENT);

				// Add the non-host API function to the function table and get its index

				int iFuncIndex = Msc.AddFunc(GetCurrLexeme(), FALSE);

				// Check for a function redefinition

				if (iFuncIndex == -1)
						Error.ExitOnCodeError("Function redefinition");

				// Set the scope to the function

				g_iCurrScope = iFuncIndex;

				// Read the opening parenthesis

				ReadToken(TOKEN_TYPE_DELIM_OPEN_PAREN);

				// Use the look-ahead character to determine if the function takes parameters

				if (GetLookAheadChar() != ')') {
						// If the function being defined is _Main (), flag an error since _Main ()
						// cannot accept paraemters

						if (g_ScriptHeader.iIsMainFuncPresent == TRUE && g_ScriptHeader.iMainFuncIndex == iFuncIndex) {
								Error.ExitOnCodeError("_Main () cannot accept parameters");
						}

						// Start the parameter count at zero

						int iParamCount = 0;

						// Crete an array to store the parameter list locally

						String[] ppstrParamList = new String[MAX_FUNC_DECLARE_PARAM_COUNT];

						// Read the parameters

						while (true) {
								// Read the identifier

								ReadToken(TOKEN_TYPE_IDENT);

								// Copy the current lexeme to the parameter list array

								ppstrParamList[iParamCount] = CopyCurrLexeme();

								// Increment the parameter count

								++iParamCount;

								// Check again for the closing parenthesis to see if the parameter list is done

								if (GetLookAheadChar() == ')')
										break;

								// Otherwise read a comma and move to the next parameter

								ReadToken(TOKEN_TYPE_DELIM_COMMA);
						}

						// Set the final parameter count

						Msc.SetFuncParamCount(g_iCurrScope, iParamCount);

						// Write the parameters to the function's symbol table in reverse order, so they'll
						// be emitted from right-to-left

						while (iParamCount > 0) {
								--iParamCount;

								// Add the parameter to the symbol table

								Msc.AddSymbol(ppstrParamList[iParamCount], 1, g_iCurrScope, SYMBOL_TYPE_PARAM);
						}
				}

				// Read the closing parenthesis

				ReadToken(TOKEN_TYPE_DELIM_CLOSE_PAREN);

				// Read the opening curly brace

				ReadToken(TOKEN_TYPE_DELIM_OPEN_CURLY_BRACE);

				// Parse the function's body

				ParseBlock();

				// Return to the global scope

				g_iCurrScope = SCOPE_GLOBAL;
		}

		/***********************************************************************
		 * 
		 * ParseExpr ()
		 * 
		 * Parses an expression.
		 */
		public void ParseExpr() {
				
				int iInstrIndex;

				// The current operator type
				int iOpType;

				// Parse the subexpression
				ParseSubExpr();

				// Parse any subsequent relational or logical operators

				while (true) {
						
						// Get the next token

						if (GetNextToken() != TOKEN_TYPE_OP
						                || (IsOpRelational(GetCurrOp()) == FALSE && IsOpLogical(GetCurrOp()) == FALSE)) {
								RewindTokenStream();
								break;
						}
						// Save the operator

						iOpType = GetCurrOp();

						// Parse the second term

						ParseSubExpr();

						// Pop the first operand into _T1

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);

						// Pop the second operand into _T0

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

						// ---- Perform the binary operation associated with the specified operator

						// Determine the operator type

						if (IsOpRelational(iOpType) == TRUE) {
								// Get a pair of free jump target indices

								int iTrueJumpTargetIndex = Msc.GetNextJumpTargetIndex(), iExitJumpTargetIndex = Msc
								                .GetNextJumpTargetIndex();

								// It's a relational operator

								switch (iOpType) {
										// Equal

										case OP_TYPE_EQUAL: {
												// Generate a JE instruction

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JE);
												break;
										}

												// Not Equal

										case OP_TYPE_NOT_EQUAL: {
												// Generate a JNE instruction

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JNE);
												break;
										}

												// Greater

										case OP_TYPE_GREATER: {
												// Generate a JG instruction

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JG);
												break;
										}

												// Less

										case OP_TYPE_LESS: {
												// Generate a JL instruction

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JL);
												break;
										}

												// Greater or Equal

										case OP_TYPE_GREATER_EQUAL: {
												// Generate a JGE instruction

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JGE);
												break;
										}

												// Less Than or Equal

										case OP_TYPE_LESS_EQUAL: {
												// Generate a JLE instruction

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JLE);
												break;
										}
								}

								// Add the jump instruction's operands (_T0 and _T1)

								Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
								Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);
								Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iTrueJumpTargetIndex);

								// Generate the outcome for falsehood

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);

								// Generate a jump past the true outcome

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JMP);
								Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iExitJumpTargetIndex);

								// Set the jump target for the true outcome

								Msc.AddICodeJumpTarget(g_iCurrScope, iTrueJumpTargetIndex);

								// Generate the outcome for truth

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 1);

								// Set the jump target for exiting the operand evaluation

								Msc.AddICodeJumpTarget(g_iCurrScope, iExitJumpTargetIndex);
						} else {
								// It must be a logical operator

								switch (iOpType) {
										// And

										case OP_TYPE_LOGICAL_AND: {
												// Get a pair of free jump target indices

												int iFalseJumpTargetIndex = Msc.GetNextJumpTargetIndex(), iExitJumpTargetIndex = Msc
												                .GetNextJumpTargetIndex();

												// JE _T0, 0, True

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JE);
												Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
												Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);
												Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex,
												                iFalseJumpTargetIndex);

												// JE _T1, 0, True

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JE);
												Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);
												Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);
												Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex,
												                iFalseJumpTargetIndex);

												// Push 1

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
												Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 1);

												// Jmp Exit

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JMP);
												Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex,
												                iExitJumpTargetIndex);

												// L0: (False)

												Msc.AddICodeJumpTarget(g_iCurrScope, iFalseJumpTargetIndex);

												// Push 0

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
												Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);

												// L1: (Exit)

												Msc.AddICodeJumpTarget(g_iCurrScope, iExitJumpTargetIndex);

												break;
										}

												// Or

										case OP_TYPE_LOGICAL_OR: {
												// Get a pair of free jump target indices

												int iTrueJumpTargetIndex = Msc.GetNextJumpTargetIndex(), iExitJumpTargetIndex = Msc
												                .GetNextJumpTargetIndex();

												// JNE _T0, 0, True

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JNE);
												Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
												Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);
												Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex,
												                iTrueJumpTargetIndex);

												// JNE _T1, 0, True

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JNE);
												Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);
												Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);
												Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex,
												                iTrueJumpTargetIndex);

												// Push 0

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
												Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);

												// Jmp Exit

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JMP);
												Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex,
												                iExitJumpTargetIndex);

												// L0: (True)

												Msc.AddICodeJumpTarget(g_iCurrScope, iTrueJumpTargetIndex);

												// Push 1

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
												Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 1);

												// L1: (Exit)

												Msc.AddICodeJumpTarget(g_iCurrScope, iExitJumpTargetIndex);

												break;
										}
								}
						}
				}
		}

		/***********************************************************************
		 * 
		 * ParseSubExpr ()
		 * 
		 * Parses a sub expression.
		 */

		public void ParseSubExpr() {
				int iInstrIndex;

				// The current operator type

				int iOpType;

				// Parse the first term
				ParseTerm();

				// Parse any subsequent +, - or $ operators

				while (true) {
						// Get the next token
						
						if (GetNextToken() != TOKEN_TYPE_OP
						                || (GetCurrOp() != OP_TYPE_ADD && GetCurrOp() != OP_TYPE_SUB && GetCurrOp() != OP_TYPE_CONCAT)) {
								RewindTokenStream();
								break;
						}
						// Save the operator

						iOpType = GetCurrOp();

						// Parse the second term

						ParseTerm();

						// Pop the first operand into _T1

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);

						// Pop the second operand into _T0

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

						// Perform the binary operation associated with the specified operator

						int iOpInstr = -1;
						switch (iOpType) {
								// Binary addition

								case OP_TYPE_ADD:
										iOpInstr = INSTR_ADD;
										break;

								// Binary subtraction

								case OP_TYPE_SUB:
										iOpInstr = INSTR_SUB;
										break;

								// Binary string concatenation

								case OP_TYPE_CONCAT:
										iOpInstr = INSTR_CONCAT;
										break;
						}
						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, iOpInstr);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);

						// Push the result (stored in _T0)

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
				}
		}

		/***********************************************************************
		 * 
		 * ParseTerm ()
		 * 
		 * Parses a term.
		 */

		public void ParseTerm() {
				int iInstrIndex;

				// The current operator type

				int iOpType;

				// Parse the first factor
				ParseFactor();

				// Parse any subsequent *, /, %, ^, &, |, #, << and >> operators

				while (true) {
						// Get the next token
						
						if (GetNextToken() != TOKEN_TYPE_OP
						                || (GetCurrOp() != OP_TYPE_MUL && GetCurrOp() != OP_TYPE_DIV
						                                && GetCurrOp() != OP_TYPE_MOD && GetCurrOp() != OP_TYPE_EXP
						                                && GetCurrOp() != OP_TYPE_BITWISE_AND
						                                && GetCurrOp() != OP_TYPE_BITWISE_OR
						                                && GetCurrOp() != OP_TYPE_BITWISE_XOR
						                                && GetCurrOp() != OP_TYPE_BITWISE_SHIFT_LEFT && GetCurrOp() != OP_TYPE_BITWISE_SHIFT_RIGHT)) {
								RewindTokenStream();
								break;
						}
						// Save the operator

						iOpType = GetCurrOp();

						// Parse the second factor

						ParseFactor();

						// Pop the first operand into _T1

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);

						// Pop the second operand into _T0

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

						// Perform the binary operation associated with the specified operator

						int iOpInstr = -1;
						switch (iOpType) {
								// Binary multiplication

								case OP_TYPE_MUL:
										iOpInstr = INSTR_MUL;
										break;

								// Binary division

								case OP_TYPE_DIV:
										iOpInstr = INSTR_DIV;
										break;

								// Binary modulus

								case OP_TYPE_MOD:
										iOpInstr = INSTR_MOD;
										break;

								// Binary exponentiation

								case OP_TYPE_EXP:
										iOpInstr = INSTR_EXP;
										break;

								// Binary bitwise AND

								case OP_TYPE_BITWISE_AND:
										iOpInstr = INSTR_AND;
										break;

								// Binary bitwise OR

								case OP_TYPE_BITWISE_OR:
										iOpInstr = INSTR_OR;
										break;

								// Binary bitwise XOR

								case OP_TYPE_BITWISE_XOR:
										iOpInstr = INSTR_XOR;
										break;

								// Binary bitwise shift left

								case OP_TYPE_BITWISE_SHIFT_LEFT:
										iOpInstr = INSTR_SHL;
										break;

								// Binary bitwise shift left

								case OP_TYPE_BITWISE_SHIFT_RIGHT:
										iOpInstr = INSTR_SHR;
										break;
						}
						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, iOpInstr);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);

						// Push the result (stored in _T0)

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
				}
		}

		/***********************************************************************
		 * 
		 * ParseFactor ()
		 * 
		 * Parses a factor.
		 */

		public void ParseFactor() {
				int iInstrIndex;
				int iUnaryOpPending = FALSE;
				int iOpType = -1;
				// First check for a unary operator

				if (GetNextToken() == TOKEN_TYPE_OP && (GetCurrOp() == OP_TYPE_ADD || GetCurrOp() == OP_TYPE_SUB
				                                || GetCurrOp() == OP_TYPE_BITWISE_NOT || GetCurrOp() == OP_TYPE_LOGICAL_NOT)) {
						// If it was found, save it and set the unary operator flag

						iUnaryOpPending = TRUE;
						iOpType = GetCurrOp();
				} else {
						// Otherwise rewind the token stream

						RewindTokenStream();
				}

				// Determine which type of factor we're dealing with based on the next token

				switch (GetNextToken()) {
						// It's a true or false constant, so push either 0 and 1 onto the stack

						case TOKEN_TYPE_RSRVD_TRUE:
						case TOKEN_TYPE_RSRVD_FALSE:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex,
								                GetCurrToken() == TOKEN_TYPE_RSRVD_TRUE ? 1 : 0);
								break;

						// It's an integer literal, so push it onto the stack

						case TOKEN_TYPE_INT:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, Tool.atoi(GetCurrLexeme()));
								break;

						// It's a float literal, so push it onto the stack

						case TOKEN_TYPE_FLOAT:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddFloatICodeOp(g_iCurrScope, iInstrIndex, (float) Tool.atof(GetCurrLexeme()));
								break;

						// It's a string literal, so add it to the string table and push the resulting
						// string index onto the stack

						case TOKEN_TYPE_STRING: {
								int iStringIndex = Msc.AddString(g_StringTable, GetCurrLexeme());
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddStringICodeOp(g_iCurrScope, iInstrIndex, iStringIndex);
								break;
						}

								// It's an identifier

						case TOKEN_TYPE_IDENT: {
								// First find out if the identifier is a variable or array

								SymbolNode pSymbol = Msc.GetSymbolByIdent(GetCurrLexeme(), g_iCurrScope);
								if (pSymbol != null) {
										// Does an array index follow the identifier?

										if (GetLookAheadChar() == '[') {
												// Ensure the variable is an array

												if (pSymbol.iSize == 1)
														Error.ExitOnCodeError("Invalid array");

												// Verify the opening brace

												ReadToken(TOKEN_TYPE_DELIM_OPEN_BRACE);

												// Make sure an expression is present

												if (GetLookAheadChar() == ']')
														Error.ExitOnCodeError("Invalid expression");

												// Parse the index as an expression recursively

												ParseExpr();

												// Make sure the index is closed

												ReadToken(TOKEN_TYPE_DELIM_CLOSE_BRACE);

												// Pop the resulting value into _T0 and use it as the index variable

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
												Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

												// Push the original identifier onto the stack as an array, indexed
												// with _T0

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
												Msc.AddArrayIndexVarICodeOp(g_iCurrScope, iInstrIndex, pSymbol.iIndex,
												                g_iTempVar_0_SymbolIndex);
										} else {
												// If not, make sure the identifier is not an array, and push it onto
												// the stack

												if (pSymbol.iSize == 1) {
														iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
														Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, pSymbol.iIndex);
												} else {
														Error.ExitOnCodeError("Arrays must be indexed");
												}
										}
								} else {
										// The identifier wasn't a variable or array, so find out if it's a
										// function

										if (Msc.GetFuncByName(GetCurrLexeme()) != null) {
												// It is, so parse the call

												ParseFuncCall();

												// Push the return value

												iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
												Msc.AddRegICodeOp(g_iCurrScope, iInstrIndex, REG_CODE_RETVAL);
										}
								}

								break;
						}

								// It's a nested expression, so call ParseExpr () recursively and validate the
								// presence of the closing parenthesis

						case TOKEN_TYPE_DELIM_OPEN_PAREN:
								ParseExpr();
								ReadToken(TOKEN_TYPE_DELIM_CLOSE_PAREN);
								break;

						// Anything else is invalid

						default:
								Error.ExitOnCodeError("Invalid input");
				}

				// Is a unary operator pending?

				if (iUnaryOpPending != FALSE) {
						// If so, pop the result of the factor off the top of the stack

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

						// Perform the unary operation

						if (iOpType == OP_TYPE_LOGICAL_NOT) {
								// Get a pair of free jump target indices

								int iTrueJumpTargetIndex = Msc.GetNextJumpTargetIndex(), iExitJumpTargetIndex = Msc
								                .GetNextJumpTargetIndex();

								// JE _T0, 0, True

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JE);
								Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
								Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);
								Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iTrueJumpTargetIndex);

								// Push 0

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);

								// Jmp L1

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JMP);
								Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iExitJumpTargetIndex);

								// L0: (True)

								Msc.AddICodeJumpTarget(g_iCurrScope, iTrueJumpTargetIndex);

								// Push 1

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 1);

								// L1: (Exit)

								Msc.AddICodeJumpTarget(g_iCurrScope, iExitJumpTargetIndex);
						} else {
								int iOpIndex = -1;
								switch (iOpType) {
										// Negation

										case OP_TYPE_SUB:
												iOpIndex = INSTR_NEG;
												break;

										// Bitwise not

										case OP_TYPE_BITWISE_NOT:
												iOpIndex = INSTR_NOT;
												break;
								}

								// Add the instruction's operand

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, iOpIndex);
								Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

								// Push the result onto the stack

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_PUSH);
								Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
						}
				}
		}

		/***********************************************************************
		 * 
		 * ParseIf ()
		 * 
		 * Parses an if block.
		 * 
		 * if ( <Expression> ) <Statement> if ( <Expression> ) <Statement> else
		 * <Statement>
		 */

		public void ParseIf() {
				int iInstrIndex;

				// Make sure we're inside a function

				if (g_iCurrScope == SCOPE_GLOBAL)
						Error.ExitOnCodeError("if illegal in global scope");

				// Annotate the line

				Msc.AddICodeSourceLine(g_iCurrScope, GetCurrSourceLine());

				// Create a jump target to mark the beginning of the false block

				int iFalseJumpTargetIndex = Msc.GetNextJumpTargetIndex();

				// Read the opening parenthesis

				ReadToken(TOKEN_TYPE_DELIM_OPEN_PAREN);

				// Parse the expression and leave the result on the stack

				ParseExpr();

				// Read the closing parenthesis

				ReadToken(TOKEN_TYPE_DELIM_CLOSE_PAREN);

				// Pop the result into _T0 and compare it to zero

				iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
				Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

				// If the result is zero, jump to the false target

				iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JE);
				Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
				Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);
				Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iFalseJumpTargetIndex);

				// Parse the true block

				ParseStatement();

				// Look for an else clause

				if (GetNextToken() == TOKEN_TYPE_RSRVD_ELSE) {
						// If it's found, append the true block with an unconditional jump past the false
						// block

						int iSkipFalseJumpTargetIndex = Msc.GetNextJumpTargetIndex();
						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JMP);
						Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iSkipFalseJumpTargetIndex);

						// Place the false target just before the false block

						Msc.AddICodeJumpTarget(g_iCurrScope, iFalseJumpTargetIndex);

						// Parse the false block

						ParseStatement();

						// Set a jump target beyond the false block

						Msc.AddICodeJumpTarget(g_iCurrScope, iSkipFalseJumpTargetIndex);
				} else {
						// Otherwise, put the token back

						RewindTokenStream();

						// Place the false target after the true block

						Msc.AddICodeJumpTarget(g_iCurrScope, iFalseJumpTargetIndex);
				}
		}

		/***********************************************************************
		 * 
		 * ParseWhile ()
		 * 
		 * Parses a while loop block.
		 * 
		 * while ( <Expression> ) <Statement>
		 */

		public void ParseWhile() {
				int iInstrIndex;

				// Make sure we're inside a function

				if (g_iCurrScope == SCOPE_GLOBAL)
						Error.ExitOnCodeError("Statement illegal in global scope");

				// Annotate the line

				Msc.AddICodeSourceLine(g_iCurrScope, GetCurrSourceLine());

				// Get two jump targets; for the top and bottom of the loop

				int iStartTargetIndex = Msc.GetNextJumpTargetIndex(), iEndTargetIndex = Msc.GetNextJumpTargetIndex();

				// Set a jump target at the top of the loop

				Msc.AddICodeJumpTarget(g_iCurrScope, iStartTargetIndex);

				// Read the opening parenthesis

				ReadToken(TOKEN_TYPE_DELIM_OPEN_PAREN);

				// Parse the expression and leave the result on the stack

				ParseExpr();

				// Read the closing parenthesis

				ReadToken(TOKEN_TYPE_DELIM_CLOSE_PAREN);

				// Pop the result into _T0 and jump out of the loop if it's nonzero

				iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
				Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

				iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JE);
				Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
				Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);
				Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iEndTargetIndex);

				// Create a new loop instance structure

				Loop pLoop = new Loop();

				// Set the starting and ending jump target indices

				pLoop.iStartTargetIndex = iStartTargetIndex;
				pLoop.iEndTargetIndex = iEndTargetIndex;

				// Push the loop structure onto the stack

				Push(g_LoopStack, pLoop);

				// Parse the loop body

				ParseStatement();

				// Pop the loop instance off the stack

				Pop(g_LoopStack);

				// Unconditionally jump back to the start of the loop

				iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JMP);
				Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iStartTargetIndex);

				// Set a jump target for the end of the loop

				Msc.AddICodeJumpTarget(g_iCurrScope, iEndTargetIndex);
		}

		/***********************************************************************
		 * 
		 * ParseFor ()
		 * 
		 * Parses a for loop block.
		 * 
		 * for ( <Initializer>; <Condition>; <Perpetuator> ) <Statement>
		 */

		public void ParseFor() {
				if (g_iCurrScope == SCOPE_GLOBAL)
						Error.ExitOnCodeError("for illegal in global scope");

				// Annotate the line

				Msc.AddICodeSourceLine(g_iCurrScope, GetCurrSourceLine());

				/*
				 * A for loop parser implementation could go here
				 */
		}

		/***********************************************************************
		 * 
		 * ParseBreak ()
		 * 
		 * Parses a break statement.
		 */

		public void ParseBreak() {
				// Make sure we're in a loop

				if (IsStackEmpty(g_LoopStack) == TRUE)
						Error.ExitOnCodeError("break illegal outside loops");

				// Annotate the line

				Msc.AddICodeSourceLine(g_iCurrScope, GetCurrSourceLine());

				// Attempt to read the semicolon

				ReadToken(TOKEN_TYPE_DELIM_SEMICOLON);

				// Get the jump target index for the end of the loop

				int iTargetIndex = ((Loop) Peek(g_LoopStack)).iEndTargetIndex;

				// Unconditionally jump to the end of the loop

				int iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JMP);
				Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iTargetIndex);
		}

		/***********************************************************************
		 * 
		 * ParseContinue ()
		 * 
		 * Parses a continue statement.
		 */

		void ParseContinue() {
				// Make sure we're inside a function

				if (IsStackEmpty(g_LoopStack) == TRUE)
						Error.ExitOnCodeError("continue illegal outside loops");

				// Annotate the line

				Msc.AddICodeSourceLine(g_iCurrScope, GetCurrSourceLine());

				// Attempt to read the semicolon

				ReadToken(TOKEN_TYPE_DELIM_SEMICOLON);

				// Get the jump target index for the start of the loop

				int iTargetIndex = ((Loop) Peek(g_LoopStack)).iStartTargetIndex;

				// Unconditionally jump to the end of the loop

				int iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_JMP);
				Msc.AddJumpTargetICodeOp(g_iCurrScope, iInstrIndex, iTargetIndex);
		}

		/***********************************************************************
		 * 
		 * ParseReturn ()
		 * 
		 * Parses a return statement.
		 * 
		 * return; return <expr>;
		 */

		public void ParseReturn() {
				int iInstrIndex;

				// Make sure we're inside a function

				if (g_iCurrScope == SCOPE_GLOBAL)
						Error.ExitOnCodeError("return illegal in global scope");

				// Annotate the line

				Msc.AddICodeSourceLine(g_iCurrScope, GetCurrSourceLine());

				// If a semicolon doesn't appear to follow, parse the expression and place it in
				// _RetVal

				if (GetLookAheadChar() != ';') {
						// Parse the expression to calculate the return value and leave the result on the stack.

						ParseExpr();

						// Determine which function we're returning from

						if (g_ScriptHeader.iIsMainFuncPresent == TRUE && g_ScriptHeader.iMainFuncIndex == g_iCurrScope) {
								// It is _Main (), so pop the result into _T0

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
								Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
						} else {
								// It's not _Main, so pop the result into the _RetVal register

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
								Msc.AddRegICodeOp(g_iCurrScope, iInstrIndex, REG_CODE_RETVAL);
						}
				} else {
						// Clear _T0 in case we're exiting _Main ()

						if (g_ScriptHeader.iIsMainFuncPresent == TRUE && g_ScriptHeader.iMainFuncIndex == g_iCurrScope) {

								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_MOV);
								Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
								Msc.AddIntICodeOp(g_iCurrScope, iInstrIndex, 0);
						}
				}

				if (g_ScriptHeader.iIsMainFuncPresent == TRUE && g_ScriptHeader.iMainFuncIndex == g_iCurrScope) {
						// It's _Main, so exit the script with _T0 as the exit code

						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_EXIT);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
				} else {
						// It's not _Main, so return from the function

						Msc.AddICodeInstr(g_iCurrScope, INSTR_RET);
				}

				// Validate the presence of the semicolon

				ReadToken(TOKEN_TYPE_DELIM_SEMICOLON);
		}

		/***********************************************************************
		 * 
		 * ParseAssign ()
		 * 
		 * Parses an assignment statement.
		 * 
		 * <Ident> <Assign-Op> <Expr>;
		 */

		public void ParseAssign() {
				// Make sure we're inside a function

				if (g_iCurrScope == SCOPE_GLOBAL)
						Error.ExitOnCodeError("Assignment illegal in global scope");

				int iInstrIndex;

				// Assignment operator

				int iAssignOp = -1;

				// Annotate the line

				Msc.AddICodeSourceLine(g_iCurrScope, GetCurrSourceLine());

				// ---- Parse the variable or array

				SymbolNode pSymbol = Msc.GetSymbolByIdent(GetCurrLexeme(), g_iCurrScope);

				// Does an array index follow the identifier?

				int iIsArray = FALSE;
				if (GetLookAheadChar() == '[') {
						// Ensure the variable is an array

						if (pSymbol.iSize == 1)
								Error.ExitOnCodeError("Invalid array");

						// Verify the opening brace

						ReadToken(TOKEN_TYPE_DELIM_OPEN_BRACE);

						// Make sure an expression is present

						if (GetLookAheadChar() == ']')
								Error.ExitOnCodeError("Invalid expression");

						// Parse the index as an expression

						ParseExpr();

						// Make sure the index is closed

						ReadToken(TOKEN_TYPE_DELIM_CLOSE_BRACE);

						// Set the array flag

						iIsArray = TRUE;
				} else {
						// Make sure the variable isn't an array

						if (pSymbol.iSize > 1)
								Error.ExitOnCodeError("Arrays must be indexed");
				}

				// ---- Parse the assignment operator

				if (GetNextToken() != TOKEN_TYPE_OP
				                && (GetCurrOp() != OP_TYPE_ASSIGN && GetCurrOp() != OP_TYPE_ASSIGN_ADD
				                                && GetCurrOp() != OP_TYPE_ASSIGN_SUB
				                                && GetCurrOp() != OP_TYPE_ASSIGN_MUL
				                                && GetCurrOp() != OP_TYPE_ASSIGN_DIV
				                                && GetCurrOp() != OP_TYPE_ASSIGN_MOD
				                                && GetCurrOp() != OP_TYPE_ASSIGN_EXP
				                                && GetCurrOp() != OP_TYPE_ASSIGN_CONCAT
				                                && GetCurrOp() != OP_TYPE_ASSIGN_AND
				                                && GetCurrOp() != OP_TYPE_ASSIGN_OR
				                                && GetCurrOp() != OP_TYPE_ASSIGN_XOR
				                                && GetCurrOp() != OP_TYPE_ASSIGN_SHIFT_LEFT && GetCurrOp() != OP_TYPE_ASSIGN_SHIFT_RIGHT))
						Error.ExitOnCodeError("Illegal assignment operator");
				else
						iAssignOp = GetCurrOp();

				// ---- Parse the value expression

				ParseExpr();

				// Validate the presence of the semicolon

				ReadToken(TOKEN_TYPE_DELIM_SEMICOLON);

				// Pop the value into _T0

				iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
				Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);

				// If the variable was an array, pop the top of the stack into _T1 for use as the index

				if (iIsArray == TRUE) {
						iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_POP);
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_1_SymbolIndex);
				}

				// ---- Generate the I-code for the assignment instruction

				switch (iAssignOp) {
						// =

						case OP_TYPE_ASSIGN:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_MOV);
								break;

						// +=

						case OP_TYPE_ASSIGN_ADD:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_ADD);
								break;

						// -=

						case OP_TYPE_ASSIGN_SUB:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_SUB);
								break;

						// *=

						case OP_TYPE_ASSIGN_MUL:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_MUL);
								break;

						// /=

						case OP_TYPE_ASSIGN_DIV:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_DIV);
								break;

						// %=

						case OP_TYPE_ASSIGN_MOD:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_MOD);
								break;

						// ^=

						case OP_TYPE_ASSIGN_EXP:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_EXP);
								break;

						// $=

						case OP_TYPE_ASSIGN_CONCAT:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_CONCAT);
								break;

						// &=

						case OP_TYPE_ASSIGN_AND:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_AND);
								break;

						// |=

						case OP_TYPE_ASSIGN_OR:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_OR);
								break;

						// #=

						case OP_TYPE_ASSIGN_XOR:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_XOR);
								break;

						// <<=

						case OP_TYPE_ASSIGN_SHIFT_LEFT:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_SHL);
								break;

						// >>=

						case OP_TYPE_ASSIGN_SHIFT_RIGHT:
								iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, INSTR_SHR);
								break;
				}

				// Generate the destination operand

				if (iIsArray == TRUE)
						Msc.AddArrayIndexVarICodeOp(g_iCurrScope, iInstrIndex, pSymbol.iIndex, g_iTempVar_1_SymbolIndex);
				else
						Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, pSymbol.iIndex);

				// Generate the source

				Msc.AddVarICodeOp(g_iCurrScope, iInstrIndex, g_iTempVar_0_SymbolIndex);
		}

		/***********************************************************************
		 * 
		 * ParseFuncCall ()
		 * 
		 * Parses a function call
		 * 
		 * <Ident> ( <Expr>, <Expr> );
		 */

		public void ParseFuncCall() {
				// Get the function by it's identifier

				FuncNode pFunc = Msc.GetFuncByName(GetCurrLexeme());

				// It is, so start the parameter count at zero

				int iParamCount = 0;

				// Attempt to read the opening parenthesis

				ReadToken(TOKEN_TYPE_DELIM_OPEN_PAREN);

				// Parse each parameter and push it onto the stack

				while (true) {
						// Find out if there's another parameter to push

						if (GetLookAheadChar() != ')') {
								// There is, so parse it as an expression

								ParseExpr();

								// Increment the parameter count and make sure it's not greater than the amount
								// accepted by the function (unless it's a host API function

								++iParamCount;
								if (pFunc.iIsHostAPI == FALSE && iParamCount > pFunc.iParamCount)
										Error.ExitOnCodeError("Too many parameters");

								// Unless this is the final parameter, attempt to read a comma

								if (GetLookAheadChar() != ')')
										ReadToken(TOKEN_TYPE_DELIM_COMMA);
						} else {
								// There isn't, so break the loop and complete the call

								break;
						}
				}

				// Attempt to read the closing parenthesis

				ReadToken(TOKEN_TYPE_DELIM_CLOSE_PAREN);

				// Make sure the parameter wasn't passed too few parameters (unless
				// it's a host API function)

				if (pFunc.iIsHostAPI == FALSE && iParamCount < pFunc.iParamCount)
						Error.ExitOnCodeError("Too few parameters");

				// Call the function, but make sure the right call instruction is used

				int iCallInstr = INSTR_CALL;
				if (pFunc.iIsHostAPI == TRUE)
						iCallInstr = INSTR_CALLHOST;

				int iInstrIndex = Msc.AddICodeInstr(g_iCurrScope, iCallInstr);
				Msc.AddFuncICodeOp(g_iCurrScope, iInstrIndex, pFunc.iIndex);
		}
}
