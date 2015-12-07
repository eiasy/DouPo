package mmo.common.bean.role;

import mmo.common.bean.skill.ASkill;

/**
 * 与角色相关的函数
 * 
 * @author 李天喜
 * 
 */
public interface IRole {

	/***
	 * 获取角色的宠物
	 * 
	 * @return 宠物对象
	 */
	public Role generatePet();

	/**
	 * 战斗相关的函数
	 * 
	 * @author 李天喜
	 * 
	 */
	public interface Battle {

		/**
		 * 获取命中值
		 * 
		 * @return short 命中值
		 */
		public int getAttackChance();

		/**
		 * 获取BUF对攻击力的影响值
		 * 
		 * @return BUF对攻击力的影响值
		 */
		public short getBufAttack();

		/**
		 * 获取BUF对防御值的影响值
		 * 
		 * @return BUF对防御值的影响值
		 */
		public int getBufDefence();

		/**
		 * 获取闪避值
		 * 
		 * @return short 闪避值
		 */
		public int getDuckChance();

		/**
		 * 获取被动技能对攻击力的影响值
		 * 
		 * @return 被动技能对攻击力的影响值
		 */
		public short getEdSkillAttack();

		/**
		 * 获取被动技能对防御的影响值
		 * 
		 * @return 被动技能对防御的影响值
		 */
		public int getEdSkillDefence();

		/**
		 * 获取装备对攻击力的增幅值
		 * 
		 * @return 装备对攻击力的增幅值
		 */
		public short getEquipAttack();

		/**
		 * 获取装备对防御的增幅值
		 * 
		 * @return 装备对防御的增幅值
		 */
		public int getEquipDefence();

		/**
		 * 获取角色的综合攻击力，在综合攻击力下限和综合攻击力上限之间取值
		 * 
		 * @return 综合攻击力
		 */
		public int getSumAttack();

		/**
		 * 获取综合防御值，在综合防护下限和综合防御上限之间取值
		 * 
		 * @return 综合防御值
		 */
		public int getSumDefence();

		/**
		 * 获取闪避综合值
		 * 
		 * @return 闪避综合值
		 */
		public int getSumDuckChance();

		/**
		 * 获取命中的综合值
		 * 
		 * @return 命中的综合值
		 */
		public int getSumAttackChance();
	}

	/**
	 * 增加仇恨值
	 * 
	 * @param value
	 *            新增的仇恨值
	 * @param enmity
	 *            仇恨对象
	 */
	public void addEnmityValue(int value, Role enmity);

	/**
	 * @return short 获取角色的基础HP，随级别成长的基本HP
	 */
	public int getHpBase();

	/**
	 * @return 境界
	 */
	public byte getRealm();

	/**
	 * @return 角色经验值
	 */
	public int getExperience();

	/**
	 * 获取目标角色对象中当前角色的所在方向（上下左右）
	 * 
	 * @param target
	 *            目标对象
	 * @return 方向值
	 */
	public byte getFaceDir(Role target);

	/**
	 * @return Role 攻击的目标,被怪物复写
	 */
	public IRole getHitTarget();

	/**
	 * @return 当前的HP
	 */
	public int getHp();

	/**
	 * @return 角色ID
	 */
	public int getId();


	/**
	 * @return 角色等级
	 */
	public short getLevel();

	/**
	 * @return 角色所在的场景编号
	 */
	public int getMapId();

	/**
	 * @return 角色名称
	 */
	public String getUsername();

	/**
	 * @return 角色性别
	 */
	public byte getSex();

	/**
	 * 获取角色拥有的技能
	 * 
	 * @param skillId
	 *            指定的技能编号
	 * @return 技能对象，如果没有学习该技能则返回null
	 */
	public ASkill getSkill(int skillId);

	/**
	 * 获取最大HP
	 * 
	 * @return 最大HP
	 */
	public int getHpSumMax();

	/**
	 * 获取移动速度的综合值
	 * 
	 * @return 移动速度的综合值
	 */
	public short getSumMoveSpeed();

	/**
	 * @return X坐标值，角色当前Tile坐标
	 */
	public short getTileX();

	/**
	 * @return Y坐标值，角色当前Tile坐标
	 */
	public short getTileY();

	/**
	 * 判断角色是否处于攻击状态
	 * 
	 * @return true:攻击状态，false：不处于攻击状态
	 */
	public boolean isAttackState();

	/**
	 * 判断角色是否处于防御状态
	 * 
	 * @return true:防御状态，false：不处于防御状态
	 */
	public boolean isDefenceState();

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
	 *            -1推出变身，0保持原状态，1进入变身
	 * @param evil
	 *            -1推出魔化，0保持原状态，1进入魔化
	 * @return 角色行为状态的综合值
	 */
	public int updateBehavior(int physics, int magic, int move, int distortion, int evil);
}
