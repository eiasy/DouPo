package com.hygame.dpcq.action;

import javax.servlet.http.HttpServletRequest;


import model.proto.Message;
import io.netty.channel.Channel;

import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.model.proto.util.MessageData;
import com.opensymphony.xwork2.ActionSupport;

public class SendMessageAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;
	private String result;
	private HttpServletRequest request;
	private Integer serverid ;
	private String closeServerMsg;
	
	
	public String getCloseServerMsg() {
		return closeServerMsg;
	}

	public void setCloseServerMsg(String closeServerMsg) {
		this.closeServerMsg = closeServerMsg;
	}

	public Integer getServerid() {
		return serverid;
	}

	public void setServerid(Integer serverid) {
		this.serverid = serverid;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setServletRequest(HttpServletRequest arg0)
	{  
		this.request = arg0;    
	}  
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	
	/*//查询在线人数
	public String onlineNumber() throws Exception {
		
		Map<String, String> map = new TreeMap<String, String>();
		map.put("在线人数", "10000");
		Channel channel = GameCoon.serverMap.get(id).getChannel();
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20000);
		msg.setVersion(0);
		channel.writeAndFlush(msg.build());
		JSONObject json = JSONObject.fromObject(map);
		result = json.toString();//给result赋值，传递给页面 
		System.out.println("result = "+ result);
		return SUCCESS;
		
	}*/
	
	//关闭游戏服务器
	public String closeServer()throws Exception {
		
		//组织服务器列表
		try {
			GameCoon.orgServerList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Channel channel = GameCoon.getChannel(serverid);
		
		MessageData md = new MessageData();
		
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		md.putStringItem("closeServerMsg", new String(closeServerMsg.getBytes("iso-8859-1"), "utf-8"));
		
		msg.setHeader(20001);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		channel.writeAndFlush(msg.build());
		
		GameCoon.serverMap.get(serverid).setChannel(null);
		
		return SUCCESS;	
	}
	//服务器短线重连
//	public String reconnection()throws Exception {
//		GameCoon gc = new GameCoon();
//		gc.getGameCoon();
//		return SUCCESS;	
//	}
	
}
