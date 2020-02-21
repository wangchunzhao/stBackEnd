ALTER TABLE `sap_materials` ADD COLUMN `material_type` VARCHAR(10) NULL AFTER `material_size`;
ALTER TABLE `k_item` ADD COLUMN `item_status` VARCHAR(10) NULL COMMENT '行状态' AFTER `color_options`;
ALTER TABLE `k_order_info` 
CHANGE COLUMN `contactor1_id` `contactor1_id` VARCHAR(64) NULL DEFAULT NULL COMMENT '第一联系人身份证' ,
CHANGE COLUMN `contactor2_id` `contactor2_id` VARCHAR(64) NULL DEFAULT NULL COMMENT '第二联系人身份证' ,
CHANGE COLUMN `contactor3_id` `contactor3_id` VARCHAR(64) NULL DEFAULT NULL COMMENT '第三联系人身份证' ;


