
/**********************************************************
* Copyright (c) 2013-2015 shenzhen yunva echnology Co.,Ltd
* All rights reserved.
  @author:    baohaibo
  @date:      2015-06-05
  @version:	  v1.0.3
  @filename:  YvErrorCode.h
  @rule:  1 + channelid + type + num
  @example: 1901 : upload file fail
***********************************************************/

#ifndef  _YVERROR_CODE_
#define  _YVERROR_CODE_

typedef enum
{
	YvErrorCodeUnknown              =  1,       //unknown  error

	YvErrorCodeModifyUserInfo       =  1001,    //modify user info id error, by all msg
	YvErrorCodeChatSendMsgFail      =  1002,    //disconnect the network, by all msg
	YvErrorCodeChatUploadFileFail   =  1003,    //chat send image or audio fail, by all msg
	YvErrorCodeChatByBanned			=  1004,    //channel chat by banned
	YvErrorCodeChatTextMaxNum       =  1005,    //More than the maximum length

	YvErrorCodeLoginTimeOut         =  1101,    //login timeout > 15s
	YvErrorCodeLoginNotJar          =  1102,    //not find the android service jar
	YvErrorCodeLoginCallBackJar     =  1103,    //login callback android jar fail

	YvErrorCodeNotlogged            =  1900,    //Account not login, tool req
	YvErrorCodeUploadFile           =  1901,    //upload file fail
	YvErrorCodeDownFile             =  1902,    //down file fail
	YvErrorCodeDomainFail           =  1903,    //url domain analysis fail, network disconnect or url error
	YvErrorCodeUploadRepeat         =  1904,    //upload file reqeat
	YvErrorCodeDownRepeat           =  1905,    //down file reqeat

	YvErrorCodeRecordPower          =  1911,    //record audio error permission denied(ios > ios7) 
	YvErrorCodeRecordEncode         =  1912,    //record audio create encode fail
	YvErrorCodeRecordPathError      =  1913,    //record audio filename error or sd space is insufficient

	YvErrorCodePlayFileNull         =  1921,    //play file path error
	YvErrorCodePlayFileSmall        =  1922,    //play file size samll  < 192B
	YvErrorCodePlayUrlError         =  1923,    //url domain analysis fail

	YvErrorCodeSpeechHttpFail       =  1931,    //speech http req fail
	YvErrorCodeSpeechFileFail       =  1932,    //speech file fail
	YvErrorCodeSpeechAuthFail       =  1933,    //speech auth fail
	YvErrorCodeSpeechResultFail     =  1934,    //speech result error, believe that information to see msg
	
	YvErrorCodeGPSUnKnown           =  1940,    //unknown error
	YvErrorCodeGPSCallBackJarError  =  1941,    //callback jar method fail->android
	YvErrorCodeGPSNetwork           =  1942,    //network error
	YvErrorCodeGPSGPSClose          =  1943,    //GPS close
	yvErrorCodeGPSNotAllow          =  1944,    //not allow
	yvErrorCodeGPSOSNotSupport      =  1945,    //os not support
    yvErrorCodeLocateTypeNotSupport =  1946,    //locate type not support
    yvErrorCodeLBSLangNotSupport	=  1947,	//language not support
	yvErrorCodeLBSSettingNotAllow	=  1948,	//settings change while locating is not allow
	yvErrorCodeLBSGetLongLatFail	=  1949,	//get your position(longitude_latitude) fail
	yvErrorCodeLBSLocatingRightNow	=  1950,	//locating right now, please do it later
}yverror;

#endif