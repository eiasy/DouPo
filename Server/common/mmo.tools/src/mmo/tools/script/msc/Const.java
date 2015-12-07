package mmo.tools.script.msc;

import java.io.BufferedReader;
import java.util.LinkedList;

import mmo.tools.script.msc.Struct.Link;
import mmo.tools.script.msc.Struct.ScriptHeader;



/**
 * Author: cuirongzhou Date: 2007-11-26 Time: 11:59:59
 */
public class Const {

		// ---- Constants -----------------------------------------------------------------------------

		// ---- General ---------------------------------------------------------------------------

		public static final int      TRUE                      = 1;      // True

		public static final int      FALSE                     = 0;      // False

		// ---- Program ---------------------------------------------------------------------------

		public static final int      VERSION_MAJOR             = 0;      // Major version number
		public static final int      VERSION_MINOR             = 8;      // Minor version number

		// ---- Filename --------------------------------------------------------------------------

		public static final int      MAX_FILENAME_SIZE         = 2048;   // Maximum filename length

		public static final String   SOURCE_FILE_EXT           = ".XSS"; // Extension of a source code file
		public static final String   OUTPUT_FILE_EXT           = ".XASM"; // Extension of an output assembly file

		// ---- Source Code -----------------------------------------------------------------------

		public static final int      MAX_SOURCE_LINE_SIZE      = 4096;   // Maximum source line length

		public static final int      MAX_IDENT_SIZE            = 256;    // Maximum identifier size

		// ---- Priority Types --------------------------------------------------------------------

		public static final int      PRIORITY_NONE             = 0;      // A priority wasn't specified
		public static final int      PRIORITY_USER             = 1;      // User-defined priority
		public static final int      PRIORITY_LOW              = 2;      // Low priority
		public static final int      PRIORITY_MED              = 3;      // Medium priority
		public static final int      PRIORITY_HIGH             = 4;      // High priority

		public static final String   PRIORITY_LOW_KEYWORD      = "Low";  // Low priority keyword
		public static final String   PRIORITY_MED_KEYWORD      = "Med";  // Low priority keyword
		public static final String   PRIORITY_HIGH_KEYWORD     = "High"; // Low priority keyword

		// ---- Functions -------------------------------------------------------------------------

		public static final String   MAIN_FUNC_NAME            = "_Main"; // _Main ()'s name

		// ---- Register Codes---------------------------------------------------------------------

		public static final int      REG_CODE_RETVAL           = 0;      // _RetVal

		// ---- Internal Script Entities ----------------------------------------------------------

		public static final String   TEMP_VAR_0                = "_T0";  // Temporary variable 0
		public static final String   TEMP_VAR_1                = "_T1";  // Temporary variable 1

		public static String         g_pstrSourceFilename;                // [ MAX_FILENAME_SIZE ],	// Source code filename
		public static String         g_pstrOutputFilename;                // [ MAX_FILENAME_SIZE ];	// Executable filename

		public static Link           g_SourceCode;                       // Source code linked list

		// ---- Scope -----------------------------------------------------------------------------

		public static final int      SCOPE_GLOBAL              = 0;      // Global scope

		// ---- Symbol Types ----------------------------------------------------------------------

		public static final int      SYMBOL_TYPE_VAR           = 0;      // Variable
		public static final int      SYMBOL_TYPE_PARAM         = 1;      // Parameter

		// ---- I-Code Node Types -----------------------------------------------------------------

		public static final int      ICODE_NODE_INSTR          = 0;      // An I-code instruction
		public static final int      ICODE_NODE_SOURCE_LINE    = 1;      // Source-code annotation
		public static final int      ICODE_NODE_JUMP_TARGET    = 2;      // A jump target

		// ---- I-Code Instruction Opcodes --------------------------------------------------------

		public static final int      INSTR_MOV                 = 0;

		public static final int      INSTR_ADD                 = 1;
		public static final int      INSTR_SUB                 = 2;
		public static final int      INSTR_MUL                 = 3;
		public static final int      INSTR_DIV                 = 4;
		public static final int      INSTR_MOD                 = 5;
		public static final int      INSTR_EXP                 = 6;
		public static final int      INSTR_NEG                 = 7;
		public static final int      INSTR_INC                 = 8;
		public static final int      INSTR_DEC                 = 9;

		public static final int      INSTR_AND                 = 10;
		public static final int      INSTR_OR                  = 11;
		public static final int      INSTR_XOR                 = 12;
		public static final int      INSTR_NOT                 = 13;
		public static final int      INSTR_SHL                 = 14;
		public static final int      INSTR_SHR                 = 15;

		public static final int      INSTR_CONCAT              = 16;
		public static final int      INSTR_GETCHAR             = 17;
		public static final int      INSTR_SETCHAR             = 18;

		public static final int      INSTR_JMP                 = 19;
		public static final int      INSTR_JE                  = 20;
		public static final int      INSTR_JNE                 = 21;
		public static final int      INSTR_JG                  = 22;
		public static final int      INSTR_JL                  = 23;
		public static final int      INSTR_JGE                 = 24;
		public static final int      INSTR_JLE                 = 25;

		public static final int      INSTR_PUSH                = 26;
		public static final int      INSTR_POP                 = 27;

		public static final int      INSTR_CALL                = 28;
		public static final int      INSTR_RET                 = 29;
		public static final int      INSTR_CALLHOST            = 30;

		public static final int      INSTR_PAUSE               = 31;
		public static final int      INSTR_EXIT                = 32;

		// ---- Operand Types ---------------------------------------------------------------------

		public static final int      OP_TYPE_INT               = 0;      // Integer literal value
		public static final int      OP_TYPE_FLOAT             = 1;      // Floating-point literal value
		public static final int      OP_TYPE_STRING_INDEX      = 2;      // String literal value
		public static final int      OP_TYPE_VAR               = 3;      // Variable
		public static final int      OP_TYPE_ARRAY_INDEX_ABS   = 4;      // Array with absolute index
		public static final int      OP_TYPE_ARRAY_INDEX_VAR   = 5;      // Array with relative index
		public static final int      OP_TYPE_JUMP_TARGET_INDEX = 6;      // Jump target index
		public static final int      OP_TYPE_FUNC_INDEX        = 7;      // Function index
		public static final int      OP_TYPE_REG               = 9;      // Register

		// ---- Script ----------------------------------------------------------------------------

		public static ScriptHeader   g_ScriptHeader;                     // Script header data
		// ---- I-Code Stream ---------------------------------------------------------------------

		public static LinkedList     g_ICodeStream;                      // I-code stream

		// ---- Function Table --------------------------------------------------------------------

		public static LinkedList     g_FuncTable;                        // The function table

		// ---- Symbol Table ----------------------------------------------------------------------

		public static LinkedList     g_SymbolTable;                      // The symbol table

		// ---- String Table ----------------------------------------------------------------------

		public static Link           g_StringTable;                      // The string table

		public static BufferedReader g_pSourceFile;

		public static int            g_iTempVar_0_SymbolIndex;             // Temporary variable symbol indices
		public static int            g_iTempVar_1_SymbolIndex;

		// ---- Data Structures -----------------------------------------------------------------------

		//    // ---- Script ----------------------------------------------------------------------------
		//
		//    public class ScriptHeader
		//    {
		//        public int iStackSize;                             // Requested stack size
		//
		//        public int iIsMainFuncPresent;                     // Is _Main () present?
		//        public int iMainFuncIndex;                            // _Main ()'s function index
		//
		//        public int iPriorityType;                          // The thread priority type
		//        public int iUserPriority;                          // The user-defined priority (if any)
		//    }

}
