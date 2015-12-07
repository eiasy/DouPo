package mmo.module.rank.runnable;

import mmo.module.rank.RankList;
import mmo.module.rank.RankManager;
import mmo.tools.log.LoggerError;
import mmo.tools.thread.runnable.IDatabaseRunnable;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class SaveRankData implements IDatabaseRunnable {
	private RankList rankList;
	private IoBuffer data;

	public SaveRankData(RankList rankList, IoBuffer data) {
		this.rankList = rankList;
		this.data = data;
	}

	public void run() {
		try {
			if (rankList == null) {
				return;
			}
			RankManager.getInstance().getRankService().saveRankData(rankList, data);
		} catch (Exception e) {
			LoggerError.error("SaveRankData", e);
		} finally {
			rankList = null;
			PacketBufferPool.freePacketBuffer(data);
			data = null;
		}
	}
}