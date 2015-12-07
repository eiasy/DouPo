package mmo.module.cache.role;

import mmo.module.gm.bean.TreeNode;

final public class CacheRole extends CacheRoleFight {
	private TreeNode complexData = new TreeNode("复合数据");
	private TreeNode baseData    = new TreeNode("基础数据");
	private int      gameId;
	private String   gameName;
	private int      serverId;
	private String   serverName;
	/************************************** 存储标识位 ******/
	/** 需要持久化到数据库 */
	boolean          toPersist;
	/************************************** 角色基本属性字段 ******/
	String           userId;
	short            accuse;
	String           realm;
	int              experience;
	short            evilPK;

	String           gamehonor;
	int              hp;
	short            longevity;
	int              sceneId;
	int              onlinetime;
	int              pkf;
	int              pks;
	int              pkContinueWin;
	int              arenaIntegral;
	String           sex;
	String           dbState;
	short            tileX;
	short            tileY;
	short            kidney;
	long             sectId;
	short            vipLevel;
	int              integral;
	int              chargeTotal;
	int              layer;
	int              reliveScene;
	String           shopTimer;
	String           freeze;
	int              maxFighting;
	String           levelupFirstTime;
	int              sectCreateCount;
	short            virtualVIPLevel;
	String           virtualVIPexpiredTime;

	int              oneStandIntegral;
	int              leaderIntegral;
	/***************************************************************************** 时间 ******/
	String           timeCreateRole;
	String           timeLastLogin;
	String           timeLastOffline;

	/***********************************************************************************/
	int              channelId;
	String           permit;
	int              rmbMoney;
	int              gameMoney;
	int              xiuWei;
	long             forbidChatTime;
	String           sceneName;
	String           onlineState;

	public CacheRole() {

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public short getAccuse() {
		return accuse;
	}

	public void setAccuse(short accuse) {
		this.accuse = accuse;
	}

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public short getEvilPK() {
		return evilPK;
	}

	public void setEvilPK(short evilPK) {
		this.evilPK = evilPK;
	}

	public String getGamehonor() {
		return gamehonor;
	}

	public void setGamehonor(String gamehonor) {
		this.gamehonor = gamehonor;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public short getLongevity() {
		return longevity;
	}

	public void setLongevity(short longevity) {
		this.longevity = longevity;
	}

	public int getSceneId() {
		return sceneId;
	}

	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}

	public int getOnlinetime() {
		return onlinetime;
	}

	public void setOnlinetime(int onlinetime) {
		this.onlinetime = onlinetime;
	}

	public int getPkf() {
		return pkf;
	}

	public void setPkf(int pkf) {
		this.pkf = pkf;
	}

	public int getPks() {
		return pks;
	}

	public void setPks(int pks) {
		this.pks = pks;
	}

	public int getPkContinueWin() {
		return pkContinueWin;
	}

	public void setPkContinueWin(int pkContinueWin) {
		this.pkContinueWin = pkContinueWin;
	}

	public int getArenaIntegral() {
		return arenaIntegral;
	}

	public void setArenaIntegral(int arenaIntegral) {
		this.arenaIntegral = arenaIntegral;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDbState() {
		return dbState;
	}

	public void setDbState(String dbState) {
		this.dbState = dbState;
	}

	public short getTileX() {
		return tileX;
	}

	public void setTileX(short tileX) {
		this.tileX = tileX;
	}

	public short getTileY() {
		return tileY;
	}

	public void setTileY(short tileY) {
		this.tileY = tileY;
	}

	public short getKidney() {
		return kidney;
	}

	public void setKidney(short kidney) {
		this.kidney = kidney;
	}

	public long getSectId() {
		return sectId;
	}

	public void setSectId(long sectId) {
		this.sectId = sectId;
	}

	public short getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(short vipLevel) {
		this.vipLevel = vipLevel;
	}

	public int getIntegral() {
		return integral;
	}

	public void setIntegral(int integral) {
		this.integral = integral;
	}

	public int getChargeTotal() {
		return chargeTotal;
	}

	public void setChargeTotal(int chargeTotal) {
		this.chargeTotal = chargeTotal;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getReliveScene() {
		return reliveScene;
	}

	public void setReliveScene(int reliveScene) {
		this.reliveScene = reliveScene;
	}

	public String getShopTimer() {
		return shopTimer;
	}

	public void setShopTimer(String shopTimer) {
		this.shopTimer = shopTimer;
	}

	public String getFreeze() {
		return freeze;
	}

	public void setFreeze(String freeze) {
		this.freeze = freeze;
	}

	public int getMaxFighting() {
		return maxFighting;
	}

	public void setMaxFighting(int maxFighting) {
		this.maxFighting = maxFighting;
	}

	public String getLevelupFirstTime() {
		return levelupFirstTime;
	}

	public void setLevelupFirstTime(String levelupFirstTime) {
		this.levelupFirstTime = levelupFirstTime;
	}

	public int getSectCreateCount() {
		return sectCreateCount;
	}

	public void setSectCreateCount(int sectCreateCount) {
		this.sectCreateCount = sectCreateCount;
	}

	public short getVirtualVIPLevel() {
		return virtualVIPLevel;
	}

	public void setVirtualVIPLevel(short virtualVIPLevel) {
		this.virtualVIPLevel = virtualVIPLevel;
	}

	public String getVirtualVIPexpiredTime() {
		return virtualVIPexpiredTime;
	}

	public void setVirtualVIPexpiredTime(String virtualVIPexpiredTime) {
		this.virtualVIPexpiredTime = virtualVIPexpiredTime;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getPermit() {
		return permit;
	}

	public void setPermit(String permit) {
		this.permit = permit;
	}

	public int getRmbMoney() {
		return rmbMoney;
	}

	public void setRmbMoney(int lingShi) {
		this.rmbMoney = lingShi;
	}

	public int getGameMoney() {
		return gameMoney;
	}

	public void setGameMoney(int gameMoney) {
		this.gameMoney = gameMoney;
	}

	public int getOneStandIntegral() {
		return oneStandIntegral;
	}

	public void setOneStandIntegral(int oneStandIntegral) {
		this.oneStandIntegral = oneStandIntegral;
	}

	public int getLeaderIntegral() {
		return leaderIntegral;
	}

	public void setLeaderIntegral(int leaderIntegral) {
		this.leaderIntegral = leaderIntegral;
	}

	public long getForbidChatTime() {
		return forbidChatTime;
	}

	public void setForbidChatTime(long forbidChatTime) {
		this.forbidChatTime = forbidChatTime;
	}

	@Override
	public String toString() {
		return "CacheRole [roleId=" + roleId + ", realm=" + realm + ", hp=" + hp + ", level=" + level + ", sumHp=" + sumHp + "]";
	}

	public int getXiuWei() {
		return xiuWei;
	}

	public void setXiuWei(int xiuWei) {
		this.xiuWei = xiuWei;
	}

	public String getTimeCreateRole() {
		return timeCreateRole;
	}

	public void setTimeCreateRole(String timeCreateRole) {
		this.timeCreateRole = timeCreateRole;
	}

	public String getTimeLastLogin() {
		return timeLastLogin;
	}

	public void setTimeLastLogin(String timeLastLogin) {
		this.timeLastLogin = timeLastLogin;
	}

	public String getTimeLastOffline() {
		return timeLastOffline;
	}

	public void setTimeLastOffline(String timeLastOffline) {
		this.timeLastOffline = timeLastOffline;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(String onlineState) {
		this.onlineState = onlineState;
	}

	public TreeNode getGoodsBag() {
		return complexData;
	}

	public void setGoodsBag(TreeNode goodsBag) {
		this.complexData = goodsBag;
	}

	public TreeNode getBaseData() {
		return baseData;
	}

	public void setBaseData(TreeNode baseData) {
		this.baseData = baseData;
	}
}
