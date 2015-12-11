package com.huayi.doupo.logic.util;

import java.lang.reflect.Field;

import com.huayi.doupo.base.model.InstPlayerEquip;
import com.huayi.doupo.base.model.InstPlayerHoldStar;
import com.huayi.doupo.base.util.base.StringUtil;

/**
 * 利用反射将某个类生成为MsgData的格式
 * @author mp
 * @date 2013-10-12 下午2:20:59
 */
public class ConvMsgDataUtil {
	
	public static void main(String[] args) {
		printMsgData(new InstPlayerHoldStar(), "msgData");
				
//		printMethod(new InstPlayerHoldStar(), "set");
	}
	
	public static void printMsgData(Object obj, String msgHeader){
		 String objHeader = StringUtil.uncapitalize(obj.getClass().getSimpleName());
		 Field[] fields = obj.getClass().getDeclaredFields();
		 StringBuffer sbBuffer = new StringBuffer();
		 for (int j = 0; j < fields.length; j++) {
			 if(fields[j].getName().equals("version") || fields[j].getName().equals("index") || fields[j].getName().equals("result")){
				 continue;
			 }
			 if(fields[j].getType().getName().equals("int")){
				 gener("Int", sbBuffer, fields[j].getName(), objHeader,msgHeader, j-2);
			 }
			 if(fields[j].getType().getName().equals("java.lang.String")){
				 gener("String", sbBuffer, fields[j].getName(), objHeader,msgHeader, j-2);
			 }
			 if(fields[j].getType().getName().equals("long")){
				 gener("Long", sbBuffer, fields[j].getName(), objHeader,msgHeader, j-2);
			 }
			 if(fields[j].getType().getName().equals("float")){
				 gener("Float", sbBuffer, fields[j].getName(), objHeader,msgHeader, j-2);
			 }
			 if(fields[j].getType().getName().equals("double")){
				 gener("Double", sbBuffer, fields[j].getName(), objHeader,msgHeader, j-2);
			 }
		 }
		 System.out.println(sbBuffer.toString());
	}
	
	private static void gener(String type, StringBuffer sbBuffer, String filedName, String objHeader, String msgHeader, int index){
		 sbBuffer.append(msgHeader+".put"+type+"Item(\"");
		 sbBuffer.append((index+1)+"\", "+objHeader+".get"+StringUtil.capitalize(filedName));
		 sbBuffer.append("());\n");
	}
	
	public static void printMethod(Object obj, String methodType){
		 String objHeader = StringUtil.uncapitalize(obj.getClass().getSimpleName());
		 Field[] fields = obj.getClass().getDeclaredFields();
		 StringBuffer sbBuffer = new StringBuffer();
		 for (int j = 0; j < fields.length; j++) {
			 if(fields[j].getName().equals("version") || fields[j].getName().equals("index") || fields[j].getName().equals("result")){
				 continue;
			 }
			 generMethod(objHeader, methodType, sbBuffer, fields[j].getName(), j-2);
		 }
		 System.out.println(sbBuffer.toString());
	}
	
	private static void generMethod(String objHeader, String type, StringBuffer sbBuffer, String filedName, int index){
		 sbBuffer.append(objHeader + "." + type +StringUtil.capitalize(filedName));
		 sbBuffer.append("();\n");
	}
}
