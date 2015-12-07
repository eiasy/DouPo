package mmo.common.bean.role;

import java.util.List;

import mmo.common.bean.goods.AGoods;
import mmo.common.bean.sect.IGameSect;

public interface IGameEvent {
	/**
	 * 副本失败
	 * 
	 * @param duplicate
	 */
	public void eventDuplicateFail(int duplicate);

	/**
	 * 完成副本
	 * 
	 * @param duplicate
	 */
	public void eventDuplicateSuccess(int duplicate);

	/**
	 * 物品被丢弃
	 * 
	 * @param goods
	 */
	public void eventGoodsDeleted(AGoods goods);

	/**
	 * 装备物品
	 * 
	 * @param goods
	 */
	public void eventGoodsEquip(AGoods goods);

	/**
	 * 物品放入背包
	 * 
	 * @param goods
	 */
	public void eventGoodsToPack(AGoods goods, int count);

	/**
	 * 物品放入随身商店
	 * 
	 * @param goods
	 */
	public void eventGoodsToShop(AGoods goods);

	/**
	 * 物品被移入仓库
	 * 
	 * @param goods
	 */
	public void eventGoodsToStore(AGoods goods);

	/**
	 * 被对方杀死
	 * 
	 * @param killer
	 */
	public void eventKilled(Role killer);

	/***
	 * 杀死角色
	 * 
	 * @param deathRole
	 */
	public void eventKillRole(Role deathRole);

	/**
	 * 接受任务
	 * 
	 * @param mission
	 */
	public void eventMissionAccept(int mission);

	/**
	 * 提交任务
	 * 
	 * @param missionId
	 */
	public void eventMissionCommit(int missionId);

	/**
	 * 放弃任务
	 * 
	 * @param mission
	 */
	public void eventMissionDeleted(int mission);

	/**
	 * 资金发生变化
	 * 
	 * @param moneyId
	 */
	public void eventMoneyChange(int moneyId);

	/**
	 * 杀死怪物
	 * 
	 * @param monsters
	 */
	public void eventMonstersKill(List<Role> monsters);

	/**
	 * 开启穴位
	 * 
	 * @param identity
	 */
	public void eventOpenIdentity(byte identity);

	/**
	 * 充值
	 * 
	 * @param count
	 */
	public void eventRecharge(int count);

	/**
	 * 进入场景
	 * 
	 * @param sceneId
	 */
	public void eventSceneEnter(int sceneId);

	/**
	 * 宗战失败
	 */
	public void eventSectBattleFail();

	/**
	 * 宗战胜利
	 */
	public void eventSectBattleSuccess();

	/**
	 * 创建宗门
	 * 
	 * @param sect
	 */
	public void eventSectCreate(IGameSect sect);

	/**
	 * 加入宗门
	 * 
	 * @param sect
	 */
	public void eventSectJoin(IGameSect sect);

	/**
	 * 退出宗门
	 */
	public void eventSectQuit();

	/**
	 * 技能或心法升级
	 * 
	 * @param skillId
	 */
	public void eventSkillUpgrade(int skillId);

	/**
	 * 卸下装备
	 * 
	 * @param goods
	 */
	public void eventUnequipGoods(AGoods goods);

	/**
	 * 级别提升
	 */
	public void eventUpgradeLevel(String reason);

	/**
	 * 境界提升
	 */
	public void eventUpgradeRealm();

	/**
	 * 提升VIP等级
	 */
	public void eventUpgradeVipLevel();

	/**
	 * 使用物品
	 * 
	 * @param goods
	 */
	public void eventUseGoods(AGoods goods);

	/**
	 * 角色上线
	 */
	public void eventToOnline();

	/**
	 * 角色离线
	 */
	public void eventToOffline();
}
