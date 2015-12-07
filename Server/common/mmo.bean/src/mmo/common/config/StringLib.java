package mmo.common.config;

import mmo.common.protocol.ui.ClientUI;
import mmo.tools.util.string.StringSplit;

public interface StringLib {
	/**
	 * 符号Char
	 * 
	 * @author Administrator
	 * 
	 */
	public interface SymbolChar {
		char symbolDollarChar  = '$';
		char symbolWellChar    = '#';
		char symbolCommaChar   = ',';
		char symbolSemiChar    = ';';
		char symbolColonChar   = ':';
		char symbolEqual       = '=';
		char symbolAdd         = '+';
		char symbolTrad01Right = ')';
		char symbolStar        = '*';
	}

	/**
	 * 符号String
	 * 
	 * @author Administrator
	 * 
	 */
	public interface SymbolStr {
		String symbolDollarChar     = "$";
		String symbolWellStr        = "#";
		String symbolTradChinaLeft  = "（";
		String symbolTradChinaRight = "）";
		String symbolTrad01Left     = "(";
		String symbolTrad01Right    = ")";
		String symbolTrad02Left     = "[";
		String symbolTrad02Right    = "]";
		String symbolTrad03Left     = "【";
		String symbolTrad03Right    = "】";
		String symbolComma          = "，";
		String symbolEnglishComma   = ",";
		String symbolSemi           = "；";
		String symbolEnglishSemi    = ";";
		String symbolColon          = ":";
		String symbolMinus          = "-";
		String symbolPlus           = "+";
		String symbolPercent        = "%";
		String symbolStar           = "*";
	}

	/**
	 * 中文序号
	 * 
	 * @author Administrator
	 * 
	 */
	public interface ChineseNumber {
		String number00 = "零";
		String number01 = "壹";
		String number02 = "贰";
		String number03 = "叁";
		String number04 = "肆";
		String number05 = "伍";
		String number06 = "陆";
		String number07 = "柒";
		String number08 = "捌";
		String number09 = "玖";
		String number10 = "拾";
	}

	public interface FigureNumber {
		String number0  = "0";
		String number1  = "1";
		String number2  = "2";
		String number3  = "3";
		String number4  = "4";
		String number5  = "5";
		String number6  = "6";
		String number07 = "7";
		String number8  = "8";
		String number9  = "9";
	}

	String relogin = "请重新登录游戏！";

	/**
	 * 服务器提示用
	 * 
	 * @author Administrator
	 * 
	 */
	public interface ServerInfo {
		String commonDoing          = "即将开放";
		String operateFinish        = "操作已完成！";
		String operateDispatch      = "操作发出，请稍后！";
		String commonFail           = "操作失败";
		String commonProErr         = "错误的协议！";
		String repairServer         = "服务器已经进入维护状态，请稍后再登录！";
		String commonFail_cmdError  = "操作失败，发送的请求指令无法识别！";

		String alterPwdFail         = "修改密码失败，请稍后再试！";
		String alterPwdFailUser     = "修改密码失败，账号不匹配！";

		String bindPhoneNext        = "确认手机绑定？";
		String valCodeSent          = "验证码已经发送，请注意查收！";
		String phoneError           = "请输入正确的手机号！";
		String bindPhoneUseridError = "手机绑定失败，账号信息错误！";
		String bindPhonePWDError    = "手机绑定失败，密码错误！";
		String bindPhoneVcodeError  = "手机绑定失败，验证码错误！";
		String bindPhonePhoneError  = "手机绑定失败，手机号不一致！";
		String bindPhoneFail        = "手机绑定失败，请稍后再试！";
		String bindPhoneSucc        = "手机绑定成功！";
	}

	/**
	 * 消息频道用
	 * 
	 * @author Administrator
	 * 
	 */
	public interface ChatChannel {
		String obtain  = "获得";
		String nameExp = " 经验";
	}

	/**
	 * 活动用
	 * 
	 * @author Administrator
	 * 
	 */
	public interface Activity {
		String timeFull = "全天";
	}

	/**
	 * 按钮名称
	 * 
	 * @author Administrator
	 * 
	 */
	public static interface Button {
		String btnEnter   = "进入";
		String btnCancel  = "取消";
		String btnRefresh = "刷新";
		String btnAgree   = "同意";
		String btnRefuse  = "拒绝";
		String exit       = "退出";
		String buyGoods   = "购买";
		String useGoods   = "使用";
		String ensure     = "确定";
		String recharge   = "充值";
	}

	public static interface StoreName {
		String storeName01 = "壹";
		String storeName02 = "VIP壹";
		String storeName03 = "VIP贰";
		String storeName04 = "VIP叁";
		String storeName05 = "VIP肆";
		String storeName06 = "VIP伍";
		String storeName07 = "VIP陆";
		String storeName08 = "VIP柒";
		String storeName09 = "VIP捌";
		String storeName10 = "VIP玖";
	}

	/**
	 * 普通
	 * 
	 * @author Administrator
	 * 
	 */
	public interface CommonStr {
		String commonNo     = "";
		String commonSpace  = " ";
		String commonClosed = "未开启";
		String optionsSuc   = "设置成功";
		String changing     = "正在切换场景";
		String paramater    = "P\\[0\\]";
	}

	String realmDisasterNotice = "修士[color:f370e5]{0}[/color]在[color:f370e5]{1}[/color]地渡劫，光大仙友们快快去抢宝物！";

	public static interface Email {
		String STR_SENDED          = "发送成功";
		String ERROR_ADDRESS       = "收件人不存在!";
		String STR_SEND_SELF_ERROR = "不能发给自己";
		String STR_NO_GOODS        = "道具不存在";
		String STR_COUNT_ERR       = "物品数量不足！";
		String STR_COUNT_0         = "道具数量不能为0";
		String STR_WARN_ERR        = "非法操作！";
		String DELETED             = "邮件已经被删除";
		String CANCELED            = "对方已取消邮件交易";
		String PICKUPED            = "附件已经被提取";
		String STR_PICKSUC         = "提取成功";
		String STR_PICKSUC_ALL     = "全部提取成功";
		String STR_TARGETREFUSE    = "收件人拒绝";
		String STR_DELETESUC       = "删除邮件成功";
		String STR_NOEMAIL         = "邮件已不存在";
	}

	String bagFull      = "背包已满！";

	String STR_OFF_LINE = "对方已离线";

	public static interface Goods {
		String AWARDED        = "获得物品";
		String fabaoStudyLv   = "领悟此技能需要角色等级为";
		String USE_SKILL_BOOK = "请先召唤出“{0}”后，再使用技能书";
	}

	String                     compositeLack    = "材料不足，不能进行合成操作！";
	String                     compositeSuccess = "合成成功！";
	public static final String levelLess        = "等级小于";
	public static final String cannotEnter      = "不能进入";

	public static interface Relation {
		String STR_NOT_FOUND      = "对方不在线";
		String STR_ADD_GOODFRIEND = "已成为你的好友";
		String STR_FAIL_ADD_SELF  = "不能向自己发出邀请加好友！";
		String STR_ERROR          = "好友已经被移除！";
		String STR_FULL           = "可添加人数已上限，提升VIP等级可扩大上限！";
	}

	String INLAY_INFO           = "镶嵌:";
	String strengthNotFound     = "精炼失败，找不到要精炼的物品！";
	String autoUserLingshi      = "绑灵数量不足，是否同意使用灵石？";
	String shopDurability       = "物品耐久未满！";
	String goodsTimeOver        = "物品已过有效期";
	String goodsIsBinded        = "绑定物品不能进行交易";
	String chatNull             = "聊天信息不能为空！";
	String chatOffline          = "对方已经离线！";
	String chatToSelf           = "不能跟自己聊天！";
	String chatNoHorn           = "【传讯符】不足，请到商城购买!";
	String chatPrivate          = "私聊";
	String chatPrivateTo        = "对你私聊！";
	String chatWorld            = "世界";
	String chatZone             = "区域";
	String chatSect             = "宗门";
	String chatTeam             = "队伍";
	String chatHorn             = "喇叭";
	String chatHornGuide        = "喇叭引导";

	String storeSelf            = "普通仓库";
	String storeVip             = "VIP仓库";

	String store2bagSuc         = "物品已经从仓库中取出！";
	String shopBuySuc           = "购买成功！";
	String sectAward            = "宗门获得：";
	String emailNew             = "未读邮件";

	String doingDuplicate       = "副本正在进行中，不能进行寻路！";
	String roleOffline          = "对方已经离线，不能进行传送！";
	String needMoney            = "传送符和灵石不足，不能进行传送！";
	String transferFail         = "对方当前所在场景为特殊场景，无法进行传送！";
	String levelSmaller         = "当前等级过低，无法传送到高等级区域！";
	String locationRole         = "场景传送";
	String sectNotExist         = "目标宗门不存在！";
	String requestSended        = "申请信息已经发出，请耐心等候！";
	String sectNoPower          = "权限不够！";
	String messageHandled       = "消息已经被处理！";
	String gmCommitSucc         = "消息提交成功，感谢对我们的支持！";
	String gmNull               = "提交失败，内容不能为空！";
	String deleteFail           = "删除角色失败，角色已经进入游戏世界！";
	String deleteFailSect       = "该角色为宗门宗主，不能删除，请转让宗主职位后再试！";
	String shopRoleOffline      = "购买失败，对方已经离线！";
	String mijingOpenTime       = "场景开放时间未到！";
	String notCrossScene        = "不能传送！";
	String noSceretScene        = "秘境不存在！";
	String sceretSceneNotStart  = "秘境未开启或已经结束！";
	String sceretCountCost      = "今日挑战剩余次数：0" + ClientUI.StringCommand.BR + "请提高【VIP等级】来提升挑战次数！";

	String shopRoleNoGoods      = "购买失败，物品已经下架或卖出！";
	String shopGoodsCount       = "商品剩余数量不足！";
	String shopRoleMoneyMore    = "购买失败，灵石不足！";
	String buyCount             = "购买数量必须大于1！";
	String petOvertime          = "战斗数据已过期！";
	String serverFull           = "服务异常火爆，稍后将进入游戏";
	String serverRepairing      = "该服务器正在维护中，请选择其他服务器进入游戏！";
	String TEXT_REPLACE         = "***";
	String username             = "角色昵称必须由2到5位的汉字或2到10字母和数字组合而成！";
	String sectname             = "宗门名称必须由由2到5位的汉字或2到10字母和数字组合而成！";
	String roleCharge           = "玩家充值";
	String roleChargeFail       = "玩家充值失败";
	String patchCharge          = "补点";
	String unknow               = "未知";

	String nameRepeat           = "重名";
	String keyword              = "包含敏感词汇";
	String lengthErr            = "长度不满足条件";
	String createState          = "状态错误";
	String chargeWay            = "支付方式";
	String chargeChannel        = "支付通道";
	String chargeFail           = "充值操作失败，请注意核对充值信息！";
	String chargeSucc           = "充值操作已经完成，请注意查收！";
	String chargeFailNoGame     = "返回充值信息，游戏不存在！";
	String chargeFailNoServer   = "返回充值信息，游戏服务器不存在！";
	String chargeFailServerStop = "返回充值信息，游戏服务器停服！";
	String chargeFailWait       = "充值操作失败，请稍后再试！";
	String cancelJoinSect       = "申请已经被取消！";
	String noJoinTheSect        = "还未申请加入该宗门";
	String cancelJoinSectFail   = "取消申请失败，请稍后再试！";
	String requestParamFail     = "参数请求失败，请稍后再试！";
	String sectNameKeyword      = "宗门名称包含非法文字";
	String roleNameKeyword      = "角色昵称包含非法文字";
	String systemNotice         = "公告：";
	String needMoreEnegry       = "能量不足！";
	String TREASURE_GET         = "请先领取法宝！";
	String TREASURE_ERR         = "法宝数据不存在！";
	String TREASURE_GETED       = "已经领取！";
	String TREASURE_RESET       = "重置失败！";
	String RANK_NOT_EXIST       = "加载排行失败！";
	String SKILL_STUDY          = "学习成功！";
	String SKILL_LEVEL_UP       = "升级成功!";

	public static interface Identity {
		String STR_FAIL = "未达到该境界，无法学习！";
	}

	public static interface Duplicate {
		String noOpenLight     = "祭坛尚未点亮，无法进入下一场景！";
		String levelDissatisfy = "等级不足，无法进入副本";
		String noHeroTeam      = "英雄模式副本，不能邀请玩家入队";
		String isHeroTeam      = "对方在特殊场景，不能邀请入队";
		String isCoolTime      = "副本冷却中！";
		String noDupClass      = "副本对象不存在，ID：";
		String haveDupNotOver  = "还有副本未结束！";
		String isHero          = "英雄";
		String level           = "级";
		String arrayInfoLenght = "阵容不能少于1个伙伴或大于5个伙伴";
	}

	public static interface Npc {
		String WARN_DIS = "距离太远了";
	}

	public static interface SecretArea {
		String secretArea     = "秘境传送";
		String secretContent1 = "进入秘境需要";
		String secretContent2 = "，是否进入？";
	}

	public static interface cdkey {
		String bateAward   = "内测奖励";
		String bateMoney   = "每天可以领取一次内测灵石奖励，是否现在领取";
		String btnMoney    = "领取";
		String used        = "您已兑换过该物品";
		String suc         = "兑换成功,请打开邮件查看";
		String noEffective = "兑换失败，输入的兑换码有误或已经完成兑换！";
		String targetApp   = "该兑换码不能在该分区使用！";
		String overdate    = "该兑换码已经过期！";
		String giftFull    = "礼包袋已满，不能进行兑换！";
		String suc_money   = "领取成功";
	}

	public static interface strengthen {
		String stengthenLingshi = "灵石不足";
	}

	public static interface Message {
		String findPathFail           = "目标地点不可达！";
		String operateFailTimer       = "操作失败，对象已经过期！";
		String awardActivityUndo      = "领取奖励失败，活动还未完成！";
		String sectBattleNoResponse   = "你的宗门没有被挑战，不能进入圣地！";
		String peaceSceneInfo         = "和平场景不能切换";
		String duplicateScene         = "副本场景不能切换";
		String sectBattleScene        = "宗战场景不能切换";
		String sectDidongScene        = "宗门地洞不能切换";
		String nameShop               = "[商店]";
		String duplicatePro           = "副本提示";

		String teamCreateExist        = "已经加入队伍，不能再申请创建队伍！";
		String teamCreateSuccess      = "创建队伍成功！";
		String teamCreateSingleD      = "你绑定了一个单人副本，不能创建队伍！";
		String teamCreateSingleTD     = "目标绑定了一个单人副本，不能加入队伍！";
		String teamInviteSelf         = "不能邀请自己";
		String teamInviteNo           = "你不是队长，不能发出邀请！";
		String teamFull               = "队伍已经满员，不能邀请新成员！";
		String teamInvite             = "邀请入队";
		String teamInviteOffline      = "对方已经下线，不能加入队伍！";
		String teamInviteSame         = "目标已经和你在同一个队伍！";
		String teaminviteOther        = "目标已经加入其他队伍！";
		String teamRequestInvite      = "邀请你加入队伍，是否同意加入？";
		String teamRequestJoin        = "申请加入你的队伍，是否同意加入？";
		String teamCreateInvite       = "申请组队";
		String shopRoleBagFull        = "背包空间不足，不能购买物品！";
		String toShopFailBattle       = "战斗场景不能进入摆摊状态！";
		String toShopFailEmpty        = "摊位没有商品，不能进入摆摊状态！";
		String toShopFailSuccess      = "进入摆摊状态！";
		String exitShopState          = "脱离摆摊状态！";
		String stringLength           = "摆摊标语最长只能输入8个字！";
		String loginFirst             = StringSplit
		                                      .transformString("欢迎进入[fontstyle:30]凡人修仙传[/fontstyle]的世界，一场惊心动魄的修仙之旅即将拉开序幕…[/r][fontstyle:29]青牛镇镇长[/fontstyle]正在不远处等着你，不妨与他交谈一下吧！");
		String loginFirstBtn          = "确定";
		String needTargetScene        = "未到达指定的位置，不能使用物品！";

		String shoperOffline          = "对方已经下线无法查看货摊！";
		String shoperNotShopping      = "对方没有进入摆摊状态，无法查看货摊！";
		String vipBoxReceived         = "成功领取宝箱！";
		String vipFull                = "宝箱已经全部领取！";

		String convert                = "物品兑换";
		String SECT_NOENTER           = "还未加入宗门";
		String FromSectAreaNPCMission = "需要在宗门内提交任务";
		String joinedSectSoNotJoin    = "对方已加入其它宗门，无法加入本宗门！";
		String tiredLight             = "你已进入疲劳期，打怪获得经验收益降低50%!";
		String tiredHeavy             = "你已进入深度疲劳期，杀死怪物将不再获得经验!";
		String noExistTeam            = "队伍不存在！";
		String skillHasOpenOnly       = "宝法已开启一个技能领悟！";
		String openTreasureNoMoney    = "机缘不足，无法修复法宝！";
	}

	public static interface OfflineExperience {
		String experience      = "经验";
		String time            = "分钟";
		String RECEIVE_SUCCESS = "领取成功";
		String received        = "你已经领取过离线经验奖励";
		String RECEIVE_FAIL    = "没有经验可以领取！";
	}

	public static interface TeamMessage {
		String monitorLogined = "该队伍队长不在线！";
		String targetLogined  = "对方已经下线！";
		String targetInTeam   = "对方已经在队伍里！";
		String teamFail       = "组队失败!";
		String isNotMonitor   = "你不是队长，组队失败!";
		String teamFull       = "队员已经满员！";
		String refuseTeam     = "对方拒绝组队！";
		String outTeam        = "你离开了队伍！";
		String noPower        = "你不是队长，不能提出队员！";
		String notMonitor     = "你不是队长！";

	}

	public static interface SectInfo {
		String noPower            = "您不是宗主,权限不足";
		String power              = "长老以上职位才可进行招收";
		String offline            = "该角色不存在或已离线";
		String lowLevel           = "该角色等级不足15级,无法加入宗门";
		String lowLevelNOjoin     = "等级不足15级,无法申请加入宗门";
		String doingBattle        = "正在进行宗战，不能解散宗门";
		String lowLevelChangeHost = "等级不足35级,不能传位为宗主";
	}

	public final static String BTN_AWARED       = "已领取";
	public final static String OPEN_WORLD_BOSS  = "活动<世界首领>已开启";
	public final static String CLOSE_WORLD_BOSS = "一番肆虐之后，世界首领扬长而去了....";
	public final static String KILL_WORLD_BOSS  = "万众一心！经历一番苦战，世界首领已被群雄消灭";

	public final static String OPEN_DA_ZUO      = "活动<打坐>已开启";

	public final static String OPEN_SECRET      = "活动<秘境>已开启";

	public final static String MORE_ONE_DAY     = "剩余#天";
	public final static String MORE_ONE_HOUR    = "剩余#小时";
	public final static String LESS_X_MINUTE    = "剩余#分钟";

	public static interface Account {

		String loginSuc               = "登录成功！";
		String registerSuc            = "注册成功！";
		String chaneelUCLoginFail     = "渠道UC用户登陆失败！";
		String ucLoginFail            = "登录UC失败！";
		String c91LoginFail           = "登录91失败！";
		String cxiaomiLoginFail       = "登录小米失败！";
		String baoruanLoginFail       = "宝软登录失败！";
		String dangleLoginFail        = "当乐验证失败！";
		String channelLoginFail       = "渠道验证失败！";
		String noAccount              = "帐号不存在！";
		String loginOvertime          = "登录失败，请重新登录！";
		String testWait               = "内侧即将开启，请稍后！";
		String loginFail              = "登录失败，请稍后再试！";
		String loginPwdErr            = "登录失败，账号和密码错误！";
		String loginChannel           = "登录失败，未知的渠道编号！";
		String dangleRegisterFaill_1  = "注册失败，帐号只能包含字母和数字！";
		String dangleRegisterFaill_2  = "注册失败，帐号已被占用！";
		String dangleRegisterFaill_3  = "注册失败，首字符不能为数字！";
		String dangleRegisterFaill_4  = "注册失败，帐号长度不能大于10！";
		String dangleRegisterFaill616 = "注册失败，密码不能大于16位！";
		String dangleRegisterFaill612 = "注册失败，账号不能为空！";
		String dangleRegisterFaill615 = "注册失败，密码不能小于6位！";
		String registerFaill          = "注册失败，请稍后再试！";
		String registerLengthErr      = "注册失败，帐号长度的不能小于5位或大于50位！";
		String serverRepairing        = "该服务器正在维护中，请选择其他服务器进入游戏！";
		String connectServer          = "连接服务器";
		String registerLawErr         = "注册失败，包含非法字符！";
		String userid                 = "账号必须为5到50位字母和数字的组合！";
		String registerPwdErr         = "注册失败，帐号长度的不能小于6位或大于30位！";
		String registerNumErr         = "注册失败，不允许用数字开头！";
		String registerCountErr       = "注册失败,同一个IP注册数达到上限！";
		String registerRepeatErr      = "已经被注册，请重新输入帐号！";
		String serverFull             = "服务器已满，请选择其他服务器或稍后再试！";
		String alterPwdFail           = "修改密码失败！";
		String phoneError             = "请输入正确的手机号！";
		String valCodeSent            = "验证码已经发送，请注意查收！";
		String bindPhoneNext          = "确认手机绑定？";
		String bindPhoneUseridError   = "手机绑定失败，账号信息错误！";
		String bindPhonePWDError      = "手机绑定失败，密码错误！";
		String bindPhoneVcodeError    = "手机绑定失败，验证码错误！";
		String bindPhonePhoneError    = "手机绑定失败，手机号不一致！";
		String bindPhoneFail          = "手机绑定失败，请稍后再试！";
		String bindPhoneSucc          = "手机绑定成功！";
		String resetPWDFail           = "找回密码失败，手机号未绑定账号，如有疑问请联系GM！";
		String resetPWDSucc           = "绑定的账号及新密码已经以短信的形式发送，请注意查收！";
		String askResetPwd            = "是否要重置密码？";
		String askBindPhone           = "是否要绑定手机？";
		String noProduct              = "选择的产品不存在";
		String appListSent            = "服务器列表已经发出！";
		String cyisouLoginFail        = "宜搜登录失败。";
		String c360LoginFail          = "登录360失败。";
	}

	public static interface Mansion {
		String NO_HORSE = "该坐骑还未获得";
	}
}
