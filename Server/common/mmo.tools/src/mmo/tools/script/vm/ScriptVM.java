package mmo.tools.script.vm;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

/**
 * Author: cuirongzhou Date: 2007-10-31 Time: 14:14:19
 */
public class ScriptVM {
	public ScriptVM() {
	}

	// public static final String pScriptFile = "TEST_0.XSE";
	// ---- General -------------------------------------------------------------

	public static final byte   TRUE                        = 1;
	public static final byte   FALSE                       = 0;

	// ---- Stack -----------------------------------------------------------------

	public static final int    DEF_STACK_SIZE              = 512;                         // The default stack size

	// ---- Multithreading ------------------------------------------------

	public static final int    THREAD_TIMESLICE_DUR        = 20;                          // Timeslice duration (in
	                                                                                       // milliseconds)
	// ---- The Host API ----------------------------------------------------

	public static final int    XS_GLOBAL_FUNC              = -1;                          // Flags
	// a host API function as being
	// ---- File I/O -----------------------------------------------------------
	public static final String EXEC_FILE_EXT               = ".XSE";                      // Executable file extension

	public static final String XSE_ID_STRING               = "XSE0";                      // Used to validate an .XSE
	                                                                                       // executable

	// ---- XS_LoadScript () Error Codes -----------------------
	public static final byte   LOAD_OK                     = 0;                           // Load
	// successful
	public static final byte   LOAD_ERROR_FILE_IO          = 1;                           // File I/O error (most
	                                                                                       // likely a file not found
	                                                                                       // error
	public static final byte   LOAD_ERROR_INVALID_XSE      = 2;                           // Invalid .XSE structure
	public static final byte   LOAD_ERROR_UNSUPPORTED_VERS = 3;                           // The format version is
	                                                                                       // unsupported
	public static final byte   LOAD_ERROR_OUT_OF_MEMORY    = 4;                           // Out of memory
	public static final byte   LOAD_ERROR_OUT_OF_THREADS   = 5;                           // Out of threads

	// ---- Threading -----------------------------------------------------------

	public static final byte   XS_THREAD_PRIORITY_USER     = 0;                           // User-defined priority
	public static final byte   XS_THREAD_PRIORITY_LOW      = 1;                           // Low priority
	public static final byte   XS_THREAD_PRIORITY_MED      = 2;                           // Medium priority
	public static final byte   XS_THREAD_PRIORITY_HIGH     = 3;                           // High priority

	public static final byte   XS_INFINITE_TIMESLICE       = -1;                          // Allows a thread to run
	                                                                                       // indefinitely

	public static final int    MAX_THREAD_COUNT            = 4;
	// ---- Multithreading --------------------------------------------------------------------

	public static final int    THREAD_MODE_MULTI           = 0;                           // Multithreaded execution
	public static final int    THREAD_MODE_SINGLE          = 1;                           // Single-threaded execution
	public static final int    THREAD_PRIORITY_DUR_LOW     = 20;                          // Low-priority thread
	                                                                                       // timeslice
	public static final int    THREAD_PRIORITY_DUR_MED     = 40;                          // Medium-priority thread
	                                                                                       // timeslice
	public static final int    THREAD_PRIORITY_DUR_HIGH    = 80;                          // High-priority thread
	                                                                                       // timeslice
	// ---- The Host API -----------------------------------------------------
	// public static final int MAX_HOST_API_SIZE = HostAPIFunc.funcName.length; // Maximum number of functions in the
	// ---- Operand Types ---------------------------------------------------

	public static final byte   OP_TYPE_NULL                = -1;                          // Uninitialized/Null data
	public static final byte   OP_TYPE_INT                 = 0;                           // Integer literal value
	public static final byte   OP_TYPE_FLOAT               = 1;                           // Floating-point literal
	                                                                                       // value
	public static final byte   OP_TYPE_STRING              = 2;                           // String literal value
	public static final byte   OP_TYPE_ABS_STACK_INDEX     = 3;                           // Absolute array index
	public static final byte   OP_TYPE_REL_STACK_INDEX     = 4;                           // Relative array index
	public static final byte   OP_TYPE_INSTR_INDEX         = 5;                           // Instruction index
	public static final byte   OP_TYPE_FUNC_INDEX          = 6;                           // Function index
	public static final byte   OP_TYPE_HOST_API_CALL_INDEX = 7;                           // Host API all index
	public static final byte   OP_TYPE_REG                 = 8;                           // Register
	public static final byte   OP_TYPE_STACK_BASE_MARKER   = 9;                           // Marks a stack base
	// ---- Instruction Opcodes ---------------------------------------

	public static final byte   INSTR_MOV                   = 0;

	public static final byte   INSTR_ADD                   = 1;
	public static final byte   INSTR_SUB                   = 2;
	public static final byte   INSTR_MUL                   = 3;
	public static final byte   INSTR_DIV                   = 4;
	public static final byte   INSTR_MOD                   = 5;
	public static final byte   INSTR_EXP                   = 6;
	public static final byte   INSTR_NEG                   = 7;
	public static final byte   INSTR_INC                   = 8;
	public static final byte   INSTR_DEC                   = 9;

	public static final byte   INSTR_AND                   = 10;
	public static final byte   INSTR_OR                    = 11;
	public static final byte   INSTR_XOR                   = 12;
	public static final byte   INSTR_NOT                   = 13;
	public static final byte   INSTR_SHL                   = 14;
	public static final byte   INSTR_SHR                   = 15;

	public static final byte   INSTR_CONCAT                = 16;
	public static final byte   INSTR_GETCHAR               = 17;
	public static final byte   INSTR_SETCHAR               = 18;

	public static final byte   INSTR_JMP                   = 19;
	public static final byte   INSTR_JE                    = 20;
	public static final byte   INSTR_JNE                   = 21;
	public static final byte   INSTR_JG                    = 22;
	public static final byte   INSTR_JL                    = 23;
	public static final byte   INSTR_JGE                   = 24;
	public static final byte   INSTR_JLE                   = 25;

	public static final byte   INSTR_PUSH                  = 26;
	public static final byte   INSTR_POP                   = 27;

	public static final byte   INSTR_CALL                  = 28;
	public static final byte   INSTR_RET                   = 29;
	public static final byte   INSTR_CALLHOST              = 30;

	public static final byte   INSTR_PAUSE                 = 31;
	public static final byte   INSTR_EXIT                  = 32;

	public boolean             shutVM                      = false;

	// ---- Scripts ---------------------------------------------------------------

	public static Script[]     g_Scripts                   = new Script[MAX_THREAD_COUNT]; // The script array

	public int                 g_iCurrThreadMode;                                         // The current threading
	                                                                                       // mode
	public int                 g_iCurrThread;                                             // The currently running
	                                                                                       // thread
	// public long g_iCurrThreadActiveTime; // The time at which the current thread was activated
	public long                iPauseEndTime;                                             // Pause Time
	// ---- The String Table --------------------------

	// ---- The Host API -----------------------------------------------------
	public Vector<HostAPI>     g_HostAPI                   = new Vector<HostAPI>();       // host API

	// ---- Instruction Mnemonics ----------------------------------

	// This array is just used to print out the mnemonics for each instruction as they're executed, using the opcode as
	// an array index Test string
	/**
	 * public final String ppstrMnemonics[] = { "Mov", "Add", "Sub", "Mul", "Div", "Mod", "Exp", "Neg", "Inc", "Dec",
	 * "And", "Or", "XOr", "Not", "ShL", "ShR", "Concat", "GetChar", "SetChar", "Jmp", "JE", "JNE", "JG", "JL", "JGE",
	 * "JLE", "Push", "Pop", "Call", "Ret", "CallHost", "Pause", "Exit" };
	 **/

	// ---- Functions -------------------------------------------------------------

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_Init ()
	 * <p/>
	 * Initializes the runtime environment.
	 */

	public void XS_Init() {
		for (int iCurrScriptIndex = 0; iCurrScriptIndex < MAX_THREAD_COUNT; ++iCurrScriptIndex) {
			g_Scripts[iCurrScriptIndex] = new Script();
			g_Scripts[iCurrScriptIndex].iIsActive = false;

			g_Scripts[iCurrScriptIndex].iIsRunning = false;
			g_Scripts[iCurrScriptIndex].iIsMainFuncPresent = FALSE;
			g_Scripts[iCurrScriptIndex].iIsPaused = false;

			// g_Scripts[iCurrScriptIndex].Stack = new RuntimeStack();
			// g_Scripts[iCurrScriptIndex].HostAPICallTable = new HostAPICallTable();
		}

		// g_StrTable = new String[MAX_THREAD_COUNT][];

		g_iCurrThreadMode = THREAD_MODE_MULTI;
		// g_iCurrThread = 0;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_ShutDown ()
	 * <p/>
	 * Shuts down the runtime environment.
	 */

	public void XS_ShutDown() {
		if (g_Scripts == null) {
			return;
		}
		for (int iCurrScriptIndex = 0; iCurrScriptIndex < MAX_THREAD_COUNT; ++iCurrScriptIndex) {
			if (g_Scripts[iCurrScriptIndex] == null) {
				continue;
			}
			if (!g_Scripts[iCurrScriptIndex].iIsActive)
				continue;
			g_Scripts[iCurrScriptIndex].iIsActive = false;
			g_Scripts[iCurrScriptIndex].instruction = null;
			g_Scripts[iCurrScriptIndex].stackType = null;
			g_Scripts[iCurrScriptIndex].stackUnionValue = null;

			g_Scripts[iCurrScriptIndex].iEntryPoint = null; // The entry point
			// g_Scripts[iCurrScriptIndex].iParamCount = null; // The parameter count
			g_Scripts[iCurrScriptIndex].iLocalDataSize = null; // Total size of all local data
			g_Scripts[iCurrScriptIndex].iStackFrameSize = null; // Total size of the stack frame
			g_Scripts[iCurrScriptIndex].funcName = null;

			g_Scripts[iCurrScriptIndex].hostAPICalls = null;
		}
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_LoadScript ()
	 * <p/>
	 * Loads an .XSE file into memory.
	 */

	public int XS_LoadScript(String pstrFilename, int iThreadTimeslice) throws Exception {
		int iFreeThreadFound = FALSE;
		int iThreadIndex = 0;
		for (int iCurrThreadIndex = 0; iCurrThreadIndex < MAX_THREAD_COUNT; ++iCurrThreadIndex) {

			if (!g_Scripts[iCurrThreadIndex].iIsActive) {
				iThreadIndex = iCurrThreadIndex;
				iFreeThreadFound = TRUE;
				break;
			}
		}

		if (iFreeThreadFound == FALSE)
			return LOAD_ERROR_OUT_OF_THREADS;
		DataInputStream dis = new DataInputStream(pstrFilename.getClass().getResourceAsStream(pstrFilename));

		try {
			readHeader(iThreadIndex, dis, iThreadTimeslice);
			readInstrs(iThreadIndex, dis);
			readStrTable(iThreadIndex, dis);
			readFuncTable(iThreadIndex, dis);
			readHostAPI(iThreadIndex, dis);

		} catch (Exception e) {
			e.printStackTrace();
		}
		dis.close();

		// System.out.print(pstrFilename + " loaded successfully!\n");
		// System.out.print("\n");
		// System.out.print(" Stack Size: " + g_Scripts[iThreadIndex].Stack.iType.length + "\n");
		// System.out.print("Global Data Size: " + g_Scripts[iThreadIndex].iGlobalDataSize + "\n");
		// System.out.print(" Functions: " + g_Scripts[iThreadIndex].FuncTable.iSize + "\n");
		//
		// System.out.print("_Main () Present: ");
		// if (g_Scripts[iThreadIndex].iIsMainFuncPresent != 0)
		// System.out.print("Yes (Index " + g_Scripts[iThreadIndex].iMainFuncIndex + ")");
		// else
		// System.out.print("No");
		// System.out.print("\n");
		//
		// System.out.print(" Host API Calls: " + g_Scripts[iThreadIndex].HostAPICallTable.iSize + "\n");
		// System.out.print(" Instructions: " + g_Scripts[iThreadIndex].InstrStream.iSize + "\n");
		// System.out.print(" String Literals: " + g_StrTable[iThreadIndex].length + "\n");
		//
		// System.out.print("\n");

		g_Scripts[iThreadIndex].iIsActive = true;

		g_Scripts[iThreadIndex].iIsRunning = false;

		XS_ResetScript(iThreadIndex);
		return iThreadIndex;
	}

	/**
	 * 
	 */
	private final void readHeader(int iThreadIndex, DataInputStream dis, int iThreadTimeslice) throws IOException {
		String pstrIDString;

		pstrIDString = dis.readUTF();

		if (!pstrIDString.equals(XSE_ID_STRING))
			ExitOnCodeError("LOAD_ERROR_INVALID_XSE", iThreadIndex);
		pstrIDString = null;
		int iMajorVersion = dis.read(), iMinorVersion = dis.read();
		if (iMajorVersion != 0 || iMinorVersion != 8)
			ExitOnCodeError("LOAD_ERROR_UNSUPPORTED_VERS", iThreadIndex);
		int iSize = dis.readInt();

		if (iSize == 0) {
			iSize = DEF_STACK_SIZE;
		}
		// g_Scripts[iThreadIndex].Stack = new RuntimeStack();
		g_Scripts[iThreadIndex].stackType = new byte[iSize];
		g_Scripts[iThreadIndex].stackUnionValue = new int[iSize];
		g_Scripts[iThreadIndex].scriptContext = new int[4];
		g_Scripts[iThreadIndex]._RetVal = new int[2];
		g_Scripts[iThreadIndex].scriptContext[0] = dis.readInt();
		g_Scripts[iThreadIndex].iIsMainFuncPresent = dis.readByte();

		g_Scripts[iThreadIndex].scriptContext[1] = dis.readInt();

		int iPriorityType = 0;
		iPriorityType = dis.readByte();

		g_Scripts[iThreadIndex].iTimesliceDur = dis.readInt();

		if (iThreadTimeslice != XS_THREAD_PRIORITY_USER)
			iPriorityType = iThreadTimeslice;

		// If the priority type is not set to user-defined, fill in the appropriate timeslice duration

		switch (iPriorityType) {
			case XS_THREAD_PRIORITY_LOW:
				g_Scripts[iThreadIndex].iTimesliceDur = THREAD_PRIORITY_DUR_LOW;
				break;

			case XS_THREAD_PRIORITY_MED:
				g_Scripts[iThreadIndex].iTimesliceDur = THREAD_PRIORITY_DUR_MED;
				break;

			case XS_THREAD_PRIORITY_HIGH:
				g_Scripts[iThreadIndex].iTimesliceDur = THREAD_PRIORITY_DUR_HIGH;
				break;
		}
	}

	private final void readInstrs(int iThreadIndex, DataInputStream dis) throws IOException {
		// Read the instruction count (4 bytes)
		int size = dis.readInt();
		// g_Scripts[iThreadIndex].iSize = size;
		// Allocate the stream
		g_Scripts[iThreadIndex].instruction = new byte[size][];
		// Read the instruction data
		for (int iCurrInstrIndex = 0; iCurrInstrIndex < size; ++iCurrInstrIndex) {
			// Read the opcode (2 bytes)
			byte iOpcode = (byte) dis.readShort();
			// Read the operand count (1 byte)
			byte iOpCount = dis.readByte();
			g_Scripts[iThreadIndex].instruction[iCurrInstrIndex] = new byte[2 + 5 * iOpCount];
			// g_Scripts[iThreadIndex].pInstrs[iCurrInstrIndex].iOpCount = iOpCount;
			g_Scripts[iThreadIndex].instruction[iCurrInstrIndex][0] = iOpcode;
			g_Scripts[iThreadIndex].instruction[iCurrInstrIndex][1] = iOpCount;
			// Allocate space for the operand list in a temporary pointer
			// int[][] pOpList = new int[iOpCount][2];

			// Read in the operand list (N bytes)

			for (int iCurrOpIndex = 0; iCurrOpIndex < iOpCount; ++iCurrOpIndex) {
				g_Scripts[iThreadIndex].instruction[iCurrInstrIndex][2 + iCurrOpIndex * 5] = dis.readByte();
				g_Scripts[iThreadIndex].instruction[iCurrInstrIndex][2 + iCurrOpIndex * 5 + 1] = dis.readByte();
				g_Scripts[iThreadIndex].instruction[iCurrInstrIndex][2 + iCurrOpIndex * 5 + 2] = dis.readByte();
				g_Scripts[iThreadIndex].instruction[iCurrInstrIndex][2 + iCurrOpIndex * 5 + 3] = dis.readByte();
				g_Scripts[iThreadIndex].instruction[iCurrInstrIndex][2 + iCurrOpIndex * 5 + 4] = dis.readByte();

			}
		}

	}

	public int byte2Int(byte v0, byte v1, byte v2, byte v3) {
		return ((((v0 & 0xff) << 24) | ((v1 & 0xff) << 16) | ((v2 & 0xff) << 8) | (v3 & 0xff)));
	}

	private final void updateStrTable(int threadIndex, int index, String s) {
		g_Scripts[threadIndex].g_StrTable[index] = s;
	}

	private final void readStrTable(int iThreadIndex, DataInputStream dis) throws IOException {
		// Read the table size (4 bytes)

		int iStringTableSize = 0;
		iStringTableSize = dis.readInt();
		g_Scripts[iThreadIndex].g_StrTable = new String[iStringTableSize];
		for (int i = 0; i < iStringTableSize; i++) {
			g_Scripts[iThreadIndex].g_StrTable[i] = new String();
			g_Scripts[iThreadIndex].g_StrTable[i] = dis.readUTF();
		}
	}

	private final void readFuncTable(int iThreadIndex, DataInputStream dis) throws IOException {
		// Read the function count (4 bytes)

		int iFuncTableSize = dis.readInt();

		g_Scripts[iThreadIndex].iEntryPoint = new int[iFuncTableSize]; // The entry point
		// g_Scripts[iThreadIndex].iParamCount = new byte[iFuncTableSize]; // The parameter count
		g_Scripts[iThreadIndex].iLocalDataSize = new int[iFuncTableSize]; // Total size of all local data
		g_Scripts[iThreadIndex].iStackFrameSize = new int[iFuncTableSize]; // Total size of the stack frame
		g_Scripts[iThreadIndex].funcName = new String[iFuncTableSize];
		// g_Scripts[iThreadIndex].funcSize = iFuncTableSize;
		// Read each function

		for (int iCurrFuncIndex = 0; iCurrFuncIndex < iFuncTableSize; ++iCurrFuncIndex) {
			// Read the entry point (4 bytes)

			g_Scripts[iThreadIndex].iEntryPoint[iCurrFuncIndex] = dis.readInt();

			// Read the parameter count (1 byte)

			byte iParamCount = dis.readByte();

			// Read the local data size (4 bytes)

			g_Scripts[iThreadIndex].iLocalDataSize[iCurrFuncIndex] = dis.readInt();

			// Calculate the stack size

			g_Scripts[iThreadIndex].iStackFrameSize[iCurrFuncIndex] = iParamCount + 1 + g_Scripts[iThreadIndex].iLocalDataSize[iCurrFuncIndex];

			g_Scripts[iThreadIndex].funcName[iCurrFuncIndex] = dis.readUTF();
		}

	}

	private final void readHostAPI(int iThreadIndex, DataInputStream dis) throws IOException {
		// Read the host API call count
		int HostAPICallTableSize = dis.readInt();

		// Allocate space for the table

		g_Scripts[iThreadIndex].hostAPICalls = new String[HostAPICallTableSize];

		// Read each host API call

		for (int iCurrCallIndex = 0; iCurrCallIndex < HostAPICallTableSize; ++iCurrCallIndex) {
			// Read the host API call string size (1 byte)

			// int iCallLength = dis.read();

			// Allocate space for the string plus the null
			// terminator in a temporary pointer

			String pstrCurrCall;

			// Read the host API call string data and append the
			// null terminator
			pstrCurrCall = dis.readUTF();

			// Assign the temporary pointer to the table

			g_Scripts[iThreadIndex].hostAPICalls[iCurrCallIndex] = pstrCurrCall;
		}

	}

	public void ExitOnCodeError(String msg, int g_iCurrThread) {

		System.out.println("VM ERROR: " + msg);
		System.out.println("Could not run thread: " + g_iCurrThread);
		System.out.println("==========================================================");
	}

	public void XS_RunScript(int g_iCurrThread, int iTimesliceDur) {
		XS_ResetScript(g_iCurrThread);
		long iMainTimesliceStartTime = System.currentTimeMillis();
		while (true) {
			long curTime = GetCurrTime();

			if (g_Scripts[g_iCurrThread].iIsPaused) {
				if (GetCurrTime() >= g_Scripts[g_iCurrThread].iPauseEndTime) {
					g_Scripts[g_iCurrThread].iIsPaused = false;
				} else {
					continue;
				}
			}
			g_Scripts[g_iCurrThread].iExitExecLoop = false;
			runInstr(g_iCurrThread);
			if (g_Scripts[g_iCurrThread].iExitExecLoop)
				break;
			if (iTimesliceDur != XS_INFINITE_TIMESLICE) {
				if (curTime > iMainTimesliceStartTime + iTimesliceDur) {
					break;
				}
			}
		}
	}

	public void XS_RunScripts(int iTimesliceDur) {
		long iMainTimesliceStartTime = System.currentTimeMillis();
		long g_iCurrThreadActiveTime = GetCurrTime();
		while (true) {
			long curTime = GetCurrTime();
			if (g_iCurrThreadMode == THREAD_MODE_MULTI) {
				if (curTime > g_iCurrThreadActiveTime + g_Scripts[g_iCurrThread].iTimesliceDur || !g_Scripts[g_iCurrThread].iIsRunning) {
					while (true) {
						++g_iCurrThread;

						if (g_iCurrThread >= MAX_THREAD_COUNT) {
							g_iCurrThread = 0;
						}

						if (g_Scripts[g_iCurrThread].iIsActive && g_Scripts[g_iCurrThread].iIsRunning)
							break;

					}

					g_iCurrThreadActiveTime = curTime;
				}
			}
			g_Scripts[g_iCurrThread].iExitExecLoop = false;

			if (g_Scripts[g_iCurrThread].iIsPaused) {

				if (GetCurrTime() >= g_Scripts[g_iCurrThread].iPauseEndTime) {
					g_Scripts[g_iCurrThread].iIsPaused = false;
				} else {

					continue;
				}
			}

			runInstr(g_iCurrThread);

			if (g_Scripts[g_iCurrThread].iExitExecLoop)
				break;
			if (iTimesliceDur != XS_INFINITE_TIMESLICE) {
				if (curTime > iMainTimesliceStartTime + iTimesliceDur) {
					break;
				}
			}
		}

	}

	public void runInstr(int g_iCurrThread) {

		int iCurrInstr = g_Scripts[g_iCurrThread].scriptContext[2];
		if (iCurrInstr < 0) {
			// 鎯宠鍋滄鑴氭湰杩愯
			XS_StopScript(g_iCurrThread);
			g_Scripts[g_iCurrThread].iExitExecLoop = true;
			return;
		}
		// Get the current opcode

		byte iOpcode = g_Scripts[g_iCurrThread].instruction[iCurrInstr][0];

		switch (iOpcode) {
			case INSTR_MOV:

				// Arithmetic Operations

			case INSTR_ADD:
			case INSTR_SUB:
			case INSTR_MUL:
			case INSTR_DIV:
			case INSTR_MOD:
			case INSTR_EXP:

				// Bitwise Operations

			case INSTR_AND:
			case INSTR_OR:
			case INSTR_XOR:
			case INSTR_SHL:
			case INSTR_SHR: {
				// Get a local copy of the destination
				byte DestT = ResolveOpType(g_iCurrThread, 0);
				int DestV = ResolveOpValue(g_iCurrThread, 0);

				// Get a local copy of the source
				// operand (operand index 1)

				byte SourceT = ResolveOpType(g_iCurrThread, 1);
				int SourceV = ResolveOpValue(g_iCurrThread, 1);
				// Depending on the instruction, perform
				// a binary operation

				switch (iOpcode) {
					// Move

					case INSTR_MOV:

						if (DestT == SourceT && DestV == SourceV)
							break;

						DestT = SourceT;
						DestV = SourceV;

						break;

					// Add

					case INSTR_ADD:

						if (DestT == OP_TYPE_INT) {
							DestV = DestV + SourceV;
						}
						break;
					// Subtract

					case INSTR_SUB:

						if (DestT == OP_TYPE_INT) {
							DestV = DestV - SourceV;
						}

						break;

					// Multiply

					case INSTR_MUL:

						if (DestT == OP_TYPE_INT) {
							DestV = DestV * SourceV;
						}
						break;

					// Divide

					case INSTR_DIV:

						if (DestT == OP_TYPE_INT) {
							DestV = DestV / SourceV;
						}
						break;

					// Modulus

					case INSTR_MOD:

						// Remember, Mod works with integers only

						if (DestT == OP_TYPE_INT) {
							DestV = DestV % SourceV;
						}

						break;

					// Exponentiate

					case INSTR_EXP:

						if (DestT == OP_TYPE_INT)
							DestV = power(DestV, SourceV);
						break;

					// The bitwise instructions only work with integers. They do nothing when the destination data type
					// is anything else.

					// And

					case INSTR_AND:

						if (DestT == OP_TYPE_INT)
							DestV = DestV & ResolveOpAsInt(g_iCurrThread, 1);

						break;

					// Or

					case INSTR_OR:

						if (DestT == OP_TYPE_INT)
							DestV = DestV | ResolveOpAsInt(g_iCurrThread, 1);

						break;

					// Exclusive Or

					case INSTR_XOR:

						if (DestT == OP_TYPE_INT)
							DestV = DestV ^ ResolveOpAsInt(g_iCurrThread, 1);

						break;

					// Shift Left

					case INSTR_SHL:

						if (DestT == OP_TYPE_INT)
							DestV = DestV << ResolveOpAsInt(g_iCurrThread, 1);
						break;

					// Shift Right

					case INSTR_SHR:

						if (DestT == OP_TYPE_INT)
							DestV = DestV >> ResolveOpAsInt(g_iCurrThread, 1);

						break;
				}

				// Use ResolveOpPntr () to get a pointer
				// to the destination Value structure
				// and
				// move the result there

				SetOpPntr(g_iCurrThread, 0, (byte) DestT, DestV);

				break;
			}

				// ---- Unary Operations

				// These instructions work much like the
				// binary operations in the sense that
				// they only work with integers and
				// floats (except Not, which works with
				// integers only). Any other destination
				// data type will be ignored.

			case INSTR_NEG:
			case INSTR_NOT:
			case INSTR_INC:
			case INSTR_DEC: {
				// Get the destination type (operand
				// index 0)

				// Get a local copy of the destination
				// itself

				byte DestT = ResolveOpType(g_iCurrThread, 0);
				int DestV = ResolveOpValue(g_iCurrThread, 0);

				switch (iOpcode) {
					// Negate

					case INSTR_NEG:

						if (DestT == OP_TYPE_INT)
							DestV = -CoerceValueToInt(g_iCurrThread, (byte) DestT, DestV);
						break;

					// Not

					case INSTR_NOT:

						if (DestT == OP_TYPE_INT)
							DestV = ~CoerceValueToInt(g_iCurrThread, (byte) DestT, DestV);

						break;

					// Increment

					case INSTR_INC:

						if (DestT == OP_TYPE_INT)
							DestV = CoerceValueToInt(g_iCurrThread, (byte) DestT, DestV) + 1;

						break;

					// Decrement

					case INSTR_DEC:

						if (DestT == OP_TYPE_INT)
							DestV = CoerceValueToInt(g_iCurrThread, (byte) DestT, DestV) - 1;

						break;
				}

				// Move the result to the destination
				SetOpPntr(g_iCurrThread, 0, (byte) DestT, DestV);

				break;
			}

				// ---- String Processing

			case INSTR_CONCAT: {
				// Get a local copy of the destination
				// operand (operand index 0)

				byte DestT = ResolveOpType(g_iCurrThread, 0);
				int DestV = ResolveOpValue(g_iCurrThread, 0);

				// Get a local copy of the source string
				// (operand index 1)

				String pstrSourceString = ResolveOpAsString(g_iCurrThread, 1);

				// If the destination isn't a string, do
				// nothing

				if (DestT != OP_TYPE_STRING)
					break;
				updateStrTable(g_iCurrThread, DestV, g_Scripts[g_iCurrThread].g_StrTable[DestV] + pstrSourceString);

				// Copy the concatenated string pointer
				// to its destination
				SetOpPntr(g_iCurrThread, 0, (byte) DestT, DestV);

				break;
			}

			case INSTR_GETCHAR: {
				// Get a local copy of the destination
				// operand (operand index 0)

				byte DestT = ResolveOpType(g_iCurrThread, 0);
				int DestV = ResolveOpValue(g_iCurrThread, 0);

				// Get a local copy of the source string
				// (operand index 1)

				String pstrSourceString = ResolveOpAsString(g_iCurrThread, 1);

				// Find out whether or not the
				// desDestTn is already a string
				int index = DestV;
				String pstrNewString;
				if (DestT == OP_TYPE_STRING) {
					// If it is, we can use it's
					// existing string buffer as
					// long as it's at
					// least 1 character

					if (g_Scripts[g_iCurrThread].g_StrTable[index].length() >= 1) {
						pstrNewString = g_Scripts[g_iCurrThread].g_StrTable[index];
					}
				} else {
					// Otherwise allocate a new
					// string and set the type
					DestT = OP_TYPE_STRING;
				}

				// Get the index of the character
				// (operand index 2)

				int iSourceIndex = ResolveOpAsInt(g_iCurrThread, 2);
				// Copy the character and append a
				// null-terminator
				pstrNewString = "" + pstrSourceString.charAt(iSourceIndex);

				// Set the string pointer in the
				// destination Value structure

				g_Scripts[g_iCurrThread].g_StrTable[index] = pstrNewString;

				// Copy the concatenated string pointer
				// to its destination
				SetOpPntr(g_iCurrThread, 0, (byte) DestT, DestV);

				break;
			}

			case INSTR_SETCHAR: {
				// Get the destination index (operand
				// index 1)

				int iDestIndex = ResolveOpAsInt(g_iCurrThread, 1);

				// If the destination isn't a string, do
				// nothing

				if (ResolveOpType(g_iCurrThread, 0) != OP_TYPE_STRING)
					break;

				// Get the source character (operand
				// index 2)

				String pstrSourceString = ResolveOpAsString(g_iCurrThread, 2);

				int index = ResolveOpPntr(g_iCurrThread, 0);
				char[] ch = g_Scripts[g_iCurrThread].g_StrTable[index].toCharArray();
				ch[iDestIndex] = pstrSourceString.charAt(0);
				g_Scripts[g_iCurrThread].g_StrTable[index] = new String(ch);

				break;
			}

				// ---- Conditional Branching

			case INSTR_JMP: {
				// Get the index of the target
				// instruction (opcode index 0)

				int iTargetIndex = ResolveOpAsInstrIndex(g_iCurrThread, 0);

				// Move the instruction pointer to the
				// target

				g_Scripts[g_iCurrThread].scriptContext[2] = iTargetIndex;
				break;
			}

			case INSTR_JE:
			case INSTR_JNE:
			case INSTR_JG:
			case INSTR_JL:
			case INSTR_JGE:
			case INSTR_JLE: {
				// Get the two operands
				byte Op0T = ResolveOpType(g_iCurrThread, 0);
				int Op0V = ResolveOpValue(g_iCurrThread, 0);
				int Op1V = ResolveOpValue(g_iCurrThread, 1);

				// Get the index of the target
				// instruction (opcode index 2)

				int iTargetIndex = ResolveOpAsInstrIndex(g_iCurrThread, 2);

				// Perform the specified comparison and
				// jump if it evaluates to true

				int iJump = FALSE;

				switch (iOpcode) {
					// Jump if Equal

					case INSTR_JE: {
						switch (Op0T) {
							case OP_TYPE_INT:
								if (Op0V == Op1V)
									iJump = TRUE;
								break;
							case OP_TYPE_FLOAT:
								break;
							case OP_TYPE_STRING:
								// cmpare
								// index
								if (Op0V == Op1V)
									iJump = TRUE;
								break;
						}
						break;
					}

						// Jump if Not Equal

					case INSTR_JNE: {
						switch (Op0T) {
							case OP_TYPE_INT:
								if (Op0V != Op1V)
									iJump = TRUE;
								break;
							case OP_TYPE_FLOAT:
								iJump = TRUE;
								break;

							case OP_TYPE_STRING:
								if (Op0V != Op1V)
									iJump = TRUE;
								break;
						}
						break;
					}

						// Jump if Greater

					case INSTR_JG:

						if (Op0T == OP_TYPE_INT) {
							if (Op0V > Op1V)
								iJump = TRUE;
						} else {
						}

						break;

					// Jump if Less

					case INSTR_JL:

						if (Op0T == OP_TYPE_INT) {
							if (Op0V < Op1V)
								iJump = TRUE;
						} else {
						}

						break;

					// Jump if Greater or Equal

					case INSTR_JGE:

						if (Op0T == OP_TYPE_INT) {
							if (Op0V >= Op1V)
								iJump = TRUE;
						} else {
						}

						break;

					// Jump if Less or Equal

					case INSTR_JLE:

						if (Op0T == OP_TYPE_INT) {
							if (Op0V <= Op1V)
								iJump = TRUE;
						} else {
						}

						break;
				}

				// If the comparison evaluated to TRUE,
				// make the jump

				if (iJump != 0) {
					g_Scripts[g_iCurrThread].scriptContext[2] = iTargetIndex;
				} else {
				}
				break;
			}

				// ---- The Stack Interface

			case INSTR_PUSH: {
				// Get a local copy of the source
				// operand (operand index 0)
				byte SourceT = ResolveOpType(g_iCurrThread, 0);
				int SourceV = ResolveOpValue(g_iCurrThread, 0);

				// Push the value onto the stack

				Push(g_iCurrThread, SourceT, SourceV);

				break;
			}

			case INSTR_POP: {
				// Pop the top of the stack into the
				// destination

				int tmp = Pop(g_iCurrThread);
				SetOpPntr(g_iCurrThread, 0, g_Scripts[g_iCurrThread].stackType[tmp], g_Scripts[g_iCurrThread].stackUnionValue[tmp]);
				break;
			}

				// ---- The Function Call Interface

			case INSTR_CALL: {

				int iFuncIndex = ResolveOpAsFuncIndex(g_iCurrThread, 0);

				// Advance the instruction pointer so it
				// points to the instruction
				// immediately following the call

				++g_Scripts[g_iCurrThread].scriptContext[2];

				// Call the function

				CallFunc(g_iCurrThread, iFuncIndex);

				break;
			}

			case INSTR_RET: {
				// Get the current function index off
				// the top of the stack and use it to
				// get
				// the corresponding function structure

				int FuncIndex = Pop(g_iCurrThread);

				// byte[] tempByte = int2Byte(FuncIndex[1]);
				// Check for the presence of a stack
				// base marker

				if (g_Scripts[g_iCurrThread].stackType[FuncIndex] == OP_TYPE_STACK_BASE_MARKER)
					g_Scripts[g_iCurrThread].iExitExecLoop = true;
				int value = g_Scripts[g_iCurrThread].stackUnionValue[FuncIndex];
				int func = getHightBit(value);
				int iFrameIndex = getLowBit(value);
				// Read the return address structure
				// from the stack, which is stored one
				// index below the local data

				int ReturnAddr = GetStackValue(g_iCurrThread, g_Scripts[g_iCurrThread].scriptContext[3]
				        - (g_Scripts[g_iCurrThread].iLocalDataSize[func] + 1));

				// Pop the stack frame along with the
				// return address

				PopFrame(g_iCurrThread, g_Scripts[g_iCurrThread].iStackFrameSize[func]);

				// Restore the previous frame index

				g_Scripts[g_iCurrThread].iFrameIndex = iFrameIndex;

				// Make the jump to the return address

				g_Scripts[g_iCurrThread].scriptContext[2] = ReturnAddr;
				break;
			}

			case INSTR_CALLHOST: {
				// Use operand zero to index into the
				// host API call table and get the
				// host API function name

				int temp = ResolveOpValue(g_iCurrThread, 0);
				int HostAPICallV = temp;
				int iHostAPICallIndex = HostAPICallV;

				// Get the name of the host API function

				String pstrFuncName = GetHostAPICall(g_iCurrThread, iHostAPICallIndex);

				// Search through the host API until the
				// matching function is found

				for (int i = 0; i < g_HostAPI.size(); i++) {
					HostAPI hostApi = (HostAPI) g_HostAPI.elementAt(i);
					if (hostApi.call(pstrFuncName, g_iCurrThread)) {
						hostApi = null;
						break;
					}
					hostApi = null;
				}
				break;
			}

				// ---- Misc

			case INSTR_PAUSE: {
				// If we're already paused, do nothing

				if (g_Scripts[g_iCurrThread].iIsPaused)
					break;

				// Get the pause duration

				int iPauseDuration = ResolveOpAsInt(g_iCurrThread, 0);
				// Determine the ending pause time

				g_Scripts[g_iCurrThread].iPauseEndTime = GetCurrTime() + iPauseDuration;

				// Pause the script

				g_Scripts[g_iCurrThread].iIsPaused = true;

				break;
			}

			case INSTR_EXIT:

				// Resolve operand zero to find the exit code
				// Get it from the integer field

				// Tell the XVM to stop executing the
				// script

				g_Scripts[g_iCurrThread].iIsRunning = false;

				// Break the execution cycle

				g_Scripts[g_iCurrThread].iExitExecLoop = true;

				break;
		}

		if (iCurrInstr == g_Scripts[g_iCurrThread].scriptContext[2])
			++g_Scripts[g_iCurrThread].scriptContext[2];
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_ResetScript ()
	 * <p/>
	 * Resets the script.
	 */

	public void XS_ResetScript(int iThreadIndex) {
		// Get _Main ()'s function index in case we need it

		int iMainFuncIndex = g_Scripts[iThreadIndex].scriptContext[1];
		iPauseEndTime = 0;
		// If the function table is present, set the entry point

		if (g_Scripts[iThreadIndex].iEntryPoint != null) {
			if (g_Scripts[iThreadIndex].iIsMainFuncPresent != 0) {
				g_Scripts[iThreadIndex].scriptContext[2] = g_Scripts[iThreadIndex].iEntryPoint[iMainFuncIndex];
			}
		}

		// Clear the stack

		g_Scripts[iThreadIndex].scriptContext[3] = 0;
		g_Scripts[iThreadIndex].iFrameIndex = 0;

		// Set the entire stack to null
		int iSize = g_Scripts[iThreadIndex].stackType.length;
		for (int iCurrElmntIndex = 0; iCurrElmntIndex < iSize; ++iCurrElmntIndex) {
			g_Scripts[iThreadIndex].stackType[iCurrElmntIndex] = OP_TYPE_NULL;
		}

		// Unpause the script

		g_Scripts[iThreadIndex].iIsPaused = false;

		// Allocate space for the globals

		PushFrame(iThreadIndex, g_Scripts[iThreadIndex].scriptContext[0]);
		PushFrame(iThreadIndex, g_Scripts[iThreadIndex].iStackFrameSize[iMainFuncIndex] + 1);
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_StartScript ()
	 * <p/>
	 * Starts the execution of a script.
	 */

	public void XS_StartScript(int iThreadIndex) {
		// Make sure the thread index is valid and active

		if (IsThreadActive(iThreadIndex) == FALSE)
			return;

		// Set the thread's execution flag

		g_Scripts[iThreadIndex].iIsRunning = true;

		// Set the current thread to the script

		g_iCurrThread = iThreadIndex;

		// Set the activation time for the current thread to get things
		// rolling
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_StopScript ()
	 * <p/>
	 * Stops the execution of a script.
	 */

	public void XS_StopScript(int iThreadIndex) {
		// Make sure the thread index is valid and active

		if (IsThreadActive(iThreadIndex) == FALSE)
			return;

		// Clear the thread's execution flag

		g_Scripts[iThreadIndex].iIsRunning = false;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_PauseScript ()
	 * <p/>
	 * Pauses a script for a specified duration.
	 */

	public void XS_PauseScript(int iThreadIndex, int iDur) {
		// Make sure the thread index is valid and active

		if (IsThreadActive(iThreadIndex) == FALSE)
			return;

		// Set the pause flag

		g_Scripts[iThreadIndex].iIsPaused = true;

		// Set the duration of the pause

		g_Scripts[iThreadIndex].iPauseEndTime = GetCurrTime() + iDur;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_UnpauseScript ()
	 * <p/>
	 * Unpauses a script.
	 */

	public void XS_UnpauseScript(int iThreadIndex) {
		// Make sure the thread index is valid and active

		if (IsThreadActive(iThreadIndex) == FALSE)
			return;

		// Clear the pause flag

		g_Scripts[iThreadIndex].iIsPaused = false;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_GetReturnValueAsString ()
	 * <p/>
	 * Returns the last returned value as a string.
	 */

	public String XS_GetReturnValueAsString(int iThreadIndex) {
		// Make sure the thread index is valid and active

		if (IsThreadActive(iThreadIndex) == FALSE)
			return null;

		// Return _RetVal's string field
		int index = g_Scripts[iThreadIndex]._RetVal[1];
		return g_Scripts[iThreadIndex].g_StrTable[index];
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * CoereceValueToInt ()
	 * <p/>
	 * Coerces a Value structure from it's current type to an integer value.
	 */

	public int CoerceValueToInt(int g_iCurrThread, byte iType, int Val) {
		// Determine which type the Value currently is

		switch (iType) {
			// It's an integer, so return it as-is
			case OP_TYPE_FLOAT:
			case OP_TYPE_INT:
				return Val;
			case OP_TYPE_STRING:
				return Integer.parseInt(g_Scripts[g_iCurrThread].g_StrTable[Val]);
				// Anything else is invalid
			default:
				return 0;
		}
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * CoereceValueToString ()
	 * <p/>
	 * Coerces a Value structure from it's current type to a string value.
	 */

	public String CoerceValueToString(int g_iCurrThread, byte iType, int Val) {

		String pstrCoercion;
		int tmp = Val;
		// Determine which type the Value currently is
		switch (iType) {
			// It's an integer, so convert it to a string
			case OP_TYPE_FLOAT:
			case OP_TYPE_INT:
				pstrCoercion = String.valueOf(tmp);
				// itoa ( Val.iIntLiteral, pstrCoercion, 10 );
				return pstrCoercion;

			case OP_TYPE_STRING:
				return g_Scripts[g_iCurrThread].g_StrTable[tmp];
				// Anything else is invalid
			default:
				return null;
		}
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * GetOpType ()
	 * <p/>
	 * Returns the type of the specified operand in the current instruction.
	 */

	public int GetOpType(int g_iCurrThread, int iOpIndex) {
		// Get the current instruction

		int iCurrInstr = g_Scripts[g_iCurrThread].scriptContext[2];

		// Return the type

		return g_Scripts[g_iCurrThread].instruction[iCurrInstr][2 + iOpIndex * 5];// g_Scripts[g_iCurrThread].pInstrs[iCurrInstr].pOpList[iOpIndex][0];
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveOpStackIndex ()
	 * <p/>
	 * Resolves an operand's stack index, whether it's absolute or relative.
	 */

	public int ResolveOpStackIndex(int g_iCurrThread, int iOpIndex) {
		// Get the current instruction

		int iCurrInstr = g_Scripts[g_iCurrThread].scriptContext[2];

		// Get the operand type type

		// int[] OpValue = g_Scripts[g_iCurrThread].pInstrs[iCurrInstr].pOpList[iOpIndex];
		int startIndex = 2 + iOpIndex * 5;
		byte[] instr = g_Scripts[g_iCurrThread].instruction[iCurrInstr];
		// Resolve the stack index based on its type
		int tmp = byte2Int(instr[startIndex + 1], instr[startIndex + 2], instr[startIndex + 3], instr[startIndex + 4]);// OpValue[1];
		switch (instr[startIndex]) {
			// It's an absolute index so return it as-is

			case OP_TYPE_ABS_STACK_INDEX:
				return tmp;

				// It's a relative index so resolve it

			case OP_TYPE_REL_STACK_INDEX: {
				short iBaseIndex = (short) (getHightBit(tmp));
				short iOffsetIndex = (short) (getLowBit(tmp));

				int StackValue = GetStackValue(g_iCurrThread, iOffsetIndex);

				// Now add the variable's integer field to the
				// base index to produce the
				// absolute index
				return iBaseIndex + StackValue;
			}

			default:
				return 0;
		}
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveOpValue ()
	 * <p/>
	 * Resolves an operand and returns it's associated Value structure.
	 */

	public int ResolveOpValue(int g_iCurrThread, int iOpIndex) {
		// Get the current instruction

		int iCurrInstr = g_Scripts[g_iCurrThread].scriptContext[2];

		// Get the operand type type

		// int[] OpValue = g_Scripts[g_iCurrThread].pInstrs[iCurrInstr].pOpList[iOpIndex];
		int startIndex = 2 + iOpIndex * 5;
		byte[] instr = g_Scripts[g_iCurrThread].instruction[iCurrInstr];
		// Resolve the stack index based on its type
		int tmp = byte2Int(instr[startIndex + 1], instr[startIndex + 2], instr[startIndex + 3], instr[startIndex + 4]);

		// Determine what to return based on the value's type

		switch (instr[startIndex]) {
			// It's a stack index so resolve it

			case OP_TYPE_ABS_STACK_INDEX:
			case OP_TYPE_REL_STACK_INDEX: {
				// Resolve the index and use it to return the
				// corresponding stack element

				int iAbsIndex = ResolveOpStackIndex(g_iCurrThread, iOpIndex);

				// int[] tmp = new int[2];
				return GetStackValue(g_iCurrThread, iAbsIndex);

			}

				// It's in _RetVal

			case OP_TYPE_REG:
				return g_Scripts[g_iCurrThread]._RetVal[1];

				// Anything else can be returned as-is

			default:
				return tmp;
		}
	}

	public byte ResolveOpType(int g_iCurrThread, int iOpIndex) {
		// Get the current instruction

		int iCurrInstr = g_Scripts[g_iCurrThread].scriptContext[2];

		// Get the operand type type

		// int[] OpValue = g_Scripts[g_iCurrThread].pInstrs[iCurrInstr].pOpList[iOpIndex];
		int startIndex = 2 + iOpIndex * 5;
		byte[] instr = g_Scripts[g_iCurrThread].instruction[iCurrInstr];
		// Resolve the stack index based on its type
		// int tmp = byte2Int(instr[startIndex + 1], instr[startIndex + 2], instr[startIndex + 3], instr[startIndex +
		// 4]);

		// Determine what to return based on the value's type

		switch (instr[startIndex]) {
			// It's a stack index so resolve it

			case OP_TYPE_ABS_STACK_INDEX:
			case OP_TYPE_REL_STACK_INDEX: {
				// Resolve the index and use it to return the
				// corresponding stack element

				int iAbsIndex = ResolveOpStackIndex(g_iCurrThread, iOpIndex);

				// int[] tmp = new int[2];
				return GetStackType(g_iCurrThread, iAbsIndex);
			}

				// It's in _RetVal

			case OP_TYPE_REG:
				return (byte) g_Scripts[g_iCurrThread]._RetVal[0];

				// Anything else can be returned as-is

			default:
				return instr[startIndex];
		}
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveOpAsInt ()
	 * <p/>
	 * Resolves and coerces an operand's value to an integer value.
	 */

	public int ResolveOpAsInt(int g_iCurrThread, int iOpIndex) {
		// Resolve the operand's value

		// int[] OpValue = ResolveOpValue(iOpIndex);

		// Coerce it to an int and return it

		int iInt = CoerceValueToInt(g_iCurrThread, ResolveOpType(g_iCurrThread, iOpIndex), ResolveOpValue(g_iCurrThread, iOpIndex));
		return iInt;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveOpAsString ()
	 * <p/>
	 * Resolves and coerces an operand's value to a string value, allocating the space for a new string if necessary.
	 */

	public String ResolveOpAsString(int g_iCurrThread, int iOpIndex) {
		// Resolve the operand's value
		// int[] OpValue = ResolveOpValue(iOpIndex);
		// Coerce it to a string and return it
		return CoerceValueToString(g_iCurrThread, ResolveOpType(g_iCurrThread, iOpIndex), ResolveOpValue(g_iCurrThread, iOpIndex));
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveOpAsInstrIndex ()
	 * <p/>
	 * Resolves an operand as an intruction index.
	 */

	public int ResolveOpAsInstrIndex(int g_iCurrThread, int iOpIndex) {
		// Resolve the operand's value

		// int[] OpValue = ResolveOpValue(iOpIndex);

		// Return it's instruction index

		return ResolveOpValue(g_iCurrThread, iOpIndex);
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveOpAsFuncIndex ()
	 * <p/>
	 * Resolves an operand as a function index.
	 */

	public int ResolveOpAsFuncIndex(int g_iCurrThread, int iOpIndex) {
		// Resolve the operand's value

		// int[] OpValue = ResolveOpValue(iOpIndex);

		// Return the function index

		return ResolveOpValue(g_iCurrThread, iOpIndex);
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveOpAsHostAPICall ()
	 * <p/>
	 * Resolves an operand as a host API call
	 */

	public String ResolveOpAsHostAPICall(int g_iCurrThread, int iOpIndex) {
		// Resolve the operand's value

		// int[] OpValue = ResolveOpValue(iOpIndex);

		return GetHostAPICall(g_iCurrThread, ResolveOpValue(g_iCurrThread, iOpIndex));
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveOpPntr ()
	 * <p/>
	 * Resolves an operand and returns a pointer to it's Value structure.
	 */

	public int ResolveOpPntr(int g_iCurrThread, int iOpIndex) {
		// Get the method of indirection

		int iIndirMethod = GetOpType(g_iCurrThread, iOpIndex);

		// Return a pointer to wherever the operand lies

		switch (iIndirMethod) {
			// It's _RetVal

			case OP_TYPE_REG:
				return g_Scripts[g_iCurrThread]._RetVal[1];

				// It's on the stack

			case OP_TYPE_ABS_STACK_INDEX:
			case OP_TYPE_REL_STACK_INDEX: {
				int iStackIndex = ResolveOpStackIndex(g_iCurrThread, iOpIndex);
				// return g_Scripts[g_iCurrThread].Stack.pElmnts[ResolveStackIndex(iStackIndex)];
				return g_Scripts[g_iCurrThread].stackUnionValue[ResolveStackIndex(g_iCurrThread, iStackIndex)];
			}
		}

		return 0;
	}

	public void SetOpPntr(int g_iCurrThread, int iOpIndex, byte iType, int val) {
		int iIndirMethod = GetOpType(g_iCurrThread, iOpIndex);

		// Return a pointer to wherever the operand lies

		switch (iIndirMethod) {
			// It's _RetVal

			case OP_TYPE_REG:
				g_Scripts[g_iCurrThread]._RetVal[0] = iType;
				g_Scripts[g_iCurrThread]._RetVal[1] = val;

			case OP_TYPE_ABS_STACK_INDEX:
			case OP_TYPE_REL_STACK_INDEX: {
				int iStackIndex = ResolveOpStackIndex(g_iCurrThread, iOpIndex);
				g_Scripts[g_iCurrThread].stackType[ResolveStackIndex(g_iCurrThread, iStackIndex)] = iType;
				g_Scripts[g_iCurrThread].stackUnionValue[ResolveStackIndex(g_iCurrThread, iStackIndex)] = val;
			}
		}

	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * GetStackValue ()
	 * <p/>
	 * Returns the specified stack value.
	 */

	public byte GetStackType(int g_iCurrThread, int iIndex) {

		return g_Scripts[g_iCurrThread].stackType[ResolveStackIndex(g_iCurrThread, iIndex)];
	}

	public int GetStackValue(int g_iCurrThread, int iIndex) {

		return g_Scripts[g_iCurrThread].stackUnionValue[ResolveStackIndex(g_iCurrThread, iIndex)];
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * SetStackValue ()
	 * <p/>
	 * Sets the specified stack value.
	 */

	public void SetStackValue(int g_iCurrThread, int iIndex, byte iType, int Val) {
		// Use ResolveStackIndex () to set the element at the specified
		// index
		int stackIndex = ResolveStackIndex(g_iCurrThread, iIndex);
		g_Scripts[g_iCurrThread].stackType[stackIndex] = iType;
		g_Scripts[g_iCurrThread].stackUnionValue[stackIndex] = Val;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * ResolveStackIndex ()
	 * <p/>
	 * Resolves a stack index by translating negative indices relative to the top of the stack, to positive ones
	 * relative to the bottom.
	 */

	public int ResolveStackIndex(int g_iCurrThread, int iIndex) {
		return (iIndex < 0 ? iIndex += g_Scripts[g_iCurrThread].iFrameIndex : iIndex);
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * Push ()
	 * <p/>
	 * Pushes an element onto the stack.
	 */

	public void Push(int iThreadIndex, byte iType, int Val) {
		int iTopIndex = g_Scripts[iThreadIndex].scriptContext[3];
		g_Scripts[iThreadIndex].stackType[iTopIndex] = iType;
		g_Scripts[iThreadIndex].stackUnionValue[iTopIndex] = Val;
		++g_Scripts[iThreadIndex].scriptContext[3];
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * Pop ()
	 * <p/>
	 * Pops the element off the top of the stack.
	 */

	public int Pop(int iThreadIndex) {
		// Decrement the top index to clear the old element for
		// overwriting

		return --g_Scripts[iThreadIndex].scriptContext[3];
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * PushFrame ()
	 * <p/>
	 * Pushes a stack frame.
	 */

	public void PushFrame(int iThreadIndex, int iSize) {
		// Increment the top index by the size of the frame

		g_Scripts[iThreadIndex].scriptContext[3] += iSize;

		// Move the frame index to the new top of the stack

		g_Scripts[iThreadIndex].iFrameIndex = g_Scripts[iThreadIndex].scriptContext[3];
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * PopFrame ()
	 * <p/>
	 * Pops a stack frame.
	 */

	public void PopFrame(int g_iCurrThread, int iSize) {
		// Decrement the top index by the size of the frame

		g_Scripts[g_iCurrThread].scriptContext[3] -= iSize;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * GetHostAPICall ()
	 * <p/>
	 * Returns the host API call corresponding to the specified index.
	 */

	public String GetHostAPICall(int g_iCurrThread, int iIndex) {
		return g_Scripts[g_iCurrThread].hostAPICalls[iIndex];
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * PrintOpIndir ()
	 * <p/>
	 * Prints an operand's method of indirection.
	 */

	public void PrintOpIndir(int g_iCurrThread, int iOpIndex) {
		// Get the method of indirection

		int iIndirMethod = GetOpType(g_iCurrThread, iOpIndex);

		// Print it out

		switch (iIndirMethod) {
			// It's _RetVal

			case OP_TYPE_REG:
				System.out.print("_RetVal");
				break;

			// It's on the stack

			case OP_TYPE_ABS_STACK_INDEX:
			case OP_TYPE_REL_STACK_INDEX: {
				int iStackIndex = ResolveOpStackIndex(g_iCurrThread, iOpIndex);
				System.out.print("[ " + iStackIndex + " ]");
				break;
			}
		}
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * PrintOpValue ()
	 * <p/>
	 * Prints an operand's value.
	 */

	public void PrintOpValue(int g_iCurrThread, int iOpIndex) {
		// if (true) return;
		// Resolve the operand's value

		// int[] Op = ResolveOpValue(iOpIndex);

		// Print it out

		switch (ResolveOpType(g_iCurrThread, iOpIndex)) {
			case OP_TYPE_NULL:
				System.out.print("Null");
				break;

			case OP_TYPE_INT:
				System.out.print("" + ResolveOpValue(g_iCurrThread, iOpIndex));
				break;
			// TODO
			case OP_TYPE_FLOAT:
				// System.out.print("" + Op.floatValue);
				break;

			case OP_TYPE_STRING:
				System.out.print("\"" + g_Scripts[g_iCurrThread].g_StrTable[ResolveOpValue(g_iCurrThread, iOpIndex)] + "\"");
				break;

			case OP_TYPE_INSTR_INDEX:
				System.out.print("" + ResolveOpValue(g_iCurrThread, iOpIndex));
				break;

			case OP_TYPE_HOST_API_CALL_INDEX: {
				String pstrHostAPICall = ResolveOpAsHostAPICall(g_iCurrThread, iOpIndex);
				System.out.print(pstrHostAPICall);
				break;
			}
		}
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * GetCurrTime ()
	 * <p/>
	 * Wrapper for the system-dependant method of determining the current time in milliseconds.
	 */

	public long GetCurrTime() {

		return System.currentTimeMillis();
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * CallFunc ()
	 * <p/>
	 * Calls a function based on its index.
	 */

	public void CallFunc(int iThreadIndex, int iIndex) {
		// Func DestFunc = GetFunc(iThreadIndex, iIndex);

		// Save the current stack frame index
		int iFrameIndex = g_Scripts[iThreadIndex].iFrameIndex;

		// Push the return address, which is the current instruction

		;
		Push(iThreadIndex, (byte) 0, g_Scripts[iThreadIndex].scriptContext[2]);

		int frame = g_Scripts[iThreadIndex].iLocalDataSize[iIndex] + 1;
		PushFrame(iThreadIndex, frame);
		// Write the function index and old stack frame to the top of the stack

		int func = byte2Int((byte) ((iIndex >> 8) & 0xff), (byte) ((iIndex) & 0xff), (byte) ((iFrameIndex >> 8) & 0xff),
		        (byte) ((iFrameIndex) & 0xff));// byte2Int(tmpByte);

		SetStackValue(iThreadIndex, g_Scripts[iThreadIndex].scriptContext[3] - 1, (byte) 0, func);

		// Let the caller make the jump to the entry point

		g_Scripts[iThreadIndex].scriptContext[2] = g_Scripts[iThreadIndex].iEntryPoint[iIndex];

	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * IsValidThreadIndex ()
	 * <p/>
	 * Returns TRUE if the specified thread index is within the bounds of the array, FALSE otherwise.
	 */

	public int IsValidThreadIndex(int iIndex) {
		return (iIndex < 0 || iIndex > MAX_THREAD_COUNT ? FALSE : TRUE);
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * IsThreadActive ()
	 * <p/>
	 * Returns TRUE if the specified thread is both a valid index and active, FALSE otherwise.
	 */

	public byte IsThreadActive(int iIndex) {
		return ((IsValidThreadIndex(iIndex) == TRUE) && g_Scripts[iIndex].iIsActive ? TRUE : FALSE);
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * GetFuncIndexByName ()
	 * <p/>
	 * Returns the index into the function table corresponding to the specified name.
	 */

	public int GetFuncIndexByName(int iThreadIndex, String pstrName) {
		// Loop through each function and look for a matching name
		int funcSize = g_Scripts[iThreadIndex].funcName.length;
		for (int iFuncIndex = 0; iFuncIndex < funcSize; ++iFuncIndex) {
			// If the names match, return the index

			if (pstrName.equals(g_Scripts[iThreadIndex].funcName[iFuncIndex]))
				return iFuncIndex;
		}

		// A match wasn't found, so return -1

		return -1;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_CallScriptFunc ()
	 * <p/>
	 * Calls a script function from the host application.
	 */

	public void XS_CallScriptFunc(int g_iCurrThread, int iThreadIndex, String pstrName) {
		// Make sure the thread index is valid and active

		if (IsThreadActive(iThreadIndex) == FALSE)
			return;

		// ---- Calling the function

		// Preserve the current state of the VM

		int iPrevThreadMode = g_iCurrThreadMode;
		int iPrevThread = g_iCurrThread;

		// Set the threading mode for single-threaded execution

		g_iCurrThreadMode = THREAD_MODE_SINGLE;

		// Set the active thread to the one specified

		g_iCurrThread = iThreadIndex;

		// Get the function's index based on it's name

		int iFuncIndex = GetFuncIndexByName(iThreadIndex, pstrName);

		// Make sure the function name was valid

		if (iFuncIndex == -1)
			return;

		// Call the function

		CallFunc(iThreadIndex, iFuncIndex);

		// Set the stack base

		SetStackValue(g_iCurrThread, g_Scripts[g_iCurrThread].scriptContext[3] - 1, OP_TYPE_STACK_BASE_MARKER,
		        GetStackValue(g_iCurrThread, g_Scripts[g_iCurrThread].scriptContext[3] - 1));

		// Allow the script code to execute uninterrupted until the
		// function returns

		XS_RunScripts(XS_INFINITE_TIMESLICE);

		// ---- Handling the function return

		// Restore the VM state

		g_iCurrThreadMode = iPrevThreadMode;
		g_iCurrThread = iPrevThread;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_InvokeScriptFunc ()
	 * <p/>
	 * Invokes a script function from the host application, meaning the call executes in sync with the script.
	 */

	public void XS_InvokeScriptFunc(int iThreadIndex, String pstrName) {
		// Make sure the thread index is valid and active

		if (IsThreadActive(iThreadIndex) == FALSE)
			return;

		// Get the function's index based on its name

		int iFuncIndex = GetFuncIndexByName(iThreadIndex, pstrName);

		// Make sure the function name was valid

		if (iFuncIndex == -1)
			return;

		// Call the function

		CallFunc(iThreadIndex, iFuncIndex);
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_RegisterHostAPIFunc ()
	 * <p/>
	 * Registers a function with the host API.
	 */

	public void XS_RegisterHostAPIFunc(HostAPI hostApi) {
		g_HostAPI.addElement(hostApi);

	}

	public int getMethodID(String[] methodNames, String name) {
		if (methodNames != null) {
			for (int i = 0; i < methodNames.length; i++) {
				if (name.equals(methodNames[i].toUpperCase())) {
					return i;
				}
			}
		}
		return -1;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_GetParamAsInt ()
	 * <p/>
	 * Returns the specified integer parameter to a host API function.
	 */

	public int XS_GetParamAsInt(int iThreadIndex, int iParamIndex) {
		// Get the current top element

		int iTopIndex = g_Scripts[iThreadIndex].scriptContext[3];
		// int[] Param = g_Scripts[iThreadIndex].Stack.pElmnts[iTopIndex - (iParamIndex + 1)];

		// Coerce the top element of the stack to an integer

		int iInt = CoerceValueToInt(iThreadIndex, g_Scripts[iThreadIndex].stackType[iTopIndex - (iParamIndex + 1)],
		        g_Scripts[iThreadIndex].stackUnionValue[iTopIndex - (iParamIndex + 1)]);

		// Return the value

		return iInt;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_GetParamAsString ()
	 * <p/>
	 * Returns the specified string parameter to a host API function.
	 */

	public String XS_GetParamAsString(int g_iCurrThread, int iParamIndex) {
		// Get the current top element

		int iTopIndex = g_Scripts[g_iCurrThread].scriptContext[3];
		// int[] Param = g_Scripts[iThreadIndex].Stack.pElmnts[iTopIndex - (iParamIndex + 1)];

		// Coerce the top element of the stack to a string

		String pstrString = CoerceValueToString(g_iCurrThread, g_Scripts[g_iCurrThread].stackType[iTopIndex - (iParamIndex + 1)],
		        g_Scripts[g_iCurrThread].stackUnionValue[iTopIndex - (iParamIndex + 1)]);

		// Return the value

		return pstrString;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_ReturnFromHost ()
	 * <p/>
	 * Returns from a host API function.
	 */

	public void XS_ReturnFromHost(int iThreadIndex, int iParamCount) {
		// Clear the parameters off the stack

		g_Scripts[iThreadIndex].scriptContext[3] -= iParamCount;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_ReturnIntFromHost ()
	 * <p/>
	 * Returns an integer from a host API function.
	 */

	public void XS_ReturnIntFromHost(int iThreadIndex, int iParamCount, int iInt) {
		// Clear the parameters off the stack

		g_Scripts[iThreadIndex].scriptContext[3] -= iParamCount;

		// Put the return value and type in _RetVal

		g_Scripts[iThreadIndex]._RetVal[0] = OP_TYPE_INT;
		g_Scripts[iThreadIndex]._RetVal[1] = iInt;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_ReturnFloatFromHost ()
	 * <p/>
	 * Returns a float from a host API function.
	 */

	public void XS_ReturnFloatFromHost(int iThreadIndex, int iParamCount, float fFloat) {
		// Clear the parameters off the stack

		g_Scripts[iThreadIndex].scriptContext[3] -= iParamCount;

		// Put the return value and type in _RetVal

		g_Scripts[iThreadIndex]._RetVal[0] = OP_TYPE_FLOAT;
	}

	/**
	 * ***************************************************************************************
	 * <p/>
	 * XS_ReturnStringFromHost ()
	 * <p/>
	 * Returns a string from a host API function.
	 */

	public void XS_ReturnStringFromHost(int iThreadIndex, int iParamCount, String pstrString) {
		// Clear the parameters off the stack
		g_Scripts[iThreadIndex].scriptContext[3] -= iParamCount;
		g_Scripts[iThreadIndex].g_StrTable = expandArray(g_Scripts[iThreadIndex].g_StrTable, 1);
		int size = g_Scripts[iThreadIndex].g_StrTable.length;
		int index = size - 1;
		g_Scripts[iThreadIndex].g_StrTable[index] = pstrString;

		// Put the return value and type in _RetVal

		g_Scripts[iThreadIndex]._RetVal[0] = OP_TYPE_STRING;
		g_Scripts[iThreadIndex]._RetVal[1] = index;
		// ReturnValue.pstrStringLiteral = pstrString;
	}

	private final String[] expandArray(String[] oldArray, int expandBy) {
		String[] newArray = new String[oldArray.length + expandBy];
		System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
		return newArray;
	}

	// 姹傛寚鏁扮殑Power绠楁硶(int 鐗堟湰)
	private final int power(int v, int exp) {
		int r = 1;
		for (int i = 0; i < exp; i++) {
			r *= v;
		}
		return r;
	}

	public class Script // Encapsulates a full script
	{
		// 褰撳墠鎸囦护鎸囬拡/鑴氭湰涓诲嚱鏁扮储寮?鍫嗘爤椤剁储寮?褰撳墠鍑芥暟鍫嗘爤妗嗘灦椤剁储寮?鍏ㄥ眬鏁版嵁澶у皬/鍑芥暟璺熻釜鏍堥《鎸囬拡/鏃堕棿鐗?
		public boolean  iExitExecLoop = false;
		public boolean  iIsActive;
		// scriptContext[0]=iGlobalDataSize;
		// scriptContext[1]=iMainFuncIndex
		// scriptContext[2]=iCurrInstr
		// scriptContext[3]=iTopIndex
		public int[]    scriptContext;
		// Header data
		// public int iGlobalDataSize; // The size of the script's global data
		public byte     iIsMainFuncPresent;   // Is _Main () present?
		// public int iMainFuncIndex; // _Main ()'s function index

		// Runtime tracking
		public boolean  iIsRunning;           // Is the script running?
		public boolean  iIsPaused;            // Is the script currently paused?
		public long     iPauseEndTime;        // If so, when should it resume?

		public long     iTimesliceDur;        // The thread's timeslice duration
		// Register file
		public int[]    _RetVal;              // The _RetVal register

		// ////////////
		public byte[][] instruction;
		// ////////////
		// public int iCurrInstr; // The instruction pointer
		// //////////////////////////////
		public byte[]   stackType;
		public int[]    stackUnionValue;
		// public int iTopIndex; // The top index
		public int      iFrameIndex;          // Index of the top of the

		public int[]    iEntryPoint;          // The entry point
		public int[]    iLocalDataSize;       // Total size of all local data
		public int[]    iStackFrameSize;      // Total size of the stack frame
		public String[] funcName;

		public String[] hostAPICalls;         // Pointer to the call array

		public String[] g_StrTable;
	}

	// public static void main(String[] a) {
	// ScriptVM svm = new ScriptVM();
	// svm.XS_Init();// 蹇呴』璋冪敤init锛堬級鏂规硶
	// try {
	// int iThreadIndex = svm.XS_LoadScript("/HELLO.XSE", ScriptVM.XS_THREAD_PRIORITY_USER);
	// svm.XS_RegisterHostAPIFunc(new HostAPIFunction(svm, null));
	// svm.XS_StartScript(iThreadIndex);
	// svm.XS_RunScripts(100);
	//
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }

	public int getHightBit(int i) {
		return ((i >> 24 & 0xff) << 8) | ((i >> 16) & 0x00ff);
	}

	public int getLowBit(int i) {
		return ((i >> 8) & 0x0000ff) << 8 | i & 0x000000ff;
	}

	public boolean isShutVM() {
		return shutVM;
	}

	// public void shutVM() {
	// this.shutVM = true;;
	// }

}
