#ifndef _YVIM_CMDDEF_H__
#define _YVIM_CMDDEF_H__




//云消息SOURCE
#define  CLOUDMSG_SYSTEM          "SYSTEM"
#define  CLOUDMSG_PUSH            "PUSH"
#define  CLOUDMSG_FRIEND          "P2P"
#define  CLOUDMSG_GROUP           "GROUP"

//云消息确认
#define /*uint32*/	  CLOUDMSG_ID				110				//云消息ID
#define /*string*/	  CLOUDMSG_SOURCE			111				//云标示符 {来源ID SYSTEM系统消息  PUSH 推送消息}



/*******************************登录模块********************************
*
*			              模块类型: IM_LOGIN
*
***********************************************************************/

//云娃登录请求
#define IM_LOGIN_REQ                    0x11000
namespace x11000{
	enum{
		/*uint32*/	 userid				= 1, //用户ID
		/*string*/	 pwd				= 2, //用户密码
		/*string*/	 pgameServiceID		= 3, //游戏服务ID
		/*string[]*/ wildCard			= 4, //通配符
		/*uint8*/    readstatus         = 5, //通知读取确认： 1为需要确认
	};
}

//云娃登录返回
#define IM_LOGIN_RESP	                 0x11001
namespace x11001 {
	enum {
		/*uint32*/ result		= 1, //返回结果 不为0即为失败
		/*string*/ msg			= 2, //错误描述
		/*string*/ nickname		= 4, //用户昵称
		/*uint32*/ userId		= 5, //用户ID
		/*string*/ iconurl		= 6, //用户图像地址
	};
}


//cp账号登录请求
#define IM_THIRD_LOGIN_REQ				  0x11002
namespace x11002{
	enum{
		/*string*/	 tt					= 1, //cp登录凭证
		/*string*/	 pgameServiceID		= 2, //游戏服务ID
		/*string[]*/ wildCard			= 3, //通配符
		/*uint8*/    readstatus         = 4, //通知读取确认： 1为需要确认
	};
}

//cp账号登录返回
#define IM_THIRD_LOGIN_RESP				   0x11003
namespace x11003 {
	enum {
		/*uint32*/ result			= 1, //返回结果 不为0即为失败
		/*string*/ msg				= 2, //错误描述
		/*uint32*/ userid			= 3, //云娃ID
		/*string*/ nickName			= 4, //用户昵称
		/*string*/ iconUrl			= 5, //用户图像地址
		/*string*/ thirdUserId		= 6, //第三方用户ID
		/*string*/ thirdUserName	= 7, //第三方用户名
	};

}

//注销
#define IM_LOGOUT_REQ	                    0x11004
namespace x11004
{
}


//设置设备信息
#define IM_DEVICE_SETINFO                   0x11012
namespace x11012 {
	enum {
		/*string*/ imsi        = 1,
		/*string*/ imei        = 2,
		/*string*/ mac         = 3,
		/*string*/ appVersion  = 4,
		/*string*/ networkType = 5,
	};
}

//重连成功通知
#define IM_RECONNECTION_NOTIFY               0x11013
namespace x11013 {
	enum {
		/*uint32*/ userid        = 1,
	};
}

//获取第三方账号信息
#define IM_GET_THIRDBINDINFO_REQ             0x11014
namespace x11014 {
	enum {
		/*uint32*/ appid        = 1,   
		/*string*/ uid          = 2, 
	};
}

#define IM_GET_THIRDBINDINFO_RESP            0x11015
namespace x11015 {
	enum {
		/*uint32*/ result		= 1, 
		/*string*/ msg			= 2, 
		/*uint32*/ yunvaid      = 3,   
		/*string*/ nickname     = 4,
		/*string*/ iconUrl      = 5,
		/*string*/ level        = 6,
		/*string*/ vip          = 7,
		/*string*/ ext          = 8,
	};
}

enum yv_net
{
	yv_net_disconnect = 0,
	yv_net_connect = 1,
};

//网络状态通知
#define IM_NET_STATE_NOTIFY                  0x11016
namespace x11016 {
	enum {
		/*uint8*/ state        = 1,     //yv_net
	};
}

/*******************************好友模块********************************
*
*			              模块类型: IM_FRIEND
*
***********************************************************************/


namespace  xUserInfo {
	enum{
		/*string*/ nickname  = 1, //用户昵称
		/*uint32*/ userid    = 2, //用户ID
		/*string*/ iconurl   = 3, //用户图像地址
		/*uint8*/  online    = 4, //是否在线
		/*string*/ userlevel = 5, //用户等级
		/*string*/ viplevel  = 6, //vip等级
		/*string*/ ext       = 7, //扩展字段
		/*uint8*/  shieldmsg = 8, //是否屏蔽聊天消息
		/*uint8*/  sex       = 9, //性别
		/*string*/ group     = 10, //所在组名称
		/*string*/ remark    = 11, //备注
	};
}

namespace xSearchInfo{
	enum{
		/*uint32*/ yunvaId  = 1,
		/*string*/ userId   = 2, //用户ID
		/*string*/ nickName = 3, //用户昵称
		/*string*/ iconUrl  = 4, //用户图像地址
		/*string*/ level    = 5, //用户等级
		/*string*/ vip      = 6, //用户VIP等级
		/*string*/ Ext      = 7, //扩展字段
	};
}




//请求添加好友
#define IM_FRIEND_ADD_REQ        0x12000
namespace x12000 {
	enum  {
		/*uint32*/    userid = 1, //用户ID
		/*string*/    greet  = 2, //问候语
	};
}

//请求添加好友消息发送回应
#define IM_FRIEND_ADD_RESPOND    0x12025
namespace x12025 {
	enum  {
		/*uint32*/		result   = 1, 
		/*string*/		msg		 = 2,
		/*uint32*/      userid   = 3,
	};
}

//好友请求通知
#define IM_FRIEND_ADD_NOTIFY    0x12002
namespace x12002 {
	enum {
		/*uint32*/ userid = 1,  //用户ID
		/*string*/ name   = 3,  //用户名称
		/*string*/ greet  = 2,  //问候语
		/*string*/ sign   = 4,	//签名
		/*string*/ url    = 5,  //头像地址
	};
}

//（同意/拒绝）添加好友
#define IM_FRIEND_ADD_ACCEPT   0x12003 
namespace x12003 {
	enum {
		/*uint32*/  userid = 1, //用户ID
		/*uint8*/	affirm = 2,	//是否同意 e_addfriend_affirm
		/*string*/	greet  = 3,	//问候语
	};
}

//（同意/拒绝）添加好友回应
#define IM_FRIEND_ACCEPT_RESP   0x12004
namespace x12004 {
	enum {
		/*uint32*/ result = 4,    
		/*string*/ msg	  = 5,     
		/*uint32*/ userid = 1, //用户ID
		/*uint8*/  affirm = 2, //是否同意 e_addfriend_affirm
		/*string*/ greet  = 3, //问候语
	};
}

enum e_addfriend_affirm{
	af_refuse    = 0, //拒绝
	af_agree     = 1, //同意加好友(单项)
	af_agree_add = 2, //同意加好友并加对方为好友(双向)
};

//（同意/拒绝）添加好友通知
#define IM_FRIEND_ADD_RESP      0x12001
namespace x12001 {
	enum {
		/*uint32*/ affirm = 1, //返回结果 e_addfriend_affirm
		/*uint32*/ userid = 2, //用户ID
		/*string*/ name   = 3, //用户名称
		/*string*/ url    = 4, //头像地址
		/*string*/ greet  = 5, //问候语
	};
}

enum e_delfriend{
	df_exit_in_list     = 0, //只从我的好友列表中删除
	df_remove_from_list = 1, //双向删除
};

//删除好友请求
#define IM_FRIEND_DEL_REQ   0x12005
namespace x12005 {
	enum {
		/*uint32*/	del_friend	= 1,  //删除好友id
		/*uint8*/   act         = 2,  //动作 e_delfriend
	}; 
}

//删除好友返回
#define IM_FRIEND_DEL_RESP  0x12006  
namespace x12006 {
	enum {
		/*uint32*/		result			= 1,    
		/*string*/		msg				= 2,     
		/*uint32*/	    del_friend		= 3,  //删除好友id
		/*uint8*/       act				= 4,  //动作 e_delfriend
	};
}


//删除好友通知
#define IM_FRIEND_DEL_NOTIFY   0x12007
namespace x12007 {
	enum {
		/*uint32*/ del_friend	 = 1, //删除好友id
		/*uint8*/  del_fromlist  = 2, //从自己的好友列表中删除 e_delfriend该枚举字段
	};
}

//搜索好友请求
#define IM_FRIEND_SEARCH_REQ   0x12018  
namespace x12018 {
	enum {
		/*string*/ keyworld = 1, //搜索关键字
		/*uint32*/ start    = 2, //搜索起始位置
		/*uint32*/ count    = 3, //返回结果总数
	};
}

//搜索好友回应
#define IM_FRIEND_SEARCH_RESP  0x12019  
namespace x12019 {
	enum {
		/*uint32*/		result   = 1, //结果信息
		/*string*/		msg		 = 2, //错误信息
		/*xSearchInfo[]*/ userinfo = 3, //用户信息
	};
}

//推荐好友
#define IM_FRIEND_RECOMMAND_REQ     0x12008  
namespace x12008 {
	enum {
		/*uint32*/ start = 1, //结果列表的起始位置
		/*uint32*/ count = 2, //返回结果条目
	};
}

//推荐好友回应
#define IM_FRIEND_RECOMMAND_RESP   0x12009
namespace x12009 {
	enum {
		/*uint32*/		result   = 1, //结果信息
		/*string*/		msg		 = 2, //错误信息
		/*xSearchInfo[]*/ userinfo = 3, //用户信息
	};
}

//操作好友黑名单
enum e_oper_friend_act
{
	oper_add_blacklist = 3, //加入黑名单
	oper_del_blacklist = 4, //删除黑名单
};

//好友操作请求(黑名单) 
#define IM_FRIEND_OPER_REQ		   0x12010
namespace x12010 {
	enum {
		/*uint32*/	userId = 1,	//操作者ID()
		/*uint32*/	operId = 2,	//被操作ID（要加入黑名单的id）
		/*uint8*/	act	   = 3,	//动作   oper_friend_act
	};
}

//好友操作回应(黑名单)
#define IM_FRIEND_OPER_RESP	    	 0x12011
namespace x12011 {
	enum {
		/*uint32*/	result		= 5,
		/*string*/	msg			= 6, 
		/*uint32*/	userId 		= 1, //用户ID
		/*uint32*/	operId		= 2, //操作ID
		/*uint8*/	act			= 3, //动作
		/*uint8*/	oper_state	= 4, //对方状态
	};
}

//好友列表查询请求
#define IM_FRIEND_LIST_REQ		     0x12028
namespace x12028{
	enum {
		/*string*/ group        = 1, //所在组名称，不填表示包括所有组
	};
}

//好友列表查询回应
#define IM_FRIEND_LIST_RESP		     0x12029
namespace x12029{
	enum {
		/*uint32*/		result	 = 2,
		/*string*/		msg		 = 3,
		/*xUserInfo[]*/ userinfo = 1, //用户信息
	};
}

//好友列表推送
#define IM_FRIEND_LIST_NOTIFY		  0x12012  
namespace x12012{
	enum {
		/*xUserInfo[]*/ userinfo = 1, //用户信息
	};
}

//黑名单列表查询请求
#define IM_FRIEND_BLACKLIST_REQ		     0x12030
namespace x12030{
	enum {
		
	};
}

//黑名单列表查询回应
#define IM_FRIEND_BLACKLIST_RESP		 0x12031
namespace x12031{
	enum {
		/*uint32*/		result	 = 2,
		/*string*/		msg		 = 3,
		/*xUserInfo[]*/ userinfo = 1, //用户信息
	};
}

//黑名单列表推送
#define IM_FRIEND_BLACKLIST_NOTIFY       0x12013
namespace x12013 {
	enum {
		/*xUserInfo[]*/ userinfo = 1, //用户信息
	};
}


namespace xNearChatInfo
{
	enum
	{
		/*string*/ nickname  = 1, //用户昵称
		/*uint32*/ userid    = 2, //用户ID
		/*string*/ iconurl   = 3, //用户头像地址
		/*uint8*/  online    = 4, //是否在线
		/*string*/ userlevel = 5, //用户等级
		/*string*/ viplevel  = 6, //用户VIP等级
		/*string*/ ext       = 7, //扩展字段
		/*uint8*/  shieldmsg = 8, //是否屏蔽聊天消息
		/*uint8*/  sex       = 9, //性别
		/*string*/ group     = 10, //所在组名称
		/*string*/ remark    = 11, //备注
		/*uint32*/ times     = 12, //最近聊天时间,单位（秒）
	};
}


namespace xRecentConactList {
	enum e_recent_user {
		/*uint32*/        endId    = 1, //结束索引
		/*uint32*/        unread   = 2, //未读消息数
		/*xP2PChatMsg*/   msg      = 3, //最后一条消息
		/*xNearChatInfo*/ user     = 4,
	};
}

//最近联系人列表查询请求
#define IM_FRIEND_NEARLIST_REQ		0x12032
namespace x12032{
	enum {
		
	};
}

//最近联系人列表查询回应
#define IM_FRIEND_NEARLIST_RESP		0x12033
namespace x12033{
	enum {
		/*uint32*/		result	 = 2,
		/*string*/		msg		 = 3,
		/*xRecentConactList[]*/ recent = 1, //用户信息
	};
}

/*最近联系人推送*/
#define IM_FRIEND_NEARLIST_NOTIFY    0x12014
namespace x12014 {
	enum {
		/*xRecentConactList[]*/  recent = 1, //最近联系人 xRecentConactList
	};
}

//好友状态
enum e_friend_status{
	fs_offline = 0, //下线
	fs_online  = 1, //在线						
};

/*好友状态推送*/
#define IM_FRIEND_STATUS_NOTIFY    0x12015
namespace x12015 {
	enum {
		/*uint32*/ userid = 1, //用户ID
		/*uint8*/  status = 2, //好友状态 e_status
	};
}

//设置好友信息
#define IM_FRIEND_INFOSET_REQ	0x12016
namespace x12016 {
	enum {
		/*uint32*/	friendId = 1, //好友ID
		/*string*/	group	 = 2, //好友所在组
		/*string*/	note	 = 3, //好友备注
	};
}

#define IM_FRIEND_INFOSET_RESP	0x12017
namespace x12017 {
	enum {
		/*uint32*/	result		= 4, //				
		/*string*/	msg			= 5, //错误信息
		/*uint32*/	friendId	= 1, //好友ID				
		/*string*/	group		= 2, //好友所在组
		/*string*/	note		= 3, //好友备注
	};
}

//获取个人信息
#define IM_USER_GETINFO_REQ       0x12020
namespace x12020 {
	enum {
		/*uint32*/ userid = 1, //用户ID
	};
}

#define IM_USER_GETINFO_RESP      0x12021
namespace x12021 {
	enum {
		/*uint32*/  result    = 8,
		/*string*/  msg		  = 9,     //错误消息
		/*uint32*/  userid    = 1,
		/*uint8*/   sex       = 2,     //性别
		/*string*/  nickname  = 3,     //昵称
		/*string*/  headicon  = 4,     //图像地址
		/*string*/  userlevel = 5,     //用户等级
		/*string*/  viplevel  = 6,     //用户VIP等级
		/*string*/  ext       = 7,     //扩展字段
	};
}

//修改个人信息
#define IM_USER_SETINFO_REQ      0x12022
namespace x12022 {
	enum{
		/*string*/  userid    = 1,      //cp用户id
		/*string*/  nickname  = 2,      //用户昵称
		/*string*/  iconurl   = 3,      //用户图像地址
		/*string*/  userlevel = 4,      //用户等级
		/*string*/  viplevel  = 5,      //vip等级
		/*string*/  ext       = 6,      //扩展字段
	};
}

//修改个人信息响应
#define IM_USER_SETINFO_RESP      0x12023
namespace x12023 {
	enum{
		/*uint32*/		result   = 1, 
		/*string*/		msg		 = 2,
	};
}

//好友个人信息修改通知
#define IM_USER_SETINFO_NOTIFY      0x12024
namespace x12024 {
	enum{
		/*uint32*/  userid    = 1, //yunva id
		/*string*/  nickname  = 2, //用户昵称
		/*string*/  iconurl   = 3, //用户图像地址
		/*string*/  userlevel = 4, //用户等级
		/*string*/  viplevel  = 5, //vip等级
		/*string*/  ext       = 6, //扩展字段
	};
}

//删除最近联系人
#define IM_REMOVE_CONTACTES_REQ      0x12026
namespace x12026 {
	enum{
		/*uint32[]*/  userid    = 1,      //删除用户id数组
	};
}

//删除最近联系人响应
#define IM_REMOVE_CONTACTES_RESP      0x12027
namespace x12027 {
	enum{
		/*uint32*/		  result    = 1, 
		/*string*/		  msg       = 2,
		/*uint32*/        userid    = 3,  
	};
}

/*******************************群模块********************************
*
*			              模块类型: IM_GROUPS
*
**********************************************************************/

namespace xGroupUser {
	enum {
		/*uint32*/	userId			= 1, //用户ID
		/*string*/  nickname        = 2, //用户昵称
		/*string*/  iconurl         = 3, //头像
		/*uint8*/   sex             = 4, //性别
		/*string*/	alias			= 5, //名片
		/*uint8*/	role			= 6, //角色
		/*uint8*/	level			= 7, //等级
		/*uint32*/	grade			= 8, //积分
		/*uint32*/	lately_online	= 9, //最后一次上线时间
		/*uint8*/	online			= 10, //是否在线
	};
}

//群用户列表
#define IM_GROUP_USERLIST_NOTIFY  0x13000
namespace x13000 {
	enum {
		/*uint32*/     groupid     = 1, //群ID
		/*object[]*/   xGroupUser  = 2,	//用户列表
	};
}

//修改资料通知
#define IM_GROUP_USERMDY_NOTIFY	   0x13001
namespace x13001 {
	enum {
		/*uint32*/  groupid         = 1, //群ID
		/*uint32*/	userId			= 2, //用户ID				
		/*string*/	name			= 3, //群名称
		/*string*/	icon			= 4, //群图标
		/*string*/	announcement	= 5, //群公告
		/*uint8*/	verify			= 6, //验证方式
		/*string*/	alias			= 8, //名片修改
	};
}

enum e_groupverify {
	gv_allow	  =	1,	//不需要验证
	//gv_answer	  =	2,	//答题验证
	gv_audit	  =	3,	//管理员审核
	gv_not_allow  =	4,	//群不允许加入
};

//创建群
#define IM_GROUP_CREATE_REQ     0x13002   
namespace x13002 {
	enum {
		/*uint32*/ verify     = 1, //群验证方式 e_groupverify
		/*string*/ name       = 2, //群名称
		/*string*/ iconUrl    = 3, //群头像
	};
}

/*创建群回应*/
#define IM_GROUP_CREATE_RESP      0x13003
namespace x13003 {
	enum {
		/*uint32*/ result  = 1, //创建结果
		/*string*/ msg     = 2, //错误信息
		/*uint32*/ groupid = 3, //群ID
	};
}

/*搜索群*/
#define IM_GROUP_SEARCH_REQ     0x13004   
namespace x13004 {
	enum {
		/*uint32*/ groupid = 1, //群ID
	};
}

/*搜索群回应*/
#define IM_GROUP_SEARCH_RESP      0x13005
namespace x13005 {
	enum {
		/*uint32*/ result       = 1, //结果信息
		/*string*/ msg          = 2, //错误信息
		/*uint32*/ groupid      = 3, //群ID
		/*uint32*/ verify       = 4, //群验证方式
		/*string*/ name         = 5, //群名称
		/*string*/ iconurl      = 6, //图标
		/*uint32*/ numbercount  = 7, //总共人数
		/*uint32*/ currentnum   = 8, //加入人数
		/*uint32*/ ownerid      = 9, //拥有者ID
		/*string*/ announcement = 10,//群宣言

	};
}

//加入群
#define IM_GROUP_JOIN_REQ        0x13006 
namespace x13006  {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*string*/ greet   = 2, //问候语
	};
}

enum e_joingroup {
	jg_refuse = 0, //拒绝
	jg_agree  = 1, //同意
};

/*申请加群返回结果通知*/
#define IM_GROUP_JOIN_RESP      0x13009
namespace x13009 {
	enum {
		/*uint32*/ result    = 1, //创建结果          
		/*string*/ msg       = 2, //错误信息
		/*uint32*/ groupid   = 3, //群ID
		/*uint32*/ userid    = 4, //用户ID
		/*uint8*/  agree     = 5, //是否同意加入群 e_joingroup
		/*string*/ groupname = 6, //群名称
		/*string*/ greet     = 7, //问候语
		/*string*/ iconurl   = 8, //用户头像地址
	};
}

//加入群通知
#define IM_GROUP_JOIN_NOTIFY    0x13007   
namespace x13007 {
	enum {
		/*uint32*/ groupid   = 1, //群ID
		/*uint32*/ userid    = 2, //用户ID
		/*string*/ username  = 3, //用户名
		/*string*/ groupname = 4, //群名
		/*string*/ greet     = 5, //问候语
		/*string*/ iconurl   = 6, //图像地址
	};
}

//同意拒绝加群
#define IM_GROUP_JOIN_ACCEPT    0x13008
namespace x13008 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //用户（申请者）ID
		/*uint8*/  agree   = 3, //e_joingroup 
		/*string*/ greet   = 4, //拒绝原因
	};
}

#define IM_GROUP_JOIN_ACCEPT_RESP    0x13040
namespace x13040 {
	enum {
		/*uint32*/ result	= 1,	
		/*string*/ msg		= 2,	
		/*uint32*/ groupid  = 3,	//群ID
		/*uint32*/ userid   = 4,	//用户（申请者）ID
		/*string*/ reason	= 5,	//原因
	};
}

//退群
#define IM_GROUP_EXIT_REQ     0x13010
namespace x13010 {
	enum {
		/*uint32*/ groupid = 1, //群ID
	};
}

//退群响应
#define IM_GROUP_EXIT_RESP   0x13011
namespace x13011 {
	enum {
		/*uint32*/ result  = 1, //结果信息 
		/*string*/ msg     = 2, //错误信息  
		/*uint32*/ groupid = 3, //群名称
		/*uint32*/ userid  = 4, //用户ID
	};
}

//退群通知
#define IM_GROUP_EXIT_NOTIFY   0x13012
namespace x13012 {
	enum {
		/*uint32*/ groupid = 1, //群名称
		/*uint32*/ userid  = 2, //用户ID
	};
}

//群离线消息设置
enum e_group_msg{
	gm_nosupport = 0, //不支持
	gm_support   = 1, //支持
};

/*修改群属性*/
#define IM_GROUP_MODIFY_REQ  0x13013   
namespace x13013 {
	enum {
		/*uint32*/ groupid      = 1, //群ID
		/*string*/ name         = 2, //群名称
		/*string*/ icon         = 3, //群图标
		/*string*/ announcement = 4, //群公告
		/*uint8*/  verify       = 5, //验证方式
		/*uint8*/  msg_set      = 6, //群消息设置
		/*string*/ alias        = 8,//名片修改
	};
}

//修改群属性响应
#define IM_GROUP_MODIFY_RESP  0x13014  
namespace x13014 {
	enum {
		/*uint32*/ result       = 1, //结果信息         
		/*string*/ msg          = 2, //错误信息   
		/*uint32*/ groupid      = 3, //群ID 
		/*string*/ name         = 4, //群名称
		/*string*/ icon         = 5, //群图标
		/*string*/ announcement = 6, //群公告
		/*uint8*/  verify       = 7, //验证方式
		/*uint8*/  msg_set      = 8, //群消息设置
		/*string*/ alias        = 10,//名片修改
	};
}

//转移群主请求
#define IM_GROUP_SHIFTOWNER_REQ   0x13015
namespace x13015 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //用户ID
	};
}

//转移群主通知
#define IM_GROUP_SHIFTOWNER_NOTIFY   0x13016
namespace x13016 {
	enum {

		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //用户ID
		/*uint32*/ shiftid = 3, //转移对象
	};
}

//转移群主响应
#define IM_GROUP_SHIFTOWNER_RESP   0x13017
namespace x13017 {
	enum {
		/*uint32*/ result  = 1, //结果信息      
		/*string*/ msg     = 2, //错误信息
		/*uint32*/ groupid = 3, //群ID
		/*uint32*/ userid  = 4, //用户ID
		/*uint32*/ shiftid = 5,  //转移对象
	};
}

//踢除群成员
#define IM_GROUP_KICK_REQ    0x13018
namespace x13018 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //用户ID
	};
}

//踢除群成员通知
#define IM_KGROUP_KICK_NOTIFY   0x13019
namespace x13019 {
	enum {
		/*uint32*/ groupid   = 1, //群ID
		/*uint32*/ userid    = 2, //用户ID
		/*uint32*/ kickid    = 3, //被踢成员ID
		/*string*/ groupname = 4, //群名称
	};
}

#define IM_GROUP_KICK_RESP   0x13020
namespace x13020 {
	enum {
		/*uint32*/ result  = 1, //结果信息     
		/*string*/ msg     = 2, //错误信息
		/*uint32*/ groupid = 3, //群ID
	};
}

//邀请好友入群
#define IM_GROUP_INVITE_REQ   0x13021
namespace x13021 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //被邀请用户ID
		/*string*/ greet   = 3, //问候语
	};
}

//邀请好友入群回应
#define IM_GROUP_INVITE_RESPON   0x13041
namespace x13041 {
	enum {
		/*uint32*/ result  = 1, //结果信息     
		/*string*/ msg     = 2, //错误信息
		/*uint32*/ groupid = 3, //群ID
		/*uint32*/ invitedid  = 4, //被邀请用户ID
	};
}

//被邀请入群通知
#define IM_GROUP_INVITE_NOTIFY   0x13022
namespace x13022 {
	enum {
		/*uint32*/ groupid    = 1, //群ID
		/*uint32*/ inviteid   = 2, //邀请用户ID
		/*string*/ invitename = 3, //邀请用户名
		/*string*/ greet      = 4, //问候语
		/*string*/ groupname  = 5, //群名称
		/*string*/ groupicon  = 6, //群图像地址
	};
}

//被邀请用户返回结果
enum e_group_invite {
	gi_refuse = 0, //拒绝
	gi_agree  = 1, //同意
};

//被邀请者同意或拒绝群邀请
#define IM_GROUP_INVITE_ACCEPT   0x13023
namespace x13023 {
	enum {
		/*uint32*/ groupid	 = 4, //群ID
		/*string*/ invitename = 5, //邀请用户名
		/*uint32*/ inviteid  = 1, //邀请用户ID
		/*uint32*/ agree     = 2, //是否同意入群 e_group_invite
		/*string*/ greet     = 3, //问候语
	};
}

//被邀请者同意或拒绝群邀请响应
#define IM_GROUP_INVITE_ACCEPT_RESP   0x13042
namespace x13042 {
	enum {
		/*uint32*/ result    = 1, //结果信息      
		/*string*/ msg       = 2, //错误信息
		/*uint32*/ groupid   = 3, //群ID
		/*uint32*/ inviteid  = 4, //邀请用户ID
	};
}

//被邀请者同意或拒绝群邀请通知
#define IM_GROUP_INVITE_RESP   0x13024
namespace x13024 {
	enum {
		/*uint32*/ groupid   = 3, //群ID
		/*uint32*/ inviteid  = 4, //被邀请用户ID
		/*string*/ groupname = 5, //群名称
		/*uint8*/  agree     = 6, //是否同意入群 e_group_invite
		/*string*/ greet	 = 7, //问候语
	};
}

//群成员角色
enum e_group_role{
	gr_owners	= 2, //群所有者
	gr_admin	= 3, //群管理者
	gr_number	= 4, //群成员
	gr_visitor	= 10,//群游客
};

//设置群成员角色请求
#define IM_GROUP_SETROLE_REQ   0x13025
namespace x13025 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //用户ID
		/*uint8*/  role    = 3, //用户角色  e_group_role
	};
}

//设置群成员角色返回
#define IM_GROUP_SETROLE_RESP    0x13026
namespace x13026 {
	enum {
		/*uint32*/ result  = 1, //结果信息     
		/*string*/ msg     = 2, //错误信息
		/*uint32*/ groupid = 3, //群ID
	};
}

//设置群成员角色通知
#define IM_GROUP_SETROLE_NOTIFY    0x13027
namespace x13027 {
	enum {
		/*uint32*/ groupid  = 1, //群ID
		/*uint32*/ operid   = 2, //操作者ID
		/*uint32*/ byuserid = 3, //被操作者ID
		/*uint32*/ role     = 4, //修改后角色 e_group_role
	};
}

//解散群请求
#define IM_GROUP_DISSOLVE_REQ  0x13028
namespace x13028 {
	enum {
		/*uint32*/ grouid = 1, //群ID
	};
}

//解散群响应
#define IM_GROUP_DISSOLVE_RESP  0x13029
namespace x13029 {
	enum {
		/*uint32*/ result = 1, //结果信息       
		/*string*/ msg    = 2, //错误信息
		/*uint32*/ grouid = 3, //群ID
	};
}

//管理员修改他人名片
#define IM_GROUP_SETOTHER_REQ   0x13030
namespace x13030 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //用户ID
		/*string*/ alias   = 3, //用户名片
	};
}

//修改他人名片通知
#define IM_GROUP_SETOTHER_NOTIFY   0x13031
namespace x13031 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //用户ID
		/*string*/ alias   = 3, //用户名片
	};
}

//修改他人名片返回
#define IM_GROUP_SETOTHER_RESP   0x13032
namespace x13032 {
	enum {
		/*uint32*/ result  = 1, //结果信息     
		/*string*/ msg     = 2, //错误信息
		/*uint32*/ groupid = 3, //群ID
	};
}

//群属性通知(群列表)
#define IM_GROUP_PROPERTY_NOTIFY   0x13033
namespace x13033 {
	enum {
		/*uint32*/  groupid         = 1, //群ID
		/*string*/	name			= 2, //群名称
		/*string*/	icon			= 3, //群图标
		/*string*/	announcement	= 4, //群公告
		/*uint8*/	level			= 5, //群等级
		/*uint8*/	verify			= 6, //验证方式
		/*uint32*/	number_limit	= 7, //人数限制
		/*uint32*/	owner			= 8, //群所有者
		/*uint8*/	msg_set			= 9, //群消息设置
		/*uint32*/  user_count      = 11,//当前用户数
		/*uint8*/   role            = 12,//我在群中的角色

	};
}

enum group_member_online {
	gm_status_online = 1,    //在线
};

//群成员上线
#define IM_GROUP_MEMBER_ONLINE   0x13034
namespace x13034 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*uint32*/ userid  = 2, //用户ID
		/*uint8*/  online  = 3, //用户是否在线 group_member_online
	};
}

//新成员加入群
#define IM_GROUP_USERJOIN_NOTIFY  0x13035
namespace x13035 {
	enum {
		/*uint32*/     groupid = 1, //群ID
		/*xGroupUser*/ xUser   = 2, //用户信息
	};
}




/*******************************聊天模块********************************
*
*			              模块类型: IM_CHAT
*
* 注：非好友可以通过对方ID发送消息
*
***********************************************************************/

//好友聊天-文本
#define IM_CHAT_FRIEND_TEXT_REQ  0x14000
namespace x14000 {
	enum {
		/*uint32*/ userid = 1, //好友ID
		/*string*/ data   = 2, //消息内容
		/*string*/ ext    = 3, //扩展字段(可不传)
		/*string*/ flag   = 4, //消息标记(可不传)
	};
}

//好友聊天-图像
#define IM_CHAT_FRIEND_IMAGE_REQ  0x14001
namespace x14001 {
	enum {
		/*uint32*/ userid = 1, //好友ID
		/*string*/ image  = 2, //图片路径
		/*string*/ ext    = 3, //扩展字段(可不传)
		/*string*/ flag   = 4, //消息标记(可不传)
	};
}


//好友聊天 - 语音
#define IM_CHATI_FRIEND_AUDIO_REQ  0x14002
namespace x14002 {
	enum {
		/*uint32*/ userid = 1, //好友ID
		/*string*/ file   = 2, //语音文件路径(可以是语音文件URL)
		/*uint32*/ time   = 3, //文件播放时长(秒)
		/*string*/ txt    = 4, //附带文本(可选)
		/*string*/ ext    = 5, //扩展字段(可不传)
		/*string*/ flag   = 6, //消息标记(可不传)
	};
}

enum e_chat_msgtype {
	chat_msgtype_image = 0, //图像文件
	chat_msgtype_audio = 1, //音频文件
	chat_msgtype_text  = 2, //文本消息
};

//好友聊天通知
#define IM_CHAT_FRIEND_NOTIFY 0x14003
namespace x14003 {
	enum {
		/*uint32*/ userid    = 1,  //好友ID
		/*string*/ name      = 2,  //好友名称
		/*string*/ signature = 3,  //好友签名
		/*string*/ headurl   = 4,  //头像地址
		/*uint32*/ sendtime  = 5,  //发送时间
		/*uint8*/  type      = 6,  //类型 e_chat_msgtype
		/*string*/ data      = 7,  //若为文本类型，则是消息内容，若为音频，则是文件url，若为图像，则是大图像地址
		/*string*/ imageurl  = 8,  //若为图片，则是小图像地址
		/*uint32*/ audiotime = 9,  //若为音频文件, 则是文件播放时长(秒)
		/*string*/ attach    = 10, //若为音频文件，则是附加文本(没有附带文本时为空)
		/*string*/ ext1      = 11, //扩展字段

	};
}



#define IM_CHATT_FRIEND_RESP  0x14004
namespace x14004 {
	enum {
		/*uint32*/ result       = 1,  
		/*string*/ msg          = 2, 
		/*uint8*/  type         = 3,
		/*uint32*/ userid       = 4,   //好友ID
		/*string*/ flag         = 5,
		/*uint32*/ indexid      = 6,   //消息索引id
		/*string*/ text         = 7,   //文字内容
		/*string*/ audiourl     = 8,   //录音文件url
		/*uint32*/ audiotime    = 9,   //录音时长
		/*string*/ imageurl1    = 10,  //图片原图
		/*string*/ imageurl2    = 11,  //缩略图
		/*string*/ ext1         = 13,  //扩展字段
	};
}

//群聊 - 文本
#define IM_CHAT_GROUP_TEXT_REQ    0x14006
namespace x14006 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*string*/ text    = 2, //文本消息
		/*string*/ ext     = 3, //扩展字段
		/*string*/ flag	   = 4, //消息标记(可不传)
	};
}

//群聊 -  图片
#define IM_CHAT_GROUP_IMAGE_REQ    0x14007
namespace x14007 {
	enum {
		/*uint32*/ groupid  = 1, //群ID
		/*string*/ image    = 2, //图像路径
		/*string*/ ext      = 3, //扩展字段
		/*string*/ flag		= 4, //消息标记(可不传)
	};
}

//群聊 - 语音
#define IM_CHATA_GROUP_AUDIO_REQ    0x14008
namespace x14008 {
	enum {
		/*uint32*/ groupid = 1, //群ID
		/*string*/ file    = 2, //音频文件路径
		/*uint32*/ time    = 3, //音频文件播放时长(秒)
		/*string*/ txt     = 4, //附带文本(可选)
		/*string*/ ext     = 5, //扩展字段
		/*string*/ flag	   = 6, //消息标记(可不传)
	};
}

//群聊天推送
#define IM_CHAT_GROUP_NOTIFY    0x14009
namespace x14009 {
	enum {
		/*uint32*/ groupid   = 1, //群ID
		/*uint32*/ sendid    = 2, //发送者ID
		/*uint32*/ time      = 3, //发送时间
		/*string*/ groupicon = 4, //群头像地址
		/*string*/ groupname = 5, //群名称
		/*uint8*/  type      = 6, //消息类型 e_chat_msgtype
		/*string*/ data      = 7, //若为文本类型，则是消息内容，若为音频，则是文件地址，若为图像，则是大图像地址
		/*string*/ imageurl  = 8, //若为图片，则是小图像地址
		/*uint32*/ audiotime = 9, //若为音频文件, 则为文件播放时长(秒)
		/*string*/ attach    = 10, //若为音频文件，则为附加文本(没有附带文本时为空)
		/*string*/ ext1      = 11, //扩展字段

	};
}

//群聊消息发送响应
#define IM_CHAT_GROUPMSG_RESP  0x14010
namespace x14010 {
	enum {
		/*uint32*/ result       = 1,  
		/*string*/ msg          = 2, 
		/*uint32*/ groupid      = 3, //群ID
		/*uint32*/ index        = 4, //消息序号
	};
}




/*******************************离线云消息模块********************************
*
*			              模块类型: IM_CLOUND
*
****************************************************************************/

//好友离线结构
namespace xP2PChatMsg
{
	enum
	{
		/*uint32*/ userid    = 1,  //好友ID
		/*string*/ name      = 2,  //好友名称
		/*string*/ signature = 3,  //好友签名
		/*string*/ headurl   = 4,  //头像地址
		/*uint32*/ sendtime  = 5,  //发送时间
		/*uint8*/  type      = 6,  //类型 e_chat_msgtype
		/*string*/ data      = 7,  //若为文本类型，则是消息内容，若为音频，则是文件url，若为图像，则是大图像地址
		/*string*/ imageurl  = 8,  //若为图片，则是小图像地址
		/*uint32*/ audiotime = 9,  //若为音频文件, 则是文件播放时长(秒)
		/*string*/ attach    = 10, //若为音频文件，则是附加文本(没有附带文本时为空)
		/*string*/ ext1      = 11, //扩展字段
	};
}

//群离线结构
namespace xGroupChatMsg
{
	enum {
		/*uint32*/ groupid   = 1, //群ID
		/*uint32*/ sendid    = 2, //发送者ID
		/*uint32*/ time      = 3, //发送时间
		/*string*/ groupicon = 4, //群头像地址
		/*string*/ groupname = 5, //群名称
		/*uint8*/  type      = 6, //消息类型 e_chat_msgtype
		/*string*/ data      = 7, //若为文本类型，则是消息内容，若为音频，则是文件地址，若为图像，则是大图像地址
		/*string*/ imageurl  = 8, //若为图片，则是小图像地址
		/*uint32*/ audiotime = 9, //若为音频文件, 则为文件播放时长(秒)
		/*string*/ attach    = 10, //若为音频文件，则为附加文本(没有附带文本时为空)
		/*string*/ ext1      = 11, //扩展字段
	};
}

//云消息通知
#define IM_CLOUDMSG_NOTIFY           0x15002
namespace x15002 {
	enum {
		/*string*/ source  = 1, //来源
		/*uint32*/ id      = 2, //若是好友消息, 则为好友ID
		/*uint32*/ beginid = 3, //开始索引
		/*uint32*/ endid   = 4, //结束索引
		/*uint32*/ time    = 5, //结束索引时间
		/*xMsg*/   packet  = 6, //结束索引内容  xP2PChatMsg,  xGroupChatMsg
		/*uint32*/ unread  = 7,	//未读消息数
	};
}

//请求云消息
#define IM_CLOUDMSG_LIMIT_REQ         0x15003
namespace x15003 {
	enum {
		/*string*/ source = 1, //来源(好友消息, 群消息)
		/*uint32*/ id     = 2, //若是好友消息, 则为好友ID
		/*uint32*/ index  = 3, //起始位置: 0表示从最后位置拉取，当index为0时limit为负值且limit绝对值 <= 20才有效
		/*int32*/  limit  = 4, //获取条数: 负数表示向上获取，正数向下获取
	};
}

//请求云消息响应
#define IM_CLOUDMSG_LIMIT_RESP        0x15004
namespace x15004 {
	enum {
		/*uint32*/ result  = 1, //结果信息     
		/*string*/ msg     = 2, //错误信息
		/*string*/ source  = 3, //来源(userId 好友消息)
		/*uint32*/ id      = 4, //若是好友消息, 则为好友ID
		/*uint32*/ index   = 5, //起始位置
		/*uint32*/ limit   = 6, //获取条数
	};
}

//云消息回应通知
#define IM_CLOUDMSG_LIMIT_NOTIFY       0x15005
namespace x15005 {
	enum {
		/*string*/		source	= 1, //来源
		/*uint32*/      id      = 2, //若是好友消息, 则为好友ID
		/*uint32*/		count	= 3, //消息数
		/*uint32*/		indexId	= 4, //（返回的第一条）消息索引
		/*uint32*/		ptime	= 5, //（返回的第一条）消息时间
		/*xMsg[]*/		packet	= 6, //索引内容list xP2PChatMsg,  xGroupChatMsg
	};
}

//PUSH消息
#define IM_MSG_PUSH                   0x15006
namespace x15006 {
	enum {
		/*uint32*/ appid = 1,
		/*string*/ data  = 2, //json
	};
}

//云消息确认
#define IM_CLOUDMSG_READ_STATUS       0x15007
namespace x15007 {
	enum {
		/*uint32*/      id      = 1,  //对应 CLOUDMSG_ID::110
		/*string*/		source	= 2,  //对应 CLOUDMSG_SOURCE::111
	};
}


#define IM_CLOUDMSG_READ_RESP         0x15009
namespace x15009 {
	enum {
		/*uint32*/      result  = 1, 
		/*string*/      msg     = 2, 
		/*uint32*/      id      = 3,  
		/*string*/		source	= 4, 
	};
}


//离线消息忽略(群消息)
#define IM_CLOUDMSG_IGNORE_REQ        0x15008
namespace x15008 {
	enum
	{
		/*string*/		source    = 1,  //对应 云消息SOURCE
		/*uint32*/      id        = 2,  //好友ID，或者群ID
		/*uint32*/      index     = 3,  //忽略到的位置， 0：表示全部忽略
	};
}


#define IM_CLOUDMSG_IGNORE_RESP       0x15010
namespace x15010 {
	enum
	{
		/*uint32*/      result    = 1, 
		/*string*/      msg       = 2, 
		/*string*/		source    = 3,  
		/*uint32*/      id        = 4,  
		/*uint32*/      index     = 5,  
	};
}


/*******************************频道模块********************************
*
*			              模块类型: IM_CHANNEL
*
************************************************************************/

//登录 注:登录账号传入了通配符，会直接登录， 不需要再调此登录
#define IM_CHANNEL_LOGIN_REQ            0x16007
namespace x16007{
	enum {
		/*string*/	 pgameServiceID		= 1, //游戏服务ID
		/*string[]*/ wildCard			= 2, //通配符
	};
}

#define IM_CHANNEL_LOGIN_RESP           0x16008
namespace x16008{
	enum {
		/*uint32*/    result               = 1, 
		/*string*/    msg                  = 2, 
		/*string[]*/  wildCard			   = 3, //通配符
		/*string*/	  announcement		   = 4, //公告
	};
}

//退出频道
#define IM_CHANNEL_LOGOUT_REQ            0x16009
namespace x16009{

}

#define IM_CHANNEL_LOGOUT_RESP            0x16019
namespace x16019{
	enum {
		/*uint32*/		result			= 1, 
		/*string*/		msg				= 2, 
		/*uint32*/		room_id 		= 3,	//语音房间号
		/*uint32*/		user_id			= 4,	//用户ID
	};
}


//修改通配符
#define IM_CHANNEL_MODIFY_REQ            0x16011
namespace x16011{
	enum {
		/*uint8*/    operate            = 1,     //0：移除，1：添加
		/*uint8*/    channel            = 2,     //通道（0-9）
		/*string*/   wildCard			= 3,     //通配符
	};
}

#define IM_CHANNEL_MODIFY_RESP           0x16012
namespace x16012{
	enum {
		/*uint32*/   result               = 1, 
		/*string*/   msg                  = 2, 
		/*string[]*/ wildCard			  = 3, //通配符
	};
}



//获取频道信息请求
#define IM_CHANNEL_GETINFO_REQ			0x16000

//获取频道信息返回
#define IM_CHANNEL_GETINFO_RESP			0x16001
namespace x16001{
	enum{
		/*uint32*/ result	= 2,
		/*string*/ msg		= 3,
		/*string[]*/	xGame_channel = 1, //游戏通道
	};
}

//发送频道文字消息请求
#define IM_CHANNEL_TEXTMSG_REQ	    	0x16002    
namespace x16002{
	enum{
		/*string*/	textMsg	 = 1, //发送内容
		/*string*/	wildCard = 2, //通配符
		/*string*/	expand	 = 3, //透传字段
		/*string*/  flag     = 4, //消息标记(可不传)
	};
}


//发送频道语音消息
#define IM_CHANNEL_VOICEMSG_REQ	         0x16003
namespace x16003{
	enum{
		/*string*/	voiceFilePath	   = 1, //录音文件路径名(可以是语音文件URL)
		/*uint32*/	voiceDurationTime  = 2, //录音时长  单位(秒)
		/*string*/	wildCard		   = 3, //游戏通道字符串
		/*string*/  txt                = 4, //附带文本(可选)
		/*string*/	expand			   = 5, //透传字段
		/*string*/  flag               = 6, //消息标记(可不传)
	};
}

//发送消息回应
#define IM_CHANNEL_SENDMSG_RESP          0x16010
namespace x16010{
	enum
	{
		/*uint32*/  result             = 1, 
		/*string*/  msg                = 2, 
		/*uint8*/   type               = 3,   //type= 1 语音  type= 2 文本
		/*string*/	wildCard		   = 4,   //游戏通道字符串
		/*string*/  textMsg            = 5,   //文字消息
		/*string*/  url                = 6,   //语音URL
		/*uint32*/	voiceDurationTime  = 7,   //录音时长单位(秒)
		/*string*/	expand			   = 8,   //透传字段
		/*uint8*/   shield             = 9,   //是否有敏感字， 1：存在，0不存在
		/*uint8*/	channel			   = 10,  //游戏通道
		/*string*/  flag               = 11,  //消息标记(可不传)
	};
}


//频道收到消息通知
#define IM_CHANNEL_MESSAGE_NOTIFY	     0x16004
namespace x16004 {
	enum {
		/*uint32*/	user_id			= 1,  //用户ID
		/*string*/	message_body	= 2,  //消息
		/*string*/	nickname		= 3,  //昵称
		/*string*/	ext1			= 4,  //扩展1
		/*string*/	ext2			= 5,  //扩展2
		/*uint8*/	channel			= 6,  //游戏通道
		/*string*/	wildcard		= 7,  //游戏通道字符串
		/*uint32*/	message_type	= 8,  //type= 1 语音  type= 2 文本
		/*uint32*/  voiceDuration	= 9,  //type= 1 语音时 该字段为语音时长
		/*string*/  attach          = 10, //语音消息的附带文本(可选)
		/*uint8*/   shield          = 11, //是否有敏感字， 1：存在，0不存在
	};
}

//频道获取历史消息请求
#define IM_CHANNEL_HISTORY_MSG_REQ       0x16005
namespace x16005{
	enum {
		/*uint32*/	index    = 1,				//消息索引	(当前最大索引号,索引为0请求最后count条记录)
		/*int32*/	count    = 2,				//请求条数	正数为index向后请求 负数为index向前请求 (时间排序)
		/*string*/	wildcard = 3,				//游戏通道字符串
	};
}

//room消息列表
namespace xHistoryMsgInfo
{
	enum {
		/*uint32*/	index			= 1,			//消息索引
		/*string*/	ctime			= 2,			//消息时间 例如:2015-02 10:50:13
		/*uint32*/	user_id			= 3,			//用户ID
		/*string*/	message_body	= 4,			//消息
		/*string*/	nickname		= 5,			//昵称
		/*string*/	ext1			= 6,			//扩展1
		/*string*/	ext2			= 7,			//扩展2(暂时无用)
		/*uint8*/	channel			= 8,			//游戏通道
		/*string*/	wildcard		= 9,			//游戏通道字符串
		/*uint32*/	message_type	= 10,			//type= 1 语音  type= 2 文本
		/*uint32*/  voiceDuration	= 11,			//type= 1 语音时 该字段为语音时长
		/*string*/  attach          = 12,			//语音消息的附带文本(可选)
		/*uint8*/   shield          = 13,           //是否有敏感字， 1：存在，0不存在
	};
}

//频道获取历史消息返回
#define IM_CHANNEL_HISTORY_MSG_RESP       0x16006
namespace x16006{
	enum {
		/*uint32*/	result				 = 2,
		/*string*/  msg					 = 3,
		/*xHistoryMsgInfo[]*/ xHistoryMsg = 1,			//历史消息对象list
	};
}

//频道PUSH消息通知
#define IM_CHANNEL_PUSH_MSG_NOTIFY        0x16013
namespace x16013{
    enum {
        /*string*/ type = 1,            //消息类型
        /*string*/ data = 2,			//推送数据
    };
}

//获取当前频道相关参数
#define IM_CHANNEL_GET_PARAM_REQ								0x16014/*ALL*/
namespace x16014 {
	enum {
	};
}

#define IM_CHANNEL_GET_PARAM_RESP								0x16015/*ALL*/
namespace x16015 {
	enum {
		/*uint32*/  result              = 1, 
		/*string*/  msg                 = 2,
		/*string*/	announcement		= 3,	//公告
	};
}


/*******************************附加工具模块********************************
*
*			              模块类型: IM_TOOLS
*
***************************************************************************/

//开始录音(最长60秒)  
#define	IM_RECORD_STRART_REQ	             0x19000
namespace x19000{
	enum{
		/*string*/		strfilepath	   = 1,  //录音文件保存路径(.amr), 为空则自动生成  
		/*string*/      ext            = 2,  //扩展标记     
	};
}



//停止录音请求  回调返回录音文件路径名
#define	IM_RECORD_STOP_REQ		             0x19001


//停止录音返回  回调返回录音文件路径名
#define	IM_RECORD_STOP_RESP		             0x19002
namespace x19002{
	enum{
		/*uint32*/      result           = 4,  
		/*string*/      msg              = 5, 
		/*uint32*/		time		     = 1, //录音时长
		/*string*/		strfilepath      = 2, //录音保存文件路径名
		/*string*/      ext              = 3,  //扩展标记
	};
}

//播放录音请求
#define	IM_RECORD_STARTPLAY_REQ		         0x19003
namespace x19003{
	enum{
		/*string*/		strUrl		= 1, //录音url
		/*string*/		strfilepath	= 2, //录音文件路径, 为空则自动生成  
		/*string*/      ext         = 3,  //扩展标记

	};
}

//播放语音完成
#define	IM_RECORD_FINISHPLAY_RESP	         0x19004
namespace x19004{
	enum{
		/*uint32*/     result      = 1, //播放结果，0：成功
		/*string*/     describe	   = 2, //描述
		/*string*/     ext         = 3, //扩展标记
	};
}

//播放URL下载进度
#define	IM_RECORD_PLAY_PERCENT_NOTIFY	     0x19016
namespace x19016{
	enum{
		/*uint8*/      percent     = 1, //播放URL，下载进度百分比
		/*string*/     ext         = 2, //扩展标记

	};
}



//停止播放语音
#define	IM_RECORD_STOPPLAY_REQ		         0x19005
namespace x190005
{
}

enum yvspeech
{
	speech_file = 0,              //文件识别
	speech_file_and_url = 1,      //文件识别返回url
	speech_url = 2,               //url识别
	speech_live = 3,              //实时语音识别(未完成)
};

//开始语音识别
#define IM_SPEECH_START_REQ		             0x19006
namespace x19006{
	enum{
		/*string*/	  strfilepath	= 1,  //语音文件
		/*string*/    ext           = 2,  //扩展标记
		/*uint8*/     type          = 3,  //识别类型 yvspeech
		/*string*/    url           = 4,  //识别URL
	};
}


//语音识别完成返回
#define IM_SPEECH_STOP_RESP				    0x19009
namespace x19009{
	enum{
		/*uint32*/		err_id     = 1,  //0为成功
		/*string*/		err_msg    = 2,  //返回的错误描述
		/*string*/		result	   = 3,  //结果
		/*string*/      ext        = 4,  //扩展标记
		/*string*/      url        = 5,  //文件上传返回URL
	};
}


//设置语音识别语言
#define IM_SPEECH_SETLANGUAGE_REQ	        0x19008
namespace x19008{
	enum{
		/*uint8*/	inlanguage     = 1,     //yvimspeech_language
		/*uint8*/   outlanguage    = 2,    //yvimspeech_outlanguage
	};
}

//语音识别类型
enum yvimspeech_language
{
	im_speech_zn = 1, //中文
	im_speech_ct = 2, //粤语
	im_speech_en = 3, //英语
};

//语音识别返回语言类型
enum yvimspeech_outlanguage
{
	im_speechout_simplified       = 0,  //简体中文
	im_speechout_traditional      = 1,  //繁体中文
};


//上传文件
#define IM_UPLOAD_FILE_REQ				  0x19010
namespace x19010{
	enum{
		/*string*/		filename   = 1,   //文件路径
		/*string*/      fileid     = 2,   //文件ID(文件返回标示)
	};
}

//上传文件回应
#define IM_UPLOAD_FILE_RESP		          0x19011
namespace x19011{
	enum{
		/*uint32*/		result	    = 1,   //结果
		/*string*/      msg         = 2,   //错误描述
		/*string*/      fileid      = 3,   //文件ID
		/*string*/      fileurl     = 4,   //返回文件地址
		/*uint32*/      percent     = 5,   //完成百分比
	};
}


//下载文件请求 
#define IM_DOWNLOAD_FILE_REQ              0x19012
namespace x19012 {
	enum {
		/*string*/      url         = 1,   //下载地址
		/*string*/      filename    = 2,   //文件路径, 为空则自动生成
		/*string*/      fileid      = 3,   //文件ID
	};
}

//下载文件回应
#define IM_DOWNLOAD_FILE_RESP             0x19013
namespace x19013 {
	enum {
		/*uint32*/		result	     = 1,   //结果
		/*string*/      msg          = 2,   //错误描述
		/*string*/      filename     = 3,   //文件名   
		/*string*/      fileid       = 4,   //文件ID
		/*uint32*/      percent      = 5,   //完成百分比
	};
}

//设置录音信息
#define	IM_RECORD_SETINFO_REQ	          0x19014
namespace x19014{
	enum{
		/*uint32*/		times	      = 1,   //录音最大时长(秒)， 默认60s
		/*uint8*/       volume        = 2,   //录音音量回调， 1：开启， 0：关闭
	};
}

//录音声音大小通知
#define	IM_RECORD_VOLUME_NOTIFY	          0x19015
namespace x19015{
	enum{
		/*string*/      ext           = 1,  //扩展标记
		/*uint8*/       volume        = 2,  //音量大小(0-100)
	};
}


//判断URL文件是否存在
#define IM_TOOL_HAS_CACHE_FILE            0x19017
namespace x19017{
	enum
	{
		/*string*/      url             = 1, 
	};
}

//获取URL对应的文件路径 
#define IM_GET_CACHE_FILE_REQ             0x19018  
namespace x19018{
	enum
	{
		/*string*/      url             = 1, 
	};
}

#define IM_GET_CACHE_FILE_RESP            0x19019 
namespace x19019{
	enum
	{
		/*uint32*/		result			= 1,   //结果
		/*string*/      msg				= 2,   //错误描述
		/*string*/      url             = 3, 
		/*string*/      filepath        = 4,   //获取返回文件
	};
}

//清除所有缓存
#define IM_CACHE_CLEAR                     0x19020
namespace x19020{

}

#endif



