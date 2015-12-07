#ifndef _YVLBSSDK
#define _YVLBSSDK



/*******************************  LBS模块  ********************************
*
*			              模块类型: IM_LBS
*
***************************************************************************/

//更新地理位置请求
#define IM_LBS_UPDATE_LOCATION_REQ			   0x18000
namespace x18000{
}

#define IM_LBS_UPDATE_LOCATION_RESP			   0x18001
namespace x18001{
	enum
	{
		/*uint32*/		result			= 1,   //结果
		/*string*/      msg				= 2,   //错误描述
	};
}

//获取位置信息请求
#define IM_LBS_GET_LOCATION_REQ				   0x18002
namespace x18002{
}

#define IM_LBS_GET_LOCATION_RESP			   0x18003
namespace x18003{
	enum{
		/*uint32*/		result			= 1,    //结果
		/*string*/      msg				= 2,    //错误描述
		/*string*/		city			= 3,	//城市
		/*string*/		province		= 4,	//省份
		/*string*/		district		= 5,	//区
		/*string*/		detail			= 6,	//详细地址描述
		/*string*/		longitude		= 7,	//经度
		/*string*/		latitude		= 8,	//纬度
	};
}

//搜索（附近）用户(包括更新位置)
#define IM_LBS_SEARCH_AROUND_REQ			   0x18004
namespace x18004{
	enum
	{
		/*uint32*/		range			= 1,	//可选，搜索范围，默认值100000，单位：米
		/*string*/		city			= 2,	//可选，所在城市
		/*uint8*/		sex				= 3,	//可选，性别:0 不限 1男 2女
		/*uint32*/      time			= 4,	//可选，在此时间内活跃，最大有效值为2880（60*24*2，两天）单位：分钟
		/*uint32*/      pageIndex		= 5,	//必填，当前页号
		/*uint32*/      pageSize		= 6,	//必填，分页长度，(pageSize < 32)
		/*string*/      ext             = 7,    //暂不可用，扩展字段
		/*string*/		province		= 8,	//暂不可用，省份/自治区/特别行政区
		/*string*/		district		= 9,	//暂不可用，区/县/县级市
		/*string*/		detail			= 10,	//暂不可用，详细地址/模糊匹配
	};
}

//附近用户
namespace xAroundUser{
	enum{
		/*uint32*/      id				= 1,	//用户ID
		/*string*/		nickname		= 2,	//昵称
		/*uint8*/		sex				= 3,	//性别:0 未指定 1男 2女,保留
		/*string*/		city			= 4,	//所在城市
		/*string*/		headicon		= 5,    //头像地址
		/*uint32*/		distance		= 6,	//距离，单位：米
		/*string*/		lately			= 7,	//最近活跃时间
		/*string*/		longitude		= 8,	//经度
		/*string*/		latitude		= 9,	//纬度
		/*string*/		ext				= 10,	//扩展字段
	};
}

#define IM_LBS_SEARCH_AROUND_RESP		       0x18005
namespace x18005{
	enum
	{
		/*uint32*/			result	    = 1,	//结果
		/*string*/			msg			= 2,	//错误描述
		/*xAroundUser[]*/	user		= 3,	//用户列表
	};
}

//隐藏地理位置请求
#define IM_LBS_SHARE_LOCATION_REQ			   0x18006
namespace x18006{
	enum{
		/*uint8*/			hide		= 1,	//必填，0 分享 1 隐藏，默认为0
	};
}

#define IM_LBS_SHARE_LOCATION_RESP			   0x18007
namespace x18007{
	enum
	{
		/*uint32*/		result			= 1,   //结果
		/*string*/      msg				= 2,   //错误描述
	};
}


//获取支持的（包括搜索、返回信息等）本地化语言列表请求
#define IM_LBS_GET_SUPPORT_LANG_REQ			   0x18008
namespace x18008{
}

//语言类型
namespace xLanguage{
	enum{
    /*string*/	 lang_code			= 1,			//语言编号, 如：zn-CN
    /*string*/	 lang_name			= 2,			//语言名称, 如：简体中文
	};
}

#define IM_LBS_GET_SUPPORT_LANG_RESP			   0x18009
namespace x18009{
	enum
	{
		/*uint32*/		result			= 1,   //结果
		/*string*/      msg				= 2,   //错误描述
		/*xLanguage[]*/    language		= 3,   //语言列表
	};
}

//设置LBS本地化语言
#define IM_LBS_SET_LOCAL_LANG_REQ			   0x18010
namespace x18010{
	enum
	{
		/*string*/    lang_code			= 1,		//必填，语言编号 默认为zh-CN
		/*string*/    country_code		= 2,		//必填，国家编码 默认为CN
	};
}

#define IM_LBS_SET_LOCAL_LANG_RESP			   0x18011
namespace x18011{
	enum
	{
		/*uint32*/		result			= 1,   //结果
		/*string*/      msg				= 2,   //错误描述
		/*string*/		lang_code		= 3,   //语言编号
	};
}

//设置定位方式
#define IM_LBS_SET_LOCATING_TYPE_REQ			   0x18012
namespace x18012{
	enum
	{
		/*uint8*/		locate_gps		 = 1,	//可选，定位方式 GPS: 0 开 1 关， 默认为0
		/*uint8*/		locate_wifi		 = 2,	//可选，定位方式 WIFI:0 开 1 关， 默认为0
		/*uint8*/		locate_cell		 = 3,	//可选，定位方式 基站:0 开 1 关， 默认为0
		/*uint8*/		locate_network	 = 4,	//可选，定位方式 网络:0 开 1 关， 默认为0
		/*uint8*/		locate_bluetooth = 5,	//暂不可用，定位方式 蓝牙
	};
}

#define IM_LBS_SET_LOCATING_TYPE_RESP			   0x18013
namespace x18013{
	enum
	{
		/*uint32*/		result			= 1,   //结果
		/*string*/      msg				= 2,   //错误描述
	};
}


#endif  //_YVLBSSDK