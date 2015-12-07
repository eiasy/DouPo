package mmo.module.gm.bean;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import mmo.tools.util.DateUtil;
import mmo.tools.util.StringUtil;

public class BeanGift extends UIData {
	private int                   gameId;
	private String                gameName;

	private String                icon;
	private short                 level;
	private byte                  quality;
	private String                startTime;
	private String                stopTime;
	private long                  overdate;
	private String                goodses;
	private String                note;
	private Map<Integer, Integer> goodsMap = new TreeMap<Integer, Integer>();

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public short getLevel() {
		return level;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public byte getQuality() {
		return quality;
	}

	public void setQuality(byte quality) {
		this.quality = quality;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return stopTime;
	}

	// public void setStopTime(String stopTime) {
	// this.stopTime = stopTime;
	// }

	public String getGoodses() {
		return goodses;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setGoodses(String goodses) {
		this.goodses = goodses;
		if (goodses != null) {
			String[] goodsArray = StringUtil.splitString(goodses, ';');
			for (String gs : goodsArray) {
				int[] gc = StringUtil.string2IntArray(gs, ',');
				if (gc != null && gc.length > 1) {
					goodsMap.put(gc[0], gc[1]);
				}
			}
		}
	}

	public Map<Integer, Integer> getGoodsMap() {
		return goodsMap;
	}

	public long getOverdate() {
		return overdate;
	}

	public void setOverdate(long overdate) {
		this.stopTime = DateUtil.formatDate(new Date(overdate));
		this.overdate = overdate;
	}

}
