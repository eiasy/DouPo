package com.huayi.doupo.logic.handler.util.unionwar;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictUnionWarAmbush;
import com.huayi.doupo.base.model.DictUnionWarInfo;
import com.huayi.doupo.base.model.InstUnion;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.InstUnionWarAgainst;
import com.huayi.doupo.base.model.InstUnionWarContributionRank;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class UnionWarUtil {

	static final boolean ENABLE_DEBUG = false; // 开启调试
	static final long MIN_FIGHT_SECONDS = 10;// 战斗间隔时间
	static final int AGAINST_SIZE = 7; // 对阵次数
	static final int BATTLEFIELD_SIZE = 4; // 战场数
	static final int HOUR_OF_QUALIFICATION = 72; // 加入联盟多少小时才有资格参加该联盟的盟战。

	static final int HOUR_OF_INIT = 00; // 盟战初始化时间
	static final int HOUR_OF_JOIN = 17; // 报名截止时间
	static final int HOUR_OF_FIGHT = 19; // 战斗开始时间、伏击时间

	// TODO 如何保证一天内的盟战确实结束。

	// 连续的4天
	static final int DAY_8_TO_4 = Calendar.MONDAY;
	static final int DAY_4_TO_2 = Calendar.TUESDAY;
	static final int DAY_2_TO_1 = Calendar.WEDNESDAY;
	static final int DAY_1_TO_0 = Calendar.THURSDAY; // 哪一天关闭本次盟战
	static boolean isAllowOpen = false; // 盟战是否允许开启（该值在本次盟战的8进4的当天00:00:00生效）
	static boolean isThisWeekOpend = false; // 盟战是否本周开启（盟战的三天内有效）
	static boolean useTimeLimit = false; // 使用时间限制（报名截止、伏击开始）

	static int countOfThread = 0; // Against线程个数
	static int unionWarIndex = 0; // 1=8进4、2=4进2、3=2进1
	static Against[] againsts = new Against[AGAINST_SIZE];
	static HashMap<Integer, Against> unionIdToAgainst = new HashMap<Integer, Against>();

	// 定时器
	static ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

	public static void setAllowOpen(boolean allowOpen) {
		isAllowOpen = allowOpen;
	}

	public static boolean isAllowOpen() {
		return isAllowOpen;
	}

	public static void init() {
		long msOfDay = TimeUnit.DAYS.toMillis(1);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long midnight = c.getTimeInMillis() + msOfDay;
		// 开启报名
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				// TODO 移除
				try {
					switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
						case DAY_8_TO_4:
							openAgainst4();
							break;
						case DAY_4_TO_2:
							openAgainst2();
							break;
						case DAY_2_TO_1:
							openAgainst1();
						case DAY_1_TO_0:
							closeAgainst();
							break;
					}
				} catch (Exception e) {
					LogUtil.error("UnionWar " + e);
					forceShutdown();
				}
			}
		}, midnight - System.currentTimeMillis() + TimeUnit.HOURS.toMillis(HOUR_OF_INIT), msOfDay, TimeUnit.MILLISECONDS);

		// 开始战斗
		ses.scheduleAtFixedRate(new Runnable() {
			public void run() {
				// TODO 移除
				try {
					if (isThisWeekOpend) {
						switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
							case DAY_8_TO_4:
								startAgainst4();
								break;
							case DAY_4_TO_2:
								startAgainst2();
								break;
							case DAY_2_TO_1:
								startAgainst1();
								break;
						}
					}
				} catch (Exception e) {
					LogUtil.error("UnionWar " + e);
					forceShutdown();
				}
			}
		}, midnight - System.currentTimeMillis() + TimeUnit.HOURS.toMillis(HOUR_OF_FIGHT), msOfDay, TimeUnit.MILLISECONDS);
		// TODO 周1启服
		if (Calendar.getInstance().get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
			new Thread() {
				public void run() {
					// TODO 移除
					try {
						if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 16) {
							LogUtil.error("UnionWar 周一启动服务器，时间未过16:00，盟战开启");
						} else {
							LogUtil.error("UnionWar 周一启动服务器，时间超过16:00，盟战不开启");
						}
						try {
							sleep(TimeUnit.MINUTES.toMillis(1)); // 延迟1分钟
						} catch (InterruptedException e) {
						}
						openAgainst4();
					} catch (Exception e) {
						LogUtil.error("UnionWar " + e);
						forceShutdown();
					}
				}
			}.start();
		}

		// TODO 移除 开启调试代码
		if (ENABLE_DEBUG) {
			new Thread() {
				public void run() {
					while (true) {
						try {
							ServerSocket ss = new ServerSocket(987);
							Socket s = ss.accept();
							ss.close();
							while (true) {
								int c, d;
								c = s.getInputStream().read();
								d = s.getInputStream().read();
								if (c == -1 || d == -1)
									break;
								if (c == 'k' && d == 'k')
									isAllowOpen = true;
								if (c == 'g' && d == 'g')
									isAllowOpen = false;
								if (c == 'k' && d == '1')
									openAgainst4();
								if (c == 'k' && d == '2')
									openAgainst2();
								if (c == 'k' && d == '3')
									openAgainst1();
								if (c == 'd' && d == '1')
									startAgainst4();
								if (c == 'd' && d == '2')
									startAgainst2();
								if (c == 'd' && d == '3')
									startAgainst1();
								if (c == 'd' && d == 'd')
									closeAgainst();
							}
						} catch (Exception e) {
							LogUtil.error("UnionWar " + e);
						}
					}
				}
			}.start();
		}
	}

	public static void forceShutdown() {
		isAllowOpen = false;
		isThisWeekOpend = false;
		unionWarIndex = 0;
	}

	public static int[] getPosToRank() {
		int[] posToRank = new int[8];
		LinkedList<Integer> pool = new LinkedList<Integer>();
		LinkedList<Integer> left = new LinkedList<Integer>();
		LinkedList<Integer> right = new LinkedList<Integer>();
		Random r = new Random();
		boolean inLeft = r.nextBoolean();
		left.add(inLeft ? 0 : 1);
		right.add(inLeft ? 1 : 0);
		for (int i = 2; i < 8; ++i) {
			pool.add(i);
		}
		for (int i = 0; i < 3; ++i) {
			left.add(pool.remove(r.nextInt(pool.size())));
			right.add(pool.remove(r.nextInt(pool.size())));
		}
		for (int i = 0; i < 4; ++i) {
			posToRank[i + 0] = left.remove(r.nextInt(left.size()));
			posToRank[i + 4] = right.remove(r.nextInt(right.size()));
		}
		return posToRank;
	}

	public static void openAgainst4() {
		if (!isAllowOpen) {
			return;
		}
		// TODO 移除
		Calendar c = Calendar.getInstance();
		c.set(2015, 11, 27, 23, 0);
		if (System.currentTimeMillis() > c.getTimeInMillis()) { // 超过2015/12/27日23:00 则不允许开启
			LogUtil.error("UnionWar 时间超过2015/12/27 23:00:xx");
			return;
		}

		List<InstUnion> instUnionList = DALFactory.getInstUnionDAL().getList(" level >= 3 order by level desc, exp desc, createTime limit 0,8", 0);
		if (instUnionList.size() < 8) {
			LogUtil.error("UnionWar 盟战队伍不足8个，不开启");
			return;
		}

		for (int i = 0; i < AGAINST_SIZE; ++i) { // TODO 临时解决
			againsts[i] = new Against(i);
		}
		unionIdToAgainst.clear();

		isThisWeekOpend = true;
		LogUtil.error("UnionWar Open UnionWar");

		Team teamA, teamB;
		InstUnion instUnionA, instUnionB;
		int[] posToRank = getPosToRank();
		for (int i = 0; i <= 3; ++i) {
			instUnionA = instUnionList.get(posToRank[2 * i + 0]);
			if (instUnionA == null) {
				teamA = null;
			} else {
				teamA = new Team(instUnionA.getId(), instUnionA.getName(), instUnionA.getLevel(), 2 * i + 0);
				unionIdToAgainst.put(instUnionA.getId(), againsts[i]);
			}
			instUnionB = instUnionList.get(posToRank[2 * i + 1]);
			if (instUnionB == null) {
				teamB = null;
			} else {
				teamB = new Team(instUnionB.getId(), instUnionB.getName(), instUnionB.getLevel(), 2 * i + 1);
				unionIdToAgainst.put(instUnionB.getId(), againsts[i]);
			}
			againsts[i].reinit(teamA, teamB);
		}
		unionWarIndex = 1;
		try { // TODO 清空的时刻
			DALFactory.getInstUnionWarAgainstDAL().deleteByWhere(" 1=1 "); // 清空所有对阵
			// DALFactory.getInstUnionWarContributionRankDAL().deleteByWhere(" 1=1 "); // 清空所有贡献
		} catch (Exception e) {
		}
		// 添加在线观众
		for (int i = 0; i <= 3; ++i) {
			LinkedList<Integer> viewerList = new LinkedList<Integer>();
			StringBuffer where = new StringBuffer();
			if (againsts[i].teamA != null) {
				where.append(" instUnionId = ").append(againsts[i].teamA.unionId);
			}
			if (againsts[i].teamB != null) {
				if (where.length() != 0) {
					where.append(" or ");
				}
				where.append(" instUnionId = ").append(againsts[i].teamB.unionId);
			}
			List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList(where.toString(), 0);
			for (InstUnionMember instUnionMember : instUnionMemberList) {
				// join(null, "xx_x", instUnionMember.getInstPlayerId(), instUnionMember.getInstUnionId(), new Random().nextInt(4) + 1);
				viewerList.add(instUnionMember.getInstPlayerId());
			}
			againsts[i].addViewer(viewerList);
		}
		LogUtil.error("UnionWar 8 to 4 Open");
	}

	public static void startAgainst4() {
		if (!isThisWeekOpend || unionWarIndex != 1) {
			return;
		}
		countOfThread = 4;
		againsts[0].start();
		againsts[1].start();
		againsts[2].start();
		againsts[3].start();
		LogUtil.error("UnionWar 8 to 4 Start");
	}

	public static void onAgainst4Done() {
		LogUtil.error("UnionWar 8 to 4 Done");
	}

	public static void openAgainst2() {
		if (!isThisWeekOpend) {
			return;
		}
		unionIdToAgainst.clear();
		Team teamA, teamB;
		teamA = againsts[0].winer;
		if (teamA != null) {
			teamA = new Team(teamA.getUnionId(), teamA.getUnionName(), teamA.getUnionLevel(), teamA.getUnionRank());
			unionIdToAgainst.put(teamA.getUnionId(), againsts[4]);
		}
		teamB = againsts[1].winer;
		if (teamB != null) {
			teamB = new Team(teamB.getUnionId(), teamB.getUnionName(), teamB.getUnionLevel(), teamB.getUnionRank());
			unionIdToAgainst.put(teamB.getUnionId(), againsts[4]);
		}
		againsts[4].reinit(teamA, teamB);

		teamA = againsts[2].winer;
		if (teamA != null) {
			teamA = new Team(teamA.getUnionId(), teamA.getUnionName(), teamA.getUnionLevel(), teamA.getUnionRank());
			unionIdToAgainst.put(teamA.getUnionId(), againsts[5]);
		}
		teamB = againsts[3].winer;
		if (teamB != null) {
			teamB = new Team(teamB.getUnionId(), teamB.getUnionName(), teamB.getUnionLevel(), teamB.getUnionRank());
			unionIdToAgainst.put(teamB.getUnionId(), againsts[5]);
		}
		againsts[5].reinit(teamA, teamB);
		unionWarIndex = 2;

		// 添加在线观众
		for (int i = 4; i <= 5; ++i) {
			LinkedList<Integer> viewerList = new LinkedList<Integer>();
			StringBuffer where = new StringBuffer();
			if (againsts[i].teamA != null) {
				where.append(" instUnionId = ").append(againsts[i].teamA.unionId);
			}
			if (againsts[i].teamB != null) {
				if (where.length() != 0) {
					where.append(" or ");
				}
				where.append(" instUnionId = ").append(againsts[i].teamB.unionId);
			}
			List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList(where.toString(), 0);
			for (InstUnionMember instUnionMember : instUnionMemberList) {
				// join(null, "xx_x", instUnionMember.getInstPlayerId(), instUnionMember.getInstUnionId(), new Random().nextInt(4) + 1);
				viewerList.add(instUnionMember.getInstPlayerId());
			}
			againsts[i].addViewer(viewerList);
		}
		LogUtil.error("UnionWar 4 to 2 Open");
	}

	public static void startAgainst2() {
		if (!isThisWeekOpend || unionWarIndex != 2) {
			return;
		}
		countOfThread = 2;
		againsts[4].start();
		againsts[5].start();
		LogUtil.error("UnionWar 4 to 2 Start");
	}

	public static void onAgainst2Done() {
		LogUtil.error("UnionWar 4 to 2 Done");
	}

	public static void openAgainst1() {
		if (!isThisWeekOpend) {
			return;
		}
		unionIdToAgainst.clear();
		Team teamA, teamB;
		teamA = againsts[4].winer;
		if (teamA != null) {
			teamA = new Team(teamA.getUnionId(), teamA.getUnionName(), teamA.getUnionLevel(), teamA.getUnionRank());
			unionIdToAgainst.put(teamA.getUnionId(), againsts[6]);
		}
		teamB = againsts[5].winer;
		if (teamB != null) {
			teamB = new Team(teamB.getUnionId(), teamB.getUnionName(), teamB.getUnionLevel(), teamB.getUnionRank());
			unionIdToAgainst.put(teamB.getUnionId(), againsts[6]);
		}
		againsts[6].reinit(teamA, teamB);
		unionWarIndex = 3;

		// 添加在线观众
		for (int i = 6; i <= 6; ++i) {
			LinkedList<Integer> viewerList = new LinkedList<Integer>();
			StringBuffer where = new StringBuffer();
			if (againsts[i].teamA != null) {
				where.append(" instUnionId = ").append(againsts[i].teamA.unionId);
			}
			if (againsts[i].teamB != null) {
				if (where.length() != 0) {
					where.append(" or ");
				}
				where.append(" instUnionId = ").append(againsts[i].teamB.unionId);
			}
			List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList(where.toString(), 0);
			for (InstUnionMember instUnionMember : instUnionMemberList) {
				// join(null, "xx_x", instUnionMember.getInstPlayerId(), instUnionMember.getInstUnionId(), new Random().nextInt(4) + 1);
				viewerList.add(instUnionMember.getInstPlayerId());
			}
			againsts[i].addViewer(viewerList);
		}
		LogUtil.error("UnionWar 2 to 1 Open");
	}

	public static void startAgainst1() {
		if (!isThisWeekOpend || unionWarIndex != 3) {
			return;
		}
		countOfThread = 1;
		againsts[6].start();
		LogUtil.error("UnionWar 2 to 1 Start");
	}

	public static void onAgainst1Done() {
		LogUtil.error("UnionWar 2 to 1 Done");
	}

	public static void closeAgainst() {
		unionWarIndex = 0;
		LogUtil.error("UnionWar Close UnionWar");
	}

	public static synchronized void onAgainstFinished(Against against) {
		if (--countOfThread == 0) {
			switch (unionWarIndex) {
				case 1:
					onAgainst4Done();
					break;
				case 2:
					onAgainst2Done();
					break;
				case 3:
					onAgainst1Done();
					break;
			}
		}
	}

	public static void unionContribution(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		List<InstUnionWarContributionRank> instUnionWarList = DALFactory.getInstUnionWarContributionRankDAL().getList(" instUnionId = " + instUnionId + " order by isMvp desc,contribution desc", 0);
		if (instUnionWarList.size() == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "上次盟战不存在");
			return;
		}
		StringBuffer sb = new StringBuffer();
		int i = 0;
		for (InstUnionWarContributionRank e : instUnionWarList) {
			sb.append(++i);
			sb.append("|"); // TODO 分隔符，是否和玩家名冲突
			sb.append(e.getInstPlayerId());
			sb.append("|");
			sb.append(e.getInstPlayerName());
			sb.append("|");
			sb.append(e.getInstPlayerHead());
			sb.append("|");
			sb.append(e.getInstPlayerLevel());
			sb.append("|");
			sb.append(e.getIsMvp());
			sb.append("|");
			sb.append(e.getKillCount());
			sb.append("|");
			sb.append(e.getContribution());
			sb.append("/");
		}
		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		MessageData md = new MessageData();
		md.putStringItem("ranklist", sb.toString());
		MessageUtil.sendSuccMsg(channelId, msgMap, md);
	}

	public static void unionSchedule(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		if (isThisWeekOpend && unionWarIndex != 0) { // 盟战开启时间内发送及时信息
			MessageData md = new MessageData();
			for (int i = 0; i < AGAINST_SIZE; ++i) {
				Against against = againsts[i];
				against.lock();
				MessageData againstMD = new MessageData();
				againstMD.putIntItem("state", against.state.ordinal());
				againstMD.putIntItem("isAWin", against.winer == against.teamA ? 1 : 0);
				if (against.teamA != null) {
					againstMD.putIntItem("aId", against.teamA.getUnionId());
					againstMD.putStringItem("aName", against.teamA.getUnionName());
					if (against.teamA.mvp != null) {
						againstMD.putStringItem("aMvpName", against.teamA.mvp.getInstPlayerName());
					}
					againstMD.putIntItem("aScore1", against.teamA.getBattlefieldScore(0));
					againstMD.putIntItem("aScore2", against.teamA.getBattlefieldScore(1));
					againstMD.putIntItem("aScore3", against.teamA.getBattlefieldScore(2));
					againstMD.putIntItem("aScore4", against.teamA.getBattlefieldScore(3));
				}
				if (against.teamB != null) {
					againstMD.putIntItem("bId", against.teamB.getUnionId());
					againstMD.putStringItem("bName", against.teamB.getUnionName());
					if (against.teamB.mvp != null) {
						againstMD.putStringItem("bMvpName", against.teamB.mvp.getInstPlayerName());
					}
					againstMD.putIntItem("bScore1", against.teamB.getBattlefieldScore(0));
					againstMD.putIntItem("bScore2", against.teamB.getBattlefieldScore(1));
					againstMD.putIntItem("bScore3", against.teamB.getBattlefieldScore(2));
					againstMD.putIntItem("bScore4", against.teamB.getBattlefieldScore(3));
				}
				against.unlock();
				md.putMessageItem("" + i, againstMD.getMsgData());
			}
			md.putStringItem("reward8", getAgainstReward(1));
			md.putStringItem("reward4", getAgainstReward(2));
			md.putStringItem("reward2", getAgainstReward(3));
			MessageUtil.sendSuccMsg(channelId, msgMap, md);
			return;
		}
		// 上次赛程
		List<InstUnionWarAgainst> instUnionWarList = DALFactory.getInstUnionWarAgainstDAL().getList("", 0);
		if (instUnionWarList.size() != AGAINST_SIZE) {
			MessageUtil.sendFailMsg(channelId, msgMap, "上次盟战赛程不存在");
			return;
		}
		MessageData md = new MessageData();
		for (int i = 0; i < AGAINST_SIZE; ++i) {
			InstUnionWarAgainst against = instUnionWarList.get(i);
			MessageData againstMD = new MessageData();
			againstMD.putIntItem("isAWin", against.getIsAWin());
			if (against.getTeamA() != 0) {
				againstMD.putIntItem("aId", against.getTeamA());
				againstMD.putStringItem("aName", against.getTeamNameA());
				if (against.getMvpNameA() != null) {
					againstMD.putStringItem("aMvpName", against.getMvpNameA());
				}
				againstMD.putIntItem("aScore1", against.getABattlefieldScore1());
				againstMD.putIntItem("aScore2", against.getABattlefieldScore2());
				againstMD.putIntItem("aScore3", against.getABattlefieldScore3());
				againstMD.putIntItem("aScore4", against.getABattlefieldScore4());
			}
			if (against.getTeamB() != 0) {
				againstMD.putIntItem("bId", against.getTeamB());
				againstMD.putStringItem("bName", against.getTeamNameB());
				if (against.getMvpNameB() != null) {
					againstMD.putStringItem("bMvpName", against.getMvpNameB());
				}
				againstMD.putIntItem("bScore1", against.getBBattlefieldScore1());
				againstMD.putIntItem("bScore2", against.getBBattlefieldScore2());
				againstMD.putIntItem("bScore3", against.getBBattlefieldScore3());
				againstMD.putIntItem("bScore4", against.getBBattlefieldScore4());
			}
			md.putMessageItem("" + i, againstMD.getMsgData());
		}
		md.putStringItem("reward8", getAgainstReward(1));
		md.putStringItem("reward4", getAgainstReward(2));
		md.putStringItem("reward2", getAgainstReward(3));
		MessageUtil.sendSuccMsg(channelId, msgMap, md);
	}

	public static void showUnionWar(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		if (!isThisWeekOpend || unionWarIndex == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "盟战未开启");
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "联盟没有资格");
			return;
		}
		against.showUnionWar(msgMap, channelId, instPlayerId, instUnionId);
	}

	public static void join(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldId) {
		if (!isThisWeekOpend || unionWarIndex == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "盟战未开启");
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "联盟没有资格");
			return;
		}
		if (useTimeLimit && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= HOUR_OF_JOIN) {
			MessageUtil.sendFailMsg(channelId, msgMap, "超过报名时间");
			return;
		}
		against.join(msgMap, channelId, instPlayerId, instUnionId, id2idx(battlefieldId));
	}

	public static void disjoin(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldId) {
		if (!isThisWeekOpend || unionWarIndex == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "盟战未开启");
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "联盟没有资格");
			return;
		}
		if (useTimeLimit && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= HOUR_OF_JOIN) {
			MessageUtil.sendFailMsg(channelId, msgMap, "超过报名时间");
			return;
		}
		against.disjoin(msgMap, channelId, instPlayerId, instUnionId, id2idx(battlefieldId));
	}

	public static void sort(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldId) {
		if (!isThisWeekOpend || unionWarIndex == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "盟战未开启");
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "联盟没有资格");
			return;
		}
		if (useTimeLimit && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= HOUR_OF_JOIN) {
			MessageUtil.sendFailMsg(channelId, msgMap, "超过报名时间");
			return;
		}
		against.sort(msgMap, channelId, instPlayerId, instUnionId, id2idx(battlefieldId));
	}

	public static void ambush(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		if (!isThisWeekOpend || unionWarIndex == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "盟战未开启");
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "联盟没有资格");
			return;
		}
		if (useTimeLimit && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= HOUR_OF_JOIN) {
			MessageUtil.sendFailMsg(channelId, msgMap, "超过报名时间");
			return;
		}
		against.ambush(msgMap, channelId, instPlayerId, instUnionId);
	}

	public static void unambush(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		if (!isThisWeekOpend || unionWarIndex == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "盟战未开启");
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "联盟没有资格");
			return;
		}
		if (useTimeLimit && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) >= HOUR_OF_JOIN) {
			MessageUtil.sendFailMsg(channelId, msgMap, "超过报名时间");
			return;
		}
		against.unambush(msgMap, channelId, instPlayerId, instUnionId);
	}

	public static void fight(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId, int battlefieldId) {
		if (!isThisWeekOpend || unionWarIndex == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "盟战未开启");
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "联盟没有资格");
			return;
		}
		if (useTimeLimit && Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < HOUR_OF_FIGHT) {
			MessageUtil.sendFailMsg(channelId, msgMap, "还未开战，无法伏击");
			return;
		}
		against.fight(msgMap, channelId, instPlayerId, instUnionId, id2idx(battlefieldId));
	}

	public static void unionReplay(HashMap<String, Object> msgMap, String channelId, int instPlayerId, int instUnionId) {
		if (!isThisWeekOpend || unionWarIndex == 0) {
			MessageUtil.sendFailMsg(channelId, msgMap, "盟战未开启");
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			MessageUtil.sendFailMsg(channelId, msgMap, "联盟没有资格");
			return;
		}
		against.unionReplay(msgMap, channelId, instPlayerId, instUnionId);
	}

	public static void addViewer(int instPlayerId) {
		int instUnionId;
		List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			return;
		}
		against.addViewer(instPlayerId);
	}

	public static void delViewer(int instPlayerId) {
		int instUnionId;
		List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
		if (instUnionMemberList.size() == 0 || (instUnionId = instUnionMemberList.get(0).getInstUnionId()) == 0) {
			return;
		}
		Against against = unionIdToAgainst.get(instUnionId);
		if (against == null) {
			return;
		}
		against.delViewer(instPlayerId);
	}

	// 盟战信息
	public static int getAmbushLimit(int level) {
		for (DictUnionWarAmbush dictUnionWarAmbush : DictList.dictUnionWarAmbushList) {
			if (dictUnionWarAmbush.getLevelRangeStart() <= level && dictUnionWarAmbush.getLevelRangeEnd() != 0 && level <= dictUnionWarAmbush.getLevelRangeEnd()) {
				return dictUnionWarAmbush.getCount();
			}
		}
		return 0;
	}

	public static int getAliveContribution() {
		return 1; // 固定值
	}

	public static int getKillContribution(int level, float reduction) {
		return (int) (level * (1 - reduction) * 10); // 公式
	}

	public static float getReduction(int fightCount) {
		DictUnionWarInfo dictUnionWarInfo = DictMap.dictUnionWarInfoMap.get("" + unionWarIndex);
		float reducPer = dictUnionWarInfo.getReductionPer();
		float reducMax = dictUnionWarInfo.getReductionMax();
		float reducRet = reducPer * fightCount;
		return Math.min(reducRet, reducMax);
	}

	// 战场信息
	public static int idx2id(int idx) {
		return idx + 1;
	}

	public static int id2idx(int id) {
		return id - 1;
	}

	public static int getBattlefieldScore(int idx) {
		int id = idx2id(idx);
		return DictMap.dictUnionWarBattlefieldMap.get("" + id).getScore();
	}

	public static int getBattlefieldAliveScore(int idx) {
		int id = idx2id(idx);
		return DictMap.dictUnionWarBattlefieldMap.get("" + id).getAliveScore();
	}

	public static int getBattlefieldKillScore(int idx) {
		int id = idx2id(idx);
		return DictMap.dictUnionWarBattlefieldMap.get("" + id).getKillScore();
	}

	public static String getBattlefieldReward(int idx) {
		int id = idx2id(idx);
		return DictMap.dictUnionWarBattlefieldMap.get("" + id).getReward();
	}

	public static String getAgainstReward(int id) {
		DictUnionWarInfo d = DictMap.dictUnionWarInfoMap.get("" + id);
		return d.getRewardLose() + "/" + d.getRewardWin() + "/" + d.getRewardLeader() + "/" + d.getRewardMvp();
	}

}
