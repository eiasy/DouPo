package mmo.common.bean.scene;

import java.util.List;

import mmo.common.bean.role.Role;
import mmo.tools.util.Point;

import org.apache.mina.core.buffer.IoBuffer;

public interface ILayer {
	/**
	 * 获取区编号
	 * 
	 * @return 编号
	 */
	public int getZoneId();

	/**
	 * 获取场景编号
	 * 
	 * @return
	 */
	public int getSceneId();

	/**
	 * 获取图层上的用户
	 * 
	 * @return 用户列表
	 */
	public List<Role> getSceneUserRole();

	/**
	 * 战场或副本结束
	 * 
	 * @param result
	 *            true胜利，false失败
	 */
	public void gameOver(boolean result);

	/**
	 * 副本或战场的结束时间（ms）
	 * 
	 * @return
	 */
	public long getEndTime();

	/**
	 * 是否为战斗场景
	 * 
	 * @return true战斗场景，false和平场景
	 */
	public boolean isBattle();

	/**
	 * 进入场景等级下限
	 * 
	 * @return 等级下限
	 */
	public short getLevelSub();

	/**
	 * 等级上限
	 * 
	 * @return 等级上限
	 */
	public short getLevelSup();

	/**
	 * 副本的难易模式
	 */
	public byte getMode();

	public short getMaxDeath();

	/**
	 * 获得主场景
	 * 
	 * @return 主场景编号
	 */
	public int getMainScene();

	public byte getMaxCount();

	public int getDuplicateId();

	public int getLayer();

	public boolean isRemainMonster();

	// add by xiaoqiong 2013-04-09
	public boolean theMonsterIsDeath(int monsterId);

	public byte getWeather();

	public void setWeather(byte weather);

	public void changeWeather(byte weather);

	//
	// public void showMonster(int monsterId);

	/**
	 * 刷新宗门怪物
	 */
	public void refreshMonster();

	public Role getBoss();

	public void setEndTime(long endTime);

	public void addLinkedLayer(ILayer newLayer);

	/**
	 * 设置主场景
	 * 
	 * @param sceneId
	 *            主场景编号
	 */
	public void setMainScene(int sceneId);

	/***
	 * 通过怪物ID召唤一个怪物
	 * 
	 * @param monsterId
	 *            怪物编号
	 */
	public Role createMonster(int monsterId, short tileX, short tileY, short roundRadius, byte type, short level);

	/***
	 * 渡劫召唤怪物
	 * 
	 * @param hostRole
	 *            召唤者
	 */
	public Role createMonster(Role hostRole, short[] mapData);

	public void removeMonster(Role role);

	public void addReleaseEvent();

	/**
	 * 副本过关后弹出翻牌界面
	 */
	public void passScene(Role teamMember);

	/** 是否是副本图层 */
	public boolean isDuplicate();

	/** 图层上的机关NPC是否全部激活 */
	public boolean isOpenAllLight(int mapId);

	/**
	 * 在场景线程里执行一个事件
	 * 
	 * @param run
	 *            事件对象
	 */
	public void execute(Runnable run);

	/**
	 * 区域聊天
	 * 
	 * @param buf
	 */
	public void chatLayer(IoBuffer buf, String triggerName);

	/**
	 * 把数据包广播给当前图层的所有角色
	 * 
	 * @param buffer
	 */
	public void broadcastAllUser(IoBuffer buffer);

	/***
	 * 广播世界BOSS受到的伤害
	 * 
	 * @param roleAttack
	 * @param attackRankMap
	 * @param buf
	 */
	public void broadcastBossAttack(List<RoleHurt> roleHurts , IoBuffer buf, int maxHp);

	/**
	 * 图层序列号
	 * 
	 * @return
	 */
	public long getSerialId();

	/**
	 * 是否为英雄模式
	 * 
	 * @return true英雄模式，false非英雄模式
	 */
	public boolean isHeroMode();

	/**
	 * 副本是否结束
	 * 
	 * @return true副本结束，false副本未结束
	 */
	public boolean isOver();

	/**
	 * 获取图层的物理层
	 * 
	 * @return
	 */
	public short[][] getPhy();

	/**
	 * 获取该图层上与物理层相关的BUF序号
	 * 
	 * @return
	 */
	public int getBufWithPhyId();

	/**
	 * 添加一个BUF与图层物理层的关联
	 * 
	 * @param key
	 * @param pointList
	 * @param role
	 */
	public void addBufWithPhy(int key, List<Point> pointList);

	/**
	 * 获取图层角色
	 * 
	 * @return
	 */
	public Role getLayerRole();

	public void setProgress(int progress);

	public void hideNpc(int npcId);
}
