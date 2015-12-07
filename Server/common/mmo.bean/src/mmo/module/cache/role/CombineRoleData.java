package mmo.module.cache.role;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.sql.Blob;

import mmo.tools.log.LoggerError;

import org.apache.mina.core.buffer.IoBuffer;

public class CombineRoleData extends CacheRole {
	private IoBuffer mansionData;

	public IoBuffer getMansionData() {
		return mansionData;
	}

	public void setMansionData(IoBuffer mansionData) {
		this.mansionData = mansionData;
	}

	public void setMansionData(Blob roleDatas) {
		if (roleDatas == null) {
			return;
		}
		InputStream is = null;
		BufferedInputStream bis = null;
		DataInputStream dis = null;
		try {
			is = roleDatas.getBinaryStream();
			bis = new BufferedInputStream(is);
			dis = new DataInputStream(bis);
			byte[] data = new byte[dis.available()];
			dis.read(data);
			mansionData = IoBuffer.getPacketBuffer();
			mansionData.put(data);
			mansionData.flip();
		} catch (Exception e) {
			LoggerError.error("加载仙府大数据报错", e);
		}
	}
}
