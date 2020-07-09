ALTER TABLE `k_bpm_dicision` 
CHANGE COLUMN `body_discount` `body_discount` DOUBLE(5,2) NULL DEFAULT NULL COMMENT '申请柜体折扣' ,
CHANGE COLUMN `approved_body_discount` `approved_body_discount` DOUBLE(5,2) NULL DEFAULT NULL COMMENT '批准柜体折扣' ,
CHANGE COLUMN `main_diccount` `main_diccount` DOUBLE(5,2) NULL DEFAULT NULL COMMENT '申请机组折扣' ,
CHANGE COLUMN `approved_main_discount` `approved_main_discount` DOUBLE(5,2) NULL DEFAULT NULL COMMENT '批准机组折扣' ;
