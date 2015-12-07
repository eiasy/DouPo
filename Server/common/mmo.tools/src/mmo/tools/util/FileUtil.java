package mmo.tools.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;

import mmo.tools.log.LoggerError;

public class FileUtil {
	public final static String ROOT_DIR       = System.getProperty("user.dir");
	public final static String FILE_SEPARATOR = System.getProperty("file.separator");

	/**
	 * 判断一个文件是否存在 参数：文件名 返回：是否存在(true:存在,false:不存在)
	 */
	public static boolean fileIsExist(String fileName) {
		File _file = new File(fileName);
		return _file.exists();
	}

	/**
	 * 将一个文件里的数据读入到一个字节数组中 参数:文件的路径名 返回:文件的字节数组
	 */
	public static String getFileBText(String fileName) {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(System.getProperty("line.separator"));
			}
			if (br != null) {
				br.close();
			}
			return sb.toString();
		} catch (Exception e) {
			LoggerError.error("读取文件报错@" + fileName, e);
		}

		return null;
	}

	public static void filterDirFileByType(File file, String type, List<String> resultList) throws Exception {
		if (file.isDirectory()) {
			String[] files = file.list();
			Arrays.sort(files);
			for (int i = 0; i < files.length; i++) {
				filterDirFileByType(new File(file, files[i]), type, resultList);
			}
		} else if (file.getPath().endsWith(type)) {
			resultList.add(file.getParent() + "/" + file.getName());

		}
	}

	/**
	 * 重命名
	 * 
	 * @param source
	 *            源文件
	 * @param target
	 *            目标文件
	 */
	public static void rename(String source, String target) {
		byte[] data = getFileByteData(source);
		writeDataToFile(target, data);
		// File fileSource = new File(source);
		// File fileTarget = new File(target);
		// if (fileTarget.exists()) {
		// fileTarget.delete();
		// } else {
		// makeDir(fileTarget.getParentFile());
		// }
		// fileSource.renameTo(fileTarget);
	}

	/**
	 * 将一个文件里的数据读入到一个字节数组中 参数:文件的路径名 返回:文件的字节数组
	 */
	public static byte[] getFileByteData(File file) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
			return data;
		} catch (Exception e) {
			LoggerError.error("读取文件报错@" + file, e);
		}
		return null;
	}

	/**
	 * 将一个文件里的数据读入到一个字节数组中 参数:文件的路径名 返回:文件的字节数组
	 */
	public static byte[] getFileByteData(String fileName) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(fileName);
			byte[] data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
			return data;
		} catch (Exception e) {
			LoggerError.error(fileName, e);
		}
		return null;
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

	public static void writeDataToFile(File _file, byte[] data) {
		try {
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

	public static boolean writeDataToFile(String file, byte[] data) {
		try {
			File _file = new File(file);
			if (_file.exists()) {
				_file.delete();
			} else {
				makeDir(_file.getParentFile());
			}
			_file.createNewFile();
			RandomAccessFile rafo = new RandomAccessFile(_file, "rw");
			FileChannel fco = rafo.getChannel();
			MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, data.length);
			mbbo.put(data);
			mbbo.force();
			fco.close();
			rafo.close();
			return true;
		} catch (Exception e) {
			LoggerError.error("写入文件报错#" + file, e);
		}
		return false;
	}

	public static void writeDataToFile(String file, String data, String encoding) {
		try {
			File _file = new File(file);
			if (_file.exists()) {
				_file.delete();
			}
			if (!_file.exists()) {
				makeDir(_file.getParentFile());
				_file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(_file);
			OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
			BufferedWriter br = new BufferedWriter(osw);
			br.write(data);
			fos.flush();
			osw.flush();
			br.flush();
			br.close();
			osw.close();
			fos.close();
		} catch (Exception e) {
			LoggerError.error("写入文件报错#" + file, e);
		}
	}

	public static void writeDataToFile(String file, String data) {
		try {
			File _file = new File(file);
			if (_file.exists()) {
				_file.delete();
			}
			if (!_file.exists()) {
				makeDir(_file.getParentFile());
				_file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(_file);
			OutputStreamWriter osw = new OutputStreamWriter(fos);
			BufferedWriter br = new BufferedWriter(osw);
			br.write(data);
			br.flush();
			osw.flush();
			fos.flush();
			br.close();
			osw.close();
			fos.close();
		} catch (Exception e) {
			LoggerError.error("写入文件报错#" + file, e);
		}
	}
}
