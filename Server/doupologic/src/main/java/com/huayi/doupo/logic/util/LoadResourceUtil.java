package com.huayi.doupo.logic.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;

import com.huayi.doupo.base.config.ParamConfig;
import com.huayi.doupo.base.config.sdk.SdkConfig;
import com.huayi.doupo.base.dal.factory.DALFactory;
import com.huayi.doupo.base.model.InstPlayer;
import com.huayi.doupo.base.model.InstPlayerBigTable;
import com.huayi.doupo.base.model.InstUnion;
import com.huayi.doupo.base.model.InstUnionMember;
import com.huayi.doupo.base.model.socket.WorldBossPlayer;
import com.huayi.doupo.base.model.statics.StaticActivity;
import com.huayi.doupo.base.model.statics.StaticBigTable;
import com.huayi.doupo.base.util.base.ConvertUtil;
import com.huayi.doupo.base.util.base.DateUtil;
import com.huayi.doupo.base.util.base.FileUtil;
import com.huayi.doupo.base.util.base.MySqlImportAndExportUtil;
import com.huayi.doupo.base.util.base.SerializeUtil;
import com.huayi.doupo.base.util.logic.system.JdbcConfigUtil;
import com.huayi.doupo.base.util.logic.system.LogUtil;
import com.huayi.doupo.base.util.logic.system.SpringUtil;
import com.huayi.doupo.base.util.logic.system.SysConfigUtil;
import com.huayi.doupo.base.util.logic.wordfilter.WordFilterUtil;
import com.huayi.doupo.logic.handler.util.AwardUtil;

/**
 * 资源工具类
 * 
 * @author mp
 * @date 2013-9-23 下午4:01:27
 */
public class LoadResourceUtil extends DALFactory{
	
	/**
	 * 加载资源
	 * @author mp
	 * @date 2013-9-23 下午4:02:28
	 * @throws Exception
	 * @Description
	 */
	public static void loadResource() throws Exception {
		
//		Load LogBack
		loadLogBack();
		
//		Load Spring Resource
		SpringUtil.getSpringContext();
		
//		Open Server Init Inst table
		initInstTable();
		
//		Load Params
		loadParams();
		
//		Load Filter Words
		WordFilterUtil.insertFilterWordsList();
		
//		Load System Config
		SysConfigUtil.getProp();
		
//		Load Msg Resource
		ProtocolHandleUtil.getMap();
		
//		Load Name
		NameUtil.initName();
		
		//反序列化玩家战力并加载到map中
		PlayerFightValueMapUtil.unserializeFightValue();
		
		//反序列化限时英雄积分奖励
		unseriLimitTimePealpleJifen();
		
		//反序列化限时英雄排行奖励
		unseriLimitTimePealpleRank();
		
		//反序列化最强英雄排行奖励
		unseriStrongerHeroRank();
		
		//反序列化竞技场领奖
		unseriAreaReward();
		
		//反序列化世界boss排行
		unWorldBossRank();
		
		//初始化 巅峰强强者第一名列表
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '" + StaticBigTable.strogerHeroFirstNames + "'", 0);
		if (instPlayerBigTableList.size() > 0) {
			ParamConfig.strogherHeroNumOnes = instPlayerBigTableList.get(0).getValue1();
		}
	}
	
	/**
	 * 开服初始化实例表数据
	 * @author mp
	 * @date 2015-8-21 下午8:15:08
	 * @Description
	 */
	private static void initInstTable () {
		try {
			if(FileUtil.isExsits(System.getProperty("user.dir") + "/config/inst.sql")) {
				if (!isHaveInstTable()) {
					System.out.println(DateUtil.getCurrTime() + "--- start insert inst table ---");
					String path1 = System.getProperty("user.dir") + "/config/";
					Path path = Paths.get(path1, "inst.sql");
					Charset charset = Charset.forName("utf-8");
					
					//读取内容,组装List
					StringBuilder sb = new StringBuilder();
					try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
						String content = "";
						int create = 0;
						while ((content = reader.readLine()) != null){
							if (content.contains("CREATE TABLE")) {
								create = 1;
							}
							if (content.contains("INSERT INTO")) {
								sb.append(content);
								sb.append("#");
							}
							if (create == 1) {
								sb.append(content);
								if (content.contains(") ENGINE=InnoDB")) {
									create = 0;
									sb.append("#");
								}
							}
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					JdbcTemplate jdbcTemplate = (JdbcTemplate)SpringUtil.GetObjectWithSpringContext("JdbcTemplate");
					jdbcTemplate.batchUpdate(sb.toString().split("#"));
					
					System.out.println(DateUtil.getCurrTime() + "--- insert inst table end ---");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("开服初始化实例表数据", e);
		}
	}
	
	/**
	 * 反序列化限时英雄积分奖励
	 * @author mp
	 * @date 2015-8-19 上午9:36:42
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	private static void unseriLimitTimePealpleJifen () {
		try {
			String path = System.getProperty("user.dir") + "/config/";
			String fileName = "limitTimePeapleJifen.txt";
			File file = new File(path + fileName);
			if(file.exists()){
				long length = file.length();
				FileInputStream input;
				input = new FileInputStream(file);
				byte[] b = new byte[(int)length];    //设置大小，用文件长度。
				input.read(b);
				Object obj = SerializeUtil.unserialize(b);
				ParamConfig.recruitJifenRewardMap = (ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>)obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("反序列化限时英雄积分奖励Error", e);
		}
	}
	
	/**
	 * 反序列化限时英雄排行奖励
	 * @author mp
	 * @date 2015-8-19 上午9:36:42
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	private static void unseriLimitTimePealpleRank () {
		try {
			String path = System.getProperty("user.dir") + "/config/";
			String fileName = "limitTimePeapleRank.txt";
			File file = new File(path + fileName);
			if(file.exists()){
				long length = file.length();
				FileInputStream input;
				input = new FileInputStream(file);
				byte[] b = new byte[(int)length];    //设置大小，用文件长度。
				input.read(b);
				Object obj = SerializeUtil.unserialize(b);
				ParamConfig.recruitRankRewardMap = (ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>)obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("反序列化限时英雄排行奖励Error", e);
		}
	}
	
	/**
	 * 反序列化最强英雄排行奖励
	 * @author mp
	 * @date 2015-8-19 下午6:04:23
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	private static void unseriStrongerHeroRank () {
		try {
			String path = System.getProperty("user.dir") + "/config/";
			String fileName = "strogerHeroRank.txt";
			File file = new File(path + fileName);
			if(file.exists()){
				long length = file.length();
				FileInputStream input;
				input = new FileInputStream(file);
				byte[] b = new byte[(int)length];    //设置大小，用文件长度。
				input.read(b);
				Object obj = SerializeUtil.unserialize(b);
				ParamConfig.strogerRankRewardMap = (ConcurrentHashMap<Integer, ConcurrentHashMap<String, Integer>>)obj;
//				System.out.println("server start: " + ParamConfig.strogerRankRewardMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("反序列化最强英雄排行奖励Error", e);
		}
	}
	
	/**
	 * 反序列化竞技场领奖
	 * @author mp
	 * @date 2015-10-8 下午4:55:37
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	private static void unseriAreaReward () {
		try {
			String path = System.getProperty("user.dir") + "/config/";
			String fileName = "areaReward.txt";
			File file = new File(path + fileName);
			if(file.exists()){
				long length = file.length();
				FileInputStream input;
				input = new FileInputStream(file);
				byte[] b = new byte[(int)length];//设置大小，用文件长度。
				input.read(b);
				Object obj = SerializeUtil.unserialize(b);
				Map<Integer, Map<String, Integer>> awarMap = (Map<Integer, Map<String, Integer>>)obj;
				AwardUtil.setAreaAward(awarMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("反序列化竞技场领奖Error", e);
		}
	}
	
	/**
	 * 反序列化竞技场领奖
	 * @author mp
	 * @date 2015-11-30 下午14:00:00
	 * @Description
	 */
	@SuppressWarnings("unchecked")
	private static void unWorldBossRank () {
		try {
			String path = System.getProperty("user.dir") + "/config/";
			String fileName = "worldBossRank.txt";
			File file = new File(path + fileName);
			if(file.exists()){
				long length = file.length();
				FileInputStream input;
				input = new FileInputStream(file);
				byte[] b = new byte[(int)length];//设置大小，用文件长度。
				input.read(b);
				Object obj = SerializeUtil.unserialize(b);
				ParamConfig.worldBossRankList = (List<WorldBossPlayer>)obj;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.error("反序列化世界Boss排行Error", e);
		}
	}
	
	/**
	 * 初始化参数文件
	 * @author mp
	 * @date 2015-7-30 下午8:30:44
	 * @throws Exception
	 * @Description
	 */
	private static void loadParams () throws Exception{
//		Load SdkConfig
		ParamConfig.sdkConfig = (SdkConfig)SpringUtil.GetObjectWithSpringContext("SdkConfig");
			
		ParamConfig.cpuMom = Integer.valueOf(SysConfigUtil.getValue("netty.worker.cpuMom"));
		
		ParamConfig.serverAcrossDayUrl = SysConfigUtil.getValue("login.serverAcrossDay.url");
		
		//初始化充值返利规则 -固定的
		ParamConfig.rechargeRetRuleMap.put(1, 1f);
		ParamConfig.rechargeRetRuleMap.put(2, 0.2f);
		ParamConfig.rechargeRetRuleMap.put(3, 0.25f);
		ParamConfig.rechargeRetRuleMap.put(4, 0.3f);
		ParamConfig.rechargeRetRuleMap.put(5, 0.35f);
		ParamConfig.rechargeRetRuleMap.put(6, 0.4f);
		ParamConfig.rechargeRetRuleMap.put(7, 0.5f);
		
//		Init Union Map
		List<InstUnion> instUnionList = getInstUnionDAL().getList("", 0);
		for (InstUnion instUnion : instUnionList) {
			ParamConfig.unionMap.put(instUnion.getId(), instUnion.getName());
		}
		
		//玩家联盟关系表
		List<InstUnionMember> instUnionMemberList = getInstUnionMemberDAL().getList("", 0);
		for (InstUnionMember instUnionMember : instUnionMemberList) {
			ParamConfig.unionPlayer.put(instUnionMember.getInstPlayerId(), instUnionMember.getInstUnionId());
		}
		
		//全民福利-总购买基金人数
		List<InstPlayerBigTable> instPlayerBigTableList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '"+StaticBigTable.sumBuyFundNum+"'", 0);
		if (instPlayerBigTableList.size() <= 0) {
			InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
			instPlayerBigTable.setInstPlayerId(0);
			instPlayerBigTable.setProperties(StaticBigTable.sumBuyFundNum);
			instPlayerBigTable.setValue1("0");
			getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
		} else {
			
			//这个地方有点乱，购买开服基金的人数有两个标准点了，启动游戏的时候以Inst_Activity为准, 游戏的时候以Inst_Player_BigTable为准，也不能这么说，
			//因为运营有修改购买基金人数的需求,最后王力决定按数值大的来初始化
			InstPlayerBigTable instPlayerBigTable = instPlayerBigTableList.get(0);
			int fundActivityNum = getInstActivityDAL().getCount("activityId = " + StaticActivity.fund);//活动表里购买基金数
			int  fundBigTableNum = Integer.valueOf(instPlayerBigTableList.get(0).getValue1());//bigtable表里购买基金数
			if (fundActivityNum > fundBigTableNum) {//如果活动表里购买的基金数 大于 bigtable里边的，以活动表为准，同时更新bigtable
				ParamConfig.sumBuyFundNum = fundActivityNum;
				instPlayerBigTable.setValue1(fundActivityNum + "");
				getInstPlayerBigTableDAL().update(instPlayerBigTable, 0);
			} else {
				ParamConfig.sumBuyFundNum = fundBigTableNum;
			}
		}
		
		//初始化所有玩家昵称
		List<InstPlayer> instPlayerList = getInstPlayerDAL().getList("", 0);
		for (InstPlayer obj : instPlayerList) {
			ParamConfig.playerNameMap.put(obj.getId(), obj.getName());
		}

		//记录并获取开服时间
		List<InstPlayerBigTable> openServerTimeList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '"+StaticBigTable.openServerTime+"'", 0);
		if (openServerTimeList.size() <= 0) {
			String currTime = DateUtil.getCurrTime();
			InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
			instPlayerBigTable.setInstPlayerId(0);
			instPlayerBigTable.setProperties(StaticBigTable.openServerTime);
			instPlayerBigTable.setValue1(currTime);
			getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
			
			ParamConfig.openServerTime = currTime;
		} else {
			ParamConfig.openServerTime = openServerTimeList.get(0).getValue1();
		}
		
		//初始化 允许的最大在线人数
		List<InstPlayerBigTable> maxRegeditNumList = getInstPlayerBigTableDAL().getList("instPlayerId = 0 and properties = '"+StaticBigTable.maxRegeditNum+"'", 0);
		if (maxRegeditNumList.size() <= 0) {
			InstPlayerBigTable instPlayerBigTable = new InstPlayerBigTable();
			instPlayerBigTable.setInstPlayerId(0);
			instPlayerBigTable.setProperties(StaticBigTable.maxRegeditNum);
			instPlayerBigTable.setValue1(ParamConfig.canAllowMaxRegeditNum + "");
			getInstPlayerBigTableDAL().add(instPlayerBigTable, 0);
		} else {
			ParamConfig.canAllowMaxRegeditNum = ConvertUtil.toInt(maxRegeditNumList.get(0).getValue1());
		}
		
	}
	
	/**
	 * 加载logback日志文件
	 * @author mp
	 * @date 2015-7-30 下午8:28:35
	 * @Description
	 */
	public static void loadLogBack () {
        LoggerContext lc = (LoggerContext)LoggerFactory.getILoggerFactory();
        JoranConfigurator configurator = new JoranConfigurator();
        configurator.setContext(lc);
        lc.reset();
        try {
            configurator.doConfigure(System.getProperty("user.dir") + "/config/logback.xml");
       } catch (JoranException e) {
            e.printStackTrace();
        }
        StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
	}
	
	/**
	 * 插入实例表
	 * @author mp
	 * @date 2015-7-29 下午3:51:15
	 * @throws Exception
	 * @Description
	 */
	public static void insertInstTable () throws Exception{
		if(FileUtil.isExsits(System.getProperty("user.dir") + "/config/inst.sql")) {
			if (!isHaveInstTable()) {
				System.out.println("--- start insert inst table ---");
				MySqlImportAndExportUtil.importSql(JdbcConfigUtil.getProp());
				TimeUnit.SECONDS.sleep(Integer.parseInt(SysConfigUtil.getValue("insttable.seconds")));
				System.out.println("--- insert inst table end ---");
			}
		}
	}
	
	/**
	 * 获取连接
	 * @author mp
	 * @date 2015-7-31 下午4:16:36
	 * @return
	 * @throws Exception
	 * @Description
	 */
	private static Connection getMySQLConnection() throws Exception {
		String dbUri = JdbcConfigUtil.getValue("jdbc.jdbcUrl");
		String user = JdbcConfigUtil.getValue("jdbc.user");
		String pass = JdbcConfigUtil.getValue("jdbc.password");
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection(dbUri, user, pass);
		return conn;
	}
	
	/**
	 * 是否存在实例表
	 * @author mp
	 * @date 2015-7-31 下午4:32:31
	 * @return
	 * @throws Exception
	 * @Description
	 */
	private static boolean isHaveInstTable() throws Exception {
		boolean flag = true;
		ResultSet rs = null;
		Statement statement = null;
		Connection connection = null;
		
		try {
			String dbUrl = JdbcConfigUtil.getValue("jdbc.jdbcUrl");
			String dbStr = dbUrl.substring(dbUrl.indexOf("//") + 2, dbUrl.indexOf("?"));
			String dbName = dbStr.substring(dbStr.indexOf("/") + 1, dbStr.length());
			String sql = "select count(*) as count from information_schema.tables where table_schema='"+ dbName +"' and table_name like '%Inst%'";
			connection = getMySQLConnection();
			statement = connection.createStatement();
			rs = statement.executeQuery(sql);
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("count");
			}
			if (count <= 0) {
				flag = false;
			} else {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
		return flag;
	}
}
