package com.hygame.dpcq.action;

import javax.servlet.http.HttpServletRequest;
import io.netty.channel.Channel;
import model.proto.Message;
import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.model.proto.util.MessageData;
import com.hygame.dpcq.tools.Lock;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 玩家操作相关Action
 * @author mp
 * @date 2015-4-10 下午1:54:44
 */
public class PlayerAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	private Integer serverid;
	private HttpServletRequest request;
	private String result;
	private String name;
	private int id;

	/**
	 * 冻结账号
	 * @author mp
	 * @date 2015-4-10 上午11:15:26
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String frozen () throws Exception{
		
		Channel channel = GameCoon.getChannel(serverid);
		if(channel == null){
			result = null;
		}else{
			MessageData md = new MessageData();
			md.putStringItem("playerNameList", new String(name.getBytes("iso-8859-1"), "utf-8"));
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20014);
			msg.setVersion(0);
			msg.setMsgdata(md.getMsgData());
			// 发送消息
			channel.writeAndFlush(msg.build());
			
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
			result = "succ";
		}
		return SUCCESS;
	}
	
	/**
	 * 禁言
	 * @author mp
	 * @date 2015-8-6 下午8:18:32
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String shutup () throws Exception{
		
		Channel channel = GameCoon.getChannel(serverid);
		if(channel == null){
			result = null;
		}else{
			MessageData md = new MessageData();
			md.putStringItem("playerNameList", new String(name.getBytes("iso-8859-1"), "utf-8"));
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20022);
			msg.setVersion(0);
			msg.setMsgdata(md.getMsgData());
			// 发送消息
			channel.writeAndFlush(msg.build());
			
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
			result = "succ";
		}
		return SUCCESS;
	}
	
	// 获取冻结账号列表
	public String showFrozen() throws Exception {

		Channel channel = GameCoon.getChannel(serverid);
		if(channel == null){
			result = null;
		}else{
			MessageData md = new MessageData();
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20015);
			msg.setVersion(0);
			msg.setMsgdata(md.getMsgData());
			channel.writeAndFlush(msg.build());
			
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
			
			// 获取冻结账号列表
			result = Lock.threadMapReturnString.get("frozenList");
		}
		return SUCCESS;
	}
	
	/**
	 * 获取禁言列表
	 * @author mp
	 * @date 2015-8-6 下午8:19:53
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String showShutup() throws Exception {

		Channel channel = GameCoon.getChannel(serverid);
		if(channel == null){
			result = null;
		}else{
			MessageData md = new MessageData();
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20023);
			msg.setVersion(0);
			msg.setMsgdata(md.getMsgData());
			channel.writeAndFlush(msg.build());
			
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
			
			// 获取禁言列表
			result = Lock.threadMapReturnString.get("shutupList");
		}
		return SUCCESS;
	}
	
	/**
	 * 解除冻结
	 * @author mp
	 * @date 2015-4-10 下午1:55:28
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String frozenFree() throws Exception {
		
		Channel channel = GameCoon.getChannel(serverid);
		if(channel == null){
			result = null;
		}else{
			MessageData md = new MessageData();
			md.putIntItem("id", id);
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20016);
			msg.setVersion(0);
			msg.setMsgdata(md.getMsgData());
			channel.writeAndFlush(msg.build());
			
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
		}
		return "djzh";
	}
	
	/**
	 * 解除禁言
	 * @author mp
	 * @date 2015-8-6 下午8:21:20
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String shutupFree() throws Exception {
		
		Channel channel = GameCoon.getChannel(serverid);
		if(channel == null){
			result = null;
		}else{
			MessageData md = new MessageData();
			md.putIntItem("id", id);
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20024);
			msg.setVersion(0);
			msg.setMsgdata(md.getMsgData());
			channel.writeAndFlush(msg.build());
			
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
		}
		return "jjzh";
	}
	
	public Integer getServerid() {
		return serverid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setServerid(Integer serverid) {
		this.serverid = serverid;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
