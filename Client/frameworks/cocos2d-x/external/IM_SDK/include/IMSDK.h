#ifndef _IMSDK_H__
#define _IMSDK_H__

#include "yvpacket_sdk.h"

#ifdef _MSC_VER
#ifdef  _YVIM_EXPORTS
#define YVIM_API    extern "C" __declspec(dllexport)  
#else
#define YVIM_API    /*__declspec(dllimport)*/
#endif

#else
#define YVIM_API    //extern "C"
#define _stdcall    
#endif


#define  YV_RET_SUCC      0      //成功
#define  YV_RET_FAIL      -1      //失败


enum CmdChannel {
	IM_LOGIN   = 1,
	IM_FRIEND  = 2,
	IM_GROUPS  = 3,
	IM_CHAT    = 4,
	IM_CLOUND  = 5,
	IM_CHANNEL = 6,
	IM_TROOPS  = 7,
	IM_LBS     = 8,
	IM_TOOLS   = 9,
	IM_LIVE    = 10, 
};

#ifdef __cplusplus
extern "C" {
#endif

typedef void (_stdcall *YVCallBack)(enum CmdChannel type, unsigned int cmdid, YV_PARSER parser, unsigned long context);

//初始化
/*
* callback: 回调函数
* context： 回调上下文
* appid： cp appid
* dbpath：缓存目录路径
* test： 是否为测试环境，true为测试环境
* 返回 0表示成功，-1表示失败 
*/
YVIM_API int  YVIM_Init(YVCallBack callback, unsigned long context, unsigned int appid, const char* path, bool test);

//释放
YVIM_API void YVIM_Release();

//发送命令
/*
* type: 发送模块ID， CmdChannel枚举
* cmdid： 协议号
* parser：生成数据包句柄
* 返回 0表示成功，-1表示失败
*/
YVIM_API int  YVIM_SendCmd(enum CmdChannel type, unsigned int cmdid, YV_PARSER parser);

////设置回调方式
///*
//*默认主线程回调
//*main: true 主线程回调， false 子线程回调
//*/
YVIM_API void YVIM_SetCallBackMode(bool mode);
#ifdef __cplusplus
}
#endif

#endif