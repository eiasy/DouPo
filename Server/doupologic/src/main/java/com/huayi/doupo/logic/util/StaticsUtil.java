package com.huayi.doupo.logic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.logic.handler.base.BaseHandler;

/**
 * 统计工具类
 * @author mp
 * @date 2014-4-1 上午10:27:46
 */
public class StaticsUtil  extends BaseHandler{
	
//	private static String result = "";
	
	public static void main(String[] args){/*
		
		try {
			
			//删除原有日志文件
			FileUtil.delDirectoryFiles("D:/log/result");
			
			long startTime = System.currentTimeMillis();
			System.out.println("开始读取文件 ...");
			
			//读取文件,组织数据
			Map<Integer, List<String>> playerMap = orgData();
			
			System.out.println("读取文件完成 ! 共耗时 " + ((float)(System.currentTimeMillis() - startTime) / 1000) + "秒\n");
			
			//统计总注册人数
			int totalPlayer = playerMap.size();
			result = result +"总注册人数：" + totalPlayer + "\n";
			System.out.println("总注册人数：" + totalPlayer);
			
	        //统计1.2版本总人数
			List<Integer> twoLinkedList = new LinkedList<Integer>();
			Map<Integer, List<String>> twoTotalPlayerMap = getTwoTotalPlayerNum(playerMap, twoLinkedList);
	        int twoTotalPlayer = twoTotalPlayerMap.size();
	        result = result + "\n1.2版本人数：" + twoTotalPlayer + "\n" + "1.2版本人数/总人数的比例：" + twoTotalPlayer + "/" + totalPlayer + "=" + (float)twoTotalPlayer / (float)totalPlayer + "\n";
			System.out.println("\n1.2版本人数：" + twoTotalPlayer);
			System.out.println("1.2版本人数/总人数的比例：" + twoTotalPlayer + "/" + totalPlayer + "=" + (float)twoTotalPlayer / (float)totalPlayer);
			
			//统计1.2版本完成引导的总人数
			Map<Integer, List<String>> finishGuidMap = getFinishGuidPlayerNum(twoLinkedList, twoTotalPlayerMap);
			int finishGuidPlayerNum = finishGuidMap.size();
			result = result + "\n1.2版本中, [做完] 引导的人数：" + finishGuidPlayerNum + "\n" + "1.2版本中 [做完] 引导/1.2版本 [总人数] 的比例 : " + finishGuidPlayerNum + "/" + "" + twoTotalPlayer + "=" + (float)finishGuidPlayerNum / (float)twoTotalPlayer + "\n";
			System.out.println("\n1.2版本中 [做完] 引导的人数：" + finishGuidPlayerNum);
			System.out.println("1.2版本中 [做完] 引导/1.2版本 [总人数] 的比例 : " + finishGuidPlayerNum + "/" + "" + twoTotalPlayer + "=" + (float)finishGuidPlayerNum / (float)twoTotalPlayer);
			
			//统计1.2版本开小号的人数
			List<Integer> litNumLinkedList = getLitNumPlayerNum(finishGuidMap);
			int litNum = litNumLinkedList.size();
			result = result + "\n1.2版本中 [做完] 引导, [开小号] 的人数: " + litNum + "\n" + "1.2版本中 [开小号的]/1.2版本中 [做完] 引导人数的比例: " + litNum + "/" + "" + finishGuidPlayerNum + "=" + (float)litNum / (float)finishGuidPlayerNum + "\n";
			System.out.println("\n1.2版本中 [做完] 引导, [开小号] 的人数: " + litNum);
			System.out.println("1.2版本中 [开小号的]/1.2版本中 [做完] 引导人数的比例: " + litNum + "/" + "" + finishGuidPlayerNum + "=" + (float)litNum / (float)finishGuidPlayerNum);
			
			//统计1.2版本点点看看就走的人数
			int randomLookNum = getLookPlayerNum(litNumLinkedList.size(), finishGuidMap);
			result = result + "\n1.2版本中做完引导,点了点,看了看就走的: " + randomLookNum + "\n" + "1.2版本中 [随便看看] 的 /1.2版本中 [做完] 引导人数的比例: " + randomLookNum + "/" + "" + finishGuidPlayerNum + "=" + (float)randomLookNum / (float)finishGuidPlayerNum + "\n";
			System.out.println("\n1.2版本中做完引导,点了点,看了看就走的: " + randomLookNum);
			System.out.println("1.2版本中 [随便看看] 的 /1.2版本中 [做完] 引导人数的比例: " + randomLookNum + "/" + "" + finishGuidPlayerNum + "=" + (float)randomLookNum / (float)finishGuidPlayerNum);
			
			//生成整体统计文件
			geneFile("D:/log/result/整体统计.txt", result);
			
			lookPlayerAct(playerMap);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	*/}
	
	/**
	 * 查看某个玩家的行为
	 * @author mp
	 * @date 2014-4-1 上午10:39:47
	 * @param playerMap
	 * @throws Exception
	 * @Description
	 */
	public static void lookPlayerAct (Map<Integer, List<String>> playerMap) throws Exception{
		System.out.println("\n\n查看玩家行为,请输入玩家ID");
		String instPlayerId = new BufferedReader(new InputStreamReader(System.in)).readLine();
		while(!instPlayerId.equals("exit")){
			List<String> playerInfoList = playerMap.get(ConvertUtil.toInt(instPlayerId));
			for (String playerInfo : playerInfoList) {
				System.out.println(playerInfo);
			}
	        System.out.println("请输入玩家ID");
	        instPlayerId = new BufferedReader(new InputStreamReader(System.in)).readLine();
		}
	}
	
	/**
	 * 获取所有玩家
	 * @author mp
	 * @date 2014-3-31 下午3:58:39
	 * @return
	 * @Description
	 */
/*	public static Map<Integer, InstPlayer>  getTotalPlayer (Map<Integer, List<String>> playerMap) {
		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("instPlayerId > 500");
		Map<Integer, InstPlayer> instPlayerMap = new LinkedHashMap<Integer, InstPlayer>();
		for(InstPlayer instPlayer : instPlayerList){
			int instPlayerId = instPlayer.getInstPlayerId();
			if(playerMap.get(instPlayerId) == null){
				System.out.println(instPlayerId + "  ");
			}
			instPlayerMap.put(instPlayer.getInstPlayerId(), instPlayer);
		}
		return instPlayerMap;
	}*/
	
	/**
	 * 1.2版本中完成引导的玩家
	 * @author mp
	 * @date 2014-3-31 下午3:35:18
	 * @param twoLinkedList
	 * @return
	 * @Description
	 */
	public static Map<Integer, List<String>> getFinishGuidPlayerNum (List<Integer> twoLinkedList, Map<Integer, List<String>> twoTotalPlayerMap) {
		
		Map<Integer, List<String>> finishGuidMap = new LinkedHashMap<Integer, List<String>>();
		
		List<Integer> finishGuidList = new LinkedList<Integer>();
		List<Integer> noFinishGuidList = new LinkedList<Integer>();
		
		for(Entry<Integer, List<String>> entry : twoTotalPlayerMap.entrySet()){
			int instPlayerId = entry.getKey();
			List<String> playerLogInfoList = entry.getValue();
			
			int index = 0;
			int guidStepIndex = 0;
			int finiGuidStepIndex = 0;
			
			for (String linePlayerInfo : playerLogInfoList) {
				index ++ ;
				if (linePlayerInfo.contains("推图")) {
					guidStepIndex ++ ;
				}
				if (guidStepIndex == 3) {
					finiGuidStepIndex = index;
					break;
				}
			}
			
			//完成引导
			if (finiGuidStepIndex != 0 && (playerLogInfoList.size() >= (finiGuidStepIndex + 2))) {
				finishGuidList.add(instPlayerId);
				finishGuidMap.put(instPlayerId, playerLogInfoList);
			} else {
				noFinishGuidList.add(instPlayerId);
			}
			
		}
		
		geneFile("D:/log/result/1_2版本 [完成] 引导的玩家数据 " + finishGuidList.size() + ".txt", finishGuidList);
		geneFile("D:/log/result/1_2版本 [未完成] 引导的玩家数据" + noFinishGuidList.size() + ".txt", noFinishGuidList);
		
		return finishGuidMap;
	}
	
	/**
	 * 读取文件,组织数据
	 * @author mp
	 * @date 2014-3-31 下午3:10:03
	 * @return
	 * @throws Exception
	 * @Description
	 */
/*	public static Map<Integer, List<String>> orgData() throws Exception{
		Map<Integer, List<String>> playerMap = new HashMap<Integer, List<String>>();
		
		List<String> fileNameList = FileUtil.getFileNameList("D:/log");
		List<SysMaxOnlineNumEveryDay> sysMaxOnlineNumEveryDayList = getSysMaxOnlineNumEveryDayDAL().getList(" 1=1 order by settleTime");
		for (SysMaxOnlineNumEveryDay obj : sysMaxOnlineNumEveryDayList) {
			String settleTimeYMD = DateUtil.getYMDFormateData(DateUtil.getAddNumDayDate(obj.getSettleTime(), -1));
			for (String fileName : fileNameList) {
				if (fileName.contains(settleTimeYMD)) {
					String tempString = null;
					BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("D:/log/"+fileName), "utf-8"));
			        while ((tempString = reader.readLine()) != null) {
			        	if(tempString.contains("instPlayerId = ") && !tempString.contains("心跳检测")){
			        		int instPlayerId = getInstPlayerId(tempString);
			        		if (instPlayerId != 0){
			        			List<String> valueList = playerMap.get(instPlayerId);
			        			if(valueList == null || valueList.size() == 0){
			        				valueList = new LinkedList<String>();
			        			}
			        			valueList.add(tempString);
			        			playerMap.put(instPlayerId, valueList);
			        		}
			        	}
			        }
				}
			}
		}
        return playerMap;
	}*/
	
	/**
	 * 获取1.2版本玩家
	 * @author mp
	 * @date 2014-3-31 下午2:15:54
	 * @return
	 * @Description
	 */
	public static Map<Integer, List<String>> getTwoTotalPlayerNum (Map<Integer, List<String>> playerMap, List<Integer> twoLinkedList) {
		Map<Integer, List<String>> twoTotalPlayerMap = new LinkedHashMap<Integer, List<String>>();
		for(Entry<Integer, List<String>> entry : playerMap.entrySet()){
			int instPlayerId = entry.getKey();
			List<String> playerInfoList = entry.getValue();
			for(String string : playerInfoList){
				if(string.contains("推图") || string.contains("商店招募")){
					if(string.contains("商店招募")){
						twoLinkedList.add(instPlayerId);
						twoTotalPlayerMap.put(instPlayerId, playerInfoList);
					}
					break;
				}
			}
		}
		
		geneFile("D:/log/result/1_2版本 玩家数据" + twoLinkedList.size() + ".txt", twoLinkedList);
		
		return  twoTotalPlayerMap;
	}
	
	/**
	 * 获取1.2版本完成引导,但开小号的玩家
	 * 推图3次后再调2次协议后表示引导完成
	 * 完成引导后往下看3行,如果在这3行里出现招募次数大于等于1,并且没做别的就判定是开小号的人
	 * @author mp
	 * @date 2014-3-31 下午4:15:48
	 * @param playerMap
	 * @return
	 * @Description
	 */
	public static List<Integer> getLitNumPlayerNum (Map<Integer, List<String>> playerMap) {
		List<Integer> litNumLinkedList = new LinkedList<Integer>();
		for(Entry<Integer, List<String>> entry : playerMap.entrySet()){
			int instPlayerId = entry.getKey();
			List<String> playerInfoList = entry.getValue();
			List<String> pdLitNumLinkedList = new LinkedList<String>();
			int index = 0;
			int guidIndex = 0;
			int startIndex = 0;
			int endIndex = 0;
			for(String string : playerInfoList){
				index ++ ;
				if (string.contains("推图")) {
					guidIndex ++ ;
				}
				if (guidIndex == 3) {
					startIndex ++ ;
					endIndex = index + 2 + 3;//index + 2表示完成引导(推图3次后再调2次协议后表示引导完成)， + 3 表示完成引导后往下看3行,如果在这3行里出现招募次数大于等于1,并且没做别的就判定是开小号的人。
					guidIndex ++;
				}
				if(startIndex > 0){
					if(string.contains("商店招募")){
						pdLitNumLinkedList.add(string);
					}
					if (index > endIndex) {
						break;
					}
				}
			}
			int sumStep = playerInfoList.size();
			if (sumStep <= endIndex) {
				if (pdLitNumLinkedList.size() >= 1) {
					litNumLinkedList.add(instPlayerId);
				}
			}
		}
		geneFile("D:/log/result/1_2版本 [开小号] 玩家数据" + litNumLinkedList.size() + ".txt", litNumLinkedList);
		
		return  litNumLinkedList;
	}
	
	/**
	 * 获取随便看看的玩家
	 * @author mp
	 * @date 2014-3-31 下午5:43:21
	 * @param playerMap
	 * @return
	 * @Description
	 */
	public static int getLookPlayerNum (int litNumPlayerNum, Map<Integer, List<String>> finishGuidMap) {
		int randomLookNum = 0;
		StringBuffer sb = new StringBuffer("");
		int twoFinishPlayerNum = finishGuidMap.size();
		int [] numArray = {50, 100, 200, 300, 400, 500, 210};
		Map<Integer, List<Integer>> lookMap = new LinkedHashMap<Integer, List<Integer>>();
		for(Entry<Integer, List<String>> entry : finishGuidMap.entrySet()){
			int instPlayerId = entry.getKey();
			List<String> playerInfoList = entry.getValue();
			
			int index = 0;
			int guidIndex = 0;
			int finishGuidStepNum = 0;
			for(String string : playerInfoList){
				index ++ ;
				if (string.contains("推图")) {
					guidIndex ++ ;
				}
				if (guidIndex == 3) {
					finishGuidStepNum = index;
					guidIndex ++;
					break;
				}
			}
			
			for (int num : numArray) {
				//完成引导后的剩余步骤
				int finishGuidReaminStepNum = playerInfoList.size() - (finishGuidStepNum + 2);
				if (finishGuidReaminStepNum <= num) {
					List<Integer> valueList = lookMap.get(num);
        			if(valueList == null || valueList.size() == 0){
        				valueList = new LinkedList<Integer>();
        			}
        			valueList.add(instPlayerId);
        			lookMap.put(num, valueList);
				}
			}
		}
		
		//给Map按Key排序
		ArrayList<Entry<Integer,List<Integer>>> sortEntryList = new ArrayList<Entry<Integer,List<Integer>>>(lookMap.entrySet());    
        Collections.sort(sortEntryList, new Comparator<Map.Entry<Integer, List<Integer>>>() {    
            public int compare(Map.Entry<Integer, List<Integer>> o1, Map.Entry<Integer, List<Integer>> o2) {    
                return (o1.getKey() - o2.getKey());
            }    
        });
		
		for(Entry<Integer, List<Integer>> entry : sortEntryList){
			int num = entry.getKey();
			int lookPlayerNum = entry.getValue().size();
			if (num == 50) {
				randomLookNum = lookPlayerNum - litNumPlayerNum;
				List<Integer> lookLinkedList = entry.getValue();
				geneFile("D:/log/result/1_2版本 完成引导但未完成[50]步的玩家数据" + lookLinkedList.size() + ".txt", lookLinkedList);
			}
			sb.append("完成引导但未完成 [" + num + "] 步就走掉的  / 1.2版本中 [做完] 引导人数的比例 : " + lookPlayerNum + "/" + twoFinishPlayerNum + " = " + ((float)lookPlayerNum / (float)twoFinishPlayerNum) + "\n");
		}
		
		geneFile("D:/log/result/1_2版本 [随便看看的] 玩家数据统计.txt", sb.toString());
		return randomLookNum;
	}
	
	
	
	/**
	 * 生成文件
	 * @author mp
	 * @date 2014-3-31 下午3:42:06
	 * @param filePath
	 * @param list
	 * @Description
	 */
	public static void geneFile(String filePath, List<Integer> list){
		File file = new File(filePath);
		if(file.exists()){
			file.delete();
		}
//		FileUtil.writeFileForList(file, list, true);
	}
	
	/**
	 * 生成文件
	 * @author mp
	 * @date 2014-4-1 上午10:25:59
	 * @param filePath
	 * @param content
	 * @Description
	 */
	public static void geneFile(String filePath, String content){
		File file = new File(filePath);
//		FileUtil.writeFile(file, content, true);
	}
	
	/**
	 * 截取InstPlayerId
	 * @author mp
	 * @date 2014-3-31 下午2:00:18
	 * @param str
	 * @return
	 * @Description
	 */
	public static int getInstPlayerId(String str){
		return Integer.valueOf(str.substring(str.indexOf("=") + 1, str.indexOf("header")).trim());
	}
	
	@Test
	public void test(){
		try {
			FileUtil.delDirectoryFiles("D:/log/result");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
