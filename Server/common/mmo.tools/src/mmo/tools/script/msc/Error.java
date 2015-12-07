package mmo.tools.script.msc;

/**
 * Author: cuirongzhou Date: 2007-11-28 Time: 14:44:41
 */
public class Error extends Const {

	// ---- Include Files -------------------------------------------------------------------------

	// #include "error.h"
	// #include "lexer.h"
	private static Lexer lexer;

	public Error(Lexer l) {
		lexer = l;
	}

	// ---- Functions -----------------------------------------------------------------------------

	/******************************************************************************************
	 * 
	 * ExitOnError ()
	 * 
	 * Prints a general error message and exits.
	 */

	public static void ExitOnError(String pstrErrorMssg) {
		// Print the message

		System.out.println("Fatal Error: " + pstrErrorMssg);

		// Exit the program

		Msc.Exit();
	}

	/******************************************************************************************
	 * 
	 * ExitOnCodeError ()
	 * 
	 * Prints an code-related error message and exits.
	 */

	public static void ExitOnCodeError(String pstrErrorMssg) {
		// Print the message

		System.out.println("Error: " + pstrErrorMssg);
		System.out.println("Line " + lexer.GetCurrSourceLineIndex());

		// Reduce all of the source line's spaces to tabs so it takes less space and so the
		// karet lines up with the current token properly

		String pstrSourceLine = null;// [ MAX_SOURCE_LINE_SIZE ];

		// If the current line is a valid string, copy it into the local source line buffer

		String pstrCurrSourceLine = lexer.GetCurrSourceLine();
		if (pstrCurrSourceLine != null)
			pstrSourceLine = pstrCurrSourceLine;

		// If the last character of the line is a line break, clip it

		int iLastCharIndex = pstrSourceLine.length() - 1;
		if (iLastCharIndex > 0 && pstrSourceLine.charAt(iLastCharIndex) == '\n')
			pstrSourceLine = pstrSourceLine.substring(0, iLastCharIndex - 1);

		// Loop through each character and replace tabs with spaces
		pstrSourceLine = pstrSourceLine.replaceAll("\t", " ");

		// Print the offending source line

		System.out.println(pstrSourceLine);

		// Print a karet at the start of the (presumably) offending lexeme

		for (int iCurrSpace = 0; iCurrSpace < lexer.GetLexemeStartIndex(); ++iCurrSpace)
			System.out.print(" ");
		System.out.print("^\n");

		// Print message indicating that the script could not be assembled

		System.out.println("Could not compile " + g_pstrSourceFilename);

		// Exit the program

		Msc.Exit();
	}
}
