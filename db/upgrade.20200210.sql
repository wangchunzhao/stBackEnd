ALTER TABLE `k_order_info` 
ADD COLUMN `additional_freight` DECIMAL(13,2) NULL AFTER `maintenance_fee`;
ALTER TABLE `k_item` 
CHANGE COLUMN `actural_prica_of_optional` `actural_price_of_optional` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '可选项实卖价' ,
CHANGE COLUMN `transcation_prica_of_optional` `transcation_price_of_optional` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '可选项转移价' ;
ALTER TABLE `k_item` 
CHANGE COLUMN `actural_price` `actual_price` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '产品实卖价' ,
CHANGE COLUMN `actural_price_of_optional` `actual_price_of_optional` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '可选项实卖价' ,
CHANGE COLUMN `transcation_price` `transaction_price` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '产品转移价' ,
CHANGE COLUMN `transcation_price_of_optional` `transaction_price_of_optional` DECIMAL(13,2) NULL DEFAULT NULL COMMENT '可选项转移价' ;
