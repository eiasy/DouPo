
#include "Update.h"
#include <string.h>
#include "CCLuaEngine.h"
#include "EncryptLuaLoader.h"
#include "Encrypt.h"
#include "cocostudio/CocoStudio.h"
#include "cocostudio/DictionaryHelper.h"
#include "crashhelper/lua/TestinLuaExceptionHandler.h"
#include "JNIUtils.h"

#define FLAG_FILE       "install"   // 标记文件用于判断是否为新安装的客户端
#define VERSION_FILE    "version"   // 版本号记录文件
#define LIST_FILE       "list"      // 列表文件
#define CONFIG_FILE     "config"    // 配置文件
#define RES_DIR         "res/"      // 外部资源目录
#define RES_PATH_MAX    256         // 资源文件路径最大长度(不包含'\0')
#define HASH_STRING_MAX 256         // HASH值字符串长度(不包含'\0')

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include "platform/android/jni/JniHelper.h"
#include <android/log.h>
#define  LOG_TAG    "Update"
#define  LOGD(...)  __android_log_print(ANDROID_LOG_DEBUG,LOG_TAG, ##__VA_ARGS__)
#else
#define LOGD(...)
#endif

USING_NS_CC;
using namespace std;
using namespace cocos2d::ui;
using namespace cocos2d::network;
using namespace cocostudio;

#if (CC_TARGET_PLATFORM == CC_PLATFORM_WIN32)
#include <io.h>
#include <direct.h>
#include <sys/stat.h>

#define PATH_LENGTH 256 // 定义最大路径长度

// path不要以'/'结尾
static void removePath(const char *path)
{
    if(path == NULL)
    {
        return;
    }

    struct stat st;

    if(stat(path, &st))
    {
        return;
    }

    if(S_IFDIR & st.st_mode)
    {
        char subPath[PATH_LENGTH];
        strcpy(subPath, path);
        strcat(subPath, "/*");
        intptr_t handle;
        _finddata_t findData;
        handle = _findfirst(subPath, &findData);

        if(handle == -1)
        {
            return;
        }

        do
        {
            if(strcmp(findData.name, ".") == 0 || strcmp(findData.name, "..") == 0)
                continue;

            strcpy(subPath, path);
            strcat(subPath, "/");
            strcat(subPath, findData.name);
            removePath(subPath);
        }
        while(_findnext(handle, &findData) == 0);

        _findclose(handle);
        _rmdir(path);
    }
    else if(S_IFREG & st.st_mode)
    {
        remove(path);
    }
}

#elif (CC_TARGET_PLATFORM == CC_PLATFORM_IOS) || (CC_TARGET_PLATFORM == CC_PLATFORM_MAC) || (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)

#include <unistd.h>
#include <dirent.h>
#include <sys/stat.h>

#define _mkdir(s)   mkdir(s,S_IRWXU)

static void removePath(const char *path)
{
    if(path == NULL)
    {
        return;
    }

    struct stat st;

    if(stat(path, &st) != 0)
    {
        return;
    }

    if(S_ISDIR(st.st_mode))
    {
        DIR *dir;

        if((dir = opendir(path)) == NULL)
        {
            return;
        }

        dirent *ent;

        while((ent = readdir(dir)))
        {
            if(strcmp(ent->d_name, ".") == 0 || strcmp(ent->d_name, "..") == 0)
                continue;

            char subPath[256];
            strcpy(subPath, path);
            strcat(subPath, "/");
            strcat(subPath, ent->d_name);
            removePath(subPath);
        }

        closedir(dir);
        rmdir(path);
    }
    else if(S_ISREG(st.st_mode))
    {
        remove(path);
    }
}

#endif

static unsigned char toHex(unsigned char x)
{
    return  x > 9 ? x + 55 : x + 48;
}

static unsigned char fromHex(unsigned char x)
{
    unsigned char y;

    if(x >= 'A' && x <= 'Z') y = x - 'A' + 10;
    else if(x >= 'a' && x <= 'z') y = x - 'a' + 10;
    else if(x >= '0' && x <= '9') y = x - '0';
    else assert(0);

    return y;
}

static std::string urlEncode(const std::string& str)
{
    std::string strTemp = "";
    size_t length = str.length();

    for(size_t i = 0; i < length; i++)
    {
        if(isalnum((unsigned char)str[i]) ||
                (str[i] == '-') ||
                (str[i] == '_') ||
                (str[i] == '.') ||
                (str[i] == '~'))
            strTemp += str[i];
        else if(str[i] == ' ')
            strTemp += "+";
        else
        {
            strTemp += '%';
            strTemp += toHex((unsigned char)str[i] >> 4);
            strTemp += toHex((unsigned char)str[i] % 16);
        }
    }

    return strTemp;
}

static std::string urlDecode(const std::string& str)
{
    std::string strTemp = "";
    size_t length = str.length();

    for(size_t i = 0; i < length; i++)
    {
        if(str[i] == '+') strTemp += ' ';
        else if(str[i] == '%')
        {
            assert(i + 2 < length);
            unsigned char high = fromHex((unsigned char)str[++i]);
            unsigned char low = fromHex((unsigned char)str[++i]);
            strTemp += high * 16 + low;
        }
        else strTemp += str[i];
    }

    return strTemp;
}

static Update *updatePtr = nullptr;

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include <jni.h>

extern "C" {
    JNIEXPORT void JNICALL Java_org_cocos2dx_lua_AppActivity_setDownloadApkProgress
    (JNIEnv *env, jclass clazz, jint errorCode, jint fileSize, jint completeSize)
    {
        updatePtr->downloadApk(errorCode, fileSize, completeSize);
    }
}
#endif

Update *Update::create()
{
    Update *pUpdate = new Update();

    if(pUpdate && pUpdate->init())
    {
        pUpdate->autorelease();
        return pUpdate;
    }

    CC_SAFE_DELETE(pUpdate);
    return NULL;
}

bool Update::init(void)
{
    _internalVersionFile = FileUtils::getInstance()->fullPathForFilename(VERSION_FILE);
    string internalPath = _internalVersionFile.substr(0, _internalVersionFile.find_last_of("/") + 1);
    _internalListFile = internalPath + LIST_FILE;
    string externalPath = FileUtils::getInstance()->getWritablePath();
    _externalFlagFile = externalPath + FLAG_FILE;
    _externalVersionFile = externalPath + VERSION_FILE;
    _externalListFile = externalPath + LIST_FILE;
    _externalResourceDir = externalPath + RES_DIR;

    checkFlag();

    loadConfig();

    setupUI();

    updatePtr = this;

    return true;
}

void Update::checkFlag(void)
{
    Data internalData = FileUtils::getInstance()->getDataFromFile(_internalVersionFile);    // !!! internalVersionFile

    if(FileUtils::getInstance()->isFileExist(_externalFlagFile))    // !!! externalVersionFlag
    {
        Data externalData = FileUtils::getInstance()->getDataFromFile(_externalFlagFile);   // !!! externalVersionFlag

        if(internalData.getSize() == externalData.getSize()
                && memcmp(internalData.getBytes(), externalData.getBytes(), externalData.getSize()) == 0)
        {
            return;
        }
        else
        {
            removePath(_externalFlagFile.c_str());
            removePath(_externalVersionFile.c_str());
            removePath(_externalListFile.c_str());
            removePath(_externalResourceDir.c_str());
        }
    }

    // write flag
    FILE *file = fopen(_externalFlagFile.c_str(), "wb");
    fwrite(internalData.getBytes(), internalData.getSize(), 1, file);
    fclose(file);
}

void Update::loadConfig(void)
{
#if (CC_TARGET_PLATFORM == CC_PLATFORM_WIN32)
    _updateEnable = false;
    _updateSkippable = true;
#else
    _updateEnable = true;
    _updateSkippable = false;
#endif
    string configData = FileUtils::getInstance()->getStringFromFile(CONFIG_FILE);

    if(configData.length())
    {
        char *data = (char *)configData.c_str();
        char  var[128];
        char  val[1024];

        while(*data)
        {
            int nRet = sscanf(data, "%[^=\r\n]=%[^\r\n]", var, val);

            if(nRet == 2)
            {
                if(strcmp(var, "enable") == 0)
                {
                    _updateEnable = !!atoi(val);
                }
                else if(strcmp(var, "skippable") == 0)
                {
                    _updateSkippable = !!atoi(val);
                }
                else if(strcmp(var, "url") == 0)
                {
                    _updateUrl = val;

                    if(!_updateUrl.empty() && _updateUrl.back() != '/')
                    {
                        _updateUrl += '/';
                    }
                }
                else if(strcmp(var, "NOTICE_URL") == 0)
                {
                    _noticeUrl = val;
                }
            }

            if(data = strchr(data, '\n'))
            {
                ++data;
            }
            else
            {
                break;
            }
        }
    }
}

#define TIME_ALL	3.4f // 流光周期时间
#define TIME_SHOW	2.0f // 流光最长可视时间

void Update::update(float delta)
{
	float timeline = ((float)_spriteLight->getTag())/1000;
	timeline += delta;
	if(timeline > TIME_ALL)
	{
		timeline -= TIME_ALL;
	}
	_spriteLight->setTag(timeline*1000);
	if(timeline > TIME_SHOW)
	{
		_spriteLight->setVisible(false);
		return;
	}
	Size size = _widgetPercent->getContentSize();
	float widthTotal   = size.width;
	float widthPercent = widthTotal * _widgetPercent->getPercent()/100;
	float widthLeft    = widthTotal * timeline / TIME_SHOW - 0; // -0
	float widthRight   = widthPercent - widthLeft;
	if(widthLeft < widthPercent)
	{
		// 处理边界问题
		float widthMin = min(widthLeft,widthRight);
		float scale    = 2 * widthMin / _spriteLight->getContentSize().width; // todo width == 0
		if(scale > 1) // 可缩小不可放大
		{
			scale = 1;
		}
		_spriteLight->setScaleX(scale);
		_spriteLight->setPosition(widthLeft,size.height / 2);
		_spriteLight->setVisible(true);
	}
	else
	{
		_spriteLight->setVisible(false);
	}
}

#define SPLASH_PATH "ani/splash/fm_anim.ExportJson"
void Update::setupUI(void)
{
    auto widget = CSLoader::getInstance()->createNode("ui/ui_update.csb");
    addChild(widget);
    _widgetProgress = dynamic_cast<Widget *>(Helper::seekNodeByName(widget, "image_loading"));
    _widgetPercent  = dynamic_cast<LoadingBar *>(Helper::seekNodeByName(widget, "bar_loading"));
	_spriteLight    = Sprite::create("image/loading_g.png");
	_spriteLight->setTag(0);
	scheduleUpdate();
	_widgetPercent->addChild(_spriteLight);

    _widgetLeft     = dynamic_cast<Text *>(Helper::seekNodeByName(widget, "text_l"));
    _widgetRight    = dynamic_cast<Text *>(Helper::seekNodeByName(widget, "text_r"));
    _widgetState    = dynamic_cast<Text *>(Helper::seekNodeByName(widget, "text_state"));
    _widgetMsgBox   = dynamic_cast<Widget *>(Helper::seekNodeByName(widget, "panel_hint"));
    _widgetMsgStr   = dynamic_cast<Text *>(Helper::seekNodeByName(widget, "text_hint1"));
    _widgetMsgStr->setTextAreaSize(Size(440, 160));
    _widgetMsgStr->setTextHorizontalAlignment(TextHAlignment::CENTER);
    _widgetMsgStr->setTextVerticalAlignment(TextVAlignment::CENTER);
    Button *_widgetBtnOK     = dynamic_cast<Button *>(Helper::seekNodeByName(widget, "btn_ok"));
    Button *_widgetBtnCancel = dynamic_cast<Button *>(Helper::seekNodeByName(widget, "btn_cancel"));
    _widgetBtnOK    ->addTouchEventListener(std::bind(&Update::onButtonOK    , this, std::placeholders::_1, std::placeholders::_2));
    _widgetBtnCancel->addTouchEventListener(std::bind(&Update::onButtonCancel, this, std::placeholders::_1, std::placeholders::_2));
    Text *constText = dynamic_cast<Text *>(Helper::seekNodeByName(widget, "const_text"));
    string constString = constText->getString();
    // todo size
    int p = 0, q = 0;
    q = constString.find('|', p);
    _strCheckingVersion     = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strCheckVersionError   = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strCheckVersionNoEqual = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strCheckingList        = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strCheckListError      = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strDownloadSizeTip     = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strdownloadError       = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strProgressLeftText    = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strProgressRightTextInM = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strProgressRightTextInK = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _strLoadingResources    = constString.substr(p, q - p);
    p = ++q;
    q = constString.find('|', p);
    _sizeOfTip              = atoi(constString.substr(p).c_str());

    if(_sizeOfTip > 1024 || _sizeOfTip < 0)
    {
        _sizeOfTip = 0;
    }

    ArmatureDataManager::getInstance()->addArmatureFileInfo(SPLASH_PATH);
    auto widgetBG   = Helper::seekNodeByName(widget, "image_basemap");
    auto armature = Armature::create("fm_anim");
    armature->getAnimation()->playWithIndex(0, -1, 1);
    armature->setPosition(widgetBG->getAnchorPointInPoints());
    widgetBG->addChild(armature);
}

void Update::onButtonOK(Ref *btn, Widget::TouchEventType eventType)
{
    if(eventType != Widget::TouchEventType::ENDED)
    {
        return;
    }

    _widgetMsgBox->setVisible(false);

    switch(_msgboxType)
    {
        case MBT_FORCE_UPDATE_PACKAGE:
        case MBT_UPDATE_PACKAGE:
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
            _downloadedSize = 0;
            _totalSize = 0;
            JNIUtils::startDownloadApk(_packageUrl.c_str());
#else
            Application::getInstance()->openURL(_packageUrl);
#endif
            break;

        case MBT_CHECK_PACKAGE_ERROR:
            JNIUtils::exitGame();
            break;

        case MBT_DOWNLOAD_APK_ERROR:
        case MBT_DOWNLOAD_APK_SUCCEED:
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
            JNIUtils::startDownloadApk(_packageUrl.c_str());
#endif
            break;

        case MBT_CHECK_VERSION_ERROR:
            doCheckVersion();
            break;

        case MBT_CHECK_FORCE_UPDATE_ERROR:
            doCheckForceUpdate();
            break;

        case MBT_CHECK_LIST_ERROR:
            doCheckList();
            break;

        case MBT_DOWNLOAD_SIZE_LARGE:
            doDownload();
            break;

        case MBT_DOWNLOAD_ERROR:
            doDownload();
            break;
    }

}

void Update::onButtonCancel(Ref *btn, Widget::TouchEventType eventType)
{
    if(eventType != Widget::TouchEventType::ENDED)
    {
        return;
    }

    _widgetMsgBox->setVisible(false);

    switch(_msgboxType)
	{
		case MBT_DOWNLOAD_APK_ERROR:
		case MBT_DOWNLOAD_APK_SUCCEED:
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
			if(_isForceUpdateAPK)
			{
				JNIUtils::exitGame();
			}
			else
			{
				if(_updateEnable)
				{
					doCheckVersion();
				}
				else
				{
					callLua();
				}
			}
#endif
			break;
        case MBT_CHECK_VERSION_ERROR:
        case MBT_CHECK_LIST_ERROR:
        case MBT_DOWNLOAD_SIZE_LARGE:
            if(_updateSkippable)
            {
                onDownloadDone();
            }
            else
            {
                JNIUtils::exitGame();
            }

            break;

        case MBT_UPDATE_PACKAGE:
            if(_updateEnable)
            {
                doCheckVersion();
            }
            else
            {
                callLua();
            }

            break;

        case MBT_CHECK_FORCE_UPDATE_ERROR:
        case MBT_CHECK_PACKAGE_ERROR:
        case MBT_FORCE_UPDATE_PACKAGE:
        case MBT_DOWNLOAD_ERROR:
            JNIUtils::exitGame();
            break;
    }
}

void Update::makeFileList(FileList& fileListMap, const char *fileList)
{
    char fileName[RES_PATH_MAX + 1];
    char hashSHA1[HASH_STRING_MAX + 1];
    int  fileSize;

    while(*fileList)
    {
        int nRet = sscanf(fileList, "%[^=]=%[^,],%d", fileName, hashSHA1, &fileSize);

        if(nRet == 3)
        {
            fileListMap.insert(pair<string, FileInfo>(fileName, FileInfo(fileName, hashSHA1, fileSize)));
        }

        if((fileList = strchr(fileList, '\n')))
        {
            fileList++;
        }
        else
        {
            break;
        }
    }
}

void Update::saveLocalFileList(void)
{
    FILE *file = fopen(_externalListFile.c_str(), "wb");

    for(FileList::iterator it = _localFileList.begin(); it != _localFileList.end(); ++it)
    {
        fprintf(file, "%s=%s,%d\n", (*it).second.filePath.c_str(), (*it).second.hashSHA1.c_str(), (*it).second.fileSize);
    }

    fclose(file);

}

void Update::loadLocalFileList(void)
{
    bool useExternal = FileUtils::getInstance()->isFileExist(_externalListFile);
    makeFileList(_localFileList, FileUtils::getInstance()->getStringFromFile(useExternal ? _externalListFile : _internalListFile).c_str());
}

void Update::makeDownloadFileList(const char *fileList)
{
    _downloadedSize = 0;
    _totalSize = 0;
    makeFileList(_downloadFileList, fileList);

    for(FileList::iterator downloadIterator = _downloadFileList.begin(); downloadIterator != _downloadFileList.end();)
    {
        FileList::iterator localIterator = _localFileList.find((*downloadIterator).first);

        if(localIterator != _localFileList.end() && (*localIterator).second.hashSHA1 == (*downloadIterator).second.hashSHA1)
        {
            _downloadFileList.erase(downloadIterator++);
            continue;
        }

        _totalSize += (*downloadIterator).second.fileSize;
        ++downloadIterator;
    }
}

//errorCode
//0正在检查
//1正在下载
//2用户暂停
//3出现错误
//4下载成功（Android层会自动弹出安装）
void Update::downloadApk(int errorCode,int fileSize, int completeSize)
{
	switch (errorCode)
	{
	case 3:
		{
			_msgboxType = MBT_DOWNLOAD_APK_ERROR;
			_widgetProgress->setVisible(false);
			_widgetState->setVisible(true);
			_widgetMsgBox->setVisible(true);
			// 写死
			char str[] = "\xE5\xAE\x89\xE8\xA3\x85\xE5\x8C\x85\xE4\xB8\x8B\xE8\xBD\xBD\xE9\x94\x99\xE8\xAF\xAF\xEF\xBC\x8C\xE6\x98\xAF\xE5\x90\xA6\xE9\x87\x8D\xE8\xAF\x95\xEF\xBC\x9F";
			_widgetMsgStr->setString(str);
		}
		break;
	case 4:
		{
			_msgboxType = MBT_DOWNLOAD_APK_SUCCEED;
			_widgetProgress->setVisible(false);
			_widgetState->setVisible(true);
			_widgetMsgBox->setVisible(true);
			// 写死
			char str[] = "\xE5\xAE\x89\xE8\xA3\x85\xE5\x8C\x85\xE4\xB8\x8B\xE8\xBD\xBD\xE5\xAE\x8C\xE6\x88\x90\xEF\xBC\x8C\xE6\x98\xAF\xE5\x90\xA6\xE5\xAE\x89\xE8\xA3\x85\xEF\xBC\x9F";
			_widgetMsgStr->setString(str);
		}
	case 0:
	case 1:
	case 2:
		{
			_totalSize = fileSize;
			_downloadedSize = completeSize;

			_widgetProgress->setVisible(true);
			int percent = 100;

			if(_totalSize >= 100)
			{
				percent = _downloadedSize / (_totalSize / 100);
			}

			char buff[64];
			sprintf(buff, _strProgressLeftText.c_str(), percent);
			_widgetLeft->setString(buff);

			if(_totalSize > _sizeOfTip * 1024 * 1024)
			{
				sprintf(buff, _strProgressRightTextInM.c_str(), _downloadedSize >> 20, _totalSize >> 20);
			}
			else
			{
				sprintf(buff, _strProgressRightTextInK.c_str(), _downloadedSize >> 10, _totalSize >> 10);
			}

			_widgetRight->setString(buff);
			_widgetPercent->setPercent(percent);
			_widgetState->setVisible(false);
			_widgetMsgBox->setVisible(false);
		}
		break;
	default:
		break;
	}
}

void Update::requestResourceFile(void)
{
    _widgetProgress->setVisible(true);
    int percent = 100;

    if(_totalSize >= 100)
    {
        percent = _downloadedSize / (_totalSize / 100);
    }

    char buff[64];
    sprintf(buff, _strProgressLeftText.c_str(), percent);
    _widgetLeft->setString(buff);

    if(_totalSize > _sizeOfTip * 1024 * 1024)
    {
        sprintf(buff, _strProgressRightTextInM.c_str(), _downloadedSize >> 20, _totalSize >> 20);
    }
    else
    {
        sprintf(buff, _strProgressRightTextInK.c_str(), _downloadedSize >> 10, _totalSize >> 10);
    }

    _widgetRight->setString(buff);
    _widgetPercent->setPercent(percent);
    _widgetState->setVisible(false);
    _widgetMsgBox->setVisible(false);

    if(_downloadFileList.size())
    {
        FileList::iterator it = _downloadFileList.begin();
        string url = _updateUrl + (*it).first;
        doHttpRequest(url.c_str());
    }
    else
    {
        onDownloadDone();
    }
}

void Update::writeResourceFile(const char *filePath, void *data, int length)
{
    string path = _externalResourceDir;
    auto tail = filePath, head = filePath;

    // make dirs
    for(; *head; head++)
    {
        if(*head == '/')
        {
            path.append(tail, head);
            tail = head;
            _mkdir(path.c_str());
        }
    }

    path.append(tail, head);
    FILE *file = fopen(path.c_str(), "wb");
#if (CC_TARGET_PLATFORM == CC_PLATFORM_WIN32)

    if(head[-3] == 'l' && head[-2] == 'u' && head[-1] == 'a') // todo Out of bounds OR Case sensitive
    {
        int deLen;
        void *deData = Encrypt::decrypt(&deLen, data, length);
        fwrite(deData, deLen, 1, file);
        delete[](deData);
    }
    else
    {
        fwrite(data, length, 1, file);
    }

#else
    fwrite(data, length, 1, file);
#endif
    fclose(file);
    _downloadedSize += length;
}

// todo UrlEncode {space}
void Update::doHttpRequest(const char *url)
{
    HttpRequest *request = new HttpRequest();
    request->setResponseCallback(this, httpresponse_selector(Update::onHttpResponse));
    request->setRequestType(HttpRequest::Type::GET);
    request->setUrl(url);
    HttpClient::getInstance()->send(request);
    request->release();
}

void Update::onHttpResponse(HttpClient *client, HttpResponse *response)
{
    if(response->isSucceed() && response->getResponseCode() == 200)
    {
        auto responseData = response->getResponseData();

        switch(_updateState)
        {
            case State_CheckForceUpdate:
                {
                    string temp(responseData->begin(), responseData->end());
                    rapidjson::Document json;
                    rapidjson::StringStream stream(temp.c_str());

                    json.ParseStream<0>(stream);

                    if(json.HasParseError())
                    {
                        CCLOG("GetParseError %s\n", json.GetParseError());
                    }

                    auto dh = DictionaryHelper::getInstance();
                    int code = dh->getIntValue_json(json, "code");
                    string message = dh->getStringValue_json(json, "message");
                    _updateUrl = dh->getStringValue_json(json, "res_url");
                    _loginUrl = dh->getStringValue_json(json, "login_url");
                    _packageUrl = dh->getStringValue_json(json, "dl_url");
                    _noticeUrl = dh->getStringValue_json(json, "notice_url");

                    JNIUtils::logAndroid(_loginUrl.c_str());
                    JNIUtils::logAndroid(_packageUrl.c_str());
                    JNIUtils::logAndroid(_noticeUrl.c_str());
                    JNIUtils::logAndroid(_updateUrl.c_str());

                    if(!_updateUrl.empty() && _updateUrl.back() != '/')
                    {
                        _updateUrl += '/';
                    }

                    if(code == 2)  // 强更
                    {
                        _msgboxType = MBT_FORCE_UPDATE_PACKAGE;
                        _widgetProgress->setVisible(false);
                        _widgetState->setVisible(false);
                        _widgetMsgBox->setVisible(true);
                        _widgetMsgStr->setString(message);
						_isForceUpdateAPK = true;
                    }
                    else if(code == 3)  // 有包更新
                    {
                        _msgboxType = MBT_UPDATE_PACKAGE;
                        _widgetProgress->setVisible(false);
                        _widgetState->setVisible(false);
                        _widgetMsgBox->setVisible(true);
                        _widgetMsgStr->setString(message);
						_isForceUpdateAPK = false;
                    }
                    else if(code == 4)  // 验证异常
                    {
                        _msgboxType = MBT_CHECK_PACKAGE_ERROR;
                        _widgetProgress->setVisible(false);
                        _widgetState->setVisible(false);
                        _widgetMsgBox->setVisible(true);
                        _widgetMsgStr->setString(message);
                    }
                    else
                    {
                        if(_updateEnable)
                        {
                            doCheckVersion();
                        }
                        else
                        {
                            callLua();
                        }
                    }
                }
                break;

            case State_DownloadingVersionFile:
                {
                    _versionDownload.assign(responseData->begin(), responseData->end());
                    onCheckVersionDone();
                }
                break;

            case State_DownloadingFileList:
                {
                    loadLocalFileList();
                    makeDownloadFileList(string(responseData->begin(), responseData->end()).c_str());
                    _mkdir(_externalResourceDir.c_str());
                    onCheckListDone();
                }
                break;

            case State_DownloadingResources:
                {
                    auto it = _downloadFileList.begin();
                    writeResourceFile(it->first.c_str(), responseData->data(), responseData->size());

                    // 更新本地列表
                    _localFileList.erase((*it).first);
                    _localFileList.insert(*it);

                    saveLocalFileList();

                    _downloadFileList.erase(it);
                    requestResourceFile();
                }
                break;
        }
    }
    else    // error
    {
        switch(_updateState)
        {
            case State_CheckForceUpdate:
                onCheckForceUpdateError();
                break;

            case State_DownloadingVersionFile:
                onCheckVersionError();
                break;

            case State_DownloadingFileList:
                onCheckListError();
                break;

            case State_DownloadingResources:
                onDownloadError();
                break;
        }
    }
}

void Update::onEnter(void)
{
    Node::onEnter();


#if (CC_TARGET_PLATFORM == CC_PLATFORM_WIN32)

    if(_updateEnable)
    {
        doCheckVersion();
    }
    else
    {
        callLua();
    }

#else

    if(JNIUtils::getChannelId() == "dev")
    {
        if(_updateEnable)
        {
            doCheckVersion();
        }
        else
        {
            callLua();
        }
    }
    else
    {
        doCheckForceUpdate();
    }

#endif
}

void Update::onExit(void)
{
    Node::onExit();
    ArmatureDataManager::getInstance()->removeArmatureFileInfo(SPLASH_PATH);
}

void Update::doCheckForceUpdate(void)
{
    _widgetProgress->setVisible(false);
    _widgetState->setVisible(true);
    _widgetState->setString(_strCheckingVersion);
    _widgetMsgBox->setVisible(false);
    _updateState = State_CheckForceUpdate;

    string url = "http://dpver.huayigame.com/501?";

    char buf[64];
    url.append("channel_id=").append(JNIUtils::getChannelId());
    sprintf(buf, "%d", JNIUtils::getProductId());
    url.append("&product_id=").append(buf);
    url.append("&code_version=").append(PROGRAM_VER);

    auto map = JNIUtils::getDeviceInfo();

    auto tmp = map;

    for(auto& entry : tmp)
    {
        map[entry.first] = urlEncode(entry.second);
    }

    url.append("&imei=").append(map["imei"]);
    url.append("&phone_code=").append(map["phone_code"]);
    url.append("&screen_width=").append(map["screen_width"]);
    url.append("&screen_height=").append(map["screen_height"]);
    url.append("&os_version=").append(map["devOs"]);
    url.append("&udid=").append(map["udid"]);
    url.append("&mac=").append(map["mac"]);
    url.append("&ua=").append(map["ua"]);
    url.append("&serial_code=").append(map["serial_code"]);
    url.append("&channel_sub=").append(map["packageName"]);

    doHttpRequest(url.c_str());
}

void Update::doCheckVersion(void)
{
    string url = _updateUrl + VERSION_FILE;
    _widgetProgress->setVisible(false);
    _widgetState->setVisible(true);
    _widgetState->setString(_strCheckingVersion);
    _widgetMsgBox->setVisible(false);
    _updateState = State_DownloadingVersionFile;
    doHttpRequest(url.c_str());
}

void Update::onCheckForceUpdateError()
{
    _msgboxType = MBT_CHECK_FORCE_UPDATE_ERROR;
    _widgetProgress->setVisible(false);
    _widgetState->setVisible(true);
    _widgetMsgBox->setVisible(true);
    _widgetMsgStr->setString(_strCheckVersionError);
}

void Update::onCheckVersionError(void)
{
    _msgboxType = MBT_CHECK_VERSION_ERROR;
    _widgetProgress->setVisible(false);
    _widgetState->setVisible(true);
    _widgetMsgBox->setVisible(true);
    _widgetMsgStr->setString(_strCheckVersionError);
}

void Update::onCheckVersionDone(void)
{
    bool useExternal = FileUtils::getInstance()->isFileExist(_externalVersionFile);
    string versionLocal = FileUtils::getInstance()->getStringFromFile(useExternal ? _externalVersionFile : _internalVersionFile);
    int localResourcesVersion;
    int downloadResourcesVersion;
    int lCount = sscanf(versionLocal.c_str(), "%u", &localResourcesVersion);
    int dCount = sscanf(_versionDownload.c_str(), "%u", &downloadResourcesVersion);
    CCASSERT(lCount == 1 && dCount == 1, "Update::onCheckVersionDone() Invalid version file");

    if(localResourcesVersion < downloadResourcesVersion)
    {
        doCheckList();
    }
    else
    {
        _widgetProgress->setVisible(false);
        _widgetState->setVisible(true);
        _widgetState->setString(_strLoadingResources);
        _widgetMsgBox->setVisible(false);
        runAction(CallFunc::create(std::bind(&Update::callLua, this)));
    }
}

void Update::doCheckList(void)
{
    _widgetProgress->setVisible(false);
    _widgetState->setVisible(true);
    _widgetState->setString(_strCheckingList);
    _widgetMsgBox->setVisible(false);
    _updateState = State_DownloadingFileList;
    string url = _updateUrl + LIST_FILE;
    doHttpRequest(url.c_str());
}

void Update::onCheckListError(void)
{
    _msgboxType = MBT_CHECK_LIST_ERROR;
    _widgetProgress->setVisible(false);
    _widgetState->setVisible(true);
    _widgetMsgBox->setVisible(true);
    _widgetMsgStr->setString(_strCheckListError);
}

void Update::onCheckListDone(void)
{
    if(_totalSize > _sizeOfTip * 1024 * 1024 || (_totalSize && _updateSkippable))
    {
        _msgboxType = MBT_DOWNLOAD_SIZE_LARGE;
        _widgetProgress->setVisible(false);
        _widgetState->setVisible(true);
        _widgetMsgBox->setVisible(true);
        char buff[64];
        sprintf(buff, _strDownloadSizeTip.c_str(), _totalSize >> 20);
        _widgetMsgStr->setString(buff);
    }
    else
    {
        doDownload();
    }
}

void Update::doDownload(void)
{
    _updateState = State_DownloadingResources;
    requestResourceFile();
}

void Update::onDownloadError(void)
{
    _msgboxType = MBT_DOWNLOAD_ERROR;
    _widgetProgress->setVisible(false);
    _widgetState->setVisible(false);
    _widgetMsgBox->setVisible(true);
    _widgetMsgStr->setString(_strdownloadError);
}

void Update::onDownloadDone(void)
{
    FILE *file = fopen(_externalVersionFile.c_str(), "wb");
    fwrite(_versionDownload.c_str(), _versionDownload.length(), 1, file);
    fclose(file);
    _widgetProgress->setVisible(false);
    _widgetState->setVisible(true);
    _widgetState->setString(_strLoadingResources);
    _widgetMsgBox->setVisible(false);
    runAction(CallFunc::create(std::bind(&Update::callLua, this)));
}

void Update::callLua(void)
{
    vector<string> searchPaths = FileUtils::getInstance()->getSearchPaths();
    searchPaths.insert(searchPaths.begin(), _externalResourceDir);
    FileUtils::getInstance()->setSearchPaths(searchPaths);

    auto engine = LuaEngine::getInstance();
    engine->setLuaLoader(encrypt_lua_loader);

#if CC_TARGET_PLATFORM != CC_PLATFORM_WIN32
	TestinLuaExceptionHandler::registerLuaExceptionHandler();
#endif

    string str = "dp = {NOTICE_URL = \"";
    str.append(_noticeUrl);
    str.append("\", LOGIN_URL = \"");
    str.append(_loginUrl);
    str.append("\", PROGRAM_VER = \"");
    str.append(PROGRAM_VER);
    str.append("\"}\n");
    str.append("require\"main\"");
    engine->executeString(str.c_str());


}
