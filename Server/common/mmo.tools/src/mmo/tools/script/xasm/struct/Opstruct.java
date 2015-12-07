package mmo.tools.script.xasm.struct;

/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 16:01:14
 */
public class Opstruct //extends OpUnion
{

//    union                                        // The value
//    {
//        int iIntLiteral;                        // Integer literal
//        float fFloatLiteral;                    // Float literal
//        int iStringTableIndex;                  // String table index
//        int iStackIndex;                        // Stack index
//        int unionValue;                        // Instruction index
//        int iFuncIndex;                         // Function index
//        int iHostAPICallIndex;                  // Host API Call index
//        int iReg;                               // Register code
    //    };
    public int unionValue;
    public float fFloatLiteral;                    // Float literal
    public int iType;                                  // Type
    public int iOffsetIndex;                           // Index of the offset

}
