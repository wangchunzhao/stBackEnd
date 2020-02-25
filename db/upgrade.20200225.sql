delete from sap_customer_class;
delete from sap_order_type_and_customer_class;

-- -----------------------------------------------------
-- Data for table `sap_customer_class`
-- -----------------------------------------------------
INSERT INTO `sap_customer_class` (`code`, `name`) VALUES ('01', '直销');
INSERT INTO `sap_customer_class` (`code`, `name`) VALUES ('02', '经销商');
INSERT INTO `sap_customer_class` (`code`, `name`) VALUES ('03', '经销商&直销');

-- -----------------------------------------------------
-- Data for table `sap_order_type_and_customer_class`
-- -----------------------------------------------------
INSERT INTO `sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0D', '01');
INSERT INTO `sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0T', '02');
INSERT INTO `sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0M', '01');
INSERT INTO `sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0M', '02');

update sap_customer set sap_customer_class_code = '01' where sap_customer_class_code = '10';
update sap_customer set sap_customer_class_code = '02' where sap_customer_class_code = '20';
