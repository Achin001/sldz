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
