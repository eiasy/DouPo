package com.huayi.doupo.logic.entry;


import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.factory.HandlerFactory;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by cui on 2015/9/22 0022.
 */
public class MineWarEnt extends BaseHandler {

    /**
     * 初始化矿区
     *
     * @param msgMap
     * @param channelId
     */
    public void enterMineZone(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().enterMineZone(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 刷新矿区
     * @author cui
     * @date   15/09/25
     * @param msgMap
     * @param channelId
     */
    public void refreshMineZone(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().refreshMineZone(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 一键寻矿
     * @author cui
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void aKeySearchMine(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().aKeySearchMine(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 寻矿，找玩家自己占领的矿
     * @author cui
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void searchMine(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().searchMine(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 驱逐协助者
     * @author cui
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void expelAssistant(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().expelAssistant(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 协助矿
     * @author cui 
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void assistMiner(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().assistMiner(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 放弃占领
     * @author cui 
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void giveUpOccupy(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().giveUpOccupy(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 放弃协助
     * @author cui 
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void giveUpAssist(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().giveUpAssist(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 占矿
     * @author cui 
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void occupyMine(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().occupyMine(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 占矿胜利
     * @author cui 
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void mineFightWin(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().mineFightWin(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 占矿反击
     * @author cui 
     * @date   15/09/28
     * @param msgMap
     * @param channelId
     */
    public void mineBeatBack(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().mineBeatBack(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                          + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 联盟协助
     * @author cui
     * @date   15/10/16
     * @param msgMap
     * @param channelId
     */
    public void unionAssist(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().unionAssist(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * 占矿或抢矿失败
     * @author cui
     * @date    2015/12/22
     * @param msgMap
     * @param channelId
     */
    public void mineFail(HashMap<String, Object> msgMap, String channelId) {
        try {
            HandlerFactory.getMineWarHandler().mineFail(msgMap, channelId);
        } catch (Exception e) {
            LogUtil.error("instPlayerId = " + PlayerMapUtil.getPlayerIdByChannelId(channelId) + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap, e);
            e.printStackTrace();
            MessageUtil.sendFailMsg(channelId, msgMap, errorHint(msgMap));
        }
    }

    /**
     * Method description
     *
     * @throws Exception
     */
    @Test
    public void testEnterMineZone1() throws Exception {
        System.out.println("hello world");
        enterMineZone(null, null);
    }
}

