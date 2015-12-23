package com.huayi.doupo.logic.handler.util.unionwar;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictUnionWarInfo;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.InstUnionWarContributionRank;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import com.huayi.doupo.logic.util.luafight.FightData;
import com.huayi.doupo.logic.util.luafight.FightUtil;

public class Team {

	// NOLOCK
	int unionId = 0;
	int unionLevel = 0;
	int unionRank = 0;
	String unionName = "";
	Against against = null;

	// LOCK
	ReentrantLock lock = new ReentrantLock();
	MemberList memberListNoQualification = new MemberList(this);
	MemberList memberListNotRegistered = new MemberList(this);
	MemberList memberListAmbush = new MemberList(this);
	MemberList[] memberListBattlefield = { new MemberList(this), new MemberList(this), new MemberList(this), new MemberList(this) };
	HashMap<Integer, MemberList> instPlayerId2MemberList = new HashMap<Integer, MemberList>();
	HashMap<Integer, InstUnionWarContributionRank> memberDataList = new HashMap<Integer, InstUnionWarContributionRank>();
	HashMap<Integer, FightData> fightDataList = new HashMap<Integer, FightData>();
	boolean[] allowAmbush = { false, false, false, false }; // 四个战场的是否允许伏击的开关
	int[] battlefieldWinScore = { 0, 0, 0, 0 }; // 四个战场的占领积分
	int[] battlefieldKillScore = { 0, 0, 0, 0 }; // 四个战场的击杀积分
	int[] battlefieldAliveScore = { 0, 0, 0, 0 }; // 四个战场的存活积分
	boolean isTotalScoreDirty = true;
	int totalScore = 0; // 对阵的总积分
	InstUnionWarContributionRank mvp = null; // 结算后才有效，且也可能为空，即没有MVP。

	public Team(int instUnionId, String name, int level, int rank) {
		this.unionId = instUnionId;
		this.unionName = name;
		this.unionLevel = level;
		this.unionRank = rank;
		List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList(" instUnionId = " + instUnionId, 0);
		for (InstUnionMember instUnionMember : instUnionMemberList) { // TODO 后来加入的成员如何处理？
			int instPlayerId = instUnionMember.getInstPlayerId();
			long insertTime;
			try {
				insertTime = DateUtil.getMillSecond(instUnionMember.getInsertTime());
			} catch (Exception e) {
				insertTime = System.currentTimeMillis();
			}
			if ((System.currentTimeMillis() - insertTime) < TimeUnit.HOURS.toMillis(UnionWarUtil.HOUR_OF_QUALIFICATION)) {
				memberListNoQualification.add(instPlayerId);
				instPlayerId2MemberList.put(instPlayerId, memberListNoQualification);
			} else {
				memberListNotRegistered.add(instPlayerId);
				instPlayerId2MemberList.put(instPlayerId, memberListNotRegistered);
			}
			InstUnionWarContributionRank md = new InstUnionWarContributionRank();
			md.setInstUnionId(instUnionId);
			md.setInstPlayerId(instPlayerId);
			InstPlayer instPlayer = DALFactory.getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
			md.setInstPlayerLevel(instPlayer == null ? DictMapUtil.getSysConfigIntValue(StaticSysConfig.initLevel) : instPlayer.getLevel());
			md.setInstPlayerName(instPlayer == null ? "???" : instPlayer.getName());
			md.setInstPlayerHead(instPlayer == null ? DictMapUtil.getSysConfigIntValue(StaticSysConfig.headerCardId) : instPlayer.getHeaderCardId());
			md.setGradeId(instUnionMember.getUnionGradeId());
			md.setIsMvp(0);
			md.setIsAlive(1);
			md.setLastType(-1); // -1 代表未报名
			md.setFightCount(0);
			md.setKillCount(0);
			memberDataList.put(instPlayerId, md);
			fightDataList.put(instPlayerId, FightUtil.getInstance().getFightData(instPlayerId)); // TODO 获取的时刻
		}
	}

	public int getUnionId() {
		return unionId;
	}

	public String getUnionName() {
		return unionName;
	}

	public int getUnionLevel() {
		return unionLevel;
	}

	public int getUnionRank() {
		return unionRank;
	}

	public void setBattlefieldWinScore(int battlefieldIdx, int score) {
		battlefieldWinScore[battlefieldIdx] = score;
		isTotalScoreDirty = true;
	}

	public int getBattlefieldWinScore(int battlefieldIdx) {
		return battlefieldWinScore[battlefieldIdx];
	}

	public void setBattlefieldKillScore(int battlefieldIdx, int score) {
		battlefieldKillScore[battlefieldIdx] = score;
		isTotalScoreDirty = true;
	}

	public int getBattlefieldKillScore(int battlefieldIdx) {
		return battlefieldKillScore[battlefieldIdx];
	}

	public void setBattlefieldAliveScore(int battlefieldIdx, int score) {
		battlefieldAliveScore[battlefieldIdx] = score;
		isTotalScoreDirty = true;
	}

	public int getBattlefieldAliveScore(int battlefieldIdx) {
		return battlefieldAliveScore[battlefieldIdx];
	}

	public int getBattlefieldScore(int battlefieldIdx) {
		return battlefieldWinScore[battlefieldIdx] + battlefieldKillScore[battlefieldIdx] + battlefieldAliveScore[battlefieldIdx];
	}

	public int getTotalScore() {
		if (isTotalScoreDirty) {
			totalScore = 0;
			for (int i = 0; i < UnionWarUtil.BATTLEFIELD_SIZE; ++i) {
				totalScore += battlefieldWinScore[i] + battlefieldKillScore[i] + battlefieldAliveScore[i];
			}
		}
		return totalScore;
	}

	public void lock() {
		lock.lock();
	}

	public void unlock() {
		lock.unlock();
	}

	public void join(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldIdx) {
		try {
			lock();
			MemberList ml = instPlayerId2MemberList.get(instPlayerId);
			if (ml == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, "盟战开启之时，您不是联盟成员");
				return;
			}
			if (ml == memberListNoQualification) {
				MessageUtil.sendFailMsg(channelId, msgMap, "加入联盟" + UnionWarUtil.HOUR_OF_QUALIFICATION + "小时候才能参加盟战");
				return;
			}
			if (ml == memberListAmbush) {
				MessageUtil.sendFailMsg(channelId, msgMap, "您已被挑选成为伏兵，开战前不能加入战场");
				return;
			}
			if (ml == memberListBattlefield[battlefieldIdx]) {
				MessageUtil.sendFailMsg(channelId, msgMap, "您已经报名了该战场");
				return;
			}
			ml.remove(Integer.valueOf(instPlayerId));
			memberListBattlefield[battlefieldIdx].add(instPlayerId);
			instPlayerId2MemberList.put(instPlayerId, memberListBattlefield[battlefieldIdx]);
			memberDataList.get(instPlayerId).setLastType(battlefieldIdx);
			MessageUtil.sendSuccMsg(channelId, msgMap);
			against.onJoin(this, instPlayerId, battlefieldIdx);
		} finally {
			unlock();
		}
	}

	public void disjoin(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldIdx) {
		try {
			lock();
			MemberList ml = instPlayerId2MemberList.get(instPlayerId);
			if (ml == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, "盟战开启之时，您不是联盟成员");
				return;
			}
			if (ml == memberListNoQualification) {
				MessageUtil.sendFailMsg(channelId, msgMap, "加入联盟" + UnionWarUtil.HOUR_OF_QUALIFICATION + "小时候才能参加盟战");
				return;
			}
			if (ml == memberListNotRegistered) {
				MessageUtil.sendFailMsg(channelId, msgMap, "您还没有报名盟战");
				return;
			}
			ml.remove(Integer.valueOf(instPlayerId)); // TODO 若是伏兵，则会自动移除，若客户端已同步信息，则客户端需确认。
			memberListNotRegistered.add(instPlayerId);
			instPlayerId2MemberList.put(instPlayerId, memberListNotRegistered);
			memberDataList.get(instPlayerId).setLastType(-1); // -1 代表未报名
			MessageUtil.sendSuccMsg(channelId, msgMap);
			against.onDisjoin(this, instPlayerId, battlefieldIdx);
		} finally {
			unlock();
		}
	}

	public void sort(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldIdx) {
		String[] idList = ((String) msgMap.get("idList")).split("_");
		try {
			lock();
			MemberList ml = memberListBattlefield[battlefieldIdx];
			int index = -1;
			for (String id : idList) {
				Integer playerId = Integer.valueOf(id);
				if (ml.remove(playerId)) {
					ml.add(++index, playerId);
				}
			}
			MessageUtil.sendSuccMsg(channelId, msgMap);
			against.onSort(this, battlefieldIdx);
		} finally {
			unlock();
		}
	}

	public void ambush(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		int gradeId = memberDataList.get(instPlayerId).getGradeId();
		if (gradeId != 1 && gradeId != 2) {
			MessageUtil.sendFailMsg(channelId, msgMap, "您没有操作权限");
			return;
		}
		Integer playerId = (Integer) msgMap.get("playerId");
		try {
			lock();
			MemberList ml = instPlayerId2MemberList.get(playerId);
			if (ml == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, "盟战开启之时，该玩家不是联盟成员");
				return;
			}
			if (ml == memberListNoQualification) {
				MessageUtil.sendFailMsg(channelId, msgMap, "加入联盟" + UnionWarUtil.HOUR_OF_QUALIFICATION + "小时候才能参加盟战");
				return;
			}
			if (ml == memberListAmbush) {
				MessageUtil.sendFailMsg(channelId, msgMap, "该玩家已经是伏兵了");
				return;
			}
			if (memberListAmbush.size() >= UnionWarUtil.getAmbushLimit(unionLevel)) {
				MessageUtil.sendFailMsg(channelId, msgMap, "伏兵已达上限");
			}
			ml.remove(playerId);
			memberListAmbush.add(playerId);
			instPlayerId2MemberList.put(playerId, memberListAmbush);
			MessageUtil.sendSuccMsg(channelId, msgMap);
			against.onAmbush(this, playerId);
		} finally {
			unlock();
		}
	}

	public void unambush(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		int gradeId = memberDataList.get(instPlayerId).getGradeId();
		if (gradeId != 1 && gradeId != 2) {
			MessageUtil.sendFailMsg(channelId, msgMap, "您没有操作权限");
			return;
		}
		Integer playerId = (Integer) msgMap.get("playerId");
		try {
			lock();
			MemberList ml = instPlayerId2MemberList.get(playerId);
			if (ml == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, "盟战开启之时，该玩家不是联盟成员");
				return;
			}
			if (ml == memberListNoQualification) {
				MessageUtil.sendFailMsg(channelId, msgMap, "加入联盟" + UnionWarUtil.HOUR_OF_QUALIFICATION + "小时候才能参加盟战");
				return;
			}
			if (ml != memberListAmbush) {
				MessageUtil.sendFailMsg(channelId, msgMap, "此玩家已经不是伏兵了");
				return;
			}
			ml.remove(playerId);
			int lastType = memberDataList.get(playerId).getLastType();
			if (lastType == -1) {
				memberListNotRegistered.add(playerId);
				instPlayerId2MemberList.put(playerId, memberListNotRegistered);
			} else {
				memberListBattlefield[lastType].add(playerId);
				instPlayerId2MemberList.put(playerId, memberListBattlefield[lastType]);
			}
			MessageUtil.sendSuccMsg(channelId, msgMap);
			against.onUnambush(this, playerId, lastType);
		} finally {
			unlock();
		}
	}

	public void fight(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldIdx) {
		try {
			lock();
			if (!isAllowAmbush(battlefieldIdx)) {
				MessageUtil.sendFailMsg(channelId, msgMap, "战场已结束，无法伏击。");
				return;
			}
			MemberList ml = instPlayerId2MemberList.get(instPlayerId);
			if (ml != memberListAmbush) {
				MessageUtil.sendFailMsg(channelId, msgMap, "您不是伏兵，无法伏击");
				return;
			}
			ml.remove(Integer.valueOf(instPlayerId));
			memberListBattlefield[battlefieldIdx].add(instPlayerId);
			instPlayerId2MemberList.put(instPlayerId, memberListBattlefield[battlefieldIdx]);
			MessageUtil.sendSuccMsg(channelId, msgMap);
			against.onFight(this, instPlayerId, battlefieldIdx);
		} finally {
			unlock();
		}
	}

	public MemberList getBattlefieldMemberList(int idx) {
		return memberListBattlefield[idx];
	}

	public boolean isAllowAmbush(int idx) {
		return allowAmbush[idx];
	}

	public void setAllowAmbush(int idx, boolean allowAmbush) {
		this.allowAmbush[idx] = allowAmbush;
	}

	public void calcContribution() {
		int mvpContribution = 0;
		for (InstUnionWarContributionRank r : memberDataList.values()) {
			if (r.getIsAlive() == 1) {
				r.setContribution(r.getContribution() + UnionWarUtil.getAliveContribution());
			}
			if (mvpContribution < r.getContribution()) {
				mvpContribution = r.getContribution();
				mvp = r;
			}
		}
		if (against.winer == this && mvp != null) { // 保存MVP
			mvp.setIsMvp(1);
		}
		// 入库
		try {
			DALFactory.getInstUnionWarContributionRankDAL().deleteByWhere(" instUnionId = " + unionId);
		} catch (Exception e) {
		}
		for (InstUnionWarContributionRank r : memberDataList.values()) {
			try {
				DALFactory.getInstUnionWarContributionRankDAL().add(r, 0);
			} catch (Exception e) {
			}
		}
	}

	private void award(int instPlayerId, String reward, String desc) {
		try {
			MessageData syncMsgData = new MessageData();
			ActivityUtil.addInstPlayerAward(instPlayerId, 3, reward, DateUtil.getCurrTime(), desc, syncMsgData);
			Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
			if (player != null) {
				MessageUtil.sendSyncMsg(player.getChannelId(), syncMsgData);
			}
		} catch (Exception e) {
		}
	}

	public void award() {
		DictUnionWarInfo dictUnionWarInfo = DictMap.dictUnionWarInfoMap.get("" + UnionWarUtil.unionWarIndex);
		if (dictUnionWarInfo == null) { // 。。。没有奖励
			return;
		}
		if (against.winer != this) { // 本队未获胜
			// 失败方参与奖
			String rewardLose = dictUnionWarInfo.getRewardLose();
			if (rewardLose.length() != 0) {
				for (int instPlayerId : memberListAmbush) {
					award(instPlayerId, rewardLose, "盟战参与奖励");
				}
				for (MemberList ml : memberListBattlefield) {
					for (int instPlayerId : ml) {
						award(instPlayerId, rewardLose, "盟战参与奖励");
					}
				}
			}
			return;
		}
		// 胜利奖
		String rewardWin = dictUnionWarInfo.getRewardWin();
		if (rewardWin.length() != 0) {
			for (int instPlayerId : memberListAmbush) {
				award(instPlayerId, rewardWin, "盟战胜利参与奖励");
			}
			for (MemberList ml : memberListBattlefield) {
				for (int instPlayerId : ml) {
					award(instPlayerId, rewardWin, "盟战胜利参与奖励");
				}
			}
		}
		// 盟主、副盟主奖
		String rewardLeader = dictUnionWarInfo.getRewardWin();
		if (rewardLeader.length() != 0) {
			for (int instPlayerId : memberListAmbush) {
				int gradeId = memberDataList.get(instPlayerId).getGradeId();
				if (gradeId == 1 || gradeId == 2) {
					award(instPlayerId, rewardLeader, "盟战胜利盟主、副盟主奖励");
				}
			}
			for (MemberList ml : memberListBattlefield) {
				for (int instPlayerId : ml) {
					int gradeId = memberDataList.get(instPlayerId).getGradeId();
					if (gradeId == 1 || gradeId == 2) {
						award(instPlayerId, rewardLeader, "盟战胜利盟主、副盟主奖励");
					}
				}
			}
		}
		// MVP奖
		String rewardMVP = dictUnionWarInfo.getRewardMvp();
		if (rewardMVP.length() != 0 && mvp != null) {
			award(mvp.getInstPlayerId(), rewardMVP, "盟战胜利MVP奖励");
		}
	}

}
