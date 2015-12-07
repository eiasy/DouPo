/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.102
Source Server Version : 50505
Source Host           : 192.168.1.102:3306
Source Database       : data_center

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2015-10-21 16:31:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` varchar(64) DEFAULT NULL COMMENT '账号',
  `userName` varchar(64) DEFAULT NULL COMMENT '用户名',
  `userPwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `powers` varchar(255) DEFAULT NULL COMMENT '权限',
  `ipLimit` varchar(255) DEFAULT NULL COMMENT '限定IP',
  `sessionId` varchar(128) DEFAULT NULL,
  `overtime` bigint(11) DEFAULT '0',
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', 'admin', 'admin', '{\"1\":[1,3,5,6,7]}', null, '8df3b4aa61dd97eb120e7798ed650245', '1444544429658', '0');

-- ----------------------------
-- Table structure for admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu`;
CREATE TABLE `admin_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(64) DEFAULT NULL,
  `orderFactor` int(11) DEFAULT '0' COMMENT '菜单排序（升序）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_menu
-- ----------------------------
INSERT INTO `admin_menu` VALUES ('1', '客户工具', '0');

-- ----------------------------
-- Table structure for admin_menu_item
-- ----------------------------
DROP TABLE IF EXISTS `admin_menu_item`;
CREATE TABLE `admin_menu_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `itemName` varchar(64) DEFAULT NULL,
  `menuId` int(11) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `className` varchar(255) DEFAULT NULL,
  `orderFactor` int(11) DEFAULT '0' COMMENT '菜单向排序（升序）',
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_menu_item
-- ----------------------------
INSERT INTO `admin_menu_item` VALUES ('1', '玩家账号', '1', null, null, '1', '0');
INSERT INTO `admin_menu_item` VALUES ('2', '道具查询', '1', null, null, '2', '0');
INSERT INTO `admin_menu_item` VALUES ('3', '创建订单', '1', null, null, '3', '0');
INSERT INTO `admin_menu_item` VALUES ('4', '重置密码', '2', null, null, '0', '0');
INSERT INTO `admin_menu_item` VALUES ('5', '完成支付', '1', null, null, '4', '0');
INSERT INTO `admin_menu_item` VALUES ('6', '到帐记录', '1', null, null, '5', '0');
INSERT INTO `admin_menu_item` VALUES ('7', '分区配置', '1', null, null, '5', '0');

-- ----------------------------
-- Table structure for app_configs
-- ----------------------------
DROP TABLE IF EXISTS `app_configs`;
CREATE TABLE `app_configs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `projectId` int(11) DEFAULT '0' COMMENT '项目名称',
  `appId` int(11) DEFAULT '0' COMMENT '应用编号',
  `appName` varchar(64) DEFAULT NULL COMMENT '应用名称',
  `appMark` varchar(64) DEFAULT NULL COMMENT '应用标签',
  `appIp` varchar(64) DEFAULT NULL COMMENT '应用部署所在主机IP',
  `appPort1` int(11) DEFAULT '0' COMMENT '应用要启动端口1',
  `appPort1Desc` varchar(64) DEFAULT NULL COMMENT '端口1功能描述',
  `appPort2` int(11) DEFAULT '0' COMMENT '应用要启动端口2',
  `appPort2Desc` varchar(64) DEFAULT NULL COMMENT '端口2功能描述',
  `appPort3` int(11) DEFAULT '0' COMMENT '应用要启动端口3',
  `appPort3Desc` varchar(64) DEFAULT NULL COMMENT '端口3功能描述',
  `callbackUrl` varchar(255) DEFAULT NULL COMMENT '回调地址',
  `dbName` varchar(64) DEFAULT NULL COMMENT '主数据库名称',
  `dbIp` varchar(64) DEFAULT NULL COMMENT '数据库所在主机IP',
  `dbPort` int(11) DEFAULT '0' COMMENT '数据库连接端口',
  `dbUser` varchar(64) DEFAULT NULL COMMENT '数据库用户名',
  `dbPass` varchar(64) DEFAULT NULL COMMENT '数据库密码',
  `logLevel` varchar(32) DEFAULT NULL COMMENT '日志级别',
  `logSwitch` varchar(255) DEFAULT '0' COMMENT '日志开关',
  `configFile` varchar(255) DEFAULT NULL COMMENT '配置文件入口',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of app_configs
-- ----------------------------
INSERT INTO `app_configs` VALUES ('1', '10002', '1', '充值1区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '192.168.1.101', '3306', 'dpaiz', 'dppass', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('2', '10002', '2', '充值2区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('3', '10002', '3', '充值3区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('4', '10002', '4', '充值4区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('5', '10002', '5', '充值5区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('6', '10002', '6', '充值6区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('7', '10002', '7', '充值7区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('8', '10002', '8', '充值8区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('9', '10002', '9', '充值9区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('10', '10002', '10', '充值10区', 'charge', '192.168.1.101', '12005', '渠道回调端口', '0', null, '0', null, null, 'db_charge_doupo', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('21', '2', '20001', '斗气大陆', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq001', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('22', '2', '20002', '石墨废墟', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq002', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('23', '2', '20003', '青莲火山', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq003', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('24', '2', '20004', '死灵地穴', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq004', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('25', '2', '20005', '死灵树丛', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq005', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('26', '2', '20006', '净莲妖地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq006', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('27', '2', '20007', '香格里拉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq007', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('28', '2', '20008', '南原半岛', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq008', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('29', '2', '20009', '黑域平原', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq009', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('30', '2', '20010', '镇鬼森林', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq010', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('31', '2', '20011', '大岭沼泽', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq011', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('32', '2', '20012', '黄沙盆地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq012', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('33', '2', '20013', '黑山高地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq013', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('34', '2', '20014', '迦南天池', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq014', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('35', '2', '20015', '中州大陆', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq015', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('36', '2', '20016', '迦南学院', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq016', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('37', '2', '20017', '天目山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq017', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('38', '2', '20018', '风雷山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq018', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('39', '2', '20019', '炙火山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq019', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('40', '2', '20020', '亡魂山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq020', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('41', '3', '30001', '斗气大陆', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios001', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('42', '3', '30002', '石墨废墟', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios002', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('43', '3', '30003', '青莲火山', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios003', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('44', '3', '30004', '死灵地穴', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios004', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('45', '3', '30005', '死灵树丛', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios005', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('46', '3', '30006', '净莲妖地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios006', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('47', '3', '30007', '香格里拉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios007', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('48', '3', '30008', '南原半岛', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios008', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('49', '3', '30009', '黑域平原', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios009', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('50', '3', '30010', '镇鬼森林', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios010', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('51', '3', '30011', '大岭沼泽', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios011', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('52', '3', '30012', '黄沙盆地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios012', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('53', '3', '30013', '黑山高地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios013', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('54', '3', '30014', '迦南天池', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios014', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('55', '3', '30015', '中州大陆', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios015', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('56', '3', '30016', '迦南学院', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios016', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('57', '3', '30017', '天目山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios017', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('58', '3', '30018', '风雷山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios018', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('59', '3', '30019', '炙火山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios019', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('60', '3', '30020', '亡魂山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqios020', '10.10.119.170', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('61', '4', '40001', '斗气大陆', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy001', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('62', '4', '40002', '石墨废墟', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy002', '10.10.105.76', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('63', '4', '40003', '青莲火山', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy003', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('64', '4', '40004', '死灵地穴', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy004', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('65', '4', '40005', '死灵树丛', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy005', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('66', '4', '40006', '净莲妖地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy006', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('67', '4', '40007', '香格里拉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy007', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('68', '4', '40008', '南原半岛', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy008', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('69', '4', '40009', '黑域平原', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy009', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('70', '4', '40010', '镇鬼森林', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy010', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('71', '4', '40011', '大岭沼泽', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy011', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('72', '4', '40012', '黄沙盆地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy012', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('73', '4', '40013', '黑山高地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy013', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('74', '4', '40014', '迦南天池', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy014', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('75', '4', '40015', '中州大陆', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy015', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('76', '4', '40016', '迦南学院', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy016', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('77', '4', '40017', '天目山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy017', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('78', '4', '40018', '风雷山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy018', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('79', '4', '40019', '炙火山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy019', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('80', '4', '40020', '亡魂山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqzy020', '10.10.16.178', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('81', '2', '20021', '天星山脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq021', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('82', '2', '20022', '蛮荒古域', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq022', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('83', '2', '20023', '妖凰空间', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq023', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('84', '2', '20024', '冥蛇地脉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq024', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('85', '2', '20025', '纳兰嫣然', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq025', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('86', '2', '20026', '陀舍古帝', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq026', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('87', '2', '20027', '丹王古河', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq027', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('88', '2', '20028', '若琳导师', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq028', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('89', '2', '20029', '金银二老', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq029', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('90', '2', '20030', '鹰山老人aa', 'game', null, '0', null, '0', null, '0', null, null, 'dpcq030', '10.10.167.191', '3306', 'dpevent', 'DouPoEvent0919', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('91', '0', '20021', '天星山脉', 'game', null, '0', null, '0', null, '0', null, null, '', null, '0', null, null, null, '0', null);
INSERT INTO `app_configs` VALUES ('92', '5', '50001', '斗气大陆', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqqq001', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cdb_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('93', '5', '50002', '石墨废墟', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqqq002', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('94', '5', '50003', '青莲火山', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqqq003', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('95', '5', '50004', '死灵地穴', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqqq004', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('96', '5', '50005', '死灵树丛', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqqq005', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('97', '5', '50006', '净莲妖地', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqqq006', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('98', '5', '50007', '香格里拉', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqqq007', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('99', '5', '50008', '南原半岛', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqqq008', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('100', '5', '50009', '黑域平原', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqq009', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('101', '5', '50010', '镇鬼森林', 'game', null, '0', null, '0', null, '0', null, null, 'dpcqq010', '55f939052dfab.sh.cdb.myqcloud.com', '7716', 'cbd_outerroot', 'mh1E!2n4ZM!bP3j7', 'warn', '0', null);
INSERT INTO `app_configs` VALUES ('102', '2', '20001', '斗气大陆', null, null, '0', null, '0', null, '0', null, null, 'db100', 'dp.com', '3306', 'user', 'pass', null, '0', null);
INSERT INTO `app_configs` VALUES ('103', '2', '20099', '丹气凝灵', null, null, '0', null, '0', null, '0', null, null, 'db', 'dp.com', '3306', 'user', 'pass', null, '0', null);

-- ----------------------------
-- Table structure for event_account
-- ----------------------------
DROP TABLE IF EXISTS `event_account`;
CREATE TABLE `event_account` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `eventSource` varchar(64) DEFAULT NULL,
  `eventTag` varchar(64) DEFAULT NULL,
  `appTag` varchar(64) DEFAULT NULL,
  `platform` varchar(64) DEFAULT NULL,
  `serverTag` varchar(64) DEFAULT '0',
  `channelTag` varchar(128) DEFAULT NULL COMMENT '渠道标签',
  `channelSub` varchar(128) DEFAULT NULL,
  `accountId` int(11) DEFAULT '0',
  `userId` varchar(255) DEFAULT NULL,
  `roleId` int(11) DEFAULT '0',
  `roleName` varchar(32) DEFAULT NULL,
  `roleLevel` int(11) DEFAULT '0',
  `vipLevel` int(11) DEFAULT '0',
  `value1string` varchar(255) DEFAULT NULL,
  `value2string` varchar(255) DEFAULT NULL,
  `value3string` varchar(255) DEFAULT NULL,
  `value4string` varchar(255) DEFAULT NULL,
  `value5string` varchar(255) DEFAULT NULL,
  `value6string` varchar(255) DEFAULT NULL,
  `value7string` varchar(255) DEFAULT NULL,
  `value8string` varchar(255) DEFAULT NULL,
  `key1int` varchar(64) DEFAULT NULL,
  `value1int` int(11) DEFAULT '0',
  `key2int` varchar(64) DEFAULT NULL,
  `value2int` int(11) DEFAULT '0',
  `key3int` varchar(64) DEFAULT NULL,
  `value3int` int(11) DEFAULT NULL,
  `key1long` varchar(64) DEFAULT NULL,
  `value1long` bigint(11) DEFAULT '0',
  `key1double` varchar(64) DEFAULT NULL,
  `value1double` double DEFAULT '0',
  `timeAdd` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of event_account
-- ----------------------------

-- ----------------------------
-- Table structure for event_charge
-- ----------------------------
DROP TABLE IF EXISTS `event_charge`;
CREATE TABLE `event_charge` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `eventSource` varchar(64) DEFAULT NULL,
  `eventTag` varchar(64) DEFAULT NULL,
  `appTag` varchar(64) DEFAULT NULL,
  `platform` varchar(64) DEFAULT NULL,
  `serverTag` varchar(64) DEFAULT '0',
  `channelTag` varchar(128) DEFAULT NULL COMMENT '渠道标签',
  `channelSub` varchar(128) DEFAULT NULL,
  `accountId` int(11) DEFAULT '0',
  `userId` varchar(255) DEFAULT NULL,
  `roleId` int(11) DEFAULT '0',
  `roleName` varchar(32) DEFAULT NULL,
  `roleLevel` int(11) DEFAULT '0',
  `vipLevel` int(11) DEFAULT '0',
  `value1string` varchar(255) DEFAULT NULL,
  `value2string` varchar(255) DEFAULT NULL,
  `value3string` varchar(255) DEFAULT NULL,
  `value4string` varchar(255) DEFAULT NULL,
  `value5string` varchar(255) DEFAULT NULL,
  `value6string` varchar(255) DEFAULT NULL,
  `value7string` varchar(255) DEFAULT NULL,
  `value8string` varchar(255) DEFAULT NULL,
  `key1int` varchar(64) DEFAULT NULL,
  `value1int` int(11) DEFAULT '0',
  `key2int` varchar(64) DEFAULT NULL,
  `value2int` int(11) DEFAULT '0',
  `key3int` varchar(64) DEFAULT NULL,
  `value3int` int(11) DEFAULT NULL,
  `key1long` varchar(64) DEFAULT NULL,
  `value1long` bigint(11) DEFAULT '0',
  `key1double` varchar(64) DEFAULT NULL,
  `value1double` double DEFAULT '0',
  `timeAdd` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of event_charge
-- ----------------------------

-- ----------------------------
-- Table structure for event_default
-- ----------------------------
DROP TABLE IF EXISTS `event_default`;
CREATE TABLE `event_default` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `eventSource` varchar(64) DEFAULT NULL,
  `eventTag` varchar(64) DEFAULT NULL,
  `appTag` varchar(64) DEFAULT NULL,
  `platform` varchar(64) DEFAULT NULL,
  `serverTag` varchar(64) DEFAULT '0',
  `channelTag` varchar(128) DEFAULT NULL COMMENT '渠道标签',
  `channelSub` varchar(128) DEFAULT NULL,
  `accountId` int(11) DEFAULT '0',
  `userId` varchar(255) DEFAULT NULL,
  `roleId` int(11) DEFAULT '0',
  `roleName` varchar(32) DEFAULT NULL,
  `roleLevel` int(11) DEFAULT '0',
  `vipLevel` int(11) DEFAULT '0',
  `value1string` varchar(255) DEFAULT NULL,
  `value2string` varchar(255) DEFAULT NULL,
  `value3string` varchar(255) DEFAULT NULL,
  `value4string` varchar(255) DEFAULT NULL,
  `value5string` varchar(255) DEFAULT NULL,
  `value6string` varchar(255) DEFAULT NULL,
  `value7string` varchar(255) DEFAULT NULL,
  `value8string` varchar(255) DEFAULT NULL,
  `key1int` varchar(64) DEFAULT NULL,
  `value1int` int(11) DEFAULT '0',
  `key2int` varchar(64) DEFAULT NULL,
  `value2int` int(11) DEFAULT '0',
  `key3int` varchar(64) DEFAULT NULL,
  `value3int` int(11) DEFAULT NULL,
  `key1long` varchar(64) DEFAULT NULL,
  `value1long` bigint(11) DEFAULT '0',
  `key1double` varchar(64) DEFAULT NULL,
  `value1double` double DEFAULT '0',
  `timeAdd` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of event_default
-- ----------------------------

-- ----------------------------
-- Table structure for Test
-- ----------------------------
DROP TABLE IF EXISTS `Test`;
CREATE TABLE `Test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Test
-- ----------------------------
