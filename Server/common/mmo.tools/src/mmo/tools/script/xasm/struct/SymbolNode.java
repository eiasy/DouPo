package mmo.tools.script.xasm.struct;

/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 16:11:28
 */
public class SymbolNode
{
    public int iIndex;                                    // Index
    public String pstrIdent;// = new char[ConstData.MAX_IDENT_SIZE];          // Identifier
    public int iSize;                                  // Size (1 for variables, N for arrays)
    public int iStackIndex;                            // The stack index to which the symbol
    // points
    public int iFuncIndex;                             // Function in which the symbol resides
}
