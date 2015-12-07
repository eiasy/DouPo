package mmo.module.cache.role;

import mmo.common.bean.role.RoleMini;
import mmo.tools.util.StringUtil;

public class CacheRoleFight {
	public static final String FLAG_fabao            = "fabao";
	public static final String FLAG_pet              = "pet";
	public static final String FLAG_ALL_PET          = "allPet";
	public static final String FLAG_RENAME           = "rename";
	public static final String FLAG_ROLE_SKILL       = "roleSkill";
	public static final String FLAG_EQUIP_INFO       = "equipInfo";
	public static final String FLAG_FOLLOW_PET       = "followPet";
	public static final String FLAG_WORLD_BOSS_ARRAY = "worldBossArray";
	public static final String FLAG_COMMON_ARRAY     = "commonArray";
	public static final String FLAG_EXPAND_INFO      = "expandInfo";
	public static final String FLAG_TOTAL_FIGHT      = "totalFight";
	/** 一小时内没有 */
	private final static long  OVERTIME_OFFSET       = 1000 * 60 * 60;
	/** 过期时间 */
	private long               overtime;
	/** 对象时间被重置过，需要重新排队 */
	private boolean            reorder;
	/****************************************************/
	/** 角色编号 */
	protected int              roleId;
	/** 角色名称 */
	protected String           username;
	/** 角色账号 */
	protected int              accountId;
	short                      level;
	byte                       profession;

	/** 综合攻击力 */
	protected int              sumAttack;
	/** HP上限值 */
	protected int              sumHp;
	/** 综合防御值 */
	protected int              sumDefence;
//	/** 综合命中 */
//	protected int              sumAttackChance;
//	/** 综合闪避 */
//	protected int              sumDuck;
//	/** 综合暴击 */
//	protected int              sumCruel;
//	/** 综合韧性 */
//	protected int              sumTenacity;
//	/** 综合格挡 */
//	protected int              sumFender;
//	/** 综合破甲 */
//	protected int              sumDestroy;
//	/** 综合吸血 */
//	protected int              sumSuckHp;
//	/** 综合反弹 */
//	protected int              sumRebound;
//	/** 综合破防 */
//	protected int              sumReduceDefence;
//	/** 综合抵抗 */
//	protected int              sumResist;
	/** 角色骨骼 */
	protected String           skeletons;
	/** 装备的法宝及激活的技能 */
	protected String           fabaoAndSkill;
	/** 宠物基本信息 */
	protected String           petInfo;
	/** 角色战斗力 */
	protected int              fight;
	/** 坐骑动画 */
	protected String           mountAni;
	/** 角色技能数据 */
	protected String           roleSkill;
	/** 是否可以重命名 */
	protected boolean          rename;
	/** 跟随宠物的信息 */
	protected String           followPetInfo;
	/** 装备的道具信息 */
	protected String           equipInfo;
	/** 世界首领阵容信息 */
	protected String           worldBossArrayInfo;
	/** 普通阵容信息 */
	protected String           commonArrayInfo;
	/** 自身附加数据信息 */
	protected String           roleExpandInfo;
	/** 总战斗力 */
	protected int              totalFight;
	/** 所有宠物 */
	protected String           allPet;

	public String getAllPet() {
		if (allPet == null) {
			this.allPet = "";
		}
		return allPet;
	}

	public void setAllPet(String allPet) {
		if (allPet == null) {
			this.allPet = "";
		} else {
			this.allPet = allPet;
		}
	}

	public int getTotalFight() {
		return totalFight;
	}

	public void setTotalFight(int totalFight) {
		this.totalFight = totalFight;
	}

	public CacheRoleFight() {
		this.overtime = System.currentTimeMillis() + OVERTIME_OFFSET;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public byte getProfession() {
		return profession;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public int getSumAttack() {
		return sumAttack;
	}

	public void setSumAttack(int sumAttack) {
		this.sumAttack = sumAttack;
	}

	public int getSumHp() {
		return sumHp;
	}

	public void setSumHp(int sumHp) {
		this.sumHp = sumHp;
	}

	public int getSumDefence() {
		return sumDefence;
	}

	public void setSumDefence(int sumDefence) {
		this.sumDefence = sumDefence;
	}

//	public int getSumAttackChance() {
//		return sumAttackChance;
//	}
//
//	public void setSumAttackChance(int sumAttackChance) {
//		this.sumAttackChance = sumAttackChance;
//	}
//
//	public int getSumDuck() {
//		return sumDuck;
//	}
//
//	public void setSumDuck(int sumDuck) {
//		this.sumDuck = sumDuck;
//	}
//
//	public int getSumCruel() {
//		return sumCruel;
//	}
//
//	public void setSumCruel(int sumCruel) {
//		this.sumCruel = sumCruel;
//	}
//
//	public int getSumTenacity() {
//		return sumTenacity;
//	}
//
//	public void setSumTenacity(int sumTenacity) {
//		this.sumTenacity = sumTenacity;
//	}
//
//	public int getSumFender() {
//		return sumFender;
//	}
//
//	public void setSumFender(int sumFender) {
//		this.sumFender = sumFender;
//	}
//
//	public int getSumDestroy() {
//		return sumDestroy;
//	}
//
//	public void setSumDestroy(int sumDestroy) {
//		this.sumDestroy = sumDestroy;
//	}
//
//	public int getSumSuckHp() {
//		return sumSuckHp;
//	}
//
//	public void setSumSuckHp(int sumSuckHp) {
//		this.sumSuckHp = sumSuckHp;
//	}
//
//	public int getSumRebound() {
//		return sumRebound;
//	}
//
//	public void setSumRebound(int sumRebound) {
//		this.sumRebound = sumRebound;
//	}
//
//	public int getSumReduceDefence() {
//		return sumReduceDefence;
//	}
//
//	public void setSumReduceDefence(int sumReduceDefence) {
//		this.sumReduceDefence = sumReduceDefence;
//	}
//
//	public int getSumResist() {
//		return sumResist;
//	}
//
//	public void setSumResist(int sumResist) {
//		this.sumResist = sumResist;
//	}

	public String getSkeletons() {
		return skeletons;
	}

	public void setSkeletons(String skeletons) {
		this.skeletons = skeletons;
	}

	public void setComplex(String complex) {
		String[] array = StringUtil.splitString(complex, '#');
		if (array.length > 0) {
			String item = null;
			int length = array.length;
			for (int ii = 0; ii < length; ii++) {
				item = array[ii];
				if (item.startsWith(FLAG_fabao)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						fabaoAndSkill = array2[1];
					} else {
						fabaoAndSkill = "15008,3008";
					}
				} else if (item.startsWith(FLAG_pet)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						petInfo = array2[1];
					} else {
						petInfo = "";
					}
				} else if (item.startsWith(FLAG_ROLE_SKILL)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						roleSkill = array2[1];
					} else {
						roleSkill = "";
					}
				} else if (item.startsWith(FLAG_FOLLOW_PET)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						followPetInfo = array2[1];
					} else {
						followPetInfo = "";
					}
				} else if (item.startsWith(FLAG_EQUIP_INFO)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						equipInfo = array2[1];
					} else {
						equipInfo = "";
					}
				} else if (item.startsWith(FLAG_WORLD_BOSS_ARRAY)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						worldBossArrayInfo = array2[1];
					} else {
						worldBossArrayInfo = "";
					}
				} else if (item.startsWith(FLAG_COMMON_ARRAY)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						commonArrayInfo = array2[1];
					} else {
						commonArrayInfo = "";
					}
				} else if (item.startsWith(FLAG_ALL_PET)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						allPet = array2[1];
					} else {
						allPet = "";
					}
				} else if (item.startsWith(FLAG_EXPAND_INFO)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						roleExpandInfo = array2[1];
					} else {
						roleExpandInfo = "";
					}
				} else if (item.equalsIgnoreCase(FLAG_RENAME)) {
					this.rename = true;
				} else if (item.startsWith("gm")) {
					addGmCmd(item);
				} else if (item.startsWith(FLAG_TOTAL_FIGHT)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						this.totalFight = Integer.parseInt(array2[1]);
					}
				} else {
					skeletons = item;
				}
			}
		}
	}

	public void addGmCmd(String item) {

	}

	public String getComplex() {
		StringBuilder sb = new StringBuilder();
		sb.append(skeletons);
		sb.append("#").append(FLAG_fabao).append(":").append(fabaoAndSkill);
		sb.append("#").append(FLAG_pet).append(":").append(petInfo);
		sb.append("#").append(FLAG_ROLE_SKILL).append(":").append(roleSkill);
		sb.append("#").append(FLAG_FOLLOW_PET).append(":").append(followPetInfo);
		sb.append("#").append(FLAG_EQUIP_INFO).append(":").append(equipInfo);
		sb.append("#").append(FLAG_WORLD_BOSS_ARRAY).append(":").append(worldBossArrayInfo);
		sb.append("#").append(FLAG_COMMON_ARRAY).append(":").append(commonArrayInfo);
		sb.append("#").append(FLAG_ALL_PET).append(":").append(allPet);
		sb.append("#").append(FLAG_EXPAND_INFO).append(":").append(roleExpandInfo);
		sb.append("#").append(FLAG_TOTAL_FIGHT).append(":").append(totalFight);
		if (rename) {
			sb.append("#rename");
		}
		return sb.toString();
	}

	public int getFight() {
		return fight;
	}

	public void setFight(int fight) {
		this.fight = fight;
	}

	public String getMountAni() {
		return mountAni;
	}

	public void setMountAni(String mountAni) {
		this.mountAni = mountAni;
	}

	/**
	 * 判断缓存对象是否已经过期
	 * 
	 * @param currTime
	 *            当前时间
	 * @return true已经过期，需要被回收掉，同时被回收还有同一个账号下的其他缓存角色
	 */
	public final boolean isOvertime(long currTime) {
		return currTime > overtime;
	}

	/**
	 * 重置过期时间
	 */
	public void resetOvertime() {
		this.overtime = System.currentTimeMillis() + OVERTIME_OFFSET;
		this.reorder = true;
	}

	public boolean isReorder() {
		return reorder;
	}

	public void setReorder(boolean reorder) {
		this.reorder = reorder;
	}

	public String getFabaoAndSkill() {
		if (fabaoAndSkill == null) {
			fabaoAndSkill = "15008,3008";
		}
		return fabaoAndSkill;
	}

	public void setFabaoAndSkill(String fabaoAndSkill) {
		this.fabaoAndSkill = fabaoAndSkill;
	}

	public final void resetCacheRoleFight(CacheRoleFight crf) {
		if (crf == null) {
			return;
		}
		crf.setLevel(level);
		crf.setSkeletons(skeletons);
		crf.setFabaoAndSkill(fabaoAndSkill);
		crf.setPetInfo(petInfo);
		crf.setSumAttack(sumAttack);
		crf.setSumDefence(sumDefence);
		crf.setSumHp(sumHp);
//		crf.setSumAttackChance(sumAttackChance);
//		crf.setSumCruel(sumCruel);
//		crf.setSumDestroy(sumDestroy);
//		crf.setSumDuck(sumDuck);
//		crf.setSumFender(sumFender);
//		crf.setSumRebound(sumRebound);
//		crf.setSumReduceDefence(sumReduceDefence);
//		crf.setSumResist(sumResist);
//		crf.setSumSuckHp(sumSuckHp);
//		crf.setSumTenacity(sumTenacity);
		crf.setFight(fight);
		crf.setRoleSkill(roleSkill);
		crf.setFollowPetInfo(followPetInfo);
		crf.setEquipInfo(equipInfo);
		crf.setWorldBossArrayInfo(worldBossArrayInfo);
		crf.setTotalFight(totalFight);
		crf.setCommonArrayInfo(commonArrayInfo);
		crf.setAllPet(allPet);
		crf.setRoleExpandInfo(roleExpandInfo);
	}

	public void resetCacheRoleFight(RoleMini rm) {
		if (rm == null) {
			return;
		}
		rm.setLevel(level);
		rm.setSkeletons(skeletons);
		rm.setFabaoAndSkill(fabaoAndSkill);
		rm.setPetInfo(petInfo);
		rm.setSumAttack(sumAttack);
		rm.setSumDefence(sumDefence);
		rm.setSumHp(sumHp);
		rm.setFightPower(fight);
		rm.setRoleSkill(roleSkill);
		rm.setFollowPetInfo(followPetInfo);
		rm.setEquipInfo(equipInfo);
		rm.setTotalFight(totalFight);
		rm.setWorldBossArrayInfo(worldBossArrayInfo);
		rm.setCommonArrayInfo(commonArrayInfo);
		rm.setAllPet(allPet);
		rm.setRoleExpandInfo(roleExpandInfo);
	}

	public final CacheRoleFight toCacheRoleFight() {
		CacheRoleFight crf = new CacheRoleFight();
		crf.setAccountId(accountId);
		crf.setLevel(level);
		crf.setProfession(profession);
		crf.setRoleId(roleId);
		crf.setSkeletons(skeletons);
		crf.setFabaoAndSkill(fabaoAndSkill);
		crf.setPetInfo(petInfo);
		crf.setSumAttack(sumAttack);
		crf.setSumDefence(sumDefence);
		crf.setSumHp(sumHp);
		crf.setUsername(username);
		crf.setFight(fight);
		crf.setMountAni(mountAni);
		crf.setRoleSkill(roleSkill);
		crf.setFollowPetInfo(followPetInfo);
		crf.setEquipInfo(equipInfo);
		crf.setWorldBossArrayInfo(worldBossArrayInfo);
		crf.setTotalFight(totalFight);
		crf.setCommonArrayInfo(commonArrayInfo);
		crf.setAllPet(allPet);
		crf.setRoleExpandInfo(roleExpandInfo);
		return crf;
	}

	public String getPetInfo() {
		if (petInfo == null) {
			petInfo = "";
		}
		return petInfo;
	}

	public void setPetInfo(String petInfo) {
		if (petInfo == null) {
			this.petInfo = "";
		} else {
			this.petInfo = petInfo;
		}
	}

	public String getFollowPetInfo() {
		if (followPetInfo == null) {
			followPetInfo = "";
		}
		return followPetInfo;
	}

	public void setFollowPetInfo(String followPetInfo) {
		if (followPetInfo == null) {
			this.followPetInfo = "";
		} else {
			this.followPetInfo = followPetInfo;
		}
	}

	public String getEquipInfo() {
		if (equipInfo == null) {
			equipInfo = "";
		}
		return equipInfo;
	}

	public void setEquipInfo(String equipInfo) {
		if (equipInfo == null) {
			this.equipInfo = "";
		} else {
			this.equipInfo = equipInfo;
		}
	}

	public String getWorldBossArrayInfo() {
		if (worldBossArrayInfo == null) {
			worldBossArrayInfo = "";
		}
		return worldBossArrayInfo;
	}

	public void setWorldBossArrayInfo(String worldBossArrayInfo) {
		if (worldBossArrayInfo == null) {
			this.worldBossArrayInfo = "";
		} else {
			this.worldBossArrayInfo = worldBossArrayInfo;
		}
	}

	public String getCommonArrayInfo() {
		if (commonArrayInfo == null) {
			commonArrayInfo = "";
		}
		return commonArrayInfo;
	}

	public void setCommonArrayInfo(String commonArrayInfo) {
		if (commonArrayInfo == null) {
			this.commonArrayInfo = "";
		} else {
			this.commonArrayInfo = commonArrayInfo;
		}
	}

	public String getRoleExpandInfo() {
		if (roleExpandInfo == null) {
			roleExpandInfo = "";
		}
		return roleExpandInfo;
	}

	public void setRoleExpandInfo(String roleExpandInfo) {
		if (roleExpandInfo == null) {
			this.roleExpandInfo = "";
		} else {
			this.roleExpandInfo = roleExpandInfo;
		}
	}

	public String getRoleSkill() {
		if (roleSkill == null) {
			roleSkill = "";
		}
		return roleSkill;
	}

	public void setRoleSkill(String roleSkill) {
		if (roleSkill == null) {
			this.roleSkill = "";
		} else {
			this.roleSkill = roleSkill;
		}
	}

	public boolean isRename() {
		return rename;
	}

	public void setRename(boolean rename) {
		this.rename = rename;
	}
}
