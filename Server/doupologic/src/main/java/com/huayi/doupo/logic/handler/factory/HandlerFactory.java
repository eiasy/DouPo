package com.huayi.doupo.logic.handler.factory;

import com.huayi.doupo.base.dal.factory.SpringDalContext;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.handler.*;

/**
 * Handler工厂类
 * @author mp
 * @date 2014-5-6 下午2:25:52
 */
public class HandlerFactory {
	
	public static ThingHandler getThingHandler() {
		return	(ThingHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.ThingHandler);
	}
	
	public static PlayerHandler getPlayerHandler() {
		return	(PlayerHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.PlayerHandler);
	}
	
	public static SysHandler getSysHandler() {
		return	(SysHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.SysHandler);
	}
	
	public static CardHandler getCardHandler() {
		return	(CardHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.CardHandler);
	}
	
	public static EquipHandler getEquipHandler() {
		return	(EquipHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.EquipHandler);
	}
	
	public static KungFuHandler getKungFuHandler() {
		return	(KungFuHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.KungFuHandler);
	}
	
	public static FireHandler getFireHandler() {
		return	(FireHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.FireHandler);
	}
	
	public static PillHandler getPillHandler() {
		return	(PillHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.PillHandler);
	}
	
	public static PagodaHandler getPagodaHandler() {
		return	(PagodaHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.PagodaHandler);
	}
	
	public static ChapterHandler getChapterHandler() {
		return	(ChapterHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.ChapterHandler);
	}
	
	public static LootHandler getLootHandler() {
		return	(LootHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.LootHandler);
	}
	
	public static ManualSkillHandler getManualSkillHandler() {
		return	(ManualSkillHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.ManualSkillHandler);
	}
	
	public static ActivityHandler getActivityHandler() {
		return	(ActivityHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.ActivityHandler);
	}
	
	public static GmHandler getGmHandler() {
		return	(GmHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.GmHandler);
	}
	
	public static ArenaHandler getArenaHandler() {
		return	(ArenaHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.ArenaHandler);
	}
	
	public static MagicHandler getMagicHandler() {
		return	(MagicHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.MagicHandler);
	}
	
	public static WorldBossHandler getWorldBossHandler() {
		return	(WorldBossHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.WorldBossHandler);
	}
	
	public static BeautyCardHandler getBeautyCardHandler() {
		return	(BeautyCardHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.BeautyCardHandler);
	}
	
	public static UnionHandler getUnionHandler() {
		return	(UnionHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.UnionHandler);
	}

	public static MineWarHandler getMineWarHandler() {
		return	(MineWarHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.MineWarHandler);
	}
	
	public static FightSoulHandler getFightSoulHandler() {
		return	(FightSoulHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.FightSoulHandler);
	}
	
	public static WingHandler getWingHandler() {
		return	(WingHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.WingHandler);
	}

	public static YFireHandler getYFireHandler() {
		return	(YFireHandler) SpringUtil.GetObjectWithSpringContext(SpringDalContext.YFireHandler);
	}
	
}


