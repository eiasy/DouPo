/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.36
Source Server Version : 50530
Source Host           : 192.168.1.36:3306
Source Database       : testin

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2015-10-12 10:33:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for Inst_Activity
-- ----------------------------

CREATE TABLE `Inst_Activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `activityId` int(11) DEFAULT NULL COMMENT '活动字典Id',
  `activityTime` varchar(1024) DEFAULT NULL COMMENT '活动开启时间   在开服基金活动中，此字段记录已领取的基金id并用分号分开  限时抢购表示已经购买的Id分号隔开 月卡的结束时间 星星兑换记录(id_次数)',
  `isForever` int(1) DEFAULT NULL COMMENT '是否永久 0-非永久 1-永久',
  `useNum` int(11) DEFAULT NULL COMMENT '活动使用次数 hjy-已刷新次数  限时抢购表示结束时间的秒数 月卡的类型 星星兑换消耗的星数',
  `spareOne` varchar(100) DEFAULT NULL COMMENT '备用字段1 月卡最近一次的领取时间',
  `spareTwo` varchar(100) DEFAULT NULL COMMENT '备用字段2',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`),
  KEY `activityId` (`activityId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动实例表（目前是涉及到拍卖行跟黑角域）';

-- ----------------------------
-- Records of Inst_Activity
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Activity_LevelBag
-- ----------------------------

CREATE TABLE `Inst_Activity_LevelBag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `end` varchar(100) DEFAULT NULL COMMENT '已经领取的等级礼包活动Id 分号隔开',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='等级礼包活动实例表';

-- ----------------------------
-- Records of Inst_Activity_LevelBag
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Activity_OnlineRewards
-- ----------------------------

CREATE TABLE `Inst_Activity_OnlineRewards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `onlineRewardsId` int(11) DEFAULT NULL COMMENT '在线奖励字典Id',
  `onlineTime` int(11) DEFAULT NULL COMMENT '在线倒计时毫秒数 0表示结束 ',
  `things` varchar(500) DEFAULT NULL COMMENT '奖励物品 tableTypeId_tableFieldId_tableValue',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='在线奖励实例表';

-- ----------------------------
-- Records of Inst_Activity_OnlineRewards
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Activity_OpenServiceBag
-- ----------------------------

CREATE TABLE `Inst_Activity_OpenServiceBag` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `day` int(11) DEFAULT NULL COMMENT '登录天数',
  `can` varchar(100) DEFAULT NULL COMMENT '可以领取的开服礼包活动Id 分号隔开',
  `end` varchar(100) DEFAULT NULL COMMENT '已经领取的开服礼包活动Id 分号隔开',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='开服礼包活动实例表';

-- ----------------------------
-- Records of Inst_Activity_OpenServiceBag
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Activity_SignIn
-- ----------------------------

CREATE TABLE `Inst_Activity_SignIn` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `type` int(1) DEFAULT NULL COMMENT '签到类型(1-普通签到 2-豪华签到)',
  `day` int(11) DEFAULT NULL COMMENT '签到天数 对于豪华签到为签到次数',
  `isTwo` int(1) DEFAULT NULL COMMENT '是否领取双倍奖励 0-全部领取 1-单倍领取',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='签到活动实例表';

-- ----------------------------
-- Records of Inst_Activity_SignIn
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Activity_Treasures
-- ----------------------------

CREATE TABLE `Inst_Activity_Treasures` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `instPlayerId` int(11) NOT NULL COMMENT '玩家实例id',
  `treasuresId` int(11) NOT NULL COMMENT '招财进宝id',
  `version` int(11) NOT NULL COMMENT '版本号',
  `insertTime` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='招财进宝活动实例表';

-- ----------------------------
-- Records of Inst_Activity_Treasures
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Auction_Shop
-- ----------------------------

CREATE TABLE `Inst_Auction_Shop` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `tableTypeId` int(11) DEFAULT NULL COMMENT '表类型Id',
  `tableFieldId` int(11) DEFAULT NULL COMMENT '表字段Id',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `sellType` int(1) DEFAULT NULL COMMENT '出售类型 1-元宝 2-精魂',
  `sellValue` int(11) DEFAULT NULL COMMENT '出售值',
  `sellOut` int(1) DEFAULT NULL COMMENT '是否售完 0-未售完 1-售完',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='拍卖行实例表';

-- ----------------------------
-- Records of Inst_Auction_Shop
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Blob_Data
-- ----------------------------

CREATE TABLE `Inst_Blob_Data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT '0',
  `datas` mediumblob COMMENT '大数据',
  `note` varchar(64) DEFAULT NULL COMMENT '描述',
  `lastTime` varchar(64) DEFAULT NULL COMMENT '最后一次更新时间',
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Inst_Blob_Data
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_CdKey_GetRecord
-- ----------------------------

CREATE TABLE `Inst_CdKey_GetRecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `cdKeyTypeId` int(11) DEFAULT NULL COMMENT '兑换码类型Id',
  `cdk` varchar(50) DEFAULT NULL COMMENT '兑换码',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `cdKey_getRecord_instPlayerId` (`instPlayerId`),
  KEY `cdKey_getRecord_cdk` (`cdk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统兑换码领取记录实例表';

-- ----------------------------
-- Records of Inst_CdKey_GetRecord
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_ChapterActivity
-- ----------------------------

CREATE TABLE `Inst_ChapterActivity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `chapterId` int(11) DEFAULT NULL COMMENT '副本章节Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='活动副本实例表';

-- ----------------------------
-- Records of Inst_ChapterActivity
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Equip_Gem
-- ----------------------------

CREATE TABLE `Inst_Equip_Gem` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instPlayerEquipId` int(11) DEFAULT NULL COMMENT '玩家装备实例Id',
  `thingId` int(11) DEFAULT NULL COMMENT '物品Id 0表示未镶嵌宝石',
  `position` int(11) DEFAULT NULL COMMENT '位置',
  `insertTime` varchar(50) DEFAULT NULL,
  `updateTime` varchar(50) DEFAULT NULL,
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家装备镶嵌宝石实例表';

-- ----------------------------
-- Records of Inst_Equip_Gem
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_HJY_Store
-- ----------------------------

CREATE TABLE `Inst_HJY_Store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `tableTypeId` int(11) DEFAULT NULL COMMENT '表类型Id',
  `tableFieldId` int(11) DEFAULT NULL COMMENT '表字段Id',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `sellType` int(1) DEFAULT NULL COMMENT '出售类型 1-元宝 2-精魂',
  `sellValue` int(11) DEFAULT NULL COMMENT '出售值',
  `sellOut` int(1) DEFAULT NULL COMMENT '是否售完 0-未售完 1-售完',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='黑角域卖场实例表';

-- ----------------------------
-- Records of Inst_HJY_Store
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_LuckRank
-- ----------------------------

CREATE TABLE `Inst_LuckRank` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `playerId` int(11) DEFAULT '0',
  `name` varchar(64) DEFAULT NULL,
  `rankValue` int(11) DEFAULT '0',
  `orderIndex` int(11) DEFAULT '0',
  `countReset` varchar(64) DEFAULT NULL,
  `startRemain` int(11) DEFAULT '0',
  `refreshRemain` int(11) DEFAULT '0',
  `items` varchar(1024) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Inst_LuckRank
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player
-- ----------------------------

CREATE TABLE `Inst_Player` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `openId` varchar(300) DEFAULT NULL COMMENT '账号Id',
  `name` varchar(256) DEFAULT NULL COMMENT '名称',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `gold` int(11) DEFAULT NULL COMMENT '元宝',
  `copper` varchar(50) DEFAULT NULL COMMENT '铜钱',
  `exp` int(11) DEFAULT NULL COMMENT '经验',
  `energy` int(11) DEFAULT NULL COMMENT '体力',
  `maxEnergy` int(11) DEFAULT NULL COMMENT '最大体力',
  `vigor` int(11) DEFAULT NULL COMMENT '耐力',
  `maxVigor` int(11) DEFAULT NULL COMMENT '最大耐力',
  `cardNum` int(11) DEFAULT NULL COMMENT '可上阵卡牌数',
  `maxCardNum` int(11) DEFAULT NULL COMMENT '最大上阵卡牌数',
  `guidStep` varchar(50) DEFAULT NULL COMMENT '引导步骤 内容格式为：level_step&level_step;',
  `chapterId` int(11) DEFAULT NULL COMMENT '章节Id',
  `barrierId` int(11) DEFAULT NULL COMMENT '关卡Id',
  `fireGainRuleId` int(11) DEFAULT NULL COMMENT '异火抓取规则Id',
  `instPlayerFireId` int(11) DEFAULT NULL COMMENT '玩家异火实例Id  0-未装备',
  `vipLevel` int(11) DEFAULT NULL COMMENT 'vip等级',
  `vigour` int(11) DEFAULT NULL COMMENT '元气（该字段目前没用）',
  `culture` int(11) DEFAULT NULL COMMENT '火能（由之前的修为改为火能）',
  `lastEnergyRecoverTime` varchar(50) DEFAULT NULL COMMENT '两个含义：1-首次破满/使用时间 2-上一次体力恢复时间',
  `lastVigorRecoverTime` varchar(50) DEFAULT NULL COMMENT '两个含义：1-首次破满/使用时间 2-上一次耐力恢复时间',
  `barrierNum` int(11) DEFAULT NULL COMMENT '副本战斗胜利次数（用于控制拍卖行开启）',
  `buyEnergyNum` int(11) DEFAULT NULL COMMENT '当日购买体力的次数',
  `buyVigorNum` int(11) DEFAULT NULL COMMENT '当日购买耐力的次数',
  `lastBuyEnergyTime` varchar(50) DEFAULT NULL COMMENT '最后一次购买体力的时间',
  `lastBuyVigorTime` varchar(50) DEFAULT NULL COMMENT '最后一次购买耐力的时间',
  `guipedBarrier` varchar(255) DEFAULT NULL COMMENT '关卡引导步骤',
  `washTime` varchar(50) DEFAULT NULL COMMENT '洗澡恢复体力时间',
  `dailyTashTime` varchar(50) DEFAULT NULL COMMENT '每日任务时间',
  `headerCardId` int(11) DEFAULT NULL COMMENT '头像卡牌Id',
  `vipIds` varchar(100) DEFAULT NULL COMMENT '用于存储已领取的vipId礼包',
  `pullBlack` int(1) DEFAULT NULL COMMENT '拉黑 1-拉黑',
  `isGetFirstRechargeGift` int(11) DEFAULT NULL COMMENT '是否已领首充礼包 0-未领取 1-已领取',
  `beautyCardTime` varchar(50) DEFAULT NULL COMMENT '美人缠绵时间',
  `serverId` int(11) DEFAULT NULL,
  `channel` varchar(50) DEFAULT NULL,
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(100) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `openId` (`openId`(255)) USING BTREE,
  KEY `name` (`name`(255))
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家实例表';

-- ----------------------------
-- Records of Inst_Player
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Achievement
-- ----------------------------

CREATE TABLE `Inst_Player_Achievement` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `achievementTypeId` int(11) DEFAULT NULL COMMENT '成就类型',
  `achievementId` int(11) DEFAULT NULL COMMENT '当前成就字典Id 分号隔开',
  `canAchievementId` int(11) DEFAULT NULL COMMENT '完成成就字典Id 分号隔开',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家成就实例表';

-- ----------------------------
-- Records of Inst_Player_Achievement
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_AchievementValue
-- ----------------------------

CREATE TABLE `Inst_Player_AchievementValue` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `achievementTypeId` int(11) DEFAULT NULL COMMENT '成就类型',
  `value` int(11) DEFAULT NULL COMMENT '默认计数',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家成就计数实例表';

-- ----------------------------
-- Records of Inst_Player_AchievementValue
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Arena
-- ----------------------------

CREATE TABLE `Inst_Player_Arena` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `rank` int(11) DEFAULT NULL COMMENT '排名',
  `upRank` int(11) DEFAULT NULL COMMENT '历史最高排名  -1:NPC  >=0:PC',
  `arenaNum` int(11) DEFAULT NULL COMMENT '竞技次数',
  `arenaTime` varchar(50) DEFAULT NULL COMMENT '竞技时间',
  `prestige` int(11) DEFAULT NULL COMMENT '威望',
  `buyArenaNum` int(11) DEFAULT NULL COMMENT '购买次数 购买挑战次数',
  `awardRank` int(11) DEFAULT NULL COMMENT '领取前进将的排名',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`) USING BTREE,
  KEY `rank` (`rank`),
  KEY `upRank` (`upRank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家竞技场实例表';

-- ----------------------------
-- Records of Inst_Player_Arena
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Arena_NPC
-- ----------------------------

CREATE TABLE `Inst_Player_Arena_NPC` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(10) DEFAULT NULL COMMENT 'NPC名字',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `cards` varchar(300) DEFAULT NULL COMMENT '卡牌列表',
  `formations` varchar(300) DEFAULT NULL COMMENT '卡牌阵型列表',
  `lineups` varchar(300) DEFAULT NULL COMMENT '卡牌阵容列表',
  `equips` varchar(250) DEFAULT NULL COMMENT '装备列表',
  `magics` varchar(300) DEFAULT NULL COMMENT '功法法宝列表',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家竞技场NPC实例表';

-- ----------------------------
-- Records of Inst_Player_Arena_NPC
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_ArenaConvert
-- ----------------------------

CREATE TABLE `Inst_Player_ArenaConvert` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `arenaConvertId` int(11) DEFAULT NULL COMMENT '竞技场兑换字典Id',
  `convertNum` int(11) DEFAULT NULL COMMENT '兑换次数',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='竞技场兑换实例表';

-- ----------------------------
-- Records of Inst_Player_ArenaConvert
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Award
-- ----------------------------

CREATE TABLE `Inst_Player_Award` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `name` int(2) DEFAULT NULL COMMENT '名目 1-天焚炼气塔  2-竞技场 3-系统 4-世界Boss',
  `things` varchar(500) DEFAULT NULL COMMENT '奖励物品',
  `operTime` varchar(50) DEFAULT NULL COMMENT '发奖时间',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='领奖中心实例表';

-- ----------------------------
-- Records of Inst_Player_Award
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_BagExpand
-- ----------------------------

CREATE TABLE `Inst_Player_BagExpand` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `bagTypeId` int(11) DEFAULT NULL COMMENT '背包类型',
  `expandSumNum` int(11) DEFAULT NULL COMMENT '扩充得到的总数量',
  `expandSumGold` int(11) DEFAULT NULL COMMENT '扩充消耗的总金币数',
  `expandSumTimes` int(11) DEFAULT NULL COMMENT '扩充总次数',
  `insertTime` varchar(50) DEFAULT NULL,
  `updateTime` varchar(50) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家背包扩充实例表';

-- ----------------------------
-- Records of Inst_Player_BagExpand
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Barrier
-- ----------------------------

CREATE TABLE `Inst_Player_Barrier` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `barrierId` int(11) DEFAULT NULL COMMENT '副本关卡字典Id',
  `fightNum` int(11) DEFAULT NULL COMMENT '已战斗次数',
  `chapterId` int(11) DEFAULT NULL COMMENT '副本章节Id',
  `barrierLevel` int(11) DEFAULT NULL COMMENT '通过的战斗关卡等级',
  `resetNum` int(11) DEFAULT NULL COMMENT '重置次数（用于普通战斗购买战斗次数）',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `welfareBox` int(1) DEFAULT NULL COMMENT '福利箱状态 用于普通副本 1-可 2-已',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家副本关卡实例表';

-- ----------------------------
-- Records of Inst_Player_Barrier
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Beauty_Card
-- ----------------------------

CREATE TABLE `Inst_Player_Beauty_Card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `beautyCardId` int(11) DEFAULT NULL COMMENT '美人Id',
  `beautyCardExpId` int(11) DEFAULT NULL COMMENT '美人经验Id',
  `exp` int(11) DEFAULT NULL COMMENT '经验值',
  `pr` int(11) DEFAULT NULL COMMENT '概率',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家美人实例表';

-- ----------------------------
-- Records of Inst_Player_Beauty_Card
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_BigTable
-- ----------------------------

CREATE TABLE `Inst_Player_BigTable` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家Id',
  `properties` varchar(50) DEFAULT NULL COMMENT '玩家属性',
  `value1` varchar(500) DEFAULT NULL COMMENT '属性相关值1',
  `value2` varchar(500) DEFAULT NULL COMMENT '属性相关值2',
  `value3` varchar(500) DEFAULT NULL COMMENT '属性相关值3',
  `value4` varchar(500) DEFAULT NULL COMMENT '属性相关值4',
  `value5` varchar(500) DEFAULT NULL COMMENT '属性相关值5',
  `version` int(11) DEFAULT NULL,
  `insertTime` varchar(100) DEFAULT NULL,
  `updateTime` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`),
  KEY `properties` (`properties`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家BigTable实例表';

-- ----------------------------
-- Records of Inst_Player_BigTable
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_BoxCount
-- ----------------------------

CREATE TABLE `Inst_Player_BoxCount` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家Id',
  `openValue` float(12,2) DEFAULT NULL COMMENT '开箱子值',
  `specialBoxRuleId` int(11) DEFAULT NULL COMMENT '特殊箱子规则Id',
  `isHit` int(11) DEFAULT NULL COMMENT '是否命中 0-未命中  1-命中',
  `insertTime` varchar(100) DEFAULT NULL,
  `updateTime` varchar(100) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='箱子计数实例表';

-- ----------------------------
-- Records of Inst_Player_BoxCount
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Card
-- ----------------------------

CREATE TABLE `Inst_Player_Card` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `cardId` int(11) DEFAULT NULL COMMENT '卡牌Id',
  `qualityId` int(11) DEFAULT NULL COMMENT '品阶Id',
  `starLevelId` int(11) DEFAULT NULL COMMENT '星级Id',
  `titleDetailId` int(11) DEFAULT NULL COMMENT '具体称号Id',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `exp` int(11) DEFAULT NULL COMMENT '经验',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `inTeam` int(11) DEFAULT NULL COMMENT '是否在队伍中 0-不在 1-在',
  `useTalentValue` int(11) DEFAULT NULL COMMENT '潜力值',
  `instPlayerKungFuId` int(11) DEFAULT NULL COMMENT '玩家功法实例Id',
  `instPlayerConstells` varchar(500) DEFAULT NULL COMMENT '玩家命宫实例',
  `potential` int(11) DEFAULT NULL COMMENT '潜力点',
  `isLock` int(11) DEFAULT NULL COMMENT '锁  0-不锁 1-锁',
  `trainNum` int(4) DEFAULT NULL COMMENT '修炼次数',
  `trainAcceptNum` int(4) DEFAULT NULL COMMENT '接受修炼次数',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家卡牌实例表';

-- ----------------------------
-- Records of Inst_Player_Card
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Cards
-- ----------------------------

CREATE TABLE `Inst_Player_Cards` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `cardIds` varchar(1000) DEFAULT NULL COMMENT '卡牌Id列表',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家得到过的卡牌实例表 卡牌不重复';

-- ----------------------------
-- Records of Inst_Player_Cards
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_CardSoul
-- ----------------------------

CREATE TABLE `Inst_Player_CardSoul` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `cardId` int(11) DEFAULT NULL COMMENT '卡牌字典Id',
  `cardSoulId` int(11) DEFAULT NULL COMMENT '卡牌魂魄字典Id',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '插入时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家卡牌魂魄实例表';

-- ----------------------------
-- Records of Inst_Player_CardSoul
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Chapter
-- ----------------------------

CREATE TABLE `Inst_Player_Chapter` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `chapterId` int(11) DEFAULT NULL COMMENT '副本章节字典Id',
  `fightNum` int(11) DEFAULT NULL COMMENT '已挑战次数',
  `starNum` int(11) DEFAULT NULL COMMENT '获得星数',
  `isPass` int(11) DEFAULT NULL COMMENT '是否通过 0-未通过 1-通过',
  `getStarType` varchar(10) DEFAULT NULL COMMENT '领取星数奖励 1-10星 2-20星 3-30星',
  `buyNum` int(11) DEFAULT NULL COMMENT '活动副本购买战斗次数',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家副本章节实例表';

-- ----------------------------
-- Records of Inst_Player_Chapter
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_ChapterType
-- ----------------------------

CREATE TABLE `Inst_Player_ChapterType` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `chapterType` int(11) DEFAULT NULL COMMENT '副本章节字典Id 1-普通 2-精英 3-活动',
  `fightNum` int(11) DEFAULT NULL COMMENT '已挑战次数（精英副本）',
  `aKeyTime` varchar(50) DEFAULT NULL COMMENT '一键战斗时间（普通副本）',
  `buyNum` int(11) DEFAULT NULL COMMENT '购买次数 用于普通（购买冷却的次数），精英（购买攻击的次数）',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家副本章节类型实例表';

-- ----------------------------
-- Records of Inst_Player_ChapterType
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Chip
-- ----------------------------

CREATE TABLE `Inst_Player_Chip` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `chipId` int(11) DEFAULT NULL COMMENT '碎片字典Id',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家碎片实例表';

-- ----------------------------
-- Records of Inst_Player_Chip
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Constell
-- ----------------------------

CREATE TABLE `Inst_Player_Constell` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instCardId` int(11) DEFAULT NULL COMMENT '卡牌实例Id',
  `constellId` int(11) DEFAULT NULL COMMENT '命宫字典Id',
  `pills` varchar(200) DEFAULT NULL COMMENT '命宫丹药状态 0-未服用 1-服用',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`),
  KEY `instCardId` (`instCardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家命宫实例表';

-- ----------------------------
-- Records of Inst_Player_Constell
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_DailyTask
-- ----------------------------

CREATE TABLE `Inst_Player_DailyTask` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家Id',
  `dailyTashkId` int(11) DEFAULT NULL COMMENT '每日任务字典Id',
  `times` int(11) DEFAULT NULL COMMENT '当前完成次数',
  `rewardState` int(11) DEFAULT NULL COMMENT '奖励状态 0-未领取  1-已领取',
  `insertTime` varchar(100) DEFAULT NULL,
  `updateTime` varchar(100) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每日任务实例表';

-- ----------------------------
-- Records of Inst_Player_DailyTask
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Equip
-- ----------------------------

CREATE TABLE `Inst_Player_Equip` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `equipTypeId` int(11) DEFAULT NULL COMMENT '装备类型Id',
  `equipId` int(11) DEFAULT NULL COMMENT '装备Id',
  `level` int(11) DEFAULT NULL COMMENT '装备等级',
  `instCardId` int(11) DEFAULT NULL COMMENT '卡牌Id  此装备被装备在哪个卡牌身上.未装备为0',
  `isInlay` int(11) DEFAULT NULL COMMENT '是否镶嵌 0-未镶嵌 1-已镶嵌',
  `equipAdvanceId` int(2) DEFAULT NULL COMMENT '装备进阶字典Id 当前进行的进阶',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家装备实例表';

-- ----------------------------
-- Records of Inst_Player_Equip
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Fire
-- ----------------------------

CREATE TABLE `Inst_Player_Fire` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家Id',
  `fireId` int(11) DEFAULT NULL COMMENT '异火字典Id',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `exp` int(11) DEFAULT NULL COMMENT '经验',
  `bySkillIds` varchar(50) DEFAULT NULL COMMENT '被动技能 位置_异火斗技字典ID;位置_异火斗技字典ID',
  `isUse` int(11) DEFAULT NULL COMMENT '是否上阵  0-不在阵 1-在阵',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家异火实例表';

-- ----------------------------
-- Records of Inst_Player_Fire
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_FireTemp
-- ----------------------------

CREATE TABLE `Inst_Player_FireTemp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerFireId` int(11) DEFAULT NULL COMMENT '异火实例Id',
  `position` int(11) DEFAULT NULL COMMENT '位置',
  `fireSkillId` int(11) DEFAULT NULL COMMENT '异火技能字典Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerFireId` (`instPlayerFireId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家异火技能蜕变临时实例表';

-- ----------------------------
-- Records of Inst_Player_FireTemp
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Formation
-- ----------------------------

CREATE TABLE `Inst_Player_Formation` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instCardId` int(11) DEFAULT NULL COMMENT '卡牌实例Id',
  `type` int(11) DEFAULT NULL COMMENT '阵型类型 1-正规军 2-替补队员',
  `position` int(11) DEFAULT NULL COMMENT '站位',
  `cardId` int(11) DEFAULT NULL COMMENT '卡牌字典Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家阵型实例表';

-- ----------------------------
-- Records of Inst_Player_Formation
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_GmReward
-- ----------------------------

CREATE TABLE `Inst_Player_GmReward` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `sendRewardTime` varchar(50) DEFAULT NULL COMMENT '发奖时间',
  `getRewardTime` varchar(50) DEFAULT NULL COMMENT '得奖时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家商店购买实例表.只存储商店中的道具和vip礼包。道具保留今日和昨日数据，礼包永久保存。礼包存储时，bagType设置成0.';

-- ----------------------------
-- Records of Inst_Player_GmReward
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_GoldStatics
-- ----------------------------

CREATE TABLE `Inst_Player_GoldStatics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型 0-减法  1-加法',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `source` int(11) DEFAULT NULL COMMENT '来源 协议号',
  `operTime` varchar(100) DEFAULT NULL COMMENT '操作时间',
  `operDate` varchar(100) DEFAULT NULL COMMENT '操作日期',
  `descrption` varchar(100) DEFAULT NULL COMMENT '描述',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='元宝统计实例表';

-- ----------------------------
-- Records of Inst_Player_GoldStatics
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_GrabTheHour
-- ----------------------------

CREATE TABLE `Inst_Player_GrabTheHour` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `grabTheHourId` int(5) DEFAULT NULL COMMENT '整点抢字典Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='整点抢实例表';

-- ----------------------------
-- Records of Inst_Player_GrabTheHour
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_KungFu
-- ----------------------------

CREATE TABLE `Inst_Player_KungFu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `kungFuId` int(11) DEFAULT NULL COMMENT '功法Id',
  `exp` int(11) DEFAULT NULL COMMENT '当前经验值',
  `kungFuTierAddId` int(11) DEFAULT NULL COMMENT '功法重数加成字典Id',
  `acupointNodeId` int(11) DEFAULT NULL COMMENT '当前要开启的经脉节点字典表Id',
  `acupointNodes` varchar(100) DEFAULT NULL COMMENT '当前重数下的所有经脉节点字典Id',
  `fightPropOne` varchar(50) DEFAULT NULL COMMENT '属性1',
  `fightPropTwo` varchar(50) DEFAULT NULL COMMENT '属性2',
  `fightPropThree` varchar(50) DEFAULT NULL COMMENT '属性3',
  `instPlayerCardId` int(11) DEFAULT NULL COMMENT '装备的卡牌实例Id  0-未使用',
  `fightPropFour` varchar(50) DEFAULT NULL COMMENT '属性4',
  `fightPropFive` varchar(50) DEFAULT NULL COMMENT '属性5',
  `fightPropSix` varchar(50) DEFAULT NULL COMMENT '属性6',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家功法实例表';

-- ----------------------------
-- Records of Inst_Player_KungFu
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Lineup
-- ----------------------------

CREATE TABLE `Inst_Player_Lineup` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instPlayerFormationId` int(11) DEFAULT NULL COMMENT '阵型Id',
  `equipTypeId` int(11) DEFAULT NULL COMMENT '装备类型Id',
  `instPlayerEquipId` int(11) DEFAULT NULL COMMENT '玩家装备Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家战斗阵容实例表';

-- ----------------------------
-- Records of Inst_Player_Lineup
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Loot
-- ----------------------------

CREATE TABLE `Inst_Player_Loot` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `protectTime` varchar(50) DEFAULT NULL COMMENT '受保护截止时间',
  `skills` varchar(500) DEFAULT NULL COMMENT '技能列表 技能Id_拼合次数 分号隔开',
  `kungFus` varchar(500) DEFAULT NULL COMMENT '功法列表 功法Id_拼合次数 分号隔开',
  `magics` varchar(500) DEFAULT NULL COMMENT '法宝列表',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `lootNum` int(2) DEFAULT NULL COMMENT '抢夺次数',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家抢夺实例表';

-- ----------------------------
-- Records of Inst_Player_Loot
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Magic
-- ----------------------------

CREATE TABLE `Inst_Player_Magic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `magicId` int(11) DEFAULT NULL COMMENT '法宝功法Id',
  `magicType` int(1) DEFAULT NULL COMMENT '法宝功法类型 1-法宝 2-功法',
  `quality` int(1) DEFAULT NULL COMMENT '品质 1-天 2-地 3-玄 -黄',
  `magicLevelId` int(11) DEFAULT NULL COMMENT '法宝功法等级经验字典Id',
  `exp` int(11) DEFAULT NULL COMMENT '经验',
  `instCardId` int(11) DEFAULT NULL COMMENT '卡牌Id 此功法/法宝被装备在哪个卡牌身上.未装备为0',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家法宝功法实例表';

-- ----------------------------
-- Records of Inst_Player_Magic
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Mail
-- ----------------------------

CREATE TABLE `Inst_Player_Mail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `enemyName` varchar(50) DEFAULT NULL COMMENT '敌人(对方）名字',
  `type` int(11) DEFAULT NULL COMMENT '邮件类型 1-抢夺 2-竞技场 3-社交 4-系统',
  `value` int(11) DEFAULT NULL COMMENT '数值 跟随邮件类型不同 1时-碎片字典Id 2时-竞技场滑落到的名次',
  `results` int(11) DEFAULT NULL COMMENT '结果 0-防守失败 1-防守成功 只用于竞技场/强碎片是否防守成功,和社交和系统无关',
  `content` varchar(50) DEFAULT NULL COMMENT '邮件内容-只用于社交和系统,和战斗无关，字数限制为40',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家邮件实例表';

-- ----------------------------
-- Records of Inst_Player_Mail
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_ManualSkill
-- ----------------------------

CREATE TABLE `Inst_Player_ManualSkill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `type` int(11) DEFAULT NULL COMMENT '类别 1-攻 2-辅 3-控 4-毒',
  `manualSkillId` int(11) DEFAULT NULL COMMENT '手动技能Id',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `exp` int(11) DEFAULT NULL COMMENT '经验 当前经验值',
  `isUse` int(11) DEFAULT NULL COMMENT '0-未使用 >0-已使用',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家手动技能实例表';

-- ----------------------------
-- Records of Inst_Player_ManualSkill
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_ManualSkill_Line
-- ----------------------------

CREATE TABLE `Inst_Player_ManualSkill_Line` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `position1` int(11) DEFAULT NULL COMMENT '位置1',
  `position2` int(11) DEFAULT NULL COMMENT '位置2',
  `position3` int(11) DEFAULT NULL COMMENT '位置3',
  `position4` int(11) DEFAULT NULL COMMENT '位置4 当前经验值',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家手动技能排布实例表';

-- ----------------------------
-- Records of Inst_Player_ManualSkill_Line
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Mine
-- ----------------------------

CREATE TABLE `Inst_Player_Mine` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `mineId` int(11) DEFAULT NULL COMMENT '矿的ID',
  `mineType` int(11) DEFAULT NULL COMMENT '矿主的类型 1.铁矿 2.铜矿 3.银矿 4.金矿',
  `mineZone` int(11) DEFAULT NULL COMMENT '矿主的类型 0.普通矿区 1.高级矿区',
  `minePage` int(11) DEFAULT NULL COMMENT '矿区页数',
  `mineIndex` int(11) DEFAULT NULL COMMENT '当前矿区页数里的第几个',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家ID',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '当前矿被从空矿变成被占领的时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '当前矿被重新占领的时间',
  `assistId1` int(11) DEFAULT NULL COMMENT '协助者1的ID',
  `aTime1` varchar(50) DEFAULT NULL COMMENT '协助者1开始协助的时间',
  `assistId2` int(11) DEFAULT NULL COMMENT '协助者2的ID',
  `aTime2` varchar(50) DEFAULT NULL COMMENT '协助者2开始协助的时间',
  `extratime` int(11) DEFAULT NULL COMMENT '当协助者离开时记录协助的时间长度，秒为单位',
  `version` int(11) DEFAULT NULL COMMENT '数据版本',
  PRIMARY KEY (`id`),
  KEY `mineId` (`mineId`) USING BTREE,
  KEY `instPlayerId` (`instPlayerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Inst_Player_Mine
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Pagoda
-- ----------------------------

CREATE TABLE `Inst_Player_Pagoda` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `pagodaStoreyId` int(11) DEFAULT NULL COMMENT '塔层字典Id',
  `num` int(11) DEFAULT NULL COMMENT '塔层挑战数量  0-未过  1,2,3表示次数',
  `resetNum` int(11) DEFAULT NULL COMMENT '重置次数',
  `searchNum` int(11) DEFAULT NULL COMMENT '搜寻次数',
  `maxPagodaStoreyId` int(11) DEFAULT NULL COMMENT '最高挑战到的塔层字典Id',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `pagodaStores` varchar(200) DEFAULT NULL,
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家塔实例表';

-- ----------------------------
-- Records of Inst_Player_Pagoda
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Partner
-- ----------------------------

CREATE TABLE `Inst_Player_Partner` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instCardId` int(11) DEFAULT NULL COMMENT '卡牌实例Id',
  `cardId` int(11) DEFAULT NULL COMMENT '卡牌字典Id',
  `position` int(11) DEFAULT NULL COMMENT '位置',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '插入时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家小伙伴实例表';

-- ----------------------------
-- Records of Inst_Player_Partner
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Pill
-- ----------------------------

CREATE TABLE `Inst_Player_Pill` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `pillId` int(11) DEFAULT NULL COMMENT '丹药字典Id',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家丹药实例表';

-- ----------------------------
-- Records of Inst_Player_Pill
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_PillRecipe
-- ----------------------------

CREATE TABLE `Inst_Player_PillRecipe` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `pillRecipeId` int(11) DEFAULT NULL COMMENT '丹药药方字典Id',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家丹药药方实例表';

-- ----------------------------
-- Records of Inst_Player_PillRecipe
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_PillRecipeThing
-- ----------------------------

CREATE TABLE `Inst_Player_PillRecipeThing` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `pillRecipeThingId` int(11) DEFAULT NULL COMMENT '丹药药方材料字典Id',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号a',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='丹药药方碎片字典表';

-- ----------------------------
-- Records of Inst_Player_PillRecipeThing
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_PillThing
-- ----------------------------

CREATE TABLE `Inst_Player_PillThing` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `pillThingId` int(11) DEFAULT NULL COMMENT '丹药材料字典Id',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `operTime` varchar(50) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家丹药材料实例表';

-- ----------------------------
-- Records of Inst_Player_PillThing
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_PrivateSale
-- ----------------------------

CREATE TABLE `Inst_Player_PrivateSale` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `privateSaleId` int(5) DEFAULT NULL COMMENT '特卖会字典Id',
  `count` int(5) DEFAULT NULL COMMENT '购买次数',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='特卖会实例表';

-- ----------------------------
-- Records of Inst_Player_PrivateSale
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Recharge
-- ----------------------------

CREATE TABLE `Inst_Player_Recharge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `openId` varchar(300) DEFAULT NULL COMMENT '账号',
  `playerName` varchar(100) DEFAULT NULL COMMENT '玩家名字',
  `channel` varchar(100) DEFAULT NULL COMMENT '渠道',
  `thisrmb` int(11) DEFAULT NULL COMMENT '本次充值人民币',
  `thisamt` int(11) DEFAULT NULL COMMENT '本次充值游戏币金额',
  `saveamt` int(11) DEFAULT NULL COMMENT '累计充值游戏币金额',
  `payChannel` int(11) DEFAULT NULL COMMENT '支付渠道 0=个帐渠道,1=财付通,2=银行卡快捷支付,3=没介绍,4=Q卡渠道,5=手机充值卡渠道,6=话费渠道,7=元宝渠道,8=微信支付渠道,',
  `balance` int(11) DEFAULT NULL COMMENT '冗余字段-腾讯返回的：游戏币个数（包含了赠送游戏币）',
  `genbalance` int(11) DEFAULT NULL COMMENT '冗余字段-腾讯返回的：赠送游戏币个数',
  `saveNum` int(11) DEFAULT NULL COMMENT '冗余字段-回调接口返回的,下单成功时购买的数量(游戏币)',
  `source` varchar(100) DEFAULT NULL COMMENT '来源',
  `orderId` varchar(100) DEFAULT NULL COMMENT '充值服务器生成的订单号',
  `serverId` int(11) DEFAULT NULL COMMENT '服务器编号',
  `accountId` varchar(100) DEFAULT NULL COMMENT '账号服务器生成的id',
  `orderform` varchar(255) DEFAULT NULL COMMENT '渠道生成的订单号',
  `roleLevel` int(11) DEFAULT NULL COMMENT '角色等级',
  `goodsId` int(11) DEFAULT NULL COMMENT '道具编号-充值id',
  `ctype` int(11) DEFAULT NULL COMMENT '充值类型（1：成功，2：GM补点，3：充值失败',
  `money` int(11) DEFAULT NULL COMMENT '充值服务器返回的本次充值金额',
  `rechargeRecordId` varchar(100) DEFAULT NULL COMMENT '充值记录编号',
  `operateDate` varchar(100) DEFAULT NULL COMMENT '操作日期',
  `operateTime` varchar(100) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `Player_Recharge_InstPlayerId` (`instPlayerId`),
  KEY `orderform` (`orderform`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家充值实例表';

-- ----------------------------
-- Records of Inst_Player_Recharge
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_RechargeCallBack
-- ----------------------------

CREATE TABLE `Inst_Player_RechargeCallBack` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家Id',
  `openId` varchar(300) DEFAULT NULL,
  `playerName` varchar(100) DEFAULT NULL COMMENT '玩家名字',
  `resultCode` int(11) DEFAULT NULL COMMENT '返回码 -2=说明游戏初始化绑定service不成功,-1=支付流程失败,0=支付流程成功,2=用户取消',
  `payChannel` int(11) DEFAULT NULL COMMENT '支付渠道 0=个帐渠道,1=财付通,2=银行卡快捷支付,3=没介绍,4=Q卡渠道,5=手机充值卡渠道,6=话费渠道,7=元宝渠道,8=微信支付渠道,',
  `payState` int(11) DEFAULT NULL COMMENT '支付状态 -1=未知 0=支付成功,1=用户取消,2=支付出错',
  `provideState` int(11) DEFAULT NULL COMMENT '发货状态 -1=无法知道是否发货成功，如：财付通、手机充值卡渠道,0=发货成功',
  `saveNum` int(11) DEFAULT NULL COMMENT '下单成功时购买的数量(游戏币)',
  `resultMsg` varchar(500) DEFAULT NULL COMMENT '返回信息',
  `extendInfo` varchar(500) DEFAULT NULL COMMENT '扩展信息',
  `operDate` varchar(100) DEFAULT NULL COMMENT '操作日期',
  `operTime` varchar(100) DEFAULT NULL COMMENT '操作时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家充值回调实例表';

-- ----------------------------
-- Records of Inst_Player_RechargeCallBack
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_RechargeFail
-- ----------------------------

CREATE TABLE `Inst_Player_RechargeFail` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `openId` varchar(300) DEFAULT NULL COMMENT '账号',
  `playerName` varchar(100) DEFAULT NULL COMMENT '玩家名字',
  `channel` varchar(100) DEFAULT NULL COMMENT '渠道',
  `money` int(11) DEFAULT NULL COMMENT '本次充值人民币',
  `orderId` varchar(100) DEFAULT NULL COMMENT '充值服务器订单号',
  `accountId` varchar(100) DEFAULT NULL COMMENT '账号服务器账号id',
  `ctype` int(11) DEFAULT NULL COMMENT '充值类型（1：成功，2：GM补点，3：充值失败）',
  `orderform` varchar(100) DEFAULT NULL COMMENT '渠道生成的订单号',
  `rechargeRecordId` varchar(100) DEFAULT NULL COMMENT '充值记录编号',
  `goodsId` varchar(100) DEFAULT NULL COMMENT '道具编号-充值id',
  `operateDate` varchar(100) DEFAULT NULL COMMENT '操作日期',
  `operateTime` varchar(100) DEFAULT NULL COMMENT '操作时间',
  `des` varchar(255) DEFAULT NULL,
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `Player_Recharge_InstPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家充值实例表';

-- ----------------------------
-- Records of Inst_Player_RechargeFail
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Recruit
-- ----------------------------

CREATE TABLE `Inst_Player_Recruit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `recruitTypeId` int(11) DEFAULT NULL COMMENT '招募类型Id',
  `recruitSumTimes` int(11) DEFAULT NULL COMMENT '招募总次数',
  `feeRecruitTimes` int(11) DEFAULT NULL COMMENT '费用招募次数',
  `lastRecruitTime` varchar(50) DEFAULT NULL COMMENT '最后一次招募时间',
  `lastFreeRecruitTime` varchar(50) DEFAULT NULL COMMENT '最后一次免费招募时间',
  `nextFreeRecruitTime` varchar(50) DEFAULT NULL COMMENT '下一次免费招募时间',
  `remainRecruitTimes` int(11) DEFAULT NULL COMMENT '白银招募中每日剩余招募次数',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '插入时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家招募实例表';

-- ----------------------------
-- Records of Inst_Player_Recruit
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_ResolveTemp
-- ----------------------------

CREATE TABLE `Inst_Player_ResolveTemp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `resolveResult` varchar(300) DEFAULT NULL COMMENT '分解结果 tableTypeId_tableFieldId_value;',
  `consumCopper` int(11) DEFAULT NULL COMMENT '分解消耗的铜币数',
  `resolveType` int(11) DEFAULT NULL,
  `resolveList` varchar(300) DEFAULT NULL,
  `insertTime` varchar(50) DEFAULT NULL,
  `updateTime` varchar(50) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家分解临时表';

-- ----------------------------
-- Records of Inst_Player_ResolveTemp
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_RestoreTemp
-- ----------------------------

CREATE TABLE `Inst_Player_RestoreTemp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instCardId` int(11) DEFAULT NULL COMMENT '卡牌实例Id',
  `tableTypeId` int(11) DEFAULT NULL COMMENT '表类型Id',
  `tableFieldId` int(11) DEFAULT NULL COMMENT '表字段Id',
  `value` int(11) DEFAULT NULL COMMENT '值',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家轮回临时表';

-- ----------------------------
-- Records of Inst_Player_RestoreTemp
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Store
-- ----------------------------

CREATE TABLE `Inst_Player_Store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `thingId` int(11) DEFAULT NULL COMMENT '物品Id',
  `bagType` int(11) DEFAULT NULL COMMENT '购买类型',
  `num` int(11) DEFAULT NULL COMMENT '购买个数',
  `buyTime` varchar(50) DEFAULT NULL COMMENT '购买时间',
  `insertTime` varchar(50) DEFAULT NULL,
  `updateTime` varchar(50) DEFAULT NULL,
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家商店购买实例表.只存储商店中的道具和vip礼包。道具保留今日和昨日数据，礼包永久保存。礼包存储时，bagType设置成0.';

-- ----------------------------
-- Records of Inst_Player_Store
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Thing
-- ----------------------------

CREATE TABLE `Inst_Player_Thing` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `thingId` int(11) DEFAULT NULL COMMENT '物品Id',
  `level` int(11) DEFAULT NULL COMMENT '物品等级 当物品为金宝箱时,此字段表示特殊宝箱的数量, 金宝箱总数量=此字段数量+num字段下的数量',
  `num` int(11) DEFAULT NULL COMMENT '数量',
  `thingTypeId` int(11) DEFAULT NULL COMMENT '物品类型Id',
  `bagTypeId` int(11) DEFAULT NULL,
  `indexOrder` int(11) DEFAULT NULL COMMENT '物品排序',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL,
  `updateTime` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家物品实例表';

-- ----------------------------
-- Records of Inst_Player_Thing
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Train
-- ----------------------------

CREATE TABLE `Inst_Player_Train` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instPlayerCardId` int(11) DEFAULT NULL COMMENT '卡牌实例Id',
  `fightPropId` int(11) DEFAULT NULL COMMENT '战斗属性Id',
  `fightPropValue` int(11) DEFAULT NULL COMMENT '具体值',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`),
  KEY `instPlayerCardId` (`instPlayerCardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家卡牌修炼存储实例表';

-- ----------------------------
-- Records of Inst_Player_Train
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_TrainTemp
-- ----------------------------

CREATE TABLE `Inst_Player_TrainTemp` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerCardId` int(11) DEFAULT NULL COMMENT '卡牌实例Id',
  `fightPropId` int(11) DEFAULT NULL COMMENT '战斗属性Id',
  `fightPropValue` int(11) DEFAULT NULL COMMENT '具体值',
  `trainNum` int(2) DEFAULT NULL COMMENT '本次修炼次数',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerCardId` (`instPlayerCardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家卡牌修炼存储临时实例表';

-- ----------------------------
-- Records of Inst_Player_TrainTemp
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_TryToPractice
-- ----------------------------

CREATE TABLE `Inst_Player_TryToPractice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `tryToPracticeIds` varchar(500) DEFAULT NULL COMMENT '领取过的试练日字典Ids',
  `canTryToPracticeIds` varchar(500) DEFAULT NULL COMMENT '可领取的试练日字典Ids',
  `awards` varchar(20) DEFAULT NULL COMMENT '试练日大奖领取过的天数 ;',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='试练日实例表';

-- ----------------------------
-- Records of Inst_Player_TryToPractice
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Player_Wash
-- ----------------------------

CREATE TABLE `Inst_Player_Wash` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instPlayerEquipId` int(11) DEFAULT NULL COMMENT '装备实例Id',
  `fightPropId` int(11) DEFAULT NULL COMMENT '战斗属性Id',
  `equipWashId` int(11) DEFAULT NULL COMMENT '洗练字典Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家装备洗练存储实例表';

-- ----------------------------
-- Records of Inst_Player_Wash
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_RankDanta
-- ----------------------------

CREATE TABLE `Inst_RankDanta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `playerId` int(11) DEFAULT NULL COMMENT '玩家ID',
  `playerName` varchar(64) DEFAULT NULL,
  `playerLevel` int(11) DEFAULT NULL COMMENT '等级',
  `rankIndex` int(11) DEFAULT NULL COMMENT '名次',
  `maxLayer` int(11) DEFAULT NULL COMMENT '最大图层',
  `addTime` varchar(64) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(64) DEFAULT '' COMMENT '更新时间',
  `startCount` int(11) DEFAULT NULL COMMENT '启动次数',
  `finishCount` int(11) DEFAULT NULL COMMENT '通关次数',
  `version` int(11) DEFAULT '0' COMMENT '版本号',
  `medalCount` int(11) DEFAULT NULL COMMENT '获得的勋章数量',
  `instUnionId` int(11) DEFAULT NULL COMMENT '联盟ID',
  `headerCardId` int(11) DEFAULT NULL COMMENT '卡片头像ID',
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Inst_RankDanta
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_RankDantaDay
-- ----------------------------

CREATE TABLE `Inst_RankDantaDay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `playerId` int(11) DEFAULT NULL COMMENT '玩家ID',
  `playerName` varchar(64) DEFAULT NULL,
  `playerLevel` int(11) DEFAULT NULL COMMENT '等级',
  `rankIndex` int(11) DEFAULT NULL COMMENT '名次',
  `maxLayer` int(11) DEFAULT NULL COMMENT '最大图层',
  `addTime` varchar(64) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(64) DEFAULT '' COMMENT '更新时间',
  `startCount` int(11) DEFAULT NULL COMMENT '启动的次数',
  `layerInfo` text COMMENT 'layerId_轮次1_状态_轮次2_状态;',
  `medalCount` int(11) DEFAULT NULL COMMENT '获得勋章的数量',
  `startLayer` int(11) DEFAULT NULL COMMENT '进入层编号',
  `finishLayer` int(11) DEFAULT NULL COMMENT '最后一次通过的层数',
  `version` int(11) DEFAULT NULL,
  `instUnionId` int(11) DEFAULT NULL COMMENT '联盟ID',
  `headerCardId` int(11) DEFAULT NULL COMMENT '卡片头像ID',
  PRIMARY KEY (`id`),
  KEY `index_player_id` (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Inst_RankDantaDay
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_TotalCost
-- ----------------------------

CREATE TABLE `Inst_TotalCost` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `playerId` int(11) DEFAULT '0',
  `startPoint` varchar(64) DEFAULT NULL,
  `stopPoint` varchar(64) DEFAULT NULL,
  `costCount` int(11) DEFAULT '0',
  `costReset` varchar(64) DEFAULT NULL,
  `amountState` varchar(512) DEFAULT NULL,
  `version` int(11) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `playerId` (`playerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Inst_TotalCost
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Union
-- ----------------------------

CREATE TABLE `Inst_Union` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `exp` int(11) DEFAULT NULL COMMENT '经验',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `gradeTypeId` int(2) DEFAULT NULL COMMENT '联盟类型',
  `maxMember` int(11) DEFAULT NULL COMMENT '最大成员数',
  `currentMember` int(11) DEFAULT NULL COMMENT '当前成员数',
  `headInstPlayerId` int(11) DEFAULT NULL COMMENT '团长玩家实例Id',
  `createTime` varchar(100) DEFAULT NULL COMMENT '创建时间',
  `unionNotice` varchar(255) DEFAULT NULL COMMENT '联盟公告',
  `unionManifesto` varchar(255) DEFAULT NULL COMMENT '联盟宣言',
  `plan` int(3) DEFAULT NULL COMMENT '进度',
  `constructionNum` int(2) DEFAULT NULL COMMENT '当日建设人数',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联盟实例表';

-- ----------------------------
-- Records of Inst_Union
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Union_Apply
-- ----------------------------

CREATE TABLE `Inst_Union_Apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `instUnionrId` int(11) DEFAULT NULL COMMENT '联盟实例Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`),
  KEY `instUnionrId` (`instUnionrId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='玩家联盟申请实例表';

-- ----------------------------
-- Records of Inst_Union_Apply
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Union_Box
-- ----------------------------

CREATE TABLE `Inst_Union_Box` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instUnionId` int(11) DEFAULT NULL COMMENT '联盟实例Id',
  `unionBoxs` varchar(10) DEFAULT NULL COMMENT '联盟宝箱字典Id 分号分开',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '成员玩家实例',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instUnionId` (`instUnionId`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联盟动态实例表';

-- ----------------------------
-- Records of Inst_Union_Box
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Union_Dynamic
-- ----------------------------

CREATE TABLE `Inst_Union_Dynamic` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instUnionId` int(11) DEFAULT NULL COMMENT '联盟实例Id',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '成员玩家实例Id',
  `unionBuild` int(11) DEFAULT NULL COMMENT '联盟建设字典Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instUnionId` (`instUnionId`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联盟动态实例表';

-- ----------------------------
-- Records of Inst_Union_Dynamic
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Union_Member
-- ----------------------------

CREATE TABLE `Inst_Union_Member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instUnionId` int(11) DEFAULT NULL COMMENT '工会实例Id',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '成员玩家实例Id',
  `unionGradeId` int(11) DEFAULT NULL COMMENT '工会职位字典Id',
  `sumOffer` int(11) DEFAULT NULL COMMENT '总贡献',
  `currentOffer` int(11) DEFAULT NULL COMMENT '当天贡献',
  `unionBuildId` int(2) DEFAULT NULL COMMENT '联盟建设字典Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instUnionId` (`instUnionId`),
  KEY `instPlayerId` (`instPlayerId`),
  KEY `unionGradeId` (`unionGradeId`),
  KEY `unionBuildId` (`unionBuildId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联盟成员实例表';

-- ----------------------------
-- Records of Inst_Union_Member
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_Union_Store
-- ----------------------------

CREATE TABLE `Inst_Union_Store` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instUnionId` int(11) DEFAULT NULL COMMENT '联盟实例Id',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '成员玩家实例Id',
  `unionStoreOnes` varchar(250) DEFAULT NULL COMMENT '道具购买记录(用于每天可购买) id_num;',
  `unionStoreTwos` varchar(250) DEFAULT NULL COMMENT '限时购买记录',
  `unionStoreThrees` varchar(250) DEFAULT NULL COMMENT '奖励领取记录',
  `unionStores` varchar(50) DEFAULT NULL COMMENT '永久购买记录（目前用于道具一次性购买)id_num;',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instUnionId` (`instUnionId`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联盟商店实例表';

-- ----------------------------
-- Records of Inst_Union_Store
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_User
-- ----------------------------

CREATE TABLE `Inst_User` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `openId` varchar(300) DEFAULT NULL COMMENT '账号Id',
  `onlineState` int(11) DEFAULT NULL COMMENT ' 状态: 0-离线, 1-在线  2-冻结 3-禁言',
  `firstLoginDate` varchar(50) DEFAULT NULL COMMENT '注册日期',
  `firstLoginTime` varchar(50) DEFAULT NULL COMMENT '首次登录时间 玩家注册时间',
  `loginTotalTimes` int(11) DEFAULT NULL COMMENT '登录总次数',
  `onlineTotalTime` varchar(50) DEFAULT NULL COMMENT '在线总时间 单位-毫秒',
  `lastLoginDate` varchar(50) DEFAULT NULL COMMENT '最近一次登录日期',
  `lastLoginTime` varchar(50) DEFAULT NULL COMMENT '最近一次登录时间 存的为用户的登录时间',
  `lastLeaveDate` varchar(50) DEFAULT NULL COMMENT '最近一次离开日期',
  `lastLeaveTime` varchar(50) DEFAULT NULL COMMENT '最近一次离开时间',
  `channel` varchar(50) DEFAULT NULL COMMENT '渠道',
  `frozenTime` varchar(50) DEFAULT NULL COMMENT '冻结时间',
  `serverId` int(11) DEFAULT NULL,
  `accountId` varchar(300) DEFAULT NULL COMMENT '账号服务器  账号Id',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `openId` (`openId`(255)),
  KEY `firstLoginDate` (`firstLoginDate`),
  KEY `lastLoginDate` (`lastLoginDate`),
  KEY `onlineState` (`onlineState`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户实例表';

-- ----------------------------
-- Records of Inst_User
-- ----------------------------

-- ----------------------------
-- Table structure for Inst_WorldBoss_Rank
-- ----------------------------

CREATE TABLE `Inst_WorldBoss_Rank` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `instPlayerId` int(11) DEFAULT NULL COMMENT '玩家实例Id',
  `rank` int(11) DEFAULT NULL COMMENT '排名 0-最后一击',
  `hurt` int(11) DEFAULT NULL COMMENT '总伤害',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `insertTime` varchar(50) DEFAULT NULL COMMENT '添加时间',
  `updateTime` varchar(50) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `instPlayerId` (`instPlayerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='世界Boss玩家战斗实例表';

-- ----------------------------
-- Records of Inst_WorldBoss_Rank
-- ----------------------------

-- ----------------------------
-- Table structure for Sys_Activity
-- ----------------------------

CREATE TABLE `Sys_Activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `smallUiId` int(11) DEFAULT NULL COMMENT '小头像Id',
  `startTime` varchar(50) DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(50) DEFAULT NULL COMMENT '结束时间',
  `isNew` int(1) DEFAULT NULL COMMENT '是否为最新活动 0-不是最新 1-是最新',
  `isForevery` int(1) DEFAULT NULL COMMENT '是否为永久活动 0-不是 1-是',
  `isView` int(1) DEFAULT NULL COMMENT '是否显示 0-不显示 1-显示',
  `sname` varchar(50) DEFAULT NULL COMMENT '静态字段',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `rank` int(11) DEFAULT NULL COMMENT '排序',
  `isHotKey` int(11) DEFAULT NULL COMMENT '是否有活动快捷图标 0-没有 1-有',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=304 DEFAULT CHARSET=utf8 COMMENT='活动字典表';

-- ----------------------------
-- Records of Sys_Activity
-- ----------------------------
INSERT INTO `Sys_Activity` VALUES ('1', '泡温泉', '15002', '', '', '1', '0', '1', 'wash', '永久', '1', '0', '5');
INSERT INTO `Sys_Activity` VALUES ('2', '黑角域卖场', '15001', null, null, '1', '0', '1', 'hJYStore', '永久', '2', '0', '4');
INSERT INTO `Sys_Activity` VALUES ('3', '成就', '15036', null, null, '1', '1', '1', 'achievement', '永久', '3', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('4', '拍卖行', '15000', null, null, '1', '1', '0', 'auctionShop', null, '4', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('5', '邮件', '15054', null, null, '1', '1', '1', 'mail', '永久', '5', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('6', '开服基金', '15057', null, null, '1', '1', '1', 'fund', '永久', '6', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('7', '72小时抢购', '15055', '', '', '1', '2', '1', 'flashSale', '开服3天', '7', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('8', '累计充值', '15058', '2015-10-10 00:00:00', '2025-09-22 00:00:00', '1', '1', '1', 'addRecharge', '时间段-但isView=1', '8', '0', '1');
INSERT INTO `Sys_Activity` VALUES ('9', '月卡', '15056', null, null, '1', '1', '1', 'monthCard', '30', '9', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('10', '排行榜', '15060', null, null, '1', '1', '1', 'rank', '永久', '10', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('11', '限时兑换', '15067', '', '', '1', '1', '0', 'LimitTimeExchange', '在Dict_activity_Exchange修改', '11', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('12', '累计消耗', '15068', '2015-08-03 00:00:00', '2025-09-22 00:00:00', '1', '1', '1', 'SaveConsume', '时间段', '12', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('13', '幸运转轮', '15075', '2015-08-03 00:00:00', '2025-09-22 00:00:00', '1', '1', '1', 'lucky', '时间段', '13', '0', '1');
INSERT INTO `Sys_Activity` VALUES ('101', '每日特惠', '15072', null, null, '1', '1', '1', 'dailyDeals', '永久', '101', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('102', '限时抢购', '15069', '2015-08-03 00:00:00', '2025-09-22 00:00:00', '1', '2', '1', 'limitShopping', '时间段', '102', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('103', '整点抢', '15070', '2015-10-10 00:00:00', '2025-09-22 00:00:00', '1', '1', '1', 'grabTheHour', '时间段', '103', '0', '1');
INSERT INTO `Sys_Activity` VALUES ('104', '特卖会', '15071', '2015-08-03 00:00:00', '2025-09-22 00:00:00', '1', '2', '0', 'privateSale', '没开', '104', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('105', '限时英雄', '15073', '2015-08-22 00:00:00', '2025-09-22 00:00:00', '1', '90', '1', 'LimitTimeHero', '时间段', '105', '0', '4');
INSERT INTO `Sys_Activity` VALUES ('106', '巅峰强者', '15074', '2015-08-22 00:00:00', '2025-09-22 00:00:00', '1', '1', '1', 'StrongHero', '时间段', '106', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('107', '充值双倍', '15092', '2015-09-13 19:21:19', '2025-09-22 00:00:00', '1', '1', '1', 'rechargeDouble', '时间段', '107', '0', '2');
INSERT INTO `Sys_Activity` VALUES ('108', '招财进宝', '15105', '2015-08-22 00:00:00', '2025-09-22 00:00:00', '1', '1', '1', 'MakeMoney', '时间段', '108', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('201', '等级特卖', null, null, null, '1', '1', '1', 'levelSell', '永久', '201', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('202', 'vip特卖', null, null, null, '1', '1', '1', 'vipSell', '永久', '202', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('203', '星星兑换', null, null, null, '1', '1', '1', 'starStore', '永久', '203', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('204', '月卡贵族', null, null, null, '1', '2', '0', 'monthCardStore', '永久', '204', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('301', '在线奖励', null, null, null, '1', '1', '1', 'onlineRewards', '永久', '301', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('302', '开服礼包', null, null, null, '1', '1', '1', 'openServiceBag', '永久', '302', '0', '0');
INSERT INTO `Sys_Activity` VALUES ('303', '签到奖励', null, null, null, '1', '1', '1', 'signIn', '永久', '303', '0', '0');

-- ----------------------------
-- Table structure for Sys_CdKey
-- ----------------------------

CREATE TABLE `Sys_CdKey` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `cdKeyTypeId` int(11) DEFAULT NULL COMMENT '兑换码类型Id',
  `cdk` varchar(50) DEFAULT NULL COMMENT '兑换码',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`),
  KEY `cdKeyTypeId` (`cdKeyTypeId`),
  KEY `cdk` (`cdk`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统兑换码字典表';

-- ----------------------------
-- Records of Sys_CdKey
-- ----------------------------

-- ----------------------------
-- Table structure for Sys_CdKey_Type
-- ----------------------------

CREATE TABLE `Sys_CdKey_Type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `endTime` varchar(100) DEFAULT NULL COMMENT '失效时间',
  `thingId` varchar(100) DEFAULT NULL COMMENT '奖励物品礼包字典Id',
  `sname` varchar(100) DEFAULT NULL COMMENT '静态字段',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统兑换码类型字典表';

-- ----------------------------
-- Records of Sys_CdKey_Type
-- ----------------------------

-- ----------------------------
-- Table structure for Sys_FilterWords
-- ----------------------------

CREATE TABLE `Sys_FilterWords` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `level` int(11) DEFAULT '0',
  `isChat` int(11) DEFAULT NULL COMMENT '是否过滤聊天 1是0否',
  `isPlayerName` int(11) DEFAULT NULL COMMENT '是否过滤playerName 1是0否',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2048 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of Sys_FilterWords
-- ----------------------------
INSERT INTO `Sys_FilterWords` VALUES ('1', '国民党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2', '共产党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('3', '江泽民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('11', '政府', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('12', '中国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('13', '中华人民共和国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('14', '中华民族', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('15', '政权', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('16', '颠覆政权', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('17', '艹你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('18', '操你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('19', '艹他', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('20', '艹她', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('21', '艹它', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('22', '操她', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('23', '操他', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('24', '操它', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('25', 'TMD', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('26', 'tmd', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('27', 'NMD', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('28', 'nmd', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('29', '日你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('30', '日她', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('31', '日他', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('32', '日它', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('33', '黄色网站', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('34', 'www', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('35', '.com', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('36', '.cn', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('37', '.net', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('40', '黄色网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('41', '黄网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('42', '黄色小电影', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('43', '黄色文学', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('44', '黄色漫画', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('45', '黄色激情', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('46', '激情黄色', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('47', '黄片', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('48', '黄小说', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('49', '骚货', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('50', '婊子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('51', '西藏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('52', '新疆', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('72', '共  产  党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('74', '靠', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('82', '|想上你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('91', '64动乱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('92', '89暴乱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('93', '89动乱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('94', '89风波', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('95', '89六四', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('96', '89年的斗争', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('97', '89学潮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('98', '89运动', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('101', 'bitch', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('102', 'BITCH', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('118', 'fuck', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('119', 'FUCK', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('120', 'Fuck', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('126', 'ｆｕｃｋ', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('127', 'ＦＵＣＫ', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('136', 'Fuck You', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('137', 'fuck you', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('138', 'FUCK YOU', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('148', 'fucker', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('168', 'S B', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('169', 'SARS', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('170', 'SB', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('171', 'sb', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('173', 'sex', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('174', 'SF', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('175', 'SHIT', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('176', 'Shit', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('177', 'shit', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('178', 'ｓｈｉｔ', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('179', 'ＳＨＩＴ', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('186', 'unionnet', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('187', 'WWW', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('190', '八九风波', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('191', '八九年那个春夏之交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('192', '八九学潮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('193', '白痴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('194', '白鸟敢夫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('201', '暴乱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('208', '本拉登', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('209', '屄', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('210', '逼养的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('212', '妣', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('213', '赑', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('214', '婊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('219', '参拜靖国神社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('220', '藏独', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('221', '操', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('222', '操逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('223', '操比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('225', '操你老母', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('226', '操你老娘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('227', '操你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('228', '操死', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('229', '草你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('230', '肏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('232', '插她', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('233', '插你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('234', '插他', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('235', '插我', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('237', '娼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('239', '车仑女干', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('240', '陈水扁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('243', '赤匪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('244', '抽插', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('246', '吹萧', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('247', '吹蕭', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('249', '戳B', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('250', '戳比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('251', '达赖', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('252', '打倒中国共产党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('253', '打飞机', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('254', '打炮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('255', '大*FA弟*子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('257', '大法', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('258', '大法弟子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('259', '大鸡巴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('260', '大雞巴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('271', '蕩婦', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('274', '邓小平', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('275', '邓笑贫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('276', '鄧小平', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('280', '屌', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('283', '腚', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('287', '动乱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('288', '独裁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('289', '独裁政治', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('291', '独立', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('292', '多党制救中国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('296', '扼杀权力', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('297', '扼杀权利', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('303', '發倫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('304', '發淪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('305', '發輪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('306', '發論', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('307', '法(轮)功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('314', '法*轮功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('315', '法+轮+功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('316', '法×', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('317', '法?轮?功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('319', '法抡', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('320', '法仑', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('321', '法伦', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('322', '法沦', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('323', '法轮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('324', '法轮大法', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('325', '法轮功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('326', '法倫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('327', '法淪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('328', '法輪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('329', '法论', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('330', '法论大法', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('331', '法论功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('332', '法論', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('333', '法十轮十功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('334', '法谪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('335', '法谪功|法', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('337', '反攻大陆', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('338', '反共', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('339', '犯贱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('342', '疯狗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('344', '干她', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('345', '干你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('346', '干他', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('347', '幹她', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('348', '幹你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('349', '幹他', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('352', '肛交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('353', '肛门', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('354', '肛門', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('360', '睾丸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('363', '弓虽女干', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('364', '公审江泽民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('365', '公审李鹏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('369', '共党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('370', '共匪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('371', '共狗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('372', '狗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('373', '狗娘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('374', '狗屁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('375', '狗日', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('376', '狗日的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('380', '广西禽流感', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('381', '龟头', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('382', '龜頭', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('383', '国父蒋介石', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('385', '国民党万岁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('393', '红志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('394', '红智', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('395', '宏法', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('396', '洪传', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('397', '洪吟', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('398', '洪志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('399', '洪智', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('400', '紅志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('401', '紅智', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('403', '胡紧掏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('404', '胡锦涛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('405', '胡锦滔', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('406', '胡锦淘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('407', '胡景涛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('408', '胡瘟', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('409', '胡主席', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('410', '胡总书记', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('413', '黃菊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('414', '黄菊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('416', '回良玉', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('417', '混蛋', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('418', '鸡巴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('419', '鸡吧', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('420', '鸡奸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('421', '雞巴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('422', '妓', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('423', '妓女', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('424', '贾庆林', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('425', '价格绝对优惠', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('426', '奸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('430', '贱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('431', '贱人', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('432', '贱种', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('433', '江[泽]民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('435', '江I泽I民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('436', '江独裁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('437', '江核心', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('438', '江流氓', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('439', '江罗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('443', '江则民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('445', '江澤民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('446', '江贼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('447', '江贼民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('448', '江折民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('449', '江猪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('450', '江猪媳', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('451', '江主席', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('452', '将则民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('453', '僵贼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('454', '僵贼民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('455', '疆独', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('456', '蒋介石', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('457', '蒋经国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('462', '蒋中正', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('463', '酱猪媳', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('465', '她妈的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('466', '她妈地', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('467', '她娘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('468', '她爷', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('470', '锦涛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('471', '精子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('472', '警匪一家', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('473', '巨乳', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('475', '开苞', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('477', '開苞', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('479', '尻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('481', '靠你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('482', '可怜卖菜妇', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('483', '口交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('484', '口淫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('488', '老江', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('489', '老骚比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('490', '老骚货', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('491', '李长春', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('493', '李登辉', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('494', '李红志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('495', '李红痔', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('496', '李宏志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('498', '李洪志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('501', '李鹏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('502', '李鵬', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('503', '李瑞环', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('506', '李月月鸟', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('507', '连战', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('508', '刘杰', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('509', '刘少奇', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('514', '六四', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('515', '六四参加者', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('516', '六四参加者回忆录', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('517', '六四点击', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('518', '六四回忆录', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('519', '六四平暴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('520', '六四平反', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('521', '六四事件', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('522', '六四死难者', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('523', '六四屠城', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('524', '六四以后', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('525', '六四以前', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('526', '六四运动', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('527', '六四真相', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('528', '六四正名', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('529', '六四之后', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('530', '六四之前', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('531', '六四周年祭', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('532', '六月four日', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('536', '卵子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('537', '抡功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('538', '伦公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('539', '伦功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('540', '伦攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('541', '沦公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('542', '沦功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('543', '沦攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('544', '轮大', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('545', '轮公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('546', '轮功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('547', '轮攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('548', '倫公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('549', '倫功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('550', '倫攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('551', '淪公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('552', '淪功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('553', '淪攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('554', '輪公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('555', '輪功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('556', '輪攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('557', '论公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('558', '论功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('559', '论攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('560', '論公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('561', '論功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('562', '論攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('563', '罗干', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('564', '妈逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('565', '妈的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('566', '妈个', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('567', '妈个比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('568', '妈个老比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('569', '妈妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('570', '妈妈的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('571', '媽個', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('572', '卖淫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('574', '毛厕洞', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('575', '毛片', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('576', '毛泽东', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('577', '毛贼东', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('578', '毛主席', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('583', '蒙独', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('584', '密洞', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('585', '密穴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('586', '绵恒', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('587', '免费影院', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('598', '奶', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('599', '奶子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('600', '你她妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('601', '你她吗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('602', '你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('603', '你妈逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('604', '你妈的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('605', '你媽逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('606', '你娘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('607', '你他吗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('608', '你它妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('609', '你它吗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('610', '娘的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('611', '娘个比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('612', '女干', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('613', '女马白勺', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('614', '炮打中共', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('615', '炮打中宣部', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('617', '屁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('618', '屁眼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('619', '嫖娼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('620', '平反六四', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('621', '平沼骐一郎', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('622', '强暴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('623', '强奸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('630', '全球公审江泽民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('633', '人民政府', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('634', '日', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('635', '日B', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('636', '日逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('637', '日比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('639', '日你老母', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('640', '日你老娘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('641', '日你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('642', '日批', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('643', '肉棒', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('644', '肉壁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('645', '肉洞', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('646', '肉缝', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('647', '肉棍', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('648', '肉棍子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('649', '如何推翻中共', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('650', '乳', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('651', '乳波臀浪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('652', '乳房', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('653', '乳交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('654', '赛维', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('655', '赛维创世', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('656', '三个代表', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('657', '三个代婊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('658', '三级片', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('659', '三民主义', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('660', '三青团', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('661', '骚比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('663', '色情', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('664', '沙比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('665', '傻×', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('666', '傻B', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('667', '傻b', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('668', '傻Ｂ', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('669', '傻逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('670', '傻比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('671', '傻鄙', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('672', '傻叉', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('673', '傻瓜', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('674', '煞笔', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('675', '山本五十六', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('676', '上访', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('677', '上海帮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('678', '上海瘪三', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('679', '上你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('680', '射精', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('681', '生者与死者', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('682', '生殖器', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('687', '十三点', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('688', '屎', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('689', '手淫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('690', '鼠疫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('691', '双峰微颤', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('692', '谁出卖了中国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('693', '私處', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('701', '孙文', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('702', '孙逸仙', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('703', '孙中山', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('704', '他妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('705', '他妈的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('706', '他妈地', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('707', '他娘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('708', '他爷', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('709', '它妈的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('710', '它妈地', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('711', '台獨', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('712', '台独', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('713', '台湾独立', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('714', '台湾狗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('715', '太子党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('716', '太子党替罪羊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('717', '讨伐', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('718', '讨伐共党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('719', '讨伐中宣部', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('720', '天安门残案', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('721', '天安门惨案', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('722', '天安门档案', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('723', '天安门录影带', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('724', '天安门母亲', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('725', '天安门情人', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('726', '天安门事件', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('727', '天安门屠城', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('728', '天安门屠杀', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('729', '天安们母亲', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('730', '天安们母亲运动', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('733', '捅你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('734', '捅我', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('740', '王八', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('743', '猥琐', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('744', '猥亵', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('748', '温家宝', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('749', '溫家寶', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('751', '我操', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('752', '我靠', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('753', '我日', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('754', '我太阳你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('755', '吳邦國', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('756', '吴邦国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('765', '西藏独立', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('769', '宪政', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('770', '想上你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('771', '小逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('772', '小瘪三', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('773', '小矶国昭', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('774', '小鸡巴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('775', '小鸡鸡', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('776', '小卵泡', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('777', '小卵子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('778', '小平', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('781', '小乳头', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('782', '小骚比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('783', '小骚货', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('786', '邪教', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('787', '新疆独立', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('792', '性爱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('793', '性交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('794', '性虐待', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('796', '畜生', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('805', '血洒天安门', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('806', '血洗天安门', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('809', '阳具', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('810', '陽具', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('812', '爷爷', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('813', '一党专制', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('814', '一九八九六四', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('815', '一九八九年六月四日', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('819', '疫情', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('820', '阴部', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('821', '阴道', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('822', '阴蒂', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('823', '阴核', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('824', '阴户', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('825', '阴茎', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('826', '阴毛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('827', '阴门', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('828', '陰道', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('829', '陰蒂', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('830', '陰戶', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('831', '陰莖', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('832', '陰毛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('833', '陰門', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('834', '陰囊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('835', '淫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('836', '淫棍', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('837', '淫水', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('838', '淫穴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('848', '杂碎', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('849', '杂种', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('855', '则民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('856', '择民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('857', '泽民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('858', '贼民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('859', '曾庆红', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('860', '曾慶紅', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('865', '真善忍', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('869', '政治斗争', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('870', '政治局', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('871', '支那', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('872', '支那人', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('876', '中共', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('877', '中共中央', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('879', '中国共产党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('883', '中国猪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('884', '中华民国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('888', '中央政治局', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('889', '周恩来', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('890', '朱林', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('891', '朱熔基', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('892', '朱镕基', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('902', '作愛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('903', '做爱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('904', '做愛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1090', '林彪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1093', '希特勒', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1095', '反革命', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1096', '我叼你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1101', '狂日你妹妹', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1102', '轮奸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1111', '东突', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1112', '基地组织', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1114', '烂逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1120', '*法*轮*功*', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1121', '烂比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1127', '*李*洪*志*', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1128', '烂货', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1131', '牛逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1134', '13点', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1135', '大血比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1137', 'X妈牛比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1140', '2逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1141', '老逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1144', '国家主席', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1145', '性欲', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1146', '6•4', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1147', '老毛子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1158', '国名党万岁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1159', '血逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1160', '8•9', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1164', '国务院', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1167', '台湾党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1175', '台湾帝国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1176', '阿扁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1177', '阳精', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1179', '李宏旨', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1182', '阿扁万岁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1188', '叼你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1191', '贺国强', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1197', '贺龙', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1202', '黑社会', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1203', '一党专政', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1211', '一中一台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1223', '亲民党', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1224', '红卫兵', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1225', '阴唇', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1226', '李先念', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1233', '两腿之间', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1234', '东条英机', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1235', '包皮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1236', '去你妈的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1243', '全国人大', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1244', '后庭', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1247', '东突暴动和成立', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1249', '瘸腿帮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1250', '胡XX', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1255', '胡紧涛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1257', '六.四', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1258', '东亚病夫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1260', '本？拉登', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1261', '日GM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1266', '天安门一代', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1267', '本•拉登', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1268', '日Gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1269', '胡紧套', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1270', '阴水', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1272', '天皇', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1274', '日gM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1278', '六-四', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1280', '天皇陛下', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1281', '笨逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1283', '淫荡', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1285', '独立台湾会', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1288', '日X妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1290', '淫货', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1294', '胡耀邦', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1295', '淫贱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1297', '逼毛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1298', '淫叫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1301', '逼你老母', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1302', '回回', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1306', '逼样', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1308', '淫语连连', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1311', '二B', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1312', '比毛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1314', '回民暴动', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1315', '淫欲', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1317', '骡干', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1318', '二逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1326', '妈批', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1327', '发抡功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1329', '鸡八', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1330', '媽B发伦', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1333', '舆论钳制', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1335', '媽的', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1336', '发伦功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1337', '肉穴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1338', '鸡鸡', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1340', '发轮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1343', '欲火焚身', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1345', '卖.国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1354', '曰GM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1355', 'f u c k', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1356', '发论功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1359', '曰Gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1361', 'F U C K', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1363', '乳头', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1364', '几届中央政治局常委', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1365', '曰gM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1367', '卖逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1368', '法（轮）功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1369', '外挂', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1371', '曰gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1373', '卖比', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1374', '法*轮*功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1378', '法功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1380', '操GM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1381', '三个呆婊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1385', '卖党求荣', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1388', '操Gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1393', '操gM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1398', '操gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1399', '三陪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1400', '奸夫淫妇', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1405', '操XX', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1406', '奸你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1409', '王岐山', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1411', '骚B贱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1412', '张朝阳', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1414', '毛XX', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1417', '操蛋', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1418', '贱逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1419', '骚逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1426', '贱货', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1430', '分裂祖国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1432', '操你八辈祖宗', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1439', '杀人犯', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1440', '江八点', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1444', '温加宝', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1447', '真理教', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1450', '温家保', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1458', '草的你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1460', '江青', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1462', '干GM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1463', '草媽', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1465', '江戏子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1467', '干Gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1468', '我操你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1470', '傻吊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1471', '江择民', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1473', '干gM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1474', '草拟妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1475', '傻卵', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1478', 'GY', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1480', '干 gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1481', '我日你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1483', '煞逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1485', '政变', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1487', '蒙古达子', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1490', '插GM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1494', '蒙古独立', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1495', '干你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1496', '吴官正', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1497', '插Gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1498', '社会主义', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1501', '干你娘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1503', '插gM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1507', '干死你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1523', '插你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1542', '十八摸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1543', '叫小姐', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1545', '十年动乱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1549', '嫩B', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1550', '金日成', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1551', '陈毅', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1552', '金正日', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1555', '嫩b', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1556', '精液', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1559', '朱德', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1563', '朱容鸡', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1566', '朱容基', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1567', '共产主义', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1574', '军国主义', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1576', '猪容基', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1577', '狗b', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1579', '戳你', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1580', '主席', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1582', '狗操', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1585', '转法轮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1588', '私/服', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1592', '自焚', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1595', '私_服', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1596', '私-服', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1597', '靠你妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1598', '鸟GM', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1599', '大B', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1602', '鸟Gm', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1603', '大逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1609', '作爱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1610', '大东亚共荣', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1611', '四川独立', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1614', '四人帮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1615', '拉登', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1619', '閈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1629', '伱媽', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1635', '国父', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1637', '蒋公', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1638', '装B', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1640', '泽东毛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1642', '毛润之', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1643', '恩来', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1644', '润之', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1645', '中央', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1648', '激情视频', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1649', '激情聊天', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1650', '嫖客', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1651', '妈妈桑', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1690', '毛澤東', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1699', '爸爸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1700', '奶奶', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1701', '国共', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1708', '逼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1709', '丫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1713', '共產黨', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1714', '共和國', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1718', '卍', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1719', '党卫军', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1720', '黨衛軍', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1722', '习近平', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1723', '妹妹', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1725', '政策', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1726', '人民代表大会', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1727', '薄熙来', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1728', '李克强', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1729', '张德江', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1730', '俞正声', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1731', '刘云山', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1733', '张高丽', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1734', '钓鱼岛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1736', '周永康', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1737', '徐才厚', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1738', '法轮功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1739', '李洪志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1740', '信徒', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1741', '主佛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1742', '活摘', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1743', '陆雪琴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1744', '法輪功', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1745', '西藏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1746', '圣战', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1747', '疆独', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1748', '新疆', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1749', '东伊运', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1750', '东突', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1751', '人间福报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1752', '自由新闻报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1753', '都市日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1754', '头条日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1755', '香港商报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1756', '香港电台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1757', 'Discuss', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1758', '华富财经', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1759', '新报网站', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1760', 'The Standard', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1761', 'Hong Kong Herald. 台湾报纸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1762', '中央社新闻网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1763', '中央日报网络报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1764', '中时电子报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1765', '联合新闻网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1766', '自由时报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1767', '新台湾新闻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1768', '中华日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1769', '民众电子报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1770', '交清电子报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1771', '东亚日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1772', '马祖日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1773', '国语日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1774', '八方国际资讯', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1775', '自立晚报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1776', '台湾旺报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1777', '天下杂志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1778', '美洲台湾日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1779', '经济日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1780', '苹果日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1781', '壹周刊(台湾)', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1782', '世界电影(台湾)', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1783', '鑫报e乐网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1784', '工商时报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1785', '金融邮报(台湾股网)', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1786', '30杂志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1787', '农业电子报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1788', '双语学生邮报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1789', '中国时报新竹分社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1790', '1999亚太新新闻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1791', '台湾记者协会', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1792', '生命力公益新闻网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1793', '英文中国邮报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1794', '台英社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1795', 'Taipei Times', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1796', '天眼日报社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1797', '青年日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1798', '世界新闻媒体网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1799', '非常新闻通讯社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1800', '更生日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1801', '彭博新闻社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1802', '彭博商业周刊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1803', '纽约时报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1804', 'C-SPAN', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1805', '明报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1806', '明报月刊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1807', '星岛日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1808', '太阳报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1809', '亚洲周刊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1810', '忽然1周', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1811', '壹周刊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1812', '爽报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1813', '穿越浏览器', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1814', '香港报纸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1815', '联合报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1816', '旺报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1817', '中华电视公司', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1818', '客家电视台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1819', '原住民族电视台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1820', '壹电视', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1821', '澳洲广播电台中文网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1822', '荷兰国际广播电台中文网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1823', 'Engadget中文网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1824', '博客大赛网站', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1825', '自由亚洲电台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1826', '自由欧洲电台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1827', '加拿大国际广播电台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1828', '法国国际广播电台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1829', '梵蒂冈广播电台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1830', '梵蒂冈亚洲新闻通讯社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1831', '今日悉尼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1832', '澳大利亚时报澳奇网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1833', '中欧新闻网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1834', '北美中文网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1835', '南洋视界', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1836', '华人今日网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1837', '多维新闻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1838', '牛博网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1839', '博讯', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1840', '香港独立媒体', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1841', '媒体公民行动网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1842', '新头壳', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1843', '主场新闻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1844', '香港人网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1845', 'MyRadio', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1846', '民间电台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1847', '阳光时务周刊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1848', '开放杂志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1849', '苦劳网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1850', '留园网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1851', '新三才', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1852', '希望之声国际广播电台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1853', '新唐人电视台', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1854', '大纪元时报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1855', '信报财经新闻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1856', '公教报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1857', '星岛日报消息', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1858', '达赖喇嘛的智慧箴言', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1859', '辛子陵', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1860', '高文谦', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1861', '心灵法门“白话佛法”系列节目', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1862', '世界报纸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1863', '周恩来', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1864', '红太阳的陨落', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1865', 'CNN/NHK', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1866', '彭博', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1867', '美国有线电视频道', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1868', '国际广播的电视节目', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1869', '穆斯林', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1870', '达赖', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1871', '美国之音', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1872', '世维会', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1873', '伊斯兰', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1874', '真主', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1875', '安拉', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1876', '白话佛法', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1877', 'islam', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1878', '黄祸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1879', '天藏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1880', '法西斯 ', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1881', '右翼', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1882', 'CND刊物和论坛', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1883', '东方日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1884', '内幕', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1885', '中国茉莉花革命', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1886', '美国之音', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1888', '法广中文网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1889', '明镜网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1890', '大事件', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1891', '北京之春', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1892', '多维', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1893', '中央社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1894', '倍可亲', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1895', 'BBC', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1896', '大纪元网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1897', '阿波罗新闻网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1898', '看中国专栏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1899', '万维读者网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1900', 'RFA', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1901', '零八宪章', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1902', '华尔街日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1903', '法广新闻网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1904', '中国密报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1905', '民主中国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1906', '多维新闻网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1907', '万维博客', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1908', '太陽報', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1909', '东网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1910', '世界日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1911', '法广网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1912', '希望之声', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1913', '世界新闻网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1914', '阿波罗网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1915', '内幕第28期', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1916', '多维网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1917', '新纪元周刊387期', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1918', '中国时报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1919', '新唐人电视台网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1920', '南华早报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1921', '联合早报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1922', '星岛环球网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1923', '博讯网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1924', '金融时报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1925', '南早中文网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1926', '新史记', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1927', '金山桥', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1928', '牛泪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1929', '德国之声中文网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1930', '信报月刊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1931', '中国人权双周刊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1932', '明星新聞網', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1933', '西藏之声', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1934', '开放网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1935', 'RFI', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1936', '博谈网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1937', '观察者网', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1938', '路透社', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1939', '香港经济日报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1940', '新纪元', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1941', '纵览中国', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1942', '爱思想', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1943', '明镜新闻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1944', '动向杂志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1945', '争鸣杂志', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1946', 'DJY', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1947', '茉莉花革命', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1948', '英国金融时报', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1949', '明镜周刊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1950', '联合新闻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1951', 'BBC', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1953', 'AV', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1954', '草榴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1955', '性交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1956', '做爱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1957', '裸体', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1958', '裸聊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1959', '性交易', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1960', '援交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1961', '性感', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1962', '诱惑', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1963', '重口味', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1964', '情色', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1965', '做爱姿势', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1966', '性虐', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1967', '肛交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1968', '口交', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1969', '胸推', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1970', '自慰', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1971', '淫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1972', '卖身', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1973', '偷欢', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1974', '赤裸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1975', '勾引', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1976', '强奸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1977', '迷奸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1978', '淫荡', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1979', '高潮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1980', '偷精', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1981', '卖淫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1982', '性骚扰', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1983', '意淫', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1984', '破处', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1985', '吹萧', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1986', '打炮', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1987', '失身', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1988', '失禁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1989', '外遇', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1990', '变态', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1991', '出轨', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1992', '呻吟', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1993', '闷骚', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1994', '风骚', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1995', '调戏', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1996', '调教', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1997', '不良', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1998', '寻欢', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('1999', '推女郎', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2000', '诱人', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2001', '害羞', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2002', '色撸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2003', 'TD', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2004', '撸', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2005', '性乐', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2006', '恋孕', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2007', '爱爱', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2008', '里番', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2009', '天天干', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2010', '性息', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2011', '裸欲', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2012', '调教性奴', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2013', '成人软件', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2014', 'sex聊天室', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2015', '小姐裸聊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2016', '情色五月', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2017', '美女祼聊', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2018', '同居万岁', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2019', '风流岁月', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2020', '一本道', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2022', '滥情', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2023', '暴君', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2024', '同居', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2025', '人屠', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2026', '撩人', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2027', '专宠', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2028', '禁忌', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2029', '木耳', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2030', '丰乳', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2031', '翘臀', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2032', '乳波', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2033', '臀浪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2034', '浪臀', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2035', '咪咪', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2036', '小攻', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2037', '诱惑', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2038', '小姐', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2039', '妓女', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2040', '很黄', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2041', '他妈', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2042', '小右', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2043', '小受', '1', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2044', 'gm', '0', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2045', 'Gm', '0', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2046', 'gM', '0', '1', '1', null);
INSERT INTO `Sys_FilterWords` VALUES ('2047', 'GM', '0', '1', '1', null);

-- ----------------------------
-- Table structure for Sys_GmReward
-- ----------------------------

CREATE TABLE `Sys_GmReward` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `paramList` varchar(1000) DEFAULT NULL COMMENT '参数列表',
  `rewardTime` varchar(100) DEFAULT NULL COMMENT '发放时间',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='GM全服发奖系统表';

-- ----------------------------
-- Records of Sys_GmReward
-- ----------------------------

-- ----------------------------
-- Table structure for Sys_Marquee
-- ----------------------------

CREATE TABLE `Sys_Marquee` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `startTime` varchar(100) DEFAULT NULL COMMENT '开始日期 格式：[2014-10-01 00:00:00]',
  `endTime` varchar(100) DEFAULT NULL COMMENT '结束日期 格式：[2014-10-01 00:00:00]',
  `inteval` int(11) DEFAULT NULL COMMENT '轮询间隔 单位-秒',
  `content` varchar(1000) DEFAULT NULL COMMENT '内容',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='平台GM跑马灯字典表';

-- ----------------------------
-- Records of Sys_Marquee
-- ----------------------------

-- ----------------------------
-- Table structure for Sys_MsgRule
-- ----------------------------

CREATE TABLE `Sys_MsgRule` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '注释',
  `sname` varchar(255) DEFAULT NULL COMMENT '静态文件字段名也是服务端方法名',
  `classPath` varchar(255) DEFAULT NULL COMMENT '服务端Class路径',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20037 DEFAULT CHARSET=utf8 COMMENT='前后端消息约定规则表';

-- ----------------------------
-- Records of Sys_MsgRule
-- ----------------------------
INSERT INTO `Sys_MsgRule` VALUES ('1000', '心跳检测', 'heartbeat', 'com.huayi.doupo.logic.entry.SystemEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('1001', '玩家登陆', 'login', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1002', '选择初始卡牌', 'choiceCard', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1003', '卡牌换位', 'convertPosition', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1004', '卡牌上阵', 'cardInTeam', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1005', '更换卡牌', 'convertCard', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1006', '吃卡', 'deleteCard', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1007', '添加/更换装备', 'addEquipment', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1008', '卸装备', 'putOffEquip', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1009', '强化装备', 'strengthen', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1010', '一键强化', 'quickStrengthen', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1011', '洗练', 'refinement', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1012', '装备打孔', 'openHole', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1013', '装备镶嵌宝石', 'equipInlay', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1014', '装备宝石拆除', 'takeOffGem', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1015', '装备背包中宝石升级', 'packGemUpgrade', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1016', '装备装备身上宝石升级', 'equipGemUpgrade', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1017', '装备拼合', 'equipPiece', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1018', '小伙伴上阵', 'cardInPartner', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1019', '小伙伴下阵', 'cardOutPartner', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1020', '卡牌魂魄召唤', 'cardSoulCall', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1021', '卡牌修炼', 'train', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1022', '接受卡牌修炼', 'trainAccept', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1023', '卡牌突破', 'breakThrough', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1024', '添加功法', 'addKungFu', 'com.huayi.doupo.logic.entry.KungFuEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1025', '运功', 'addNode', 'com.huayi.doupo.logic.entry.KungFuEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1026', '一键升满', 'addNodes', 'com.huayi.doupo.logic.entry.KungFuEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1027', '更换功法', 'convertKungFu', 'com.huayi.doupo.logic.entry.KungFuEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1028', '吞噬功法', 'deleteKungFu', 'com.huayi.doupo.logic.entry.KungFuEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1029', '开启/蜕变异火技能', 'changeFireSkill', 'com.huayi.doupo.logic.entry.FireEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1030', '保留异火斗技', 'trainFireSkill', 'com.huayi.doupo.logic.entry.FireEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1031', '异火抓取', 'fireGain', 'com.huayi.doupo.logic.entry.FireEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1032', '分解-预览分解结果', 'preViewResolve', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1033', '确认分解', 'makeSureResolve', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1034', '异火上阵', 'fireUse', 'com.huayi.doupo.logic.entry.FireEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1035', '异火出售', 'fireSell', 'com.huayi.doupo.logic.entry.FireEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1036', '异火升级', 'fireUpgrade', 'com.huayi.doupo.logic.entry.FireEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1037', '异火传承', 'fireInherit', 'com.huayi.doupo.logic.entry.FireEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1038', '物品出售', 'sell', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1039', '使用丹药', 'usePill', 'com.huayi.doupo.logic.entry.PillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1040', '炼制丹药', 'addPill', 'com.huayi.doupo.logic.entry.PillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1041', '获取商城数据', 'getStoreData', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1042', '炼制/一键炼制', 'addPills', 'com.huayi.doupo.logic.entry.PillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1043', '出售丹药', 'deletePill', 'com.huayi.doupo.logic.entry.PillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1045', '商店购买', 'buy', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1046', '背包扩容', 'bagExpand', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1047', '魔核转换', 'coreConvert', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1048', '物品使用', 'thingUse', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1049', '爬塔——战', 'war', 'com.huayi.doupo.logic.entry.PagodaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1050', '爬塔——重置', 'reset', 'com.huayi.doupo.logic.entry.PagodaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1051', '爬塔——扫荡', 'mop', 'com.huayi.doupo.logic.entry.PagodaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1052', '爬塔——搜寻', 'search', 'com.huayi.doupo.logic.entry.PagodaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1053', '获取招募信息', 'getRecruitInfo', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1054', '卡牌招募', 'cardRecruit', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1055', '出售丹药材料', 'deletePillThing', 'com.huayi.doupo.logic.entry.PillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1056', '副本——普通战斗', 'commonWar', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1057', '一键战斗', 'aKeyCommonWar', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1058', '星数开箱', 'chapterOpenBox', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1059', '清除连战冷却时间', 'clearColdTime', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1060', '精英战斗', 'eliteWar', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1061', '普通战斗重置战斗次数', 'resetFightNum', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1062', '精英战斗购买次数', 'buyEliteFightNum', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1063', '活动战斗', 'activityWar', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1064', '活动战斗购买次数', 'buyActivityFightNum', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1065', '聊天', 'chat', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1066', '卡牌进阶', 'cardAdvance', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1067', '卡牌出售', 'sellCards', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1068', '功法出售', 'sellKungFus', 'com.huayi.doupo.logic.entry.KungFuEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1069', '下发抢夺玩家/NPC列表', 'lootPlayer', 'com.huayi.doupo.logic.entry.LootEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1070', '和平镇', 'lootPeace', 'com.huayi.doupo.logic.entry.LootEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1071', '抢夺', 'lootWar', 'com.huayi.doupo.logic.entry.LootEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1072', '战斗胜利', 'lootWarWin', 'com.huayi.doupo.logic.entry.LootEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1073', '抢夺拼合', 'lootPiece', 'com.huayi.doupo.logic.entry.LootEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1074', '吃丹', 'eatPill', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1075', '手动技能上阵使用', 'use', 'com.huayi.doupo.logic.entry.ManualSkillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1076', '更换/卸下', 'convertManualSkill', 'com.huayi.doupo.logic.entry.ManualSkillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1077', '吃技能/升级', 'eatManualSkill', 'com.huayi.doupo.logic.entry.ManualSkillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1078', '出售', 'manualSkillSell', 'com.huayi.doupo.logic.entry.ManualSkillEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1079', '锁卡', 'lockCard', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1080', '轮回预览', 'restoreCardView', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1081', '轮回', 'restoreCard', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1082', '体力恢复', 'energyRecover', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1083', '耐力恢复', 'vigorRecover', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1084', '玩家拍卖行/黑角域倒计时时间到', 'updateAuctionOrHjy', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1085', '兑换商品', 'convertGoods', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1086', '拍卖场-白金贵宾', 'platinumVIP', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1087', '战斗失败-普通/精英/活动', 'warFailed', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1088', '元宝购买体力/耐力', 'goldEnergyOrVigor', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1089', '在线奖励领奖', 'onlineRewards', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1090', '初始化开服礼包物品', 'openServiceBagView', '', '暂时不用', null);
INSERT INTO `Sys_MsgRule` VALUES ('1091', '开服礼包领取', 'openServiceBag', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1092', '领取等级礼包', 'levelBag', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1093', '签到', 'signIn', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1094', '领奖中心领奖', 'award', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1095', '领奖中心-全部领取', 'aKeyAward', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1096', '签到-领取双倍', 'twoSignIn', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1097', '引导步骤', 'guidStep', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1098', '初始竞技场', 'arena', 'com.huayi.doupo.logic.entry.ArenaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1099', '竞技场排行（前十名）', 'arenaList', 'com.huayi.doupo.logic.entry.ArenaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1100', '竞技场挑战', 'arenaWar', 'com.huayi.doupo.logic.entry.ArenaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1101', '竞技场抢夺胜利/失败', 'arenaWarWin', 'com.huayi.doupo.logic.entry.ArenaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1104', '竞技场兑换', 'arenaConvert', 'com.huayi.doupo.logic.entry.ArenaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1105', '断线重连', 'relink', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1106', '获取玩家战斗相关数据', 'enemyPlayerInfo', 'com.huayi.doupo.logic.entry.ArenaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1107', '起名字', 'giveName', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1108', '功法/法宝  上阵/更换', 'putOn', 'com.huayi.doupo.logic.entry.MagicEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1109', '功法/法宝下阵', 'putOff', 'com.huayi.doupo.logic.entry.MagicEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1110', '强化法宝与功法', 'strengthenMagic', 'com.huayi.doupo.logic.entry.MagicEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1111', '洗澡恢复体力', 'wash', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1112', '每日任务领奖', 'dailyTashReward', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1113', '进入世界Boss主页', 'clickWorldBossBtn', 'com.huayi.doupo.logic.entry.WorldBossEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1114', '参战世界Boss', 'joinWorldBoss', 'com.huayi.doupo.logic.entry.WorldBossEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1115', '退出世界Boss', 'exitWorldBoss', 'com.huayi.doupo.logic.entry.WorldBossEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1116', '挑战世界Boss', 'fightWorldBoss', 'com.huayi.doupo.logic.entry.WorldBossEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1117', '重生', 'rebirthWorldBoss', 'com.huayi.doupo.logic.entry.WorldBossEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1118', '鼓舞', 'inspireWorldBoss', 'com.huayi.doupo.logic.entry.WorldBossEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1119', '玩家选择头像', 'selectHeader', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1120', '成就领奖', 'achievementReward', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1121', '随机获取名字', 'randomName', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1122', '开箱子', 'openBox', 'com.huayi.doupo.logic.entry.ThingEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1123', '领取vip礼包', 'getVipGiftBag', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1124', '普通副本开福利箱', 'openWelfareBox', 'com.huayi.doupo.logic.entry.ChapterEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1125', '示爱(赠送亲密度)', 'courtship', 'com.huayi.doupo.logic.entry.BeautyCardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1126', '缠绵', 'linger', 'com.huayi.doupo.logic.entry.BeautyCardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1127', '征服', 'conquer', 'com.huayi.doupo.logic.entry.BeautyCardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1128', '充值回调', 'rechargeCallBack', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1129', '查看当前累计充值元宝', 'lookSaveAmt', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1130', '领取首充大礼包', 'getFirstRechargeGift', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1131', '兑换码领奖', 'cDKeyAward', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1132', '获取总值', 'lookRecargeSumT', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1133', '获取在线人数', 'onlineNumT', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1134', '购买开服基金', 'buyFund', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1135', '领取开服基金', 'getFund', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1136', '领取累计充值物品', 'getAddRecargeThings', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1137', '限时抢购购买', 'flashSale', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1138', '月卡每日领取', 'getMonthCard', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1139', '签到初始化数据', 'initSignIn', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1140', '排行榜', 'ranking', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1141', '获取玩家的战力值', 'fightValue', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1142', '获取每日特惠列表', 'getDailyDeals', 'com.huayi.doupo.logic.entry.ActivityEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('1143', '每日特惠', 'dailyDeals', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1144', '获取限时抢购列表', 'getLimitShopping', 'com.huayi.doupo.logic.entry.ActivityEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('1145', '限时抢购', 'limitShopping', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1146', '下发整点抢物品', 'grabTheHour', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1147', '整点抢购买物品', 'grabTheHourBuy', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1148', '下发特卖会物品', 'privateSale', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1149', '特卖会购买物品', 'privateSaleBuy', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1150', '获取lv专卖', 'getLvSell', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1151', 'lv专卖购买', 'lvSellBuy', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1152', '获取vip专卖', 'getVipSell', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1153', 'vip专卖购买', 'vipSellBuy', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1154', '星星兑换', 'starStoreBuy', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1155', '月卡贵族下发数据', 'monthCardStore', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1156', '月卡贵族购买', 'monthCardStoreBuy', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1157', '用于测试ios充值', 'rechargeTest', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1158', '境界', 'realm', 'com.huayi.doupo.logic.entry.CardEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1159', '装备进阶', 'equipAdvance', 'com.huayi.doupo.logic.entry.EquipEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1160', '爬塔———商店', 'store', 'com.huayi.doupo.logic.entry.PagodaEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1161', '获取充值订单号', 'getOrder', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1162', '创建联盟', 'createUnion', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1163', '下发联盟信息', 'unionDetail', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1164', '联盟排行', 'unionRank', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1165', '申请加入联盟', 'applyAddUnion', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1166', '获取申请', 'obtainApply', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1167', '同意申请', 'agreeApply', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1168', '取消/拒绝申请', 'refuseApply', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1169', '踢出/退出联盟', 'exitUnion', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1170', '编写联盟公告/宣言', 'writeUnion', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1171', '任命', 'appointUnion', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1172', '解散联盟', 'dissolveUnion', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1173', '下发成员信息', 'unionMember', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1174', '联盟建设', 'unionBuild', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1175', '获取申请联盟列表', 'applyUnion', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1176', '下发联盟动态', 'unionDynamic', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1177', '联盟宝箱', 'unionBox', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1178', '联盟商店数据下发', 'unionStore', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1179', '联盟商店购买', 'buyUnionStore', 'com.huayi.doupo.logic.entry.UnionEntry', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1180', '发起邮件', 'sendMail', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1181', '打开聊天窗口', 'openChatWindow', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1182', '关闭聊天窗口', 'closeChatWindow', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1183', '试练日领奖', 'tryToPractice', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1184', '试练日购买', 'tryToPracticeBuy', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1185', '试练日副本', 'tryToPracticeBarrier', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1186', '试练日领大奖', 'tryToPracticeAward', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1187', '获取全民福利列表', 'getAllPeapleWeal', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1188', '全民福利领奖', 'allPeapleWealReward', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1189', '刷新限时兑换列表', 'refreshExchange', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1190', '兑换道具', 'requestExchange', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1191', '刷新累计消耗列表', 'refreshTotalCost', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1192', '领取累计消耗奖励', 'getTotalCostAward', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1193', '进入限时英雄界面', 'intoLimitTimeHero', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1194', '获取限时英雄积分奖励', 'getJifenRewardList', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1195', '进入最强英雄界面', 'intoStrogerHero', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1196', '获取最强英雄排行奖励', 'getStrogerHeroRankReward', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1197', '幸运转轮', 'activityLuck', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1198', '获取系统时间', 'getSysTime', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1199', '进入月卡界面', 'intoMonthCard', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1200', '领取月卡额外赠送物品', 'getMonthCardThing', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1201', '招财进宝', 'treasuresFillTheHome', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1202', '丹塔处理器', 'dantaHandler', 'com.huayi.doupo.logic.entry.ActivityEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1203', '进入矿区', 'enterMineZone', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1204', '刷新矿区', 'refreshMineZone', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1205', '一键寻矿', 'aKeySearchMine', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1206', '寻矿，找玩家自己占领的矿', 'searchMine', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1207', '驱逐协助者', 'expelAssistant', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1208', '协助矿', 'assistMiner', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1209', '放弃占领', 'giveUpOccupy', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1210', '放弃协助', 'giveUpAssist', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1211', '占矿', 'occupyMine', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1212', '占矿胜利', 'mineFightWin', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1213', '占矿反击', 'mineBeatBack', 'com.huayi.doupo.logic.entry.MineWarEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('1214', '检测战斗', 'checkFight', 'com.huayi.doupo.logic.entry.SystemEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('3000', '玩家登陆,无数据库操作', 'login1', 'com.huayi.doupo.logic.entry.PlayerEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('3001', '玩家登陆,只有数据库操作', 'login2', 'com.huayi.doupo.logic.entry.PlayerEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('3002', '测试引导', 'testGuid', 'com.huayi.doupo.logic.entry.PlayerEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('3003', '测试起名字', 'testGiveName', 'com.huayi.doupo.logic.entry.PlayerEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('3004', '测试战斗', 'testFight', 'com.huayi.doupo.logic.entry.PlayerEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('3005', '测试其他', 'testOther', 'com.huayi.doupo.logic.entry.PlayerEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('10000', '前端同步数据', 'updateData', 'xxx', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('10001', '推送聊天数据', 'pushChatData', 'xxx', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('10002', '推送关服通知', 'pushCloseServerData', 'xxx', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('10003', '推送世界Boss相关数据', 'pushWorldBossData', 'xxx', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('10004', '推送跑马灯', 'pushMarquee', 'xxx', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('10005', '推送充值成功通知', 'pushRechargeSucc', 'xxx', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('11000', '快速升级（战队）', 'quickUpgrade', 'com.huayi.doupo.logic.entry.PlayerEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20000', '获取在线人数', 'onlineNum', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20001', '关服', 'closeServer', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20002', '缓存控制', 'cacheControl', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20003', '刷新字典数据', 'refreshDictData', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20004', '派发奖励', 'sendReward', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20005', '高级账号', 'highPlayer', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20006', '获取自定义跑马灯', 'getUserMarquee', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20007', '添加自定义跑马灯', 'addUserMarquee', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20008', '修改自定义跑马灯', 'updateUserMarquee', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20009', '删除自定义跑马灯', 'delUserMarquee', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20010', '获取活动', 'getActivity', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20011', '更新活动', 'updateActivity', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20012', '查看玩家map', 'lookPlayerMap', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20013', '更新字典数据', 'updateDict', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20014', '冻结账号', 'frozen', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20015', '获取冻结账号列表', 'frozenList', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20016', '解除冻结', 'frozenFree', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20017', '设置允许最大同时在线', 'allowMaxOnline', 'com.huayi.doupo.logic.entry.GmEnt', '默认500', null);
INSERT INTO `Sys_MsgRule` VALUES ('20018', '获取总值', 'lookRecargeSum', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20019', '初始化世界Boss', 'initWorldBossBlood', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20020', '模拟充值', 'analogRecharge', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20021', '设置聊天CD时间', 'setChatCd', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20022', '禁言', 'shutup', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20023', '获取禁言列表', 'shutupList', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20024', '解除禁言', 'shutupFree', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20025', '查看玩家缓存大小', 'lookPlayerMapSize', 'com.huayi.doupo.logic.entry.GmEnt', '', null);
INSERT INTO `Sys_MsgRule` VALUES ('20026', '设置登录验证状态', 'setLoginEvnState', 'com.huayi.doupo.logic.entry.GmEnt', '0-不验证 1-验证', null);
INSERT INTO `Sys_MsgRule` VALUES ('20027', '设置购买开服基金总人数', 'setOpenServerFundNum', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20028', '是否向客服工具推送数据', 'pushData', 'com.huayi.doupo.logic.entry.GmEnt', '0-不推 1-推', null);
INSERT INTO `Sys_MsgRule` VALUES ('20029', '给玩家去引导', 'dropGuip', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20030', '查看游戏服中map大小', 'lookMapSizeInfo', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20031', '查看游戏服中map容量', 'lookMapCaptainInfo', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20032', '查看玩家缓存容量', 'lookPlayerMapCaptainInfo', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20033', '清除下线几个小时的玩家缓存对象,默认2个小时', 'clearNHour', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20034', '设置允许的最大注册数', 'setCanAllowMaxRegeditNum', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20035', '添加月卡', 'addMonthCard', 'com.huayi.doupo.logic.entry.GmEnt', null, null);
INSERT INTO `Sys_MsgRule` VALUES ('20036', '查询当前设置的最大注册数', 'currMaxRegeditNum', 'com.huayi.doupo.logic.entry.GmEnt', null, null);

-- ----------------------------
-- Table structure for Sys_QuartzJob
-- ----------------------------

CREATE TABLE `Sys_QuartzJob` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `jobName` varchar(100) DEFAULT NULL COMMENT '工作名称',
  `triggerName` varchar(100) DEFAULT NULL COMMENT '触发器名称',
  `cronExpression` varchar(100) DEFAULT NULL COMMENT '调度表达式',
  `startTime` varchar(100) DEFAULT NULL COMMENT '开始时间',
  `endTime` varchar(100) DEFAULT NULL COMMENT '结束时间',
  `jobClassPath` varchar(100) DEFAULT NULL COMMENT '工作执行的类路径',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='定时任务系统表';

-- ----------------------------
-- Records of Sys_QuartzJob
-- ----------------------------
INSERT INTO `Sys_QuartzJob` VALUES ('1', '重置当天开放的活动副本', 'chapterActivityHandlerJ', 'chapterActivityHandlerT', '15 00 00 * * ?', '2014-08-29 21:00:00', '2025-12-03 21:00:00', 'com.huayi.doupo.logic.handler.quartz.ChapterActivityHandler', '内含统计最大最小在线人数逻辑', null);
INSERT INTO `Sys_QuartzJob` VALUES ('2', '竞技场每日发奖', 'arenaAwardHandlerJ', 'arenaAwardHandlerT', '59 59 21 * * ?', '2014-08-29 21:00:00', '2025-12-03 21:00:00', 'com.huayi.doupo.logic.handler.quartz.ArenaAwardHandler', '', null);
INSERT INTO `Sys_QuartzJob` VALUES ('3', '世界Boss开启', 'worldBossStartHandlerJ', 'worldBossStartHandlerT', '59 59 20 * * ?', '2014-08-29 21:00:00', '2025-12-03 21:00:00', 'com.huayi.doupo.logic.handler.quartz.WorldBossStartHandler', '', null);
INSERT INTO `Sys_QuartzJob` VALUES ('4', '资源矿每日发奖', 'mineAwardHandlerJ', 'mineAwardHandlerT', '10 0 14,2 * * ?', '2014-08-29 21:00:00', '2025-12-03 21:00:00', 'com.huayi.doupo.logic.handler.quartz.MineAwardHandler', '', null);
INSERT INTO `Sys_QuartzJob` VALUES ('5', '检查资源矿时间变动', 'modifyQuartzHandlerJ', 'modifyQuartzHandlerT', '0 30 */1 * * ?', '2014-08-29 21:00:00', '2025-12-03 21:00:00', 'com.huayi.doupo.logic.handler.quartz.ModifyQuartzHandler', '', null);

-- ----------------------------
-- Table structure for Sys_Statics
-- ----------------------------

CREATE TABLE `Sys_Statics` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `maxOnlineNum` int(11) DEFAULT NULL COMMENT '最大在线人数',
  `maxOnlineTime` varchar(50) DEFAULT NULL COMMENT '最大在线人数时间点',
  `minOnlineNum` int(11) DEFAULT NULL COMMENT '最小在线人数',
  `minOnlineTime` varchar(50) DEFAULT NULL COMMENT '最小在线人数时间点',
  `maxHitBossNum` int(11) DEFAULT NULL COMMENT '最大打世界Boss的人数',
  `twoDayBackPer` float(8,2) DEFAULT NULL COMMENT '次日留存',
  `threeDayBackPer` float(8,2) DEFAULT NULL COMMENT '三日留存',
  `sevenDayBackPer` float(8,2) DEFAULT NULL COMMENT '七日留存',
  `thirtyDayBackPer` float(8,2) DEFAULT NULL,
  `settleTime` varchar(50) DEFAULT NULL COMMENT '处理时间',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统统计表 统计最大最小在线人数';

-- ----------------------------
-- Records of Sys_Statics
-- ----------------------------
