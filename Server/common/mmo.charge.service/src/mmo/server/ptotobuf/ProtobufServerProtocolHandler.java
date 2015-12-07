package mmo.server.ptotobuf;

import mmo.common.proto.ProtobufMessage;
import mmo.extension.mina.server.logichandler.ServerProtocolHandler;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IoSession;

public class ProtobufServerProtocolHandler extends ServerProtocolHandler
		implements IoHandler {

	public ProtobufServerProtocolHandler() {

	}

	public void sessionOpened(IoSession session) throws Exception {
		if (netConfim > 0) {
			ProtobufMessage.Msg.Builder mb = ProtobufMessage.Msg.newBuilder();
			mb.setHeader(1000);
			mb.setVersion(1);
			ProtobufMessage.MsgData.Builder mdb = ProtobufMessage.MsgData
					.newBuilder();
			ProtobufMessage.IntItem.Builder ib_1 = ProtobufMessage.IntItem
					.newBuilder();
			ib_1.setKey("a-1");
			ib_1.setValue(1);
			mdb.addIntItem(ib_1);
			ProtobufMessage.IntItem.Builder ib_2 = ProtobufMessage.IntItem
					.newBuilder();
			ib_2.setKey("a-2");
			ib_2.setValue(2);
			mdb.addIntItem(ib_2);
			ProtobufMessage.LongItem.Builder lb_3 = ProtobufMessage.LongItem
					.newBuilder();
			lb_3.setKey("多少l-3");
			lb_3.setValue(3);
			mdb.addLongItem(lb_3);

			ProtobufMessage.MessageItem.Builder mib = ProtobufMessage.MessageItem
					.newBuilder();

			ProtobufMessage.MsgData.Builder mdb_2 = ProtobufMessage.MsgData
					.newBuilder();
			ib_1 = ProtobufMessage.IntItem.newBuilder();
			ib_1.setKey("a-11");
			ib_1.setValue(1);
			mdb_2.addIntItem(ib_1);
			ib_2 = ProtobufMessage.IntItem.newBuilder();
			ib_2.setKey("a--22");
			ib_2.setValue(2);
			mdb_2.addIntItem(ib_2);
			lb_3 = ProtobufMessage.LongItem.newBuilder();
			lb_3.setKey("l--33");
			lb_3.setValue(3);
			mdb_2.addLongItem(lb_3);
			mib.setMsgdata(mdb_2);
			mib.setKey("达到mi--1");
			mdb.addMessageItem(mib);
			mb.setMsgdata(mdb);

			ProtobufMessage.Msg message = mb.build();
			byte[] packet = message.toByteArray();
			IoBuffer data = IoBuffer.getPacketBuffer();
			data.putInt(packet.length);
			data.put(packet, 0, packet.length);
			data.flip();
			session.write(data);
		}
	}
}