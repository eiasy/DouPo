package mmo.tools.test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.apache.mina.core.buffer.IoBuffer;

public class TestStream {
	public static void main(String[] arg) {
		int position = 0;
		IoBuffer buf = IoBuffer.getPacketBuffer();

		buf.putInt(1001);
		position = buf.position();
		buf.putInt(0);
		buf.put((byte) 1);
		buf.putInt(position, buf.position());

		buf.putInt(1002);
		position = buf.position();
		buf.putInt(0);
		buf.putShort((short) 2);
		buf.putInt(position, buf.position());

		buf.putInt(1003);
		position = buf.position();
		buf.putInt(0);
		buf.putInt(3);
		buf.putInt(position, buf.position());

		buf.putInt(1004);
		position = buf.position();
		buf.putInt(0);
		buf.putLong(4);
		buf.putInt(position, buf.position());

		buf.putInt(1005);
		position = buf.position();
		buf.putInt(0);
		buf.putString("1贰3肆");
		buf.putInt(position, buf.position());

		buf.putInt(1006);
		position = buf.position();
		buf.putInt(0);
		buf.put((byte) 5);
		buf.putInt(position, buf.position());

		buf.putInt(1007);
		position = buf.position();
		buf.putInt(0);
		buf.putShort((short) 6);
		buf.putInt(position, buf.position());

		buf.putInt(1008);
		position = buf.position();
		buf.putInt(0);
		buf.putInt(7);
		buf.putInt(position, buf.position());

		buf.putInt(1009);
		position = buf.position();
		buf.putInt(0);
		buf.putLong(8);
		buf.putInt(position, buf.position());

		buf.putInt(1010);
		position = buf.position();
		buf.putInt(0);
		buf.putString("伍6柒8");
		buf.putInt(position, buf.position());

		buf.putInt(-1);
		buf.flip();
		byte[] array = new byte[buf.limit()];
		buf.get(array);

		DataInputStream dis = new DataInputStream(new ByteArrayInputStream(array));
		try {
			array = new byte[dis.available()];
			dis.read(array);
			buf = IoBuffer.getPacketBuffer();
			buf.put(array);
			buf.flip();

			int pro = 0;
			int pos = 0;
			Object value = null;
			while ((pro = buf.getInt()) != -1) {
				System.out.print(pro + ", ");
				pos = buf.getInt();
				System.out.print(pos + ", ");
				value = null;
				try {
					switch (pro) {
						case 1001: {
							value = buf.get();
							break;
						}
						case 1002: {
							// value = buf.getShort();
							break;
						}
						case 1003: {
							value = buf.getInt();
							break;
						}
						case 1004: {
							value = buf.getLong();
							break;
						}
						case 1006: {
							value = buf.get();
							break;
						}
						case 1007: {
							value = buf.getShort();
							break;
						}
						case 1008: {
							value = buf.getInt();
							break;
						}
						case 1009: {
							value = buf.getLong();
							break;
						}
						case 1005: {
							value = buf.getString();
							break;
						}
						case 1010: {
							value = buf.getString();
							break;
						}
					}
					System.out.println(value + ",");
				} catch (Exception e) {
					e.printStackTrace();// TODO: handle exception
				} finally {
					System.out.println(pos);
					buf.position(pos);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
