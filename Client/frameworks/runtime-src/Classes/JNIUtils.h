#ifndef __JNIUTILS_H__
#define __JNIUTILS_H__

#include <string>
#include <unordered_map>
#include "cocos2d.h"

using namespace std;

#define PROGRAM_VER "1.5.0"

class JNIUtils
{
public:
    static void browserUrl(const char *url);
    static void showGameNotice(const char *url);
    static void exitGame();
    static void startDownloadApk(const char *url);
    static string getChannelId();
    static int getProductId();
    static unordered_map<string, string> getDeviceInfo();
    static void logAndroid(const string& str);
	static void setUserInfo(const string& str);

#if CC_TARGET_PLATFORM != CC_PLATFORM_IOS
	static void yvSetConfigTest(bool test);
	static void yvSetConfigServerId(const string& serverId);
	static void yvSetConfigTempPath(const string& tempPath);
	static void yvCpLogin(const string& ttStr);
	static bool yvCpLogout();
	static void yvStartRecord();
	static void yvStopRecord();
	static void yvPlayRecordByLocalPath(string& path);
	static void yvPlayRecordByUrl(string& url);
	static void yvStopPlay();
	static void yvDownLoadFile(string& url);
	static bool yvIsPlaying();
	static long long yvPlayingId();
#endif
};

#endif // !__JNIUTILS_H__
