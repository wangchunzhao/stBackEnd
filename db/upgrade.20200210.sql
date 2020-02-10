ALTER TABLE `k_order_info` 
ADD COLUMN `additional_freight` DECIMAL(13,2) NULL AFTER `maintenance_fee`;
ALTER TABLE `k_item` 
CHANGE COLUMN `actural_prica_of_optional` `optional_actual_price` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '可选项实卖价' ,
CHANGE COLUMN `transcation_prica_of_optional` `optional_transaction_price` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '可选项转移价' ;
CHANGE COLUMN `actural_price` `actual_price` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '产品实卖价' ,
CHANGE COLUMN `transcation_price` `transaction_price` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '产品转移价' ;
