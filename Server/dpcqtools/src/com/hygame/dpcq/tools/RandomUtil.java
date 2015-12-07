package com.hygame.dpcq.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 随机数帮助类
 * @author mp
 * @date 2013-10-17 下午2:42:12
 */
public class RandomUtil{
	/**
	 * 获得指定范围，指定个数的不重复的随机数,从1开始；
	 * @param limitNum
	 * @param sum
	 * @return
	 */
	public static List<Integer> getRandomNoRepeat(int num, int maxNum) {
		List<Integer> list = new ArrayList<Integer>();
		Random random = new Random();
		int ran = 0;
		while (true) {
			ran = random.nextInt(maxNum) + 1;
			if (list.contains(ran)) {
				continue;
			} else {
				list.add(ran);
			}
			if (list.size() == num) {
				break;
			}
		}
		return list;
	}
	
	/**
	 * 从map中随机出三个数值类型的Key，以List<Integer>格式返回，*****下标是从1开始的*****
	 * @author mp
	 * @date 2013-11-23 下午5:02:33
	 * @param num
	 * @return
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Integer> getRandomNoRepeat(int num, Map map){
		List<Integer> retList = new ArrayList<Integer>();
		Set<Integer> set = map.keySet();
		Object [] intArray = set.toArray();
		if(map.size() >= num){
			List<Integer> list = RandomUtil.getRandomNoRepeat(num, intArray.length);
			for(int i : list){
				int value = (Integer)intArray[i-1];
				retList.add(value);
			}
		}
		return retList;
	}
	
	public static void main(String[] args) {
		for(int i = 0; i < 100; i++){
			System.out.println(RandomUtil.getRandomNoRepeat(3, 3));
		}
	}
	
	/**
	 * 返回指定范围内的随机数[min, max]
	 * @author mp
	 * @date 2013-11-5 上午10:00:10
	 * @param min
	 * @param max
	 * @return
	 * @Description
	 */
	public static int getRangeInt(int min, int max){
		return new Random().nextInt(max - min + 1) + min;
	}
	
	/**
	 * 得到[0-maxNum)的随机数
	 * @author mp
	 * @date 2013-10-17 下午2:43:35
	 * @param maxNum
	 * @return
	 * @Description
	 */
	public static int getRandomInt (int maxNum){
		return new Random().nextInt(maxNum);
	}
	
	/**
	 * 得到(0,1)的随机数
	 * @author mp
	 * @date 2013-10-18 上午11:29:45
	 * @return
	 * @Description
	 */
	public static float getRandomFloat(){
		return new Random().nextFloat();
	}
	
}