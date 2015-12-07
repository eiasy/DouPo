package mmo.tools.script.msc.Struct;

/**
 * Author: cuirongzhou
 * Date: 2007-12-4
 * Time: 11:58:11
 */
public class ScriptHeader
{
    public int iStackSize;                             // Requested stack size

    public int iIsMainFuncPresent;                     // Is _Main () present?
    public int iMainFuncIndex;                            // _Main ()'s function index

    public int iPriorityType;                          // The thread priority type
    public int iUserPriority;                          // The user-defined priority (if any)
}
