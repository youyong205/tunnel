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
  `type` varchar(64) NOT NULL  COMMENT '隧道类型',
  `email` varchar(1024) NOT NULL  COMMENT '邮件通知列表',
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
  `angle`  double NOT NULL COMMENT '初始偏移角度',
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
  `environment` varchar(64) NOT NULL  COMMENT '盾构段环境',
  `externalDiameter` double NOT NULL COMMENT '盾构段外径',
  `sequence` double NOT NULL COMMENT '盾构段顺序号',
  `des` varchar(1024) COMMENT '盾构段简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隧道盾构段信息';

CREATE TABLE `liningRingConstruction`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingId` int(11) NOT NULL COMMENT '衬砌环ID',
  `name` varchar(64) NOT NULL  COMMENT '衬砌环编号',
  `sequence` double NOT NULL COMMENT '衬砌环顺序号',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `positionAngle` double NOT NULL COMMENT '管片拼装定位角(°)',
  `scheduleId` int(11) NOT NULL COMMENT '施工进度ID',
  `computingStaff` varchar(64) NOT NULL  COMMENT '计算人员',
  `inspectors` varchar(64) NOT NULL  COMMENT '检查人员',
  `surveyors` varchar(64) NOT NULL  COMMENT '测量人员',
  `planeDeviation` varchar(64) NOT NULL  COMMENT '管片平面偏差',
  `elevationDeviation` varchar(64) NOT NULL  COMMENT '管片高程偏差',
  `gapUp` varchar(64) NOT NULL  COMMENT '管片间隙上',
  `gapDown` varchar(64) NOT NULL  COMMENT '管片间隙下',
  `gapLeft` varchar(64) NOT NULL  COMMENT '管片间隙左',
  `gapRight` varchar(64) NOT NULL  COMMENT '管片间隙右',
  `gap1` varchar(64) NOT NULL  COMMENT '管片间隙1',
  `gap2` varchar(64) NOT NULL  COMMENT '管片间隙2',
  `gap3` varchar(64) NOT NULL  COMMENT '管片间隙3',
  `gap4` varchar(64) NOT NULL  COMMENT '管片间隙4',
  `gap5` varchar(64) NOT NULL  COMMENT '管片间隙5',
  `gap6` varchar(64) NOT NULL  COMMENT '管片间隙6',
  `gap7` varchar(64) NOT NULL  COMMENT '管片间隙7',
  `gap8` varchar(64) NOT NULL  COMMENT '管片间隙8',
  `diameter` double NOT NULL COMMENT '横径（m）',
  `verticalDiameter` double NOT NULL COMMENT '竖径（m）',
  `leftUp` varchar(64) NOT NULL  COMMENT '左上',
  `leftDown` varchar(64) NOT NULL  COMMENT '左下',
  `rightUp` varchar(64) NOT NULL  COMMENT '右上',
  `rightDown` varchar(64) NOT NULL  COMMENT '右下',
  `deformationState` varchar(64)   COMMENT '横断面变形状态',
  `longitudinalDeformationState` varchar(64)  COMMENT '纵断面变形状态',
  `girthOpenState` varchar(64)  COMMENT '环缝张开状态',
  `longitudinalOpenState` varchar(64)  COMMENT '纵缝张开状态',
  `girthFaultState` varchar(64)  COMMENT '环缝错台状态',
  `longitudinalFaultState` varchar(64)  COMMENT '纵缝错台状态',
  `coverLossState` varchar(64)  COMMENT '保护层损失状态',
  `cracksState` varchar(64)  COMMENT '裂缝状态',
  `deformationId` varchar(64)   COMMENT '横断面变形状态ID',
  `longitudinalDeformationId` varchar(64)  COMMENT '纵断面变形状态ID',
  `girthOpenId` varchar(64)  COMMENT '环缝张开状态ID',
  `longitudinalOpenId` varchar(64)  COMMENT '纵缝张开状态ID',
  `girthFaultId` varchar(64)  COMMENT '环缝错台状态ID',
  `longitudinalFaultId` varchar(64)  COMMENT '纵缝错台状态ID',
  `coverLossId` varchar(64)  COMMENT '保护层损失状态ID',
  `settlementId` varchar(64)  COMMENT '裂缝状态ID',
  `seepageId` varchar(64)  COMMENT '沉降状态ID',
  `cracksId` varchar(64)  COMMENT '渗漏水状态ID',
  `rustId` varchar(64)  COMMENT '锈蚀状态ID',
  `des` varchar(1024) COMMENT '衬砌环施工简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='盾构段衬砌环施工信息';

CREATE TABLE `liningRingDeformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `measuringPoing` varchar(64) NOT NULL COMMENT '测点',
  `value` double NOT NULL COMMENT '本次测值D(mm)',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环横断面变形检测信息';

CREATE TABLE `liningRingLongitudinalDeformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `measuringPoing` varchar(64) NOT NULL COMMENT '测点',
  `value` double NOT NULL COMMENT '纵向曲率值',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环纵断面变形检测信息';

CREATE TABLE `girthOpen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `type` int(11) NOT NULL COMMENT '1表示和上一环，2表示和下一环',
  `measuringPoing` varchar(64) NOT NULL COMMENT '测点',
  `value` double NOT NULL COMMENT '环缝张开量值',
  `serious` int(11) NOT NULL COMMENT '是否出现严重连接缺陷,1表示没有，2表示有',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环环缝张开检测信息';

CREATE TABLE `girthFault` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `type` int(11) NOT NULL COMMENT '1表示和上一环，2表示和下一环',
  `value` double NOT NULL COMMENT '环缝错台值',
  `serious` int(11) NOT NULL COMMENT '是否出现严重连接缺陷,1表示没有，2表示有',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环环缝错台检测信息';

CREATE TABLE `settlement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `value` double NOT NULL COMMENT '沉降值',
  `distance` double NOT NULL COMMENT '与初始点距离',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环环缝沉降检测信息';

CREATE TABLE `longitudinalOpen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `type` int(11) NOT NULL COMMENT '1表示和上一块张开，2表示和下一块张开',
  `value` double NOT NULL COMMENT '纵缝张开量',
  `serious` int(11) NOT NULL COMMENT '是否出现严重连接缺陷,1表示没有，2表示有',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环纵缝张开检测信息';

CREATE TABLE `longitudinalFault` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `type` int(11) NOT NULL COMMENT '1表示和上一块错台，2表示和下一块错台',
  `value` double NOT NULL COMMENT '纵缝错台值',
  `serious` int(11) NOT NULL COMMENT '是否出现严重连接缺陷,1表示没有，2表示有',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环纵缝错台检测信息';

CREATE TABLE `coverLoss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `type` varchar(64) NOT NULL COMMENT '蜂窝、麻面、混凝土起层、剥落、露筋，其他',
  `shape` varchar(64) NOT NULL COMMENT '剥落形状',
  `width` double NOT NULL COMMENT '宽度(mm)',
  `height` double NOT NULL COMMENT '高度(mm)',
  `depth` double NOT NULL COMMENT '深度(mm)',
  `area` double NOT NULL COMMENT '面积(mm2)',
  `date` datetime NOT NULL COMMENT '测量时间',
  `serious` int(11) NOT NULL COMMENT '是否出现严重连接缺陷,1表示没有，2表示有',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环保护层缺失检测信息';

CREATE TABLE `cracks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `number` int(11) NOT NULL COMMENT '裂缝条数',
  `type` varchar(64) NOT NULL COMMENT '裂缝形态',
  `length` double NOT NULL COMMENT '长度(mm)',
  `width` double NOT NULL COMMENT '宽度(mm)',
  `angle` double NOT NULL COMMENT '中心点环向角度(°)',
  `dip` double NOT NULL COMMENT '裂缝倾角(°)',
  `date` datetime NOT NULL COMMENT '测量时间',
  `serious` int(11) NOT NULL COMMENT '是否出现严重连接缺陷,1表示没有，2表示有',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环裂缝检测信息';

CREATE TABLE `seepage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `shape` varchar(64) NOT NULL COMMENT '基本形状',
  `size` double NOT NULL COMMENT '尺寸',
  `startAngle` double NOT NULL COMMENT '起始环向角度(°)',
  `endAngle` double NOT NULL COMMENT '终止环向角度(°)',
  `affect` int(11) NOT NULL COMMENT '是否影响设备，1表示不，2表示是',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环渗漏水检测信息';

CREATE TABLE `rust` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `liningRingConstructionId` int(11) NOT NULL COMMENT '衬砌环ID',
  `blockIndex` int(11) NOT NULL COMMENT '衬砌环的所在块',
  `shape` varchar(64) NOT NULL COMMENT '锈蚀形状',
  `area` double NOT NULL COMMENT '锈蚀面积(m㎡)',
  `startAngle` double NOT NULL COMMENT '起始环向角度(°)',
  `endAngle` double NOT NULL COMMENT '终止环向角度(°)',
  `date` datetime NOT NULL COMMENT '测量时间',
  `des` varchar(1024) COMMENT '简介',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_date` (`date`),
  KEY `ix_tunnel_section_liningRingConstruction` (`tunnelId`,`tunnelSectionId`,`liningRingConstructionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='衬砌环锈蚀检测信息';

CREATE TABLE `rectangleComponent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号名称',
  `type` varchar(64) NOT NULL  COMMENT '口型构建类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `des` varchar(1024) COMMENT '口型构建描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='口型构件基本信息';

CREATE TABLE `plank`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '车道板编号名称',
  `type` varchar(64) NOT NULL  COMMENT '类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `concreteStrength` varchar(64) NOT NULL  COMMENT '混凝土强度',
  `reinforcementStrength` varchar(64) NOT NULL  COMMENT '钢筋强度',
  `designPath` varchar(64) NOT NULL  COMMENT '设计路中',
  `designLongitudinal` varchar(64) NOT NULL  COMMENT '设计纵坡',
  `length` varchar(64) NOT NULL COMMENT '车道板长度',
  `des` varchar(1024) COMMENT '车道板描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车道板基本信息';

CREATE TABLE `bracket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号名称',
  `type` varchar(64) NOT NULL  COMMENT '牛腿类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `concreteStrength` varchar(64) NOT NULL  COMMENT '混凝土强度',
  `reinforcementStrength` varchar(64) NOT NULL  COMMENT '钢筋强度',
  `des` varchar(1024) COMMENT '牛腿描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='牛腿基本信息';

CREATE TABLE `saddleWeight` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号名称',
  `type` varchar(64) NOT NULL  COMMENT '压重块类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `concreteStrength` varchar(64) NOT NULL  COMMENT '混凝土强度',
  `reinforcementStrength` varchar(64) NOT NULL  COMMENT '钢筋强度',
  `des` varchar(1024) COMMENT '压重块描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='压重块基本信息';

CREATE TABLE `flueSheet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号名称',
  `type` varchar(64) NOT NULL  COMMENT '烟道板类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `concreteStrength` varchar(64) NOT NULL  COMMENT '混凝土强度',
  `reinforcementStrength` varchar(64) NOT NULL  COMMENT '钢筋强度',
  `des` varchar(1024) COMMENT '烟道板描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='烟道板基本信息';

CREATE TABLE `pumpingStation`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号名称',
  `type` varchar(64) NOT NULL  COMMENT '泵房类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `stakeMileage` varchar(64) NOT NULL COMMENT '桩号里程',
  `position`  varchar(64) NOT NULL COMMENT '位置描述',
  `laneMileage` double NOT NULL COMMENT '车道中心线里程(m)',
  `shieldMileage` double NOT NULL COMMENT '盾构中心线里程(m)',
  `absolutEelevation` double NOT NULL COMMENT '+0.000对应绝对标高(m)',
  `des` varchar(1024) COMMENT '泵房备注',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='泵房基本信息';

CREATE TABLE `escape`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号名称',
  `type` varchar(64) NOT NULL  COMMENT '逃生楼梯类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `stakeMileage` varchar(64) NOT NULL COMMENT '桩号里程',
  `position`  varchar(64) NOT NULL COMMENT '位置描述',
  `actualMileage` double NOT NULL COMMENT '实测里程(m)',
  `des` varchar(1024) COMMENT '逃生楼梯备注',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='逃生楼梯基本信息';

CREATE TABLE `linePipe`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号名称',
  `type` varchar(64) NOT NULL  COMMENT '预埋管线类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `stakeMileage` varchar(64) NOT NULL COMMENT '桩号里程',
  `position`  varchar(64) NOT NULL COMMENT '位置描述',
  `des` varchar(1024) COMMENT '预埋管线备注',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='预埋管线基本信息';

CREATE TABLE `facility`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '设备编号名称',
  `type` varchar(64) NOT NULL  COMMENT '设备类型',
  `lineType` varchar(64) NOT NULL  COMMENT '线路类型',
  `stakeMileage` varchar(64) NOT NULL COMMENT '桩号里程',
  `position`  varchar(64) NOT NULL COMMENT '位置描述',
  `liningRingId` varchar(64) NOT NULL COMMENT '关联环号',
  `boxNumber` varchar(64)  NOT NULL COMMENT '箱号',
  `width` double NOT NULL COMMENT '长度',
  `height` double NOT NULL COMMENT '高度',
  `constructionUnitId` double NOT NULL COMMENT '管养单位ID',
  `des` varchar(1024) COMMENT '设备备注',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section` (`tunnelId`,`tunnelSectionId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备基本信息';

CREATE TABLE `document`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module` varchar(64) NOT NULL COMMENT '文档模块',
  `name` varchar(64) NOT NULL  COMMENT '文档名称',
  `type` varchar(256) NOT NULL  COMMENT '文档类型',
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
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
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
  PRIMARY KEY (`id`),
  KEY `ix_tunnel` (`tunnelId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='联络通道基本信息';

CREATE TABLE `workingWell`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号',
  `type` varchar(64) NOT NULL  COMMENT '类型',
  `tunnelSectionId` int(11) NOT NULL COMMENT '前面一个盾构段ID',
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
  PRIMARY KEY (`id`),
  KEY `ix_tunnel` (`tunnelId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工作井基本信息';

CREATE TABLE `buriedSection`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
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
  PRIMARY KEY (`id`),
  KEY `ix_tunnel` (`tunnelId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='暗埋段基本信息';

CREATE TABLE `openSection`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `documentId` int(11) NOT NULL COMMENT '设计文档ID',
  `scheduleId` int(11) NOT NULL COMMENT '进度ID',
  `name` varchar(64) NOT NULL  COMMENT '编号',
  `type` varchar(64) NOT NULL  COMMENT '类型',
  `startPosition` double NOT NULL COMMENT '开始里程(m)',
  `endPosition` double NOT NULL COMMENT '结束里程(m)',
  `envelope` varchar(64) NOT NULL COMMENT '围护结构',
  `startFloor` double NOT NULL COMMENT '起始地板标高(m)',
  `endFcoverLossor` double NOT NULL COMMENT '终止底板标高(m)',
  `startRoadDome` double NOT NULL COMMENT '起始路拱顶标高(m)',
  `endRoadDome` double NOT NULL COMMENT '终止路拱顶标高(m)',
  `crestPileBottom` double NOT NULL COMMENT '坡顶搅拌桩底标高(m)',
  `platformPileBottom` double NOT NULL COMMENT '平台搅拌桩底标高(m)',
  `des` varchar(1024) COMMENT '敞开段描述',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel` (`tunnelId`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='敞开段基本信息';

CREATE TABLE `constructionUnit`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(64) NOT NULL COMMENT '施工单位类型',
  `name` varchar(128) NOT NULL  COMMENT '施工单位名称',
  `address` varchar(128) NOT NULL  COMMENT '施工单位地址',
  `workers` varchar(128) NOT NULL  COMMENT '施工人员',
  `phone` varchar(64) NOT NULL  COMMENT '联系方式',
  `des` varchar(1024) COMMENT '备注',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='施工单位基本信息';

CREATE TABLE `schedule`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `constructionUnitId` int(11) NOT NULL COMMENT '施工单位ID',
  `type` varchar(64) NOT NULL  COMMENT '进度所属隧道构建类型',
  `startTime` datetime NOT NULL COMMENT '开始时间',
  `endTime` datetime NOT NULL COMMENT '结束时间',
  `des` varchar(1024) COMMENT '备注',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='施工进度基本信息';

CREATE TABLE `inspection`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `type` varchar(64)  NOT NULL COMMENT'检测所属隧道构建类型',
  `componentId` int(11)  NOT NULL COMMENT'检测所属盾构段构建的ID',
  `constructionUnitId` int(11) NOT NULL COMMENT '检测单位ID',
  `time` datetime NOT NULL COMMENT '检查时间',
  `description` varchar(1024) COMMENT '质量描述',
  `action` varchar(512) NOT NULL  COMMENT '处理措施',
  `actionTime` datetime NOT NULL COMMENT '处理时间',
  `des` varchar(1024) COMMENT '备注',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section_type` (`tunnelId`,`tunnelSectionId`,`type`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隧道质量检测基本信息';

CREATE TABLE `curing`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `tunnelSectionId` int(11) NOT NULL COMMENT '盾构段ID',
  `type` varchar(64)  NOT NULL COMMENT '养护所属隧道构建类型',
  `componentId` int(11)  NOT NULL COMMENT '养护所属盾构段构建的ID',
  `constructionUnitId` int(11) NOT NULL COMMENT '养护单位ID',
  `documentId` int(11) NOT NULL COMMENT '养护资料文档',
  `time` datetime NOT NULL COMMENT '养护时间',
  `position` varchar(256)  NOT NULL COMMENT '位置描述',
  `action` varchar(1024) NOT NULL  COMMENT '处理措施',
  `des` varchar(1024) COMMENT '备注',
  `creationDate` datetime NOT NULL COMMENT '创建时间',
  `modifyDate` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `ix_tunnel_section_type` (`tunnelId`,`tunnelSectionId`,`type`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='隧道养护基本信息';

CREATE TABLE `mailRecord` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tunnelId` int(11) NOT NULL COMMENT '隧道ID',
  `type` int(11) NOT NULL COMMENT '发送邮件的内容，1表示告警邮件，2表示每日报表邮件',
  `time` datetime NOT NULL COMMENT '发送时间',
  `receivers` varchar(500) NOT NULL COMMENT '接受用户邮件列表',
  `title` varchar(200) NOT NULL COMMENT '邮件标题',
  `content` text NOT NULL COMMENT '邮件具体内容',
  `status` int(11) NOT NULL COMMENT '1表示发送成功、2表示发送失败',
  `creation_date` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件通知记录表';








