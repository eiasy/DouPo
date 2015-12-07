package com.huayi.doupo.logic.entry;

import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

import java.util.HashMap;

/**
 * Created by cui on 2015/10/27 0027.
 */
public class YFireEnt  extends BaseHandler {

    /**
     *  进入异火祭坛
     * @param msgMap
     * @param channelId
     */
    public void enterYFire(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getYFireHandler().enterYFire(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 装备异火
     * @param msgMap
     * @param channelId
     */
    public void equipYFire(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getYFireHandler().equipYFire(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 吃火灵恢复能量
     * @param msgMap
     * @param channelId
     */
    public void cureYFire(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getYFireHandler().cureYFire(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 激活异火
     * @param msgMap
     * @param channelId
     */
    public void activeYFire(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getYFireHandler().activeYFire(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }
}
