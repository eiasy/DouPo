package mmo.module.rank.bean.arena;

import mmo.common.config.role.RoleProfession;

public class ArenaAi {
	private String arenaAi  = null; // 脚本AI
	private int    skill_1  = 0;
	private int    skill_2  = 0;
	private int    skill_3  = 0;
	private int    skill_4  = 0;
	private int    skill_5  = 0;
	private int    skill_6  = 0;
	private int    skill_7  = 0;
	private int    skill_8  = 0;
	private int    skill_9  = 0;
	private int    skill_10 = 0;
	private int    skill_11 = 0;
	private int    skill_12 = 0;
	private int    skill_13 = 0;
	private int    skill_14 = 0;
	private int    skill_15 = 0;
	private int    skill_16 = 0;

	public ArenaAi() {

	}

	public ArenaAi(byte profession) {
		switch (profession) {
			case RoleProfession.XuanXian_1: {
				this.arenaAi = "airanka10";
				this.skill_1 = 4100;
				this.skill_2 = 4101;
				this.skill_3 = 4102;
				this.skill_4 = 4103;
				this.skill_5 = 4106;
				break;
			}
			case RoleProfession.XiuLuo_2: {
				this.arenaAi = "airanka10";
				this.skill_1 = 4200;
				this.skill_2 = 4201;
				this.skill_3 = 4202;
				this.skill_4 = 4203;
				this.skill_5 = 4206;
				break;
			}
			case RoleProfession.YuLing_16: {
				this.arenaAi = "airanka10";
				this.skill_1 = 4300;
				this.skill_2 = 4301;
				this.skill_3 = 4302;
				this.skill_4 = 4303;
				this.skill_5 = 4306;
				break;
			}
			default: {
				this.arenaAi = "airanka10";
				this.skill_1 = 4200;
				this.skill_2 = 4201;
				this.skill_3 = 4202;
				this.skill_4 = 4203;
				this.skill_5 = 4206;
				break;
			}
		}
	}

	public String getArenaAi() {
		return arenaAi;
	}

	public void setArenaAi(String arenaAi) {
		this.arenaAi = arenaAi;
	}

	public int getSkill_1() {
		return skill_1;
	}

	public void setSkill_1(int skill_1) {
		this.skill_1 = skill_1;
	}

	public int getSkill_2() {
		return skill_2;
	}

	public void setSkill_2(int skill_2) {
		this.skill_2 = skill_2;
	}

	public int getSkill_3() {
		return skill_3;
	}

	public void setSkill_3(int skill_3) {
		this.skill_3 = skill_3;
	}

	public int getSkill_4() {
		return skill_4;
	}

	public void setSkill_4(int skill_4) {
		this.skill_4 = skill_4;
	}

	public int getSkill_5() {
		return skill_5;
	}

	public void setSkill_5(int skill_5) {
		this.skill_5 = skill_5;
	}

	public int getSkill_6() {
		return skill_6;
	}

	public void setSkill_6(int skill_6) {
		this.skill_6 = skill_6;
	}

	public int getSkill_7() {
		return skill_7;
	}

	public void setSkill_7(int skill_7) {
		this.skill_7 = skill_7;
	}

	public int getSkill_8() {
		return skill_8;
	}

	public void setSkill_8(int skill_8) {
		this.skill_8 = skill_8;
	}

	public int getSkill_9() {
		return skill_9;
	}

	public void setSkill_9(int skill_9) {
		this.skill_9 = skill_9;
	}

	public int getSkill_10() {
		return skill_10;
	}

	public void setSkill_10(int skill_10) {
		this.skill_10 = skill_10;
	}

	public int getSkill_11() {
		return skill_11;
	}

	public void setSkill_11(int skill_11) {
		this.skill_11 = skill_11;
	}

	public int getSkill_12() {
		return skill_12;
	}

	public void setSkill_12(int skill_12) {
		this.skill_12 = skill_12;
	}

	public int getSkill_13() {
		return skill_13;
	}

	public void setSkill_13(int skill_13) {
		this.skill_13 = skill_13;
	}

	public int getSkill_14() {
		return skill_14;
	}

	public void setSkill_14(int skill_14) {
		this.skill_14 = skill_14;
	}

	public int getSkill_15() {
		return skill_15;
	}

	public void setSkill_15(int skill_15) {
		this.skill_15 = skill_15;
	}

	public int getSkill_16() {
		return skill_16;
	}

	public void setSkill_16(int skill_16) {
		this.skill_16 = skill_16;
	}

}
