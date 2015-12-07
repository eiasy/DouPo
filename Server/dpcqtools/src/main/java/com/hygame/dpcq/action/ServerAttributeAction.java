package com.hygame.dpcq.action;

import io.netty.channel.Channel;

import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import model.proto.Message;

import com.google.gson.internal.LinkedTreeMap;
import com.hygame.dpcq.config.ParamConfig;
import com.hygame.dpcq.coon.GameCoon;
import com.hygame.dpcq.db.dao.model.ServerAttribute;
import com.hygame.dpcq.model.proto.util.MessageData;
import com.hygame.dpcq.tools.FileUtil;
import com.hygame.dpcq.tools.HttpClientUtil;
import com.hygame.dpcq.tools.JsonUtil;
import com.hygame.dpcq.tools.Lock;
import com.hygame.dpcq.tools.PropertyUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 服务器相关
 * @author mp
 * @date 2015-4-2 下午2:35:49
 */
public class ServerAttributeAction extends ActionSupport {

	private int serverId;
	private int start;
	private int end;
	private String result;
	private HttpServletRequest request;
	private static final long serialVersionUID = 1L;
	public static List<ServerAttribute> serverAttributeAfterList = new ArrayList<ServerAttribute>();

	public static void main(String[] args) {
		System.out.println(new ServerAttributeAction().getUpdateSql());
	}
	
	/**
	 * 从文件中组装sql
	 * @author mp
	 * @date 2015-4-3 下午4:54:48
	 * @return
	 * @Description
	 */
	private String getUpdateSql () {
		
		Path path = Paths.get(ParamConfig.uploadFilePath, "update.txt");
		Charset charset = Charset.forName("utf-8");
		
		//读取内容,组装List
		StringBuilder sb = new StringBuilder();
		try(BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String content = "";
			while ((content = reader.readLine()) != null){
				if (content.contains("DROP TABLE")) {
					sb.append(content.replace("DROP TABLE IF EXISTS", "truncate table"));
					sb.append("#");
				}
				if (content.contains("INSERT INTO")) {
					sb.append(content);
					sb.append("#");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	/**
	 * 多服批量更新字典数据
	 * 记录失败服务器[主要有两种情况 1-Channel为关闭状态  2-Channel开着但是游戏服处理错误  3-本地程序处理时出现异常]
	 * @author mp
	 * @date 2015-4-3 下午4:02:05
	 * @Description
	 */
	public String updateDict () {
		
		String isUseGmHttp = PropertyUtil.getValue("use.gm.http");
		if (isUseGmHttp.equals("1")) {
			
			String updateSql = getUpdateSql();
			
//			System.out.println("updateSql = " + updateSql);
			
			if (!updateSql.equals("")) {
				
				//删除更新列表文件, result.txt
				FileUtil.deleFile(ParamConfig.uploadFilePath, "result.txt");
				
				//更新
				for (int i = start; i <= end; i++) {
					execUpdateHttp(updateSql, i);
				}
				result = "succ";
			}
		} else {
			
			GameCoon.orgServerList();
			
			String updateSql = getUpdateSql();
			
//		System.out.println("updateSql = " + updateSql);
			
			if (!updateSql.equals("")) {
				
				//删除更新列表文件, result.txt
				FileUtil.deleFile(ParamConfig.uploadFilePath, "result.txt");
				
				//更新
				for (int i = start; i <= end; i++) {
					execUpdate(updateSql, i);
				}
				result = "succ";
			}
		}
		
		return SUCCESS;
	}
	
	/**
	 * 获取gm Http连接地址
	 * @author mp
	 * @date 2015-10-26 下午3:06:34
	 * @return
	 * @Description
	 */
	public static String getGmHttpUri (int serverId) {
		orgRechargeServerInfo();
		String ip = GameCoon.serverInfoMap.get(serverId).split("_")[0];
		String gmHttpPort = GameCoon.serverInfoMap.get(serverId).split("_")[1];
		return "http://" + ip + ":" + gmHttpPort + "/30001";
	}
	
	/**
	 * 执行更新-Http
	 * @author mp
	 * @date 2015-10-26 下午2:38:52
	 * @param updateSql
	 * @param serverId
	 * @Description
	 */
	private static void execUpdateHttp (String updateSql, int serverId) {
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("command", "updateDict");
			paramMap.put("updateSql", updateSql);
			String params = HttpClientUtil.httpBuildQuery(paramMap);
			String retMsg = HttpClientUtil.postMapSubmit(getGmHttpUri(serverId), params);
			if("0".equals(retMsg)) {
				ServerAttributeAction.recordFailResult(serverId, ParamConfig.succ);
			} else {
				ServerAttributeAction.recordFailResult(serverId, ParamConfig.fail);
			}
		} catch (Exception e) {
			recordFailResult(serverId, ParamConfig.fail);
		}
	}
	
	/**
	 * 执行更新
	 * @author mp
	 * @date 2015-4-8 下午2:22:06
	 * @param updateSql
	 * @param serverId
	 * @Description
	 */
	private static void execUpdate (String updateSql, int serverId) {
		try {
			Channel channel = GameCoon.getChannel(serverId);
			if (channel != null && channel.isOpen() && channel.isActive()) {
				MessageData md = new MessageData();
				Message.Msg.Builder msg = Message.Msg.newBuilder();
				msg.setHeader(20013);
				msg.setVersion(0);
				md.putStringItem("updateSql", updateSql);
				msg.setMsgdata(md.getMsgData());
				channel.writeAndFlush(msg.build());
				Thread current = new Thread();
				try {
					Lock.threadMap.put("callback", current);
					synchronized (current) {
						try {
							current.wait();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					recordFailResult(serverId, ParamConfig.fail);
					e.printStackTrace();
				}
			} else {
				recordFailResult(serverId, ParamConfig.fail);
			}
		} catch (Exception e) {
			recordFailResult(serverId, ParamConfig.fail);
		}
		
		//关闭当前Channel-一台机器启动到一定的Channel数量后会卡,现在的处理方式是处理完一个关掉一个
		Channel channel = GameCoon.serverMap.get(serverId).getChannel();
		if (channel != null) {
			channel.close();
			channel = null;
		}
	}
	
	/**
	 * 记录结果  result = -1表示失败, = 0表示成功
	 * @author mp
	 * @date 2015-4-8 上午10:39:03
	 * @param serverId
	 * @param result
	 * @Description
	 */
	public static void recordFailResult (int serverId, int result) {
		FileUtil.writeFile(new File (ParamConfig.uploadFilePath + "/result.txt"), serverId + "_" + result + ";" + "\n", true);
	}
	
	/**
	 * 组织服务器列表
	 * @author mp
	 * @date 2015-4-2 下午4:36:29
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void orgServerList() {
		try {
			//关掉原始保留的连接
	    	for(Entry<Integer, ServerAttribute> entry : GameCoon.serverMap.entrySet()){
	    		ServerAttribute serverAttribute = entry.getValue();
	    		if (serverAttribute != null && serverAttribute.getChannel() != null) {
	    			serverAttribute.getChannel().close();
				}
	    	}
			GameCoon.serverMap.clear();
			serverAttributeAfterList.clear();
//			List<String> fileList = FileUtil.readFileToList(ParamConfig.uploadFilePath, "serverlist.txt","utf-8");
//			for (String str : fileList) {
//				int id = Integer.valueOf(str.split("_")[0]);
//				String name = str.split("_")[1];
//				int state = Integer.valueOf(str.split("_")[2]);
//				String ip = str.split("_")[3];
//				int port = Integer.valueOf(StringUtil.noContainLastString(str.split("_")[4])) + 10000;
//				ServerAttribute sa = new ServerAttribute(id, name, state,ip, port);
//				serverAttributeAfterList.add(sa);
//				GameCoon.serverMap.put(sa.getId(), sa);
//			}
			
			String fileName = PropertyUtil.getValue("serverList.name");
			
			String fileStr = FileUtil.readFileToStr(ParamConfig.uploadFilePath, fileName, "utf-8");
//			System.out.println("fileStr = " + fileStr);
			Map<String, List<LinkedTreeMap<String, Object>>> retMap = JsonUtil.fromJson(fileStr, Map.class);
			List<LinkedTreeMap<String, Object>> retList = retMap.get("servers_all");
			for (LinkedTreeMap<String, Object> obj : retList) {
				int id = (int)(double)(Double)(obj.get("id"));
				String name = obj.get("name") + "";
				int state = (int)(double)(Double)(obj.get("state"));
				String ip = obj.get("ip") + "";
				int port = (int)(double)(Double)(obj.get("port")) + 10000;
				ServerAttribute sa = new ServerAttribute(id, name, state,ip, port);
				serverAttributeAfterList.add(sa);
				GameCoon.serverMap.put(sa.getId(), sa);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 获取服务器列表-仅IP
	 * @author mp
	 * @date 2015-10-26 下午2:53:28
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	public static void orgRechargeServerInfo () {
		try {
			String fileName = PropertyUtil.getValue("serverList.name");
			String fileStr = FileUtil.readFileToStr(ParamConfig.uploadFilePath, fileName, "utf-8");
			Map<String, List<LinkedTreeMap<String, Object>>> retMap = JsonUtil.fromJson(fileStr, Map.class);
			List<LinkedTreeMap<String, Object>> retList = retMap.get("servers_all");
			for (LinkedTreeMap<String, Object> obj : retList) {
				int id = (int)(double)(Double)(obj.get("id"));
				String ip = obj.get("ip") + "";
				int port = (int)(double)(Double)(obj.get("port")) + 20000;
				GameCoon.serverInfoMap.put(id, ip + "_" + port);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * 验证服务器Channel状态
	 * @author mp
	 * @date 2015-4-2 下午4:30:11
	 * @return
	 * @Description
	 */
	public String validateChannel () {
		Channel channel = GameCoon.getChannel(serverId);
		if (channel == null) {
			result = null;
		} else {
			result = "succ";
		}
		return SUCCESS;
	}
	
	/**
	 * 展示服务器列表
	 * @author mp
	 * @date 2015-4-1 下午4:46:54
	 * @return
	 * @throws Exception
	 * @Description
	 */
	public String showServerList() throws Exception {
		ActionContext context = ActionContext.getContext();
		context.put("mes", serverAttributeAfterList.size());
		context.put("serlist", serverAttributeAfterList);
		return "fqpz";
	}

	/**
	 * 查看结果
	 * @author mp
	 * @date 2015-4-8 上午11:55:04
	 * @return
	 * @Description
	 */
	public String lookResult () {
		StringBuffer sb = new StringBuffer("0表示成功, -1表示失败, 结果为： ");
		sb.append("<br><br>");
		StringBuffer failSb = new StringBuffer("失败服务器列表： ");
		failSb.append("<br><br>");
//		System.out.println("ParamConfig.uploadFilePath = " + ParamConfig.uploadFilePath + "/result.txt");
		List<String> resultList = FileUtil.readFileToList(new File (ParamConfig.uploadFilePath + "/result.txt"));
		if (resultList != null) {
			int i = 0;
			int j = 0;
			for (String string : resultList) {
//				System.out.println("line = " + string);
				String serverId = string.split("_")[0];
				String state = string.split("_")[1];
				
				//记录失败服务器
				if (state.equals("-1;")) {
					j++;
					failSb.append("<font color='red'>" + serverId + "</font>" + "_" + state + "&nbsp;&nbsp;");
					if (j % 10 == 0) {
						failSb.append("<br>");
					}
				}
				
				//记录所有服务器
				i++;
				sb.append("<font color='red'>" + serverId + "</font>" + "_" + state + "&nbsp;&nbsp;");
				if (i % 10 == 0) {
					sb.append("<br>");
				}
			}
		}
		result = sb.toString() + "<br><br>" + failSb.toString();
		return SUCCESS;
	}
	
	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}
