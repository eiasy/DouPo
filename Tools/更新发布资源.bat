SET TOOL_DIR=%~dp0%
SET SRC_DIR=%TOOL_DIR%..\Client\trunk\res\
SET DST_DIR=%TOOL_DIR%..\..\res\
SET DST_DIR1=%TOOL_DIR%..\..\res-ios\
SET DST_DIR2=%TOOL_DIR%..\..\res-qq\
robocopy %SRC_DIR% %DST_DIR% /XD .svn /MIR
robocopy %SRC_DIR% %DST_DIR1% /XD .svn /MIR
robocopy %SRC_DIR% %DST_DIR2% /XD .svn /MIR