package com.huayi.doupo.logic.handler.util.marquee;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticMsgRule;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

/**
 * 取数据线程  每1秒取一次数据
 * @author mp
 * @date 2013-12-20 下午5:14:58
 */
public class TakeThread extends Thread implements Runnable{
	
	List<String> contentList = new ArrayList<String>();
	
	@Override
	public void run() {
		
//		while (true) {
//			try {
//				TimeUnit.SECONDS.sleep(MarqueeUtil.sysMarqueeIntevalTime);
//				String content = MarqueeUtil.blockingDeque.pollFirst();
//				if (content != null) {
//					System.out.println(DateUtil.getCurrTime() + content);
//				} else {
//					System.out.println(DateUtil.getCurrTime() + "--- no data ---");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
		
		while(true){
			try {
				TimeUnit.SECONDS.sleep(MarqueeUtil.sysMarqueeIntevalTime);
				
				StringBuffer sbBuffer = new StringBuffer("");
				String content = MarqueeUtil.blockingDeque.pollFirst();
				
				if(content != null){
					sbBuffer.append(content);
					
					MessageData retMsgData = new MessageData();
					retMsgData.putStringItem("1", sbBuffer.toString());//内容
//					retMsgData.putStringItem("2", "65,245,69");//颜色区分
					
//					System.out.println(content);
					
					Map<String, Player> playerMap = PlayerMapUtil.getMap();
					for(Entry<String, Player> map : playerMap.entrySet()){
						String channelId = map.getKey();
						MessageUtil.pushMsg(channelId, StaticMsgRule.pushMarquee, retMsgData);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/*
		while(true){
			try {
				TimeUnit.SECONDS.sleep(MarqueeUtil.sysMarqueeIntevalTime);
				
				StringBuffer sbBuffer = new StringBuffer("");
				for(int i = 1; i <= MarqueeUtil.sysMarqueeMsgNum; i++){
					String content = MarqueeUtil.blockingDeque.pollFirst();
					if(content == null){
						if(contentList.size() <= 0){
							String randomName = RandomNameUtil.getRandmName();
							String con[] = {"在开宝箱时，意外获得技能  幽灵神风拳，恭喜他!","经过不懈的努力，成功拼合技能  幽灵神风拳",
									"在开宝箱中意外获得装备  七星刀 ，真是太幸运了。","在开宝箱中意外获得装备  白色披风 ，真是太幸运了。",
									"在商店中招募 猿·贝吉塔 成为自己的队员。","在商店中招募 东界王神 成为自己的队员。",
									"在商店中招募 布欧 成为自己的队员。",
							"在商店中招募 猿·孙悟饭 成为自己的队员。"};
							int randomIndex = RandomUtil.getRandomInt(con.length);
							content = randomName + con[randomIndex];
						}else{
							int randomIndex = RandomUtil.getRandomInt(contentList.size());
							content = contentList.get(randomIndex);
						}
					}else{
						if(contentList.size() < 20){
							if(content.contains("开宝箱") || content.contains("成功拼合技能") || content.contains("联盟商店兑换") || content.contains("成功锻造") || content.contains("商店中招募") || content.contains("集齐足够的魂魄")){
								contentList.add(content);
							}
						}else{
							if(content.contains("开宝箱") || content.contains("成功拼合技能") || content.contains("联盟商店兑换") || content.contains("成功锻造") || content.contains("商店中招募") || content.contains("集齐足够的魂魄")){
								int randomIndex = RandomUtil.getRandomInt(contentList.size());
								contentList.set(randomIndex, content);
							}
						}
					}
					sbBuffer.append(content);
					sbBuffer.append(";");
				}
				
//				System.out.println("===marquee===" + sbBuffer.toString());
				
				MessageData syncMsgData = new MessageData();
				MessageData marqueeMsgData = new MessageData();
				marqueeMsgData.putStringItem("1", sbBuffer.toString());
				syncMsgData.putMessageItem("marqueeInfo", marqueeMsgData.getMsgData());
				
				Map<Integer, Player> playerMap = PlayerMapUtil.getMap();
				for(Entry<Integer, Player> map : playerMap.entrySet()){
					int channelId = map.getKey();
					MessageUtil.sendSuccMsgToAllPlayer(channelId, StaticMsgRule.pushMarquee, 1, syncMsgData);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	*/}
}