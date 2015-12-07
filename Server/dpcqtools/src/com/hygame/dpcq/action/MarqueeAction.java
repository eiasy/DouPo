package com.hygame.dpcq.action;

import javax.servlet.http.HttpServletRequest;

import io.netty.channel.Channel;
import model.proto.Message;

import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.model.proto.util.MessageData;
import com.hygame.dpcq.tools.Lock;
import com.opensymphony.xwork2.ActionSupport;

public class MarqueeAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private String marquee;
	private Integer serverid;
	private HttpServletRequest request;
	private String result;
	private int id;
	private String startTime;
	private String endTime;
	private int inteval;
	private String content;

	// 获取跑马灯
	public String showMarquee() throws Exception {

//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		if(channel == null){
			result = null;
		}else{
			MessageData md = new MessageData();
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			// 跑马灯
			msg.setHeader(20006);
			msg.setVersion(0);
			msg.setMsgdata(md.getMsgData());
			// 发送消息
			channel.writeAndFlush(msg.build());
			// System.out.println("1111111111111");
			// 锁线程
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
			String showMarquee = Lock.threadMapReturnString.get("pmdlist");
			// 组成json数据
			// JSONObject json = JSONObject.fromObject(new HashMap<String ,
			// String>().put("在线人数", onlineNumber));
			// 传过来的就是json 字符串
			result = showMarquee;// 给result赋值，传递给页面
	
			// System.out.println(result);
		}

		return SUCCESS;
	}

	public String delMarquee() throws Exception {
		// System.out.println("删除");
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		MessageData md = new MessageData();
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		// 跑马灯
		msg.setHeader(20009);
		msg.setVersion(0);
		md.putIntItem("id", id);
		msg.setMsgdata(md.getMsgData());
		// 发送消息
		channel.writeAndFlush(msg.build());
		return "pmdgl";

	}

	public String addMarquee() throws Exception {
		// System.out.println("添加");

//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		MessageData md = new MessageData();
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		// 跑马灯
		msg.setHeader(20007);
		msg.setVersion(0);
		md.putStringItem("startTime", startTime);
		md.putStringItem("endTime", endTime);
		md.putIntItem("inteval", inteval);
		content = new String(content.getBytes("iso-8859-1"), "utf-8");
		md.putStringItem("content", content);
		msg.setMsgdata(md.getMsgData());
		// 发送消息
		channel.writeAndFlush(msg.build());
		return "pmdgl";

	}

	public String updateMarquee() throws Exception {
		// System.out.println("修改");
//		Channel channel = GameCoon.serverMap.get(serverid).getChannel();
		Channel channel = GameCoon.getChannel(serverid);
		MessageData md = new MessageData();
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		// 跑马灯
		msg.setHeader(20008);
		msg.setVersion(0);
		md.putIntItem("id", id);
		md.putStringItem("startTime", startTime);
		md.putStringItem("endTime", endTime);
		md.putIntItem("inteval", inteval);
		content = new String(content.getBytes("iso-8859-1"), "utf-8");
		md.putStringItem("content", content);

		msg.setMsgdata(md.getMsgData());
		// 发送消息
		channel.writeAndFlush(msg.build());
		return "pmdgl";
	}
	
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getInteval() {
		return inteval;
	}

	public void setInteval(int inteval) {
		this.inteval = inteval;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getServerid() {
		return serverid;
	}

	public void setServerid(Integer serverid) {
		this.serverid = serverid;
	}

	public String getMarquee() {
		return marquee;
	}

	public void setMarquee(String marquee) {
		this.marquee = marquee;
	}
	
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
