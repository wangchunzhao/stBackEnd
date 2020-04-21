ALTER TABLE `k_characteristics` 
CHANGE COLUMN `value_code` `value_code` VARCHAR(45) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin' NULL COMMENT '选定的特征值的代码' ,
CHANGE COLUMN `is_configurable` `is_configurable` INT(11) NULL COMMENT '可配置' ;

ALTER TABLE `k_item_color` 
CHANGE COLUMN `color_code` `color_code` VARCHAR(10) CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_bin' NULL COMMENT '颜色编码' ;

ALTER TABLE `k_order_info` 
CHANGE COLUMN `contract_manager` `contract_manager` VARCHAR(45) NULL COMMENT '支持经理，合同管理员' ;