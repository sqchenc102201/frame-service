create table frame_test_user(
    id Bigint
)

CREATE TABLE `frame_test_user` (
`id`  bigint(20) NOT NULL AUTO_INCREMENT ,
`account`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL ,
`name`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL ,
`sex`  tinyint(1) NULL DEFAULT NULL ,
`age`  int(10) NULL DEFAULT NULL ,
PRIMARY KEY (`id`)
) comment "测试表";
