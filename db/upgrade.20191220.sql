-- -----------------------------------------------------
-- Table 旧的权限管理表
-- -----------------------------------------------------
DROP TABLE IF EXISTS `b_application_of_rolechange` ;

DROP TABLE IF EXISTS `b_operation2role` ;

DROP TABLE IF EXISTS `b_users` ;

DROP TABLE IF EXISTS `b_roles` ;

DROP TABLE IF EXISTS `b_operations` ;

DROP VIEW IF EXISTS `user_operation_view` ; 



drop table if exists b_operation;

drop table if exists b_role;

drop table if exists b_role_operation;

drop table if exists b_user;

drop table if exists b_user_role;

/*==============================================================*/
/* Table: b_operation                                           */
/*==============================================================*/
create table b_operation
(
   id                   varchar(4) not null,
   name                 varchar(64) not null comment '名称',
   description          varchar(128) comment '描述',
   parent_id            varchar(4) comment '父ID',
   operation_type       varchar(4) comment '类型，menu为菜单',
   primary key (id)
);

/*==============================================================*/
/* Table: b_role                                                */
/*==============================================================*/
create table b_role
(
   id                   integer unsigned not null auto_increment,
   name                 varchar (64) not null,
   is_active            integer default 1,
   primary key (id)
);

/*==============================================================*/
/* Table: b_role_operation                                      */
/*==============================================================*/
create table b_role_operation
(
   role_id              integer not null,
   operation_id         varchar(4) not null,
   primary key (role_id, operation_id)
);

/*==============================================================*/
/* Table: b_user                                                */
/*==============================================================*/
create table b_user
(
   id                   integer unsigned not null auto_increment,
   user_identity        varchar(45) not null comment 'LDAP域账号，唯一',
   name                 varchar(45) comment '用户姓名',
   user_mail            varchar(45) not null comment '用户邮箱',
   is_active            integer unsigned zerofill not null comment '1 启用； 0 禁用',
   creater              varchar(45) not null comment '创建人域账号',
   create_time          datetime not null comment '创建时间',
   updater              varchar(45) not null comment '修改人账号',
   update_time          datetime not null comment '修改时间',
   tel                  varchar(16),
   primary key (id),
   key user_mail_unique (user_mail)
);

/*==============================================================*/
/* Table: b_user_role                                           */
/*==============================================================*/
create table b_user_role
(
   user_id              integer not null,
   role_id              integer not null,
   primary key (user_id, role_id)
);

alter table b_user_role comment '用户角色关系表';

-- -----------------------------------------------------
-- Data for table `b_operation`
-- -----------------------------------------------------
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1001', '代办任务', NULL, NULL, 'menu');
INSERT INTO `b_operation` (`id`, `name`, `description`, `parent_id`, `operation_type`) VALUES ('1002', 'newOrder', NULL, NULL, 'menu');
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
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (2,'st','st@dxc.com','st name',1,'12345678901','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (3,'sales','wang@dxc.com','sales',1,'12345678901','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (4,'support','support@qhc.com','support',1,'12345678901','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (5,'b2c','b2c@qhc.com','b2c',1,'12345678901','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (6,'enginer','enginer@qhc.com','enginer',1,'12345678901','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (7,'manger','manger@qhc.com','manager',1,'12345678901','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (8,'admin','admin@qhc.com','admin',1,'12345678901','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (9,'ads','ads@carrier.com','ads',1,'','admin',now(),'admin',now());
INSERT INTO `b_user` (`id`,`user_identity`,`user_mail`,`name`,`is_active`,`tel`,`creater`,`create_time`,`updater`,`update_time`) VALUES (10,'abc','aa@qq.com','aa',1,'1','admin',now(),'admin',now());

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
INSERT INTO `b_role_operation` (`operation_id`,`role_id`) VALUES ('1013', 1 );
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
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (2, 2);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (3, 2);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (4, 5);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (5, 3);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (6, 4);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (7, 6);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (9, 3);
INSERT INTO `b_user_role` (`user_id`,`role_id`) VALUES (10, 4);
