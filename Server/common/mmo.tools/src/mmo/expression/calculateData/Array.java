package mmo.expression.calculateData;


/**
 * 数组工具
 */
public final class Array {

	/**
	 * 检测数组长度，如果长度不足，则数组长度为length+growLength
	 * 
	 * @param array
	 *            数组
	 * @param length
	 *            需要的数组长度
	 * @param growLength
	 *            数组增长长度
	 * @return 数组
	 */
	public static byte[] checkArrayLength(byte[] array, int length,
			int growLength) {
		if (length >= array.length) {
			array = arrayGrow(array, length + growLength - array.length);
		}
		return array;
	}

	public static long[] checkArrayLength(long[] array, int length,
			int growLength) {
		if (length >= array.length) {
			array = arrayGrow(array, length + growLength - array.length);
		}
		return array;
	}

	public static short[] checkArrayLength(short[] array, int length,
			int growLength) {
		if (length >= array.length) {
			array = arrayGrow(array, length + growLength - array.length);
		}
		return array;
	}

	public static Object[] checkArrayLength(Object[] array, int length,
			int growLength) {
		if (length >= array.length) {
			array = arrayGrow(array, length + growLength - array.length);
		}
		return array;
	}

	public static int[] checkArrayLength(int[] array, int length, int growLength) {
		if (length >= array.length) {
			array = arrayGrow(array, length + growLength - array.length);
		}
		return array;
	}

	public static String[] checkArrayLength(String[] array, int length,
			int growLength) {
		if (length >= array.length) {
			array = arrayGrow(array, length + growLength - array.length);
		}
		return array;
	}

	public static byte[] arrayCopy(byte[] array) {
		return newArray(array, array.length);
	}

	public static short[] arrayCopy(short[] array) {
		return newArray(array, array.length);
	}

	public static int[] arrayCopy(int[] array) {
		return newArray(array, array.length);
	}

	public static String[] arrayCopy(String[] array) {
		return newArray(array, array.length);
	}

	public static Object[] arrayCopy(Object[] array) {
		return newArray(array, array.length);
	}

	public static Object[] arrayGrow(Object[] array, int growLen) {
		if (array == null) {
			return new Object[growLen];
		}
		return newArray(array, array.length + growLen);
	}

	public static long[] arrayGrow(long[] array, int growLen) {
		if (array == null) {
			return new long[growLen];
		}
		return newArray(array, array.length + growLen);
	}

	public static boolean[] arrayGrow(boolean[] array, int growLen) {
		if (array == null) {
			return new boolean[growLen];
		}
		return newArray(array, array.length + growLen);
	}

	public static String[] arrayGrow(String[] array, int growLen) {
		if (array == null) {
			return new String[growLen];
		}
		return newArray(array, array.length + growLen);
	}

	public static int[] arrayGrow(int[] array, int growLen) {
		if (array == null) {
			return new int[growLen];
		}
		return newArray(array, array.length + growLen);
	}

	public static short[] arrayGrow(short[] array, int growLen) {
		if (array == null) {
			return new short[growLen];
		}
		return newArray(array, array.length + growLen);
	}

	public static byte[] arrayGrow(byte[] array, int growLen) {
		if (array == null) {
			return new byte[growLen];
		}
		return newArray(array, array.length + growLen);
	}

	public static int[][] arrayGrow(int[][] array, int growLen) {
		return newArray(array, array.length + growLen);
	}

	public static short[][] arrayGrow(short[][] array, int growLen) {
		return newArray(array, array.length + growLen);
	}

	public static Object[] newArray(Object[] array, int newArrayLen) {

		Object[] newArray = new Object[newArrayLen];
		if(array!=null){
			System.arraycopy(array, 0, newArray, 0,
					newArrayLen > array.length ? array.length : newArrayLen);
			}
		return newArray;
	}

	public static long[] newArray(long[] array, int newArrayLen) {

		long[] newArray = new long[newArrayLen];
		if(array!=null){
			System.arraycopy(array, 0, newArray, 0,
					newArrayLen > array.length ? array.length : newArrayLen);
			}
		return newArray;
	}

	public static boolean[] newArray(boolean[] array, int newArrayLen) {

		boolean[] newArray = new boolean[newArrayLen];
		if(array!=null){
			System.arraycopy(array, 0, newArray, 0,
					newArrayLen > array.length ? array.length : newArrayLen);
			}
		return newArray;
	}

	public static byte[] newArray(byte[] array, int newArrayLen) {

		byte[] newArray = new byte[newArrayLen];
		if(array!=null){
		System.arraycopy(array, 0, newArray, 0,
				newArrayLen > array.length ? array.length : newArrayLen);
		}
		return newArray;
	}

	public static int[] newArray(int[] array, int newArrayLen) {

		int[] newArray = new int[newArrayLen];
		if(array!=null){			
		System.arraycopy(array, 0, newArray, 0,
				newArrayLen > array.length ? array.length : newArrayLen);
		}
		return newArray;
	}

	public static byte[][] newArray(byte[][] array, int newArrayLen) {

		byte[][] newArray = new byte[newArrayLen][];
		if(array!=null){
			System.arraycopy(array, 0, newArray, 0,
					newArrayLen > array.length ? array.length : newArrayLen);
			}
		return newArray;
	}

	public static short[][] newArray(short[][] array, int newArrayLen) {

		short[][] newArray = new short[newArrayLen][];
		if(array!=null){
			System.arraycopy(array, 0, newArray, 0,
					newArrayLen > array.length ? array.length : newArrayLen);
			}
		return newArray;
	}

	public static String[] newArray(String[] array, int newArrayLen) {

		String[] newArray = new String[newArrayLen];
		if(array!=null){
			System.arraycopy(array, 0, newArray, 0,
					newArrayLen > array.length ? array.length : newArrayLen);
			}
		return newArray;
	}

	public static short[] newArray(short[] array, int newArrayLen) {

		short[] newArray = new short[newArrayLen];
		if(array!=null){
			System.arraycopy(array, 0, newArray, 0,
					newArrayLen > array.length ? array.length : newArrayLen);
			}
		return newArray;
	}

	public static int[][] newArray(int[][] array, int newArrayLen) {

		int[][] newArray = new int[newArrayLen][];
		if(array!=null){
			System.arraycopy(array, 0, newArray, 0,
					newArrayLen > array.length ? array.length : newArrayLen);
			}
		return newArray;
	}

	/**
	 * 互换数组的元素
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	public static final void swap(int[] arr, int i, int j) {
		int temp = arr[j];
		arr[j] = arr[i];
		arr[i] = temp;
	}
	
	
//	/**
//	 * 数组拷贝，有数据表明，20个以内，这样拷贝比较快，20个以上，用system.arrayCopy比较快
//	 */
//	public static void arraycopy(String[] array1,int index1,String[] array2,int index2,int len){
//		for(int i=0;i<len;i++){
//			array2[index2+i]=array1[index1+i];
//		}
//	}
	/**
	 * 数组拷贝，有数据表明，20个以内，这样拷贝比较快，20个以上，用system.arrayCopy比较快
	 */
	public static void arraycopy(int[] array1,int index1,int[] array2,int index2,int len){
		for(int i=0;i<len;i++){
			array2[index2+i]=array1[index1+i];
		}
	}
	/**
	 * 数组拷贝，有数据表明，20个以内，这样拷贝比较快，20个以上，用system.arrayCopy比较快
	 */
	public static void arraycopy(short[] array1,int index1,short[] array2,int index2,int len){
		for(int i=0;i<len;i++){
			array2[index2+i]=array1[index1+i];
		}
	}
}
