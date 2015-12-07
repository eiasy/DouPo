package mmo.common.bean.sect;

import java.sql.Blob;
import java.util.List;

import mmo.common.bean.goods.AGoods;
import mmo.common.bean.role.Role;
import mmo.common.bean.scene.ILayer;
import mmo.common.bean.sect.arm.IProductArm;
import mmo.common.bean.sect.battle.ISectBattle;
import mmo.common.bean.sect.building.ABuildingLevel;
import mmo.common.bean.sect.equip.ISectEquip;

import org.apache.mina.core.buffer.IoBuffer;

public interface IGameSect {

	/**
	 * 获取宗门编号
	 * 
	 * @return 宗门编号
	 */
	public long getId();

	public void setId(long id);

	/**
	 * 通过角色ID获取宗门角色
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 宗门角色
	 */
	public SectRole getRequestRole(int roleId);

	/**
	 * 添加一个阵眼
	 * 
	 * @param monster
	 *            怪物
	 */
	public void addZhenyan(Role monster);

	/**
	 * 是否为宗门成员
	 * 
	 * @param roleId
	 *            角色ID
	 * @return true宗门成员，false非宗门成员
	 */
	public boolean isMember(int roleId);

	/**
	 * 重置阵眼
	 */
	public void resetZhenyan();

	/**
	 * 获取完成度
	 * 
	 * @return
	 */
	public short getComplete();

	/**
	 * 设置完成度
	 * 
	 * @param complete
	 */
	public void setComplete(short complete);

	/**
	 * 获取参与宗战次数
	 * 
	 * @return 参与宗战的次数
	 */
	public int getBattleCount();

	/**
	 * 宗战胜利的次数
	 * 
	 * @return 宗战胜利的次数
	 */
	public int getWinBattleCount();

	/**
	 * 设置宗战次数
	 * 
	 * @param battleCount
	 *            宗战次数
	 */
	public void setBattleCount(int battleCount);

	/**
	 * 设置宗战胜利次数
	 * 
	 * @param winBattleCount
	 *            宗战胜利次数
	 */
	public void setWinBattleCount(int winBattleCount);

	/**
	 * 修改完成度
	 * 
	 * @param value
	 * @return
	 */
	public short changeComplete(short value);

	/**
	 * 设置总声望
	 * 
	 * @param prestige
	 */
	public void setPrestige(int prestige);

	/**
	 * 获取势力编号
	 * 
	 * @return 势力编号
	 */
	public byte getGroup();

	/**
	 * 获取势力名
	 * 
	 * @return 势力名
	 */

	public String getGroupName(byte id);

	/**
	 * 设置势力编号
	 * 
	 * @param group
	 *            势力编号
	 */
	public void setGroup(byte group);

	public String getName();

	public void setName(String name);

	public short getLevel();

	public int getMoney(int moneyId);

	public boolean charge(int moneyId, int count);

	public int changeMoney(int moneyId, int count);

	public void setLevel(short level);

	public int getHost();

	public void setHost(int sectHost);

	public List<SectRole> getMembers();

	public SectRole getHostRole();

	public boolean isBeast(int monsterId);

	public short getMansionLevel();

	public void setMansionLevel(short mansionLevel);

	public short getBurseLevel();

	public void setBurseLevel(short burseLevel);

	public short getStoreHouseLevel();

	public void setStoreHouseLevel(short storeHouseLevel);

	public void addBeastExp(int value);

	public short getBeastParkLevel();

	public void setBeastParkLevel(short beastParkLevel);

	public short getCollegeLevel();

	public void setCollegeLevel(short collegeLevel);

	public short getWarnMinistryLevel();

	public void buyArm(IProductArm pa, int count);

	public void setWarnMinistryLevel(short warnMinistryLevel);

	public void upgrade(SectLevel sectLevel);

	public short getGoodsCount(int goodsId);

	public void changeGoodCount(int goodsId, short offset);

	public int getBeastHappy();

	public int getCreator();

	public void setCreator(int creator);

	public void changePosition(Role role, byte position);

	public void setBeastHappy(int beastHappy);

	public int getBeastExp();

	public void setBeastExp(int beastExp);

	public byte getManorState();

	public void setManorState(byte manorState);

	public int getLayer();

	public void setLayer(int manorLayer);

	public void setData(Blob skillData);

	public boolean isManorOpened();

	public int getSpellPuzzleId();

	public void setSpellPuzzleId(int spellId);

	public short getSpellPuzzleLevel();

	public void setSpellPuzzleLevel(short spellLevel);

	/**
	 * 获取该等级的府第可以容纳的人数上限
	 * 
	 * @return 府第可以容纳的人数上限
	 */
	public int getMaxMember();

	/**
	 * 获取该等级的金库可以容纳绑灵的数量
	 * 
	 * @return 金库可以容纳绑灵的数量
	 */
	public long getMaxGold();

	/**
	 * 获取该等级的库房可以容纳单个物品数量
	 * 
	 * @return 库房可以容纳单个物品数量
	 */
	public int getMaxGoods();

	/**
	 * 获取该等级的书院购买的阵法可以使用次数
	 * 
	 * @return 书院购买的阵法可以使用次数
	 */
	public int getMaxReuse();

	/**
	 * 获取该等级的兵部可以容纳储备军的数量
	 * 
	 * @return 兵部可以容纳储备军的数量
	 */
	public int getMaxArmament();

	// public int getSpellHpId();
	//
	// public void setSpellHpId(int spellHpId);

	public short getSpellHpLevel();

	public void setSpellHpLevel(short spellHpLevel);

	public long getAtime();

	public void setAtime(long atime);

	public long getDtime();

	public void setDtime(long dtime);

	public byte getState();

	public void setState(byte state);

	public IoBuffer packetData();

	public boolean isHost(int roleId);

	public int getBeastId();

	public void setBeastId(int beastId);

	public short getBeastLevel();

	public void setBeastLevel(short beastLevel);

	public boolean isArmOut(int monsterId);

	public void armDeath(int monsterId);

	public int getMemberCount();

	public String getBeastState();

	public String getPurpose();

	public void setPurpose(String purpose);

	public int getArmCount();

	public String getPositionName(int roleId);

	public byte getPosition(int roleId);

	public int getApplyBattleId();

	public void setApplyBattleId(int applyBattleId);

	public int getResponseBattleId();

	public void setResponseBattleId(int responseBattleId);

	public ISectBattle getSectApplyBattle();

	public ISectBattle getSectResponseBattle();

	public boolean isHostOnline();

	/**
	 * 通过制定职位及该职位以上的成员
	 * 
	 * @param position
	 *            职位编号
	 * @return 成员列表
	 */
	public List<Integer> getMemebers(byte position);

	public boolean isSubHostOnline();

	/**
	 * 库房存储的物品
	 * 
	 * @return 物品列表
	 */
	public List<AGoods> getStoreGoodses();

	public short getStoreCapacity();

	public ISectEquip[] getSectEquips();

	public void flushArmData(IoBuffer buf);

	public ISectEquip getSectEquip(int equipId);

	public void equipUpgrade(ISectEquip se);

	public boolean addMember(Role role, ABuildingLevel buildingLevel, int connectId);

	/***
	 * 请求加入宗门
	 * 
	 * @param role
	 *            请求加入宗门的角色
	 */
	public void addRequestRole(Role role);

	/**
	 * 加载请求加入宗门的角色列表
	 * 
	 * @param role
	 *            角色
	 * @param connectId
	 *            连接ID
	 */
	public void requestRoleList(Role role, int connectId);

	/**
	 * 判断宗门成员是否已满
	 * 
	 * @return true未满，false已满
	 */
	public boolean isNotFull();

	public void removeMember(int id);

	public boolean changePurpose(Role role, String purpose, int connectId);

	public boolean kickout(Role role, int roleId, int connectId);

	/**
	 * @alter
	 * @name：renqiang
	 * @date：2013-05-09
	 * @note：转让宗主和任命副宗主独立执行，不需要传递职位信息
	 */
	// public boolean changePosition(Role role, int roleId, byte position, int connectId);
	public boolean changePosition(Role role, int roleId, int connectId);

	public void createSect(Role trigger, int connectId);

	/**
	 * 更新宗门信息
	 * 
	 * @param sect
	 *            宗门
	 */
	public void updateSect();

	/**
	 * 解散宗门
	 */
	public void dimiss();

	/***
	 * 完整更新宗门信息
	 * 
	 * @param sect
	 *            宗门
	 */
	public void updateSectFull();

	public void openManor(Role trigger, int connectId);

	public void giveBeast(Role trigger, int connectId);

	public void setBeastLayer(ILayer layer);

	public Role getBeastRole();

	public short getMaxBeastLevel();

	public void refreshBeastPark(Role userRole, String uiScript, int connectId);

	public void reCheckBeastPark(Role userRole, String uiScript, int connectId);

	public void updBeastHappyByUseItem(Role userRole, int uiScript, int connectId);

	public void reCheckStoreHouse(Role userRole, String uiScript, int connectId);

	/**
	 * 增加神兽的开心度
	 * 
	 * @param value
	 */
	public void addBeastHappy(int value);

	/**
	 * 升级幻阵
	 */
	public void upgradePuzzle();

	/**
	 * 升级阵法
	 */
	public void upgradeSpellHp();

	public void refreshSpellList(Role userRole, int connectId);

	public void refreshMansion(Role userRole, int connectId);

	public void refreshStoreHouse(Role userRole, int connectId);

	public void refreshWarMinstry(Role userRole, int connectId);

	public void refreshCollege(Role userRole, int connectId);

	public void refreshBurse(Role userRole, int connectId);

	public void getSectInfo(Role role, int connectId);

	public void getMemberList(Role role, int connectId);

	public void upgradeCondition(Role userRole, int connectId);

	public void lookManor(Role role, int connectId);

	public void refreshManor(Role role, int connectId);

	public void requestQuit(Role userRole, int connectId);

	public void enterManor(Role userRole, int connectId);

	public void inviteRole(Role role, String name, int connectId);

	// ----------1.7--------
	public long getProtectedTime();

	public void setProtectedTime(long protectedTime);

	public boolean isOverProtected();

	public int getPrestige();

	public int getTempPrestige();

	public void changePrestige(int prestige);

	public boolean clearPrestige();

	public boolean clearTempPrestige();

	public boolean isLingxiu();

	public void addWinBattleCount(int winBattleCount);

	public void addBattleCount(int battleCount);

	/**
	 * 刷新宗门数据
	 */
	public void refresh(long currTime);

	/**
	 * 刷新宗门成员在线状态
	 */
	public void refreshUserState(int roleId, byte state);

	/**
	 * 同意加入宗门
	 * 
	 * @param role
	 * @param roleId
	 * @param connectId
	 */
	public void agreeRequestJoin(Role role, int roleId, int connectId);

	/**
	 * 拒绝加入宗门
	 * 
	 * @param role
	 * @param roleId
	 * @param connectId
	 */
	public void refuseRequestJoin(Role role, int roleId, int connectId);

	/**
	 * 角色升级，刷新宗门成员显示级数
	 * 
	 * @param roleId
	 *            角色ID
	 * @param level
	 *            角色升级后等级
	 */
	public void refreshMemberLevel(int roleId, short roleLevel);

	/**
	 * 任命副宗主
	 * 
	 * @param role
	 *            当前角色
	 * @param roleId
	 *            目标角色ID
	 * @param connectId
	 */
	public boolean appointViceHost(Role role, int roleId, int connectId);

	/**
	 * 解除副宗主职务
	 * 
	 * @param role
	 *            当前角色
	 * @param roleId
	 *            目标角色ID
	 * @param connectId
	 */
	public boolean relievedViceHost(Role role, int roleId, int connectId);

	/**
	 * 移除一个请求加入宗门的角色
	 * 
	 * @param roleId
	 *            角色ID
	 */
	public SectRole removeRequestRole(int roleId);

	/**
	 * 取得宗门的成员
	 * 
	 * @param id
	 *            角色ID
	 */
	public SectRole getSectMemberRole(int id);

//	public void saveFull();
//
//	public void save();

//	public void forceSaveFull();
}