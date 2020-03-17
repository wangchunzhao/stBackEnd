ALTER TABLE `k_item` 
ADD COLUMN `optional_standard_price` DECIMAL(13,2) NULL DEFAULT 0 COMMENT '可选项标准价差' AFTER `transaction_price`,
ADD COLUMN `optional_retail_price` DECIMAL(13,2) NULL DEFAULT 0 COMMENT '可选项零售价差' AFTER `optional_standard_price`;
