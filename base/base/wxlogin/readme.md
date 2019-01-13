# 微信小程序登录独立模块
本模块是一个web+dao模块，引入该模块，即拥有微信登录的功能

## 1.依赖数据库表
```
/*
TreeSoft Data Transfer For MySQL
Source Server         : 127.0.0.1
Source Server Version : 8.0.11
Source Host           : 127.0.0.1:3306
Source Database       : test
web site: www.treesoft.cn
Date: 2019-01-14 00:12:48
*/

SET FOREIGN_KEY_CHECKS=0;
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

```

## 2.pom文件引入该模块
```
<dependency>
    <groupId>com.zjm</groupId>
    <artifactId>wxlogin</artifactId>
</dependency>
```

## 3.微信js访问
```
https://xxx.xxx.xxx/wx/login
访问类型：POST
入参：String code , String encryptedData , String iv
```