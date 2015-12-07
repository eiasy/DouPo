/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.102
Source Server Version : 50505
Source Host           : 192.168.1.102:3306
Source Database       : db_charge_doupo

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2015-10-21 16:29:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for idfa_active
-- ----------------------------
DROP TABLE IF EXISTS `idfa_active`;
CREATE TABLE `idfa_active` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `app_id` varchar(128) DEFAULT NULL COMMENT '应用编号',
  `channel_tag` varchar(64) DEFAULT NULL COMMENT '渠道标签',
  `idfa` varchar(255) DEFAULT 'idfa',
  `device_mac` varchar(255) DEFAULT NULL COMMENT 'mac',
  `device_imei` varchar(255) DEFAULT NULL COMMENT 'imei',
  `ip` varchar(64) DEFAULT NULL COMMENT 'ip',
  `add_time` varchar(255) DEFAULT NULL COMMENT '添加时间',
  `app_start` int(3) DEFAULT NULL COMMENT '启动数量',
  `app_start_time` datetime DEFAULT NULL COMMENT '首充启动时间',
  `role_create` int(3) DEFAULT NULL COMMENT '创建角色编号',
  `role_create_time` datetime DEFAULT NULL COMMENT '创建角色时间',
  `role_create_channel` varchar(255) DEFAULT NULL COMMENT '创建角色归属渠道',
  `role_user_id` varchar(255) DEFAULT NULL COMMENT '账号或openid',
  `descript` varchar(512) DEFAULT NULL COMMENT '描述',
  `state` tinyint(3) DEFAULT '0' COMMENT '状态',
  `device_udid` varchar(255) DEFAULT NULL COMMENT 'udid',
  `device_serial` varchar(128) DEFAULT NULL COMMENT '设备序列号',
  `device_ua` varchar(512) DEFAULT NULL COMMENT 'ua',
  `device_os` varchar(255) DEFAULT NULL COMMENT '操作系统',
  `device_os_version` varchar(64) DEFAULT NULL COMMENT '操作系统版本号',
  `channel_sub` varchar(64) DEFAULT NULL COMMENT '子渠道',
  `media` varchar(64) DEFAULT NULL COMMENT '媒介',
  `callback` varchar(255) DEFAULT NULL COMMENT '回调地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for idfa_event
-- ----------------------------
DROP TABLE IF EXISTS `idfa_event`;
CREATE TABLE `idfa_event` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `event_type` varchar(64) DEFAULT NULL,
  `event_tag` varchar(64) DEFAULT NULL,
  `app_id` varchar(128) DEFAULT NULL,
  `channel_tag` varchar(128) DEFAULT NULL COMMENT '渠道标签',
  `idfa` varchar(255) DEFAULT NULL,
  `device_mac` varchar(128) DEFAULT NULL COMMENT 'mac地址',
  `device_imei` varchar(255) DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `time_add` datetime DEFAULT NULL,
  `device_udid` varchar(255) DEFAULT NULL COMMENT '设备UDID',
  `device_serial` varchar(255) DEFAULT NULL COMMENT '设备序列号',
  `device_ua` varchar(512) DEFAULT NULL COMMENT '设备UA',
  `device_os` varchar(128) DEFAULT NULL COMMENT '设备操作系统',
  `device_os_version` varchar(64) DEFAULT NULL,
  `phone_code` varchar(64) DEFAULT NULL,
  `descript` varchar(512) DEFAULT NULL,
  `channel_sub` varchar(64) DEFAULT NULL,
  `media` varchar(64) DEFAULT NULL,
  `callback` varchar(255) DEFAULT NULL,
  `key_1` varchar(64) DEFAULT NULL,
  `value_1` varchar(255) DEFAULT NULL,
  `key_2` varchar(64) DEFAULT NULL,
  `value_2` varchar(255) DEFAULT NULL,
  `key_3` varchar(64) DEFAULT NULL,
  `value_3` varchar(255) DEFAULT NULL,
  `key_4` varchar(64) DEFAULT NULL,
  `value_4` varchar(255) DEFAULT NULL,
  `key_5` varchar(64) DEFAULT NULL,
  `value_5` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=417 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for Test
-- ----------------------------
DROP TABLE IF EXISTS `Test`;
CREATE TABLE `Test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zone_0_order
-- ----------------------------
DROP TABLE IF EXISTS `zone_0_order`;
CREATE TABLE `zone_0_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `gameid` int(11) DEFAULT NULL COMMENT '游戏编号（独服编号）',
  `serverid` int(11) DEFAULT NULL COMMENT '分区编号',
  `orderform` varchar(256) DEFAULT NULL COMMENT '自有订单号',
  `channelid` varchar(64) DEFAULT NULL COMMENT '渠道ID',
  `channelsub` varchar(128) DEFAULT NULL COMMENT '子渠道编号或包名',
  `accountid` int(11) DEFAULT NULL COMMENT '账号id（自有账号服生成）',
  `roleid` int(11) DEFAULT NULL COMMENT '角色编号',
  `rolename` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `rolelevel` smallint(6) DEFAULT NULL COMMENT '角色等级',
  `deviceos` varchar(1024) DEFAULT NULL COMMENT '设备操作系统',
  `itemid` int(11) DEFAULT NULL COMMENT '道具编号',
  `itemname` varchar(30) DEFAULT NULL COMMENT '道具名称',
  `itemprice` int(11) DEFAULT NULL COMMENT '道具价格',
  `itemcount` int(11) DEFAULT NULL COMMENT '购买数量',
  `status` tinyint(3) DEFAULT NULL COMMENT '状态：0未被付费，1已完成支付',
  `timecreate` datetime DEFAULT NULL COMMENT '订单创建时间',
  `timehandle` datetime DEFAULT NULL COMMENT '订单处理时间',
  `userid` varchar(256) DEFAULT NULL COMMENT '用户openid',
  `deviceimei` varchar(255) DEFAULT NULL COMMENT '设备imei',
  `device_serial` varchar(255) DEFAULT NULL COMMENT '设备序列号',
  `device_mac` varchar(255) DEFAULT NULL COMMENT '设备mac地址',
  `idfa` varchar(255) DEFAULT NULL COMMENT 'idfa',
  `handle_zone_id` int(11) DEFAULT '0' COMMENT '接收渠道回调的应用编号',
  `appId` int(11) DEFAULT '0' COMMENT '创建订单的服务器编号',
  PRIMARY KEY (`id`),
  KEY `index_orderform` (`orderform`(255))
) ENGINE=InnoDB AUTO_INCREMENT=15563 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zone_0_qq
-- ----------------------------
DROP TABLE IF EXISTS `zone_0_qq`;
CREATE TABLE `zone_0_qq` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `resultCode` int(11) DEFAULT NULL,
  `payChannel` int(11) DEFAULT NULL,
  `payState` int(11) DEFAULT NULL,
  `provideState` int(11) DEFAULT NULL,
  `saveNum` int(11) DEFAULT NULL,
  `extendInfo` varchar(512) DEFAULT NULL,
  `orderId` varchar(64) DEFAULT NULL,
  `remoteIp` varchar(64) DEFAULT NULL,
  `openid` varchar(255) DEFAULT NULL,
  `openkey` varchar(512) DEFAULT NULL,
  `payToken` varchar(512) DEFAULT NULL,
  `pf` varchar(512) DEFAULT NULL,
  `pfkey` varchar(255) DEFAULT NULL,
  `actionType` varchar(32) DEFAULT NULL,
  `cdata` varchar(512) DEFAULT NULL,
  `addTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `imei` varchar(255) DEFAULT NULL,
  `idfa` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `checkCount` int(11) DEFAULT NULL COMMENT '检查次数',
  `chargeType` varchar(32) DEFAULT NULL COMMENT '测试或充值',
  `appId` int(11) DEFAULT NULL COMMENT '创建记录的应用编号',
  `handleAppId` int(11) DEFAULT NULL COMMENT '处理该记录的应用编号',
  `zoneId` int(11) DEFAULT '0' COMMENT '分区编号',
  `channelOrderId` varchar(255) DEFAULT NULL COMMENT 'qq订单',
  PRIMARY KEY (`id`),
  KEY `index_order_id` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=143 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zone_0_store_receipt
-- ----------------------------
DROP TABLE IF EXISTS `zone_0_store_receipt`;
CREATE TABLE `zone_0_store_receipt` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `receipt` text COMMENT '票据',
  `status` int(11) DEFAULT '0' COMMENT '状态：0新票据，1已经完成支付验证的新票据，2充值服务器已经生成充值记录，其他为非法',
  `gameid` int(11) DEFAULT '0' COMMENT '游戏编号或独服编号',
  `serverid` int(11) DEFAULT '0' COMMENT '分区编号',
  `channelid` varchar(60) DEFAULT NULL COMMENT '渠道编号',
  `channelsub` varchar(128) DEFAULT NULL COMMENT '子渠道编号',
  `accountid` int(11) DEFAULT '0' COMMENT '账号ID（自有账号服务器生成）',
  `roleid` int(11) DEFAULT '0' COMMENT '角色编号',
  `rolelevel` smallint(6) DEFAULT '0' COMMENT '角色等级',
  `rolename` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `goodsid` int(11) DEFAULT '0' COMMENT '道具编号',
  `goodsname` varchar(30) DEFAULT NULL COMMENT '道具名称',
  `price` int(11) DEFAULT '0' COMMENT '单价',
  `count` int(11) DEFAULT '0' COMMENT '数量',
  `deviceos` varchar(128) DEFAULT NULL COMMENT '设备os',
  `userid` varchar(50) DEFAULT NULL COMMENT '用户账号（openid）',
  `atime` datetime DEFAULT NULL COMMENT '添加时间',
  `udate` datetime DEFAULT NULL COMMENT '更新时间',
  `orderform` varchar(128) DEFAULT NULL COMMENT '订单号',
  `proid` varchar(128) DEFAULT NULL COMMENT 'appstore后台商品ID',
  `deviceimei` varchar(255) DEFAULT NULL COMMENT 'imei',
  `devicemac` varchar(128) DEFAULT NULL COMMENT 'mac',
  `deviceserial` varchar(128) DEFAULT NULL COMMENT '设备序列号',
  `idfa` varchar(256) DEFAULT NULL COMMENT 'idfa',
  `receiptmd5` varchar(64) DEFAULT NULL COMMENT '票据的md5签名',
  `checkResult` text COMMENT '验证票据的结果',
  `chargeType` varchar(64) DEFAULT NULL COMMENT '充值类型',
  `appId` int(11) DEFAULT '0' COMMENT '接收票据的应用编号',
  PRIMARY KEY (`id`),
  KEY `index_md5` (`receiptmd5`)
) ENGINE=InnoDB AUTO_INCREMENT=2090 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zone_1_charge
-- ----------------------------
DROP TABLE IF EXISTS `zone_1_charge`;
CREATE TABLE `zone_1_charge` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(256) DEFAULT NULL COMMENT '自有订单',
  `gameid` int(11) DEFAULT '0' COMMENT '游戏编号（独服编号）',
  `serverid` int(11) DEFAULT '0' COMMENT '分区编号',
  `channelid` varchar(64) DEFAULT '0' COMMENT '渠道编号',
  `accountid` int(11) DEFAULT '0' COMMENT '自有数据库内账号ID',
  `roleid` int(11) DEFAULT '0' COMMENT '角色ID',
  `rolename` varchar(30) DEFAULT NULL COMMENT '角色昵称',
  `money` int(11) DEFAULT '0' COMMENT '人民币（分）',
  `getmoney` int(11) DEFAULT '0' COMMENT '获得的游戏币',
  `ctype` tinyint(3) DEFAULT '1' COMMENT '1：角色充值成功，2：gm补点，3：角色充值失败，4：角色充值异常',
  `state` tinyint(3) DEFAULT '0' COMMENT '1：游戏服未确认，2：游戏服已确认，3：腾讯未发货',
  `atime` datetime DEFAULT NULL COMMENT '添加时间',
  `dtime` datetime DEFAULT NULL COMMENT '处理时间',
  `note` varchar(1024) DEFAULT NULL COMMENT '信息描述',
  `orderform` varchar(255) DEFAULT NULL COMMENT '渠道订单（appstore充值记录该项为票据的md5签名值）',
  `proxy` varchar(20) DEFAULT NULL COMMENT '代理渠道',
  `proxychannel` varchar(20) DEFAULT NULL COMMENT '充值方式',
  `proxytime` datetime DEFAULT NULL COMMENT '渠道回调传过来的时间',
  `userid` varchar(256) DEFAULT NULL COMMENT 'openid',
  `channelsub` varchar(30) DEFAULT NULL COMMENT '子渠道',
  `rolelevel` smallint(6) DEFAULT '0' COMMENT '生成订单时角色等级',
  `goodsid` int(11) DEFAULT '0' COMMENT '商品ID',
  `goodsname` varchar(20) DEFAULT NULL COMMENT '商品名称',
  `goodscount` int(11) DEFAULT '0' COMMENT '购买数量',
  `deviceos` varchar(1024) DEFAULT NULL COMMENT '设备操作系统',
  `price` int(11) DEFAULT NULL COMMENT '单价',
  `deviceimei` varchar(255) DEFAULT NULL COMMENT 'imei',
  `device_serial` varchar(255) DEFAULT NULL COMMENT '设备序列号',
  `device_mac` varchar(255) DEFAULT NULL COMMENT '设备mac地址',
  `idfa` varchar(255) DEFAULT NULL COMMENT '设备idfa',
  PRIMARY KEY (`id`),
  KEY `orderid_index` (`orderid`(255))
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for zone_1_order
-- ----------------------------
DROP TABLE IF EXISTS `zone_1_order`;
CREATE TABLE `zone_1_order` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `gameid` int(11) DEFAULT NULL,
  `serverid` int(11) DEFAULT NULL,
  `orderform` varchar(256) DEFAULT NULL,
  `channelid` varchar(64) DEFAULT NULL,
  `channelsub` varchar(128) DEFAULT NULL,
  `accountid` int(11) DEFAULT NULL,
  `roleid` int(11) DEFAULT NULL,
  `rolename` varchar(30) DEFAULT NULL,
  `rolelevel` smallint(6) DEFAULT NULL,
  `deviceos` varchar(1024) DEFAULT NULL,
  `itemid` int(11) DEFAULT NULL,
  `itemname` varchar(30) DEFAULT NULL,
  `itemprice` int(11) DEFAULT NULL,
  `itemcount` int(11) DEFAULT NULL,
  `status` tinyint(3) DEFAULT NULL,
  `timecreate` datetime DEFAULT NULL,
  `timehandle` datetime DEFAULT NULL,
  `userid` varchar(256) DEFAULT NULL,
  `deviceimei` varchar(255) DEFAULT NULL,
  `device_serial` varchar(255) DEFAULT NULL,
  `device_mac` varchar(255) DEFAULT NULL,
  `idfa` varchar(255) DEFAULT NULL,
  `handle_zone_id` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `index_orderform` (`orderform`(255))
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8;
