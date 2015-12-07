package mmo.tools.util;

public final class SynInteger {
	private int value;

	public SynInteger(int value) {
		this.value = value;
	}

	public SynInteger() {

	}

	public int getAndIncrement() {
		int i = 0;
		synchronized (this) {
			i = value++;
		}
		return i;
	}

	public int getAndDecrement() {
		int i = 0;
		synchronized (this) {
			i = value--;
		}
		return i;
	}

	public int get() {
		int i = 0;
		synchronized (this) {
			i = value;
		}
		return i;
	}

	public void set(int value) {
		synchronized (this) {
			this.value = value;
		}
	}
}
