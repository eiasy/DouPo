package mmo.tools.script.xasm.struct;

/**
 * Author: cuirongzhou
 * Date: 2007-10-29
 * Time: 15:58:02
 */
public class ScriptHeader
{
    public int iStackSize;                             // Requested stack size
    public int iGlobalDataSize;                        // The size of the script's global data
    public int iIsMainFuncPresent;                     // Is _Main () present?
    public int iMainFuncIndex;                            // _Main ()'s function index

    public int iPriorityType;                          // The thread priority type
    public int iUserPriority;                          // The user-defined priority (if any);


}
