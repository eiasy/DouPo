package com.huayi.doupo.logic.handler;

import com.huayi.doupo.base.model.*;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.StaticThing;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.OrgFrontMsgUtil;
import com.huayi.doupo.logic.handler.util.ThingUtil;
import com.huayi.doupo.logic.handler.util.YFireUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by cui on 2015/10/27 0027.
 */
public class YFireHandler extends BaseHandler {

    public static final String NULL = "";       // 空字符串
    /**
     * 进入异火祭坛
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/10/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void enterYFire(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!YFireUtil.checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //刷新异火
        MessageData playerMsgData = new MessageData();
        MessageData syncMsgData = new MessageData();


        List<InstPlayerYFire> fireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
        if (fireList.size() > 0) {
            for (InstPlayerYFire fire : fireList) {
                getInstPlayerYFireDAL().update(fire, instPlayerId);
                OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, fire, fire.getId(), fire.getResult(), syncMsgData);
            }
        }
        MessageUtil.sendSyncMsg(channelId, syncMsgData);
        MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
    }


    /**
     * 装备异火，更换异火
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/10/27
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void equipYFire(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!YFireUtil.checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int fireId = (Integer) msgMap.get("fireId");        //异火ID
        int cardId = (Integer) msgMap.get("cardId");        //卡牌ID
        int posIndex = (Integer) msgMap.get("posIndex");    //异火装备在卡牌上的位置

        //刷新异火


        List<InstPlayerYFire> fireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
        if (fireList.size() <= 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "没有激活的异火");
            return;
        }

        InstPlayerYFire instPlayerYFire = null;
        for (InstPlayerYFire fire : fireList) {
            if (fire.getFireId() == fireId) {
                instPlayerYFire = fire;
                break;
            }
        }
        if (instPlayerYFire == null) {
            MessageUtil.sendFailMsg(channelId, msgMap, "没有激活的异火");
            return;
        }

        int state = instPlayerYFire.getState();
        if (state != YFireUtil.STATE_RAMPAGE) {
            MessageUtil.sendFailMsg(channelId, msgMap, "异火处于狂暴状态才可以装备");
            return;
        }

        //验证这张卡玩家是否有
        InstPlayerCard instPlayerCard = getInstPlayerCardDAL().getModel(cardId, instPlayerId);
        if (instPlayerCard.getInstPlayerId() != instPlayerId) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_differentPlayers);
            return;
        }

        //是否已在阵上
        if (instPlayerCard.getInTeam() == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "未上阵的卡牌无法装备异火");
            return;
        }

//        DictYFire dictYFire = DictList.dictYFireList.get(instPlayerYFire.getFireId());
        DictYFire dictYFire = null;
        for (DictYFire fire : DictList.dictYFireList) {
            if(fire.getId() == instPlayerYFire.getFireId()){
                dictYFire = fire;
                break;
            }
        }
        if (dictYFire == null) {
            LogUtil.warn("instPlayerId = " + instPlayerId + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap);
            return;
        }


        MessageData syncMsgData = new MessageData();
        YFireUtil.costFireHp(instPlayerYFire);
        if(instPlayerYFire.getHp() <= 0){
            getInstPlayerYFireDAL().update(instPlayerYFire, instPlayerId);
            OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerYFire, instPlayerYFire.getId(), instPlayerYFire.getResult(), syncMsgData);
            MessageUtil.sendSyncMsg(channelId, syncMsgData);
            MessageUtil.sendFailMsg(channelId, msgMap, "异火处于狂暴状态才可以装备");
            return;
        }


        YFireUtil.setFireToCard(instPlayer, instPlayerYFire, fireList, instPlayerCard, posIndex, syncMsgData);

        MessageUtil.sendSyncMsg(channelId, syncMsgData);
        MessageUtil.sendSuccMsg(channelId, msgMap, new MessageData());


    }


    /**
     * 吃道具
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/10/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void cureYFire(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!YFireUtil.checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int fireId = (Integer) msgMap.get("fireId");        //异火ID
        int type = (Integer) msgMap.get("type");            //吃异火类型（0.使用碎片 1.异火灵石）
        int count = (Integer) msgMap.get("count");          //数量

        if (type < 0 || type > 1 || count <= 0 || fireId <= 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "非法操作");
            return;
        }

        List<InstPlayerYFire> fireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
        if (fireList.size() <= 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "异火没有激活");
            return;
        }

        InstPlayerYFire instPlayerYFire = null;
        for (InstPlayerYFire fire : fireList) {
            if (fire.getFireId() == fireId) {
                instPlayerYFire = fire;
                break;
            }
        }
        if (instPlayerYFire == null) {
            MessageUtil.sendFailMsg(channelId, msgMap, "异火没有激活");
            return;
        }

//        DictYFire dictYFire = DictList.dictYFireList.get(instPlayerYFire.getFireId());
        DictYFire dictYFire = null;
        for (DictYFire fire : DictList.dictYFireList) {
            if(fire.getId() == instPlayerYFire.getFireId()){
                dictYFire = fire;
                break;
            }
        }
        if (dictYFire == null) {
            LogUtil.warn("instPlayerId = " + instPlayerId + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap);
            return;
        }





        MessageData syncMsgData = new MessageData();
        if (type == 0) {
            if (instPlayerYFire.getChipCount() == 0 || instPlayerYFire.getChipCount() < count) {
                MessageUtil.sendFailMsg(channelId, msgMap, "火种数量不足");
                return;
            }
            //消耗异火能量
            YFireUtil.costFireHp(instPlayerYFire);
            int cureVar = dictYFire.getCureVar() * count;
            int delta = instPlayerYFire.getHp() + cureVar;
            delta = delta > dictYFire.getHpMax() ? dictYFire.getHpMax() : delta;
            int deltaCount = instPlayerYFire.getChipCount() - count;

            instPlayerYFire.setHp(delta);
            instPlayerYFire.setChipCount(deltaCount);

        } else {
            List<InstPlayerThing> things = getInstPlayerThingDAL().getList(" instPlayerId = " + instPlayerId + " and thingId = " + StaticThing.thing304, instPlayerId);
            if (things.size() <= 0 || (things.size() > 0 && things.get(0).getNum() < count)) {
                MessageUtil.sendFailMsg(channelId, msgMap, "异火灵石不足");
                return;
            }

            //消耗异火能量
            YFireUtil.costFireHp(instPlayerYFire);
            InstPlayerThing instPlayerThing = things.get(0);
            ThingUtil.updateInstPlayerThing(instPlayerId, instPlayerThing, count, syncMsgData, msgMap);


            int cureVar = DictMapUtil.getSysConfigIntValue(StaticSysConfig.fireCureVar) * count;
            int delta = instPlayerYFire.getHp() + cureVar;
            delta = delta > dictYFire.getHpMax() ? dictYFire.getHpMax() : delta;
            instPlayerYFire.setHp(delta);
        }


        if (instPlayerYFire.getHp() >= dictYFire.getHpMax()){
            instPlayerYFire.setState(YFireUtil.STATE_RAMPAGE); //狂暴状态
        }


        getInstPlayerYFireDAL().update(instPlayerYFire, instPlayerId);
        OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerYFire, instPlayerYFire.getId(), instPlayerYFire.getResult(), syncMsgData);
        MessageUtil.sendSyncMsg(channelId, syncMsgData);
        MessageUtil.sendSuccMsg(channelId, msgMap, new MessageData());
    }

    /**
     * 激活异火
     * @author cui
     * @date    15/10/28
     * @param msgMap
     * @param channelId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void activeYFire(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!YFireUtil.checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int fireId = (Integer) msgMap.get("fireId");        //异火ID


        List<InstPlayerYFire> fireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
        if (fireList.size() <= 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "异火不存在");
            return;
        }

        InstPlayerYFire instPlayerYFire = null;
        for (InstPlayerYFire fire : fireList) {
            if (fire.getFireId() == fireId) {
                instPlayerYFire = fire;
                break;
            }
        }
        if (instPlayerYFire == null) {
            MessageUtil.sendFailMsg(channelId, msgMap, "异火不存在");
            return;
        }

        DictYFire dictYFire = null;
        for (DictYFire fire : DictList.dictYFireList) {
            if(fire.getId() == instPlayerYFire.getFireId()){
                dictYFire = fire;
                break;
            }
        }
        if (dictYFire == null) {
            LogUtil.warn("instPlayerId = " + instPlayerId + ", "
                    + DictMap.sysMsgRuleMap.get((int) msgMap.get("header") + "").getName() + ",  " + msgMap);
            return;
        }

        if (instPlayerYFire.getChipCount() < dictYFire.getChipMax()) {
            MessageUtil.sendFailMsg(channelId, msgMap, "异火碎片不足");
            return;
        }

        int count = instPlayerYFire.getChipCount() - dictYFire.getChipMax();
        instPlayerYFire.setChipCount(count);
        instPlayerYFire.setState(YFireUtil.STATE_RAMPAGE);      //激活时狂暴
        instPlayerYFire.setFireTime(NULL);
        instPlayerYFire.setOwner(NULL);
        instPlayerYFire.setHp(dictYFire.getHpMax());
        instPlayerYFire.setSpeed(0);

        MessageData syncMsgData = new MessageData();
        getInstPlayerYFireDAL().update(instPlayerYFire, instPlayerId);
        OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerYFire, instPlayerYFire.getId(), instPlayerYFire.getResult(), syncMsgData);
        MessageUtil.sendSyncMsg(channelId, syncMsgData);
        MessageUtil.sendSuccMsg(channelId, msgMap, new MessageData());

    }
}
