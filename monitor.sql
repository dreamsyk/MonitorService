/*
Navicat MySQL Data Transfer

Source Server         : VM_DESKTOP
Source Server Version : 50546
Source Host           : 192.168.1.188:3306
Source Database       : monitor

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2015-12-17 15:31:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cpu
-- ----------------------------
DROP TABLE IF EXISTS `cpu`;
CREATE TABLE `cpu` (
  `time` mediumtext,
  `use_prec` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Table structure for memory
-- ----------------------------
DROP TABLE IF EXISTS `memory`;
CREATE TABLE `memory` (
  `time` mediumtext,
  `use_prec` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
