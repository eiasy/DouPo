package com.hygame.dpcq.action;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import model.proto.Message;

import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.db.dao.model.User;
import com.hygame.dpcq.model.proto.util.MessageData;
import com.hygame.dpcq.tools.HttpClientUtil;
import com.hygame.dpcq.tools.Lock;
import com.hygame.dpcq.tools.LogUtil;
import com.opensymphony.xwork2.ActionContext;
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
	private int start;
	private int end;

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
		System.out.println("添加");

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
	
	/**
	 * 多服添加跑马灯
	 * @author mp
	 * @date 2015-10-29 下午4:58:04
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String addMarqueeMulty() throws Exception {
		
		String failList = "";
		int failId = 0;
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("command", "addMarqueeMulty");
		paramMap.put("startTime", startTime);
		paramMap.put("endTime", endTime);
		paramMap.put("inteval", inteval + "");
		paramMap.put("content", new String(content.getBytes("iso-8859-1"), "utf-8"));
		String params = HttpClientUtil.httpBuildQuery(paramMap);
		
		ActionContext context=ActionContext.getContext();
		User u = (User)context.getSession().get("user");
		String userName = "";
		if (u != null) {
			userName = u.getName();
		}
		
		LogUtil.info("多服发跑马灯：userName=" + userName + " 开始结束服务器ID=" + start + " - " + end + " 开始时间=" + startTime + " 结束时间=" + endTime + " 间隔=" + inteval + " 内容=" + new String(content.getBytes("iso-8859-1"), "utf-8"));
		
		for (int i = start; i <= end; i++) {
			failId = i;
			try {
				String retMsg = HttpClientUtil.postMapSubmit(ServerAttributeAction.getGmHttpUri(i), params);
				if (!retMsg.equals("操作成功")) {
					failList += i + ";";
				}
			} catch (Exception e) {
				failList += failId + ";";
				e.printStackTrace();
			}
		}
		if (failList.equals("")) {
			failList = "全部添加成功";
		} else {
			failList = "失败列表为：" + failList;
		}
		result = failList;
		return SUCCESS;
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	
}
