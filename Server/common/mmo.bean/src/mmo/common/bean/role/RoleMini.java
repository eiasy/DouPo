package mmo.common.bean.role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.config.role.RoleProfession;
import mmo.common.system.GameParameter;
import mmo.module.cache.role.CacheRoleFight;
import mmo.tools.util.StringUtil;

public class RoleMini {
	/** 角色编号 */
	protected int                   roleId;
	/** 角色名称 */
	protected String                roleName           = "";
	/** 角色等级 */
	protected short                 level;
	/** VIP等级 */
	protected short                 vipLevel;
	/** 角色状态 */
	protected byte                  dbState;
	/** 境界值 */
	protected byte                  realm;
	/** 队伍编号 */
	protected int                   teamId;
	/** 性别 */
	protected byte                  sex;
	/** 职业 */
	protected byte                  profession         = RoleProfession.XuanXian_1;
	/** 账号id */
	protected int                   accountId;
	/** 战斗力 */
	protected int                   fightPower;
	/**************************************************************/
	/** 综合攻击力 */
	protected int                   sumAttack;
	/** HP上限值 */
	protected int                   sumHp;
	/** 综合防御值 */
	protected int                   sumDefence;
	/** 综合命中 */
	protected int                   sumAttackChance;
	/** 综合闪避 */
	protected int                   sumDuck;
	/** 综合暴击 */
	protected int                   sumCruel;
	/** 综合韧性 */
	protected int                   sumTenacity;
	/** 综合格挡 */
	protected int                   sumFender;
	/** 综合破甲 */
	protected int                   sumDestroy;
	/** 综合吸血 */
	protected int                   sumSuckHp;
	/** 综合反弹 */
	protected int                   sumRebound;
	/** 综合破防 */
	protected int                   sumReduceDefence;
	/** 综合抵抗 */
	protected int                   sumResist;
	/** 斗法胜利次数 */
	protected int                   pks;
	/** 斗法失败次数 */
	protected int                   pkf;

	/** 角色骨骼 */
	protected String                skeletons;
	/** 装备的法宝及激活的技能 */
	protected String                fabaoAndSkill;
	/** 斗法阵容基本信息 */
	protected String                petInfo            = "";
	/** 角色技能数据 */
	protected String                roleSkill          = "";
	/** 斗法对战记录 */
	protected List<ChallengeLog>    challengeLogs      = new ArrayList<ChallengeLog>();
	/** 跟随的宠物信息 */
	protected String                followPetInfo      = "";
	/** 世界首领的阵容信息 */
	protected String                worldBossArrayInfo = "";
	/** 普通阵容信息 */
	protected String                commonArrayInfo    = "";
	/** 所有的伙伴 */
	protected String                allPet             = "";
	/** 自身附加数据 */
	protected String                roleExpandInfo     = "";
	/** 总战斗力 */
	protected int                   totalFight;
	/** 私斗阵容 */
	protected String                privateChallengePets;
	/** 角色装备的道具信息 */
	protected String                equipInfo          = "";
	/** 玩家拥有某种道具的数量 */
	protected Map<Integer, Integer> roleGoodsCount     = new HashMap<Integer, Integer>();

	/** PK连续获胜次数 */
	private int                     pkContinueWin;
	private int                     createSecretCount;
	/***************************************************** 仙府相关数据 ***********/
	/** 仙府等级 */
	private short                   mansionLevel       = 1;
	/** 仙府经验 */
	private int                     mansionExp;

	public RoleMini() {

	}

	public void setGoodsCount(int goodsId, int count) {
		roleGoodsCount.put(goodsId, count);
	}

	public int getGoodsCount(int goodsId) {
		Integer count = roleGoodsCount.get(goodsId);
		if (count == null) {
			return 0;
		}
		return count;
	}

	public void goodsCountChanged(int goodsId, int offset) {
		Integer count = roleGoodsCount.get(goodsId);
		if (count == null) {
			count = 0;
		}
		count += offset;
		roleGoodsCount.put(goodsId, count);
	}

	public Map<Integer, Integer> getRoleGoodsCount() {
		return roleGoodsCount;
	}

	public String getPrivateChallengePets() {
		if (privateChallengePets == null) {
			privateChallengePets = "";
		}
		return privateChallengePets;
	}

	public void setPrivateChallengePets(String privateChallengePets) {
		this.privateChallengePets = privateChallengePets;
	}

	public String getAllPet() {
		return allPet;
	}

	public void setAllPet(String allPet) {
		this.allPet = allPet;
	}

	public int getTotalFight() {
		return totalFight;
	}

	public void setTotalFight(int totalFight) {
		this.totalFight = totalFight;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public short getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(short vipLevel) {
		this.vipLevel = vipLevel;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public byte getDBState() {
		return dbState;
	}

	public void setDBState(byte state) {
		this.dbState = state;
	}

	public boolean isDeleted() {
		return dbState == 1;
	}

	public void toDeleted() {
		this.dbState = 1;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public byte getRealm() {
		return realm;
	}

	public void setRealm(byte realm) {
		this.realm = realm;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public byte getProfession() {
		return profession;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@Override
	public String toString() {
		return "RoleMini [roleId=" + roleId + ", roleName=" + roleName + ", level=" + level + ", dbState=" + dbState + ", realm=" + realm
		        + ", teamId=" + teamId + ", sex=" + sex + ", profession=" + profession + ", accountId=" + accountId + "]";
	}

	public int getFightPower() {
		return fightPower;
	}

	public void setFightPower(int fightPower) {
		this.fightPower = fightPower;
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

	public int getSumAttackChance() {
		return sumAttackChance;
	}

	public void setSumAttackChance(int sumAttackChance) {
		this.sumAttackChance = sumAttackChance;
	}

	public int getSumDuck() {
		return sumDuck;
	}

	public void setSumDuck(int sumDuck) {
		this.sumDuck = sumDuck;
	}

	public int getSumCruel() {
		return sumCruel;
	}

	public void setSumCruel(int sumCruel) {
		this.sumCruel = sumCruel;
	}

	public int getSumTenacity() {
		return sumTenacity;
	}

	public void setSumTenacity(int sumTenacity) {
		this.sumTenacity = sumTenacity;
	}

	public int getSumFender() {
		return sumFender;
	}

	public void setSumFender(int sumFender) {
		this.sumFender = sumFender;
	}

	public int getSumDestroy() {
		return sumDestroy;
	}

	public void setSumDestroy(int sumDestroy) {
		this.sumDestroy = sumDestroy;
	}

	public int getSumSuckHp() {
		return sumSuckHp;
	}

	public void setSumSuckHp(int sumSuckHp) {
		this.sumSuckHp = sumSuckHp;
	}

	public int getSumRebound() {
		return sumRebound;
	}

	public void setSumRebound(int sumRebound) {
		this.sumRebound = sumRebound;
	}

	public int getSumReduceDefence() {
		return sumReduceDefence;
	}

	public void setSumReduceDefence(int sumReduceDefence) {
		this.sumReduceDefence = sumReduceDefence;
	}

	public int getSumResist() {
		return sumResist;
	}

	public void setSumResist(int sumResist) {
		this.sumResist = sumResist;
	}

	public String getSkeletons() {
		return skeletons;
	}

	public void setSkeletons(String skeletons) {
		this.skeletons = skeletons;
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

	public short getMansionLevel() {
		return mansionLevel;
	}

	public void setMansionLevel(short mansionLevel) {
		this.mansionLevel = mansionLevel;
	}

	public int getMansionExp() {
		return mansionExp;
	}

	public void setMansionExp(int mansionExp) {
		this.mansionExp = mansionExp;
	}

	public final void mansionLevelUpgrade(int costExp) {
		this.mansionExp -= costExp;
		this.mansionLevel++;
	}

	public final void addMansionExp(int expCount) {
		this.mansionExp += expCount;
	}

	public int getPkContinueWin() {
		return pkContinueWin;
	}

	public void setPkContinueWin(int pkContinueWin) {
		this.pkContinueWin = pkContinueWin;
	}

	public int getCreateSecretCount() {
		return createSecretCount;
	}

	public void setCreateSecretCount(int createSecretCount) {
		this.createSecretCount = createSecretCount;
	}

	/** 宗门建立次数 */
	public void addSectCreateCount() {
		createSecretCount++;
	}

	public int getPks() {
		return pks;
	}

	public void setPks(int pks) {
		this.pks = pks;
	}

	public int getPkf() {
		return pkf;
	}

	public void setPkf(int pkf) {
		this.pkf = pkf;
	}

	public List<ChallengeLog> getChallengeLogs() {
		return challengeLogs;
	}

	public void clearChallengeLogs() {
		challengeLogs.clear();
	}

	public void addChallengeLog(ChallengeLog challengeLog) {
		this.challengeLogs.add(challengeLog);
		if (challengeLogs.size() > 0 && challengeLogs.size() > GameParameter.getChallengeLogCount()) {
			challengeLogs.remove(0);
		}
	}

	public void setComplex(String complex) {
		String[] array = StringUtil.splitString(complex, '#');
		if (array.length > 0) {
			String item = null;
			int length = array.length;
			for (int ii = 0; ii < length; ii++) {
				item = array[ii];
				if (item.startsWith(CacheRoleFight.FLAG_fabao)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						fabaoAndSkill = array2[1];
					} else {
						fabaoAndSkill = "15008,3008";
					}
				} else if (item.startsWith(CacheRoleFight.FLAG_pet)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						petInfo = array2[1];
					} else {
						petInfo = "";
					}
				} else if (item.startsWith(CacheRoleFight.FLAG_ROLE_SKILL)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						roleSkill = array2[1];
					} else {
						roleSkill = "";
					}
				} else if (item.startsWith(CacheRoleFight.FLAG_FOLLOW_PET)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						followPetInfo = array2[1];
					} else {
						followPetInfo = "";
					}
				} else if (item.startsWith(CacheRoleFight.FLAG_EQUIP_INFO)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						equipInfo = array2[1];
					} else {
						equipInfo = "";
					}
				} else if (item.startsWith(CacheRoleFight.FLAG_WORLD_BOSS_ARRAY)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						worldBossArrayInfo = array2[1];
					} else {
						worldBossArrayInfo = "";
					}
				} else if (item.startsWith(CacheRoleFight.FLAG_COMMON_ARRAY)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						commonArrayInfo = array2[1];
					} else {
						commonArrayInfo = "";
					}
				} else if (item.startsWith(CacheRoleFight.FLAG_EXPAND_INFO)) {
					String[] array2 = StringUtil.splitString(item, ':');
					if (array2.length > 1) {
						roleExpandInfo = array2[1];
					} else {
						roleExpandInfo = "";
					}
				} else {
					skeletons = item;
				}
			}
		}
	}
}
