package mmo.tools.util;

public class CollectionUtil {
	private Object[] objects;
	private int[]    indexes;

	public CollectionUtil() {
		objects = new Object[0];
		indexes = new int[0];
	}

	/**
	 * 将对象加入到容器中 参数1:对象 参数2:对象对应的编号
	 */
	public void addObject(Object _object, int index) {
		Object[] _objects = new Object[objects.length + 1];
		int[] tempIndexes = new int[indexes.length + 1];

		System.arraycopy(objects, 0, _objects, 0, objects.length);
		System.arraycopy(indexes, 0, tempIndexes, 0, indexes.length);

		_objects[_objects.length - 1] = _object;
		tempIndexes[tempIndexes.length - 1] = index;

		objects = _objects;
		indexes = tempIndexes;
	}

	/**
	 * 判断容器中有无改编号的对象 参数1:对象对应的编号 返回:是否有该对象 true 有 false 无
	 */
	public boolean isHaveObject(int index) {
		int count = indexes.length;
		for (int i = 0; i < count; i++) {
			if (indexes[i] == index)
				return true;
		}
		return false;
	}

	/**
	 * 得到对象数组 返回:对象数组
	 */
	public Object[] getObjects() {
		return objects;
	}

	/**
	 * 得到编号数组 返回:对象编号
	 */
	public int[] getIndexes() {
		return indexes;
	}

	/**
	 * 释放资源
	 */
	public void finalize() {
		objects = null;
		indexes = null;
	}
}