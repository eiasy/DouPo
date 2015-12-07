package mmo.common.bean.email;

import java.sql.Timestamp;

import mmo.common.bean.goods.AGoods;

public interface IEmail {

	public abstract long getId();

	public abstract String getFromName();

	public abstract void setFromName(String fromName);

	public abstract String getTargetName();

	public abstract void setTargetName(String targetName);

	public abstract String getTitle();

	public abstract String getContent();

	public abstract void setId(long id);

	public abstract void setTitle(String title);

	public abstract void setContent(String content);

	public abstract byte getMoneyState();

	public abstract void setMoneyState(byte moneyState);

	public abstract int getFromid();

	public abstract void setFromid(int fromid);

	public abstract int getTargetid();

	public abstract void setTargetid(int targetid);

	public abstract byte getEmailState();

	public abstract void setEmailState(byte emailState);

	public abstract byte getPickup();

	public abstract void setPickup(byte pickup);

	public abstract byte getEtype();

	public abstract void setEtype(byte etype);

	public abstract Timestamp getCtime();

	public abstract void setCtime(Timestamp ctime);

	public abstract Timestamp getPtime();

	public abstract void setPtime(Timestamp ptime);

	public abstract boolean isTrade();

	public abstract int getMoneyId();

	public abstract void setMoneyId(int moneyId);

	public abstract int getMoneyCount();

	public abstract void setMoneyCount(int moneyCount);

	public void addGoods(AGoods goods);
}