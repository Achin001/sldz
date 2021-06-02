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
