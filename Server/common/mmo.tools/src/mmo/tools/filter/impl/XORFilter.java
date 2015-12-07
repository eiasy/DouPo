package mmo.tools.filter.impl;

import mmo.tools.filter.IFilter;

public class XORFilter implements IFilter {
	private byte[]  secKey   = { 11, 21, 31, 42, 53, 64, 75, 86, 97 };
	private int     count    = 2;
	private boolean isFilter = false;

	public XORFilter() {

	}

	public byte[] onReceiveData(byte[] data) {
		if (isFilter) {
			return onReceiveData(data, secKey, count);
		}
		return data;
	}

	public byte[] onSendData(byte[] data) {
		if (isFilter) {
			return onSendData(data, secKey, count);
		}
		return data;
	}

	public byte[] onReceiveData(byte[] data, byte[] key, int count) {
		if (isFilter) {
			if (key == null) {
				key = secKey;
			}
			if (count < 0) {
				count = this.count;
			}
			int dataLength = data.length;
			int secKeyLength = key.length;
			while (count > 0) {
				for (int i = 0; i < dataLength; i++) {
					data[i] = (byte) (data[i] ^ key[i * count % secKeyLength]);
				}
				count--;
			}
			return data;
		}
		return data;
	}

	public byte[] onSendData(byte[] data, byte[] key, int count) {
		if (isFilter) {
			if (key == null) {
				key = secKey;
			}
			if (count < 0) {
				count = this.count;
			}
			int dataLength = data.length;
			int secKeyLength = key.length;
			byte[] temp = new byte[dataLength];
			System.arraycopy(data, 0, temp, 0, dataLength);
			while (count > 0) {
				for (int i = 0; i < dataLength; i++) {
					temp[i] = (byte) (temp[i] ^ key[i * count % secKeyLength]);
				}
				count--;
			}
			return temp;
		}
		return data;
	}

	public int filter(int value, byte[] key, int count) {
		if (isFilter) {
			if (key == null) {
				key = secKey;
			}
			if (count < 0) {
				count = this.count;
			}
			int secKeyLength = key.length;
			int v0 = (value >>> 24) & 0xFF;
			int v1 = (value >>> 16) & 0xFF;
			int v2 = (value >>> 8) & 0xFF;
			int v3 = (value >>> 0) & 0xFF;
			while (count > 0) {
				v0 = v0 ^ key[1 * count % secKeyLength];
				v1 = v1 ^ key[2 * count % secKeyLength];
				v2 = v2 ^ key[3 * count % secKeyLength];
				v3 = v3 ^ key[4 * count % secKeyLength];
				count--;
			}
			return (v0 << 24) + (v1 << 16) + (v2 << 8) + (v3 << 0);
		}
		return value;
	}
	
	public void setFilter(boolean isFilter){
		this.isFilter = isFilter;
	}
	
	public boolean isFilter(){
		return isFilter;
	}
}
