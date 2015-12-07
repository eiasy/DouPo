package mmo.tools.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import mmo.tools.log.LoggerError;

public class MyClassLoader extends ClassLoader {

	private boolean alwaysDefineClass = true;
	private String  classPath         = System.getProperty("user.dir") + File.separator + "classes";

	@Override
	protected Class<?> findClass(String fullClassName) throws ClassNotFoundException {
		Class<?> clazz = null;
		byte[] raw = readClassBytes(fullClassName);
		clazz = defineClass(fullClassName, raw, 0, raw.length);
		resolveClass(clazz);
		return clazz;
	}

	private byte[] readClassBytes(String fullClassName) {
		byte[] raw = null;
		InputStream stream = null;
		File file = new File(classPath + File.separator + fullClassName.replaceAll("\\.", "/") + ".class");
		try {
			stream = new FileInputStream(file);
			raw = new byte[(int) file.length()];
			stream.read(raw);
		} catch (Exception e) {
			LoggerError.error("加载类：" + fullClassName, e);
		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				LoggerError.error("关闭文件#加载类：" + fullClassName, e);
			}
		}
		return raw;
	}

	public boolean isAlwaysDefineClass() {
		return alwaysDefineClass;
	}

	public void setAlwaysDefineClass(boolean alwaysDefineClass) {
		this.alwaysDefineClass = alwaysDefineClass;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
}
