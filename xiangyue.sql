/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50515
Source Host           : localhost:3306
Source Database       : xiangyue

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2018-04-16 21:49:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `consume`
-- ----------------------------
DROP TABLE IF EXISTS `consume`;
CREATE TABLE `consume` (
  `consume_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员消费记录id',
  `member_card` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '会员卡号',
  `desk_id` int(11) NOT NULL COMMENT '桌台编号',
  `good_id` int(11) NOT NULL COMMENT '商品id',
  `consume_time` datetime NOT NULL COMMENT '会员消费时间',
  `consume_total` double NOT NULL COMMENT '会员消费总价',
  PRIMARY KEY (`consume_id`),
  KEY `fk_consume_desk_id` (`desk_id`),
  KEY `fk_consume_goods_id` (`good_id`),
  KEY `fk_consume_member_card` (`member_card`) USING BTREE,
  CONSTRAINT `fk_consume_desk_id` FOREIGN KEY (`desk_id`) REFERENCES `desk` (`desk_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_consume_goods_id` FOREIGN KEY (`good_id`) REFERENCES `goods` (`good_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_consume_member_card` FOREIGN KEY (`member_card`) REFERENCES `member` (`member_card`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of consume
-- ----------------------------

-- ----------------------------
-- Table structure for `desk`
-- ----------------------------
DROP TABLE IF EXISTS `desk`;
CREATE TABLE `desk` (
  `desk_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '桌子id',
  `desk_type` int(11) NOT NULL COMMENT '桌子_类型',
  `desk_number` int(11) NOT NULL COMMENT '座位号',
  `desk_state` int(11) NOT NULL COMMENT '桌子状态',
  PRIMARY KEY (`desk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of desk
-- ----------------------------
INSERT INTO `desk` VALUES ('1', '4', '1', '0');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `good_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `good_number` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品编号',
  `good_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品名称',
  `good_type` int(11) NOT NULL,
  `good_price` double NOT NULL COMMENT '商品价格',
  `good_stock` int(11) NOT NULL COMMENT '商品数量',
  PRIMARY KEY (`good_id`),
  KEY `fk_good_type` (`good_type`),
  CONSTRAINT `fk_good_type` FOREIGN KEY (`good_type`) REFERENCES `goods_type` (`type_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of goods
-- ----------------------------

-- ----------------------------
-- Table structure for `goods_type`
-- ----------------------------
DROP TABLE IF EXISTS `goods_type`;
CREATE TABLE `goods_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类型编号',
  `type_name` varchar(255) NOT NULL COMMENT '商品类型名称',
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_type
-- ----------------------------

-- ----------------------------
-- Table structure for `income`
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `income_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '收益编号',
  `income_total` double NOT NULL COMMENT '收益金额',
  `income_data` datetime NOT NULL COMMENT '收益时间',
  PRIMARY KEY (`income_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of income
-- ----------------------------

-- ----------------------------
-- Table structure for `manager`
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `manager_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `manager_username` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '用户名',
  `manager_password` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '密码',
  `manager_power` int(11) NOT NULL COMMENT '权限',
  PRIMARY KEY (`manager_id`),
  KEY `fk_manage_power` (`manager_power`),
  CONSTRAINT `fk_manage_power` FOREIGN KEY (`manager_power`) REFERENCES `type` (`power_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'admin', 'admin', '0');
INSERT INTO `manager` VALUES ('2', '实打实的', '333333', '0');
INSERT INTO `manager` VALUES ('6', '深大', '呜呜呜呜呜呜', '5');

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员id',
  `member_card` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '会员卡号',
  `member_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '会员名字',
  `member_password` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '会员密码',
  `member_phone` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '会员手机号码',
  `member_ingral` int(11) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  KEY `member_card` (`member_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for `type`
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type` (
  `power_id` int(11) NOT NULL AUTO_INCREMENT,
  `power_depict` varchar(255) NOT NULL,
  PRIMARY KEY (`power_id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES ('0', '系统管理员');
INSERT INTO `type` VALUES ('5', '餐厅管理员');
INSERT INTO `type` VALUES ('6', 'sd');
INSERT INTO `type` VALUES ('7', 'sdsds');
INSERT INTO `type` VALUES ('11', 'dasdasd');
INSERT INTO `type` VALUES ('12', 'asdasd');
INSERT INTO `type` VALUES ('13', 'sdasda');
INSERT INTO `type` VALUES ('14', 'adsasda');
INSERT INTO `type` VALUES ('15', '33333333333');
INSERT INTO `type` VALUES ('16', '3444');
INSERT INTO `type` VALUES ('17', '343434');
INSERT INTO `type` VALUES ('18', '34322');
INSERT INTO `type` VALUES ('19', '324');

-- ----------------------------
-- Table structure for `purchase`
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品采购记录id',
  `purchase_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '商品采购的名称',
  `purchase_price` double NOT NULL COMMENT '商品采购的单价',
  `purchase_number` int(11) NOT NULL COMMENT '商品采购的数量',
  `purchase_time` datetime NOT NULL COMMENT '商品采购的时间',
  `purchase_total` double NOT NULL COMMENT '商品采购的总价',
  PRIMARY KEY (`purchase_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of purchase
-- ----------------------------
INSERT INTO `purchase` VALUES ('1', '鸡蛋', '0.5', '500', '2018-03-11 11:38:00', '250');

-- ----------------------------
-- Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_series` int(11) NOT NULL,
  `menu_power` int(11) NOT NULL,
  `menu_name` varchar(255) NOT NULL,
  `menu_uri` varchar(255) DEFAULT NULL,
  `menu_ico` varchar(255) DEFAULT NULL,
  `type` int(11) NOT NULL,
  PRIMARY KEY (`menu_id`),
  KEY `fk_power` (`menu_power`),
  CONSTRAINT `fk_power` FOREIGN KEY (`menu_power`) REFERENCES `type` (`power_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '0', '餐厅管理', null, null, '0');
INSERT INTO `sys_menu` VALUES ('2', '0', '0', '商品管理', '', 'fa fa-cutlery', '0');
INSERT INTO `sys_menu` VALUES ('3', '0', '0', '系统管理', '', 'fa fa-gear', '0');
INSERT INTO `sys_menu` VALUES ('4', '3', '0', '菜单管理', '/sys/menu/menu', 'fa fa-navicon', '1');
INSERT INTO `sys_menu` VALUES ('9', '3', '0', '角色管理', '/sys/type/type', '', '1');
INSERT INTO `sys_menu` VALUES ('12', '3', '0', '用户管理', '/sys/user/user', '', '1');
