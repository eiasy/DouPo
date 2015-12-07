package com.huayi.doupo.logic.handler.quartz;

import com.huayi.doupo.base.model.DictMineType;
import com.huayi.doupo.base.model.InstPlayerMine;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.handler.util.MineUtil;
import com.huayi.doupo.logic.handler.util.PlayerUtil;
import com.huayi.doupo.logic.util.LoadResourceUtil;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;


/**
 * Created by cui on 2015/10/5 0005.
 */
public class MineAwardHandler extends BaseHandler implements Job {
    /**
     * 资源矿发奖
     *
     * @param context
     * @throws JobExecutionException
     * @author cui
     * @data 05/10/2015
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        MineUtil.grantAward();
    }

//    private void grantAward() {
//        List<InstPlayerMine> instPlayerMineList = getInstPlayerMineDAL().getList("", 0);
//        for (InstPlayerMine mine : instPlayerMineList) {
//            int pid = mine.getInstPlayerId();
//            int assitid1 = mine.getAssistId1();
//            int assitid2 = mine.getAssistId2();
//
//            DictMineType type = getMineTypeObj(mine.getMineType());
//            if (type == null) {
//                continue;
//            }
//
//            //矿被抢夺，所以需要给老矿主结算
//            try {
//                PlayerUtil.accountMine(pid, mine, type, 0, 2, pid);
//
//                if (assitid1 > 0) {
//                    PlayerUtil.accountMine(assitid1, mine, type, 1, 2, assitid1);
//                }
//                if (assitid2 > 0) {
//                    PlayerUtil.accountMine(assitid2, mine, type, 2, 2, assitid2);
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                LogUtil.error("资源矿发奖时，Error" + " pid=" + pid + " assitid1=" + assitid1 + " assitid2=" + assitid2, e);
//            }
//        }
//
//        //准备清理表数据
//        try {
//            LogUtil.warn("准备删除数据");
//            getInstPlayerMineDAL().deleteByWhere("");
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogUtil.error("资源矿发奖，清理数据Error", e);
//        }
//    }

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




    @Test
    public void testMineAward() throws Exception {
        DictData.loadDictData();
        LoadResourceUtil.loadResource();
        MineUtil.grantAward();
    }
}
