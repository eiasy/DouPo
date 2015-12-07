package com.huayi.doupo.logic.handler.quartz;

import com.huayi.doupo.base.model.SysQuartzJob;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.statics.StaticSysConfig;
import com.huayi.doupo.base.util.logic.system.DictMapUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;
import com.huayi.doupo.logic.util.LoadResourceUtil;
import com.huayi.doupo.logic.util.QuartzUtil;
import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.List;

/**
 * Created by cui on 2015/10/5 0005.
 */
public class ModifyQuartzHandler extends BaseHandler implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            LogUtil.warn("定时器定时触发事情===============================================");
            modifyJob();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void modifyJob(){
        try {

            List<SysQuartzJob> sysQuartzJobList = getSysQuartzJobDAL().getList("", 0);
            int first = DictMapUtil.getSysConfigIntValue(StaticSysConfig.firstTimeEnd);
            int second = DictMapUtil.getSysConfigIntValue(StaticSysConfig.secondTimeEnd);
            for (SysQuartzJob job : sysQuartzJobList) {
                if (job.getJobName().equals("mineAwardHandlerJ")) {
                    boolean isReady = true;
                    String cron = job.getCronExpression();
                    String[] strs = cron.split(" ");
                    if (strs.length > 3) {
                        String[] times = strs[2].split(",");
                        if (times.length > 1) {
                            if (first == Integer.parseInt(times[0]) && second == Integer.parseInt(times[1])) {
                                isReady = false;
                            }
                        }
                    }
                    if (isReady) {
                        job.setCronExpression("10 0 " + first + "," + second + " * * ?");
                        String sql = String.format("UPDATE Sys_QuartzJob SET cronExpression=\"%2$s\" WHERE id=%1$d", job.getId(), job.getCronExpression());
                        System.out.println(sql);
                        getSysQuartzJobDAL().update(sql);

                        QuartzUtil.modifyJob(job);
                        LogUtil.warn("定时器有变动:" + "modifyQuartzHandlerJ" + job.getCronExpression());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJob() throws Exception {
        LoadResourceUtil.loadResource();
        DictData.loadDictData();
        modifyJob();
    }


}
