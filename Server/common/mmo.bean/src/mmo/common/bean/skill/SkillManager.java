package mmo.common.bean.skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.common.config.role.RoleProfession;

public class SkillManager {
	private static SkillManager instance = new SkillManager();

	public static final SkillManager getInstance() {
		if (instance == null) {
			instance = new SkillManager();
		}
		return instance;
	}

	/** 游戏中技能列表 */
	private Map<Integer, ASkill> gameSkills     = null;
	/** 技能名称与ID的映射 */
	private Map<String, Integer> skillNameToId  = null;
	/** 法宝技能 */
	private List<Integer>        fabaoSkills    = new ArrayList<Integer>(100);
	/** 玄仙职业技能 */
	private List<Integer>        xuanXianSkills = new ArrayList<Integer>();
	/** 修罗职业技能 */
	private List<Integer>        xiuLuoSkills   = new ArrayList<Integer>();
	/** 御灵职业技能 */
	private List<Integer>        yuLingSkills   = new ArrayList<Integer>();
	/** 职业技能对应的序号 */
	private Map<Integer, Short>  serails        = null;

	private SkillManager() {

	}

	public void init(ISkillService skillService, ISkillExecute skillExecute) {
	}

	/**
	 * 通过技能名称获取ID
	 * 
	 * @param name
	 * @return
	 */
	public final int getSkillId(String name) {
		Integer skillId = skillNameToId.get(name);
		if (skillId == null) {
			skillId = 0;
		}
		return skillId;
	}

	public final ASkill getSkill(int skillId) {
		return gameSkills.get(skillId);
	}

	/**
	 * 获取职业技能对应的序号
	 * 
	 * @param skillId
	 * @return
	 */
	public short getSerail(int skillId) {
		if (serails == null) {
			serails = new HashMap<Integer, Short>(64, 1);
			short index = 0;
			for (int id : xuanXianSkills) {
				if (isNotBaseSkill(id)) {
					serails.put(id, index++);
				}
			}
			index = 0;
			for (int id : xiuLuoSkills) {
				if (isNotBaseSkill(id)) {
					serails.put(id, index++);
				}
			}
			index = 0;
			for (int id : yuLingSkills) {
				if (isNotBaseSkill(id)) {
					serails.put(id, index++);
				}
			}
		}
		return serails.get(skillId);
	}

	/**
	 * 添加技能
	 */
	public final void addSkill(int skillId, ASkill skill) {
		if (this.gameSkills == null) {
			this.gameSkills = new HashMap<Integer, ASkill>(256);
			this.skillNameToId = new HashMap<String, Integer>(256);
		}
		this.gameSkills.put(skillId, skill);
		this.skillNameToId.put(skill.getName(), skillId);
	}

	/**
	 * 添加法宝技能
	 * 
	 * @param skillId
	 */
	public final void addFabaoSkill(int skillId) {
		if (fabaoSkills == null) {
			fabaoSkills = new ArrayList<Integer>(100);
		}
		fabaoSkills.add(skillId);
	}

	public final List<Integer> getFabaoSkill() {
		return fabaoSkills;
	}

	/**
	 * 添加职业技能
	 * 
	 * @param skillId
	 *            技能ID
	 * @param profession
	 *            职业
	 */
	public final void addProfessionSkill(int skillId, byte profession) {
		switch (profession) {
			case RoleProfession.XuanXian_1: {
				if (xuanXianSkills == null) {
					xuanXianSkills = new ArrayList<Integer>();
				}
				xuanXianSkills.add(skillId);
				break;
			}
			case RoleProfession.XiuLuo_2: {
				if (xiuLuoSkills == null) {
					xiuLuoSkills = new ArrayList<Integer>();
				}
				xiuLuoSkills.add(skillId);
				break;
			}
			case RoleProfession.YuLing_16: {
				if (yuLingSkills == null) {
					yuLingSkills = new ArrayList<Integer>();
				}
				yuLingSkills.add(skillId);
				break;
			}
		}
	}

	/**
	 * 获取职业技能
	 */
	public List<Integer> getProfessionSkill(byte profession) {
		switch (profession) {
			case RoleProfession.XuanXian_1: {
				return xuanXianSkills;
			}
			case RoleProfession.XiuLuo_2: {
				return xiuLuoSkills;
			}
			case RoleProfession.YuLing_16: {
				return yuLingSkills;
			}
		}
		return null;
	}

	/**
	 * 判断是否是基础攻击
	 * 
	 * @param skill
	 * @return true 不是基础攻击
	 */
	public final static boolean isNotBaseSkill(int skillId) {
		if (skillId == 4100 || skillId == 4101 || skillId == 4102 || skillId == 4200 || skillId == 4201 || skillId == 4202 || skillId == 4300
		        || skillId == 4301 || skillId == 4302) {
			return false;
		}
		return true;
	}
}
