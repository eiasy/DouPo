SET TOOL_DIR=%~dp0%
SET WORK_DIR=%TOOL_DIR%..\
SET RESOURCE=%WORK_DIR%Resource\trunk\
SET ANIMATION=%RESOURCE%ani\
SET ANI=%TOOL_DIR%\ani\

if exist %ANI% rd /S /Q %ANI%
if not exist %ANI% (
mkdir %ANI%
mkdir %ANI%animation
mkdir %ANI%image
)

xcopy %ANIMATION%* %ANI%image\ /s /h

call convert.exe %ANI%
pause...