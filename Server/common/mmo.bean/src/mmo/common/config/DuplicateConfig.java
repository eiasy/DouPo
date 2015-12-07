package mmo.common.config;

public interface DuplicateConfig {
	/** 副本通关后倒计时 秒 */
	public static final int   WAITTIME                   = 30;
	/** 副本正常通关后的免费翻牌次数 */
	public static final short FREEOPENCOUNT              = 1;
	/** 副本正常通关后的精力翻牌次数 */
	public static final short PAYOPENCOUNT               = 1;
	/** 副本正常通关后的翻牌中包含的物品道具最大个数 */
	public static final short GOODSCOUNT                 = 2;
	/** 副本正常通关 牌的最大个数 */
	public static final short CARDCOUNT                  = 4;
	/** 免费牌 */
	public static byte        FREE                       = 1;
	/** 付费牌 */
	public static byte        PAY                        = 2;
	/** 付费牌消耗的货币名称 */
	public static String      COSTNAME                   = "精力";
	/** 通关翻牌界面标题 */
	public static String      PASSTITLE                  = "通关成功";
	/** 未通关时翻牌界面标题 */
	public static String      NOPASSTITLE                = "过关奖励";
	/** 副本组队平台最大等待房间数 */
	public static int         MAX_WAIT_COUNT             = 50;
	/** 通关时经验奖励 角色LV*比率13 */
	public static int         DUP_EXP_BY_ROLELEVEL_RATIO = 13;

	/***
	 * 副本状态
	 * 
	 * @author 李天喜
	 * 
	 */
	interface State {
		/** 开启 未打过 */
		byte ON    = 1;
		/** 关闭 */
		byte OFF   = 0;
		/** 已打过 */
		byte ON_ED = 2;
	}

	/**
	 * 副本模式
	 * 
	 * @author 李天喜
	 * 
	 */
	interface Mode {
		// /** 简单模式 */
		// byte easy = 0;
		// /** 普通模式 */
		// byte common = 1;
		// /** 困难模式 */
		// byte hard = 2;
		/** 英雄模式(单人， 难道较大) */
		byte heroMode   = 1;
		/** 普通模式(多人，难道一般) */
		byte commonMode = 0;
	}

	interface RoleCount {
		byte c_1 = 1;
		byte c_2 = 3;
		byte c_3 = 4;
	}

	interface Time {
		/** 副本创建后最大空闲时间，否则副本结束(用于英雄模式和试练塔) **/
		int maxNullTime = 5 * 60 * 1000;
		/**
		 * @alter
		 * @name：xiaoqiong
		 * @date：2012-5-22
		 * @note：设置一个最小值，以实现副本内无玩家则副本结束(用于普通副本)
		 */
		int minNullTime = 500;
	}

	interface ShowState {
		/** 未满 */
		byte noFull = 0;
		/** 已满 */
		byte full   = 1;
		/** 锁定 */
		byte lock   = 2;
	}

	/**
	 * 难度
	 * 
	 */
	interface DupLevel {
		/** 简单 */
		byte easy   = 0;
		/** 普通 */
		byte normal = 1;
		/** 困难 */
		byte hard   = 2;
	}

	interface Ids {
		/** 神秘石屋ID */
		int StoneHouse     = 1;
		/** 七玄殿ID */
		int QiXuanDian     = 2;
		/** 飞翎洞ID */
		int FeiLinDong     = 3;
		/** 尸腐穴ID */
		int ShiFuXue       = 4;
		/** 烈焰洞ID */
		int LieYanDong     = 5;
		/** 寒冰窟ID */
		int HanBingKu      = 6;

		/** 英雄模式-神秘石屋ID */
		int heroStoneHouse = 21;
		/** 英雄模式-七玄殿ID */
		int heroQiXuanDian = 22;
		/** 英雄模式-飞翎洞ID */
		int heroFeiLinDong = 23;
		/** 英雄模式-尸腐穴ID */
		int heroShiFuXue   = 24;
		/** 英雄模式-烈焰洞ID */
		int heroLieYanDong = 25;
		/** 英雄模式-寒冰窟ID */
		int heroHanBingKu  = 26;
	}

	interface AreaStarState {
		/** 未打完区域 */
		byte unPass      = 0;
		/** 普通打完区域副本 */
		byte commonPass  = 1;
		/** 完美打完区域副本，每个副本都是3星 */
		byte perfectPass = 2;
	}

	interface OneStandPassState {
		/** 未开启 */
		byte unPass  = 0;
		/** 通过 */
		byte pass    = 1;
		/** 领取奖励 */
		byte awarded = 2;
	}
}
