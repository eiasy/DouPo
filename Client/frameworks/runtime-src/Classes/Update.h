
////////////////////////////////////////////////////////////////////////////////
//
//  Copyright (C) 2014 华益天信科技(北京)有限公司. All rights reserved.
//
//  File:      Update.h
//
//  Purpose:   资源更新模块
//
//  History:
//  2014-05-12 安迷             Created.
//  0000-00-00 ____YourName____ ____TheDescription____
//
////////////////////////////////////////////////////////////////////////////////

#ifndef _UPDATE_H_
#define _UPDATE_H_

#include <map>
#include <string.h>
#include "cocos2d.h"
#include "ui/CocosGUI.h"
#include "network/HttpClient.h"
#include "cocostudio/CCSGUIReader.h"

using namespace std;
using namespace cocos2d;
using namespace cocos2d::ui;
using namespace cocostudio;

class Update : public Node
{
protected:  // typedef
    enum UpdateState
    {
        State_CheckForceUpdate,
        State_DownloadingApk,
        State_DownloadingVersionFile,
        State_DownloadingFileList,
        State_DownloadingResources,
    };
    struct FileInfo
    {
        FileInfo(string path, string sha1, int size)
        {
            filePath = path;
            hashSHA1 = sha1;
            fileSize = size;
        }
        string filePath;
        string hashSHA1;
        int fileSize;
    };
    typedef map<string, FileInfo> FileList;

public:     // function
    static Update *create(void);

    void downloadApk(int errorCode, int fileSize, int completeSize);
    void update(float delta);

protected:  // function
    virtual bool init(void);

    void checkFlag(void);
    void loadConfig(void);
    void setupUI(void);
    void onButtonOK(Ref *a, ui::Widget::TouchEventType aa);
    void onButtonCancel(Ref *a, ui::Widget::TouchEventType aa);
    void makeFileList(FileList& fileListMap, const char *fileList);
    void saveLocalFileList(void);
    void loadLocalFileList(void);
    void makeDownloadFileList(const char *fileList);

    void requestResourceFile(void);
    void writeResourceFile(const char *filePath, void *data, int length);

    void doHttpRequest(const char *url);
    void onHttpResponse(cocos2d::network::HttpClient *client, cocos2d::network::HttpResponse *response);

    virtual void onEnter(void);
    virtual void onExit(void);

    void doCheckForceUpdate(void);
    void onCheckForceUpdateError(void);
    void doCheckVersion(void);
    void onCheckVersionError(void);
    void onCheckVersionDone(void);
    void doCheckList(void);
    void onCheckListError(void);
    void onCheckListDone(void);
    void doDownload(void);
    void onDownloadError(void);
    void onDownloadDone(void);

    void callLua(void);
private:    // function
public:     // member
protected:  // member
    UpdateState _updateState;
    bool _updateEnable;
    bool _updateSkippable;
    string _updateUrl;
    string _internalVersionFile;
    string _internalListFile;
    string _externalFlagFile;
    string _externalVersionFile;
    string _externalListFile;
    string _externalResourceDir;
    string _versionDownload;
    string _loginUrl;
    string _packageUrl;
    string _noticeUrl;
    FileList _localFileList;
    FileList _downloadFileList;
    int _downloadedSize;
    int _totalSize;
private:    //member
    enum MsgBoxType
    {
        MBT_FORCE_UPDATE_PACKAGE,
        MBT_UPDATE_PACKAGE,
        MBT_CHECK_PACKAGE_ERROR,
        MBT_CHECK_FORCE_UPDATE_ERROR,
		MBT_DOWNLOAD_APK_ERROR,
		MBT_DOWNLOAD_APK_SUCCEED,
        MBT_CHECK_VERSION_ERROR,
        MBT_CHECK_LIST_ERROR,
        MBT_DOWNLOAD_SIZE_LARGE,
        MBT_DOWNLOAD_ERROR,
    } _msgboxType;
    LoadingBar *_widgetPercent;
    Widget *_widgetProgress;
    Widget *_widgetMsgBox;
    Sprite *_spriteLight;
    Text *_widgetLeft;
    Text *_widgetRight;
    Text *_widgetState;
    Text *_widgetMsgStr;
    string _strCheckingVersion;
    string _strCheckVersionError;
    string _strCheckVersionNoEqual;
    string _strCheckingList;
    string _strCheckListError;
    string _strDownloadSizeTip;
    string _strdownloadError;
    string _strProgressLeftText;
    string _strProgressRightTextInM;
    string _strProgressRightTextInK;
    string _strLoadingResources;
    int _sizeOfTip;
	bool _isForceUpdateAPK;
};

#endif  //_UPDATE_H_
