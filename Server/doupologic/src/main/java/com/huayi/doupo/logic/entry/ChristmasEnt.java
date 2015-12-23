package com.huayi.doupo.logic.entry;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

import java.util.HashMap;

/**
 * Created by Coolcow on 2015/12/18 0018.
 */
public class ChristmasEnt extends BaseHandler{
    /**
     * 副本——普通战斗
     * @author cui
     * @date
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @Description
     */
    public void christmasDay(HashMap<String, Object> msgMap, String channelId){
        try {
            HandlerFactory.getChristmasHandler().christmasDay(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", " + DictMap.sysMsgRuleMap.get((int)msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }
}
