package com.huayi.doupo.base.model.fight;

import java.io.Serializable;


/**
 * 被动技能封装类
 * @author mp
 * @date 2013-11-5 下午1:36:54
 */
public class BeforceSkill implements Serializable{
	
	private static final long serialVersionUID = 5935192146958719374L;

	private int warriorId;
	
	private int team;
	
	private int position;
	
	private int fightPropId;
	
	private float fightPropValue;
	

	public float getFightPropValue() {
		return fightPropValue;
	}

	public void setFightPropValue(float fightPropValue) {
		this.fightPropValue = fightPropValue;
	}

	public int getWarriorId() {
		return warriorId;
	}

	public void setWarriorId(int warriorId) {
		this.warriorId = warriorId;
	}

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getFightPropId() {
		return fightPropId;
	}

	public void setFightPropId(int fightPropId) {
		this.fightPropId = fightPropId;
	}

	
}
