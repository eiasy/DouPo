SET TOOL_DIR=%~dp0%
SET WORK_DIR=%TOOL_DIR%\..\
SET resourcedir=%WORK_DIR%Client\trunk\res
SET motherdir=%TOOL_DIR%\doupomother
SET dest=%TOOL_DIR%\douppo

if exist %dest% rd %dest% /s /q
if not exist %dest% md %dest%

xcopy %resourcedir%\*  %dest% /y /s /e
xcopy "%motherdir%\*.*"  %dest% /y

SET LUA_DIR=%dest%\script

if exist %LUA_DIR% rd /S /Q %LUA_DIR%
if not exist %LUA_DIR% mkdir %LUA_DIR%

xcopy %WORK_DIR%Client\trunk\src\* %LUA_DIR% /s /e /y /i
for /r %LUA_DIR% %%i in (*.lua) do Encrypt.exe %%i

start %dest%