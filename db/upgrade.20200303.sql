ALTER TABLE `k_item` 
ADD COLUMN `b2c_estimated_cost` DECIMAL(13,2) NULL COMMENT 'B2C评估成本' AFTER `b2c_estimated_price`;
