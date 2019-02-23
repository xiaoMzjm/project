# 表结构
-- --------------------------------
-- Table structure for `advertisement`
-- -------------------------------- 
DROP TABLE IF EXISTS `advertisement`; 
CREATE TABLE `advertisement` (
  `biz_key` varchar(64) NOT NULL COMMENT 'key',
  `pic_url` varchar(1024) DEFAULT NULL COMMENT '图片地址',
  `type` varchar(64) DEFAULT NULL COMMENT '类型',
  `id` varchar(64) DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`biz_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ;