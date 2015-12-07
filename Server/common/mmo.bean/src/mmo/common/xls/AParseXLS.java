package mmo.common.xls;

import java.io.FileInputStream;
import java.io.InputStream;

import mmo.tools.log.LoggerError;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * XLS文件解析器
 * 
 * @author 李天喜
 * 
 */
public abstract class AParseXLS {
	/** 源文件名 */
	protected String       sourceFile;
	/** EXCEL文件 */
	protected HSSFWorkbook hwb = null;
	/** 重命名后的完成文件路径 */
	protected String       targetFile;
	/** 文件流 */
	private InputStream    is  = null;

	public AParseXLS(String sourceFile) {
		this(sourceFile, true);
	}

	public AParseXLS(String sourceFile, boolean redirect) {
		this.sourceFile = sourceFile;
		if (redirect) {
			this.targetFile = AXLSManager.getTargetFile(sourceFile);
		} else {
			this.targetFile = sourceFile;
		}
		try {
			is = new FileInputStream(targetFile);
			hwb = new HSSFWorkbook(is);
		} catch (Exception e) {
			LoggerError.error("加载文件报错：" + sourceFile, e);
		}
	}

	public HSSFSheet getSheet(String sheetName) {
		if (hwb == null) {
			return null;
		}
		return hwb.getSheet(sheetName);
	}

	public String getSourceFile() {
		return sourceFile;
	}

	/**
	 * 解析XLS文件
	 */
	abstract protected boolean parse();

	public boolean execute() {
		boolean result = parse();

		try {
			hwb = null;
			if (is != null) {
				is.close();
			}
		} catch (Exception e) {
			LoggerError.error("释放文件件流报错：" + sourceFile, e);
		} finally {
			sourceFile = null;
			targetFile = null;
		}
		return result;
	}

	public HSSFWorkbook getHwb() {
		return hwb;
	}
}
