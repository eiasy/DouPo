package com.huayi.doupo.logic.handler.util;

import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.List;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayerGmReward;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.base.util.base.SerializeUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.logic.util.ChannelMapUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

/**
 * Gm工具类
 * @author mp
 * @date 2014-10-10 下午3:22:32
 */
public class GmUtil extends DALFactory {
	
	/**
	 * 权限验证
	 * @author mp
	 * @date 2014-10-10 下午3:24:24
	 * @Description
	 */
	public static boolean validation (HashMap<String, Object> msgMap, String channelId) throws Exception{
		
		boolean flag = false;
		
		//获取Channel信息
		Channel channel = ChannelMapUtil.getByChannelId(channelId);
		String localAddress = channel.localAddress().toString();
		String gmPort = SysConfigUtil.getValue("socketGm.port");
		
		//只有连接Gm端口才能处理逻辑
		if(localAddress == null || localAddress.equals("")){
			flag = true;
			return flag;
		}else{
			if(!gmPort.equals(localAddress.split(":")[1])){
				flag = true;
				return flag;
			} else {
				//设置gm的ChannelId-还是修改成每次都重新记录吧[担心冬冬那边不能保证每次只掉一个链接]
//				if (ParamConfig.gmChannelId.equals("")) {
					ParamConfig.gmChannelId = channelId;
//				}
			}
		}
		
		//除GM端口外的其他端口,不可GM逻辑
		if (flag) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_operError);
		}
		
		return flag;
	}
	
	/**
	 * 添加Gm全服发奖
	 * @author mp
	 * @date 2014-11-13 下午4:33:50
	 * @param instPlayerId
	 * @param sendRewardTime
	 * @throws Exception
	 * @Description
	 */
	public static void addGmReward (int instPlayerId, String sendRewardTime) throws Exception {
		
		//先删除三天前的数据,用剩下的数据和全服发奖列表作比较[全服发奖也不保留三天前的数据]
		int receiveDay = DictMapUtil.getSysConfigIntValue(StaticSysConfig.receiveDay);
		String receiveDayYhs = DateUtil.getNumDayDate(receiveDay * -1);
		List<InstPlayerGmReward> instPlayerGmRewardList = getInstPlayerGmRewardDAL().getList("instPlayerId = " + instPlayerId + " and sendRewardTime <= '"+receiveDayYhs+"'", 0);
		for (InstPlayerGmReward obj : instPlayerGmRewardList) {
			getInstPlayerGmRewardDAL().deleteById(obj.getId(), 0);
		}
		
		InstPlayerGmReward instPlayerGmReward = new InstPlayerGmReward();
		instPlayerGmReward.setInstPlayerId(instPlayerId);
		instPlayerGmReward.setSendRewardTime(sendRewardTime);
		instPlayerGmReward.setGetRewardTime(DateUtil.getCurrTime());
		getInstPlayerGmRewardDAL().add(instPlayerGmReward, 0);
	}
	
	/**
	 * 发送成功消息
	 * @author mp
	 * @date 2014-12-10 下午2:47:47
	 * @param channelId
	 * @param msgMap
	 * @param messageData
	 * @Description
	 */
	public static void sendSuccMsg (String channelId, HashMap<String, Object> msgMap) {
		MessageData retMsgData = new MessageData();
		retMsgData.putStringItem("r", "s");
		MessageUtil.sendGMMsg(channelId, msgMap, retMsgData);
	}
	
	/**
	 * 序列化限时英雄积分奖励
	 * @author mp
	 * @date 2015-8-19 上午9:26:20
	 * @Description
	 */
	public static void seriLimitTimePeapleJifen () {
		String path = System.getProperty("user.dir") + "/config/";
		String fileName = "limitTimePeapleJifen.txt";
		FileUtil.createFile(path, fileName);
		FileUtil.writeFile(path + fileName, SerializeUtil.serialize(ParamConfig.recruitJifenRewardMap));
	}
	
	/**
	 * 序列化限时英雄排行奖励
	 * @author mp
	 * @date 2015-8-19 上午9:26:40
	 * @Description
	 */
	public static void seriLimitTimePeapleRank () {
		String path = System.getProperty("user.dir") + "/config/";
		String fileName = "limitTimePeapleRank.txt";
		FileUtil.createFile(path, fileName);
		FileUtil.writeFile(path + fileName, SerializeUtil.serialize(ParamConfig.recruitRankRewardMap));
	}
	
	/**
	 * 序列化最强英雄排行奖励
	 * @author mp
	 * @date 2015-8-19 下午6:01:10
	 * @Description
	 */
	public static void seriStrogerHeroRank () {
//		System.out.println("gm close server : " + ParamConfig.strogerRankRewardMap);
		String path = System.getProperty("user.dir") + "/config/";
		String fileName = "strogerHeroRank.txt";
		FileUtil.createFile(path, fileName);
		FileUtil.writeFile(path + fileName, SerializeUtil.serialize(ParamConfig.strogerRankRewardMap));
	}
	
	/**
	 * 序列化世界Boss排行奖励
	 * @author mp
	 * @date 2015-11-30 下午13:45:10
	 * @Description
	 */
	public static void seriWorldBossRank () {
		String path = System.getProperty("user.dir") + "/config/";
		String fileName = "worldBossRank.txt";
		FileUtil.createFile(path, fileName);
		FileUtil.writeFile(path + fileName, SerializeUtil.serialize(ParamConfig.worldBossRankList));
	}
	
	/**
	 * 序列化竞技场奖励
	 * @author mp
	 * @date 2015-10-8 下午4:42:26
	 * @Description
	 */
	public static void seriAreaReward () {
		String path = System.getProperty("user.dir") + "/config/";
		String fileName = "areaReward.txt";
		FileUtil.createFile(path, fileName);
		FileUtil.writeFile(path + fileName, SerializeUtil.serialize(AwardUtil.getMap()));
	}
	
}
