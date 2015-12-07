#ifndef _YVSDK_YVPATH_H_
#define _YVSDK_YVPATH_H_
#include <string>
#include "YVDef.h"
#include "YVUtils/YVUtil.h"

namespace YVSDK
{
	//文件状态
	enum YVFileState
	{
		UnkownState ,

		DownLoadingState,                //下载当中
		UpdateLoadingState ,              //上传当中

		DownLoadErroSate ,                //下载失败
		UpdateLoadErrorState,

		OnlyLocalState  ,                  //仅存在本地状态
		OnlyNetWorkState,                //仅存在网络状态

		BothExistState ,                  //网络及本地状态存在
	};

	//文件路径类型，网络文件与本地文件一对一类型
	class _YVFilePath
	{
	public:
		_YVFilePath(const char* path, const char* url);
		//获取本地路径
		std::string& getLocalPath();
		//获取网络路径
		std::string& getUrlPath();
		//设置本地路径
		void setLocalPath(std::string& path);
		//设置网络路径
		void setUrlPath(std::string& url);
		//获取文件状态
		YVFileState getState();
		//获取文件唯一id(自己分配的与SDK无关)
		uint64 getPathId();
		//设置文件状态
		void setState(YVFileState);
	private:
		YVFileState m_state;	//文件状态类型
		uint64 m_id;            //文件随机分配的唯一的id.不重复
		std::string m_path;		//本地地址
		std::string m_url;      //网络地址
	};

	//申明为知能指针
	WISDOM_PTR(_YVFilePath, YVFilePathPtr);
}
#endif
