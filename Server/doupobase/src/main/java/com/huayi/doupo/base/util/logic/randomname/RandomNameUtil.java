package com.huayi.doupo.base.util.logic.randomname;

import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.huayi.doupo.base.model.DictCard;
import com.huayi.doupo.base.model.dict.DictData;
import com.huayi.doupo.base.model.dict.DictList;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.base.util.base.RandomUtil;


/**
 * 随机名字库工具类
 * @author mp
 * @date 2013-12-13 上午10:40:21
 */
public class RandomNameUtil {/*
	
	/**
	 * 一个汉字的名
	 */
	public static List<String> oneWorldName = new ArrayList<String>();
	
	/**
	 * 两个汉字的姓
	 */
	public static List<String> twoWorldFamilyName = new ArrayList<String>();

	/**
	 * 初始化随机名字库
	 * @author mp
	 * @date 2013-12-13 上午11:02:23
	 * @Description
	 */
	public static void initRandomNameUtil(){
		List<DictCard> warriorList = DictList.dictCardList;
		for(DictCard obj : warriorList){
			String nameOne = obj.getName();
			String nameTwo = obj.getName();
			oneWorldName.add(nameOne.substring(1,nameOne.length()));
			if(nameTwo.length() > 2){
				twoWorldFamilyName.add(nameTwo.substring(0,2));
			}
		}
	}
	
	/**
	 * 获取随机名字
	 * @author mp
	 * @date 2013-12-13 上午11:06:11
	 * @return
	 * @Description
	 */
	public static String getRandmName(){
		String randomName = "";
		int randomOne = RandomUtil.getRandomInt(oneWorldName.size());
		int randomTwo = RandomUtil.getRandomInt(twoWorldFamilyName.size());
		randomName = twoWorldFamilyName.get(randomTwo) + oneWorldName.get(randomOne);
		char [] nameArray = randomName.toCharArray();
		List<String> nameList = new ArrayList<String>();
		for(char c : nameArray){
			nameList.add(String.valueOf(c));
		}
		Collections.shuffle(nameList);//洗牌
		StringBuffer newName = new StringBuffer("");
		for(String s : nameList){
			newName.append(s);
		}
		randomName = newName.toString();
		if(randomName.length() > 5){
			randomName = randomName.substring(0,5);
		}
		return randomName;
	}
	
	public static void main(String[] args) throws Exception {
		DictData.loadDictData();
		initRandomNameUtil();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 10000; i++) {
			sb.append(getRandmName() + "\n");
		}
		FileUtil.writeContentToFile("E:/", "aa.txt", "utf-8", sb.toString(), StandardOpenOption.CREATE_NEW);
	}
	
}
