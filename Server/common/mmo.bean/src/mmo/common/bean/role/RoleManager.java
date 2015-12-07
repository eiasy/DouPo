package mmo.common.bean.role;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import mmo.common.bean.notice.NoticeManager;
import mmo.common.bean.sect.SectRole;
import mmo.common.config.ClientCustom;
import mmo.common.config.role.RoleConfig;
import mmo.common.config.role.RoleGameState;
import mmo.common.config.role.RoleProfession;
import mmo.common.config.role.RoleRealmConfig;
import mmo.common.config.version.VersionConfig;
import mmo.common.protocol.game.CommonGamePropertyKey;
import mmo.common.protocol.game.UserProtocol;
import mmo.common.protocol.game.operate.ChatChannel;
import mmo.common.protocol.game.sub.SubPro_1045_friend;
import mmo.common.protocol.ui.ClientUI;
import mmo.common.protocol.ui.main.Main_0_account;
import mmo.common.protocol.ui.main.Main_1300_Rank;
import mmo.common.protocol.ui.main.Main_200_Equip;
import mmo.common.protocol.ui.main.Main_800_Social;
import mmo.common.system.GameParameter;
import mmo.module.rank.bean.RankData;
import mmo.tools.log.LoggerError;
import mmo.tools.log.LoggerFilter;
import mmo.tools.thread.heartbeat.AHeartbeat;
import mmo.tools.util.MathUtil;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class RoleManager extends AHeartbeat {
	private final static RoleMini                   DEFAULT_ROLE_MINI   = new RoleMini();
	private static final Object                     PUBLIC_VALUE        = new Object();
	/** 第一次挑战的机器人 */
	private static final List<Integer>              firstChallengeRobot = new ArrayList<Integer>();
	/** 第一次挑战的机器人 */
	private final static List<RankData>             challengeRoles      = new ArrayList<RankData>();
	/** 游戏中所有的怪物及NPC<key=角色ID，value角色对象> */
	private static Map<Integer, Role>               npc_monster_pet     = new ConcurrentHashMap<Integer, Role>();
	/** 用户池<key=角色ID，value角色对象> */
	private static Map<Integer, Role>               onlineRoles         = new ConcurrentHashMap<Integer, Role>();
	/** 在线机器人<key=角色ID，value角色对象> */
	private static Map<Integer, Role>               onlineRobots        = new ConcurrentHashMap<Integer, Role>();
	/** 名称和id之间的映射<key=用户名Hashcode，value=角色ID> */
	private static Map<String, Integer>             onlineRoleNames     = new ConcurrentHashMap<String, Integer>();
	/** 在线的帐号ID<key=账号ID,value=角色ID> */
	private static Map<Integer, Integer>            onlineUserIds       = new ConcurrentHashMap<Integer, Integer>();
	/** 已经创建的所有角色<key=角色ID，value=状态> */
	private static Map<Integer, RoleMini>           roleMiniInfo        = new ConcurrentHashMap<Integer, RoleMini>();
	/** RoleMini列表 */
	private static List<RoleMini>                   roleMiniList        = new ArrayList<RoleMini>(1000);
	/** 机器人角色<key=角色ID，value=状态> */
	private static Map<Integer, RoleMini>           roleMiniRobots      = new ConcurrentHashMap<Integer, RoleMini>();
	/** 已经占用的角色名称与角色的映射表<key=角色名Hashcode，value=角色ID> */
	private static Map<Integer, Integer>            name2roleId         = new ConcurrentHashMap<Integer, Integer>();
	/** 机器人已经占用的角色名称与角色的映射表<key=角色名Hashcode，value=角色ID> */
	private static Map<Integer, Integer>            robotname2roleId    = new ConcurrentHashMap<Integer, Integer>();
	/***/
	private static Map<Short, Map<Integer, Object>> roleLevelMap        = new ConcurrentHashMap<Short, Map<Integer, Object>>();
	/** 与账号关联的自定义数据-腾讯 */
	private static Map<Integer, String>             accountCustomData   = new ConcurrentHashMap<Integer, String>();
	private static IRoleService                     roleService         = null;

	private static IFriendManager                   friendManager       = new IFriendManager() {

		                                                                    @Override
		                                                                    public Friend getFriend(int roleId, String targetName) {
			                                                                    // TODO Auto-generated method
			                                                                    // stub
			                                                                    return null;
		                                                                    }

		                                                                    @Override
		                                                                    public Friend getFriend(int roleId, int targetId) {
			                                                                    // TODO Auto-generated method
			                                                                    // stub
			                                                                    return null;
		                                                                    }
	                                                                    };

	public static IFriendManager getFriendManager() {
		return friendManager;
	}

	public static void addChallengeRole(RankData roleData) {
		roleData.setAllChallenge(true);
		challengeRoles.add(roleData);
	}

	public static List<RankData> getChallengeroles() {
		return challengeRoles;
	}

	public final static void addFirstChallengeRobot(int roleId) {
		if (!firstChallengeRobot.contains(roleId)) {
			firstChallengeRobot.add(roleId);
		}
	}

	public final static List<Integer> getFirstChallengeRobots() {
		Collections.shuffle(firstChallengeRobot);
		return firstChallengeRobot;
	}

	public final static void addAccountCustomData(int accountId, String data) {
		if (data != null) {
			accountCustomData.put(accountId, data);
		}
	}

	public final static String getAccountCustomData(int accountId) {
		return accountCustomData.get(accountId);
	}

	public static void setFriendManager(IFriendManager friendManager) {
		if (friendManager != null) {
			RoleManager.friendManager = friendManager;
		}
	}

	public static void init(IRoleService roleService) {
		RoleManager.roleService = roleService;
		List<RoleMini> roleList = roleService.getRoleDIdByState();
		int rsize = roleList.size();
		for (int ri = 0; ri < rsize; ri++) {
			addRoleMini(roleList.get(ri));
		}
	}

	private static final void addRoleMini(RoleMini rm) {
		if (rm.getAccountId() < 1000) {
			roleMiniRobots.put(rm.getRoleId(), rm);
		} else {
			roleMiniInfo.put(rm.getRoleId(), rm);
			roleMiniList.add(rm);
		}
		name2roleId.put(rm.getRoleName().hashCode(), rm.getRoleId());
	}

	/**
	 * 添加一个世界唯一角色（NPC，怪物，宠物）
	 * 
	 * @param role
	 *            唯一的角色
	 */
	public static final void addWorldRole(Role role) {
		if (role == null) {
			return;
		}
		npc_monster_pet.put(role.getId(), role);
	}

	/**
	 * 释放世界唯一角色（NPC，怪物，宠物）
	 * 
	 * @param role
	 */
	public static final void releaseRole(Role role) {
		if (role == null) {
			return;
		}
		npc_monster_pet.remove(role.getId());

	}

	/**
	 * 通过世界唯一ID获取角色对象（玩家，NPC，怪物，宠物）
	 * 
	 * @param roleId
	 *            角色ID
	 * @return 角色对象
	 */
	public final static Role getWorldRole(int roleId) {
		Role role = npc_monster_pet.get(roleId);
		if (role == null) {
			role = getOLUserRole(roleId);
		}
		return role;
	}

	public static final boolean validateName(String name) {
		if (name == null) {
			return false;
		}
		return name2roleId.get(name.hashCode()) == null && robotname2roleId.get(name.hashCode()) == null;
	}

	public final static void addRobotName(String name) {
		robotname2roleId.put(name.hashCode(), Integer.MAX_VALUE);
	}

	public static final void addRoleName(Role role) {
		RoleMini rm = new RoleMini();
		rm.setRoleId(role.getId());
		rm.setRoleName(role.getUsername());
		rm.setDBState(role.getDbState());
		rm.setLevel(role.getLevel());
		rm.setVipLevel(role.getVipLevel());
		rm.setRealm(role.getRealm());
		rm.setSex(role.getSex());
		rm.setProfession(role.getProfession());
		rm.setAccountId(role.getAccountId());
		rm.setSkeletons(role.getSkeletons());
		rm.setFabaoAndSkill(role.getFaBaoAndSkill());
		rm.setPetInfo(role.getPointArrayPetInfo(Main_200_Equip.Main_224_Sub.doufa_1_array));
		rm.setSumAttack(role.getSumAttack());
		rm.setSumAttackChance(role.getSumDuckChance());
		rm.setSumCruel(role.getSumCruel());
		rm.setSumDefence(role.getSumDefence());
		rm.setSumDestroy(role.getSumDestroy());
		rm.setSumDuck(role.getSumDuckChance());
		rm.setSumFender(role.getSumFender());
		rm.setSumHp(role.getComplexHp());
		rm.setSumRebound(role.getSumRebound());
		rm.setSumReduceDefence(role.getSumReduceDefence());
		rm.setSumResist(role.getSumResist());
		rm.setSumSuckHp(role.getSumSuckHp());
		rm.setSumTenacity(role.getSumTenacity());
		rm.setFightPower(role.getFighting());
		rm.setRoleSkill(role.getRoleSkillInfo());
		rm.setFollowPetInfo(role.getFollowPetInfo());
		rm.setTotalFight(role.getTotalFight());
		rm.setWorldBossArrayInfo(role.getPointArrayPetInfo(Main_200_Equip.Main_224_Sub.worldBoss_2_array));
		rm.setCommonArrayInfo(role.getPointArrayPetInfo(Main_200_Equip.Main_224_Sub.common_0_array));
		rm.setAllPet(role.getAllPetInfo());
		rm.setRoleExpandInfo(role.getRoleExpandInfo());
		addRoleMini(rm);
	}

	public final static Collection<Role> getOnlineRoles() {
		return onlineRoles.values();
	}

	public static final RoleMini getRoleMini(int roleId) {
		if (roleId < 1) {
			return DEFAULT_ROLE_MINI;
		}
		RoleMini rm = roleMiniInfo.get(roleId);
		if (rm == null) {
			return roleMiniRobots.get(roleId);
		}
		return rm;
	}

	public static final boolean isDeleted(int roleId) {
		RoleMini state = getRoleMini(roleId);
		if (state == null) {
			return false;
		}
		return state.getDBState() == 1;
	}

	/**
	 * 用户池加入用户
	 */
	public static void addOLUserRole(Role role) {
		if (role == null) {
			return;
		}
		getInstance().addRole(role);
	}

	/***
	 * 通过名称找到在线玩家
	 * 
	 * @param name
	 * @return
	 */
	public static Role getOLUserRole(String name) {
		Integer roleId = onlineRoleNames.get(name);
		if (roleId == null) {
			return null;
		}
		return getOLUserRole(roleId);
	}

	/**
	 * 用户池删除用户
	 */
	public static void roleToOffline(Role role) {
		if (role == null) {
			return;
		}
		getInstance().removeRole(role);
	}

	public static Role getOLUserRole(int roleId) {
		Role role = onlineRoles.get(roleId);
		if (role != null) {
			if (role.getClientModel() == ClientCustom.ClientModel.V_2_ROBOT || (role.isLogined() && role.getNetSession() != null)) {
				return role;
			}
			getInstance().removeRole(role);
			return null;
		}
		return onlineRobots.get(roleId);
	}

	public static Role getOLUserRoleByUserId(int accountId) {
		Integer roleId = onlineUserIds.get(accountId);
		if (roleId == null) {
			return null;
		}
		return getOLUserRole(roleId);
	}

	public final static boolean isOnline(int roleId) {
		return onlineRoles.containsKey(roleId);
	}

	public static int getOnlineCount() {
		return onlineRoles.size();
	}

	public final static Role loadOfflineRole(int roleId) {
		return roleService.getOffLineUserRole(roleId);
	}

	public static final boolean kickRole(int roleId) {
		Role role = onlineRoles.get(roleId);
		if (role != null) {
			role.kickedout(NoticeManager.getInstance().getNoticeKickout());
			return true;
		}
		return false;
	}

	public static final RoleMini getRoleMini(String roleName) {
		Integer roleId = name2roleId.get(roleName.hashCode());
		if (roleId == null) {
			return null;
		}
		return getRoleMini(roleId);
	}

	/** 角色平均等级 */
	public static Logger             averageLevel = null;
	private final static RoleManager instance     = new RoleManager();

	private RoleManager() {
	}

	public static final RoleManager getInstance() {
		return instance;
	}

	class AddRole implements RoleManagerRunable {
		private Role role;

		public AddRole(Role role) {
			this.role = role;
		}

		@Override
		public void run() {
			try {
				if (role != null && role.isLogined()) {
					if (role.getClientModel() == ClientCustom.ClientModel.V_2_ROBOT) {
						onlineRobots.put(role.getId(), role);
					} else {
						onlineRoles.put(role.getId(), role);
					}
					onlineRoleNames.put(role.getUsername(), role.getId());
					onlineUserIds.put(role.getAccountId(), role.getId());
					addToLevel(role.getId(), role.getLevel());
					role = null;
				}
			} catch (Exception e) {
				LoggerError.error("AddRole", e);
			} finally {
				role = null;
			}
		}
	}

	class RemoveRole implements RoleManagerRunable {
		private Role role;

		public RemoveRole(Role role) {
			this.role = role;
		}

		@Override
		public void run() {
			try {
				if (role != null) {
					Role temp = null;
					if (role.getClientModel() == ClientCustom.ClientModel.V_2_ROBOT) {
						temp = onlineRobots.get(role.getId());
					} else {
						temp = onlineRoles.get(role.getId());
						if (temp != null) {
							if (role.getObjectId() < temp.getObjectId()) {
								return;
							}
						}
					}
					if (role.equals(temp)) {
						onlineRoles.remove(role.getId());
						onlineUserIds.remove(role.getAccountId());
						onlineRoleNames.remove(role.getUsername());
						removeFromLevel(role.getId(), role.getLevel());
					}
				}
			} catch (Exception e) {
				LoggerError.error("RemoveRole", e);
			} finally {
				role = null;
			}
		}
	}

	private void removeFromLevel(int roleId, short level) {
		Map<Integer, Object> levelMap = roleLevelMap.get(level);
		if (levelMap != null) {
			levelMap.remove(roleId);
		}
	}

	private void addToLevel(int roleId, short level) {
		Map<Integer, Object> levelMap = roleLevelMap.get(level);
		if (levelMap == null) {
			levelMap = new HashMap<Integer, Object>();
			roleLevelMap.put(level, levelMap);
		}
		levelMap.put(roleId, PUBLIC_VALUE);
	}

	class RoleLevelUpgrade implements RoleManagerRunable {
		private int   roleId;
		private short newLevel;

		public RoleLevelUpgrade(int roleId, short newLevel) {
			this.roleId = roleId;
			this.newLevel = newLevel;
		}

		@Override
		public void run() {
			try {
				removeFromLevel(roleId, (short) (newLevel - 1));
				if (onlineRoles.get(roleId) != null) {
					addToLevel(roleId, newLevel);
				}
			} catch (Exception e) {
				LoggerError.error("RoleLevelUpgrade", e);
			} finally {
			}
		}
	}

	/**
	 * 重置活跃度任务
	 * 
	 * @author 肖琼
	 * 
	 */
	class ResetLivenessMission implements RoleManagerRunable {
		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							role.resetLivenessMission();
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("WorldBossNotice", e);
			}
		}
	}

	/**
	 * 重置一战到底的进入次数
	 * 
	 * @author 肖琼
	 * 
	 */
	class OneStandEnterCount implements RoleManagerRunable {
		private int gateId = 0;

		public OneStandEnterCount(int gateId) {
			this.gateId = gateId;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							// 重置次数
							role.getDayData().addEnterDupCount(gateId, (byte) 0);
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("OneStandEnterCount", e);
			}
		}
	}

	/**
	 * 重置世界首领进入次数
	 * 
	 * @author 肖琼
	 * 
	 */
	class WorldBossEnterCount implements RoleManagerRunable {
		private int gateId = 0;

		public WorldBossEnterCount(int gateId) {
			this.gateId = gateId;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							// 重置次数
							role.getDayData().addEnterDupCount(gateId, (byte) 0);
							role.setLeaderReplay(0);
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("WorldBossEnterCount", e);
			}
		}
	}

	/**
	 * 世界首领活动状态
	 * 
	 * @author 肖琼
	 * 
	 */
	class WorldBossState implements RoleManagerRunable {
		private IoBuffer buf = null;

		public WorldBossState(IoBuffer buf) {
			this.buf = buf;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							// 更新按钮状态
							role.sendData(buf.duplicateBuffer());
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("WorldBossState", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
			}
		}
	}

	/**
	 * 世界首领公告
	 * 
	 * @author 肖琼
	 * 
	 */
	class WorldBossNotice implements RoleManagerRunable {
		private IoBuffer buf;

		public WorldBossNotice(IoBuffer buf) {
			this.buf = buf;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							role.sendData(buf.duplicateBuffer());
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("WorldBossNotice", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
			}
		}
	}

	/**
	 * 关闭活动按钮
	 * 
	 * @author Administrator
	 * 
	 */
	class CloseGameActivityButton implements RoleManagerRunable {
		private IoBuffer buf;

		public CloseGameActivityButton(IoBuffer buf) {
			this.buf = buf;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							role.sendData(buf.duplicateBuffer());
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("CloseGameActivityButton", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
			}
		}
	}

	/**
	 * 重置在线奖励界面
	 * 
	 * @author 肖琼
	 * 
	 */
	class ResetOnlineTimeAward implements RoleManagerRunable {
		private IoBuffer buf;

		public ResetOnlineTimeAward(IoBuffer buf) {
			this.buf = buf;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							if (role.getRegisterDays() < 3) {
								role.sendData(buf.duplicateBuffer());
							}
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("ResetOnlineTimeAward", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
			}
		}
	}

	/**
	 * 世界聊天
	 * 
	 * @author fanren
	 * 
	 */
	class ChatWorld implements RoleManagerRunable {
		private IoBuffer buf;
		private String   triggerName;

		public ChatWorld(IoBuffer buf, String triggerName) {
			this.buf = buf;
			this.triggerName = triggerName;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							if (role.isShowChat()) {
								role.sendData(buf.duplicateBuffer());
							}
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("ChatWorld", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
				triggerName = null;
			}
		}
	}

	class ChatHorn implements RoleManagerRunable {
		private IoBuffer buf;

		public ChatHorn(IoBuffer buf) {
			this.buf = buf;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							role.sendData(buf.duplicateBuffer());
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("ChatHorn", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
			}
		}
	}

	class ChatNotice implements RoleManagerRunable {
		private IoBuffer buf;

		public ChatNotice(IoBuffer buf) {
			this.buf = buf;
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							role.sendData(buf.duplicateBuffer());
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("ChatNotice", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
			}
		}
	}

	class CloseNet implements RoleManagerRunable {

		public CloseNet() {
		}

		@Override
		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						role.close(true);
					}
				}
			} catch (Exception e) {
				LoggerError.error("ChatNotice", e);
			}
		}
	}

	class ChatSect implements RoleManagerRunable {
		private IoBuffer       buf;
		private List<SectRole> members;

		public ChatSect(List<SectRole> members, IoBuffer buf) {
			this.members = members;
			this.buf = buf;
		}

		@Override
		public void run() {
			try {
				if (members != null) {
					for (SectRole roleId : members) {
						Role role = RoleManager.getOLUserRole(roleId.getRoleId());
						if (role != null && role.isLogined() && role.isShowChat()) {
							role.sendData(buf.duplicateBuffer());
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("ChatSect", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				if (members != null) {
					members.clear();
				}
				buf = null;
				members = null;
			}
		}
	}

	class RecommendBattleHelper implements RoleManagerRunable {
		private int   connectId = 0;
		private int   roleId;
		private short type;

		public RecommendBattleHelper(int selRoleId, int connectId, short type) {
			this.roleId = selRoleId;
			this.connectId = connectId;
			this.type = type;
		}

		@Override
		public void run() {
			Role selRole = getOLUserRole(roleId);
			if (selRole == null) {
				return;
			}
			short recommendFriendNum = 8;// 推荐好友数
			int max = RoleRealmConfig.getMaxLevel(selRole.getRealm());// GameParameter.getRoleMaxLevel();
			short levelPlus = selRole.getLevel();
			short levelMinus = (short) (selRole.getLevel() - 1);
			Map<Integer, Object> roles = null;
			Set<Integer> roleSet = null;
			Role otherRole = null;
			short roleCount = 0;

			IoBuffer buf = PacketBufferPool.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
			buf.setNetConfirm(connectId);
			if (type == SubPro_1045_friend.ADD_FRIEND_RECOMMEND) {
				buf.setScriptName(VersionConfig.Client.getUIScript(selRole.getClientVersion(), VersionConfig.UIIdentity.FRIEND_RECOMMEND));
			} else {
				buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
			}
			buf.setOverlap(false);
			buf.setCmd(ClientUI.UI.CLEAR_OBJECT_LIST);
			buf.setMain(Main_800_Social.main_802_socrial);
			buf.setSub(Main_800_Social.Sub_800.sub_800_14_RECOMMEND);

			buf.setCmd(ClientUI.UI.ITEM_LENGTH);
			buf.setMain(Main_800_Social.main_802_socrial);
			buf.setSub(Main_800_Social.Sub_800.sub_800_14_RECOMMEND);
			int position = buf.position();
			buf.putShort((short) 0);
			buf.endSub();

			buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);
			buf.setMain(Main_800_Social.main_802_socrial);
			buf.setSub(Main_800_Social.Sub_800.sub_800_14_RECOMMEND);

			for (int li = 0; li < max; li++) {
				if (roleCount < recommendFriendNum) {
					roles = roleLevelMap.get(levelPlus);
					if (roles != null) {
						roleSet = roles.keySet();
						for (int otherRoleId : roleSet) {
							if (roleId == otherRoleId || friendManager.getFriend(selRole.getId(), otherRoleId) != null) {
								continue;
							}
							otherRole = getOLUserRole(otherRoleId);
							if (otherRole != null) {
								Iterator<Role> pets = otherRole.getPets().values().iterator();
								if (pets != null && pets.hasNext()) {
									Role pet = pets.next();
									buf.setSerial(roleCount++);
									buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ID_5, otherRole.getId());
									buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, otherRole.getUsername());
									buf.setProperty(CommonGamePropertyKey.ItemKey.ITEM_STAR_924, pet.getStar());
									buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ICON_7, pet.getPetIcon());
									buf.setProperty(CommonGamePropertyKey.Pet.PET_LEVEL, pet.getLevel());
									buf.setProperty(CommonGamePropertyKey.Pet.PET_ID_1250, pet.getRealId());
									buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_QUALITY_13, pet.getQuality());
									buf.endProperty();
									if (roleCount >= recommendFriendNum) {
										break;
									}
								}
							}
						}
					}
					if (roleCount < recommendFriendNum) {
						roles = roleLevelMap.get(levelMinus);
						if (roles != null) {
							roleSet = roles.keySet();
							for (int otherRoleId : roleSet) {
								if (roleId == otherRoleId || friendManager.getFriend(selRole.getId(), otherRoleId) != null) {
									continue;
								}
								otherRole = getOLUserRole(otherRoleId);
								if (otherRole != null) {
									Iterator<Role> pets = otherRole.getPets().values().iterator();
									if (pets != null && pets.hasNext()) {
										Role pet = pets.next();
										buf.setSerial(roleCount++);
										buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ID_5, otherRole.getId());
										buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, otherRole.getUsername());
										buf.setProperty(CommonGamePropertyKey.ItemKey.ITEM_STAR_924, pet.getStar());
										buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ICON_7, pet.getPetIcon());
										buf.setProperty(CommonGamePropertyKey.Pet.PET_LEVEL, pet.getLevel());
										buf.setProperty(CommonGamePropertyKey.Pet.PET_ID_1250, pet.getRealId());
										buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_QUALITY_13, pet.getQuality());
										buf.endProperty();
										if (roleCount >= recommendFriendNum) {
											break;
										}
									}
								}
							}
						}
					} else {
						break;
					}
					levelPlus++;
					levelMinus--;
				} else {
					break;
				}
			}
			buf.putShort(position, roleCount);
			buf.endSerial();
			buf.endSub();
			buf.endMain();
			buf.endCmd();
			selRole.sendData(buf);
		}
	}

	class RecommendFriend implements RoleManagerRunable {
		private int connectId = 0;
		private int roleId;

		public RecommendFriend(int selRoleId, int connectId) {
			this.roleId = selRoleId;
			this.connectId = connectId;
		}

		@Override
		public void run() {
			Role selRole = onlineRoles.get(roleId);
			if (selRole == null) {
				return;
			}

			short recommendFriendNum = 8;// 推荐好友数

			IoBuffer buf = PacketBufferPool.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
			buf.setNetConfirm(connectId);
			buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
			buf.setOverlap(false);
			buf.setCmd(ClientUI.UI.CLEAR_OBJECT_LIST);
			buf.setMain(Main_800_Social.main_802_socrial);
			buf.setSub(Main_800_Social.Sub_800.sub_800_14_RECOMMEND);

			buf.setCmd(ClientUI.UI.ITEM_LENGTH);
			buf.setMain(Main_800_Social.main_802_socrial);
			buf.setSub(Main_800_Social.Sub_800.sub_800_14_RECOMMEND);
			int position = buf.position();
			buf.putShort((short) 0);
			buf.endSub();

			buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);
			buf.setMain(Main_800_Social.main_802_socrial);
			buf.setSub(Main_800_Social.Sub_800.sub_800_14_RECOMMEND);
			Collection<Role> olRoles = onlineRoles.values();

			short roleCount = 0;

			Map<Integer, RoleMini> roleList = new HashMap<Integer, RoleMini>();
			for (Role or : olRoles) {
				if (or.getId() != roleId && friendManager.getFriend(roleId, or.getId()) == null) {
					roleList.put(or.getId(), getRoleMini(or.getId()));
				}
				if (roleList.size() > recommendFriendNum) {
					break;
				}
			}
			if (roleList.size() > recommendFriendNum) {
				Collection<RoleMini> roles = roleList.values();
				for (RoleMini otherRole : roles) {
					buf.setSerial(roleCount++);
					buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ID_5, otherRole.getRoleId());
					buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, otherRole.getRoleName());
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_REALM_705, otherRole.getRealm());
					buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_LEVEL_0, otherRole.getLevel());
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_REALM_705, RoleRealmConfig.getName(otherRole.getRealm()));
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_AVATAR_714, RoleConfig.getAvatar(otherRole));
					buf.setProperty(CommonGamePropertyKey.RoleKey.HEAD_AVATAR_BIG_748, RoleConfig.getAvatarBig(otherRole));
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_PROFESSION_701, otherRole.getProfession());
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_PROFESSION_701, RoleProfession.getProfessionName(otherRole.getProfession()));
					buf.endProperty();
				}
			} else {
				Collection<RoleMini> roles = roleList.values();
				for (RoleMini oRole : roles) {
					buf.setSerial(roleCount++);
					buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ID_5, oRole.getRoleId());
					buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, oRole.getRoleName());
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_REALM_705, oRole.getRealm());
					buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_LEVEL_0, oRole.getLevel());
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_REALM_705, RoleRealmConfig.getName(oRole.getRealm()));
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_AVATAR_714, RoleConfig.getAvatar(oRole));
					buf.setProperty(CommonGamePropertyKey.RoleKey.HEAD_AVATAR_BIG_748, RoleConfig.getAvatarBig(oRole));
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_PROFESSION_701, oRole.getProfession());
					buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_PROFESSION_701, RoleProfession.getProfessionName(oRole.getProfession()));
					buf.endProperty();
				}
				if (roleCount < recommendFriendNum) {
					int remain = recommendFriendNum - roleCount;
					int offset = roleMiniList.size() / remain;
					int sub = 0;
					RoleMini rm = null;
					for (int ri = 0; ri < remain; ri++) {
						rm = roleMiniList.get(MathUtil.getRandom(sub, sub + offset));
						if (roleList.get(rm.getRoleId()) != null || rm.isDeleted() || rm.getRoleId() == roleId
						        || friendManager.getFriend(selRole.getId(), rm.getRoleId()) != null) {
							rm = roleMiniList.get(MathUtil.getRandom(sub, sub + offset));
							if (roleList.get(rm.getRoleId()) != null || rm.isDeleted() || rm.getRoleId() == roleId
							        || friendManager.getFriend(selRole.getId(), rm.getRoleId()) != null) {
								continue;
							}
						}
						roleList.put(rm.getRoleId(), rm);
						buf.setSerial(roleCount++);
						buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ID_5, rm.getRoleId());
						buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, rm.getRoleName());
						buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_REALM_705, rm.getRealm());
						buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_LEVEL_0, rm.getLevel());
						buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_REALM_705, RoleRealmConfig.getName(rm.getRealm()));
						buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_AVATAR_714, RoleConfig.getAvatar(rm));
						buf.setProperty(CommonGamePropertyKey.RoleKey.HEAD_AVATAR_BIG_748, RoleConfig.getAvatarBig(rm));
						buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_PROFESSION_701, rm.getProfession());
						buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_PROFESSION_701, RoleProfession.getProfessionName(rm.getProfession()));
						buf.endProperty();
					}
				}
			}

			buf.putShort(position, roleCount);
			buf.endSerial();
			buf.endSub();
			buf.endMain();
			buf.endCmd();
			selRole.sendData(buf);
		}
	}

	class AverageLevel implements RoleManagerRunable {

		public AverageLevel() {

		}

		public void run() {
			try {
				int onlineCount = 0;
				float totalLevel = 0;
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							onlineCount++;
							totalLevel += role.getLevel();
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
				if (averageLevel != null) {
					if (onlineCount > 0) {
						StringBuilder sb = new StringBuilder();
						sb.append(onlineCount);
						sb.append(LoggerFilter.logDivide).append(totalLevel / onlineCount);
						averageLevel.info(sb.toString());
					} else {
						StringBuilder sb = new StringBuilder();
						sb.append(onlineCount);
						sb.append(LoggerFilter.logDivide).append(0);
						averageLevel.info(sb.toString());
					}
				}
			} catch (Exception e) {
				LoggerError.error("AverageLevel", e);
			}
		}
	}

	class SystemNotice implements RoleManagerRunable {
		private IoBuffer buf;

		public SystemNotice(IoBuffer buf) {
			this.buf = buf;

		}

		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							role.sendData(buf.duplicateBuffer());
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("SystemNotice", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
			}
		}
	}

	class SystemNoticeLevel implements RoleManagerRunable {
		private IoBuffer buf;
		private short    subLevel;

		public SystemNoticeLevel(IoBuffer buf, short subLevel) {
			this.buf = buf;
			this.subLevel = subLevel;
		}

		public void run() {
			try {
				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null) {
						if (role.isLogined()) {
							if (role.getLevel() >= subLevel) {
								role.sendData(buf.duplicateBuffer());
							}
						} else {
							execute(new RemoveRole(role));
						}
					}
				}
			} catch (Exception e) {
				LoggerError.error("SystemNotice", e);
			} finally {
				if (buf != null) {
					PacketBufferPool.freePacketBuffer(buf);
				}
				buf = null;
			}
		}
	}

	public final void addSerachRoleTask(int roleId, String roleName, int connectID) {
		execute(new SearchRoleByNameData(roleId, roleName, connectID));
	}

	class SearchRoleByNameData implements RoleManagerRunable {
		private String name;
		private int    roleId;
		private int    connectID;

		public SearchRoleByNameData(int roleId, String roleName, int connectID) {
			this.name = roleName;
			this.roleId = roleId;
			this.connectID = connectID;
		}

		public void run() {

			Role matchRoleList = onlineRoles.get(roleId);
			if (matchRoleList != null) {
				List<Role> matchRole = new ArrayList<Role>();

				Collection<Role> users = onlineRoles.values();
				for (Role role : users) {
					if (role != null && role.getId() != roleId) { // 排除自己
						if (role.getUsername().contains(name)) {
							matchRole.add(role);
						}
						// 最多20个结果
						if (matchRole.size() > 20) {
							break;
						}
					}
				}

				IoBuffer buf = PacketBufferPool.getPacketBuffer();
				buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
				buf.setNetConfirm(connectID);
				buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
				buf.setOverlap(false); // false 替换 true 新窗口打开
				buf.setCmd(ClientUI.UI.CLEAR_OBJECT_LIST);
				buf.setMain(Main_800_Social.main_802_socrial);
				buf.setSub(Main_800_Social.Sub_800.sub_800_4_SEARCH);
				buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);
				buf.setMain(Main_800_Social.main_802_socrial);
				buf.setSub(Main_800_Social.Sub_800.sub_800_4_SEARCH);

				if (matchRole.size() > 0) {
					for (int i = 0; i < matchRole.size(); i++) {
						buf.setSerial(i);
						buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ID_5, matchRole.get(i).getId());
						buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_PROFESSION_701, matchRole.get(i).getProfession());
						buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_NAME_6, matchRole.get(i).getUsername());
						buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_STATUS_17, RoleGameState.stateOnline);
						buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_LEVEL_0, matchRole.get(i).getLevel());
						buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_SEX_702, matchRole.get(i).getSex());
						buf.setProperty(CommonGamePropertyKey.RoleKey.ROLE_AVATAR_714, RoleConfig.getAvatar(matchRole.get(i)));
						buf.endProperty();
					}
				} else {
					buf.setSerial(0);
					buf.endProperty();
				}
				buf.endSerial();
				buf.endSub();
				buf.endMain();
				buf.endCmd();
				matchRoleList.sendData(buf);
			}
		}
	}

	/**
	 * 重置活跃度任务
	 */
	public void resetLivenessMission() {
		execute(new ResetLivenessMission());
	}

	/**
	 * 重置世界首领的进入次数
	 * 
	 * @param duplicateId
	 */
	public final void worldBossEnterCount(int gateId) {
		execute(new WorldBossEnterCount(gateId));
	}

	public final void oneStandEnterCount(int gateId) {
		execute(new OneStandEnterCount(gateId));
	}

	/**
	 * 发送世界首领活动状态
	 */
	public final void worldBossState(byte state) {
		long curTime = System.currentTimeMillis();
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_6500_OPEN_UI);
		buf.setNetConfirm(-1);
		buf.setScriptName(VersionConfig.Client.NONE_SCRIPT);
		buf.setOverlap(false);
		buf.setCmd(ClientUI.UI.ADD_OBJECT_PROPERTY);
		buf.setMain(Main_0_account.main_2_function);// 主类别
		buf.setSub(Main_0_account.Sub_2.sub_0_function);// 子类别
		buf.setSerial(13);// 序号
		buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_ACTION_14, state);
		buf.endProperty();
		if (state == 0) {
			buf.setCmd(ClientUI.UI.ADD_PROPERTY_GROUP);
			buf.setMain(Main_1300_Rank.main_1304_Leader);
			buf.setSub(0);
			buf.setSerial(0);
			long surplus = GameParameter.getOpenWordBoss() - curTime;
			if (surplus > 0) {
				int second = (int) (surplus / 1000);
				buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_TIME_15, second);
			}
			buf.setProperty(CommonGamePropertyKey.CommonKey.COMMON_SYSDATE_33, (int) ((curTime / 10) & 0x7fffffff));
			buf.endProperty();
			buf.endSerial();
			buf.endSub();
			buf.endMain();
		}
		buf.endCmd();
		execute(new WorldBossState(buf));
	}

	/**
	 * 发送世界BOSS公告
	 * 
	 * @param message
	 */
	public final void worldBossNotice(String message) {
		if (message != null) {
			IoBuffer buf = PacketBufferPool.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_5020_CHAT);
			buf.put(ChatChannel.CHANNEL_NOTICE);
			buf.putString(message);
			execute(new WorldBossNotice(buf));
		}
	}

	/**
	 * 发送重置在线奖励界面数据
	 */
	public final void resetOnlineTimeAward(IoBuffer buf) {
		if (buf != null) {
			execute(new ResetOnlineTimeAward(buf));
		}
	}

	public final void closeGameActivityButton(IoBuffer buf) {
		if (buf != null) {
			execute(new CloseGameActivityButton(buf));
		}
	}

	public void roleLevelUpTo(int roleId, short newLevel) {
		execute(new RoleLevelUpgrade(roleId, newLevel));
	}

	public final void chatWorld(IoBuffer buf, String triggerName) {
		execute(new ChatWorld(buf, triggerName));
	}

	public final void chatHorn(IoBuffer buf) {
		execute(new ChatHorn(buf));
	}

	public final void chatNotice(IoBuffer buf) {
		execute(new ChatNotice(buf));
	}

	public final void chatNotice(byte channel, String message) {
		if (message != null) {
			IoBuffer buf = PacketBufferPool.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_5020_CHAT);
			buf.put(channel);
			buf.putString(message);
			chatNotice(buf);
		}
	}

	public final void chatSect(IoBuffer buf, List<SectRole> members) {
		execute(new ChatSect(members, buf));
	}

	public final void recommendBattleHelper(int selRoleId, int connectId, short type) {
		execute(new RecommendBattleHelper(selRoleId, connectId, type));
	}

	public final void recommendFriend(int selRoleId, int connectId) {
		execute(new RecommendFriend(selRoleId, connectId));
	}

	public final void addRole(Role role) {
		execute(new AddRole(role));
	}

	public final void removeRole(Role role) {
		execute(new RemoveRole(role));
	}

	/**
	 * 只显示在屏幕上方，会显示在聊天频道
	 * 
	 * @param msg
	 */
	public final void systemNotice(String msg) {
		IoBuffer buf = PacketBufferPool.getPacketBuffer();
		buf.setProtocol(UserProtocol.Server.PROS_5020_CHAT);
		buf.put(ChatChannel.CHANNEL_NOTICE);
		buf.putString(msg);
		execute(new SystemNotice(buf));
	}

	public final void systemNotice(IoBuffer buf) {
		execute(new SystemNotice(buf));
	}

	/**
	 * 只显示在屏幕下方，不会显示在聊天频道
	 * 
	 * @param msg
	 */
	public final void systemNoticeByPros5085(String msg) {
		if (msg != null) {
			IoBuffer buf = IoBuffer.getPacketBuffer();
			buf.setProtocol(UserProtocol.Server.PROS_5085_MESSAGE);
			buf.putString(msg);
			execute(new SystemNotice(buf));
		}
	}

	public final void systemNoticeLevel(IoBuffer buf, short subLevel) {
		execute(new SystemNoticeLevel(buf, subLevel));
	}

	public final void averageLevel() {
		execute(new AverageLevel());
	}

	public final void closeNet() {
		execute(new CloseNet());
	}

	public void execute(RoleManagerRunable run) {
		super.addEvent(run);
	}

	public static List<RoleMini> getRoleMiniList() {
		return roleMiniList;
	}

	public void callback() {
		// TODO Auto-generated method stub

	}
}
