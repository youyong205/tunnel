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

CREATE TABLE  `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL  COMMENT '角色名称',
  `des` varchar(64) NOT NULL  COMMENT '角色描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统角色基本信息';

CREATE TABLE  `resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(64) NOT NULL  COMMENT '资源模块',
  `name` varchar(64) NOT NULL  COMMENT '资源名称',
  `des` varchar(64) NOT NULL  COMMENT '资源描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统资源基本信息';

CREATE TABLE `userRole`(
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关系';

CREATE TABLE `roleResource`(
  `roleId` int(11) NOT NULL,
  `resourceId` int(11) NOT NULL,
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`roleId`,`resourceId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源关系';

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
  `des` varchar(1024) COMMENT '衬砌环描述',
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
  `des` varchar(1024) COMMENT '衬砌环块描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环块基本信息';

CREATE TABLE `tunnelSection`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `name` varchar(64) NOT NULL  COMMENT '盾构段编号',
  `type` varchar(64) NOT NULL  COMMENT '盾构段类型',
  `des` varchar(1024) COMMENT '隧道简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隧道盾构段信息';

CREATE TABLE `document`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(64) NOT NULL COMMENT '文档模块',
  `name` varchar(64) NOT NULL  COMMENT '文档名称',
  `type` varchar(64) NOT NULL  COMMENT '文档类型',
  `path` varchar(512) NOT NULL COMMENT '文档相对路径',
  `absolutePath` varchar(256) NOT NULL COMMENT '文档绝对路径',
  `des` varchar(1024) COMMENT '文档描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统文档基本信息';

CREATE TABLE `contactChannel`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `name` varchar(64) NOT NULL  COMMENT '名称编号',
  `type` varchar(64) NOT NULL  COMMENT '类型',
  `upLine` double NOT NULL COMMENT '上行线隧道里程(m)',
  `downLine` double NOT NULL COMMENT '下行线隧道里程(m)',
  `upCenter` double NOT NULL COMMENT '上行线隧道中心标高(m)',
  `downCenter` double NOT NULL COMMENT '下行线隧道中心标高(m)',
  `soilOrder` varchar(64) NOT NULL COMMENT '所属土层层序',
  `des` varchar(1024) COMMENT '联络通道描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联络通道基本信息';

CREATE TABLE `workingWell`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `name` varchar(64) NOT NULL  COMMENT '编号',
  `type` varchar(64) NOT NULL  COMMENT '类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `eleationOne` double NOT NULL COMMENT '下一层标高标高(m)',
  `eleationTwo` double NOT NULL COMMENT '下二层标高标高(m)',
  `eleationThree` double NOT NULL COMMENT '下三层标高标高(m)',
  `eleationFour` double NOT NULL COMMENT '下四层标高标高(m)',
  `eleationMezzanine` double NOT NULL COMMENT '烟道夹层标高(m)',
  `des` varchar(1024) COMMENT '联络通道描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作井基本信息';

CREATE TABLE `buriedSection`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `name` varchar(64) NOT NULL  COMMENT '编号',
  `type` varchar(64) NOT NULL  COMMENT '类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `envelope` varchar(64) NOT NULL COMMENT '围护结构',
  `startFloor` double NOT NULL COMMENT '起始地板标高(m)',
  `endFloor` double NOT NULL COMMENT '终止底板标高(m)',
  `linedWallThickness` double NOT NULL COMMENT '内衬墙厚度(m)',
  `floorThickness` double NOT NULL COMMENT '底板厚度(m)',
  `roofThickness` double NOT NULL COMMENT '顶板厚度(m)',
  `startHeadroom` double NOT NULL COMMENT '起始净空高度(m)',
  `endHeadroom` double NOT NULL COMMENT '终止净空高度(m)',
  `des` varchar(1024) COMMENT '暗埋段描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='暗埋段基本信息';

CREATE TABLE `openSection`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `name` varchar(64) NOT NULL  COMMENT '编号',
  `type` varchar(64) NOT NULL  COMMENT '类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `envelope` varchar(64) NOT NULL COMMENT '围护结构',
  `startFloor` double NOT NULL COMMENT '起始地板标高(m)',
  `endFloor` double NOT NULL COMMENT '终止底板标高(m)',
  `startRoadDome` double NOT NULL COMMENT '起始路拱顶标高(m)',
  `endRoadDome` double NOT NULL COMMENT '终止路拱顶标高(m)',
  `crestPileBottom` double NOT NULL COMMENT '坡顶搅拌桩底标高(m)',
  `platformPileBottom` double NOT NULL COMMENT '平台搅拌桩底标高(m)',
  `des` varchar(1024) COMMENT '敞开段描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='敞开段基本信息';



