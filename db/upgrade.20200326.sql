ALTER TABLE `k_order_info` 
ADD COLUMN `is_special_order` INT(11) NULL DEFAULT 0 COMMENT '是否特批下单' AFTER `unpredictable`;
