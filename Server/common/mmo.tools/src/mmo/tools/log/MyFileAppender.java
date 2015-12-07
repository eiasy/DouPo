package mmo.tools.log;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.DailyRollingFileAppender;

public class MyFileAppender extends DailyRollingFileAppender {
	
	public void setFile(String fileName, boolean append, boolean bufferedIO, int bufferSize) throws IOException {
		makeDir(new File(fileName));
		super.setFile(fileName, append, bufferedIO, bufferSize);
	}

	private void makeDir(File file) {
		File parent = file.getParentFile();
		if (!parent.isDirectory()) {
			makeDir(parent);
			parent.mkdir();
		}
	}
}
