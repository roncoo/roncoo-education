/******************************************/
/*   数据库全名 = os_job   */
/******************************************/
CREATE DATABASE `os_job`;
USE `os_job`;

-- ----------------------------
-- Table structure for xxl_job_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_group`;
CREATE TABLE `xxl_job_group`
(
    `id`           int          NOT NULL AUTO_INCREMENT,
    `app_name`     varchar(64)  NOT NULL COMMENT '执行器AppName',
    `title`        varchar(12)  NOT NULL COMMENT '执行器名称',
    `order`        int          NOT NULL DEFAULT 0 COMMENT '排序',
    `address_type` tinyint      NOT NULL DEFAULT 0 COMMENT '执行器地址类型：0=自动注册、1=手动录入',
    `address_list` varchar(512) NULL     DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
    `update_time`  datetime     NULL     DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_group
-- ----------------------------
INSERT INTO `xxl_job_group`
VALUES (1, 'user-executor', '用户执行器', 1, 0, '', '2024-01-01 01:01:01');
INSERT INTO `xxl_job_group`
VALUES (2, 'course-executor', '课程执行器', 0, 0, '', '2024-01-01 01:01:01');

-- ----------------------------
-- Table structure for xxl_job_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_info`;
CREATE TABLE `xxl_job_info`
(
    `id`                        int          NOT NULL AUTO_INCREMENT,
    `job_group`                 int          NOT NULL COMMENT '执行器主键ID',
    `job_desc`                  varchar(255) NOT NULL,
    `add_time`                  datetime     NULL     DEFAULT NULL,
    `update_time`               datetime     NULL     DEFAULT NULL,
    `author`                    varchar(64)  NULL     DEFAULT NULL COMMENT '作者',
    `alarm_email`               varchar(255) NULL     DEFAULT NULL COMMENT '报警邮件',
    `executor_route_strategy`   varchar(50)  NULL     DEFAULT NULL COMMENT '执行器路由策略',
    `executor_handler`          varchar(255) NULL     DEFAULT NULL COMMENT '执行器任务handler',
    `executor_param`            varchar(512) NULL     DEFAULT NULL COMMENT '执行器任务参数',
    `executor_block_strategy`   varchar(50)  NULL     DEFAULT NULL COMMENT '阻塞处理策略',
    `executor_timeout`          int          NOT NULL DEFAULT 0 COMMENT '任务执行超时时间，单位秒',
    `executor_fail_retry_count` int          NOT NULL DEFAULT 0 COMMENT '失败重试次数',
    `glue_type`                 varchar(50)  NOT NULL COMMENT 'GLUE类型',
    `glue_source`               mediumtext   NULL COMMENT 'GLUE源代码',
    `glue_remark`               varchar(128) NULL     DEFAULT NULL COMMENT 'GLUE备注',
    `glue_updatetime`           datetime     NULL     DEFAULT NULL COMMENT 'GLUE更新时间',
    `child_jobid`               varchar(255) NULL     DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
    `trigger_status`            tinyint      NOT NULL DEFAULT 0 COMMENT '调度状态：0-停止，1-运行',
    `trigger_last_time`         bigint       NOT NULL DEFAULT 0 COMMENT '上次调度时间',
    `trigger_next_time`         bigint       NOT NULL DEFAULT 0 COMMENT '下次调度时间',
    `schedule_type`             varchar(50)  NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
    `schedule_conf`             varchar(128) NULL     DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
    `misfire_strategy`          varchar(50)  NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
-- ----------------------------
-- Records of xxl_job_info
-- ----------------------------
INSERT INTO `xxl_job_info`
VALUES (1, 1, '订单过期处理', '2018-11-03 22:21:31', '2022-09-30 22:29:21', 'roncoo', 'xxx@qq.com', 'RANDOM',
        'orderJobHandler', '', 'DISCARD_LATER', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2018-11-03 22:21:31', '', 1,
        1711612812563, 1711612872563, 'FIX_RATE', '60', 'DO_NOTHING');
INSERT INTO `xxl_job_info`
VALUES (2, 2, '课程ES同步', '2022-09-25 14:06:18', '2023-07-17 10:40:20', 'roncoo', 'xxx@qq.com', 'RANDOM',
        'courseJobHandler', '', 'DISCARD_LATER', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-09-25 14:06:18', '', 0, 0, 0,
        'CRON', '0 0 5 * * ?', 'DO_NOTHING');
INSERT INTO `xxl_job_info`
VALUES (3, 2, '课程进度同步', '2022-09-25 14:02:42', '2024-03-14 09:38:48', 'roncoo', 'xxx@qq.com', 'RANDOM',
        'userStudyJobHandler', '', 'DISCARD_LATER', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2022-09-25 14:02:42', '', 1,
        1711612817031, 1711612827031, 'FIX_RATE', '10', 'DO_NOTHING');
INSERT INTO `xxl_job_info`
VALUES (4, 2, '视频状态处理', '2023-06-08 14:59:28', '2024-03-14 09:31:14', 'roncoo', 'xxx@qq.com', 'RANDOM',
        'resourceJobHandler', '', 'DISCARD_LATER', 0, 0, 'BEAN', '', 'GLUE代码初始化', '2023-06-08 14:59:28', '', 1,
        1711612279350, 1711612879350, 'FIX_RATE', '600', 'DO_NOTHING');

-- ----------------------------
-- Table structure for xxl_job_lock
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_lock`;
CREATE TABLE `xxl_job_lock`
(
    `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
    PRIMARY KEY (`lock_name`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_lock
-- ----------------------------
INSERT INTO `xxl_job_lock`
VALUES ('schedule_lock');

-- ----------------------------
-- Table structure for xxl_job_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log`;
CREATE TABLE `xxl_job_log`
(
    `id`                        bigint       NOT NULL AUTO_INCREMENT,
    `job_group`                 int          NOT NULL COMMENT '执行器主键ID',
    `job_id`                    int          NOT NULL COMMENT '任务，主键ID',
    `executor_address`          varchar(255) NULL     DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
    `executor_handler`          varchar(255) NULL     DEFAULT NULL COMMENT '执行器任务handler',
    `executor_param`            varchar(512) NULL     DEFAULT NULL COMMENT '执行器任务参数',
    `executor_sharding_param`   varchar(20)  NULL     DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
    `executor_fail_retry_count` int          NOT NULL DEFAULT 0 COMMENT '失败重试次数',
    `trigger_time`              datetime     NULL     DEFAULT NULL COMMENT '调度-时间',
    `trigger_code`              int          NOT NULL COMMENT '调度-结果',
    `trigger_msg`               text         NULL COMMENT '调度-日志',
    `handle_time`               datetime     NULL     DEFAULT NULL COMMENT '执行-时间',
    `handle_code`               int          NOT NULL COMMENT '执行-状态',
    `handle_msg`                text         NULL COMMENT '执行-日志',
    `alarm_status`              tinyint      NOT NULL DEFAULT 0 COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `I_handle_code` (`handle_code`) USING BTREE,
    INDEX `I_trigger_time` (`trigger_time`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for xxl_job_log_report
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_log_report`;
CREATE TABLE `xxl_job_log_report`
(
    `id`            int      NOT NULL AUTO_INCREMENT,
    `trigger_day`   datetime NULL     DEFAULT NULL COMMENT '调度-时间',
    `running_count` int      NOT NULL DEFAULT 0 COMMENT '运行中-日志数量',
    `suc_count`     int      NOT NULL DEFAULT 0 COMMENT '执行成功-日志数量',
    `fail_count`    int      NOT NULL DEFAULT 0 COMMENT '执行失败-日志数量',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_log_report
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_logglue
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_logglue`;
CREATE TABLE `xxl_job_logglue`
(
    `id`          int          NOT NULL AUTO_INCREMENT,
    `job_id`      int          NOT NULL COMMENT '任务，主键ID',
    `glue_type`   varchar(50)  NULL DEFAULT NULL COMMENT 'GLUE类型',
    `glue_source` mediumtext   NULL COMMENT 'GLUE源代码',
    `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
    `add_time`    datetime     NULL DEFAULT NULL,
    `update_time` datetime     NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_logglue
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_registry`;
CREATE TABLE `xxl_job_registry`
(
    `id`             int          NOT NULL AUTO_INCREMENT,
    `registry_group` varchar(50)  NOT NULL,
    `registry_key`   varchar(255) NOT NULL,
    `registry_value` varchar(255) NOT NULL,
    `update_time`    datetime     NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `i_g_k_v` (`registry_group`, `registry_key`, `registry_value`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for xxl_job_user
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_user`;
CREATE TABLE `xxl_job_user`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `username`   varchar(50)  NOT NULL COMMENT '账号',
    `password`   varchar(50)  NOT NULL COMMENT '密码',
    `role`       tinyint      NOT NULL COMMENT '角色：0-普通用户、1-管理员',
    `permission` varchar(255) NULL DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `i_username` (`username`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxl_job_user
-- ----------------------------
INSERT INTO `xxl_job_user`
VALUES (1, 'admin', 'e10adc3949ba59abbe56e057f20f883e', 1, NULL);

SET FOREIGN_KEY_CHECKS = 1;
