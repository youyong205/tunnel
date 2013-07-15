CREATE TABLE  `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(64) NOT NULL  COMMENT '登陆名',
  `password` varchar(64) NOT NULL COMMENT '用户密码',
  `realName` varchar(64) NOT NULL COMMENT '真实姓名',
  `role` int(11) NOT NULL COMMENT '1/管理员,2/数据录入员,３/普通用户',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_userName` (`userName`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户基本信息';

CREATE TABLE  `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(64) NOT NULL  COMMENT '模块名',
  `operation` varchar(64) NOT NULL COMMENT '操作名',
  `userId` int(11) NOT NULL COMMENT '用户ID',
  `detail` varchar(1024) NOT NULL COMMENT '操作内容',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `ix_module_operation` (`module`,`operation`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户操作日志';

