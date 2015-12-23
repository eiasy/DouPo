package com.huayi.doupo.logic.handler;


import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.model.*;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticFunctionOpen;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.GoldStaticsType;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.*;
import com.huayi.doupo.logic.util.LoadResourceUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import org.junit.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by cui on 2015/9/22 0022.
 */
public class MineWarHandler extends BaseHandler {

    private final static int CLOSED = 0;        // 活动未开启
    private final static int OPEN = 1;          // 活动开启
    private final static int MASTER = 0;        // 矿主
    private final static int SLAVE = 1;         // 协助者
    private final static int FAILURE = 0;       // 没有领取任何特殊奖励
    private final static int SUCCESS = 1;       // 成功领取特殊奖励
    public static final String SEP_BAR = "|";   // 字符串分割
    public static final String SEP_SEM = "/";   // 字符串分割
    public static final String NULL = "";       // 空字符串
    public static final String SEP_SPACE = " ";       // 空格字符串
    public static final String SEP_LINE = "_";       // 空格字符串

    public static final int MAIL_TYPE_MINE = 5;         //邮件类型   5.资源矿

//    占矿时间到结算 = 2，协助矿时间到结算 = 3，守矿失败结算 = 4，协助守矿失败结算 = 5,放弃矿结算 = 6,放弃协助矿结算 = 7,被驱逐结算 = 8，被动放弃协助矿结算 = 9


    /**
     * 矿的格式：    (什么矿区 | 第几页 | 第几个) ( 0 | 000 | 0)
     * 什么矿区：   0.普通矿区  1.高级矿区
     * 第几页：     1~n页
     * 第几个：     1~n个
     */


    /**
     * 验证是否通过
     *
     * @param msgMap
     * @param channelId
     * @param instPlayer
     * @return
     * @throws Exception
     * @author cui
     * @date 09/30/2015
     */
    private boolean checkRecognize(Map<String, Object> msgMap, String channelId, InstPlayer instPlayer) throws Exception {

        // 检查玩家等级是否符合
//        if (instPlayer.getLevel() < DictMapUtil.getSysConfigIntValue(StaticSysConfig.levelLimit)) {
        //是否达到开放等级
        DictFunctionOpen dictFunctionOpen = DictMap.dictFunctionOpenMap.get(StaticFunctionOpen.mine + "");
        if (instPlayer.getLevel() < dictFunctionOpen.getLevel()) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notPlayerLevel);

            return false;
        }


        ArrayList<String> time = MineUtil.checkAcitivityTime();
        boolean timeout = true;
        //保证time是配对的
        // 验证是否在活动期内
        String currYmd = DateUtil.getYmdDate();
        long currMill = DateUtil.getCurrMill();
        for (int i = 0; i < time.size(); i += 2) {
            String startTime = currYmd + SEP_SPACE + time.get(i);
            String endTime = currYmd + SEP_SPACE + time.get(i + 1);
            if (currMill >= DateUtil.getMillSecond(startTime) && currMill <= DateUtil.getMillSecond(endTime)) {
                timeout = false;
                break;
            }
        }

        // 当时间没到时，弹出提示，无法进入
        if (timeout) {
            MessageData timeMsg = new MessageData();

            timeMsg.putIntItem("activityStartTime",
                    DictMapUtil.getSysConfigIntValue(StaticSysConfig.firstTimeStart) * 3600);    // 发送起始时间，客户端需要 发送秒为单位
            timeMsg.putIntItem("activityEndTime",
                    DictMapUtil.getSysConfigIntValue(StaticSysConfig.firstTimeEnd) * 3600);    // 发送结束时间，客户端需要 发送秒为单位
            timeMsg.putIntItem("activityStartTime2",
                    DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeStart) * 3600);    // 发送起始时间2，客户端需要 发送秒为单位
            timeMsg.putIntItem("activityEndTime2",
                    DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeEnd) * 3600);    // 发送结束时间2，客户端需要 发送秒为单位

            MessageUtil.sendFailMsg(channelId, msgMap, "hide", timeMsg);  //跟客户度约定好了 hide是，客户度自动处理

            return false;
        }

        return true;
    }


//    /**
//     * local method 刷新普通矿
//     *
//     * @param msgMap
//     * @param channelId
//     * @param instPlayer
//     * @author cui
//     * @date 15/09/25
//     */
//    private MessageData refreshJunior(String channelId, InstPlayer instPlayer) {
//        //TODO 有重复获取，是否需要额外传进变量有待分析
//        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id
//
//        MessageData playerMsgData = new MessageData();
//        StringBuilder sb = new StringBuilder();
//        StringBuilder sbUnion = new StringBuilder();
//
//        int zoneType = 0; //普通区域
//        List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" instPlayerId = " + instPlayerId + " and mineZone = " + zoneType, 0);
//        InstPlayerMine playerMine = null;
//        int playerMineid = 0;
//        int index = 0;
//
//        if (mineList.size() > 0) {
//            playerMine = mineList.get(0);
//            playerMineid = playerMine.getMineId();
//            index = playerMineid % 10;
//        }
//        for (int i = 0; i < MineUtil.MINE_JUNIOR_ARRAY.length; i++) {
//            if (playerMineid > 0) {
//                if (i == index - 1) {
//                    sbUnion.setLength(0);//公会名字初始化
//
//                    sb.append(playerMineid).append(SEP_BAR);
//                    sb.append(MineUtil.MINE_JUNIOR_ARRAY[i]).append(SEP_BAR);
////                    sb.append(1).append(SEP_BAR); //被占领
//
//                    List<InstUnionMember> unionMembers = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
//                    if (unionMembers.size() > 0) {
//                        int instUnionId = unionMembers.get(0).getInstUnionId();
//                        InstUnion instUnion = getInstUnionDAL().getModel(instUnionId, 0);
//                        sbUnion.append(instUnion.getName());
//                    }
//
//                    sb.append(instPlayer.getId()).append(SEP_BAR);          //ID
//                    sb.append(instPlayer.getLevel()).append(SEP_BAR);       //等级
//                    sb.append(instPlayer.getName()).append(SEP_BAR);        //昵称
//                    sb.append(sbUnion.toString()).append(SEP_BAR);          //公会
//
//                    sb.append(0).append(SEP_BAR);       //玩家ID
//                    sb.append(0).append(SEP_BAR);       //玩家等级
//                    sb.append(NULL).append(SEP_BAR);    //玩家昵称
//
//                    sb.append(0).append(SEP_BAR);       //玩家ID
//                    sb.append(0).append(SEP_BAR);       //玩家等级
//                    sb.append(NULL).append(SEP_BAR);    //玩家昵称
//                    sb.append(SEP_SEM);
//                    continue;
//                }
//            }
//            int mineid = 0 * 10000 + 1 * 10 + (i + 1); //普通矿区的第一页
//            sb.append(mineid).append(SEP_BAR);
//            sb.append(MineUtil.MINE_JUNIOR_ARRAY[i]).append(SEP_BAR);
////            sb.append(0).append(SEP_BAR); //没有被占领
//            sb.append(SEP_SEM);    //
//        }
//        if (sb.length() > 0)
//            sb.deleteCharAt(sb.length() - 1); //删除最后一位分号
//
//        playerMsgData.putIntItem("type", zoneType);
//        playerMsgData.putIntItem("pageIndex", 1); //普通矿只有第一页
//        playerMsgData.putStringItem("mines", sb.toString());
//
//
//        //玩家占矿的ID
//        List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().
//                getList(" instPlayerId = " + instPlayerId + " or assistId1 = " + instPlayerId + " or assistId2 = " + instPlayerId, 0);
//        sb.setLength(0);
//        if (instPlayerMineList.size() > 0) {
//            InstPlayerMine mine = instPlayerMineList.get(0);
//            sb.append(mine.getMineId()).append(SEP_BAR);
//            sb.append(mine.getMineType()).append(SEP_BAR);
//            if (instPlayerId == mine.getInstPlayerId()) {    //矿主
//                sb.append(MASTER);
//            } else { //协助者
//                sb.append(SLAVE);
//            }
//
//            playerMsgData.putStringItem("mine", sb.toString());
//        } else {
//            playerMsgData.putStringItem("mine", "0|0|0");   //矿是空时全发0
//        }
//        return playerMsgData;
////        MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
//    }

    /**
     * local method 刷新普通矿
     *
     * @param sb
     * @param instPlayerId
     * @param pageIndex
     * @return
     * @author cui
     * @date 15/10/2015
     */
    private String refreshJunior(int instPlayerId, int pageIndex) {
        StringBuilder sb = new StringBuilder();
        StringBuilder sbUnion = new StringBuilder();
        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        //玩家占矿的ID
        List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().
                getList(" instPlayerId = " + instPlayerId + " or assistId1 = " + instPlayerId, 0);
        sb.setLength(0);
        int occupy = 0;
        InstPlayerMine playerMine = null;
        if (instPlayerMineList.size() > 0) {
            playerMine = instPlayerMineList.get(0);
            if (playerMine.getMinePage() == pageIndex && playerMine.getMineZone() == 0) {
                occupy = 1;
            }
        }
        if (occupy == 1) {
            sbUnion.setLength(0);//公会名字初始化

            sb.append(playerMine.getMineId()).append(SEP_BAR);
            sb.append(MineUtil.MINE_JUNIOR_ARRAY[0]).append(SEP_BAR);
//                    sb.append(1).append(SEP_BAR); //被占领

            List<InstUnionMember> unionMembers = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
            if (unionMembers.size() > 0) {
                int instUnionId = unionMembers.get(0).getInstUnionId();
                if(instUnionId > 0) {
                    InstUnion instUnion = getInstUnionDAL().getModel(instUnionId, 0);
                    if(instUnion != null)
                        sbUnion.append(instUnion.getName());
                }
            }

            sb.append(instPlayer.getId()).append(SEP_BAR);          //ID
            sb.append(instPlayer.getLevel()).append(SEP_BAR);       //等级
            sb.append(instPlayer.getName()).append(SEP_BAR);        //昵称
            sb.append(sbUnion.toString()).append(SEP_BAR);          //公会
            sb.append(0).append(SEP_BAR);       //占领起始时间
            sb.append(NULL).append(SEP_BAR);    //占矿特殊奖励
            sb.append(0).append(SEP_BAR);       //特殊奖励状态

            sb.append(0).append(SEP_BAR);       //玩家ID
            sb.append(0).append(SEP_BAR);       //玩家等级
            sb.append(NULL).append(SEP_BAR);    //玩家昵称
            sb.append(0).append(SEP_BAR);       //协助起始时间(s)
            sb.append(NULL).append(SEP_BAR);    //协助者特殊奖励
            sb.append(0).append(SEP_BAR);       //特殊奖励领取状态
        } else {
            int mineid = 0 * 10000 + pageIndex * 10 + (1); //普通矿区的第一页
            sb.append(mineid).append(SEP_BAR);
            sb.append(MineUtil.MINE_JUNIOR_ARRAY[0]).append(SEP_BAR);
            sb.append(0).append(SEP_BAR);       //玩家ID
            sb.append(0).append(SEP_BAR);       //玩家等级
            sb.append(NULL).append(SEP_BAR);    //玩家昵称
            sb.append(NULL).append(SEP_BAR);    //公会
            sb.append(0).append(SEP_BAR);       //占领起始时间
            sb.append(NULL).append(SEP_BAR);    //占矿特殊奖励
            sb.append(0).append(SEP_BAR);       //特殊奖励状态

            sb.append(0).append(SEP_BAR);       //玩家ID
            sb.append(0).append(SEP_BAR);       //玩家等级
            sb.append(NULL).append(SEP_BAR);    //玩家昵称
            sb.append(0).append(SEP_BAR);       //协助起始时间(s)
            sb.append(NULL).append(SEP_BAR);    //协助者特殊奖励
            sb.append(0).append(SEP_BAR);       //特殊奖励领取状态
        }

        return sb.toString();
    }

    /**
     * local method 刷新高级矿
     *
     * @param msgMap
     * @param channelId
     * @param pageIndex
     * @author cui
     * @date 15/09/25
     */
    private MessageData refreshSenior(String channelId, int pageIndex) throws Exception {
        //TODO 有重复获取，是否需要额外传进变量有待分析
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        MessageData playerMsgData = new MessageData();
        StringBuilder sb = new StringBuilder();
        StringBuilder sbUnion = new StringBuilder();
        StringBuilder thing = new StringBuilder();

        // 高级矿区，第n页

        sb.setLength(0);
        for (int i = 0; i < MineUtil.MINE_SENIOR_ARRAY.length; i++) {
            int mineid = 1 * 10000 + pageIndex * 10 + (i + 1); //高级矿区的第n页
            List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
            int size = mineList.size();
            int occupy = 0;
            InstPlayer masterPlayer = null;
            InstPlayerMine mine = null;
            if (size > 0) {
                mine = mineList.get(0);
                int pid = mine.getInstPlayerId();
                masterPlayer = getInstPlayerDAL().getModel(pid, pid);
            } else {
                //矿不存在就直接返回不墨迹做其他处理了
                return null;
            }

            if (masterPlayer != null) {
                occupy = 1;
            }

            sb.append(mineid).append(SEP_BAR);
            sb.append(mine.getMineType()).append(SEP_BAR);

            if (occupy == 0) {    //没有被占领时不需要后面了
                sb.append(0).append(SEP_BAR);       //玩家ID
                sb.append(0).append(SEP_BAR);       //玩家等级
                sb.append(NULL).append(SEP_BAR);    //玩家昵称
                sb.append(NULL).append(SEP_BAR);    //公会
                sb.append(0).append(SEP_BAR);    //占领时间

                //特殊奖励矿主的

                sb.append(MineUtil.getFirstThing(mine.getMasterThing())).append(SEP_BAR);            //矿主特殊奖励
                sb.append(mine.getMasterThingState()).append(SEP_BAR);  //特殊奖励领取状态

                sb.append(0).append(SEP_BAR);       //玩家ID
                sb.append(0).append(SEP_BAR);       //玩家等级
                sb.append(NULL).append(SEP_BAR);    //玩家昵称
                sb.append(0).append(SEP_BAR);       //协助起始时间(s)

                //特殊奖励协助者的

                sb.append(MineUtil.getFirstThing(mine.getAssistThing())).append(SEP_BAR);            //协助者特殊奖励
                sb.append(mine.getAssistThingState()).append(SEP_BAR);  //特殊奖励领取状态

                sb.append(SEP_SEM);
                continue;
            }

            sbUnion.setLength(0);//公会名字初始化

            if (masterPlayer != null) {
                List<InstUnionMember> unionMembers = getInstUnionMemberDAL().getList(" instPlayerId = " + masterPlayer.getId(), 0);
                if (unionMembers.size() > 0) {
                    int instUnionId = unionMembers.get(0).getInstUnionId();
                    if(instUnionId > 0) {
                        InstUnion instUnion = getInstUnionDAL().getModel(instUnionId, 0);
                        if(instUnion != null)
                            sbUnion.append(instUnion.getName());
                    }
                }

                int asistid1 = mine.getAssistId1();
                InstPlayer slaver1 = getInstPlayerDAL().getModel(asistid1, asistid1);


                sb.append(masterPlayer.getId()).append(SEP_BAR);         //ID
                sb.append(masterPlayer.getLevel()).append(SEP_BAR);       //等级
                sb.append(masterPlayer.getName()).append(SEP_BAR);       //昵称
                sb.append(sbUnion.toString()).append(SEP_BAR);           //公会
                sb.append(DateUtil.string2MillSecond(mine.getMasterTime()) / 1000).append(SEP_BAR);        //占领时间

                //特殊奖励矿主的


                sb.append(MineUtil.getFirstThing(mine.getMasterThing())).append(SEP_BAR);            //矿主特殊奖励
                sb.append(mine.getMasterThingState()).append(SEP_BAR);  //特殊奖励领取状态


                if (asistid1 > 0) {
                    sb.append(asistid1).append(SEP_BAR);    //玩家ID
                    sb.append(slaver1.getLevel()).append(SEP_BAR);    //玩家等级
                    sb.append(slaver1.getName()).append(SEP_BAR);    //玩家昵称
                    sb.append(DateUtil.string2MillSecond(mine.getATime1()) / 1000).append(SEP_BAR);       //协助起始时间(s)
                } else {
                    sb.append(0).append(SEP_BAR);    //玩家ID
                    sb.append(0).append(SEP_BAR);    //玩家等级
                    sb.append(NULL).append(SEP_BAR);    //玩家昵称
                    sb.append(0).append(SEP_BAR);       //协助起始时间(s)
                }

                //特殊奖励协助者的
                thing.setLength(0);
                thing.append(MineUtil.getFirstThing(mine.getAssistThing()));

                sb.append(thing.toString()).append(SEP_BAR);            //协助者特殊奖励
                sb.append(mine.getAssistThingState()).append(SEP_BAR);  //特殊奖励领取状态

            } else {
                sb.append(0).append(SEP_BAR);       //等级
                sb.append(NULL).append(SEP_BAR);    //昵称
                sb.append(sbUnion.toString()).append(SEP_BAR);    //公会
                sb.append(0).append(SEP_BAR);        //占领时间

                //特殊奖励矿主的


                sb.append(MineUtil.getFirstThing(mine.getMasterThing())).append(SEP_BAR);            //矿主特殊奖励
                sb.append(mine.getMasterThingState()).append(SEP_BAR);  //特殊奖励领取状态

                sb.append(0).append(SEP_BAR);    //玩家ID
                sb.append(0).append(SEP_BAR);    //玩家等级
                sb.append(NULL).append(SEP_BAR);    //玩家昵称
                sb.append(0).append(SEP_BAR);       //协助起始时间(s)

                //特殊奖励协助者的


                sb.append(MineUtil.getFirstThing(mine.getAssistThing())).append(SEP_BAR);            //协助者特殊奖励
                sb.append(mine.getAssistThingState()).append(SEP_BAR);  //特殊奖励领取状态

            }
            sb.append(SEP_SEM);    //
        }


        //添加普通矿
        sb.append(refreshJunior(instPlayerId, pageIndex));

//        if (sb.length() > 0)
//            sb.deleteCharAt(sb.length() - 1); //删除最后一位分号

        playerMsgData.putIntItem("pageIndex", pageIndex); //第n页
        playerMsgData.putIntItem("weather", MineUtil.getWeather(pageIndex)); //这页天气

        playerMsgData.putStringItem("mines", sb.toString());


        //玩家占矿的ID
        List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().
                getList(" instPlayerId = " + instPlayerId + " or assistId1 = " + instPlayerId, 0);
        sb.setLength(0);
        if (instPlayerMineList.size() > 0) {
            InstPlayerMine mine = instPlayerMineList.get(0);
            sb.append(mine.getMineId()).append(SEP_BAR);
            sb.append(mine.getMineType()).append(SEP_BAR);
            if (instPlayerId == mine.getInstPlayerId()) {    //矿主
                sb.append(MASTER).append(SEP_BAR);
            } else { //协助者
                sb.append(SLAVE).append(SEP_BAR);
            }

        } else {
            sb.append("0|0|0|");     //矿是空时全发0
        }

        //玩家是否领取过特殊奖励
        List<InstPlayerMineAward> instPlayerMineAwardList = getInstPlayerMineAwardDAL().
                getList(" instPlayerId = " + instPlayerId, 0);
        if (instPlayerMineAwardList.size() > 0) {
            int awardType = instPlayerMineAwardList.get(0).getAwardType();
            if(awardType == 0){
                sb.append(1); //为了兼容版本，1代表成功
            }else {
                sb.append(awardType); //成功领取
            }
        } else {
            sb.append(FAILURE); //没有领取
        }

        playerMsgData.putStringItem("mine", sb.toString());

        return playerMsgData;
    }

    /**
     * local method 矿资源结算
     *
     * @param instPlayerId
     * @param mine
     * @param type
     * @param who
     * @throws Exception
     * @author cui
     * @date 09/29/2015
     */
    private void accountMine(int instPlayerId, InstPlayerMine mine, DictMineType type, int who, int accountType, int enermyId) throws Exception {
        MineUtil.accountMine(instPlayerId, mine, type, who, accountType, enermyId);
    }


    /**
     * @param msgMap
     * @param channelId
     * @param instPlayer
     * @param mineType
     * @return 因为语法局限性，为了不创建垃圾类，所以消耗检测的地方有多处重复，修改时尽量都查找
     * @author cui
     * @date 09/29/2015
     */
    private boolean checkCost(Map<String, Object> msgMap, String channelId, InstPlayer instPlayer, int mineType) {
        int len = DictList.dictMineTypeList.size();
        boolean permit = false;
        int costType = 0;
        int costValue = 0;
        for (int i = 0; i < len; i++) {
            DictMineType type = DictList.dictMineTypeList.get(i);
            if (type.getMineType() == mineType) {
                costType = type.getMasterCsType();
                costValue = type.getMasterCsValue();
                permit = true;
                break;
            }
        }
        if (len == 0 || !permit) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return false;
        }
        switch (costType) {
            case 0://消耗耐力
                if (instPlayer.getVigor() < costValue) {
                    MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notVigor);
                    return false;
                }
                break;
            case 1://消耗元宝
                if (instPlayer.getGold() < costValue) {
                    MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
                    return false;
                }
                break;
            default:
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_dataError);
                return false;
        }
        return true;
    }

    /**
     * local method 矿资源消耗
     *
     * @param msgMap
     * @param channelId
     * @param instPlayer
     * @param mineType
     * @return
     * @throws Exception
     */
    private boolean consumeCost(Map<String, Object> msgMap, String channelId, InstPlayer instPlayer, int mineType) throws Exception {
        //TODO 有重复获取，是否需要额外传进变量有待分析
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id
        int len = DictList.dictMineTypeList.size();
        boolean permit = false;
        int costType = 0;
        int costValue = 0;
        for (int i = 0; i < len; i++) {
            DictMineType type = DictList.dictMineTypeList.get(i);
            if (type.getMineType() == mineType) {
                costType = type.getMasterCsType();
                costValue = type.getMasterCsValue();
                permit = true;
                break;
            }
        }
        if (len == 0 || !permit) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return false;
        }
        MessageData syncMsgData = new MessageData();
        switch (costType) {
            case 0://消耗耐力
                if (instPlayer.getVigor() < costValue) {
                    MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notVigor);
                    return false;
                }

                int maxVigor = DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor);
                if (instPlayer.getVigor() >= maxVigor && (instPlayer.getVigor() - costValue) < maxVigor) {
                    instPlayer.setLastVigorRecoverTime(DateUtil.getCurrTime());
                }
                instPlayer.setVigor(instPlayer.getVigor() - costValue);
                getInstPlayerDAL().update(instPlayer, instPlayerId);

                OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
                MessageUtil.sendSyncMsg(channelId, syncMsgData);
                break;
            case 1://消耗元宝
                if (instPlayer.getGold() < costValue) {
                    MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
                    return false;
                }

                PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, costValue, msgMap);
                getInstPlayerDAL().update(instPlayer, instPlayerId);

                OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
                MessageUtil.sendSyncMsg(channelId, syncMsgData);
                break;
            default:
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_dataError);
                return false;
        }

        return true;
    }

//    /**
//     * 获取矿资源对象
//     *
//     * @param mineType
//     * @return
//     */
//    private DictMineType getMineTypeObj(int mineType) {
//        //产出检查
//        int len = DictList.dictMineTypeList.size();
//
//        for (int i = 0; i < len; i++) {
//            DictMineType type = DictList.dictMineTypeList.get(i);
//            if (type.getMineType() == mineType) {
//
//                return type;
//            }
//        }
//        return null;
//    }


    /**
     * 初始化矿区
     *
     * @author cui
     * @data 09/30/2015
     */
    private MessageData initialMine(MessageData playerMsgData) {
        //初始化矿区，发送基本信息
//        MessageData playerMsgData = new MessageData();

        playerMsgData.putIntItem("minePageCount", DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage));    // 高级矿最大页数

        StringBuilder sb = new StringBuilder();
        DictMineType ironMine = DictMap.dictMineTypeMap.get(1 + "");
        DictMineType copperMine = DictMap.dictMineTypeMap.get(2 + "");
        DictMineType silverMine = DictMap.dictMineTypeMap.get(3 + "");
        DictMineType goldMine = DictMap.dictMineTypeMap.get(4 + "");

        // 采矿速度
        sb.append(ironMine.getMasterCopperCount()).append(SEP_BAR);
        sb.append(ironMine.getMasterThing83Count()).append(SEP_BAR);
        sb.append(ironMine.getMasterCsValue()).append(SEP_BAR);
        sb.append(ironMine.getMasterCsType()).append(SEP_BAR);
        sb.append(ironMine.getSlaveCsValue()).append(SEP_BAR);
        sb.append(ironMine.getSlaveCsType()).append(SEP_SEM);

        // 铜矿速度
        sb.append(copperMine.getMasterCopperCount()).append(SEP_BAR);
        sb.append(copperMine.getMasterThing83Count()).append(SEP_BAR);
        sb.append(copperMine.getMasterCsValue()).append(SEP_BAR);
        sb.append(copperMine.getMasterCsType()).append(SEP_BAR);
        sb.append(copperMine.getSlaveCsValue()).append(SEP_BAR);
        sb.append(copperMine.getSlaveCsType()).append(SEP_SEM);

        // 银矿速度
        sb.append(silverMine.getMasterCopperCount()).append(SEP_BAR);
        sb.append(silverMine.getMasterThing83Count()).append(SEP_BAR);
        sb.append(silverMine.getMasterCsValue()).append(SEP_BAR);
        sb.append(silverMine.getMasterCsType()).append(SEP_BAR);
        sb.append(silverMine.getSlaveCsValue()).append(SEP_BAR);
        sb.append(silverMine.getSlaveCsType()).append(SEP_SEM);

        // 金矿速度
        sb.append(goldMine.getMasterCopperCount()).append(SEP_BAR);
        sb.append(goldMine.getMasterThing83Count()).append(SEP_BAR);
        sb.append(goldMine.getMasterCsValue()).append(SEP_BAR);
        sb.append(goldMine.getMasterCsType()).append(SEP_BAR);
        sb.append(goldMine.getSlaveCsValue()).append(SEP_BAR);
        sb.append(goldMine.getSlaveCsType());
        playerMsgData.putStringItem("produceSpeed", sb.toString());


        //天气变化
        sb.setLength(0);
        for (DictMineWeather weather : DictList.dictMineWeatherList) {
            sb.append(weather.getValue()).append(SEP_BAR);
        }
        if (sb.length() > 0)
            sb.deleteCharAt(sb.length() - 1); //删除最后一位分号

        playerMsgData.putStringItem("weatherAddPercent", sb.toString());

        playerMsgData.putIntItem("assistAddPercent", DictMapUtil.getSysConfigIntValue(StaticSysConfig.assistAddPercent));

        playerMsgData.putIntItem("specialRewardOccupyTime", DictMapUtil.getSysConfigIntValue(StaticSysConfig.masterPerTime) * 3600); // 发送起始时间，客户端需要 发送秒为单位
        playerMsgData.putIntItem("specialRewardAssistTime", DictMapUtil.getSysConfigIntValue(StaticSysConfig.slavePerTime) * 3600);  // 发送起始时间，客户端需要 发送秒为单位

        playerMsgData.putIntItem("activityStartTime", DictMapUtil.getSysConfigIntValue(StaticSysConfig.firstTimeStart) * 3600);    // 发送起始时间，客户端需要 发送秒为单位
        playerMsgData.putIntItem("activityEndTime", DictMapUtil.getSysConfigIntValue(StaticSysConfig.firstTimeEnd) * 3600);    // 发送结束时间，客户端需要 发送秒为单位
        playerMsgData.putIntItem("activityStartTime2", DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeStart) * 3600);    // 发送起始时间2，客户端需要 发送秒为单位
        playerMsgData.putIntItem("activityEndTime2", DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeEnd) * 3600);    // 发送结束时间2，客户端需要 发送秒为单位

        return playerMsgData;
    }

    /**
     * 进入矿区
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/25
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void enterMineZone(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }


        //刷新高级矿区
        MessageData playerMsgData = refreshSenior(channelId, 1); //高级矿区第一页
        if (playerMsgData == null) {
            MessageUtil.sendFailMsg(channelId, msgMap, "矿不存在");
            return;
        }

        // 初始化矿区
        playerMsgData = initialMine(playerMsgData);

        MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
    }

    /**
     * 刷新矿区
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/25
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void refreshMineZone(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int pageIndex = (Integer) msgMap.get("pageIndex"); //查看第几页

        //进入逻辑
        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        if (pageIndex <= mineMaxPage && pageIndex > 0) {
            //刷新
            MessageData playerMsgData = refreshSenior(channelId, pageIndex);
            MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
        } else {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
        }
    }

    /**
     * 一键寻矿
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void aKeySearchMine(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineType = (Integer) msgMap.get("type"); //矿类型
        if (mineType < 0 || mineType > 3) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        if (mineType == 0) { //铁矿
            //刷新普通区域
            MessageData playerMsgData = refreshSenior(channelId, 1); //直接进入第一页就行了
            MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);

        } else {   //铜矿，银矿，金矿
            //TODO 字符串过长，是否需要考虑内存，后面再进行优化
//                String sql = "SELECT minePage FROM (SELECT minePage,count(minePage) as size FROM Inst_Player_Mine WHERE mineType=1 AND mineZone=1 GROUP BY minePage) AS a  WHERE a.size>1";
            //寻找空矿
            List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" instPlayerId = 0 and mineZone = 1 and mineType = " + mineType, 0);
            int size = mineList.size();
            if (size > 0) {
                // 高级矿区，第n页
                InstPlayerMine mine = mineList.get(0);
                //刷新普通区域
                MessageData playerMsgData = refreshSenior(channelId, mine.getMinePage());
                MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
            } else {
                MessageUtil.sendFailMsg(channelId, msgMap, "当前没有空的该类型资源矿");
            }
        }
    }

    /**
     * 寻矿，找玩家自己占领的矿
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void searchMine(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineid = (Integer) msgMap.get("mineId"); //矿ID
        //进入逻辑
        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        if (mineid <= 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }


        //不需要判断这个矿是否被占领，因为只要发来这协议都得刷新数据，等同于更新
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;

        if (zoneType > 1 || zoneType < 0 || pageIndex < 1 || pageIndex > mineMaxPage) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        MessageData playerMsgData = new MessageData();


        //刷新高级区域
        playerMsgData = refreshSenior(channelId, pageIndex);


        //玩家矿不存在时判断
        List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
        int size = mineList.size();
        if (size == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
        } else {
            MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
        }


    }

    /**
     * 驱逐协助者
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void expelAssistant(Map<String, Object> msgMap, String channelId) throws Exception {
        /*
        *     驱逐协助者已经取消
       *
        */
        if (true)
            return;

        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineid = (Integer) msgMap.get("mineId"); //矿ID
        int assistid = (Integer) msgMap.get("assistId"); //协助者ID

        if (assistid <= 0 || mineid <= 0) { //协助者ID不许是0
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //进入逻辑
        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数

        //检查矿数据数据有效性
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;

        //检查矿ID的真实性
        if (zoneType != 1 || pageIndex > mineMaxPage || pageIndex < 1) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }
        synchronized (ParamConfig.mineLock) { //加锁处理
            List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
            int size = mineList.size();
            if (size == 0) {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, "驱逐失败，资源矿有变动", playerMsgData);
                return;
            }

            InstPlayerMine mine = null;
            mine = mineList.get(0);
            int pid = mine.getInstPlayerId();
            int assist1 = mine.getAssistId1();

            if (pid != instPlayerId) {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, "驱逐失败，资源矿有变动", playerMsgData);
                return;
            }

            if (assistid == assist1) { //踢人成功
                DictMineType type = MineUtil.getMineTypeObj(mine.getMineType());
                if (type == null) {
                    return;
                }
                accountMine(assistid, mine, type, 1, 8, pid);

                mine.setAssistId1(0);
                mine.setATime1(NULL);
                getInstPlayerMineDAL().update(mine, 0);

                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);

                MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);



            } else {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
            }
        }

    }

    /**
     * 协助矿
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void assistMiner(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineid = (Integer) msgMap.get("mineId"); //矿ID


        if (mineid <= 0) { //矿ID不许是0
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //检查矿数据数据有效性
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;

        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        if (pageIndex > mineMaxPage) {
            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, 1);//第一页
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
            return;
        }

        //检查玩家是否占矿了
        List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().
                getList(" instPlayerId = " + instPlayerId + " or assistId1 = " + instPlayerId, 0);

        if (instPlayerMineList.size() > 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "您已有资源矿，无法协助");
            return;
        }

        synchronized (ParamConfig.mineLock) { //加锁处理
            //进入逻辑
            List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
            int size = mineList.size();
            InstPlayerMine mine = null;

            if (size == 0 || zoneType == 0) {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
                return;
            }

            //消耗检查
            mine = mineList.get(0);
            int mineType = mine.getMineType();

            int len = DictList.dictMineTypeList.size();
            boolean permit = false;
            int costType = 0;
            int costValue = 0;
            for (int i = 0; i < len; i++) {
                DictMineType type = DictList.dictMineTypeList.get(i);
                if (type.getMineType() == mineType) {
                    costType = type.getSlaveCsType();
                    costValue = type.getSlaveCsValue();
                    permit = true;
                    break;
                }
            }
            if (len == 0 || !permit) {
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
                return;
            }

            switch (costType) {
                case 0://消耗耐力
                    if (instPlayer.getVigor() < costValue) {
                        MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_notVigor);
                        return;
                    }
                    break;
                case 1://消耗元宝
                    if (instPlayer.getGold() < costValue) {
                        MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_goldNotEnough);
                        return;
                    }
                    break;
                default:
                    MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_dataError);
                    return;
            }

            //检查是否有矿主，是否有协助者已满
            int pid = mine.getInstPlayerId();
            int assist1 = mine.getAssistId1();
            if (pid <= 0) {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
                return;
            }

            if (assist1 > 0) {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
                return;
            }

            //检查协助者是否有公会
            int playerUnionId = 0;
            List<InstUnionMember> unionMembers = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
            if (unionMembers.size() > 0) {
                playerUnionId = unionMembers.get(0).getInstUnionId();
            }
            if (playerUnionId <= 0) {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, "没有加入联盟无法协助", playerMsgData);
                return;
            }
            //检查和矿主公会是否一样
            List<InstUnionMember> unionMaster = getInstUnionMemberDAL().getList(" instPlayerId = " + pid, 0);
            if (unionMaster.size() > 0) {
                int unionMasterId = unionMaster.get(0).getInstUnionId();
                if (unionMasterId <= 0 || playerUnionId != unionMasterId) {
                    //刷新高级区域
                    MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                    MessageUtil.sendFailMsg(channelId, msgMap, "您跟矿主不是同一个联盟无法协助", playerMsgData);
                    return;
                }
            } else {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, "您跟矿主不是同一个联盟无法协助", playerMsgData);
                return;
            }


            MessageData syncMsgData = new MessageData();
            //消耗元宝
            //消耗耐力
            switch (costType) {
                case 0:
                    int maxVigor = DictMapUtil.getSysConfigIntValue(StaticSysConfig.maxVigor);
                    if (instPlayer.getVigor() >= maxVigor && (instPlayer.getVigor() - costValue) < maxVigor) {
                        instPlayer.setLastVigorRecoverTime(DateUtil.getCurrTime());
                    }
                    instPlayer.setVigor(instPlayer.getVigor() - costValue);
                    getInstPlayerDAL().update(instPlayer, instPlayerId);
                    OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
                    MessageUtil.sendSyncMsg(channelId, syncMsgData);
                    break;
                case 1:
                    PlayerUtil.goldStatics(instPlayer, GoldStaticsType.del, costValue, msgMap);
                    getInstPlayerDAL().update(instPlayer, instPlayerId);
                    OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, instPlayer, instPlayer.getId(), instPlayer.getResult(), syncMsgData);
                    MessageUtil.sendSyncMsg(channelId, syncMsgData);
                    break;
            }

            //进入协助位

            mine.setAssistId1(instPlayerId);
            mine.setATime1(DateUtil.getCurrTime());

            getInstPlayerMineDAL().update(mine, 0);

            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, pageIndex);

            MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);

        }

    }

    /**
     * 放弃占领
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void giveUpOccupy(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineid = (Integer) msgMap.get("mineId"); //矿ID


        if (mineid <= 0) { //矿ID不许是0
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //检查矿数据数据有效性
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;

        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        if (pageIndex > mineMaxPage) {
            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, 1);//第一页
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
            return;
        }

        synchronized (ParamConfig.mineLock) { //加锁处理
            //进入逻辑
            List<InstPlayerMine> mineList;
            if (zoneType == 0) { //普通矿可以有重复，所以通过玩家ID来寻找
                mineList = getInstPlayerMineDAL().getList(" instPlayerId = " + instPlayerId, 0);
            } else {
                mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
            }
            int size = mineList.size();
            InstPlayerMine mine = null;

            if (size == 0) {
                //刷新高级区域，无视当普通，强制刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
                return;
            }


            //产出检查
            mine = mineList.get(0);
            int mineType = mine.getMineType();
            DictMineType type = null;

            int len = DictList.dictMineTypeList.size();
            boolean permit = false;

            for (int i = 0; i < len; i++) {
                DictMineType tmptype = DictList.dictMineTypeList.get(i);
                if (tmptype.getMineType() == mineType) {

                    type = tmptype;
                    permit = true;
                    break;
                }
            }
            if (len == 0 || !permit) {
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
                return;
            }

            //检查是否是这个矿的协助者
            int pid = mine.getInstPlayerId();
            int assist1 = mine.getAssistId1();

            if (instPlayerId != pid) {
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
                return;
            }

            //给矿主结算
            accountMine(pid, mine, type, 0, 6, pid);
            //排除普通区域
            if (zoneType > 0) {
                //协助者1结算
                if (assist1 > 0) {
                    accountMine(assist1, mine, type, 1, 9, pid);
                }
            }

            //放弃
            mine.setInstPlayerId(0);
            mine.setMasterTime(NULL);
            mine.setAssistId1(0);
            mine.setATime1(NULL);


            if(zoneType == 0){
                getInstPlayerMineDAL().deleteById(mine.getId(), 0);
            }else {
                getInstPlayerMineDAL().update(mine, 0);
            }

            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, pageIndex);

            MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
        }
    }


    /**
     * 放弃协助
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void giveUpAssist(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineid = (Integer) msgMap.get("mineId"); //矿ID


        if (mineid <= 0) { //矿ID不许是0
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //检查矿数据数据有效性
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;

        synchronized (ParamConfig.mineLock) { //加锁处理
            //进入逻辑
            List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
            int size = mineList.size();
            InstPlayerMine mine = null;

            if (size == 0 || zoneType == 0) {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
                return;
            }

            //产出检查

            mine = mineList.get(0);
            int mineType = mine.getMineType();

            //检查是否是这个矿的协助者
            int assist1 = mine.getAssistId1();

            if (instPlayerId != assist1) {
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
                return;
            }

//            // 计算时间间隔 = 当前时间 - 上次时间
            long intevalTime = DateUtil.getCurrMill() - DateUtil.getMillSecond((instPlayerId == assist1) ? mine.getATime1() : mine.getATime2());
//
            DictMineType type = MineUtil.getMineTypeObj(mineType);
            if (type == null) {
                return;
            }
            accountMine(instPlayerId, mine, type, (instPlayerId == assist1 ? 1 : 2), 7, instPlayerId);

            int oldextratime = mine.getExtratime();
            if (intevalTime > 0) {
                mine.setExtratime(oldextratime + (int) (intevalTime / 1000));
            }

            mine.setAssistId1(0);
            mine.setATime1(NULL);


            getInstPlayerMineDAL().update(mine, 0);

            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, pageIndex);

            MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
        }
    }

    /**
     * 占矿
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void occupyMine(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineid = (Integer) msgMap.get("mineId"); //矿ID
        int masterid = (Integer) msgMap.get("minerId"); //矿主ID
        int actionType = masterid == 0 ? 0 : 1;          //0.占矿  1.抢矿


        if (mineid <= 0) { //矿ID不许是0
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //检查矿数据数据有效性
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;
        int mineIndex = mineid % 10;


        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        if (pageIndex > mineMaxPage) {
            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, 1);//第一页
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
            return;
        }

        if (mineIndex == 0) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }
        if (zoneType == 0 && mineIndex > MineUtil.MINE_JUNIOR_ARRAY.length) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }
        if (zoneType > 0 && mineIndex > MineUtil.MINE_SENIOR_ARRAY.length) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }

        //为了防止客户端乱发 区域
        if (zoneType > 1) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }

        int mineType = (zoneType == 0) ? MineUtil.MINE_JUNIOR_ARRAY[mineIndex - 1] : MineUtil.MINE_SENIOR_ARRAY[mineIndex - 1];
        //高级矿有些是动态变动的 所以得需要查库获得矿类型
        if(zoneType > 0) {
            List<InstPlayerMine> list = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
            if (list.size() > 0) {
                InstPlayerMine mineTmp = list.get(0);
                mineType = mineTmp.getMineType();
            }
        }

        //消耗检查
        if (!checkCost(msgMap, channelId, instPlayer, mineType)) {
            return;
        }
        synchronized (ParamConfig.mineLock) { //加锁处理
            //检查玩家是否占矿了
            List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().
                    getList(" instPlayerId = " + instPlayerId + " or assistId1 = " + instPlayerId, 0);

            if (instPlayerMineList.size() > 0) {
                MessageUtil.sendFailMsg(channelId, msgMap, "您已有资源矿，无法重复占领");
                return;
            }

            //进入逻辑
            List<InstPlayerMine> mineList;

            if (zoneType == 0) { //普通矿可以有重复，所以通过玩家ID来寻找
                //来真正的占矿逻辑
                int targetId = DictMapUtil.getSysConfigIntValue(StaticSysConfig.targetNpcid);
                MessageData playerMsgData = new MessageData();
                playerMsgData = EnemyPlayerUtil.retEnemyNPCInfoMsgData(playerMsgData, targetId);  //随机取一个 竞技场NPC数据
                playerMsgData.putIntItem("fightType", 0); //和NPC战斗
                playerMsgData.putIntItem("playerId", targetId); //NPC_ID

                MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
                return;
            }

            mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);

            int size = mineList.size();
            if (size == 0) {
                MessageUtil.sendFailMsg(channelId, msgMap, "找不到资源矿:" + mineid);
                return;
            }
            InstPlayerMine mine = mineList.get(0);


            if (actionType == 0 && mine.getInstPlayerId() > 0) { //占矿时发现 矿里有人
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, "矿已被其他玩家占领，您来晚了", playerMsgData);
                return;
            }

            if (actionType == 1 && mine.getInstPlayerId() == 0) { //抢夺时发现，矿里没人

                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);
                MessageUtil.sendFailMsg(channelId, msgMap, "矿主已跑，无法抢夺", playerMsgData);
                return;
            }
            MessageData playerMsgData = new MessageData();
            if (mine.getInstPlayerId() == 0) {
                //来真正的占矿逻辑
                int targetId = DictMapUtil.getSysConfigIntValue(StaticSysConfig.targetNpcid);
                playerMsgData = EnemyPlayerUtil.retEnemyNPCInfoMsgData(playerMsgData, targetId);  //随机取一个 竞技场NPC数据
                playerMsgData.putIntItem("fightType", 0); //和NPC战斗
                playerMsgData.putIntItem("playerId", targetId); //NPC_ID

                MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
                return;
            } else {
                //来真正的抢矿逻辑
                int targetId = mine.getInstPlayerId();
                playerMsgData = EnemyPlayerUtil.retEnemyPlayerInfoMsgData(playerMsgData, targetId);
                playerMsgData.putIntItem("fightType", 1); //和玩家战斗
                playerMsgData.putIntItem("playerId", targetId); //player_ID

                MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
                return;
            }
        }

    }

    /**
     * 占矿胜利
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void mineFightWin(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int actionType = (Integer) msgMap.get("fightType");     //0.占矿  1.抢矿
        int masterid = (Integer) msgMap.get("id");              //矿主或者NPC ID
        int mineid = (Integer) msgMap.get("mineId");            //矿ID
        String coredata = (String) msgMap.get("coredata");       //1:2_3|3_3;2:2_3|3_3;3:2_3|3_3  1-卡牌 2-装备 3-功法法宝

        //为了NPC时 复杂度降低 矿主ID写成0
        masterid = actionType == 0 ? 0 : masterid;


        //用于验证玩家是否利用烧饼等修改器
        if (VerifyUtil.vfpullBlack(channelId, msgMap, instPlayer, coredata)) {
            return;
        }

        if (mineid <= 0) { //矿ID不许是0
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //检查矿数据数据有效性
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;
        int mineIndex = mineid % 10;


        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        if (pageIndex > mineMaxPage) {
            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, 1);//第一页
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
            return;
        }

        if (mineIndex == 0) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }
        if (zoneType == 0 && mineIndex > MineUtil.MINE_JUNIOR_ARRAY.length) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }
        if (zoneType > 0 && mineIndex > MineUtil.MINE_SENIOR_ARRAY.length) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }

        //为了防止客户端乱发 区域
        if (zoneType > 1) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }

        int mineType = (zoneType == 0) ? MineUtil.MINE_JUNIOR_ARRAY[mineIndex - 1] : MineUtil.MINE_SENIOR_ARRAY[mineIndex - 1];

        //高级矿有些是动态变动的 所以得需要查库获得矿类型
        if(zoneType > 0) {
            List<InstPlayerMine> list = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
            if (list.size() > 0) {
                InstPlayerMine mineTmp = list.get(0);
                mineType = mineTmp.getMineType();
            }
        }

        //消耗检查
        if (!checkCost(msgMap, channelId, instPlayer, mineType)) {
            return;
        }

        synchronized (ParamConfig.mineLock) { //加锁处理
            //检查玩家是否占矿了
            List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().
                    getList(" instPlayerId = " + instPlayerId + " or assistId1 = " + instPlayerId, 0);

            if (instPlayerMineList.size() > 0) {
                MessageUtil.sendFailMsg(channelId, msgMap, "您已有资源矿，无法重复占领");
                return;
            }
            //进入逻辑, 占领普通矿
            if (zoneType == 0) {
                //资源矿的消耗
                if (!consumeCost(msgMap, channelId, instPlayer, mineType)) {
                    return;
                }
                //来真正的占矿逻辑
                InstPlayerMine playerMine = new InstPlayerMine();
                playerMine.setMineId(mineid);
                playerMine.setMineType(mineType);
                playerMine.setMineZone(zoneType);
                playerMine.setMinePage(pageIndex);
                playerMine.setMineIndex(mineIndex);
                playerMine.setInstPlayerId(instPlayerId);
                playerMine.setMasterTime(DateUtil.getCurrTime());
                playerMine.setAssistId1(0);
                playerMine.setATime1(NULL);
                playerMine.setWeather(MineUtil.getWeather(pageIndex));

                getInstPlayerMineDAL().add(playerMine, 0);

                //刷新矿数据
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);

                // 初始化矿区
                playerMsgData = initialMine(playerMsgData);

                MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
                return;
            }

            //进入逻辑, 占领高级或者抢夺高级
            List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);

            int size = mineList.size();
            if (size == 0) {
                MessageUtil.sendFailMsg(channelId, msgMap, "找不到资源矿:" + mineid);
                return;
            }
            InstPlayerMine mine = mineList.get(0);
            if (actionType == 0 && mine.getInstPlayerId() > 0) { //占矿时发现 矿里有人
                //刷新高级区域
                MessageData playerMsgData = refreshSenior(channelId, pageIndex);

                // 初始化矿区
                playerMsgData = initialMine(playerMsgData);
                MessageUtil.sendFailMsg(channelId, msgMap, "hide", playerMsgData);
                return;
            }

            if (mine.getInstPlayerId() > 0) {
                //来真正的抢矿逻辑
                //发来的ID 和 实际矿主ID  不符时
                if (masterid != mine.getInstPlayerId()) {
                    //刷新高级区域
                    MessageData playerMsgData = refreshSenior(channelId, pageIndex);

                    // 初始化矿区
                    playerMsgData = initialMine(playerMsgData);
                    MessageUtil.sendFailMsg(channelId, msgMap, "hide", playerMsgData);
                    return;
                }
            }

            //资源矿的消耗
            if (!consumeCost(msgMap, channelId, instPlayer, mineType)) {
                return;
            }

            //占矿 或者 抢矿时 发现矿主已经没有了
            if (actionType == 0 || mine.getInstPlayerId() == 0) {
                //来真正的占矿逻辑
//                InstPlayerMine playerMine = new InstPlayerMine();
//                mine.setMineId(mineid);
//                playerMine.setMineType(mineType);
//                playerMine.setMineZone(zoneType);
//                playerMine.setMinePage(pageIndex);
//                playerMine.setMineIndex(mineIndex);
                mine.setInstPlayerId(instPlayerId);
                mine.setMasterTime(DateUtil.getCurrTime());
                mine.setAssistId1(0);
                mine.setATime1(NULL);

                getInstPlayerMineDAL().update(mine, 0);


            } else {
                //来真正的抢矿逻辑
                int pid = mine.getInstPlayerId();
                int assitid1 = mine.getAssistId1();

                DictMineType type = MineUtil.getMineTypeObj(mineType);
                if (type == null) {
                    return;
                }
                //矿被抢夺，所以需要给老矿主结算
                accountMine(pid, mine, type, 0, 4, instPlayerId);
                if (assitid1 > 0) {
                    accountMine(assitid1, mine, type, 1, 5, instPlayerId);
                }

                //成功抢夺
//                mine.setMineId(mineid);
//                mine.setMineType(mineType);
//                mine.setMineZone(zoneType);
//                mine.setMinePage(pageIndex);
//                mine.setMineIndex(mineIndex);
                mine.setInstPlayerId(instPlayerId);
                mine.setMasterTime(DateUtil.getCurrTime());
                mine.setAssistId1(0);
                mine.setATime1(NULL);

                getInstPlayerMineDAL().update(mine, 0);

            }

            //刷新矿数据
            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, pageIndex);

            // 初始化矿区
            playerMsgData = initialMine(playerMsgData);

            MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);
        }
    }


    /**
     * 占矿反击
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/09/28
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void mineBeatBack(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int masterid = (Integer) msgMap.get("minerId"); //矿主ID


        //检查玩家是否占矿了
        List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().
                getList(" instPlayerId = " + instPlayerId + " or assistId1 = " + instPlayerId, 0);

        if (instPlayerMineList.size() > 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "您已有资源矿，无法重复占领");
            return;
        }

        //检查反击对手是否占矿了
        List<InstPlayerMine> enermyrMineList = getInstPlayerMineDAL().
                getList(" instPlayerId = " + masterid, 0);

        if (enermyrMineList.size() == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "该玩家未占领矿");
            return;
        }

        InstPlayerMine mine = enermyrMineList.get(0);

        //检查矿数据数据有效性
        int zoneType = mine.getMineId() / 10000;
//        int mineIndex = mine.getMineId() % 10;

        if (zoneType == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "该玩家未占领矿");
            return;
        }
//        int mineType = MineUtil.MINE_SENIOR_ARRAY[mineIndex - 1];
        int mineType = mine.getMineType();


        //消耗检查
        if (!checkCost(msgMap, channelId, instPlayer, mineType)) {
            return;
        }

        //进入逻辑
        MessageData playerMsgData = new MessageData();

        int targetId = mine.getInstPlayerId();
        playerMsgData = EnemyPlayerUtil.retEnemyPlayerInfoMsgData(playerMsgData, targetId);
        playerMsgData.putIntItem("mineId", mine.getMineId());
        playerMsgData.putIntItem("fightType", 1); //和玩家战斗
        playerMsgData.putIntItem("playerId", targetId); //player_ID

        MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);


    }


    /**
     * 联盟协助
     *
     * @param msgMap
     * @param channelId
     * @throws Exception
     * @author cui
     * @date 15/10/16
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void unionAssist(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineid = (Integer) msgMap.get("mineId");    //矿ID
        int masterid = (Integer) msgMap.get("minerId");   //矿主ID


        if (mineid <= 0 || masterid <= 0) { //矿ID不许是0
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //检查矿数据数据有效性
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;

        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        if (pageIndex > mineMaxPage || zoneType == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //检查玩家是否占矿了
        List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().
                getList(" instPlayerId = " + instPlayerId + " or assistId1 = " + instPlayerId, 0);

        if (instPlayerMineList.size() > 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, "您已有资源矿，无法协助");
            return;
        }

        synchronized (ParamConfig.mineLock) { //加锁处理
            //进入逻辑
            List<InstPlayerMine> mineList = getInstPlayerMineDAL().getList(" mineId = " + mineid + " and  instPlayerId = " + masterid, 0);
            int size = mineList.size();

            if (size == 0) {
                MessageUtil.sendFailMsg(channelId, msgMap, "矿主已更换或离开，协助已失效");
                return;
            }
            InstPlayerMine mine = null;
            mine = mineList.get(0);
            if (mine.getAssistId1() > 0) {
                MessageUtil.sendFailMsg(channelId, msgMap, "协助者已满");
                return;
            }


            //检查是否有矿主，是否有协助者已满
            int pid = mine.getInstPlayerId();

            //检查协助者是否有公会
            int playerUnionId = 0;
            List<InstUnionMember> unionMembers = getInstUnionMemberDAL().getList(" instPlayerId = " + instPlayerId, 0);
            if (unionMembers.size() > 0) {
                playerUnionId = unionMembers.get(0).getInstUnionId();
            }
            if (playerUnionId <= 0) {
                MessageUtil.sendFailMsg(channelId, msgMap, "没有加入联盟无法协助");
                return;
            }
            //检查和矿主公会是否一样
            List<InstUnionMember> unionMaster = getInstUnionMemberDAL().getList(" instPlayerId = " + pid, 0);

            if(unionMaster.size() == 0 || (playerUnionId != unionMaster.get(0).getInstUnionId())){
                MessageUtil.sendFailMsg(channelId, msgMap, "您跟矿主不是同一个联盟无法协助");
                return;
            }

            //判断活动快要借宿
            ArrayList<String> time = MineUtil.checkAcitivityTime();
            boolean nearTimeout = false;
            //保证time是配对的
            // 验证是否在活动期内
            String currYmd = DateUtil.getYmdDate();
            long currMill = DateUtil.getCurrMill();
            int slavePerTime = DictMapUtil.getSysConfigIntValue(StaticSysConfig.slavePerTime) * 3600;
            for (int i = 0; i < time.size(); i += 2) {
                String startTime = currYmd + SEP_SPACE + time.get(i);
                String endTime = currYmd + SEP_SPACE + time.get(i + 1);

                if (currMill >= DateUtil.getMillSecond(startTime) && currMill <= DateUtil.getMillSecond(endTime)) {
                    if(DateUtil.getMillSecond(endTime) - currMill < slavePerTime){
                        nearTimeout = true;
                    }
                    break;
                }
            }
            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, pageIndex);
            // 初始化矿区
            playerMsgData = initialMine(playerMsgData);

            //检查特殊奖励是否在
            if(mine.getAssistThingState() > 0){
                playerMsgData.putIntItem("type",2); //协助奖励已经被领完
            }else if(nearTimeout){
                playerMsgData.putIntItem("type",1); //资源矿不足1小时

            }else{
                playerMsgData.putIntItem("type",0); //直接跳到矿主的矿
            }

            MessageUtil.sendSuccMsg(channelId, msgMap, playerMsgData);

        }
    }

    /**
     * 抢矿或占矿 失败
     * @author  cui
     * @date    2015/12/22
     * @param msgMap
     * @param channelId
     * @throws Exception
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void mineFail(Map<String, Object> msgMap, String channelId) throws Exception {
        int instPlayerId = getInstPlayerId(channelId);    // 玩家实例Id

        if (instPlayerId == 0) {
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);

            return;
        }

        InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId, instPlayerId);
        if (!checkRecognize(msgMap, channelId, instPlayer)) {
            return;
        }

        //读取协议数据
        int mineid = (Integer) msgMap.get("mineId");            //矿ID

        if (mineid <= 0) { //矿ID不许是0
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_PlayerIdVerfy);
            return;
        }

        //检查矿数据数据有效性
        int zoneType = mineid / 10000;
        int pageIndex = (mineid % 10000) / 10;
        int mineIndex = mineid % 10;


        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        if (pageIndex > mineMaxPage) {
            //刷新高级区域
            MessageData playerMsgData = refreshSenior(channelId, 1);//第一页
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh, playerMsgData);
            return;
        }

        if (mineIndex == 0) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }
        if (zoneType == 0 && mineIndex > MineUtil.MINE_JUNIOR_ARRAY.length) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }
        if (zoneType > 0 && mineIndex > MineUtil.MINE_SENIOR_ARRAY.length) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }

        //为了防止客户端乱发 区域
        if (zoneType > 1) {
            //越界了
            MessageUtil.sendFailMsg(channelId, msgMap, StaticCnServer.fail_mineRefresh);
            return;
        }

        int mineType = (zoneType == 0) ? MineUtil.MINE_JUNIOR_ARRAY[mineIndex - 1] : MineUtil.MINE_SENIOR_ARRAY[mineIndex - 1];

        //高级矿有些是动态变动的 所以得需要查库获得矿类型
        if(zoneType > 0) {
            List<InstPlayerMine> list = getInstPlayerMineDAL().getList(" mineId = " + mineid, 0);
            if (list.size() > 0) {
                InstPlayerMine mineTmp = list.get(0);
                mineType = mineTmp.getMineType();
            }
        }

        //消耗检查
        if (!checkCost(msgMap, channelId, instPlayer, mineType)) {
            return;
        }
        //资源矿的消耗
        if (!consumeCost(msgMap, channelId, instPlayer, mineType)) {
            return;
        }

        MessageUtil.sendSuccMsg(channelId, msgMap, new MessageData());

    }

    @Test
    public void testSql() throws Exception {
//        int mineType = 1, zoneType = 1, fullSize = 1;
//        String sql = String.format("SELECT minePage FROM (SELECT minePage,count(minePage) as size FROM Inst_Player_Mine WHERE mineType=%s AND mineZone=%s GROUP BY minePage) AS a  WHERE a.size>%s", mineType, zoneType, fullSize);
//        System.out.println(sql);
//        List<Map<String, Object>> minePageList = getInstPlayerMineDAL().sqlHelper(sql);
//        System.out.println(minePageList.get(0).get("minePage"));
//
//        StringBuilder sb = new StringBuilder();
//        sb.append("how many peple was dead");
//        sb.deleteCharAt(sb.length() - 1);
//        System.out.println(sb.toString());
        LoadResourceUtil.loadResource();
        DictData.loadDictData();
//        DictMineType ironMine = DictMap.dictMineTypeMap.get(1 + "");
//        DictMineType copperMine = DictMap.dictMineTypeMap.get(2 + "");
//        DictMineType silverMine = DictMap.dictMineTypeMap.get(3 + "");
//        DictMineType goldMine = DictMap.dictMineTypeMap.get(4 + "");

//        System.out.println(goldMine.getId());

//        int stime2 = DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeStart);
//        int etime2 = DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeEnd);
//        System.out.println("s:"+stime2+" e:"+etime2);
        int size = getInstPlayerMineDAL().getCount("");
        List<InstPlayerMine> mine = getInstPlayerMineDAL().getListPagination(1, 2, "", 0);
        System.out.println("count:" + size + " min: " + mine.size() + " id:" + mine.get(1).getId());

//        ArrayList<String> time = checkAcitivityTime();
//        boolean timeout = true;
//        //保证time是配对的
//        // 验证是否在活动期内
//        String currYmd = DateUtil.getYmdDate();
//        long currMill = DateUtil.getCurrMill();
//        for (int i = 0; i < time.size(); i += 2) {
//            String startTime = currYmd + SEP_SPACE + time.get(i);
//            String endTime = currYmd + SEP_SPACE + time.get(i + 1);
//            if (currMill >= DateUtil.getMillSecond(startTime) && currMill <= DateUtil.getMillSecond(endTime)) {
//                timeout = false;
////                break;
//            }
//        }
//
//        System.out.println("time:" + timeout);
    }

    @Test
    public void testRand() throws Exception {

//        DateUtil.getMillSecond("2015-10-09 02:00:00");
        long ttt = DateUtil.string2MillSecond("2015-10-09 02:00:00") / 1000;
        System.out.println("时间：" + ttt);
        int count = 0;
        int max = 1;
        for (int i = 0; i < max; i++) {
            int rnd = MineUtil.getSeedRand(1, 100);
            rnd = Math.abs(rnd);
            System.out.println("print rand number:" + rnd + "\t\t\t\t" + ((rnd > 50) ? "暴击" : ""));
            if (rnd > 50) count++;
//            ThirdTest third = new ThirdTest();
//            third.setid(i);
//            third.start();
//            Thread.sleep(0);
        }

//        while (testCount < max){
//            Thread.sleep(0);
//        }

//        Thread.sleep(1000);
//        System.out.println("线程数量："+testCount);
//        System.out.println("统计暴击次数："+cri);
//
//        for(int i : array){
//            System.out.println(i);
//        }
//        count = 0;
//        for (int i = 0; i < 500; i++) {
//
//            int rnd2 = getSeddRand(1,10000);
//            rnd2 = Math.abs(rnd2);
//            System.out.println("print rand number:"+rnd2);
//            if(rnd2 < 2000) count++;
//        }
        System.out.println("统计次数：" + count);

    }
//    static int testCount = 0;
//    static int cri = 0;
//    static ArrayList<Integer> array = new ArrayList<Integer>();
//
//    class ThirdTest extends Thread{
//        public int tid = 0;
//        void setid(int id){
//            tid = id;
//        }
//        @Override
//        public void run() {
//            super.run();
////            System.out.println("================线程ID："+tid);
//            testCount++;
//            int count = 0;
//            for (int i = 0; i < 10; i++) {
////                int rnd = getSeddRand(1,100);
//                array.add(getSeddRand(1,100));
////                System.out.println(getSeddRand(1,100));
////                rnd = Math.abs(rnd);
////                System.out.println("线程("+tid+")："+rnd+"\t\t\t\t" + ((rnd>50)?"暴击":""));
////                if(rnd>50)cri++;
//            }
//        }
//    }
}


