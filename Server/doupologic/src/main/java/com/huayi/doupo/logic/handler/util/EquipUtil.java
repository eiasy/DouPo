package com.huayi.doupo.logic.handler.util;

import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.*;
import com.huayi.doupo.base.model.dict.DictMap;
import com.huayi.doupo.base.model.dict.DictMapList;
import com.huayi.doupo.base.model.statics.StaticCnServer;
import com.huayi.doupo.base.model.statics.StaticSyncState;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.util.MessageData;
import com.huayi.doupo.logic.util.MessageUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Coolcow on 2015/12/9 0009.
 */
public class EquipUtil extends DALFactory {

    /**
     * 因橙色装备可以进阶到红色装备，破坏了原有的数据结构，故只能增加一个实力表来承载装备上多增加的淬炼值
     * 当删除装备时，（注意）需要一起把这件装备相关联的实力表进行检查，当有关联值时需要一同删除
     *
     * @param id
     * @param instPlayerId
     * @throws Exception
     */
    public static void deletePlayerEquip(int id, int instPlayerId) throws Exception {
        //删除装备
        getInstPlayerEquipDAL().deleteById(id, instPlayerId);

        //删除装备关联的淬炼值实力表
        List<InstPlayerRedEquip> instPlayerRedEquipList = getInstPlayerRedEquipDAL().getList("equipInstId = " + id, instPlayerId);
        if(instPlayerRedEquipList != null) {
            for (InstPlayerRedEquip redEquip : instPlayerRedEquipList) {
                getInstPlayerRedEquipDAL().deleteById(redEquip.getId(), instPlayerId);
            }
        }

    }


    public static void checkEquipAdvance(MessageData syncMsgData, MessageData retMsgData, InstPlayerEquip playerEquip, int instPlayerId) throws Exception {
        List<DictEquipAdvance> equipAdvanceList = (List<DictEquipAdvance>) DictMapList.dictEquipAdvanceMap.get(playerEquip.getEquipTypeId());
        int equipAdvanceId = playerEquip.getEquipAdvanceId();
        //验证当前是否已经为满级了,满级了不允许再次操作
        if (equipAdvanceId != 0) {
            DictEquipAdvance dictEquipAdvanceYz = DictMap.dictEquipAdvanceMap.get(equipAdvanceId + "");
            if (dictEquipAdvanceYz.getEquipQualityId() > 4) { //红品质装备
                if (dictEquipAdvanceYz.getStarLevel() >= 5) {
                    return;
                }
                //判断消费是否可进阶
                List<DictEquipAdvancered> dictEquipAdvancereds = (List<DictEquipAdvancered>) DictMapList.dictEquipAdvanceredMap.get(playerEquip.getEquipId());

                //进阶到红装
                DictEquipAdvance dictEquipAdvanceNew = null;
                for (DictEquipAdvance obj : equipAdvanceList) {
                    if (obj.getEquipQualityId() == 5 && obj.getStarLevel() == dictEquipAdvanceYz.getStarLevel() + 1) {
                        dictEquipAdvanceNew = obj;
                        break;
                    }
                }
                if (dictEquipAdvanceNew == null) {
                    return;
                }
                playerEquip.setEquipAdvanceId(dictEquipAdvanceNew.getId());

                getInstPlayerEquipDAL().update(playerEquip, instPlayerId);
                OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, playerEquip, playerEquip.getId(), playerEquip.getResult(), syncMsgData);

                retMsgData.putIntItem("isAc", 1);

                //记录日志
                String log = "装备进阶红色：instPlayerId=" + instPlayerId + " 进阶等级=" + dictEquipAdvanceNew.getId() + " 进阶结果=成功";
                LogUtil.info(log);

            } else if (dictEquipAdvanceYz.getEquipQualityId() == 4 && dictEquipAdvanceYz.getStarLevel() >= 5) {
                //进阶到红装
                DictEquipAdvance dictEquipAdvanceNew = null;
                for (DictEquipAdvance obj : equipAdvanceList) {
                    if (obj.getEquipQualityId() == 5 && obj.getStarLevel() == 0) {
                        dictEquipAdvanceNew = obj;
                        break;
                    }
                }
                if (dictEquipAdvanceNew == null) {
                    return;
                }
                playerEquip.setEquipAdvanceId(dictEquipAdvanceNew.getId());

                getInstPlayerEquipDAL().update(playerEquip, instPlayerId);
                OrgFrontMsgUtil.orgSyncMsgData(StaticSyncState.update, playerEquip, playerEquip.getId(), playerEquip.getResult(), syncMsgData);

                retMsgData.putIntItem("isAc", 1);

                //记录日志
                String log = "装备进阶红色：instPlayerId=" + instPlayerId + " 进阶等级=" + dictEquipAdvanceNew.getId() + " 进阶结果=成功";
                LogUtil.info(log);
            }
        }
    }


}
