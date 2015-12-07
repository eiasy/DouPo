package mmo.tools.script.msc.Struct;

public class ICodeNode {
	public int        iType;           // The node type
	public ICodeInstr Instr;           // The I-code instruction
	public String     pstrSourceLine;  // The source line with which this
	// instruction is annotated
	public int        iJumpTargetIndex; // The jump target index

}
