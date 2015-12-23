SET TOOL_DIR=%~dp0%
SET WORK_DIR=%TOOL_DIR%..\

SET LUA_DIR=%WORK_DIR%Client\trunk\res\script\

if exist %LUA_DIR% rd /S /Q %LUA_DIR%
if not exist %LUA_DIR% mkdir %LUA_DIR%

xcopy %WORK_DIR%Client\trunk\src\* %LUA_DIR% /s /e /y /i
for /r %LUA_DIR% %%i in (*.lua) do Encrypt.exe %%i

java -jar %TOOL_DIR%digestlister.jar SHA-1 %WORK_DIR%Client\trunk\res\ %WORK_DIR%Client\trunk\res\list %TOOL_DIR%list %WORK_DIR%Client\trunk\res\version ani\splash\

pause..