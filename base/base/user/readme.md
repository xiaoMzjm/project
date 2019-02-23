# 表结构
-- --------------------------------
-- Table structure for `user`
-- --------------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(64) NOT NULL COMMENT 'id',
  `open_id` varchar(64) DEFAULT NULL COMMENT 'openId',
  `nick_name` varchar(64) DEFAULT NULL COMMENT 'nickName',
  `gender` varchar(64) DEFAULT NULL COMMENT 'gender',
  `language` varchar(64) DEFAULT NULL COMMENT 'language',
  `city` varchar(64) DEFAULT NULL COMMENT 'city',
  `province` varchar(64) DEFAULT NULL COMMENT 'province',
  `country` varchar(64) DEFAULT NULL COMMENT 'country',
  `avatar_url` varchar(256) DEFAULT NULL COMMENT 'avatarUrl',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `token` varchar(64) DEFAULT NULL COMMENT 'token',
  `code` varchar(64) DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;