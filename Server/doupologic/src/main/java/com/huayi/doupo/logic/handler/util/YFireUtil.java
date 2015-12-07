package com.huayi.doupo.logic.handler.util;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.*;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by cui on 2015/10/27 0027.
 */
public class YFireUtil extends DALFactory {

    public static final int STATE_NONE = 0;     //未激活
    public static final int STATE_NORMAL = 1;   //旺盛状态
    public static final int STATE_RAMPAGE = 2;  //狂暴状态

    public static final String NULL = "";       // 空字符串


    public static void addInstPlayerYFire(int instPlayerId, int id, int count, MessageData syncMsgData) throws Exception {
        if (id <= 0 || count <= 0) {
            return;
        }
        List<InstPlayerYFire> instPlayerYFireList = getInstPlayerYFireDAL().getList("instPlayerId = " + instPlayerId, instPlayerId);
        boolean canAdd = true;
        if (instPlayerYFireList.size() > 0) {
            for (InstPlayerYFire fire : instPlayerYFireList) {
                if (fire.getFireId() == id) {
                    fire.setChipCount(fire.getChipCount() + count);
                    getInstPlayerYFireDAL().update(fire, instPlayerId);
                    OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, fire, fire.getId(), fire.getResult(), syncMsgData);
                    canAdd = false;
                    break;
                }
            }
        }

        if(canAdd){
            InstPlayerYFire fire = new InstPlayerYFire();
            fire.setInstPlayerId(instPlayerId);
            fire.setFireId(id);
            fire.setState(STATE_NONE);//默认未激活
            fire.setFireTime(NULL);
            fire.setHp(0);
            fire.setSpeed(0);
            fire.setOwner(NULL);
            fire.setChipCount(count);
            fire.setVersion(1);     //修改版本
            getInstPlayerYFireDAL().add(fire, instPlayerId);
            OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.add, fire, fire.getId(), fire.getResult(), syncMsgData);
        }
    }


    /**
     * 验证是否通过
     *
     * @param msgMap
     * @param channelId
     * @param instPlayer
     * @return
     * @throws Exception
     * @author cui
     * @date 15/10/17
     */
    public static boolean checkRecognize(Map<String, Object> msgMap, String channelId, InstPlayer instPlayer) throws Exception {

        // 检查玩家等级是否符合
        //是否达到开放等级
        DictFunctionOpen dictFunctionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.fire + "");
        if (instPlayer.getLevel() < dictFunctionOpen.getLevel()) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPlayerLevel);

            return false;
        }

        return true;
    }

    /**
     * @param instPlayerYFire
     * @throws Exception
     * @author cui
     * @date 15/10/29
     */
    public static void costFireHp(InstPlayerYFire instPlayerYFire) throws Exception {
//        DictYFire dictYFire = DictList.dictYFireList.get(instPlayerYFire.getFireId());
        DictYFire dictYFire = null;
        for (DictYFire fire : DictList.dictYFireList) {
            if(fire.getId() == instPlayerYFire.getFireId()){
                dictYFire = fire;
                break;
            }
        }
        //不重复检测 是否空

        int value = dictYFire.getCost() * instPlayerYFire.getSpeed();

        String time = instPlayerYFire.getFireTime();
        // 计算时间间隔 = 当前时间 - 上次时间
        long intevalTime = 0;
        if (time != null && !time.equals("")) {
            intevalTime = DateUtil.getCurrMill() - DateUtil.getMillSecond(time);
            intevalTime = intevalTime / 1000 / 60;
        }
        value = (int) (value * intevalTime);
        value = instPlayerYFire.getHp() - value;
        if (value <= 0) {
            instPlayerYFire.setHp(0);                           //清零
            instPlayerYFire.setState(YFireUtil.STATE_NORMAL);   //旺盛状态
        } else {
            instPlayerYFire.setHp(value);
        }
        instPlayerYFire.setFireTime(DateUtil.getCurrTime());
//        OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerYFire, instPlayerYFire.getId(), instPlayerYFire.getResult(), syncMsgData);
    }

    /**
     * @param instPlayer
     * @param instPlayerYFire
     * @param fireList
     * @param instPlayerCard
     * @param posIndex
     * @param syncMsgData
     * @throws Exception
     * @author cui
     * @date 15/10/28
     */
    public static void setFireToCard(InstPlayer instPlayer, InstPlayerYFire instPlayerYFire, List<InstPlayerYFire> fireList,
                                     InstPlayerCard instPlayerCard, int posIndex, MessageData syncMsgData) throws Exception {
        //初始化
        InstPlayerYFire repeatFire = null;
        HashMap<Integer, int[]> cardList = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        for (InstPlayerYFire fire : fireList) {
            String owner = fire.getOwner();
            if(owner == null || owner.equals("")) continue;
            String[] list = owner.split(";");
            sb.setLength(0);
            for (String obj : list) {
                String[] slice = obj.split("_");
                int id = Integer.parseInt(slice[0]);
                int index = Integer.parseInt(slice[1]);

                int[] pos = cardList.get(id);
                if (pos != null) {
                    pos[index] = fire.getFireId();
                } else {
                    pos = new int[3];
                }
                cardList.put(id, pos);

                if (id == instPlayerCard.getId() && index == posIndex) {
                    repeatFire = fire;
                } else {
                    sb.append(id).append("_").append(index).append(";");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            fire.setOwner(sb.toString());
        }

        sb.setLength(0);
        if (repeatFire != null) {
            int speed = repeatFire.getSpeed() - 1;
            costFireHp(repeatFire);
            repeatFire.setSpeed(speed < 0 ? 0 : speed);
            getInstPlayerYFireDAL().update(repeatFire, instPlayer.getId());
            InstPlayerYFire newFire = repeatFire.clone(); //为了满足客户端消耗速度
            DictYFire dictYFire = null;
            for (DictYFire fire : DictList.dictYFireList) {
                if(fire.getId() == newFire.getFireId()){
                    dictYFire = fire;
                    break;
                }
            }
            if(dictYFire != null){
                newFire.setSpeed(dictYFire.getCost());
            }else{
                newFire.setSpeed(0);
            }
            //非常特殊处理，不要轻易改动
            OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, repeatFire, repeatFire.getId(), newFire.getResult(), syncMsgData);
        }


        String owner = instPlayerYFire.getOwner();
        if(owner == null || owner.equals("")){
            sb.append(instPlayerCard.getId()).append("_").append(posIndex);
        }else{
            sb.append(owner).append(";").append(instPlayerCard.getId()).append("_").append(posIndex);
        }
        instPlayerYFire.setOwner(sb.toString());

        costFireHp(instPlayerYFire);
        int speed = instPlayerYFire.getSpeed() + 1;
        instPlayerYFire.setSpeed(speed < 0 ? 0 : speed);
        getInstPlayerYFireDAL().update(instPlayerYFire, instPlayer.getId());

        InstPlayerYFire newFire = instPlayerYFire.clone(); //为了满足客户端消耗速度
        DictYFire dictYFire = null;
        for (DictYFire fire : DictList.dictYFireList) {
            if(fire.getId() == newFire.getFireId()){
                dictYFire = fire;
                break;
            }
        }
        if(dictYFire != null){
            newFire.setSpeed(dictYFire.getCost());
        }else{
            newFire.setSpeed(0);
        }
        //非常特殊处理，不要轻易改动
        OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayerYFire, instPlayerYFire.getId(), newFire.getResult(), syncMsgData);

    }


    /**
     * @param instPlayerId
     * @param oldInstPlayerCard
     * @param newInstPlayerCard
     * @param syncMsgData
     * @throws Exception
     * @author cui
     * @date 15/10/29
     */
    public static void updateFire(int instPlayerId, InstPlayerCard oldInstPlayerCard, InstPlayerCard newInstPlayerCard, MessageData syncMsgData) throws Exception {
        List<InstPlayerYFire> fireList = getInstPlayerYFireDAL().getList(" instPlayerId = " + instPlayerId, instPlayerId);
        if (fireList.size() <= 0) {
            return;
        }
        //初始化
        StringBuilder sb = new StringBuilder();
        boolean mustUpdate;
        for (InstPlayerYFire fire : fireList) {
            String owner = fire.getOwner();
            if(owner == null || owner.equals("")) continue;
            String[] list = owner.split(";");
            sb.setLength(0);
            mustUpdate = false;
            for (String obj : list) {
                String[] slice = obj.split("_");
                int id = Integer.parseInt(slice[0]);
                int index = Integer.parseInt(slice[1]);

                if (id == oldInstPlayerCard.getId()) {
                    mustUpdate = true;
                    sb.append(newInstPlayerCard.getId()).append("_").append(index).append(";");
                } else {
                    sb.append(id).append("_").append(index).append(";");
                }
            }
            if (sb.length() > 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            fire.setOwner(sb.toString());

            if (mustUpdate) {
                getInstPlayerYFireDAL().update(fire, instPlayerId);
                OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, fire, fire.getId(), fire.getResult(), syncMsgData);
            }
        }

    }


    public static void costFire(InstPlayerYFire instPlayerYFire){
        instPlayerYFire.setState(STATE_NONE);
        instPlayerYFire.setOwner(NULL);
        instPlayerYFire.setFireTime(NULL);
        instPlayerYFire.setHp(0);
    }

    @Test
    public void testName(){
        String owner = null;
        if(owner == null || owner.equals("")) {
            System.out.println("fffff");
        }

//        System.out.println(list);
    }

}
