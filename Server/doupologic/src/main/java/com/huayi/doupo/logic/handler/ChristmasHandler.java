package com.huayi.doupo.logic.handler;

import com.huayi.doupo.base.model.DictGenerBoxThing;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerChris;
import com.huayi.doupo.base.model.SysActivity;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.StringUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Coolcow on 2015/12/18 0018.
 */
public class ChristmasHandler extends BaseHandler {

    /**
     * 圣诞活动
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 2015/12/18
     * @Description
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void christmasDay(HashMap<String, Object> msgMap, String channelId) throws Exception {

        // 获取参数
        int instPlayerId = getInstPlayerId(channelId);

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        int type = (Integer) msgMap.get("type");//圣诞活动 功能类型  (0.进入活动界面  1.开启拼手气礼盒  2.开启圣诞礼盒)

        if (type < 0 || type > 2) {
            MessageUtil.sendFailMsg(channelId, msgMap, "非法数据-1");
            return;
        }

        //判断当前时间是否
        SysActivity activity = getSysActivityDAL().getModel(110, 0);  //获取活动时间
        String startTime = activity.getStartTime();
        String endTime = activity.getEndTime();
        if (DateUtil.getCurrMill() < DateUtil.getMillSecond(startTime)) {
            MessageUtil.sendFailMsg(channelId, msgMap, "活动未开启");
            return;
        }

        if (DateUtil.getCurrMill() > DateUtil.getMillSecond(endTime)) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_activityOver);
            return;
        }


        List<InstPlayerChris> playerChrises = getInstPlayerChrisDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
        InstPlayerChris instPlayerChris = null;
        if (playerChrises != null && playerChrises.size() > 0) {
            instPlayerChris = playerChrises.get(0);
            //判断这条数据是否是活动时间内的有效数据
            long insertTime = DateUtil.getMillSecond(instPlayerChris.getInsertTime());
            if(insertTime > DateUtil.getMillSecond(endTime) || insertTime < DateUtil.getMillSecond(startTime)){
                getInstPlayerChrisDAL().deleteById(instPlayerChris.getId(), instPlayerId);
                instPlayerChris = null;
                playerChrises = null;
            }
        }
        String currDay = DateUtil.getYmdDate();
        MessageData syncMsgData = new MessageData();
        MessageData retMsgData = new MessageData();

        int firstCount = 2;
        int secondCount = 1;
        String openedThingId = null;

        if (instPlayerChris != null) {
            if (currDay.equals(instPlayerChris.getFirstTime())) {
                firstCount -= instPlayerChris.getFirstCount();
                firstCount = (firstCount < 0) ? 0 : firstCount;
            }else{
                //重置领取次数
                instPlayerChris.setFirstCount(0);
            }

            long currSecondTime = DateUtil.getMillSecond(instPlayerChris.getSecondTime() + " 00:00:00");
            if (currSecondTime >= DateUtil.getMillSecond(startTime) && currSecondTime <= DateUtil.getMillSecond(endTime)) {
                secondCount -= instPlayerChris.getSecondCount();
                secondCount = (secondCount < 0) ? 0 : secondCount;
            }else{
                //重置领取次数
                instPlayerChris.setSecondCount(0);
            }
        }
        if (type == 0) {
            retMsgData.putIntItem("first", firstCount);
            retMsgData.putIntItem("second", secondCount);

            MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);
            return;

        } else {
            if (type == 1) {
                firstCount -= 1;
                if (instPlayerChris != null) {
//                    if (currDay.equals(instPlayerChris.getFirstTime())) {
//                        firstCount -= instPlayerChris.getFirstCount();
//                    }
                    //次数已经用尽
                    if (firstCount < 0) {
                        MessageUtil.sendFailMsg(channelId, msgMap, "今日礼盒已经领取过");
                        return;
                    }
                    instPlayerChris.setFirstTime(currDay);
                    instPlayerChris.setFirstCount(instPlayerChris.getFirstCount() + 1);
                    getInstPlayerChrisDAL().update(instPlayerChris, instPlayerId);
                } else {
                    instPlayerChris = new InstPlayerChris();
                    instPlayerChris.setInstPlayerId(instPlayerId);
                    instPlayerChris.setFirstTime(currDay);
                    instPlayerChris.setFirstCount(1);
                    instPlayerChris.setSecondTime(currDay);
                    instPlayerChris.setSecondCount(0);
                    getInstPlayerChrisDAL().add(instPlayerChris, instPlayerId);
                }
                openedThingId = ThingUtil.openGenerThingId(1025);  //礼包物品ID
            } else {
                secondCount -= 1;
                if(instPlayerChris != null){
//                    long currSecondTime = DateUtil.getMillSecond(instPlayerChris.getSecondTime() + " 00:00:00");
//                    if (currSecondTime >= DateUtil.getMillSecond(startTime) && currSecondTime <= DateUtil.getMillSecond(endTime)) {
//                        secondCount -= instPlayerChris.getSecondCount();
//                    }

                    //次数已经用尽
                    if(secondCount < 0){
                        MessageUtil.sendFailMsg(channelId, msgMap, "活动礼盒已经领取过");
                        return;
                    }
                    instPlayerChris.setSecondTime(currDay);
                    instPlayerChris.setSecondCount(instPlayerChris.getSecondCount() + 1);
                    getInstPlayerChrisDAL().update(instPlayerChris,instPlayerId);
                }else{
                    instPlayerChris = new InstPlayerChris();
                    instPlayerChris.setInstPlayerId(instPlayerId);
                    instPlayerChris.setFirstTime(currDay);
                    instPlayerChris.setFirstCount(0);
                    instPlayerChris.setSecondTime(currDay);
                    instPlayerChris.setSecondCount(1);
                    getInstPlayerChrisDAL().add(instPlayerChris,instPlayerId);
                }
                openedThingId = ThingUtil.openGenerThingId(1024);  //礼包物品ID
            }
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);

        StringBuilder  builder = new StringBuilder();
        Map<String, String> thingMap = new HashMap<String, String>();
        // 添加礼盒开出的物品
        for (String thing : openedThingId.split(";")) {
            int tableTypeId = 0;
            int tableFieldId = 0;
            int value = 0;
            String tableType = thing.split("_")[0];
            int id = ConvertUtil.toInt(thing.split("_")[1]);
            if (tableType.equals("1")) {
                DictGenerBoxThing generBoxThing = DictMap.dictGenerBoxThingMap.get(id + "");
                tableTypeId = generBoxThing.getTableTypeId();
                tableFieldId = generBoxThing.getTableFieldId();
                value = generBoxThing.getValue();
                builder.append(tableTypeId).append("_").append(tableFieldId).append("_").append(value).append(";");
            }
            // 当产出多个物品时,将最终操作结果,放入一个map
            ThingUtil.groupThingMap(thingMap, tableTypeId, tableFieldId, value);
        }

        //日志处理
        String log = "圣诞礼盒:instPlayerId=" + instPlayerId + " 礼盒类型=" + type + " 开出的物品列表=" + thingMap;
        LogUtil.info(log);

        ThingUtil.groupThingMap(instPlayer, thingMap, syncMsgData, msgMap);

        // 将宝箱开出的id发给客户端,以供展示
        // [格式为1_1;2_2; 下划线前面的数为1或2两个值, 等于1 表示去Dict_Gener_BoxThing表查, 等于2 表示去Dict_Special_BoxThing表查； 下划线后边的数为表的id；字符串最后不包含分号]

        retMsgData.putStringItem("1", StringUtil.noContainLastString(builder.toString()));

        retMsgData.putIntItem("first", firstCount);
        retMsgData.putIntItem("second", secondCount);


        MessageUtil.sendSyncMsg(channelId, syncMsgData);
        MessageUtil.sendSuccMsg(channelId, msgMap, retMsgData);

    }


}
