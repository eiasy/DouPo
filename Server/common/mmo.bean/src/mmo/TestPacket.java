package mmo;

import java.util.ArrayList;
import java.util.List;

import mmo.common.protocol.ui.ClientUI;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.buffer.PacketBufferPool;

public class TestPacket {

	public static final void test6005(IoBuffer packet, boolean print) {
		int pro = packet.getInt(4);
		switch (pro) {
			case 6005: {
				test_6005(packet, print);
				break;
			}
			case 6500:
			case 6501:
			case 6509: {
				test_6500_6501(packet, print);
				break;
			}
			case 6511: {
				IoBuffer pck = packet.duplicateBuffer();
				pck.flip();
				int length = pck.getInt();
				if (print) {
					System.out.println("length=" + length);
				}
				pro = pck.getInt();
				if (print) {
					System.out.println("pro=" + pro);
				}
				int roleId = pck.getInt();
				if (print) {
					System.out.println("roleId=" + roleId);
				}
				prop(pck, print);
				break;
			}
			case 6510: {
				IoBuffer pck = packet.duplicateBuffer();
				pck.flip();
				int length = pck.getInt();
				if (print) {
					System.out.println("length=" + length);
				}
				pro = pck.getInt();
				if (print) {
					System.out.println("pro=" + pro);
				}
				int roleId = pck.getInt();
				int hostId = pck.getInt();
				if (print) {
					System.out.println("roleId=" + roleId);
					System.out.println("hostId=" + hostId);
				}
				prop(pck, print);
				break;
			}
		}
	}

	private static void test_6005(IoBuffer packet, boolean print) {
		IoBuffer pck = packet.duplicateBuffer();
		pck.flip();
		int length = pck.getInt();
		if (print) {
			System.out.println("length=" + length);
		}
		int pro = pck.getInt();
		if (print) {
			System.out.println("pro=" + pro);
		}
		int roleId = -1;
		while ((roleId = pck.getInt()) != -1) {
			if (print) {
				System.out.println("    roleid=" + roleId);
			}
			prop(pck, print);
		}
		if (print) {
			System.out.println("position=" + pck.position() + "," + pck.limit());
		}
	}

	private static void test_6500_6501(IoBuffer packet, boolean print) {
		IoBuffer pck = packet.duplicateBuffer();
		pck.flip();
		int length = pck.getInt();
		if (print) {
			System.out.println("length=" + length);
		}
		int pro = pck.getInt();
		int connectid = pck.getInt();
		if (print) {
			System.out.println("connectid=" + connectid);
		}
		String script = pck.getString();
		boolean overlap = pck.getBoolean();
		if (print) {
			System.out.println("run script = " + script);
			System.out.println("overlap = " + overlap);
		}
		byte cmd = -1;
		short main = -1;
		int sub = -1;
		int index = -1;
		short prop = -1;
		boolean tmp = false;
		int roleId = -1;
		String file = null;
		byte skeletonId = -1;
		String skeletonFile = null;
		int count = -1;
		while ((cmd = pck.get()) != -1) {
			if (print) {
				System.out.println("cmd=" + cmd);
			}
			switch (cmd) {
				case ClientUI.UI.DELETE_ALL_BY_PROP_27: {
					main = pck.getShort();
					sub = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
					}
					prop01(pck, print);
					tmp = pck.getBoolean();
					if (print) {
						System.out.println("            tmp=" + tmp);
					}
					break;
				}
				case ClientUI.UI.DELETE_OBJECT_BY_INDEX: {
					main = pck.getShort();
					sub = pck.getInt();
					index = pck.getInt();
					tmp = pck.getBoolean();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("            index=" + index);
						System.out.println("    tmp=" + tmp);
					}
					break;
				}
				case ClientUI.UI.CLEAR_OBJECT_LIST: {
					main = pck.getShort();
					sub = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
					}
					break;
				}
				case ClientUI.UI.CLEAR_OBJECT: {
					main = pck.getShort();
					sub = pck.getInt();
					index = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("            index=" + index);
					}
					break;
				}
				case ClientUI.UI.DELETE_OBJECT_LIST: {
					main = pck.getShort();
					sub = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
					}
					break;
				}
				case ClientUI.UI.DELETE_OBJECT_BY_PROPERTY: {
					main = pck.getShort();
					sub = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
					}
					prop01(pck, print);
					tmp = pck.getBoolean();
					if (print) {
						System.out.println("            tmp=" + tmp);
					}
					break;
				}
				case ClientUI.UI.ADD_OBJECT_PROPERTY: {
					main = pck.getShort();
					sub = pck.getInt();
					index = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("            index=" + index);
					}
					prop(pck, print);
					break;
				}
				case ClientUI.UI.ADD_PROPERTY_GROUP: {
					while ((main = pck.getShort()) != -1) {
						if (print) {
							System.out.println("    main=" + main);
						}
						while ((sub = pck.getInt()) != -1) {
							if (print) {
								System.out.println("        sub=" + sub);
							}
							while ((index = pck.getInt()) != -1) {
								if (print) {
									System.out.println("            index=" + index);
								}
								prop(pck, print);
							}
						}
					}
					break;
				}
				case ClientUI.UI.ADD_OBJECT: {
					main = pck.getShort();
					sub = pck.getInt();
					index = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("            index=" + index);
					}
					prop(pck, print);
					break;
				}
				case ClientUI.UI.UPDATE_OBJECT: {
					main = pck.getShort();
					sub = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
					}
					prop(pck, print);
					break;
				}
				case ClientUI.UI.SKELETION_ANIMATION: {
					while ((roleId = pck.getInt()) != -1) {
						file = pck.getString();
						if (print) {
							System.out.println("    roleId=" + roleId);
							System.out.println("    file=" + file);
						}
						while ((skeletonId = pck.get()) != -1) {
							skeletonFile = pck.getString();
							if (print) {
								System.out.println("        skeletonId=" + skeletonId);
								System.out.println("        skeletonFile=" + skeletonFile);
							}
						}
					}
					break;
				}
				case ClientUI.UI.ITEM_LENGTH: {
					main = pck.getShort();
					if (print) {
						System.out.println("    main=" + main);
					}
					while ((sub = pck.getInt()) != -1) {
						count = pck.getShort();
						if (print) {
							System.out.println("        sub=" + sub);
							System.out.println("            count=" + count);
						}
					}
					break;
				}
				case ClientUI.UI.CLEAR_MAIN_CATE: {
					main = pck.getShort();
					if (print) {
						System.out.println("    main=" + main);
					}
					break;
				}
				case ClientUI.UI.REFERENCE_OBJECT_24: {
					main = pck.getShort();
					sub = pck.getInt();
					index = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("            index=" + index);
					}
					main = pck.getShort();
					sub = pck.getInt();
					index = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("            index=" + index);
					}
					break;
				}
				case ClientUI.UI.MOVE_OBJECT_25: {
					main = pck.getShort();
					sub = pck.getInt();
					index = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("            index=" + index);
					}
					prop(pck, print);
					break;
				}
				case ClientUI.UI.REFERENCE_OBJECT_26: {
					main = pck.getShort();
					sub = pck.getInt();
					short key = pck.getShort();
					int value = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("           key=" + key + ", value=" + value);
					}
					main = pck.getShort();
					sub = pck.getInt();
					index = pck.getInt();
					if (print) {
						System.out.println("    main=" + main);
						System.out.println("        sub=" + sub);
						System.out.println("            index=" + index);
					}
				}
			}
		}
		if (print) {
			System.out.println("position=" + pck.position() + "," + pck.limit());
		}
		if (pck.limit() - pck.position() != 0 && pro != 6501 && pro != 6509) {
			try {
				int a = 1 / 0;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (print) {
			System.out.println("=============================================================" + connectid);
		}
	}

	private static void prop(IoBuffer pck, boolean print) {
		short key = 0;
		int tkey = 0;
		Object value = 0;
		while ((key = pck.getShort()) > -1) {
			tkey = key & 0x0fff;
			if (print) {
				System.out.print("                k=" + tkey);
			}
			switch (key & 0xF000) {
				case PacketBufferPool.DataType.TYPE_BOOLEAN:
				case PacketBufferPool.DataType.TYPE_BYTE: {
					value = pck.get();
					break;
				}
				case PacketBufferPool.DataType.TYPE_INT: {
					value = pck.getInt();
					break;
				}
				case PacketBufferPool.DataType.TYPE_FLOAT: {
					value = pck.getFloat();
					break;
				}
				case PacketBufferPool.DataType.TYPE_LIST: {
					List<Short> list = new ArrayList<Short>();
					short lv = -1;
					while ((lv = pck.getShort()) != -1) {
						list.add(lv);
					}
					value = list;
					break;
				}
				case PacketBufferPool.DataType.TYPE_LONG: {
					value = pck.getLong();
					break;
				}
				case PacketBufferPool.DataType.TYPE_SHORT: {
					value = pck.getShort();
					break;
				}
				case PacketBufferPool.DataType.TYPE_STRING: {
					value = pck.getString();
					break;
				}
			}
			if (print) {
				System.out.println("                v = " + value);
			}
		}

	}

	private static void prop01(IoBuffer pck, boolean print) {
		short key = pck.getShort();
		int tkey = 0;
		Object value = 0;
		tkey = key & 0x0fff;
		if (print) {
			System.out.print("                k=" + tkey);
		}
		switch (key & 0xF000) {
			case PacketBufferPool.DataType.TYPE_BOOLEAN:
			case PacketBufferPool.DataType.TYPE_BYTE: {
				value = pck.get();
				break;
			}
			case PacketBufferPool.DataType.TYPE_INT: {
				value = pck.getInt();
				break;
			}
			case PacketBufferPool.DataType.TYPE_FLOAT: {
				value = pck.getFloat();
				break;
			}
			case PacketBufferPool.DataType.TYPE_LIST: {
				List<Short> list = new ArrayList<Short>();
				short lv = -1;
				while ((lv = pck.getShort()) != -1) {
					list.add(lv);
				}
				value = list;
				break;
			}
			case PacketBufferPool.DataType.TYPE_LONG: {
				value = pck.getLong();
				break;
			}
			case PacketBufferPool.DataType.TYPE_SHORT: {
				value = pck.getShort();
				break;
			}
			case PacketBufferPool.DataType.TYPE_STRING: {
				value = pck.getString();
				break;
			}
		}
		if (print) {
			System.out.println("                v = " + value);
		}

	}
}
