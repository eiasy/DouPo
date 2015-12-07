package mmo.module.admin;

import java.sql.Timestamp;

public class GameAdmin {
	public final static String ID       = "id";
	public final static String USERID   = "userid";
	public final static String PASSWORD = "password";
	public final static String PURVIEW  = "purview";
	public final static String ATIME    = "atime";
	public final static String ACTIVE   = "active";
	public final static String CATE     = "cate";

	private int                id;
	private String             userid;
	private String             password;
	private byte               cate;
	private int                purview;
	private Timestamp          atime;
	private byte               active;

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final String getUserid() {
		return userid;
	}

	public final void setUserid(String userid) {
		this.userid = userid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public final byte getCate() {
		return cate;
	}

	public final void setCate(byte cate) {
		this.cate = cate;
	}

	public final int getPurview() {
		return purview;
	}

	public final void setPurview(int purview) {
		this.purview = purview;
	}

	public final Timestamp getAtime() {
		return atime;
	}

	public final void setAtime(Timestamp atime) {
		this.atime = atime;
	}

	public final byte getActive() {
		return active;
	}

	public final void setActive(byte active) {
		this.active = active;
	}

	public String toString() {
		return "[ID:" + userid + "]";
	}
}
