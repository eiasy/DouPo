package mmo.tools.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class ClassLoaderUtil {

	/**
	 * 加载Java类。 使用全限定类名
	 * 
	 * @paramclassName
	 * @return
	 */
	public static Class<?> loadClass(String className) {
		try {
			return getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("class not found '" + className + "'", e);
		}
	}

	/**
	 * 得到类加载器
	 * 
	 * @return
	 */
	public static ClassLoader getClassLoader() {
		return ClassLoaderUtil.class.getClassLoader();
	}

	/**
	 * 提供相对于classpath的资源路径，返回文件的输入流
	 * 
	 * @paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用 ../来查找
	 * @return 文件输入流
	 * @throwsIOException
	 * @throwsMalformedURLException
	 */
	public static InputStream getStream(String relativePath) throws MalformedURLException, IOException {
		if (!relativePath.contains("../")) {
			return getClassLoader().getResourceAsStream(relativePath);
		} else {
			return ClassLoaderUtil.getStreamByExtendResource(relativePath);
		}
	}

	/**
	 * 
	 * @paramurl
	 * @return
	 * @throwsIOException
	 */
	public static InputStream getStream(URL url) throws IOException {
		if (url != null) {
			return url.openStream();
		} else {
			return null;
		}
	}

	/**
	 * 
	 * @paramrelativePath必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使用 ../来查找
	 * @return
	 * @throwsMalformedURLException
	 * @throwsIOException
	 */
	public static InputStream getStreamByExtendResource(String relativePath) throws MalformedURLException, IOException {
		return ClassLoaderUtil.getStream(ClassLoaderUtil.getExtendResource(relativePath));
	}

	/**
	 * 提供相对于classpath的资源路径，返回属性对象，它是一个散列表
	 * 
	 * @paramresource
	 * @return
	 */
	public static Properties getProperties(String resource) {
		Properties properties = new Properties();
		try {
			properties.load(getStream(resource));
		} catch (IOException e) {
			throw new RuntimeException("couldn't load properties file '" + resource + "'", e);
		}
		return properties;
	}

	/**
	 * 得到本Class所在的ClassLoader的Classpat的绝对路径。 URL形式的
	 * 
	 * @return
	 */
	public static String getAbsolutePathOfClassLoaderClassPath() {
		String rootPath = System.getProperty("user.dir");
		rootPath = rootPath.replace("\\", "/");
		return rootPath;
	}

	/**
	 * 
	 * @paramrelativePath 必须传递资源的相对路径。是相对于classpath的路径。如果需要查找classpath外部的资源，需要使 用../来查找
	 * @return资源的绝对URL
	 * @throwsMalformedURLException
	 */
	public static String getExtendResource(String relativePath) {
		relativePath = relativePath.replace("${user.dir}", FileUtil.ROOT_DIR);
		if (!relativePath.contains("../")) {
			return ClassLoaderUtil.getResource(relativePath);
		}
		String classPathAbsolutePath = ClassLoaderUtil.getAbsolutePathOfClassLoaderClassPath();
		if (relativePath.substring(0, 1).equals("/")) {
			relativePath = relativePath.substring(1);
		}
		String wildcardString = relativePath.substring(0, relativePath.lastIndexOf("../") + 3);
		relativePath = relativePath.substring(relativePath.lastIndexOf("../") + 3);
		int containSum = ClassLoaderUtil.containSum(wildcardString, "../");
		classPathAbsolutePath = ClassLoaderUtil.cutLastString(classPathAbsolutePath, "/", containSum);
		String resourceAbsolutePath = classPathAbsolutePath + relativePath;
		return resourceAbsolutePath;
	}

	private static int containSum(String source, String dest) {
		int containSum = 0;
		int destLength = dest.length();
		while (source.contains(dest)) {
			containSum = containSum + 1;
			source = source.substring(destLength);
		}
		return containSum;
	}

	private static String cutLastString(String source, String dest, int num) {
		for (int i = 0; i < num; i++) {
			source = source.substring(0, source.lastIndexOf(dest, source.length() - 2) + 1);
		}
		return source;
	}

	public static String getResource(String resource) {
		File file = new File(resource);
		return file.getAbsolutePath();
	}

	/**
	 * @paramargs
	 * @throwsMalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {
		// ClassLoaderUtil.getExtendResource("../spring/dao.xml");
		// ClassLoaderUtil.getExtendResource("../../../src/log4j.properties");
		System.out.println(ClassLoaderUtil.getExtendResource("log4j.properties"));
		System.out.println(ClassLoaderUtil.getExtendResource("../log4j.properties"));
	}
}
