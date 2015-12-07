/*
Navicat MySQL Data Transfer

Source Server         : 192.168.1.36
Source Server Version : 50530
Source Host           : 192.168.1.36:3306
Source Database       : dpcqtools

Target Server Type    : MYSQL
Target Server Version : 50530
File Encoding         : 65001

Date: 2015-12-09 00:10:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for filecom
-- ----------------------------
DROP TABLE IF EXISTS `filecom`;
CREATE TABLE `filecom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `filename` varchar(20) DEFAULT NULL,
  `fileurl` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of filecom
-- ----------------------------

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `typeid` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`,`typeid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '一品生命丹', '1');
INSERT INTO `goods` VALUES ('1', '打孔器', '2');
INSERT INTO `goods` VALUES ('1', '金币', '3');
INSERT INTO `goods` VALUES ('1', '等级', '4');
INSERT INTO `goods` VALUES ('1', '生命', '5');
INSERT INTO `goods` VALUES ('1', '玄重尺', '6');
INSERT INTO `goods` VALUES ('1', '萧宁', '7');
INSERT INTO `goods` VALUES ('1', '禄存', '8');
INSERT INTO `goods` VALUES ('1', '焚诀碎片一', '10');
INSERT INTO `goods` VALUES ('1', '黄莲精', '11');
INSERT INTO `goods` VALUES ('1', '万兽灵焱', '15');
INSERT INTO `goods` VALUES ('1', '万兽灵焱火种', '16');
INSERT INTO `goods` VALUES ('2', '二品生命丹', '1');
INSERT INTO `goods` VALUES ('2', '菩提子', '2');
INSERT INTO `goods` VALUES ('2', '银币', '3');
INSERT INTO `goods` VALUES ('2', '具体称号Id', '4');
INSERT INTO `goods` VALUES ('2', '物攻', '5');
INSERT INTO `goods` VALUES ('2', '加列奥', '7');
INSERT INTO `goods` VALUES ('2', '文曲', '8');
INSERT INTO `goods` VALUES ('2', '焚诀碎片二', '10');
INSERT INTO `goods` VALUES ('2', '血灵芝', '11');
INSERT INTO `goods` VALUES ('2', '青莲座', '13');
INSERT INTO `goods` VALUES ('2', '青莲地心火', '15');
INSERT INTO `goods` VALUES ('2', '青莲地心火火种', '16');
INSERT INTO `goods` VALUES ('3', '三品生命丹', '1');
INSERT INTO `goods` VALUES ('3', '经验', '4');
INSERT INTO `goods` VALUES ('3', '法攻', '5');
INSERT INTO `goods` VALUES ('3', '柳席', '7');
INSERT INTO `goods` VALUES ('3', '天机', '8');
INSERT INTO `goods` VALUES ('3', '焚诀碎片三', '10');
INSERT INTO `goods` VALUES ('3', '凝血草', '11');
INSERT INTO `goods` VALUES ('3', '龙凰钟', '13');
INSERT INTO `goods` VALUES ('3', '海心焰', '15');
INSERT INTO `goods` VALUES ('3', '海心焰火种', '16');
INSERT INTO `goods` VALUES ('4', '四品生命丹', '1');
INSERT INTO `goods` VALUES ('4', '破镜符', '2');
INSERT INTO `goods` VALUES ('4', '火能石', '3');
INSERT INTO `goods` VALUES ('4', '潜力', '4');
INSERT INTO `goods` VALUES ('4', '闪避', '5');
INSERT INTO `goods` VALUES ('4', '穆力', '7');
INSERT INTO `goods` VALUES ('4', '天魁', '8');
INSERT INTO `goods` VALUES ('4', '焚诀碎片四', '10');
INSERT INTO `goods` VALUES ('4', '活气果', '11');
INSERT INTO `goods` VALUES ('4', '陨落心炎', '15');
INSERT INTO `goods` VALUES ('4', '陨落心炎火种', '16');
INSERT INTO `goods` VALUES ('5', '五品生命丹', '1');
INSERT INTO `goods` VALUES ('5', '金刚心', '2');
INSERT INTO `goods` VALUES ('5', '暴击', '5');
INSERT INTO `goods` VALUES ('5', '风府', '8');
INSERT INTO `goods` VALUES ('5', '焚诀碎片五', '10');
INSERT INTO `goods` VALUES ('5', '紫叶兰草', '11');
INSERT INTO `goods` VALUES ('5', '骨灵冷火', '15');
INSERT INTO `goods` VALUES ('5', '骨灵冷火火种', '16');
INSERT INTO `goods` VALUES ('6', '六品生命丹', '1');
INSERT INTO `goods` VALUES ('6', '突破石', '2');
INSERT INTO `goods` VALUES ('6', '命中', '5');
INSERT INTO `goods` VALUES ('6', '雁翎剑', '6');
INSERT INTO `goods` VALUES ('6', '雪魅', '7');
INSERT INTO `goods` VALUES ('6', '天权', '8');
INSERT INTO `goods` VALUES ('6', '焚诀碎片六', '10');
INSERT INTO `goods` VALUES ('6', '洗骨花', '11');
INSERT INTO `goods` VALUES ('6', '三千焱炎火', '15');
INSERT INTO `goods` VALUES ('6', '三千焱炎火火种', '16');
INSERT INTO `goods` VALUES ('7', '一品物攻丹', '1');
INSERT INTO `goods` VALUES ('7', '火晶', '2');
INSERT INTO `goods` VALUES ('7', '韧性', '5');
INSERT INTO `goods` VALUES ('7', '万魂伞', '6');
INSERT INTO `goods` VALUES ('7', '琳菲', '7');
INSERT INTO `goods` VALUES ('7', '廉贞', '8');
INSERT INTO `goods` VALUES ('7', '九字风雷碎片一', '10');
INSERT INTO `goods` VALUES ('7', '罂粟花', '11');
INSERT INTO `goods` VALUES ('7', '焰分噬浪', '12');
INSERT INTO `goods` VALUES ('7', '九幽金祖火', '15');
INSERT INTO `goods` VALUES ('7', '九幽金祖火火种', '16');
INSERT INTO `goods` VALUES ('8', '二品物攻丹', '1');
INSERT INTO `goods` VALUES ('8', '物防', '5');
INSERT INTO `goods` VALUES ('8', '骨皇刀', '6');
INSERT INTO `goods` VALUES ('8', '葛叶', '7');
INSERT INTO `goods` VALUES ('8', '天府', '8');
INSERT INTO `goods` VALUES ('8', '九字风雷碎片二', '10');
INSERT INTO `goods` VALUES ('8', '寒血果', '11');
INSERT INTO `goods` VALUES ('8', '玄冰旋杀', '12');
INSERT INTO `goods` VALUES ('8', '黑魔鼎', '13');
INSERT INTO `goods` VALUES ('8', '生灵之焱', '15');
INSERT INTO `goods` VALUES ('8', '生灵之焱火种', '16');
INSERT INTO `goods` VALUES ('9', '三品物攻丹', '1');
INSERT INTO `goods` VALUES ('9', '银票', '2');
INSERT INTO `goods` VALUES ('9', '法防', '5');
INSERT INTO `goods` VALUES ('9', '乌钢锤', '6');
INSERT INTO `goods` VALUES ('9', '弗兰克', '7');
INSERT INTO `goods` VALUES ('9', '七杀', '8');
INSERT INTO `goods` VALUES ('9', '九字风雷碎片三', '10');
INSERT INTO `goods` VALUES ('9', '紫烟果', '11');
INSERT INTO `goods` VALUES ('9', '金帝焚天', '12');
INSERT INTO `goods` VALUES ('9', '金帝焚天炎', '15');
INSERT INTO `goods` VALUES ('9', '金帝焚天炎火种', '16');
INSERT INTO `goods` VALUES ('10', '四品物攻丹', '1');
INSERT INTO `goods` VALUES ('10', '银票', '2');
INSERT INTO `goods` VALUES ('10', '昊雷锤', '6');
INSERT INTO `goods` VALUES ('10', '奥拓', '7');
INSERT INTO `goods` VALUES ('10', '破军', '8');
INSERT INTO `goods` VALUES ('10', '九字风雷碎片四', '10');
INSERT INTO `goods` VALUES ('10', '紫蓝叶', '11');
INSERT INTO `goods` VALUES ('10', '佛怒火莲', '12');
INSERT INTO `goods` VALUES ('10', '净莲妖火', '15');
INSERT INTO `goods` VALUES ('10', '净莲妖火火种', '16');
INSERT INTO `goods` VALUES ('11', '五品物攻丹', '1');
INSERT INTO `goods` VALUES ('11', '锁魂链', '6');
INSERT INTO `goods` VALUES ('11', '墨承', '7');
INSERT INTO `goods` VALUES ('11', '武曲', '8');
INSERT INTO `goods` VALUES ('11', '九字风雷碎片五', '10');
INSERT INTO `goods` VALUES ('11', '腾龙花心', '11');
INSERT INTO `goods` VALUES ('11', '魂之葬礼', '12');
INSERT INTO `goods` VALUES ('11', '降魂幡', '13');
INSERT INTO `goods` VALUES ('11', '虚无吞炎', '15');
INSERT INTO `goods` VALUES ('11', '虚无吞炎火种', '16');
INSERT INTO `goods` VALUES ('12', '六品物攻丹', '1');
INSERT INTO `goods` VALUES ('12', '魂源', '2');
INSERT INTO `goods` VALUES ('12', '寒冰魔枪', '6');
INSERT INTO `goods` VALUES ('12', '白山', '7');
INSERT INTO `goods` VALUES ('12', '地劫', '8');
INSERT INTO `goods` VALUES ('12', '九字风雷碎片六', '10');
INSERT INTO `goods` VALUES ('12', '阴神花', '11');
INSERT INTO `goods` VALUES ('12', '九幽冥手', '12');
INSERT INTO `goods` VALUES ('12', '翻天印', '13');
INSERT INTO `goods` VALUES ('12', '帝炎', '15');
INSERT INTO `goods` VALUES ('12', '帝炎火种', '16');
INSERT INTO `goods` VALUES ('13', '一品法攻丹', '1');
INSERT INTO `goods` VALUES ('13', '一阶生命魔核', '2');
INSERT INTO `goods` VALUES ('13', '紫月玄扇', '6');
INSERT INTO `goods` VALUES ('13', '木辰', '7');
INSERT INTO `goods` VALUES ('13', '幽冥', '8');
INSERT INTO `goods` VALUES ('13', '天章九梵碎片一', '10');
INSERT INTO `goods` VALUES ('13', '火阳灵叶', '11');
INSERT INTO `goods` VALUES ('13', '水曼陀罗', '12');
INSERT INTO `goods` VALUES ('13', '七星幡', '13');
INSERT INTO `goods` VALUES ('14', '二品法攻丹', '1');
INSERT INTO `goods` VALUES ('14', '二阶生命魔核', '2');
INSERT INTO `goods` VALUES ('14', '天祭战斧', '6');
INSERT INTO `goods` VALUES ('14', '白程', '7');
INSERT INTO `goods` VALUES ('14', '巨阙', '8');
INSERT INTO `goods` VALUES ('14', '天章九梵碎片二', '10');
INSERT INTO `goods` VALUES ('14', '雪玉骨参', '11');
INSERT INTO `goods` VALUES ('14', '天罗炼火', '12');
INSERT INTO `goods` VALUES ('14', '玲珑塔', '13');
INSERT INTO `goods` VALUES ('15', '三品法攻丹', '1');
INSERT INTO `goods` VALUES ('15', '三阶生命魔核', '2');
INSERT INTO `goods` VALUES ('15', '叶欣蓝', '7');
INSERT INTO `goods` VALUES ('15', '红鸾', '8');
INSERT INTO `goods` VALUES ('15', '天章九梵碎片三', '10');
INSERT INTO `goods` VALUES ('15', '墨叶莲', '11');
INSERT INTO `goods` VALUES ('15', '雨蚀苍穹', '12');
INSERT INTO `goods` VALUES ('15', '凤舞铃', '13');
INSERT INTO `goods` VALUES ('16', '四品法攻丹', '1');
INSERT INTO `goods` VALUES ('16', 'vip0级礼包', '2');
INSERT INTO `goods` VALUES ('16', '姚盛', '7');
INSERT INTO `goods` VALUES ('16', '天喜', '8');
INSERT INTO `goods` VALUES ('16', '天章九梵碎片四', '10');
INSERT INTO `goods` VALUES ('16', '聚灵草', '11');
INSERT INTO `goods` VALUES ('16', '大噬血术', '12');
INSERT INTO `goods` VALUES ('16', '断魂符', '13');
INSERT INTO `goods` VALUES ('17', '五品法攻丹', '1');
INSERT INTO `goods` VALUES ('17', 'vip1级礼包', '2');
INSERT INTO `goods` VALUES ('17', '木战', '7');
INSERT INTO `goods` VALUES ('17', '贪狼', '8');
INSERT INTO `goods` VALUES ('17', '天章九梵碎片五', '10');
INSERT INTO `goods` VALUES ('17', '三尾风叶', '11');
INSERT INTO `goods` VALUES ('17', '千幻毒泉', '12');
INSERT INTO `goods` VALUES ('18', '六品法攻丹', '1');
INSERT INTO `goods` VALUES ('18', '四阶生命魔核', '2');
INSERT INTO `goods` VALUES ('18', '莫崖', '7');
INSERT INTO `goods` VALUES ('18', '天刑', '8');
INSERT INTO `goods` VALUES ('18', '天章九梵碎片六', '10');
INSERT INTO `goods` VALUES ('18', '离土果', '11');
INSERT INTO `goods` VALUES ('18', '万影缚魂', '12');
INSERT INTO `goods` VALUES ('18', '定魂铃', '13');
INSERT INTO `goods` VALUES ('19', '一品物防丹', '1');
INSERT INTO `goods` VALUES ('19', '五阶生命魔核', '2');
INSERT INTO `goods` VALUES ('19', '乾坤天斧', '6');
INSERT INTO `goods` VALUES ('19', '萧媚', '7');
INSERT INTO `goods` VALUES ('19', '太阴', '8');
INSERT INTO `goods` VALUES ('19', '帝印决碎片一', '10');
INSERT INTO `goods` VALUES ('19', '清心三叶草', '11');
INSERT INTO `goods` VALUES ('19', '妖瞳控体', '12');
INSERT INTO `goods` VALUES ('19', '焚诀', '13');
INSERT INTO `goods` VALUES ('20', '二品物防丹', '1');
INSERT INTO `goods` VALUES ('20', '六阶生命魔核', '2');
INSERT INTO `goods` VALUES ('20', '穆蛇', '7');
INSERT INTO `goods` VALUES ('20', '太阳', '8');
INSERT INTO `goods` VALUES ('20', '帝印决碎片二', '10');
INSERT INTO `goods` VALUES ('20', '佛心果', '11');
INSERT INTO `goods` VALUES ('20', '紫晶封印', '12');
INSERT INTO `goods` VALUES ('20', '九字风雷', '13');
INSERT INTO `goods` VALUES ('21', '三品物防丹', '1');
INSERT INTO `goods` VALUES ('21', '一阶物防魔核', '2');
INSERT INTO `goods` VALUES ('21', '月牙戟枪', '6');
INSERT INTO `goods` VALUES ('21', '柳翎', '7');
INSERT INTO `goods` VALUES ('21', '地牢', '8');
INSERT INTO `goods` VALUES ('21', '帝印决碎片三', '10');
INSERT INTO `goods` VALUES ('21', '吸灵树', '11');
INSERT INTO `goods` VALUES ('21', '雷动八荒', '12');
INSERT INTO `goods` VALUES ('21', '神音三动', '13');
INSERT INTO `goods` VALUES ('22', '四品物防丹', '1');
INSERT INTO `goods` VALUES ('22', '二阶物防魔核', '2');
INSERT INTO `goods` VALUES ('22', '曹单', '7');
INSERT INTO `goods` VALUES ('22', '太渊', '8');
INSERT INTO `goods` VALUES ('22', '曹单魂魄', '9');
INSERT INTO `goods` VALUES ('22', '帝印决碎片四', '10');
INSERT INTO `goods` VALUES ('22', '青焰果', '11');
INSERT INTO `goods` VALUES ('22', '化血', '12');
INSERT INTO `goods` VALUES ('22', '混沌天诀', '13');
INSERT INTO `goods` VALUES ('23', '五品物防丹', '1');
INSERT INTO `goods` VALUES ('23', '三阶物防魔核', '2');
INSERT INTO `goods` VALUES ('23', '炼狱魔刀', '6');
INSERT INTO `goods` VALUES ('23', '月媚', '7');
INSERT INTO `goods` VALUES ('23', '陀罗', '8');
INSERT INTO `goods` VALUES ('23', '帝印决碎片五', '10');
INSERT INTO `goods` VALUES ('23', '黑天麻', '11');
INSERT INTO `goods` VALUES ('23', '升灵', '12');
INSERT INTO `goods` VALUES ('23', '天章九梵', '13');
INSERT INTO `goods` VALUES ('24', '六品物防丹', '1');
INSERT INTO `goods` VALUES ('24', '四阶物防魔核', '2');
INSERT INTO `goods` VALUES ('24', '墨巴斯', '7');
INSERT INTO `goods` VALUES ('24', '地空', '8');
INSERT INTO `goods` VALUES ('24', '墨巴斯魂魄', '9');
INSERT INTO `goods` VALUES ('24', '帝印决碎片六', '10');
INSERT INTO `goods` VALUES ('24', '速龙涎', '11');
INSERT INTO `goods` VALUES ('24', '回天', '12');
INSERT INTO `goods` VALUES ('24', '帝印决', '13');
INSERT INTO `goods` VALUES ('25', '一品法防丹', '1');
INSERT INTO `goods` VALUES ('25', '五阶物防魔核', '2');
INSERT INTO `goods` VALUES ('25', '花蛇儿', '7');
INSERT INTO `goods` VALUES ('25', '紫微', '8');
INSERT INTO `goods` VALUES ('25', '九重凤火碎片一', '10');
INSERT INTO `goods` VALUES ('25', '夜灵叶', '11');
INSERT INTO `goods` VALUES ('25', '九重凤火', '13');
INSERT INTO `goods` VALUES ('26', '二品法防丹', '1');
INSERT INTO `goods` VALUES ('26', '六阶物防魔核', '2');
INSERT INTO `goods` VALUES ('26', '紫金摩杵', '6');
INSERT INTO `goods` VALUES ('26', '夭夜', '7');
INSERT INTO `goods` VALUES ('26', '文昌', '8');
INSERT INTO `goods` VALUES ('26', '九重凤火碎片二', '10');
INSERT INTO `goods` VALUES ('26', '昙灵果', '11');
INSERT INTO `goods` VALUES ('27', '三品法防丹', '1');
INSERT INTO `goods` VALUES ('27', '一阶法防魔核', '2');
INSERT INTO `goods` VALUES ('27', '云棱', '7');
INSERT INTO `goods` VALUES ('27', '天梁', '8');
INSERT INTO `goods` VALUES ('27', '九重凤火碎片三', '10');
INSERT INTO `goods` VALUES ('27', '千灵草', '11');
INSERT INTO `goods` VALUES ('27', '暗影功', '13');
INSERT INTO `goods` VALUES ('28', '四品法防丹', '1');
INSERT INTO `goods` VALUES ('28', '二阶法防魔核', '2');
INSERT INTO `goods` VALUES ('28', '韩月', '7');
INSERT INTO `goods` VALUES ('28', '天相', '8');
INSERT INTO `goods` VALUES ('28', '韩月魂魄', '9');
INSERT INTO `goods` VALUES ('28', '九重凤火碎片四', '10');
INSERT INTO `goods` VALUES ('28', '三叶青芝', '11');
INSERT INTO `goods` VALUES ('28', '血魂诀', '13');
INSERT INTO `goods` VALUES ('29', '五品法防丹', '1');
INSERT INTO `goods` VALUES ('29', '三阶法防魔核', '2');
INSERT INTO `goods` VALUES ('29', '炎利', '7');
INSERT INTO `goods` VALUES ('29', '太冲', '8');
INSERT INTO `goods` VALUES ('29', '神魔同诀碎片一', '10');
INSERT INTO `goods` VALUES ('29', '木灵三针花', '11');
INSERT INTO `goods` VALUES ('30', '六品法防丹', '1');
INSERT INTO `goods` VALUES ('30', '四阶法防魔核', '2');
INSERT INTO `goods` VALUES ('30', '乾坤刀', '6');
INSERT INTO `goods` VALUES ('30', '蜈崖', '7');
INSERT INTO `goods` VALUES ('30', '天璇', '8');
INSERT INTO `goods` VALUES ('30', '神魔同诀碎片二', '10');
INSERT INTO `goods` VALUES ('30', '赤炎果', '11');
INSERT INTO `goods` VALUES ('30', '神魔同诀', '13');
INSERT INTO `goods` VALUES ('31', '低级经验丹', '1');
INSERT INTO `goods` VALUES ('31', '五阶法防魔核', '2');
INSERT INTO `goods` VALUES ('31', '流云匕首', '6');
INSERT INTO `goods` VALUES ('31', '韩雪', '7');
INSERT INTO `goods` VALUES ('31', '天姚', '8');
INSERT INTO `goods` VALUES ('31', '韩雪魂魄', '9');
INSERT INTO `goods` VALUES ('31', '神魔同诀碎片三', '10');
INSERT INTO `goods` VALUES ('31', '冰灵焰草', '11');
INSERT INTO `goods` VALUES ('32', '中级经验丹', '1');
INSERT INTO `goods` VALUES ('32', '六阶法防魔核', '2');
INSERT INTO `goods` VALUES ('32', '洪辰', '7');
INSERT INTO `goods` VALUES ('32', '天马', '8');
INSERT INTO `goods` VALUES ('32', '神魔同诀碎片四', '10');
INSERT INTO `goods` VALUES ('32', '回灵赤果', '11');
INSERT INTO `goods` VALUES ('33', '高级经验丹', '1');
INSERT INTO `goods` VALUES ('33', '一阶物攻魔核', '2');
INSERT INTO `goods` VALUES ('33', '左辅', '8');
INSERT INTO `goods` VALUES ('33', '神音三动碎片一', '10');
INSERT INTO `goods` VALUES ('33', '清体草', '11');
INSERT INTO `goods` VALUES ('34', '低级潜力丹', '1');
INSERT INTO `goods` VALUES ('34', '二阶物攻魔核', '2');
INSERT INTO `goods` VALUES ('34', '辰闲', '7');
INSERT INTO `goods` VALUES ('34', '右弼', '8');
INSERT INTO `goods` VALUES ('34', '神音三动碎片二', '10');
INSERT INTO `goods` VALUES ('34', '冰火融魂果', '11');
INSERT INTO `goods` VALUES ('35', '中级潜力丹', '1');
INSERT INTO `goods` VALUES ('35', '三阶物攻魔核', '2');
INSERT INTO `goods` VALUES ('35', '翎泉', '7');
INSERT INTO `goods` VALUES ('35', '巨门', '8');
INSERT INTO `goods` VALUES ('35', '神音三动碎片三', '10');
INSERT INTO `goods` VALUES ('35', '水灵莲子', '11');
INSERT INTO `goods` VALUES ('36', '高级潜力丹', '1');
INSERT INTO `goods` VALUES ('36', '四阶物攻魔核', '2');
INSERT INTO `goods` VALUES ('36', '萧战', '7');
INSERT INTO `goods` VALUES ('36', '天同', '8');
INSERT INTO `goods` VALUES ('36', '萧战魂魄', '9');
INSERT INTO `goods` VALUES ('36', '神音三动碎片四', '10');
INSERT INTO `goods` VALUES ('36', '玉龙涎', '11');
INSERT INTO `goods` VALUES ('37', '五阶物攻魔核', '2');
INSERT INTO `goods` VALUES ('37', '萧鼎', '7');
INSERT INTO `goods` VALUES ('37', '天枢', '8');
INSERT INTO `goods` VALUES ('37', '萧鼎魂魄', '9');
INSERT INTO `goods` VALUES ('37', '混沌天诀碎片一', '10');
INSERT INTO `goods` VALUES ('37', '寒髓枝', '11');
INSERT INTO `goods` VALUES ('37', '法宝银片', '13');
INSERT INTO `goods` VALUES ('38', '六阶物攻魔核', '2');
INSERT INTO `goods` VALUES ('38', '萧玉', '7');
INSERT INTO `goods` VALUES ('38', '玉衡', '8');
INSERT INTO `goods` VALUES ('38', '萧玉魂魄', '9');
INSERT INTO `goods` VALUES ('38', '混沌天诀碎片二', '10');
INSERT INTO `goods` VALUES ('38', '冰火龙须果', '11');
INSERT INTO `goods` VALUES ('38', '法宝金片', '13');
INSERT INTO `goods` VALUES ('39', '一阶法攻魔核', '2');
INSERT INTO `goods` VALUES ('39', '纳兰桀', '7');
INSERT INTO `goods` VALUES ('39', '擎羊', '8');
INSERT INTO `goods` VALUES ('39', '混沌天诀碎片三', '10');
INSERT INTO `goods` VALUES ('39', '地心火芝', '11');
INSERT INTO `goods` VALUES ('39', '功法银卷', '13');
INSERT INTO `goods` VALUES ('40', '二阶法攻魔核', '2');
INSERT INTO `goods` VALUES ('40', '米特尔', '7');
INSERT INTO `goods` VALUES ('40', '天钺', '8');
INSERT INTO `goods` VALUES ('40', '混沌天诀碎片四', '10');
INSERT INTO `goods` VALUES ('40', '青木仙藤', '11');
INSERT INTO `goods` VALUES ('40', '功法金卷', '13');
INSERT INTO `goods` VALUES ('41', '三阶法攻魔核', '2');
INSERT INTO `goods` VALUES ('41', '范凌', '7');
INSERT INTO `goods` VALUES ('41', '暗影功碎片一', '10');
INSERT INTO `goods` VALUES ('41', '极寒灵芝', '11');
INSERT INTO `goods` VALUES ('41', '帝魂决', '13');
INSERT INTO `goods` VALUES ('42', '四阶法攻魔核', '2');
INSERT INTO `goods` VALUES ('42', '暗影功碎片二', '10');
INSERT INTO `goods` VALUES ('42', '寿王浆', '11');
INSERT INTO `goods` VALUES ('42', '灭魂碑', '13');
INSERT INTO `goods` VALUES ('43', '五阶法攻魔核', '2');
INSERT INTO `goods` VALUES ('43', '琥嘉', '7');
INSERT INTO `goods` VALUES ('43', '琥嘉魂魄', '9');
INSERT INTO `goods` VALUES ('43', '暗影功碎片三', '10');
INSERT INTO `goods` VALUES ('43', '魂婴果', '11');
INSERT INTO `goods` VALUES ('44', '六阶法攻魔核', '2');
INSERT INTO `goods` VALUES ('44', '金老', '7');
INSERT INTO `goods` VALUES ('44', '金老魂魄', '9');
INSERT INTO `goods` VALUES ('44', '血魂诀碎片一', '10');
INSERT INTO `goods` VALUES ('44', '紫灵塑体花', '11');
INSERT INTO `goods` VALUES ('45', '海之心甲', '6');
INSERT INTO `goods` VALUES ('45', '银老', '7');
INSERT INTO `goods` VALUES ('45', '银老魂魄', '9');
INSERT INTO `goods` VALUES ('45', '血魂诀碎片二', '10');
INSERT INTO `goods` VALUES ('45', '地心魂髓', '11');
INSERT INTO `goods` VALUES ('46', '万魂冥甲', '6');
INSERT INTO `goods` VALUES ('46', '范痨', '7');
INSERT INTO `goods` VALUES ('46', '血魂诀碎片三', '10');
INSERT INTO `goods` VALUES ('46', '凤火磷果', '11');
INSERT INTO `goods` VALUES ('47', '骨皇圣衣', '6');
INSERT INTO `goods` VALUES ('47', '凌影', '7');
INSERT INTO `goods` VALUES ('47', '凌影魂魄', '9');
INSERT INTO `goods` VALUES ('47', '功法银卷碎片一', '10');
INSERT INTO `goods` VALUES ('47', '佛焰根', '11');
INSERT INTO `goods` VALUES ('48', '玄重天甲', '6');
INSERT INTO `goods` VALUES ('48', '萧厉', '7');
INSERT INTO `goods` VALUES ('48', '萧厉魂魄', '9');
INSERT INTO `goods` VALUES ('48', '功法银卷碎片二', '10');
INSERT INTO `goods` VALUES ('48', '青岚草', '11');
INSERT INTO `goods` VALUES ('49', '修罗罡铠', '6');
INSERT INTO `goods` VALUES ('49', '法犸', '7');
INSERT INTO `goods` VALUES ('49', '法犸魂魄', '9');
INSERT INTO `goods` VALUES ('49', '功法银卷碎片三', '10');
INSERT INTO `goods` VALUES ('49', '香烛草', '11');
INSERT INTO `goods` VALUES ('50', '幻冥皇铠', '6');
INSERT INTO `goods` VALUES ('50', '小公主', '7');
INSERT INTO `goods` VALUES ('50', '小公主魂魄', '9');
INSERT INTO `goods` VALUES ('50', '功法金卷碎片一', '10');
INSERT INTO `goods` VALUES ('50', '青莲果', '11');
INSERT INTO `goods` VALUES ('51', '两仪玄甲', '6');
INSERT INTO `goods` VALUES ('51', '雅妃', '7');
INSERT INTO `goods` VALUES ('51', '雅妃魂魄', '9');
INSERT INTO `goods` VALUES ('51', '功法金卷碎片二', '10');
INSERT INTO `goods` VALUES ('51', '蛇脱花', '11');
INSERT INTO `goods` VALUES ('52', '连环宝甲', '6');
INSERT INTO `goods` VALUES ('52', '若琳导师', '7');
INSERT INTO `goods` VALUES ('52', '若琳导师魂魄', '9');
INSERT INTO `goods` VALUES ('52', '功法金卷碎片三', '10');
INSERT INTO `goods` VALUES ('52', '天麻翡石精', '11');
INSERT INTO `goods` VALUES ('53', '天魔火甲', '6');
INSERT INTO `goods` VALUES ('53', '叶重', '7');
INSERT INTO `goods` VALUES ('53', '叶重魂魄', '9');
INSERT INTO `goods` VALUES ('53', '功法金卷碎片四', '10');
INSERT INTO `goods` VALUES ('53', '丹灵浆', '11');
INSERT INTO `goods` VALUES ('54', '青莲座碎片一', '10');
INSERT INTO `goods` VALUES ('54', '万年血灵参', '11');
INSERT INTO `goods` VALUES ('55', '天玉战铠', '6');
INSERT INTO `goods` VALUES ('55', '青莲座碎片二', '10');
INSERT INTO `goods` VALUES ('55', '青岩木', '11');
INSERT INTO `goods` VALUES ('56', '落英神铠', '6');
INSERT INTO `goods` VALUES ('56', '曹颖', '7');
INSERT INTO `goods` VALUES ('56', '曹颖魂魄', '9');
INSERT INTO `goods` VALUES ('56', '青莲座碎片三', '10');
INSERT INTO `goods` VALUES ('56', '白灵参果', '11');
INSERT INTO `goods` VALUES ('57', '花锦', '7');
INSERT INTO `goods` VALUES ('57', '花锦魂魄', '9');
INSERT INTO `goods` VALUES ('57', '青莲座碎片四', '10');
INSERT INTO `goods` VALUES ('57', '天翡果', '11');
INSERT INTO `goods` VALUES ('58', '素银甲', '6');
INSERT INTO `goods` VALUES ('58', '易尘', '7');
INSERT INTO `goods` VALUES ('58', '易尘魂魄', '9');
INSERT INTO `goods` VALUES ('58', '青莲座碎片五', '10');
INSERT INTO `goods` VALUES ('58', '厚土芝', '11');
INSERT INTO `goods` VALUES ('59', '鹜护法', '7');
INSERT INTO `goods` VALUES ('59', '鹜护法魂魄', '9');
INSERT INTO `goods` VALUES ('59', '青莲座碎片六', '10');
INSERT INTO `goods` VALUES ('59', '火灵根', '11');
INSERT INTO `goods` VALUES ('60', '柳叶凤甲', '6');
INSERT INTO `goods` VALUES ('60', '龙凰钟碎片一', '10');
INSERT INTO `goods` VALUES ('60', '青冥果', '11');
INSERT INTO `goods` VALUES ('61', '龙凰钟碎片二', '10');
INSERT INTO `goods` VALUES ('62', '夜魔战甲', '6');
INSERT INTO `goods` VALUES ('62', '龙凰钟碎片三', '10');
INSERT INTO `goods` VALUES ('63', '金丝软甲', '6');
INSERT INTO `goods` VALUES ('63', '魂崖', '7');
INSERT INTO `goods` VALUES ('63', '魂崖魂魄', '9');
INSERT INTO `goods` VALUES ('63', '龙凰钟碎片四', '10');
INSERT INTO `goods` VALUES ('64', '锁子金甲', '6');
INSERT INTO `goods` VALUES ('64', '魂厉', '7');
INSERT INTO `goods` VALUES ('64', '魂厉魂魄', '9');
INSERT INTO `goods` VALUES ('64', '龙凰钟碎片五', '10');
INSERT INTO `goods` VALUES ('65', '玄重心链', '6');
INSERT INTO `goods` VALUES ('65', '吴昊', '7');
INSERT INTO `goods` VALUES ('65', '吴昊魂魄', '9');
INSERT INTO `goods` VALUES ('65', '龙凰钟碎片六', '10');
INSERT INTO `goods` VALUES ('66', '秘魔灵珠', '6');
INSERT INTO `goods` VALUES ('66', '柳擎', '7');
INSERT INTO `goods` VALUES ('66', '柳擎魂魄', '9');
INSERT INTO `goods` VALUES ('66', '黑魔鼎碎片一', '10');
INSERT INTO `goods` VALUES ('67', '骨皇幽珠', '6');
INSERT INTO `goods` VALUES ('67', '熊战', '7');
INSERT INTO `goods` VALUES ('67', '熊战魂魄', '9');
INSERT INTO `goods` VALUES ('67', '黑魔鼎碎片二', '10');
INSERT INTO `goods` VALUES ('68', '幽鬼项链', '6');
INSERT INTO `goods` VALUES ('68', '加刑天', '7');
INSERT INTO `goods` VALUES ('68', '加刑天魂魄', '9');
INSERT INTO `goods` VALUES ('68', '黑魔鼎碎片三', '10');
INSERT INTO `goods` VALUES ('69', '天机锁链', '6');
INSERT INTO `goods` VALUES ('69', '云山', '7');
INSERT INTO `goods` VALUES ('69', '云山魂魄', '9');
INSERT INTO `goods` VALUES ('69', '黑魔鼎碎片四', '10');
INSERT INTO `goods` VALUES ('70', '残殇之星', '6');
INSERT INTO `goods` VALUES ('70', '丹王古河', '7');
INSERT INTO `goods` VALUES ('70', '丹王古河魂魄', '9');
INSERT INTO `goods` VALUES ('70', '黑魔鼎碎片五', '10');
INSERT INTO `goods` VALUES ('71', '招募令', '2');
INSERT INTO `goods` VALUES ('71', '万魂灵玉', '6');
INSERT INTO `goods` VALUES ('71', '琥乾', '7');
INSERT INTO `goods` VALUES ('71', '琥乾魂魄', '9');
INSERT INTO `goods` VALUES ('71', '黑魔鼎碎片六', '10');
INSERT INTO `goods` VALUES ('72', '和平牌', '2');
INSERT INTO `goods` VALUES ('72', '韩枫', '7');
INSERT INTO `goods` VALUES ('72', '韩枫魂魄', '9');
INSERT INTO `goods` VALUES ('72', '翻天印碎片一', '10');
INSERT INTO `goods` VALUES ('73', '金宝箱', '2');
INSERT INTO `goods` VALUES ('73', '雁落天', '7');
INSERT INTO `goods` VALUES ('73', '雁落天魂魄', '9');
INSERT INTO `goods` VALUES ('73', '翻天印碎片二', '10');
INSERT INTO `goods` VALUES ('74', '银宝箱', '2');
INSERT INTO `goods` VALUES ('74', '雪魂丝链', '6');
INSERT INTO `goods` VALUES ('74', '蝎毕岩', '7');
INSERT INTO `goods` VALUES ('74', '蝎毕岩魂魄', '9');
INSERT INTO `goods` VALUES ('74', '翻天印碎片三', '10');
INSERT INTO `goods` VALUES ('75', '铜宝箱', '2');
INSERT INTO `goods` VALUES ('75', '魔狱项链', '6');
INSERT INTO `goods` VALUES ('75', '沈云', '7');
INSERT INTO `goods` VALUES ('75', '沈云魂魄', '9');
INSERT INTO `goods` VALUES ('75', '翻天印碎片四', '10');
INSERT INTO `goods` VALUES ('76', '金钥匙', '2');
INSERT INTO `goods` VALUES ('76', '龙吟项链', '6');
INSERT INTO `goods` VALUES ('76', '费天', '7');
INSERT INTO `goods` VALUES ('76', '费天魂魄', '9');
INSERT INTO `goods` VALUES ('76', '翻天印碎片五', '10');
INSERT INTO `goods` VALUES ('77', '银钥匙', '2');
INSERT INTO `goods` VALUES ('77', '定魂项链', '6');
INSERT INTO `goods` VALUES ('77', '金谷', '7');
INSERT INTO `goods` VALUES ('77', '金谷魂魄', '9');
INSERT INTO `goods` VALUES ('77', '翻天印碎片六', '10');
INSERT INTO `goods` VALUES ('78', '铜钥匙', '2');
INSERT INTO `goods` VALUES ('78', '妖花邪君', '7');
INSERT INTO `goods` VALUES ('78', '降魂幡碎片一', '10');
INSERT INTO `goods` VALUES ('79', '耐力丹', '2');
INSERT INTO `goods` VALUES ('79', '火纹项链', '6');
INSERT INTO `goods` VALUES ('79', '辰天南', '7');
INSERT INTO `goods` VALUES ('79', '降魂幡碎片二', '10');
INSERT INTO `goods` VALUES ('80', '体力丹', '2');
INSERT INTO `goods` VALUES ('80', '玄金项链', '6');
INSERT INTO `goods` VALUES ('80', '降魂幡碎片三', '10');
INSERT INTO `goods` VALUES ('81', '装备锁', '2');
INSERT INTO `goods` VALUES ('81', '谜之项链', '6');
INSERT INTO `goods` VALUES ('81', '慕青鸾', '7');
INSERT INTO `goods` VALUES ('81', '慕青鸾魂魄', '9');
INSERT INTO `goods` VALUES ('81', '降魂幡碎片四', '10');
INSERT INTO `goods` VALUES ('82', '刷新令', '2');
INSERT INTO `goods` VALUES ('82', '紫晶坠子', '6');
INSERT INTO `goods` VALUES ('82', '洪天啸', '7');
INSERT INTO `goods` VALUES ('82', '七星幡碎片一', '10');
INSERT INTO `goods` VALUES ('83', '进阶石', '2');
INSERT INTO `goods` VALUES ('83', '碧玉坠', '6');
INSERT INTO `goods` VALUES ('83', '七星幡碎片二', '10');
INSERT INTO `goods` VALUES ('84', '蓝岩心石', '2');
INSERT INTO `goods` VALUES ('84', '苏千', '7');
INSERT INTO `goods` VALUES ('84', '苏千魂魄', '9');
INSERT INTO `goods` VALUES ('84', '七星幡碎片三', '10');
INSERT INTO `goods` VALUES ('85', '紫岩心石', '2');
INSERT INTO `goods` VALUES ('85', '玄重帝冠', '6');
INSERT INTO `goods` VALUES ('85', '七星幡碎片四', '10');
INSERT INTO `goods` VALUES ('86', '金宝箱', '2');
INSERT INTO `goods` VALUES ('86', '骨皇之冕', '6');
INSERT INTO `goods` VALUES ('86', '玲珑塔碎片一', '10');
INSERT INTO `goods` VALUES ('87', '境界丹', '2');
INSERT INTO `goods` VALUES ('87', '青炙头盔', '6');
INSERT INTO `goods` VALUES ('87', '玲珑塔碎片二', '10');
INSERT INTO `goods` VALUES ('88', '祝福水', '2');
INSERT INTO `goods` VALUES ('88', '凌霄翼盔', '6');
INSERT INTO `goods` VALUES ('88', '萧炎', '7');
INSERT INTO `goods` VALUES ('88', '萧炎魂魄', '9');
INSERT INTO `goods` VALUES ('88', '玲珑塔碎片三', '10');
INSERT INTO `goods` VALUES ('89', '幸运石', '2');
INSERT INTO `goods` VALUES ('89', '虹影面甲', '6');
INSERT INTO `goods` VALUES ('89', '小医仙', '7');
INSERT INTO `goods` VALUES ('89', '小医仙魂魄', '9');
INSERT INTO `goods` VALUES ('89', '玲珑塔碎片四', '10');
INSERT INTO `goods` VALUES ('90', '祥瑞珠', '2');
INSERT INTO `goods` VALUES ('90', '苍穹羽冠', '6');
INSERT INTO `goods` VALUES ('90', '青鳞', '7');
INSERT INTO `goods` VALUES ('90', '青鳞魂魄', '9');
INSERT INTO `goods` VALUES ('90', '凤舞铃碎片一', '10');
INSERT INTO `goods` VALUES ('91', '神魂凭证', '2');
INSERT INTO `goods` VALUES ('91', '幻星头巾', '6');
INSERT INTO `goods` VALUES ('91', '云韵', '7');
INSERT INTO `goods` VALUES ('91', '云韵魂魄', '9');
INSERT INTO `goods` VALUES ('91', '凤舞铃碎片二', '10');
INSERT INTO `goods` VALUES ('92', '万魂冠', '6');
INSERT INTO `goods` VALUES ('92', '林修崖', '7');
INSERT INTO `goods` VALUES ('92', '林修崖魂魄', '9');
INSERT INTO `goods` VALUES ('92', '凤舞铃碎片三', '10');
INSERT INTO `goods` VALUES ('93', '紫芒头盔', '6');
INSERT INTO `goods` VALUES ('93', '林焱', '7');
INSERT INTO `goods` VALUES ('93', '林焱魂魄', '9');
INSERT INTO `goods` VALUES ('93', '凤舞铃碎片四', '10');
INSERT INTO `goods` VALUES ('94', '海波东', '7');
INSERT INTO `goods` VALUES ('94', '海波东魂魄', '9');
INSERT INTO `goods` VALUES ('94', '断魂符碎片一', '10');
INSERT INTO `goods` VALUES ('95', '圣灵头盔', '6');
INSERT INTO `goods` VALUES ('95', '纳兰嫣然', '7');
INSERT INTO `goods` VALUES ('95', '纳兰嫣然魂魄', '9');
INSERT INTO `goods` VALUES ('95', '断魂符碎片二', '10');
INSERT INTO `goods` VALUES ('96', '锈色之冠', '6');
INSERT INTO `goods` VALUES ('96', '断魂符碎片三', '10');
INSERT INTO `goods` VALUES ('97', '灭焰巨盔', '6');
INSERT INTO `goods` VALUES ('97', '凤清儿', '7');
INSERT INTO `goods` VALUES ('97', '凤清儿魂魄', '9');
INSERT INTO `goods` VALUES ('97', '定魂铃碎片一', '10');
INSERT INTO `goods` VALUES ('98', '魂风', '7');
INSERT INTO `goods` VALUES ('98', '魂风魂魄', '9');
INSERT INTO `goods` VALUES ('98', '定魂铃碎片二', '10');
INSERT INTO `goods` VALUES ('99', '血皇之冠', '6');
INSERT INTO `goods` VALUES ('99', '魂玉', '7');
INSERT INTO `goods` VALUES ('99', '魂玉魂魄', '9');
INSERT INTO `goods` VALUES ('99', '定魂铃碎片三', '10');
INSERT INTO `goods` VALUES ('100', '并蒂莲', '2');
INSERT INTO `goods` VALUES ('100', '海神头盔', '6');
INSERT INTO `goods` VALUES ('100', '莫天行', '7');
INSERT INTO `goods` VALUES ('100', '莫天行魂魄', '9');
INSERT INTO `goods` VALUES ('100', '法宝银片碎片一', '10');
INSERT INTO `goods` VALUES ('101', '相思豆', '2');
INSERT INTO `goods` VALUES ('101', '鹰山老人', '7');
INSERT INTO `goods` VALUES ('101', '鹰山老人魂魄', '9');
INSERT INTO `goods` VALUES ('101', '法宝银片碎片二', '10');
INSERT INTO `goods` VALUES ('102', '合欢锁', '2');
INSERT INTO `goods` VALUES ('102', '百战盔', '6');
INSERT INTO `goods` VALUES ('102', '法宝银片碎片三', '10');
INSERT INTO `goods` VALUES ('103', '连理枝', '2');
INSERT INTO `goods` VALUES ('103', '铁龙面甲', '6');
INSERT INTO `goods` VALUES ('103', '金石', '7');
INSERT INTO `goods` VALUES ('103', '金石魂魄', '9');
INSERT INTO `goods` VALUES ('103', '法宝金片碎片一', '10');
INSERT INTO `goods` VALUES ('104', '一线牵', '2');
INSERT INTO `goods` VALUES ('104', '祥瑞天斧', '6');
INSERT INTO `goods` VALUES ('104', '雷尊者', '7');
INSERT INTO `goods` VALUES ('104', '雷尊者魂魄', '9');
INSERT INTO `goods` VALUES ('104', '法宝金片碎片二', '10');
INSERT INTO `goods` VALUES ('105', '同心结', '2');
INSERT INTO `goods` VALUES ('105', '祥瑞丝甲', '6');
INSERT INTO `goods` VALUES ('105', '剑尊者', '7');
INSERT INTO `goods` VALUES ('105', '剑尊者魂魄', '9');
INSERT INTO `goods` VALUES ('105', '法宝金片碎片三', '10');
INSERT INTO `goods` VALUES ('106', '红玫瑰', '2');
INSERT INTO `goods` VALUES ('106', '祥瑞玉髓', '6');
INSERT INTO `goods` VALUES ('106', '黄泉尊者', '7');
INSERT INTO `goods` VALUES ('106', '黄泉尊者魂魄', '9');
INSERT INTO `goods` VALUES ('106', '法宝金片碎片四', '10');
INSERT INTO `goods` VALUES ('107', '永恒钻戒', '2');
INSERT INTO `goods` VALUES ('107', '祥瑞灵盔', '6');
INSERT INTO `goods` VALUES ('107', '青海尊者', '7');
INSERT INTO `goods` VALUES ('107', '青海尊者魂魄', '9');
INSERT INTO `goods` VALUES ('107', '帝魂决碎片一', '10');
INSERT INTO `goods` VALUES ('108', '鸾凤古琴', '2');
INSERT INTO `goods` VALUES ('108', '帝魂决碎片二', '10');
INSERT INTO `goods` VALUES ('109', '巧克力', '2');
INSERT INTO `goods` VALUES ('109', '邙天尺', '7');
INSERT INTO `goods` VALUES ('109', '邙天尺魂魄', '9');
INSERT INTO `goods` VALUES ('109', '帝魂决碎片三', '10');
INSERT INTO `goods` VALUES ('110', '曜天火', '7');
INSERT INTO `goods` VALUES ('110', '曜天火魂魄', '9');
INSERT INTO `goods` VALUES ('110', '帝魂决碎片四', '10');
INSERT INTO `goods` VALUES ('111', '地魔老鬼', '7');
INSERT INTO `goods` VALUES ('111', '地魔老鬼魂魄', '9');
INSERT INTO `goods` VALUES ('111', '帝魂决碎片五', '10');
INSERT INTO `goods` VALUES ('112', '风尊者', '7');
INSERT INTO `goods` VALUES ('112', '风尊者魂魄', '9');
INSERT INTO `goods` VALUES ('112', '帝魂决碎片六', '10');
INSERT INTO `goods` VALUES ('113', '灭魂碑碎片一', '10');
INSERT INTO `goods` VALUES ('114', '灭魂碑碎片二', '10');
INSERT INTO `goods` VALUES ('115', '冰尊者', '7');
INSERT INTO `goods` VALUES ('115', '冰尊者', '9');
INSERT INTO `goods` VALUES ('115', '灭魂碑碎片三', '10');
INSERT INTO `goods` VALUES ('116', '玄衣', '7');
INSERT INTO `goods` VALUES ('116', '玄衣', '9');
INSERT INTO `goods` VALUES ('116', '灭魂碑碎片四', '10');
INSERT INTO `goods` VALUES ('117', '灭魂碑碎片五', '10');
INSERT INTO `goods` VALUES ('118', '灭魂碑碎片六', '10');
INSERT INTO `goods` VALUES ('132', '摘星老鬼', '7');
INSERT INTO `goods` VALUES ('132', '摘星老鬼魂魄', '9');
INSERT INTO `goods` VALUES ('133', '慕骨老人', '7');
INSERT INTO `goods` VALUES ('133', '慕骨老人魂魄', '9');
INSERT INTO `goods` VALUES ('135', '药老', '7');
INSERT INTO `goods` VALUES ('135', '药老魂魄', '9');
INSERT INTO `goods` VALUES ('136', '萧薰儿', '7');
INSERT INTO `goods` VALUES ('136', '萧薰儿魂魄', '9');
INSERT INTO `goods` VALUES ('139', '紫妍', '7');
INSERT INTO `goods` VALUES ('139', '紫妍魂魄', '9');
INSERT INTO `goods` VALUES ('144', '美杜莎', '7');
INSERT INTO `goods` VALUES ('144', '美杜莎魂魄', '9');
INSERT INTO `goods` VALUES ('148', null, '0');
INSERT INTO `goods` VALUES ('148', '魂灭生', '7');
INSERT INTO `goods` VALUES ('148', '魂灭生魂魄', '9');
INSERT INTO `goods` VALUES ('200', '玄重尺碎片', '2');
INSERT INTO `goods` VALUES ('206', '万魂剑碎片', '2');
INSERT INTO `goods` VALUES ('207', '骨皇刀碎片', '2');
INSERT INTO `goods` VALUES ('211', '寒冰魔枪碎片', '2');
INSERT INTO `goods` VALUES ('212', '紫月玄扇碎片', '2');
INSERT INTO `goods` VALUES ('218', '乾坤天斧碎片', '2');
INSERT INTO `goods` VALUES ('220', '月牙戟枪碎片', '2');
INSERT INTO `goods` VALUES ('222', '炼狱魔刀碎片', '2');
INSERT INTO `goods` VALUES ('231', '万魂冥甲碎片', '2');
INSERT INTO `goods` VALUES ('232', '骨皇圣衣碎片', '2');
INSERT INTO `goods` VALUES ('233', '玄重天甲碎片', '2');
INSERT INTO `goods` VALUES ('234', '修罗罡铠碎片', '2');
INSERT INTO `goods` VALUES ('235', '幻冥皇铠碎片', '2');
INSERT INTO `goods` VALUES ('238', '天魔火甲碎片', '2');
INSERT INTO `goods` VALUES ('240', '天玉战铠碎片', '2');
INSERT INTO `goods` VALUES ('241', '落英神铠碎片', '2');
INSERT INTO `goods` VALUES ('254', '玄重心链碎片', '2');
INSERT INTO `goods` VALUES ('256', '骨皇幽珠碎片', '2');
INSERT INTO `goods` VALUES ('257', '幽鬼项链碎片', '2');
INSERT INTO `goods` VALUES ('258', '天机锁链碎片', '2');
INSERT INTO `goods` VALUES ('260', '万魂灵玉碎片', '2');
INSERT INTO `goods` VALUES ('263', '雪魂丝链碎片', '2');
INSERT INTO `goods` VALUES ('264', '魔狱项链碎片', '2');
INSERT INTO `goods` VALUES ('265', '龙吟项链碎片', '2');
INSERT INTO `goods` VALUES ('266', '玄重帝冠碎片', '2');
INSERT INTO `goods` VALUES ('267', '骨皇之冕碎片', '2');
INSERT INTO `goods` VALUES ('268', '青炙头盔碎片', '2');
INSERT INTO `goods` VALUES ('269', '凌霄翼盔碎片', '2');
INSERT INTO `goods` VALUES ('270', '虹影面甲碎片', '2');
INSERT INTO `goods` VALUES ('273', '万魂冠碎片', '2');
INSERT INTO `goods` VALUES ('276', '圣灵头盔碎片', '2');
INSERT INTO `goods` VALUES ('277', '锈色之冠碎片', '2');
INSERT INTO `goods` VALUES ('278', '祥瑞天斧碎片', '2');
INSERT INTO `goods` VALUES ('279', '祥瑞丝甲碎片', '2');
INSERT INTO `goods` VALUES ('280', '祥瑞玉髓碎片', '2');
INSERT INTO `goods` VALUES ('281', '祥瑞灵盔碎片', '2');
INSERT INTO `goods` VALUES ('300', '超级境界丹', '2');
INSERT INTO `goods` VALUES ('301', '超级进阶石', '2');
INSERT INTO `goods` VALUES ('302', '灵宝精魄', '2');
INSERT INTO `goods` VALUES ('303', '万魂之源', '2');
INSERT INTO `goods` VALUES ('304', '异火灵石', '2');
INSERT INTO `goods` VALUES ('305', '神羽强化石', '2');
INSERT INTO `goods` VALUES ('306', '一阶神羽碎片', '2');
INSERT INTO `goods` VALUES ('307', '二阶神羽碎片', '2');
INSERT INTO `goods` VALUES ('308', '三阶神羽碎片', '2');
INSERT INTO `goods` VALUES ('400', '噬血甲', '12');
INSERT INTO `goods` VALUES ('401', '风雷祭', '12');
INSERT INTO `goods` VALUES ('402', '丹融天', '12');
INSERT INTO `goods` VALUES ('403', '黑极崩劲', '12');
INSERT INTO `goods` VALUES ('404', '毁灭之印', '12');
INSERT INTO `goods` VALUES ('405', '空间绞杀', '12');
INSERT INTO `goods` VALUES ('500', 'Q群礼包', '2');
INSERT INTO `goods` VALUES ('501', '公会礼包', '2');
INSERT INTO `goods` VALUES ('502', '渠道礼包', '2');
INSERT INTO `goods` VALUES ('503', '体验礼包1', '2');
INSERT INTO `goods` VALUES ('504', '体验礼包2', '2');
INSERT INTO `goods` VALUES ('505', '体验礼包3', '2');
INSERT INTO `goods` VALUES ('506', '体验礼包4', '2');
INSERT INTO `goods` VALUES ('507', '体验礼包5', '2');
INSERT INTO `goods` VALUES ('508', '体验礼包6', '2');
INSERT INTO `goods` VALUES ('509', '体验礼包7', '2');
INSERT INTO `goods` VALUES ('510', '体验礼包8', '2');
INSERT INTO `goods` VALUES ('511', '体验礼包9', '2');
INSERT INTO `goods` VALUES ('512', '体验礼包10', '2');
INSERT INTO `goods` VALUES ('513', '体验礼包11', '2');
INSERT INTO `goods` VALUES ('514', '体验礼包12', '2');
INSERT INTO `goods` VALUES ('515', '体验礼包13', '2');
INSERT INTO `goods` VALUES ('516', '体验礼包14', '2');
INSERT INTO `goods` VALUES ('517', '体验礼包15', '2');
INSERT INTO `goods` VALUES ('1000', '极品橙色功法礼盒', '2');
INSERT INTO `goods` VALUES ('1001', '橙色功法礼盒', '2');
INSERT INTO `goods` VALUES ('1002', '紫色功法礼盒', '2');
INSERT INTO `goods` VALUES ('1003', '极品橙色法宝礼盒', '2');
INSERT INTO `goods` VALUES ('1004', '橙色法宝礼盒', '2');
INSERT INTO `goods` VALUES ('1005', '紫色法宝礼盒', '2');
INSERT INTO `goods` VALUES ('1006', '橙色英雄礼盒', '2');
INSERT INTO `goods` VALUES ('1007', '紫色英雄礼盒', '2');
INSERT INTO `goods` VALUES ('1008', '橙色装备礼盒', '2');
INSERT INTO `goods` VALUES ('1009', '紫色装备礼盒', '2');
INSERT INTO `goods` VALUES ('1010', '魔核礼盒', '2');
INSERT INTO `goods` VALUES ('1011', '元宝礼盒', '2');
INSERT INTO `goods` VALUES ('1012', '普天同庆礼盒', '2');
INSERT INTO `goods` VALUES ('1013', '至尊礼盒', '2');
INSERT INTO `goods` VALUES ('1014', '祥瑞套装礼盒', '2');
INSERT INTO `goods` VALUES ('1015', '重阳礼盒', '2');
INSERT INTO `goods` VALUES ('1016', '陨落心炎宝盒', '2');
INSERT INTO `goods` VALUES ('1017', '超级进阶宝盒', '2');
INSERT INTO `goods` VALUES ('1018', '海心焰宝盒', '2');
INSERT INTO `goods` VALUES ('1019', '青莲地心火宝盒', '2');
INSERT INTO `goods` VALUES ('1020', '斗魂宝盒', '2');
INSERT INTO `goods` VALUES ('1021', '玄重套装', '2');
INSERT INTO `goods` VALUES ('1022', '骨皇套装', '2');
INSERT INTO `goods` VALUES ('1200', '幸运礼盒', '2');

-- ----------------------------
-- Table structure for menucom
-- ----------------------------
DROP TABLE IF EXISTS `menucom`;
CREATE TABLE `menucom` (
  `id` int(11) NOT NULL,
  `name` varchar(20) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `parent_node` int(11) DEFAULT NULL COMMENT '父节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menucom
-- ----------------------------
INSERT INTO `menucom` VALUES ('1', '运营管理', null, '0');
INSERT INTO `menucom` VALUES ('2', '客服管理', null, '0');
INSERT INTO `menucom` VALUES ('3', '运维管理', null, '0');
INSERT INTO `menucom` VALUES ('4', '系统管理', null, '0');
INSERT INTO `menucom` VALUES ('5', '活动管理', null, '0');
INSERT INTO `menucom` VALUES ('11', '玩家帐号', 'jump(\'wjzh\')', '1');
INSERT INTO `menucom` VALUES ('12', '更新字典表', 'jump(\'yxhd\')', '1');
INSERT INTO `menucom` VALUES ('13', '冻结账号', 'jump(\'djzh\')', '1');
INSERT INTO `menucom` VALUES ('14', '单人发放物品', 'dopost(\'goods!selestGoods.action\')', '1');
INSERT INTO `menucom` VALUES ('15', '跑马灯管理', 'jump(\'pmdgl\')', '1');
INSERT INTO `menucom` VALUES ('16', '活动开关', 'jump(\'hdkg\')', '5');
INSERT INTO `menucom` VALUES ('17', '全服发放物品', 'dopost(\'goods!selestGoodsAll.action\')', '1');
INSERT INTO `menucom` VALUES ('21', '玩家帐号', 'jump(\'wjzh\')', '2');
INSERT INTO `menucom` VALUES ('31', '分区配置', 'dopost(\'server!showServerList.action\')', '3');
INSERT INTO `menucom` VALUES ('32', '数据相关', 'jump(\'sjxg\')', '3');
INSERT INTO `menucom` VALUES ('33', '配置相关', 'jump(\'pzxg\')', '3');
INSERT INTO `menucom` VALUES ('41', '账户管理', 'dopost(\'user!findUserAll.action\')', '4');
INSERT INTO `menucom` VALUES ('42', '权限管理', 'dopost(\'user!findQxgl.action\')', '4');
INSERT INTO `menucom` VALUES ('43', '帮助', 'jump(\'help\')', '4');
INSERT INTO `menucom` VALUES ('44', '禁言账号', 'jump(\'jjzh\')', '1');
INSERT INTO `menucom` VALUES ('45', '配置活动周期', 'jump(\'zhouhuodong\')', '5');
INSERT INTO `menucom` VALUES ('46', '配置巅峰强者排行奖励', 'jump(\'dianfengqz\')', '5');
INSERT INTO `menucom` VALUES ('47', '配置限时英雄排行奖励', 'jump(\'xianshiyxph\')', '5');
INSERT INTO `menucom` VALUES ('48', '配置限时英雄积分奖励', 'jump(\'xianshiyxjf\')', '5');
INSERT INTO `menucom` VALUES ('49', '配置招财进宝', 'jump(\'zhaocaijb\')', '5');
INSERT INTO `menucom` VALUES ('50', '配置累计消耗', 'jump(\'leijixh\')', '5');
INSERT INTO `menucom` VALUES ('51', '配置整点抢', 'jump(\'zhengdianq\')', '5');
INSERT INTO `menucom` VALUES ('52', '配置转盘物品数据', 'jump(\'zhuanpansj\')', '5');
INSERT INTO `menucom` VALUES ('53', '配置转盘排行奖励', 'jump(\'zhuanpanph\')', '5');
INSERT INTO `menucom` VALUES ('54', '配置每日特惠', 'jump(\'meirith\')', '5');
INSERT INTO `menucom` VALUES ('55', '配置限时抢购', 'jump(\'xianshiqg\')', '5');
INSERT INTO `menucom` VALUES ('56', '配置累计充值', 'jump(\'leijicz\')', '5');
INSERT INTO `menucom` VALUES ('57', '配置天魂墓地活动副本', 'jump(\'tianhunmd\')', '5');
INSERT INTO `menucom` VALUES ('58', '配置资源矿特殊奖励', 'jump(\'mineboxthing\')', '5');
INSERT INTO `menucom` VALUES ('59', '配置世界BOSS奖励', 'jump(\'worldBoss\')', '5');
INSERT INTO `menucom` VALUES ('60', '配置节假日活动', 'jump(\'jiejiari\')', '5');
INSERT INTO `menucom` VALUES ('61', '配置屠魔商店', 'jump(\'tumosd\')', '5');
INSERT INTO `menucom` VALUES ('62', '物品查询', null, '2');
INSERT INTO `menucom` VALUES ('1000', '冻结账号', 'jump(\'djzh\')', '2');
INSERT INTO `menucom` VALUES ('1001', '单人发放物品', 'dopost(\'goods!selestGoods.action\')', '2');
INSERT INTO `menucom` VALUES ('1002', '全服发放物品', 'dopost(\'goods!selestGoodsAll.action\')', '2');
INSERT INTO `menucom` VALUES ('1003', '跑马灯管理', 'jump(\'pmdgl\')', '2');
INSERT INTO `menucom` VALUES ('1004', '禁言账号', 'jump(\'jjzh\')', '2');
INSERT INTO `menucom` VALUES ('1005', '其他管理', 'jump(\'pzxgKF\')', '2');

-- ----------------------------
-- Table structure for modulecom
-- ----------------------------
DROP TABLE IF EXISTS `modulecom`;
CREATE TABLE `modulecom` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of modulecom
-- ----------------------------
INSERT INTO `modulecom` VALUES ('1', '运营管理');
INSERT INTO `modulecom` VALUES ('2', '客服管理');
INSERT INTO `modulecom` VALUES ('3', '运维管理');
INSERT INTO `modulecom` VALUES ('4', '系统管理');

-- ----------------------------
-- Table structure for server
-- ----------------------------
DROP TABLE IF EXISTS `server`;
CREATE TABLE `server` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `servername` varchar(20) DEFAULT NULL,
  `serverip` varchar(20) DEFAULT NULL,
  `serverport` int(11) DEFAULT NULL,
  `dbname` varchar(20) DEFAULT NULL,
  `dbpassword` varchar(20) DEFAULT NULL,
  `dbip` varchar(20) DEFAULT NULL,
  `dbport` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of server
-- ----------------------------
INSERT INTO `server` VALUES ('1', '苗朋', '192.168.1.43', '20000', 'DBname', 'DBpass', '192.168.1.43', '3306');
INSERT INTO `server` VALUES ('3', '胡泽文', '192.168.1.42', '20000', 'db', 'db', '192.168.1.42', '3306');
INSERT INTO `server` VALUES ('4', '内网测试', '192.168.4.180', '20001', '', '', '', '0');
INSERT INTO `server` VALUES ('5', '外网QQ', '42.62.56.25', '20001', null, null, null, null);
INSERT INTO `server` VALUES ('6', '外网微信', '42.62.56.25', '20002', null, null, null, null);

-- ----------------------------
-- Table structure for TableType
-- ----------------------------
DROP TABLE IF EXISTS `TableType`;
CREATE TABLE `TableType` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of TableType
-- ----------------------------
INSERT INTO `TableType` VALUES ('1', '丹药');
INSERT INTO `TableType` VALUES ('2', '物品');
INSERT INTO `TableType` VALUES ('3', '金币/银币');
INSERT INTO `TableType` VALUES ('6', '装备');
INSERT INTO `TableType` VALUES ('7', '卡牌');
INSERT INTO `TableType` VALUES ('9', '魂魄');
INSERT INTO `TableType` VALUES ('10', '碎片');
INSERT INTO `TableType` VALUES ('11', '丹材');
INSERT INTO `TableType` VALUES ('13', '功法/法宝');
INSERT INTO `TableType` VALUES ('15', '异火字典表');
INSERT INTO `TableType` VALUES ('16', '异火碎片字典表');
INSERT INTO `TableType` VALUES ('17', '翅膀');
INSERT INTO `TableType` VALUES ('18', '斗魂');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `ip` varchar(20) DEFAULT NULL,
  `competenceid` varchar(200) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'huayigm0919', 'admin', null, '1,11,12,13,14,15,17,2,21,3,31,32,33,4,41,42,43,44,5,16,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62', null);
INSERT INTO `user` VALUES ('2', 'admin', 'admin', 'admin', '', '1,11,12,13,14,15,17,2,21,3,31,32,33,4,41,42,43,44,5,16,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62', null);
INSERT INTO `user` VALUES ('3', 'y2game01', 'y2game01', 'y2game01', '', '2,1000,1001,1003,1004,1002,1005', null);
INSERT INTO `user` VALUES ('4', 'test', 'test', 'test', '', '1,11,12,13,14,15,17,2,21,3,31,32,33,4,41,42,43,44,5,16,45,46', '');

-- ----------------------------
-- Table structure for user_com
-- ----------------------------
DROP TABLE IF EXISTS `user_com`;
CREATE TABLE `user_com` (
  `id` int(11) NOT NULL,
  `userid` int(11) DEFAULT NULL,
  `competenceid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_com
-- ----------------------------
