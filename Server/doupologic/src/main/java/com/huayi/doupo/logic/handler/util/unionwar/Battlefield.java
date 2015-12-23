package com.huayi.doupo.logic.handler.util.unionwar;

import com.huayi.doupo.base.model.InstUnionWarContributionRank;
import com.huayi.doupo.logic.util.luafight.FightData;
import com.huayi.doupo.logic.util.luafight.FightOutput;

public class Battlefield {

	enum BattlefieldState {
		AA, BS_WAITING, BS_FIGHTING, BS_FINISHED,
	}

	static final int AWIN = 1;
	static final int BWIN = 0;
	static final int DRAW = -1;

	// NOLOCK
	int battlefieldIdx = 0;
	Against against = null;

	// LOCK !!!由Against.lock进行加锁、解锁。
	BattlefieldState battlefieldState = BattlefieldState.BS_WAITING; // 由Against控制。
	Team teamA = null;
	Team teamB = null;
	int result = DRAW;
	int battlefieldFightCount = 0;

	public Battlefield(int idx, Against a) {
		battlefieldIdx = idx;
		against = a;
	}

	public void lockA() {
		if (teamA != null) {
			teamA.lock();
		}
	}

	public void unlockA() {
		if (teamA != null) {
			teamA.unlock();
		}
	}

	public void lockB() {
		if (teamB != null) {
			teamB.lock();
		}
	}

	public void unlockB() {
		if (teamB != null) {
			teamB.unlock();
		}
	}

	public void reinit(Team a, Team b) {
		battlefieldState = BattlefieldState.BS_WAITING;
		teamA = a;
		teamB = b;
		result = DRAW;
		battlefieldFightCount = 0;
	}

	void updateScore() {
		if (teamA != null) {
			teamA.setBattlefieldAliveScore(battlefieldIdx, teamA.getBattlefieldMemberList(battlefieldIdx).getAliveCount() * UnionWarUtil.getBattlefieldAliveScore(battlefieldIdx));
			if (teamB != null) {
				teamA.setBattlefieldKillScore(battlefieldIdx, teamB.getBattlefieldMemberList(battlefieldIdx).getDeathCount() * UnionWarUtil.getBattlefieldKillScore(battlefieldIdx));
			}
		}
		if (teamB != null) {
			teamB.setBattlefieldAliveScore(battlefieldIdx, teamB.getBattlefieldMemberList(battlefieldIdx).getAliveCount() * UnionWarUtil.getBattlefieldAliveScore(battlefieldIdx));
			if (teamA != null) {
				teamB.setBattlefieldKillScore(battlefieldIdx, teamA.getBattlefieldMemberList(battlefieldIdx).getDeathCount() * UnionWarUtil.getBattlefieldKillScore(battlefieldIdx));
			}
		}
		if (result == DRAW) {
		} else if (result == AWIN) {
			teamA.setBattlefieldWinScore(battlefieldIdx, UnionWarUtil.getBattlefieldScore(battlefieldIdx));
		} else if (result == BWIN) {
			teamB.setBattlefieldWinScore(battlefieldIdx, UnionWarUtil.getBattlefieldScore(battlefieldIdx));
		}
	}

	public void runBattlefield() {
		if (teamA == null || teamB == null) { // 有空队伍则快速计算。
			lockA();
			lockB();
			against.lock();
			against.onBattlefieldStart(this);
			updateScore();
			against.onBattlefieldDone(this, result);
			against.unlock();
			unlockB();
			unlockA();
			return;
		} // if (teamA == null || teamB == null)

		// 两支队伍对阵。
		MemberList mlA = teamA.getBattlefieldMemberList(battlefieldIdx);
		MemberList mlB = teamB.getBattlefieldMemberList(battlefieldIdx);
		lockA();
		lockB();
		against.onBattlefieldStart(this);
		teamA.setAllowAmbush(battlefieldIdx, true); // 允许伏兵进入x战场的战斗队列
		teamB.setAllowAmbush(battlefieldIdx, true); // 允许伏兵进入x战场的战斗队列
		unlockB();
		unlockA();
		boolean isAWin = false;
		while (true) {
			InstUnionWarContributionRank mdA, mdB;
			long msStart = System.currentTimeMillis();
			try {
				lockA();
				lockB();
				mdA = mlA.rotatePeekAlive();
				mdB = mlB.rotatePeekAlive();
				if (mdA == null || mdB == null) {
					teamA.setAllowAmbush(battlefieldIdx, false);
					teamB.setAllowAmbush(battlefieldIdx, false);
					if (mdA != null) {
						mlA.rotateNegative1();
					}
					if (mdB != null) {
						mlB.rotateNegative1();
					}
					break;
				}
			} finally {
				unlockB();
				unlockA();
			}
			// FightOutput fr = new FightOutput();
			// fr.setWin(new Random().nextBoolean());
			// fr.setVideo("video");
			FightData fightDataA = teamA.fightDataList.get(mdA.getInstPlayerId());
			FightData fightDataB = teamB.fightDataList.get(mdB.getInstPlayerId());
			fightDataA.setReduction(UnionWarUtil.getReduction(mdA.getFightCount()));
			fightDataB.setReduction(UnionWarUtil.getReduction(mdB.getFightCount()));
			FightOutput fr = against.luaFightEngine.calculate(fightDataA, fightDataB);
			isAWin = fr.isWin();
			// int videoId = 0;
			// try { // 保存录像，数据比较大，及时入库。
			// InstUnionWarVideo instVideo = new InstUnionWarVideo();
			// instVideo.setAgainstIdx(against.againstIndex);
			// instVideo.setInstPlayerA(mdA.getInstPlayerId());
			// instVideo.setInstPlayerB(mdB.getInstPlayerId());
			// instVideo.setIsAWin(isTeamAWin ? 1 : 0);
			// instVideo.setVideo(fr.getVideo());
			// DALFactory.getInstUnionWarVideoDAL().add(instVideo, 0);
			// videoId = instVideo.getId();
			// } catch (Exception e) {
			// }
			long msEnd = System.currentTimeMillis();
			try {
				Thread.sleep(msStart + UnionWarUtil.MIN_FIGHT_SECONDS * 1000 - msEnd);
			} catch (IllegalArgumentException e) { // 超时无需处理，继续
			} catch (InterruptedException e) { // 异常无法处理，继续
			}
			// TODO 执行时间到onFightDone是否需要统计
			lockA();
			lockB();
			against.lock();
			mlA.rotateNegative1(); // !!! 必须延迟
			mlB.rotateNegative1();
			mdA.setFightCount(mdA.getFightCount() + 1);
			mdB.setFightCount(mdB.getFightCount() + 1);
			if (isAWin) {
				mdB.setIsAlive(0);
				mdA.setKillCount(mdA.getKillCount() + 1);
				mdA.setContribution(mdA.getContribution() + UnionWarUtil.getKillContribution(mdB.getInstPlayerLevel(), fightDataB.getReduction()));
			} else {
				mdA.setIsAlive(0);
				mdB.setKillCount(mdB.getKillCount() + 1);
				mdB.setContribution(mdB.getContribution() + UnionWarUtil.getKillContribution(mdA.getInstPlayerLevel(), fightDataA.getReduction()));
			}
			updateScore();
			against.onFightDone(this, isAWin, mdA, mdB, ++battlefieldFightCount, fr.getVideoLua());
			against.unlock();
			unlockB();
			unlockA();
		}
		lockA();
		lockB();
		against.lock();
		if (mlA.getAliveCount() > 0) {
			result = AWIN;
		} else if (mlB.getAliveCount() > 0) {
			result = BWIN;
		} else {
			result = DRAW;
		}
		// if (mlA.getDeathCount() == 0 && mlB.getDeathCount() == 0) { // 无人死亡即无人参战，即平局。
		// result = DRAW;
		// } else if (isAWin) { // 赢得最后一场战斗的角色一定属于胜利的一队。
		// result = AWIN;
		// } else {
		// result = BWIN;
		// }
		updateScore();
		against.onBattlefieldDone(this, result);
		against.unlock();
		unlockB();
		unlockA();
	}
}
