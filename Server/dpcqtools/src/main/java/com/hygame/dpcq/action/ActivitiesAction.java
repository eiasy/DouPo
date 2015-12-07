package com.hygame.dpcq.action;

import com.hygame.dpcq.model.*;
import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import model.proto.Message;
import net.sf.json.JSONArray;

import com.hygame.dpcq.config.Goods;
import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.model.proto.util.MessageData;
import com.hygame.dpcq.servlet.Boot;
import com.hygame.dpcq.tools.HttpClientUtil;
import com.hygame.dpcq.tools.JsonUtil;
import com.hygame.dpcq.tools.Lock;
import com.hygame.dpcq.tools.PropertyUtil;
import com.hygame.dpcq.tools.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ActivitiesAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private Integer serverid;
	private HttpServletRequest request;  
	private String result;
	private int id ;
	private String name ;
	private int smallUiId ;
	private String startTime ;
	private String endTime ;
	private int isNew ;
	private int isForevery ;
	private int isView ;
	private int rank ;
	private int chance ;     //权值

	private String content; //周活动内容
	
	private String rewards;//巅峰强者/限时英雄奖励 /资源矿
	private String thingCode;//物品代码
	
	private int startRankNum;//开始排名-限时英雄排名奖励
	private int endRankNum;//结束排名-限时英雄排名奖励
	
	private int cost;//招财进宝-消耗元宝数
	private int obtain;//招财进宝-获得元宝数
	private int weight;//招财进宝-权重
	
	private String things;//整点抢物品
	private int buyCount;//全服购买次数
	private int buyType;//购买类型 1-元宝 2-银币
	private int buyValue;//现价
	private int originalValue;//原价
	
	private int mark;//转盘 物品类型
	private String marks;//排名段
	
	private String thingsName;//每日特惠-礼包名称
	private String price;//每日特惠-礼包内物品价格
	
	private int limitNum;//限时抢购-限购次数
	private int oldPrice;//限时抢购-原价
	private int newPrice;//限时抢购-现价
	private int moneyType;//限时抢购-货币类型 [1-金币 2-银币]
	
	private float multiple;//节假日活动
	
	private int type;//屠魔商店   类型
	private int needbossIntegral;//屠魔商店  兑换积分
	
	
	

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getNeedbossIntegral() {
		return needbossIntegral;
	}

	public void setNeedbossIntegral(int needbossIntegral) {
		this.needbossIntegral = needbossIntegral;
	}

	public float getMultiple() {
		return multiple;
	}

	public void setMultiple(float multiple) {
		this.multiple = multiple;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		this.limitNum = limitNum;
	}

	public int getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(int oldPrice) {
		this.oldPrice = oldPrice;
	}

	public int getNewPrice() {
		return newPrice;
	}

	public void setNewPrice(int newPrice) {
		this.newPrice = newPrice;
	}

	public int getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(int moneyType) {
		this.moneyType = moneyType;
	}

	public String getThingsName() {
		return thingsName;
	}

	public void setThingsName(String thingsName) {
		this.thingsName = thingsName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public String getThings() {
		return things;
	}

	public void setThings(String things) {
		this.things = things;
	}

	public int getBuyCount() {
		return buyCount;
	}

	public void setBuyCount(int buyCount) {
		this.buyCount = buyCount;
	}

	public int getBuyType() {
		return buyType;
	}

	public void setBuyType(int buyType) {
		this.buyType = buyType;
	}

	public int getBuyValue() {
		return buyValue;
	}

	public void setBuyValue(int buyValue) {
		this.buyValue = buyValue;
	}

	public int getOriginalValue() {
		return originalValue;
	}

	public void setOriginalValue(int originalValue) {
		this.originalValue = originalValue;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getObtain() {
		return obtain;
	}

	public void setObtain(int obtain) {
		this.obtain = obtain;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getStartRankNum() {
		return startRankNum;
	}

	public void setStartRankNum(int startRankNum) {
		this.startRankNum = startRankNum;
	}

	public int getEndRankNum() {
		return endRankNum;
	}

	public void setEndRankNum(int endRankNum) {
		this.endRankNum = endRankNum;
	}

	public String getThingCode() {
		return thingCode;
	}

	public void setThingCode(String thingCode) {
		this.thingCode = thingCode;
	}

	public String getRewards() {
		return rewards;
	}

	public void setRewards(String rewards) {
		this.rewards = rewards;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getChance() {
		return chance;
	}

	public void setChance(int chance) {
		this.chance = chance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSmallUiId() {
		return smallUiId;
	}

	public void setSmallUiId(int smallUiId) {
		this.smallUiId = smallUiId;
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

	public int getIsNew() {
		return isNew;
	}

	public void setIsNew(int isNew) {
		this.isNew = isNew;
	}

	public int getIsForevery() {
		return isForevery;
	}

	public void setIsForevery(int isForevery) {
		this.isForevery = isForevery;
	}

	public int getIsView() {
		return isView;
	}

	public void setIsView(int isView) {
		this.isView = isView;
	}

	public Integer getServerid() {
		return serverid;
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
	public String showActivities() throws Exception{
		
		Channel channel = GameCoon.getChannel(serverid);
		if(channel == null){
			result = null;
		}else{
			MessageData md = new MessageData();
			Message.Msg.Builder msg = Message.Msg.newBuilder();
			msg.setHeader(20010);
			msg.setVersion(0);
			msg.setMsgdata(md.getMsgData());
			channel.writeAndFlush(msg.build());
	
			//锁线程
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
			String showMarquee = Lock.threadMapReturnString.get("actlist");
			result = showMarquee;//给result赋值，传递给页面 
		}
		return SUCCESS;	
	}
	
	public String updateActivities() throws Exception{
		Channel channel = GameCoon.getChannel(serverid);
		
		MessageData md = new MessageData();

		Message.Msg.Builder msg = Message.Msg.newBuilder();
		//跑马灯
		msg.setHeader(20011);
		msg.setVersion(0);
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		md.putIntItem("id", id);
		md.putStringItem("name", name);
		md.putIntItem("smallUiId", smallUiId);
		
		md.putStringItem("startTime", startTime);
		md.putStringItem("endTime", endTime);
		md.putIntItem("isNew", isNew);
		md.putIntItem("isForevery", 0);
		md.putIntItem("isHotKey", isForevery);
		md.putIntItem("isView", isView);
		md.putIntItem("rank", rank);
		md.putIntItem("chance", chance);
		msg.setMsgdata(md.getMsgData());
		//发送消息
		channel.writeAndFlush(msg.build());
		
		return "hdkg";
	}
	
	/**
	 * 获取周活动
	 * @author mp
	 * @date 2015-9-18 上午10:12:03
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String getZhouHuoDong() throws Exception{
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getZhouHuoDong");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新周活动
	 * @author mp
	 * @date 2015-9-18 上午10:50:53
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateZhouHuoDong() throws Exception{
		try {
			content = new String(content.getBytes("iso-8859-1"), "utf-8");
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateZhouHuoDong");
			paramMap.put("content", content);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取巅峰强者排名奖励列表
	 * @author mp
	 * @date 2015-9-18 下午3:38:59
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getStrogerHeroList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getStrogerHeroList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityStrogerHero> strongerHeroList = (List<DictActivityStrogerHero>) JSONArray.toList(jsonobj,DictActivityStrogerHero.class);
			List<DictActivityStrogerHero> strongerHeroNewList = new ArrayList<>(); 
			for (DictActivityStrogerHero obj : strongerHeroList) {
				String rewards = obj.getRewards();
				int tableTypeId = Integer.valueOf(rewards.split("_")[0]);
				int tableFieldId = Integer.valueOf(rewards.split("_")[1]);
				int value = Integer.valueOf(rewards.split("_")[2]);
				String name = getGoodsName(tableTypeId, tableFieldId);
				obj.setRewards(name + "_" + value);
				strongerHeroNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(strongerHeroNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新巅峰强者排名奖励
	 * @author mp
	 * @date 2015-9-18 下午8:30:37
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateStrogerHeroList() throws Exception {
		try {
			rewards = new String(rewards.getBytes("iso-8859-1"), "utf-8");
			if (rewards.contains(";")) {
				result = "此奖励中,奖励物品不允许为多个";
				return SUCCESS;
			}
			String name = rewards.split("_")[0];
			int value = Integer.valueOf(rewards.split("_")[1]);
			String thingList = "";
			if (thingCode.equals("")) {
				thingList = getThingsList(name, value);
				//验证是否有此物品
				if (thingList.equals("")) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			} else {
				thingList = thingCode;
				//验证是否存在
				if (!isExistThing(thingList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateStrogerHeroList");
			paramMap.put("id", id+"");
			paramMap.put("rewards", thingList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取限时英雄排名奖励列表
	 * @author mp
	 * @date 2015-9-21 上午10:36:35
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getLimitHeroRankList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getLimitHeroRankList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityLimitTimeHeroRankReward> limitHeroRankList = (List<DictActivityLimitTimeHeroRankReward>) JSONArray.toList(jsonobj,DictActivityLimitTimeHeroRankReward.class);
			List<DictActivityLimitTimeHeroRankReward> limitHeroRankNewList = new ArrayList<>(); 
			for (DictActivityLimitTimeHeroRankReward obj : limitHeroRankList) {
				String newRewards = "";
				if (obj.getStartRankNum() == 0 && obj.getEndRankNum() == 0) {
					obj.setRewards("7_" + obj.getRewards() + "_1");
				}
				String rewards = obj.getRewards();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setRewards(StringUtil.noContainLastString(newRewards));
				limitHeroRankNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(limitHeroRankNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取限时英雄积分奖励列表
	 * @author mp
	 * @date 2015-9-21 上午11:20:36
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getLimitHeroJifenList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getLimitHeroJifenList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityLimitTimeHeroJiFenReward> limitHeroJifenList = (List<DictActivityLimitTimeHeroJiFenReward>) JSONArray.toList(jsonobj,DictActivityLimitTimeHeroJiFenReward.class);
			List<DictActivityLimitTimeHeroJiFenReward> limitHeroJifenNewList = new ArrayList<>(); 
			for (DictActivityLimitTimeHeroJiFenReward obj : limitHeroJifenList) {
				String newRewards = "";
				String rewards = obj.getRewards();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setRewards(StringUtil.noContainLastString(newRewards));
				limitHeroJifenNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(limitHeroJifenNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新限时英雄排名奖励列表
	 * @author mp
	 * @date 2015-9-21 下午2:11:29
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateLimitHeroRankList() throws Exception {
		
		try {
			String thingAllList = "";
			rewards = new String(rewards.getBytes("iso-8859-1"), "utf-8");
			
			if (id == 100) {
				if (rewards.contains(";")) {
					result = "推荐卡牌只能为一个";
					return SUCCESS;
				}
				if (thingCode.equals("")) {
					String thingList = "";
					String name = rewards.split("_")[0];
					int value = Integer.valueOf(rewards.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所推荐的卡牌不存在";
						return SUCCESS;
					}
					if (!thingList.split("_")[0].equals("7")) {
						result = "您只能选择卡牌类型";
						return SUCCESS;
					}
					thingAllList = thingList.split("_")[1];
				} else {
					if (!thingCode.split("_")[0].equals("7")) {
						result = "您只能选择卡牌类型";
						return SUCCESS;
					}
					thingAllList = thingCode.split("_")[1];
				}
			} else {
				if (thingCode.equals("")) {
					//验证这些物品是否都存在
					for (String rewardObj : rewards.split(";")) {
						String thingList = "";
						String name = rewardObj.split("_")[0];
						int value = Integer.valueOf(rewardObj.split("_")[1]);
						thingList = getThingsList(name, value);
						if (thingList.equals("")) {
							result = "您所修改的奖励物品不存在";
							return SUCCESS;
						}
						thingAllList += thingList + ";";
					}
					thingAllList = StringUtil.noContainLastString(thingAllList);
				} else {
					thingAllList = thingCode;
					//验证是否存在
					if (!isExistThingList(thingAllList)) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
				}
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateLimitHeroRankList");
			paramMap.put("id", id+"");
			paramMap.put("rewards", thingAllList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新限时英雄积分奖励列表
	 * @author mp
	 * @date 2015-9-21 下午4:54:41
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateLimitHeroJifenList() throws Exception {
		try {
			String thingAllList = "";
			rewards = new String(rewards.getBytes("iso-8859-1"), "utf-8");
			
			if (thingCode.equals("")) {
				//验证这些物品是否都存在
				for (String rewardObj : rewards.split(";")) {
					String thingList = "";
					String name = rewardObj.split("_")[0];
					int value = Integer.valueOf(rewardObj.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
					thingAllList += thingList + ";";
				}
				thingAllList = StringUtil.noContainLastString(thingAllList);
			} else {
				thingAllList = thingCode;
				//验证是否存在
				if (!isExistThingList(thingAllList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateLimitHeroJifenList");
			paramMap.put("id", id+"");
			paramMap.put("rewards", thingAllList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取招财进宝活动
	 * @author mp
	 * @date 2015-9-22 上午11:42:34
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String getTreasuresList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getTreasuresList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取节假日活动列表
	 * @author mp
	 * @date 2015-11-5 下午5:36:26
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String getActivityHolidayList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getActivityHolidayList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新节假日活动列表
	 * @author mp
	 * @date 2015-11-5 下午5:39:46
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateActivityHolidayList1() throws Exception {
		try {
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateActivityHolidayList");
			paramMap.put("id", id+"");
			paramMap.put("startTime", startTime + "");
			paramMap.put("multiple", multiple + "");
			paramMap.put("endTime", endTime + "");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新招财进宝活动
	 * @author mp
	 * @date 2015-9-22 下午2:04:56
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateTreasuresList() throws Exception {
		try {
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateTreasuresList");
			paramMap.put("id", id+"");
			paramMap.put("cost", cost + "");
			paramMap.put("obtain", obtain + "");
			paramMap.put("weight", weight + "");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取累计消耗列表
	 * @author mp
	 * @date 2015-9-22 下午2:56:44
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getTotalCostList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getTotalCostList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictactivityTotalCost> activityTotalCostList = (List<DictactivityTotalCost>) JSONArray.toList(jsonobj,DictactivityTotalCost.class);
			List<DictactivityTotalCost> activityTotalCostNewList = new ArrayList<>(); 
			for (DictactivityTotalCost obj : activityTotalCostList) {
				String newRewards = "";
				String rewards = obj.getAwards();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setAwards(StringUtil.noContainLastString(newRewards));
				activityTotalCostNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(activityTotalCostNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取世界boss商店
	 * @author mp
	 * @date 2015-12-1 下午2:22:06
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getBossShopList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getBossShopList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictWorldBossStore> worldBossStoreList = (List<DictWorldBossStore>) JSONArray.toList(jsonobj,DictWorldBossStore.class);
			List<DictWorldBossStore> worldBossStoreNewList = new ArrayList<>(); 
			for (DictWorldBossStore obj : worldBossStoreList) {
				String newRewards = "";
				String rewards = obj.getThings();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setThings(StringUtil.noContainLastString(newRewards));
				worldBossStoreNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(worldBossStoreNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新世界Boss商店
	 * @author mp
	 * @date 2015-12-1 下午2:35:05
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateBossShopList() throws Exception {
		try {
			String thingAllList = "";
			
			if (things.contains(";")) {
				result = "此奖励中,奖励物品不允许为多个";
				return SUCCESS;
			}
			
			things = new String(things.getBytes("iso-8859-1"), "utf-8");
			name = new String(name.getBytes("iso-8859-1"), "utf-8");
			
			if (thingCode.equals("")) {
				//验证这些物品是否都存在
				for (String rewardObj : things.split(";")) {
					String thingList = "";
					String name = rewardObj.split("_")[0];
					int value = Integer.valueOf(rewardObj.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
					thingAllList += thingList + ";";
				}
				thingAllList = StringUtil.noContainLastString(thingAllList);
			} else {
				thingAllList = thingCode;
				//验证是否存在
				if (!isExistThingList(thingAllList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateBossShopList");
			paramMap.put("id", id+"");
			paramMap.put("things", thingAllList);
			paramMap.put("name", name + "");
			paramMap.put("rank", rank + "");
			paramMap.put("needbossIntegral", needbossIntegral + "");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新累计消耗列表
	 * @author mp
	 * @date 2015-9-22 下午5:25:25
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateTotalCostList() throws Exception {
		try {
			String thingAllList = "";
			rewards = new String(rewards.getBytes("iso-8859-1"), "utf-8");
			
			if (thingCode.equals("")) {
				//验证这些物品是否都存在
				for (String rewardObj : rewards.split(";")) {
					String thingList = "";
					String name = rewardObj.split("_")[0];
					int value = Integer.valueOf(rewardObj.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
					thingAllList += thingList + ";";
				}
				thingAllList = StringUtil.noContainLastString(thingAllList);
			} else {
				thingAllList = thingCode;
				//验证是否存在
				if (!isExistThingList(thingAllList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateTotalCostList");
			paramMap.put("id", id+"");
			paramMap.put("awards", thingAllList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取整点抢活动列表
	 * @author mp
	 * @date 2015-9-23 上午10:42:50
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getGrabTheHourList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getGrabTheHourList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityGrabTheHour> activityGrabTheHourList = (List<DictActivityGrabTheHour>) JSONArray.toList(jsonobj,DictActivityGrabTheHour.class);
			List<DictActivityGrabTheHour> activityGrabTheHourNewList = new ArrayList<>(); 
			for (DictActivityGrabTheHour obj : activityGrabTheHourList) {
				String newRewards = "";
				String rewards = obj.getThings();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setThings(StringUtil.noContainLastString(newRewards));
				activityGrabTheHourNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(activityGrabTheHourNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新整点抢配置
	 * @author mp
	 * @date 2015-9-23 上午11:42:11
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateGrabTheHourList() throws Exception {
		try {
			things = new String(things.getBytes("iso-8859-1"), "utf-8");
			if (things.contains(";")) {
				result = "此奖励中,奖励物品不允许为多个";
				return SUCCESS;
			}
			String name = things.split("_")[0];
			int value = Integer.valueOf(things.split("_")[1]);
			String thingList = "";
			if (thingCode.equals("")) {
				thingList = getThingsList(name, value);
				//验证是否有此物品
				if (thingList.equals("")) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			} else {
				thingList = thingCode;
				//验证是否存在
				if (!isExistThing(thingList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			
			if (buyType != 1 && buyType !=2) {
				result = "购买类型只允许填1或者2";
				return SUCCESS;
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateGrabTheHourList");
			paramMap.put("id", id+"");
			paramMap.put("things", thingList);
			paramMap.put("buyCount", buyCount + "");
			paramMap.put("buyType", buyType + "");
			paramMap.put("buyValue", buyValue + "");
			paramMap.put("originalValue", originalValue + "");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取转盘数据
	 * @author mp
	 * @date 2015-9-23 下午2:28:00
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getLuckList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getLuckList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityLuck> activityLuckList = (List<DictActivityLuck>) JSONArray.toList(jsonobj,DictActivityLuck.class);
			List<DictActivityLuck> activityLuckNewList = new ArrayList<>(); 
			for (DictActivityLuck obj : activityLuckList) {
				String newRewards = "";
				String rewards = obj.getData().split("_")[0] + "_" + obj.getData().split("_")[1] + "_" + obj.getData().split("_")[2];
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setData(StringUtil.noContainLastString(newRewards));
				activityLuckNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(activityLuckNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取转盘排行奖励
	 * @author mp
	 * @date 2015-9-23 下午7:30:34
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getLuckRankRewardList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getLuckRankRewardList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityLuck> activityLuckList = (List<DictActivityLuck>) JSONArray.toList(jsonobj,DictActivityLuck.class);
			List<DictActivityLuck> activityLuckNewList = new ArrayList<>(); 
			Map<String, DictActivityLuck> noRepMap = new LinkedHashMap<>();
			for (DictActivityLuck obj : activityLuckList) {
				if (noRepMap.containsKey(obj.getMark())) {
					System.out.println("contains= " + obj.getMark());
					DictActivityLuck activityLuck = noRepMap.get(obj.getMark());
					activityLuck.setData(activityLuck.getData() + ";" + obj.getData());
				} else {
					System.out.println("contains else = " + obj.getMark());
					DictActivityLuck activityLuck = new DictActivityLuck();
					activityLuck.setMark(obj.getMark());
					activityLuck.setData(obj.getData());
					noRepMap.put(obj.getMark(), activityLuck);
				}
			}
			System.out.println(noRepMap);
			int index = 0;
			for (Entry<String, DictActivityLuck> entry : noRepMap.entrySet()) {
				index ++;
				DictActivityLuck obj = entry.getValue();
    			String newRewards = "";
    			for (String oldReward : obj.getData().split(";")) {
    				int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
    				int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
    				int value = Integer.valueOf(oldReward.split("_")[2]);
    				String name = getGoodsName(tableTypeId, tableFieldId);
    				newRewards += (name + "_" + value + ";");
    			}
    			obj.setId(index);
    			obj.setData(StringUtil.noContainLastString(newRewards));
    			activityLuckNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(activityLuckNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新转盘数据
	 * @author mp
	 * @date 2015-9-24 上午10:35:17
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateLuckList() throws Exception {
		try {
			things = new String(things.getBytes("iso-8859-1"), "utf-8");
			if (things.contains(";")) {
				result = "此奖励中,奖励物品不允许为多个";
				return SUCCESS;
			}
			String name = things.split("_")[0];
			int value = Integer.valueOf(things.split("_")[1]);
			String thingList = "";
			if (thingCode.equals("")) {
				thingList = getThingsList(name, value);
				//验证是否有此物品
				if (thingList.equals("")) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			} else {
				thingList = thingCode;
				//验证是否存在
				if (!isExistThing(thingList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateLuckList");
			paramMap.put("id", id+"");
			if (mark == 1) {
				thingList = thingList + "_1_1";
			} else if (mark == 2) {
				thingList = thingList + "_1";
			}
			paramMap.put("data", thingList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新转盘排行奖励数据
	 * @author mp
	 * @date 2015-9-25 上午10:25:26
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateLuckRankList() throws Exception {
		try {
			String thingAllList = "";
			things = new String(things.getBytes("iso-8859-1"), "utf-8");
			
			if (thingCode.equals("")) {
				//验证这些物品是否都存在
				for (String rewardObj : things.split(";")) {
					String thingList = "";
					String name = rewardObj.split("_")[0];
					int value = Integer.valueOf(rewardObj.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
					thingAllList += thingList + ";";
				}
				thingAllList = StringUtil.noContainLastString(thingAllList);
			} else {
				thingAllList = thingCode;
				//验证是否存在
				if (!isExistThingList(thingAllList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateLuckRankList");
			paramMap.put("mark", marks+"");
			paramMap.put("data", thingAllList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}

	/**
	 * 获取每日特惠活动列表
	 * @author mp
	 * @date 2015-9-25 下午12:05:21
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getDailyDealsList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getDailyDealsList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityDailyDeals> dailyDealsList = (List<DictActivityDailyDeals>) JSONArray.toList(jsonobj,DictActivityDailyDeals.class);
			List<DictActivityDailyDeals> dailyDealsNewList = new ArrayList<>(); 
			for (DictActivityDailyDeals obj : dailyDealsList) {
				String newRewards = "";
				String rewards = obj.getThings();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setThings(StringUtil.noContainLastString(newRewards));
				dailyDealsNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(dailyDealsNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新每日特惠活动
	 * @author mp
	 * @date 2015-9-25 下午1:49:13
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateDailyDealsList() throws Exception {
		try {
			String thingAllList = "";
			things = new String(things.getBytes("iso-8859-1"), "utf-8");
			thingsName = new String(thingsName.getBytes("iso-8859-1"), "utf-8");
			
			//验证礼包物品个数
			if (things.split(";").length != 6) {
				result = "礼包物品只能是6个";
				return SUCCESS;
			}
			
			//验证礼包物品价格个数
			if (price.split(";").length != 6) {
				result = "礼包物品价格只能是6个";
				return SUCCESS;
			}
			
			//验证礼包物品价格是否都为数字
			for (String numString : price.split(";")) {
				try {
					Integer.valueOf(numString);
				} catch (Exception e) {
					result = "价格列表里只能都为数字";
					return SUCCESS;
				}
			}
			
			if (thingCode.equals("")) {
				//验证这些物品是否都存在
				for (String rewardObj : things.split(";")) {
					String thingList = "";
					String name = rewardObj.split("_")[0];
					int value = Integer.valueOf(rewardObj.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
					thingAllList += thingList + ";";
				}
				thingAllList = StringUtil.noContainLastString(thingAllList);
			} else {
				thingAllList = thingCode;
				//验证是否存在
				if (!isExistThingList(thingAllList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateDailyDealsList");
			paramMap.put("id", id+"");
			paramMap.put("price", price+"");
			paramMap.put("thingsName", thingsName+"");
			paramMap.put("things", thingAllList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}

	/**
	 * 获取限时抢购列表
	 * @author mp
	 * @date 2015-9-25 下午2:53:48
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getLimitShoppingList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getLimitShoppingList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityLimitShopping> activityLimitShoppingList = (List<DictActivityLimitShopping>) JSONArray.toList(jsonobj,DictActivityLimitShopping.class);
			List<DictActivityLimitShopping> activityLimitShoppingNewList = new ArrayList<>(); 
			for (DictActivityLimitShopping obj : activityLimitShoppingList) {
				String newRewards = "";
				String rewards = obj.getThing();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setThing(StringUtil.noContainLastString(newRewards));
				activityLimitShoppingNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(activityLimitShoppingNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新限时抢购活动数据
	 * @author mp
	 * @date 2015-9-25 下午3:22:07
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateLimitShoppingList() throws Exception {
		try {
			things = new String(things.getBytes("iso-8859-1"), "utf-8");
			if (things.contains(";")) {
				result = "此奖励中,奖励物品不允许为多个";
				return SUCCESS;
			}
			String name = things.split("_")[0];
			int value = Integer.valueOf(things.split("_")[1]);
			String thingList = "";
			if (thingCode.equals("")) {
				thingList = getThingsList(name, value);
				//验证是否有此物品
				if (thingList.equals("")) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			} else {
				thingList = thingCode;
				//验证是否存在
				if (!isExistThing(thingList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			
			if (moneyType != 1 && moneyType !=2) {
				result = "购买类型只允许填1或者2";
				return SUCCESS;
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateLimitShoppingList");
			paramMap.put("id", id+"");
			paramMap.put("thing", thingList);
			paramMap.put("limitNum", limitNum + "");
			paramMap.put("oldPrice", oldPrice + "");
			paramMap.put("newPrice", newPrice + "");
			paramMap.put("moneyType", moneyType + "");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取累计充值数据列表
	 * @author mp
	 * @date 2015-10-8 上午10:55:02
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getAddRechargeList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getAddRechargeList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivityAddRecharge> addRechargeList = (List<DictActivityAddRecharge>) JSONArray.toList(jsonobj,DictActivityAddRecharge.class);
			List<DictActivityAddRecharge> addRechargeNewList = new ArrayList<>(); 
			for (DictActivityAddRecharge obj : addRechargeList) {
				String newRewards = "";
				String rewards = obj.getThings();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setThings(StringUtil.noContainLastString(newRewards));
				addRechargeNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(addRechargeNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新累计充值数据
	 * @author mp
	 * @date 2015-10-8 上午11:21:03
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateAddRechargeList() throws Exception {
		try {
			String thingAllList = "";
			things = new String(things.getBytes("iso-8859-1"), "utf-8");
			
			if (thingCode.equals("")) {
				//验证这些物品是否都存在
				for (String rewardObj : things.split(";")) {
					String thingList = "";
					String name = rewardObj.split("_")[0];
					int value = Integer.valueOf(rewardObj.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
					thingAllList += thingList + ";";
				}
				thingAllList = StringUtil.noContainLastString(thingAllList);
			} else {
				thingAllList = thingCode;
				//验证是否存在
				if (!isExistThingList(thingAllList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateAddRechargeList");
			paramMap.put("id", id+"");
			paramMap.put("things", thingAllList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取魂魄活动副本掉落
	 * @author mp
	 * @date 2015-10-14 下午3:16:49
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getSoulActivityChapterList () throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getSoulActivityChapterList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			
	        JSONArray jsonobj = JSONArray.fromObject(retMsg);  
	        List<DictActivitySoulChapterDrop> soulChapterDropList = (List<DictActivitySoulChapterDrop>) JSONArray.toList(jsonobj,DictActivitySoulChapterDrop.class);
			List<DictActivitySoulChapterDrop> soulChapterDropNewList = new ArrayList<>(); 
			for (DictActivitySoulChapterDrop obj : soulChapterDropList) {
				String rewards = obj.getThings();
				int tableTypeId = Integer.valueOf(rewards.split("_")[0]);
				int tableFieldId = Integer.valueOf(rewards.split("_")[1]);
				int value = Integer.valueOf(rewards.split("_")[2]);
				String name = getGoodsName(tableTypeId, tableFieldId);
				obj.setThings(name + "_" + value);
				soulChapterDropNewList.add(obj);
			}
			retMsg = JsonUtil.toJson(soulChapterDropNewList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新魂魄活动副本掉落
	 * @author mp
	 * @date 2015-10-14 下午3:17:11
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateSoulActivityChapterList () throws Exception {
		try {
			things = new String(things.getBytes("iso-8859-1"), "utf-8");
			if (things.contains(";")) {
				result = "此奖励中,奖励物品不允许为多个";
				return SUCCESS;
			}
			String name = things.split("_")[0];
			int value = Integer.valueOf(things.split("_")[1]);
			String thingList = "";
			if (thingCode.equals("")) {
				thingList = getThingsList(name, value);
				//验证是否有此物品
				if (thingList.equals("")) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			} else {
				thingList = thingCode;
				//验证是否存在
				if (!isExistThing(thingList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}
			
			if (!thingList.split("_")[0].equals("9")) {
				result = "您只能选择卡牌魂魄类型";
				return SUCCESS;
			}
			
			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateSoulActivityChapterList");
			paramMap.put("id", id+"");
			paramMap.put("things", thingList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}

	/**
	 * 获取资源矿特殊奖励列表
	 * @author	cui
	 * @date 	15/10/20
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getMineBoxThingList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getMineBoxThingList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);

			JSONArray jsonobj = JSONArray.fromObject(retMsg);
			List<DictMineBoxThing> oldList = (List<DictMineBoxThing>) JSONArray.toList(jsonobj,DictMineBoxThing.class);
			List<DictMineBoxThing> newList = new ArrayList<>();
			for (DictMineBoxThing obj : oldList) {
				String newRewards = "";
				String rewards = obj.getThing();
				for (String oldReward : rewards.split(";")) {
					int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
					int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
					int value = Integer.valueOf(oldReward.split("_")[2]);
					String name = getGoodsName(tableTypeId, tableFieldId);
					newRewards += (name + "_" + value + ";");
				}
				obj.setThing(StringUtil.noContainLastString(newRewards));
				newList.add(obj);
			}
			retMsg = JsonUtil.toJson(newList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}

	/**
	 * 更新资源矿特殊奖励列表
	 * @author 	cui
	 * @date 	15/10/20
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateMineBoxThingList() throws Exception {

		try {
			String thingAllList = "";
			rewards = new String(rewards.getBytes("iso-8859-1"), "utf-8");

			if (thingCode.equals("")) {
				//验证这些物品是否都存在
				for (String rewardObj : rewards.split(";")) {
					String thingList = "";
					String name = rewardObj.split("_")[0];
					int value = Integer.valueOf(rewardObj.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
					thingAllList += thingList + ";";
				}
				thingAllList = StringUtil.noContainLastString(thingAllList);
			} else {
				thingAllList = thingCode;
				//验证是否存在
				if (!isExistThingList(thingAllList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}

			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateMineBoxThingList");
			paramMap.put("id", id+"");
			paramMap.put("chance", chance+"");
			paramMap.put("things", thingAllList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}

	/**
	 * 获取世界BOSS奖励列表
	 * @author	cui
	 * @date 	15/11/02
	 * @return
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	public String getWorldBossList() throws Exception {
		try {
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "getWorldBossList");
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);

			JSONArray jsonobj = JSONArray.fromObject(retMsg);
			List<DictWorldBossReward> oldList = (List<DictWorldBossReward>) JSONArray.toList(jsonobj,DictWorldBossReward.class);
			List<DictWorldBossReward> newList = new ArrayList<>();
			for (DictWorldBossReward obj : oldList) {
				String newRewards = "";
				String rewards = obj.getDescription();
				if(rewards != null) {
					for (String oldReward : rewards.split(";")) {
						int tableTypeId = Integer.valueOf(oldReward.split("_")[0]);
						int tableFieldId = Integer.valueOf(oldReward.split("_")[1]);
						int value = Integer.valueOf(oldReward.split("_")[2]);
						String name = getGoodsName(tableTypeId, tableFieldId);
						newRewards += (name + "_" + value + ";");
					}
					obj.setDescription(StringUtil.noContainLastString(newRewards));
				}else{
					obj.setDescription("");
				}
				newList.add(obj);
			}
			retMsg = JsonUtil.toJson(newList);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}

	/**
	 * 更新世界BOSS奖励列表
	 * @author 	cui
	 * @date 	15/11/02
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String updateWorldBossList() throws Exception {

		try {
			String thingAllList = "";
			rewards = new String(rewards.getBytes("iso-8859-1"), "utf-8");

			if (thingCode.equals("")) {
				//验证这些物品是否都存在
				for (String rewardObj : rewards.split(";")) {
					String thingList = "";
					String name = rewardObj.split("_")[0];
					int value = Integer.valueOf(rewardObj.split("_")[1]);
					thingList = getThingsList(name, value);
					if (thingList.equals("")) {
						result = "您所修改的奖励物品不存在";
						return SUCCESS;
					}
					thingAllList += thingList + ";";
				}
				thingAllList = StringUtil.noContainLastString(thingAllList);
			} else {
				thingAllList = thingCode;
				//验证是否存在
				if (!isExistThingList(thingAllList)) {
					result = "您所修改的奖励物品不存在";
					return SUCCESS;
				}
			}

			//发送修改协议
			String activityHdUrl = PropertyUtil.getValue("activity.server.url");
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateWorldBossList");
			paramMap.put("id", id+"");
			paramMap.put("things", thingAllList);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(activityHdUrl, params);
			result = retMsg;
		} catch (Exception e) {
			e.printStackTrace();
			result = null;
		}
		return SUCCESS;
	}

	/**
	 * 是否存在这个物品,存在返回true,不存在返回false（单个）
	 * @author mp
	 * @date 2015-9-19 下午3:55:02
	 * @param thingCode
	 * @return
	 * @Description
	 */
	private boolean isExistThing (String thingCode) {
		boolean flag = false;
		int tableTypeId = Integer.valueOf(thingCode.split("_")[0]);
		int tableFiledId = Integer.valueOf(thingCode.split("_")[1]);
		
		for (Goods obj : Boot.goodList) {
			if (obj.getTypeid() == tableTypeId && obj.getId() == tableFiledId) {
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * 是否存在这些物品,存在返回true,不存在返回false（多个）
	 * @author mp
	 * @date 2015-9-21 下午3:58:09
	 * @param thingCode
	 * @return
	 * @Description
	 */
	private boolean isExistThingList (String thingCodeList) {
		boolean retFlag = true;
		for (String thingObj : thingCodeList.split(";")) {
			boolean flag = false;
			int tableTypeId = Integer.valueOf(thingObj.split("_")[0]);
			int tableFiledId = Integer.valueOf(thingObj.split("_")[1]);
			
			for (Goods obj : Boot.goodList) {
				if (obj.getTypeid() == tableTypeId && obj.getId() == tableFiledId) {
					flag = true;
					break;
				}
			}
			if (flag == false) {
				retFlag = false;
				break;
			}
		}
		
		return retFlag;
	}
	
	/**
	 * 根据表类型和物品名字获取物品串
	 * @author mp
	 * @date 2015-9-19 上午9:48:32
	 * @param tableTypeId
	 * @param goodsName
	 * @return
	 * @Description
	 */
	private String getThingsList (String goodsName, int value) {
		String thingsList = "";
		for (Goods obj : Boot.goodList) {
			if (obj.getName().equals(goodsName)) {
				thingsList = obj.getTypeid() + "_" + obj.getId() + "_" + value;
				break;
			}
		}
		return thingsList;
	}
	
	/**
	 * 根据表类型和表id获取物品名字
	 * @author mp
	 * @date 2015-9-18 下午4:12:38
	 * @param tableTypeId
	 * @param tableFieldId
	 * @return
	 * @Description
	 */
	private String getGoodsName (int tableTypeId, int tableFieldId) {
		String ret = tableFieldId + "";
		for (Goods obj : Boot.goodList) {
			if (obj.getTypeid() == tableTypeId && obj.getId() == tableFieldId) {
				ret = obj.getName();
				break;
			}
		}
		return ret;
	}
}
