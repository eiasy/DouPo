package mmo.common.module.sdk.token;

public class TokenData {
	private String  token;
	private String  userid;
	private String  username;
	private String  channelSub;
	private long    overtime;
	private boolean handled;

	protected TokenData(String token, String userid, String username, String channelSub, long overtime) {
		super();
		this.token = token;
		this.userid = userid;
		this.username = username;
		this.channelSub = channelSub;
		this.overtime = overtime;
	}

	public String getToken() {
		return token;
	}

	public String getUserid() {
		return userid;
	}

	public String getUsername() {
		return username;
	}

	public String getChannelSub() {
		return channelSub;
	}

	public long getOvertime() {
		return overtime;
	}

	public boolean isHandled() {
		return handled;
	}

	public void setHandled(boolean handled) {
		this.handled = handled;
	}
	
	public void resetTime(long overtime){
		this.overtime=overtime;
	}

	@Override
	public String toString() {
		return "TokenData [token=" + token + ", userid=" + userid + ", username=" + username + ", channelSub=" + channelSub + ", overtime="
		        + overtime + ", handled=" + handled + "]";
	}
}
