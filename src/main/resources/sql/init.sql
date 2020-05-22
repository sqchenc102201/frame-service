create table `frame_test_user` (
`id`  bigint(20) not null auto_increment ,
`account`  varchar(100) character set utf8mb4 collate utf8mb4_bin null default null ,
`name`  varchar(100) character set utf8mb4 collate utf8mb4_general_ci null default null ,
`sex`  tinyint(1) null default null ,
`age`  int(10) null default null ,
primary key (`id`)
)enging=innodb default charset=utf8mb4;


-- 文件上传记录表
create table `com_file` (
`id`  bigint(20) not null auto_increment ,
`c_uuid`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment 'uuid' ,
`c_file_name`  varchar(500) character set utf8mb4 collate utf8mb4_bin not null comment '文件名' ,
`c_file_size`  int(10) not null default 0 comment '文件大小' ,
`c_file_path`  varchar(500) character set utf8mb4 collate utf8mb4_bin not null comment '文件路径' ,
`c_creator_account`  varchar(20) character set utf8mb4 collate utf8mb4_bin null default null comment '创建人工号' ,
`c_creator_name`  varchar(20) character set utf8mb4 collate utf8mb4_bin null default null comment '创建人姓名' ,
`c_created`  datetime not null default current_timestamp comment '创建时间' ,
-- `c_updated`  datetime not null default current_timestamp on update current_timestamp comment '更新时间' ,
primary key (`id`)
)engine=innodb auto_increment=1 default character set=utf8mb4 collate=utf8mb4_bin;


-- 角色表
create table `sys_role` (
`id`  bigint(20) not null auto_increment ,
`code`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '角色编码' ,
`name`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '角色名称' ,
`desc`  varchar(200) character set utf8mb4 collate utf8mb4_bin not null comment '角色描述' ,
`status`  tinyint(1) not null default 0 comment '0：无效，1、有效' ,
`creator`  varchar(20) character set utf8mb4 collate utf8mb4_bin null default null comment '操作人工号' ,
`create_time`  datetime not null default current_timestamp comment '创建时间' ,
`update_time`  datetime not null default current_timestamp on update current_timestamp comment '更新时间' ,
primary key (`id`)
)engine=innodb auto_increment=1 default character set=utf8mb4 collate=utf8mb4_bin;

-- 角色权限表
create table `sys_role` (
`id`  bigint(20) not null auto_increment ,
`role_code`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '角色编码' ,
`permission_code`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '权限编码' ,
`creator`  varchar(20) character set utf8mb4 collate utf8mb4_bin null default null comment '操作人工号' ,
`create_time`  datetime not null default current_timestamp comment '创建时间' ,
`update_time`  datetime not null default current_timestamp on update current_timestamp comment '更新时间' ,
primary key (`id`)
)engine=innodb auto_increment=1 default character set=utf8mb4 collate=utf8mb4_bin;

-- 角色用户表
create table `sys_role_user` (
`id`  bigint(20) not null auto_increment ,
`user_account`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '角色编码' ,
`role_code`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '权限编码' ,
`status`  tinyint(1) not null default 0 comment '0：无效，1、有效' ,
`creator`  varchar(20) character set utf8mb4 collate utf8mb4_bin null default null comment '操作人工号' ,
`create_time`  datetime not null default current_timestamp comment '创建时间' ,
`update_time`  datetime not null default current_timestamp on update current_timestamp comment '更新时间' ,
primary key (`id`)
)engine=innodb auto_increment=1 default character set=utf8mb4 collate=utf8mb4_bin;

-- 权限码表
create table `sys_permission_code` (
`id`  bigint(20) not null auto_increment ,
`code`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '权限编码' ,
`create_time`  datetime not null default current_timestamp comment '创建时间'
primary key (`id`)
)engine=innodb auto_increment=1 default character set=utf8mb4 collate=utf8mb4_bin;

-- 基础用户表
create table `sys_user` (
`id`  bigint(20) not null auto_increment ,
`account`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '用户工号' ,
`name`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '用户姓名' ,
`name_en`  varchar(100) character set utf8mb4 collate utf8mb4_bin not null comment '用户英文姓名' ,
`status`  tinyint(1) not null default 0 comment '0：无效，1、有效' ,
`create_time`  datetime not null default current_timestamp comment '创建时间' ,
`update_time`  datetime not null default current_timestamp on update current_timestamp comment '更新时间' ,
primary key (`id`)
)engine=innodb auto_increment=1 default character set=utf8mb4 collate=utf8mb4_bin;

