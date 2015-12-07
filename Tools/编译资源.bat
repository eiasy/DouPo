SET TOOL_DIR=%~dp0%
SET WORK_DIR=%TOOL_DIR%..\
SET RESOURCE=%WORK_DIR%Resource\trunk\
SET ANIMATION=%TOOL_DIR%ani\image\
SET UI=%RESOURCE%ui\cocosstudio\

SET SERVER_RES_DIR=%WORK_DIR%Client\trunk\res\
SET BINARY_DIR=%WORK_DIR%Tools\binary\
SET CACHE_BINARY_DIR=%WORK_DIR%Tools\cachebinary\

if exist %BINARY_DIR% rd /S /Q %BINARY_DIR%
if not exist %BINARY_DIR% mkdir %BINARY_DIR%

xcopy %ANIMATION%* %BINARY_DIR%ani\ /s /h

call convert.exe  %BINARY_DIR% %CACHE_BINARY_DIR% %BINARY_DIR%/ani/ %UI%

move %SERVER_RES_DIR%/version %BINARY_DIR%/version
move %SERVER_RES_DIR%/list %BINARY_DIR%/list

if exist %SERVER_RES_DIR% rd /S /Q %SERVER_RES_DIR%
if not exist %SERVER_RES_DIR% mkdir %SERVER_RES_DIR%

xcopy %RESOURCE%data\* %SERVER_RES_DIR%data\ /s /e /y /i
xcopy %RESOURCE%particle\* %SERVER_RES_DIR%particle\ /s /e /y /i
xcopy %RESOURCE%ui\cocosstudio\image\* %SERVER_RES_DIR%image\ /s /e /y /i
xcopy %RESOURCE%sound\* %SERVER_RES_DIR%sound\ /s /e /y /i
xcopy %RESOURCE%ui\cocosstudio\ui\*.* %SERVER_RES_DIR%ui\ /s /h

del %SERVER_RES_DIR%cache.properties

xcopy %BINARY_DIR%* %SERVER_RES_DIR% /s /e /y /i

call pvrcczgetter.exe %SERVER_RES_DIR% ./cachepvrpng/ ./jpgcompress.txt ./jpgdir.txt

xcopy %RESOURCE%default\* %SERVER_RES_DIR% /s /e /y /i

rd /S /Q %BINARY_DIR%
if exist md.png del md.png
if exist pvr.png del pvr.png
if exist pvr-fs8.png del pvr-fs8.png

pause..