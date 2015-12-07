package mmo.module.rank;


import org.apache.mina.core.buffer.IoBuffer;


public interface IRankService {

	void loadRankTopData(RankList rankList);

	public void saveRankData(RankList rankList, IoBuffer data);
}
