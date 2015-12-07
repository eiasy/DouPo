package com.huayi.doupo.logic.handler;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.jdbc.core.JdbcTemplate;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.SysGmReward;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.logic.handler.util.ActivityUtil;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * Gm工具http处理类
 * @author mp
 * @date 2015-10-26 下午2:29:34
 */
public class GmHttpHandler extends DALFactory {
	
	/**
	 * 更新字典数据-多服
	 * @author mp
	 * @date 2015-10-26 下午2:30:48
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static String updateDict (Map<String, String> paramsMap) throws Exception{
		try {
			
			String updateSql = paramsMap.get("updateSql");
			JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.GetObjectWithSpringContext("JdbcTemplate");
			jdbcTemplate.batchUpdate(updateSql.toString().split("#"));
			
			//刷新字典数据
			StringBuilder tablesBuilder = new StringBuilder();
			for (String sql : updateSql.toString().split("#")) {
				if (sql.contains("truncate table") && (sql.contains("Dict") || sql.contains("Sys"))) {
					tablesBuilder.append(sql.replace("truncate table", "").replace("`", "").trim());
				}
			}
			String tableNameList = tablesBuilder.toString();
			if (tableNameList != null && !tableNameList.equals("")) {
				DictData.isAll = 0;
				DictData.tableNameList = tableNameList.replace("_", "");
				DictData.dictData(0);
				
				//刷新完以后重置初始值
				DictData.isAll = 1;
				DictData.tableNameList = "";
			}
			return "0";
		} catch (Exception e) {
			e.printStackTrace();
			return "1";
		}
	}
	
	/**
	 * 添加跑马灯
	 * @author mp
	 * @date 2015-10-29 下午5:16:48
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static String addMarqueeMulty (Map<String, String> paramsMap) throws Exception {
		
		//获取参数
		String startTime = paramsMap.get("startTime");
		String endTime = paramsMap.get("endTime");
		Integer inteval = Integer.valueOf(paramsMap.get("inteval"));
		String content = paramsMap.get("content");
		
		//添加跑马灯线程
		MarqueeUtil.addUserMarquee(content, endTime, inteval, startTime);
		
		return "操作成功";
	}
	
	/**
	 * 多服发放全服奖励
	 * @author mp
	 * @date 2015-10-30 下午1:52:49
	 * @param paramsMap
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public static String provideAllMulty (Map<String, String> paramsMap) throws Exception {
		String content = paramsMap.get("content");
		String thingList = paramsMap.get("thingList");
		String currTime = DateUtil.getCurrTime();
		
		//插之前删除3天前的数据,防止数据不断增加,玩家领奖也只能领近三天的全服奖励
		int receiveDay = DictMapUtil.getSysConfigIntValue(StaticSysConfig.receiveDay);
		String receiveDayYhs = DateUtil.getNumDayDate(receiveDay * -1);
		List<SysGmReward> sysGmRewardList = getSysGmRewardDAL().getList("rewardTime <= '"+receiveDayYhs+"'", 0);
		for (SysGmReward obj : sysGmRewardList) {
			getSysGmRewardDAL().deleteById(obj.getId(), 0);
		}
		
		//插入GM全服发奖系统表
		SysGmReward sysGmReward = new SysGmReward();
		sysGmReward.setContent(content);
		sysGmReward.setParamList(thingList);
		sysGmReward.setRewardTime(currTime);
		getSysGmRewardDAL().add(sysGmReward, 0);
		
		//给当前在线玩家发奖
		for (Entry<String, Player> entry : PlayerMapUtil.getMap().entrySet()) {
			String onlineChannelId = entry.getKey();
			Player onlinePlayer = entry.getValue();
			int onlinePlayerId = onlinePlayer.getPlayerId();
			
			//同步消息
			MessageData otherSyncMsgData = new MessageData();
			ActivityUtil.addInstPlayerAward(onlinePlayerId, 3, thingList, currTime, content, otherSyncMsgData);
			MessageUtil.sendSyncMsg(onlineChannelId, otherSyncMsgData);
		}
		
		//刷新SysGmReward字典表
		String tableNameList = "Sys_GmReward";
		if (tableNameList != null && !tableNameList.equals("")) {
			DictData.isAll = 0;
			DictData.tableNameList = tableNameList.replace("_", "");
		}
		DictData.dictData(0);
		
		//刷新完以后重置初始值
		DictData.isAll = 1;
		DictData.tableNameList = "";
		
		return "操作成功";
	}
	
}
