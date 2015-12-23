package com.huayi.doupo.logic.handler.util.unionwar;

import java.util.Collections;
import java.util.LinkedList;

import com.huayi.doupo.base.model.InstUnionWarContributionRank;

@SuppressWarnings("serial")
public class MemberList extends LinkedList<Integer> {

	Team team = null;

	public MemberList(Team t) {
		team = t;
	}

	// 获取下一个可战斗的角色
	public InstUnionWarContributionRank rotatePeekAlive() {
		int size = size();
		while (0 < size--) { // 循环有限次数
			InstUnionWarContributionRank instUnionWarContributionRank = team.memberDataList.get(peek());
			if (instUnionWarContributionRank.getIsAlive() == 1) {
				return instUnionWarContributionRank;
			}
			Collections.rotate(this, -1);
		}
		return null;
	}

	public void rotateNegative1() {
		Collections.rotate(this, -1);
	}

	public int getAliveCount() {
		int aliveCount = 0;
		for (int i : this) {
			InstUnionWarContributionRank instUnionWarContributionRank = team.memberDataList.get(i);
			if (instUnionWarContributionRank.getIsAlive() == 1) {
				++aliveCount;
			}
		}
		return aliveCount;
	}

	public int getDeathCount() {
		int deathCount = 0;
		for (int i : this) {
			if (team.memberDataList.get(i).getIsAlive() != 1) {
				++deathCount;
			}
		}
		return deathCount;
	}

}
