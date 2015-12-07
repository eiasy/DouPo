#!/bin/bash
shPath="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
shFile="$shPath/$( basename "${BASH_SOURCE[0]}" )"

BAKPWD=$(pwd)
cd ${shPath}

echo ${shPath}

echo 制作Encrypt程序
cd Encrypt
classdir=../../Client/trunk/frameworks/runtime-src/Classes
gcc -c -I. -Izlib -I${classdir} zlib/*.c
g++ -c -I. -Izlib -I${classdir} ${classdir}/Encrypt.cpp *.cpp
g++ -o encrypt *.o
cd ..
echo 制作Encrypt程序完成
echo 拷贝资源并加密
find ../Client/trunk/src/ -name "*" -exec touch -cm {} \;
rsync -ur --delete --exclude=".DS_Store" ../Client/trunk/src/ ../Client/trunk/res/script/
find ../Client/trunk/res/script/ -name "*.lua" -exec ./Encrypt/encrypt {} \;
echo 拷贝资源并加密完成

echo 生成list文件
java -jar ./digestlister.jar SHA-1 ../Client/trunk/res/ ../Client/trunk/res/list
echo 生成list文件完成

cd ${BAKPWD}
