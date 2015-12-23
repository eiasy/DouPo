SET TOOL_DIR=%~dp0%
SET SRC_DIR=%TOOL_DIR%..\Client\trunk\res\
SET DST_DIR=%TOOL_DIR%..\..\res\
SET DST_DIR1=%TOOL_DIR%..\..\res-ios\
SET DST_DIR2=%TOOL_DIR%..\..\res-qq\
robocopy %SRC_DIR% %DST_DIR% /XD .svn /MIR
robocopy %SRC_DIR% %DST_DIR1% /XD .svn /MIR
robocopy %SRC_DIR% %DST_DIR2% /XD .svn /MIR

MD %DST_DIR%unencrypted
MD %DST_DIR1%unencrypted
MD %DST_DIR2%unencrypted

FOR %%i IN (fightbuffer.lua fightcalc.lua fightercalc.lua fightpyros.lua fightyokes.lua skillmanager.lua) DO (
	COPY /Y %SRC_DIR%..\src\%%i %DST_DIR%unencrypted\
	COPY /Y %SRC_DIR%..\src\%%i %DST_DIR1%unencrypted\
	COPY /Y %SRC_DIR%..\src\%%i %DST_DIR2%unencrypted\
)
