SET TOOL_DIR=%~dp0%
SET WORK_DIR=%TOOL_DIR%..\

java -jar %TOOL_DIR%digestlister.jar SHA-1 %WORK_DIR%Client\trunk\res\ %WORK_DIR%Client\trunk\res\list %TOOL_DIR%list %WORK_DIR%Client\trunk\res\version

pause..