CREATE TABLE `frame_test_user` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`account`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL ,
`name`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`sex`  tinyint(1) NULL DEFAULT NULL ,
`age`  int(10) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
)ENGING=InnoDB DEFAULT CHARSET=utf8mb4;


-- 文件上传记录表
CREATE TABLE `com_file` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`c_uuid`  varchar(100) NOT NULL COMMENT 'UUID',
`c_file_name` varchar(500) NOT NULL COMMENT '文件名',
`c_file_size` int(10) NOT NULL DEFAULT 0 COMMENT '文件大小',
`c_file_path` varchar(500) NOT NULL COMMENT '文件路径',
`c_creator_account` varchar(20) COMMENT '创建人工号',
`c_creator_name` varchar(20) COMMENT '创建人姓名',
`c_created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
PRIMARY KEY (`id`)
)ENGING=InnoDB DEFAULT CHARSET=utf8mb4;
