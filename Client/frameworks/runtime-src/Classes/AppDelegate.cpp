#include "AppDelegate.h"
#include "CCLuaEngine.h"
#include "SimpleAudioEngine.h"
#include "cocos2d.h"
#include "lua_module_register.h"

#include "Update.h"
#include "network/HttpClient.h"
#include "CCLuaEngine.h"
#include "lua_LuaBridge_auto.hpp"
#include "lua_LuaBridge_manual.hpp"

#if CC_TARGET_PLATFORM != CC_PLATFORM_IOS
#include "YVSDK/YVSDK.h"
using namespace  YVSDK;
#endif
#include "JNIUtils.h"

#if ANYSDK
#include "PluginChannel.h"
#endif
using namespace CocosDenshion;

USING_NS_CC;
using namespace std;

#if CC_TARGET_PLATFORM != CC_PLATFORM_IOS
//=========================ÔÆÍÞSDKÊÂ¼þDispatchÀà==================================
class DispatchMsgNode : public cocos2d::Node
	, public YVListern::YVCPLoginListern
	, public YVListern::YVStopRecordListern
	, public YVListern::YVFinishPlayListern
	, public YVListern::YVUpLoadFileListern
	, public YVListern::YVDownLoadFileListern
{
public:
	bool init()
	{
		isschedule = false;
		return  Node::init();
	}
	CREATE_FUNC(DispatchMsgNode);
	void startDispatch()
	{
		if (isschedule) return;
		
		auto yv = YVPlatform::getSingletonPtr();
		yv->addCPLoginListern(this);
		yv->addStopRecordListern(this);
		yv->addFinishPlayListern(this);
		yv->addUpLoadFileListern(this);
		yv->addDownLoadFileListern(this);

		isschedule = true;
		Director::getInstance()->getScheduler()->schedule(SEL_SCHEDULE(&DispatchMsgNode::update), this, 0, false);
	}
	void stopDispatch()
	{
		if (!isschedule) return;
		isschedule = false;
		Director::getInstance()->getScheduler()->unschedule(SEL_SCHEDULE(&DispatchMsgNode::update), this);
		
		auto yv = YVPlatform::getSingletonPtr();
		yv->delCPLoginListern(this);
		yv->delStopRecordListern(this);
		yv->delFinishPlayListern(this);
		yv->delUpLoadFileListern(this);
		yv->delDownLoadFileListern(this);
	}
	void update(float dt)
	{
		YVPlatform::getSingletonPtr()->updateSdk(dt);
	}

	void onCPLoginListern(CPLoginResponce* r)
	{
		char codes[512];
		sprintf(codes, "SDK.onYVCPLoginListern(%d, '%s', %d, '%s', '%s')", r->result, r->msg.c_str(), r->userid, r->thirdUserId.c_str(), r->thirdUserName.c_str());
		LuaEngine::getInstance()->executeString(codes);
	}

	void onStopRecordListern(RecordStopNotify* r)
	{
		YVFilePathPtr yvPath = r->strfilepath;
		
		char codes[512];
		sprintf(codes, "SDK.onYVStopRecordListern(%d, '%s', '%s')", r->time, yvPath->getLocalPath().c_str(), r->ext.c_str());
		LuaEngine::getInstance()->executeString(codes);
		YVPlatform::getSingletonPtr()->upLoadFile(yvPath);
	}

	void onFinishPlayListern(StartPlayVoiceRespond* r)
	{
		char codes[512];
		sprintf(codes, "SDK.onYVFinishPlayListern(%d, '%s', '%s', '%s')", r->result, r->describe.c_str(), r->filePath->getUrlPath().c_str(), r->ext.c_str());
		LuaEngine::getInstance()->executeString(codes);
	}

	void onUpLoadFileListern(UpLoadFileRespond* r)
	{
		char codes[512];
		sprintf(codes, "SDK.onYVUpLoadFileListern(%d, '%s', '%s', '%s', %d)", r->result, r->msg.c_str(), r->fileid.c_str(), r->fileurl.c_str(), r->percent);
		LuaEngine::getInstance()->executeString(codes);
	}

	void onDownLoadFileListern(YVFilePathPtr yvPath)
	{
		char codes[512];
		sprintf(codes, "SDK.onYVDownLoadFileListern('%s')", yvPath->getLocalPath().c_str());
		LuaEngine::getInstance()->executeString(codes);
	}

private:
	bool isschedule;

};
//========================================================================================
#endif

AppDelegate::AppDelegate() {
#if CC_TARGET_PLATFORM != CC_PLATFORM_IOS
	m_dispathMsgNode = NULL;
#endif
}

AppDelegate::~AppDelegate() {
#if CC_TARGET_PLATFORM != CC_PLATFORM_IOS
	if (m_dispathMsgNode != NULL)
	{
		m_dispathMsgNode->stopDispatch();
		m_dispathMsgNode->release();
		m_dispathMsgNode = NULL;
	}
#endif
    SimpleAudioEngine::end();
    network::HttpClient::destroyInstance();
}

//if you want a different context,just modify the value of glContextAttrs
//it will takes effect on all platforms
void AppDelegate::initGLContextAttrs() {
    //set OpenGL context attributions,now can only set six attributions:
    //red,green,blue,alpha,depth,stencil
    GLContextAttrs glContextAttrs = {8, 8, 8, 8, 24, 8};

    GLView::setGLContextAttrs(glContextAttrs);
}

// If you want to use packages manager to install more packages,
// don't modify or remove this function
static int register_all_packages() {
    return 0; //flag for packages manager
}

bool AppDelegate::applicationDidFinishLaunching() {
#if ANYSDK
    PluginChannel::getInstance()->loadPlugins();
    CCLOG("AppDelegate.launching");
#endif
    auto director = Director::getInstance();
    auto glview = director->getOpenGLView();
#define S_W 640
#define S_H 1136

    if(!glview) {
#if CC_TARGET_PLATFORM == CC_PLATFORM_WIN32
        float screenH = GetSystemMetrics(SM_CYSCREEN);
        float screenW = GetSystemMetrics(SM_CXSCREEN);
        float scale = 1;

        if(screenH - 80 < S_H)
            scale = (screenH - 80) / S_H;

        if(scale > 1.0) {
            scale = 1.0f;
        }

        glview = GLViewImpl::createWithRect("DouPo", Rect(0, 0, S_W, S_H), scale);
        SetWindowPos(FindWindowA("GLFW30", "DouPo"), HWND_TOP, (screenW - S_W) / 2, (screenH - 80 - scale * S_H) / 2, 0, 0, SWP_NOZORDER | SWP_NOSIZE);
#else
        glview = GLViewImpl::createWithRect("DouPo", Rect(0, 0, 0, 0));
#endif
        director->setOpenGLView(glview);
    }

    glview->setDesignResolutionSize(640, 1136, ResolutionPolicy::SHOW_ALL);
    // set default FPS
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
    setAnimationInterval(1.0 / 50.0f);
    director->setAnimationInterval(1.0f / 50.0f);
#else
    director->setAnimationInterval(1.0f / 60.0f);
#endif
    director->setDisplayStats(false);

    // register lua module
    auto engine = LuaEngine::getInstance();
    ScriptEngineManager::getInstance()->setScriptEngine(engine);
    lua_State *L = engine->getLuaStack()->getLuaState();
    lua_module_register(L);

    register_all_packages();

    lua_getglobal(L, "_G");

    if(lua_istable(L, -1)) {
        register_all_LuaBridge(L);
        register_all_LuaBridge_manual(L);
    }

    lua_pop(L, 1);

    LuaStack *stack = engine->getLuaStack();
    stack->setXXTEAKeyAndSign("2dxLua", strlen("2dxLua"), "XXTEA", strlen("XXTEA"));

    //register custom function
    //LuaStack* stack = engine->getLuaStack();
    //register_custom_function(stack->getLuaState());

    auto scene = Scene::create();
    scene->addChild(Update::create());
    Director::getInstance()->runWithScene(scene);

#if CC_TARGET_PLATFORM != CC_PLATFORM_IOS
	if (m_dispathMsgNode == NULL)
	{
		YVPlatform* platform = YVPlatform::getSingletonPtr();
		platform->setConfig(ConfigAppid, 1000167);
		platform->setConfig(ConfigTempPath, (FileUtils::getInstance()->getWritablePath() + "voice/").c_str());
		platform->setConfig(ConfigIsTest, false);//true
		platform->setConfig(ConfigChannelKey, "0x001", NULL);
		platform->setConfig(ConfigServerId, "1");
		platform->setConfig(ConfigSpeechWhenSend, true);
		platform->init();    
		m_dispathMsgNode = DispatchMsgNode::create();
		m_dispathMsgNode->retain();
		m_dispathMsgNode->startDispatch();
	}
#endif

    return true;
}

// This function will be called when the app is inactive. When comes a phone call,it's be invoked too
void AppDelegate::applicationDidEnterBackground() {
    Director::getInstance()->stopAnimation();
#if (CC_TARGET_PLATFORM != CC_PLATFORM_ANDROID)
    SimpleAudioEngine::getInstance()->pauseBackgroundMusic();
#endif
    LuaEngine::getInstance()->executeGlobalFunction("applicationDidEnterBackground");
}

// this function will be called when the app is active again
void AppDelegate::applicationWillEnterForeground() {
#if ANYSDK
    PluginChannel *channel = PluginChannel::getInstance();
    channel->showToolBar(kToolBarTopLeft);
    channel->pause();
#endif
    Director::getInstance()->startAnimation();
    SimpleAudioEngine::getInstance()->resumeBackgroundMusic();
    LuaEngine::getInstance()->executeGlobalFunction("applicationWillEnterForeground");
}
