package com.hygame.dpcq.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StringTools {
	//分析字符串 根据字符串生成sql 语句
	//update
	public Map<Object, Map<String, List<String>>> updateSQL(String str){
		//返回 结果是一个 二维 map
		//第一层的 key 是 id 值 是一个map
		//第二层的 key 是sql 所执行的分区数据库列表 也用于前台显示 值 是 一条一条的sql
		Map<Object, Map<String, List<String>>> map = new TreeMap<Object, Map<String, List<String>>>();
		
		//sheet 的个数 也就是更新对应几张表
		String[] pts = str.split("&&");
		//遍历
		for(int l = 0 ; l < pts.length ; l++){
			
			//获得四项的字符串  表名 ， 分区 ， 字段 ， 记录
			String[] str1 = pts[l].split("&");
			if(str1.length >= 4){
				//获得表名
				String tabname = str1[0];
				//执行分区
				String partition = str1[1];
				//记录数量根据记录条数生成sql的数量
				String[] record = str1[3].split("#");
				Map<String, List<String>> pt = new TreeMap<String , List<String>>();
				List<String> list = new ArrayList<String>();
				//每一条记录 拼成一个sql
				for(int i = 0 ; i < record.length ; i++){
				
					
					StringBuffer strbuff = new StringBuffer();
					//添加的记录值
					String row = record[i];
					//确定每个记录有几个字段
					String[] rec = row.split(",");
					for(int n = 1 ; n < rec.length ; n++){
						//循环写入
						strbuff.append( str1[2].split(",")[n] +" = '"+ rec[n] +"'" );
						if(rec.length -1 > n)
							strbuff.append(" , ");	
					}
					strbuff.append(" where ");
					strbuff.append( str1[2].split(",")[0] +" = "+ rec[0] );
					String sql = "update "+ tabname + " set " + strbuff.toString();
					list.add(sql);
					//存进map
					
				}
				pt.put(partition, list);
				map.put(tabname, pt);
			
			}
		}
		return map;	
	}
}
