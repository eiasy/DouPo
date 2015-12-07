package mmo.extension.mina.codec;

import mmo.extension.application.ApplicationConfig;
import mmo.tools.log.LoggerError;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class GameDecoder extends CumulativeProtocolDecoder {
	protected static ApplicationConfig ac       = ApplicationConfig.getInstance();
	protected static final String      TYPE     = "RECEIVE";
	protected long                     logTime  = System.currentTimeMillis();
	protected long                     total    = 0;
	protected int                      pkgCount = 0;
	protected int                      pkgMax   = 0;
	protected CodecFactory         codecFactor;

	public GameDecoder(CodecFactory codecFactor) {
		this.codecFactor = codecFactor;
	}

	protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		if (in.prefixedDataAvailable(4)) {
			int dataLength = in.getInt();
			IoBuffer pb = PacketBufferPool.getPacketBuffer();
			if (pb.capacity() < dataLength) {
				pb.capacity(dataLength);
			}
			in.get(pb.array(), 0, dataLength);
			pb.position(dataLength);
			pb.flip();
			if (dataLength == 0) {
				LoggerError.messageLog.error("doDecode dataLength = " + dataLength);
				return true;
			}
			out.write(pb);
			if (ac.isListenNet()) {
				long curTime = System.currentTimeMillis();
				if (curTime > logTime + ac.getRefreshOffset()) {
					long offset = curTime - logTime;
					logTime = curTime;
					LogNet.loggerNetPkg(TYPE, pkgMax, pkgCount, total, offset, codecFactor.getType(), codecFactor.getDesc());
					pkgMax = dataLength;
					pkgCount = 1;
					total = dataLength;
				} else {
					if (dataLength > pkgMax) {
						pkgMax = dataLength;
					}
					pkgCount++;
					total += dataLength;
				}
			}
			return true;
		}
		return false;
	}
}
