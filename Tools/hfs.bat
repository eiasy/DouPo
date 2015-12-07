@echo off
pushd %~dp0
title %0
echo Ìí¼Óhfs.exe µ½ÓÒ¼ü²Ëµ¥"ÍøÒ³ÏÂÔØ(Q)"
reg add HKCR\*\shell\3Http /ve /d ÍøÒ³ÏÂÔØ^(^&Q^) /f>NUL
reg add HKCR\*\shell\3Http\Command /ve /d "\"%cd%\hfs.exe\" \"%%1\"" /f>NUL

reg add HKCR\Folder\shell\2Http /ve /d ÍøÒ³ÏÂÔØ^(^&Q^) /f>NUL
reg add HKCR\Folder\shell\2Http\Command /ve /d "\"%cd%\hfs.exe\" \"%%1\"" /f>NUL

popd