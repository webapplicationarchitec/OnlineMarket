/*
Navicat MySQL Data Transfer

Source Server         : maxrank2020
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : onlineecom_maxrank

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-05-02 16:09:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL DEFAULT '',
  `password` varchar(255) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `shippingAddress` varchar(254) DEFAULT NULL,
  `billingAddress` varchar(254) DEFAULT NULL,
  `payment` varchar(254) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `username` (`username`),
  KEY `username_2` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'admin', 'admin', 'Admin', 'admin@admin', '', null, null, '1');
INSERT INTO `account` VALUES ('2', 'buyer', 'buyer', 'Buyer', 'buyer@maxrank', '4th, 52557, Fairfield, IA,USA', '4th, 52557, Fairfield, IA,USA', 'DebitCard', '3');
INSERT INTO `account` VALUES ('3', 'seller', 'seller', 'Seller', 'seller@maxrank', '4th, 52557, Fairfield, IA,USA', '4th, 52557, Fairfield, IA,USA', null, '2');

-- ----------------------------
-- Table structure for `buyerpoints`
-- ----------------------------
DROP TABLE IF EXISTS `buyerpoints`;
CREATE TABLE `buyerpoints` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyerid` varchar(255) NOT NULL,
  `sellerid` varchar(255) NOT NULL,
  `point` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `FK_BP_Buy` (`buyerid`),
  KEY `FK_BP_Sel` (`sellerid`),
  CONSTRAINT `FK_BP_Buy` FOREIGN KEY (`buyerid`) REFERENCES `account` (`username`),
  CONSTRAINT `FK_BP_Sel` FOREIGN KEY (`sellerid`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of buyerpoints
-- ----------------------------

-- ----------------------------
-- Table structure for `category`
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'Computer& Laptop');
INSERT INTO `category` VALUES ('2', 'Digital Cameras');
INSERT INTO `category` VALUES ('3', 'Smart Phones');
INSERT INTO `category` VALUES ('4', 'Televisions');
INSERT INTO `category` VALUES ('5', 'Audio');
INSERT INTO `category` VALUES ('6', 'Smart Watches');
INSERT INTO `category` VALUES ('7', '');

-- ----------------------------
-- Table structure for `followers`
-- ----------------------------
DROP TABLE IF EXISTS `followers`;
CREATE TABLE `followers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buyerid` varchar(20) NOT NULL,
  `sellerid` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_FoSel` (`buyerid`),
  KEY `FK_FoBuy` (`sellerid`),
  CONSTRAINT `FK_FoBuy` FOREIGN KEY (`sellerid`) REFERENCES `account` (`username`),
  CONSTRAINT `FK_FoSel` FOREIGN KEY (`buyerid`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of followers
-- ----------------------------

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderNO` varchar(255) NOT NULL,
  `created_date` date DEFAULT NULL,
  `shipping_date` date DEFAULT NULL,
  `delivered_date` date DEFAULT NULL,
  `tax` int(11) DEFAULT NULL,
  `shippingfee` double DEFAULT NULL,
  `total` double DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL COMMENT '1 New, 2 Shipped, 3 On The Way, 4 Delivered, 5 Cancel',
  `createby` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `orderNO` (`orderNO`),
  KEY `FK_OBuy` (`createby`),
  CONSTRAINT `FK_OBuy` FOREIGN KEY (`createby`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------

-- ----------------------------
-- Table structure for `orderdetail`
-- ----------------------------
DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL,
  `orderid` varchar(255) DEFAULT NULL,
  `productid` int(11) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `qty` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ODO` (`orderid`),
  KEY `FK_ODP` (`productid`),
  CONSTRAINT `FK_ODO` FOREIGN KEY (`orderid`) REFERENCES `order` (`orderNO`),
  CONSTRAINT `FK_ODP` FOREIGN KEY (`productid`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `product`
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` text DEFAULT NULL,
  `price` double DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `status` tinyint(11) DEFAULT NULL COMMENT '1 New, 2 InStock, 3 OutOfStock',
  `catid` int(11) DEFAULT NULL,
  `createby` varchar(20) DEFAULT NULL,
  `createdate` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Pro_Cat` (`catid`),
  KEY `FK_Pro_Sell` (`createby`),
  CONSTRAINT `FK_Pro_Cat` FOREIGN KEY (`catid`) REFERENCES `category` (`id`),
  CONSTRAINT `FK_Pro_Sell` FOREIGN KEY (`createby`) REFERENCES `account` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for `review`
-- ----------------------------
DROP TABLE IF EXISTS `review`;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `productid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT 1 COMMENT '1 New, 2 Approved, 3 Rejected',
  `comment` text DEFAULT NULL,
  `createddate` date DEFAULT NULL,
  `createdby` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ReviewProduct` (`productid`),
  KEY `FK_RevewBuyer` (`createdby`),
  CONSTRAINT `FK_RevewBuyer` FOREIGN KEY (`createdby`) REFERENCES `account` (`username`),
  CONSTRAINT `FK_ReviewProduct` FOREIGN KEY (`productid`) REFERENCES `product` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of review
-- ----------------------------
