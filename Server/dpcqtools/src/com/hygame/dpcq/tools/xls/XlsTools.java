package com.hygame.dpcq.tools.xls;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;


public class XlsTools {
	//操作excel 的工具类
	POIFSFileSystem fs ;
	HSSFWorkbook wb;
	int sheetcount = 0;
	public XlsTools(String url) throws FileNotFoundException, IOException{
		fs = new POIFSFileSystem(new FileInputStream(url));  
		//工作薄对象
		wb = new HSSFWorkbook(fs);  
		//sheet 个数
		sheetcount = wb.getNumberOfSheets();
		//有效行数
	}
	//获得指定sheet的有效行数
	public int rowCount(Sheet sheet){
		int rowCount = sheet.getLastRowNum();  
		return rowCount;		
	}
	//获得指定行数的有效单元格树
	public int getCellNum(Row row){
		short  i = row.getLastCellNum(); 
		return i;		
	}
	public String previewupdate(){
		StringBuffer sql = new StringBuffer();
		//获取sheet个数 拼sql 语句只拼update
		for(int i = 0 ; i < sheetcount ; i++){
			//获得 一个sheet
			Sheet sheet = wb.getSheetAt(i);
			//以sheet 名 作为表名
			String sqltabname = sheet.getSheetName();
		//	sql.append("表名:");
			sql.append(sqltabname);
			sql.append("&");
			//有效行数
			for(int n = 0 ; n<= rowCount(sheet) ; n++){
				//获取第一行数据作为操作分区以,号相隔all为全部分区
				if(n == 0){
				//	sql.append("分区:");
					Row row = sheet.getRow(n);
					//各个分区
					int m =	getCellNum(row);
					for(int c = 0 ; c < m ; c++){
						Cell cell = row.getCell(c);
						String cellString = cell.getStringCellValue();
						//分区
						sql.append(cellString);
					}
					sql.append("&");
				}
				if(n == 1 ){
					//sql.append("字段:");
					Row row = sheet.getRow(n);
					//表中各个字段名
					int m =	getCellNum(row);
					//获得第二行每个单元格的值 作为数据库表的每一个属性 生成sql
					for(int c = 0 ; c < m ; c++){
						Cell cell = row.getCell(c);
						String cellString = cell.getStringCellValue();
						//字段
						sql.append(cellString);
						if(c + 1 < m)
						sql.append(",");
					}
					sql.append("&");
				}
				if(n > 1 ){
					//sql.append("记录:");
					Row row = sheet.getRow(n);
					//表中各个字段名
					int m =	getCellNum(row);
					//获得第二行每个单元格的值 作为数据库表的每一个属性 生成sql
					for(int c = 0 ; c < m ; c++){
						Cell cell = row.getCell(c);
						//设置每个单元格的属性为String
						cell.setCellType(HSSFCell.CELL_TYPE_STRING);
						String cellString = cell.getStringCellValue();
						//字段
						sql.append(cellString);
						if(c + 1 < m)
						sql.append(",");
					}
					if(n != rowCount(sheet))
					sql.append("#");

				}
			}
			sql.append("&&");
		}
	//	System.out.println(sql.toString());
		return sql.toString();	
	}

}
