package mmo.module.cache.role;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import mmo.common.config.SpeedConfig;
import mmo.tools.log.LoggerError;

import org.apache.mina.core.buffer.IoBuffer;

public class CacheRole extends CacheRoleFight {
	/** 角色对象ID */
	long         objectId;
	/************************************** 存储标识位 ******/
	/** 需要持久化到数据库 */
	boolean      toPersist;
	/************************************** 角色基本属性字段 ******/
	String       userId;
	byte         realm;
	int          experience;

	short        gamehonor;
	int          hp;
	int          sceneId;
	int          onlinetime;
	int          arenaIntegral;
	byte         sex;
	byte         dbState;
	short        tileX;
	short        tileY;
	IoBuffer     roleData;
	long         sectId;
	short        vipLevel;
	int          integral;
	int          layer;
	int          reliveScene;
	long         shopTimer;
	long         freeze;
	int          maxFighting;
	long         levelupFirstTime;
	short        virtualVIPLevel;
	long         virtualVIPexpiredTime;

	int          oneStandIntegral;
	int          leaderIntegral;
	/***************************************************************************** 时间 ******/
	Timestamp    timeCreateRole;
	Timestamp    timeLastLogin;
	Timestamp    timeLastOffline;

	/***********************************************************************************/
	int          channelId;
	String       permit;
	int          belongto;
	String       channelSub;
	int          rmbMoney;
	int          gameMoney;
	int          xiuWei;
	long         forbidChatTime;
	/** 归属服务器 */
	int          belongServer;
	/******************************************************************************* GM指令 ***/
	List<String> gmCmds = new ArrayList<String>();

	public CacheRole() {

	}

	public long getObjectId() {
		return objectId;
	}

	public void setObjectId(long objectId) {
		this.objectId = objectId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public byte getRealm() {
		return realm;
	}

	public void setRealm(byte realm) {
		this.realm = realm;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public Timestamp getTimeCreateRole() {
		return timeCreateRole;
	}

	public void setTimeCreateRole(Timestamp firsttime) {
		this.timeCreateRole = firsttime;
	}

	public short getGamehonor() {
		return gamehonor;
	}

	public void setGamehonor(short gamehonor) {
		this.gamehonor = gamehonor;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
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

	public int getArenaIntegral() {
		return arenaIntegral;
	}

	public void setArenaIntegral(int arenaIntegral) {
		this.arenaIntegral = arenaIntegral;
	}

	public byte getSex() {
		return sex;
	}

	public void setSex(byte sex) {
		this.sex = sex;
	}

	public byte getDbState() {
		return dbState;
	}

	public void setDbState(byte dbState) {
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

	public IoBuffer getRoleData() {
		return roleData;
	}

	public void setRoleData(Blob roleDatas) {
		if (roleDatas == null) {
			return;
		}
		InputStream is = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			is = roleDatas.getBinaryStream();
			bis = new BufferedInputStream(is);
			dis = new DataInputStream(bis);
			byte[] data = new byte[dis.available()];
			dis.read(data);
			roleData = IoBuffer.getPacketBuffer();
			roleData.put(data);
			roleData.flip();
		} catch (Exception e) {
			LoggerError.error("加载角色大数据报错", e);
		}
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

	public long getShopTimer() {
		return shopTimer;
	}

	public void setShopTimer(long shopTimer) {
		this.shopTimer = shopTimer;
	}

	public Timestamp getTimeLastOffline() {
		return timeLastOffline;
	}

	public void setTimeLastOffline(Timestamp lastOffline) {
		this.timeLastOffline = lastOffline;
	}

	public long getFreeze() {
		return freeze;
	}

	public void setFreeze(long freeze) {
		this.freeze = freeze;
	}

	public int getMaxFighting() {
		return maxFighting;
	}

	public void setMaxFighting(int maxFighting) {
		this.maxFighting = maxFighting;
	}

	public long getLevelupFirstTime() {
		return levelupFirstTime;
	}

	public void setLevelupFirstTime(long levelupFirstTime) {
		this.levelupFirstTime = levelupFirstTime;
	}

	public Timestamp getTimeLastLogin() {
		return timeLastLogin;
	}

	public void setTimeLastLogin(Timestamp lastLogin) {
		this.timeLastLogin = lastLogin;
	}

	public short getVirtualVIPLevel() {
		return virtualVIPLevel;
	}

	public void setVirtualVIPLevel(short virtualVIPLevel) {
		this.virtualVIPLevel = virtualVIPLevel;
	}

	public long getVirtualVIPexpiredTime() {
		return virtualVIPexpiredTime;
	}

	public void setVirtualVIPexpiredTime(long virtualVIPexpiredTime) {
		this.virtualVIPexpiredTime = virtualVIPexpiredTime;
	}

	public boolean isFirstLogin() {
		return timeLastLogin == null || timeLastLogin.getTime() < 1;
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

	public void setRoleData(IoBuffer roleData) {
		this.roleData = roleData;
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
		return "CacheRole [roleId=" + roleId + ", realm=" + realm + ", hp=" + hp + ", level=" + level + "]";
	}

	public int getXiuWei() {
		return xiuWei;
	}

	public void setXiuWei(int xiuWei) {
		this.xiuWei = xiuWei;
	}

	public void setPixelXY(int x, int y) {
		if (x < 0 || y < 0) {
			return;
		}
		this.tileX = (short) ((x - SpeedConfig.HALF_TILE) / SpeedConfig.TILE_SIZE);
		this.tileY = (short) ((y - SpeedConfig.HALF_TILE) / SpeedConfig.TILE_SIZE);
	}

	public void addGmCmd(String gmCmd) {
		if (this.gmCmds == null) {
			this.gmCmds = new ArrayList<String>();
		}
		this.gmCmds.add(gmCmd);
	}

	public List<String> getGmCmds() {
		return gmCmds;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public int getBelongto() {
		return belongto;
	}

	public void setBelongto(int belongto) {
		this.belongto = belongto;
	}

	public int getBelongServer() {
		return belongServer;
	}

	public void setBelongServer(int belongServer) {
		this.belongServer = belongServer;
	}
}
