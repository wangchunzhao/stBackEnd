ALTER TABLE `k_order` 
ADD COLUMN `quote_order_id` INT NULL COMMENT '关联的报价单ID，报价单下单后的订单需要关联原有报价单' AFTER `quote_status`;
