package mmo.common.xls;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import mmo.tools.log.LoggerError;
import mmo.tools.util.DateUtil;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;

/**
 * 解析单页
 * 
 * @author 李天喜
 * 
 */
abstract public class AParseSheet {
	private final static char      SYMBOL_C_COMMA     = '，';
	protected final static char    SYMBOL_E_COMMA     = ',';
	private final static char      SYMBOL_C_semicolon = '；';
	protected final static char    SYMBOL_E_semicolon = ';';
	private final static char      SYMBOL_C_DOT       = '：';
	protected final static char    SYMBOL_E_DOT       = ':';
	protected final static char    SYMBOL_C_BRACKETS  = '】';
	protected final static char    SYMBOL_C_WAVE      = '~';
	protected final static char    SYMBOL_C_ROUND     = '（';
	protected final static char    SYMBOL_E_ROUND     = '(';
	protected final static char    SYMBOL_CR_ROUND    = '）';
	protected final static char    SYMBOL_ER_ROUND    = ')';
	protected final static char    SYMBOL_MINUS       = '-';
	protected final static char    SYMBOL_PLUS        = '+';

	protected final static String  SYMBOL_AT          = "@";
	protected final static String  SYMBOL_AND         = "&";
	protected final static String  SYMBOL_SHARP       = "#";
	protected final static String  SYMBOL_PERCENT     = "%";

	protected static final String  YES                = "是";
	protected static final String  NO                 = "否";

	protected Map<Object, Integer> columnIndex        = new HashMap<Object, Integer>();
	protected HSSFSheet            sheet;
	private FormulaEvaluator       evaluator          = null;
	private String                 sourceFile;

	public AParseSheet(String sourceFile, HSSFSheet sheet) {
		this.sourceFile = sourceFile;
		this.sheet = sheet;
		if (sheet != null) {
			evaluator = sheet.getWorkbook().getCreationHelper().createFormulaEvaluator();
		}
		initColumnIndex();
	}

	public final boolean execute() {
		// LoggerError.messageLog.warn("------开始解析------>>" + getSourceFile() + ">>" + getSheetName());
		boolean result = parse();
		// LoggerError.messageLog.warn("######结束解析######>>" + getSourceFile() + ">>" + getSheetName());
		releaseSheet();
		return result;
	}

	public String getSourceFile() {
		return sourceFile;
	}

	private void initColumnIndex() {
		if (sheet == null) {
			return;
		}
		if (columnIndex == null) {
			columnIndex = new HashMap<Object, Integer>();
		} else {
			columnIndex.clear();
		}
		HSSFRow row = sheet.getRow(0);
		Cell cell = null;
		int column = row.getLastCellNum();
		for (int index = 0; index < column; index++) {
			cell = row.getCell(index);
			if (cell != null) {
				String stringValue = null;
				if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
					columnIndex.put(cell.getNumericCellValue(), index);
				} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
					if (evaluator != null) {
						CellValue cellValue = evaluator.evaluate(cell);
						switch (cellValue.getCellType()) {
							case Cell.CELL_TYPE_BOOLEAN:
								columnIndex.put(String.valueOf(cellValue.getBooleanValue()).toLowerCase(), index);
							case Cell.CELL_TYPE_NUMERIC:
								columnIndex.put(String.valueOf(cellValue.getNumberValue()).toLowerCase(), index);
							case Cell.CELL_TYPE_STRING:
								stringValue = String.valueOf(cellValue.getStringValue()).toLowerCase();
								stringValue = stringValue.replace(SYMBOL_C_ROUND, SYMBOL_E_ROUND);
								stringValue = stringValue.replace(SYMBOL_CR_ROUND, SYMBOL_ER_ROUND);
								columnIndex.put(stringValue, index);
								break;
							default: {
								stringValue = String.valueOf(cellValue.getStringValue()).toLowerCase();
								stringValue = stringValue.replace(SYMBOL_C_ROUND, SYMBOL_E_ROUND);
								stringValue = stringValue.replace(SYMBOL_CR_ROUND, SYMBOL_ER_ROUND);
								columnIndex.put(stringValue, index);
							}
						}
					}
				} else {
					stringValue = cell.getStringCellValue().toLowerCase();
					stringValue = stringValue.replace(SYMBOL_C_ROUND, SYMBOL_E_ROUND);
					stringValue = stringValue.replace(SYMBOL_CR_ROUND, SYMBOL_ER_ROUND);
					columnIndex.put(stringValue, index);
				}
			}
		}
	}

	/**
	 * 获取一行中指定列的字符串值
	 * 
	 * @param row
	 *            行
	 * @param index
	 *            列
	 * @return 字符串值，找不到则返回NULL
	 */
	public String getStringValue(HSSFRow row, int index) {
		if (index < 0) {
			return null;
		}
		HSSFCell cell = row.getCell(index);
		if (cell == null) {
			return null;
		}
		String value = getCellStringValue(cell);
		if (value != null) {
			value = value.trim();
			value = value.replace(SYMBOL_C_COMMA, SYMBOL_E_COMMA);
			value = value.replace(SYMBOL_C_DOT, SYMBOL_E_DOT);
			value = value.replace(SYMBOL_C_semicolon, SYMBOL_E_semicolon);
			value = value.replace(SYMBOL_C_ROUND, SYMBOL_E_ROUND);
			value = value.replace(SYMBOL_CR_ROUND, SYMBOL_ER_ROUND);
			return value;
		}
		return null;
	}

	/**
	 * 获取一行中指定列的浮点值
	 * 
	 * @param row
	 *            行
	 * @param index
	 *            列
	 * @return 浮点值，找不到则返回NULL
	 */
	public double getDoubleValue(HSSFRow row, int index) {
		return Double.parseDouble(getStringValue(row, index));
	}

	/**
	 * 获取一行中指定列的浮点值
	 * 
	 * @param row
	 *            行
	 * @param columnName
	 *            列名
	 * @return 浮点值，找不到则返回NULL
	 */
	public double getDoubleValue(HSSFRow row, String columnName) {
		return Double.parseDouble(getStringValue(row, columnName));
	}

	/**
	 * 获取一行中指定列的浮点值(兼容模式，如果格子未填或空字符则返回0.0)
	 * 
	 * @param row
	 *            行
	 * @param columnName
	 *            列名
	 * @return 浮点值，找不到则返回NULL
	 */
	public float getFloatValueRelax(HSSFRow row, String columnName) {
		try {
			String value = getStringValue(row, columnName);
			if (value == null || value.trim().length() == 0) {
				return 0;
			}
			return Float.parseFloat(value);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取一行中指定列的int值
	 * 
	 * @param row
	 *            行
	 * @param index
	 *            列
	 * @return int值，找不到则返回NULL
	 */
	public int getIntValue(HSSFRow row, int index) {
		return (int) getDoubleValue(row, index);
	}

	/**
	 * 获取一行中指定列的int值(兼容模式，如果格子未填或空字符则返回0.0)
	 * 
	 * @param row
	 *            行
	 * @param columnName
	 *            列名
	 * @return int值，找不到则返回NULL
	 */
	public int getIntValueRelax(HSSFRow row, String columnName) {
		return (int) getFloatValueRelax(row, columnName);
	}

	/**
	 * 获取一行中指定列的int值
	 * 
	 * @param row
	 *            行
	 * @param columnName
	 *            列名
	 * @return int值，找不到则返回NULL
	 */
	public int getIntValue(HSSFRow row, String columnName) {
		return (int) getDoubleValue(row, columnName);
	}

	/**
	 * 获取一行中指定列的short值
	 * 
	 * @param row
	 *            行
	 * @param columnName
	 *            列名
	 * @return int值，找不到则返回NULL
	 */
	public short getShortValue(HSSFRow row, String columnName) {
		return (short) getDoubleValue(row, columnName);
	}

	/**
	 * 获取一行中指定列的short值
	 * 
	 * @param row
	 *            行
	 * @param columnName
	 *            列名
	 * @return int值，找不到则返回NULL
	 */
	public short getShortValueRelax(HSSFRow row, String columnName) {
		return (short) getIntValueRelax(row, columnName);
	}

	/**
	 * 获取当前列的内容
	 * 
	 * @param hssfCell
	 *            当前列
	 * @return 转化为字符串后的内容
	 */
	private String getCellStringValue(HSSFCell hssfCell) {
		int cellType = hssfCell.getCellType();
		switch (cellType) {
			case Cell.CELL_TYPE_BOOLEAN: {
				return String.valueOf(hssfCell.getBooleanCellValue());
			}
			case Cell.CELL_TYPE_NUMERIC: {
				String result = "";
				if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {// 处理日期格式、时间格式
					SimpleDateFormat sdf = null;
					Date date = hssfCell.getDateCellValue();
					int format = hssfCell.getCellStyle().getDataFormat();

					switch (format) {
						case 0xe: {// "m/d/yy"
							result = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
							break;
						}
						case 0xf: {// "d-mmm-yy"
							result = DateUtil.formatDate(date, "yyyy-MM-dd");
							break;
						}
						case 0x10: {// "d-mmm"
							result = DateUtil.formatDate(date, "MM-dd");
							break;
						}
						case 0x11: {// "mmm-yy"
							result = DateUtil.formatDate(date, "yyyy-MM");
							break;
						}
						case 0x12: {// "h:mm AM/PM"
							result = DateUtil.formatDate(date, "HH:mm");
							break;
						}
						case 0x13: {// "h:mm:ss AM/PM"
							result = DateUtil.formatDate(date, "HH:mm:ss");
							break;
						}
						case 0x14: {// "h:mm"
							result = DateUtil.formatDate(date, "HH:mm");
							break;
						}
						case 0x15: {// "h:mm:ss"
							result = DateUtil.formatDate(date, "HH:mm:ss");
							break;
						}
						case 0x16: {// "m/d/yy h:mm"
							result = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
							break;
						}
						default: {
							result = DateUtil.formatDate(date, "yyyy-MM-dd HH:mm:ss");
							break;
						}
					}
				} else {
					result = String.valueOf(hssfCell.getNumericCellValue());
				}
				return result;
			}
			case Cell.CELL_TYPE_FORMULA: {
				if (evaluator != null) {
					CellValue cellValue = evaluator.evaluate(hssfCell);
					switch (cellValue.getCellType()) {
						case Cell.CELL_TYPE_BOOLEAN:
							return String.valueOf(cellValue.getBooleanValue());
						case Cell.CELL_TYPE_NUMERIC:
							return String.valueOf(cellValue.getNumberValue());
						case Cell.CELL_TYPE_STRING:
							return String.valueOf(cellValue.getStringValue());
						default: {
							return String.valueOf(cellValue.getStringValue());
						}
					}
				}
			}
			case Cell.CELL_TYPE_BLANK:
			case Cell.CELL_TYPE_ERROR:
			case Cell.CELL_TYPE_STRING: {
				return String.valueOf(hssfCell.getStringCellValue());
			}
			default: {
				return String.valueOf(hssfCell.getStringCellValue());
			}
		}
	}

	/**
	 * 获取一行中指定列的字符串值
	 * 
	 * @param row
	 *            行
	 * @param String
	 *            columnName 列
	 * @return 字符串值，找不到则返回NULL
	 */
	public String getStringValue(HSSFRow row, String columnName) {
		String str = null;
		try {
			str = getStringValue(row, getColumnIndex(columnName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * 通过第一行中某一列中的值获取所在的列
	 * 
	 * @param columnName
	 *            列名（第一行中的值）
	 * @return 列值
	 */
	public int getColumnIndex(String columnName) {
		if (columnName == null) {
			return -1;
		}
		columnName = columnName.replace(SYMBOL_C_ROUND, SYMBOL_E_ROUND);
		columnName = columnName.replace(SYMBOL_CR_ROUND, SYMBOL_ER_ROUND);
		Integer index = columnIndex.get(columnName.toLowerCase());
		if (index == null) {
			return -1;
		}
		return index;
	}

	/**
	 * 释放资源
	 */
	protected void releaseSheet() {
		if (columnIndex != null) {
			columnIndex.clear();
			columnIndex = null;
		}
		evaluator = null;
		sheet = null;
	}

	/**
	 * 获取该页总行数
	 * 
	 * @return 总行数
	 */
	public int getLastRowNum() {
		if (sheet == null) {
			return 0;
		}
		return sheet.getLastRowNum();
	}

	/**
	 * 获取一行数据
	 * 
	 * @param row
	 *            行数
	 * @return 行
	 */
	public HSSFRow getRow(int row) {
		if (sheet == null) {
			error(row, getSourceFile(), getSheetName());
		}
		return sheet.getRow(row);
	}

	public String getSheetName() {
		if (sheet == null) {
			return null;
		}
		return sheet.getSheetName();
	}

	private final static String ERROR  = "【ERROR】";
	private final static String SYMBOL = ">>";
	private final static String ROW    = "ROW:";

	public void error(int row, String title) {
		StringBuffer sb = new StringBuffer();
		sb.append(ERROR).append(getSourceFile()).append(SYMBOL).append(getSheetName()).append(SYMBOL).append(ROW).append(row).append(SYMBOL)
		        .append(title);
		LoggerError.messageLog.error(sb.toString());
	}

	public void error(int row, String title, Object value) {
		StringBuffer sb = new StringBuffer();
		sb.append(ERROR).append(getSourceFile()).append(SYMBOL).append(getSheetName()).append(SYMBOL).append(ROW).append(row).append(SYMBOL)
		        .append(title).append(SYMBOL).append(value);
		LoggerError.messageLog.error(sb.toString());
	}

	public void error(int row, String title, Exception e) {
		StringBuffer sb = new StringBuffer();
		sb.append(ERROR).append(getSourceFile()).append(SYMBOL).append(getSheetName()).append(SYMBOL).append(ROW).append(row).append(SYMBOL)
		        .append(title);
		LoggerError.error(sb.toString(), e);
	}

	public void error(int row, String title, Object value, Exception e) {
		StringBuffer sb = new StringBuffer();
		sb.append(ERROR).append(getSourceFile()).append(SYMBOL).append(getSheetName()).append(SYMBOL).append(ROW).append(row).append(SYMBOL)
		        .append(title).append(SYMBOL).append(value);
		LoggerError.error(sb.toString(), e);
	}

	/**
	 * 解析选项页
	 */
	abstract public boolean parse();
}
