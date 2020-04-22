ALTER TABLE `k_order_info` 
CHANGE COLUMN `body_discount` `body_discount` DOUBLE(5,4) NULL DEFAULT NULL COMMENT '柜体折扣' ,
CHANGE COLUMN `approved_body_discount` `approved_body_discount` DOUBLE(5,4) NULL DEFAULT NULL COMMENT '批准的柜体折扣' ,
CHANGE COLUMN `main_discount` `main_discount` DOUBLE(5,4) NULL DEFAULT NULL COMMENT '机组折扣' ,
CHANGE COLUMN `approved_main_discount` `approved_main_discount` DOUBLE(5,4) NULL DEFAULT NULL COMMENT '批准的机组折扣' ,
CHANGE COLUMN `discount` `discount` DOUBLE(5,4) NULL DEFAULT NULL COMMENT '合并折扣' ;

ALTER TABLE `k_item` 
CHANGE COLUMN `discount` `discount` DOUBLE(5,4) NULL DEFAULT NULL COMMENT '折扣' ;

update k_order_info set body_discount = ifnull(body_discount, 0) / 100, main_discount = ifnull(main_discount, 0) / 100 where 1=1;