package com.huayi.doupo.base.model;

import java.io.*;

/**
	
*/
@SuppressWarnings("serial")
public class InstUnionWarAgainst implements Serializable
{
	private int index;
	public String result = "";
	/**
		id
	*/
	private int id;
	public int getId(){
		return id;
	}
	public void setId(int id) {
		this.id = id;
		index = 1;
		result += index + "*int*" + id + "#";
	}

	public void setId(int id, int bs) {
		this.id = id;
	}

	/**
		对阵索引
	*/
	private int againstIndex;
	public int getAgainstIndex(){
		return againstIndex;
	}
	public void setAgainstIndex(int againstIndex) {
		this.againstIndex = againstIndex;
		index = 2;
		result += index + "*int*" + againstIndex + "#";
	}

	public void setAgainstIndex(int againstIndex, int bs) {
		this.againstIndex = againstIndex;
	}

	/**
		
	*/
	private int isAWin;
	public int getIsAWin(){
		return isAWin;
	}
	public void setIsAWin(int isAWin) {
		this.isAWin = isAWin;
		index = 3;
		result += index + "*int*" + isAWin + "#";
	}

	public void setIsAWin(int isAWin, int bs) {
		this.isAWin = isAWin;
	}

	/**
		队伍A
	*/
	private int teamA;
	public int getTeamA(){
		return teamA;
	}
	public void setTeamA(int teamA) {
		this.teamA = teamA;
		index = 4;
		result += index + "*int*" + teamA + "#";
	}

	public void setTeamA(int teamA, int bs) {
		this.teamA = teamA;
	}

	/**
		联盟A名字
	*/
	private String teamNameA;
	public String getTeamNameA(){
		return teamNameA;
	}
	public void setTeamNameA(String teamNameA) {
		this.teamNameA = teamNameA;
		index = 5;
		result += index + "*String*" + teamNameA + "#";
	}

	public void setTeamNameA(String teamNameA, int bs) {
		this.teamNameA = teamNameA;
	}

	/**
		联盟A的MVP名字
	*/
	private String mvpNameA;
	public String getMvpNameA(){
		return mvpNameA;
	}
	public void setMvpNameA(String mvpNameA) {
		this.mvpNameA = mvpNameA;
		index = 6;
		result += index + "*String*" + mvpNameA + "#";
	}

	public void setMvpNameA(String mvpNameA, int bs) {
		this.mvpNameA = mvpNameA;
	}

	/**
		a战场1积分
	*/
	private int aBattlefieldScore1;
	public int getABattlefieldScore1(){
		return aBattlefieldScore1;
	}
	public void setABattlefieldScore1(int aBattlefieldScore1) {
		this.aBattlefieldScore1 = aBattlefieldScore1;
		index = 7;
		result += index + "*int*" + aBattlefieldScore1 + "#";
	}

	public void setABattlefieldScore1(int aBattlefieldScore1, int bs) {
		this.aBattlefieldScore1 = aBattlefieldScore1;
	}

	/**
		a战场2积分
	*/
	private int aBattlefieldScore2;
	public int getABattlefieldScore2(){
		return aBattlefieldScore2;
	}
	public void setABattlefieldScore2(int aBattlefieldScore2) {
		this.aBattlefieldScore2 = aBattlefieldScore2;
		index = 8;
		result += index + "*int*" + aBattlefieldScore2 + "#";
	}

	public void setABattlefieldScore2(int aBattlefieldScore2, int bs) {
		this.aBattlefieldScore2 = aBattlefieldScore2;
	}

	/**
		a战场3积分
	*/
	private int aBattlefieldScore3;
	public int getABattlefieldScore3(){
		return aBattlefieldScore3;
	}
	public void setABattlefieldScore3(int aBattlefieldScore3) {
		this.aBattlefieldScore3 = aBattlefieldScore3;
		index = 9;
		result += index + "*int*" + aBattlefieldScore3 + "#";
	}

	public void setABattlefieldScore3(int aBattlefieldScore3, int bs) {
		this.aBattlefieldScore3 = aBattlefieldScore3;
	}

	/**
		a战场4积分
	*/
	private int aBattlefieldScore4;
	public int getABattlefieldScore4(){
		return aBattlefieldScore4;
	}
	public void setABattlefieldScore4(int aBattlefieldScore4) {
		this.aBattlefieldScore4 = aBattlefieldScore4;
		index = 10;
		result += index + "*int*" + aBattlefieldScore4 + "#";
	}

	public void setABattlefieldScore4(int aBattlefieldScore4, int bs) {
		this.aBattlefieldScore4 = aBattlefieldScore4;
	}

	/**
		队伍B
	*/
	private int teamB;
	public int getTeamB(){
		return teamB;
	}
	public void setTeamB(int teamB) {
		this.teamB = teamB;
		index = 11;
		result += index + "*int*" + teamB + "#";
	}

	public void setTeamB(int teamB, int bs) {
		this.teamB = teamB;
	}

	/**
		联盟B名字
	*/
	private String teamNameB;
	public String getTeamNameB(){
		return teamNameB;
	}
	public void setTeamNameB(String teamNameB) {
		this.teamNameB = teamNameB;
		index = 12;
		result += index + "*String*" + teamNameB + "#";
	}

	public void setTeamNameB(String teamNameB, int bs) {
		this.teamNameB = teamNameB;
	}

	/**
		联盟B的MVP名字
	*/
	private String mvpNameB;
	public String getMvpNameB(){
		return mvpNameB;
	}
	public void setMvpNameB(String mvpNameB) {
		this.mvpNameB = mvpNameB;
		index = 13;
		result += index + "*String*" + mvpNameB + "#";
	}

	public void setMvpNameB(String mvpNameB, int bs) {
		this.mvpNameB = mvpNameB;
	}

	/**
		b战场1积分
	*/
	private int bBattlefieldScore1;
	public int getBBattlefieldScore1(){
		return bBattlefieldScore1;
	}
	public void setBBattlefieldScore1(int bBattlefieldScore1) {
		this.bBattlefieldScore1 = bBattlefieldScore1;
		index = 14;
		result += index + "*int*" + bBattlefieldScore1 + "#";
	}

	public void setBBattlefieldScore1(int bBattlefieldScore1, int bs) {
		this.bBattlefieldScore1 = bBattlefieldScore1;
	}

	/**
		b战场2积分
	*/
	private int bBattlefieldScore2;
	public int getBBattlefieldScore2(){
		return bBattlefieldScore2;
	}
	public void setBBattlefieldScore2(int bBattlefieldScore2) {
		this.bBattlefieldScore2 = bBattlefieldScore2;
		index = 15;
		result += index + "*int*" + bBattlefieldScore2 + "#";
	}

	public void setBBattlefieldScore2(int bBattlefieldScore2, int bs) {
		this.bBattlefieldScore2 = bBattlefieldScore2;
	}

	/**
		b战场3积分
	*/
	private int bBattlefieldScore3;
	public int getBBattlefieldScore3(){
		return bBattlefieldScore3;
	}
	public void setBBattlefieldScore3(int bBattlefieldScore3) {
		this.bBattlefieldScore3 = bBattlefieldScore3;
		index = 16;
		result += index + "*int*" + bBattlefieldScore3 + "#";
	}

	public void setBBattlefieldScore3(int bBattlefieldScore3, int bs) {
		this.bBattlefieldScore3 = bBattlefieldScore3;
	}

	/**
		b战场4积分
	*/
	private int bBattlefieldScore4;
	public int getBBattlefieldScore4(){
		return bBattlefieldScore4;
	}
	public void setBBattlefieldScore4(int bBattlefieldScore4) {
		this.bBattlefieldScore4 = bBattlefieldScore4;
		index = 17;
		result += index + "*int*" + bBattlefieldScore4 + "#";
	}

	public void setBBattlefieldScore4(int bBattlefieldScore4, int bs) {
		this.bBattlefieldScore4 = bBattlefieldScore4;
	}

	/**
		
	*/
	private int version;
	public int getVersion(){
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
		index = 18;
		result += index + "*int*" + version + "#";
	}

	public void setVersion(int version, int bs) {
		this.version = version;
	}

	public String getResult(){
		return result;
	}

	public InstUnionWarAgainst clone(){
		InstUnionWarAgainst extend=new InstUnionWarAgainst();
		extend.setId(this.id);
		extend.setAgainstIndex(this.againstIndex);
		extend.setIsAWin(this.isAWin);
		extend.setTeamA(this.teamA);
		extend.setTeamNameA(this.teamNameA);
		extend.setMvpNameA(this.mvpNameA);
		extend.setABattlefieldScore1(this.aBattlefieldScore1);
		extend.setABattlefieldScore2(this.aBattlefieldScore2);
		extend.setABattlefieldScore3(this.aBattlefieldScore3);
		extend.setABattlefieldScore4(this.aBattlefieldScore4);
		extend.setTeamB(this.teamB);
		extend.setTeamNameB(this.teamNameB);
		extend.setMvpNameB(this.mvpNameB);
		extend.setBBattlefieldScore1(this.bBattlefieldScore1);
		extend.setBBattlefieldScore2(this.bBattlefieldScore2);
		extend.setBBattlefieldScore3(this.bBattlefieldScore3);
		extend.setBBattlefieldScore4(this.bBattlefieldScore4);
		extend.setVersion(this.version);
		return extend;
	}
}
