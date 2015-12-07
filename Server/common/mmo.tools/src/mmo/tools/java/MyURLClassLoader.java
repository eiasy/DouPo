package mmo.tools.java;

import java.net.URL;
import java.net.URLClassLoader;

public class MyURLClassLoader extends URLClassLoader {

	public MyURLClassLoader(URL url) {
		super(new URL[] { url });
	}

	public static MyURLClassLoader reloadJar(String jarFile) {
		try {
			URL url = new URL(jarFile);
			return new MyURLClassLoader(url);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public Class<?> findClass(String classFile) {
		try {
			return super.findClass(classFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
}