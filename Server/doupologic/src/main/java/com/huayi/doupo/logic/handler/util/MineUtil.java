package com.huayi.doupo.logic.handler.util;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.*;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.socket.Player;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.model.statics.custom.CustomMarqueeType;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.util.marquee.MarqueeUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import com.huayi.doupo.logic.util.PlayerMapUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by cui on 2015/10/16 0016.
 */
public class MineUtil extends DALFactory {
    public static int[] WEATHER_LIST;   //天气数组 每页天气不同

//    private static boolean setUpSeed = true;

    private static long seed = 3000;


    private static ArrayList<String> actTimeList;
    private static int firstTimeStart;
    private static int firstTimeEnd;
    private static int secondTimeStart;
    private static int secondTimeEnd;

    public static final String SEP_SPACE = " ";       // 空格字符串
    public static final String NULL = "";       // 空字符串

    public static final int MINE_TYPE_IRON = 0;    // 铁矿
    public static final int MINE_TYPE_COPPER = 1;  // 铜矿
    public static final int MINE_TYPE_SILVER = 2;  // 银矿
    public static final int MINE_TYPE_GOLD = 3;    // 金矿

    public static final int[] MINE_JUNIOR_ARRAY = new int[]{0}; //铁矿
    public static final int[] MINE_SENIOR_ARRAY = new int[]{2, 2, 3, 1, 1}; //银矿，金矿，银矿，铜矿，铜矿

    public synchronized static int getSeedRand(int min, int max) {
//        if (setUpSeed) {
//            setUpSeed = false;
//            seed = DateUtil.string2MillSecond("2015-10-09 02:00:00");
////            System.out.println("种子："+seed);
//        }
        final long multiplier = 0x5DEECE66DL;
        final long addend = 0xBL;
        seed = (seed * multiplier + addend) % 640000000000L;
        return ((int) (Math.abs(seed % (max - min + 1)) + min));
    }

    public synchronized static void setUpSeed(String newSeed) throws Exception {
//        setUpSeed = true;
//        seed = DateUtil.string2MillSecond("2015-10-09 02:00:00");
        seed = DateUtil.string2MillSecond(newSeed);
        LogUtil.warn("种子：" + seed);
    }


    /**
     * 检查活动开启时间
     *
     * @return
     * @author cui
     * @date 16/10/2015
     */
    public static ArrayList<String> checkAcitivityTime() {
        boolean result = false;
        int start1 = DictMapUtil.getSysConfigIntValue(StaticSysConfig.firstTimeStart);
        if (start1 != firstTimeStart) {
            result = true;
            firstTimeStart = start1;
        }
        int end1 = DictMapUtil.getSysConfigIntValue(StaticSysConfig.firstTimeEnd);
        if (end1 != firstTimeEnd) {
            result = true;
            firstTimeEnd = end1;
        }
        int start2 = DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeStart);
        if (start2 != secondTimeStart) {
            result = true;
            secondTimeStart = start2;
        }

        int end2 = DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeEnd);
        if (end2 != secondTimeEnd) {
            result = true;
            secondTimeEnd = end2;
        }

        if (actTimeList == null) {
            actTimeList = new ArrayList<>();
        }
        if (result) {
            actTimeList.clear();
            //结束时间比开始时间小时，跨天了，需要分段
            if (firstTimeStart > firstTimeEnd) {
                //分一段
                //开始
                actTimeList.add(" 00:00:00");
                //结束
                if (firstTimeEnd < 10)
                    actTimeList.add(" 0" + firstTimeEnd + ":00:00");
                else
                    actTimeList.add(" " + firstTimeEnd + ":00:00");

                //分一段
                //开始
                if (firstTimeStart < 10)
                    actTimeList.add(" 0" + firstTimeStart + ":00:00");
                else
                    actTimeList.add(" " + firstTimeStart + ":00:00");
                //结束
                actTimeList.add(" 23:59:59");
            }
            if (firstTimeStart < firstTimeEnd) {
                //分一段
                //开始
                if (firstTimeStart < 10)
                    actTimeList.add(" 0" + firstTimeStart + ":00:00");
                else
                    actTimeList.add(" " + firstTimeStart + ":00:00");

                //结束
                if (firstTimeEnd < 10)
                    actTimeList.add(" 0" + firstTimeEnd + ":00:00");
                else
                    actTimeList.add(" " + firstTimeEnd + ":00:00");
            }

            if (secondTimeStart > secondTimeEnd) {
                //分一段
                //开始
                actTimeList.add(" 00:00:00");
                //结束
                if (secondTimeEnd < 10)
                    actTimeList.add(" 0" + secondTimeEnd + ":00:00");
                else
                    actTimeList.add(" " + secondTimeEnd + ":00:00");

                //分一段
                //开始
                if (secondTimeStart < 10)
                    actTimeList.add(" 0" + secondTimeStart + ":00:00");
                else
                    actTimeList.add(" " + secondTimeStart + ":00:00");
                //结束
                actTimeList.add(" 23:59:59");
            }
            if (secondTimeStart < secondTimeEnd) {
                //分一段
                //开始
                if (secondTimeStart < 10)
                    actTimeList.add(" 0" + secondTimeStart + ":00:00");
                else
                    actTimeList.add(" " + secondTimeStart + ":00:00");

                //结束
                if (secondTimeEnd < 10)
                    actTimeList.add(" 0" + secondTimeEnd + ":00:00");
                else
                    actTimeList.add(" " + secondTimeEnd + ":00:00");
            }
        }
        return actTimeList;
    }


    public static void createMine() throws Exception {
        //检查矿是否被创建出来
        int size = getInstPlayerMineDAL().getCount("");
        //矿已经清理了
        if (size <= 0) {
            //初始化矿
            initialMineData();
        } else {
            ArrayList<String> time = checkAcitivityTime();
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
            //已经已经结束，但表里还有数据时强制结算,并重新建表
            if (timeout) {
                //结算 并 初始化矿
                grantAward();


            } else {  //活动期间所以不结算，但需要重新生成天气

                List<InstPlayerMine> mine = getInstPlayerMineDAL().getListPagination(1, 1, "", 0);
                String insertTime = mine.get(0).getInitTime();
                //初始化天气
                initWeather(insertTime);

            }

        }
    }

    public static void initWeather(String insertTime) throws Exception {
        //初始化种子
        setUpSeed(insertTime);

        //开始创建
        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数
        //天气的长度
        int len = DictList.dictMineWeatherList.size();
        WEATHER_LIST = new int[mineMaxPage];
        for (int i = 0; i < mineMaxPage; i++) {
            WEATHER_LIST[i] = MineUtil.getSeedRand(1, len);
        }
    }

    /**
     * 初始化矿数据
     *
     * @author cui
     * @date 16/10/2015
     */
    private static void initialMineData() throws Exception {
        String insertTime = DateUtil.getCurrTime();
        //初始化天气
        initWeather(insertTime);
        //开始创建
        int mineMaxPage = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMaxPage);    // 高级矿最大页数

        int zoneType = 1;
        //创建金矿
        int MINE_GOLD_COUNT = DictMapUtil.getSysConfigIntValue(StaticSysConfig.goleMineMaxCount);  //金矿最高出现的次数

        InstPlayerMine mine = new InstPlayerMine();
        int id = 1;

        //初始化权值
        initRatePower();

        for (int i = 0; i < mineMaxPage; i++) {
            int pageIndex = i + 1;
            for (int index = 0; index < MINE_SENIOR_ARRAY.length; index++) {
                int mineIndex = index + 1;
                int mineId = 1 * 10000 + pageIndex * 10 + (mineIndex); //高级矿区的第n页
                int mineType = MINE_TYPE_COPPER; //先设置默认矿
                mine.setId(id);
                mine.setMineId(mineId);
                if (MINE_SENIOR_ARRAY[index] == MINE_TYPE_GOLD) {
                    if (MINE_GOLD_COUNT > 0) {
                        mineType = MINE_TYPE_GOLD;          //金矿
                        MINE_GOLD_COUNT--;
                    } else {
                        mineType = MINE_TYPE_COPPER;        //铜矿
                    }
                } else {
                    mineType = MINE_SENIOR_ARRAY[index];
                }
                mine.setMineType(mineType);
                mine.setMineZone(zoneType);
                mine.setMinePage(pageIndex);
                mine.setMineIndex(mineIndex);
                mine.setInstPlayerId(0);
                mine.setInitTime(insertTime);
                mine.setMasterTime(NULL);
                mine.setAssistId1(0);
                mine.setATime1(NULL);
                mine.setExtratime(0);
                mine.setWeather(WEATHER_LIST[i]);
                mine.setMasterThing(randThing(0, mineType));
                mine.setMasterThingState(0);
                mine.setAssistThing(randThing(1, mineType));
                mine.setAssistThingState(0);
                mine.setVersion(0);

                getInstPlayerMineDAL().add(mine, 0);
                id++;
            }
        }

    }

    private static int GOLD_RATE_POWER = 0;         //金矿权值总值
    private static int SILVER_RATE_POWER = 0;       //银矿权值总值
    private static int COPPER_RATE_POWER = 0;       //铜矿权值总值

    /**
     * 当需要批量生成物品时先初始化 权值
     */
    private static void initRatePower() {
        GOLD_RATE_POWER = 0;
        SILVER_RATE_POWER = 0;
        COPPER_RATE_POWER = 0;
    }

    /**
     * @param accountType 0.矿主   1.协助者
     * @param mineType    矿类型
     */
    public static String randThing(int accountType, int mineType) {

        int type = accountType * 10 + mineType;
        int TOTAL = 0;
        List<DictMineBoxThing> mineBoxThingList = (List<DictMineBoxThing>) DictMapList.dictMineBoxThingMap.get(type);
//        if (mineType == MINE_TYPE_GOLD) {
//            if (GOLD_RATE_POWER == 0) {
//                for (DictMineBoxThing obj : mineBoxThingList) {
//                    TOTAL += obj.getChance();
//                }
//                GOLD_RATE_POWER = TOTAL;
//            } else {
//                TOTAL = GOLD_RATE_POWER;
//            }
//        } else if (mineType == MINE_TYPE_SILVER) {
//            if (SILVER_RATE_POWER == 0) {
//                for (DictMineBoxThing obj : mineBoxThingList) {
//                    TOTAL += obj.getChance();
//                }
//                SILVER_RATE_POWER = TOTAL;
//            } else {
//                TOTAL = SILVER_RATE_POWER;
//            }
//        } else if (mineType == MINE_TYPE_COPPER) {
//            if (COPPER_RATE_POWER == 0) {
//                for (DictMineBoxThing obj : mineBoxThingList) {
//                    TOTAL += obj.getChance();
//                }
//                COPPER_RATE_POWER = TOTAL;
//            } else {
//                TOTAL = COPPER_RATE_POWER;
//            }
//        } else {
//            for (DictMineBoxThing obj : mineBoxThingList) {
//                TOTAL += obj.getChance();
//            }
//        }
        for (DictMineBoxThing obj : mineBoxThingList) {
            TOTAL += obj.getChance();
        }

        int rnd = getSeedRand(1, TOTAL);
        for (DictMineBoxThing obj : mineBoxThingList) {
            rnd -= obj.getChance();
            if (rnd <= 0) {
                return obj.getThing();
            }
        }

        return NULL;

    }

    /**
     * @param pageindex
     * @return
     * @author cui
     * @date 15/10/17
     */
    public static int getWeather(int pageindex) {
        int index = pageindex - 1;
        if (index < 0) {
            index = 0;
        } else if (index >= WEATHER_LIST.length) {
            index = WEATHER_LIST.length - 1;
        }
        return WEATHER_LIST[index];
    }

    /**
     * 获取矿资源对象
     *
     * @param mineType
     * @return
     */
    public static DictMineType getMineTypeObj(int mineType) {
        //产出检查
        int len = DictList.dictMineTypeList.size();

        for (int i = 0; i < len; i++) {
            DictMineType type = DictList.dictMineTypeList.get(i);
            if (type.getMineType() == mineType) {

                return type;
            }
        }
        return null;
    }

    /**
     * 奖励结算
     *
     * @author cui
     * @date 16/10/2015
     */
    public synchronized static void grantAward() {
        List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().getList("", 0);
        for (InstPlayerMine mine : instPlayerMineList) {
            int pid = mine.getInstPlayerId();
            int assitid1 = mine.getAssistId1();

            DictMineType type = getMineTypeObj(mine.getMineType());
            if (type == null) {
                continue;
            }

            //矿被抢夺，所以需要给老矿主结算
            try {
                if (pid > 0) {
                    accountMine(pid, mine, type, 0, 2, pid);
                }
                if (assitid1 > 0) {
                    accountMine(assitid1, mine, type, 1, 2, assitid1);
                }

            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.error("资源矿发奖时，Error" + " pid=" + pid + " assitid1=" + assitid1, e);
            }
        }

        //准备清理表数据
        try {
            LogUtil.warn("准备删除数据");
            getInstPlayerMineDAL().deleteByWhere("");
            getInstPlayerMineAwardDAL().deleteByWhere("");

            //初始化矿
            initialMineData();
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error("资源矿发奖，清理数据Error", e);
        }
    }

    /**
     * 矿资源结算
     *
     * @param instPlayerId
     * @param mine
     * @param type
     * @param who
     * @throws Exception
     * @author cui
     * @date 09/29/2015
     */
    public static void accountMine(int instPlayerId, InstPlayerMine mine, DictMineType type, int who, int accountType, int enermyId) throws Exception {
        if (instPlayerId <= 0) {
            //理论上不会传入 ID 0的值,但有时候测试代码会强制运行，所以尽量排除
            return;
        }
        String time = "";
        int copperCount;
        int thing83Count;
        int extraCopper = 0;
        int extraThing83 = 0;
        int isExitAward = 0;
        int awardState = 0;
        int resultTime = 2 * 3600; //默认2小时
        String awardThing = "";

        int cycleTime = type.getCycleTime();
        float mineMultiple = DictMapUtil.getSysConfigIntValue(StaticSysConfig.mineMultiple);
        if (mineMultiple < 1) {
            mineMultiple = 1;
        }

        int weather = mine.getWeather();
        if (MineUtil.WEATHER_LIST == null) {
            //初始化天气
            initWeather(mine.getInitTime());
        }
        if (weather < MineUtil.WEATHER_LIST.length) {
            int id = MineUtil.WEATHER_LIST[weather] - 1;
            if (id < 0) id = 0;
            DictMineWeather mineWeather = DictList.dictMineWeatherList.get(id);
            mineMultiple += ((float) mineWeather.getValue()) / 100;
        }


        switch (who) {
            default:
            case 0:
                time = mine.getMasterTime();
                copperCount = type.getMasterCopperCount();
                thing83Count = type.getMasterThing83Count();
                int extraTime = mine.getExtratime();
                String atime1 = mine.getATime1();
                String atime2 = mine.getATime2();


                isExitAward = getInstPlayerMineAwardDAL().getCount(" instPlayerId = " + instPlayerId);
                awardState = mine.getMasterThingState();
                awardThing = mine.getMasterThing();
                resultTime = DictMapUtil.getSysConfigIntValue(StaticSysConfig.masterPerTime) * 3600;
//                //数据去完矿对象不会用到所以在这里修改矿数据
//                mine.setInstPlayerId(0);
//                mine.setMasterTime("");
//                mine.setExtratime(0);

                //计算协助者提供的收益
//				if(atime1 != null){
//					if(atime1.equals("0")){
//						System.out.println("这里需要断点处理一下了!!!:::"+atime1);
//					}
//				}
                if (atime1 != null && !atime1.equals("") && !atime1.equals("0")) {
                    extraTime += (DateUtil.getCurrMill() - DateUtil.getMillSecond(atime1)) / 1000;
                }
                if (atime2 != null && !atime2.equals("") && !atime2.equals("0")) {
                    extraTime += (DateUtil.getCurrMill() - DateUtil.getMillSecond(atime2)) / 1000;
                }
                int profitPercent = DictMapUtil.getSysConfigIntValue(StaticSysConfig.assistAddPercent);
                extraCopper = type.getSlaveCopperCount() * extraTime * profitPercent / (cycleTime * 100);
                extraThing83 = type.getSlaveThing83Count() * extraTime * profitPercent / (cycleTime * 100);

                break;
            case 1:
                time = mine.getATime1();
                copperCount = type.getSlaveCopperCount();
                thing83Count = type.getSlaveThing83Count();

                isExitAward = getInstPlayerMineAwardDAL().getCount(" instPlayerId = " + instPlayerId);
                awardState = mine.getAssistThingState();
                awardThing = mine.getAssistThing();
                resultTime = DictMapUtil.getSysConfigIntValue(StaticSysConfig.slavePerTime) * 3600;
//                //数据去完矿对象不会用到所以在这里修改矿数据
//                mine.setAssistId1(0);
//                mine.setATime1("");
                break;
            case 2:
                time = mine.getATime2();
                copperCount = type.getSlaveCopperCount();
                thing83Count = type.getSlaveThing83Count();

                isExitAward = getInstPlayerMineAwardDAL().getCount(" instPlayerId = " + instPlayerId);
                awardState = mine.getAssistThingState();
                awardThing = mine.getAssistThing();
                resultTime = DictMapUtil.getSysConfigIntValue(StaticSysConfig.slavePerTime) * 3600;
//                //数据去完矿对象不会用到所以在这里修改矿数据
//                mine.setAssistId2(0);
//                mine.setATime2("");
                break;
        }
        // 计算时间间隔 = 当前时间 - 上次时间
        long intevalTime = 0;
        if (time != null && !time.equals("")) {
            intevalTime = DateUtil.getCurrMill() - DateUtil.getMillSecond(time);
        }
        // 收益计算
        long profitCopper = (long) (mineMultiple * (extraCopper + (copperCount * intevalTime / (cycleTime * 1000))));
        long profitThing83 = (long) (mineMultiple * (extraThing83 + (thing83Count * intevalTime / (cycleTime * 1000))));


        Player player = PlayerMapUtil.getPlayerByPlayerId(instPlayerId);
        InstPlayer enermy = getInstPlayerDAL().getModel(enermyId, enermyId);
        StringBuilder content = new StringBuilder();
//        System.out.println("enermy 实力：" + player);
        String name = "";
        if (enermy != null) {
            name = enermy.getName();
        }
        content.append(intevalTime / 1000).append("|").append(profitCopper).append("|").append(profitThing83);
        if (profitCopper <= 0 && profitThing83 <= 0) {
            //无收益 ，不处理 ，矿主没收益时，协助者也无收益

            PlayerUtil.addPlayerMail(player, instPlayerId, content.toString(), enermyId, accountType, name, 5);
            return;
        }

        //添加领奖中心实例数据
        MessageData syncMsgData = new MessageData();
        String description = "资源矿结算奖励，总计获得";
        StringBuilder things = new StringBuilder();


        //特殊奖励结算判断
        if (isExitAward == 0 && awardState == 0 && awardThing != null && !awardThing.equals("") && intevalTime / 1000 >= resultTime) {
            things.append(awardThing).append(";");
            switch (who) {
                case 0:
                    mine.setMasterThingState(instPlayerId);
                    break;
                case 1:
                case 2:
                    mine.setAssistThingState(instPlayerId);
                    break;
            }
            InstPlayerMineAward mineAward = new InstPlayerMineAward();
            mineAward.setInstPlayerId(instPlayerId);
            if (who == 0)
                mineAward.setAwardType(1); //矿主奖励
            else
                mineAward.setAwardType(2); //协助奖励
            mineAward.setVersion(0);
            getInstPlayerMineAwardDAL().add(mineAward, 0);

            //为了把特殊奖励写到邮件里
            content.append("|").append(awardThing);

            //跑马灯相关
            //当在金矿获得特殊奖励时发跑马灯
            if (type.getMineType() == MINE_TYPE_GOLD) {
                List<InstPlayer> list = getInstPlayerDAL().getList(" id = " + instPlayerId, instPlayerId);
                if (list.size() > 0) {
                    try {
                        boolean error = false;
                        StringBuilder sbName = new StringBuilder();
                        String[] root = awardThing.split(";");
                        for (String node : root) {
                            String[] str = node.split("_");
                            String nodeType = str[0];
                            String nodeId = str[1];
                            String nodeCount = str[2];
                            switch (nodeType) {
                                case "1": //丹药
                                    DictPill pill = DictMap.dictPillMap.get(nodeId);
                                    sbName.append(pill.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                case "2": //物品
                                    DictThing thing = DictMap.dictThingMap.get(nodeId);
                                    sbName.append(thing.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                case "3": //金币银币
                                    DictPlayerBaseProp playerBaseProp = DictMap.dictPlayerBasePropMap.get(nodeId);
                                    sbName.append(playerBaseProp.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                case "6": //装备
                                    DictEquipment equipment = DictMap.dictEquipmentMap.get(nodeId);
                                    sbName.append(equipment.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                case "7": //卡牌
                                    DictCard card = DictMap.dictCardMap.get(nodeId);
                                    sbName.append(card.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                case "9": //魂魄
                                    DictCardSoul cardSoul = DictMap.dictCardSoulMap.get(nodeId);
                                    sbName.append(cardSoul.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                case "10": //碎片
                                    DictChip chip = DictMap.dictChipMap.get(nodeId);
                                    sbName.append(chip.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                case "11": //丹材
                                    DictPillThing pillThing = DictMap.dictPillThingMap.get(nodeId);
                                    sbName.append(pillThing.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                case "13": //功法/法宝
                                    DictMagic magic = DictMap.dictMagicMap.get(nodeId);
                                    sbName.append(magic.getName()).append("*").append(nodeCount).append(",");
                                    break;
                                default:
                                    error = true;
                                    break;
//                                    DictCard dictCard = DictMap.dictCardMap.get(nodeId);
                            }
                        }
                        if (sbName.length() > 0) {
                            sbName.deleteCharAt(sbName.length() - 1);
                        }
                        if(!error) {
                            String pName = list.get(0).getName();
                            String chats_contents = pName + "占领" + type.getMineName() + "，获得了特殊奖励" + sbName.toString();
                            MarqueeUtil.putMsgToMarquee(chats_contents);
                        }
                    } catch (Exception e) {
                        LogUtil.error(e);
                        e.printStackTrace();
                    }
                }
            }
        }


        if (profitThing83 > 0) {
            things.append(2).append('_').append(83).append('_').append(profitThing83).append(";"); //进阶石
        }

        if (profitCopper > 0) {
            things.append(3).append('_').append(2).append('_').append(profitCopper).append(";");    //银币
        }

        things.deleteCharAt(things.length() - 1);


        //领奖中心发奖
        ActivityUtil.addInstPlayerAward(instPlayerId, 3, things.toString(), DateUtil.getCurrTime(), description, syncMsgData);
        // 在线玩家
        if (player != null) {
            MessageUtil.sendSyncMsgToOne(player.getOpenId(), syncMsgData);
        }


        PlayerUtil.addPlayerMail(player, instPlayerId, content.toString(), enermyId, accountType, name, 5);

    }


    public static String getFirstThing(String things) {
        if (things == null) {
            return NULL;
        }

        String[] result = things.split(";");
        if (result.length > 0) {
            return result[0];
        }
        return NULL;
    }

    @Test
    public void testTT() throws Exception {
//        LoadResourceUtil.loadResource();
//        DictData.loadDictData();
//        int id = 4;
//        DictMineWeather mineWeather = DictList.dictMineWeatherList.get(id - 1);
//        System.out.println(((float) mineWeather.getValue()) / 100);

//        //初始化种子
//        setUpSeed("2015-10-19 11:00:10");
//
//        //开始创建
//        int mineMaxPage = 15;
//        //天气的长度
//        int len = 2;
//        WEATHER_LIST = new int[mineMaxPage];
//        for (int i = 0; i < mineMaxPage; i++) {
//            WEATHER_LIST[i] = MineUtil.getSeedRandTest(0, len);
//            System.out.println(WEATHER_LIST[i]);
//        }

        String out = getFirstThing("2_33_2");
        System.out.println(out);
    }

//    public synchronized static int getSeedRandTest(int min, int max) {
//
//        final long multiplier = 0x5DEECE66DL;
//        final long addend = 0xBL;
//        seed = (seed * multiplier + addend) % 640000000000L;
//        return ((int) (Math.abs(seed % (max - min + 1)) + min));
//    }

}
