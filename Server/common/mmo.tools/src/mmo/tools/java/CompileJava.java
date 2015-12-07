package mmo.tools.java;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

public class CompileJava {
	public static void main(String[] args) throws Exception {
		compile2jar(System.getProperty("user.dir") + java.io.File.separator + "source", ".", "test.jar", " ./org");
	}

	public static boolean compile2jar(List<String> javaFiles, String binDir, String jarFile, String jarDir) {
		try {
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
			List<String> options = new ArrayList<String>();
			options.add("-d");
			options.add(binDir);
			Iterable<? extends JavaFileObject> files = fileManager.getJavaFileObjectsFromStrings(javaFiles);
			JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, options, null, files);

			Boolean result = task.call();
			if (result == true) {
			}

			Runtime run = Runtime.getRuntime();
			StringBuilder sb = new StringBuilder();
			sb.append("jar cvf ").append(jarFile).append(" ").append(jarDir);
			Process p = run.exec(sb.toString());
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			while (br.readLine() != null) {
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean compile2jar(String compileDir, String binDir, String jarFile, String jarDir) {
		try {
			List<String> javaFiles = new ArrayList<String>();
			File path = new File(compileDir);
			File[] list = path.listFiles(new JavaFileFilter(".java"));
			for (int i = 0; i < list.length; i++) {
				javaFiles.add(list[i].getAbsolutePath());
			}
			return compile2jar(javaFiles, binDir, jarFile, jarDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}

class JavaFileFilter implements FileFilter {
	private String keyword;

	public JavaFileFilter(String keyword) {
		this.keyword = keyword;
	}

	public boolean accept(File pathname) {
		return pathname.getName().toLowerCase().endsWith(keyword);
	}
}