package mmo.tools.script.msc.Struct;

import java.util.LinkedList;

public class FuncNode {
	public int        iIndex;     // Index
	public String     pstrName;   // Name
	public int        iIsHostAPI; // Is this a host API function?
	public int        iParamCount; // The number of accepted parameters
	public LinkedList ICodeStream; // Local I-code stream
}
