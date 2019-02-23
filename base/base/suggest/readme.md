# 表结构
-- --------------------------------
-- Table structure for `suggest`
-- --------------------------------
DROP TABLE IF EXISTS `suggest`;
CREATE TABLE `suggest` (
  `id` varchar(64) NOT NULL COMMENT '唯一标识',
  `gmt_create` datetime NOT NULL COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `deleted` tinyint NOT NULL COMMENT '是否删除：1删除，0非删除',
  `features` varchar(1024) DEFAULT NULL COMMENT '扩展字段',
  `status` tinyint NOT NULL COMMENT '状态：1创建，2已回复',

  `biz_key` varchar(64) NOT NULL COMMENT '业务身份',
  `type` varchar(64) DEFAULT NULL COMMENT '类型：1遇到问题，2新功能建议',
  `suggest` varchar(4096) NOT NULL COMMENT '反馈或建议',
  `user_id` varchar(64) NOT NULL COMMENT '用户ID',
  `reply` varchar(4096) DEFAULT NULL COMMENT '回复',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;

CREATE TABLE `suggest_detail` (
  `id` varchar(64) NOT NULL COMMENT '唯一标识',
  `suggest_id` varchar(64) DEFAULT NULL COMMENT '主单ID',
  `pic_url` varchar(4096) DEFAULT NULL COMMENT '图片地址',
  `video_url` varchar(4096) DEFAULT NULL COMMENT '视频地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;