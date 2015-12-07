package com.huayi.doupo.logic.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.UUID;

import com.huayi.doupo.base.model.SysCdKey;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;

/**
 * 生成注册码
 * @author mp
 * @date 2014-3-11 下午4:49:31
 */
public class CdkGenerUtil extends BaseHandler{

	public static void main(String[] args) {
		
		LinkedHashSet hashSet = new LinkedHashSet();
		List<SysCdKey> cdkList = getSysCdKeyDAL().getList("cdKeyTypeId = " + 1, 0);
		for(SysCdKey obj : cdkList){
			hashSet.add(obj.getCdk());
		}
		while(true){
			String uuid = UUID.randomUUID().toString();
			String cdkey = uuid.split("-")[4].replace('1', '8').replace('i', 'a').replace('l', 'h').replace('0', '9').replace('o', 'x');
			cdkey = ("kf" + cdkey).substring(0,8);
			hashSet.add(cdkey);
			if(hashSet.size() >= 5000){
				break;
			}
		}
		System.out.println("开始生成！");
		Iterator<String> iterator = hashSet.iterator();
		String filePath = "D:/work/cdk.txt";
		Path path = Paths.get(filePath);
    	if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(iterator.hasNext()){
			String value = iterator.next() + "\n";
			File file = new File(filePath);
			FileUtil.writeContentToFile(file, value, true);
		}
		System.out.println("生成完成！");
	}
}
