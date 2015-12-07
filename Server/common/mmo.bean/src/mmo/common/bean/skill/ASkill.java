package mmo.common.bean.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import mmo.common.bean.buf.SubjoinBuf;
import mmo.common.bean.extension.SkillSet;
import mmo.common.bean.role.Role;
import mmo.common.config.MoneyConfig;
import mmo.common.config.SkillConfig;
import mmo.common.config.skill.SkillFlag;
import mmo.common.config.skill.SkillPersist;
import mmo.common.config.version.VersionConfig;
import mmo.module.logger.role.LogGoodsOperates;
import mmo.tools.util.MathUtil;

public class ASkill implements Cloneable {
	/** 全体 */
	public static final byte                     FLAG_ALL        = 2;
	/** 友方 */
	public static final byte                     FLAG_FRIEND     = 0;
	/** 敌方 */
	public static final byte                     FLAG_ENMITY     = 1;
	protected static final int                   CHANGE_HP       = 0x01ffffff;
	protected static final int                   CHANGE_MP       = 0x02ffffff;
	protected static final int                   CHANGE_EXP      = 0x03ffffff;

	protected static final Map<Integer, ASkill>  NULL            = new HashMap<Integer, ASkill>();
	protected static final SkillSet              NULL_SET        = new SkillSet();
	protected static final Map<Byte, Integer>    NULL_CONDITION  = new HashMap<Byte, Integer>();
	protected static final Role                  NULL_HOST       = new Role();
	protected static final Map<Integer, Integer> NULL_PARAMETER  = new HashMap<Integer, Integer>();
	/** 施法时方向 **/
	protected short                              directAngle;
	/** 区域技能中心坐标点X */
	protected short                              centerX         = 0;
	/** 区域技能中心坐标点Y */
	protected short                              centerY         = 0;
	/** 准备时间 **/
	protected int                                pretime         = 0;
	/** 冷却时间 **/
	protected int                                cooltime        = 2000;
	/** 施法几率 */
	protected short                              rate;
	/***************************************************************************************/
	/************* 技能基本属性 **************/
	/***************************************************************************************/
	/** 技能名称 */
	protected String                             name;
	/** 小图标 **/
	protected String                             icon;
	/** 技能最高等级 **/
	protected short                              maxLevel;
	/** 技能等级 */
	protected short                              level           = 0;
	/** 技能编号 **/
	protected int                                skillid;
	/** 1秘籍，2技能，3心法 **/
	protected short                              mainType;
	// /** 类别：普通攻击-主动技-被动技-BUF-状态区-消耗品 **/
	/** 类别：职业技能/法宝技能/宠物技能/BUFF */
	protected short                              cate;
	/** 附加类别：药品-普通攻击-执行BUF-BUF监听伤害-BUF监听治疗-BUF监听经验-BUF监听攻击 */
	protected short                              extraCate;
	/** 效果：0增益，1减益 */
	protected byte                               effect;
	/** 标识位 **/
	protected short                              flag;
	/***************************************************************************************/
	/************* 角色相关 **************/
	/***************************************************************************************/
	/** 施法对象 **/
	protected Role                               firer           = null;
	/** 施法时选中的目标对象 **/
	protected Role                               focus           = null;
	// /** 更新属性的目标对象 **/
	// protected Role operateTarget = null;
	/** 继承施法者的团队标识 **/
	protected int                                teamId          = 0;
	/** 继承施法者的战团标识 **/
	protected short                              groupId;
	/** 继承施法者的阵营标识 **/
	protected int                                camp;
	/** 技能的拥有者 */
	protected int                                roleid;
	/** 和角色绑定后的ID */
	protected int                                roleSkillId;
	/***************************************************************************************/
	/************* 相关配置-等级-秘籍-技能-心法 **************/
	/***************************************************************************************/
	/** 秘籍绑定的技能集或技能绑定的心法集 */
	protected List<SkillSet>                     setSkill        = new ArrayList<SkillSet>();
	/** 秘籍绑定的技能的开启等级或技能各个等级的开启等级或技能绑定的心法的开启等级 */
	protected Map<Short, SkillSet>               setLevel        = new HashMap<Short, SkillSet>();
	/** 绑定技能（秘籍绑定技能或技能绑定的心法） */
	protected int                                activeSkill;
	/** 秘籍列表中已经开启技能 */
	protected Map<Integer, ASkill>               openedList      = NULL;
	/** 共用 秘籍绑定的技能技能绑定的心法 */
	protected Map<Integer, ASkill>               bindSkillsMap   = new HashMap<Integer, ASkill>();
	/** 开启条件 */
	protected SkillSet                           activeCondition = NULL_SET;
	/** 技能参数 **/
	protected Map<Integer, Integer>              parameters      = null;
	/** 各个版本的ICON */
	protected String                             iconVersions;
	/** 版本号-ICON */
	protected Map<Byte, String>                  iconVersionMap  = new HashMap<Byte, String>();
	/** 技能冷却时间或BUF的剩余时间是否要保存到数据库 */
	protected byte                               persist;
	/** 父技能-创建该对象的技能 */
	protected int                                parentSkill;

	protected byte[]                             iconData;                                         // 技能图标数据
	protected String                             note;                                             // 技能描述
	protected Map<Short, String>                 notes           = new HashMap<Short, String>();   // 关于技能在各个级别的描述信息

	public void init() {
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		if (note != null) {
			this.note = note.trim();
		}
	}

	/**
	 * 获取施法时的方向
	 * 
	 * @return byte 施法时面向的方向
	 */
	public short getDirectAngle() {
		return directAngle;
	}

	/**
	 * 设置施法时面向的方向
	 * 
	 * @param directAngle
	 *            施法时面向的方向
	 */
	public void setDirectAngle(short directAngle) {
		this.directAngle = directAngle;
	}

	/**
	 * 获取区域技能中心坐标点X
	 * 
	 * @return short 区域技能中心坐标点X
	 */
	public short getCenterX() {
		return centerX;
	}

	/**
	 * 设置区域技能中心坐标点X
	 * 
	 * @param centerX
	 *            区域技能中心坐标点X
	 */
	public void setCenterX(short centerX) {
		this.centerX = centerX;
	}

	/**
	 * 获取区域技能中心坐标点Y
	 * 
	 * @return short 区域技能中心坐标点Y
	 */
	public short getCenterY() {
		return centerY;
	}

	/**
	 * 设置区域技能中心坐标点Y
	 * 
	 * @param centerY
	 *            区域技能中心坐标点Y
	 */
	public void setCenterY(short centerY) {
		this.centerY = centerY;
	}

	/**
	 * 获取技能剩余的冷却时间(ms)
	 * 
	 * @return
	 */
	public int getRemainCool() {
		return 0;
	}

	/**
	 * 获取触发过滤器的角色
	 * 
	 * @return 触发过滤器的角色
	 */
	public Role getFilterTrigger() {
		return null;
	}

	/**
	 * 设置触发过滤器的角色
	 * 
	 * @return 触发过滤器的角色
	 */
	public void setFilterTrigger(Role trigger) {

	}

	/**
	 * 是否为减益
	 * 
	 * @return true减益，false增益
	 */
	public boolean isMinus() {
		return effect == 1;
	}

	/**
	 * 获取技能依赖的条件
	 * 
	 * @return 技能依赖的条件
	 */
	public Map<Byte, Integer> getConditions() {
		return NULL_CONDITION;
	}

	public void initFlag() {

	}

	public void setGameSkill(ASkill gameSkill) {

	}

	public void initBuf(ASkill parent, Role target, int continueTime, int refreshOffset) {

	}

	/**
	 * 初始化BUF数据
	 * 
	 * @param parent
	 *            父技能
	 * @param target
	 *            目标
	 */
	public void initBuf(ASkill parent, Role target) {

	}

	public void initStateZone(ASkill parent, int continueTime, int refreshOffset, short tileX, short tileY, short radiusX, short radiusY, int actID) {

	}

	public void initStateZone(ASkill parent, int centerX, int centerY, int radiusX, int radiusY) {

	}

	/**
	 * 给角色添加一个BUF
	 * 
	 * @param role
	 *            目标角色
	 * @param subBuf
	 *            buf数据集
	 */
	public void addBuf(Role role, SubjoinBuf subBuf) {

	}

	/**
	 * 给角色添加一个只执行一次的BUF
	 * 
	 * @param role
	 *            目标角色对象
	 * @param bufId
	 *            Buf对应的技能ID
	 * @param continueTime
	 *            持续时间
	 * @param flag
	 *            玩家身上已经有该技能时的处理方式：0不添加直接返回，1重置该BUF计时器，2叠加
	 */
	public void addBuf(Role role, int bufId, int continueTime, int flag, Map<Integer, Integer> parameters) {
		addBuf(role, bufId, continueTime, -1, flag, parameters);
	}

	/**
	 * 给角色添加一个状态Buf
	 * 
	 * @param role
	 *            目标角色对象
	 * @param bufId
	 *            Buf对应的技能ID
	 * @param continueTime
	 *            持续时间
	 * @param refreshOffset
	 *            刷新间隔
	 * @param flag
	 *            玩家身上已经有该技能时的处理方式：0不添加直接返回，1重置该BUF计时器，2叠加
	 */
	public void addBuf(Role role, int bufId, int continueTime, int refreshOffset, int flag, Map<Integer, Integer> parameter) {
	}

	/**
	 * 给角色添加一个状态Buf
	 * 
	 * @param role
	 *            目标角色对象
	 * @param bufId
	 *            Buf对应的技能ID
	 * @param continueTime
	 *            持续时间
	 * @param refreshOffset
	 *            刷新间隔
	 * @param flag
	 *            玩家身上已经有该技能时的处理方式：0不添加直接返回，1重置该BUF计时器，2叠加
	 */
	public void addBuf(Role role, int bufId, int continueTime, int refreshOffset, int flag) {
		addBuf(role, bufId, continueTime, refreshOffset, flag, new HashMap<Integer, Integer>());
	}

	public boolean isEndCooltime() {
		return false;
	}

	public void clearBuf(Role role, int bufId) {

	}

	public void flushBufAndStateArea() {

	}

	public void clearMinusBuf(Role role, int count) {

	}

	public void clearPlusBuf(Role role, int count) {

	}

	public ASkill clone() {
		ASkill o = null;
		try {
			o = (ASkill) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	public void clearSkillCooltime(Role role) {

	}

	public void clearSkillCooltime(Role role, int... skillId) {

	}

	public void createStateZone(Map<Integer, Integer> parameters, int actId, short offset, short radiusX, short radiusY, int continueTime,
	        int refreshOffset, int skillid) {

	}

	/**
	 * 
	 * @param parameters
	 *            伤害参数
	 * @param actId
	 *            动作Id
	 * @param centerX
	 *            中心点X坐标
	 * @param centerY
	 *            中心点Y坐标
	 * @param radiusX
	 *            搜索范围X坐标
	 * @param radiusY
	 *            搜索范围Y坐标
	 * @param continueTime
	 *            持续时间
	 * @param refreshOffset
	 *            间隔时间
	 * @param skillid
	 *            BUF_Id
	 */
	public void createStateZone(Map<Integer, Integer> parameters, int actId, short centerX, short centerY, short radiusX, short radiusY,
	        int continueTime, int refreshOffset, int skillid) {
	}

	/**
	 * 创建一个场景BUF
	 * 
	 * @param centerX
	 *            中心点X坐标
	 * @param centerY
	 *            中心点Y坐标
	 * @param subBuf
	 *            子BUF
	 */
	public void createStateZone(int centerX, int centerY, SubjoinBuf subBuf) {

	}

	public void deplete(short mp, short hp) {

	}

	// public void endUpdateRole() {
	//
	// }

	public int getBufCount(Role role, int bufId) {
		return 0;
	}

	public int getRemainTime(Role role, int bufId) {
		return 0;
	}

	public short getNeedNimbus() {
		return 0;
	}

	public ASkill getRoleSkill(Role role, int skillId) {
		return null;
	}

	public List<Role> limitSearchRoleInBuf(int flag, int buf, int count) {
		return null;
	}

	public List<Role> limitSearchRoleInScene(int tileX, int tileY, int radiusX, int radiusY, int flag, int count) {
		return null;
	}

	public List<Role> limitSearchRoleInScene(int tileX, int tileY, int radiusX, int radiusY, int flag, int buf, int count) {
		return null;
	}

	public List<Role> limitSearchRoles(int flag, int count) {
		return null;
	}

	public List<Role> limitSearchRoles(Role role, int offset, int radiusX, int radiusY, int flag, int count) {
		return null;
	}

	public List<Role> limitSearchRoles(Role role, int offset, int radiusX, int radiusY, int flag, int buf, int count) {
		return null;
	}

	public void resetBuf(Role role, int bufId) {

	}

	public List<Role> searchRoleInBuf(int flag, int buf) {
		return null;
	}

	public List<Role> searchRoleInScene(int tileX, int tileY, int radiusX, int radiusY, int flag) {
		return null;
	}

	public List<Role> searchRoleInScene(int tileX, int tileY, int radiusX, int radiusY, int flag, int buf) {
		return null;
	}

	public List<Role> searchRoles(int flag) {
		return null;
	}

	/**
	 * 以角色为参照物，在角色面向的方向上偏移一定的距离为中心，搜索指定类型的角色
	 * 
	 * @param role
	 *            参照的角色
	 * @param offset
	 *            偏移量
	 * @param radiusX
	 *            检索半径X
	 * @param radiusY
	 *            检索半径Y
	 * @param flag
	 *            标识：0搜索全部，1：己方单位，2敌方单位
	 * @return
	 */
	public List<Role> searchRoles(Role role, int offset, int radiusX, int radiusY, int flag) {
		return null;
	}

	public List<Role> searchRoles(Role role, int offset, int radiusX, int radiusY, int flag, int buf) {
		return null;
	}

	public List<Role> searchRoles(Role role) {
		return null;
	}

	public List<Role> searchRoles(int centerX, int centerY, int flag) {
		return null;
	}

	public void setNeedNimbus(int needNimbus) {

	}

	// public void setOperateRole(Role role) {
	//
	// }

	public void switchState() {

	}

	/**
	 * 更新角色的行为状态
	 * 
	 * @param switchState
	 *            是否要切换状态（true切换攻击——防御，false保持原状态）
	 * 
	 * @param physics
	 *            -1禁止物理攻击，0保持原状态，1恢复物理攻击
	 * @param magic
	 *            -1禁止法术攻击，0保持原状态，1恢复法术攻击
	 * @param move
	 *            -1禁止移动，0保持原状态，1恢复移动
	 * @param distortion
	 *            -1退出变身，0保持原状态，1进入变身
	 * @param evil
	 *            -1退出魔化，0保持原状态，1进入魔化
	 */
	public void updateBehavior(boolean switchState, int physics, int magic, int move, int distortion, int evil) {

	}

	public int updateBufRemainTime(Role role, int bufId, int value) {
		return 0;
	}

	public void updateRoleAttack(int value) {

	}

	public void updateRoleDuckChance(short value) {

	}

	public void updateRoleImmune(short value) {

	}

	public void updateRoleReboundPercent(int reboundPercent) {

	}

	/**
	 * 更新HP，该方法不被监听器监听
	 * 
	 * @param value
	 *            变化量
	 */
	public void updateRoleHp(Role operateTarget, int value) {

	}

	// /**
	// * 受到伤害-HP减少更新，该方法不被监听器监听
	// *
	// * @param value
	// * 变化量
	// */
	// public void updateRoleHpForHurt(int value) {
	//
	// }
	//
	// /**
	// * 治疗-HP增加更新，该方法不被监听器监听
	// *
	// * @param value
	// * 变化量
	// */
	// public void updateRoleHpForCure(int value) {
	//
	// }

	/**
	 * 更新HP，该方法将会被监听器监听
	 * 
	 * @param value
	 *            变化量
	 * @param effect
	 *            效果
	 */
	public void updateRoleHp(Role target, int value, int effect) {

	}

	// /**
	// * 受到伤害-HP减少更新，该方法被监听器监听
	// *
	// * @param value
	// * 变化量
	// */
	// public void updateRoleHpForHurt(int value, int effect) {
	//
	// }
	//
	// /**
	// * 治疗-HP增加更新，该方法被监听器监听
	// *
	// * @param value
	// * 变化量
	// */
	// public void updateRoleHpForCure(int value, int effect) {
	//
	// }

	public void updateRoleMaxHp(int value) {

	}

	// public void updateRoleMaxMp(short value) {
	//
	// }

	public void updateRoleMoveSpeed(short value) {

	}

	// public void updateRoleMp(int value) {
	//
	// }

	// /**
	// * 更新角色的魔法值
	// *
	// * @param value
	// * 变化值
	// * @param effect
	// * 效果
	// */
	// public void updateRoleMp(int value, int effect) {
	//
	// }

	public void updateRoleCruelChance(short value) {

	}

	public void updateRoleAttackChance(short value) {
	}

	public void updateRoleDefence(int value) {

	}

	public boolean preJudge() {
		return false;
	}

	public void addPet(Role pet) {
	}

	public Role generatePet() {
		return null;
	}

	public boolean isReady2Play() {
		return false;
	}

	public void toOther() {

	}

	public void finish() {

	}

	public List<EffectLog> getEffectLogs() {
		return null;
	}

	public class EffectLog {
		protected short key   = 0;
		protected int   value = 0;

		public EffectLog(short key, int value) {
			this.key = key;
			this.value = value;
		}

		public short getKey() {
			return key;
		}

		public void setKey(short key) {
			this.key = key;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	/***
	 * 伤害反弹
	 * 
	 * @author 李天喜
	 * 
	 */
	public class Rebound {
		public ASkill source;
		public int    hurt;
		public Role   role;

		public Rebound(ASkill source, int rebound, Role target) {
			this.source = source;
			this.hurt = rebound;
			this.role = target;
		}
	}

	/***
	 * 伤害反弹
	 * 
	 * @author 李天喜
	 * 
	 */
	public class Retort {
		public ASkill parent;
		public int    hurt;
		public short  radius;

		public Retort(ASkill source, int rebound, short radius) {
			this.parent = source;
			this.hurt = rebound;
			this.radius = radius;
		}
	}

	public int getNextSkill() {
		return 0;
	}

	public short getDepend() {
		return 0;
	}

	/**
	 * 添加释放技能依赖的条件
	 * 
	 * @param type
	 *            条件类型（持有BUF，没有BUF，状态集）
	 * @param value
	 *            BUF编号或状态集合
	 */
	public void addCondition(byte type, int value) {

	}

	public byte getTarget() {
		return 0;
	}

	public String getActionFile() {
		return null;
	}

	public boolean playSkill() {
		return false;
	}

	public void initSkill() {

	}

	public void setDepend(short short1) {

	}

	public void setCombo(byte byte1) {

	}

	public void setNextSkill(int int1) {

	}

	public void setMaxLevel(short short1) {

	}

	public void setProfession(byte short1) {

	}

	public void setEffect(byte byte1) {

	}

	public void setActionFile(String string) {

	}

	public void setBinds(String string) {

	}

	public void setInital(byte byte1) {

	}

	public void setActionCate(byte byte1) {

	}

	public void setTarget(byte byte1) {

	}

	public boolean isFinish() {
		return false;
	}

	public boolean refresh() {
		return false;
	}

	/**
	 * 通知角色创建BUF
	 * 
	 * @param role
	 *            角色对象
	 */
	public void createBuf(Role role) {

	}

	/**
	 * 添加一对参数
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void addParameter(int key, int value) {
		if (parameters == null) {
			parameters = new HashMap<Integer, Integer>();
		}
		parameters.put(key, value);
	}

	/**
	 * 获取参数值
	 * 
	 * @param key
	 *            键
	 * @return 值
	 */
	public int getParameter(int key) {
		if (parameters == null) {
			return 0;
		}
		Integer value = parameters.get(key);
		if (value == null) {
			return 0;
		}
		return value;
	}

	/**
	 * 添加HP的变化量
	 * 
	 * @param changeHp
	 *            HP变化量
	 */
	public void addParameterHp(int changeHp) {
		parameters.put(CHANGE_HP, changeHp);
	}

	/**
	 * 添加HP的变化量
	 * 
	 * @param changeMp
	 *            MP变化量
	 */
	public void addParameterMp(int changeMp) {
		parameters.put(CHANGE_MP, changeMp);
	}

	/**
	 * 添加经验的变化量
	 * 
	 * @param changeExp
	 *            经验变化量
	 */
	public void addParameterExp(int changeExp) {
		parameters.put(CHANGE_EXP, changeExp);
	}

	/**
	 * 获得HP的变化量
	 * 
	 * @return HP的变化量
	 */
	public int getHpChangeValue() {
		if (parameters == null) {
			return 0;
		}
		Integer value = parameters.get(CHANGE_HP);
		if (value == null) {
			return 0;
		}
		return value;
	}

	/**
	 * 获得MP的变化量
	 * 
	 * @return MP的变化量
	 */
	public int getMpChangeValue() {
		if (parameters == null) {
			return 0;
		}
		Integer value = parameters.get(CHANGE_MP);
		if (value == null) {
			return 0;
		}
		return value;
	}

	/**
	 * 获得经验的变化量
	 * 
	 * @return 经验的变化量
	 */
	public int getExpChangeValue() {
		if (parameters == null) {
			return 0;
		}
		Integer value = parameters.get(CHANGE_EXP);
		if (value == null) {
			return 0;
		}
		return value;
	}

	public void setCooltime(int cooltime) {
		this.cooltime = cooltime;
	}

	public short getRate() {
		return rate;
	}

	public void setRate(short rate) {
		this.rate = rate;
	}

	public int getCooltime() {
		return cooltime;
	}

	public int getPretime() {
		return pretime;
	}

	public void setPretime(int pretime) {
		this.pretime = pretime;
	}

	/**
	 * 改变当前的冷却实际
	 * 
	 * @param value
	 *            冷却改变量（>0代表冷却时间加长）
	 */
	public void changeEndCoolTime(int value) {
	}

	//
	/**
	 * 获取消耗的HP
	 * 
	 * @return short 消耗的HP
	 */
	public short getNeedHp() {
		return 0;
	}

	/**
	 * 获取消耗的魔法值
	 * 
	 * @return short 消耗的魔法值
	 */
	public short getNeedMp() {
		return 0;
	}

	//
	/**
	 * 设置施法距离
	 * 
	 * @param needDistance
	 *            施法距离
	 */
	public void setNeedDistance(short needDistance) {
	}

	/**
	 * 设置消耗的HP
	 * 
	 * @param needHP
	 *            消耗的HP
	 */
	public void setNeedHp(int needHP) {
	}

	/**
	 * 设置消耗的魔法
	 * 
	 * @param needMP
	 *            消耗的魔法
	 */
	public void setNeedMp(int needMP) {
	}

	public short getNeedDistance() {
		return 0;
	}

	/**
	 * 带有延迟时间的启动冷却
	 * 
	 * @param lateTime
	 */
	public void startCool(int lateTime) {
	}

	protected boolean isOutArea(int centerX, int centerY, int radiusX, int radiusY, Role role) {
		return role.isDeath() || MathUtil.point2ellips(role.getPixelX(), role.getPixelY(), centerX, centerY, radiusX, radiusY) > 1;
	}

	public void clearCooltime() {
		// TODO Auto-generated method stub

	}

	/**
	 * 目标对象包括施法者
	 */
	public final void includeFirer() {
		this.flag |= SkillFlag.FIRER_IN;
	}

	public void addNote(short level, String note) {
		notes.put(level, note);
	}

	public Map<Short, String> getNotes() {
		return notes;
	}

	public void setNotes(Map<Short, String> notes) {
		this.notes = notes;
	}

	/**
	 * 附加类别：药品-普通攻击-执行BUF-BUF监听伤害-BUF监听治疗-BUF监听经验-BUF监听攻击
	 * 
	 * @return 附加类别
	 */
	public short getExtraCate() {
		return extraCate;
	}

	/**
	 * 设置附加类别：药品-普通攻击-执行BUF-BUF监听伤害-BUF监听治疗-BUF监听经验-BUF监听攻击
	 * 
	 * @param extraCate
	 *            附加类别
	 */
	public void setExtraCate(short extraCate) {
		this.extraCate = extraCate;
	}

	public String getInfo() {
		return skillid + ":" + name;
	}

	/**
	 * 判断是否可以升级
	 * 
	 * @return 技能升级失败原因，升级成功返回null
	 */
	public String isLevelUp() {
		if (firer == null) {
			return "宿主对象不存在";
		}
		if (firer.getLevel() < getUpgradeLevel()) {
			return "升级失败，你的等级需要达到" + getUpgradeLevel();
		}
		if (level >= getMaxLevel()) {
			level = getMaxLevel();
			return "升级失败，技能已经满级！";
		}
		if (firer.getMoney(MoneyConfig.XIU_WEI_1012) < getUpgradeCost()) {
			return "修炼点不足" + getUpgradeCost() + "，不能升级！";
		}
		return null;
	}

	/**
	 * 技能升级
	 * 
	 * @return 技能升级失败原因，升级成功返回null
	 */
	public String levelUp() {
		if (firer == null) {
			return "宿主对象不存在";
		}
		if (firer.getLevel() < getUpgradeLevel()) {
			return "升级失败，你的等级需要达到" + getUpgradeLevel();
		}
		if (level >= firer.getLevel()) {
			level = firer.getLevel();
			return "升级失败，技能等级已达当前最高级，需先提高人物等级";
		}
		int cost = getUpgradeCost();
		if (firer.getMoney(MoneyConfig.XIU_WEI_1012) < cost) {
			return "修炼点不足" + cost + "，不能升级！";
		}
		firer.costMoney(MoneyConfig.XIU_WEI_1012, -cost, LogGoodsOperates.SystemCost.OPERATE_COST, LogGoodsOperates.SystemCost.PROJECT_SKILL_LEVEL,
		        firer.getOrderFormId());
		level += 1;
		return null;
	}

	public void setSkillid(int id) {
		this.skillid = id;
	}

	public int getSkillid() {
		return skillid;
	}

	public short getLevel() {
		return firer == null ? 0 : level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	/**
	 * 获取施法者对象
	 * 
	 * @return Role 施法者
	 */
	public Role getFirer() {
		return firer;
	}

	/**
	 * 获取施法时选中的目标对象
	 * 
	 * @return Role 施法时选中的目标对象
	 */
	public Role getFocus() {
		return focus;
	}

	// public Role getOperateTarget() {
	// return operateTarget;
	// }

	public void setFocus(Role focus) {
		this.focus = focus;
	}

	public void setFirer(Role firer) {
		this.firer = firer;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public short getGroupId() {
		return groupId;
	}

	public void setGroupId(short groupId) {
		this.groupId = groupId;
	}

	public void setCamp(short camp) {
		this.camp = camp;
	}

	// public void setOperateTarget(Role operateTarget) {
	// this.operateTarget = operateTarget;
	// }

	public String getIcon() {
		return icon;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCate(short cate) {
		this.cate = cate;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public List<SkillSet> getSetSkill() {
		return setSkill;
	}

	public int getActiveSkill() {
		return activeSkill;
	}

	public void setActiveSkill(int activeSkill) {
		this.activeSkill = activeSkill;
	}

	public void checkLevel() {
		if (firer == null) {
			return;
		}
		short temp = this.level;
		SkillSet ss = null;
		short tmpMax = maxLevel;
		if (tmpMax == -1) {
			tmpMax = firer.getLevel();
		}
		while ((++temp) <= tmpMax) {
			ss = setLevel.get(temp);
			if (ss != null && ss.validateLevel(firer) && ss.getMoney() <= 0) {
				this.level = ss.getLevel();
			} else {
				break;
			}
		}
	}

	public List<SkillSet> getSetLevelList() {
		List<SkillSet> list = new ArrayList<SkillSet>();
		list.addAll(setLevel.values());
		return list;
	}

	/**
	 * 获取指定等级的配置信息
	 * 
	 * @param level
	 *            等级
	 * @return 等级开启及学习条件
	 */
	public SkillSet getLevelSet(short level) {
		if (level == 0) {
			level = 1;
		}
		SkillSet ss = setLevel.get(level);
		if (ss == null) {
			ss = new SkillSet();
			ss.setLevel((short) (level + 1));
		}
		return ss;
	}

	/**
	 * 获取当前等级的配置信息
	 * 
	 * @return 等级开启及学习条件
	 */
	public SkillSet getLevelSet() {
		SkillSet ss = setLevel.get(level);
		if (ss == null) {
			ss = new SkillSet();
			ss.setLevel((short) (level + 1));
		}
		return ss;
	}

	/**
	 * 获取技能当前等级的开启等级
	 * 
	 * @return 开启时需要的玩家等级
	 */
	public short getActiveLevel() {
		SkillSet ss = setLevel.get(level);
		if (ss != null) {
			return ss.getLv();
		}
		return 1;
	}

	public int getRoleSkillId() {
		return roleSkillId;
	}

	/**
	 * 验证技能或心法是否被激活
	 * 
	 * @param skillId
	 *            带验证的技能ID
	 * @return true标识技能或心法被激活
	 */
	public boolean isActive(int skillId) {
		return this.activeSkill == skillId;
	}

	public void setRoleSkillId(int bindID) {
		this.roleSkillId = bindID;
	}

	public short getCate() {
		return cate;
	}

	public short getMainType() {
		return mainType;
	}

	public void setMainType(short mainType) {
		this.mainType = mainType;
	}

	public String toString() {
		return "CommonSkill id=" + skillid + ", name = " + name;
	}

	public String bind2tag() {
		StringBuilder sb = new StringBuilder();
		sb.append("<sets>");
		for (SkillSet ss : setSkill) {
			if (ss != null) {
				sb.append(ss.getTag());
			}
		}
		sb.append("</sets>");
		return sb.toString();
	}

	/**
	 * 设置等级描述
	 * 
	 * @param level
	 *            等级
	 * @param note
	 *            等级描述
	 */
	public void setLevelNote(int level, String note) {
		short tmp = (short) level;
		SkillSet levelSet = setLevel.get(tmp);
		if (levelSet == null) {
			setLevel.put(tmp, new SkillSet());
			levelSet = setLevel.get(tmp);
		}
		levelSet.setNote(note);
	}

	/**
	 * 设置技能相应等级的开启等级
	 * 
	 * @param skillLevel
	 *            技能等级
	 * @param studyLevel
	 *            开启等级
	 */
	public void setStudyLevel(short skillLevel, short studyLevel) {
		SkillSet levelSet = setLevel.get(skillLevel);
		if (levelSet == null) {
			setLevel.put(level, new SkillSet());
			levelSet = setLevel.get(level);
		}
		levelSet.setLv(studyLevel);
	}

	public SkillSet getActiveCondition() {
		return activeCondition;
	}

	public void setActiveCondition(SkillSet activeCondition) {
		this.activeCondition = activeCondition;
	}

	public void initOpenedMap() {
		openedList = new HashMap<Integer, ASkill>();
	}

	public void opendSubSkill(ASkill skill) {
		openedList.put(skill.getSkillid(), skill);
		skill.parentSkill = skillid;
	}

	public SkillSet getUpgradeSet() {
		return activeCondition;
	}

	public int getUpgradeCost() {
		return getUpgradeSet().getMoney();
	}

	public ASkill getSubSkill(int skillId) {
		return openedList.get(skillId);
	}

	public Map<Integer, ASkill> getOpenedList() {
		return openedList;
	}

	public int getUpgradeLevel() {
		return getUpgradeSet().getLv();
	}

	/**
	 * 获取秘籍绑定的技能列表或技能绑定的心法
	 * 
	 * @return 获取绑定的技能的列表
	 */
	public List<ASkill> getBindSkills() {
		return new ArrayList<ASkill>();
	}

	/***
	 * 验证秘籍是否包含该技能或技能是否包含该心法
	 * 
	 * @param skillId
	 *            带验证的技能编号
	 * @return true为包含
	 */
	public boolean validateSkill(int skillId) {
		for (SkillSet skill : setSkill) {
			if (skillId == skill.getId()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取运算参数集
	 * 
	 * @return 参数映射表
	 */
	public Map<Integer, Integer> getParameters() {
		if (this.parameters == null) {
			return NULL_PARAMETER;
		}
		return this.parameters;
	}

	/**
	 * 设置运算参数
	 * 
	 * @param parameters
	 */
	public void setParameters(Map<Integer, Integer> parameters) {
		this.parameters = parameters;
	}

	public Map<Integer, ASkill> getBindSkillsMap() {
		return bindSkillsMap;
	}

	public void addIconVersion(byte version, String icon) {
		iconVersionMap.remove(version);
		if (icon == null || icon.trim().length() == 0) {
			return;
		}
		iconVersionMap.put(version, icon.trim());
	}

	public String iconVersionToString() {
		if (iconVersionMap.size() > 0) {
			Set<Byte> keys = iconVersionMap.keySet();
			StringBuilder sb = new StringBuilder();
			for (byte key : keys) {
				sb.append(key).append("=").append(iconVersionMap.get(key)).append(";");
			}
			this.iconVersions = sb.toString();
		} else {
			this.iconVersions = "";
		}
		return this.iconVersions;
	}

	public String getIconByVersion(byte version) {
		String icon = iconVersionMap.get(version);
		if (icon == null || icon.length() == 0) {
			return "";
		}
		return icon;
	}

	public String getIconVersion() {
		return this.iconVersions;
	}

	public void setIconVersions(String iconVersions) {
		iconVersionMap.clear();
		if (iconVersions == null || iconVersions.length() == 0) {
			this.iconVersions = "";
			return;
		}
		this.iconVersions = iconVersions;
		// String[] versions = StringUtil.splitString(iconVersions, ';');
		// String[] icons = null;
		// int vlength = versions.length;
		// for (int vi = 0; vi < vlength; vi++) {
		// icons = StringUtil.splitString(versions[vi], '=');
		// iconVersionMap.put(Byte.parseByte(icons[0]), icons[1]);
		// }
		iconVersionMap.put(VersionConfig.IconVersion.middle, iconVersions);
	}

	public byte getPersist() {
		return persist;
	}

	public int getParentSkill() {
		return parentSkill;
	}

	public void setPersist(byte persist) {
		this.persist = persist;
	}

	/**
	 * 判断技能是否需要持久化
	 * 
	 * @return
	 */
	public boolean isPersist() {
		return this.persist == SkillPersist.persistYes;
	}

	/**
	 * 获取宿主对象
	 * 
	 * @return 宿主对象
	 */
	public Role getHost() {
		return NULL_HOST;
	}

	public short getMaxLevel() {
		return 0;
	}

	public short getFlag() {
		return 0;
	}

	public void release() {
		if (parameters != null) {
			parameters.clear();
			parameters = null;
		}
		focus = null;
		// operateTarget = null;
	}

	/**
	 * 设置技能的五行组合
	 * 
	 * @param fiveElementGroup
	 */
	public void changFiveElementGroup(short fiveElementGroup) {

	}

	/**
	 * 获取攻击力百分比
	 * 
	 * @return
	 */
	public int getAttackPercent() {
		return 0;
	}

	/**
	 * 获取附加攻击值
	 * 
	 * @return
	 */
	public int getAttackExpand() {
		return 0;
	}

	/**
	 * 获取附加攻击值增幅
	 * 
	 * @return
	 */
	public int getAttackGrow() {
		return 0;
	}

	// /**
	// * 获取检索范围半径X
	// *
	// * @return
	// */
	// public int getRadiusX() {
	// return 0;
	// }
	//
	// /**
	// * 获取检索范围半径Y
	// *
	// * @return
	// */
	// public int getRadiusY() {
	// return 0;
	// }

	/**
	 * 获取影响范围
	 * 
	 * @return
	 */
	public byte getEffectScope() {
		return SkillConfig.EffectScope.SINGLE;
	}

	/**
	 * 获取施法对象类型(自身、目标焦点、目标区域)
	 * 
	 * @return
	 */
	public byte getPlayTarget() {
		return SkillConfig.PlayTarget.FOCUS;
	}

	/**
	 * 获取伤害次数
	 * 
	 * @return
	 */
	public int getHurtCounts() {
		return 0;
	}

	/**
	 * 获取子BUF1
	 * 
	 * @return
	 */
	public SubjoinBuf getSubBuf_1() {
		return null;
	}

	/**
	 * 获取子BUF2
	 * 
	 * @return
	 */
	public SubjoinBuf getSubBuf_2() {
		return null;
	}

	/**
	 * 获取BUF类型 增益或减益
	 * 
	 * @return
	 */
	public byte getBufType() {
		return SkillConfig.BufType.DEBUFF;
	}

	/**
	 * 获取对象类型
	 * 
	 * @return
	 */
	public byte getTargetType() {
		return FLAG_ENMITY;
	}

	/**
	 * 获取目标区域技能的偏移量
	 * 
	 * @return
	 */
	public int getOffset() {
		return 0;
	}

	/**
	 * 获取免疫属性值
	 * 
	 * @return
	 */
	public short getBufImmune() {
		return 0;
	}

	/**
	 * 获取最多影响目标数量
	 * 
	 * @return
	 */
	public int getTargetMaxCount() {
		return 0;
	}

	/**
	 * 获取驱散五行的值
	 * 
	 * @return
	 */
	public short getDispel_effect() {
		return 0;
	}

	/**
	 * 获取五行值
	 * 
	 * @return
	 */
	public short getFiveElements() {
		return 0;
	}

	/**
	 * 获取技能增加攻击力
	 * 
	 * @return
	 */
	public int getAddAttack() {
		return 0;
	}

	/**
	 * 获取技能增加攻击力增幅
	 * 
	 * @return
	 */
	public int getAddAttackGrow() {
		return 0;
	}

	/**
	 * 获取技能增加生命
	 * 
	 * @return
	 */
	public int getAddMaxHp() {
		return 0;
	}

	/**
	 * 获取技能增加生命增幅
	 * 
	 * @return
	 */
	public int getAddMaxHpGrow() {
		return 0;
	}

	/**
	 * 获取技能增加防御
	 * 
	 * @return
	 */
	public int getAddDefence() {
		return 0;
	}

	/**
	 * 获取技能增加防御增幅
	 * 
	 * @return
	 */
	public int getAddDefenceGrow() {
		return 0;
	}

	/**
	 * 修改角色身上该BUF当前叠加层数
	 * 
	 * @param currentNumber
	 *            添加的层数
	 */
	public void setCurrentNumber(int currentNumber) {

	}

	/**
	 * 获取角色身上该BUF叠加的层数
	 * 
	 * @return
	 */
	public int getCurrentNumber() {

		return 0;
	}

	/**
	 * 清除该BUF的层数
	 */
	public void clearCurrentNumber() {

	}

	/**
	 * 获取生命百分比
	 * 
	 * @return
	 */
	public int getHpPercent() {
		return 0;
	}

	/**
	 * 获取附加生命
	 * 
	 * @return
	 */
	public int getHpExpand() {
		return 0;
	}

	/**
	 * 获取防御百分比
	 * 
	 * @return
	 */
	public int getDefencePercent() {
		return 0;
	}

	/**
	 * 获取附加防御
	 * 
	 * @return
	 */
	public int getDefenceExpand() {
		return 0;
	}

	/**
	 * 获取技能的职业类型
	 * 
	 * @return
	 */
	public byte getProfession() {
		return 0;
	}

	/**
	 * 获取BUF类别
	 * 
	 * @return
	 */
	public byte getBufCate() {
		return 0;
	}

	/**
	 * 获取施放类型
	 * 
	 * @return
	 */
	public short getPlayType() {
		return SkillConfig.playType.INITIATIVE;
	}

	/**
	 * 特殊移动 5527协议
	 */
	public void pro_5527_specialMove(int[] path, short speed) {

	}

	/**
	 * 设置BUF于图层物理层关联的序号
	 * 
	 * @param key
	 */
	public void setPhyKey(int key) {

	}

	/**
	 * 技能对角色属性产生影响(被动技能)
	 * 
	 * @param 是否是升级技能导致
	 */
	public void effectToRolePro(boolean isLevelUp) {

	}

	public int getDestroyExpand() {
		return 0;
	}

	public int getFenderExpand() {
		return 0;
	}

	public int getDuckChanceExpand() {
		return 0;
	}

	public int getCruelExpand() {
		return 0;
	}

	public int getTenacityExpand() {
		return 0;
	}

	public int getAttackChanceExpand() {
		return 0;
	}

	public Map<Integer, Integer> getProps() {
		return null;
	}

	public void setOpenState(byte openState) {

	}

	public byte getOpenState() {
		return 0;
	}

	public byte getSkillQuality() {
		return 0;
	}

	public void setSkillAdvanceLevel(int level) {

	}

	public int getSkillAdvanceLevel() {
		return 0;
	}

	public byte getOpenRealm() {
		return 0;
	}

	public void setSkillQuality(byte b) {

	}

	public short getOpenLv() {
		return 0;
	}
}
