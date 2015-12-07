package mmo.tools.script.xasm.struct;

/**
 * Author: cuirongzhou
 * Date: 2007-10-30
 * Time: 10:30:50
 */
public class LabelNode
{
    public int iIndex;                                    // Index
    public String pstrIdent;// = new char[ConstData.MAX_IDENT_SIZE];          // Identifier
    public int iTargetIndex;                           // Index of the target instruction
    public int iFuncIndex;                             // Function in which the label resides
}
