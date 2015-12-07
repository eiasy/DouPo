package com.hygame.dpcq.action;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.proto.Message;
import net.sf.json.JSONObject;

import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.db.dao.model.User;
import com.hygame.dpcq.model.proto.util.MessageData;
import com.hygame.dpcq.tools.JsonUtil;
import com.hygame.dpcq.tools.Lock;
import com.hygame.dpcq.tools.LogUtil;
import com.hygame.dpcq.tools.PropertyUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class OperationAction  extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private String type ;
	private String dbname;
	private Integer serverid;
	private String bossValue;
	private String chatCd;
	private String maxOnlineNum;
	private String serverListType;
	private String result;
	private String serverList;
	private Integer rmb;
	private String playerName;
	private String loginEnvState;
	private String openServerFund;
	private String pushData;
	private String plName;
	private String clearNHour;
	private String maxRegedit;
	private String monthCardPname;
	private String monthCardStartTime;
	private String goldMonthCardPname;
	private String goldMonthCardStartTime;
	
	
	
	public String getMonthCardPname() {
		return monthCardPname;
	}
	public void setMonthCardPname(String monthCardPname) {
		this.monthCardPname = monthCardPname;
	}
	public String getMonthCardStartTime() {
		return monthCardStartTime;
	}
	public void setMonthCardStartTime(String monthCardStartTime) {
		this.monthCardStartTime = monthCardStartTime;
	}
	public String getMaxRegedit() {
		return maxRegedit;
	}
	public void setMaxRegedit(String maxRegedit) {
		this.maxRegedit = maxRegedit;
	}
	public String getClearNHour() {
		return clearNHour;
	}
	public void setClearNHour(String clearNHour) {
		this.clearNHour = clearNHour;
	}
	public String getPlName() {
		return plName;
	}
	public void setPlName(String plName) {
		this.plName = plName;
	}
	public String getPushData() {
		return pushData;
	}
	public void setPushData(String pushData) {
		this.pushData = pushData;
	}
	public String getOpenServerFund() {
		return openServerFund;
	}
	public void setOpenServerFund(String openServerFund) {
		this.openServerFund = openServerFund;
	}
	public String getLoginEnvState() {
		return loginEnvState;
	}
	public void setLoginEnvState(String loginEnvState) {
		this.loginEnvState = loginEnvState;
	}
	public String getChatCd() {
		return chatCd;
	}
	public void setChatCd(String chatCd) {
		this.chatCd = chatCd;
	}
	public Integer getRmb() {
		return rmb;
	}
	public void setRmb(Integer rmb) {
		this.rmb = rmb;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public String getMaxOnlineNum() {
		return maxOnlineNum;
	}
	public void setMaxOnlineNum(String maxOnlineNum) {
		this.maxOnlineNum = maxOnlineNum;
	}
	public void setServerList(String serverList) {
		this.serverList = serverList;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getServerListType() {
		return serverListType;
	}
	public void setServerListType(String serverListType) {
		this.serverListType = serverListType;
	}
	public String getBossValue() {
		return bossValue;
	}
	public void setBossValue(String bossValue) {
		this.bossValue = bossValue;
	}
	public Integer getServerid() {
		return serverid;
	}
	public void setServerid(Integer serverid) {
		this.serverid = serverid;
	}
	public String getDbname() {
		return dbname;
	}
	public void setDbname(String dbname) {
		this.dbname = dbname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * 更新服务器列表
	 * @author mp
	 * @date 2015-2-27 上午9:56:39
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateServerList() throws Exception {
//		serverList = new String (serverList.getBytes("iso-8859-1"), "utf-8");
//		System.out.println(serverList.length() + "  " + serverList);
		
		String ch = "";
		String env = PropertyUtil.getValue("serverList.env");//0-window system  1-linux system
		if (env.equals("0")) {
			ch = "\n";
		} else if (env.equals("1")){
			ch = ",";
			serverList = serverList.replace("\n", "");
		}
		String [] strs = serverList.split(ch);
		StringBuilder sb  = new StringBuilder("");
		for (int i = 0; i < strs.length; i++) {
			if (!strs[i].equals("")) {
				sb.append(strs[i]).append("\n");
			}
		}
		serverList = sb.toString().substring(0, sb.toString().length()-1);
		if (serverListType.equals("1")) {
//			HttpClientUtil.httpPost("http://"+PropertyUtil.getValue("serverList.ip")+":"+PropertyUtil.getValue("serverList.port")+"/DPServerList/serverList/gm/update?type=1&content=" + serverList.replace("\n", "*"), 10 * 60 * 1000, true);
//			HttpClientUtil.postSubmit("http://localhost:8080/DPServerList/serverList/gm/update?type=1&content=" + serverList.replace("\n", "*"));
//			HttpClientUtil.postSubmit("http://localhost:8080/DPServerList/serverList/gm/update?type=1&", serverList.replace("\n", "*"));
		} else if (serverListType.equals("2")) {
//			System.out.println("http://"+PropertyUtil.getValue("serverList.ip")+":"+PropertyUtil.getValue("serverList.port")+"/DPServerList/serverList/gm/update?type=2&content=" + serverList.replace("\n", "*"));
//			HttpClientUtil.httpPost("http://"+PropertyUtil.getValue("serverList.ip")+":"+PropertyUtil.getValue("serverList.port")+"/DPServerList/serverList/gm/update?type=2&content=" + serverList.replace("\n", "*"), 10 * 60 * 1000, true);
		}
		return SUCCESS;
	}
	
	/**
	 * 获取服务器列表
	 * @author mp
	 * @date 2015-8-6 下午7:46:55
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public String getServerList() throws Exception {
		
		String content = "";
		String ch = "";
		String env = PropertyUtil.getValue("serverList.env");//0-window system  1-linux system
		if (env.equals("0")) {
			ch = "\n";
		} else if (env.equals("1")){
			ch = ",<br>";
		}
		
		if (serverListType.equals("1")) {
//			content = HttpClientUtil.httpPost("http://"+PropertyUtil.getValue("serverList.ip")+":"+PropertyUtil.getValue("serverList.port")+"/DPServerList/serverList/client/qq", 10 * 60 * 1000, true);
		} else if (serverListType.equals("2")) {
//			content = HttpClientUtil.httpPost("http://"+PropertyUtil.getValue("serverList.ip")+":"+PropertyUtil.getValue("serverList.port")+"/DPServerList/serverList/client/wx", 10 * 60 * 1000, true);
		}
		
		StringBuilder list = new StringBuilder();
		List<String> serverList = JsonUtil.fromJson(content, ArrayList.class);
		for (String string : serverList) {
			list.append(string).append(ch);
		}
		
		HashMap<String, String> map = new HashMap<String , String>();
		map.put("list", list.toString());
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();
		
		return SUCCESS;
	}
	
	/**
	 * 查看当前设置的最大注册人数
	 * @author mp
	 * @date 2015-9-2 上午11:21:17
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String currMaxRegeditNum() throws Exception {
		Channel channel = GameCoon.getChannel(serverid);
		if (channel == null) {
			result = null;
		} else {
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20036);
			msg.setVersion(0);
			channel.writeAndFlush(msg.build());
			// 发送消息后锁住线程 等游戏服务器发送返回信息

			Thread current = new Thread();
			try {
				Lock.threadMap.put("callback", current);
				synchronized (current) {
					try {
						current.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				e.toString();
			}
			// 获取在线人数
			String onlineNumber = Lock.threadMapReturnString.get("currMaxRegeditNum");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("当前设置", onlineNumber);

			// 组成json数据
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();// 给result赋值，传递给页面
		}
		
		return SUCCESS;
	}
	
	/**
	 * 设置世界Boss血量
	 * @author mp
	 * @date 2015-2-11 上午9:47:09
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String worldBoss() throws Exception {
		
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		//System.out.println("刷新字典表");
		MessageData md = new MessageData();
		md.putIntItem("value", Integer.valueOf(bossValue));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20019);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	/**
	 * 设置聊天CD时间
	 * @author mp
	 * @date 2015-8-6 下午7:47:34
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String loginEnvState() throws Exception {
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		//System.out.println("刷新字典表");
		MessageData md = new MessageData();
		md.putIntItem("value", Integer.valueOf(loginEnvState));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20026);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	/**
	 * 设置聊天CD时间
	 * @author mp
	 * @date 2015-8-6 下午7:47:34
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String openServerFund() throws Exception {
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		//System.out.println("刷新字典表");
		MessageData md = new MessageData();
		md.putIntItem("value", Integer.valueOf(openServerFund));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20027);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	public String chatCd() throws Exception {
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		//System.out.println("刷新字典表");
		MessageData md = new MessageData();
		md.putIntItem("value", Integer.valueOf(chatCd));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20021);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	public String pushData() throws Exception {
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		//System.out.println("刷新字典表");
		MessageData md = new MessageData();
		md.putIntItem("value", Integer.valueOf(pushData));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20028);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	public String clearNHour() throws Exception {
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		MessageData md = new MessageData();
		md.putIntItem("value", Integer.valueOf(clearNHour));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20033);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	public String maxRegedit() throws Exception {
		
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		MessageData md = new MessageData();
		md.putIntItem("value", Integer.valueOf(maxRegedit));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20034);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	public String plName() throws Exception {
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		//System.out.println("刷新字典表");
		MessageData md = new MessageData();
		md.putStringItem("value", new String(plName.getBytes("iso-8859-1"), "utf-8"));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20029);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	/**
	 * 设置最大同时在线人数
	 * @author mp
	 * @date 2015-4-13 下午2:22:39
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String maxOnlineNum() throws Exception {
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		//System.out.println("刷新字典表");
		MessageData md = new MessageData();
		md.putIntItem("maxOnlineNum", Integer.valueOf(maxOnlineNum));

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20017);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}
	
	/**
	 * 模拟充值
	 * @author mp
	 * @date 2015-5-1 下午4:22:24
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String recharge() throws Exception {
		
		ActionContext context=ActionContext.getContext();
		User u = (User)context.getSession().get("user");
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		MessageData md = new MessageData();
		md.putStringItem("playerName", new String(playerName.getBytes("iso-8859-1"), "utf-8"));
		md.putIntItem("rmb", rmb);

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20020);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		LogUtil.info("recharge:user=" + u.getName() + ";rechargeWho=" + new String(playerName.getBytes("iso-8859-1"), "utf-8") + ";rechargeRmb=" + rmb);
		
		return "pzxg";
	}
	
	public String monthCard() throws Exception {
		
		ActionContext context=ActionContext.getContext();
		User u = (User)context.getSession().get("user");
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		MessageData md = new MessageData();
		md.putStringItem("monthCardPname", new String(monthCardPname.getBytes("iso-8859-1"), "utf-8"));
		md.putStringItem("monthCardStartTime", monthCardStartTime);

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20035);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		LogUtil.info("monthCard:user=" + u.getName() + ";monthCardWho=" + new String(monthCardPname.getBytes("iso-8859-1"), "utf-8") + ";monthCardStartTime=" + monthCardStartTime);
		
		return "pzxg";
	}
	
	//刷新字典数据
	public String dictionary() throws Exception {
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		//System.out.println("刷新字典表");
		MessageData md = new MessageData();
		md.putStringItem("tableNameList", dbname);

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20003);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "pzxg";
	}

	//是否使用缓存
	public String isCache()throws Exception {
		//System.out.println("是否用缓存");
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		//组织消息
		MessageData md = new MessageData();

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		
		md.putStringItem("type", type);
		msg.setHeader(20002);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		return "pzxg";
	}
}
