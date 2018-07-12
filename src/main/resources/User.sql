/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 80001
Source Host           : localhost:3306
Source Database       : mmall

Target Server Type    : MYSQL
Target Server Version : 80001
File Encoding         : 65001

Date: 2018-07-12 22:09:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for mmall_user
-- ----------------------------
DROP TABLE IF EXISTS `mmall_user`;
CREATE TABLE `mmall_user` (
`id`  int(11) NOT NULL AUTO_INCREMENT COMMENT '用户表id' ,
`username`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名' ,
`password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码，MD5加密' ,
`email`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`phone`  varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL ,
`question`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '找回密码问题' ,
`answer`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '找回密码答案' ,
`role`  int(4) NOT NULL COMMENT '角色0-管理员,1-普通用户' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
`update_time`  datetime NOT NULL COMMENT '最后一次更新时间' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `user_name_unique` (`username`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of mmall_user
-- ----------------------------
BEGIN;
INSERT INTO `mmall_user` VALUES ('1', 'admin', 'FD7AE5D1D0052231BDADCE35CC7A75FE', 'admin@happymmall.com', '13800138000', '问题', '答案', '1', '2016-11-06 16:56:45', '2017-12-30 16:39:04'), ('13', 'geely', '', 'geely@happymmall.com', '13800138000', '问题', '答案', '0', '2016-11-19 22:19:25', '2016-11-19 22:19:25'), ('17', 'rosen', '095AC193FE2212EEC7A93E8FEFF11902', 'rosen1@happymmall.com', '13800138000', '问题', '答案', '0', '2017-03-17 10:51:33', '2017-04-09 23:13:26'), ('21', 'soonerbetter', 'DE6D76FE7C40D5A1A8F04213F2BEFBEE', 'test06@happymmall.com', '13800138000', '105204', '105204', '0', '2017-04-13 21:26:22', '2017-04-13 21:26:22'), ('22', 'aaa', 'AF926D18CBFD25236BB49985221CBF60', 'aaa', '15645321654', 'qwe', 'gfgr', '0', '2017-12-04 21:02:48', '2017-12-30 16:22:23'), ('23', 'bbbb', 'F5498381C9109429A612AE07C4D1D192', 'abdmin@happymmall.com', '1380138000', '问题', '答案', '0', '2018-02-21 15:45:28', '2018-02-21 15:45:28');
COMMIT;
