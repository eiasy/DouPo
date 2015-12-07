package mmo.tools.script.xasm.struct;

/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 16:09:28
 */
public class Instr
{
    public int iOpcode;                                // Opcode
    public int iOpCount;                               // Number of operands
    public Opstruct[] pOpList;                               // Pointer to operand list
}
