package mmo.tools.script.msc.Struct;

import java.util.Vector;

public class Link {

	// LinkedListNode * pHead, // Pointer to head node
	// * pTail; // Pointer to tail nail node
	//
	// int iNodeCount; // The number of nodes in the list

	private Vector vec;
	private int    size;

	public Link() {
		vec = new Vector();
	}

	public int addNode(Object obj) {
		vec.add(obj);
		size++;
		return size - 1;
	}

	public void setNode(Object obj, int index) {
		vec.setElementAt(obj, index);
	}

	public boolean delNode(Object obj) {
		size--;
		return vec.removeElement(obj);
	}

	public void insertNode(Object obj, int index) {
		vec.insertElementAt(obj, index);
		size++;
	}

	public Object getNode(int index) {
		return vec.get(index);
	}

	public Object getTailNode() {
		if (size > 0)
			return vec.get(size - 1);
		else
			return null;
	}

	public int getSize() {
		return size;
	}

	public void delTailNode() {
		if (size > 0) {
			vec.remove(size - 1);
			size--;
		}
	}

	/**
	 * Add delNode from index
	 */
	public void delNode(int index) {
		if (index < size) {
			vec.remove(index);
			size--;
		}
	}

	public void free() {
		if (vec != null) {
			vec.clear();
		}
	}
}
