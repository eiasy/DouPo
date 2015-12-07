call "%VS110COMNTOOLS%vsvars32.bat"

cd Encrypt
del *.obj >nul 2>nul
del *.exe >nul 2>nul
cl -I. -Izlib -I../../Client/trunk/frameworks/runtime-src/Classes ../../Client/trunk/frameworks/runtime-src/Classes/Encrypt.cpp zlib/*.c *.cpp
pause..