package mmo.tools.script.msc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Stack;
import java.util.Vector;

public class FilterInstruction {

		public static final String    COMMENT_START = ";";
		public static final String    CMD_PUSH      = "Push";
		public static final String    CMD_POP       = "Pop";
		public static final String    CMD_MOV       = "Mov";
		public static final String    CMD_JE        = "JE";
		public static final String    CMD_JNE       = "JNE";
		public static final String    CMD_JG        = "JG";
		public static final String    CMD_JL        = "JL";
		public static final String    CMD_JGE       = "JGE";
		public static final String    CMD_JLE       = "JLE";

		public static final String    TMP_T0        = "_T0";
		public static final String    TMP_T1        = "_T1";

		public static final String    OFFSET        = "\t\t";

		public static final String    FILE_TYPE     = ".E.XASM";

		private static Vector<String> pushCommands  = new Vector<String>();
		private static Vector<String> destCommands  = new Vector<String>();
		private FilterInstruction(){
				
		}

		public static void loadCommands(String file) {
				try {
						pushCommands.clear();
						destCommands.clear();
						String str = new String(getFileByteData(file));

						String[] temp = str.split("\n");
						String tmp = null;
						for (int i = 0; i < temp.length; i++) {
								tmp = temp[i].trim();
								if (tmp.startsWith(COMMENT_START)) {
										continue;
								} else {
										if (tmp.length() > 0) {
												pushCommands.addElement(tmp);
										}
								}
								temp[i] = null;
						}
						temp = null;
						reducePushPopCmd();
						reduceMovCmd();
						File _file = new File(file.substring(0, file.lastIndexOf('.')) + FILE_TYPE);

						if (!_file.exists()) {
								makeDir(_file.getParentFile());
								_file.createNewFile();
						}
						if (!_file.exists()) {
								_file.createNewFile();
						}
						FileWriter fw = new FileWriter(_file);
						for (int i = 0; i < destCommands.size(); i++) {
								fw.write(destCommands.elementAt(i));
								fw.write('\n');
						}
						fw.flush();
						fw.close();
				} catch (FileNotFoundException e) {
						e.printStackTrace();
				} catch (IOException e) {
						e.printStackTrace();
				}
		}

		private static void reducePushPopCmd() {
				String currCmd = null;
				String push = null;
				Stack<String> pushCmd = new Stack<String>();
				Stack<String> modCmd = new Stack<String>();
				StringBuffer sb = new StringBuffer();

				for (int i = 0; i < pushCommands.size(); i++) {
						currCmd = pushCommands.elementAt(i);
						if (currCmd.startsWith(CMD_PUSH)) {
								pushCmd.push(currCmd);
						} else if (currCmd.startsWith(CMD_POP)) {
								if (pushCmd.size() > 0) {
										push = pushCmd.pop();
										if ((push.contains(TMP_T0) && currCmd.contains(TMP_T0))
										                || (push.contains(TMP_T1) && currCmd.contains(TMP_T1))) {
												continue;
										}
										sb.append(CMD_MOV);
										sb.append(OFFSET);
										sb.append(currCmd.substring(3).trim());
										sb.append(',');
										sb.append(' ');
										sb.append(push.substring(4));
										modCmd.push(sb.toString());
										sb.setLength(0);
								} else {
										for (int j = 0; j < modCmd.size(); j++) {
												destCommands.addElement(modCmd.elementAt(j));
										}
										modCmd.clear();
										destCommands.addElement(currCmd);
								}
						} else {
								for (int j = 0; j < pushCmd.size(); j++) {
										destCommands.addElement(pushCmd.elementAt(j));
								}
								pushCmd.clear();
								for (int j = 0; j < modCmd.size(); j++) {
										destCommands.addElement(modCmd.elementAt(j));
								}
								modCmd.clear();
								destCommands.addElement(currCmd);
						}
				}
				pushCommands.clear();
				pushCommands.addAll(destCommands);
		}

		private static void reduceMovCmd() {
				String currCmd = null;
				String moveCmd = null;
				for (int i = 0; i < destCommands.size();) {
						currCmd = destCommands.elementAt(i);
						if (currCmd.startsWith(CMD_MOV)) {
								if (currCmd.contains(TMP_T0) && currCmd.contains(TMP_T1)) {
										i++;
										continue;
								}
								moveCmd = currCmd.substring(3).trim();

								if (moveCmd.startsWith(TMP_T0)) {
										int next = i + 1;
										while (true) {
												if (next >= destCommands.size()) {
														break;
												}
												if (destCommands.elementAt(next).indexOf(TMP_T0) >= 0) {
														String nextCmd = destCommands.elementAt(next);
														int index = nextCmd.indexOf(",");
														int t0Index = nextCmd.indexOf(TMP_T0);
														moveCmd = moveCmd.substring(moveCmd.indexOf(",") + 1).trim();
														if (index < t0Index) {
																destCommands.setElementAt(nextCmd.replace(TMP_T0, moveCmd), next);
																destCommands.remove(i);

														} else if (nextCmd.contains(CMD_JE) || nextCmd.contains(CMD_JG) || nextCmd.contains(CMD_JL)
														                || nextCmd.contains(CMD_JGE) || nextCmd.contains(CMD_JLE)
														                || nextCmd.contains(CMD_JNE)) {
																destCommands.setElementAt(nextCmd.replace(TMP_T0, moveCmd), next);
																destCommands.remove(i);
														} else {
																i++;
														}
														break;
												}
												next++;
										}
								} else if (moveCmd.startsWith(TMP_T1)) {
										int next = i + 1;
										while (true) {
												if (next >= destCommands.size()) {
														break;
												}
												if (destCommands.elementAt(next).indexOf(TMP_T1) >= 0) {
														String nextCmd = destCommands.elementAt(next);
														int index = nextCmd.indexOf(",");
														int t0Index = nextCmd.indexOf(TMP_T1);
														moveCmd = moveCmd.substring(moveCmd.indexOf(",") + 1).trim();
														if (index < t0Index) {
																destCommands.setElementAt(nextCmd.replace(TMP_T1, moveCmd), next);
																destCommands.remove(i);

														} else if (nextCmd.contains(CMD_JE) || nextCmd.contains(CMD_JG) || nextCmd.contains(CMD_JL)
														                || nextCmd.contains(CMD_JGE) || nextCmd.contains(CMD_JLE)
														                || nextCmd.contains(CMD_JNE)) {
																destCommands.setElementAt(nextCmd.replace(TMP_T1, moveCmd), next);
																destCommands.remove(i);
														} else {
																i++;
														}
														break;
												}
												next++;
										}
								} else {
										i++;
								}

						} else {
								i++;
						}
				}
		}

		public void printSourceCommand() {
				int size = pushCommands.size();
				for (int i = 0; i < size; i++) {
						System.out.println(pushCommands.elementAt(i));
				}
		}

		public void printDestCommand() {
				int size = destCommands.size();
				for (int i = 0; i < size; i++) {
						System.out.println(destCommands.elementAt(i));
				}
		}

		/**
		 * ????????????????????????????? ????:?????·???? ????:????????????
		 */
		public static byte[] getFileByteData(String fileName) {
				byte[] data = null;
				try {
						RandomAccessFile in = new RandomAccessFile(fileName, "r");
						FileChannel channel = in.getChannel();
						int length = (int) channel.size();
						MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
						data = new byte[length];
						buffer.get(data, 0, length);
						channel.close();
						in.close();
						return data;
				} catch (FileNotFoundException e) {
						e.printStackTrace();
				} catch (IOException ioe) {
						ioe.printStackTrace();
				}
				return null;
		}

		public static void writeDataToFile(String file, byte[] data) {
				try {
						File _file = new File(file);
						if (!_file.exists()) {
								makeDir(_file.getParentFile());
								_file.createNewFile();
						}
						if (!_file.exists()) {
								_file.createNewFile();
						}
						RandomAccessFile rafo = new RandomAccessFile(_file, "rw");
						FileChannel fco = rafo.getChannel();
						MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, data.length);
						mbbo.put(data);
						fco.close();
						rafo.close();
				} catch (FileNotFoundException e) {
						e.printStackTrace();
				} catch (IOException ioe) {
						ioe.printStackTrace();
				}
		}

		/**
		 * ?ж????????????? ????????? ???????????(true:????,false:??????)
		 */
		public static boolean fileIsExist(String fileName) {
				File _file = new File(fileName);
				return _file.exists();
		}

		public static void makeDir(File file) {
				if (!file.exists()) {
						File parent = file.getParentFile();
						if (!parent.isDirectory()) {
								makeDir(parent);
						}
						file.mkdir();
				}
		}

		public static void main(String[] args) {
				loadCommands(System.getProperty("user.dir") + "/bin/ship1.XASM");
				loadCommands(System.getProperty("user.dir") + "/bin/ship2.XASM");
				loadCommands(System.getProperty("user.dir") + "/bin/interlude.XASM");

		}
}
