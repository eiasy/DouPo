package com.huayi.doupo.logic.test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.DictThing;
import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.InstPlayerThing;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.SerializeUtil;
import com.huayi.doupo.base.util.logic.system.LogicLogUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;

/**
 * 测试类
 * win7 64bit OS, i5CPU, 4gMEM,  
 * @author mp
 * @date 2014-8-21 下午4:39:05
 */
public class PressureTest extends DALFactory{
	
	public static boolean flag = false;
	
	/**
	 * 1.getModel 1000次某个玩家Id的数据比getList1次这个玩家Id的数据要耗时的多
	 * 2.getList()结果的数据越大查询越耗时, 如果查询结果一样,和此表中的数据大小关系不大(***前提必须是做索引后***，如果不做索引，查询时间会随着数据增大而变缓，哪怕结果集一样)
	 * 3.索引有效期作用的范围：单位数据少[instPlayerId = 130 有100条数据, 索引时间=假设此表中只有这100条数据时的查询时间]，整体数据大[100000条],查询条件种类繁多[instPlayerId 从1 - 100000]
	 * @author mp
	 * @date 2014-8-25 下午3:43:07
	 * @param args
	 * @throws Exception
	 * @Description
	 */
	public static void main(String[] args) throws Exception{
		SpringUtil.getSpringContext();
//		test1();// 测试查库和循环结果的 耗时
		test2();// InnoDB引擎下插入某表1万条数据耗时
//		test3();// 缓存作用[多线程]
//		test4();// 缓存作用[单线程]
//		test5();// 测试事务
//		test6();// 压力测试
		
//		initData();
		
/*		List<String> strList = Lists.newArrayList();
		Map<String, List<String>> map = Maps.newHashMap();
		List<InstPlayerThing> thingList = getInstPlayerThingDAL().getList("", 0);
		System.out.println(thingList.size());
		for(InstPlayerThing obj : thingList){
			String id = obj.getId() + "";
			String instPlayerId = obj.getInstPlayerId() + ";";
			String all = id + ";" + instPlayerId;
			strList.add(all);
		}
		map.put("1", strList);
		
		byte[] b = SerializeUtil.serialize(map);
		System.out.println(b.length);*/
		
		
//		1289035  b kb m
		
		
//		insertEquip();
//		select();
		
	}
	
	public static void select () {
		
		int instPlayerId = 118;
		int instPlayerId2 = 119;
		
//		InstPlayer instPlayer = getInstPlayerDAL().getModel(instPlayerId2, instPlayerId2);
		DictThing dictThing = getDictThingDAL().getModel(1, 0);
		
		for (int i = 0; i < 10; i++) {
			long start = DateUtil.getCurrMill();
			List<InstPlayerEquip> instPlayerEquipList = getInstPlayerEquipDAL().getList("instPlayerId = " + (instPlayerId2 + i), (instPlayerId2 + i));
			System.out.println("  first select used time is " + (DateUtil.getCurrMill() - start));
			
			long start2 = DateUtil.getCurrMill();
			List<InstPlayerEquip> instPlayerEquipList2 = getInstPlayerEquipDAL().getList("instPlayerId = " + (instPlayerId + i), (instPlayerId + i));
			System.out.println("  second select used time is " + (DateUtil.getCurrMill() - start2));
			
			System.out.println("======================");
			try {
				TimeUnit.MILLISECONDS.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		

	}
	
	public static void insertEquip () throws Exception{
		for (int i = 0; i < 100000; i++) {
			InstPlayerEquip instPlayerEquip = new InstPlayerEquip();
			instPlayerEquip.setInstPlayerId((i+1));
			instPlayerEquip.setEquipTypeId(1);
			instPlayerEquip.setEquipId(45);
			instPlayerEquip.setLevel(0);
			instPlayerEquip.setInstCardId(0);
			instPlayerEquip.setIsInlay(0);
			getInstPlayerEquipDAL().add(instPlayerEquip, 0);
		}
		System.out.println("-- insert succ --");
	}
	
	
	public static void initData () throws Exception{
		long start = System.currentTimeMillis();
		addInstPlayerThing(100000, 100);
//		addInstPlayerThing(1000, 500);
//		addInstPlayerThing(250000, 100);
		System.out.println("--- all insert over ---" + (System.currentTimeMillis() - start) + "ms");
	}
	
	
	/**
	 * 测试： 压力测试-多线程模拟多用户集中操作
	 * 
	 * 用例2： Inst_Player_Thing表初始10万条,玩家Id=1000的数据有500条. 每1秒向此表写入1条数据并查询2次玩家Id=1000的数据, 每做完这些操作写入日志.
	 * 结果：    可满足同时500线程[人]每秒做此操作 CPU-[26%-50%]
	 * 
	 * 用例2： Inst_Player_Thing表初始100万条,玩家Id=1000的数据有500条. 每1秒向此表写入1条数据并查询2次玩家Id=1000的数据, 每做完这些操作写入日志.
	 * 结果：    可满足同时280线程[人]每1秒做此操作 CPU-[30%-33%]
	 * @author mp
	 * @date 2014-8-23 下午5:10:44
	 * @Description
	 */
	public static void test6 () throws Exception{
		
		for (int i = 0; i < 400; i++) {
			
			System.out.println("---- " + i);
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						try {
							
							TimeUnit.SECONDS.sleep(1);
							
							//写1次
							for (int j = 0; j < 1; j++) {
								InstPlayerThing instPlayerThing = new InstPlayerThing();
								instPlayerThing.setBagTypeId(1);
								instPlayerThing.setIndexOrder(1);
								instPlayerThing.setInsertTime("aaaaaaaaaaaaaaaaaaaaa");
								instPlayerThing.setInstPlayerId(100);
								instPlayerThing.setLevel(111);
								instPlayerThing.setNum(1111);
								instPlayerThing.setThingId(1111);
								instPlayerThing.setThingTypeId(1111);
								instPlayerThing.setUpdateTime("aaaaaaaaaaaaaaaaaaaaa");
								instPlayerThing.setVersion(1);
								getInstPlayerThingDAL().add(instPlayerThing, 0);
							}
							
							//查2次
							for (int j = 0; j < 2; j++) {
								List<InstPlayerThing> thingList = getInstPlayerThingDAL().getList("instPlayerId = 1000", 1000);
							}
							
							//写入日志
							LogicLogUtil.info("bagExpand", "test curr");
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
			}).start();
		}
		
	}
	
	/**
	 * 测试: 事务是否工作
	 * 结果： 正常工作
	 * @author mp
	 * @date 2014-8-23 下午4:00:26
	 * @Description
	 */
	public static void test5 () {
		try {
			
//			long start = System.currentTimeMillis();
//			for (int i = 5001; i <= 6000; i++) {
//				InstPlayerThing instPlayerThing = getInstPlayerThingDAL().getModel(1000, 0);
//			}
			long end = System.currentTimeMillis();
//			System.out.println("1000 get model used time =  " + (end - start));
			//1749(1w)   1638(10w) 1703(50w)
			for (int i = 0; i < 5000; i++) {
				List<InstPlayerThing> instPlayerThingList = getInstPlayerThingDAL().getList("instPlayerId = 1000", 1000);
			}
			
			System.out.println("1 get list used time = " + (System.currentTimeMillis() - end));
			
//			HandlerFactory.getSysHandler().testTran();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 测试： 缓存作用[单线程]
	 * 
	 * 1)测试用例：Inst_Player_Thing表中含100条数据 , 循环5000次
	 * 结果：不使用缓存 耗时3500ms , 使用缓存 耗时2300ms , 比前者快1.52倍
	 * 
	 * 2)测试用例：Inst_Player_Thing表中含1000条数据 , 循环5000次
	 * 结果：不使用缓存 耗时21200ms , 使用缓存 耗时8100ms , 比前者快2.61倍
	 * 
	 * 3)测试用例：Inst_Player_Thing表中含10000条数据 , 循环5000次
	 * 结果：不使用缓存 耗时39681ms ***会出现线程死掉的情况***, 使用缓存 耗时17074ms , 比前者快2.32倍
	 * 
	 * 12694ms
	 * @author mp
	 * @date 2014-8-22 下午2:47:03
	 * @Description
	 */
	public static void test4() {
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			if (i % 100 == 0) {
				System.out.println(i);
			}
			List<InstPlayerThing> thingList = getInstPlayerThingDAL().getList("instPlayerId = 1000", 1000);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(" used time = " + (endTime - startTime) + "ms");
	}
	
	/**
	 * 测试: 缓存作用[多线程]
	 * 
	 * 1)测试用例： Inst_Player_Thing表中含100条数据 , 5000个线程每隔10秒做一次查询
	 * 结果：可能受用例影响[并未做到像单线程循环那么紧凑],速度相差不多,7s左右. 但CPU相差很多.不使用缓存 CPU最高峰值[40%-50%],使用缓存CPU最高峰值[25%-40%],比前者节省10%多的CPU资源
	 * 
	 * 2)测试用例： Inst_Player_Thing表中含1000条数据 , 5000个线程每隔13秒做一次查询
	 * 结果： 不使用缓存 耗时11s, CPU最高峰值[85%-90%], 使用缓存 耗时8s, CPU最高峰值[65%-75%], 速度比前者快3s, CPU比前者节省15%多
	 * 
	 * 3)测试用例： Inst_Player_Thing表中含10000条数据 , 1000个线程每隔13秒做一次查询
	 * 结果： 不使用缓存 耗时15s, CPU最高峰值[95%-100%], 使用缓存 耗时8s, CPU最高峰值[92%], 速度比前者快7s, CPU比前者节省8%多
	 * @author mp
	 * @date 2014-8-22 上午10:21:12
	 * @throws Exception
	 * @Description
	 */
	public static void test3 () throws Exception{
		final long startTime = System.currentTimeMillis();
		
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						if (PressureTest.flag) {
							List<InstPlayerThing> thingList = getInstPlayerThingDAL().getList("", 0);
							System.out.println((System.currentTimeMillis()) - startTime);
						}
						try {
							TimeUnit.SECONDS.sleep(15);
						} catch (Exception e) {
						}
					}
				}
			}).start();
			if (i == 1000-1) {
				PressureTest.flag = true;
				System.out.println("--read over---");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println(" used time = " + (endTime - startTime) + "ms");
	}
	
	/**
	 * 测试:InnoDB引擎下插入某表1万条数据耗时
	 * 用例：向Dict_Thing表插入1万条数据
	 * innodb_flush_log_at_trx_commit=1时,耗时260000ms=4分钟
	 * innodb_flush_log_at_trx_commit=2时,耗时3300ms=3.3秒 比设置成1快70多倍
	 * @author mp
	 * @date 2014-8-21 下午5:41:14
	 * @throws Exception
	 * @Description
	 */
	public static void test2 () throws Exception{

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			if (i % 1000 == 0) {
				System.out.println(i);
			}
			//为什么把new obj放到外面在插入数据库的时候, 过了1万会越来越慢呢?
			DictThing obj = new DictThing();
			obj.setBagTypeId(1);
			obj.setBigUiId(111);
			obj.setBuyCopper(1111);
			obj.setBuyGold(111);
			obj.setChildThings("aaaaaaaaaaaaa");
			obj.setCoreConvCopper(1111);
			obj.setDescription("aaaaaaaaaaaaaaaa");
			obj.setEquipmentId(111);
			obj.setFightPropId(1111);
			obj.setThingTypeId(11111);
			getDictThingDAL().add(obj, 0);
		}
		long endTime = System.currentTimeMillis();
		System.out.println(" used time = " + (endTime - startTime) + "ms");
	}
	
	/**
	 * 测试：测试查库和循环结果的 耗时
	 * 
	 * 用例一：Dict_Thing表[11个字段],含1万条数据
	 * 结果：a.查表Dict_thing耗时400ms   b.[1-2]毫秒可以循环2万条Dict_Thing的数据
	 * 
	 * 用例二：Dict_Thing表[11个字段],含2万条数据
	 * 结果：a.查表Dict_thing耗时470ms   b.[2]毫秒可以循环2万条Dict_Thing的数据
	 * 
	 * 计算机1ms循环1万次数据没问题,2ms循环2万次数据没问题
	 * 1万条数据循环两次的耗时和2万条数据循环1次的耗时是不一样的,后者用时多
	 * @author mp
	 * @date 2014-8-21 下午4:39:45
	 * @Description
	 */
	public static void test1() {
		long start = System.currentTimeMillis();
		List<DictThing> thingList = getDictThingDAL().getList("", 0);
		System.out.println("db list " + thingList.size() + " used time = " + (System.currentTimeMillis() - start) + "ms");
		int count = 0;
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < thingList.size(); j++) {
				DictThing thing = thingList.get(j);//只管取出来
				count ++;
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("count = " + count + " used time = " + (endTime - startTime) + "ms");
	}
	
	public static void addInstPlayerThing (int num, int instPlayerId) throws Exception{
		for (int i = 0; i < num; i++) {
			if (i != 0 && i % 1000 == 0) {
				System.out.println(i);
			}
			InstPlayerThing instPlayerThing = new InstPlayerThing();
//			instPlayerThing.setBagTypeId(1);
//			instPlayerThing.setIndexOrder(1);
//			instPlayerThing.setInsertTime("aaaaaaaaaaaaaaaaaaaaa");
			instPlayerThing.setInstPlayerId(instPlayerId);
//			instPlayerThing.setLevel(111);
//			instPlayerThing.setNum(1111);
//			instPlayerThing.setThingId(1111);
//			instPlayerThing.setThingTypeId(1111);
//			instPlayerThing.setUpdateTime("aaaaaaaaaaaaaaaaaaaaa");
//			instPlayerThing.setVersion(1);
			getInstPlayerThingDAL().add(instPlayerThing, 0);
		}
	}
	
}
