package mmo.tools.script.xasm.struct;

/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 15:58:39
 */
public class InstrLookup
{
//    typedef      int OpTypes;                            // Operand type bitfield alias type

        public String pstrMnemonic;    // = new char[ConstData.MAX_INSTR_MNEMONIC_SIZE];  // Mnemonic string
        public int iOpcode;                                // Opcode
        public int iOpCount;                               // Number of operands
        public int[] OpList;                           // Pointer to operand list
}
