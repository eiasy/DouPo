package mmo.common.bean.role;

/**
 * 记录角色某事件的次数
 * 
 * @author 肖琼
 * 
 */
public class RoleThingsCount {
	/** 角色进行3-5星抽卡的次数 */
	private int pick3To5PetCardCount = 0;
	/** 角色死亡次数 */
	private int deathCount           = 0;
	/** 角色原地复活次数 */
	private int localReliveCount     = 0;
	/** 角色进行斗法总次数 */
	private int douFaTotalCount      = 0;
	/** 角色进行斗法胜利次数 */
	private int douFaWinCount        = 0;
	/** 角色挑战世界首领次数 */
	private int worldBossCount       = 0;
	/** 角色挑战一战到底次数 */
	private int oneStandCount        = 0;
	/** 角色寻宝次数 */
	private int findTreasureCount    = 0;
	/** 角色开启藏宝图次数 */
	private int openTreasureMapCount = 0;
	/** 角色开启秘境次数 */
	private int openSecretCount      = 0;
	/** 最大连胜次数 */
	private int maxContinueWinCount  = 0;
	/** 累积充值元宝数量 */
	private int totalRechargeYuanBao = 0;
	/** 抢夺成功次数 */
	private int grabSuccessCount;
	/** 抢夺失败次数 */
	private int grabFailCount;
	/** 指定法宝台NPC点击次数 */
	private int fabaoStageClickCount = 0;

	/** 炼化成功次数 */
	private int compositeSuccessCount;
	/** 炼化失败次数 */
	private int compositeFailCount;
	/** 合成伙伴碎片次数 */
	private int compositePetSuccessCount;

	public int getGrabSuccessCount() {
		return grabSuccessCount;
	}

	public void grabSuccess() {
		grabSuccessCount++;
	}

	public void grabFail() {
		grabFailCount++;
	}

	public void setGrabSuccessCount(int grabSuccessCount) {
		this.grabSuccessCount = grabSuccessCount;
	}

	public int getGrabFailCount() {
		return grabFailCount;
	}

	public void setGrabFailCount(int grabFailCount) {
		this.grabFailCount = grabFailCount;
	}

	public int getCompositeFailCount() {
		return compositeFailCount;
	}

	public void setCompositeFailCount(int compositeFailCount) {
		this.compositeFailCount = compositeFailCount;
	}

	public int getCompositeSuccessCount() {
		return compositeSuccessCount;
	}

	public void setCompositeSuccessCount(int compositeCount) {
		this.compositeSuccessCount = compositeCount;
	}

	public void compositeSuccess() {
		this.compositeSuccessCount++;
	}

	public int getCompositePetSuccessCount() {
		return compositePetSuccessCount;
	}

	public void setCompositePetSuccessCount(int compositeCount) {
		this.compositePetSuccessCount = compositeCount;
	}

	public void compositePetSuccess() {
		this.compositePetSuccessCount++;
	}

	public void compositeFail() {
		this.compositeFailCount++;
	}

	public RoleThingsCount() {
	}

	public void release() {

	}

	public int getPick3To5PetCardCount() {
		return pick3To5PetCardCount;
	}

	public void setPick3To5PetCardCount(int pick3To5PetCardCount) {
		this.pick3To5PetCardCount = pick3To5PetCardCount;
	}

	public int addSelfPick3To5PetCardCount() {
		return ++this.pick3To5PetCardCount;
	}

	public int getDeathCount() {
		return deathCount;
	}

	public void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}

	public int addSelfDeathCount() {
		return ++this.deathCount;
	}

	public int getLocalReliveCount() {
		return localReliveCount;
	}

	public void setLocalReliveCount(int localReliveCount) {
		this.localReliveCount = localReliveCount;
	}

	public int addSelfLocalReliveCount() {
		return ++this.localReliveCount;
	}

	public int getDouFaTotalCount() {
		return douFaTotalCount;
	}

	public void setDouFaTotalCount(int douFaTotalCount) {
		this.douFaTotalCount = douFaTotalCount;
	}

	public int addSelfDouFaTotalCount() {
		return ++this.douFaTotalCount;
	}

	public int getDouFaWinCount() {
		return douFaWinCount;
	}

	public void setDouFaWinCount(int douFaWinCount) {
		this.douFaWinCount = douFaWinCount;
	}

	public int addSelfDouFaWinCount() {
		return ++this.douFaWinCount;
	}

	public int getWorldBossCount() {
		return worldBossCount;
	}

	public void setWorldBossCount(int worldBossCount) {
		this.worldBossCount = worldBossCount;
	}

	public int addSelfWorldBossCount() {
		return ++this.worldBossCount;
	}

	public int getOneStandCount() {
		return oneStandCount;
	}

	public void setOneStandCount(int oneStandCount) {
		this.oneStandCount = oneStandCount;
	}

	public int addSelfOneStandCount() {
		return ++this.oneStandCount;
	}

	public int getFindTreasureCount() {
		return findTreasureCount;
	}

	public void setFindTreasureCount(int findTreasureCount) {
		this.findTreasureCount = findTreasureCount;
	}

	public int addSelfFindTreasureCount() {
		return ++this.findTreasureCount;
	}

	public int getOpenTreasureMapCount() {
		return openTreasureMapCount;
	}

	public void setOpenTreasureMapCount(int openTreasureMapCount) {
		this.openTreasureMapCount = openTreasureMapCount;
	}

	public int addOpenTreasureMapCount() {
		return ++this.openTreasureMapCount;
	}

	public int getOpenSecretCount() {
		return openSecretCount;
	}

	public void setOpenSecretCount(int openSecretCount) {
		this.openSecretCount = openSecretCount;
	}

	public int addOpenSecretCount() {
		return ++this.openSecretCount;
	}

	public int getMaxContinueWinCount() {
		return maxContinueWinCount;
	}

	public void setMaxContinueWinCount(int maxContinueWinCount) {
		if (maxContinueWinCount > this.maxContinueWinCount) {
			this.maxContinueWinCount = maxContinueWinCount;
		}
	}

	public int getTotalRechargeYuanBao() {
		return totalRechargeYuanBao;
	}

	public void setTotalRechargeYuanBao(int totalRechargeYuanBao) {
		this.totalRechargeYuanBao = totalRechargeYuanBao;
	}

	public int getFabaoStageClickCount() {
		return fabaoStageClickCount;
	}

	public void setFabaoStageClickCount(int fabaoStageClickCount) {
		this.fabaoStageClickCount = fabaoStageClickCount;
	}

	public void clickFabaoStage() {
		this.fabaoStageClickCount++;
	}

}
