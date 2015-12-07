package mmo.module.gm.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mmo.module.cache.role.ChargeLog;
import mmo.module.cache.role.RoleEmail;
import mmo.module.cache.role.RoleRelation;

public class AccountRole {
	private int                gameId;
	private String             gameName;
	private int                serverId;
	private String             serverName;
	/***************************/
	private int                accountId;
	private String             userId;
	/***************************/
	private int                roleId;
	private String             roleName;
	private short              level;
	private int                experience;
	private String             scene;
	private String             timeCreate;
	private String             timeOffline;
	private byte               onlineState;
	private byte               dbState;
	private short              vipLevel;
	private int                channelId;
	private byte               profession;
	private byte               realm;
	private int                lingShi;
	private int                yuanBao;
	private long               freeze;
	private short              mansionLevel;
	private List<RoleRelation> myFriend   = new ArrayList<RoleRelation>();
	private List<RoleRelation> joinMe     = new ArrayList<RoleRelation>();
	private List<RoleRelation> requestMe  = new ArrayList<RoleRelation>();
	private List<RoleRelation> myRequest  = new ArrayList<RoleRelation>();
	private List<RoleEmail>    emails     = new ArrayList<RoleEmail>();
	private List<ChargeLog>    chargeLogs = new ArrayList<ChargeLog>();

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public byte getProfession() {
		return profession;
	}

	public void setProfession(byte profession) {
		this.profession = profession;
	}

	public byte getRealm() {
		return realm;
	}

	public void setRealm(byte realm) {
		this.realm = realm;
	}

	public int getLingShi() {
		return lingShi;
	}

	public void setLingShi(int lingShi) {
		this.lingShi = lingShi;
	}

	public int getYuanBao() {
		return yuanBao;
	}

	public void setYuanBao(int yuanBao) {
		this.yuanBao = yuanBao;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getScene() {
		return scene;
	}

	public void setScene(String scene) {
		this.scene = scene;
	}

	public String getTimeCreate() {
		return timeCreate;
	}

	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}

	public String getTimeOffline() {
		return timeOffline;
	}

	public void setTimeOffline(String timeOffline) {
		this.timeOffline = timeOffline;
	}

	public byte getOnlineState() {
		return onlineState;
	}

	public void setOnlineState(byte onlineState) {
		this.onlineState = onlineState;
	}

	public byte getDbState() {
		return dbState;
	}

	public void setDbState(byte dbState) {
		this.dbState = dbState;
	}

	public short getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(short vipLevel) {
		this.vipLevel = vipLevel;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
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

	public long getFreeze() {
		return freeze;
	}

	public void setFreeze(long freeze) {
		this.freeze = freeze;
	}

	public List<RoleRelation> getMyFriend() {
		return myFriend;
	}

	public void setMyFriend(List<RoleRelation> myFriend) {
		this.myFriend = myFriend;
	}

	public List<RoleRelation> getJoinMe() {
		return joinMe;
	}

	public void setJoinMe(List<RoleRelation> joinMe) {
		this.joinMe = joinMe;
	}

	public List<RoleRelation> getRequestMe() {
		return requestMe;
	}

	public void setRequestMe(List<RoleRelation> requestMe) {
		this.requestMe = requestMe;
	}

	public List<RoleRelation> getMyRequest() {
		return myRequest;
	}

	public void setMyRequest(List<RoleRelation> myRequest) {
		this.myRequest = myRequest;
	}

	public List<RoleEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<RoleEmail> emails) {
		this.emails = emails;
	}

	public List<ChargeLog> getChargeLogs() {
		return chargeLogs;
	}

	public void setChargeLogs(List<ChargeLog> chargeLogs) {
		this.chargeLogs = chargeLogs;
	}

	public short getMansionLevel() {
		return mansionLevel;
	}

	public void setMansionLevel(short mansionLevel) {
		this.mansionLevel = mansionLevel;
	}

	public static class Realm {
		/** 境界数量 */
		public static final byte               count     = 5;
		/** 凡人 */
		public static final byte               FanRen_0  = 0;
		/** 炼气 */
		public static final byte               LianQi_1  = 1;
		/** 筑基 */
		public static final byte               ZhuJi_2   = 2;
		/** 结丹 */
		public static final byte               JieDan_3  = 3;
		/** 元婴 */
		public static final byte               YuanYing  = 4;
		/** 化神 */
		public static final byte               HuaSheng  = 5;
		/** 炼虚 */
		public static final byte               LianXu_6  = 6;
		/** 合体 */
		public static final byte               HeTi_7    = 7;
		/** 大乘 */
		public static final byte               DaCheng_8 = 8;

		private static final Map<Byte, String> names     = new HashMap<Byte, String>();
		static {
			names.put(FanRen_0, "凡人");
			names.put(LianQi_1, "炼气");
			names.put(ZhuJi_2, "筑基");
			names.put(JieDan_3, "结丹");
			names.put(YuanYing, "元婴");
			names.put(HuaSheng, "化神");
			names.put(LianXu_6, "炼虚");
			names.put(HeTi_7, "合体");
			names.put(DaCheng_8, "大乘");
		}

		public static final String getName(byte realm) {
			return names.get(realm);
		}
	}

	public static class Profession {
		/** 玄仙 */
		public final static byte               XuanXian_1       = 1;
		/** 修罗 */
		public final static byte               XiuLuo_2         = 2;
		/** 御灵 */
		public final static byte               YuLing_16        = 16;
		private static final Map<Byte, String> PROFESSION_NAMES = new HashMap<Byte, String>();
		static {
			PROFESSION_NAMES.put(XuanXian_1, "玄仙");
			PROFESSION_NAMES.put(XiuLuo_2, "修罗");
			PROFESSION_NAMES.put(YuLing_16, "御灵");
		}

		public static final String getProfessionName(byte key) {
			return PROFESSION_NAMES.get(key);
		}
	}

	public static class StateOnline {
		private static final String[] states = new String[] { "离线", "在线" };

		public final static String getStateOnline(int state) {
			if (state > -1 && state < states.length) {
				return states[state];
			}
			return "异常：" + state;
		}
	}

	public static final class StateDb {
		private static final String[] states = new String[] { "正常", "删除", "封号" };

		public final static String getStateOnline(int state) {
			if (state > -1 && state < states.length) {
				return states[state];
			}
			return "异常：" + state;
		}
	}
}
