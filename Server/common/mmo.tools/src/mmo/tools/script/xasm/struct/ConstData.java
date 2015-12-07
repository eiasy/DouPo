/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 12:17:45
 */
package mmo.tools.script.xasm.struct;

public class ConstData
{
    // ---- General ---------------------------------------------------------------------------

    // ---- Constants -----------------------------------------------------------------------------

//        #ifndef TRUE
//            #define TRUE                    1           // True
//        #endif
//
//        #ifndef FALSE
    //            #define FALSE                   0           // False
    //        #endif
    public static final byte TRUE = 1;
    public static final byte FALSE = 0;
    // ---- Filename --------------------------------------------------------------------------

    public static final int MAX_FILENAME_SIZE = 2048;        // Maximum filename length
    public static final String SOURCE_FILE_EXT = ".XASM";     // Extension of a source code file
    public static final String EXEC_FILE_EXT = ".XSE";      // Extension of an executable code file

    // ---- Source Code -----------------------------------------------------------------------

    public static final int MAX_SOURCE_CODE_SIZE = 65536;      // Maximum number of lines in source
    // code
    public static final int MAX_SOURCE_LINE_SIZE = 4096;       // Maximum source line length

    // ---- ,XSE Header -----------------------------------------------------------------------

    public static final String XSE_ID_STRING = "XSE0";        // Written to the file to state it's
    // validity
    public static final int VERSION_MAJOR = 0;          // Major version number
    public static final int VERSION_MINOR = 8;          // Minor version number

    // ---- Lexer -----------------------------------------------------------------------------

    public static final int MAX_LEXEME_SIZE = 256;         // Maximum lexeme size

    public static final int LEX_STATE_NO_STRING = 0;          // Lexemes are scanned as normal
    public static final int LEX_STATE_IN_STRING = 1;           // Lexemes are scanned as strings
    public static final int LEX_STATE_END_STRING = 2;           // Lexemes are scanned as normal, and the
    // next state is LEXEME_STATE_NOSTRING

    public static final int TOKEN_TYPE_INT = 0;           // An integer literal
    public static final int TOKEN_TYPE_FLOAT = 1;           // An floating-point literal
    public static final int TOKEN_TYPE_STRING = 2;           // An string literal
    public static final int TOKEN_TYPE_QUOTE = 3;           // A double-quote
    public static final int TOKEN_TYPE_IDENT = 4;           // An identifier
    public static final int TOKEN_TYPE_COLON = 5;           // A colon
    public static final int TOKEN_TYPE_OPEN_BRACKET = 6;           // An openening bracket
    public static final int TOKEN_TYPE_CLOSE_BRACKET = 7;           // An closing bracket
    public static final int TOKEN_TYPE_COMMA = 8;           // A comma
    public static final int TOKEN_TYPE_OPEN_BRACE = 9;           // An openening curly brace
    public static final int TOKEN_TYPE_CLOSE_BRACE = 10;          // An closing curly brace
    public static final int TOKEN_TYPE_NEWLINE = 11;          // A newline

    public static final int TOKEN_TYPE_INSTR = 12;            // An instruction
    public static final int TOKEN_TYPE_SETSTACKSIZE = 13;      // The SetStackSize directive
    public static final int TOKEN_TYPE_SETPRIORITY = 14;        // The SetPriority directive
    public static final int TOKEN_TYPE_VAR = 15;        // The Var/Var [] directives
    public static final int TOKEN_TYPE_FUNC = 16;         // The Func directives
    public static final int TOKEN_TYPE_PARAM = 17;        // The Param directives
    public static final int TOKEN_TYPE_REG_RETVAL = 18;         // The _RetVal directives

    public static final int TOKEN_TYPE_INVALID = 19;         // Error code for invalid tokens
    public static final int END_OF_TOKEN_STREAM = 20;         // The end of the stream has been
    // reached

    public static final int MAX_IDENT_SIZE = 256;        // Maximum identifier size

    // ---- Instruction Lookup Table ----------------------------------------------------------

    public static final int MAX_INSTR_LOOKUP_COUNT = 256;         // The maximum number of instructions
    // the lookup table will hold
    public static final int MAX_INSTR_MNEMONIC_SIZE = 16;          // Maximum size of an instruction
    // mnemonic's string

    // ---- Instruction Opcodes -----------------------------------------------------------

    public static final int INSTR_MOV = 0;

    public static final int INSTR_ADD = 1;
    public static final int INSTR_SUB = 2;
    public static final int INSTR_MUL = 3;
    public static final int INSTR_DIV = 4;
    public static final int INSTR_MOD = 5;
    public static final int INSTR_EXP = 6;
    public static final int INSTR_NEG = 7;
    public static final int INSTR_INC = 8;
    public static final int INSTR_DEC = 9;

    public static final int INSTR_AND = 10;
    public static final int INSTR_OR = 11;
    public static final int INSTR_XOR = 12;
    public static final int INSTR_NOT = 13;
    public static final int INSTR_SHL = 14;
    public static final int INSTR_SHR = 15;

    public static final int INSTR_CONCAT = 16;
    public static final int INSTR_GETCHAR = 17;
    public static final int INSTR_SETCHAR = 18;

    public static final int INSTR_JMP = 19;
    public static final int INSTR_JE = 20;
    public static final int INSTR_JNE = 21;
    public static final int INSTR_JG = 22;
    public static final int INSTR_JL = 23;
    public static final int INSTR_JGE = 24;
    public static final int INSTR_JLE = 25;

    public static final int INSTR_PUSH = 26;
    public static final int INSTR_POP = 27;

    public static final int INSTR_CALL = 28;
    public static final int INSTR_RET = 29;
    public static final int INSTR_CALLHOST = 30;

    public static final int INSTR_PAUSE = 31;
    public static final int INSTR_EXIT = 32;

    // ---- Operand Type Bitfield Flags ---------------------------------------------------

    // The following constants are used as flags into an operand type bit field, hence
    // their values being increasing powers of 2.

    public static final int OP_FLAG_TYPE_INT = 1;           // Integer literal value
    public static final int OP_FLAG_TYPE_FLOAT = 2;           // Floating-point literal value
    public static final int OP_FLAG_TYPE_STRING = 4;           // Integer literal value
    public static final int OP_FLAG_TYPE_MEM_REF = 8;           // Memory reference (variable or array
    // index, both absolute and relative)
    public static final int OP_FLAG_TYPE_LINE_LABEL = 16;          // Line label (used for jumps)
    public static final int OP_FLAG_TYPE_FUNC_NAME = 32;          // Function table index (used for Call)
    public static final int OP_FLAG_TYPE_HOST_API_CALL = 64;     // Host API Call table index (used for
    // CallHost)
    public static final int OP_FLAG_TYPE_REG = 128;         // Register

    // ---- Assembled Instruction Stream ------------------------------------------------------

    public static final int OP_TYPE_INT = 0;           // Integer literal value
    public static final int OP_TYPE_FLOAT = 1;           // Floating-point literal value
    public static final int OP_TYPE_STRING_INDEX = 2;           // String literal value
    public static final int OP_TYPE_ABS_STACK_INDEX = 3;           // Absolute array index
    public static final int OP_TYPE_REL_STACK_INDEX = 4;           // Relative array index
    public static final int OP_TYPE_INSTR_INDEX = 5;           // Instruction index
    public static final int OP_TYPE_FUNC_INDEX = 6;           // Function index
    public static final int OP_TYPE_HOST_API_CALL_INDEX = 7;           // Host API call index
    public static final int OP_TYPE_REG = 8;           // Register

    // ---- Priority Types --------------------------------------------------------------------

    public static final int PRIORITY_USER = 0;         // User-defined priority
    public static final int PRIORITY_LOW = 1;          // Low priority
    public static final int PRIORITY_MED = 2;         // Medium priority
    public static final int PRIORITY_HIGH = 3;         // High priority

    public static final String PRIORITY_LOW_KEYWORD = "LOW";       // Low priority keyword
    public static final String PRIORITY_MED_KEYWORD = "MED";      // Low priority keyword
    public static final String PRIORITY_HIGH_KEYWORD = "HIGH";     // Low priority keyword
    // ---- Functions -------------------------------------------------------------------------

    public static final String MAIN_FUNC_NAME = "_MAIN";        // _Main ()'s name

    // ---- Error Strings ---------------------------------------------------------------------

    // The following macros are used to represent assembly-time error strings

    public static final String ERROR_MSSG_INVALID_INPUT = "Invalid input";

    public static final String ERROR_MSSG_LOCAL_SETSTACKSIZE = "SetStackSize can only appear in the global scope";

    public static final String ERROR_MSSG_INVALID_STACK_SIZE = "Invalid stack size";

    public static final String ERROR_MSSG_MULTIPLE_SETSTACKSIZES = "Multiple instances of SetStackSize illegal";

    public static final String ERROR_MSSG_LOCAL_SETPRIORITY = "SetPriority can only appear in the global scope";

    public static final String ERROR_MSSG_INVALID_PRIORITY = "Invalid priority";

    public static final String ERROR_MSSG_MULTIPLE_SETPRIORITIES = "Multiple instances of SetPriority illegal";

    public static final String ERROR_MSSG_IDENT_EXPECTED = "Identifier expected";

    public static final String ERROR_MSSG_INVALID_ARRAY_SIZE = "Invalid array size";

    public static final String ERROR_MSSG_IDENT_REDEFINITION = "Identifier redefinition";

    public static final String ERROR_MSSG_UNDEFINED_IDENT = "Undefined identifier";

    public static final String ERROR_MSSG_NESTED_FUNC = "Nested functions illegal";

    public static final String ERROR_MSSG_FUNC_REDEFINITION = "Function redefinition";

    public static final String ERROR_MSSG_UNDEFINED_FUNC = "Undefined function";

    public static final String ERROR_MSSG_GLOBAL_PARAM = "Parameters can only appear inside functions";

    public static final String ERROR_MSSG_MAIN_PARAM = "_Main () function cannot accept parameters";

    public static final String ERROR_MSSG_GLOBAL_LINE_LABEL = "Line labels can only appear inside functions";

    public static final String ERROR_MSSG_LINE_LABEL_REDEFINITION = "Line label redefinition";

    public static final String ERROR_MSSG_UNDEFINED_LINE_LABEL = "Undefined line label";

    public static final String ERROR_MSSG_GLOBAL_INSTR = "Instructions can only appear inside functions";

    public static final String ERROR_MSSG_INVALID_INSTR = "Invalid instruction";

    public static final String ERROR_MSSG_INVALID_OP = "Invalid operand";

    public static final String ERROR_MSSG_INVALID_STRING = "Invalid string";

    public static final String ERROR_MSSG_INVALID_ARRAY_NOT_INDEXED = "Arrays must be indexed";

    public static final String ERROR_MSSG_INVALID_ARRAY = "Invalid array";

    public static final String ERROR_MSSG_INVALID_ARRAY_INDEX = "Invalid array index";

}
