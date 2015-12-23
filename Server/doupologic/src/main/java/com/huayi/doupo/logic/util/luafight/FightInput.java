package com.huayi.doupo.logic.util.luafight;

import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;

public class FightInput {

	boolean allowSpeed3 = false;
	boolean isPVE = false;
	boolean isSelfFirst = false;
	boolean isBoss = false;
	boolean allowSkipFight = true;
	boolean skipEmbattle = true;
	String bgImagePath0 = "image/backgroundWar/heijiaoyu02.png";
	String bgImagePath1 = "image/backgroundWar/heijiaoyu01.png";
	FightData fightDataA, fightDataB;

	public FightInput(FightData fdA, FightData fdB) {
		fightDataA = fdA;
		fightDataB = fdB;
	}

	public boolean isAllowSpeed3() {
		return allowSpeed3;
	}

	public void setAllowSpeed3(boolean allowSpeed3) {
		this.allowSpeed3 = allowSpeed3;
	}

	public boolean isPVE() {
		return isPVE;
	}

	public void setPVE(boolean isPVE) {
		this.isPVE = isPVE;
	}

	public boolean isSelfFirst() {
		return isSelfFirst;
	}

	public void setSelfFirst(boolean isSelfFirst) {
		this.isSelfFirst = isSelfFirst;
	}

	public boolean isBoss() {
		return isBoss;
	}

	public void setBoss(boolean isBoss) {
		this.isBoss = isBoss;
	}

	public boolean isAllowSkipFight() {
		return allowSkipFight;
	}

	public void setAllowSkipFight(boolean allowSkipFight) {
		this.allowSkipFight = allowSkipFight;
	}

	public boolean isSkipEmbattle() {
		return skipEmbattle;
	}

	public void setSkipEmbattle(boolean skipEmbattle) {
		this.skipEmbattle = skipEmbattle;
	}

	public String getBgImagePath0() {
		return bgImagePath0;
	}

	public void setBgImagePath0(String bgImagePath0) {
		this.bgImagePath0 = bgImagePath0;
	}

	public String getBgImagePath1() {
		return bgImagePath1;
	}

	public void setBgImagePath1(String bgImagePath1) {
		this.bgImagePath1 = bgImagePath1;
	}

	public LuaValue toLuaValue() {
		LuaValue initData = new LuaTable();
		initData.set("allowSpeed3", LuaValue.valueOf(allowSpeed3));
		initData.set("isPVE", LuaValue.valueOf(isPVE));
		initData.set("isSelfFirst", LuaValue.valueOf(fightDataA.getPower() > fightDataB.getPower()));
		initData.set("isBoss", LuaValue.valueOf(isBoss));
		initData.set("allowSkipFight", LuaValue.valueOf(allowSkipFight));
		initData.set("skipEmbattle", LuaValue.valueOf(skipEmbattle));
		initData.set("bgImagePath0", LuaValue.valueOf(bgImagePath0));
		initData.set("bgImagePath1", LuaValue.valueOf(bgImagePath1));
		initData.set("myData", fightDataA.toLua());

		LuaValue otherDatas = new LuaTable();
		initData.set("otherData", otherDatas);
		otherDatas.set(1, fightDataB.toLua());
		return initData;
	}
}
