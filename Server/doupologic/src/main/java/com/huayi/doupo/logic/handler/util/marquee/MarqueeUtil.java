package com.huayi.doupo.logic.handler.util.marquee;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import com.google.common.collect.Lists;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.DictEquipment;
import com.huayi.doupo.base.model.DictGenerBoxThing;
import com.huayi.doupo.base.model.DictMagic;
import com.huayi.doupo.base.model.DictQuality;
import com.huayi.doupo.base.model.DictSpecialBoxThing;
import com.huayi.doupo.base.model.DictStarLevel;
import com.huayi.doupo.base.model.DictTitle;
import com.huayi.doupo.base.model.DictTitleDetail;
import com.huayi.doupo.base.model.SysMarquee;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticEquipQuality;
import com.huayi.doupo.base.model.statics.StaticMagicQuality;
import com.huayi.doupo.base.model.statics.StaticQuality;
import com.huayi.doupo.base.model.statics.StaticTableType;
import com.huayi.doupo.base.model.statics.custom.CustomMarqueeType;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import com.huayi.doupo.logic.util.SysMarqueeMapUtil;

/**
 * 跑马灯工具类
 * 
 * @author mp
 * @date 2014-12-10 下午5:32:37
 */
public class MarqueeUtil extends DALFactory {

	public static int sysMarqueeIntevalTime = 30; // 系统跑马灯消息发送间隔时间，单位-秒

	public static int sysMarqueeMsgNum = 1;// 系统跑马灯发送消息条数

	public static int openSysMarquee = 1;// 开启系统跑马灯-默认为开启，非1为关闭系统跑马灯

	public static int timeRecord = 0;

	public static String quartzMarqueeContent = "《斗破苍穹》官方微信、Q群火爆开启！ 内容：1、官方Q群为：339456979。2、官方微信为：longzhu_2014";

	public static BlockingDeque<String> blockingDeque = new LinkedBlockingDeque<String>();

	/**
	 * 程序启动时,跑马灯操作
	 * 
	 * @author mp
	 * @date 2014-12-11 下午2:17:47
	 * @Description
	 */
	public static void marquee() throws Exception {
		initMarquee();// 去掉过期的,激活可用的
		startTakeThread();// 隔多久向玩家推送一次数据
		// startPutThread();//每隔多久放入一条公告
	}

	/**
	 * 初始化跑马灯
	 * 
	 * @author mp
	 * @date 2014-12-11 上午11:26:43
	 * @Description
	 */
	public static void initMarquee() throws Exception {

		List<SysMarquee> sysMarqueeList = getSysMarqueeDAL().getList("", 0);

		for (SysMarquee obj : sysMarqueeList) {
			long currTimeMill = DateUtil.getCurrMill();
			long endTimeMill = DateUtil.getMillSecond(obj.getEndTime());

			// 激活未过期的
			if (currTimeMill < endTimeMill) {

				// 开启线程
				MarqueeThread marqueeThread = new MarqueeThread(obj.getContent(), obj.getEndTime(), obj.getInteval(), obj.getStartTime(), obj.getId());

				// 将线程放入线程map加以管理
				SysMarqueeMapUtil.add(obj.getId(), marqueeThread);

				// 启动此线程
				marqueeThread.start();

				// 删除已过期的
			} else {
				getSysMarqueeDAL().deleteById(obj.getId(), 0);
			}
		}
	}

	/**
	 * 启动跑马灯取数据线程
	 * 
	 * @author mp
	 * @date 2013-12-20 下午5:17:36
	 * @Description
	 */
	public static void startTakeThread() {
		new TakeThread().start();
		System.out.println("取数据线程启动成功");
	}

	/**
	 * 启动跑马灯放数据线程
	 * 
	 * @author mp
	 * @date 2013-12-20 下午5:18:17
	 * @Description
	 */
	public static void startPutThread() {
		new PutThread().start();
		System.out.println("放数据线程启动成功");
	}

	/**
	 * 启动gm同步线程
	 * 
	 * @author mp
	 * @date 2014-1-5 下午2:55:32
	 * @Description
	 */
	public static void startGmSyncThread() {
		// new GmSyncThread().start();
		System.out.println("GM同步线程启动成功");
	}

	/**
	 * 添加自定义跑马灯
	 * 
	 * @author mp
	 * @date 2014-12-10 下午5:28:35
	 * @param content
	 * @param endTime
	 * @param startTime
	 * @param inteval
	 * @throws Exception
	 * @Description
	 */
	public static void addUserMarquee(String content, String endTime, int inteval, String startTime) throws Exception {

		// 加入数据库
		SysMarquee sysMarquee = addSysMarquee(content, endTime, inteval, startTime);

		// 开启一个跑马灯线程
		MarqueeThread marqueeThread = new MarqueeThread(content, endTime, inteval, startTime, sysMarquee.getId());

		// 将线程放入线程map加以管理
		SysMarqueeMapUtil.add(sysMarquee.getId(), marqueeThread);

		// 启动此线程
		marqueeThread.start();
	}

	/**
	 * 更新自定义跑马灯
	 * 
	 * @author mp
	 * @date 2014-12-11 上午10:22:24
	 * @param id
	 * @param content
	 * @param endTime
	 * @param inteval
	 * @param startTime
	 * @Description
	 */
	@SuppressWarnings("deprecation")
	public static void updateUserMarquee(int id, String content, String endTime, int inteval, String startTime) throws Exception {

		SysMarquee sysMarquee = getSysMarqueeDAL().getModel(id, 0);
		MarqueeThread oldMarqueeThread = SysMarqueeMapUtil.getMarqueeThreadById(id);

		// 如果内容发生变化,删除原有双端队列中的所有旧消息
		if (!sysMarquee.getContent().equals(content)) {
			List<String> rmList = Lists.newArrayList();
			rmList.add(sysMarquee.getContent());
			MarqueeUtil.blockingDeque.removeAll(rmList);
		}

		// 强制终止原有线程[防止线程正在休眠呢，假设休眠时间很长,不能及时起作用]
		oldMarqueeThread.stop();
		SysMarqueeMapUtil.removeById(id);

		// 创建新线程
		MarqueeThread newMarqueeThread = new MarqueeThread(content, endTime, inteval, startTime, sysMarquee.getId());
		SysMarqueeMapUtil.add(id, newMarqueeThread);
		newMarqueeThread.start();

		// 修改数据库
		sysMarquee.setContent(content);
		sysMarquee.setEndTime(endTime);
		sysMarquee.setStartTime(startTime);
		sysMarquee.setInteval(inteval);
		getSysMarqueeDAL().update(sysMarquee, 0);
	}

	/**
	 * 删除指定自定义跑马灯
	 * 
	 * @author mp
	 * @date 2014-12-11 上午10:03:26
	 * @param id
	 * @throws Exception
	 * @Description
	 */
	@SuppressWarnings("deprecation")
	public static void delUserMarquee(int id) throws Exception {

		MarqueeThread marqueeThread = SysMarqueeMapUtil.getMarqueeThreadById(id);
		SysMarquee sysMarquee = getSysMarqueeDAL().getModel(id, 0);

		if (marqueeThread != null) {

			// 删除队列中的相关数据
			List<String> rmList = Lists.newArrayList();
			rmList.add(sysMarquee.getContent());
			MarqueeUtil.blockingDeque.removeAll(rmList);

			// 强制终止线程
			marqueeThread.stop();

			// 删除map中的此线程
			SysMarqueeMapUtil.removeById(id);

			// 在数据库中删除
			getSysMarqueeDAL().deleteById(id, 0);
		}
	}

	/**
	 * 将消息放入跑马灯
	 * 
	 * @author mp
	 * @date 2014-1-15 下午8:32:29
	 * @param channelId
	 * @param dictId
	 * @param type
	 * @param source
	 * @Description
	 */
	public static void putMsgToMarquee(String channelId, String dictId, int type, int source) {

		try {

			String content = "";
			Player player = PlayerMapUtil.getPlayerByChannelId(channelId);

			if (type == CustomMarqueeType.MARQUEE_TYPE_CARD) {

				// 获取卡牌名称
				String cardName = "";
				if (dictId.contains(";")) {
					String cardIds[] = dictId.split(";");
					for (String cardId : cardIds) {
						DictCard dictCard = DictMap.dictCardMap.get(cardId);
						if (dictCard != null) {
							if (dictCard.getQualityId() == StaticQuality.purple) {
								cardName = cardName + dictCard.getName() + ",";
							}
						}
					}
				} else {
					DictCard dictCard = DictMap.dictCardMap.get(dictId);
					if (dictCard != null) {
						if (dictCard.getQualityId() == StaticQuality.purple) {
							cardName = dictCard.getName();
						}
					}
				}

				// 根据来源,显示不同提示
				if (source == CustomMarqueeType.MARQUEE_SOURCE_RECRUIT) {
					if (!cardName.equals("")) {
						content = "恭喜" + player.getPlayerName() + "在商城招募中,招募  [" + cardName + "] 成为自己的队员";
					}
				} else if (source == CustomMarqueeType.MARQUEE_SOURCE_RECRUITTEN) {
					if (!cardName.equals("")) {
						content = "恭喜" + player.getPlayerName() + "在商城钻石招募十次中,招募  [" + StringUtil.noContainLastString(cardName) + "] 成为自己的队员";
					}
				}

			} else if (type == CustomMarqueeType.MARQUEE_TYPE_EQUIP) {

				String equipName = "";
				if (source == CustomMarqueeType.MARQUEE_SOURCE_OPENBOX) {
					// [格式为1_1;2_2; 下划线前面的数为1或2两个值, 等于1 表示去Dict_Gener_BoxThing表查, 等于2 表示去Dict_Special_BoxThing表查； 下划线后边的数为表的id；字符串最后不包含分号]
					String things[] = dictId.split(";");
					for (String thingType : things) {

						int tableTypeId = 0;
						int tableFieldId = 0;

						String whichTable = thingType.split("_")[0];
						String tableId = thingType.split("_")[1];
						if (whichTable.equals("1")) {
							DictGenerBoxThing dictGenerBoxThing = DictMap.dictGenerBoxThingMap.get(tableId);
							tableTypeId = dictGenerBoxThing.getTableTypeId();
							tableFieldId = dictGenerBoxThing.getTableFieldId();
						} else if (whichTable.equals("2")) {
							DictSpecialBoxThing dictSpecialBoxThing = DictMap.dictSpecialBoxThingMap.get(tableId);
							tableTypeId = dictSpecialBoxThing.getTableTypeId();
							tableFieldId = dictSpecialBoxThing.getTableFieldId();
						}
						if (tableTypeId == StaticTableType.DictEquipment) {
							DictEquipment dictEquipment = DictMap.dictEquipmentMap.get(tableFieldId + "");
							if (dictEquipment != null) {
								if (dictEquipment.getEquipQualityId() == StaticEquipQuality.purple) {
									equipName += dictEquipment.getName() + ",";
								}
							}
						}
					}

					if (!equipName.equals("")) {
						content = player.getPlayerName() + "在开宝箱中意外获得橙色装备  [" + StringUtil.noContainLastString(equipName) + "] ,真是太幸运了!";
					}
				}
			} else if (type == CustomMarqueeType.MARQUEE_TYPE_MAGIC) {
				if (source == CustomMarqueeType.MARQUEE_SOURCE_CHIPJOIN) {
					DictMagic dictMagic = DictMap.dictMagicMap.get(dictId);
					if (dictMagic != null) {
						if (dictMagic.getMagicQualityId() == StaticMagicQuality.TJ) {
							// 类型 1-法宝 2-功法
							if (dictMagic.getType() == 1) {
								content = player.getPlayerName() + "在碎片拼合中,获得天阶法宝  [" + dictMagic.getName() + "] ,好棒呀!";
							} else if (dictMagic.getType() == 2) {
								content = player.getPlayerName() + "在碎片拼合中,获得天阶功法  [" + dictMagic.getName() + "] ,好棒呀!";
							}
						}
					}
				}
			} else if (type == CustomMarqueeType.MARQUEE_TYPE_OTHER) {
				if (source == CustomMarqueeType.MARQUEE_SOURCE_BREAK) {
					DictTitleDetail dictTitleDetail = DictMap.dictTitleDetailMap.get(dictId.split(";")[0]);
					DictCard dictCard = DictMap.dictCardMap.get(dictId.split(";")[1]);
					if (dictTitleDetail != null) {
						DictTitle dictTitle = DictMap.dictTitleMap.get(dictTitleDetail.getTitleId() + "");
						if (dictTitle != null && dictTitle.getId() != 1) {
							content = player.getPlayerName() + "在境界突破中,将 " + dictCard.getName() + " 突破到  [" + dictTitle.getName() + "] 级别, 真不简单!";
						}
					}
				} else if (source == CustomMarqueeType.MARQUEE_SOURCE_ADWANCE) {
					String cardId = dictId.split(";")[0];
					String beforeQualityId = (dictId.split(";")[1]).split("_")[0];
					String beforeStarLevelId = (dictId.split(";")[1]).split("_")[1];
					String afterQualityId = (dictId.split(";")[2]).split("_")[0];
					String aflterStarLevelId = (dictId.split(";")[2]).split("_")[1];

					DictCard dictCard = DictMap.dictCardMap.get(cardId);
					DictQuality beforeQuality = DictMap.dictQualityMap.get(beforeQualityId);
					DictStarLevel beforeStarLevel = DictMap.dictStarLevelMap.get(beforeStarLevelId);
					DictQuality afterQuality = DictMap.dictQualityMap.get(afterQualityId);
					DictStarLevel afterStarLevel = DictMap.dictStarLevelMap.get(aflterStarLevelId);

					content = "恭喜" + player.getPlayerName() + "将" + dictCard.getName() + "由 " + beforeQuality.getName() + beforeStarLevel.getName() + " 进阶成[" + afterQuality.getName() + afterStarLevel.getName() + "]";

				} else if (source == CustomMarqueeType.MARQUEE_SOURCE_ARENA) {
					int beforeRank = ConvertUtil.toInt(dictId.split(";")[0]);
					int afterRank = ConvertUtil.toInt(dictId.split(";")[1]);
					if (afterRank < beforeRank && afterRank <= 100) {
						content = "恭喜" + player.getPlayerName() + "在竞技场中, 由第" + beforeRank + "名提升到第[" + afterRank + "]名";
					}
				} else if (source == CustomMarqueeType.MARQUEE_SOURCE_ZHUANPAN) {
					content = "恭喜" + player.getPlayerName() + "在幸运轮盘中抽到限定物品" + dictId;
				}
			}

			if (!content.equals("")) {
				if (openSysMarquee == 1) {
					if (blockingDeque.size() < 1000) {
						blockingDeque.putLast(content);
					}
				}
			}

		} catch (Exception e) {
			LogUtil.error(e);
			e.printStackTrace();
		}
	}

	/**
	 * 定制内容后直接需要发跑马灯（因为有可能是离线玩家获取奖励时也需要发）
	 * @author 	cui
	 * @date 	15/10/22
	 * @param content
	 */
	public static void putMsgToMarquee(String content) {
		try{
			if (!content.equals("")) {
				if (openSysMarquee == 1) {
					if (blockingDeque.size() < 1000) {
						blockingDeque.putLast(content);
					}
				}
			}
		}catch (Exception e) {
				LogUtil.error(e);
				e.printStackTrace();
		}
	}

		/**
         * 添加自定义跑马灯
         *
         * @author mp
         * @date 2014-12-10 下午5:36:51
         * @param content
         * @param endTime
         * @param inteval
         * @param startTime
         * @return
         * @throws Exception
         * @Description
         */
	public static SysMarquee addSysMarquee(String content, String endTime, int inteval, String startTime) throws Exception {
		SysMarquee sysMarquee = new SysMarquee();
		sysMarquee.setContent(content);
		sysMarquee.setEndTime(endTime);
		sysMarquee.setInteval(inteval);
		sysMarquee.setStartTime(startTime);
		return getSysMarqueeDAL().add(sysMarquee, 0);
	}

}
