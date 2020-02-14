ALTER TABLE `k_item` 
ADD COLUMN `color_options` VARCHAR(100) NULL COMMENT '最终颜色可选项数据格式：喷粉部位:颜色选项,   P01:1,P06:1,P07:1' AFTER `config_comments`;

drop table if exists sap_material_product_class;

/*==============================================================*/
/* Table: sap_material_product_class                            */
/*==============================================================*/
create table sap_material_product_class
(
   material_code        varchar(18) not null comment '物料编码',
   product_class        varchar(10) not null comment '产品系列号',
   primary key (material_code)
);

alter table sap_material_product_class comment '物料产品系列号关系';

drop table if exists sap_product_class;

/*==============================================================*/
/* Table: sap_product_class                                     */
/*==============================================================*/
create table sap_product_class
(
   product_class        varchar(10) not null comment '产品系列号',
   painting_class       varchar(10) not null comment '喷粉部位',
   color_class          varchar(10) comment '颜色分组',
   default_color        varchar(10) comment '默认颜色选项',
   primary key (product_class, painting_class)
);

alter table sap_product_class comment '产品颜色系列';

drop table if exists sap_painting_class;

/*==============================================================*/
/* Table: sap_painting_class                                    */
/*==============================================================*/
create table sap_painting_class
(
   painting_class       varchar(10) not null comment '喷粉部位编码',
   painting_parts       varchar(64) not null comment '喷粉部位描述',
   primary key (painting_class)
);

alter table sap_painting_class comment '产品喷粉部位';

drop table if exists sap_color_class;

/*==============================================================*/
/* Table: sap_color_class                                       */
/*==============================================================*/
create table sap_color_class
(
   color_class          varchar(10) not null comment '颜色分组',
   color_code           varchar(10) not null comment '颜色可选项',
   color_material_code  varchar(32) comment '粉末物料号码',
   color_description    varchar(64) comment '颜色特性值描述',
   primary key (color_class, color_code)
);

alter table sap_color_class comment '颜色分组';

drop table if exists k_item_color;

/*==============================================================*/
/* Table: k_item_color                                          */
/*==============================================================*/
create table k_item_color
(
   id                   integer unsigned not null auto_increment,
   item_id              integer not null comment '行项目ID',
   painting_class       varchar(10) not null comment '喷粉分组',
   color_code           varchar(10) not null comment '颜色编码',
   primary key (id)
);

alter table k_item_color comment '物料颜色选项结果';
