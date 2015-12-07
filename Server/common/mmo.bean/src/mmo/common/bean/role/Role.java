package mmo.common.bean.role;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import mmo.common.activity.bean.RoleGmActivity;
import mmo.common.bean.buf.IBuf;
import mmo.common.bean.email.IEmail;
import mmo.common.bean.extension.EffectFunction;
import mmo.common.bean.extension.ProgressState;
import mmo.common.bean.goods.AGoods;
import mmo.common.bean.mission.extension.Award;
import mmo.common.bean.option.GameOption;
import mmo.common.bean.pet.PetArrayInfo;
import mmo.common.bean.role.store.StoreHouse;
import mmo.common.bean.scene.GameScene;
import mmo.common.bean.scene.ILayer;
import mmo.common.bean.scene.ISceneGrid;
import mmo.common.bean.scene.PassDupllicateData;
import mmo.common.bean.sect.IGameSect;
import mmo.common.bean.skill.ASkill;
import mmo.common.bean.skill.SkillManager;
import mmo.common.bean.skill.cool.CommonCool;
import mmo.common.bean.skill.cool.CommonCoolManager;
import mmo.common.config.ClientCustom;
import mmo.common.config.SkillConfig;
import mmo.common.config.SpeedConfig;
import mmo.common.config.StringConstant;
import mmo.common.config.goods.GoodsConfig;
import mmo.common.config.role.RoleGameState;
import mmo.common.config.role.RoleRealmConfig;
import mmo.common.config.skill.SkillCate;
import mmo.common.config.version.VersionConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;
import mmo.common.protocol.game.UserProtocol;
import mmo.common.protocol.ui.ClientUI;
import mmo.common.protocol.ui.main.Main_900_Pet;
import mmo.common.system.GameSystem;
import mmo.expression.IExpressionData;
import mmo.tools.log.LoggerError;
import mmo.tools.net.extension.session.NetRole;
import mmo.tools.thread.runnable.ILogicRunnable;
import mmo.tools.util.DateUtil;
import mmo.tools.util.MathUtil;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class Role extends NetRole implements Cloneable, IRole, IRole.Battle, IGameEvent, IExpressionData {
	protected static final int               BROADCAST_OFFSET      = 8 * 1000;
	protected final static AtomicLong        OBJECT_ID_GENERATOR   = new AtomicLong(1);
	protected static final String            ERR_RELEASE_RES       = "ReleaseResource-Role";
	protected static final Object            COMMON_VALUE          = new Object();
	protected static final List<Role>        NULL_ROLE_LIST        = new ArrayList<Role>();
	protected static final List<IBuf>        NULL_BUF              = new ArrayList<IBuf>();
	protected static final Map<Long, Object> NULL_REQUEST          = new HashMap<Long, Object>();
	protected static final short[][]         NULL_PHY              = new short[0][0];
	protected static final List<AGoods>      NULL_GOODS            = new ArrayList<AGoods>();
	public static final int                  MAX_SKILL             = 5;
	/** 真实ID */
	protected int                            realId;
	/** 已穿戴的装备<装备类别固定的格子序号， 装备绑定ID> */
	protected Map<Integer, Integer>          equipedGoods          = null;
	/** 客户端包类型 */
	protected byte                           clientModel           = 0;
	/** 对象ID */
	private long                             objectId;

	/***************************************************************************************/
	/*** 成长相关 ***/
	/***************************************************************************************/
	/** 最大等级 */
	private short                            maxLevel              = 0;
	/** 经验 **/
	protected int                            experience            = 0;
	/** 级别 **/
	protected short                          level                 = 0;
	/** 境界 **/
	protected byte                           realm                 = 0;
	/** 攻击目标 **/
	protected Role                           hitTarget             = null;
	/** 阵营标识 **/
	protected int                            camp                  = 0;
	/** 角色当前战斗力 */
	protected int                            fighting              = 0;
	/** 角色最高战斗力 */
	protected int                            maxFighting           = 0;

	/** 升级时的日期(毫秒数) */
	protected long                           levelupFristTime      = 0;
	/** 体验卡VIP等级 */
	protected short                          virtualVIPLevel       = 0;
	/** 体验卡VIP等级 使用有效期 */
	protected long                           virtualVIPexpiredTime = 0;
	/** 星级 */
	protected byte                           roleStar              = 0;
	/** 技能点 */
	protected int                            skillPoint            = 1;
	/** 累积获得的技能点总数量 */
	protected int                            totalSkillPoint       = 1;
	/***************************************************************************************/
	/***************** 穿戴装备 ***/
	/***************************************************************************************/
	/** 1274 宠物携带的法宝ID 武器 */
	protected int                            equipFabao;
	/** 1275 宠物携带的装备1 武器 */
	protected AGoods                         equipWeapon;
	/** 1276 宠物携带的装备2 衣服 */
	protected AGoods                         equipClothes;
	/** 1277 宠物携带的装备3 头盔 */
	protected AGoods                         equipHelmet;
	/** 1278 宠物携带的装备4 鞋子 */
	protected AGoods                         equipShoe;

	/***************************************************************************************/
	/***************** 技能相关 ***/
	/***************************************************************************************/
	protected Map<Long, Object>              requestSectList       = null;
	/** 角色拥有的技能 **/
	protected Map<Integer, ASkill>           roleSkill             = new HashMap<Integer, ASkill>();
	protected ASkill[]                       currSkill             = new ASkill[MAX_SKILL];
	/** 未开启的技能 */
	protected List<Integer>                  unOpenSkills          = new ArrayList<Integer>();
	protected Map<Integer, CommonCool>       skillCools            = new HashMap<Integer, CommonCool>();
	protected Map<Integer, CommonCool>       goodsCools            = new HashMap<Integer, CommonCool>();

	/***************************************************************************************/
	/*** 对象构建 ***/
	/***************************************************************************************/
	/** 构建者 */
	protected byte                           builder               = 0;
	/** 宝宝的创建者 */
	private int                              parent                = 0;
	/** 寿命（该值为0时，灵兽被收回，宝宝则被销毁） */
	protected int                            lifetime              = Integer.MAX_VALUE;
	/** 描述 **/
	protected String                         note                  = null;
	/** 动画配置文件 **/
	protected String                         animation             = null;
	/** 数据状态 **/
	protected byte                           dbState               = 0;
	/** 复活时间 **/
	protected long                           reliveTime            = -1;
	/** ms **/
	protected int                            reliveOffsetTime      = Integer.MAX_VALUE;
	/** 战斗状态消失时间 **/
	protected long                           battleEndTime         = 0;
	/** 游戏状态:攻击，防御，魔化，变身,战斗---影响技能的使用 **/
	protected int                            behavior              = 0;
	/** 对五行属性的免疫状态 */
	protected short                          immuneElement         = 0;
	/** BUF与免疫值的映射 */
	protected Map<Integer, Short>            bufToImmune           = new HashMap<Integer, Short>();
	/** 动作状态 */
	protected short                          actionState           = 0;
	/** 是否成功登陆 **/
	protected byte                           loginState            = RoleGameState.BEFORE_LOGIN;
	/** 战斗模式 */
	protected byte                           battleModel           = 0;
	/** 角色杀戮值 */
	protected int                            killingValue          = 0;
	/** 物品的主类别 */
	protected short                          goodsMain             = ClientUI.GOODS.ID;
	/** 是否被GM强制踢下线 */
	protected boolean                        isGmKickout           = true;
	/** 是否已经被释放 */
	protected boolean                        isReleased            = false;

	/***************************************************************************************/
	/*** 位置相关 ***/
	/***************************************************************************************/
	/*** 游戏分区编号，该编号在同一个服务器中对地图分区时才会用到 **/
	protected int                            zoneId                = 0;
	/** 分布的图层 **/
	protected int                            layer                 = 0;
	/** 地图编号 **/
	protected int                            mapId                 = 0;
	/** 怪物当前所在地图X方向地砖坐标 **/
	protected short                          tileX                 = 0;
	/** 怪物当前所在地图Y方向地砖坐标 **/
	protected short                          tileY                 = 0;
	/** 像素坐标-X */
	protected int                            pixelX                = 0;
	/** 像素坐标-Y */
	protected int                            pixelY                = 0;
	/** 图层区坐标-X */
	protected int                            gridx                 = 0;
	/** 图层区坐标-Y */
	protected int                            gridy                 = 0;

	/***************************************************************************************/
	/***************** 社交相关 ***/
	/***************************************************************************************/
	/** 队伍ID */
	protected int                            teamId                = 0;
	/** 团编号 */
	private short                            groupId               = 0;
	/** 宗门ID */
	protected long                           sectId                = 0;
	// /** 建立宗门次数 */
	// protected int sectCreateCount = 0;

	/***************************************************************************************/
	/***************** 数据校验 ***/
	/***************************************************************************************/
	/** 刷新时间 **/
	protected long                           updateTime            = System.currentTimeMillis();
	/** 下一次刷新时间 */
	protected long                           nextUpdatetime        = 0;
	/** hp发生改变 */
	protected boolean                        dirtyHp               = false;
	/** 广播时间 */
	protected long                           broadcastTime         = 0;
	/** 刷新间隔 **/
	protected int                            updateOffset          = 300;
	/** 下次刷新杀戮值时间 */
	protected long                           updateKillingValue    = System.currentTimeMillis() + DateUtil.ONE_HOUR_M;
	/** 宗门签到年 */
	protected int                            sectSignYearValue     = 0;
	/** 宗门签到天（一年内） */
	protected int                            sectSignDayOfYear     = 0;

	/***************************************************************************************/
	/***************** 视野相关 ***/
	/***************************************************************************************/
	/** 视野内的玩家 */
	protected List<Role>                     visiableRoles         = null;
	/** 视野内角色的映射表 */
	protected Map<Integer, Role>             visiableRoleMap       = null;
	/** 视野内容纳人数的上限值 */
	protected short                          visibility            = SpeedConfig.visibilityDefault;
	/** 可视距离 */
	protected int                            visiableDistance      = SpeedConfig.visibleDistance;
	/** 能够看到角色的怪物列表 */
	protected List<Role>                     visibleMonsters       = null;
	/** 屏蔽其他玩家-true只显示同一个队伍的玩家，false显示视野内的玩家 */
	protected boolean                        isHideOther;
	/** 已经进入场景 */
	protected boolean                        enteredScene;
	/** 表达式需要的参数 */
	private int                              expressParameter;
	/** 表达式需要的参数2 */
	private int                              expressParameter2;
	/***************************************************************************************/
	/***************** 属性成长 ***/
	/***************************************************************************************/
	/** 当前MP */
	protected int                            mp;
	/** 角色等级相关的基础MP */
	protected int                            baseMp;
	/** 装备加成的MP */
	protected int                            equipMp;
	/** 当前生命值 **/
	protected int                            hp                    = 0;
	/** 基础生命值 **/
	protected int                            hpBase                = 0;
	/** 装备对角色HP上限的增幅值 */
	protected int                            hpEquip               = 0;
	/** 被动技能对生命的增幅值 */
	protected int                            hpSkillPro            = 0;
	/** 灵兽(天赋)对生命的增幅 */
	protected int                            hpPet                 = 0;
	/** 境界穴位对生命的增幅 */
	protected int                            hpRealm               = 0;
	/** 丹药对生命的增幅 */
	protected int                            hpMedicine            = 0;
	/** 最大生命值 **/
	protected int                            hpSumMax              = 0;

	/** 生命回复 **/
	protected int                            hpChange              = 0;
	/** 装备对生命恢复的增幅值 */
	protected int                            hpChangeEquip         = 0;
	/** 生命恢复的综合值 */
	protected int                            sumHpChange           = 0;

	/** 基础攻击 */
	protected int                            baseAttack            = 0;
	/** 装备对攻击的影响值 */
	protected int                            attackEquip           = 0;
	/** 被动技能对攻击的增幅值 */
	protected int                            attackSkillPro        = 0;
	/** 灵兽(天赋)对攻击的增幅 */
	protected int                            attackPet             = 0;
	/** 境界穴位对攻击的增幅 */
	protected int                            attackRealm           = 0;
	/** 丹药对攻击的增幅 */
	protected int                            attackMedicine        = 0;
	/** 综合攻击 */
	protected int                            sumAttack             = 0;

	/** 基础防御 */
	protected int                            baseDefence           = 0;
	/** 装备对角色防御综合值的增幅值 */
	protected int                            defenceEquip          = 0;
	/** 被动技能对防御的增幅值 */
	protected int                            defenceSkillPro       = 0;
	/** 灵兽(天赋)对防御的增幅 */
	protected int                            defencePet            = 0;
	/** 境界穴位对防御的增幅 */
	protected int                            defenceRealm          = 0;
	/** 丹药对防御的增幅 */
	protected int                            defenceMedicine       = 0;
	/** 综合防御 **/
	protected int                            sumDefence            = 0;

	/***************************************************************************************/
	/***************** 角色培养 ***/
	/***************************************************************************************/
	/** 体术 **/
	protected int                            baseForce             = 0;
	/** 法术 **/
	protected int                            baseMagic             = 0;
	/** 神识 **/
	protected int                            baseSprit             = 0;
	/** 装备对体术加成值 **/
	protected int                            forceEquip            = 0;
	/** 装备对法术加成值 **/
	protected int                            magicEquip            = 0;
	/** 装备对神识加成 值 **/
	protected int                            spritEquip            = 0;
	/** 培养对体术加成值 */
	protected int                            forceTrain            = 0;
	/** 培养对法术加成值 **/
	protected int                            magicTrain            = 0;
	/** 培养对神识加成 值 **/
	protected int                            spritTrain            = 0;
	/** 体术 综合值 **/
	protected int                            sumForce              = 0;
	/** 法术综合值 **/
	protected int                            sumMagic              = 0;
	/** 神识综合值 **/
	protected int                            sumSprit              = 0;
	/** 体术上限值 **/
	protected int                            supForce              = 0;
	/** 法术上限值 **/
	protected int                            supMagic              = 0;
	/** 神识上限值 **/
	protected int                            supSprit              = 0;

	/***************************************************************************************/
	/***************** 战斗属性 ***/
	/***************************************************************************************/
	/** 基础命中 **/
	protected int                            attackChance          = 0;
	/** 综合命中 **/
	protected int                            sumAttackChance       = 0;
	/** 装备对角色命中的增幅值 */
	protected int                            attackChanceEquip     = 0;
	/** 灵兽(天赋)对角色命中的增幅值 */
	protected int                            attackChancePet       = 0;
	/** 境界对角色命中的增幅 */
	protected int                            attackChanceRealm     = 0;
	/** 穴位对角色命中的增幅 */
	protected int                            attackChanceMedicine  = 0;

	/** 基础闪避值(抵消命中值) **/
	protected int                            duckChance            = 0;
	/** 装备对角色闪避的增幅值 */
	protected int                            duckChanceEquip       = 0;
	/** 灵兽(天赋)对角色闪避的增幅值 */
	protected int                            duckChancePet         = 0;
	/** 境界对角色闪避的增幅 */
	protected int                            duckChanceRealm       = 0;
	/** 穴位对角色闪避的增幅 */
	protected int                            duckChanceMedicine    = 0;
	/** 综合闪避 **/
	protected int                            sumDuckChance         = 0;

	/** 基础暴击 **/
	protected int                            cruel                 = 0;
	/** 综合暴击 **/
	protected int                            sumCruel              = 0;
	/** 装备对角色暴击的增幅值 */
	protected short                          cruelEquip            = 0;
	/** 灵兽(天赋)对角色暴击的增幅值 */
	protected short                          cruelPet              = 0;
	/** 境界对角色闪避的增幅 */
	protected int                            cruelRealm            = 0;
	/** 穴位对角色闪避的增幅 */
	protected int                            cruelMedicine         = 0;

	/** 基础韧性(抵消暴击值) */
	protected int                            tenacity              = 0;
	/** 装备对角色的韧性增幅值 */
	protected int                            tenacityEquip         = 0;
	/** 灵兽(天赋)对角色的韧性增幅值 */
	protected int                            tenacityPet           = 0;
	/** 境界对角色韧性的增幅 */
	protected int                            tenacityRealm         = 0;
	/** 穴位对角色韧性的增幅 */
	protected int                            tenacityMedicine      = 0;
	/** 综合韧性 */
	protected int                            sumTenacity           = 0;

	/** 基础格挡(抵消破甲值) */
	protected int                            fender                = 0;
	/** 装备对角色格挡增幅值 */
	protected int                            fenderEquip           = 0;
	/** 灵兽(天赋)对角色格挡增幅值 */
	protected int                            fenderPet             = 0;
	/** 境界对角色格挡的增幅 */
	protected int                            fenderRealm           = 0;
	/** 穴位对角色格挡的增幅 */
	protected int                            fenderMedicine        = 0;
	/** 综合格挡 */
	protected int                            sumFender             = 0;

	/** 基础破甲 */
	protected int                            destroy               = 0;
	/** 装备对破甲的增幅值 */
	protected int                            destroyEquip          = 0;
	/** 灵兽(天赋)对破甲的增幅值 */
	protected int                            destroyPet            = 0;
	/** 境界对角色破甲的增幅 */
	protected int                            destroyRealm          = 0;
	/** 穴位对角色破甲的增幅 */
	protected int                            destroyMedicine       = 0;
	/** 综合破甲 */
	protected int                            sumDestroy            = 0;

	/** 基础吸血 */
	protected int                            suckHp                = 0;
	/** 装备(天赋)对吸血的增幅值 */
	protected int                            suckHpEquip           = 0;
	/** 穴位对角色吸血的增幅 */
	protected int                            suckHpMedicine        = 0;
	/** 吸血综合值(即固定吸血值) */
	protected int                            sumSuckHp             = 0;

	/** 基础反弹值 */
	protected int                            rebound               = 0;
	/** 装备对反弹的增幅值 */
	protected int                            reboundEquip          = 0;
	/** 穴位对角色反弹的增幅 */
	protected int                            reboundMedicine       = 0;
	/** 综合反弹(即固定反弹伤害值) */
	protected int                            sumRebound            = 0;
	/** 反弹伤害百分比(有反弹BUF时有该值) */
	protected int                            reboundPercent        = 0;

	/** 基础破防 */
	protected int                            reduceDefence         = 0;
	/** 装备对破防的增幅 */
	protected int                            reduceDefenceEquip    = 0;
	/** 境界对角色破防的增幅 */
	protected int                            reduceDefenceRealm    = 0;
	/** 穴位对角色破防的增幅 */
	protected int                            reduceDefenceMedicine = 0;
	/** 综合破防 */
	protected int                            sumReduceDefence      = 0;

	/** 基础抵抗(抵消破防值) */
	protected int                            resist                = 0;
	/** 装备对抵抗的增幅值 */
	protected int                            resistEquip           = 0;
	/** 境界对角色抵抗的增幅 */
	protected int                            resistRealm           = 0;
	/** 穴位对角色抵抗的增幅 */
	protected int                            resistMedicine        = 0;
	/** 综合抵抗 */
	protected int                            sumResist             = 0;

	/** 移动的像素速度 **/
	protected short                          moveSpeed             = 0;
	/** 移动速度 **/
	protected short                          sumMoveSpeed          = 0;

	// /** 是否为机器人 */
	// protected boolean isRobot;
	/** 缘分影响集合 */
	protected List<EffectFunction>           luckEffects           = null;
	/** 激活的套装集合<套装ID，装备真实ID列表> */
	protected Map<Integer, List<Integer>>    activatePertains      = null;
	/** 进阶等级，最高级5 */
	protected byte                           roleAdvanceLv         = 0;

	/***************** 20140920新版数据 ******************************************************************/
	/** 已渡劫的次数 */
	protected byte                           elevatedCount         = 0;
	/** 当前的渡劫ID */
	protected int                            curElevateId          = 0;
	/** 当前渡劫穴位的状态<穴位ID,状态 0未激活1可激活2已激活> */
	protected Map<Integer, Byte>             acupointState         = new HashMap<Integer, Byte>();
	/** 已激活的穴位 */
	protected Set<Integer>                   acupointedList        = new HashSet<Integer>();
	/** 已渡劫的ID */
	protected Set<Integer>                   elevatedList          = new HashSet<Integer>();
	/** 当前的灵根 */
	protected byte                           curLingGen            = 0;
	/** 已开启的灵根 */
	protected Set<Byte>                      openLingGenList       = new HashSet<Byte>();
	/** 是否开启所有当前穴位 */
	protected boolean                        isOpenAllAcupoint     = false;
	/** 已学习的技能等级总和 */
	protected int                            skillLvSum            = 0;
	/** 已激活的穴位需求等级总和 */
	protected int                            acupointLvSum         = 0;
	/** 激活的缘分 */
	protected List<Integer>                  activityLucks         = new ArrayList<Integer>();

	/***************************************************************************************/
	/***************** 保留属性 ***/
	/***************************************************************************************/

	/***************************************************************************************/
	/***************** 属性结束 ***/
	/***************************************************************************************/

	/**
	 * 释放资源
	 */
	public void releaseResource() {
		setReleased(true);
		try {
			if (requestSectList != null) {
				requestSectList.clear();
				requestSectList = null;
			}
			if (roleSkill != null) {
				roleSkill.clear();
				roleSkill = null;
			}
			currSkill = null;

			if (skillCools != null) {
				skillCools.clear();
				skillCools = null;
			}
			if (goodsCools != null) {
				goodsCools.clear();
				goodsCools = null;
			}
			if (visiableRoles != null) {
				visiableRoles.clear();
				visiableRoles = null;
			}
			if (visiableRoleMap != null) {
				visiableRoleMap.clear();
				visiableRoleMap = null;
			}
			if (visibleMonsters != null) {
				visibleMonsters.clear();
				visibleMonsters = null;
			}
			if (bufToImmune != null) {
				bufToImmune.clear();
				bufToImmune = null;
			}
			if (acupointState != null) {
				acupointState.clear();
				acupointState = null;
			}
			if (acupointedList != null) {
				acupointedList.clear();
				acupointedList = null;
			}
			if (elevatedList != null) {
				elevatedList.clear();
				elevatedList = null;
			}
			if (openLingGenList != null) {
				openLingGenList.clear();
				openLingGenList = null;
			}
			if (equipedGoods != null) {
				equipedGoods.clear();
				equipedGoods = null;
			}
			if (luckEffects != null) {
				luckEffects.clear();
				luckEffects = null;
			}
			if (activatePertains != null) {
				for (List<Integer> temp : activatePertains.values()) {
					temp.clear();
					temp = null;
				}
				activatePertains.clear();
				activatePertains = null;
			}
		} catch (Exception e) {
			LoggerError.error(ERR_RELEASE_RES, e);
		} finally {
			super.releaseResource();
		}
	}

	public int getMp() {
		return mp;
	}

	public void setMp(int mp) {
		this.mp = mp;
	}

	public int getBaseMp() {
		return baseMp;
	}

	public void setBaseMp(int baseMp) {
		this.baseMp = baseMp;
	}

	public int getEquipMp() {
		return equipMp;
	}

	public void setEquipMp(int equipMp) {
		this.equipMp = equipMp;
	}

	/**
	 * 保存除装备(包括法宝、穴位)外对该类属性的加成值
	 */
	protected void saveUnEquipPro() {
		this.hpSumMax -= this.hpEquip;
		this.sumAttack -= this.attackEquip;
		this.sumDefence -= this.defenceEquip;
		this.sumAttackChance -= this.attackChanceEquip;
		this.sumDuckChance -= this.duckChanceEquip;
		this.sumCruel -= this.cruelEquip;
		this.sumTenacity -= this.tenacityEquip;
		this.sumFender -= this.fenderEquip;
		this.sumDestroy -= this.destroyEquip;
		this.sumSuckHp -= this.suckHpEquip;
		this.sumRebound -= this.reboundEquip;
		this.sumReduceDefence -= this.reduceDefenceEquip;
		this.sumResist -= this.resistEquip;
		this.sumForce -= this.forceEquip;
		this.sumMagic -= this.magicEquip;
		this.sumSprit -= this.spritEquip;
	}

	/**
	 * 保存除灵兽对属性影响外该类属性的加成值
	 */
	public void saveUnPetPro() {
		// this.hpSumMax -= this.hpPet;
		this.sumAttack -= this.attackPet;
		this.sumDefence -= this.defencePet;
		this.sumAttackChance -= this.attackChancePet;
		this.sumDuckChance -= this.duckChancePet;
		this.sumCruel -= this.cruelPet;
		this.sumTenacity -= this.tenacityPet;
		this.sumFender -= this.fenderPet;
		this.sumDestroy -= this.destroyPet;
	}

	/**
	 * 保存除境界穴位对属性的影响外属性的值
	 */
	protected void saveUnRealmPro() {
		this.hpSumMax -= this.hpRealm;
		this.sumAttack -= this.attackRealm;
		this.sumDefence -= this.defenceRealm;
		this.sumAttackChance -= this.attackChanceRealm;
		this.sumDuckChance -= this.duckChanceRealm;
		this.sumCruel -= this.cruelRealm;
		this.sumTenacity -= this.tenacityRealm;
		this.sumFender -= this.fenderRealm;
		this.sumDestroy -= this.destroyRealm;
		this.sumReduceDefence -= this.reduceDefenceRealm;
		this.sumResist -= this.resistRealm;
	}

	/**
	 * 保存除丹药对属性的影响外属性的值
	 */
	protected void saveUnMedicinePro() {
		this.hpSumMax -= this.hpMedicine;
		this.sumAttack -= this.attackMedicine;
		this.sumDefence -= this.defenceMedicine;
		this.sumAttackChance -= this.attackChanceMedicine;
		this.sumDuckChance -= this.duckChanceMedicine;
		this.sumCruel -= this.cruelMedicine;
		this.sumTenacity -= this.tenacityMedicine;
		this.sumFender -= this.fenderMedicine;
		this.sumDestroy -= this.destroyMedicine;
		this.sumReduceDefence -= this.reduceDefenceMedicine;
		this.sumResist -= this.resistMedicine;
	}

	/**
	 * 清除角色基础属性
	 */
	public void clearBasePro() {
		setHpBase(0);
		setBaseAttack(0);
		setBaseDefence(0);
		setAttackChance(0);
		setDuckChance(0);
		setCruel(0);
		setTenacity(0);
		setFender(0);
		setDestroy(0);
		setSuckHp(0);
		setRebound(0);
		setReduceDefence(0);
		setResist(0);
	}

	/**
	 * 清除灵兽对角色属性的增幅值
	 */
	public void clearPetToRolePro() {
		this.attackPet = 0;
		this.defencePet = 0;
		this.attackChancePet = 0;
		this.duckChancePet = 0;
		this.cruelPet = 0;
		this.tenacityPet = 0;
		this.fenderPet = 0;
		this.destroyPet = 0;
	}

	/**
	 * 清除境界穴位对角色属性的增幅值
	 */
	protected void clearRealmToPro() {
		this.hpRealm = 0;
		this.attackRealm = 0;
		this.defenceRealm = 0;
		this.attackChanceRealm = 0;
		this.duckChanceRealm = 0;
		this.cruelRealm = 0;
		this.tenacityRealm = 0;
		this.fenderRealm = 0;
		this.destroyRealm = 0;
		this.reduceDefenceRealm = 0;
		this.resistRealm = 0;
	}

	/**
	 * 清除丹药对角色属性的增幅值
	 */
	protected void clearMedicineToPro() {
		this.hpMedicine = 0;
		this.attackMedicine = 0;
		this.defenceMedicine = 0;
		this.attackChanceMedicine = 0;
		this.duckChanceMedicine = 0;
		this.cruelMedicine = 0;
		this.tenacityMedicine = 0;
		this.fenderMedicine = 0;
		this.destroyMedicine = 0;
		this.reduceDefenceMedicine = 0;
		this.resistMedicine = 0;
	}

	/**
	 * 清除装备对属性的增幅
	 */
	protected void clearEquipPro() {
		this.forceEquip = 0;
		this.magicEquip = 0;
		this.spritEquip = 0;
		this.hpEquip = 0;
		this.equipMp = 0;
		this.attackEquip = 0;
		this.defenceEquip = 0;
		this.attackChanceEquip = 0;
		this.duckChanceEquip = 0;
		this.cruelEquip = 0;
		this.tenacityEquip = 0;
		this.fenderEquip = 0;
		this.destroyEquip = 0;
		this.suckHpEquip = 0;
		this.suckHpMedicine = 0;
		this.reboundEquip = 0;
		this.reboundMedicine = 0;
		this.reduceDefenceEquip = 0;
		this.resistEquip = 0;
	}

	public boolean isReleased() {
		return isReleased;
	}

	public void setReleased(boolean isReleases) {
		this.isReleased = isReleases;
	}

	public final void addSkillCommonCool(CommonCool cool) {
		if (cool == null) {
			return;
		}
		CommonCool myCool = cool.clone();
		myCool.startCool();
		skillCools.put(cool.getId(), myCool);
	}

	public boolean isEnteredScene() {
		return enteredScene;
	}

	public void toEnterScene() {
		enteredScene = true;
	}

	public void toExitScene() {
		enteredScene = false;
	}

	public final void addGoodsCommonCool(CommonCool cool) {
		if (cool == null) {
			return;
		}
		CommonCool myCool = cool.clone();
		myCool.startCool();
		goodsCools.put(cool.getId(), myCool);
	}

	/**
	 * 是否被GM强制被踢下线
	 * 
	 * @return true强制踢下线，false正常离线
	 */
	public boolean isGmKickout() {
		return isGmKickout;
	}

	/**
	 * 是否被GM强制被踢下线
	 * 
	 * @return true强制踢下线，false正常离线
	 */
	public void setGmKickout(boolean isGmKickout) {
		this.isGmKickout = isGmKickout;
	}

	/**
	 * 是否是被召唤的
	 * 
	 * @return true是被召唤，false不是被召唤的
	 */
	public boolean isCallUp() {
		return false;
	}

	/**
	 * 设置是否被召唤的
	 * 
	 * @param isCallUp
	 *            true是被召唤，false不是被召唤的
	 */
	public void setCallUp(boolean isCallUp) {
	}

	public int getVisiableDistance() {
		return visiableDistance;
	}

	public void setVisiableDistance(int visiableDistance) {
		this.visiableDistance = visiableDistance;
	}

	public void requestJoinSect(long sectId) {
		if (requestSectList == null) {
			requestSectList = new HashMap<Long, Object>();
		}
		requestSectList.put(sectId, COMMON_VALUE);
	}

	/**
	 * 获取真实ID
	 * 
	 * @return
	 */
	public int getRealId() {
		return realId;
	}

	public void setRealId(int referenceID) {
		this.realId = referenceID;
	}

	public boolean pushDeath() {
		return false;
	}

	/**
	 * 通过技能ID获得技能的公共冷去对象，没有公共冷却返回NULL
	 * 
	 * @param skillId
	 * @return 公共冷去对象，没有公共冷却返回NULL
	 */
	public final CommonCool getSkillCool(int skillId) {
		return skillCools.get(CommonCoolManager.getSkillCoolId(skillId));
	}

	// /**
	// * 添加一个新的物品技能冷却
	// *
	// * @param gsc
	// * 冷却对象
	// */
	// public void addGoodsSkillCool(GoodsSkillCool gsc) {
	// if (gsc != null) {
	// goodsSkillCools.put(gsc.getSkillId(), gsc);
	// gsc.startCool();
	// }
	// }

	public void responseConnect(int connectId) {
		if (connectId < 0) {
			return;
		}
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_8004_CONNECT_ID);
		buf.setNetConfirm(connectId);
		sendData(buf);
	}

	// /**
	// * 通过技能编号获取物品技能冷却对象
	// *
	// * @param skillId
	// * 技能编号
	// * @return 冷却对象
	// */
	// public GoodsSkillCool getGoodsSkillCool(int skillId) {
	// return goodsSkillCools.get(skillId);
	// }

	public ILayer getDuplicateLayer() {
		return null;
	}

	// /**
	// * 通过技能编号移除物品技能冷却对象
	// *
	// * @param skillId
	// * 技能编号
	// */
	// public void removeGoodsSkillCool(int skillId) {
	// goodsSkillCools.remove(skillId);
	// }

	/**
	 * 通过物品ID获得物品的公共冷去对象，没有公共冷却返回NULL
	 * 
	 * @param goodsId
	 * @return 公共冷去对象，没有公共冷却返回NULL
	 */
	public final CommonCool getGoodsCool(int goodsId) {
		return goodsCools.get(CommonCoolManager.getGoodsCoolId(goodsId));
	}

	/**
	 * 获取战斗模式
	 * 
	 * @return 战斗模式
	 */
	public byte getBattleModel() {
		return battleModel;
	}

	/**
	 * 设置战斗模式
	 * 
	 * @param battleModel
	 *            战斗模式
	 */
	public void setBattleModel(byte battleModel) {
		this.battleModel = battleModel;
	}

	/**
	 * 切换战斗模式
	 * 
	 * @param model
	 *            战斗模式
	 * @param connectId
	 *            连接ID
	 * @param isBattle
	 *            是否战斗场景
	 * @param isSystem
	 *            是否系统强制切换
	 */
	public void changeBattleModel(byte model, int connectId, boolean isBattle, boolean isSystem) {

	}

	/**
	 * 消息频道
	 * 
	 * @param channel
	 *            频道
	 * @param message
	 *            消息内容
	 */
	public void channelMessage(byte channel, String message) {

	}

	public boolean isRequestSect(long sectId) {
		if (requestSectList == null) {
			return false;
		}
		return requestSectList.get(sectId) != null;
	}

	/**
	 * 技能学习-装备引导
	 * 
	 * @param skillId
	 *            技能编号
	 */
	public void leadSkill(int skillId) {

	}

	/**
	 * 获取像素坐标-X
	 * 
	 * @return 像素坐标-X
	 */
	public int getPixelX() {
		return pixelX;
	}

	/**
	 * 是否在和平场景:false在和平场景，true在战斗场景
	 */
	public boolean isSceneBattle() {
		return true;
	}

	/**
	 * 是否为玩家
	 * 
	 * @return true为玩家，false不是玩家
	 */
	public boolean isUserRole() {
		return false;
	}

	/**
	 * 设置副本序列号
	 * 
	 * @param duplicateSerial
	 *            副本序列号
	 */
	public void setDuplicateSerial(long duplicateSerial) {
	}

	/**
	 * 返回当前绑定副本的序列号
	 * 
	 * @return 副本的序列号
	 */
	public long getDuplicateSerial() {
		return 0;
	}

	/**
	 * 是否为宝宝
	 * 
	 * @return true宝宝，false不是宝宝
	 */
	public boolean isPet() {
		return false;
	}

	/**
	 * 是否为怪物
	 * 
	 * @return true为怪物，false不是怪物
	 */
	public boolean isMonster() {
		return false;
	}

	/***
	 * 是否为NPC
	 * 
	 * @return true为NPC，false不是NPC
	 */
	public boolean isNpc() {
		return false;
	}

	public byte getIconVersion() {
		return 0;
	}

	/**
	 * 获取像素坐标-Y
	 * 
	 * @return 像素坐标-Y
	 */
	public int getPixelY() {
		return pixelY;
	}

	/**
	 * 获取背包剩余的空格子数
	 * 
	 * @return 背包剩余的空格子数
	 */
	public int getFreeGridCount() {
		return 0;
	}

	/**
	 * 获取可见的敌方角色列表
	 * 
	 * @return 可见的敌方角色列表
	 */
	public List<Role> getVisiableEnemyRole() {
		return NULL_ROLE_LIST;
	}

	/**
	 * 获取可见的敌方角色列表
	 * 
	 * @return 可见的敌方角色列表
	 */
	public List<Role> getVisiableFriendRole() {
		return NULL_ROLE_LIST;
	}

	/***
	 * 获取队伍成员列表
	 * 
	 * @return 队伍成员列表
	 */
	public List<Role> getTeamMembers() {
		return NULL_ROLE_LIST;
	}

	/**
	 * 获取可以看得到角色的怪物列表
	 * 
	 * @return 可以看得到角色的怪物列表
	 */
	public List<Role> getVisibleMonsters() {
		List<Role> tempList = visibleMonsters;
		if (tempList != null) {
			return tempList;
		}
		return NULL_ROLE_LIST;
	}

	/**
	 * 根据标识检索指定范围内的角色（玩家和怪）
	 * 
	 * @param centerX
	 *            中心坐标-X
	 * @param centerY
	 *            中心坐标-Y
	 * @param radiusX
	 *            检索半径-X
	 * @param radiusY
	 *            检索半径-Y
	 * @param flag
	 *            检索标识（0:所有角色，1：己方单位，2，敌方单位）
	 * @return
	 */
	public List<Role> searchNearRoles(int centerX, int centerY, int radiusX, int radiusY, int flag) {
		return NULL_ROLE_LIST;
	}

	/**
	 * 搜索以角色为中心指定方向扇形范围内的目标
	 * 
	 * @param dir
	 *            指定方向
	 * @param radius
	 *            范围半径
	 * @param angle
	 *            角度
	 * @param flag
	 *            标识
	 * @return
	 */
	public List<Role> searchSectorRole(short dir, int radius, int angle, byte flag) {
		return NULL_ROLE_LIST;
	}

	/**
	 * 判断角色是否在指定的椭圆区域外
	 * 
	 * @param centerX
	 *            中心坐标-X
	 * @param centerY
	 *            中心坐标-Y
	 * @param radiusX
	 *            半径-X
	 * @param radiusY
	 *            半径-Y
	 * @return true在椭圆外，false在椭圆内
	 */
	public boolean isOutArea(int centerX, int centerY, int radiusX, int radiusY) {
		return isDeath() || MathUtil.point2ellips(getPixelX(), getPixelY(), centerX, centerY, radiusX, radiusY) > 1;
	}

	/**
	 * 获取斜率
	 * 
	 * @param angle
	 *            角度
	 * @return
	 */
	public double getSlope(int angle) {
		double radian = (3.1415926535 / 180) * angle;
		return Math.sin(radian);
	}

	/**
	 * 判断角色是否在指定的扇形内
	 * 
	 * @param dir
	 *            方向
	 * @param angle
	 *            角度
	 * @return
	 */
	public boolean isOutSector(short dirAngle, int angle) {
		dirAngle += 22;
		int dir = dirAngle / 45;
		if (dirAngle > 360) {
			dir = DirectConfig.DIRECT_RIGHT;
		}
		int pixeX = getPixelX();
		int pixeY = getPixelY();
		switch (dir) {
			case DirectConfig.DIRECT_UP: {
				// 上 -斜率
				double k = getSlope((180 - angle) / 2);
				if (pixeY >= 0 && pixeY >= -k * pixeX && pixeY <= k * pixeX) {
					return true;
				}
				break;
			}
			case DirectConfig.DIRECT_DOWN: {
				// 下-斜率
				double k = getSlope((180 - angle) / 2);
				if (pixeY <= 0 && pixeY >= k * pixeX && pixeY <= -k * pixeX) {
					return true;
				}
				break;
			}
			case DirectConfig.DIRECT_LEFT: {
				// 左-斜率
				double k = getSlope((180 - angle) / 2);
				if (pixeX >= 0 && pixeY >= -k * pixeX && pixeY <= k * pixeX) {
					return true;
				}
				break;
			}
			case DirectConfig.DIRECT_RIGHT: {
				// 右-斜率
				double k = getSlope((180 - angle) / 2);
				if (pixeX <= 0 && pixeY >= k * pixeX && pixeY <= -k * pixeX) {
					return true;
				}
				break;
			}
			case DirectConfig.DIRECT_LEFT_DOWN: {
				// 左下 - 斜率
				double ka = getSlope((180 - angle) / 2 - 45);
				double kb = getSlope((180 - angle) / 2 + 45);
				if (pixeY <= -pixeX && pixeY >= ka * pixeX && pixeY <= kb * pixeX) {
					return true;
				}
				break;
			}
			case DirectConfig.DIRECT_LEFT_UP: {
				// 左上 - 斜率
				double ka = getSlope((180 - angle) / 2 - 45);
				double kb = getSlope((180 - angle) / 2 + 45);
				if (pixeY >= pixeX && pixeY >= ka * pixeX && pixeY <= kb * pixeX) {
					return true;
				}
				break;
			}
			case DirectConfig.DIRECT_RIGHT_DOWN: {
				// 右下 - 斜率
				double ka = getSlope((180 - angle) / 2 - 45);
				double kb = getSlope((180 - angle) / 2 + 45);
				if (pixeY <= pixeX && pixeY >= kb * pixeX && pixeY <= ka * pixeX) {
					return true;
				}
				break;
			}
			case DirectConfig.DIRECT_RIGHT_UP: {
				// 右上- 斜率
				double ka = getSlope((180 - angle) / 2 - 45);
				double kb = getSlope((180 - angle) / 2 + 45);
				if (pixeY >= -pixeX && pixeY >= kb * pixeX && pixeY <= ka * pixeX) {
					return true;
				}
				break;
			}
		}
		return false;
	}

	/**
	 * 进入怪物的视野
	 * 
	 * @param monster
	 *            怪物
	 */
	public void addViewMonster(Role monster) {
		List<Role> tempList = visibleMonsters;
		Map<Integer, Role> tempMap = visiableRoleMap;
		if (tempList != null) {
			tempList.add(monster);
		}
		if (tempMap != null) {
			tempMap.put(monster.getId(), monster);
		}
	}

	/**
	 * 离开怪物的视野
	 * 
	 * @param monster
	 *            怪物
	 */
	public void outViewMonster(Role monster) {
		List<Role> tempList = visibleMonsters;
		Map<Integer, Role> tempMap = visiableRoleMap;
		if (tempList != null) {
			tempList.remove(monster);
		}
		if (tempMap != null) {
			tempMap.remove(monster.getId());
		}
	}

	/**
	 * 设置像素坐标-Y
	 * 
	 * @param pixelY
	 *            像素坐标-Y
	 */
	public void setPixelY(int pixelY) {
		this.pixelY = pixelY;
		tileY = (short) (pixelY / SpeedConfig.TILE_SIZE);
	}

	/**
	 * 设置像素坐标-X
	 * 
	 * @param pixelX
	 *            像素坐标-X
	 */
	public void setPixelX(int pixelX) {
		this.pixelX = pixelX;
		tileX = (short) (pixelX / SpeedConfig.TILE_SIZE);
	}

	/**
	 * 获取所属宗门
	 * 
	 * @return 所属宗门，没有加入宗门则返回null
	 */
	public IGameSect getGameSect() {
		return null;
	}

	/**
	 * 获取所在场景的名称
	 * 
	 * @return 所在场景的名称
	 */
	public String getSceneName() {
		return StringConstant.logUnknown;
	}

	/**
	 * 获取所属宗门的ID
	 * 
	 * @return 所属宗门的ID
	 */
	public long getSectId() {
		return sectId;
	}

	/**
	 * 设置所属宗门的ID
	 * 
	 * @param sectId
	 *            所属宗门的ID
	 */
	public void setSectId(long sectId) {
		this.sectId = sectId;
	}

	/**
	 * 修改境界点上限值
	 * 
	 * @param value
	 *            变化量
	 * @return 变化后的上限值
	 */
	public int changeRealmPointMax(int value) {
		return 0;
	}

	/**
	 * 提升境界
	 * 
	 * @param newRealm
	 *            新境界
	 * @return 是否提升成功：true提升成功，false提升失败
	 */
	public boolean changeRealm(int newRealm) {
		int offset = newRealm - realm;
		if (offset > 0) {
			this.realm = (byte) newRealm;
			return true;
		}
		return false;
	}

	/**
	 * 通过货币ID获取角色拥有的货币
	 * 
	 * @param id
	 *            货币ID
	 * @return 货币对象
	 */
	public AGoods getMoneyGoods(int id) {
		return null;
	}

	/**
	 * 通过道具的真实ID获取背包中的数量
	 * 
	 * @param goodsId
	 * @return 数量
	 */
	public int getGoodsCountByGoodsId(int goodsId) {
		return 0;
	}

	/**
	 * 以物品的真实ID来统计物品的数量
	 * 
	 * @param goodsId
	 *            物品的真实ID，获取方式为：GameGoods.getGoodsId();
	 * @return 总数量
	 */
	public int getGoodsCountByGoodsId(int goodsId, boolean isBinded) {
		return 0;
	}

	public int getGoodsCountByGoodsId(int goodsId, byte quality, byte addition) {
		/**
		 * @alter
		 * @name：xiaoqiong
		 * @date：13-5-21
		 * @note：增加byte类型参数-任务的辅助条件
		 */
		return 0;
	}

	public int getGoodsCountByGoodsIdAndBag(int goodsId, short belongBag) {
		return 0;
	}

	/**
	 * 判断角色背包内有没有指定的物品
	 * 
	 * @param goodsId
	 *            物品的真实ID
	 * @return true有，false没有
	 */
	public boolean hasGoods(int goodsId) {
		return false;
	}

	/**
	 * 场景格子发生改变
	 * 
	 * @return true改变，false未改变
	 */
	public final boolean isOutGrid() {
		return (tileX / SpeedConfig.GRID_WIDTH != gridx) || (tileY / SpeedConfig.GRID_HEIGHT != gridy);
	}

	/**
	 * 获取格子坐标-X
	 * 
	 * @return 格子坐标-X
	 */
	public int getGridx() {
		return gridx;
	}

	/**
	 * 设置格子坐标-X
	 * 
	 * @param gridx
	 *            格子坐标-X
	 */
	public void setGridx(int gridx) {
		this.gridx = gridx;
	}

	/**
	 * 获取格子坐标-Y
	 * 
	 * @return
	 */
	public int getGridy() {
		return gridy;
	}

	/**
	 * 设置格子坐标-Y
	 * 
	 * @param gridy
	 *            格子坐标-Y
	 */
	public void setGridy(int gridy) {
		this.gridy = gridy;
	}

	/**
	 * 重置角色的战斗属性
	 * 
	 * @param updateUI
	 */
	public void resetRoleFightProperty(boolean updateUI) {

	}

	/**
	 * 获取角色游戏状态
	 * 
	 * @return
	 */
	public byte getLoginState() {
		return loginState;
	}

	/**
	 * 设置角色
	 * 
	 * @param loginState
	 */
	public void setLoginState(byte loginState) {
		this.loginState = loginState;
	}

	public final boolean isLogining() {
		return loginState == RoleGameState.LOGINING;
	}

	/**
	 * 结束副本
	 */
	public void finishDuplicate() {

	}

	/**
	 * 设置怪物死亡时掉落绑灵的下限值
	 * 
	 * @param goldSub
	 */
	public void setGoldSub(int goldSub) {

	}

	/**
	 * 设置怪物死亡时掉落绑灵的上限值
	 * 
	 * @param goldSup
	 */
	public void setGoldSup(int goldSup) {

	}

	/**
	 * 重置角色属性
	 */
	public void resetRole() {
		this.vipLevel = 0;
		this.integral = 0;
		experience = 0;
		level = 0;
		realm = 0;
		hitTarget = null;
		camp = 0;
		sumMoveSpeed = 0;
		moveSpeed = 0;
		builder = 0;
		maxLevel = 0;
		parent = 0;
		sectId = 0;
		lifetime = Integer.MAX_VALUE;
		zoneId = 0; // 游戏分区编号，该编号在同一个服务器中对地图分区时才会用到
		layer = 0; // 分布的图层
		mapId = 0; // 地图编号
		tileX = 0; // 怪物当前所在地图X方向地砖坐标
		tileY = 0; // 怪物当前所在地图Y方向地砖坐标
		teamId = 0;
		groupId = 0;
		animation = null; // 动画配置文件
		dbState = 0; // 数据状态
		reliveTime = 0; // 复活时间
		reliveOffsetTime = Integer.MAX_VALUE; // ms
		battleEndTime = 0; // 战斗状态消失时间
		behavior = 0; // 游戏状态:攻击，防御，魔化，变身,战斗---影响技能的使用
		immuneElement = 0; // 免疫五行属性的状态
		updateTime = System.currentTimeMillis(); // 刷新时间
		nextUpdatetime = 0; // 下一次刷新时间
		updateOffset = 300; // 刷新间隔
		loginState = RoleGameState.BEFORE_LOGIN;
		levelupFristTime = 0;
		/* *重置战斗属性开始 */
		hp = 0;
		mp = 0;
		hpBase = 0;
		hpEquip = 0;
		equipMp = 0;
		hpSkillPro = 0;
		hpSumMax = 0;
		hpChange = 0;
		hpChangeEquip = 0;
		// hpPet = 0;
		hpRealm = 0;
		hpMedicine = 0;
		sumHpChange = 0;

		baseAttack = 0;
		attackEquip = 0;
		attackSkillPro = 0;
		attackPet = 0;
		attackRealm = 0;
		attackMedicine = 0;
		sumAttack = 0;

		baseDefence = 0;
		defenceEquip = 0;
		defenceSkillPro = 0;
		defencePet = 0;
		defenceRealm = 0;
		defenceMedicine = 0;
		sumDefence = 0;

		attackChance = 0;
		attackChanceEquip = 0;
		attackChancePet = 0;
		attackChanceRealm = 0;
		attackChanceMedicine = 0;
		sumAttackChance = 0;

		duckChance = 0;
		duckChanceEquip = 0;
		duckChancePet = 0;
		duckChanceRealm = 0;
		duckChanceMedicine = 0;
		sumDuckChance = 0;

		cruel = 0;
		cruelEquip = 0;
		cruelPet = 0;
		cruelRealm = 0;
		cruelMedicine = 0;
		sumCruel = 0;

		tenacity = 0;
		tenacityEquip = 0;
		tenacityPet = 0;
		tenacityRealm = 0;
		tenacityMedicine = 0;
		sumTenacity = 0;

		fender = 0;
		fenderEquip = 0;
		fenderPet = 0;
		fenderRealm = 0;
		fenderMedicine = 0;
		sumFender = 0;

		destroy = 0;
		destroyEquip = 0;
		destroyPet = 0;
		destroyRealm = 0;
		destroyMedicine = 0;
		sumDestroy = 0;

		suckHp = 0;
		suckHpEquip = 0;
		suckHpMedicine = 0;
		sumSuckHp = 0;

		rebound = 0;
		reboundEquip = 0;
		reboundMedicine = 0;
		sumRebound = 0;
		reboundPercent = 0;

		reduceDefence = 0;
		reduceDefenceEquip = 0;
		reduceDefenceRealm = 0;
		reduceDefenceMedicine = 0;
		sumReduceDefence = 0;

		resist = 0;
		resistEquip = 0;
		resistRealm = 0;
		resistMedicine = 0;
		sumResist = 0;

		/* 重置战斗属性结束* 重置培养属性开始 */
		baseForce = 0;
		baseMagic = 0;
		baseSprit = 0;
		forceEquip = 0;
		magicEquip = 0;
		spritEquip = 0;
		forceTrain = 0;
		magicTrain = 0;
		spritTrain = 0;
		sumForce = 0;
		sumMagic = 0;
		sumSprit = 0;
		supForce = 0;
		supMagic = 0;
		supSprit = 0;
		/* *重置培养属性结束 */

		if (roleSkill != null) {
			roleSkill.clear();
		}
		if (unOpenSkills != null) {
			unOpenSkills.clear();
		}
		for (int si = 0; si < MAX_SKILL; si++) {
			currSkill[si] = null;
		}
		if (skillCools != null) {
			skillCools.clear();
		}
		if (goodsCools != null) {
			goodsCools.clear();
		}
		if (visiableRoleMap != null) {
			visiableRoleMap.clear();
		}
		if (visiableRoles != null) {
			visiableRoles.clear();
		}
		if (visibleMonsters != null) {
			visibleMonsters.clear();
		}
		if (bufToImmune != null) {
			bufToImmune.clear();
		}
		if (acupointState != null) {
			acupointState.clear();
		}
		if (acupointedList != null) {
			acupointedList.clear();
		}
		if (openLingGenList != null) {
			openLingGenList.clear();
		}
		if (elevatedList != null) {
			elevatedList.clear();
		}
	}

	/**
	 * 判断角色是否已经接受该任务
	 * 
	 * @param missionId
	 *            任务编号
	 * @return true已经接受，false未接受
	 */
	public boolean hasMission(int missionId) {
		return false;
	}

	public short[][] getPhysicalArray() {
		return NULL_PHY;
	}

	/**
	 * 通过与角色绑定后的ID获取物品
	 * 
	 * @param id
	 *            与角色绑定后的ID
	 * @return 物品
	 */
	public AGoods getGoods(int id) {
		return null;
	}

	/**
	 * 在背包中查找指定类别的物品
	 * 
	 * @param cate
	 *            物品类别
	 * @return 物品对象，找不到返回null
	 */
	public AGoods getGoodsByCate(short cate, short belongBag) {
		return null;
	}

	/**
	 * 通过物品类别获取该类别所有物品
	 * 
	 * @param cate
	 * @return
	 */
	public List<AGoods> getGoodsListByCate(short cate, short belongBag) {
		return null;
	}

	/**
	 * 判断角色是否已经登陆
	 * 
	 * @return true已经登陆，false未登陆
	 */
	public boolean isLogined() {
		return loginState == RoleGameState.LOGINED;
	}

	/**
	 * 检测是否要刷新
	 * 
	 * @return true刷新，false不刷新
	 */
	public boolean isUpdate() {
		return nextUpdatetime <= System.currentTimeMillis() && clientModel != ClientCustom.ClientModel.V_2_ROBOT;
	}

	/**
	 * 判断角色是否在排队
	 * 
	 * @return true排队，false未排队
	 */
	public boolean isWaitQueue() {
		return loginState == RoleGameState.WAIT_QUEUE;
	}

	/**
	 * 广播数据包
	 * 
	 * @param packet
	 *            数据包
	 * @param self
	 *            是否要发给自己：true发给自己，false不发给自己
	 */
	public void broadcast(IoBuffer buf, boolean self) {
		broadcast(buf, self, 0);
	}

	/**
	 * 广播数据包
	 * 
	 * @param packet
	 *            数据包
	 * @param self
	 *            是否要发给自己：true发给自己，false不发给自己
	 */
	public void broadcast(IoBuffer buf, boolean self, int except) {
		if (except > 0) {
			List<Role> vr = getVisiableRoles();
			if (vr != null) {
				int size = vr.size();
				for (int i = 0; i < size; i++) {
					if (vr.get(i).getId() != except) {
						vr.get(i).sendData(buf.duplicateBuffer());
					}
				}
				if (self) {
					sendData(buf);
				} else {
					PacketBufferPool.freePacketBuffer(buf);
				}
			}
		} else {
			List<Role> vr = getVisiableRoles();
			if (vr != null) {
				int size = vr.size();
				for (int i = 0; i < size; i++) {
					vr.get(i).sendData(buf.duplicateBuffer());
				}
				if (self) {
					sendData(buf);
				} else {
					PacketBufferPool.freePacketBuffer(buf);
				}
			}
		}
	}

	/**
	 * 广播数据包
	 * 
	 * @param packet
	 *            数据包
	 * @param self
	 *            是否要发给自己：true发给自己，false不发给自己
	 * @param targets
	 *            目标
	 */
	public void broadcast(IoBuffer packet, boolean self, List<Role> targets) {
		List<Role> tempList = visiableRoles;
		if (tempList != null && tempList.size() > 0) {
			int size = tempList.size();
			Role role = null;
			int tsize = targets.size();
			Role tid = null;
			for (int i = 0; i < size; i++) {
				role = tempList.get(i);
				for (int ti = 0; ti < tsize; ti++) {
					tid = targets.get(ti);
					if (role.interestRole(tid) || tid.getId() == role.getId()) {
						role.sendData(packet.duplicateBuffer());
						break;
					}
				}
			}
		}
		if (self) {
			sendData(packet);
		} else {
			PacketBufferPool.freePacketBuffer(packet);
		}
	}

	// private void broadcastHp(int except) {
	// IoBuffer buffer = PacketBufferPool.getPacketBuffer();
	// buffer.setProtocol(UserProtocol.Server.PROS_6005_UPDATE_ROLE);
	// buffer.putInt(id);
	// buffer.setProperty(CommonGamePropertyKey.tempKey.TEMP_HP_CURR_603, getHp());
	// buffer.putShort((short) -1);
	// buffer.putInt(-1);
	// broadcast(buffer, true, except);
	// }

	/**
	 * 判断角色是否在视野内
	 * 
	 * @param roleId
	 *            角色编号
	 * @return true在视野内，false未在视野
	 */
	public boolean interestRole(Role role) {
		Role target = role;
		if (role.isPet()) {
			target = role.getHost();
		}
		return (visiableRoleMap != null && visiableRoleMap.containsKey(target.getId())) || target.getId() == id;
	}

	public void addPath(IoBuffer path) {

	}

	public void resetRefreshTime() {

	}

	/**
	 * 刷新角色
	 */
	final public void updateRole() {
		if (isUpdate()) {
			updateTime();
			updateBuf();
			updateSkill();
			updateState();
			updatePets();
		}
	}

	/**
	 * 刷新角色视野内的角色
	 * 
	 * @param nearRoles
	 *            相邻场景格子（注：nearRoles只允许遍历，不允许做清理或重新赋值）
	 * @param nearRoleLength
	 *            相邻格子的数量
	 */
	public void updateVisibility(ISceneGrid[] nearRoles, byte nearRoleLength) {
		checkRoleInView();
		if (isVisibilityMore()) {
			if (nearRoleLength > 0) {
				Role urole = null;
				List<Role> roleList = null;
				int srsize = 0;
				for (int ri = 0; ri < nearRoleLength; ri++) {
					roleList = nearRoles[ri].getRoles();
					srsize = roleList.size();
					for (int sri = 0; sri < srsize; sri++) {
						urole = roleList.get(sri);
						if (visiableRoleMap.containsKey(urole.getId()) || urole.getId() == id) {
							continue;
						}
						if (urole.isVisibilityMore() && getPixelDistance(urole.getPixelX(), urole.getPixelY()) < getVisiableDistance()) {
							enterView(urole);
						}
						if (visiableRoles.size() >= visibility) {
							return;
						}
					}
				}
			}
		}
	}

	/**
	 * 清除视野内的角色
	 */
	public void clearVisibleRoles() {
		List<Role> tempList = visiableRoles;
		if (tempList != null) {
			tempList.clear();
		}
		Map<Integer, Role> tempMap = visiableRoleMap;
		if (tempMap != null) {
			tempMap.clear();
		}
	}

	/**
	 * 清理视野内的怪物
	 */
	public void clearVisibleMonsters() {
		List<Role> tempList = visibleMonsters;
		if (tempList != null) {
			tempList.clear();
		}
	}

	/**
	 * 进入角色视野
	 * 
	 * @param urole
	 *            角色
	 */
	protected void enterView(Role urole) {
		Map<Integer, Role> tempMap = visiableRoleMap;
		if (tempMap != null) {
			tempMap.put(urole.getId(), urole);
		}
		List<Role> tempList = visiableRoles;
		if (tempList != null) {
			tempList.add(urole);
		}
	}

	/**
	 * 强制角色进入视野
	 * 
	 * @param role
	 *            角色
	 */
	public void enterViewForced(Role role) {
		Map<Integer, Role> tempMap = visiableRoleMap;
		if (tempMap != null) {
			tempMap.put(role.getId(), role);
		}
		List<Role> tempList = visiableRoles;
		if (tempList != null) {
			tempList.add(role);
		}
	}

	/**
	 * 检测已经在视野内的角色
	 */
	protected void checkRoleInView() {
		List<Role> tempList = visiableRoles;
		Map<Integer, Role> tempMap = visiableRoleMap;
		if (tempList == null || tempMap == null) {
			return;
		}
		int rsize = tempList.size();
		if (rsize > 0) {
			Role vrole = null;
			for (int ri = 0, ci = 0; ci < rsize && ri < rsize; ci++) {
				vrole = tempList.get(ri);
				if (vrole.isHideOther || isHideOther) {
					if (teamId > 0 && vrole.teamId != teamId) {
						tempList.remove(ri);
						tempMap.remove(vrole.getId());
						outView(vrole);
						rsize = tempList.size();
						continue;
					}
				}
				if (vrole.getMapId() != mapId || !vrole.isLogined() || getPixelDistance(vrole.getPixelX(), vrole.getPixelY()) > getVisiableDistance()) {
					tempList.remove(ri);
					tempMap.remove(vrole.getId());
					outView(vrole);
					rsize = tempList.size();
					continue;
				}
				ri++;
			}
		}
	}

	/**
	 * 重新定位角色坐标
	 * 
	 * @param target
	 *            目标角色
	 */
	public void relocation(Role target) {

	}

	/**
	 * 脱离角色视野
	 * 
	 * @param vrole
	 *            角色
	 */
	protected void outView(Role vrole) {
	}

	/**
	 * 强制把角色从视野中移除
	 * 
	 * @param role
	 *            角色
	 */
	public void outViewForced(Role role) {
		List<Role> temp = visiableRoles;
		if (temp != null) {
			temp.remove(role);
		}

		Map<Integer, Role> tempMap = visiableRoleMap;
		if (tempMap != null) {
			tempMap.remove(role.getId());
		}
	}

	/**
	 * 视野内容纳的角色是否已经饱和
	 * 
	 * @return true未满，false已满
	 */
	public boolean isVisibilityMore() {
		List<Role> tempList = visiableRoles;
		if (tempList == null) {
			return false;
		}
		return visiableRoles.size() < visibility;
	}

	public List<Role> getVisiableRoles() {
		return visiableRoles;
	}

	/**
	 * 刷新角色身上的BUF
	 */
	public void updateBuf() {

	}

	/**
	 * 刷新角色路径
	 */
	public void updateRolePath() {

	}

	/**
	 * 向客户端推送一条消息
	 * 
	 * @param tipsType
	 *            消息显示类型
	 * @param messageCate
	 * @param opcode
	 * @param message
	 * @param connectId
	 */
	public void sendOpcode(byte tipsType, byte messageCate, int opcode, String message, int connectId) {

	}

	public Role() {
		behavior = RoleState.GAME_ATTACK;
		this.objectId = OBJECT_ID_GENERATOR.getAndIncrement();
	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	/**
	 * 获取路径对象
	 * 
	 * @return
	 */
	public Object[] getPath() {
		return null;
	}

	/**
	 * 添加一组路径
	 * 
	 * @param path
	 */
	public void addPath(int[] path) {

	}

	/**
	 * 获取角色所在图层
	 * 
	 * @return 图层对象
	 */
	public ILayer getLayerObject() {
		return null;
	}

	/**
	 * 通过骨骼ID获取骨骼文件名
	 * 
	 * @param skeleton
	 *            骨骼ID
	 * @return 骨骼文件名
	 */
	public String getSkeletonFile(byte skeleton) {
		return "";
	}

	/**
	 * 获取并封装骨骼
	 * 
	 * @param skeleton
	 * @param buf
	 * @return 骨骼文件名
	 */
	public String getSkeletonFile(byte skeleton, IoBuffer buf) {
		buf.put(skeleton);
		buf.putString("");
		return "";
	}

	/**
	 * 清理路径
	 */
	public void clearPath() {

	}

	/**
	 * 刷新时间
	 */
	public void updateTime() {
		long curr = System.currentTimeMillis();
		long delta = curr - updateTime;
		updateTime = curr;
		lifetime -= delta;
		nextUpdatetime = curr + updateOffset;
		if (dirtyHp && curr > broadcastTime) {
			dirtyHp = false;
			broadcastTime = curr + BROADCAST_OFFSET;
			// broadcastHp(0);
		}
	}

	/**
	 * 刷新宠物
	 */
	public void updatePets() {

	}

	/**
	 * 增加仇恨值
	 * 
	 * @param value
	 *            新增的仇恨值
	 * @param enmity
	 *            仇恨对象
	 */
	public void addEnmityValue(int value, Role enmity) {

	}

	/**
	 * 获得经验
	 * 
	 * @param experience
	 *            新增加的经验值
	 * @return 获取经验后等级改变量（新等级-原等级）
	 */
	public short addRoleExperience(int experience) {
		return 0;
	}

	/**
	 * 获得仙府经验
	 * 
	 * @param experience
	 *            新增加的经验值
	 * @return 获取经验后等级改变量（新等级-原等级）
	 */
	public short addMansionExperience(int experience) {
		return 0;
	}

	/**
	 * 获得经验
	 * 
	 * @param experience
	 *            新增加的经验值
	 * @param maxLevel
	 *            等级上限
	 * @return 获取经验后等级改变量（新等级-原等级）
	 */
	public short addExperience(int experience, int maxLevel) {
		return 0;
	}

	/**
	 * 获得境界点
	 * 
	 * @param value
	 *            变化量
	 */
	public void awardRealmPoint(int value) {
	}

	/**
	 * 添加一个宠物
	 * 
	 * @param pet
	 */
	public void addPet(Role pet, boolean checkMission) {

	}

	/**
	 * 增加一个出战宠物
	 * 
	 * @param pet
	 */
	public void addCurPet(byte index, Role pet) {

	}

	public void resetQilingRole(Role pet) {

	}

	/**
	 * 获取器灵对象
	 * 
	 * @return 器灵对象
	 */
	public Role getQilingRole() {
		return null;
	}

	/**
	 * 是否要创建角色
	 * 
	 * @return true创建角色，false不创建角色
	 */
	public final boolean isCreateRole() {
		return loginState == RoleGameState.CREATE_ROLE;
	}

	/**
	 * 被敌方攻击并造成伤害
	 * 
	 * @param hitValue
	 *            伤害值
	 * @param enmity
	 *            敌方
	 * @return true死亡，false未死亡
	 */
	public boolean attacked(int hitValue, Role enmity) {
		boolean isDeath = false;
		hp += hitValue;
		isDeath = hp <= 1;
		if (isDeath) {
			hp = 0;
		}
		dirtyHp = true;
		if (hitValue < 1) {
			enterBehavior(RoleState.GAME_BATTLE);
			if (enmity != null) {
				enmity.enterBehavior(RoleState.GAME_BATTLE);
			}
		}
		return isDeath;
	}

	/**
	 * 是否为地方
	 * 
	 * @param role
	 *            目标
	 * @return true地方，false友方
	 */
	public boolean isEnmity(Role role) {
		if (role == null) {
			return false;
		}
		return this.camp != role.camp;
	}

	/**
	 * 清除所有技能的冷却时间
	 */
	public void clearSkillCooltime() {
		Collection<ASkill> skillTmp = roleSkill.values();
		for (ASkill skill : skillTmp) {
			skill.clearCooltime();
		}
	}

	public Map<Long, Object> getRequestSectList() {
		if (requestSectList == null) {
			return NULL_REQUEST;
		}
		return requestSectList;
	}

	public void addRequestSect(long sectId) {
		if (requestSectList == null) {
			requestSectList = new HashMap<Long, Object>();
		}
		requestSectList.put(sectId, COMMON_VALUE);
	}

	public void joinSect(long sectId) {
		this.sectId = sectId;
	}

	public void removeRequestSect(long sectId) {
		if (requestSectList != null) {
			requestSectList.remove(sectId);
		}
	}

	/**
	 * 克隆对象
	 */
	public Object clone() {
		Role o = null;
		try {
			o = (Role) super.clone();
			o.roleSkill = new HashMap<Integer, ASkill>(); // 角色拥有的技能
			o.currSkill = new ASkill[MAX_SKILL];
			o.skillCools = new HashMap<Integer, CommonCool>();
			o.goodsCools = new HashMap<Integer, CommonCool>();
			o.visiableRoleMap = new HashMap<Integer, Role>();
			o.visiableRoles = new LinkedList<Role>();
			o.bufToImmune = new HashMap<Integer, Short>();
			o.acupointState = new HashMap<Integer, Byte>();
			o.acupointedList = new HashSet<Integer>();
			o.elevatedList = new HashSet<Integer>();
			o.openLingGenList = new HashSet<Byte>();

		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}

	/**
	 * 进入游戏状态
	 * 
	 * @param behavior
	 *            行为
	 */
	public void enterBehavior(int behavior) {
		this.behavior |= behavior;
	}

	/**
	 * 退出游戏状态
	 * 
	 * @param behavior
	 *            行为
	 */
	public void exitBehavior(int behavior) {
		this.behavior &= (~behavior);
	}

	public boolean hasPointBehavior(int behavior) {
		return (this.behavior & RoleState.GAME_DA_ZUO) != 0;
	}

	/**
	 * 释放技能时失败原因
	 * 
	 * @param cate
	 *            技能类别
	 * 
	 * @param message
	 *            施法失败原因
	 */
	public void failSkillMsg(short cate, String message) {

	}

	/**
	 * 获取角色动画配置文件
	 * 
	 * @return 角色动画配置文件
	 */
	public String getAnimation() {
		return animation;
	}

	public long getBattleEndTime() {
		return battleEndTime;
	}

	/**
	 * 获取角色行为状态的综合值（禁止物理攻击，禁止法术攻击，禁止移动，变身，魔化）
	 * 
	 * @return 角色行为状态的综合值
	 */
	public int getBehavior() {
		return behavior;
	}

	/**
	 * 获取BUF对攻击力的影响值
	 * 
	 * @return BUF对攻击力的影响值
	 */
	public short getBufAttack() {
		return 0;
	}

	/**
	 * 获取角色身上附加BUF的数量
	 * 
	 * @param bufId
	 *            附加的BUF的ID
	 * @return 附加数量
	 */
	public int getBufCount(int bufId) {
		return 0;
	}

	/**
	 * 获取BUF对防御值的影响值
	 * 
	 * @return BUF对防御值的影响值
	 */
	public int getBufDefence() {
		return 0;
	}

	public byte getBuilder() {
		return builder;
	}

	/**
	 * 获取阵营
	 * 
	 * @return 阵营
	 */
	public int getCamp() {
		return camp;
	}

	/**
	 * 获取境界
	 */
	public byte getRealm() {
		return realm;
	}

	/**
	 * 获取数据状态
	 * 
	 * @return 数据状态
	 */
	public byte getDbState() {
		return this.dbState;
	}

	/**
	 * 获取两个tile间的距离
	 * 
	 * @param tilex
	 *            x坐标
	 * @param tiley
	 *            y坐标
	 * @return 距离
	 */
	public int getDistance(short tilex, short tiley) {
		int dx = this.tileX - tilex;
		int dy = this.tileY - tiley;
		return (int) Math.sqrt(dx * dx + dy * dy);
	}

	/**
	 * 获取两个tile间的距离
	 * 
	 * @param tilex
	 *            x坐标
	 * @param tiley
	 *            y坐标
	 * @return 距离
	 */
	public int getPixelDistance(int pixelX, int pixelY) {
		int dx = this.pixelX - pixelX;
		int dy = this.pixelY - pixelY;
		return dx * dx + dy * dy;
	}

	public boolean isPhy(int tx, int ty) {
		return false;
	}

	/**
	 * 获取被动技能对攻击力的影响值
	 * 
	 * @return 被动技能对攻击力的影响值
	 */
	public short getEdSkillAttack() {
		return 0;
	}

	/**
	 * 获取被动技能对防御的影响值
	 * 
	 * @return 被动技能对防御的影响值
	 */
	public int getEdSkillDefence() {
		return 0;
	}

	/**
	 * 获取装备对攻击力的增幅值
	 * 
	 * @return 装备对攻击力的增幅值
	 */
	public short getEquipAttack() {
		return 0;
	}

	/**
	 * 获取装备对防御的增幅值
	 * 
	 * @return 装备对防御的增幅值
	 */
	public int getEquipDefence() {
		return 0;
	}

	/**
	 * 获取角色经验
	 */
	public int getExperience() {
		return experience;
	}

	/**
	 * 获取角色的面对的方向（ 0下 ;1 左 ;2上 ;3右 ;4 左下 ; 5左上 ; 6右上 ;7右下 ）
	 */
	public final byte getFaceDir(Role target) {
		if (target == null) {
			return DirectConfig.DIRECT_DOWN;
		}
		int tarCeilX = target.getTileX();
		int tarCeilY = target.getTileY();
		if (tileX < tarCeilX) {
			return DirectConfig.DIRECT_RIGHT;
		} else if (tileX > tarCeilX) {
			return DirectConfig.DIRECT_LEFT;
		} else {
			if (tileY < tarCeilY) {
				return DirectConfig.DIRECT_DOWN;
			} else if (tileY > tarCeilY) {
				return DirectConfig.DIRECT_UP;
			}
		}

		return DirectConfig.DIRECT_DOWN;
	}

	/**
	 * 获取战团ID
	 * 
	 * @return 战团ID
	 */
	public short getGroupId() {
		return groupId;
	}

	/**
	 * @return Role 攻击的目标,被怪物复写
	 */
	public Role getHitTarget() {
		return hitTarget;
	}

	/**
	 * 获取角色所在的图层
	 * 
	 * @return 角色所在的图层
	 */
	public int getLayer() {
		return layer;
	}

	/**
	 * 获取等级
	 */
	public short getLevel() {
		return level;
	}

	/**
	 * 刷新角色视野内的角色
	 */
	public void updateVisibility(List<ISceneGrid> nearRoles) {
		checkRoleInView();
		List<Role> tempList = visiableRoles;
		Map<Integer, Role> tempMap = visiableRoleMap;
		if (tempList == null || tempMap == null || tempList.size() >= visibility) {
			return;
		}
		if (isVisibilityMore()) {
			int rsize = nearRoles.size();
			if (rsize > 0) {
				Role urole = null;
				List<Role> roleList = null;
				int srsize = 0;
				for (int ri = 0; ri < rsize; ri++) {
					roleList = nearRoles.get(ri).getRoles();
					srsize = roleList.size();
					for (int sri = 0; sri < srsize; sri++) {
						urole = roleList.get(sri);
						if (tempMap.containsKey(urole.getId()) || urole.getId() == id) {
							continue;
						}
						if (urole.isVisibilityMore() && getPixelDistance(urole.getPixelX(), urole.getPixelY()) < getVisiableDistance()) {
							enterView(urole);
						}
						if (tempList.size() >= visibility) {
							return;
						}
					}
				}
			}
		}
	}

	public int getLifetime() {
		return lifetime;
	}

	/**
	 * 获取角色所在的场景编号
	 * 
	 * @return 角色所在的场景编号
	 */
	public int getMapId() {
		return this.mapId;
	}

	/**
	 * 获取最大等级（宝宝成长或怪物成长的上限值）
	 * 
	 * @return 最大等级
	 */
	public short getMaxLevel() {
		return maxLevel;
	}

	/**
	 * 获取角色已经完成指定任务的数量
	 * 
	 * @param missionId
	 *            已经完成的任务的ID
	 * @return 完成的次数(已经提交)
	 */
	public int getMissionLogCount(int missionId) {
		return 0;
	}

	/**
	 * 获取移动参考值
	 * 
	 * @return 移动参考值
	 */
	public short getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * 通过key修改角色属性
	 * 
	 * @param key
	 *            键
	 * @param value
	 *            值
	 */
	public void updateRoleProperty(short key, String value) {
	}

	/**
	 * 获取角色描述
	 * 
	 * @return 角色描述
	 */
	public String getNote() {
		return this.note;
	}

	/**
	 * 获取父对象的ID
	 * 
	 * @return 父对象的ID
	 */
	public int getParent() {
		return parent;
	}

	/**
	 * 通过宠物编号获取宠物对象
	 * 
	 * @param petId
	 *            宠物编号
	 * @return 宠物对象
	 */
	public Role getPet(int petId) {
		return null;
	}

	/**
	 * 通过宠物真实ID获取出战的该宠物
	 * 
	 * @param realId
	 * @return
	 */
	public Role getCurPetByRealId(int realId) {
		return null;
	}

	/**
	 * 通过宠物ID获取第一个非出战的宠物非自身的宠物
	 * 
	 * @param realId
	 * @return
	 */
	public Role getUnSelfPetByRealId(int petId, int realId) {
		return null;
	}

	/**
	 * 获取角色拥有的宠物列表
	 * 
	 * @return 角色拥有的宠物列表
	 */
	public Map<Integer, Role> getPets() {
		return null;
	}

	/**
	 * 获取角色的复活间隔
	 * 
	 * @return short 角色死亡后复活间隔时间（ms）
	 */
	public int getReliveOffsetTime() {
		return reliveOffsetTime;
	}

	public long getReliveTime() {
		return reliveTime;
	}

	public void setReliveTime(long time) {
		this.reliveTime = time;
	}

	public ASkill getSkill(int skillId) {
		return roleSkill.get(skillId);
	}

	public ASkill[] getSkills() {
		Collection<ASkill> skillTmp = roleSkill.values();
		ASkill[] skills = new ASkill[skillTmp.size()];
		skillTmp.toArray(skills);
		return skills;
	}

	/**
	 * 获取角色的职业技能
	 * 
	 * @return
	 */
	public List<ASkill> getProfessionSkills() {
		Collection<ASkill> skillTmp = roleSkill.values();
		List<ASkill> result = new ArrayList<ASkill>();
		for (ASkill skill : skillTmp) {
			if (skill.getCate() == SkillConfig.Cate.PROFESSION) {
				result.add(skill);
			}
		}
		return result;
	}

	/**
	 * 获取角色装备栏中已经强化了point点的装备数量
	 * 
	 * @param point
	 *            强化底线
	 * @return 达到条件的装备的数量
	 */
	public int getSuitPointCount(short point) {
		return 0;
	}

	/**
	 * 获取移动速度的综合值
	 * 
	 * @return 移动速度的综合值
	 */
	public short getSumMoveSpeed() {
		return sumMoveSpeed;
	}

	/**
	 * 获取队伍编号
	 * 
	 * @return 队伍编号
	 */
	public int getTeamId() {
		return teamId;
	}

	/**
	 * X坐标值，角色当前Tile坐标
	 * 
	 * @return X坐标值，角色当前Tile坐标
	 */
	public short getTileX() {
		return tileX;
	}

	/**
	 * Y坐标值，角色当前Tile坐标
	 * 
	 * @return Y坐标值，角色当前Tile坐标
	 */
	public short getTileY() {
		return tileY;
	}

	/**
	 * 获取角色所在分区的编号
	 * 
	 * @return int 分区编号
	 */
	public int getZoneId() {
		return zoneId;
	}

	/**
	 * 判断角色是否处于攻击状态
	 * 
	 * @return true:攻击状态，false：不处于攻击状态
	 */
	public boolean isAttackState() {
		return (behavior & RoleState.GAME_ATTACK) == RoleState.GAME_ATTACK;
	}

	/**
	 * 判断角色是处于战斗状态
	 * 
	 * @return boolean true战斗状态，false不在战斗状态
	 */
	public boolean isBattleState() {
		return (behavior & RoleState.GAME_BATTLE) == RoleState.GAME_BATTLE;
	}

	/**
	 * 判断角色是否死亡
	 * 
	 * @return boolean true死亡，false存活
	 */
	public boolean isDeath() {
		return hp < 1;
	}

	/**
	 * 判断角色是否处于防御状态
	 * 
	 * @return true:防御状态，false：不处于防御状态
	 */
	public boolean isDefenceState() {
		return (behavior & RoleState.GAME_DEFENCE) == RoleState.GAME_DEFENCE;
	}

	/**
	 * 判断角色是否处于变身状态
	 * 
	 * @return true:变身状态，false：不处于变身状态
	 */
	public boolean isDistortion() {
		return (behavior & RoleState.GAME_DISTORTION) == RoleState.GAME_DISTORTION;
	}

	/**
	 * 判断角色是否正在做指定的任务
	 * 
	 * @param missionId
	 *            任务编号
	 * @return true正在做，false未做
	 */
	public boolean isDoingMission(int missionId) {
		return false;
	}

	/**
	 * 判断角色是否被禁止释放魔法
	 * 
	 * @return boolean true禁止释放魔法，false可以释放魔法
	 */
	public boolean isEstopMagic() {
		return (behavior & RoleState.GAME_ESTOP_MOGIC) == RoleState.GAME_ESTOP_MOGIC;
	}

	/**
	 * 判断角色是否被禁止移动
	 * 
	 * @return boolean true禁止移动，false可以移动
	 */
	public boolean isEstopMove() {
		return (behavior & RoleState.GAME_ESTOP_MOVE) == RoleState.GAME_ESTOP_MOVE || this.moveSpeed < 1;
	}

	/**
	 * 判断角色是否被禁止物理攻击
	 * 
	 * @return boolean true禁止物理攻击，false可以物理攻击
	 */
	public boolean isEstopPhysics() {
		return (behavior & RoleState.GAME_ESTOP_PHYSICS) == RoleState.GAME_ESTOP_PHYSICS;
	}

	/**
	 * 判断角色是否处于魔化状态
	 * 
	 * @return true:魔化状态，false：不处于魔化状态
	 */
	public boolean isEvil() {
		return (behavior & RoleState.GAME_EVIL) == RoleState.GAME_EVIL;
	}

	/**
	 * 判断角色是否复活
	 * 
	 * @return boolean true角色可以复活，false角色不能复活
	 */
	public boolean isRelive() {
		return System.currentTimeMillis() > reliveTime;
	}

	public int getReliveScene() {
		return 0;
	}

	public void setReliveScene(int reliveScene) {
	}

	/**
	 * 移除攻击目标
	 * 
	 * @param target
	 *            移除目标
	 */
	public void removeHitTarget(Role target) {
	}

	/**
	 * 移除角色技能
	 * 
	 * @param id
	 *            技能编号
	 */
	public void removeSkill(int id) {
		roleSkill.remove(id);
	}

	/**
	 * 重置角色等级属性
	 */
	public void resetLevelProperties(short newLevel) {
		this.level = newLevel;
	}

	public ILayer validateLayer(int scene, int layer) {
		return validateLayer(0, scene, layer);
	}

	public ILayer validateLayer(int zone, int scene, int layer) {
		return null;
	}

	/**
	 * 设置角色动画配置文件
	 * 
	 * @param animation
	 *            角色动画配置文件
	 */
	public void setAnimation(String animation) {
		this.animation = animation;
	}

	public void setBattleEndTime(long battleEndTime) {
		this.battleEndTime = battleEndTime;
	}

	public void setBehavior(short behavior) {
		this.behavior = behavior;
	}

	public void setBuilder(byte builder) {
		this.builder = builder;
	}

	/**
	 * 设置阵营
	 * 
	 * @param camp
	 */
	public void setCamp(int camp) {
		this.camp = camp;
	}

	/**
	 * 阵营发生变动
	 * 
	 * @param camp
	 */
	public void changeCamp(int camp) {
		this.camp = camp;
	}

	/**
	 * 设置角色境界
	 * 
	 * @param realm
	 *            境界
	 */
	public void setRealm(byte realm) {
		this.realm = realm;
	}

	public void addRealm() {
		if (this.realm < RoleRealmConfig.JieDan_3) {// 上限限制 TODO
			this.realm++;
		}
	}

	/**
	 * 修改角色的当前坐标
	 * 
	 * @param x
	 *            X坐标值
	 * @param y
	 *            Y坐标值
	 */
	public void setCurrTileXY(int x, int y) {
		if (this.tileX != x || this.tileY != y) {
			this.tileX = (short) x;
			this.tileY = (short) y;
			this.pixelX = tileX * SpeedConfig.TILE_SIZE + SpeedConfig.HALF_TILE;
			this.pixelY = tileY * SpeedConfig.TILE_SIZE + SpeedConfig.HALF_TILE;
		}
	}

	public void setPixelXY(int x, int y) {
		this.pixelX = x;
		this.pixelY = y;
		this.tileX = (short) ((this.pixelX - SpeedConfig.HALF_TILE) / SpeedConfig.TILE_SIZE);
		this.tileY = (short) ((this.pixelY - SpeedConfig.HALF_TILE) / SpeedConfig.TILE_SIZE);
	}

	/**
	 * 设置状态
	 * 
	 * @param dbState
	 *            状态值
	 */
	public void setDbState(byte dbState) {
		this.dbState = dbState;
	}

	/**
	 * 设置战团ID
	 * 
	 * @param groupId
	 *            战团ID
	 */
	public void setGroupId(short groupId) {
		this.groupId = groupId;
	}

	public void setHitTarget(Role hitTarget) {
		this.hitTarget = hitTarget;
		System.err.println("hitTarget - 99 = " + hitTarget);
	}

	public boolean isOutVisiable(Role role) {
		return getPixelDistance(role.getPixelX(), role.getPixelY()) > getVisiableDistance();
	}

	public void setHpChange(short hpChange) {
		this.hpChange = hpChange;
	}

	/**
	 * 设置角色所在的图层
	 * 
	 * @param layer
	 *            角色所在的图层
	 */
	public void setLayer(int layer) {
		this.layer = layer;
	}

	/**
	 * 设置角色等级
	 * 
	 * @param level
	 *            角色等级
	 */
	public void setLevel(short level) {
		this.level = level;
	}

	public void setLifetime(int lifetime) {
		this.lifetime = lifetime;
	}

	/**
	 * 设置角色所在的场景
	 * 
	 * @param mapid
	 *            场景编号
	 */
	public void setMapId(int mapid) {
		this.mapId = mapid;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = (short) maxLevel;
	}

	public int getMoney(int moneyId) {
		return 0;
	}

	/**
	 * 增加货币
	 * 
	 * @param moneyId
	 *            货币ID
	 * @param count
	 *            增加的数量
	 * @param reason
	 *            增加的原因
	 * @param note
	 *            备注
	 * @return 增加后的货币数量
	 */
	public void addMoney(int moneyId, int count, String operate, String project, String orderFormId, boolean checkExp) {
		if (count == 0) {
			return;
		}
		addMoney(moneyId, count, operate, project, orderFormId, false, checkExp);
	}

	/**
	 * 增加货币
	 * 
	 * @param moneyId
	 *            货币ID
	 * @param count
	 *            增加的数量
	 * @param reason
	 *            增加的原因
	 * @param note
	 *            备注
	 * @return 增加后的货币数量
	 */
	public int addMoney(int moneyId, int count, String operate, String project, String orderFormId, boolean patch, boolean checkExp) {
		return 0;
	}

	/**
	 * 增加货币
	 * 
	 * @param moneyId
	 *            货币ID
	 * @param count
	 *            增加的数量
	 * @param reason
	 *            增加的原因
	 * @param note
	 *            备注
	 * @param maxCate
	 *            上限类型
	 * @return 增加后的货币数量
	 */
	public int addMoney(int moneyId, int count, String operate, String project, String orderFormId, boolean patch, short maxCate, boolean checkExp) {
		return 0;
	}

	/**
	 * 扣除货币
	 * 
	 * @param moneyId
	 *            货币ID
	 * @param count
	 *            扣除的数量
	 * @param reason
	 *            扣除的原因
	 * @param note
	 *            备注
	 * @param jionActivity
	 *            扣除的货币是否参与活动计算
	 * 
	 * @return 扣除后货币数量
	 * 
	 */
	public int costMoney(int moneyId, int count, String operate, String project, String orderFormId, boolean jionActivity) {
		/**
		 * @add
		 * @name：xiaoqiong
		 * @date：2015-01-29
		 * @note：扣除货币的方法
		 */
		return 0;
	}

	public int costMoney(int moneyId, int count, String operate, String project, String orderFormId) {
		/**
		 * @add
		 * @name：xiaoqiong
		 * @date：2012-04-15
		 * @note：扣除货币的方法
		 */
		return 0;
	}

	/**
	 * 设置移动速度参照数
	 * 
	 * @param moveSpeed
	 *            参照数值
	 */
	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = (short) moveSpeed;
	}

	/**
	 * 设置角色描述信息
	 * 
	 * @param note
	 *            描述信息
	 */
	public void setNote(String note) {
		this.note = note;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	/**
	 * 设置角色死亡后的复活间隔时间
	 * 
	 * @param reliveOffsetTime
	 *            死亡后复活间隔时间（ms）
	 */
	public void setReliveOffsetTime(int reliveOffsetTime) {
		if (reliveOffsetTime < 0) {
			this.reliveOffsetTime = reliveOffsetTime = Integer.MAX_VALUE;
		} else {
			this.reliveOffsetTime = reliveOffsetTime;
		}
	}

	public void addSkill(ASkill skill) {
		if (skill != null) {
			skill.setFirer(this);
			skill.initSkill();
			roleSkill.put(skill.getSkillid(), skill);
			if (skill.getCate() == SkillCate.ED_SKILL) {
				skill.playSkill();
				skill.finish();
			}
			if (this.realm >= skill.getOpenRealm() && skill.getOpenState() == SkillConfig.State.UNOPEN && skill.getOpenLv() <= this.level) {
				skill.setOpenState(SkillConfig.State.UNSTUDY);
				this.skillLvSum += 1;
			}
			if (skill.getOpenState() == SkillConfig.State.UNOPEN) {
				unOpenSkills.add(skill.getSkillid());
			}
		}
	}

	/**
	 * 获得物品
	 * 
	 * @param goods
	 * @param awardReason
	 * @return 实际获得的数量
	 */
	public int awardCommonGoods(AGoods goods, String operate, String project, String orderFormId) {
		return 0;
	}

	/**
	 * 奖励物品
	 * 
	 * @param goodsId
	 *            物品ID
	 * @param count
	 *            数量
	 */
	public void awardCommonGoods(String operate, String project, String orderFormId, int goodsId, int count, byte pinZhi) {
	}

	/**
	 * 奖励物品
	 * 
	 * @param goodsId
	 *            物品ID
	 * @param count
	 *            数量
	 */
	public void awardCommonGoods(String operate, String project, String orderFormId, int goodsId, int count) {
	}

	/**
	 * 奖励一个物品
	 * 
	 * @param project
	 * @param goodsId
	 *            物品ID
	 * @param count
	 *            数量
	 * @param quality
	 *            品质
	 * @param binded
	 *            是否绑定
	 */
	public void awardCommonGoods(String operate, String project, String orderFormId, int goodsId, int count, boolean binded, byte pinZhi) {

	}

	/**
	 * 设置移动速度的综合值
	 * 
	 * @param sumMoveSpeed
	 *            移动速度的综合值
	 */
	public void setSumMoveSpeed(int sumMoveSpeed) {
		this.sumMoveSpeed = (short) sumMoveSpeed;
	}

	public int changeSumMoveSpeed(int value) {
		int old = sumMoveSpeed;
		this.sumMoveSpeed += value;
		if (this.sumMoveSpeed < moveSpeed) {
			this.sumMoveSpeed = moveSpeed;
		}
		return this.sumMoveSpeed - old;
	}

	/**
	 * 设置队伍编号
	 * 
	 * @param teamId
	 */
	public void setTeamId(int teamId) {
		this.teamId = teamId;
		RoleMini rm = RoleManager.getRoleMini(id);
		if (rm != null) {
			rm.setTeamId(teamId);
		}
	}

	/**
	 * 设置角色当前Tile坐标的X坐标
	 * 
	 * @param x
	 *            X坐标值
	 */
	public void setTileX(short x) {
		setCurrTileXY(x, tileY);
	}

	/**
	 * 设置角色当前Tile坐标的Y坐标
	 * 
	 * @param y
	 *            Y坐标值
	 */
	public void setTileY(short y) {
		setCurrTileXY(tileX, y);
	}

	/**
	 * 设置角色所在的分区编号
	 * 
	 * @param zoneId
	 *            分区编号
	 */
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}

	public boolean skillJudge(ASkill skill) {
		return false;
	}

	public void missionAwards(int missionId, List<Award> awards, int experience, int money) {

	}

	public void monsterAwards(List<AGoods> awardGoods, int experience, int money) {

	}

	public void upgrade() {

	}

	public final void clearGroupProperty(short main, short sub) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
		buf.setNetConfirm(-1);
		buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
		buf.setOverlap(false);
		buf.setCmd(ClientUI.UI.CLEAR_OBJECT_LIST);
		buf.setMain(main);
		buf.setSub(sub);
		buf.endCmd();
		sendData(buf);
	}

	public boolean stopSkill(int skillKey) {
		for (int i = 0; i < currSkill.length; i++) {
			ASkill skill = currSkill[i];
			if (skill != null && skillKey == skill.getSkillid()) {
				currSkill[i] = null;
				return true;
			}
		}

		return false;
	}

	/**
	 * 切换角色的行为状态（攻击-防御）
	 * 
	 * @return 角色行为状态的综合值
	 */
	public int switchState() {
		if (isAttackState()) {
			exitBehavior(RoleState.GAME_ATTACK);
			enterBehavior(RoleState.GAME_DEFENCE);
		} else {
			exitBehavior(RoleState.GAME_DEFENCE);
			enterBehavior(RoleState.GAME_ATTACK);
		}
		return behavior;
	}

	/**
	 * 角色进入死亡状态
	 */
	public void toDeath() {
	}

	public int getLevelExperience() {
		return 0;
	}

	public void relive() {

	}

	public void relive(int mapID, int layer, int x, int y) {

	}

	/**
	 * 刷新战斗状态
	 */
	public void updateState() {

	}

	/**
	 * 更新角色的行为状态
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
	 * @return 角色行为状态的综合值
	 */
	public int updateBehavior(int physics, int magic, int move, int distortion, int evil) {
		switch (physics) {
			case -1: {
				enterBehavior(RoleState.GAME_ESTOP_PHYSICS);
				break;
			}
			case 0: {
				break;
			}
			case 1: {
				exitBehavior(RoleState.GAME_ESTOP_PHYSICS);
				break;
			}
		}
		switch (magic) {
			case -1: {
				enterBehavior(RoleState.GAME_ESTOP_MOGIC);
				break;
			}
			case 0: {
				break;
			}
			case 1: {
				exitBehavior(RoleState.GAME_ESTOP_MOGIC);
				break;
			}
		}
		switch (distortion) {
			case -1: {
				exitBehavior(RoleState.GAME_DISTORTION);
				break;
			}
			case 0: {
				break;
			}
			case 1: {
				enterBehavior(RoleState.GAME_DISTORTION);
				break;
			}
		}
		switch (evil) {
			case -1: {
				exitBehavior(RoleState.GAME_EVIL);
				break;
			}
			case 0: {
				break;
			}
			case 1: {
				enterBehavior(RoleState.GAME_EVIL);
				break;
			}
		}
		switch (move) {
			case -1: {
				enterBehavior(RoleState.GAME_ESTOP_MOVE);
				break;
			}
			case 0: {
				break;
			}
			case 1: {
				exitBehavior(RoleState.GAME_ESTOP_MOVE);
				break;
			}
		}
		return behavior;
	}

	public boolean missionNeedGoods(int mission, int goods) {
		return false;
	}

	/**
	 * 技能升级
	 * 
	 * @param mijiID
	 *            所属秘籍
	 * @param skillID
	 *            技能
	 * @param xinfaID
	 *            心法
	 * @param connectID
	 *            连接ID
	 */
	public void skillLeveUp(int mijiID, int skillID, int xinfaID, int connectID) {

	}

	/**
	 * 装备技能
	 * 
	 * @param key
	 *            按键
	 * @param skillID
	 *            技能编号
	 * @param connectID
	 *            连接id
	 */
	public void equipSkill(int key, int skillID, int connectID) {

	}

	/**
	 * 更新角色复活时间
	 */
	public void updateReliveTime() {
		reliveTime = System.currentTimeMillis() + reliveOffsetTime;
	}

	public boolean updateSkill() {
		ASkill skill = null;
		for (int si = 0; si < MAX_SKILL; si++) {
			skill = currSkill[si];
			if (skill != null && skill.isReady2Play()) {
				skill.playSkill();
				skill.finish();
				currSkill[si] = null;
			}
		}
		return true;
	}

	public void sortSkill() {

	}

	public Role getHost() {
		return null;
	}

	public void setHost(Role host) {
	}

	public short generateEmailIndex(long emailId) {
		return 0;
	}

	/**
	 * 重置角色的属性
	 * 
	 * @param props
	 *            属性键值对
	 */
	public void resetProperties(Map<Integer, Integer> props) {

	}

	public String getUIScript(int uiId) {
		return VersionConfig.Client.NONE_SCRIPT;
	}

	/**
	 * 显示传送阵
	 * 
	 * @param exitId
	 *            传送阵的ID
	 */
	public void showExit(int exitId) {

	}

	/***
	 * 清除攻击目标
	 */
	public void resetHitTarget() {
		this.hitTarget = null;
	}

	/**
	 * 退出当前场景，进入到新场景
	 * 
	 * @param scene
	 *            待进入的新场景
	 * @param ceilX
	 *            新场景的TileX
	 * @param ceilY
	 *            新场景的TileY
	 */
	public void changeScene(GameScene scene, int ceilX, int ceilY) {

	}

	/**
	 * 退出当前场景，进入到新场景
	 * 
	 * @param sceneId
	 *            待进入的新场景
	 * @param newLayer
	 *            进入的图层号
	 * @param ceilX
	 *            新场景的TileX
	 * @param ceilY
	 *            新场景的TileY
	 */
	public void changeScene(int sceneId, int newLayer, int ceilX, int ceilY) {

	}

	/**
	 * 进入新场景
	 * 
	 * @param sceneId
	 *            新场景编号
	 * @param layer
	 *            新场景的图层
	 * @param tilex
	 *            新场景TileX
	 * @param tiley
	 *            新场景TileY
	 */
	public void enterScene(int sceneId, int layer, int tilex, int tiley) {

	}

	public void eventChangeScene(int mapID, int newLayer, int ceilX, int ceilY) {

	}

	/**
	 * 设置动作状态
	 * 
	 * @param state
	 *            状态
	 */
	public void setActionState(short state) {
		this.actionState = state;
	}

	/**
	 * 获取动作状态
	 * 
	 * @return 动作状态
	 */
	public short getActionState() {
		return actionState;
	}

	/**
	 * 生成物品编号
	 * 
	 * @return 物品编号
	 */
	public int generateGoodsId() {
		return 0;
	}

	/**
	 * 创建邮件对象
	 * 
	 * @return 邮件对象
	 */
	public IEmail generateEmail() {
		return null;
	}

	/**
	 * 发送一封系统邮件
	 * 
	 * @param email
	 *            系统邮件
	 */
	public void sendSystemEmail(IEmail email) {

	}

	/**
	 * 离开队伍
	 */
	public void outTeam() {

	}

	/**
	 * 改变动作
	 * 
	 * @param roleId
	 *            角色
	 * @param actionId
	 *            动作编号
	 */
	public void changeAction(int roleId, byte actionId) {

	}

	/**
	 * 获取当前动作编号
	 * 
	 * @param roleId
	 *            角色编号
	 * @return 动作编号
	 */
	public short getActionId(int roleId) {
		return 0;
	}

	/**
	 * 根据NPC的编号获取NPC对象
	 * 
	 * @param npcId
	 *            NPC编号
	 * @return NPC对象
	 */
	public Role getNpc(int npcId) {
		return null;
	}

	/**
	 * 获取指定真实id的所有物品
	 * 
	 * @param goodsId
	 *            物品真实ID（GameGoods.getGoodsId()）
	 * @return 物品列表
	 */
	public List<AGoods> getAllGoodsByGoodsId(int goodsId) {
		return new ArrayList<AGoods>();
	}

	/**
	 * 获取指定真实id的第一个找到的物品
	 * 
	 * @param goodsId
	 *            物品真实ID（GameGoods.getGoodsId()）
	 * @return 物品
	 */
	public AGoods getGoodsByGoodsId(int goodsId) {
		return null;
	}

	@Override
	public void eventKillRole(Role deathRole) {
		deathRole.eventKilled(this);
	}

	@Override
	public void eventMissionCommit(int missionId) {
	}

	@Override
	public void eventGoodsEquip(AGoods goods) {

	}

	@Override
	public void eventUseGoods(AGoods goods) {

	}

	@Override
	public void eventUpgradeLevel(String reason) {

	}

	@Override
	public void eventUpgradeRealm() {

	}

	@Override
	public void eventDuplicateSuccess(int duplicate) {

	}

	@Override
	public void eventDuplicateFail(int duplicate) {

	}

	@Override
	public void eventRecharge(int count) {

	}

	@Override
	public void eventUpgradeVipLevel() {

	}

	@Override
	public void eventGoodsToPack(AGoods goods, int count) {

	}

	@Override
	public void eventMissionDeleted(int mission) {

	}

	@Override
	public void eventMissionAccept(int mission) {

	}

	@Override
	public void eventOpenIdentity(byte identity) {

	}

	@Override
	public void eventMoneyChange(int moneyId) {

	}

	@Override
	public void eventGoodsToStore(AGoods goods) {

	}

	@Override
	public void eventGoodsToShop(AGoods goods) {

	}

	@Override
	public void eventGoodsDeleted(AGoods goods) {

	}

	@Override
	public void eventSectCreate(IGameSect sect) {
	}

	@Override
	public void eventSectJoin(IGameSect sect) {
	}

	public void eventSectJoin(long sectId) {

	}

	@Override
	public void eventSectQuit() {
	}

	@Override
	public void eventMonstersKill(List<Role> monsters) {

	}

	@Override
	public void eventUnequipGoods(AGoods goods) {

	}

	@Override
	public void eventKilled(Role killer) {

	}

	/**
	 * 获得物品的主类别
	 * 
	 * @return 物品主类别
	 */
	public short getGoodsMain() {
		return goodsMain;
	}

	/**
	 * 设置物品的主类别
	 * 
	 * @param main
	 *            主类别
	 */
	public void setGoodsMain(short main) {
		this.goodsMain = main;
	}

	/**
	 * 技能或心法升级
	 * 
	 * @param skillId
	 */
	public void eventSkillUpgrade(int skillId) {

	}

	/**
	 * 进入场景
	 * 
	 * @param sceneId
	 */
	public void eventSceneEnter(int sceneId) {

	}

	@Override
	public void eventSectBattleSuccess() {

	}

	/**
	 * 宗战胜利事件
	 */
	public void eventSectBattleFail() {

	}

	/**
	 * 角色上线事件
	 */
	public void eventToOnline() {

	}

	/**
	 * 角色离线事件
	 */
	public void eventToOffline() {

	}

	/**
	 * 清除人物头顶的宗门信息
	 */
	public void clearTopSectInfo() {
	}

	// 2013-2-18
	public void changeGoodsIndex(int id, byte index, int flag) {

	}

	/**
	 * 判断目标是否可攻击
	 */
	public boolean isHitTarget(Role target) {
		return false;
	}

	public Role[] getSceneUsers() {
		return null;
	}

	/**
	 * 通过物品真实ID，删除count个
	 * 
	 * @param realId
	 *            真实ID
	 * @param count
	 *            数量
	 * @param connectId
	 * @return
	 */
	public boolean deleteGoodsByRealId(int realId, int count, String operate, String project, String orderFormId, int connectId) {
		return false;
	}

	/**
	 * 修改角色随身商店令牌有效期（剩余时间-秒）
	 * 
	 * @param second
	 *            变化量（秒）
	 */
	public void changeShopTimer(int second) {

	}

	/**
	 * 获取摆摊标语
	 */
	public String getShopSlogan() {
		return null;
	}

	public void setFirstLogin(boolean firstLogin) {

	}

	public void playAnimation(String strengthenani, byte strengthensuc, byte strengthenplaycount) {

	}

	/**
	 * 共享任务怪物
	 * 
	 * @param monster
	 *            被杀死的怪物
	 */
	public void commonMonster(Role monster) {

	}

	/**
	 * 是否屏蔽其他玩家
	 * 
	 * @return true只显示同一个队伍的玩家，false显示视野内的玩家
	 */
	public boolean isHideOther() {
		return isHideOther;
	}

	/**
	 * 设置屏蔽其他玩家
	 * 
	 * @param isHideOther
	 *            true只显示同一个队伍的玩家，false显示视野内的玩家
	 */
	public void setHideOther(boolean isHideOther) {
		this.isHideOther = isHideOther;
	}

	/**
	 * 修改角色的魔法值
	 * 
	 * @param offset
	 */
	public void updateCurrMp(int offset) {

	}

	public String getFeatureString() {
		// 产品组
		DecimalFormat df;
		StringBuilder sb = new StringBuilder();
		String productTeamFor = "000";
		df = new DecimalFormat(productTeamFor);
		int productTeam = 1;// 产品组 3位
		sb.append(df.format(productTeam));

		int clientVersion = getClientVersion();// 平台 1位
		sb.append(clientVersion);

		String format = "00";
		df = new DecimalFormat(format);
		int language = 1;// 语言
		sb.append(df.format(language));

		String prodectFormat = "00";
		df = new DecimalFormat(prodectFormat);
		int prodectId = getProductId();// 产品编码
		sb.append(df.format(prodectId));

		String channelFormat = "00000000";
		df = new DecimalFormat(channelFormat);
		int channelId = getChannelId();// 渠道 8位
		sb.append(df.format(channelId));

		df = new DecimalFormat(format);// 预留
		int reserved = 0;
		sb.append(df.format(reserved));
		return sb.toString();
	}

	public List<AGoods> getMoneys() {
		return NULL_GOODS;
	}

	public int getKillingValue() {
		return killingValue;
	}

	public void setKillingValue(int killingValue) {
		this.killingValue = killingValue;
	}

	public void addKillingValue(int killingValue) {
		this.killingValue += killingValue;
		if (this.killingValue < 0) {
			this.killingValue = 0;
		}
	}

	public long getUpdateKillingValue() {
		return updateKillingValue;
	}

	public void setUpdateKillingValue(long updateKillingValue) {
		this.updateKillingValue = updateKillingValue;
	}

	public int getFighting() {
		return fighting;
	}

	public void setFighting(int fighting) {
		this.fighting = fighting;
		// 更新角色战斗力
		RoleMini rm = RoleManager.getRoleMini(id);
		if (rm != null) {
			rm.setFightPower(fighting);
		}
	}

	public void eventTotalFightChange() {

	}

	/**
	 * 获取总战斗力
	 * 
	 * @return
	 */
	public int getTotalFight() {
		return fighting;
	}

	public int getMaxFighting() {
		return maxFighting;
	}

	public void setMaxFighting(int maxFighting) {
		this.maxFighting = maxFighting;
	}

	// public void sendNotice(String content) {
	//
	// }

	/**
	 * 禁言
	 * 
	 * @param time
	 *            禁言时间（分钟）
	 */
	public void forbidChat(int time, String reason) {

	}

	public void forbidChat(long time) {

	}

	public long getForbidChatTime() {
		return 0;

	}

	/**
	 * 角色是否被禁言
	 * 
	 * @return true被禁言，false未被禁言
	 */
	public boolean isForbidChat() {
		return false;
	}

	/**
	 * 脱离卡死
	 */
	public void toFree() {

	}

	/** 关闭窗口 */
	public void closeWindows() {
	}

	/** 获取怪物归属角色 */
	public Role getBelong() {
		return null;
	}

	/** 获取古魔死亡后能获得物品的非归属角色 */
	public List<Role> getGumoBelong() {
		return null;
	}

	public int getSectSignYearValue() {
		return sectSignYearValue;
	}

	public void setSectSignYearValue(int sectSignYearValue) {
		this.sectSignYearValue = sectSignYearValue;
	}

	public int getSectSignDayOfYear() {
		return sectSignDayOfYear;
	}

	public void setSectSignDayOfYear(int sectSignMonthValue) {
		this.sectSignDayOfYear = sectSignMonthValue;
	}

	/** 清除怪物的物品归属列表 */
	public void clearBelongs() {

	}

	public void updateNpcName(int npcCate) {

	}

	/** 是否在宗门圣地 */
	public boolean inShengDi() {
		return false;
	}

	public void kickedout(String noticeSpeed) {

	}

	public long getLevelupFirstTime() {
		return levelupFristTime;
	}

	public void setLevelupFirstTime(long levelupFristTime) {
		this.levelupFristTime = levelupFristTime;
	}

	// 是否在VIP体验期
	public boolean isVipTest() {

		boolean isVaildVipTest = true;
		// 体验VIP卡等级低于VIP等级=体验过程
		if (virtualVIPLevel <= vipLevel) {
			isVaildVipTest = false;
		} else if (virtualVIPexpiredTime == 0 || virtualVIPexpiredTime < System.currentTimeMillis()) {
			isVaildVipTest = false;
		}
		return isVaildVipTest;
	}

	public short getVirtualVIPLevel() {
		return virtualVIPLevel;
	}

	public void setVirtualVIPLevel(short virtualVIPLevel) {
		this.virtualVIPLevel = virtualVIPLevel;
	}

	public long getVirtualVIPexpiredTime() {
		return virtualVIPexpiredTime;
	}

	public void setVirtualVIPexpiredTime(long virtualVIPexpiredTime) {
		this.virtualVIPexpiredTime = virtualVIPexpiredTime;
	}

	public RoleGmActivity getRoleGmActivity(int id) {
		return null;
	}

	public void joinRoleGmActivity(int id) {

	}

	public RoleGmActivity getAndAddRoleGmActivity(int gmaId) {
		return null;
	}

	public void addMissionGoods(int mission, int goods) {
	}

	public short getEmailIndex(long id) {
		return 0;
	}

	public void changeToVirtualVip() {

	}

	public void forceDeath() {
		ILayer layerObj = getLayerObject();
		if (layerObj != null) {
			layerObj.execute(new ForceDeath());
		}
	}

	class ForceDeath implements Runnable {
		@Override
		public void run() {
			List<Role> temp = visiableRoles;
			if (temp != null && temp.size() > 0) {
				int rsize = temp.size();
				Role tmpRole = null;
				for (int ri = 0; ri < rsize; ri++) {
					tmpRole = temp.get(ri);
					if (tmpRole.isLogined()) {
						tmpRole.outViewForced(Role.this);
					}
				}
			}
			hp = 0;
		}

	}

	public boolean addTempFriend(Role session) {
		return false;
	}

	public GameOption getGameOption(short hidechat1) {
		return null;
	}

	public int getHasMonsterMap(int duplicateId, int layer) {
		return 0;
	}

	/**
	 * 该物品归属的背包区是否已满
	 */
	public boolean isCanFillGoods(AGoods goods) {
		return false;
	}

	/**
	 * 该物品归属的背包区是否已满
	 */
	public boolean isCanFillGoods(int goodsId, int count) {
		return false;
	}

	/**
	 * 获取免疫的五行值
	 */
	public short getImmuneElement() {
		return immuneElement;
	}

	/**
	 * 移除免疫BUF效果
	 */
	public void removeBufImmune(int bufId) {
		if (bufToImmune != null && bufToImmune.size() > 0) {
			bufToImmune.remove(bufId);
			// 重置免疫状态
			immuneElement = 0;
			Collection<Short> values = bufToImmune.values();
			for (short value : values) {
				immuneElement |= value;
			}
		}
	}

	/**
	 * 添加免疫BUF
	 */
	public short addBufImmune(int bufId, short value) {
		if (bufToImmune == null) {
			bufToImmune = new HashMap<Integer, Short>();
		}
		bufToImmune.put(bufId, value);
		// 如果免疫状态无该属性 则修改免疫状态
		if ((immuneElement & value) < value) {
			immuneElement |= value;
		}
		return value;
	}

	public boolean isPassable() {
		return false;
	}

	public boolean isShowChat() {
		return true;
	}

	/**
	 * 获得角色基础生命与装备加成、其他属性加成生命值的和值
	 * 
	 * @return 基础生命与装备加成生命值的和值
	 */
	public int getComplexHp() {
		return hpBase + hpEquip + hpSkillPro + hpRealm + hpMedicine + hpPet;
	}

	public int getComplexMp() {
		return baseMp + equipMp;
	}

	/**
	 * 获取角色体术和体术加成的值
	 * 
	 * @return
	 */
	public int getComplexForce() {
		return baseForce + forceTrain + forceEquip;
	}

	/**
	 * 获取角色法术和法术加成的值
	 * 
	 * @return
	 */
	public int getComplexMagic() {
		return baseMagic + magicTrain + magicEquip;
	}

	/**
	 * 获取神识和神识加成的值
	 * 
	 * @return
	 */
	public int getComplexSprit() {
		return baseSprit + spritTrain + spritEquip;
	}

	/**
	 * 设置角色经验值
	 * 
	 * @param experience
	 *            经验值
	 */
	public void setExperience(int experience) {
		this.experience = experience;
	}

	/**
	 * 获取指定区域的物品
	 * 
	 * @param zone
	 *            ui区
	 * @return
	 */
	public List<AGoods> getGoodsByZone(short zone) {
		return null;
	}

	public Collection<StoreHouse> getStoreHouese() {
		// TODO Auto-generated method stub
		return null;
	}

	public void checkMission(int flag, int id) {
		// TODO Auto-generated method stub

	}

	public void equipGoods(AGoods clothes, int i) {
		// TODO Auto-generated method stub

	}

	public RoleDayData getDayData() {
		return null;
	}

	public int getReboundPercent() {
		return reboundPercent;
	}

	public int changeReboundPercent(int reboundPercent) {
		this.reboundPercent = reboundPercent;
		return reboundPercent;
	}

	public void enterLogicQueue(ILogicRunnable run) {

	}

	// --------------------战斗属性--------------
	/**
	 * @return 当前的HP
	 */
	public int getHp() {
		return this.hp;
	}

	/**
	 * 设置角色的HP
	 * 
	 * @param hp
	 *            角色当前的HP
	 */
	public void setHp(int hp) {
		this.hp = hp;
		if (this.hp < 0) {
			this.hp = 0;
		}
	}

	/**
	 * 气血回复能力变更
	 * 
	 * @param offset
	 *            变化量
	 * @return 气血回复能力
	 */
	public int changeHp(int offset) {
		if (isDeath()) {
			return hp;
		}
		hp += offset;
		if (hp < 0) {
			hp = 0;
		}
		if (hp > getHpSumMax()) {
			hp = getHpSumMax();
		}
		dirtyHp = true;
		return hp;
	}

	/**
	 * 获取角色的基础HP，随级别成长的基本HP
	 * 
	 * @return short 角色自身HP
	 */
	public int getHpBase() {
		return hpBase;
	}

	/**
	 * 设置角色的基础HP
	 * 
	 * @param baseHP
	 *            新的基础HP
	 */
	public void setHpBase(int baseHp) {
		int oldBaseHp = this.hpBase;
		this.hpBase = baseHp;
		// 基础生命改变影响最大生命
		changeHpSumMax(baseHp - oldBaseHp);
	}

	/**
	 * 修改基础生命的值
	 * 
	 * @param value
	 * @return 变化后的值
	 */
	public int changeHpBase(int value) {
		this.hpBase += value;
		// 基础生命改变影响最大生命
		changeHpSumMax(value);
		return this.hpBase;
	}

	/**
	 * 获取装备对HP上限的影响值
	 * 
	 * @return 装备对HP上限的影响值
	 */
	public int getHpEquip() {
		return hpEquip;
	}

	/**
	 * 修改装备对HP上限的影响值
	 * 
	 * @param value
	 *            改变量
	 * @return 变化后的值
	 */
	public int changeHpEquip(int value) {
		this.hpEquip += value;
		// 装备对生命加成变化导致综合生命变化
		changeHpSumMax(value);
		return this.hpEquip;
	}

	/**
	 * 获取被动技能对HP的加成值
	 * 
	 * @return
	 */
	public int getHpSkillPro() {
		return hpSkillPro;
	}

	/**
	 * 设置被动技能对最大生命的影响
	 * 
	 * @param value
	 */
	public void setHpSkillPro(int value) {
		int old = this.hpSkillPro;
		this.hpSkillPro = value;
		changeHpSumMax(value - old);
	}

	/**
	 * 修改角色被动技能对HP的加成值
	 * 
	 * @param value
	 * @return 变化后的值
	 */
	public int changeHpSkillPro(int value) {
		this.hpSkillPro += value;
		// 其他属性影响生命导致综合生命变化
		changeHpSumMax(value);
		return this.hpSkillPro;
	}

	/**
	 * 获取境界穴位对HP的加成值
	 * 
	 * @return
	 */
	public int getHpRealmPro() {
		return hpRealm;
	}

	/**
	 * 设置境界穴位对最大生命的影响
	 * 
	 * @param value
	 */
	public void setHpRealmPro(int value) {
		int old = this.hpRealm;
		this.hpRealm = value;
		changeHpSumMax(value - old);
	}

	/**
	 * 修改境界穴位对HP的加成值
	 * 
	 * @param value
	 * @return 变化后的值
	 */
	public int changeHpRealmPro(int value) {
		this.hpRealm += value;
		// 其他属性影响生命导致综合生命变化
		changeHpSumMax(value);
		return this.hpRealm;
	}

	/**
	 * 获取丹药对HP的加成值
	 * 
	 * @return
	 */
	public int getHpMedicinePro() {
		return hpMedicine;
	}

	/**
	 * 设置丹药对最大生命的影响
	 * 
	 * @param value
	 */
	public void setHpMedicinePro(int value) {
		int old = this.hpMedicine;
		this.hpMedicine = value;
		changeHpSumMax(value - old);
	}

	/**
	 * 修改丹药对HP的加成值
	 * 
	 * @param value
	 * @return 变化后的值
	 */
	public int changeHpMedicinePro(int value) {
		this.hpMedicine += value;
		// 其他属性影响生命导致综合生命变化
		changeHpSumMax(value);
		return this.hpMedicine;
	}

	/**
	 * 修改灵兽对HP的加成值
	 * 
	 * @param value
	 * @return
	 */
	public int changeHpPet(int value) {
		this.hpPet += value;
		// 灵兽影响生命导致综合生命变化
		changeHpSumMax(value);
		return this.hpPet;
	}

	/**
	 * 获取最大HP
	 * 
	 * @return 最大HP
	 */
	public int getHpSumMax() {
		return hpSumMax;
	}

	/**
	 * 设置最大血量
	 * 
	 * @param maxHp
	 */
	public void setHpSumMax(int maxHp) {
		this.hpSumMax = maxHp;
		if (hpSumMax < 0) {
			hpSumMax = 1;
		}
	}

	/**
	 * 更改角色血量最大值
	 * 
	 * @param value
	 * @return
	 */
	public int changeHpSumMax(int value) {
		int old = hpSumMax;
		this.hpSumMax += value;
		int offset = hpBase + hpEquip + hpSkillPro + hpRealm + hpMedicine;
		if (hpSumMax < 0) {
			hpSumMax = offset;
		}
		if (this.hp > hpSumMax || this.hp == old) {
			changeHp(hpSumMax - this.hp);
		}
		return hpSumMax - old;
	}

	/**
	 * 获取基础攻击
	 * 
	 * @return
	 */
	public int getBaseAttack() {
		return baseAttack;
	}

	/**
	 * 设置基础攻击
	 * 
	 * @param baseAttack
	 */
	public void setBaseAttack(int baseAttack) {
		int oldBaseAttack = this.baseAttack;
		this.baseAttack = baseAttack;
		// 基础攻击变化引起综合攻击变化
		changeSumAttack(baseAttack - oldBaseAttack);
	}

	/**
	 * 修改基础攻击
	 * 
	 * @param value
	 * @return 变化后的值
	 */
	public int changeBaseAttack(int value) {
		this.baseAttack += value;
		// 基础攻击变化引起综合攻击变化
		changeSumAttack(value);
		return this.baseAttack;
	}

	/**
	 * 获取装备对攻击的影响值
	 * 
	 * @return 装备对攻击的影响值
	 */
	public int getAttackEquip() {
		return attackEquip;
	}

	/**
	 * 设置装备对攻击的影响值
	 * 
	 * @param attack
	 * @return 改变后的值
	 */
	public int changeAttackEquip(int value) {
		this.attackEquip += value;
		// 装备对攻击的加成影响综合攻击
		changeSumAttack(value);
		return this.attackEquip;
	}

	/**
	 * 获取被动技能对攻击的增幅值
	 * 
	 * @return
	 */
	public int getAttackSkillPro() {
		return this.attackSkillPro;
	}

	public void setAttackSkillPro(int value) {
		int old = this.attackSkillPro;
		this.attackSkillPro = value;
		changeSumAttack(value - old);
	}

	/**
	 * 修改被动技能对攻击的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeAttackSkillPro(int value) {
		this.attackSkillPro += value;
		// 其他属性对攻击的加成影响综合攻击
		changeSumAttack(value);
		return this.attackSkillPro;
	}

	/**
	 * 修改灵兽对攻击的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeAttackPet(int value) {
		this.attackPet += value;
		// 其他属性对攻击的加成影响综合攻击
		changeSumAttack(value);
		return this.attackPet;
	}

	/**
	 * 修改境界穴位对攻击的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeAttackRealm(int value) {
		this.attackRealm += value;
		// 其他属性对攻击的加成影响综合攻击
		changeSumAttack(value);
		return this.attackRealm;
	}

	/**
	 * 修改丹药对攻击的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeAttackMedicine(int value) {
		this.attackMedicine += value;
		// 其他属性对攻击的加成影响综合攻击
		changeSumAttack(value);
		return this.attackMedicine;
	}

	/**
	 * 获取综合攻击力
	 * 
	 * @return 综合攻击力
	 */
	public int getSumAttack() {
		return this.sumAttack;
	}

	/**
	 * 更改角色综合攻击
	 * 
	 * @param value
	 * 
	 * @return 改变后的值
	 */
	public int changeSumAttack(int value) {
		this.sumAttack += value;
		if (this.sumAttack < 0) {
			this.sumAttack = 0;
		}
		return this.sumAttack;
	}

	/**
	 * 获取角色基础攻击力与装备加成、其他属性加成后的攻击力的和值
	 * 
	 * @return 基础攻击力与装备加成后的攻击力的和值
	 */
	public int getComplexAttack() {
		return baseAttack + attackEquip + attackSkillPro + attackPet + attackRealm + attackMedicine;
	}

	/**
	 * 获取基础防御
	 * 
	 * @return
	 */
	public int getBaseDefence() {
		return baseDefence;
	}

	/**
	 * 设置基础防御
	 * 
	 * @param baseDefence
	 */
	public void setBaseDefence(int baseDefence) {
		int oldBaseDefence = this.baseDefence;
		this.baseDefence = baseDefence;
		// 基础防御变化导致综合防御变化
		changeSumDefence(baseDefence - oldBaseDefence);
	}

	/**
	 * 修改基础防御
	 * 
	 * @param value
	 * @return 改变后的值
	 */
	public int changeBaseDefence(int value) {
		this.baseDefence += value;
		// 基础防御变化导致综合防御变化
		changeSumDefence(value);
		return this.baseDefence;
	}

	/**
	 * 获取装备对角色防御综合值的增幅值
	 * 
	 * @return 装备对角色防御综合值的增幅值
	 */
	public int getDefenceEquip() {
		return defenceEquip;
	}

	/**
	 * 修改装备对防御的增幅值
	 * 
	 * @param value
	 * @return 改变后的值
	 */
	public int changeDefenceEquip(int value) {
		this.defenceEquip += value;
		// 装备对防御加成的变化导致综合防御变化
		changeSumDefence(value);
		return this.defenceEquip;
	}

	/**
	 * 获取被动技能对防御的增幅值
	 * 
	 * @return
	 */
	public int getDefenceSkillPro() {
		return this.defenceSkillPro;
	}

	public void setDefenceSkillPro(int value) {
		int old = this.defenceSkillPro;
		this.defenceSkillPro = value;
		changeSumDefence(value - old);
	}

	/**
	 * 修改被动技能对防御的增幅值
	 * 
	 * @param value
	 * @return 修改后的值
	 */
	public int changeDefenceSkillPro(int value) {
		this.defenceSkillPro += value;
		// 其他属性对防御加成的变化导致综合防御变化
		changeSumDefence(value);
		return this.defenceSkillPro;
	}

	/**
	 * 修改灵兽对防御的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDefencePet(int value) {
		this.defencePet += value;
		// 其他属性对防御加成的变化导致综合防御变化
		changeSumDefence(value);
		return this.defencePet;
	}

	/**
	 * 修改境界穴位对防御的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDefenceRealm(int value) {
		this.defenceRealm += value;
		// 其他属性对防御加成的变化导致综合防御变化
		changeSumDefence(value);
		return this.defenceRealm;
	}

	public int changeDefenceMedicine(int value) {
		this.defenceMedicine += value;
		// 其他属性对防御加成的变化导致综合防御变化
		changeSumDefence(value);
		return this.defenceMedicine;
	}

	/**
	 * 获取综合防御值，在综合防护下限和综合防御上限之间取值
	 * 
	 * @return 综合防御值
	 */
	public int getSumDefence() {
		return sumDefence;
	}

	/**
	 * 修改综合防御
	 * 
	 * @param value
	 * @return 改变后的值
	 */
	public int changeSumDefence(int value) {
		this.sumDefence += value;
		if (this.sumDefence < 0) {
			this.sumDefence = 0;
		}
		return this.sumDefence;
	}

	/**
	 * 获得角色基础防御和装备加成、其他属性加成防御的和值
	 * 
	 * @return 基础防御和装备加成防御的和值
	 */
	public int getComplexDefence() {
		return baseDefence + defenceEquip + defenceSkillPro + defencePet + defenceRealm + defenceMedicine;
	}

	/**
	 * 获取基础命中
	 */
	public int getAttackChance() {
		return this.attackChance;
	}

	/**
	 * 设置基础命中
	 * 
	 * @param attackChance
	 */
	public void setAttackChance(int attackChance) {
		int old = this.attackChance;
		this.attackChance = attackChance;

		changeSumAttackChance(attackChance - old);
	}

	/**
	 * 修改基础命中
	 * 
	 * @param value
	 */
	public int changeAttackChance(int value) {
		this.attackChance += value;
		// 基础命中影响综合命中
		changeSumAttackChance(value);
		return value;
	}

	/**
	 * 获取装备对命中的增幅值
	 * 
	 * @return
	 */
	public int getAttackChanceEquip() {
		return this.attackChanceEquip;
	}

	/**
	 * 修改装备对命中的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeAttackChanceEquip(int value) {
		this.attackChanceEquip += value;
		changeSumAttackChance(value);
		return this.attackChanceEquip;
	}

	/**
	 * 修改灵兽对命中的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeAttackChancePet(int value) {
		this.attackChancePet += value;
		changeSumAttackChance(value);
		return this.attackChancePet;
	}

	/**
	 * 修改境界穴位对命中的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeAttackChanceRealm(int value) {
		this.attackChanceRealm += value;
		changeSumAttackChance(value);
		return this.attackChanceRealm;
	}

	/**
	 * 修改丹药对命中的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeAttackChanceMedicine(int value) {
		this.attackChanceMedicine += value;
		changeSumAttackChance(value);
		return this.attackChanceMedicine;
	}

	/**
	 * 获取综合命中值
	 */
	public int getSumAttackChance() {
		return this.sumAttackChance;
	}

	/**
	 * 修改综合命中值
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumAttackChance(int value) {
		this.sumAttackChance += value;
		if (this.sumAttackChance < 0) {
			this.sumAttackChance = 0;
		}
		return this.sumAttackChance;
	}

	/**
	 * 获取基础命中、装备对命中增幅值的和
	 * 
	 * @return
	 */
	public int getComplexAttackChance() {
		return attackChance + attackChanceEquip + attackChancePet + attackChanceRealm + attackChanceMedicine;
	}

	/**
	 * 获取基础闪避值
	 */
	public int getDuckChance() {
		return this.duckChance;
	}

	/**
	 * 设置基础闪避值
	 * 
	 * @param duckChance
	 */
	public void setDuckChance(int duckChance) {
		int old = this.duckChance;
		this.duckChance = duckChance;

		changeSumDuckChance(duckChance - old);
	}

	/**
	 * 修改基础闪避值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDuckChance(int value) {
		this.duckChance += value;
		changeSumDuckChance(value);
		return this.duckChance;
	}

	/**
	 * 获取装备对闪避的增幅值
	 * 
	 * @return
	 */
	public int getDuckChanceEquip() {
		return this.duckChanceEquip;
	}

	/**
	 * 修改装备对闪避的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDuckChanceEquip(int value) {
		this.duckChanceEquip += value;
		changeSumDuckChance(value);
		return this.duckChanceEquip;
	}

	/**
	 * 修改灵兽对闪避的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDuckChancePet(int value) {
		this.duckChancePet += value;
		changeSumDuckChance(value);
		return this.duckChancePet;
	}

	/**
	 * 修改境界穴位对闪避的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDuckChanceRealm(int value) {
		this.duckChanceRealm += value;
		changeSumDuckChance(value);
		return this.duckChanceRealm;
	}

	/**
	 * 修改丹药对闪避的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDuckChanceMedicine(int value) {
		this.duckChanceMedicine += value;
		changeSumDuckChance(value);
		return this.duckChanceMedicine;
	}

	/**
	 * 获取综合闪避值
	 */
	public int getSumDuckChance() {
		return this.sumDuckChance;
	}

	/**
	 * 修改综合闪避值
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumDuckChance(int value) {
		this.sumDuckChance += value;
		if (this.sumDuckChance < 0) {
			this.sumDuckChance = 0;
		}
		return this.sumDuckChance;
	}

	/**
	 * 获取基础闪避、装备对闪避的增幅的和
	 * 
	 * @return
	 */
	public int getComplexDuckChance() {
		return duckChance + duckChanceEquip + duckChancePet + duckChanceRealm + duckChanceMedicine;
	}

	/**
	 * 获取基础暴击值
	 * 
	 * @return
	 */
	public int getCruel() {
		return this.cruel;
	}

	/**
	 * 设置基础暴击值
	 * 
	 * @param cruel
	 */
	public void setCruel(int cruel) {
		int old = this.cruel;
		this.cruel = cruel;
		changeSumCruel(cruel - old);
	}

	/**
	 * 修改基础暴击值
	 * 
	 * @param value
	 * @return
	 */
	public int changeCruel(int value) {
		this.cruel += value;
		changeSumCruel(value);
		return this.cruel;
	}

	/**
	 * 获取装备对暴击的增幅值
	 * 
	 * @return
	 */
	public int getCruelEquip() {
		return this.cruelEquip;
	}

	/**
	 * 修改装备对暴击的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeCruelEquip(int value) {
		this.cruelEquip += value;
		changeSumCruel(value);
		return this.cruelEquip;
	}

	/**
	 * 修改灵兽对暴击的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeCruelPet(int value) {
		this.cruelPet += value;
		changeSumCruel(value);
		return this.cruelPet;
	}

	/**
	 * 修改境界穴位对暴击的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeCruelRealm(int value) {
		this.cruelRealm += value;
		changeSumCruel(value);
		return this.cruelRealm;
	}

	/**
	 * 修改丹药对暴击的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeCruelMedicine(int value) {
		this.cruelMedicine += value;
		changeSumCruel(value);
		return this.cruelMedicine;
	}

	/**
	 * 获取综合暴击值
	 * 
	 * @return
	 */
	public int getSumCruel() {
		return this.sumCruel;
	}

	/**
	 * 修改综合暴击值
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumCruel(int value) {
		this.sumCruel += value;
		if (this.sumCruel < 0) {
			this.sumCruel = 0;
		}
		return this.sumCruel;
	}

	/**
	 * 获取基础暴击、装备对暴击的增幅值的和
	 * 
	 * @return
	 */
	public int getComplexCruel() {
		return cruel + cruelEquip + cruelPet + cruelRealm + cruelMedicine;
	}

	/**
	 * 获取基础韧性
	 * 
	 * @return
	 */
	public int getTenacity() {
		return this.tenacity;
	}

	/**
	 * 设置基础韧性
	 * 
	 * @param tenacity
	 */
	public void setTenacity(int tenacity) {
		int old = this.tenacity;
		this.tenacity = tenacity;
		changeSumTenacity(tenacity - old);
	}

	/**
	 * 修改基础韧性
	 * 
	 * @param value
	 * @return
	 */
	public int changeTenacity(int value) {
		this.tenacity += value;
		changeSumTenacity(value);
		return this.tenacity;
	}

	/**
	 * 获取装备对韧性的增幅值
	 * 
	 * @return
	 */
	public int getTenacityEquip() {
		return this.tenacityEquip;
	}

	/**
	 * 修改装备对韧性的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeTenacityEquip(int value) {
		this.tenacityEquip += value;
		changeSumTenacity(value);
		return this.tenacityEquip;
	}

	/**
	 * 修改灵兽对韧性的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeTenacityPet(int value) {
		this.tenacityPet += value;
		changeSumTenacity(value);
		return this.tenacityPet;
	}

	/**
	 * 修改境界穴位对韧性的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeTenacityRealm(int value) {
		this.tenacityRealm += value;
		changeSumTenacity(value);
		return this.tenacityRealm;
	}

	/**
	 * 修改丹药对韧性的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeTenacityMedicine(int value) {
		this.tenacityMedicine += value;
		changeSumTenacity(value);
		return this.tenacityMedicine;
	}

	/**
	 * 获取综合韧性值
	 * 
	 * @return
	 */
	public int getSumTenacity() {
		return this.sumTenacity;
	}

	/**
	 * 修改综合韧性值
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumTenacity(int value) {
		this.sumTenacity += value;
		if (this.sumTenacity < 0) {
			this.sumTenacity = 0;
		}
		return this.sumTenacity;
	}

	/**
	 * 获取基础韧性、装备对韧性的增幅值的和
	 * 
	 * @return
	 */
	public int getComplexTenacity() {
		return tenacity + tenacityEquip + tenacityPet + tenacityRealm + tenacityMedicine;
	}

	/**
	 * 获取基础破甲
	 * 
	 * @return
	 */
	public int getDestroy() {
		return this.destroy;
	}

	/**
	 * 设置基础破甲
	 * 
	 * @param destroy
	 */
	public void setDestroy(int destroy) {
		int old = this.destroy;
		this.destroy = destroy;
		changeSumDestroy(destroy - old);
	}

	/**
	 * 修改基础破甲
	 * 
	 * @param value
	 * @return
	 */
	public int changeDestroy(int value) {
		this.destroy += value;
		changeSumDestroy(value);
		return this.destroy;
	}

	/**
	 * 获取装备对破甲的增幅值
	 * 
	 * @return
	 */
	public int getDestroyEquip() {
		return this.destroyEquip;
	}

	/**
	 * 修改装备对破甲的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDestroyEquip(int value) {
		this.destroyEquip += value;
		changeSumDestroy(value);
		return this.destroyEquip;
	}

	/**
	 * 修改灵兽对破甲的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDestroyPet(int value) {
		this.destroyPet += value;
		changeSumDestroy(value);
		return this.destroyPet;
	}

	/**
	 * 修改境界穴位对破甲的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDestroyRealm(int value) {
		this.destroyRealm += value;
		changeSumDestroy(value);
		return this.destroyRealm;
	}

	/**
	 * 修改丹药对破甲的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeDestroyMedicine(int value) {
		this.destroyMedicine += value;
		changeSumDestroy(value);
		return this.destroyMedicine;
	}

	/**
	 * 获取综合破甲
	 * 
	 * @return
	 */
	public int getSumDestroy() {
		return this.sumDestroy;
	}

	/**
	 * 修改综合破甲
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumDestroy(int value) {
		this.sumDestroy += value;
		if (this.sumDestroy < 0) {
			this.sumDestroy = 0;
		}
		return this.sumDestroy;
	}

	/**
	 * 获取基础破甲、装备对破甲的增幅的和
	 * 
	 * @return
	 */
	public int getComplexDestroy() {
		return destroy + destroyEquip + destroyPet + destroyRealm + destroyMedicine;
	}

	/**
	 * 获取基础格挡
	 * 
	 * @return
	 */
	public int getFender() {
		return this.fender;
	}

	/**
	 * 设置基础格挡
	 * 
	 * @param fender
	 */
	public void setFender(int fender) {
		int old = this.fender;
		this.fender = fender;
		changeSumFender(fender - old);
	}

	/**
	 * 获取装备对格挡的增幅值
	 * 
	 * @return
	 */
	public int getFenderEquip() {
		return this.fenderEquip;
	}

	/**
	 * 修改装备对格挡的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeFenderEquip(int value) {
		this.fenderEquip += value;
		changeSumFender(value);
		return this.fenderEquip;
	}

	/**
	 * 修改灵兽对格挡的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeFenderPet(int value) {
		this.fenderPet += value;
		changeSumFender(value);
		return this.fenderPet;
	}

	/**
	 * 修改境界穴位对格挡的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeFenderRealm(int value) {
		this.fenderRealm += value;
		changeSumFender(value);
		return this.fenderRealm;
	}

	/**
	 * 修改丹药对格挡的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeFenderMedicine(int value) {
		this.fenderMedicine += value;
		changeSumFender(value);
		return this.fenderMedicine;
	}

	/**
	 * 获取综合格挡
	 * 
	 * @return
	 */
	public int getSumFender() {
		return this.sumFender;
	}

	/**
	 * 修改综合格挡
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumFender(int value) {
		this.sumFender += value;
		if (this.sumFender < 0) {
			this.sumFender = 0;
		}
		return this.sumFender;
	}

	/**
	 * 获取基础格挡、装备对格挡的增幅的和
	 * 
	 * @return
	 */
	public int getComplexFender() {
		return fender + fenderEquip + fenderPet + fenderRealm + fenderMedicine;
	}

	/**
	 * 获取基础吸血
	 * 
	 * @return
	 */
	public int getSuckHp() {
		return this.suckHp;
	}

	/**
	 * 设置基础吸血
	 * 
	 * @param suckHp
	 */
	public void setSuckHp(int suckHp) {
		int old = this.suckHp;
		this.suckHp = suckHp;
		changeSumSuckHp(suckHp - old);
	}

	/**
	 * 修改基础吸血
	 * 
	 * @param value
	 * @return
	 */
	public int changeSuckHp(int value) {
		this.suckHp += value;
		changeSumSuckHp(value);
		return this.suckHp;
	}

	/**
	 * 获取装备对吸血的增幅值
	 * 
	 * @return
	 */
	public int getSuckHpEquip() {
		return this.suckHpEquip;
	}

	/**
	 * 修改装备吸血的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeSuckHpEquip(int value) {
		this.suckHpEquip += value;
		changeSumSuckHp(value);
		return this.suckHpEquip;
	}

	/**
	 * 获取穴位对吸血的增幅值
	 * 
	 * @return
	 */
	public int getSuckHpMedicine() {
		return this.suckHpMedicine;
	}

	/**
	 * 修改穴位吸血的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeSuckHpMedicine(int value) {
		this.suckHpMedicine += value;
		changeSumSuckHp(value);
		return this.suckHpMedicine;
	}

	/**
	 * 获取综合吸血
	 * 
	 * @return
	 */
	public int getSumSuckHp() {
		return this.sumSuckHp;
	}

	/**
	 * 修改综合吸血
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumSuckHp(int value) {
		this.sumSuckHp += value;
		if (this.sumSuckHp < 0) {
			this.sumSuckHp = 0;
		}
		return this.sumSuckHp;
	}

	/**
	 * 获取基础吸血、装备对吸血的增幅的和
	 * 
	 * @return
	 */
	public int getComplexSuckHp() {
		return suckHp + suckHpEquip + suckHpMedicine;
	}

	/**
	 * 获取基础反弹
	 * 
	 * @return
	 */
	public int getRebound() {
		return this.rebound;
	}

	/**
	 * 设置基础反弹
	 * 
	 * @param rebound
	 */
	public void setRebound(int rebound) {
		int old = this.rebound;
		this.rebound = rebound;
		changeSumRebound(rebound - old);
	}

	/**
	 * 修改基础反弹
	 * 
	 * @param value
	 * @return
	 */
	public int changeRebound(int value) {
		this.rebound += value;
		changeSumRebound(value);
		return this.rebound;
	}

	/**
	 * 获取装备对反弹的增幅值
	 * 
	 * @return
	 */
	public int getReboundEquip() {
		return this.reboundEquip;
	}

	/**
	 * 修改装备对反弹的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeReboundEquip(int value) {
		this.reboundEquip += value;
		changeSumRebound(value);
		return this.reboundEquip;
	}

	/**
	 * 获取穴位对反弹的增幅值
	 * 
	 * @return
	 */
	public int getReboundMedicine() {
		return this.reboundMedicine;
	}

	/**
	 * 修改穴位对反弹的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeReboundMedicine(int value) {
		this.reboundMedicine += value;
		changeSumRebound(value);
		return this.reboundMedicine;
	}

	/**
	 * 获取综合反弹
	 * 
	 * @return
	 */
	public int getSumRebound() {
		return this.sumRebound;
	}

	/**
	 * 修改综合反弹
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumRebound(int value) {
		this.sumRebound += value;
		if (this.sumRebound < 0) {
			this.sumRebound = 0;
		}
		return this.sumRebound;
	}

	/**
	 * 获取基础反弹、装备对反弹的增幅的和
	 * 
	 * @return
	 */
	public int getComplexRebound() {
		return rebound + reboundEquip + reboundMedicine;
	}

	/**
	 * 获取基础破防
	 * 
	 * @return
	 */
	public int getReduceDefence() {
		return this.reduceDefence;
	}

	/**
	 * 设置基础破防
	 * 
	 * @param reduceDefence
	 */
	public void setReduceDefence(int reduceDefence) {
		int old = this.reduceDefence;
		this.reduceDefence = reduceDefence;
		changeSumReduceDefence(reduceDefence - old);
	}

	/**
	 * 修改基础破防
	 * 
	 * @param value
	 * @return
	 */
	public int changeReduceDefence(int value) {
		this.reduceDefence += value;
		changeSumReduceDefence(value);
		return this.reduceDefence;
	}

	/**
	 * 获取装备对破防的增幅值
	 * 
	 * @return
	 */
	public int getReduceDefenceEquip() {
		return this.reduceDefenceEquip;
	}

	/**
	 * 修改装备对破防的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeReduceDefenceEquip(int value) {
		this.reduceDefenceEquip += value;
		changeSumReduceDefence(value);
		return this.reduceDefenceEquip;
	}

	/**
	 * 修改境界穴位对破防的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeReduceDefenceRealm(int value) {
		this.reduceDefenceRealm += value;
		changeSumReduceDefence(value);
		return this.reduceDefenceRealm;
	}

	/**
	 * 修改丹药对破防的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeReduceDefenceMedicine(int value) {
		this.reduceDefenceMedicine += value;
		changeSumReduceDefence(value);
		return this.reduceDefenceMedicine;
	}

	/**
	 * 获取综合破防
	 * 
	 * @return
	 */
	public int getSumReduceDefence() {
		return this.sumReduceDefence;
	}

	/**
	 * 修改综合破防
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumReduceDefence(int value) {
		this.sumReduceDefence += value;
		if (this.sumReduceDefence < 0) {
			this.sumReduceDefence = 0;
		}
		return this.sumReduceDefence;
	}

	/**
	 * 获取基础破防、装备对破防的增幅值的和
	 * 
	 * @return
	 */
	public int getComplexReduceDefence() {
		return reduceDefence + reduceDefenceEquip + reduceDefenceRealm + reduceDefenceMedicine;
	}

	/**
	 * 获取基础抵抗
	 * 
	 * @return
	 */
	public int getResist() {
		return this.resist;
	}

	/**
	 * 设置基础抵抗
	 * 
	 * @param resist
	 */
	public void setResist(int resist) {
		int old = this.resist;
		this.resist = resist;
		changeSumResist(resist - old);
	}

	/**
	 * 修改基础抵抗
	 * 
	 * @param value
	 * @return
	 */
	public int changeResist(int value) {
		this.resist += value;
		changeSumResist(value);
		return resist;
	}

	/**
	 * 获取装备对抵抗的增幅值
	 * 
	 * @return
	 */
	public int getResistEquip() {
		return this.resistEquip;
	}

	/**
	 * 修改装备对抵抗的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeResistEquip(int value) {
		this.resistEquip += value;
		changeSumResist(value);
		return this.resistEquip;
	}

	/**
	 * 修改境界穴位对抵抗的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeResistRealm(int value) {
		this.resistRealm += value;
		changeSumResist(value);
		return this.resistRealm;
	}

	/**
	 * 修改丹药对抵抗的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeResistMedicine(int value) {
		this.resistMedicine += value;
		changeSumResist(value);
		return this.resistMedicine;
	}

	/**
	 * 获取综合抵抗
	 * 
	 * @return
	 */
	public int getSumResist() {
		return this.sumResist;
	}

	/**
	 * 修改综合抵抗
	 * 
	 * @param value
	 * @return
	 */
	public int changeSumResist(int value) {
		this.sumResist += value;
		if (this.sumResist < 0) {
			this.sumResist = 0;
		}
		return this.sumResist;
	}

	/**
	 * 获取基础抵抗。装备对抵抗的增幅值的和
	 * 
	 * @return
	 */
	public int getComplexResist() {
		return resist + resistEquip + resistRealm + resistMedicine;
	}

	// --------------培养属性-----------------
	/**
	 * 体术对生命的加成值
	 * 
	 * @param value
	 *            体术
	 * @return
	 */
	public final int getForceToHp(int value) {
		// HP = 体术 * 15
		return value * 15;
	}

	/**
	 * 法术对攻击的加成值
	 * 
	 * @param value
	 *            法术
	 * @return
	 */
	public final int getMagicToAttack(int value) {
		// 攻击 = 法术 *　5
		return value * 5;
	}

	/**
	 * 神识对防御的加成值
	 * 
	 * @param value
	 *            神识
	 * @return
	 */
	public final int getSpritToDefence(int value) {
		// 防御= 神识 *　3.5
		return (int) (value * 3.5);
	}

	/**
	 * 获取基础体术
	 * 
	 * @return short 体术
	 */
	public int getBaseForce() {
		return baseForce;
	}

	/**
	 * 设置基础体术
	 * 
	 * @param force
	 *            体术
	 */
	public void setBaseForce(int force) {
		int old = this.baseForce;
		this.baseForce = force;
		// 基础体术改变影响体术综合值改变
		changeSumForce(force - old);
	}

	/**
	 * 修改基础体术
	 * 
	 * @param value
	 * @return 修改后的值
	 */
	public int changeBaseForce(int value) {
		this.baseForce += value;
		// 基础体术改变影响体术综合值改变
		changeSumForce(value);
		return this.baseForce;
	}

	/**
	 * 获取装备对体术加成值
	 * 
	 * @return
	 */
	public int getForceEquip() {
		return this.forceEquip;
	}

	/**
	 * 修改装备体术加成值
	 * 
	 * @param value
	 * @return 修改后的值
	 */
	public int changeForceEquip(int value) {
		this.forceEquip += value;
		// 体术加成的改变影响综合体术的值
		changeSumForce(value);
		return this.forceEquip;
	}

	/**
	 * 获取培养对体术的增幅值
	 * 
	 * @return
	 */
	public int getForceTrain() {
		return this.forceTrain;
	}

	/**
	 * 设置培养对体术的增幅值
	 * 
	 * @param value
	 */
	public void setForceTrain(int value) {
		int old = this.forceTrain;
		this.forceTrain = value;
		changeSumForce(value - old);
	}

	/**
	 * 修改培养对体术的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeForceTrain(int value) {
		int old = this.forceTrain;
		this.forceTrain += value;
		if (this.forceTrain > this.supForce) {
			this.forceTrain = this.supForce;
			value = this.supForce - old;
		}
		changeSumForce(value);
		return this.forceTrain;
	}

	/**
	 * 获取综合体术
	 * 
	 * @return 综合体术
	 */
	public int getSumForce() {
		return sumForce;
	}

	/**
	 * 修改综合体术
	 * 
	 * @param sumForce
	 */
	public int changeSumForce(int sumForce) {
		this.sumForce += sumForce;
		int offset = baseForce + forceEquip + forceTrain;
		if (this.sumForce < 0) {
			this.sumForce = (short) offset;
		}
		// 体术改变影响生命
		changeHpBase(getForceToHp(sumForce));
		return this.sumForce;
	}

	/**
	 * 获取体术培养上限值
	 * 
	 * @return
	 */
	public int getSupForce() {
		return supForce;
	}

	/**
	 * 设置体术培养上限值
	 * 
	 * @param supForce
	 */
	public void setSupForce(int supForce) {
		this.supForce = supForce;
	}

	/**
	 * 获取基础法术
	 * 
	 * @return 法术
	 */
	public int getBaseMagic() {
		return baseMagic;
	}

	/**
	 * 设置基础法术
	 * 
	 * @param magic
	 */
	public void setBaseMagic(int magic) {
		int old = this.baseMagic;
		this.baseMagic = magic;
		// 基础法术的改变会影响综合法术值
		changeSumMagic(magic - old);
	}

	/**
	 * 修改基础法术值
	 * 
	 * @param value
	 * @return 修改后的值
	 */
	public int changeBaseMagic(int value) {
		this.baseMagic += value;
		// 基础法术的改变会影响综合法术值
		changeSumMagic(value);
		return this.baseMagic;
	}

	/**
	 * 获取装备对法术加成值
	 * 
	 * @return
	 */
	public int getMagicEquip() {
		return this.magicEquip;
	}

	/**
	 * 修改装备对法术加成值
	 * 
	 * @param value
	 */
	public int changeMagicAdd(int value) {
		this.magicEquip += value;
		// 法术加成的改变会影响综合法术值
		changeSumMagic(value);
		return this.magicEquip;
	}

	/**
	 * 获取培养对法术的增幅值
	 * 
	 * @return
	 */
	public int getMagicTrain() {
		return this.magicTrain;
	}

	/**
	 * 设置培养对法术的增幅值
	 * 
	 * @param value
	 */
	public void setMagicTrain(int value) {
		int old = this.magicTrain;
		this.magicTrain = value;
		// 法术加成的改变会影响综合法术值
		changeSumMagic(value - old);
	}

	/**
	 * 修改培养对法术的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeMagicTrain(int value) {
		int old = this.magicTrain;
		this.magicTrain += value;
		if (this.magicTrain > this.supMagic) {
			this.magicTrain = this.supMagic;
			value = this.supMagic - old;
		}
		// 法术加成的改变会影响综合法术值
		changeSumMagic(value);
		return this.magicTrain;
	}

	/**
	 * 获取综合法术
	 * 
	 * @return 综合法术
	 */
	public int getSumMagic() {
		return sumMagic;
	}

	/**
	 * 修改综合法术
	 * 
	 * @param sumMagic
	 * @return
	 */
	public int changeSumMagic(int sumMagic) {
		this.sumMagic += sumMagic;
		int offset = baseMagic + magicEquip + magicTrain;
		if (this.sumMagic < 0) {
			this.sumMagic = offset;
		}
		// 法术的改变会影响攻击
		changeBaseAttack(getMagicToAttack(sumMagic));
		return this.sumMagic;
	}

	/**
	 * 获取法术培养上限值
	 * 
	 * @return
	 */
	public int getSupMagic() {
		return supMagic;
	}

	/**
	 * 设置法术培养上限值
	 * 
	 * @param supMagic
	 */
	public void setSupMagic(int supMagic) {
		this.supMagic = supMagic;
	}

	/**
	 * 获取基础神识
	 * 
	 * @return short 神识
	 */
	public int getBaseSprit() {
		return baseSprit;
	}

	/**
	 * 设置基础神识
	 * 
	 * @param sprit
	 *            神识
	 */
	public void setBaseSprit(int sprit) {
		int old = this.baseSprit;
		this.baseSprit = sprit;
		// 基础神识的改变会影响综合神识值
		changeSumSprit(sprit - old);
	}

	/**
	 * 修改基础神识
	 * 
	 * @param value
	 * @return
	 */
	public int changeBaseSprit(int value) {
		this.baseSprit += value;
		// 基础神识的改变会影响综合神识值
		changeSumSprit(value);
		return this.baseSprit;
	}

	/**
	 * 获取装备对神识加成值
	 * 
	 * @return
	 */
	public int getSpritEquip() {
		return spritEquip;
	}

	/**
	 * 修改装备对神识加成值
	 * 
	 * @param spritAdd
	 */
	public int changeSpritEquip(int value) {
		this.spritEquip += value;
		// 基础神识的改变会影响综合神识值
		changeSumSprit(value);
		return this.spritEquip;
	}

	/**
	 * 获取培养对神识的增幅值
	 * 
	 * @return
	 */
	public int getSpritTrain() {
		return this.spritTrain;
	}

	/**
	 * 设置培养对神识的增幅值
	 * 
	 * @param value
	 */
	public void setSpritTrain(int value) {
		int old = this.spritTrain;
		this.spritTrain = value;
		// 神识的改变会影响综合神识值
		changeSumSprit(value - old);
	}

	/**
	 * 修改培养对神识的增幅值
	 * 
	 * @param value
	 * @return
	 */
	public int changeSpritTrain(int value) {
		int old = this.spritTrain;
		this.spritTrain += value;
		if (this.spritTrain > this.supSprit) {
			this.spritTrain = this.supSprit;
			value = this.supSprit - old;
		}
		// 神识的改变会影响综合神识值
		changeSumSprit(value);
		return this.spritTrain;
	}

	/**
	 * 获取综合神识
	 * 
	 * @return 综合神识
	 */
	public int getSumSprit() {
		return sumSprit;
	}

	/**
	 * 修改神识综合值
	 * 
	 * @param sumSprit
	 * @return
	 */
	public int changeSumSprit(int sumSprit) {
		int old = this.sumSprit;
		this.sumSprit += sumSprit;
		int offset = baseSprit + spritEquip + spritTrain;
		if (this.sumSprit < 0) {
			this.sumSprit = offset;
		}
		// 神识的改变会影响防御
		changeBaseDefence(getSpritToDefence(sumSprit));
		return sumSprit - old;
	}

	/**
	 * 获取神识培养上限值
	 * 
	 * @return
	 */
	public int getSupSprit() {
		return supSprit;
	}

	/**
	 * 设置神识培养上限值
	 * 
	 * @param supSprit
	 */
	public void setSupSprit(int supSprit) {
		this.supSprit = supSprit;
	}

	// ------------属性培养结束 -------------

	/**
	 * 取得当前释放技能列表中的可用位置
	 * 
	 * @return
	 */
	public int getCurrSkillIndex(int skillId) {
		int idx = -1;
		for (int i = 0; i < currSkill.length; i++) {
			if (currSkill[i] == null) {
				if (idx < 0) {
					idx = i;
				}
			} else if (currSkill[i].getSkillid() == skillId) {
				return -1;
			}
		}
		return idx;
	}

	/**
	 * 添加一个技能
	 * 
	 * @param skillId
	 *            技能ID
	 * @return 技能对象
	 */
	public ASkill addSkill(int skillId) {
		ASkill as = SkillManager.getInstance().getSkill(skillId);
		if (as != null) {
			ASkill skill = (ASkill) as.clone();
			skill.setRoleid(id);
			skill.setLevel((short) 1);
			skill.setFirer(this);
			skill.initSkill();
			roleSkill.put(skill.getSkillid(), skill);
			if (skill.getCate() == SkillCate.ED_SKILL) {
				skill.playSkill();
				skill.finish();
			}
			return skill;
		}
		return null;
	}

	/**
	 * 重置灵兽对属性的加成值
	 */
	public void resetPetToPro() {

	}

	public void prePlaySkill(int skillKey, byte dir, Role target) {

	}

	public int getFreeGridByBag(short uiZone) {
		return 0;
	}

	/**
	 * 设置宠物出战状态
	 * 
	 * @param state
	 */
	public void setPetState(byte state) {

	}

	public AGoods getOpenTreasuer(int treasureId) {
		return null;
	}

	public void flush2firer(Role pet) {

	}

	public void flush2other(Role pet) {

	}

	public void setSkeletons(String string) {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取角色当前坐骑对象
	 * 
	 * @return
	 */
	public AGoods getCurMountGoods() {
		return null;
	}

	/**
	 * 设置角色副本数据
	 * 
	 * @param pdd
	 */
	public void setPassDuplicateData(PassDupllicateData pdd) {

	}

	public boolean isDemoScene() {
		return false;
	}

	/**
	 * 设置竞技场次数下一次回复时间
	 * 
	 * @param time
	 */
	public void setArenaNextReplay(long time) {

	}

	/**
	 * 获取竞技场下一次回复时间
	 * 
	 * @return
	 */
	public long getArenaNextReplay() {
		return 0;
	}

	public PetArrayInfo getArrayInfo(short arrayId) {
		return null;
	}

	/**
	 * 获取一战到底的积分
	 * 
	 * @return
	 */
	public int getOneStandIntegral() {
		return 0;
	}

	public void resetIntegral(int integral) {
		setIntegral(integral);
	}

	/**
	 * 获取世界首领积分
	 */
	public int getLeaderIntegral() {
		return 0;
	}

	/** 获取宠物装备在角色身上的位置序号 */
	public byte getPetIndexByHost() {
		return 0;
	}

	/** 设置宠物装备在角色身上的位置序号 */
	public void setPetIndexByHost(byte index) {
	}

	/**
	 * 获取宠物的星级
	 * 
	 * @return
	 */
	public byte getStar() {
		return 0;
	}

	/**
	 * 重置装备对角色属性的影响
	 */
	public void resetEquipToProperties(boolean updateUI) {

	}

	/**
	 * 将宠物从出战状态切换
	 * 
	 * @param pet
	 */
	public void recoverPet(Role pet) {

	}

	/**
	 * 将宠物从出战状态切换
	 * 
	 * @param pet
	 */
	public void recoverPet() {

	}

	/**
	 * 通过背包号和参数获取第一个符合条件的格子
	 * 
	 * @param roleBag
	 * @param contrast
	 * @return
	 */
	public int getFirstFreeGrid(short roleBag, Object contrast) {
		return 0;
	}

	/**
	 * 记录一个穿戴的装备
	 * 
	 * @param index
	 *            装备类别固定的格子序号
	 * @param goodsId
	 *            装备ID
	 */
	public void addEquipedGoods(int index, int goodsId) {
		if (equipedGoods == null) {
			equipedGoods = new HashMap<Integer, Integer>();
		}
		equipedGoods.put(index, goodsId);
	}

	/**
	 * 通过装备类别固定的格子序号获取装备ID
	 * 
	 * @param index
	 *            装备类别固定的格子序号
	 * @return
	 */
	public int getEquipedGoods(int index) {
		if (equipedGoods == null) {
			return 0;
		}
		Integer goodsId = equipedGoods.get(index);
		if (goodsId == null) {
			goodsId = 0;
		}
		return goodsId;
	}

	/**
	 * 通过序号移除已穿戴的装备
	 * 
	 * @param index
	 */
	public void removeEquipedGoods(int index) {
		if (equipedGoods != null) {
			equipedGoods.remove(index);
		}
	}

	public Map<Integer, Integer> getEquipedGoods() {
		return equipedGoods;
	}

	public byte getRoleStar() {
		return roleStar;
	}

	public void setRoleStar(byte roleStar) {
		this.roleStar = roleStar;
	}

	public void addPetActivateLuck(int luckId) {
	}

	public boolean removePetActivateLuck(int luckId) {
		return false;
	}

	public List<Integer> getPetActiveLucks() {
		return null;
	}

	public boolean removeSelfActivateLuck(int luckId) {
		return false;
	}

	public int containsPetLuck(int luckId) {
		return 0;
	}

	public List<Integer> getSelfActiveLucks() {
		return null;
	}

	/**
	 * 通过物品真实ID判断是否有穿戴该物品
	 * 
	 * @param goodsId
	 *            物品真实ID
	 * @return
	 */
	public boolean hasEquipedByGoodsId(int goodsId) {
		if (equipedGoods != null && !equipedGoods.isEmpty()) {
			Collection<Integer> values = equipedGoods.values();
			AGoods goods = null;
			for (int bindId : values) {
				goods = getGoods(bindId);
				if (goods != null && goods.getGoodsId() == goodsId) {
					return true;
				}
			}
		}
		return false;
	}

	public void addLuckEffect(EffectFunction ef) {
		if (luckEffects == null) {
			luckEffects = new ArrayList<EffectFunction>();
		}
		luckEffects.add(ef);
	}

	public EffectFunction getLuckEffect(int luckId) {
		if (luckEffects != null && !luckEffects.isEmpty()) {
			EffectFunction ef = null;
			for (int i = 0, size = luckEffects.size(); i < size; i++) {
				ef = luckEffects.get(i);
				if (ef != null && ef.getEffectId() == luckId) {
					return ef;
				}
			}
		}
		return null;
	}

	public boolean removeLuckEffect(EffectFunction ef) {
		if (luckEffects != null && !luckEffects.isEmpty()) {
			ef.release();
			return luckEffects.remove(ef);
		}
		return false;
	}

	public byte getSelfLuckState(int luckId) {
		return 0;
	}

	public byte getPetLuckState(int luckId) {
		return 0;
	}

	public byte getEquipPertainState(int pertainId) {
		if (activatePertains != null) {
			if (activatePertains.containsKey(pertainId)) {
				return GoodsConfig.PertainState.ACTIVATE;
			}
		}
		return GoodsConfig.PertainState.UNACTIVATE;
	}

	public void addEquipPertain(int pertainId, int goodsId) {
		if (activatePertains == null) {
			activatePertains = new HashMap<Integer, List<Integer>>();
		}
		List<Integer> value = activatePertains.get(pertainId);
		if (value == null) {
			value = new ArrayList<Integer>();
			activatePertains.put(pertainId, value);
		}
		value.add(goodsId);
	}

	public Map<Integer, List<Integer>> getActivateEquipPertain() {
		return activatePertains;
	}

	public void checkEquipPertain(AGoods goods) {

	}

	public void closeEquipPertain(AGoods goods) {

	}

	public byte getRoleAdvanceLv() {
		return roleAdvanceLv;
	}

	public void setRoleAdvanceLv(byte roleAdvanceLv) {
		this.roleAdvanceLv = roleAdvanceLv;
	}

	public void changeRoleAdvanceLv(byte roleAdvanceLv) {

	}

	public int getRoleAdvanceData(short propKey) {
		return 0;
	}

	public int getAttackRealm() {
		return attackRealm;
	}

	public int getDefenceRealm() {
		return defenceRealm;
	}

	public List<Integer> getUnOpenSkills() {
		return unOpenSkills;
	}

	/**
	 * 开启的最后一个穴位
	 * 
	 * @return
	 */
	public short getRealmPoint() {
		return -1;
	}

	public void enterArena() {
	}

	public String getSkeletons() {
		return "";
	}

	public String getSkeletons(String split) {
		return "";
	}

	public String getFaBaoAndSkill() {
		return "";
	}

	public String getPointArrayPetInfo(short arrayId) {
		return "";
	}

	public String getRoleSkillInfo() {
		return "";
	}

	public int getNoStar5continue(byte pickOutType) {
		return 0;
	}

	public void updateRoleHp() {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6005_UPDATE_ROLE);
		buf.putInt(id);
		buf.setProperty(CommonGamePropertyKey.tempKey.TEMP_HP_MAX_619, getComplexHp());
		buf.setProperty(CommonGamePropertyKey.tempKey.TEMP_HP_CURR_603, getHp());
		buf.endProperty();
		buf.putInt(-1);
		sendData(buf);
	}

	/**
	 * 获取使用的背包格子数量
	 * 
	 * @param bagMain
	 * @return
	 */
	public int getUseGridCount(short bagMain) {
		return 0;
	}

	/**
	 * 抽牌
	 * 
	 * @param reason
	 * @param goodsId
	 * @param count
	 * @param pickOutType
	 * @param starCount
	 */
	public Role pickOutPetCard(String operate, String project, String orderFormId, int goodsId, int count, byte pickOutType, byte starCount) {
		return null;
	}

	public String getOrderFormId() {
		return null;
	}

	public int getPick3To5PetCardCount() {
		return 0;
	}

	public void setPick3To5PetCardCount(int pick3To5PetCardCount) {
	}

	public int getZoomValue() {
		return 0;
	}

	public String getPetDialog() {
		return null;
	}

	public void resetLeaderIntegral(int leaderIntegral) {
	}

	public void addPushSecretId(int pushSecretId) {

	}

	public void resetDouFaData() {

	}

	public void resetWordBossData() {

	}

	public void resetLivenessMission() {

	}

	public int[] getRoleLivenessAwardStates() {
		return null;
	}

	public void setRoleLivenessAwardStates(int[] states) {

	}

	/**
	 * 奖励货币物品
	 * 
	 * @param goods
	 *            物品
	 * @param reason
	 *            奖励原因
	 * @param orderFormId
	 *            订单号
	 * @param checkExp
	 *            是否检测经验导致升级
	 */
	public void awardMoneyGoods(AGoods goods, String operate, String project, String orderFormId, boolean checkExp) {

	}

	public void awardMoneyGoods(String operate, String project, String orderFormId, int goodsId, int count, boolean checkExp) {

	}

	public void awardMoneyGoods(AGoods goods, String operate, String project, String orderFormId, short maxCate, boolean checkExp) {

	}

	public void awardMoneyGoods(String operate, String project, String orderFormId, int goodsId, int count, short maxCate, boolean checkExp) {

	}

	public int getExpressParameter() {
		return expressParameter;
	}

	public void setExpressParameter(int expressParameter) {
		this.expressParameter = expressParameter;
	}

	public int getExpressParameter2() {
		return expressParameter2;
	}

	public void setExpressParameter2(int expressParameter2) {
		this.expressParameter2 = expressParameter2;
	}

	public int getTotalBuyCount(int shopType, int goodsId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setLeaderReplay(long leaderReplay) {

	}

	public void resetOneStandData() {

	}

	/**
	 * 设置客户端包的模型
	 * 
	 * @param model
	 */
	public void setRoleClientModel(byte model) {
		if (model < 0) {
			model = 0;
		}
		this.clientModel = model;
	}

	public byte getClientModel() {
		return this.clientModel;
	}

	public void checkSelfLuckCondition() {

	}

	public void systemMail(String message) {

	}

	@Override
	public Role generatePet() {
		return null;
	}

	public byte getPetRace() {
		return 0;
	}

	public byte getPetType() {
		return 0;
	}

	public int getModelId() {
		return 0;
	}

	public List<Integer> getPetCommonSkills() {
		return null;
	}

	public boolean hasPointPet(int modelId) {
		return false;
	}

	public String getPetIcon() {
		return null;
	}

	public Role getPetByModelId(int modelId) {
		return null;
	}

	public int getMonsterModelId() {
		return 0;
	}

	public byte getElevatedCount() {
		return elevatedCount;
	}

	public void setElevatedCount(byte count) {
		this.elevatedCount = count;
	}

	public byte getQuality() {
		return realm;
	}

	public byte getCurLingGen() {
		return this.curLingGen;
	}

	public void setCurLingGen(byte curLingGen) {
		this.curLingGen = curLingGen;
	}

	public int getCurChips() {
		return 0;
	}

	public Set<Integer> getAcupointedList() {
		return acupointedList;
	}

	public Map<Integer, Byte> getCurAcupoints() {
		return acupointState;
	}

	public Set<Integer> getElevatedList() {
		return elevatedList;
	}

	public int getCurElevateId() {
		return curElevateId;
	}

	public void setCurElevateId(int curElevateId) {
		this.curElevateId = curElevateId;
	}

	public byte getAcupointState(int acupointId) {
		Byte state = acupointState.get(acupointId);
		if (state == null) {
			return 0;
		}
		return state;
	}

	public Set<Byte> getOpenLingGenList() {
		return openLingGenList;
	}

	public boolean isOpenAllAcupoint() {
		return isOpenAllAcupoint;
	}

	public void setOpenAllAcupoint(boolean result) {
		this.isOpenAllAcupoint = result;
	}

	public void activityAcupoint(int acupointId) {

	}

	public int getNextElevateId() {
		return 0;
	}

	public List<Integer> elevating(int nextElevateId) {
		return null;
	}

	public boolean openPetLingGen() {
		return false;
	}

	public boolean openPointGate(int gateId) {
		return false;
	}

	public boolean canUseExpGoods() {
		return false;
	}

	public int callPet(String operate, String project, String orderFormId, int makeId, byte sourceMark, boolean needNotice) {
		return 0;
	}

	/**
	 * 初始化宠物属性
	 */
	public void initPetProp() {
	}

	public short getMansionLevel() {
		return 0;
	}

	public int getSkillLvSum() {
		return skillLvSum;
	}

	public void setSkillLvSum(int skillLvSum) {
		this.skillLvSum = skillLvSum;
	}

	public int getAcupointLvSum() {
		return acupointLvSum;
	}

	public void setAcupointLvSum(int acupointLvSum) {
		this.acupointLvSum = acupointLvSum;
	}

	/**
	 * 重新计算战斗力
	 */
	public void resetRoleFighting() {
	}

	public String getPetSkillInfo() {
		return null;
	}

	public String getEquipInfo() {
		StringBuilder sb = new StringBuilder();
		if (equipFabao > 0) {
			sb.append(equipFabao);
		}
		if (equipHelmet != null) {
			if (sb.length() > 0) {
				sb.append("@");
			}
			sb.append(equipHelmet.getGoodsId());
		}
		if (equipClothes != null) {
			if (sb.length() > 0) {
				sb.append("@");
			}
			sb.append(equipClothes.getGoodsId());
		}
		if (equipShoe != null) {
			if (sb.length() > 0) {
				sb.append("@");
			}
			sb.append(equipShoe.getGoodsId());
		}
		if (equipWeapon != null) {
			if (sb.length() > 0) {
				sb.append("@");
			}
			sb.append(equipWeapon.getGoodsId());
		}
		return sb.toString();
	}

	@Override
	public int getDayBuyCount(int shopType, int goodsId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean enforceOpen(int goodsId) {
		// TODO Auto-generated method stub
		return false;
	}

	public HashMap<String, String> packagDataCenterInfo() {
		return null;
	}

	public void initOneStandTargetInfo(String targetInfo, int connectId) {

	}

	public List<Integer> getPushSecretIds() {
		return new ArrayList<Integer>();
	}

	public void setSecretSceneBindId(int bindId) {

	}

	public void closeWindows(String uiScript) {

	}

	public int getRegisterDays() {
		return (int) ((DateUtil.getCurrentDateSec() - DateUtil.getCurrentDateSec(getTimeCreateRole())) / GameSystem.ONEDAYMS);
	}

	public void goodsBuyCount(int shopType, int goodsId, int goodsCount) {

	}

	public int getSkillPoint() {
		return skillPoint;
	}

	public void setSkillPoint(int skillPoint) {
		this.skillPoint = skillPoint;
	}

	public int getTotalSkillPoint() {
		return totalSkillPoint;
	}

	public void setTotalSkillPoint(int totalSkillPoint) {
		this.totalSkillPoint = totalSkillPoint;
	}

	public void changeSkillPoint(int count, String reason) {

	}

	public boolean isFollowState() {
		return false;
	}

	public void setFollowState(boolean followState) {
	}

	public List<Integer> getFollowPets() {
		return null;
	}

	public void addFollowPet(int modelId) {

	}

	public void removeFollowPet(Integer modelId) {

	}

	public String getFollowPetInfo() {
		return "";
	}

	public String getRoleExpandInfo() {
		return "";
	}

	public List<Integer> getActivityLucks() {
		return activityLucks;
	}

	public void addActivityLuck(int luckId) {
		if (!activityLucks.contains(luckId)) {
			activityLucks.add(luckId);
		}
	}

	public void resetActivityLucks(List<Integer> modelIds) {

	}

	public int[] lucksToEffect() {
		return null;
	}

	public int getWillGetIndex() {
		return 0;
	}

	public void setWillGetIndex(int willGetIndex) {
	}

	public void recoverFollowPet() {
	}

	public byte getPetSourceMark() {
		return 0;
	}

	public void setSourceMark(byte sourceMark) {
	}

	public Map<Integer, ProgressState> getCallScores() {
		return null;
	}

	public int getSkillSumAdvanceLv() {
		int sum = 0;
		ASkill skill = null;
		ASkill[] skills = getSkills();
		for (int i = 0; i < skills.length; i++) {
			skill = skills[i];
			if (skill != null) {
				sum += skill.getSkillAdvanceLevel();
			}
		}
		return sum;
	}

	protected double getFightProSum() {
		// 攻击2.6 防御1.8 生命0.2 命中1 闪避0.8 暴击2.3 韧性1.8 破甲2.6 格挡1.8 吸血3.4 反弹 2.9 破防2.3 抵抗2.3
		double sum = getComplexAttack() * 2.6 + getComplexDefence() * 1.8 + getHpSumMax() * 0.2 + getComplexAttackChance() * 1
		        + getComplexDuckChance() * 0.8 + getComplexCruel() * 2.3 + getComplexTenacity() * 1.8 + getComplexDestroy() * 2.6
		        + getComplexFender() * 1.8 + getComplexSuckHp() * 3.4 + getComplexRebound() * 2.9 + getComplexReduceDefence() * 2.3
		        + getComplexResist() * 2.3;
		return sum;
	}

	/**
	 * 重置技能加点
	 */
	public void resetSkillData(Role userRole, List<Integer> baseSkills) {
		int modelId = getModelId();
		// 当前技能点=累积获得的技能点
		this.skillPoint = this.totalSkillPoint;
		// 所有开启的技能等级变为1级
		ASkill[] skills = getSkills();
		ASkill skill = null;
		short baseLv = 1;
		for (int i = 0; i < skills.length; i++) {
			skill = skills[i];
			if (skill != null && skill.getOpenState() == SkillConfig.State.UNSTUDY) {
				skill.setLevel(baseLv);
			}
		}
		// 更新客户端数据
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
		buf.setNetConfirm(-1);
		buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
		buf.setOverlap(false);

		buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);
		buf.setMain(Main_900_Pet.main_901_petSkillInfo);
		buf.setSub(modelId);
		for (int k = 0, kSize = baseSkills.size(); k < kSize; k++) {
			skill = getSkill(baseSkills.get(k));
			if (skill != null) {
				buf.setSerial(k);
				buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_LEVEL_0, skill.getLevel());// 0 技能等级
				buf.setProperty(CommonGamePropertyKey.ItemKey.ITEM_STRENGHT_925, skill.getSkillAdvanceLevel());// 925技能进阶等级
				buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_STATUS_17, skill.getOpenState()); // 17技能开启状态
				buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_QUALITY_13, skill.getSkillQuality()); // 13技能品阶
				buf.endProperty();
			}
		}
		buf.endSerial();
		buf.endSub();
		buf.endMain();

		buf.setCmd(ClientUI.UI.UPDATE_OBJECT);
		buf.setMain(Main_900_Pet.main_900_petBaseInfo);
		buf.setSub(0);
		buf.setSearchID(CommonGamePropertyKey.Pet.PET_MODEL_ID_1272, modelId);
		buf.setProperty(CommonGamePropertyKey.MoneyGoods.SKILL_POINT_314, this.skillPoint);
		buf.endProperty();

		buf.endCmd();
		userRole.sendData(buf);
	}

	public String getAllPetInfo() {
		return "";
	}

	public String getPetInfo(int[] modelIds) {
		return "";
	}

	public void resetBaseProp() {
		// TODO Auto-generated method stub

	}

	public int getEquipFabao() {
		return equipFabao;
	}

	public void setEquipFabao(int equipFabao) {
		this.equipFabao = equipFabao;
	}

	public AGoods getEquipWeapon() {
		return equipWeapon;
	}

	public void setEquipWeapon(AGoods equipWeapon) {
		this.equipWeapon = equipWeapon;
	}

	public AGoods getEquipClothes() {
		return equipClothes;
	}

	public void setEquipClothes(AGoods equipClothes) {
		this.equipClothes = equipClothes;
	}

	public AGoods getEquipHelmet() {
		return equipHelmet;
	}

	public void setEquipHelmet(AGoods equipHelmet) {
		this.equipHelmet = equipHelmet;
	}

	public AGoods getEquipShoe() {
		return equipShoe;
	}

	public void setEquipShoe(AGoods equipShoe) {
		this.equipShoe = equipShoe;
	}

	protected void unequipGoods(AGoods equip) {
	}

	protected void equipGoods(AGoods equip) {
	}

	public AGoods deleteGoods(AGoods goods, int count, String operate, String project, String orderFormId, int connectId) {
		return null;
	}

	final public void unequipWeapon() {
		AGoods equip = this.equipWeapon;
		this.equipWeapon = null;
		unequipGoods(equip);
	}

	final public void equipWeapon(AGoods equip) {
		unequipWeapon();
		this.equipWeapon = equip;
		equipGoods(equip);
	}

	final public void unequipHelmet() {
		AGoods equip = this.equipHelmet;
		this.equipHelmet = null;
		unequipGoods(equip);
	}

	final public void equipHelmet(AGoods equip) {
		unequipHelmet();
		this.equipHelmet = equip;
		equipGoods(equip);
	}

	final public void unequipClothes() {
		AGoods equip = this.equipClothes;
		this.equipClothes = null;
		unequipGoods(equip);
	}

	final public void equipClothes(AGoods equip) {
		unequipClothes();
		this.equipClothes = equip;
		equipGoods(equip);
	}

	final public void unequipShoe() {
		AGoods equip = this.equipShoe;
		this.equipShoe = null;
		unequipGoods(equip);
	}

	final public void equipShoe(AGoods equip) {
		unequipShoe();
		this.equipShoe = equip;
		equipGoods(equip);
	}

	public void unequipFabao() {
		// AGoods equip = this.equipFabao;
		// this.equipFabao = null;
		// unequipGoods(equip);
	}

	final public void equipFabao(AGoods equip) {
		unequipFabao();
		if (equip != null) {
			this.equipFabao = equip.getGoodsId();
			equipGoods(equip);
		}
	}
}