package mmo.tools.script.xasm.struct;

/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 15:39:29
 */
public class Lexer
{
// ---- Lexical Analysis ------------------------------------------------------------------

//    typedef int Token;                              // Tokenizer alias type

    public int iCurrSourceLine;                        // Current line in the source

    public int iIndex0;                      // Indices into the string
    public int iIndex1;

    public int CurrToken;                            // Current token
    public String pstrCurrLexeme;// = new char[ConstData.MAX_LEXEME_SIZE];    // Current lexeme

    public int iCurrLexState;                          // The current lex state


}
