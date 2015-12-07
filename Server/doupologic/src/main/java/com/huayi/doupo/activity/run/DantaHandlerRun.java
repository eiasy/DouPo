package com.huayi.doupo.activity.run;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.huayi.doupo.activity.ThreadManager;
import com.huayi.doupo.activity.danta.DantaManager;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictDantaLayer;
import com.huayi.doupo.base.model.DictFunctionOpen;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstRankDanta;
import com.huayi.doupo.base.model.InstRankDantaDay;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;

public class DantaHandlerRun implements IActRunnable {
	private Map<String, Object> msgMap;
	private String channelId;

	public DantaHandlerRun(Map<String, Object> msgMap, String channelId) {
		super();
		this.msgMap = msgMap;
		this.channelId = channelId;
	}

	@Override
	public void run() {
		int instPlayerId = BaseHandler.getInstPlayerId(channelId);
		InstPlayer instPlayer = BaseHandler.getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
		// 验证是否达到开放等级
		DictFunctionOpen dictFunctionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.danta + "");
		if (instPlayer.getLevel() < dictFunctionOpen.getLevel()) {
			MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_levelNotUp);
			return;
		}
		/**
		 * String feature = null; int hashcode = 0; DantaPlayer dp = null; try { feature = (String) msgMap.get("p1"); hashcode = (int) msgMap.get("p7"); dp = DantaManager.getInstance().getDantaPlayer(instPlayerId); if (!dp.check(feature, hashcode)) { MessageUtil.sendFailMsg(channelId, msgMap, "参数异常"); return; } } catch (Exception e) { MessageUtil.sendFailMsg(channelId, msgMap, "参数异常"); return; }
		 */
		int type = 0;
		try {
			type = Integer.parseInt(msgMap.get("p2").toString());
			switch (type) {
				case 1: {// 打开丹塔界面
					openUI(type, instPlayer);
					break;
				}
				case 2: {// 打开历史排行
					rank(type, instPlayer);
					break;
				}
				case 3: {// 打开今日排行
					rankDay(type, instPlayer);
					break;
				}
				case 4: {// 重置丹塔
					reset(type, instPlayer);
					break;
				}
				case 5: {// 进入副本
					enter(type, instPlayer);
					break;
				}
				case 6: {// 副本结束
					out(type, instPlayer);
					break;
				}
				case 7: {// 检查关卡奖励是否领取
					checkLayerAward(type, instPlayer);
					break;
				}
				case 8: {// 获取当日最高层
					getDayMaxLayer(type, instPlayer);
					break;
				}
				case 9: {// 扫荡
					sweep(type, instPlayer);
					break;
				}
				default: {
					MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[P2]");
				}
			}
		} catch (Exception e) {
			LogUtil.error("丹塔操作异常#" + type, e);
			MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[P2]");
		}
	}

	private void sweep(int type, InstPlayer instPlayer) {
		try {
			InstRankDanta rd = DantaManager.getInstance().getInstRankDanta(instPlayer);
			int openLayer = DictMapUtil.getSysConfigIntValue(StaticSysConfig.dantaOpenNum);
			if (rd.getMaxLayer() < openLayer) {
				MessageUtil.sendFailMsg(channelId, msgMap, "最高层数达到" + openLayer + "层开启扫荡功能");
				return;
			}
			InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
			if (rdd.getFinishLayer() > 0) {
				MessageUtil.sendFailMsg(channelId, msgMap, "扫荡功能只能在挑战第一层之前使用");
				return;
			}
			rd.setStartCount(rd.getStartCount() + 1);
			rdd.setStartCount(rdd.getStartCount() + 1);
			MessageData retMsgData = new MessageData();
			// <type_id,count>
			Map<String, Integer> typeCounts = new TreeMap<String, Integer>();
			int medal = DictMapUtil.getSysConfigIntValue(StaticSysConfig.dantaGiveStar);
			Map<Integer, Integer> layerInfo = new HashMap<Integer, Integer>();
			try {
				String[] arrayLayer_1 = StringUtil.split(rdd.getLayerInfo(), ";");
				if (arrayLayer_1 != null) {
					for (int ai = 0; ai < arrayLayer_1.length; ai++) {
						int[] arrayLayerCount = StringUtil.string2IntArray(arrayLayer_1[ai], '_');
						if (arrayLayerCount != null && arrayLayerCount.length > 1) {
							layerInfo.put(arrayLayerCount[0], arrayLayerCount[1]);
						}
					}
				}
			} catch (Exception e) {

			}
			int layerId = 0;
			MessageData syncMsgData = new MessageData();
			int offset = DictMapUtil.getSysConfigIntValue(StaticSysConfig.dantaFloorNum);
			if (offset < 1) {
				offset = 10;
			}
			int targetLayer = rd.getMaxLayer() - (rd.getMaxLayer() - openLayer) % offset;
			DantaManager.getInstance().checkRank(instPlayer, targetLayer, medal);
			Map<String, String> thingMap = new HashMap<String, String>();
			for (int li = 0; li < targetLayer; li++) {
				layerId = li + 1;
				rd.setFinishCount(rd.getFinishCount() + 1);
				rdd.setFinishLayer(rdd.getFinishLayer() + 1);
				DictDantaLayer dantaLayer = DictMap.dictDantaLayerMap.get(layerId + "");
				if (dantaLayer == null) {
					continue;
				}
				Integer historyCount = layerInfo.get(layerId);
				if (historyCount == null) {
					historyCount = 0;
				}
				if (historyCount < DictMapUtil.getSysConfigIntValue(StaticSysConfig.DantaRewardNum)) {
					layerInfo.put(rdd.getFinishLayer(), historyCount + 1);
					String[] array_1 = StringUtil.split(dantaLayer.getLayerAwards(), "#");
					if (array_1 != null && array_1.length > 0) {
						for (int a1 = 0; a1 < array_1.length; a1++) {
							String[] array_2 = StringUtil.split(array_1[a1], ";");
							if (array_2 != null && array_2.length > 0) {
								for (int a2 = 0; a2 < array_2.length; a2++) {
									String[] array_3 = StringUtil.split(array_2[a2], "_");
									String key = array_3[0] + "_" + array_3[1];
									Integer value = typeCounts.get(key);
									if (value == null) {
										value = ConvertUtil.toInt(array_3[2]);
									} else {
										value += ConvertUtil.toInt(array_3[2]);
									}
									typeCounts.put(key, value);
									
									ThingUtil.groupThingMap(thingMap, ConvertUtil.toInt(array_3[0]), ConvertUtil.toInt(array_3[1]), ConvertUtil.toInt(array_3[2]));
									
//									ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(array_3[0]), ConvertUtil.toInt(array_3[1]), ConvertUtil.toInt(array_3[2]), syncMsgData, msgMap);
								}
							}
						}
					}
				}
			}
			// 发送物品
			ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);
			MessageUtil.sendSyncMsg(channelId, syncMsgData);
			StringBuilder sb = new StringBuilder();
			Set<String> keysType = typeCounts.keySet();
			int awardIndex = 0;
			for (String k : keysType) {
				if (awardIndex++ > 0) {
					sb.append(";");
				}
				sb.append(k).append("_").append(typeCounts.get(k));
			}
			retMsgData.putStringItem("r3", sb.toString());

			sb.setLength(0);
			Set<Integer> keys = layerInfo.keySet();
			int index = 0;
			for (int k : keys) {
				if (index++ > 0) {
					sb.append(';');
				}
				sb.append(k).append('_').append(layerInfo.get(k));
			}
			rdd.setLayerInfo(sb.toString());

			ThreadManager.accessDatabase(new UpdateRankDantaDayRun(rdd));
			ThreadManager.accessDatabase(new UpdateRankDantaRun(rd));
			retMsgData.putIntItem("r1", 0);
			retMsgData.putStringItem("r2", "");
			retMsgData.putIntItem("r4", targetLayer);
			retMsgData.putIntItem("r5", medal);
			MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
		} catch (Exception e) {
			MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[5]");
			LogUtil.error("扫荡丹塔副本异常", e);
			return;
		}
	}

	private void getDayMaxLayer(int type, InstPlayer instPlayer) {
		InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("r1", rdd.getMaxLayer());
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	private void checkLayerAward(int type, InstPlayer instPlayer) {
		InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
		int layer = (int) msgMap.get("p3");
		MessageData retMsgData = new MessageData();
		if (layer > 0 && layer - rdd.getFinishLayer() == 1) {
			Map<Integer, Integer> layerInfo = new HashMap<Integer, Integer>();
			try {
				String[] arrayLayer_1 = StringUtil.split(rdd.getLayerInfo(), ";");
				if (arrayLayer_1 != null) {
					for (int ai = 0; ai < arrayLayer_1.length; ai++) {
						int[] arrayLayerCount = StringUtil.string2IntArray(arrayLayer_1[ai], '_');
						if (arrayLayerCount != null && arrayLayerCount.length > 1) {
							layerInfo.put(arrayLayerCount[0], arrayLayerCount[1]);
						}
					}
				}
			} catch (Exception e) {

			}
			Integer historyCount = layerInfo.get(layer);
			if (historyCount == null) {
				historyCount = 0;
			}

			if (historyCount < DictMapUtil.getSysConfigIntValue(StaticSysConfig.DantaRewardNum)) {
				retMsgData.putIntItem("r1", 0);
			} else {
				retMsgData.putIntItem("r1", 1);
			}
			retMsgData.putIntItem("r2", 0);
			retMsgData.putStringItem("r3", "");
		} else {
			retMsgData.putIntItem("r2", 1);
			if (rdd.getMaxLayer() < 1) {
				retMsgData.putStringItem("r3", "今日丹塔结算时间已到，请点击“确定”开始新一天的挑战");
			} else {
				retMsgData.putStringItem("r3", "丹塔活动暂时退出就会被重置，敬请期待版本更新");
			}
		}

		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	private void out(int type, InstPlayer instPlayer) {
		try {
			InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
			MessageData retMsgData = new MessageData();
			if (rdd.getStartLayer() > 0 && rdd.getStartLayer() - rdd.getFinishLayer() == 1) {
				DictDantaLayer dantaLayer = DictMap.dictDantaLayerMap.get(rdd.getStartLayer() + "");
				if (dantaLayer == null) {
					MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[1]");
					return;
				}

				InstRankDanta rd = DantaManager.getInstance().getInstRankDanta(instPlayer);
				rd.setUpdateTime(DateUtil.getCurrTime());
				rd.setPlayerLevel(instPlayer.getLevel());

				rdd.setUpdateTime(DateUtil.getCurrTime());
				rdd.setPlayerLevel(instPlayer.getLevel());
				int result = (int) msgMap.get("p5");
				int medalCount = (int) msgMap.get("p6");
//				String coredata = (String) msgMap.get("coredata");
//				// 用于验证玩家是否利用烧饼等修改器
//				if (VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)) {
//					return;
//				}
				if (result == 1) {// 成功
					Map<String, String> thingMap = new HashMap<String, String>();
					rd.setFinishCount(rd.getFinishCount() + 1);
					rdd.setFinishLayer(rdd.getFinishLayer() + 1);
					DantaManager.getInstance().checkRank(instPlayer, rdd.getFinishLayer(), medalCount);
					Map<Integer, Integer> layerInfo = new HashMap<Integer, Integer>();
					try {
						String[] arrayLayer_1 = StringUtil.split(rdd.getLayerInfo(), ";");
						if (arrayLayer_1 != null) {
							for (int ai = 0; ai < arrayLayer_1.length; ai++) {
								int[] arrayLayerCount = StringUtil.string2IntArray(arrayLayer_1[ai], '_');
								if (arrayLayerCount != null && arrayLayerCount.length > 1) {
									layerInfo.put(arrayLayerCount[0], arrayLayerCount[1]);
								}
							}
						}
					} catch (Exception e) {

					}
					Integer historyCount = layerInfo.get(rdd.getFinishLayer());
					if (historyCount == null) {
						historyCount = 0;
					}
					if (historyCount < DictMapUtil.getSysConfigIntValue(StaticSysConfig.DantaRewardNum)) {
						layerInfo.put(rdd.getFinishLayer(), historyCount + 1);
						Set<Integer> keys = layerInfo.keySet();
						StringBuilder sb = new StringBuilder();
						int index = 0;
						for (int k : keys) {
							if (index++ > 0) {
								sb.append(';');
							}
							sb.append(k).append('_').append(layerInfo.get(k));
						}
						rdd.setLayerInfo(sb.toString());
						String[] array_1 = StringUtil.split(dantaLayer.getLayerAwards(), "#");
						if (array_1 != null && array_1.length > 0) {
							for (int a1 = 0; a1 < array_1.length; a1++) {
								String[] array_2 = StringUtil.split(array_1[a1], ";");
								if (array_2 != null && array_2.length > 0) {
									for (int a2 = 0; a2 < array_2.length; a2++) {
										String[] array_3 = StringUtil.split(array_2[a2], "_");
//										ThingUtil.groupThing(instPlayer, ConvertUtil.toInt(array_3[0]), ConvertUtil.toInt(array_3[1]), ConvertUtil.toInt(array_3[2]), retMsgData, msgMap);
										ThingUtil.groupThingMap(thingMap, ConvertUtil.toInt(array_3[0]), ConvertUtil.toInt(array_3[1]), ConvertUtil.toInt(array_3[2]));
									}
								}
							}
						}
						MessageData syncMsgData = new MessageData();
						ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);
						MessageUtil.sendSyncMsg(channelId, syncMsgData);
						
						retMsgData.putStringItem("r4", dantaLayer.getLayerAwards());
					} else {
						retMsgData.putStringItem("r4", "");
					}
				} else {// 失败
					rdd.setStartLayer(0);
					rdd.setFinishLayer(0);
				}
				ThreadManager.accessDatabase(new UpdateRankDantaDayRun(rdd));
				ThreadManager.accessDatabase(new UpdateRankDantaRun(rd));
				retMsgData.putIntItem("r2", 0);
				retMsgData.putStringItem("r3", "");
				MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
			} else {
				retMsgData.putIntItem("r2", 1);
				if (rdd.getMaxLayer() < 1) {
					retMsgData.putStringItem("r3", "今日丹塔结算时间已到，请点击“确定”开始新一天的挑战");
				} else {
					retMsgData.putStringItem("r3", "丹塔活动暂时退出就会被重置，敬请期待版本更新");
				}
				MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
			}
		} catch (Exception e) {
			MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[3]");
			LogUtil.error("离开丹塔副本异常", e);
		}
	}

	private void enter(int type, InstPlayer instPlayer) {
		int enterLayer = 0;
		try {

			enterLayer = (int) msgMap.get("p4");
			if (enterLayer < 1) {
				MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[1]");
				return;
			}
			if (DictMap.dictDantaLayerMap.get(enterLayer + "") == null) {
				MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[2]");
				return;
			}

			InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
			if (rdd.getStartLayer() == enterLayer) {
				MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[3]");
				return;
			}
			MessageData msg = new MessageData();
			if (enterLayer - rdd.getFinishLayer() == 1) {
				InstRankDanta rd = DantaManager.getInstance().getInstRankDanta(instPlayer);
				rd.setStartCount(rd.getStartCount() + 1);

				if (enterLayer == 1) {// 新开一轮
					if (rdd.getStartCount() == DictMapUtil.getSysConfigIntValue(StaticSysConfig.DanTaNum)) {
						MessageUtil.sendFailMsg(channelId, msgMap, "今日参与次数已达到上限");
						return;
					}
					rdd.setStartCount(rdd.getStartCount() + 1);
				}
				rdd.setStartLayer(enterLayer);

				List<InstUnionMember> instUnionMemberList = DALFactory.getInstUnionMemberDAL().getList("instPlayerId= " + rdd.getPlayerId(), 0);
				if (instUnionMemberList != null && instUnionMemberList.size() > 0) {
					rdd.setInstUnionId(instUnionMemberList.get(0).getInstUnionId());
					rd.setInstUnionId(instUnionMemberList.get(0).getInstUnionId());
				} else {
					rdd.setInstUnionId(0);
					rd.setInstUnionId(0);
				}
				rd.setHeaderCardId(instPlayer.getHeaderCardId());
				rdd.setHeaderCardId(instPlayer.getHeaderCardId());

				msg.putIntItem("r1", 0);
				msg.putStringItem("r2", "");
				MessageUtil.sendSuccMsg(channelId, msgMap, msg);
				BaseHandler.getInstRankDantaDayDAL().update(rdd, 0);
				BaseHandler.getInstRankDantaDAL().update(rd, 0);
			} else {
				msg.putIntItem("r1", 1);
				if (rdd.getMaxLayer() < 1) {
					msg.putStringItem("r2", "今日丹塔结算时间已到，请点击“确定”开始新一天的挑战");
				} else {
					msg.putStringItem("r2", "丹塔活动暂时退出就会被重置，敬请期待版本更新");
				}
				MessageUtil.sendSuccMsg(channelId, msgMap, msg);
			}
		} catch (Exception e) {
			MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[5]");
			LogUtil.error("进入丹塔副本异常", e);
			return;
		}
	}

	private void reset(int type, InstPlayer instPlayer) {
		try {
			InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
			rdd.setStartLayer(0);
			rdd.setFinishLayer(0);
			rdd.setUpdateTime(DateUtil.getCurrTime());
			BaseHandler.getInstRankDantaDayDAL().update(rdd, 0);
			MessageUtil.sendSuccMsg(channelId, msgMap, new MessageData());
		} catch (Exception e) {
			MessageUtil.sendFailMsg(channelId, msgMap, "参数错误[3]");
			LogUtil.error("离开丹塔副本异常", e);
		}
	}

	private void rankDay(int type, InstPlayer instPlayer) {
		InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("r0", type);
		retMsgData.putStringItem("r1", DantaManager.getInstance().getRankDayPage((int) msgMap.get("p3")));
		retMsgData.putIntItem("r2", rdd.getRankIndex() + 1);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	private void rank(int type, InstPlayer instPlayer) {
		InstRankDanta rd = DantaManager.getInstance().getInstRankDanta(instPlayer);
		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("r0", type);
		retMsgData.putStringItem("r1", DantaManager.getInstance().getRankPage((int) msgMap.get("p3")));
		retMsgData.putIntItem("r2", rd.getRankIndex() + 1);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

	private void openUI(int type, InstPlayer instPlayer) {
		InstRankDanta rd = DantaManager.getInstance().getInstRankDanta(instPlayer);
		InstRankDantaDay rdd = DantaManager.getInstance().getInstRankDantaDay(instPlayer);
		int remainCount = DictMapUtil.getSysConfigIntValue(StaticSysConfig.DanTaNum) - rdd.getStartCount();
		if (remainCount < 0) {
			remainCount = 0;
		}
		int awardMaxLayer = 0;
		String[] arrayLayer_1 = StringUtil.split(rdd.getLayerInfo(), ";");
		if (arrayLayer_1 != null) {
			for (int ai = 0; ai < arrayLayer_1.length; ai++) {
				int[] arrayLayerCount = StringUtil.string2IntArray(arrayLayer_1[ai], '_');
				if (arrayLayerCount != null && arrayLayerCount.length > 1) {
					if (arrayLayerCount[0] > awardMaxLayer) {
						awardMaxLayer = arrayLayerCount[0];
					}
				}
			}
		}

		MessageData retMsgData = new MessageData();
		retMsgData.putIntItem("r0", type);
		retMsgData.putIntItem("r1", awardMaxLayer);
		retMsgData.putIntItem("r2", rd.getMaxLayer());
		retMsgData.putIntItem("r3", remainCount);
		MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
	}

}
