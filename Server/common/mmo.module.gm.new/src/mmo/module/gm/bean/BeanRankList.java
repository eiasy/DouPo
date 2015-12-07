package mmo.module.gm.bean;

import java.util.ArrayList;
import java.util.List;

public class BeanRankList {
	private int                gameId;
	private String             gameName;
	private int                serverId;
	private String             serverName;
	private byte               rankType;
	private List<BeanRankData> rankList = new ArrayList<BeanRankData>();

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

	public byte getRankType() {
		return rankType;
	}

	public void setRankType(byte rankType) {
		this.rankType = rankType;
	}

	public List<BeanRankData> getRankList() {
		return rankList;
	}

	public void setRankList(List<BeanRankData> rankList) {
		this.rankList = rankList;
	}
}
