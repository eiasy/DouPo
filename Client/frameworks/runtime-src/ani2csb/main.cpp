#include "main.h"
#include "cocos2d.h"
#include "cocostudio/CocoStudio.h"
#include "cocostudio/CCDataReaderHelper.h"
#include "flatbuffers/flatbuffers.h"
#include "flatbuffers/util.h"
#include <regex>
#include <vector>

using namespace cocostudio;
using namespace flatbuffers;
USING_NS_CC;

int APIENTRY _tWinMain(HINSTANCE hInstance,
                       HINSTANCE hPrevInstance,
                       LPTSTR    lpCmdLine,
                       int       nCmdShow) {
    UNREFERENCED_PARAMETER(hPrevInstance);
    UNREFERENCED_PARAMETER(lpCmdLine);

    char buffer[512];
    int count = WideCharToMultiByte(CP_ACP, 0, lpCmdLine, wcslen(lpCmdLine), buffer, sizeof(buffer), nullptr, nullptr);
    buffer[count] = '\0';

    std::string src = buffer;
    const std::regex pattern("[^ ]+");
    std::sregex_token_iterator iter(src.begin(), src.end(), pattern);
    const std::sregex_token_iterator end;
    std::vector<std::string> args;

    while(iter != end) {
        args.push_back(*iter);
        ++iter;
    }

    if(args.size() >= 2) {
		DataReaderHelper::serializeFlatBuffersWithJsonFile(args[0], args[1]);
    }
}
