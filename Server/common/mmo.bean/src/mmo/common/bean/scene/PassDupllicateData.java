package mmo.common.bean.scene;

import java.util.List;

public class PassDupllicateData {
	private byte          model        = 0;    // 模式
	private long          enterTime    = 0;    // 进入副本的时间
	private long          overTime     = 0;    // 结束时间,不存大字段
//	private int           mapIdCity    = 0;    // 进入副本前场景ID
//	private int           layerIdCity  = 0;    // 进入副本前的图层ID
//	private short         tileXCity    = 0;    // 进入副本前X坐标
//	private short         tileYCity    = 0;    // 进入副本前Y坐标

	/** 副本所在区域的ID */
	private int           areaId;
	/** 副本所属关卡ID */
	private int           dupGateId;
	/** 副本入口场景的ID */
	private int           dupSceneId   = 0;    // 副本场景ID
	private int           challenge    = 0;    // 被挑战者,一战到底中用作积分记录
	private int           nextMap      = 0;    // 下一个场景ID，不存大字段
	private int           dupHardLevel = 0;    // 副本难度,一战到底中用作层数
	/** 副本开启等级 */
	private int           dupOpenLevel;
	private int           helpRoleId;
	private int           helpPetId;
	private short         helpPetLevel;
	/** 秘境关卡的动态ID */
	private int           secretGateBindId;
	/** 该副本奖励已领取,也用作是否打坐寻路 */
	private boolean       awarded      = false;
	/** 该副本是否是BOSS副本 1是0不是 */
	private byte          isBoss       = 0;
	/** 秘境普通关卡的关卡ID */
	private int           commonGateId = 0;
	/** 抢夺宝物的ID */
	private int           grabGoodsId;
	/** 自由挑战对手整容序号 */
	private List<Integer> targetSerial = null;
	/** 是否胜利 */
	private boolean       resultSuccess;

	private int           myFight;
	private int           targetFight;

	public int getMyFight() {
		return myFight;
	}

	public void setMyFight(int myFight) {
		this.myFight = myFight;
	}

	public int getTargetFight() {
		return targetFight;
	}

	public void setTargetFight(int targetFight) {
		this.targetFight = targetFight;
	}

	public boolean isResultSuccess() {
		return resultSuccess;
	}

	public void setResultSuccess(boolean resultSuccess) {
		this.resultSuccess = resultSuccess;
	}

	public int getGrabGoodsId() {
		return grabGoodsId;
	}

	public void setGrabGoodsId(int grabGoodsId) {
		this.grabGoodsId = grabGoodsId;
	}

	public PassDupllicateData() {

	}

	public int getDupOpenLevel() {
		return dupOpenLevel;
	}

	public PassDupllicateData(byte model, long enterTime, int areaId, int dupGateId, int dupSceneId, int dupOpenLevel) {
		this.model = model;
		this.enterTime = enterTime;
		this.areaId = areaId;
		this.dupGateId = dupGateId;
		this.dupSceneId = dupSceneId;
		this.nextMap = dupSceneId;
		this.dupOpenLevel = dupOpenLevel;
	}

	public int getAreaId() {
		return areaId;
	}

	public int getDupGateId() {
		return dupGateId;
	}

	public int getSecretGateBindId() {
		return secretGateBindId;
	}

	public void setSecretGateBindId(int secretGateBindId) {
		this.secretGateBindId = secretGateBindId;
	}

	public void release() {

	}

	public byte getModel() {
		return model;
	}

	public void setModel(byte model) {
		this.model = model;
	}

	public long getEnterTime() {
		return enterTime;
	}

	public void setEnterTime(long enterTime) {
		this.enterTime = enterTime;
	}

	public int getDupSceneId() {
		return dupSceneId;
	}

	public int getChallenge() {
		return challenge;
	}

	public void setChallenge(int challenge) {
		this.challenge = challenge;
	}

	public void addHelp(int helpRoleId, int helpPetId, short helpPetLevel) {
		this.helpPetId = helpPetId;
		this.helpPetLevel = helpPetLevel;
		this.helpRoleId = helpRoleId;
	}

	public long getOverTime() {
		return overTime;
	}

	public void setOverTime(long overTime) {
		this.overTime = overTime;
	}

	public int getNextMap() {
		return nextMap;
	}

	public void setNextMap(int nextMap) {
		this.nextMap = nextMap;
	}

	public int getDupHardLevel() {
		return dupHardLevel;
	}

	public void setDupHardLevel(int dupHardLevel) {
		this.dupHardLevel = dupHardLevel;
	}

	public int getHelpRoleId() {
		return helpRoleId;
	}

	public int getHelpPetId() {
		return helpPetId;
	}

	public short getHelpPetLevel() {
		return helpPetLevel;
	}

	public void setHelpRoleId(int helpRoleId) {
		this.helpRoleId = helpRoleId;
	}

	public void setHelpPetId(int helpPetId) {
		this.helpPetId = helpPetId;
	}

	public void setHelpPetLevel(short helpPetLevel) {
		this.helpPetLevel = helpPetLevel;
	}

	public boolean isAwarded() {
		return awarded;
	}

	public void setAwarded(boolean awarded) {
		this.awarded = awarded;
	}

	public byte getIsBoss() {
		return isBoss;
	}

	public void setIsBoss(byte isBoss) {
		this.isBoss = isBoss;
	}

	public int getCommonGateId() {
		return commonGateId;
	}

	public void setCommonGateId(int commonGateId) {
		this.commonGateId = commonGateId;
	}

	public List<Integer> getTargetSerial() {
		return targetSerial;
	}

	public void setTargetSerial(List<Integer> targetSerial) {
		this.targetSerial = targetSerial;
	}

}
