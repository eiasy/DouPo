package com.huayi.doupo.base.util.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 随机数帮助类
 * 
 * @author mp
 * @date 2013-10-17 下午2:42:12
 */
public class RandomUtil {

	/**
	 * 获得指定范围，指定个数的不重复的随机数,从1开始；
	 * 
	 * @param limitNum
	 * @param sum
	 * @return
	 */
	public static List<Integer> getRandomNoRepeat(int num, int maxNum) {
		List<Integer> list = new ArrayList<Integer>();
		// Random random = new Random();
		// int ran = 0;
		// int count = 0;
		// while (true) {
		// count ++;
		// ran = random.nextInt(maxNum) + 1;
		// if (list.contains(ran)) {
		// continue;
		// } else {
		// list.add(ran);
		// }
		// if (list.size() == num) {
		// break;
		// }
		// //防止死循环
		// if (count > 1000000) {
		// break;
		// }
		// }
		if (maxNum < 1) {
			return list;
		}
		if (num > 0) {
			int offset = maxNum / num;
			if (offset > 1) {
				for (int n = 0; n < num; n++) {
					if(n==num-1){
						list.add(StringUtil.getRandom(offset * n + 1, maxNum));
					}else{
						list.add(StringUtil.getRandom(offset * n + 1, offset * (n + 1)));
					}
				}
			} else {
				int[] array = new int[maxNum];
				int a1 = 0;
				int a2 = 0;
				for (int n = 0; n < maxNum; n++) {
					array[n] = n + 1;
				}
				int temp = 0;
				for (int n = 0; n < maxNum; n++) {
					a1 = StringUtil.getRandom(0,maxNum-1);
					a2 = StringUtil.getRandom(0,maxNum-1);
					temp = array[a1];
					array[a1]=array[a2];
					array[a2]=temp;
				}
				for (int n = 0; n < num; n++) {
					list.add(array[n]);
				}
			}
		}
		return list;
	}

	/**
	 * 从map中随机出三个数值类型的Key，以List<Integer>格式返回，*****下标是从1开始的*****
	 * 
	 * @author mp
	 * @date 2013-11-23 下午5:02:33
	 * @param num
	 * @return
	 * @Description
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Integer> getRandomNoRepeat(int num, Map map) {
		List<Integer> retList = new ArrayList<Integer>();
		Set<Integer> set = map.keySet();
		Object[] intArray = set.toArray();
		if (map.size() >= num) {
			List<Integer> list = RandomUtil.getRandomNoRepeat(num, intArray.length);
			for (int i : list) {
				int value = (int) intArray[i - 1];
				retList.add(value);
			}
		}
		return retList;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			System.out.println(RandomUtil.getRandomInt(4));
		}
	}

	/**
	 * 返回指定范围内的随机数[min, max]
	 * 
	 * @author mp
	 * @date 2013-11-5 上午10:00:10
	 * @param min
	 * @param max
	 * @return
	 * @Description
	 */
	public static int getRangeInt(int min, int max) {
		return new Random().nextInt(max - min + 1) + min;
	}

	/**
	 * 得到[0-maxNum)的随机数
	 * 
	 * @author mp
	 * @date 2013-10-17 下午2:43:35
	 * @param maxNum
	 * @return
	 * @Description
	 */
	public static int getRandomInt(int maxNum) {
		return new Random().nextInt(maxNum);
	}

	/**
	 * 得到(0,1)的随机数
	 * 
	 * @author mp
	 * @date 2013-10-18 上午11:29:45
	 * @return
	 * @Description
	 */
	public static float getRandomFloat() {
		return new Random().nextFloat();
	}

}