package mmo.tools.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;


/**
 * @功能：存放结果集的数据
 * @author 李天喜
 * 
 */
public class ResultList extends ArrayList<Object> {
	private static final long serialVersionUID = -2558011576098899442L;
	private int               rows;                                    // 总行数
	private int               cols;                                    // 总列列

	/**
	 * @返回:查询结果字段值
	 */
	public String getCellValue(String colName, int row) {
		String[] temp = (String[]) get(0);
		int index = 0;
		for (int i = 0; i < temp.length; i++) {
			if (colName.equals(temp[i])) {
				index = i;
			}
		}
		temp = (String[]) get(row);

		return temp[index];
	}

	/**
	 * 按行列值取出字段值
	 * 
	 * @参数1：列
	 * @参数2：行
	 * @返回：对应的值
	 */
	public String getCellValue(int cols, int row) {
		String[] temp = (String[]) get(row);
		return temp[cols];
	}

	/**
	 * 获取总列数
	 * 
	 * @返回：总列数
	 */
	public int getColCount() {
		return this.cols;
	}

	/**
	 * 返回总行数
	 * 
	 * @返回：总行数
	 */
	public int getRowCount() {
		return this.rows;
	}

	/**
	 * 设置总列数
	 * 
	 * @参数：列数
	 */
	public void setColCount(int col) {
		this.cols = col;
	}

	/**
	 * 设置总行数
	 * 
	 * @参数：行数
	 */
	public void setRowCount(int row) {
		this.rows = row;
	}

	/**
	 * 将结果集数据封装到LIST中
	 * 
	 * 返回：存放查询结果的链表
	 */
	public static ResultList buildResultList(ResultSet rs, String tableName) {
		ResultList resultList = new ResultList();
		try {
			ResultSetMetaData rsMetaData = rs.getMetaData();
			int Cols = rsMetaData.getColumnCount(); // 获取查询出来的数据字段个数
			resultList.setColCount(Cols); // 设置总列数
			String[] record = new String[Cols]; // 用来存放每一条记录
			for (int i = 1; i <= Cols; i++) {
				record[i - 1] = rsMetaData.getColumnTypeName(i); // 获取查询出来的字段名称
			}
			resultList.add(record); // 向LIST中添加一条记录，字段名

			while (rs.next()) // 循环将没一条记录加入LIST中
			{
				record = new String[Cols];
				for (int i = 1; i <= Cols; i++) {
					record[i - 1] = null;
					if ("VARCHAR".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = StringUtil.dealQuoteMark(rs.getString(i));
					} else if ("TEXT".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = StringUtil.dealQuoteMark(rs.getString(i));
					} else if (rsMetaData.getColumnTypeName(i).indexOf("TINYINT") >= 0) {
						record[i - 1] = new Integer(rs.getInt(i)).toString();
					} else if (rsMetaData.getColumnTypeName(i).indexOf("SMALLINT") >= 0) {
						record[i - 1] = new Integer(rs.getInt(i)).toString();
					} else if (rsMetaData.getColumnTypeName(i).indexOf("INTEGER") >= 0) {
						record[i - 1] = new Integer(rs.getInt(i)).toString();
					} else if ("FLOAT".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = new Float(rs.getFloat(i)).toString();
					} else if ("DOUBLE".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = new Double(rs.getDouble(i)).toString();
					} else if ("TIME".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = rs.getString(i) == null || rs.getString(i).equals("00:00:00") ? null : rs.getString(i);
					} else if ("DATE".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = rs.getString(i) == null || rs.getString(i).equals("0000-00-00") ? null : rs.getString(i);
					} else if ("TIMESTAMP".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = rs.getString(i) == null || rs.getString(i).equals("0000-00-00 00:00:00") ? null : rs.getString(i);
					} else if ("DATETIME".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = (rs.getString(i) == null || rs.getString(i).equals("0000-00-00 00:00:00")) ? null : rs.getString(i)
						        .substring(0, 19);
					} else if ("NULL".equals(rsMetaData.getColumnTypeName(i))) {
						record[i - 1] = null;
					}
				}
				resultList.add(record);
			}
			resultList.setRowCount(resultList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
}
