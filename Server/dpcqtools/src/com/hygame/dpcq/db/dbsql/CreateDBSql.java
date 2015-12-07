package com.hygame.dpcq.db.dbsql;

import java.sql.SQLException;
import java.sql.Statement;

import com.hygame.dpcq.db.conn.DBConn;



public class CreateDBSql {
	private static void DBSql()
	{
		//系统相关表


		String createuser =
				" CREATE TABLE user ("+
						"id  INTEGER PRIMARY KEY ,"+
						"username varchar(20) ,"+
						"password varchar(20) ,"+
						"name varchar(20) ,"+
						"ip varchar(20) ,"+
						"competenceid int(11) ,"+
						"remark varchar(50) "+
						") " ; 
		String createuser_comsql = 
				"CREATE TABLE user_com ("+
						"id INTEGER PRIMARY KEY,"+
						"userid int(11) ,"+
						"competenceid int(11),"+
						"type int(11)"+
						")";
		String creatmenucom =
				"CREATE TABLE menucom ("+
						"id INTEGER PRIMARY KEY,"+
						"name varchar(20),"+
						"url varchar(50)"+
						")";

		String creatmodulecom =
				"CREATE TABLE modulecom ("+
						"id INTEGER PRIMARY KEY,"+
						"name varchar(20)"+
						") ";
		String creatfilecom = 
				"CREATE TABLE filecom("+
						"id INTEGER PRIMARY KEY,"+
						"name varchar(20) ,"+
						"filename varchar(20) ,"+
						"fileurl varchar(50)"+
						") ";
		String creatserver = 
				"CREATE TABLE server("+
						"id INTEGER PRIMARY KEY,"+
						"servername varchar(20) ,"+
						"serverip varchar(20) ,"+
						"serverport INTEGER ,"+
						"dbname varchar(20) ,"+
						"dbpassword varchar(20) ,"+
						"dbip varchar(20) ,"+
						"dbport INTEGER"+
						") ";

		//GM 工具相关表

		String creatTableType = 
				"CREATE TABLE `TableType` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatGoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id` ,`typeid` )"+
						")";
		
		/*String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		
		String creatgoods = 
				"CREATE TABLE `goods` ("+
						"`id` int(11) NOT NULL  ,"+
						"`name` varchar(50) DEFAULT NULL ,"+
						"`typeid` int(11) DEFAULT NULL ,"+
						"PRIMARY KEY (`id`)"+
						")";
		*/
		


		//String test = "create table user (userid INTEGER PRIMARY KEY, username text)";
		try {
			Statement stat = DBConn.getStat();
			stat.executeUpdate("drop table if exists user;");
			stat.executeUpdate(createuser);

			stat.executeUpdate("drop table if exists user_com;");
			stat.executeUpdate(createuser_comsql);

			stat.executeUpdate("drop table if exists menucom;");
			stat.executeUpdate(creatmenucom);

			stat.executeUpdate("drop table if exists modulecom;");
			stat.executeUpdate(creatmodulecom);

			stat.executeUpdate("drop table if exists filecom;");
			stat.executeUpdate(creatfilecom);

			stat.executeUpdate("drop table if exists server;");
			stat.executeUpdate(creatserver);

			//gm工具内容数据库
			
			stat.executeUpdate("drop table if exists TableType;");
			stat.executeUpdate(creatTableType);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatGoods);

			/*stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);

			stat.executeUpdate("drop table if exists goods;");
			stat.executeUpdate(creatgoods);
*/
			
			
			System.out.println("数据库创建完成");

			stat.executeUpdate("INSERT INTO `menucom` VALUES ('1', 'wjzh', null)");
			stat.executeUpdate("INSERT INTO `menucom` VALUES ('2', 'yxhd', null)");
			stat.executeUpdate("INSERT INTO `menucom` VALUES ('3', 'xgpz', null)");
			stat.executeUpdate("INSERT INTO `menucom` VALUES ('4', 'fqpz', null)");
			stat.executeUpdate("INSERT INTO `menucom` VALUES ('5', 'sjxg', null)");
			stat.executeUpdate("INSERT INTO `menucom` VALUES ('6', 'pzxg', null)");
			stat.executeUpdate("INSERT INTO `menucom` VALUES ('7', 'zhgl', null)");
			stat.executeUpdate("INSERT INTO `menucom` VALUES ('8', 'qxgl', null)");
			System.out.println("添加menucom表数据成功");

			stat.executeUpdate("INSERT INTO user VALUES (null, 'admin', 'admin', 'admin', null, null, null);");
			System.out.println("添加admin帐号成功");


			//GM工具数据添加
			//TableType
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('1', '丹药字典表')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('2', '物品字典表')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('3', '玩家基本属性字典表')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('4', '卡牌基本属性字典表')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('5', '战斗属性')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('6', '装备字典表')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('7', '卡牌字典表')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('8', '命宫')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('9', '魂魄')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('10', '碎片字典表')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('11', '丹材')");
			stat.executeUpdate("INSERT INTO `TableType` VALUES ('12', '手动技能字典表')");

			//goods
			
			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '萧宁', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '加列奥', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '柳席', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '穆力', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '沙罗', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '雪魅', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '琳菲', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '葛叶', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '弗兰克', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('10', '奥拓', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('11', '墨承', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '白山', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '木辰', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '白程', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '叶欣蓝', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', '姚盛', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', '木战', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '莫崖', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '萧媚', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '穆蛇', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '柳翎', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '曹单', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '月媚', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '墨巴斯', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('25', '花蛇儿', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('26', '夭夜', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('27', '云棱', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('28', '韩月', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('29', '炎利', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('30', '蜈崖', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('31', '韩雪', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('32', '洪辰', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('33', '宋清', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('34', '辰闲', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('35', '翎泉', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('36', '萧战', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('37', '萧鼎', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('38', '萧玉', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('39', '纳兰桀', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('40', '米特尔', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('41', '范凌', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('42', '火稚', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('43', '琥嘉', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('44', '金老', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('45', '银老', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('46', '范痨', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('47', '凌影', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('48', '萧厉', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('49', '法犸', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('50', '小公主', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('51', '雅妃', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('52', '若琳导师', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('53', '叶重', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('54', '冰符', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('55', '冰元', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('56', '曹颖', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('57', '花锦', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('58', '易尘', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('59', '鹜护法', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('60', '唐火儿', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('61', '火炫', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('62', '药灵', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('63', '魂崖', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('64', '魂厉', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('65', '吴昊', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('66', '柳擎', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('67', '熊战', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('68', '加刑天', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('69', '云山', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('70', '丹王古河', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('71', '琥乾', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('72', '韩枫', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('73', '雁落天', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('74', '蝎毕岩', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('75', '沈云', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('76', '费天', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('77', '金谷', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('78', '妖花邪君', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('79', '辰天南', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('80', '九凤', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('81', '慕青鸾', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('82', '洪天啸', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('83', '古华', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('84', '苏千', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('85', '古刑', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('86', '古妖', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('87', '药万归', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('88', '萧炎', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('89', '小医仙', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('90', '青鳞', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('91', '云韵', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('92', '林修崖', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('93', '林焱', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('94', '海波东', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('95', '纳兰嫣然', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('96', '古青阳', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('97', '凤清儿', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('98', '魂风', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('99', '魂玉', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('100', '莫天行', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('101', '鹰山老人', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('102', '铁剑尊者', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('103', '金石', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('104', '雷尊者', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('105', '剑尊者', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('106', '黄泉尊者', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('107', '青海尊者', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('108', '妖天啸', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('109', '邙天尺', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('110', '曜天火', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('111', '地魔老鬼', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('112', '风尊者', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('113', '天蛇', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('114', '唐震', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('115', '冰尊者', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('116', '玄衣', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('117', '天雷子', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('118', '玄空子', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('119', '丘陵', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('120', '凰天', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('121', '凰轩', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('122', '黑擎', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('123', '西龙王', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('124', '南龙王', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('125', '北龙王', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('126', '古道', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('127', '通玄长老', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('128', '古烈', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('129', '药星极', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('130', '二天尊', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('131', '九天尊', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('132', '摘星老鬼', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('133', '慕骨老人', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('134', '火云老祖', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('135', '药老', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('136', '萧薰儿', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('137', '萧晨', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('138', '萧玄', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('139', '紫妍', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('140', '丹塔长老', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('141', '天冥老妖', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('142', '妖暝', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('143', '烛离', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('144', '美杜莎', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('145', '烛坤', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('146', '古元', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('147', '炎烬', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('148', '魂灭生', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('149', '副魂殿主', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('150', '魂天帝', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('151', '虚无吞炎', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('152', '魂虚子', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('153', '魂千陌', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('500', '幽海蛟兽', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('501', '紫晶翼狮王', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('502', '双头火灵蛇', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('503', '雪魔天猿', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('504', '冰霜独角狼', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('505', '黑焰紫云雕', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('506', '云岚宗女弟子', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('507', '云岚宗男弟子', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('508', '佣兵', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('509', '喽啰', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('510', '毒师', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('511', '蛇人小兵', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('512', '加列怒', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('513', '萧家弟子', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('514', '战士', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('515', '罗布', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('516', '紫晶翼狮幼兽', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('517', '甘穆', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('518', '苓儿', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('519', '卡岗', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('520', '药师', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('521', '佣兵头头', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('522', '女性蛇人', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('523', '男女蛇人', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('524', '墨冉', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('525', '绿蛮', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('526', '白牙', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('527', '蒙力', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('528', '薛崩', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('529', '陆牧', '7')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('530', '赫蒙', '7')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '萧宁魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '加列奥魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '柳席魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '穆力魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '沙罗魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '雪魅魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '琳菲魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '葛叶魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '弗兰克魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('10', '奥拓魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('11', '墨承魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '白山魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '木辰魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '白程魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '叶欣蓝魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', '姚盛魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', '木战魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '莫崖魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '萧媚魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '穆蛇魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '柳翎魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '曹单魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '月媚魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '墨巴斯魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('25', '花蛇儿魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('26', '夭夜魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('27', '云棱魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('28', '韩月魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('29', '炎利魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('30', '蜈崖魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('31', '韩雪魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('32', '洪辰魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('33', '宋清魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('34', '辰闲魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('35', '翎泉魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('36', '萧战魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('37', '萧鼎魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('38', '萧玉魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('39', '纳兰桀魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('40', '米特尔魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('41', '范凌魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('42', '火稚魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('43', '琥嘉魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('44', '金老魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('45', '银老魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('46', '范痨魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('47', '凌影魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('48', '萧厉魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('49', '法犸魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('50', '小公主魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('51', '雅妃魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('52', '若琳导师魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('53', '叶重魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('54', '冰符魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('55', '冰元魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('56', '曹颖魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('57', '花锦魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('58', '易尘魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('59', '鹜护法魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('60', '唐火儿魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('61', '火炫魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('62', '药灵魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('63', '魂崖魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('64', '魂厉魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('65', '吴昊魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('66', '柳擎魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('67', '熊战魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('68', '加刑天魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('69', '云山魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('70', '丹王古河魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('71', '琥乾魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('72', '韩枫魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('73', '雁落天魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('74', '蝎毕岩魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('75', '沈云魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('76', '费天魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('77', '金谷魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('78', '妖花邪君魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('79', '辰天南魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('80', '九凤魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('81', '慕青鸾魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('82', '洪天啸魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('83', '古华魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('84', '苏千魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('85', '古刑魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('86', '古妖魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('87', '药万归魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('88', '萧炎魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('89', '小医仙魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('90', '青鳞魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('91', '云韵魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('92', '林修崖魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('93', '林焱魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('94', '海波东魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('95', '纳兰嫣然魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('96', '古青阳魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('97', '凤清儿魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('98', '魂风魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('99', '魂玉魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('100', '莫天行魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('101', '鹰山老人魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('102', '铁剑尊者魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('103', '金石魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('104', '雷尊者魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('105', '剑尊者魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('106', '黄泉尊者魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('107', '青海尊者魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('108', '妖天啸魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('109', '邙天尺魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('110', '曜天火魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('111', '地魔老鬼魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('112', '风尊者魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('113', '天蛇魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('114', '唐震魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('115', '冰尊者魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('116', '玄衣魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('117', '天雷子魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('118', '玄空子魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('119', '丘陵魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('120', '凰天魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('121', '凰轩魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('122', '黑擎魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('123', '西龙王魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('124', '南龙王魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('125', '北龙王魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('126', '古道魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('127', '通玄长老魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('128', '古烈魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('129', '药星极魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('130', '二天尊魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('131', '九天尊魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('132', '摘星老鬼魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('133', '慕骨老人魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('134', '火云老祖魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('135', '药老魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('136', '萧薰儿魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('137', '萧晨魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('138', '萧玄魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('139', '紫妍魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('140', '丹塔长老魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('141', '天冥老妖魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('142', '妖暝魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('143', '烛离魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('144', '美杜莎魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('145', '烛坤魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('146', '古元魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('147', '炎烬魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('148', '魂灭生魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('149', '副魂殿主魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('150', '魂天帝魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('151', '虚无吞炎魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('152', '魂虚子魂魄', '9')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('153', '魂千陌魂魄', '9')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '等级', '4')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '具体称号Id', '4')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '经验', '4')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '潜力', '4')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '玄武诀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '玄武诀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '玄武诀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '狮山裂碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '狮山裂碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '狮山裂碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '七残诀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '七残诀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '七残诀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('10', '天魔功碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('11', '天魔功碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '天魔功碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '断兵诀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '断兵诀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '断兵诀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', '天演策碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', '天演策碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '天演策碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '地阙金章碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '地阙金章碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '地阙金章碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '地阙金章碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '太阴鬼箓碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '太阴鬼箓碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('25', '太阴鬼箓碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('26', '太阴鬼箓碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('27', '极武天书碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('28', '极武天书碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('29', '极武天书碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('30', '极武天书碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('31', '天魔秘籍碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('32', '天魔秘籍碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('33', '天魔秘籍碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('34', '天魔秘籍碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('35', '七劫龙诀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('36', '七劫龙诀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('37', '七劫龙诀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('38', '七劫龙诀碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('39', '逆天绝典碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('40', '逆天绝典碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('41', '逆天绝典碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('42', '逆天绝典碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('43', '盘古击碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('44', '盘古击碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('45', '盘古击碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('46', '盘古击碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('47', '盘古击碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('48', '盘古击碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('49', '蛮荒诀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('50', '蛮荒诀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('51', '蛮荒诀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('52', '蛮荒诀碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('53', '蛮荒诀碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('54', '蛮荒诀碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('55', '伏魔诀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('56', '伏魔诀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('57', '伏魔诀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('58', '伏魔诀碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('59', '伏魔诀碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('60', '伏魔诀碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('61', '将军令碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('62', '将军令碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('63', '将军令碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('64', '将军令碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('65', '将军令碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('66', '将军令碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('67', '灭世九诀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('68', '灭世九诀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('69', '灭世九诀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('70', '灭世九诀碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('71', '灭世九诀碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('72', '灭世九诀碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('73', '黑绝魔经碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('74', '黑绝魔经碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('75', '黑绝魔经碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('76', '黑绝魔经碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('77', '黑绝魔经碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('78', '黑绝魔经碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('79', '噬血甲碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('80', '噬血甲碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('81', '噬血甲碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('82', '噬血甲碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('83', '噬血甲碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('84', '噬血甲碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('85', '风雷祭碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('86', '风雷祭碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('87', '风雷祭碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('88', '风雷祭碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('89', '风雷祭碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('90', '风雷祭碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('91', '丹融天碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('92', '丹融天碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('93', '丹融天碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('94', '丹融天碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('95', '丹融天碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('96', '丹融天碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('97', '黑极崩劲碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('98', '黑极崩劲碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('99', '黑极崩劲碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('100', '黑极崩劲碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('101', '黑极崩劲碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('102', '黑极崩劲碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('103', '毁灭之印碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('104', '毁灭之印碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('105', '毁灭之印碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('106', '毁灭之印碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('107', '毁灭之印碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('108', '毁灭之印碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('109', '空间绞杀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('110', '空间绞杀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('111', '空间绞杀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('112', '空间绞杀碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('113', '空间绞杀碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('114', '空间绞杀碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('115', '焰分噬浪碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('116', '焰分噬浪碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('117', '焰分噬浪碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('118', '焰分噬浪碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('119', '焰分噬浪碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('120', '焰分噬浪碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('121', '玄冰旋杀碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('122', '玄冰旋杀碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('123', '玄冰旋杀碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('124', '玄冰旋杀碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('125', '玄冰旋杀碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('126', '玄冰旋杀碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('127', '金帝焚天碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('128', '金帝焚天碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('129', '金帝焚天碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('130', '金帝焚天碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('131', '金帝焚天碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('132', '金帝焚天碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('133', '佛怒火莲碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('134', '佛怒火莲碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('135', '佛怒火莲碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('136', '佛怒火莲碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('137', '佛怒火莲碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('138', '佛怒火莲碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('139', '魂之葬礼碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('140', '魂之葬礼碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('141', '魂之葬礼碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('142', '魂之葬礼碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('143', '魂之葬礼碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('144', '魂之葬礼碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('145', '九幽冥手碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('146', '九幽冥手碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('147', '九幽冥手碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('148', '九幽冥手碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('149', '九幽冥手碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('150', '九幽冥手碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('151', '水曼陀罗碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('152', '水曼陀罗碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('153', '水曼陀罗碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('154', '水曼陀罗碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('155', '水曼陀罗碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('156', '水曼陀罗碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('157', '天罗炼火碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('158', '天罗炼火碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('159', '天罗炼火碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('160', '天罗炼火碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('161', '天罗炼火碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('162', '天罗炼火碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('163', '雨蚀苍穹碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('164', '雨蚀苍穹碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('165', '雨蚀苍穹碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('166', '雨蚀苍穹碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('167', '雨蚀苍穹碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('168', '雨蚀苍穹碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('169', '大噬血术碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('170', '大噬血术碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('171', '大噬血术碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('172', '大噬血术碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('173', '大噬血术碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('174', '大噬血术碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('175', '千幻毒泉碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('176', '千幻毒泉碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('177', '千幻毒泉碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('178', '千幻毒泉碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('179', '千幻毒泉碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('180', '千幻毒泉碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('181', '万影缚魂碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('182', '万影缚魂碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('183', '万影缚魂碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('184', '万影缚魂碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('185', '万影缚魂碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('186', '万影缚魂碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('187', '妖瞳控体碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('188', '妖瞳控体碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('189', '妖瞳控体碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('190', '妖瞳控体碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('191', '妖瞳控体碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('192', '妖瞳控体碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('193', '紫晶封印碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('194', '紫晶封印碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('195', '紫晶封印碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('196', '紫晶封印碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('197', '紫晶封印碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('198', '紫晶封印碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('199', '雷动八荒碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('200', '雷动八荒碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('201', '雷动八荒碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('202', '雷动八荒碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('203', '雷动八荒碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('204', '雷动八荒碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('205', '化血碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('206', '化血碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('207', '化血碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('208', '化血碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('209', '化血碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('210', '化血碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('211', '升灵碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('212', '升灵碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('213', '升灵碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('214', '升灵碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('215', '升灵碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('216', '升灵碎片六', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('217', '回天碎片一', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('218', '回天碎片二', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('219', '回天碎片三', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('220', '回天碎片四', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('221', '回天碎片五', '10')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('222', '回天碎片六', '10')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '禄存', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '文曲', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '天机', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '天魁', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '风府', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '天权', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '廉贞', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '天府', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '七杀', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('10', '破军', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('11', '武曲', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '地劫', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '幽冥', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '巨阙', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '红鸾', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', '天喜', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', '贪狼', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '天刑', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '太阴', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '太阳', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '地牢', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '太渊', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '陀罗', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '地空', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('25', '紫微', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('26', '文昌', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('27', '天梁', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('28', '天相', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('29', '太冲', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('30', '天璇', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('31', '天姚', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('32', '天马', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('33', '左辅', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('34', '右弼', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('35', '巨门', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('36', '天同', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('37', '天枢', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('38', '玉衡', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('39', '擎羊', '8')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('40', '天钺', '8')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '玄重尺', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '破气连弩', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '神火弓', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '穿魂箭', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '寒锋剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '雁翎剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '屠龙剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '骨皇刀', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '乌钢锤', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('10', '昊雷锤', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('11', '锁魂链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '寒冰魔枪', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '紫月玄扇', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '天祭战斧', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '地煞魂拐', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', '九合蛇鞭', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', '龙纹锏', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '鸳鸯钺', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '乾坤天斧', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '神羽扇', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '月牙戟枪', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '金丝软鞭', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '炼狱魔刀', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '斩仙剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('25', '七星龙拐', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('26', '紫金摩杵', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('27', '惊雷弓', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('28', '流光古剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('29', '烈焰蛇枪', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('30', '乾坤刀', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('31', '流云匕首', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('32', '噬魂鞭', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('33', '三叉鬼刀', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('34', '盘龙枪', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('35', '青锋剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('36', '血战斧', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('37', '裂山枪', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('38', '游龙宝剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('39', '青寒刃', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('40', '伪帝剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('41', '斩月刀', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('42', '虬龙鞭', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('43', '云纹剑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('44', '火龙枪', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('45', '海之心甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('46', '蚕丝甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('47', '雁翎金甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('48', '龙凰古甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('49', '修罗罡铠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('50', '幻冥皇铠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('51', '两仪玄甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('52', '连环宝甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('53', '天魔火甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('54', '怒神雷甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('55', '天玉战铠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('56', '落英神铠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('57', '龙鳞龟甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('58', '素银甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('59', '犀牛宝甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('60', '柳叶凤甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('61', '寒雪甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('62', '夜魔战甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('63', '金丝软甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('64', '锁子金甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('65', '幽海纳戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('66', '骨炎戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('67', '须弥戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('68', '盘龙戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('69', '如意戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('70', '兵之戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('71', '乾坤戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('72', '混沌戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('73', '永恒戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('74', '九龙戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('75', '吟龙之戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('76', '魂灵戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('77', '风吟戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('78', '藏天戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('79', '灵罗戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('80', '星空戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('81', '天鹰之戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('82', '黄泉纳戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('83', '青岚指环', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('84', '炙火戒', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('85', '九转晶链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('86', '秘魔灵珠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('87', '魂之灵玉', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('88', '幽鬼项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('89', '天机锁链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('90', '残殇之星', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('91', '海之泪', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('92', '青珑挂珠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('93', '流光项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('94', '雪魂丝链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('95', '魔狱项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('96', '龙吟项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('97', '定魂项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('98', '化魄珠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('99', '火纹项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('100', '玄金项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('101', '谜之项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('102', '紫晶坠子', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('103', '碧玉坠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('104', '血爪项链', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('105', '九天冕', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('106', '紫金冠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('107', '青炙头盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('108', '凌霄翼盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('109', '虹影面甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('110', '苍穹羽冠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('111', '幻星头巾', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('112', '万魂冠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('113', '紫芒头盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('114', '虹翼头巾', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('115', '圣灵头盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('116', '锈色之冠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('117', '灭焰巨盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('118', '霜凌面甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('119', '血皇之冠', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('120', '海神头盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('121', '鬼头盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('122', '百战盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('123', '铁龙面甲', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('124', '精铁盔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('125', '青莲座', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('126', '降魂幡', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('127', '妖凰伞', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('128', '古帝镜', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('129', '死寂门', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('130', '亡魂碑', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('131', '龙皇钟', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('132', '五轮火盘', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('133', '乾坤柱', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('134', '玄天笔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('135', '黑魔鼎', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('136', '离魄灯', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('137', '七星幡', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('138', '玲珑塔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('139', '锁龙环', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('140', '凤舞铃', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('141', '断魂符', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('142', '寒灵塔', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('143', '翻天印', '6')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('144', '定魂铃', '6')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '生命', '5')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '物攻', '5')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '法攻', '5')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '闪避', '5')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '暴击', '5')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '命中', '5')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '韧性', '5')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '物防', '5')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '法防', '5')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '焰分噬浪', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '玄冰旋杀', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '金帝焚天', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('10', '佛怒火莲', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('11', '魂之葬礼', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '九幽冥手', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '水曼陀罗', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '天罗炼火', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '雨蚀苍穹', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', '大噬血术', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', '千幻毒泉', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '万影缚魂', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '妖瞳控体', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '紫晶封印', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '雷动八荒', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '化血', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '升灵', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '回天', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('400', '噬血甲', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('401', '风雷祭', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('402', '丹融天', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('403', '黑极崩劲', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('404', '毁灭之印', '12')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('405', '空间绞杀', '12')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '一品生命丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '二品生命丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '三品生命丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '四品生命丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '五品生命丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '六品生命丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '一品物攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '二品物攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '三品物攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('10', '四品物攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('11', '五品物攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '六品物攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '一品法攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '二品法攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '三品法攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', '四品法攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', '五品法攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '六品法攻丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '一品物防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '二品物防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '三品物防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '四品物防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '五品物防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '六品物防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('25', '一品法防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('26', '二品法防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('27', '三品法防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('28', '四品法防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('29', '五品法防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('30', '六品法防丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('31', '低级经验丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('32', '中级经验丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('33', '高级经验丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('34', '低级潜力丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('35', '中级潜力丹', '1')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('36', '高级潜力丹', '1')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '黄莲精', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '血灵芝', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '凝血草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '活气果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '紫叶兰草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '洗骨花', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '罂粟花', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '寒血果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('9', '紫烟果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('10', '紫蓝叶', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('11', '腾龙花心', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '阴神花', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '火阳灵叶', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '雪玉骨参', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '墨叶莲', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', '聚灵草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', '三尾风叶', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '离土果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '清心三叶草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '佛心果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '吸灵树', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '青焰果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '黑天麻', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '速龙涎', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('25', '夜灵叶', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('26', '昙灵果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('27', '千灵草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('28', '三叶青芝', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('29', '木灵三针花', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('30', '赤炎果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('31', '冰灵焰草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('32', '回灵赤果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('33', '清体草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('34', '冰火融魂果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('35', '水灵莲子', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('36', '玉龙涎', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('37', '寒髓枝', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('38', '冰火龙须果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('39', '地心火芝', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('40', '青木仙藤', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('41', '极寒灵芝', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('42', '寿王浆', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('43', '魂婴果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('44', '紫灵塑体花', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('45', '地心魂髓', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('46', '凤火磷果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('47', '佛焰根', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('48', '青岚草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('49', '香烛草', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('50', '青莲果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('51', '蛇脱花', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('52', '天麻翡石精', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('53', '丹灵浆', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('54', '万年血灵参', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('55', '青岩木', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('56', '白灵参果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('57', '天翡果', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('58', '厚土芝', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('59', '火灵根', '11')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('60', '青冥果', '11')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '金币', '3')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '银币', '3')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '经验', '3')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '修为', '3')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '威望', '3')");

			stat.executeUpdate("INSERT INTO `goods` VALUES ('1', '打孔器', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('2', '菩提子', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('3', '洗练锁', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('4', '培髓根', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('5', '火莲子', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('6', '升仙草', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('7', '火晶', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('8', '洗练石', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('12', '魂源', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('13', '一阶生命魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('14', '二阶生命魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('15', '三阶生命魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('16', 'vip0级礼包', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('17', 'vip1级礼包', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('18', '四阶生命魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('19', '五阶生命魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('20', '六阶生命魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('21', '一阶物防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('22', '二阶物防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('23', '三阶物防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('24', '四阶物防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('25', '五阶物防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('26', '六阶物防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('27', '一阶法防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('28', '二阶法防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('29', '三阶法防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('30', '四阶法防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('31', '五阶法防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('32', '六阶法防魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('33', '一阶物攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('34', '二阶物攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('35', '三阶物攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('36', '四阶物攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('37', '五阶物攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('38', '六阶物攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('39', '一阶法攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('40', '二阶法攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('41', '三阶法攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('42', '四阶法攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('43', '五阶法攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('44', '六阶法攻魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('45', '一阶命中魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('46', '二阶命中魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('47', '三阶命中魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('48', '四阶命中魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('49', '五阶命中魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('50', '六阶命中魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('51', '一阶闪避魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('52', '二阶闪避魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('53', '三阶闪避魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('54', '四阶闪避魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('55', '五阶闪避魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('56', '六阶闪避魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('57', '一阶暴击魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('58', '二阶暴击魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('59', '三阶暴击魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('60', '四阶暴击魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('61', '五阶暴击魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('62', '六阶暴击魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('63', '一阶韧性魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('64', '二阶韧性魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('65', '三阶韧性魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('66', '四阶韧性魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('67', '五阶韧性魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('68', '六阶韧性魔核', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('71', '招募令', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('72', '和平牌', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('73', '金宝箱', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('74', '银宝箱', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('75', '铜宝箱', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('76', '金钥匙', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('77', '银钥匙', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('78', '铜钥匙', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('79', '耐力丹', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('80', '体力丹', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('81', '装备锁', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('82', '刷新令', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('83', '绿岩心石', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('84', '蓝岩心石', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('85', '紫岩心石', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('200', '玄重尺碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('201', '破气连弩碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('202', '神火弓碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('203', '穿魂箭碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('204', '寒锋剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('205', '雁翎剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('206', '屠龙剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('207', '骨皇刀碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('208', '乌钢锤碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('209', '昊雷锤碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('210', '锁魂链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('211', '寒冰魔枪碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('212', '紫月玄扇碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('213', '天祭战斧碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('214', '地煞魂拐碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('215', '九合蛇鞭碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('216', '龙纹锏碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('217', '鸳鸯钺碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('218', '乾坤天斧碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('219', '神羽扇碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('220', '月牙戟枪碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('221', '金丝软鞭碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('222', '炼狱魔刀碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('223', '斩仙剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('224', '七星龙拐碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('225', '紫金摩杵碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('226', '惊雷弓碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('227', '流光古剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('228', '烈焰蛇枪碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('229', '乾坤刀碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('230', '流云匕首碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('231', '噬魂鞭碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('232', '三叉鬼刀碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('233', '盘龙枪碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('234', '青锋剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('235', '血战斧碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('236', '裂山枪碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('237', '游龙宝剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('238', '青寒刃碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('239', '伪帝剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('240', '斩月刀碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('241', '虬龙鞭碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('242', '云纹剑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('243', '火龙枪碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('244', '海之心甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('245', '蚕丝甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('246', '雁翎金甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('247', '龙凰古甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('248', '修罗罡铠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('249', '幻冥皇铠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('250', '两仪玄甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('251', '连环宝甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('252', '天魔火甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('253', '怒神雷甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('254', '天玉战铠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('255', '落英神铠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('256', '龙鳞龟甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('257', '素银甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('258', '犀牛宝甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('259', '柳叶凤甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('260', '寒雪甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('261', '夜魔战甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('262', '金丝软甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('263', '锁子金甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('264', '幽海纳戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('265', '骨炎戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('266', '须弥戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('267', '盘龙戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('268', '如意戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('269', '兵之戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('270', '乾坤戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('271', '混沌戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('272', '永恒戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('273', '九龙戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('274', '吟龙之戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('275', '魂灵戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('276', '风吟戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('277', '藏天戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('278', '灵罗戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('279', '星空戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('280', '天鹰之戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('281', '黄泉纳戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('282', '青岚指环碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('283', '炙火戒碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('284', '九转晶链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('285', '秘魔灵珠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('286', '魂之灵玉碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('287', '幽鬼项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('288', '天机锁链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('289', '残殇之星碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('290', '海之泪碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('291', '青珑挂珠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('292', '流光项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('293', '雪魂丝链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('294', '魔狱项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('295', '龙吟项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('296', '定魂项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('297', '化魄珠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('298', '火纹项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('299', '玄金项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('300', '谜之项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('301', '紫晶坠子碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('302', '碧玉坠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('303', '血爪项链碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('304', '九天冕碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('305', '紫金冠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('306', '青炙头盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('307', '凌霄翼盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('308', '虹影面甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('309', '苍穹羽冠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('310', '幻星头巾碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('311', '万魂冠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('312', '紫芒头盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('313', '虹翼头巾碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('314', '圣灵头盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('315', '锈色之冠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('316', '灭焰巨盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('317', '霜凌面甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('318', '血皇之冠碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('319', '海神头盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('320', '鬼头盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('321', '百战盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('322', '铁龙面甲碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('323', '精铁盔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('324', '青莲座碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('325', '降魂幡碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('326', '妖凰伞碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('327', '古帝镜碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('328', '死寂门碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('329', '亡魂碑碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('330', '龙皇钟碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('331', '五轮火盘碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('332', '乾坤柱碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('333', '玄天笔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('334', '黑魔鼎碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('335', '离魄灯碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('336', '七星幡碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('337', '玲珑塔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('338', '锁龙环碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('339', '凤舞铃碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('340', '断魂符碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('341', '寒灵塔碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('342', '翻天印碎片', '2')");
			stat.executeUpdate("INSERT INTO `goods` VALUES ('343', '定魂铃碎片', '2')");


			System.out.println("GM 数据导完");
			stat.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接出错");
		} 
	}
	public static void main(String [] ager)
	{
		/*UserImp ui = new UserImp();
		User user = new User();
		user.setUsername("username");
		user.setPassword("password");
		user.setName("name");
		user.setIp("ip");
		int i = ui.findbyall().size();*/

		//	System.out.println(i);
		DBSql();
	}
}
