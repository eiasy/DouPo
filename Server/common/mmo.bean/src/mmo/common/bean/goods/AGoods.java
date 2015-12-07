package mmo.common.bean.goods;

import java.util.List;
import java.util.Map;

import mmo.common.bean.extension.AAwardGoods;
import mmo.common.bean.extension.Function;
import mmo.common.bean.goods.filter.ShopFilter;
import mmo.common.bean.goods.price.PriceGroup;
import mmo.common.bean.role.Role;
import mmo.expression.IExpressionData;

import org.apache.mina.core.buffer.IoBuffer;

public interface AGoods extends Cloneable {
	public int getId();

	public Function getFunctionByIndex(short index);

	/**
	 * 系统回收价格
	 * 
	 * @return
	 */
	public int getRecoverPrice();

	public List<Function> getForeverProps();

	/**
	 * 验证物品的使用条件
	 * 
	 * @param role
	 *            角色
	 * @return true可以使用，false不能使用
	 */
	public boolean validateCondition(Role role);

	/**
	 * 获取物品的关键名字-去掉品阶标识后的名字
	 * 
	 * @return 关键名字
	 */
	public String getMainName();

	public short getBuyRoleLevel();

	/**
	 * 判断冷却是否结束
	 * 
	 * @param host
	 *            所属角色
	 * @return true结束，false未结束
	 */
	public boolean isEndCool(Role host);

	public void addPriceGroup(PriceGroup priceGroup);

	public void addIconVersion(byte version, String icon);

	public String iconVersionToString();

	public String getIconByVersion(byte version);

	public String getIconVersion();

	public String getTipMessage();

	public void setTipMessage(String tipMessage);

	public void setIconVersions(String iconVersions);

	public List<Function> getFunctions();

	public long getEffectTime();

	public void setEffectTime(long effectTime);

	public int getMaxCount();

	public void setMaxCount(int maxCount);

	public int changeMaxCount(int value);

	public int getCount();

	public void setCount(int count);

	/**
	 * 释放2可以丢弃
	 * 
	 * @return true可丢弃，false不能丢弃
	 */
	public boolean isRemoveable();

	/**
	 * 是否自动绑定
	 * 
	 * @return true获得时自动绑定，false获得时不自动绑定
	 */
	public boolean isAutoBind();

	public void resetDurability(int durability);

	public void setId(int id);

	public List<Function> getExpandProps();

	public List<Function> getBaseProps();

	public String getSkeletonFile(byte profession, byte skeleton);

	public int getGoodsId();

	public void setGoodsId(int id);

	public byte getRealm();

	public void setRealm(byte realm);

	public int getCooltime();

	public void setCooltime(int cooltime);

	public String getNote();

	public short getDurability();

	public void setDurability(short durability);

	public short getMaxDurability();

	public void setMaxDurability(short maxDurability);

	public void setNote(String note);

	public String getName();

	public String getNameStyle();

	public void setName(String name);

	public short getStrengthen();

	public void setStrengthen(short point);

	/**
	 * 判断物品是否可以叠加
	 * 
	 * @return true可以叠加，false不能叠加
	 */
	public boolean isOverlap();

	public boolean isSellable();

	public byte getSellable();

	public void setSellable(byte sellable);

	public String getCreateFun();

	public void setCreateFun(String createFun);

	public byte getSource();

	public void setSource(byte source);

	public short getCate();

	public short getCateSub();

	public short getLevel();

	public byte getProfession();

	public byte getQuality();

	public void setCate(short cate);

	public void setCateSub(short cateSub);

	public void setLevel(short level);

	public void setProfession(byte profession);

	public void setQuality(byte quality);

	/**
	 * @param profession
	 * @return 判断是否有穿戴
	 */
	public boolean checkProfession(byte profession);

	/**
	 * 添加一个buf
	 * 
	 * @param skillId
	 *            技能ID
	 * @param continueTime
	 *            持续时间
	 * @param refreshOffset
	 *            刷新间隔
	 */
	public abstract void addBuf(int skillId, int continueTime, int refreshOffset);

	public void addBuf(int skillId, int continueTime);

	/**
	 * 绑定一个技能
	 * 
	 * @param skillId
	 *            技能ID
	 */
	public abstract void addSkill(int skillId);

	/***
	 * 为装备绑定凹槽
	 * 
	 * @param max
	 *            最大数量
	 * @param openCount
	 *            开启数量
	 */
	public abstract void addHole(int max, int openCount);

	/***
	 * 添加基础值--攻击力
	 * 
	 * @param value
	 * @param percent
	 *            true增加角色基础值得百分比，false在角色基础值上增加固定值
	 */
	public abstract void addBaseAttack(int value, boolean percent);

	public abstract void addBaseAttack(int value, int offset, boolean percent);

	/**
	 * 添加基础值--命中
	 * 
	 * @param value
	 */
	public abstract void addBaseAttackChance(int value);

	/**
	 * 添加基础值--会心值--暴击率
	 * 
	 * @param value
	 */
	public abstract void addBaseCruelChance(int value);

	/**
	 * 添加基础值--会心效果--暴击倍数
	 * 
	 * @param value
	 */
	public abstract void addBaseCruelValue(int value);

	/**
	 * 添加基础值--防御
	 * 
	 * @param value
	 */
	public abstract void addBaseDefence(int value);

	/**
	 * 添加基础值--闪避值
	 * 
	 * @param value
	 */
	public abstract void addBaseDuckChance(int value);

	/***
	 * 增加一组基础属性
	 * 
	 * @param props
	 */
	public abstract void addBaseProps(Map<Integer, Integer> props);

	/**
	 * 添加基础值--气血--hp
	 * 
	 * @param value
	 * @param percent
	 *            true增加角色基础值得百分比，false在角色基础值上增加固定值
	 */
	public abstract void addBaseSupHp(int value, boolean percent);

	/**
	 * 添加基础值--灵力--mp
	 * 
	 * @param value
	 * @param percent
	 *            true增加角色基础值得百分比，false在角色基础值上增加固定值
	 */
	public abstract void addBaseSupMp(int value, boolean percent);

	/*********************************** 添加附加值 ******************************************/
	/***
	 * 添加附加值--攻击力
	 * 
	 * @param value
	 * @param percent
	 *            true增加角色基础值得百分比，false在角色基础值上增加固定值
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandAttack(int value, boolean percent, boolean open);

	/**
	 * 添加附加值--攻击力
	 * 
	 * @param value
	 * @param offset
	 *            浮动值
	 * @param percent
	 *            true增加角色基础值得百分比，false在角色基础值上增加固定值
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandAttack(int value, int offset, boolean percent, boolean open);

	/**
	 * 添加附加值--命中
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandAttackChance(int value, boolean open);

	/**
	 * 添加附加值--气运
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandChance(int value, boolean open);

	/**
	 * 添加附加值--会心值--暴击率
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandCruelChance(int value, boolean open);

	/**
	 * 添加附加值--会心效果--暴击倍数
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandCruelValue(int value, boolean open);

	/**
	 * 添加附加值--防御
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandDefence(int value, boolean open);

	/**
	 * 添加附加值--闪避值
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandDuckChance(int value, boolean open);

	/**
	 * 添加附加值--法术
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandMagic(int value, boolean open);

	/***
	 * 添加特殊属性--反弹状态
	 */
	public abstract void addExpandRebound(int value, boolean open);

	/***
	 * 添加特殊属性--破防
	 */
	public abstract void addExpandReduceDefence(int value, boolean open);

	/***
	 * 添加特殊属性--抵抗状态
	 */
	public abstract void addExpandResist(int value, boolean open);

	/**
	 * 添加附加值--神识
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandSprit(int value, boolean open);

	/**
	 * 添加附加值--体术
	 * 
	 * @param value
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandStrength(int value, boolean open);

	/***
	 * 添加特殊属性--吸血状态
	 */
	public abstract void addExpandSuckHp(int value, boolean open);

	/**
	 * 添加附加值--气血--hp
	 * 
	 * @param value
	 * @param percent
	 *            true增加角色基础值得百分比，false在角色基础值上增加固定值
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandSupHp(int value, boolean percent, boolean open);

	/**
	 * 添加附加值--灵力上限--mp
	 * 
	 * @param value
	 * @param percent
	 *            true增加角色基础值得百分比，false在角色基础值上增加固定值
	 * @param open
	 *            是否开启
	 */
	public abstract void addExpandSupMp(int value, boolean percent, boolean open);

	/**
	 * 添加一个扩展属性
	 * 
	 * @param key
	 *            属性键
	 * @param value
	 *            属性值
	 * @param offset
	 *            在属性值的基础上的偏移量
	 * @param percent
	 *            是否为百分比（true为百分比，false为具体的值）
	 * @param open
	 *            是否开启（true为开启，false未开启）
	 */
	public abstract void addExpandProp(int key, int value, int offset, boolean percent, boolean open);

	/**
	 * 添加一个扩展属性
	 * 
	 * @param key
	 *            属性键
	 * @param value
	 *            属性值
	 * @param percent
	 *            是否为百分比（true为百分比，false为具体的值）
	 * @param open
	 *            是否开启（true为开启，false未开启）
	 */
	public abstract void addExpandProp(int key, int value, boolean percent, boolean open);

	/**
	 * 添加一个扩展属性
	 * 
	 * @param key
	 *            属性键
	 * @param value
	 *            属性值
	 * @param open
	 *            是否开启（true为开启，false未开启）
	 */
	public abstract void addExpandProp(int key, int value, boolean open);

	/**
	 * 添加一个扩展属性
	 * 
	 * @param key
	 *            属性键
	 * @param value
	 *            属性值
	 */
	public abstract void addExpandProp(int key, int value);

	/**
	 * 添加一组扩展属性
	 * 
	 * @param props
	 */
	public abstract void addExpandProps(Map<Integer, Integer> props);

	/**
	 * 添加基础值-减防
	 * 
	 * @param value
	 *            减防
	 */
	public void addBaseReduceDefense(int value);

	/**
	 * 添加基础值-吸血
	 * 
	 * @param value
	 *            吸血
	 */
	public void addBaseSuckHp(int value);

	/**
	 * 添加基础值-反弹
	 * 
	 * @param value
	 *            反弹
	 */
	public void addBaseRebound(int value);

	/**
	 * 设置使用物品的坐标
	 * 
	 * @param sceneId
	 *            场景编号
	 * @param tilex
	 *            TILE坐标-x
	 * @param tiley
	 *            TILE坐标-y
	 * @param distance
	 *            像素距离
	 */
	public void setLocation(int sceneId, short tilex, short tiley, short distance);

	/**
	 * 验证角色是否满足使用条件
	 * 
	 * @param role
	 *            角色
	 * @return true满足条件，false验证失败
	 */
	public boolean validate(Role role);

	/**
	 * 添加基础值-抵抗
	 * 
	 * @param value
	 *            抵抗值
	 */
	public void addBaseResist(int value);

	public byte getBindType();

	public short getIndex();

	public byte getHoleAndExpandSzie();

	public void takeEffect(Role role);

	public void takeEffect(Role role, short cate);

	public void packetData(IoBuffer buf);

	public void packetEmailData(IoBuffer buf);

	public void setBindType(byte bindType);

	public int remainOverlapCount();

	public boolean isBinded();

	public int changeCount(int i);

	public void setUiZone(short bagZone);

	public void initGoldPrice();

	public short getUiZone();

	// public AGoods cloneFull();

	public AGoods clone();

	public void setIndex(short goodsIndex);

	public List<Function> getHoles();

	public void goodsDetailProperty(IoBuffer buf);

	public void goodsHolsProperty(Role toRole, IoBuffer buf);

	public void packetPrice(IoBuffer buf, Role role, int shopType);

	public void goodsNoLocalProperty(Role hostRole, IoBuffer buf);

	public void goodsNoLocalProperty(Role hostRole, IoBuffer buf, int count);

	public void goodsAllProperty(Role hostRole, IoBuffer buf, StringBuilder sb);

	public void goodsAllProperty(Role hostRole, IoBuffer buf, StringBuilder sb, int count);

	public void goodsPropertyToCommonScriptUi(int hostId, IoBuffer buf, StringBuilder sb, int count);

	public void goodsPropertyToCommonScriptUi(Role hostRole, IoBuffer buf, StringBuilder sb);

	public void goodsPropertyToPointScriptUi(int hostId, IoBuffer buf, StringBuilder sb, int count);

	public void goodsPropertyToPointScriptUi(Role hostRole, IoBuffer buf, StringBuilder sb);

	/**
	 * 通过价格组获取价格
	 * 
	 * @param groupId
	 * @return
	 */
	public PriceGroup getPriceGroup(short groupId);

	/**
	 * 物品是否已过有效期
	 */
	public boolean isTimeOver();

	/**
	 * 根据角色获取角色的使用次数限制
	 * 
	 * @param role
	 *            角色
	 * @return 使用次数限制
	 */
	public int getUseLimitCount(Role role);

	/** 是否能批量使用 */
	public boolean isBatch();

	/** 物品的归属角色 */
	public void setRoleId(int roleId);

	/**
	 * 是否对象占用的资源
	 */
	public void release();

	public void setShowFilter(ShopFilter shopFilter);

	public boolean showFail(IExpressionData expressionData);

	/** 获取物品绑定的技能ID */
	public int getSkillId();

	/** 封装物品基出属性 */
	public void packagBasePro(IoBuffer buf);

	public void packagExpandProp(IoBuffer buf);

	public List<Function> getAllProperty();

	public int getJingLianCount();

	public byte getState();

	public void setState(byte state);

	public String getPreviewAnimation();

	public int getEquipedRoleId();

	public void setEquipedRoleId(int equipedRoleId);

	public int getMakeId();

	public byte getPinZhi();

	public byte getStarCount();

	public int getPertainId();

	public void setPertainId(int pertainId);

	/**
	 * 获取归属的背包袋
	 * 
	 * @return
	 */
	public short getBelongBag();

	public void setBelongBag(short bag);

	public List<AAwardGoods> getAwardGoodses();

	public ShopFilter getBuyFilter();

	public void setBuyFilter(ShopFilter buyFilter);

	public boolean buyFail(IExpressionData expressionData, int shopType);

	public boolean isOpenAllHoles();

	public void setOpenAllHoles(boolean openAllHoles);

	public void updateGoodsPro(Role role, short key, int value, int connectId);

	public String getNoticeInfo(String hostName);

	/**
	 * 设置物品品质
	 * 
	 * @param pinZhi
	 */
	public void setPinZhi(byte pinZhi);

	/**
	 * 物品是否是新获得
	 * 
	 * @param newState
	 */
	public void setNewState(byte newState);

	public boolean isMoneyGoods();

	public int getPriceByYuanBao();

	public long getGetTime();

	public void setGetTime(long getTime);

	public byte getOpenState();

	public String getMainAni();

	public boolean isCanEquiped();

	public int getAssess(int averageLevel);

	public int getFight();

	public boolean isAssess();

	public byte getGoodsAction();
}
