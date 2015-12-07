package mmo.tools.script.msc.Struct;

/**
 * Author: cuirongzhou
 * Date: 2007-11-29
 * Time: 14:54:34
 */
public class SymbolNode
{
    	public int iIndex;									    // Index
        public String pstrIdent;             // Identifier
        public int iSize;                                      // Size (1 for variables, N for arrays)
        public int iScope;                                     // Scope (0 for globals, N for locals'
                                                        // function index)
        public int iType;                                      // Symbol type (parameter or variable)
}
