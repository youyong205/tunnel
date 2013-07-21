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

CREATE TABLE  `tunnel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL  COMMENT '隧道名称',
  `des` varchar(1024) NOT NULL COMMENT '隧道简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隧道基本信息';

CREATE TABLE  `liningRing` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL  COMMENT '衬砌环名称',
  `type` varchar(64) NOT NULL  COMMENT '衬砌环类型',
  `pipeShape` varchar(64) NOT NULL COMMENT '管片形式',
  `pipeNumber`  int(11) NOT NULL COMMENT '管片数量',
  `pipeThickness`  double NOT NULL COMMENT '管片厚度(m)',
  `pipeWidth`  double NOT NULL COMMENT '管片宽度(m)',
  `wedgeNumber`  int(11) NOT NULL COMMENT '楔形量',
  `des` varchar(1024) NOT NULL COMMENT '衬砌环描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环基本信息';

CREATE TABLE  `liningRingBlock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `liningRingId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环块的序号',
  `angle` double NOT NULL COMMENT '衬砌环块角度',
  `color` varchar(64) NOT NULL COMMENT '管片形式',
  `des` varchar(1024) NOT NULL COMMENT '衬砌环块描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环块基本信息';

CREATE TABLE `tunnelSection`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `name` varchar(64) NOT NULL  COMMENT '盾构段编号',
  `type` varchar(64) NOT NULL  COMMENT '盾构段类型',
  `des` varchar(1024) NOT NULL COMMENT '隧道简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隧道盾构段信息';


