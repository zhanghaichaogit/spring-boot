SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `boot_user`
-- ----------------------------
DROP TABLE IF EXISTS `boot_user`;
CREATE TABLE `boot_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `tel` varchar(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of boot_user
-- ----------------------------
INSERT INTO `boot_user` VALUES ('1', 'klay', '13799008800', '2016-06-27 00:01:39');
INSERT INTO `boot_user` VALUES ('2', 'Tome', '18988991234', '2016-06-27 00:35:28');
