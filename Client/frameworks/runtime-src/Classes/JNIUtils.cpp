#include "JNIUtils.h"
#include "crashhelper/TestinCrashHelper.h"
#include "cocos2d.h"
#include <regex>

USING_NS_CC;

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include "platform/android/jni/JniHelper.h"
#include <android/log.h>
#define  LOG_TAG    "Update"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG, ##__VA_ARGS__)
#else
#define LOGD(...)
#endif

#if CC_TARGET_PLATFORM != CC_PLATFORM_IOS
#include "YVSDK/YVSDK.h"
using namespace  YVSDK;
#endif

void JNIUtils::browserUrl(const char *url) {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    JniMethodInfo methodInfo;
    JniHelper::getStaticMethodInfo(methodInfo, "org.cocos2dx.lua.AppActivity", "browserUrl", "(Ljava/lang/String;)V");
    jstring stringArg = methodInfo.env->NewStringUTF(const_cast<char *>(url));
    methodInfo.env->CallStaticVoidMethod(methodInfo.classID, methodInfo.methodID, stringArg);
    methodInfo.env->DeleteLocalRef(stringArg);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);
#endif
}

void JNIUtils::exitGame() {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    JniMethodInfo methodInfo;
    JniHelper::getStaticMethodInfo(methodInfo, "org.cocos2dx.lua.AppActivity", "exitGame", "()V");
    methodInfo.env->CallStaticVoidMethod(methodInfo.classID, methodInfo.methodID);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);
#else
    exit(0);
#endif
}

void JNIUtils::startDownloadApk(const char *url) {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    JniMethodInfo methodInfo;
    JniHelper::getStaticMethodInfo(methodInfo, "org.cocos2dx.lua.AppActivity", "startDownloadApk", "(Ljava/lang/String;)V");
    jstring stringArg = methodInfo.env->NewStringUTF(const_cast<char *>(url));
    methodInfo.env->CallStaticVoidMethod(methodInfo.classID, methodInfo.methodID, stringArg);
    methodInfo.env->DeleteLocalRef(stringArg);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);
#endif
}

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
static string jstring2string(JNIEnv *env, const jstring& str) {
    const char *arr = env->GetStringUTFChars(str, 0);
    string ret = arr;
    env->ReleaseStringUTFChars(str, arr);
    return ret;
}

string JNIUtils::getChannelId() {
    JniMethodInfo methodInfo;
    JniHelper::getStaticMethodInfo(methodInfo, "org.cocos2dx.lua.SDK", "sdkGetChannel", "()Ljava/lang/String;");
    jstring result = (jstring) methodInfo.env->CallStaticObjectMethod(methodInfo.classID, methodInfo.methodID);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);

    string channelId = jstring2string(methodInfo.env, result);

    if(channelId == "y2game") return "yiyou";

    return channelId;
}

unordered_map<string, string> JNIUtils::getDeviceInfo() {
    JniMethodInfo methodInfo;
    JniHelper::getStaticMethodInfo(methodInfo, "org.cocos2dx.lua.SDK", "sdkGetDeviceInfo", "()Ljava/lang/String;");
    jstring result = (jstring) methodInfo.env->CallStaticObjectMethod(methodInfo.classID, methodInfo.methodID);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);

    string str = jstring2string(methodInfo.env, result);

    const regex pattern("[^#]+");

    sregex_token_iterator iter(str.begin(), str.end(), pattern);
    const sregex_token_iterator end;
    unordered_map<string, string> map;

    while(iter != end) {
        string kv = *iter;
        int index = kv.find('=');
        string key = kv.substr(0, index);
        string value = kv.substr(index + 1, kv.size() - index - 1);
        map.insert(make_pair(key, value));
        ++iter;
    }

    return map;
}

void JNIUtils::showGameNotice(const char *url) {
    JniMethodInfo methodInfo;
    JniHelper::getStaticMethodInfo(methodInfo, "org.cocos2dx.lua.AppActivity", "showGameNotice", "(Ljava/lang/String;)V");
    jstring stringArg = methodInfo.env->NewStringUTF(const_cast<char *>(url));
    methodInfo.env->CallStaticVoidMethod(methodInfo.classID, methodInfo.methodID, stringArg);
    methodInfo.env->DeleteLocalRef(stringArg);
    methodInfo.env->DeleteLocalRef(methodInfo.classID);
}

#elif (CC_TARGET_PLATFORM == CC_PLATFORM_WIN32)

string JNIUtils::getChannelId() {
    return "dev";
}

unordered_map<string, string> JNIUtils::getDeviceInfo() {
    unordered_map<string, string> map;
    map["devOs"] = "windows";
    auto frameSize = Director::getInstance()->getOpenGLView()->getFrameSize();
    char buf[64];
    sprintf(buf, "%d", (int) frameSize.width);
    map["screen_width"] = buf;
    sprintf(buf, "%d", (int) frameSize.height);
    map["screen_height"] = buf;
    return map;
}

void JNIUtils::showGameNotice(const char *url) {}

#endif

int JNIUtils::getProductId() {
#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
#if ANYSDK
    return 2;
#else
    return 3;
#endif
#elif (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    auto map = getDeviceInfo();
    return map["packageName"] == "com.tencent.tmgp.y2doupo" ? 5 : (map["packageName"] == "com.doupo.zhangyue.zy" ? 4 : 2);
#else
    return 1;
#endif
}

void JNIUtils::logAndroid(const string& str) {
    LOGD("%s", str.c_str());
}

void JNIUtils::setUserInfo(const string& str) {
#if CC_TARGET_PLATFORM != CC_PLATFORM_WIN32
    TestinCrashHelper::setUserInfo(str.c_str());
#endif
}

#if CC_TARGET_PLATFORM != CC_PLATFORM_IOS
void JNIUtils::yvSetConfigTest(bool test) {
	YVPlatform::getSingletonPtr()->setConfig(ConfigIsTest, test);
}

void JNIUtils::yvSetConfigServerId(const string& serverId) {
	YVPlatform::getSingletonPtr()->setConfig(ConfigServerId, serverId.c_str());
}

void JNIUtils::yvSetConfigTempPath(const string& tempPath) {
	YVPlatform::getSingletonPtr()->setConfig(ConfigTempPath, tempPath.c_str());
}

void JNIUtils::yvCpLogin(const string& ttStr) {
	YVPlatform::getSingletonPtr()->cpLogin(ttStr);
}

bool JNIUtils::yvCpLogout() {
	return YVPlatform::getSingletonPtr()->cpLogout();
}

void JNIUtils::yvStartRecord() {
	YVPlatform::getSingletonPtr()->startRecord();
}

void JNIUtils::yvStopRecord() {
	YVPlatform::getSingletonPtr()->stopRecord();
}

void JNIUtils::yvPlayRecordByLocalPath(string& path) {
	YVPlatform::getSingletonPtr()->playRecord(YVPlatform::getSingletonPtr()->getYVPathByLocal(path));
}

void JNIUtils::yvPlayRecordByUrl(string& url) {
	auto yvPath = YVPlatform::getSingletonPtr()->getYVPathByRand("amr");
	yvPath->setUrlPath(url);
	YVPlatform::getSingletonPtr()->playRecord(yvPath);
}

void JNIUtils::yvStopPlay() {
	YVPlatform::getSingletonPtr()->stopPlay();
}

void JNIUtils::yvDownLoadFile(string& url) {
	auto yvPath = YVPlatform::getSingletonPtr()->getYVPathByRand("amr");
	yvPath->setUrlPath(url);
	YVPlatform::getSingletonPtr()->downLoadFile(yvPath);
}

bool JNIUtils::yvIsPlaying() {
	return YVPlatform::getSingletonPtr()->isPlaying();
}

long long JNIUtils::yvPlayingId() {
	return YVPlatform::getSingletonPtr()->PlayingId();
}

#endif