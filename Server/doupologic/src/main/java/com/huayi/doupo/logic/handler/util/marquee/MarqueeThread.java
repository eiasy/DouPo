package com.huayi.doupo.logic.handler.util.marquee;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.logic.util.SysMarqueeMapUtil;



/**
 * 跑马灯线程类
 * @author mp
 * @date 2014-12-10 下午5:44:50
 */
public class MarqueeThread extends Thread implements Runnable{
	
	/**
	 * 开始时间
	 */
	private String startTime;
	
	/**
	 * 结束时间
	 */
	private String endTime;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 轮询间隔
	 */
	private int inteval;
	
	/**
	 * SysMarqueeId
	 */
	private int sysMarqueeId;
	
	/**
	 * 线程循环开关
	 */
	private boolean flag = true;
	
	/**
	 * 空构造方法
	 */
	public MarqueeThread() {

	}
	
	/**
	 * 构造方法
	 * @param startDate
	 * @param endDate
	 * @param startTime
	 * @param endTime
	 */
	public MarqueeThread(String content, String endTime, int inteval, String startTime, int sysMarqueeId) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.content = content;
		this.inteval = inteval;
		this.sysMarqueeId = sysMarqueeId;
	}
	
	@Override
	public void run() {
		try {
			while(flag){
				
				long startTimeMill = DateUtil.getMillSecond(startTime);
				long endTimeMill = DateUtil.getMillSecond(endTime);
				
				long currTime = DateUtil.getCurrMill();
				
				//如果超出约定时间,线程停止,并将表中的数据移除
				if(currTime > endTimeMill){
					flag = false;
					DALFactory.getSysMarqueeDAL().deleteById(sysMarqueeId, 0);
					SysMarqueeMapUtil.removeById(sysMarqueeId);
					//删除队列中存留数据
					List<String> rmList = Lists.newArrayList();
					rmList.add(content);
					MarqueeUtil.blockingDeque.removeAll(rmList);
					
				//在约定时间范围内,以设置的秒数为单位,反复调用
				} else if (currTime >= startTimeMill && currTime <= endTimeMill) {
					MarqueeUtil.blockingDeque.putFirst(content);
					TimeUnit.SECONDS.sleep(inteval);
					
				//如果还未到时间,以每1分钟为单位,反复调用
				} else if (currTime < startTimeMill) {
					TimeUnit.MINUTES.sleep(1);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getInteval() {
		return inteval;
	}

	public void setInteval(int inteval) {
		this.inteval = inteval;
	}

	public int getSysMarqueeId() {
		return sysMarqueeId;
	}

	public void setSysMarqueeId(int sysMarqueeId) {
		this.sysMarqueeId = sysMarqueeId;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}