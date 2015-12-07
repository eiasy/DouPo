package mmo.tools.util.string;

public class StringSplit {
	public final static int SIGN_TYPE_QUOTE = 0x80000000;
	private int             pos;
	private int             len;
	private char            c;
	private StringBuilder   sb;

	private StringSplit() {

	}

	public static String transformString(String str) {
		StringSplit cs = new StringSplit();
		if (str == null) {
			return "";
		}
		try {
			cs.splitString(str);
		} catch (Exception e) {
			return str;
		}
		return cs.toTranslatedString();
	}

	private void split(String str) {
		if (pos < len) {
			c = str.charAt(pos);
			if (isOpenAnglebracket(c)) {
				int count = 0;
				for (int i = pos + 1, j = len < pos + MAX_CMD_INFO_LEN ? len : pos + MAX_CMD_INFO_LEN; i < j; i++) {
					if (isOpenAnglebracket(str.charAt(i))) {
						count++;
					}
					if (isCloseAnglebracket(str.charAt(i))) {
						if (count == 0) {
							checkCmd(str.substring(pos + 1, i));
							pos = i + 1;
							split(str);
							return;
						} else {
							count--;
						}
					}
				}
			}
			writeChar(c);
			pos++;
			split(str);
		} else {
			return;
		}
	}

	private void splitString(String str) {
		pos = 0;
		len = str.length();
		sb = new StringBuilder();
		split(str);
	}

	private final static boolean isOpenAnglebracket(char c) {
		return c == '[';
	}

	private final static boolean isCloseAnglebracket(char c) {
		return c == ']';
	}

	private final static int      SIGN_CMD                        = 0xc000;
	private final static int      SIGN_END_CMD                    = 0xc100;
	private final static byte     MAX_CMD_INFO_LEN                = 60;
	private final static byte     CMD_COLOR                       = 1;
	private final static byte     CMD_RESET_COLOR                 = 2;
	private final static byte     CMD_ICON                        = 3;
	private final static byte     CMD_INFO_IMAGE                  = 4;
	private final static byte     CMD_INFO_FONT                   = 5;
	private final static byte     CMD_INFO_NUM                    = 6;
	private final static byte     CMD_FONTSTYLE                   = 7;
	private final static byte     CMD_RESET_FONTSTYLE             = 8;
	private final static byte     CMD_INFO_INTRO                  = 9;
	private final static byte     CMD_NEW_LINE                    = 10;

	public final static byte      CMD_PRO                         = 11;
	public final static byte      CMD_SCRIPT                      = 12;
	public final static byte      CMD_FACE                        = 13;
	public final static byte      CMD_OFFSET                      = 14;

	public final static byte      CMD_UNDERLINE                   = 15;
	public final static byte      CMD_UNDERLINE_END               = 16;

	public final static byte      CMD_SENTENCE                    = 21;
	public final static byte      CMD_ITEM                        = 22;
	public final static byte      CMD_SPRITE_FRAME                = 23;
	public final static byte      CMD_MATH                        = 24;
	public final static byte      CMD_DISABLE_FONT_SETTING        = 25;
	public final static byte      CMD_CANCEL_DISABLE_FONT_SETTING = 26;
	public final static byte      CMD_SPRITE_ACTION               = 27;
	public final static byte      CMD_IF                          = 28;
	public final static byte      CMD_ELSE_IF                     = 29;
	public final static byte      CMD_ELSE                        = 30;
	public final static byte      CMD_END_IF                      = 31;
	public final static byte      CMD_STRING_LIST                 = 32;
	public final static byte      CMD_NUM                         = 33;
	public final static byte      CMD_CHAR                        = 34;

	public final static byte      CMD_GRAY                        = 36;
	public final static byte      CMD_GRAY_END                    = 37;
	public final static byte      CMD_ENLARGE                     = 38;
	public final static byte      CMD_ENLARGE_END                 = 39;
	public final static byte      CMD_ROW                         = 40;
	public final static byte      CMD_ROW_END                     = 41;
	public final static byte      CMD_LINK                        = 50;

	public final static byte      CMD_BOLD                        = 51;
	public final static byte      CMD_BOLD_END                    = 52;
	public final static byte      CMD_ARTTYPE                     = 53;
	public final static byte      CMD_ARTTYPE_END                 = 54;
	
	public final static byte CMD_SPEACH = 55;
	public static String          newLine                         = ((char) (SIGN_CMD | CMD_NEW_LINE)) + "" + ((char) (SIGN_END_CMD));
	private final static byte[]   CMD_ARRAY                       = { CMD_COLOR, CMD_RESET_COLOR, CMD_ICON, CMD_INFO_IMAGE, CMD_INFO_FONT,
	        CMD_INFO_NUM, CMD_FONTSTYLE, CMD_RESET_FONTSTYLE,
	        CMD_INFO_INTRO,
	        CMD_SENTENCE,
	        // CMD_CHANNEL,CMD_CHATTER,
	        CMD_ITEM, CMD_NEW_LINE, CMD_SPRITE_FRAME, CMD_PRO, CMD_SCRIPT, CMD_FACE, CMD_OFFSET, CMD_UNDERLINE, CMD_UNDERLINE_END, CMD_MATH,
	        CMD_DISABLE_FONT_SETTING, CMD_CANCEL_DISABLE_FONT_SETTING, CMD_SPRITE_ACTION, CMD_IF, CMD_ELSE_IF, CMD_ELSE, CMD_END_IF, CMD_STRING_LIST,
	        CMD_NUM, CMD_CHAR, CMD_GRAY, CMD_GRAY_END, CMD_ENLARGE, CMD_ENLARGE_END, CMD_ROW, CMD_ROW_END, CMD_LINK, CMD_BOLD, CMD_BOLD_END,
	        CMD_ARTTYPE, CMD_ARTTYPE_END   ,CMD_SPEACH                      };
	private final static String[] CMD_STR_ARRAY                   = { "color:", "/color", "icon:", "info_image:", "info_font:", "info_num:",
	        "fontstyle:", "/fontstyle", "info_intro:",
	        "sentence",
	        // "channel:", "chatter:",
	        "i:", "/r", "frame:", "pro:", "script:", "f", "offset:", "underline", "/underline", "math:", "disableFontSetting", "/disableFontSetting",
	        "action:", "if:", "elif:", "else", "endif", "stringList:", "num:", "char:", "gray", "/gray", "enlarge", "/enlarge", "row", "/row",
	        "link:", "bold", "/bold", "art:", "/art"  ,"speach:"           };

	public void writeCmdArg(int cmd, String cmdInfo) {
		switch (cmd) {
			default:
				break;
			case CMD_ARTTYPE: {
				int index = cmdInfo.indexOf(',');
				writeShort(getInt(cmdInfo.substring(0, index)));
				writeInt(hexString2Int(cmdInfo.substring(index + 1)));

			}
				break;
			case CMD_LINK: {
				int index = cmdInfo.indexOf(',');
				writeString(cmdInfo.substring(0, index));
				writeString(cmdInfo.substring(index + 1));
			}
				break;
			case CMD_IF:
			case CMD_ELSE_IF:
			case CMD_MATH:
				// int[] mathArray = Expression.getPostfixString_intArray(cmdInfo);
				// writeShort(mathArray.length);
				// for (int i = 0; i < mathArray.length; i++) {
				// writeInt(mathArray[i]);
				// }
				break;
			case CMD_SPRITE_FRAME:
			case CMD_SPRITE_ACTION:
				int index = cmdInfo.indexOf('/');
				writeString(cmdInfo.substring(0, index));
				int index2 = cmdInfo.indexOf('/', index + 1);
				int index1 = cmdInfo.indexOf(':');
				if (index1 == -1) {
					if (index2 == -1) {
						writeShort(getInt(cmdInfo.substring(index + 1)));
					} else {
						writeShort(-1);
						writeShort(getInt(cmdInfo.substring(index + 1, index2)));
						int index3 = cmdInfo.indexOf('/', index2 + 1);
						if (index3 == -1) {
							writeShort(getInt(cmdInfo.substring(index2 + 1)));
							writeShort(-1);
						} else {
							writeShort(getInt(cmdInfo.substring(index2 + 1, index3)));
							writeShort(getInt(cmdInfo.substring(index3 + 1)));
						}

					}

				} else {
					if (index2 == -1) {
						writeShort(getInt(cmdInfo.substring(index + 1, index1)));
					} else {
						writeShort(-1);
						writeShort(getInt(cmdInfo.substring(index + 1, index2)));
						int index3 = cmdInfo.indexOf('/', index2 + 1);
						if (index3 == -1) {
							writeShort(getInt(cmdInfo.substring(index2 + 1, index1)));
							writeShort(-1);
						} else {
							writeShort(getInt(cmdInfo.substring(index2 + 1, index3)));
							writeShort(getInt(cmdInfo.substring(index3 + 1, index1)));
						}
					}
					String[] bones = cmdInfo.substring(index1 + 1).split(",");
					for (int i = 0; i < bones.length; i += 2) {
						writeShort(getInt(bones[i]));
						writeString(bones[i + 1]);
					}
				}
				break;
			case CMD_NUM:
				// index = cmdInfo.indexOf('/');
				// writeShort(getInt(cmdInfo.substring(0, index)));
				// mathArray = Expression.getPostfixString_intArray(
				// cmdInfo.substring(index + 1));
				// writeShort(mathArray.length);
				// for (int i = 0; i < mathArray.length; i++) {
				// writeInt(mathArray[i]);
				// }
				break;
			case CMD_CHAR:
				index = cmdInfo.indexOf('/');
				writeShort(getInt(cmdInfo.substring(0, index)));
				writeShort(cmdInfo.substring(index + 1).charAt(0));
				break;
			case CMD_COLOR:
				writeInt(hexString2Int(cmdInfo));
				break;

			case CMD_STRING_LIST:
				writeInt(cmdInfo.hashCode());
				break;
			case CMD_ICON:
				writeString(cmdInfo);
				break;

			case CMD_INFO_FONT:
			case CMD_INFO_INTRO:
			case CMD_INFO_IMAGE:
			case CMD_INFO_NUM:
				writeTypeInfo(cmdInfo);
				break;
			case CMD_ITEM:
			case CMD_PRO:
			case CMD_FACE:
			case CMD_SCRIPT:
			case CMD_FONTSTYLE:
			case CMD_OFFSET:
				writeShort(getInt(cmdInfo));
				break;
						case CMD_SPEACH:{
			index = cmdInfo.indexOf(',');
			writeString(cmdInfo.substring(0, index));
			writeString(cmdInfo.substring(index + 1));
		}
			break;
		}
	}

	/**
	 * 写入 样式和分类信息
	 * 
	 * @param str
	 */
	private void writeTypeInfo(String str) {
		int id = str.indexOf(',');
		if (id != -1) {
			writeShort(getInt(str.substring(0, id)));
		} else {
			writeShort(0);
		}
		id = id + 1;
		int id1;
		String temp;
		for (byte i = 0; i < 4; i++) {
			id1 = str.indexOf('/', id);
			if (id1 != -1 || (i == 3 && id < str.length())) {
				char c = str.charAt(id);
				if (id1 == -1) {
					id1 = str.length();
				}
				temp = str.substring(id, id1);
				switch (c) {
					case 'U':
					case 'S':
						int uId = 0;
						if (c == 'U') {
							if (temp.length() == 1) {
								uId = 0x1ff;
							} else {
								uId = Integer.parseInt(temp.substring(1)) | 0x100;
							}
						} else if (c == 'S') {
							int id2 = temp.indexOf("U");
							if (id2 == -1) {
								uId = (Integer.parseInt(temp.substring(1)) | 0x1000) << 9;
							} else {
								uId = ((Integer.parseInt(temp.substring(1, id2)) | 0x1000) << 9)
								        | (Integer.parseInt(temp.substring(id2 + 1)) | 0x100);
							}
						}
						writeInt(SIGN_TYPE_QUOTE | uId);
						break;
					default:

						// int[]
						// mathArray=Expression.getPostfixString_intArray(temp);
						// if(mathArray.length==1){
						// writeInt(mathArray[0]);
						// break;
						// }
						// writeInt(getInt(temp));
						break;
				}
				id = id1 + 1;
			} else {
				for (int j = i; j < 4; j++) {
					writeInt(0);
				}
				break;
			}
		}
	}

	private void writeShort(int value) {
		sb.append((char) value);
	}

	private void writeString(String string) {
		/*
		 * if (string.charAt(0) == '@') { writeShort(-1); writeShort(ScriptVM.arg(string.substring(1))); } else
		 */{

			writeShort(string.length());
			sb.append(string);
		}
	}

	private void writeInt(int value) {
		sb.append((char) (value >> 16));
		sb.append((char) value);
	}

	private void writeChar(char c) {
		sb.append(c);
	}

	private void writeCmdHead(byte cmdHead) {
		sb.append(getCmdHeadChar(cmdHead));
	}

	public static char getCmdHeadChar(byte cmdHead) {
		return (char) (SIGN_CMD | (cmdHead & 0xff));
	}

	private void checkCmd(String cmdInfo) {
		int cmdHeadIndex = -1;
		int maxLen = 0;
		for (int i = 0, j; i < CMD_STR_ARRAY.length; i++) {
			j = cmdInfo.indexOf(CMD_STR_ARRAY[i]);
			if (j == 0 && CMD_STR_ARRAY[i].length() > maxLen) {

				cmdHeadIndex = i;
				maxLen = CMD_STR_ARRAY[i].length();
			}
		}
		switch (cmdHeadIndex) {
			case -1:
				writeChar('[');
				sb.append(cmdInfo);
				writeChar(']');
				break;
			default:
				writeCmdHead(CMD_ARRAY[cmdHeadIndex]);
				cmdInfo = cmdInfo.substring(CMD_STR_ARRAY[cmdHeadIndex].length());
				writeCmdArg(CMD_ARRAY[cmdHeadIndex], cmdInfo);
				writeShort(SIGN_END_CMD);
				break;
		}
	}

	private String toTranslatedString() {
		return sb.toString();
	}

	private static int getInt(String str) {
		int i = 0;
		try {
			i = Integer.valueOf(str).intValue();
		} catch (NumberFormatException e) {
		}
		return i;
	}

	private static int hexString2Int(String hs) {
		if (hs.length() > 6) {
			return Integer.valueOf(hs.substring(0, hs.length() - 6), 16).intValue() << 24
			        | Integer.valueOf(hs.substring(hs.length() - 6), 16).intValue();
		}

		return Integer.valueOf(hs, 16).intValue();
	}
}
