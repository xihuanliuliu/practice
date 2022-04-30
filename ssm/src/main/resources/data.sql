CREATE TABLE `bill`  (
     `id` bigint UNSIGNED NOT NULL COMMENT '全平台用户唯一id',
     `bill_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `title` varchar(30) NOT NULL DEFAULT '' COMMENT '标题',
     `type_name` varchar(64) NOT NULL DEFAULT '' COMMENT '账单类型',
     `remake` varchar(64)  NOT NULL DEFAULT '' COMMENT '标记',
     `type_id` tinyint NOT NULL COMMENT '类型ID',
     `price` tinyint NOT NULL COMMENT '价格',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE INDEX `uk_usertype_userid`(`type_id`, `id`) USING BTREE,
     INDEX `idx_username`(`title`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = 'bill';

CREATE TABLE `billType`  (
     `id` bigint UNSIGNED NOT NULL COMMENT 'bill id',
     `name` varchar(64)  NOT NULL DEFAULT '' COMMENT '名称',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = 'billType';

CREATE TABLE `user`  (
     `id` bigint UNSIGNED NOT NULL COMMENT 'id',
     `user_name` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
     `login_name` varchar(64) NOT NULL DEFAULT '' COMMENT '登录别名',
     `pwd` varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
     `set` varchar(2) NOT NULL COMMENT '性别',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE INDEX `uk_usertype_userid`(`user_name`, `id`) USING BTREE,
     INDEX `idx_username`(`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = 'user';
