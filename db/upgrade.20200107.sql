ALTER TABLE `qhc2`.`k_order_info` 
ADD COLUMN `is_bulk_cargo` INT NULL COMMENT '是否全部为散件' AFTER `update_time`,
ADD COLUMN `is_urgent_delivery` INT NULL COMMENT '是否特批发货/紧急发货' AFTER `is_bulk_cargo`,
ADD COLUMN `unpredictable` VARCHAR(128) NULL COMMENT '不可预估项' AFTER `is_urgent_delivery`;
