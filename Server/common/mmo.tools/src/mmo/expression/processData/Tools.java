package mmo.expression.processData;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tools {
	private static Random random = new Random();

	public static int getInt(String str) {
		int i = 0;
		try {
			i = Integer.valueOf(str);
		} catch (NumberFormatException e) {
		}
		return i;
	}

	// public static Image createImage(String path) {
	// Image img=null;
	// System.out.println("url  "+Data.ROOT_PATH+path + ".png");
	// try {
	// img= ImageIO.read(new FileInputStream(Data.ROOT_PATH+path + ".png"));
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// return img;
	// }
	public static int hexString2Int(String hs) {
		if (hs.length() > 6) {
			return Integer.valueOf(hs.substring(0, hs.length() - 6), 16)
					.intValue() << 24
					| Integer.valueOf(hs.substring(hs.length() - 6), 16)
							.intValue();
		}

		return Integer.valueOf(hs, 16).intValue();
	}

	public static int getRandom(int min, int max) {
		return min + Math.abs(random.nextInt()) % (max - min + 1);
	}

	public static int getDoubleByte(int a, int b) {
		return (short) (getUnsignedByte(a) << 8) | getUnsignedByte(b);
	}

	public static int getTripleByte(int a, int b, int c) {
		return (int) (getUnsignedByte((byte) a) << 16)
				| (getUnsignedByte((byte) b) << 8) | getUnsignedByte((byte) c);
	}

	/**
	 * 合成UI状态标志值
	 * 
	 * @param shellId
	 * @param uiId
	 * @param state
	 * @return
	 */
	public static int getUiSignInfo(int shellId, int uiId, int state) {
		return ((shellId & 0x1ff) << 15) | ((uiId & 0x7f) << 8) | state;
	}

	public static short getUnsignedByte(int b) {
		return (short) (b & 0xFF);
	}

	public static int getQuadByte(int a, int b, int c, int d) {
		return (int) (getUnsignedByte(a) << 24) | (getUnsignedByte(b) << 16)
				| (getUnsignedByte(c) << 8) | getUnsignedByte(d);
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			return true;
		} else {
			return false;
		}
	}

	public static boolean deleteDirectory(String dir) {
		// 锟斤拷锟絛ir锟斤拷锟斤拷锟侥硷拷锟街革拷锟斤拷锟轿诧拷锟斤拷远锟斤拷锟斤拷锟侥硷拷锟街革拷锟斤拷
		if (!dir.endsWith(File.separator)) {
			dir = dir + File.separator;
		}
		File dirFile = new File(dir);
		// 锟斤拷锟絛ir锟斤拷应锟斤拷锟侥硷拷锟斤拷锟斤拷锟节ｏ拷锟斤拷锟竭诧拷锟斤拷一锟斤拷目录锟斤拷锟斤拷锟剿筹拷
		if (!dirFile.exists() || !dirFile.isDirectory()) {

			return false;
		}
		boolean flag = true;
		// 删锟斤拷锟侥硷拷锟斤拷锟铰碉拷锟斤拷锟斤拷锟侥硷拷(锟斤拷锟斤拷锟斤拷目录)
		File[] files = dirFile.listFiles();
		for (int i = 0; i < files.length; i++) {

			if (files[i].isFile()) {
				flag = deleteFile(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}

			else {
				flag = deleteDirectory(files[i].getAbsolutePath());
				if (!flag) {
					break;
				}
			}
		}

		if (!flag) {
			// System.out.println("删锟斤拷目录失锟斤拷");
			return false;
		}

		// 删锟斤拷前目录
		if (dirFile.delete()) {
			// System.out.println("删锟斤拷目录"+dir+"锟缴癸拷锟斤拷");
			return true;
		} else {
			// System.out.println("删锟斤拷目录"+dir+"失锟杰ｏ拷");
			return false;
		}
	}

	/**
	 * 锟斤拷锟斤拷锟侥硷拷
	 * 
	 * @param f1
	 *            锟斤拷锟斤拷锟斤拷锟斤拷锟侥硷拷
	 * @param f2
	 *            锟斤拷锟斤拷锟斤拷锟斤拷锟侥硷拷
	 */
	public static void copyFile(File f1, File f2) throws Exception {
		int length = 2097152;
		FileInputStream in = new FileInputStream(f1);
		FileOutputStream out = new FileOutputStream(f2);
		FileChannel inC = in.getChannel();
		FileChannel outC = out.getChannel();
		int i = 0;
		while (true) {
			if (inC.position() == inC.size()) {
				inC.close();
				outC.close();
				return;
			}
			if ((inC.size() - inC.position()) < 20971520) {
				length = (int) (inC.size() - inC.position());
			} else {
				length = 20971520;
			}
			inC.transferTo(inC.position(), length, outC);
			inC.position(inC.position() + length);
			i++;
		}
	}

	public static boolean[] read(DataInputStream dis, boolean[] b)
			throws IOException {
		b = new boolean[dis.readShort()];
		for (int i = 0; i < b.length; i++) {
			b[i] = dis.readBoolean();
		}
		return b;
	}

	public static byte[] read(DataInputStream dis, byte[] b) throws IOException {
		b = new byte[dis.readShort()];
		dis.read(b);
		return b;
	}

	public static byte[][] read(DataInputStream dis, byte[][] b)
			throws IOException {
		b = new byte[dis.readShort()][];
		for (int i = 0; i < b.length; i++) {
			b[i] = read(dis, b[i]);
		}
		return b;
	}

	public static byte[][][] read(DataInputStream dis, byte[][][] b)
			throws IOException {
		b = new byte[dis.readShort()][][];
		for (int i = 0; i < b.length; i++) {
			b[i] = read(dis, b[i]);
		}
		return b;
	}

	public static String[] read(DataInputStream dis, String[] s)
			throws IOException {
		s = new String[dis.readShort()];
		for (int i = 0; i < s.length; i++) {
			s[i] = dis.readUTF();
		}
		return s;
	}

	public static String[][] read(DataInputStream dis, String[][] s)
			throws IOException {
		s = new String[dis.readShort()][];
		for (int i = 0; i < s.length; i++) {
			s[i] = read(dis, s[i]);
		}
		return s;
	}

	public static short[] read(DataInputStream dis, short[] s)
			throws IOException {
		s = new short[dis.readShort()];
		for (int i = 0; i < s.length; i++) {
			s[i] = dis.readShort();
		}
		return s;
	}

	public static short[][] read(DataInputStream dis, short[][] s)
			throws IOException {
		s = new short[dis.readShort()][];
		for (int i = 0; i < s.length; i++) {
			s[i] = read(dis, s[i]);
		}
		return s;
	}

	public static short[][][] read(DataInputStream dis, short[][][] s)
			throws IOException {
		s = new short[dis.readShort()][][];
		for (int i = 0; i < s.length; i++) {
			s[i] = read(dis, s[i]);
		}
		return s;
	}

	public static int[] read(DataInputStream dis, int[] intArray)
			throws IOException {
		intArray = new int[dis.readShort()];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = dis.readInt();
		}

		return intArray;
	}

	public static int[][] read(DataInputStream dis, int[][] intArray)
			throws IOException {
		intArray = new int[dis.readShort()][];
		for (int i = 0; i < intArray.length; i++) {
			intArray[i] = read(dis, intArray[i]);
		}
		return intArray;
	}

	public static void write(DataOutputStream dos, boolean[] b)
			throws IOException {
		dos.writeShort(b.length);
		for (short i = 0; i < b.length; i++) {
			dos.writeBoolean(b[i]);
		}

	}

	public static void write(DataOutputStream dos, int[] intArray)
			throws IOException {
		dos.writeShort(intArray.length);
		for (int i = 0; i < intArray.length; i++) {
			dos.writeInt(intArray[i]);
		}

	}

	public static void write(DataOutputStream dos, int[][] intArray)
			throws IOException {
		dos.writeShort(intArray.length);
		for (int i = 0; i < intArray.length; i++) {
			write(dos, intArray[i]);
		}

	}

	public static void write(DataOutputStream dos, byte[] b) throws IOException {
		dos.writeShort(b.length);
		dos.write(b);

	}

	public static void write(DataOutputStream dos, byte[][] b)
			throws IOException {
		dos.writeShort(b.length);
		for (int i = 0; i < b.length; i++) {
			write(dos, b[i]);
		}

	}

	public static void write(DataOutputStream dos, byte[][][] b)
			throws IOException {
		dos.writeShort(b.length);
		for (int i = 0; i < b.length; i++) {
			write(dos, b[i]);
		}

	}

	public static void write(DataOutputStream dos, String[] s)
			throws IOException {
		dos.writeShort(s.length);
		for (int i = 0; i < s.length; i++) {
			dos.writeUTF(s[i]);
		}
	}

	public static void write(DataOutputStream dos, String[][] s)
			throws IOException {
		dos.writeShort(s.length);
		for (int i = 0; i < s.length; i++) {
			write(dos, s[i]);
		}
	}

	public static void write(DataOutputStream dos, short[] s)
			throws IOException {

		dos.writeShort(s.length);
		for (int i = 0; i < s.length; i++) {
			dos.writeShort(s[i]);
		}

	}

	public static void write(DataOutputStream dos, short[][] s)
			throws IOException {
		dos.writeShort(s.length);
		for (int i = 0; i < s.length; i++) {
			write(dos, s[i]);
		}
	}

	public static void write(DataOutputStream dos, short[][][] s)
			throws IOException {
		dos.writeShort(s.length);
		for (int i = 0; i < s.length; i++) {
			write(dos, s[i]);
		}
	}

	public static Object arrayGrow(Object array, int growLength) {
		return newArray(array, Array.getLength(array) + growLength);
	}

	// public static Object newArray(Object array){
	// Class<?> c=array.getClass();
	// if(!c.isArray()){
	// return null;
	// }
	// return newArray(array, Array.getLength(array));
	// }
	public static Object newArray(Object array, int newArrayLen) {
		Class<?> c = array.getClass();
		if (!c.isArray()) {
			return null;
		}
		Class<?> componentType = c.getComponentType();

		int length = Array.getLength(array);
		Object newArray = Array.newInstance(componentType, newArrayLen);
		System.arraycopy(array, 0, newArray, 0, length < newArrayLen ? length
				: newArrayLen);
		return newArray;
	}

	// public static ToolItem setToolBarSelection(ToolBar toolBar,String
	// toolItemName){
	// int id=getToolItemIndex(toolBar,toolItemName);
	// if(id==-1){
	// return null;
	// }
	// ToolItem[] toolItem=toolBar.getItems();
	// switch(toolItem[id].getStyle()){
	// case SWT.RADIO:
	// for(int j=0;j<toolItem.length;j++){
	// if(toolItem[j].getStyle()==SWT.RADIO){
	// toolItem[j].setSelection(false);
	// }
	// }
	// default:
	// toolItem[id].setSelection(true);
	// return toolItem[id] ;
	// }
	// }
	// public static int getToolItemIndex(ToolBar toolBar,String toolItemName){
	// ToolItem[] toolItem=toolBar.getItems();
	// for(int i=0;i<toolItem.length;i++){
	// if(toolItem[i].getText().equals(toolItemName)){
	// return i;
	// }
	// }
	// return -1;
	// }
	public static byte getLowNum(int num) {
		return (byte) (num & 0xff);
	}

	public static byte getHighNum(int num) {
		return (byte) ((num >> 8) & 0xff);
	}

	public static List<Class<?>> getAllAssignedClass(Class<?> cls) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> c : getClasses(cls)) {
			if (cls.isAssignableFrom(c) && !cls.equals(c)) {
				classes.add(c);
			}
		}

		return classes;
	}

	public static List<Class<?>> getAllAssignedClass(Class<?> cls,
			String packageName) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		for (Class<?> c : getClasses(cls, packageName)) {
			if (cls.isAssignableFrom(c) && !cls.equals(c)) {
				classes.add(c);
			}
		}

		return classes;
	}

	private static List<Class<?>> getClasses(Class<?> cls) {
		return getClasses(cls, cls.getPackage().getName());
	}

	private static List<Class<?>> getClasses(Class<?> cls, String packageName) {
		String pk = packageName;
		String path = pk.replace('.', '/');
		ClassLoader classloader = Thread.currentThread()
				.getContextClassLoader();

		String decodePath = classloader.getResource(path).getFile();
		try {
			decodePath = java.net.URLDecoder.decode(decodePath, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getClasses(new File(decodePath), pk);
	}

	private static List<Class<?>> getClasses(File dir, String pk) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		if (!dir.exists()) {
			return classes;
		}
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				classes.addAll(getClasses(f, pk + "." + f.getName()));
			}
			String name = f.getName();
			if (name.endsWith(".class")) {
				try {
					classes.add(Class.forName(pk + "."
							+ name.substring(0, name.length() - 6)));
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return classes;
	}

}
