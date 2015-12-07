package com.hygame.dpcq.action;

import io.netty.channel.Channel;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import model.proto.Message;
import net.sf.json.JSONObject;

import com.hygame.dpcq.config.Goods;
import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.db.conn.DBConn;
import com.hygame.dpcq.db.dao.model.TableType;
import com.hygame.dpcq.db.dao.model.User;
import com.hygame.dpcq.model.proto.GoodsResult;
import com.hygame.dpcq.model.proto.util.MessageData;
import com.hygame.dpcq.servlet.Boot;
import com.hygame.dpcq.tools.JsonUtil;
import com.hygame.dpcq.tools.Lock;
import com.hygame.dpcq.tools.LogUtil;
import com.hygame.dpcq.tools.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class GoodsAction extends ActionSupport {

	private String id;
	private String result;
	private String name;
	private String goods;
	private Integer serverid;
	private String content;
	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;

	/**
	 * 获取发放类型
	 * @author mp
	 * @date 2015-4-2 下午3:23:04
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String selestGoods() {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			
			ActionContext context = ActionContext.getContext();
			Map<Integer, String> map = new TreeMap<Integer, String>();
			rs = stat.executeQuery("select * from `TableType`");
			while (rs.next()) {
				TableType tt = new TableType();
				tt.setId(rs.getInt("id"));
				tt.setName(rs.getString("name"));
				map.put(tt.getId(), tt.getName());
			}
			context.getSession().put("tabletype", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, stat, rs);
		}
		return "wpff";
	}
	
	public String selestGoodsAll() throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			ActionContext context = ActionContext.getContext();
			Map<Integer, String> map = new TreeMap<Integer, String>();
			rs = stat.executeQuery("select * from `TableType`");
			while (rs.next()) {
				TableType tt = new TableType();
				tt.setId(rs.getInt("id"));
				tt.setName(rs.getString("name"));
				map.put(tt.getId(), tt.getName());
			}
			context.getSession().put("tabletype", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, stat, rs);
		}
		
		return "wpffall";
	}
	
	/**
	 * 选择物品界面
	 * @author mp
	 * @date 2015-9-19 下午1:48:33
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String selectThing() throws Exception {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			ActionContext context = ActionContext.getContext();
			Map<Integer, String> map = new TreeMap<Integer, String>();
			rs = stat.executeQuery("select * from `TableType`");
			while (rs.next()) {
				TableType tt = new TableType();
				tt.setId(rs.getInt("id"));
				tt.setName(rs.getString("name"));
				map.put(tt.getId(), tt.getName());
			}
			context.getSession().put("tabletype", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, stat, rs);
		}
		return "selectThing";
	}

	/**
	 * 获取级联查询中的子类结果
	 * @author mp
	 * @date 2015-4-2 下午3:21:14
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String sel() throws Exception {
		List<GoodsResult> goodsList = selectGoodsByType(id);
		result = JsonUtil.toJson(goodsList);// 给result赋值，传递给页面
		return SUCCESS;
	}

	/**
	 * 根据所选发放类型,获取旗下的子类
	 * @author mp
	 * @date 2015-4-2 下午3:22:13
	 * @param typeid
	 * @return
	 * @Description
	 */
	public List<GoodsResult> selectGoodsByType(String typeid) {

		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		List<GoodsResult> goodsList = new ArrayList<GoodsResult>();
		try {
			conn = DBConn.getConn();
			stat = conn.createStatement();
			// 获取数据
			rs = stat.executeQuery("select * from `goods` where typeid = " + typeid + " order by convert(name using gbk) asc");
			while (rs.next()) {
				GoodsResult goodsResult = new GoodsResult();
				goodsResult.setId(rs.getInt("id"));
				String name = rs.getString("name");
				name = "["+ StringUtil.getFirstAlpha(StringUtil.getFirstString(name)) + "] " + name;
				goodsResult.setName(name);
				goodsList.add(goodsResult);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConn.close(conn, stat, rs);
		}
		return goodsList;
	}

	/**
	 * 派发奖励
	 * @author mp
	 * @date 2015-4-2 下午3:20:08
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String provide() throws Exception {

		content = new String(content.getBytes("iso-8859-1"), "utf-8");
		name = new String(name.getBytes("iso-8859-1"), "utf-8").trim();

		char lastChar = goods.charAt(goods.length() - 1);
		if (lastChar == ';') {
			goods = goods.substring(0, goods.length() - 1);
		}
		
		try {
			String names = "";
			ActionContext context=ActionContext.getContext();
			User u = (User)context.getSession().get("user");
			for (String thing : goods.split(";")) {
				int typeId = Integer.valueOf(thing.split("_")[0]);
				int filedId = Integer.valueOf(thing.split("_")[1]);
				int value = Integer.valueOf(thing.split("_")[2]);
				String name = "";
				for (Goods goods : Boot.goodList) {
					if (goods.getTypeid() == typeId && goods.getId() == filedId) {
						name = goods.getName();
						break;
					}
				}
				names += name + "_" + value + ";";
			}
			LogUtil.info("sendAward:user=" + u.getName() + ";sendWho=" + name + ";sendContent=" + content + ";sendThings=" + names);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Channel channel = GameCoon.getChannel(serverid);
		// 组织消息
		MessageData md = new MessageData();
		md.putStringItem("playerName", name);
		md.putStringItem("thingList", goods);
		md.putStringItem("content", content);
		Message.Msg.Builder msg = Message.Msg.newBuilder();
		msg.setHeader(20004);
		msg.setVersion(0);
		msg.setMsgdata(md.getMsgData());
		// 发送消息
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
		
		String sendThingRes = Lock.threadMapReturnString.get("sendThingRes");
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("结果", onlineNumber);

		// 组成json数据
//		JSONObject json = JSONObject.fromObject(map);
//		result = json.toString();// 给result赋值，传递给页面
		result = sendThingRes;// 给result赋值，传递给页面
		
		return SUCCESS;
	}

	/**
	 * 查看在线人数
	 * @author mp
	 * @date 2015-4-2 下午3:17:52
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String onlineNumber() throws Exception {
		Channel channel = GameCoon.getChannel(serverid);
		if (channel == null) {
			result = null;
		} else {
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20000);
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
			String onlineNumber = Lock.threadMapReturnString.get("onlineNumber");
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("在线人数", onlineNumber);

			// 组成json数据
			JSONObject json = JSONObject.fromObject(map);
			result = json.toString();// 给result赋值，传递给页面
		}
		return SUCCESS;
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

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}
}
