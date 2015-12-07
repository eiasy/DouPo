package com.huayi.doupo.logic.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.model.SysStatics;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.base.util.logic.system.log.ThreadOper;

public class BatchTest extends DALFactory{
	
	private BatchTest () {
		
	}
	
	private static final int nThread = 1;

	private static List<SysStatics> list = Lists.newArrayList();
	
	private static ThreadPoolExecutor workPool = new ThreadPoolExecutor(nThread, nThread, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()); 
	
	public static void main(String[] args) throws Exception{
		
		SpringUtil.getSpringContext();
		
		long start = DateUtil.getCurrMill();
		
		List<InstPlayerThing> instPlayerThingList = new ArrayList<>();
		
		for (int i = 0; i < 10000; i++) {
			InstPlayerThing instPlayerThing = new InstPlayerThing();
			instPlayerThing.setBagTypeId(1);
			instPlayerThing.setIndexOrder(1);
			instPlayerThing.setInstPlayerId(1);
			instPlayerThing.setLevel(1);
			instPlayerThing.setNum(1);
			instPlayerThing.setThingId(1);
			instPlayerThing.setThingTypeId(1);
			
//			getInstPlayerThingDAL().add(instPlayerThing, 0);
			
			instPlayerThingList.add(instPlayerThing);
		}
		
		System.out.println("start insert");
		
		getInstPlayerThingDAL().batchAdd(instPlayerThingList);
		
		System.out.println("insert succ  " + (DateUtil.getCurrMill() - start));
		
//		float a = 3.9f;
//		System.out.println((int)a);
		
//		for (int i = 0; i < 2; i++) {
//			execute (new ThreadOper() {
//				@Override
//				public void innerRun() {
//					print ();
//				}
//			});
//		}
		
//		SpringUtil.getSpringContext();
//		
//		insert();
//		
//		batchInsert ();
//		
//		cachBatchInsert();

	}
	
	/**
	 * 打印
	 * @author mp
	 * @date 2014-9-30 下午1:58:11
	 * @Description
	 */
	public static void print () {
		try {
			
			/*
			getInstPlayerRecruitDAL().getList
			getInstPlayerPillThingDAL().getList
			getInstPlayerPillRecipeThingDAL().getList
			getInstPlayerPillRecipeDAL().getList
			getInstPlayerPillDAL().getList
			getInstPlayerPartnerDAL().getList
			getInstPlayerPagodaDAL().getList
			getInstPlayerManualSkillLineDAL().getList
			getInstPlayerManualSkillDAL().getList
			getInstPlayerMagicDAL().getList
			getInstPlayerLootDAL().getList
			getInstPlayerLineupDAL().getList
			getInstPlayerKungFuDAL().getList
			getInstPlayerGmRewardDAL().getList
			getInstPlayerFormationDAL().getList
			getInstPlayerFireTempDAL().getList
			getInstPlayerFireDAL().getList
			getInstPlayerEquipDAL().getList
			getInstPlayerDailyTaskDAL().getList*/
			
			System.out.println("----Executor----");
			TimeUnit.SECONDS.sleep(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 执行方法
	 * @author mp
	 * @date 2014-9-30 下午1:49:10
	 * @param runnable
	 * @Description
	 */
	public static void execute (Runnable runnable) {
		try {
			workPool.execute(runnable);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 单条插入
	 * @author mp
	 * @date 2014-9-29 上午11:03:30
	 * @throws Exception
	 * @Description
	 */
	public static void insert() throws Exception{
		
		List<SysStatics> sysStaticList = init();
		
		long start = System.currentTimeMillis();
		for (SysStatics obj : sysStaticList) {
			getSysStaticsDAL().add(obj, 0);
		}
		System.out.println("--- insert ok --- used time is --- " + (System.currentTimeMillis() - start));
	}
	
	/**
	 * 批量插入
	 * @author mp
	 * @date 2014-9-29 上午11:03:40
	 * @Description
	 */
	public static void batchInsert () {
		long start = System.currentTimeMillis();
		getSysStaticsDAL().batchAdd(init());
		System.out.println("--- batch ok --- used time is --- " + (System.currentTimeMillis() - start));
	}
	
	/**
	 * 带缓存的批量插入
	 * @author mp
	 * @date 2014-9-29 下午3:00:59
	 * @Description
	 */
	public static void cachBatchInsert () {
		long start = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			SysStatics sysStatics = new SysStatics();
			sysStatics.setMaxOnlineNum(i);
			list.add(sysStatics);
			if (list.size() >= 1000) {
				getSysStaticsDAL().batchAdd(list);
				list.clear();
			}
		}
		System.out.println("---cach batch insert used time is --- " + (System.currentTimeMillis() - start));
	}
	
	/**
	 * 初始化数据
	 * @author mp
	 * @date 2014-9-29 上午11:05:11
	 * @return
	 * @Description
	 */
	private static List<SysStatics> init () {
		List<SysStatics> sysStaticList = Lists.newArrayList();
		for (int i = 0; i < 10000; i++) {
			SysStatics sysStatics = new SysStatics();
			sysStatics.setMaxOnlineNum(i);
			sysStaticList.add(sysStatics);
		}
		return sysStaticList;
	}
	
}
