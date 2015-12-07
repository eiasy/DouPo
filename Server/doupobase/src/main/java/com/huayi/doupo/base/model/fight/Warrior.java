package com.huayi.doupo.base.model.fight;

public class Warrior{

	private int team;
	
	private String name;
	
	private int position;
	

	private int initAttack;
	
	private int initBlood;
	
	
	public int getInitBlood() {
		return initBlood;
	}

	public void setInitBlood(int initBlood) {
		this.initBlood = initBlood;
	}

	public int getInitAttack() {
		return initAttack;
	}

	public void setInitAttack(int initAttack) {
		this.initAttack = initAttack;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
}
