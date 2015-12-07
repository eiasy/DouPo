package com.huayi.doupo.logic.core.filter.netty;

public class EncryptHeader {

	public final static int FLAG_COMPRESSED = 0x00000001; // flags压缩标识位
	public final static int SEQUENCE_NUMBER_INCREMENT = 0xd62ca587; // 序号增量

	private int flags; // flags标识
	private int recvSequenceNumber; // 接收序号
	private int sendSequenceNumber; // 发送序号

	public int getFlags() {
		return flags;
	}

	public void setFlags(int flags) {
		this.flags = flags;
	}

	public boolean isFlagsCompressed() {
		return (flags & FLAG_COMPRESSED) != 0;
	}

	public int getRecvSequenceNumber() {
		return recvSequenceNumber;
	}

	public void setRecvSequenceNumber(int sn) {
		this.recvSequenceNumber = sn;
	}

	public int getSendSequenceNumber() {
		return sendSequenceNumber;
	}

	public void setSendSequenceNumber(int sn) {
		this.sendSequenceNumber = sn;
	}

}
