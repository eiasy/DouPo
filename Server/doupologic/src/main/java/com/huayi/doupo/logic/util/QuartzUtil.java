package com.huayi.doupo.logic.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import com.huayi.doupo.base.model.SysQuartzJob;

/**
 * 定时器管理类
 * @author mp
 * @date 2013-11-8 上午9:39:13
 */
public class QuartzUtil {
	
	private static SchedulerFactory sf = new StdSchedulerFactory();
	
	private static String jobGroupName = "job_group_name";
	private static String triggerGroupName = "trigger_group_name";

	/**
	 * 添加定时任务
	 * @author mp
	 * @date 2013-11-8 上午9:40:06
	 * @param job
	 * @throws SchedulerException
	 * @throws ParseException
	 * @Description
	 */
	public static void addJob(SysQuartzJob job) throws Exception {

		Scheduler sched = sf.getScheduler();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if (jobDetailIsExist(job.getJobName(), jobGroupName) || triggerIsExist(job.getTriggerName(), triggerGroupName)) {
			return;
		}
		if(new Date().getTime() > ((Date) formatter.parse(job.getEndTime())).getTime()){
			return;
		}
		
		// 任务名，任务组，任务执行类
		JobDetail jobDetail = new JobDetail(job.getJobName(), jobGroupName, Class.forName(job.getJobClassPath()).newInstance().getClass());
				
		CronTrigger trigger = new CronTrigger(job.getTriggerName(), triggerGroupName, job.getJobName(), jobGroupName,
				new Date(), (Date) formatter.parse(job.getEndTime()),
				job.getCronExpression());
		sched.scheduleJob(jobDetail, trigger);
		
		// 启动
		if (!sched.isShutdown())
			sched.start();
		System.out.println("定时任务: ["+job.getName()+"]  启动成功!  " + "开始时间:" + job.getStartTime()+"," + " 结束时间:" + job.getEndTime());
	}

	/**
	 * 修改一个已有的任务
	 * @author mp
	 * @date 2013-11-8 上午9:47:07
	 * @param job
	 * @throws SchedulerException
	 * @throws ParseException
	 * @Description
	 */
	public static void modifyJob(SysQuartzJob job) throws Exception {
		Scheduler sched = sf.getScheduler();
		Trigger trigger = sched.getTrigger(job.getTriggerName(), triggerGroupName);
		if (trigger != null) {
			removeJob(job);
			addJob(job);
		}
	}

	/**
	 * 移除一个任务
	 * @author mp
	 * @date 2013-11-8 上午9:46:54
	 * @param job
	 * @throws SchedulerException
	 * @Description
	 */
	public static void removeJob(SysQuartzJob job) throws Exception {
		Scheduler sched = sf.getScheduler();
		if (!jobDetailIsExist(job.getJobName(), jobGroupName) || !triggerIsExist(job.getTriggerName(), triggerGroupName)) {
			return;
		}
		//停止触发器
		sched.pauseTrigger(job.getTriggerName(), triggerGroupName);
		//移除触发器
		sched.unscheduleJob(job.getTriggerName(), triggerGroupName);
		//删除任务
		sched.deleteJob(job.getJobName(), jobGroupName);
	}

	/**
	 * 检查jobDetail是否存在
	 * @author mp
	 * @date 2013-11-8 上午9:46:39
	 * @param jobName
	 * @param jobGroupName
	 * @return
	 * @throws SchedulerException
	 * @Description
	 */
	public static boolean jobDetailIsExist(String jobName, String jobGroupName) throws Exception {
		Scheduler sched = sf.getScheduler();
		JobDetail j = sched.getJobDetail(jobName, jobGroupName);
		if (j != null) {
			System.out.println("jobDetail (" + jobName + "," + jobGroupName + ") already is exist!");
			return true;
		}
		return false;
	}

	/**
	 * 检查trigger是否存在
	 * @author mp
	 * @date 2013-11-8 上午9:46:08
	 * @param triggerName
	 * @param triggerGroupName
	 * @return
	 * @throws SchedulerException
	 * @Description
	 */
	public static boolean triggerIsExist(String triggerName, String triggerGroupName) throws Exception {
		Scheduler sched = sf.getScheduler();
		Trigger t = sched.getTrigger(triggerName, triggerGroupName);
		if (t != null) {
			System.out.println("trigger(" + triggerName + "," + triggerGroupName + ") already is exist!");
			return true;
		}
		return false;
	}
	
}
