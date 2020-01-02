/*
-- Query: select * from b_settings
*/
INSERT INTO `b_settings` (`id`,`code`,`s_value`,`enable_date`,`comment`,`operater`,`opt_time`) VALUES (1,'std_discount','0.48','2019-01-01','标准折扣','wangch','2019-01-01 00:00:00');
INSERT INTO `b_settings` (`id`,`code`,`s_value`,`enable_date`,`comment`,`operater`,`opt_time`) VALUES (2,'tax_rate','0.13','2019-01-01','税率','wangch','2019-01-01 00:00:00');

-- -----------------------------------------------------
-- Data for table `b_operation`
-- -----------------------------------------------------
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1001', '代办任务', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1002', '新建订单', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1003', '订单管理', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1004', '合同管理', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1005', '特批发货', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('800', '报表管理', NULL, NULL, '');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1006', '购销明细报表', NULL, '800', 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1007', '投标跟踪表', NULL, '800', 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('900', '系统管理', NULL, NULL, '');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1008', '用户管理', NULL, '900', 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1009', '角色管理', NULL, '900', 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1010', '参数管理', NULL, '900', 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1011', '全部订单', '查看所有订单', NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1012', '区域订单', '查看本人所在区域订单', NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1013', '用户新增', '', NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1014', 'B2C审核订单', '', NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1015', '工程人员审批订单', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1016', '支持经理审批订单', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1017', '下订单', '新建订单页面，点击下订单', NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1018', '下备货订单', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1019', '运费导入', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1020', '下推订单到SAP', NULL, NULL, 'menu');

/*
-- Query: select * from b_user
*/
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (2,'st','st@dxc.com','st name',1,'12345678901','0841','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (3,'sales','wang@dxc.com','sales',1,'12345678901','0841','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (4,'support','support@qhc.com','support',1,'12345678901','0841','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (5,'b2c','b2c@qhc.com','b2c',1,'12345678901','0841','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (6,'enginer','enginer@qhc.com','enginer',1,'12345678901','0841','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (7,'manger','manger@qhc.com','manager',1,'12345678901','0841','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (8,'admin','admin@qhc.com','admin',1,'12345678901','0841','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (9,'ads','ads@carrier.com','ads',1,'','0841','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`office_code`,`creater`,`create_time`,`updater`,`update_time`) VALUES (10,'abc','aa@qq.com','aa',1,'1','0841','admin',now(),'admin',now());

/*
-- Query: select * from b_role
*/
INSERT INTO `b_role` (`id`,`name`) VALUES (1,'系统管理员');
INSERT INTO `b_role` (`id`,`name`) VALUES (2,'客户经理');
INSERT INTO `b_role` (`id`,`name`) VALUES (3,'B2C');
INSERT INTO `b_role` (`id`,`name`) VALUES (4,'工程人员');
INSERT INTO `b_role` (`id`,`name`) VALUES (5,'支持经理');
INSERT INTO `b_role` (`id`,`name`) VALUES (6,'区域经理');
INSERT INTO `b_role` (`id`,`name`) VALUES (7,'领导组');

/*
-- Query: select * from b_role_operation
*/
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1019', 1 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1010', 1 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1009', 1 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1008', 1 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1017', 2 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1002', 2 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1001', 2 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1003', 2 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1004', 2 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1005', 2 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1006', 2 );
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1013', 2 );

/*
-- Query: select * from b_user_role
*/
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (8, 1);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (2, 2);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (3, 2);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (4, 5);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (5, 3);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (6, 4);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (7, 6);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (9, 3);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (10, 4);

/*
-- Query: select * from b_province
*/
INSERT INTO `b_province` (`code`,`name`) VALUES ('10','北京');
INSERT INTO `b_province` (`code`,`name`) VALUES ('11','上海');
INSERT INTO `b_province` (`code`,`name`) VALUES ('12','天津');
INSERT INTO `b_province` (`code`,`name`) VALUES ('13','重庆');
INSERT INTO `b_province` (`code`,`name`) VALUES ('20','山东');
INSERT INTO `b_province` (`code`,`name`) VALUES ('21','四川');

/*
-- Query: select * from b_city
*/
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('10.10','北京','10');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('11.10','上海','11');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('12.10','天津','12');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('13.10','重庆','13');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.10','济南','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.11','青岛','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.12','淄博','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.13','德州','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.14','烟台','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.15','潍坊','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.16','济宁','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.17','泰安','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.18','临沂','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.19','菏泽','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.20','聊城','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.21','威海','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.22','枣庄','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.23','日照','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.24','滕州','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.25','滨州','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.26','莱芜','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('20.27','东营','20');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.10','成都','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.11','攀枝花','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.12','自贡','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.13','绵阳','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.14','南充','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.15','凉山','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.16','宜宾','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.17','乐山','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.18','都江堰','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.19','雅安','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.20','泸州','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.21','遂宁','21');
INSERT INTO `b_city` (`code`,`name`,`province_code`) VALUES ('21.22','马尔康','21');

/*
-- Query: select * from b_area
*/
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.10','东城','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.11','西城','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.12','崇文','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.13','宣武','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.14','朝阳','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.15','丰台','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.16','石景山','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.17','海淀','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.18','门头沟','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.19','房山','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.20','通州','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.21','顺义','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.22','昌平','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.23','延庆','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.24','大兴','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.25','平谷','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.26','怀柔','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('10.10.27','密云','10.10',95.00,150.00,90.00,0.00,85.00,0.00,111.00,176.00,105.00,0.00,99.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.10','黄浦','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.11','卢湾','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.12','徐汇','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.13','长宁','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.14','静安','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.15','普陀','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.16','闸北','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.17','虹口','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.18','杨浦','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.19','闵行','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.20','宝山','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.21','嘉定','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.22','浦东新','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.23','金山','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.24','松江','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.25','青浦','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.26','崇明','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.27','南汇','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('11.10.28','奉贤','11.10',104.00,150.00,98.00,0.00,91.00,0.00,122.00,176.00,114.00,0.00,107.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.10','和平','12.10',98.00,200.00,94.00,800.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.11','河东','12.10',98.00,200.00,94.00,800.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.12','河西','12.10',98.00,200.00,94.00,800.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.13','南开','12.10',98.00,200.00,94.00,800.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.14','河北','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.15','红桥','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.16','塘沽','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.17','汉沽','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.18','大港','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.19','东丽','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.20','西青','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.21','津南','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.22','北辰','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.23','武清','12.10',98.00,200.00,94.00,0.00,85.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.24','宁河','12.10',98.00,300.00,96.00,0.00,88.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.25','静海','12.10',98.00,300.00,96.00,0.00,88.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.26','宝坻','12.10',98.00,300.00,96.00,0.00,88.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('12.10.27','蓟县','12.10',98.00,300.00,96.00,0.00,88.00,0.00,115.00,260.00,111.00,208.00,100.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.10','渝中','13.10',194.00,150.00,184.00,0.00,174.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.11','大渡口','13.10',194.00,150.00,184.00,0.00,174.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.12','江北','13.10',194.00,150.00,184.00,0.00,174.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.13','沙坪坝','13.10',194.00,150.00,184.00,0.00,174.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.14','九龙坡','13.10',194.00,150.00,184.00,0.00,174.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.15','南岸','13.10',194.00,150.00,184.00,0.00,174.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.16','北碚','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.17','万盛','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.18','双桥','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.19','渝北','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.20','巴南','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.21','万州','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.22','涪陵','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.23','黔江','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.24','合川','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.25','永川','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.26','江津','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.27','南川','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.28','长寿','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.29','潼南','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.30','铜梁','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.31','大足','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.32','荣昌','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.33','璧山','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.34','垫江','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.35','武隆','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.36','丰都','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.37','城口','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.38','梁平','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.39','开县','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.40','巫溪','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.41','巫山','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.42','奉节','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.43','云阳','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.44','忠县','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.45','石柱土家族自治','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.46','彭水苗族土家自治','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.47','西阳土家族苗族','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('13.10.48','秀山土家族苗族','13.10',222.00,150.00,211.00,0.00,200.00,0.00,254.00,176.00,242.00,0.00,230.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.10','市中','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.11','历下','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.12','槐荫','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.13','天桥','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.14','章丘','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.15','历城','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.16','长清','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.17','平阴','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.18','济阳','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.19','商河','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.10.20','钢城','20.10',73.00,150.00,66.00,0.00,63.00,0.00,85.00,176.00,77.00,0.00,74.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.10','市南','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.11','市北','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.12','四方','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.13','黄岛','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.14','崂山','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.15','城阳','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.16','李沧','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.17','胶州','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.18','即墨','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.19','平度','20.11',43.00,150.00,42.00,0.00,27.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.20','胶南','20.11',27.00,150.00,26.00,0.00,24.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.11.21','莱西','20.11',43.00,150.00,42.00,0.00,27.00,0.00,35.00,176.00,34.00,0.00,29.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.12.10','张店','20.12',62.00,100.00,52.00,0.00,43.00,0.00,72.00,161.00,61.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.12.11','淄川','20.12',62.00,150.00,52.00,0.00,43.00,0.00,72.00,161.00,61.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.12.12','博山','20.12',62.00,150.00,52.00,0.00,43.00,0.00,72.00,161.00,61.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.12.13','周村','20.12',62.00,100.00,52.00,0.00,43.00,0.00,72.00,161.00,61.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.12.14','桓台','20.12',62.00,150.00,52.00,0.00,43.00,0.00,72.00,161.00,61.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.12.15','高青','20.12',62.00,150.00,52.00,0.00,43.00,0.00,72.00,161.00,61.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.12.16','沂源','20.12',62.00,150.00,52.00,0.00,43.00,0.00,72.00,161.00,61.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.12.17','临淄','20.12',62.00,150.00,52.00,0.00,43.00,0.00,72.00,161.00,61.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.10','德城','20.13',69.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.11','乐陵','20.13',78.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.12','禹城','20.13',69.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.13','陵县','20.13',69.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.14','平原','20.13',78.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.15','夏津','20.13',78.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.16','武城','20.13',78.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.17','齐河','20.13',78.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.18','临邑','20.13',78.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.19','宁津','20.13',78.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.13.20','庆云','20.13',78.00,150.00,62.00,0.00,60.00,0.00,89.00,176.00,73.00,0.00,70.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.10','芝罘','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.11','福山','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.12','莱山','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.13','牟平','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.14','栖霞','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.15','海阳','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.16','龙口','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.17','莱阳','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.18','莱州','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.19','蓬莱','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.20','招远','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.14.21','长岛','20.14',52.00,100.00,46.00,0.00,43.00,0.00,61.00,117.00,54.00,0.00,50.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.10','潍城','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.11','寒亭','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.12','坊子','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.13','奎文','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.14','安丘','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.15','昌邑','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.16','高密','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.17','青州','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.18','诸城','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.19','寿光','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.20','临朐','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.15.21','昌乐','20.15',58.00,150.00,55.00,0.00,50.00,0.00,68.00,176.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.10','市中','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.11','任城','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.12','曲阜','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.13','兖州','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.14','邹城','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.15','微山','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.16','鱼台','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.17','金乡','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.18','嘉祥','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.19','汶上','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.20','泗水','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.16.21','梁山','20.16',78.00,150.00,77.00,0.00,67.00,0.00,92.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.17.10','泰山','20.17',83.00,150.00,77.00,0.00,67.00,0.00,98.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.17.11','岱岳','20.17',83.00,150.00,77.00,0.00,67.00,0.00,98.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.17.12','新泰','20.17',83.00,150.00,77.00,0.00,67.00,0.00,98.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.17.13','肥城','20.17',83.00,150.00,77.00,0.00,67.00,0.00,98.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.17.14','宁阳','20.17',83.00,150.00,77.00,0.00,67.00,0.00,98.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.17.15','东平','20.17',83.00,150.00,77.00,0.00,67.00,0.00,98.00,176.00,90.00,0.00,79.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.10','兰山','20.18',57.00,100.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.11','罗庄','20.18',57.00,120.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.12','河东','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.13','郯城','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.14','苍山','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.15','莒南','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.16','沂水','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.17','蒙阴','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.18','平邑','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.19','费县','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.20','沂南','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.21','临沭','20.18',57.00,150.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.18.22','兰陵','20.18',57.00,120.00,51.00,0.00,45.00,0.00,67.00,168.00,60.00,0.00,52.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.10','牡丹','20.19',83.00,150.00,78.00,0.00,74.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.11','开发','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.12','郓城','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.13','成武','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.14','曹县','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.15','单县','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.16','东明','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.17','定陶','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.18','巨野','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.19.19','鄄城','20.19',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,84.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.10','东昌府','20.20',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.11','临清','20.20',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.12','阳谷','20.20',83.00,150.00,77.00,0.00,62.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.13','莘县','20.20',83.00,150.00,77.00,0.00,62.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.14','茌平','20.20',83.00,150.00,77.00,0.00,62.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.15','东阿','20.20',83.00,150.00,77.00,0.00,62.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.16','冠县','20.20',83.00,150.00,77.00,0.00,62.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.17','高唐','20.20',83.00,150.00,77.00,0.00,62.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.20.18','开发区','20.20',83.00,150.00,77.00,0.00,72.00,0.00,98.00,176.00,90.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.21.10','环翠','20.21',57.00,100.00,55.00,0.00,49.00,0.00,67.00,117.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.21.11','荣成','20.21',57.00,100.00,55.00,0.00,51.00,0.00,67.00,117.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.21.12','乳山','20.21',57.00,100.00,55.00,0.00,49.00,0.00,67.00,117.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.21.13','文登','20.21',57.00,100.00,55.00,0.00,49.00,0.00,67.00,117.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.21.14','石岛','20.21',57.00,100.00,55.00,0.00,51.00,0.00,67.00,117.00,64.00,0.00,59.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.22.10','市中','20.22',74.00,150.00,72.00,0.00,65.00,0.00,86.00,176.00,84.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.22.11','薛城','20.22',74.00,150.00,72.00,0.00,65.00,0.00,86.00,176.00,84.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.22.12','峄城','20.22',74.00,150.00,72.00,0.00,65.00,0.00,86.00,176.00,84.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.22.13','台儿庄','20.22',74.00,150.00,72.00,0.00,65.00,0.00,86.00,176.00,84.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.22.14','山亭','20.22',74.00,150.00,72.00,0.00,65.00,0.00,86.00,176.00,84.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.22.15','滕州','20.22',74.00,150.00,72.00,0.00,65.00,0.00,86.00,176.00,84.00,0.00,76.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.23.10','东港','20.23',48.00,150.00,46.00,0.00,36.00,0.00,56.00,152.00,53.00,0.00,43.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.23.11','五莲','20.23',48.00,150.00,46.00,0.00,36.00,0.00,56.00,152.00,53.00,0.00,43.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.23.12','莒县','20.23',48.00,150.00,46.00,0.00,36.00,0.00,56.00,152.00,53.00,0.00,43.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.23.13','蓝山','20.23',48.00,100.00,46.00,0.00,36.00,0.00,56.00,152.00,53.00,0.00,43.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.23.14','岚山','20.23',48.00,100.00,46.00,0.00,36.00,0.00,56.00,152.00,53.00,0.00,43.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.24.10','市区','20.24',71.00,150.00,70.00,0.00,63.00,0.00,83.00,176.00,82.00,0.00,73.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.10','滨城','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.11','惠民','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.12','阳信','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.13','无棣','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.14','沾化','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.15','博兴','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.16','邹平','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.17','柳桥','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.25.18','魏桥','20.25',67.00,100.00,60.00,0.00,51.00,0.00,78.00,117.00,71.00,0.00,60.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.26.10','莱城','20.26',66.00,100.00,60.00,0.00,56.00,0.00,77.00,117.00,71.00,0.00,65.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.26.11','钢城','20.26',66.00,100.00,60.00,0.00,56.00,0.00,77.00,117.00,71.00,0.00,65.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.27.10','东营','20.27',62.00,100.00,57.00,0.00,52.00,0.00,72.00,137.00,67.00,0.00,61.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.27.11','河口','20.27',62.00,100.00,57.00,0.00,52.00,0.00,72.00,137.00,67.00,0.00,61.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.27.12','垦利','20.27',62.00,100.00,57.00,0.00,52.00,0.00,72.00,137.00,67.00,0.00,61.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.27.13','利津','20.27',62.00,100.00,57.00,0.00,52.00,0.00,72.00,137.00,67.00,0.00,61.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.27.14','广饶','20.27',62.00,150.00,57.00,0.00,52.00,0.00,72.00,137.00,67.00,0.00,61.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('20.27.15','大王','20.27',62.00,150.00,57.00,0.00,52.00,0.00,72.00,137.00,67.00,0.00,61.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.10','青羊','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.11','锦江','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.12','金牛','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.13','武侯','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.14','成华','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.15','龙泉驿','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.16','青白江','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.17','崇州','21.10',161.00,150.00,158.00,0.00,156.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.18','邛崃','21.10',161.00,150.00,158.00,0.00,156.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.19','都江宴','21.10',161.00,150.00,158.00,0.00,156.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.20','彭州','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.21','金堂','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.22','双流','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.23','温江','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.24','郫县','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.25','新都','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.26','大邑','21.10',185.00,150.00,180.00,0.00,170.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.27','蒲江','21.10',185.00,150.00,180.00,0.00,170.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.28','新津','21.10',185.00,150.00,180.00,0.00,170.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.29','郫都','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.30','高新','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.10.31','天府新','21.10',161.00,150.00,158.00,0.00,153.00,0.00,193.00,176.00,189.00,0.00,183.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.11.10','东区','21.11',280.00,150.00,270.00,0.00,260.00,0.00,365.00,176.00,339.00,0.00,328.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.11.11','西区','21.11',280.00,150.00,270.00,0.00,260.00,0.00,365.00,176.00,339.00,0.00,328.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.11.12','仁和','21.11',310.00,150.00,270.00,0.00,260.00,0.00,365.00,176.00,339.00,0.00,328.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.11.13','米易','21.11',310.00,150.00,270.00,0.00,260.00,0.00,365.00,176.00,339.00,0.00,328.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.11.14','盐边','21.11',380.00,150.00,367.00,0.00,362.00,0.00,365.00,176.00,339.00,0.00,328.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.12.10','自流井','21.12',220.00,150.00,210.00,0.00,196.00,0.00,265.00,176.00,254.00,0.00,235.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.12.11','贡井','21.12',220.00,150.00,210.00,0.00,196.00,0.00,265.00,176.00,254.00,0.00,235.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.12.12','大安','21.12',220.00,150.00,210.00,0.00,196.00,0.00,265.00,176.00,254.00,0.00,235.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.12.13','沿滩','21.12',220.00,150.00,210.00,0.00,196.00,0.00,265.00,176.00,254.00,0.00,235.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.12.14','荣县','21.12',240.00,150.00,230.00,0.00,210.00,0.00,265.00,176.00,254.00,0.00,235.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.12.15','富顺','21.12',240.00,150.00,230.00,0.00,210.00,0.00,265.00,176.00,254.00,0.00,235.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.10','涪城','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.11','游仙','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.12','江油','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.13','三台','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.14','盐亭','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.15','安县','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.16','梓潼','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.17','北川','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.13.18','平武','21.13',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.10','顺庆','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.11','高坪','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.12','嘉陵','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.13','阆中','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.14','南部','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.15','营山','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.16','蓬安','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.17','仪陇','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.14.18','西充','21.14',185.00,150.00,183.00,0.00,177.00,0.00,217.00,176.00,214.00,0.00,207.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.10','西昌','21.15',260.00,150.00,254.00,0.00,235.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.11','盐源','21.15',260.00,150.00,254.00,0.00,235.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.12','德昌','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.13','会理','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.14','会东','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.15','宁南','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.16','普格','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.17','布施','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.18','金阳','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.19','昭觉','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.20','喜德','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.21','冕宁','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.22','越西','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.23','甘洛','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.24','美姑','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.25','雷波','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.15.26','木里','21.15',298.00,150.00,290.00,0.00,272.00,0.00,344.00,176.00,335.00,0.00,313.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.10','翠屏','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.11','宜宾','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.12','南溪','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.13','江安','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.14','长宁','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.15','高县','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.16','筠连','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.17','珙县','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.18','兴文','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.16.19','屏山','21.16',215.00,150.00,205.00,0.00,196.00,0.00,252.00,176.00,240.00,0.00,229.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.10','市中','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.11','沙湾','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.12','五通桥','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.13','金口河','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.14','峨眉山','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.15','犍为','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.16','井研','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.17','夹江','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.18','沐川','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.19','峨边','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.17.20','马边','21.17',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.18.10','市区','21.18',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.19.10','雨城','21.19',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.19.11','名山','21.19',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.19.12','荥经','21.19',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.19.13','汉源','21.19',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.19.14','石棉','21.19',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.19.15','天全','21.19',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.19.16','芦山','21.19',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.19.17','宝兴','21.19',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.20.10','江阳','21.20',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.20.11','纳溪','21.20',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.20.12','龙马潭','21.20',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.20.13','泸县','21.20',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.20.14','合江','21.20',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.20.15','叙永','21.20',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.20.16','古蔺','21.20',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.21.10','市中','21.21',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.21.11','蓬溪','21.21',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.21.12','射洪','21.21',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.21.13','大英','21.21',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.10','马尔康','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.11','汶县','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.12','茂县','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.13','松潘','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.14','九寨沟','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.15','金川','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.16','金县','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.17','黑水','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.18','壤塘','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.19','阿坝','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.20','若尔盖','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);
INSERT INTO `b_area` (`code`,`name`,`city_code`,`price`,`price1`,`price2`,`price3`,`price4`,`price5`,`price6`,`price7`,`price8`,`price9`,`price10`,`price11`) VALUES ('21.22.21','红原','21.22',210.00,150.00,196.00,0.00,192.00,0.00,246.00,176.00,229.00,0.00,225.00,0.00);

/*
-- Query: select * from k_engining_term
*/
INSERT INTO `k_engining_term` (`code`,`name`) VALUES ('BG1GDA00000-X','安装费');
INSERT INTO `k_engining_term` (`code`,`name`) VALUES ('BG1GDB00000-X','材料费');
INSERT INTO `k_engining_term` (`code`,`name`) VALUES ('BG1R8J00000-X','电器费');
INSERT INTO `k_engining_term` (`code`,`name`) VALUES ('BG1R8K00000-X','维保费');
INSERT INTO `k_engining_term` (`code`,`name`) VALUES ('BG1R8R00000-X','冷库费');

-- -----------------------------------------------------
-- Data for table `b_material_group_order`
-- -----------------------------------------------------
INSERT INTO `b_material_group_order` (`code`, `name`) VALUES ('T101', '机柜');
INSERT INTO `b_material_group_order` (`code`, `name`) VALUES ('T102', '机组');
INSERT INTO `b_material_group_order` (`code`, `name`) VALUES ('T103', '冷凝器');
INSERT INTO `b_material_group_order` (`code`, `name`) VALUES ('T104', '冷风机');
INSERT INTO `b_material_group_order` (`code`, `name`) VALUES ('T105', '散件');
INSERT INTO `b_material_group_order` (`code`, `name`) VALUES ('T106', '其它');


-- SAP init data
-- -----------------------------------------------------
-- Data for table `sap_order_type`
-- -----------------------------------------------------
INSERT INTO `sap_order_type` (`code`, `name`) VALUES ('ZH0D', '经销商订单');
INSERT INTO `sap_order_type` (`code`, `name`) VALUES ('ZH0T', '大客户订单');
INSERT INTO `sap_order_type` (`code`, `name`) VALUES ('ZH0M', '备货订单');

-- -----------------------------------------------------
-- Data for table `sap_customer_class`
-- -----------------------------------------------------
INSERT INTO `sap_customer_class` (`code`, `name`) VALUES ('01', '直销');
INSERT INTO `sap_customer_class` (`code`, `name`) VALUES ('02', '经销商');

-- -----------------------------------------------------
-- Data for table `sap_order_type_and_customer_class`
-- -----------------------------------------------------
INSERT INTO `sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0D', '01');
INSERT INTO `sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0T', '02');
INSERT INTO `sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0M', '01');
INSERT INTO `sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0M', '02');

-- -----------------------------------------------------
-- Data for table `sap_installation_terms`
-- -----------------------------------------------------
INSERT INTO `sap_installation_terms` (`code`, `name`) VALUES ('01', '招标');
INSERT INTO `sap_installation_terms` (`code`, `name`) VALUES ('02', '自装自提');

-- -----------------------------------------------------
-- Data for table `sap_industry_code`
-- -----------------------------------------------------
INSERT INTO `sap_industry_code` (`code`, `name`, `is_fordealer`) VALUES ('unknow', '未知', 0);
INSERT INTO `sap_industry_code` (`code`, `name`, `is_fordealer`) VALUES ('0001', 'MNC（国际连锁）', 1);
INSERT INTO `sap_industry_code` (`code`, `name`, `is_fordealer`) VALUES ('0002', 'Local top 100-国内连锁百强', 1);
INSERT INTO `sap_industry_code` (`code`, `name`, `is_fordealer`) VALUES ('0003', 'Dealer（代理商）', 1);
INSERT INTO `sap_industry_code` (`code`, `name`, `is_fordealer`) VALUES ('0004', 'Local others（本地其他）', 1);
INSERT INTO `sap_industry_code` (`code`, `name`, `is_fordealer`) VALUES ('0005', 'Cold room（冷库)', 1);
INSERT INTO `sap_industry_code` (`code`, `name`, `is_fordealer`) VALUES ('0006', 'Export（出口）', 1);
INSERT INTO `sap_industry_code` (`code`, `name`, `is_fordealer`) VALUES ('0007', '便利店', 1);

-- -----------------------------------------------------
-- Data for table `sap_price_type`
-- -----------------------------------------------------
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH01', '零售价');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH02', '年采价 ');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH03', '客户折扣');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH05', '实卖价 ');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH06', '转移价 ');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH07', '转移加价百分比');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH10', '运费预估');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH11', '服务外包预估');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZHCS', '内部价格');
INSERT INTO `sap_price_type` (`code`, `name`) VALUES ('ZH08', '成本项目价');

-- -----------------------------------------------------
-- Data for table `sap_unit_of_measurement`
-- -----------------------------------------------------
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('EA', '个/卷/件');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('G', '克');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('KG', '公斤');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('TO', '吨');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('CM', '厘米');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('M', '米');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('KM', '千米');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('M2', '平方米');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('ML', '毫升');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('L', '升');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('M3', '立方米');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('ZA', '个/卷/件');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('SZ', '套');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('ROL', '卷');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('Q6', '扇');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('BOT', '瓶');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('MON', 'MON');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('Q16', 'Q16');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('Q4', 'Q4');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('ST', 'ST');
INSERT INTO `sap_unit_of_measurement` (`code`, `name`) VALUES ('MT', 'MT');

-- -----------------------------------------------------
-- Data for table `sap_material_groups`
-- -----------------------------------------------------
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3212', '安装费', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3233', '材料费', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3235', '电气费', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3236', '维保费', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3105', '冷库', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3231', '其他项目收费', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3237', '不可预估费', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3234', '销售运费', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3101', '整机柜', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3102', '分体柜', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3109', '机组', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3103', '换热器和冷凝器', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('3104', '侧板', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('9101', 'B2C', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('9102', '可选项', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('9103', '追加运费', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('1000', '散件', 'T101', 1);
INSERT INTO `sap_material_groups` (`code`, `name`, `b_material_group_order_code`, `isenable`) VALUES ('9999', '不可用物料类别', 'T106', 0);

-- -----------------------------------------------------
-- Data for table `sap_sales_type`
-- -----------------------------------------------------
INSERT INTO `sap_sales_type` (`code`, `name`) VALUES ('10', '内销');
INSERT INTO `sap_sales_type` (`code`, `name`) VALUES ('20', '出口');
INSERT INTO `sap_sales_type` (`code`, `name`) VALUES ('30', '冷库');

-- -----------------------------------------------------
-- Data for table `sap_shipping_type`
-- -----------------------------------------------------
INSERT INTO `sap_shipping_type` (`code`, `name`) VALUES ('01', '自提');
INSERT INTO `sap_shipping_type` (`code`, `name`) VALUES ('05', '非自提');

-- -----------------------------------------------------
-- Data for table `sap_receive_terms`
-- -----------------------------------------------------
INSERT INTO `sap_receive_terms` (`code`, `name`) VALUES ('01', '盖章接货');
INSERT INTO `sap_receive_terms` (`code`, `name`) VALUES ('02', '授权人签字');
INSERT INTO `sap_receive_terms` (`code`, `name`) VALUES ('03', '其它');



