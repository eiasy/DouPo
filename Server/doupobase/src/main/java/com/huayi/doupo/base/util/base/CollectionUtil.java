package com.huayi.doupo.base.util.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 集合工具类
 * @author mp
 * @date 2014-9-23 下午4:00:01
 */
public class CollectionUtil {
	
	/**
	 * 冒泡排序-从小到大
	 * @author mp
	 * @date 2014-9-23 下午4:42:03
	 * @param source
	 * @return
	 * @Description
	 */
    public static int[] bubbleSort(int[] source) {
        for (int i = 0; i < source.length -1; i++){
            for(int j = 0 ;j < source.length - i - 1; j++){
                if(source[j] < source[j + 1]){
                    int temp = source[j];
                    source[j] = source[j + 1];
                    source[j + 1] = temp;
                }
            }            
        }
        return source;
    }
    
    /**
     * 为数组排序-从小到大
     * @author mp
     * @date 2014-9-23 下午4:58:48
     * @param source
     * @return
     * @Description
     */
    public static <T> T [] sort (T [] source) {
    	 Arrays.sort(source);
    	 return source;
    }
    
    /**
     * 反转数组
     * @author mp
     * @date 2014-9-23 下午5:30:14
     * @param source
     * @return
     * @Description
     */
     public static <T> T[] reverse(T[] source) {
         int length = source.length;
         for (int i = 0; i < length / 2; i++) {
             T temp = source[i];
             source[i] = source[length - 1 - i];
             source[length - 1 - i] = temp;
         }
         return source;
     }
     
     /**
      * 将数组转换成List
      * @author mp
      * @date 2014-10-22 上午10:18:11
      * @param source
      * @return
      * @Description
      */
     public static <T> List<T> asList (T [] array) {
    	 return Arrays.asList(array);
     }
     
     /**
      * 将List转换成数组
      * @author mp
      * @date 2014-10-22 上午10:22:36
      * @param list
      * @return
      * @Description
      */
     @SuppressWarnings("unchecked")
	public static <T> T[] toArray (List<T> list) {
    	 return (T[])list.toArray();
     }
    
    /**
     * 打印数组
     * @author mp
     * @date 2014-9-23 下午5:22:58
     * @param source
     * @return
     * @Description
     */
    public static <T> void print (T [] source) {
    	System.out.println(Arrays.toString(source)); 
    }
    
    /**
     * 洗牌,打乱List的顺序
     * @author mp
     * @date 2014-9-23 下午5:05:13
     * @param list
     * @Description
     */
    public static void shuffle (List<?> list) {
    	Collections.shuffle(list);
    }

	/**
	 * 数组乱序
	 * @author hzw
	 * @date 2014-7-8下午4:50:29
	 * @param msgMap
	 * @param channelId
	 * @throws Exception
	 * @Description
	 */
	public static int[] getSequence(int[] temp) {
		int[] sequence = temp;
		int no = sequence.length;
		Random random = new Random();
		for (int i = 0; i < no; i++) {
			int p = random.nextInt(no);
			int tmp = sequence[i];
			sequence[i] = sequence[p];
			sequence[p] = tmp;
		}
		random = null;
		return sequence;
	}
	
	/**
	 * map 按value排序 降序
	 * @author mp
	 * @date 2015-8-18 上午9:18:28
	 * @param map
	 * @return
	 * @Description
	 */
    public static <K, V extends Comparable<? super V>> Map<K, V>  sortByValueDown( Map<K, V> map ) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
                return (o2.getValue()).compareTo( o1.getValue() );
            }
        } );
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list){
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
	/**
	 * map 按value排序 升序
	 * @author mp
	 * @date 2015-8-18 上午9:18:28
	 * @param map
	 * @return
	 * @Description
	 */
    public static <K, V extends Comparable<? super V>> Map<K, V>  sortByValueUp( Map<K, V> map ) {
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort( list, new Comparator<Map.Entry<K, V>>() {
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        } );
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list){
            result.put( entry.getKey(), entry.getValue() );
        }
        return result;
    }

	
    public static void main(String[] args) {
    	
    	List<Integer> integers = new ArrayList<>();
    	integers.add(1);
    	integers.add(2);
    	integers.add(3);
    	System.out.println(integers);
    	
    	
//    	System.out.println("aaaaaaaaaaa");
    	
/*    	Integer [] abc = new Integer []{10,1,222,55,11111};
//    	reverse(abc);
//		sort(abc);
    	List<Integer> intList = asList(abc);
    	System.out.println(intList);
		print(intList.toArray());*/
    	
/*    	ConcurrentHashMap<Integer, Integer> recruitJifen = new ConcurrentHashMap<Integer, Integer>();
    	recruitJifen.put(1, 1);
    	recruitJifen.put(2, 2);
    	recruitJifen.put(3, 3);
    	recruitJifen.put(4, 4);
    	
    	Map<Integer, Integer> afterMap = sortByValueDown(recruitJifen);
    	
    	System.out.println(afterMap);*/
    	
//    	ConcurrentHashMap<Integer, String> recruitJifen = new ConcurrentHashMap<Integer, String>();
//    	recruitJifen.put(1, "2");
//    	System.out.println(recruitJifen.get(10));
    	
	}
	
}