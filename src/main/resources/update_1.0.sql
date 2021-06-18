-- 2021-05-19 13:39:29 by Achin
CREATE TABLE `sldz_banner`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '轮播图';
-- 2021-05-19 13:40:29 by Achin
ALTER TABLE `sldz_banner` ADD COLUMN `banner_img` varchar(300) COMMENT '轮播图地址' AFTER `id`;
-- 2021-05-19 13:40:29 by Achin
ALTER TABLE `sldz_banner` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `banner_img`;
-- 2021-05-20 14:48:07 by Achin
CREATE TABLE `sldz_product_category`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '产品类别';
-- 2021-05-20 14:48:46 by Achin
ALTER TABLE `sldz_product_category` ADD COLUMN `category_name` varchar(100) COMMENT '类别名称' AFTER `id`;
-- 2021-05-20 14:48:46 by Achin
ALTER TABLE `sldz_product_category` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `category_name`;
-- 2021-05-20 14:56:21 by Achin
CREATE TABLE `sldz_product`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '产品';
-- 2021-05-20 15:02:13 by Achin
ALTER TABLE `sldz_product` ADD COLUMN `product_category` bigint COMMENT '产品分类id' AFTER `id`;
-- 2021-05-20 15:02:13 by Achin
ALTER TABLE `sldz_product` ADD COLUMN `product_imgs` varchar(500) COMMENT '产品主图' AFTER `product_category`;
-- 2021-05-20 15:02:13 by Achin
ALTER TABLE `sldz_product` ADD COLUMN `product_name` varchar(100) COMMENT '产品名称' AFTER `product_imgs`;
-- 2021-05-20 15:02:13 by Achin
ALTER TABLE `sldz_product` ADD COLUMN `product_price` double NOT NULL DEFAULT 0.00 COMMENT '产品价格' AFTER `product_name`;
-- 2021-05-20 15:02:14 by Achin
ALTER TABLE `sldz_product` ADD COLUMN `product_details` varchar(5000) COMMENT '产品详情' AFTER `product_price`;
-- 2021-05-20 15:02:14 by Achin
ALTER TABLE `sldz_product` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `product_details`;
-- 2021-05-21 13:59:52 by Achin
ALTER TABLE `sldz_product` ADD COLUMN `product_video` varchar(150) COMMENT '产品视频' AFTER `product_price`;
-- 2021-05-21 13:59:52 by Achin
ALTER TABLE `sldz_product` MODIFY COLUMN `product_details` varchar(5000) COMMENT '产品详情' AFTER `product_video`;
-- 2021-05-21 13:59:52 by Achin
ALTER TABLE `sldz_product` ADD COLUMN `product_stock` bigint DEFAULT 0 COMMENT '产品库存' AFTER `product_details`;
-- 2021-05-21 13:59:52 by Achin
ALTER TABLE `sldz_product` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `product_stock`;
-- 2021-05-21 14:03:31 by Achin
CREATE TABLE `sldz_user`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '用户';
-- 2021-05-31 21:32:24 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_name` varchar(100) COMMENT '代理商名字' AFTER `id`;
-- 2021-05-31 21:32:24 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_phone` varchar(100) COMMENT '代理商手机' AFTER `agent_name`;
-- 2021-05-31 21:32:24 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_pasword` varchar(100) COMMENT '代理商密码' AFTER `agent_phone`;
-- 2021-05-31 21:32:24 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_gender` bigint DEFAULT 1 COMMENT '性别 1 为男 2为女' AFTER `agent_pasword`;
-- 2021-05-31 21:32:25 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_idcar` varchar(100) COMMENT '代理商身份证' AFTER `agent_gender`;
-- 2021-05-31 21:32:25 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_sheng` varchar(100) COMMENT '省' AFTER `agent_idcar`;
-- 2021-05-31 21:32:25 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_shi` varchar(100) COMMENT '市' AFTER `agent_sheng`;
-- 2021-05-31 21:32:25 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_qu` varchar(100) COMMENT '区/县' AFTER `agent_shi`;
-- 2021-05-31 21:32:25 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_jiedao` varchar(100) COMMENT '街道' AFTER `agent_qu`;
-- 2021-05-31 21:32:25 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `agent_grade` varchar(100) COMMENT '等级' AFTER `agent_jiedao`;
-- 2021-05-31 21:32:25 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `openid` varchar(100) COMMENT '微信openid' AFTER `agent_grade`;
-- 2021-05-31 21:32:26 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `avatarUrl` varchar(100) COMMENT '微信头像' AFTER `openid`;
-- 2021-05-31 21:32:26 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `city` varchar(100) COMMENT '微信城市' AFTER `avatarUrl`;
-- 2021-05-31 21:32:26 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `country` varchar(100) COMMENT '微信国家' AFTER `city`;
-- 2021-05-31 21:32:26 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `gender` varchar(100) COMMENT '微信性别' AFTER `country`;
-- 2021-05-31 21:32:26 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `language` varchar(100) COMMENT '微信语言' AFTER `gender`;
-- 2021-05-31 21:32:26 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `nickName` varchar(100) COMMENT '微信昵称' AFTER `language`;
-- 2021-05-31 21:32:26 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `province` varchar(100) COMMENT '微信省份' AFTER `nickName`;
-- 2021-05-31 21:32:26 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `province`;
-- 2021-05-31 21:34:43 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `openid` varchar(100) COMMENT '微信openid' AFTER `id`;
-- 2021-05-31 21:34:43 by Achin
ALTER TABLE `sldz_user` CHANGE COLUMN language `languager` varchar(100) COMMENT '微信语言' AFTER `gender`;
-- 2021-05-31 21:34:43 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `nickName` varchar(100) COMMENT '微信昵称' AFTER `languager`;
-- 2021-05-31 21:34:43 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_name`;
-- 2021-05-31 21:34:43 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_phone`;
-- 2021-05-31 21:34:43 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_pasword`;
-- 2021-05-31 21:34:44 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_gender`;
-- 2021-05-31 21:34:44 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_idcar`;
-- 2021-05-31 21:34:44 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_sheng`;
-- 2021-05-31 21:34:44 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_shi`;
-- 2021-05-31 21:34:44 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_qu`;
-- 2021-05-31 21:34:44 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_jiedao`;
-- 2021-05-31 21:34:44 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `agent_grade`;
-- 2021-05-31 21:43:39 by Achin
CREATE TABLE `sldz_agent`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '代理商';
-- 2021-05-31 21:48:59 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_name` varchar(100) COMMENT '代理商名字' AFTER `id`;
-- 2021-05-31 21:48:59 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_phone` varchar(100) COMMENT '代理商手机' AFTER `agent_name`;
-- 2021-05-31 21:48:59 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_pasword` varchar(100) COMMENT '代理商密码' AFTER `agent_phone`;
-- 2021-05-31 21:48:59 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_gender` varchar(100) COMMENT '性别 1 为男 2为女' AFTER `agent_pasword`;
-- 2021-05-31 21:48:59 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_idcar` varchar(100) COMMENT '代理商身份证号码' AFTER `agent_gender`;
-- 2021-05-31 21:48:59 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_sheng` varchar(100) COMMENT '省' AFTER `agent_idcar`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_shi` varchar(100) COMMENT '市' AFTER `agent_sheng`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_qu` varchar(100) COMMENT '区/县' AFTER `agent_shi`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_jiedao` varchar(100) COMMENT '街道' AFTER `agent_qu`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_grade` varchar(100) COMMENT '等级' AFTER `agent_jiedao`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `openid` varchar(100) COMMENT '微信openid' AFTER `agent_grade`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `avatarUrl` varchar(100) COMMENT '微信头像' AFTER `openid`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `city` varchar(100) COMMENT '微信城市' AFTER `avatarUrl`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `country` varchar(100) COMMENT '微信国家' AFTER `city`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `gender` varchar(100) COMMENT '微信性别' AFTER `country`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `languager` varchar(100) COMMENT '微信语言' AFTER `gender`;
-- 2021-05-31 21:49:00 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `nickName` varchar(100) COMMENT '微信昵称' AFTER `languager`;
-- 2021-05-31 21:49:01 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `province` varchar(100) COMMENT '微信省份' AFTER `nickName`;
-- 2021-05-31 21:49:01 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `province`;
-- 2021-06-01 13:13:35 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `nickName` varchar(100) COMMENT '微信昵称' AFTER `gender`;
-- 2021-06-01 13:13:35 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `languager`;
-- 2021-06-01 13:13:49 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `nickName` varchar(100) COMMENT '微信昵称' AFTER `gender`;
-- 2021-06-01 13:13:49 by Achin
ALTER TABLE `sldz_agent` DROP COLUMN `languager`;
-- 2021-06-01 15:09:51 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `openid` varchar(100) NOT NULL COMMENT '微信openid' AFTER `id`;
-- 2021-06-01 15:09:51 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `avatarUrl` varchar(300) COMMENT '微信头像' AFTER `openid`;
-- 2021-06-01 15:09:51 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `seesion_key` varchar(150) COMMENT 'seesion_key' AFTER `province`;
-- 2021-06-01 15:09:51 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `seesion_key`;
-- 2021-06-01 15:17:26 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `province`;
-- 2021-06-01 15:17:26 by Achin
ALTER TABLE `sldz_user` DROP COLUMN `seesion_key`;
-- 2021-06-01 18:19:51 by Achin
CREATE TABLE `sldz_admin`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '管理员';
-- 2021-06-01 18:20:23 by Achin
ALTER TABLE `sldz_admin` ADD COLUMN `phone` varchar(100) COMMENT '账号' AFTER `id`;
-- 2021-06-01 18:20:23 by Achin
ALTER TABLE `sldz_admin` ADD COLUMN `password` varchar(150) COMMENT '密码' AFTER `phone`;
-- 2021-06-01 18:20:23 by Achin
ALTER TABLE `sldz_admin` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `password`;
-- 2021-06-02 17:34:52 by Achin
CREATE TABLE `sldz_agent_rel`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '代理商关系表';
-- 2021-06-02 17:35:55 by Achin
ALTER TABLE `sldz_agent_rel` ADD COLUMN `sup_id` bigint COMMENT '上级id' AFTER `id`;
-- 2021-06-02 17:35:56 by Achin
ALTER TABLE `sldz_agent_rel` ADD COLUMN `sub_id` bigint COMMENT '下级id' AFTER `sup_id`;
-- 2021-06-02 17:35:56 by Achin
ALTER TABLE `sldz_agent_rel` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `sub_id`;
-- 2021-06-02 17:39:51 by Achin
CREATE TABLE `sldz_agent_grade`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '代理商等级';
-- 2021-06-02 17:40:18 by Achin
ALTER TABLE `sldz_agent_grade` ADD COLUMN `grade_name` varchar(100) COMMENT '等级名称' AFTER `id`;
-- 2021-06-02 17:40:19 by Achin
ALTER TABLE `sldz_agent_grade` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `grade_name`;
-- 2021-06-02 17:42:14 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_grade_id` bigint COMMENT '等级id' AFTER `id`;
-- 2021-06-02 17:42:14 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `agent_name` varchar(100) COMMENT '代理商名字' AFTER `agent_grade_id`;
-- 2021-06-02 19:16:42 by Achin
ALTER TABLE `sldz_agent_rel` CHANGE COLUMN sup_id `sup_random` bigint COMMENT '上级编号' AFTER `id`;
-- 2021-06-02 19:16:42 by Achin
ALTER TABLE `sldz_agent_rel` MODIFY COLUMN `sup_random` bigint COMMENT '上级编号' AFTER `id`;
-- 2021-06-02 19:16:42 by Achin
ALTER TABLE `sldz_agent_rel` CHANGE COLUMN sub_id `sub_random` bigint COMMENT '下级编号' AFTER `sup_random`;
-- 2021-06-02 19:16:42 by Achin
ALTER TABLE `sldz_agent_rel` MODIFY COLUMN `sub_random` bigint COMMENT '下级编号' AFTER `sup_random`;
-- 2021-06-02 19:16:42 by Achin
ALTER TABLE `sldz_agent_rel` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `sub_random`;
-- 2021-06-02 19:20:09 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_random` varchar(100) COMMENT '代理商唯一编号' AFTER `agent_grade_id`;
-- 2021-06-02 19:20:09 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `agent_name` varchar(100) COMMENT '代理商名字' AFTER `agent_random`;
-- 2021-06-02 19:23:50 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_bonus` double NOT NULL DEFAULT 0 COMMENT '代理商奖励金' AFTER `agent_grade`;
-- 2021-06-02 19:23:50 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_integral` double NOT NULL DEFAULT 0 COMMENT '代理商积分' AFTER `agent_bonus`;
-- 2021-06-02 19:23:50 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `openid` varchar(100) COMMENT '微信openid' AFTER `agent_integral`;
-- 2021-06-02 19:26:46 by Achin
CREATE TABLE `sldz_agent_bonus_log`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '代理商奖励金记录';
-- 2021-06-02 19:34:51 by Achin
ALTER TABLE `sldz_agent_bonus_log` ADD COLUMN `agent_id` bigint COMMENT '代理商ID' AFTER `id`;
-- 2021-06-02 19:34:51 by Achin
ALTER TABLE `sldz_agent_bonus_log` ADD COLUMN `ronus_type` bigint COMMENT '1是收入2是支出' AFTER `agent_id`;
-- 2021-06-02 19:34:51 by Achin
ALTER TABLE `sldz_agent_bonus_log` ADD COLUMN `ronus_money` double NOT NULL DEFAULT 0 COMMENT '金额' AFTER `ronus_type`;
-- 2021-06-02 19:34:51 by Achin
ALTER TABLE `sldz_agent_bonus_log` ADD COLUMN `ronus_event` varchar(150) COMMENT '事件' AFTER `ronus_money`;
-- 2021-06-02 19:34:51 by Achin
ALTER TABLE `sldz_agent_bonus_log` ADD COLUMN `ronus_date` timestamp NULL COMMENT '日期' AFTER `ronus_event`;
-- 2021-06-02 19:34:51 by Achin
ALTER TABLE `sldz_agent_bonus_log` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `ronus_date`;
-- 2021-06-02 19:36:41 by Achin
CREATE TABLE `sldz_agent_integral_log`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '代理商积分纪录';
-- 2021-06-02 19:39:05 by Achin
ALTER TABLE `sldz_agent_integral_log` ADD COLUMN `agent_id` bigint COMMENT '代理商ID' AFTER `id`;
-- 2021-06-02 19:39:05 by Achin
ALTER TABLE `sldz_agent_integral_log` ADD COLUMN `integral_type` bigint COMMENT '1是收入2是支出' AFTER `agent_id`;
-- 2021-06-02 19:39:05 by Achin
ALTER TABLE `sldz_agent_integral_log` ADD COLUMN `integral_money` double COMMENT '金额' AFTER `integral_type`;
-- 2021-06-02 19:39:05 by Achin
ALTER TABLE `sldz_agent_integral_log` ADD COLUMN `integral_event` varchar(150) COMMENT '事件' AFTER `integral_money`;
-- 2021-06-02 19:39:05 by Achin
ALTER TABLE `sldz_agent_integral_log` ADD COLUMN `integral_date` date NULL COMMENT '日期' AFTER `integral_event`;
-- 2021-06-02 19:39:05 by Achin
ALTER TABLE `sldz_agent_integral_log` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `integral_date`;
-- 2021-06-04 11:50:41 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `agent_random` varchar(100) COMMENT '唯一编号' AFTER `agent_grade_id`;
-- 2021-06-04 11:51:22 by Achin
ALTER TABLE `sldz_user` ADD COLUMN `random` varchar(100) COMMENT '唯一编号' AFTER `province`;
-- 2021-06-04 11:51:22 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `random`;
-- 2021-06-04 13:10:35 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `agent_bonus` double DEFAULT 0.00 COMMENT '代理商奖励金' AFTER `agent_grade`;
-- 2021-06-04 13:10:35 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `agent_integral` double DEFAULT 0.00 COMMENT '代理商积分' AFTER `agent_bonus`;
-- 2021-06-04 17:48:11 by Achin
ALTER TABLE `sldz_user` MODIFY COLUMN `avatarUrl` varchar(600) COMMENT '微信头像' AFTER `openid`;
-- 2021-06-04 17:48:23 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `avatarUrl` varchar(600) COMMENT '微信头像' AFTER `openid`;
-- 2021-06-07 14:29:27 by Achin
ALTER TABLE `sldz_agent_rel` MODIFY COLUMN `sup_random` varchar(100) COMMENT '上级编号' AFTER `id`;
-- 2021-06-07 14:29:27 by Achin
ALTER TABLE `sldz_agent_rel` MODIFY COLUMN `sub_random` varchar(100) COMMENT '下级编号' AFTER `sup_random`;
-- 2021-06-09 12:39:34 by Achin
ALTER TABLE `sldz_agent` ADD COLUMN `agent_group_id` bigint COMMENT '分组id' AFTER `agent_integral`;
-- 2021-06-09 12:39:34 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `openid` varchar(100) COMMENT '微信openid' AFTER `agent_group_id`;
-- 2021-06-09 12:40:02 by Achin
CREATE TABLE `sldz_agent_group`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '代理商分组';
-- 2021-06-09 12:40:28 by Achin
ALTER TABLE `sldz_agent_group` ADD COLUMN `group_name` varchar(100) COMMENT '分组名称' AFTER `id`;
-- 2021-06-09 12:40:28 by Achin
ALTER TABLE `sldz_agent_group` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `group_name`;
-- 2021-06-09 13:03:56 by Achin
ALTER TABLE `sldz_agent` MODIFY COLUMN `agent_group_id` bigint DEFAULT 0 COMMENT '分组id' AFTER `agent_integral`;
-- 2021-06-09 13:30:41 by Achin
CREATE TABLE `sldz_bonu_ssetting`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '奖励金设置';
-- 2021-06-09 13:32:19 by Achin
ALTER TABLE `sldz_bonu_ssetting` ADD COLUMN `product_id` bigint DEFAULT 0 COMMENT '产品id' AFTER `id`;
-- 2021-06-09 13:32:19 by Achin
ALTER TABLE `sldz_bonu_ssetting` ADD COLUMN `agent_id` bigint DEFAULT 0 COMMENT '代理商id' AFTER `product_id`;
-- 2021-06-09 13:32:20 by Achin
ALTER TABLE `sldz_bonu_ssetting` ADD COLUMN `bonus` double DEFAULT 0.00 COMMENT '奖励金' AFTER `agent_id`;
-- 2021-06-09 13:32:20 by Achin
ALTER TABLE `sldz_bonu_ssetting` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `bonus`;
-- 2021-06-09 13:36:52 by Achin
ALTER TABLE `sldz_bonu_ssetting` CHANGE COLUMN agent_id `agent_random` varchar(100) DEFAULT '0' COMMENT '代理商唯一编码' AFTER `product_id`;
-- 2021-06-09 13:36:52 by Achin
ALTER TABLE `sldz_bonu_ssetting` MODIFY COLUMN `agent_random` varchar(100) DEFAULT '0' COMMENT '代理商唯一编码' AFTER `product_id`;
-- 2021-06-09 13:36:52 by Achin
ALTER TABLE `sldz_bonu_ssetting` MODIFY COLUMN `bonus` double DEFAULT 0 COMMENT '奖励金' AFTER `agent_random`;
-- 2021-06-09 18:16:44 by Achin
CREATE TABLE `sldz_agent_prduct_price`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '代理商产品价格';
-- 2021-06-09 18:20:06 by Achin
ALTER TABLE `sldz_agent_product_price` ADD COLUMN `product_id` bigint COMMENT '产品id' AFTER `id`;
-- 2021-06-09 18:20:06 by Achin
ALTER TABLE `sldz_agent_product_price` ADD COLUMN `agent_random` varchar(100) COMMENT '代理商编码' AFTER `product_id`;
-- 2021-06-09 18:20:06 by Achin
ALTER TABLE `sldz_agent_product_price` ADD COLUMN `product_price` double COMMENT '价格' AFTER `agent_random`;
-- 2021-06-09 18:20:06 by Achin
ALTER TABLE `sldz_agent_product_price` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `product_price`;
-- 2021-06-11 15:32:48 by Achin
CREATE TABLE `sldz_agent_level_reward`( `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'ID',`is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记',`create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',PRIMARY KEY (`id`))AUTO_INCREMENT=10000002 DEFAULT CHARSET=utf8mb4 COMMENT '代理层级奖励设置';
-- 2021-06-11 15:35:18 by Achin
ALTER TABLE `sldz_agent_level_reward` ADD COLUMN `reward_direct` double COMMENT '直推奖励' AFTER `id`;
-- 2021-06-11 15:35:19 by Achin
ALTER TABLE `sldz_agent_level_reward` ADD COLUMN `reward_indirect` double COMMENT '间推奖励' AFTER `reward_direct`;
-- 2021-06-11 15:35:19 by Achin
ALTER TABLE `sldz_agent_level_reward` MODIFY COLUMN `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标记' AFTER `reward_indirect`;
-- 2021-06-11 15:36:36 by Achin
ALTER TABLE `sldz_agent_level_reward` ADD COLUMN `agent_random` varchar(100) COMMENT '代理商编码' AFTER `id`;
-- 2021-06-11 15:36:36 by Achin
ALTER TABLE `sldz_agent_level_reward` MODIFY COLUMN `reward_direct` double COMMENT '直推奖励' AFTER `agent_random`;
-- 2021-06-17 15:18:33 by Achin
ALTER TABLE `sldz_agent_integral_log` CHANGE COLUMN agent_id `agent_random` varchar(100) COMMENT '代理商唯一编码' AFTER `id`;
-- 2021-06-17 15:18:33 by Achin
ALTER TABLE `sldz_agent_integral_log` MODIFY COLUMN `agent_random` varchar(100) COMMENT '代理商唯一编码' AFTER `id`;
-- 2021-06-17 15:18:34 by Achin
ALTER TABLE `sldz_agent_integral_log` MODIFY COLUMN `integral_type` bigint COMMENT '1是收入2是支出' AFTER `agent_random`;
-- 2021-06-17 15:20:32 by Achin
ALTER TABLE `sldz_agent_integral_log` MODIFY COLUMN `integral_date` varchar(100) COMMENT '日期' AFTER `integral_event`;
-- 2021-06-18 13:42:49 by Achin
ALTER TABLE `sldz_agent_bonus_log` CHANGE COLUMN agent_id `agent_random` varchar(100) COMMENT '代理商唯一编码' AFTER `id`;
-- 2021-06-18 13:42:49 by Achin
ALTER TABLE `sldz_agent_bonus_log` MODIFY COLUMN `agent_random` varchar(100) COMMENT '代理商唯一编码' AFTER `id`;
-- 2021-06-18 13:42:49 by Achin
ALTER TABLE `sldz_agent_bonus_log` MODIFY COLUMN `ronus_type` bigint COMMENT '1是收入2是支出' AFTER `agent_random`;
-- 2021-06-18 13:45:33 by Achin
ALTER TABLE `sldz_agent_bonus_log` MODIFY COLUMN `ronus_date` varchar(100) COMMENT '日期' AFTER `ronus_event`;
