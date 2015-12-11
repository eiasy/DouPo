SET TOOL_DIR=%~dp0%
SET WORK_DIR=%TOOL_DIR%..\Client\trunk\
@taskkill /f /im DouPo.exe
@pushd %WORK_DIR%\res
@start %WORK_DIR%\frameworks\runtime-src\proj.win32\Debug.win32\DouPo.exe
@popd