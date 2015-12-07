package mmo.tools.util.vector;

import java.util.Enumeration;
import java.util.NoSuchElementException;

public class EntityVector {
	private Entity[] elementData;
	private int      elementCount;
	private int      capacityIncrement = 20;

	public EntityVector(int initialCapacity, int capacityIncrement) {
		if (initialCapacity < 0) {
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		}
		this.elementData = new Entity[initialCapacity];
		this.capacityIncrement = capacityIncrement;
	}

	public EntityVector(int initialCapacity) {
		this(initialCapacity, 0);
	}

	public EntityVector() {
		this(20);
	}

	public EntityVector(EntityVector c) {
		elementData = c.toArray();
		elementCount = elementData.length;
	}

	public final synchronized void copyInto(Object[] anArray) {
		System.arraycopy(elementData, 0, anArray, 0, elementCount);
	}

	public final synchronized void trimToSize() {
		int oldCapacity = elementData.length;
		if (elementCount < oldCapacity) {
			Entity[] element = new Entity[elementCount];
			System.arraycopy(elementData, 0, element, 0, elementCount);
			elementData = element;
			element = null;
		}
	}

	public final synchronized void ensureCapacity(int minCapacity) {
		ensureCapacityHelper(minCapacity);
	}

	private final void ensureCapacityHelper(int minCapacity) {
		int oldCapacity = elementData.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (capacityIncrement > 0) ? (oldCapacity + capacityIncrement) : (oldCapacity * 2);
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			Entity[] element = new Entity[newCapacity];
			System.arraycopy(elementData, 0, element, 0, elementCount);
			elementData = element;
			element = null;
		}
	}

	public final synchronized void setSize(int newSize) {

		if (newSize > elementCount) {
			ensureCapacityHelper(newSize);
		} else {
			for (int i = newSize; i < elementCount; i++) {
				elementData[i] = null;
			}
		}
		elementCount = newSize;
	}

	public final synchronized int capacity() {
		return elementData.length;
	}

	public final synchronized int size() {
		return elementCount;
	}

	public final synchronized boolean isEmpty() {
		return elementCount == 0;
	}

	public final Enumeration<?> elements() {
		return new Enumeration<Object>() {
			int count = 0;

			public boolean hasMoreElements() {
				return count < elementCount;
			}

			public Object nextElement() {
				synchronized (EntityVector.this) {
					if (count < elementCount) {
						return elementData[count++];
					}
				}
				throw new NoSuchElementException("Vector Enumeration");
			}
		};
	}

	public final boolean contains(Object o) {
		return indexOf(o, 0) >= 0;
	}

	public int indexOf(Object o) {
		return indexOf(o, 0);
	}

	public final synchronized int indexOf(Object o, int index) {
		if (o == null) {
			for (int i = index; i < elementCount; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = index; i < elementCount; i++) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	public final synchronized int indexOfRole(Entity o, int index) {
		if (o == null) {
			for (int i = index; i < elementCount; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = index; i < elementCount; i++) {
				if (o == elementData[i]) {
					return i;
				}
			}
		}
		return -1;
	}

	public final synchronized boolean removeRole(Entity obj) {
		int i = indexOfRole(obj, 0);
		if (i >= 0) {
			removeElementAt(i);
			return true;
		}
		return false;
	}

	public final synchronized boolean containsRole(Entity o) {
		int index = 0;
		if (o == null) {
			for (int i = index; i < elementCount; i++) {
				if (elementData[i] == null) {
					return true;
				}
			}
		} else {
			for (int i = index; i < elementCount; i++) {
				if (o == elementData[i]) {
					return true;
				}
			}
		}
		return false;
	}

	public final synchronized int lastIndexOf(Object o) {
		return lastIndexOf(o, elementCount - 1);
	}

	public final synchronized int lastIndexOf(Object o, int index) {
		if (index >= elementCount) {
			throw new IndexOutOfBoundsException(index + " >= " + elementCount);
		}

		if (o == null) {
			for (int i = index; i >= 0; i--) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = index; i >= 0; i--) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

	public final synchronized Entity elementAt(int index) {
		if (index >= elementCount) {
			throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
		}
		return elementData[index];
	}

	public final synchronized Entity firstElement() {
		if (elementCount == 0) {
			return null;
		}
		return elementData[0];
	}

	public final synchronized Entity lastElement() {
		if (elementCount == 0) {
			throw new NoSuchElementException();
		}
		return elementData[elementCount - 1];
	}

	public final synchronized void setElementAt(Entity obj, int index) {
		if (index >= elementCount) {
			throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
		}
		elementData[index] = obj;
	}

	public final synchronized void removeElementAt(int index) {
		if (index >= elementCount) {
			throw new ArrayIndexOutOfBoundsException(index + " >= " + elementCount);
		} else if (index < 0) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		int j = elementCount - index - 1;
		if (j > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, j);
		}
		elementCount--;
		elementData[elementCount] = null;
	}

	public final synchronized void insertElementAt(Entity obj, int index) {
		if (index > elementCount) {
			throw new ArrayIndexOutOfBoundsException(index + " > " + elementCount);
		}
		ensureCapacityHelper(elementCount + 1);
		System.arraycopy(elementData, index, elementData, index + 1, elementCount - index);
		elementData[index] = obj;
		elementCount++;
	}

	public final synchronized void addElement(Entity obj) {
		ensureCapacityHelper(elementCount + 1);
		elementData[elementCount++] = obj;
	}

	public final synchronized boolean removeElement(Object obj) {
		int i = indexOf(obj);
		if (i >= 0) {
			removeElementAt(i);
			return true;
		}
		return false;
	}

	public final synchronized void removeAllElements() {
		for (int i = 0; i < elementCount; i++) {
			elementData[i] = null;
		}
		elementCount = 0;
	}

	public final synchronized Entity[] toArray() {
		Entity[] element = new Entity[elementCount];
		System.arraycopy(elementData, 0, element, 0, elementCount);
		return element;
	}

	public final synchronized Entity[] toArray(Entity[] a) {
		if (a.length < elementCount) {
			a = new Entity[elementCount];
		}
		System.arraycopy(elementData, 0, a, 0, elementCount);
		if (a.length > elementCount) {
			a[elementCount] = null;
		}
		return a;
	}

	public final synchronized Entity get(int index) {
		if (index >= elementCount) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		return elementData[index];
	}

	public final synchronized Entity set(int index, Entity element) {
		if (index >= elementCount) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		Entity oldValue = elementData[index];
		elementData[index] = element;
		return oldValue;
	}

	public final synchronized boolean add(Entity e) {
		ensureCapacityHelper(elementCount + 1);
		elementData[elementCount++] = e;
		return true;
	}

	public final boolean remove(Object o) {
		return removeElement(o);
	}

	public final synchronized void orderAdd(Entity element) {
		int i = 0;
		for (; i < elementCount; i++) {
			if (element.getEntityValue() > elementData[i].getEntityValue()) {
				break;
			}
		}
		insertElementAt(element, i);
	}

	public final void add(int index, Entity element) {
		insertElementAt(element, index);
	}

	public final synchronized Entity remove(int index) {
		if (elementCount == 0) {
			return null;
		}
		if (index >= elementCount) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
		Entity oldValue = elementData[index];
		int numMoved = elementCount - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		elementData[--elementCount] = null;

		return oldValue;
	}

	public final void clear() {
		removeAllElements();
	}

	public final synchronized boolean addAll(EntityVector c) {
		Entity[] a = c.toArray();
		int numNew = a.length;
		ensureCapacityHelper(elementCount + numNew);
		System.arraycopy(a, 0, elementData, elementCount, numNew);
		elementCount += numNew;
		return numNew != 0;
	}

	public final synchronized boolean addAll(int index, EntityVector c) {
		if (index < 0 || index > elementCount)
			throw new ArrayIndexOutOfBoundsException(index);

		Entity[] a = c.toArray();
		int numNew = a.length;
		ensureCapacityHelper(elementCount + numNew);

		int numMoved = elementCount - index;
		if (numMoved > 0) {
			System.arraycopy(elementData, index, elementData, index + numNew, numMoved);
		}
		System.arraycopy(a, 0, elementData, index, numNew);
		elementCount += numNew;
		return numNew != 0;
	}

	public final synchronized boolean equals(Object o) {
		return super.equals(o);
	}

	public final synchronized int hashCode() {
		return super.hashCode();
	}

	public final synchronized String toString() {
		return super.toString();
	}
}
