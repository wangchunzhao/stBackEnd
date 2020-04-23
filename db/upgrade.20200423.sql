ALTER TABLE `sap_materials` 
ADD COLUMN `sap_material_status` VARCHAR(10) NULL COMMENT '跨工厂物料状态' AFTER `sap_clazz_code`,
ADD COLUMN `sap_distribution_status` VARCHAR(10) NULL COMMENT '跨分销链状态' AFTER `sap_material_status`;
