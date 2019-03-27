/*
 Navicat Premium Data Transfer

 Source Server         : Terminal
 Source Server Type    : MySQL
 Source Server Version : 100135
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 100135
 File Encoding         : 65001

 Date: 27/03/2019 16:56:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for test_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `test_roles_permissions`;
CREATE TABLE `test_roles_permissions`  (
  `role_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `permission` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test_roles_permissions
-- ----------------------------
INSERT INTO `test_roles_permissions` VALUES ('admin', 'user:add');
INSERT INTO `test_roles_permissions` VALUES ('admin', 'user:delete');
INSERT INTO `test_roles_permissions` VALUES ('admin', 'admin:update');
INSERT INTO `test_roles_permissions` VALUES ('user', 'user:update');

-- ----------------------------
-- Table structure for test_user
-- ----------------------------
DROP TABLE IF EXISTS `test_user`;
CREATE TABLE `test_user`  (
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test_user
-- ----------------------------
INSERT INTO `test_user` VALUES ('sun', '123');

-- ----------------------------
-- Table structure for test_user_roles
-- ----------------------------
DROP TABLE IF EXISTS `test_user_roles`;
CREATE TABLE `test_user_roles`  (
  `username` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `role_name` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of test_user_roles
-- ----------------------------
INSERT INTO `test_user_roles` VALUES ('sun', 'admin');
INSERT INTO `test_user_roles` VALUES ('sun', 'user');

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles`  (
  `username` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `role_name` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('sun', 'admin');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `password` varchar(64) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('sun', '123');

SET FOREIGN_KEY_CHECKS = 1;
