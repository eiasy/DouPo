package com.huayi.doupo.logic.handler.util.unionwar;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstUnionWarAgainst;
import com.huayi.doupo.base.model.InstUnionWarContributionRank;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticMsgRule;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.util.unionwar.Battlefield.BattlefieldState;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import com.huayi.doupo.logic.util.luafight.LuaFightEngine;

public class Against implements Runnable {

	enum Event {
		INIT, JOIN, DISJOIN, SORT, AMBUSH, UNAMBUSH, FIGHT, A_START, A_B_START, A_B_FIGHT, A_B_END, A_END,
	}

	enum AgainstState {
		AS_NONE, AS_WAITING, AS_FIGHTING, AS_FINISHED,
	};

	// NOLOCK
	int againstIndex = 0; // 在UnionWarUtil.againsts中的下标。
	LuaFightEngine luaFightEngine = new LuaFightEngine(); // 战斗引擎
	Battlefield[] battlefields = { new Battlefield(0, this), new Battlefield(1, this), new Battlefield(2, this), new Battlefield(3, this) };

	// LOCK
	ReentrantLock lock = new ReentrantLock();
	AgainstState state = AgainstState.AS_NONE;
	Team teamA = null;
	Team teamB = null;
	Team winer = null; // winner
	int againstFightCount = 0;
	LinkedList<Integer> viewerList = new LinkedList<Integer>();
	LinkedList<Report> reportList = new LinkedList<Report>(); // TODO 下一天的对阵开始时清空本次战报

	public Against(int idx) {
		againstIndex = idx;
	}

	public void lock() {
		lock.lock();
	}

	public void unlock() {
		lock.unlock();
	}

	private void lockA() {
		if (teamA != null) {
			teamA.lock();
		}
	}

	private void unlockA() {
		if (teamA != null) {
			teamA.unlock();
		}
	}

	private void lockB() {
		if (teamB != null) {
			teamB.lock();
		}
	}

	private void unlockB() {
		if (teamB != null) {
			teamB.unlock();
		}
	}

	public void reinit(Team a, Team b) {
		lock();
		state = AgainstState.AS_WAITING;
		if (a != null) {
			a.against = this;
		}
		if (b != null) {
			b.against = this;
		}
		teamA = a;
		teamB = b;
		winer = null;
		againstFightCount = 0;
		viewerList.clear();
		reportList.clear();
		battlefields[0].reinit(a, b);
		battlefields[1].reinit(a, b);
		battlefields[2].reinit(a, b);
		battlefields[3].reinit(a, b);
		unlock();
	}

	public void clearViewer() {
		lock();
		viewerList.clear();
		unlock();
	}

	public String getMemberList(Team team, MemberList ml) {
		StringBuffer sb = new StringBuffer();
		for (int playerId : ml) {
			InstUnionWarContributionRank r = team.memberDataList.get(playerId);
			sb.append(r.getInstPlayerId()).append("|");
			sb.append(r.getInstPlayerName()).append("|");
			sb.append(r.getInstPlayerLevel()).append("|");
			sb.append(team.fightDataList.get(playerId).getPower()).append("|");
			sb.append(r.getInstPlayerHead()).append("|");
			sb.append(r.getGradeId()).append("|");
			InstPlayer instPlayer = DALFactory.getInstPlayerDAL().getModel(playerId, playerId);
			sb.append(instPlayer == null ? 0 : instPlayer.getVipLevel()).append("|"); // VIP 等级
			sb.append(r.getIsAlive()).append("/");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	public MessageData getCurrentStateMessageData() {
		MessageData md = new MessageData();
		md.putIntItem("event", Event.INIT.ordinal());
		md.putIntItem("againstIndex", againstIndex);
		md.putIntItem("state", state.ordinal());
		md.putIntItem("isAWin", winer == teamA ? 1 : 0);
		// A
		if (teamA != null) {
			md.putIntItem("aId", teamA.getUnionId());
			md.putStringItem("aName", teamA.getUnionName());
			md.putIntItem("aScore", teamA.getTotalScore());
			md.putIntItem("aLimit", UnionWarUtil.getAmbushLimit(teamA.getUnionLevel()));
			// A Lists
			md.putStringItem("aNoQualification", getMemberList(teamA, teamA.memberListNoQualification));
			md.putStringItem("aNotRegistered", getMemberList(teamA, teamA.memberListNotRegistered));
			md.putStringItem("aAmbush", getMemberList(teamA, teamA.memberListAmbush));
			md.putStringItem("aBattfield1", getMemberList(teamA, teamA.memberListBattlefield[0]));
			md.putStringItem("aBattfield2", getMemberList(teamA, teamA.memberListBattlefield[1]));
			md.putStringItem("aBattfield3", getMemberList(teamA, teamA.memberListBattlefield[2]));
			md.putStringItem("aBattfield4", getMemberList(teamA, teamA.memberListBattlefield[3]));
		}
		// B
		if (teamB != null) {
			md.putIntItem("bId", teamB.getUnionId());
			md.putStringItem("bName", teamB.getUnionName());
			md.putIntItem("bScore", teamB.getTotalScore());
			md.putIntItem("bLimit", UnionWarUtil.getAmbushLimit(teamB.getUnionLevel()));
			// B Lists
			md.putStringItem("bNoQualification", getMemberList(teamB, teamB.memberListNoQualification));
			md.putStringItem("bNotRegistered", getMemberList(teamB, teamB.memberListNotRegistered));
			md.putStringItem("bAmbush", getMemberList(teamB, teamB.memberListAmbush));
			md.putStringItem("bBattfield1", getMemberList(teamB, teamB.memberListBattlefield[0]));
			md.putStringItem("bBattfield2", getMemberList(teamB, teamB.memberListBattlefield[1]));
			md.putStringItem("bBattfield3", getMemberList(teamB, teamB.memberListBattlefield[2]));
			md.putStringItem("bBattfield4", getMemberList(teamB, teamB.memberListBattlefield[3]));
		}
		// Battlefield
		for (int i = 0; i < UnionWarUtil.BATTLEFIELD_SIZE; ++i) {
			MessageData mdBattlefield = new MessageData();
			Battlefield b = battlefields[i];
			mdBattlefield.putIntItem("state", b.battlefieldState.ordinal());
			mdBattlefield.putIntItem("isAWin", b.result);
			mdBattlefield.putIntItem("score", UnionWarUtil.getBattlefieldScore(i));
			mdBattlefield.putIntItem("aliveScore", UnionWarUtil.getBattlefieldAliveScore(i));
			mdBattlefield.putIntItem("killScore", UnionWarUtil.getBattlefieldKillScore(i));
			if (teamA != null) {
				mdBattlefield.putIntItem("aWinScore", teamA.getBattlefieldWinScore(i));
				mdBattlefield.putIntItem("aKillScore", teamA.getBattlefieldKillScore(i));
				mdBattlefield.putIntItem("aAliveScore", teamA.getBattlefieldAliveScore(i));
			}
			if (teamB != null) {
				mdBattlefield.putIntItem("bWinScore", teamB.getBattlefieldWinScore(i));
				mdBattlefield.putIntItem("bKillScore", teamB.getBattlefieldKillScore(i));
				mdBattlefield.putIntItem("bAliveScore", teamB.getBattlefieldAliveScore(i));
			}
			mdBattlefield.putStringItem("reward", UnionWarUtil.getBattlefieldReward(i));
			md.putMessageItem("" + UnionWarUtil.idx2id(i), mdBattlefield.getMsgData());
		}
		// 战报列表
		for (int i = 0; i < reportList.size(); ++i) {
			md.putMessageItem("report" + (i + 1), reportToMsg(reportList.get(i)).getMsgData());
		}
		return md;
	}

	public void addViewer(int instPlayerId) {
		lockA();
		lockB();
		lock();
		if (!viewerList.contains(instPlayerId)) {
			viewerList.add(instPlayerId);
		}
		Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
		if (player != null) { // 只要在线就发送初始化数据
			MessageUtil.pushMsg(player.getChannelId(), StaticMsgRule.pushUnionWar, getCurrentStateMessageData());
		}
		unlock();
		unlockB();
		unlockA();
	}

	public void addViewer(LinkedList<Integer> list) {
		lockA();
		lockB();
		lock();
		MessageData md = getCurrentStateMessageData();
		for (int instPlayerId : list) {
			if (!viewerList.contains(instPlayerId)) {
				viewerList.add(instPlayerId);
			}
			Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
			if (player != null) { // 只要在线就发送初始化数据
				MessageUtil.pushMsg(player.getChannelId(), StaticMsgRule.pushUnionWar, md);
			}
		}
		unlock();
		unlockB();
		unlockA();
	}

	public void delViewer(int instPlayerId) {
		lock();
		Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
		if (player == null) { // 不在线时才真正移除
			viewerList.remove(Integer.valueOf(instPlayerId));
		}
		unlock();
	}

	public void join(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldIdx) {
		if (teamA != null && teamA.getUnionId() == instUnionId) {
			teamA.join(msgMap, channelId, instPlayerId, instUnionId, battlefieldIdx);
			return;
		}
		if (teamB != null && teamB.getUnionId() == instUnionId) {
			teamB.join(msgMap, channelId, instPlayerId, instUnionId, battlefieldIdx);
			return;
		}
		// Error
	}

	public void onJoin(Team team, int instPlayerId, int battlefieldIdx) {
		lock();
		MessageData md = new MessageData();
		md.putIntItem("event", Event.JOIN.ordinal());
		md.putIntItem("unionId", team.getUnionId());
		md.putIntItem("playerId", instPlayerId);
		md.putIntItem("battlefieldId", UnionWarUtil.idx2id(battlefieldIdx));
		pushToViewer(md);
		unlock();
	}

	public void disjoin(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldIdx) {
		if (teamA != null && teamA.getUnionId() == instUnionId) {
			teamA.disjoin(msgMap, channelId, instPlayerId, instUnionId, battlefieldIdx);
			return;
		}
		if (teamB != null && teamB.getUnionId() == instUnionId) {
			teamB.disjoin(msgMap, channelId, instPlayerId, instUnionId, battlefieldIdx);
			return;
		}
		// Error
	}

	public void onDisjoin(Team team, int instPlayerId, int battlefieldIdx) {
		lock();
		MessageData md = new MessageData();
		md.putIntItem("event", Event.DISJOIN.ordinal());
		md.putIntItem("unionId", team.getUnionId());
		md.putIntItem("playerId", instPlayerId);
		md.putIntItem("battlefieldId", UnionWarUtil.idx2id(battlefieldIdx));
		pushToViewer(md);
		unlock();
	}

	public void sort(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldIdx) {
		if (teamA != null && teamA.getUnionId() == instUnionId) {
			teamA.sort(msgMap, channelId, instPlayerId, instUnionId, battlefieldIdx);
			return;
		}
		if (teamB != null && teamB.getUnionId() == instUnionId) {
			teamB.sort(msgMap, channelId, instPlayerId, instUnionId, battlefieldIdx);
			return;
		}
		// Error
	}

	public void onSort(Team team, int battlefieldIdx) {
		lock();
		MessageData md = new MessageData();
		md.putIntItem("event", Event.SORT.ordinal());
		md.putIntItem("unionId", team.getUnionId());
		md.putIntItem("battlefieldId", UnionWarUtil.idx2id(battlefieldIdx));
		StringBuffer sb = new StringBuffer();
		for (Integer e : team.memberListBattlefield[battlefieldIdx]) {
			sb.append(e).append("_");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		md.putStringItem("idList", sb.toString());
		pushToViewer(md);
		unlock();
	}

	public void ambush(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		if (teamA != null && teamA.getUnionId() == instUnionId) {
			teamA.ambush(msgMap, channelId, instPlayerId, instUnionId);
			return;
		}
		if (teamB != null && teamB.getUnionId() == instUnionId) {
			teamB.ambush(msgMap, channelId, instPlayerId, instUnionId);
			return;
		}
		// Error
	}

	public void onAmbush(Team team, int instPlayerId) {
		lock();
		MessageData md = new MessageData();
		md.putIntItem("event", Event.AMBUSH.ordinal());
		md.putIntItem("unionId", team.getUnionId());
		md.putIntItem("playerId", instPlayerId);
		pushToViewer(md);
		unlock();
	}

	public void unambush(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		if (teamA != null && teamA.getUnionId() == instUnionId) {
			teamA.unambush(msgMap, channelId, instPlayerId, instUnionId);
			return;
		}
		if (teamB != null && teamB.getUnionId() == instUnionId) {
			teamB.unambush(msgMap, channelId, instPlayerId, instUnionId);
			return;
		}
		// Error
	}

	public void onUnambush(Team team, int instPlayerId, int lastType) {
		lock();
		MessageData md = new MessageData();
		md.putIntItem("event", Event.UNAMBUSH.ordinal());
		md.putIntItem("unionId", team.getUnionId());
		md.putIntItem("playerId", instPlayerId);
		if (lastType == -1) {
			md.putIntItem("lastType", 0); // 代表未报名
		} else {
			md.putIntItem("lastType", UnionWarUtil.idx2id(lastType)); // 代表上次的battlefieldIdx
		}
		pushToViewer(md);
		unlock();
	}

	public void fight(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldIdx) {
		if (teamA != null && teamA.getUnionId() == instUnionId) {
			teamA.fight(msgMap, channelId, instPlayerId, instUnionId, battlefieldIdx);
			return;
		}
		if (teamB != null && teamB.getUnionId() == instUnionId) {
			teamB.fight(msgMap, channelId, instPlayerId, instUnionId, battlefieldIdx);
			return;
		}
		// Error
	}

	public void onFight(Team team, int instPlayerId, int battlefieldIdx) {
		lock();
		// 更新战场的存活积分
		team.setBattlefieldAliveScore(battlefieldIdx, team.getBattlefieldMemberList(battlefieldIdx).getAliveCount() * UnionWarUtil.getBattlefieldAliveScore(battlefieldIdx));
		// 记录战报
		Report report = new Report();
		report.setEvent(Event.FIGHT.ordinal());
		report.setBattlefieldIdx(battlefieldIdx);
		if (teamA == team) {
			report.setInstPlayerA(instPlayerId);
			// report.setBattlefieldWinScoreA(team.getBattlefieldWinScore(battlefieldIdx));
			report.setBattlefieldKillScoreA(team.getBattlefieldKillScore(battlefieldIdx));
			report.setBattlefieldAliveScoreA(team.getBattlefieldAliveScore(battlefieldIdx));
			report.setTotalScoreA(team.getTotalScore());
		}
		if (teamB == team) {
			report.setInstPlayerB(instPlayerId);
			// report.setBattlefieldWinScoreB(team.getBattlefieldWinScore(battlefieldIdx));
			report.setBattlefieldKillScoreB(team.getBattlefieldKillScore(battlefieldIdx));
			report.setBattlefieldAliveScoreB(team.getBattlefieldAliveScore(battlefieldIdx));
			report.setTotalScoreB(team.getTotalScore());
		}
		reportList.add(report);
		// 推送
		pushToViewer(reportToMsg(report));
		unlock();
	}

	public void unionReplay(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		int videoId = 0;
		try {
			lock(); // 其实可以不加锁
			if (videoId < 0 || reportList.size() <= videoId) {
				MessageUtil.sendFailMsg(channelId, msgMap, "查看战报参数错误");
				return;
			}
			String video = reportList.get(videoId).getVideoLua();
			if (video == null || video.isEmpty()) {
				MessageUtil.sendFailMsg(channelId, msgMap, "战报不存在");
				return;
			}
			MessageUtil.sendFailMsg(channelId, msgMap, video); // TODO 发送真正录像，是否进行编译？
		} finally {
			unlock();
		}
	}

	public void showUnionWar(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		lockA();
		lockB();
		lock();
		MessageData md = new MessageData();
		md.putIntItem("state", state.ordinal());
		if (teamA != null && instUnionId == teamA.getUnionId()) {
			md.putIntItem("ambushMax", UnionWarUtil.getAmbushLimit(teamA.getUnionLevel()));
		} else if (teamB != null && instUnionId == teamB.getUnionId()) {
			md.putIntItem("ambushMax", UnionWarUtil.getAmbushLimit(teamB.getUnionLevel()));
		} else {
			assert false : "出错";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < UnionWarUtil.BATTLEFIELD_SIZE; ++i) {
			sb.append(UnionWarUtil.getBattlefieldScore(i));
			sb.append("_");
			sb.append(UnionWarUtil.getBattlefieldReward(i));
			sb.append("|");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		md.putStringItem("battlefieldIntroduction", sb.toString());
		MessageUtil.sendSuccMsg(channelId, msgMap, md);
		unlock();
		unlockB();
		unlockA();
	}

	public void start() {
		new Thread(this).start();
	}

	public void run() {
		try {
			runAgainst();
		} catch (Exception e) {
			// TODO 处理
			LogUtil.error(e);
			UnionWarUtil.forceShutdown();
		}
	}

	// Thread run
	public void runAgainst() {
		onAgainstStart();
		for (int i = 0; i < UnionWarUtil.BATTLEFIELD_SIZE; ++i) {
			battlefields[i].runBattlefield();
		}
		onAgainstDone();
	}

	// Events in Thread
	public void onAgainstStart() {
		lock();
		state = AgainstState.AS_FIGHTING;
		// 记录战报
		Report report = new Report();
		report.setEvent(Event.A_START.ordinal());
		reportList.add(report);
		// 推送
		pushToViewer(reportToMsg(report));
		unlock();
	}

	public void onBattlefieldStart(Battlefield battlefield) {
		lock();
		battlefield.battlefieldState = BattlefieldState.BS_FIGHTING;
		// 记录战报
		Report report = new Report();
		report.setEvent(Event.A_B_START.ordinal());
		report.setBattlefieldIdx(battlefield.battlefieldIdx);
		reportList.add(report);
		// 推送
		pushToViewer(reportToMsg(report));
		unlock();
	}

	public void onFightDone(Battlefield battlefield, boolean isAWin, InstUnionWarContributionRank a, InstUnionWarContributionRank b, int battlefieldFightCount, String videoLua) {
		lock();
		// 记录战报
		Report report = new Report();
		report.setEvent(Event.A_B_FIGHT.ordinal());
		report.setBattlefieldIdx(battlefield.battlefieldIdx);
		report.setInstPlayerA(a.getInstPlayerId());
		report.setInstPlayerB(b.getInstPlayerId());
		report.setIsAWin(isAWin ? 1 : 0);
		report.setFightIndex(++againstFightCount);
		report.setVideoId(reportList.size());
		report.setVideoLua(""); // TODO 暂时不保存录像，不允许查看。
		// report.setBattlefieldWinScoreA(teamA.getBattlefieldWinScore(battlefield.battlefieldIdx));
		report.setBattlefieldKillScoreA(teamA.getBattlefieldKillScore(battlefield.battlefieldIdx));
		report.setBattlefieldAliveScoreA(teamA.getBattlefieldAliveScore(battlefield.battlefieldIdx));
		report.setTotalScoreA(teamA.getTotalScore());
		// report.setBattlefieldWinScoreB(teamB.getBattlefieldWinScore(battlefield.battlefieldIdx));
		report.setBattlefieldKillScoreB(teamB.getBattlefieldKillScore(battlefield.battlefieldIdx));
		report.setBattlefieldAliveScoreB(teamB.getBattlefieldAliveScore(battlefield.battlefieldIdx));
		report.setTotalScoreB(teamB.getTotalScore());
		reportList.add(report);
		// System.out.println("======================");
		// System.out.println(videoLua);
		// System.out.println("======================");
		// 推送
		pushToViewer(reportToMsg(report));
		unlock();
	}

	public void onBattlefieldDone(Battlefield battlefield, int result) {
		lock();
		battlefield.battlefieldState = BattlefieldState.BS_FINISHED;
		// 记录战报
		Report report = new Report();
		report.setEvent(Event.A_B_END.ordinal());
		report.setBattlefieldIdx(battlefield.battlefieldIdx);
		report.setIsAWin(result);
		if (teamA != null) {
			report.setBattlefieldWinScoreA(teamA.getBattlefieldWinScore(battlefield.battlefieldIdx));
			report.setBattlefieldKillScoreA(teamA.getBattlefieldKillScore(battlefield.battlefieldIdx));
			report.setBattlefieldAliveScoreA(teamA.getBattlefieldAliveScore(battlefield.battlefieldIdx));
			report.setTotalScoreA(teamA.getTotalScore());
		}
		if (teamB != null) {
			report.setBattlefieldWinScoreB(teamB.getBattlefieldWinScore(battlefield.battlefieldIdx));
			report.setBattlefieldKillScoreB(teamB.getBattlefieldKillScore(battlefield.battlefieldIdx));
			report.setBattlefieldAliveScoreB(teamB.getBattlefieldAliveScore(battlefield.battlefieldIdx));
			report.setTotalScoreB(teamB.getTotalScore());
		}
		reportList.add(report);
		// 推送
		pushToViewer(reportToMsg(report));
		unlock();
	}

	// 结束
	public void onAgainstDone() {
		lockA();
		lockB();
		lock();
		state = AgainstState.AS_FINISHED;
		// 处理胜利者
		if (teamB == null) {
			winer = teamA;
		} else if (teamA == null) {
			winer = teamB;
		} else if (teamA.getTotalScore() > teamB.getTotalScore()) {
			winer = teamA;
		} else if (teamA.getTotalScore() < teamB.getTotalScore()) {
			winer = teamB;
		} else if (teamA.getUnionRank() < teamB.getUnionRank()) { // 排名
			winer = teamA;
		} else {
			winer = teamB;
		}
		// 结算对阵玩家的贡献值及MVP
		if (teamA != null) {
			teamA.calcContribution();
		}
		if (teamB != null) {
			teamB.calcContribution();
		}
		// 保存对阵信息
		InstUnionWarAgainst instUnionWarAgainst = new InstUnionWarAgainst();
		instUnionWarAgainst.setAgainstIndex(againstIndex);
		instUnionWarAgainst.setIsAWin(winer == teamA ? 1 : 0);
		if (teamA != null) {
			instUnionWarAgainst.setTeamA(teamA.getUnionId());
			instUnionWarAgainst.setTeamNameA(teamA.getUnionName());
			if (teamA.mvp != null) {
				instUnionWarAgainst.setMvpNameA(teamA.mvp.getInstPlayerName());
			}
			instUnionWarAgainst.setABattlefieldScore1(teamA.getBattlefieldScore(0));
			instUnionWarAgainst.setABattlefieldScore2(teamA.getBattlefieldScore(1));
			instUnionWarAgainst.setABattlefieldScore3(teamA.getBattlefieldScore(2));
			instUnionWarAgainst.setABattlefieldScore4(teamA.getBattlefieldScore(3));
		}
		if (teamB != null) {
			instUnionWarAgainst.setTeamB(teamB.getUnionId());
			instUnionWarAgainst.setTeamNameB(teamB.getUnionName());
			if (teamB.mvp != null) {
				instUnionWarAgainst.setMvpNameB(teamB.mvp.getInstPlayerName());
			}
			instUnionWarAgainst.setBBattlefieldScore1(teamB.getBattlefieldScore(0));
			instUnionWarAgainst.setBBattlefieldScore2(teamB.getBattlefieldScore(1));
			instUnionWarAgainst.setBBattlefieldScore3(teamB.getBattlefieldScore(2));
			instUnionWarAgainst.setBBattlefieldScore4(teamB.getBattlefieldScore(3));
		}
		try {
			DALFactory.getInstUnionWarAgainstDAL().add(instUnionWarAgainst, 0);
		} catch (Exception e) {
		}
		// 本次战报
		Report report = new Report();
		report.setEvent(Event.A_END.ordinal());
		report.setIsAWin(winer == teamA ? 1 : 0);
		if (teamA != null) {
			report.setTotalScoreA(teamA.getTotalScore());
		}
		if (teamB != null) {
			report.setTotalScoreB(teamB.getTotalScore());
		}
		reportList.add(report);
		// 推送
		pushToViewer(reportToMsg(report));
		// 奖励
		if (teamA != null) {
			teamA.award();
		}
		if (teamB != null) {
			teamB.award();
		}
		viewerList.clear();
		UnionWarUtil.onAgainstFinished(this);
		unlock();
		unlockB();
		unlockA();
	}

	private MessageData reportToMsg(Report report) {
		MessageData md = new MessageData();
		md.putIntItem("event", report.getEvent());
		if (report.getEvent() == Event.FIGHT.ordinal()) {
			md.putIntItem("battlefieldId", UnionWarUtil.idx2id(report.getBattlefieldIdx()));
			if (report.getInstPlayerA() != 0) {
				md.putIntItem("playerId", report.getInstPlayerA());
				// md.putIntItem("winScore", report.getBattlefieldWinScoreA());
				md.putIntItem("killScore", report.getBattlefieldKillScoreA());
				md.putIntItem("aliveScore", report.getBattlefieldAliveScoreA());
				md.putIntItem("score", report.getTotalScoreA());
			}
			if (report.getInstPlayerB() != 0) {
				md.putIntItem("playerId", report.getInstPlayerB());
				// md.putIntItem("winScore", report.getBattlefieldWinScoreB());
				md.putIntItem("killScore", report.getBattlefieldKillScoreB());
				md.putIntItem("aliveScore", report.getBattlefieldAliveScoreB());
				md.putIntItem("score", report.getTotalScoreB());
			}
		} else if (report.getEvent() == Event.A_START.ordinal()) {
		} else if (report.getEvent() == Event.A_B_START.ordinal()) {
			md.putIntItem("battlefieldId", UnionWarUtil.idx2id(report.getBattlefieldIdx()));
		} else if (report.getEvent() == Event.A_B_FIGHT.ordinal()) {
			md.putIntItem("battlefieldId", UnionWarUtil.idx2id(report.getBattlefieldIdx()));
			md.putIntItem("aId", report.getInstPlayerA());
			md.putIntItem("bId", report.getInstPlayerB());
			md.putIntItem("isAWin", report.getIsAWin());
			md.putIntItem("fightCount", report.getFightIndex());
			md.putIntItem("instVideoId", report.getVideoId());
			// md.putIntItem("aWinScore", report.getBattlefieldWinScoreA());
			md.putIntItem("aKillScore", report.getBattlefieldKillScoreA());
			md.putIntItem("aAliveScore", report.getBattlefieldAliveScoreA());
			md.putIntItem("aScore", report.getTotalScoreA());
			// md.putIntItem("bWinScore", report.getBattlefieldWinScoreB());
			md.putIntItem("bKillScore", report.getBattlefieldKillScoreB());
			md.putIntItem("bAliveScore", report.getBattlefieldAliveScoreB());
			md.putIntItem("bScore", report.getTotalScoreB());
		} else if (report.getEvent() == Event.A_B_END.ordinal()) {
			md.putIntItem("battlefieldId", UnionWarUtil.idx2id(report.getBattlefieldIdx()));
			md.putIntItem("isAWin", report.getIsAWin());
			md.putIntItem("aWinScore", report.getBattlefieldWinScoreA());
			md.putIntItem("aKillScore", report.getBattlefieldKillScoreA());
			md.putIntItem("aAliveScore", report.getBattlefieldAliveScoreA());
			md.putIntItem("aScore", report.getTotalScoreA());
			md.putIntItem("bWinScore", report.getBattlefieldWinScoreB());
			md.putIntItem("bKillScore", report.getBattlefieldKillScoreB());
			md.putIntItem("bAliveScore", report.getBattlefieldAliveScoreB());
			md.putIntItem("bScore", report.getTotalScoreB());
		} else if (report.getEvent() == Event.A_END.ordinal()) {
			md.putIntItem("isAWin", report.getIsAWin());
			md.putIntItem("aScore", report.getTotalScoreA());
			md.putIntItem("bScore", report.getTotalScoreB());
		}
		return md;
	}

	private void pushToViewer(MessageData md) {
		for (int playerId : viewerList) {
			Player player = PlayerMapUtil.getPlayerByPlayerId(playerId);
			if (player != null) {
				MessageUtil.pushMsg(player.getChannelId(), StaticMsgRule.pushUnionWar, md);
			}
		}
	}

}
