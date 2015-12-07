package mmo.tools.script.msc;

import mmo.tools.script.msc.Struct.Link;

/**
 * Author: cuirongzhou Date: 2007-11-28 Time: 14:53:27
 */
public class Lexer extends Const {
	// ---- Lexemes ---------------------------------------------------------------------------

	public static final int MAX_LEXEME_SIZE                    = 1024; // Maximum individual lexeme size

	// ---- Operators -------------------------------------------------------------------------

	public static final int MAX_OP_STATE_COUNT                 = 32;  // Maximum number of operator
	// states

	// ---- Delimiters ------------------------------------------------------------------------

	public static final int MAX_DELIM_COUNT                    = 24;  // Maximum number of delimiters

	// ---- Lexer States ----------------------------------------------------------------------

	public static final int LEX_STATE_UNKNOWN                  = 0;   // Unknown lexeme type

	public static final int LEX_STATE_START                    = 1;   // Start state

	public static final int LEX_STATE_INT                      = 2;   // Integer
	public static final int LEX_STATE_FLOAT                    = 3;   // Float

	public static final int LEX_STATE_IDENT                    = 4;   // Identifier

	public static final int LEX_STATE_OP                       = 5;   // Operator
	public static final int LEX_STATE_DELIM                    = 6;   // Delimiter

	public static final int LEX_STATE_STRING                   = 7;   // String value
	public static final int LEX_STATE_STRING_ESCAPE            = 8;   // Escape sequence
	public static final int LEX_STATE_STRING_CLOSE_QUOTE       = 9;   // String closing quote

	// ---- Token Types -----------------------------------------------------------------------

	public static final int TOKEN_TYPE_END_OF_STREAM           = 0;   // End of the token stream
	public static final int TOKEN_TYPE_INVALID                 = 1;   // Invalid token

	public static final int TOKEN_TYPE_INT                     = 2;   // Integer
	public static final int TOKEN_TYPE_FLOAT                   = 3;   // Float

	public static final int TOKEN_TYPE_IDENT                   = 4;   // Identifier

	public static final int TOKEN_TYPE_RSRVD_VAR               = 5;   // var/var []
	public static final int TOKEN_TYPE_RSRVD_TRUE              = 6;   // true
	public static final int TOKEN_TYPE_RSRVD_FALSE             = 7;   // false
	public static final int TOKEN_TYPE_RSRVD_IF                = 8;   // if
	public static final int TOKEN_TYPE_RSRVD_ELSE              = 9;   // else
	public static final int TOKEN_TYPE_RSRVD_BREAK             = 10;  // break
	public static final int TOKEN_TYPE_RSRVD_CONTINUE          = 11;  // continue
	public static final int TOKEN_TYPE_RSRVD_FOR               = 12;  // for
	public static final int TOKEN_TYPE_RSRVD_WHILE             = 13;  // while
	public static final int TOKEN_TYPE_RSRVD_FUNC              = 14;  // func
	public static final int TOKEN_TYPE_RSRVD_RETURN            = 15;  // return
	public static final int TOKEN_TYPE_RSRVD_HOST              = 16;  // host

	public static final int TOKEN_TYPE_OP                      = 18;  // Operator

	public static final int TOKEN_TYPE_DELIM_COMMA             = 19;  // ,
	public static final int TOKEN_TYPE_DELIM_OPEN_PAREN        = 20;  // (
	public static final int TOKEN_TYPE_DELIM_CLOSE_PAREN       = 21;  // )
	public static final int TOKEN_TYPE_DELIM_OPEN_BRACE        = 22;  // [
	public static final int TOKEN_TYPE_DELIM_CLOSE_BRACE       = 23;  // ]
	public static final int TOKEN_TYPE_DELIM_OPEN_CURLY_BRACE  = 24;  // {
	public static final int TOKEN_TYPE_DELIM_CLOSE_CURLY_BRACE = 25;  // }
	public static final int TOKEN_TYPE_DELIM_SEMICOLON         = 26;  // ;

	public static final int TOKEN_TYPE_STRING                  = 27;  // String

	// ---- Operators -------------------------------------------------------------------------

	// ---- Arithmetic

	public static final int OP_TYPE_ADD                        = 0;   // +
	public static final int OP_TYPE_SUB                        = 1;   // -
	public static final int OP_TYPE_MUL                        = 2;   // *
	public static final int OP_TYPE_DIV                        = 3;   // /
	public static final int OP_TYPE_MOD                        = 4;   // %
	public static final int OP_TYPE_EXP                        = 5;   // ^
	public static final int OP_TYPE_CONCAT                     = 35;  // $

	public static final int OP_TYPE_INC                        = 15;  // ++
	public static final int OP_TYPE_DEC                        = 17;  // --

	public static final int OP_TYPE_ASSIGN_ADD                 = 14;  // +=
	public static final int OP_TYPE_ASSIGN_SUB                 = 16;  // -=
	public static final int OP_TYPE_ASSIGN_MUL                 = 18;  // *=
	public static final int OP_TYPE_ASSIGN_DIV                 = 19;  // /=
	public static final int OP_TYPE_ASSIGN_MOD                 = 20;  // %=
	public static final int OP_TYPE_ASSIGN_EXP                 = 21;  // ^=
	public static final int OP_TYPE_ASSIGN_CONCAT              = 36;  // $=

	// ---- Bitwise

	public static final int OP_TYPE_BITWISE_AND                = 6;   // &
	public static final int OP_TYPE_BITWISE_OR                 = 7;   // |
	public static final int OP_TYPE_BITWISE_XOR                = 8;   // #
	public static final int OP_TYPE_BITWISE_NOT                = 9;   // ~
	public static final int OP_TYPE_BITWISE_SHIFT_LEFT         = 30;  // <<
	public static final int OP_TYPE_BITWISE_SHIFT_RIGHT        = 32;  // >>

	public static final int OP_TYPE_ASSIGN_AND                 = 22;  // &=
	public static final int OP_TYPE_ASSIGN_OR                  = 24;  // |=
	public static final int OP_TYPE_ASSIGN_XOR                 = 26;  // #=
	public static final int OP_TYPE_ASSIGN_SHIFT_LEFT          = 33;  // <<=
	public static final int OP_TYPE_ASSIGN_SHIFT_RIGHT         = 34;  // >>=

	// ---- Logical

	public static final int OP_TYPE_LOGICAL_AND                = 23;  // &&
	public static final int OP_TYPE_LOGICAL_OR                 = 25;  // ||
	public static final int OP_TYPE_LOGICAL_NOT                = 10;  // !

	// ---- Relational

	public static final int OP_TYPE_EQUAL                      = 28;  // ==
	public static final int OP_TYPE_NOT_EQUAL                  = 27;  // !=
	public static final int OP_TYPE_LESS                       = 12;  // <
	public static final int OP_TYPE_GREATER                    = 13;  // >
	public static final int OP_TYPE_LESS_EQUAL                 = 29;  // <=
	public static final int OP_TYPE_GREATER_EQUAL              = 31;  // >=

	// ---- Assignment

	public static final int OP_TYPE_ASSIGN                     = 11;  // =

	// ---- Data Structures -----------------------------------------------------------------------

	public static int       Token;                                    // Token type

	public class LexerState {
		public int    iCurrLineIndex;               // Current line index
		public Link   pCurrLine;                    // Current line node pointer
		public int    CurrToken;                    // Current token
		public String pstrCurrLexeme = new String(); // Current lexeme
		public int    iCurrLexemeStart;             // Current lexeme's starting index
		public int    iCurrLexemeEnd;               // Current lexeme's ending index
		public int    iCurrOp;                      // Current operator
	}

	public class OpState // Operator state
	{

		public char cChar;         // State character
		public int  iSubStateIndex; // Index into sub state array where sub
		public int  iSubStateCount; // Number of substates
		public int  iIndex;        // Operator index

		public OpState(char c, int subIndex, int subCount, int index) {
			cChar = c;
			iSubStateIndex = subIndex;
			iSubStateCount = subCount;
			iIndex = index;
		}
	}

	// ---- Include Files -------------------------------------------------------------------------

	// #include "lexer.h"

	// ---- Globals -------------------------------------------------------------------------------

	// ---- Lexer -----------------------------------------------------------------------------

	public LexerState g_CurrLexerState = new LexerState();                                                        // The
																													// current
																													// lexer
																													// state
	public LexerState g_PrevLexerState = new LexerState();                                                        // The
																													// previous
																													// lexer
																													// state
																													// (used
																													// for
	// rewinding the token stream)
	// ---- Operators -------------------------------------------------------------------------

	// ---- First operator characters

	public OpState[]  g_OpChars0       = new OpState[] { new OpState('+', 0, 2, 0), new OpState('-', 2, 2, 1), new OpState('*', 4, 1, 2),
	        new OpState('/', 5, 1, 3), new OpState('%', 6, 1, 4), new OpState('^', 7, 1, 5), new OpState('&', 8, 2, 6), new OpState('|', 10, 2, 7),
	        new OpState('#', 12, 1, 8), new OpState('~', 0, 0, 9), new OpState('!', 13, 1, 10), new OpState('=', 14, 1, 11),
	        new OpState('<', 15, 2, 12), new OpState('>', 17, 2, 13), new OpState('$', 19, 1, 35) };

	// ---- Second operator characters

	public OpState[]  g_OpChars1       = new OpState[] { new OpState('=', 0, 0, 14), new OpState('+', 0, 0, 15), // +=,
																													// ++
	        new OpState('=', 0, 0, 16), new OpState('-', 0, 0, 17), // -=, --
	        new OpState('=', 0, 0, 18), // *=
	        new OpState('=', 0, 0, 19), // /=
	        new OpState('=', 0, 0, 20), // %=
	        new OpState('=', 0, 0, 21), // ^=
	        new OpState('=', 0, 0, 22), new OpState('&', 0, 0, 23), // &=, &&
	        new OpState('=', 0, 0, 24), new OpState('|', 0, 0, 25), // |=, ||
	        new OpState('=', 0, 0, 26), // #=
	        new OpState('=', 0, 0, 27), // !=
	        new OpState('=', 0, 0, 28), // ==
	        new OpState('=', 0, 0, 29), new OpState('<', 0, 1, 30), // <=, <<
	        new OpState('=', 0, 0, 31), new OpState('>', 1, 1, 32), // >=, >>
	        new OpState('=', 0, 0, 36) };                                                                         // $=

	// ---- Third operator characters

	public OpState[]  g_OpChars2       = new OpState[] { new OpState('=', 0, 0, 33), new OpState('=', 0, 0, 34) }; // <<=,
																													// >>=

	// ---- Delimiters ------------------------------------------------------------------------

	public char[]     cDelims          = new char[] { ',', '(', ')', '[', ']', '{', '}', ';' };

	// ---- Functions -----------------------------------------------------------------------------

	/*******************************************************************************************************************
	 * 
	 * ResetLexer ()
	 * 
	 * Resets the lexer.
	 */

	public void ResetLexer() {
		// Set the current line of code to the new line
		g_CurrLexerState = new LexerState();
		g_CurrLexerState.iCurrLineIndex = 0;
		g_CurrLexerState.pCurrLine = g_SourceCode;
		// g_CurrLexerState.pCurrLine = g_SourceCode.pHead;

		// Reset the start and end of the current lexeme to the beginning of the source

		g_CurrLexerState.iCurrLexemeStart = 0;
		g_CurrLexerState.iCurrLexemeEnd = 0;

		// Reset the current operator

		g_CurrLexerState.iCurrOp = 0;
	}

	/*******************************************************************************************************************
	 * 
	 * CopyLexerState ()
	 * 
	 * Copies one lexer state structure into another.
	 */

	public void CopyLexerState(LexerState pDestState, LexerState pSourceState) {
		// Copy each field individually to ensure a safe copy

		pDestState.iCurrLineIndex = pSourceState.iCurrLineIndex;
		// pDestState.pCurrLine = pSourceState.pCurrLine;
		pDestState.CurrToken = pSourceState.CurrToken;

		pDestState.pstrCurrLexeme = pSourceState.pstrCurrLexeme;
		pDestState.iCurrLexemeStart = pSourceState.iCurrLexemeStart;
		pDestState.iCurrLexemeEnd = pSourceState.iCurrLexemeEnd;
		pDestState.iCurrOp = pSourceState.iCurrOp;
	}

	/*******************************************************************************************************************
	 * 
	 * GetOpStateIndex ()
	 * 
	 * Returns the index of the operator state associated with the specified character and character index.
	 */

	public int GetOpStateIndex(char cChar, int iCharIndex, int iSubStateIndex, int iSubStateCount) {
		int iStartStateIndex;
		int iEndStateIndex;

		// Is the character index is zero?

		if (iCharIndex == 0) {
			// Yes, so there's no substates to worry about

			iStartStateIndex = 0;
			iEndStateIndex = MAX_OP_STATE_COUNT;
		} else {
			// No, so save the substate information

			iStartStateIndex = iSubStateIndex;
			iEndStateIndex = iStartStateIndex + iSubStateCount;
		}

		// Loop through each possible substate and look for a match

		for (int iCurrOpStateIndex = iStartStateIndex; iCurrOpStateIndex < iEndStateIndex; ++iCurrOpStateIndex) {
			// Get the current state at the specified character index

			char cOpChar = '\0';
			switch (iCharIndex) {
				case 0:
					cOpChar = g_OpChars0[iCurrOpStateIndex].cChar;
					break;

				case 1:
					cOpChar = g_OpChars1[iCurrOpStateIndex].cChar;
					break;

				case 2:
					cOpChar = g_OpChars2[iCurrOpStateIndex].cChar;
					break;
			}

			// If the character is a match, return the index

			if (cChar == cOpChar)
				return iCurrOpStateIndex;
		}

		// Return -1 if no match is found

		return -1;
	}

	/*******************************************************************************************************************
	 * 
	 * IsCharOpChar ()
	 * 
	 * Determines if the specified character is an operator character.
	 */

	public int IsCharOpChar(char cChar, int iCharIndex) {
		// Loop through each state in the specified character index and look for a match

		for (int iCurrOpStateIndex = 0; iCurrOpStateIndex < MAX_OP_STATE_COUNT; ++iCurrOpStateIndex) {
			// Get the current state at the specified character index

			char cOpChar = '\0';
			switch (iCharIndex) {
				case 0:
					if (iCurrOpStateIndex < g_OpChars0.length)
						cOpChar = g_OpChars0[iCurrOpStateIndex].cChar;
					break;

				case 1:
					if (iCurrOpStateIndex < g_OpChars1.length)
						cOpChar = g_OpChars1[iCurrOpStateIndex].cChar;
					break;

				case 2:
					if (iCurrOpStateIndex < g_OpChars2.length)
						cOpChar = g_OpChars2[iCurrOpStateIndex].cChar;
					break;
			}

			// If the character is a match, return TRUE

			if (cChar == cOpChar)
				return TRUE;
		}

		// Return FALSE if no match is found

		return FALSE;
	}

	/*******************************************************************************************************************
	 * 
	 * GetOpState ()
	 * 
	 * Returns the operator state associated with the specified index and state.
	 */

	public OpState GetOpState(int iCharIndex, int iStateIndex) {
		OpState State = null;

		// Save the specified state at the specified character index

		switch (iCharIndex) {
			case 0:
				State = g_OpChars0[iStateIndex];
				break;

			case 1:
				State = g_OpChars1[iStateIndex];
				break;

			case 2:
				State = g_OpChars2[iStateIndex];
				break;
		}

		return State;
	}

	/*******************************************************************************************************************
	 * 
	 * IsCharDelim ()
	 * 
	 * Determines whether a character is a delimiter.
	 */

	public int IsCharDelim(char cChar) {
		// Loop through each delimiter in the array and compare it to the specified character

		for (int iCurrDelimIndex = 0; iCurrDelimIndex < cDelims.length; ++iCurrDelimIndex) {
			// Return TRUE if a match was found

			if (cChar == cDelims[iCurrDelimIndex])
				return TRUE;
		}

		// The character is not a delimiter, so return FALSE

		return FALSE;
	}

	/*******************************************************************************************************************
	 * 
	 * IsCharWhitespace ()
	 * 
	 * Returns a nonzero if the given character is whitespace, or zero otherwise.
	 */

	public int IsCharWhitespace(char cChar) {
		// Return true if the character is a space or tab.

		if (cChar == ' ' || cChar == '\t' || cChar == '\n')
			return TRUE;
		else
			return FALSE;
	}

	/*******************************************************************************************************************
	 * 
	 * IsCharNumeric ()
	 * 
	 * Returns a nonzero if the given character is numeric, or zero otherwise.
	 */

	public int IsCharNumeric(char cChar) {
		// Return true if the character is between 0 and 9 inclusive.

		if (cChar >= '0' && cChar <= '9')
			return TRUE;
		else
			return FALSE;
	}

	/*******************************************************************************************************************
	 * 
	 * IsCharIdent ()
	 * 
	 * Returns a nonzero if the given character is part of a valid identifier, meaning it's an alphanumeric or
	 * underscore. Zero is returned otherwise.
	 */

	public int IsCharIdent(char cChar) {
		// Return true if the character is between 0 or 9 inclusive or is an uppercase or
		// lowercase letter or underscore

		if ((cChar >= '0' && cChar <= '9') || (cChar >= 'A' && cChar <= 'Z') || (cChar >= 'a' && cChar <= 'z') || cChar == '_')
			return TRUE;
		else
			return FALSE;
	}

	/*******************************************************************************************************************
	 * 
	 * GetNextChar ()
	 * 
	 * Returns the next character in the source buffer.
	 */

	public char GetNextChar() {
		// Make a local copy of the string pointer, unless we're at the end of the source code

		String pstrCurrLine;
		if (g_CurrLexerState.pCurrLine != null)
			pstrCurrLine = (String) (g_CurrLexerState.pCurrLine.getNode(g_CurrLexerState.iCurrLineIndex));
		else
			return '\0';
		// System.out.println("Lexer.GetNextChar :"+pstrCurrLine);
		// if ( g_CurrLexerState.pCurrLine)
		// pstrCurrLine = ( char * ) g_CurrLexerState.pCurrLine->pData;
		// else
		// return '\0';

		// If the current lexeme end index is beyond the length of the string, we're past the
		// end of the line

		if (g_CurrLexerState.iCurrLexemeEnd >= pstrCurrLine.length()) {
			// Move to the next node in the source code list

			// g_CurrLexerState.pCurrLine = g_CurrLexerState.pCurrLine->pNext;

			// Is the line valid?
			g_CurrLexerState.iCurrLineIndex++;
			if (g_CurrLexerState.iCurrLineIndex < g_CurrLexerState.pCurrLine.getSize()) {
				// Yes, so move to the next line of code and reset the lexeme pointers

				pstrCurrLine = (String) g_CurrLexerState.pCurrLine.getNode(g_CurrLexerState.iCurrLineIndex);

				// ++ g_CurrLexerState.iCurrLineIndex;
				g_CurrLexerState.iCurrLexemeStart = 0;
				g_CurrLexerState.iCurrLexemeEnd = 0;
			} else {
				// No, so return a null terminator to alert the lexer that the end of the
				// source code has been reached
				--g_CurrLexerState.iCurrLineIndex;
				return '\0';
			}

		}
		// int index = g_CurrLexerState.iCurrLexemeEnd;
		// g_CurrLexerState.iCurrLexemeEnd ++;
		// Return the character and increment the pointer
		// System.out.println("Lexer.GetNextChar 1:"+g_CurrLexerState.iCurrLineIndex +"
		// 2:"+g_CurrLexerState.pCurrLine.getSize() );
		// System.out.println("Lexer.iCurrLexemeEnd 1."+pstrCurrLine.length()+" 2."+g_CurrLexerState.iCurrLexemeEnd);
		if (pstrCurrLine.length() == 0)
			return '\0';
		return pstrCurrLine.charAt(g_CurrLexerState.iCurrLexemeEnd++);
	}

	/*******************************************************************************************************************
	 * 
	 * GetNextToken ()
	 * 
	 * Returns the next token in the source buffer.
	 */

	public int GetNextToken() {
		// Save the current lexer state for future rewinding

		CopyLexerState(g_PrevLexerState, g_CurrLexerState);

		// Start the new lexeme at the end of the last one

		g_CurrLexerState.iCurrLexemeStart = g_CurrLexerState.iCurrLexemeEnd;

		// Set the initial state to the start state

		int iCurrLexState = LEX_STATE_START;

		// Set the current operator state

		int iCurrOpCharIndex = 0;
		int iCurrOpStateIndex = 0;
		OpState CurrOpState = null;

		// Flag to determine when the lexeme is done

		int iLexemeDone = FALSE;

		// ---- Loop until a token is completed

		// Current character

		char cCurrChar;

		// Current position in the lexeme string buffer

		int iNextLexemeCharIndex = 0;
		g_CurrLexerState.pstrCurrLexeme = "";
		// Should the current character be included in the lexeme?

		int iAddCurrChar;

		// Begin the loop

		while (true) {
			// Read the next character, and exit if the end of the source has been reached

			cCurrChar = GetNextChar();
			// System.out.println("Lexer.GetNextToken char:"+cCurrChar);
			if (cCurrChar == '\0')
				break;

			// Assume the character will be added to the lexeme

			iAddCurrChar = TRUE;

			// Depending on the current state of the lexer, handle the incoming character

			switch (iCurrLexState) {
				// If an unknown state occurs, the token is invalid, so exit

				case LEX_STATE_UNKNOWN:

					iLexemeDone = TRUE;

					break;

				// The start state

				case LEX_STATE_START:

					// Just loop past whitespace, and don't add it to the lexeme

					if (IsCharWhitespace(cCurrChar) != FALSE) {
						++g_CurrLexerState.iCurrLexemeStart;
						iAddCurrChar = FALSE;
					}

					// An integer is starting

					else if (IsCharNumeric(cCurrChar) != FALSE) {
						iCurrLexState = LEX_STATE_INT;
					}

					// A float is starting

					else if (cCurrChar == '.') {
						iCurrLexState = LEX_STATE_FLOAT;
					}

					// An identifier is starting

					else if (IsCharIdent(cCurrChar) != FALSE) {
						iCurrLexState = LEX_STATE_IDENT;
					}

					// A delimiter has been read

					else if (IsCharDelim(cCurrChar) != FALSE) {
						iCurrLexState = LEX_STATE_DELIM;
					}

					// An operator is starting

					else if (IsCharOpChar(cCurrChar, 0) != FALSE) {
						// Get the index of the initial operand state

						iCurrOpStateIndex = GetOpStateIndex(cCurrChar, 0, 0, 0);
						if (iCurrOpStateIndex == -1)
							return TOKEN_TYPE_INVALID;

						// Get the full state structure

						CurrOpState = GetOpState(0, iCurrOpStateIndex);

						// Move to the next character in the operator (1)

						iCurrOpCharIndex = 1;

						// Set the current operator

						g_CurrLexerState.iCurrOp = CurrOpState.iIndex;

						iCurrLexState = LEX_STATE_OP;
					}

					// A string is starting, but don't add the opening quote to the lexeme

					else if (cCurrChar == '"') {
						iAddCurrChar = FALSE;
						iCurrLexState = LEX_STATE_STRING;
					}

					// It's invalid

					else
						iCurrLexState = LEX_STATE_UNKNOWN;

					break;

				// Integer

				case LEX_STATE_INT:

					// If a numeric is read, keep the state as-is

					if (IsCharNumeric(cCurrChar) != FALSE) {
						iCurrLexState = LEX_STATE_INT;
					}

					// If a radix point is read, the numeric is really a float

					else if (cCurrChar == '.') {
						iCurrLexState = LEX_STATE_FLOAT;
					}

					// If whitespace or a delimiter is read, the lexeme is done

					else if (IsCharWhitespace(cCurrChar) != FALSE || IsCharDelim(cCurrChar) != FALSE) {
						iAddCurrChar = FALSE;
						iLexemeDone = TRUE;
					}

					// Anything else is invalid

					else
						iCurrLexState = LEX_STATE_UNKNOWN;

					break;

				// Floating-point

				case LEX_STATE_FLOAT:

					// If a numeric is read, keep the state as-is

					if (IsCharNumeric(cCurrChar) != FALSE) {
						iCurrLexState = LEX_STATE_FLOAT;
					}

					// If whitespace or a delimiter is read, the lexeme is done

					else if (IsCharWhitespace(cCurrChar) != FALSE || IsCharDelim(cCurrChar) != FALSE) {
						iLexemeDone = TRUE;
						iAddCurrChar = FALSE;
					}

					// Anything else is invalid

					else
						iCurrLexState = LEX_STATE_UNKNOWN;

					break;

				// Identifier

				case LEX_STATE_IDENT:

					// If an identifier character is read, keep the state as-is

					if (IsCharIdent(cCurrChar) != FALSE) {
						iCurrLexState = LEX_STATE_IDENT;
					}

					// If whitespace or a delimiter is read, the lexeme is done

					else if (IsCharWhitespace(cCurrChar) != FALSE || IsCharDelim(cCurrChar) != FALSE) {
						iAddCurrChar = FALSE;
						iLexemeDone = TRUE;
					}

					// Anything else is invalid

					else
						iCurrLexState = LEX_STATE_UNKNOWN;

					break;

				// Operator

				case LEX_STATE_OP:

					// If the current character within the operator has no substates, we're done

					if (CurrOpState.iSubStateCount == 0) {
						iAddCurrChar = FALSE;
						iLexemeDone = TRUE;
						break;
					}

					// Otherwise, find out if the new character is a possible substate

					if (IsCharOpChar(cCurrChar, iCurrOpCharIndex) != FALSE) {
						// Get the index of the next substate

						iCurrOpStateIndex = GetOpStateIndex(cCurrChar, iCurrOpCharIndex, CurrOpState.iSubStateIndex, CurrOpState.iSubStateCount);
						if (iCurrOpStateIndex == -1) {
							iCurrLexState = LEX_STATE_UNKNOWN;
						} else {
							// Get the next operator structure

							CurrOpState = GetOpState(iCurrOpCharIndex, iCurrOpStateIndex);

							// Move to the next character in the operator

							++iCurrOpCharIndex;

							// Set the current operator

							g_CurrLexerState.iCurrOp = CurrOpState.iIndex;
						}
					}

					// If not, the lexeme is done

					else {
						iAddCurrChar = FALSE;
						iLexemeDone = TRUE;
					}

					break;

				// Delimiter

				case LEX_STATE_DELIM:

					// Don't add whatever comes after the delimiter to the lexeme, because
					// it's done

					iAddCurrChar = FALSE;
					iLexemeDone = TRUE;

					break;

				// String

				case LEX_STATE_STRING:

					// If the current character is a closing quote, finish the lexeme

					if (cCurrChar == '"') {
						iAddCurrChar = FALSE;
						iCurrLexState = LEX_STATE_STRING_CLOSE_QUOTE;
					}

					// If it's a newline, the string token is invalid

					else if (cCurrChar == '\n') {
						iAddCurrChar = FALSE;
						iCurrLexState = LEX_STATE_UNKNOWN;
					}

					// If it's an escape sequence, switch to the escape state and don't add the
					// backslash to the lexeme

					else if (cCurrChar == '\\') {
						iAddCurrChar = FALSE;
						iCurrLexState = LEX_STATE_STRING_ESCAPE;
					}

					// Anything else gets added to the string

					break;

				// Escape sequence

				case LEX_STATE_STRING_ESCAPE:

					// Immediately switch back to the string state, now that the character's
					// been added

					iCurrLexState = LEX_STATE_STRING;

					break;

				// String closing quote

				case LEX_STATE_STRING_CLOSE_QUOTE:

					// Finish the string lexeme

					iAddCurrChar = FALSE;
					iLexemeDone = TRUE;

					break;
			}

			// Add the next character to the lexeme and increment the index

			if (iAddCurrChar != FALSE) {
				g_CurrLexerState.pstrCurrLexeme += cCurrChar;
				++iNextLexemeCharIndex;
			}

			// If the lexeme is complete, exit the loop

			if (iLexemeDone != FALSE)
				break;
		}

		// Complete the lexeme string

		// g_CurrLexerState.pstrCurrLexeme [ iNextLexemeCharIndex ] = '\0';

		// Retract the lexeme end index by one

		--g_CurrLexerState.iCurrLexemeEnd;

		// Determine the token type

		int TokenType = 0;
		switch (iCurrLexState) {
			// Unknown

			case LEX_STATE_UNKNOWN:
				TokenType = TOKEN_TYPE_INVALID;
				break;

			// Integer

			case LEX_STATE_INT:
				TokenType = TOKEN_TYPE_INT;
				break;

			// Float

			case LEX_STATE_FLOAT:
				TokenType = TOKEN_TYPE_FLOAT;
				break;

			// Identifier/Reserved Word

			case LEX_STATE_IDENT:

				// Set the token type to identifier in case none of the reserved words match

				TokenType = TOKEN_TYPE_IDENT;

				// ---- Determine if the "identifier" is actually a reserved word

				// var/var []

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "var"))
					TokenType = TOKEN_TYPE_RSRVD_VAR;

				// true

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "true"))
					TokenType = TOKEN_TYPE_RSRVD_TRUE;

				// false

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "false"))
					TokenType = TOKEN_TYPE_RSRVD_FALSE;

				// if

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "if"))
					TokenType = TOKEN_TYPE_RSRVD_IF;

				// else

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "else"))
					TokenType = TOKEN_TYPE_RSRVD_ELSE;

				// break

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "break"))
					TokenType = TOKEN_TYPE_RSRVD_BREAK;

				// continue

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "continue"))
					TokenType = TOKEN_TYPE_RSRVD_CONTINUE;

				// for

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "for"))
					TokenType = TOKEN_TYPE_RSRVD_FOR;

				// while

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "while"))
					TokenType = TOKEN_TYPE_RSRVD_WHILE;

				// func

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "func"))
					TokenType = TOKEN_TYPE_RSRVD_FUNC;

				// return

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "return"))
					TokenType = TOKEN_TYPE_RSRVD_RETURN;

				// host

				if (Tool.stricmp(g_CurrLexerState.pstrCurrLexeme, "host"))
					TokenType = TOKEN_TYPE_RSRVD_HOST;
				System.out.println("Lexer.GetNextToken pstrCurrLexeme:" + g_CurrLexerState.pstrCurrLexeme);
				break;

			// Delimiter

			case LEX_STATE_DELIM:

				// Determine which delimiter was found

				switch (g_CurrLexerState.pstrCurrLexeme.charAt(0)) {
					case ',':
						TokenType = TOKEN_TYPE_DELIM_COMMA;
						break;

					case '(':
						TokenType = TOKEN_TYPE_DELIM_OPEN_PAREN;
						break;

					case ')':
						TokenType = TOKEN_TYPE_DELIM_CLOSE_PAREN;
						break;

					case '[':
						TokenType = TOKEN_TYPE_DELIM_OPEN_BRACE;
						break;

					case ']':
						TokenType = TOKEN_TYPE_DELIM_CLOSE_BRACE;
						break;

					case '{':
						TokenType = TOKEN_TYPE_DELIM_OPEN_CURLY_BRACE;
						break;

					case '}':
						TokenType = TOKEN_TYPE_DELIM_CLOSE_CURLY_BRACE;
						break;

					case ';':
						TokenType = TOKEN_TYPE_DELIM_SEMICOLON;
						break;
				}

				break;

			// Operators

			case LEX_STATE_OP:
				TokenType = TOKEN_TYPE_OP;
				break;

			// Strings

			case LEX_STATE_STRING_CLOSE_QUOTE:
				TokenType = TOKEN_TYPE_STRING;
				break;

			// All that's left is whitespace, which means the end of the stream

			default:
				TokenType = TOKEN_TYPE_END_OF_STREAM;
		}

		// Return the token type and set the global copy

		g_CurrLexerState.CurrToken = TokenType;
		return TokenType;
	}

	/*******************************************************************************************************************
	 * 
	 * RewindTokenStream ()
	 * 
	 * Moves the lexer back one token.
	 */

	public void RewindTokenStream() {
		CopyLexerState(g_CurrLexerState, g_PrevLexerState);
	}

	/*******************************************************************************************************************
	 * 
	 * GetCurrToken ()
	 * 
	 * Returns the current token.
	 */

	public int GetCurrToken() {
		return g_CurrLexerState.CurrToken;
	}

	/*******************************************************************************************************************
	 * 
	 * GetCurrLexeme ()
	 * 
	 * Returns a pointer to the current lexeme.
	 */

	public String GetCurrLexeme() {
		return g_CurrLexerState.pstrCurrLexeme;
	}

	/*******************************************************************************************************************
	 * 
	 * CopyCurrLexeme ()
	 * 
	 * Makes a physical copy of the current lexeme into the specified string buffer.
	 */

	public String CopyCurrLexeme() {
		return g_CurrLexerState.pstrCurrLexeme;
	}

	/*******************************************************************************************************************
	 * 
	 * GetCurrOp ()
	 * 
	 * Returns the current operator.
	 */

	int GetCurrOp() {
		return g_CurrLexerState.iCurrOp;
	}

	/*******************************************************************************************************************
	 * 
	 * GetLookAheadChar ()
	 * 
	 * Returns the first character of the next token.
	 */

	public char GetLookAheadChar() {
		// Save the current lexer state

		LexerState PrevLexerState = new LexerState();
		CopyLexerState(PrevLexerState, g_CurrLexerState);

		// Skip any whitespace that may exist and return the first non-whitespace character

		char cCurrChar;
		while (true) {
			cCurrChar = GetNextChar();
			if (IsCharWhitespace(cCurrChar) == FALSE)
				break;
		}

		// Restore the lexer state

		CopyLexerState(g_CurrLexerState, PrevLexerState);

		// Return the look-ahead character

		return cCurrChar;
	}

	/*******************************************************************************************************************
	 * 
	 * GetCurrSourceLine ()
	 * 
	 * Returns a pointer to the current source line string.
	 */

	public String GetCurrSourceLine() {
		if (g_CurrLexerState.pCurrLine != null)
			return (String) (g_CurrLexerState.pCurrLine.getNode(g_CurrLexerState.iCurrLineIndex));
		else
			return null;

	}

	/*******************************************************************************************************************
	 * 
	 * GetCurrSourceLineIndex ()
	 * 
	 * Returns the current source line number.
	 */

	int GetCurrSourceLineIndex() {
		return g_CurrLexerState.iCurrLineIndex;
	}

	/*******************************************************************************************************************
	 * 
	 * GetLexemeStartIndex ()
	 * 
	 * Returns the pointer to the start of the current lexeme
	 */

	int GetLexemeStartIndex() {
		return g_CurrLexerState.iCurrLexemeStart;
	}

}
