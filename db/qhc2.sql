-- -----------------------------------------------------
-- Schema qhc2
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `qhc2` ;

-- -----------------------------------------------------
-- Schema qhc2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `qhc2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `qhc2` ;

drop table if exists b_area;

drop table if exists b_city;

drop table if exists b_material_group_order;

drop table if exists b_notify_infor;

drop table if exists b_operation;

drop table if exists b_operation_role;

drop table if exists b_province;

drop table if exists b_role;

drop table if exists b_settings;

drop table if exists b_user;

drop table if exists b_user_role;

drop table if exists k_attached_info;

drop table if exists k_attachment;

drop table if exists k_billing_plan;

drop table if exists k_bpm_dicision;

drop table if exists k_characteristics;

drop table if exists k_configure_material;

drop table if exists k_contract;

drop table if exists k_delievery_address;

drop table if exists k_engining_cost;

drop table if exists k_engining_term;

drop table if exists k_item_b2c;

drop table if exists k_item_detail;

drop table if exists k_order;

drop table if exists k_order_info;

drop table if exists k_speical_order_application;

drop table if exists sap_characteristic;

drop table if exists sap_characteristic_value;

drop table if exists sap_clazz;

drop table if exists sap_clazz_and_character;

drop table if exists sap_color_characteristic;

drop table if exists sap_color_characteristic_value;

drop table if exists sap_currency;

drop table if exists sap_currency_sale_type;

drop table if exists sap_customer;

drop table if exists sap_customer_class;

drop table if exists sap_incoterms;

drop table if exists sap_industry;

drop table if exists sap_industry_and_customer;

drop table if exists sap_industry_code;

drop table if exists sap_installation_terms;

drop table if exists sap_last_updated;

drop table if exists sap_material_default_characteristic;

drop table if exists sap_material_groups;

drop table if exists sap_materials;

drop table if exists sap_materials_price;

drop table if exists sap_order_type;

drop table if exists sap_order_type_and_customer_class;

drop table if exists sap_payment_term_bidding_plan;

drop table if exists sap_price_type;

drop table if exists sap_receive_terms;

drop table if exists sap_sales_group;

drop table if exists sap_sales_office;

drop table if exists sap_sales_type;

drop table if exists sap_shipping_type;

drop table if exists sap_unit_of_measurement;

/*==============================================================*/
/* Table: b_area                                                */
/*==============================================================*/
create table b_area
(
   code                 varchar(32) not null,
   name                 varchar(64) not null,
   city_code            varchar(32) not null,
   price                decimal(13,2) not null comment '供应商<20m³单价',
   price1               decimal(13,2) comment '供应商<20m³送货费',
   price2               decimal(13,2) comment '供应商>20<50m³单价',
   price3               decimal(13,2) comment '供应商>20<50m³送货费',
   price4               decimal(13,2) comment '供应商>=50m³单价',
   price5               decimal(13,2) comment '供应商>50m³送货费',
   price6               decimal(13,2) comment '客户<=20m³单价',
   price7               decimal(13,2) comment '客户<=20m³送货费',
   price8               decimal(13,2) comment '客户>20<50m³单价',
   price9               decimal(13,2) comment '客户>20<50m³送货费',
   price10              decimal(13,2) comment '客户>=50m³单价',
   price11              decimal(13,2) comment '客户>=50m³送货费',
   primary key (code)
);

alter table b_area comment '区';

/*==============================================================*/
/* Table: b_city                                                */
/*==============================================================*/
create table b_city
(
   code                 varchar(32) not null,
   name                 varchar(64) not null,
   province_code        varchar(32) not null,
   primary key (code)
);

alter table b_city comment '市';

/*==============================================================*/
/* Table: b_material_group_order                                */
/*==============================================================*/
create table b_material_group_order
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   primary key (code),
   key id_unique (code)
);

alter table b_material_group_order comment '物料分组，销售工具的物料分组';

/*==============================================================*/
/* Table: b_notify_infor                                        */
/*==============================================================*/
create table b_notify_infor
(
   id                   integer unsigned not null,
   hassend              integer unsigned zerofill not null,
   msg_to               varchar(64) not null,
   msg_from             varchar(64) not null,
   message              varchar(64) not null,
   primary key (id),
   key id_unique (id)
);

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
/* Table: b_operation_role                                      */
/*==============================================================*/
create table b_operation_role
(
   role_id              integer not null,
   operation_id         varchar(4) not null,
   primary key (role_id, operation_id)
);

/*==============================================================*/
/* Table: b_province                                            */
/*==============================================================*/
create table b_province
(
   code                 varchar(32) not null,
   name                 varchar(64) not null,
   primary key (code)
);

alter table b_province comment '省';

/*==============================================================*/
/* Table: b_role                                                */
/*==============================================================*/
create table b_role
(
   id                   integer unsigned not null auto_increment,
   name                 varchar (64) not null,
   primary key (id)
);

/*==============================================================*/
/* Table: b_settings                                            */
/*==============================================================*/
create table b_settings
(
   id                   integer unsigned not null auto_increment,
   code                 varchar(32) not null,
   s_value              varchar(64) not null,
   enable_date          date not null,
   comment              varchar(64),
   operater             varchar(64) not null,
   opt_time             datetime not null,
   primary key (id)
);

alter table b_settings comment '参数配置';

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

/*==============================================================*/
/* Table: k_attached_info                                       */
/*==============================================================*/
create table k_attached_info
(
   id                   integer unsigned not null auto_increment,
   start_date_of_production date comment '最早生产开始日期',
   date_of_on_store     date comment '入库时间',
   lead_time            integer unsigned comment '生产/采购周期',
   opt_time             datetime not null,
   item_detail_id       varchar(32) not null,
   primary key (id),
   key id_unique (id)
);

alter table k_attached_info comment 'sap返回的相关信息';

/*==============================================================*/
/* Table: k_attachment                                          */
/*==============================================================*/
create table k_attachment
(
   id                   integer unsigned not null auto_increment,
   file_name            varchar(64) not null,
   file_url             varchar(128),
   order_info_id        varchar(32) not null,
   primary key (id),
   key id_unique (id)
);

alter table k_attachment comment '附件文件名称';

/*==============================================================*/
/* Table: k_billing_plan                                        */
/*==============================================================*/
create table k_billing_plan
(
   id                   integer unsigned not null auto_increment,
   code                 varchar(4) not null,
   name                 varchar(64),
   amount               decimal(13,2),
   pay_date             date,
   reason               varchar(64),
   order_info_id        varchar(32) not null,
   primary key (id),
   key id_unique (id)
);

alter table k_billing_plan comment '付款条件或billing plan';

/*==============================================================*/
/* Table: k_bpm_dicision                                        */
/*==============================================================*/
create table k_bpm_dicision
(
   id                   integer unsigned not null auto_increment,
   order_info_id        varchar(32) not null,
   approved_main_discount double(3,3),
   main_diccount        double(3,3) not null,
   approved_body_discount double(3,3),
   body_discount        double(3,3) not null,
   is_passed            integer not null,
   primary key (id)
);

alter table k_bpm_dicision comment 'BPM审批结果';

/*==============================================================*/
/* Table: k_characteristics                                     */
/*==============================================================*/
create table k_characteristics
(
   id                   integer unsigned not null auto_increment,
   key_code             varchar(45) not null comment '选定的特征代码',
   value_code           varchar(45) not null comment '选定的特征值的代码',
   is_configurable      integer not null comment '可配置',
   item_detail_id       varchar(32) not null,
   primary key (id),
   key character_unique (key_code)
);

alter table k_characteristics comment '物料选定的特征';

/*==============================================================*/
/* Table: k_configure_material                                  */
/*==============================================================*/
create table k_configure_material
(
   id                   integer not null auto_increment,
   material_code        varchar(45) not null,
   price                decimal(13,2) not null,
   item_detail_id       varchar(32) not null,
   primary key (id),
   key id_unique (id)
);

/*==============================================================*/
/* Table: k_contract                                            */
/*==============================================================*/
create table k_contract
(
   id                   integer unsigned not null auto_increment,
   order_info_id        varchar(32) not null,
   delivery_days_after_prepay integer comment '收到预付款后发货时间',
   client_name          varchar(64) not null comment '终端用户店名',
   install_location     varchar(64) not null comment '安装地点',
   acceptance_criteria_code varchar(4) not null comment '验收标准
            1001	需方负责安装调试
            1002	供方负责安装调试',
   party_a_mail         varchar(64) comment '甲方客户mail',
   invoice_address      varchar(256) comment '发票地址',
   invoice_post_code    varchar(6) comment '邮政编码',
   invoice_receiver     varchar(64) comment '发票接收人',
   invoice_tel          varchar(64) comment '联系电话',
   broker               varchar(64) comment '委托代理人',
   company_tel          varchar(64) comment '公司电话',
   bank_name            varchar(64) comment '银行名称',
   account_number       varchar(64) comment '银行账号',
   party_a_address      varchar(64) comment '单位地址',
   comany_post_code     varchar(6) comment '单位邮政编码',
   creater              varchar(32) comment '合同制作人',
   create_time          datetime not null comment '合同制作时间',
   sender               varchar(32) comment '合同发送人',
   send_time            datetime comment '合同发送时间',
   signer               varchar(32) comment '合同签署人',
   sign_time            datetime comment '合同签署时间',
   contract_status      integer comment '合同状态：1 未发送 2 已发送',
   file_hashcode        varchar(256) comment '合同文档Hash值',
   sign_contractid      varchar(64) comment '电子签约中合同Id，存放上上签中的contractId',
   primary key (id)
);

alter table k_contract comment '合同';

/*==============================================================*/
/* Table: k_delievery_address                                   */
/*==============================================================*/
create table k_delievery_address
(
   id                   varchar(32) not null,
   province_code        varchar(6),
   city_code            varchar(6),
   district_code        varchar(6),
   address              varchar(128) not null,
   order_info_id        varchar(32) not null,
   primary key (id)
);

alter table k_delievery_address comment '送达地址';

/*==============================================================*/
/* Table: k_engining_cost                                       */
/*==============================================================*/
create table k_engining_cost
(
   id                   integer unsigned not null auto_increment,
   cost                 decimal(13,2) not null comment '成本',
   opt_time             datetime not null comment '操作时间',
   operator             varchar(64) not null comment '操作人',
   order_info_id        varchar(32) not null comment '订单详情ID',
   engining_term        varchar(18) not null comment '费用类型',
   primary key (id),
   key id_unique (id)
);

alter table k_engining_cost comment '工程成本';

/*==============================================================*/
/* Table: k_engining_term                                       */
/*==============================================================*/
create table k_engining_term
(
   code                 varchar(18) not null,
   name                 varchar(64) not null,
   primary key (code)
);

alter table k_engining_term comment '工程成本费用类型';

/*==============================================================*/
/* Table: k_item_b2c                                            */
/*==============================================================*/
create table k_item_b2c
(
   id                   integer not null auto_increment,
   cost                 decimal(13,2) not null comment 'B2C成本',
   opt_time             datetime not null comment '最后修改时间',
   operator             varchar(64) not null,
   item_detail_id       varchar(32) not null,
   primary key (id),
   key id_unique (id)
);

alter table k_item_b2c comment 'B2C人员填写的评估成本';

/*==============================================================*/
/* Table: k_item_detail                                         */
/*==============================================================*/
create table k_item_detail
(
   id                   varchar(32) not null,
   order_info_id        varchar(32),
   row_num              integer not null comment '行号',
   material_code        varchar(45) not null comment '物料代码',
   is_virtual           integer not null default 0 comment '工程虚拟物料
            0:由销售录入的行项目
            1: 非销售部门录入的行项目',
   purchased            integer not null comment '物料属性：1. 采购 0：生产',
   group_code           varchar(45) not null comment '物料类型代码
            sap_material_group',
   item_category        varchar(45) not null comment '行项目类别',
   item_requirement_plan varchar(45) not null comment '需求计划',
   unit_code            varchar(3) comment '计量单位代码',
   quantity             double(13,2) not null comment '数量',
   standard_price       decimal(13,2) comment '移动平均价，即成本价格',
   retail_price         decimal(13,2) comment '零售价格',
   actural_price        decimal(13,2) comment '产品实卖价',
   transcation_price    decimal(13,2) comment '产品转移价',
   actural_prica_of_optional decimal(13,2) comment '可选项实卖价',
   transcation_prica_of_optional decimal(13,2) comment '可选项转移价',
   b2c_price_estimated  char(10) comment 'B2C评估价',
   b2c_cost_of_estimation decimal(13,2) comment 'bc2预估成本',
   b2c_comments         varchar(64) comment 'B2C备注',
   produce_date         char(10) comment '生产开始时间',
   on_store_date        char(10) comment '入库时间',
   discount             double(3,3) comment '折扣',
   volume_cube          decimal(13,2) comment '体积',
   freight              decimal(13,2) comment '运费',
   delievery_date       date comment '发货日期',
   special_comments     varchar(64) comment '特殊备注',
   config_comments      char(10) comment '配置表备注(配置表页面)',
   mosaic_image         varchar(64) comment '拼接图备注',
   attached_image       varchar(256) comment '拼接图附件',
   request_brand        varchar(64),
   request_package      varchar(64),
   request_nameplate    varchar(64),
   request_circult      varchar(64),
   color_comments       varchar(64) comment '颜色备注',
   period               integer unsigned comment '生产/采购周期',
   config_transfer_price decimal(13,2),
   config_retail_price  decimal(13,2),
   is_configurable      integer not null comment '是否是可配置物料',
   clazz_code           varchar(45) comment '物料分类代码',
   province_code        varchar(45) comment '省',
   city_code            varchar(45) comment '市',
   district_code        varchar(45) comment '区',
   address              varchar(64) comment '运输目的地，格式为{省 code:名字,市code:名字，区code:名字,address:名字}',
   ship_date            date comment '要求发货时间',
   comments             varchar(64) comment '备注，位于配置页面',
   primary key (id)
);

alter table k_item_detail comment '订单行项目表，每行记录代表一个行项目';

/*==============================================================*/
/* Table: k_order                                               */
/*==============================================================*/
create table k_order
(
   id                   varchar(32) not null,
   order_type           varchar(4) not null comment '订单类型
            ZH0D	经销商订单
            ZH0M	备货订单
            ZH0T	大客户订单',
   sequence_number      varchar(18) not null comment '序列号',
   contracter_code      varchar(10) not null comment '签约单位/ 客户',
   office_code          varchar(4) comment '销售员所属区域',
   create_time          datetime not null comment '创建时间',
   sales_code           varchar(128) not null comment '创建人（销售经理）',
   primary key (id),
   key sequence_number_unique (sequence_number)
);

alter table k_order comment '订单';

/*==============================================================*/
/* Table: k_order_info                                          */
/*==============================================================*/
create table k_order_info
(
   id                   varchar(32) not null,
   order_id             varchar(32),
   version              varchar(45) not null comment '版本名称',
   version_num          integer comment '版本序号，每次累加1',
   status               varchar(4) not null comment '订单状态
            00   草稿
            01   客户经理提交，待B2C审批
            02   客户经理提交，待工程人员审批
            03   支持经理提交，提交到BPM
            04   BPM审批通过
            05   订单变更BPM审批通过
            06   不用
            07   不用
            08   不用
            09   已下推SAP
            10   Selling Tool驳回
            11   BPM驳回',
   is_active            integer comment '最新版本，新的版本生成后旧的版本变为0， 新的版本为1
            0 非最新，1 最新',
   customer_name        varchar(64) not null comment '店名 customer name',
   record_code          varchar(45) comment '项目报备编号',
   is_reformed          integer comment '是否是改造店',
   is_convenient_store  integer comment '是否是便利店',
   is_new               integer comment '是不是新店',
   sales_tel            varchar(45) comment '客户（销售）经理电话',
   terminal_type        varchar(10) comment '终端客户性质',
   contract_number      varchar(45) not null comment '合同号',
   contract_manager     varchar(45) not null comment '支持经理，合同管理员',
   body_discount        double(3,3) comment '柜体折扣',
   main_discount        double(3,3) comment '机组折扣',
   merge_discount       double(3,3) comment '合并折扣',
   standard_discount    double(3,3) comment '标准折扣',
   is_special           integer comment '是否特批折扣/非标折扣',
   is_b2c               integer comment '是否有B2C',
   install_code         varchar(4) comment '安装code',
   confirm_type_code    varchar(4) comment '接货方式名称code',
   contactor1_id        varchar(18) comment '第一联系人身份证',
   contactor1_tel       varchar(16) comment '第一联系人电话',
   contactor2_id        varchar(18) comment '第二联系人身份证',
   contactor2_tel       varchar(16) comment '第二联系人电话',
   contactor3_id        varchar(18) comment '第三联系人身份证',
   contactor3_tel       varchar(16) comment '第三联系人电话',
   transfer_type_code   varchar(45) comment '运输类型代码',
   freight              decimal(13,2) comment '运费合计',
   warrenty             integer comment '保修年限',
   currency             varchar(3) comment '外币code',
   exchange             double(10,5) comment '汇率',
   contract_value       decimal(13,2) comment '原合同金额',
   contract_rmb_value   decimal(13,2) comment '合同人民币金额',
   items_amount         decimal(13,2) comment '购销明细金额合计',
   sale_type            varchar(2) comment '销售类型',
   tax_rate             double comment '税率',
   incoterm             varchar(45) comment '国际贸易条件code',
   incoterm_contect     varchar(64) comment '国际贸易条件内容',
   office_code          varchar(45) comment '表单里的大区code',
   group_code           varchar(45) comment '中心code',
   is_term1             integer comment '柜体控制阀门件是否甲供',
   is_term2             integer comment '分体柜是否远程监控',
   is_term3             integer comment '立体柜是否在地下室',
   earliest_delivery_date date comment '要求发货时间,最早交付时间',
   earliest_product_date date comment '工厂最早交货时间,最早生产时间',
   install_fee          decimal(13,2) not null comment '工程安装费',
   material_fee         decimal(13,2) not null comment '工程材料费',
   electrical_fee       decimal(13,2) comment '工程电气费',
   refrigeratory_fee    decimal(13,2) comment '工程冷库费',
   maintenance_fee      decimal(13,2) comment '工程维保费',
   comments             varchar(64) comment '备注 Remark',
   gross_profit_margin  text comment '毛利率，每次修改保存时自动计算订单毛利率并保存到此字段',
   submit_time          datetime comment '最后一次提交时间，会有被驳回然后提交的时间',
   bpm_submit_time      datetime comment '提交bpm审批的时间，会有被驳回然后提交的时间',
   creater              char(10) comment '创建人',
   create_time          datetime not null comment '创建时间',
   updater              varchar(128) not null comment '最后操作人',
   update_time          datetime not null comment '最后操作时间',
   primary key (id),
   key id_unique (id)
);

alter table k_order_info comment '订单详情';

/*==============================================================*/
/* Table: k_speical_order_application                           */
/*==============================================================*/
create table k_speical_order_application
(
   id                   integer unsigned not null auto_increment,
   order_info_id        varchar(32),
   applyer              varchar(128) not null,
   approver             varchar(128),
   apply_time           datetime not null,
   approval_time        datetime,
   apply_status         integer not null comment '0: 新建
            1：同意
            2：驳回',
   receive_mail_time    varchar(64) not null,
   contract_time        varchar(64) not null,
   pay_advance_payment_time varchar(64) not null,
   remark               varchar(64),
   enclosure_path       varchar(64),
   enclosure_name       varchar(64),
   primary key (id)
);

alter table k_speical_order_application comment '特批发货';

/*==============================================================*/
/* Table: sap_characteristic                                    */
/*==============================================================*/
create table sap_characteristic
(
   code                 varchar(30) not null,
   name                 varchar(64) not null,
   is_optional          integer not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_characteristic comment 'SAP物料特征';

/*==============================================================*/
/* Table: sap_characteristic_value                              */
/*==============================================================*/
create table sap_characteristic_value
(
   id                   integer not null auto_increment,
   name                 varchar(64) not null,
   code                 varchar(30) not null,
   sap_characteristic_code varchar(30) not null,
   primary key (id),
   key id_unique (id)
);

alter table sap_characteristic_value comment 'SAP物料特征值';

/*==============================================================*/
/* Table: sap_clazz                                             */
/*==============================================================*/
create table sap_clazz
(
   code                 varchar(18) not null,
   name                 varchar(64) not null,
   is_reserved          integer not null comment '保留字段，非可配置类',
   primary key (code),
   key code_unique (code)
);

alter table sap_clazz comment '特征分组';

/*==============================================================*/
/* Table: sap_clazz_and_character                               */
/*==============================================================*/
create table sap_clazz_and_character
(
   sap_clazz_code       varchar(18) not null,
   sap_characteristic_code varchar(30) not null,
   primary key (sap_characteristic_code, sap_clazz_code)
);

/*==============================================================*/
/* Table: sap_color_characteristic                              */
/*==============================================================*/
create table sap_color_characteristic
(
   code                 varchar(45) not null,
   name                 varchar(64) not null,
   sap_materials_code   varchar(18) not null,
   primary key (code),
   key code_unique (code)
);

/*==============================================================*/
/* Table: sap_color_characteristic_value                        */
/*==============================================================*/
create table sap_color_characteristic_value
(
   id                   integer not null auto_increment,
   name                 varchar(64) not null,
   code                 varchar(45) not null,
   is_default           integer not null,
   sap_color_characteristic_code varchar(45) not null,
   primary key (id),
   key seq_code (code)
);

/*==============================================================*/
/* Table: sap_currency                                          */
/*==============================================================*/
create table sap_currency
(
   code                 varchar(3) not null,
   name                 varchar(64) not null,
   rate                 double(10,5) not null,
   is_reserved          integer not null default 0,
   primary key (code),
   key code_unique (code)
);

alter table sap_currency comment 'SAP货币
';

/*==============================================================*/
/* Table: sap_currency_sale_type                                */
/*==============================================================*/
create table sap_currency_sale_type
(
   sap_sales_type_code  varchar(2) not null,
   sap_currency_code    varchar(3) not null,
   primary key (sap_currency_code, sap_sales_type_code),
   key sap_sales_type_code_currency_code_unique (sap_sales_type_code, sap_currency_code)
);

alter table sap_currency_sale_type comment 'SAP货币销售类型对应关系';

/*==============================================================*/
/* Table: sap_customer                                          */
/*==============================================================*/
create table sap_customer
(
   code                 varchar(10) not null,
   change_date          date not null,
   name                 varchar(64) not null,
   address              varchar(64),
   sap_customer_class_code varchar(2) not null comment '客户类型',
   sap_industry_code_code varchar(10) not null comment '客户级别',
   primary key (code),
   key number_unique (code)
);

alter table sap_customer comment '客户';

/*==============================================================*/
/* Table: sap_customer_class                                    */
/*==============================================================*/
create table sap_customer_class
(
   code                 varchar(2) not null comment '01:直销
            02:经销商',
   name                 varchar(64) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_customer_class comment 'SAP客户类型
01	直销
02	经销商';

/*==============================================================*/
/* Table: sap_incoterms                                         */
/*==============================================================*/
create table sap_incoterms
(
   code                 varchar(3) not null,
   name                 varchar(64) not null,
   sap_sales_type_code  varchar(2) not null comment '销售类型',
   primary key (code),
   key code_unique (code)
);

alter table sap_incoterms comment '国际条款';

/*==============================================================*/
/* Table: sap_industry                                          */
/*==============================================================*/
create table sap_industry
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   is_reserved          integer not null default 0,
   primary key (code),
   key code_unique (code)
);

alter table sap_industry comment '大客户，行业，隶属关系';

/*==============================================================*/
/* Table: sap_industry_and_customer                             */
/*==============================================================*/
create table sap_industry_and_customer
(
   sap_customer_code    varchar(16) not null,
   sap_industry_code    varchar(4) not null,
   primary key (sap_industry_code, sap_customer_code),
   key sap_customer_code_and_industry_code_unique (sap_customer_code, sap_industry_code)
);

alter table sap_industry_and_customer comment '客户隶属关系';

/*==============================================================*/
/* Table: sap_industry_code                                     */
/*==============================================================*/
create table sap_industry_code
(
   code                 varchar(10) not null comment 'terminal shop level: incustry code',
   name                 varchar(64) not null,
   is_for_dealer        integer not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_industry_code comment '客户级别
';

/*==============================================================*/
/* Table: sap_installation_terms                                */
/*==============================================================*/
create table sap_installation_terms
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_installation_terms comment '安装方式
01	招标	01
02	自装自提	02';

/*==============================================================*/
/* Table: sap_last_updated                                      */
/*==============================================================*/
create table sap_last_updated
(
   code                 varchar(32) not null,
   name                 varchar(64) not null,
   update_date          datetime not null default '2000-01-01 00:00:00',
   primary key (code),
   key code_unique (code)
);

/*==============================================================*/
/* Table: sap_material_default_characteristic                   */
/*==============================================================*/
create table sap_material_default_characteristic
(
   sap_materials_code   varchar(18) not null,
   sap_characteristic_code varchar(30) not null,
   sap_characteristic_value_id integer not null,
   primary key (sap_characteristic_code, sap_materials_code)
);

alter table sap_material_default_characteristic comment 'SAP物料特征默认值';

/*==============================================================*/
/* Table: sap_material_groups                                   */
/*==============================================================*/
create table sap_material_groups
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   b_material_group_order_code varchar(4) not null comment '销售工具的分组',
   is_enable            integer not null default 1,
   primary key (code),
   key code_unique (code)
);

alter table sap_material_groups comment 'SAP物料分组';

/*==============================================================*/
/* Table: sap_materials                                         */
/*==============================================================*/
create table sap_materials
(
   code                 varchar(18) not null comment 'code',
   description          varchar(64) not null comment '描述',
   is_configurable      integer not null comment '可配置',
   is_purchased         integer not null comment '物料属性，采购',
   stand_price          decimal(13,2) not null comment '标准价格moving_average_price',
   opt_time             datetime not null comment '操作时间',
   material_size        double(13,3) not null comment '物料体积',
   sap_unit_of_measurement_code varchar(3) not null comment '计量单位',
   sap_material_groups_code varchar(4) not null comment '物料分组',
   sap_clazz_code       varchar(18) not null comment '特征分组',
   primary key (code),
   key code_unique (code)
);

alter table sap_materials comment 'SAP物料信息';

/*==============================================================*/
/* Table: sap_materials_price                                   */
/*==============================================================*/
create table sap_materials_price
(
   sap_price_type_code  varchar(4) not null,
   sap_materials_code   varchar(18) not null,
   sap_industry_code    varchar(4) not null,
   price                decimal(13,2) not null,
   primary key (sap_materials_code, sap_industry_code, sap_price_type_code)
);

alter table sap_materials_price comment 'SAP物料价格';

/*==============================================================*/
/* Table: sap_order_type                                        */
/*==============================================================*/
create table sap_order_type
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_order_type comment 'SAP订单类型
ZH0D	经销商订单
ZH0M	备货订单
ZH0T	大客户订单';

/*==============================================================*/
/* Table: sap_order_type_and_customer_class                     */
/*==============================================================*/
create table sap_order_type_and_customer_class
(
   sap_order_type_code  varchar(4) not null,
   sap_customer_class_code varchar(2) not null,
   primary key (sap_customer_class_code, sap_order_type_code)
);

alter table sap_order_type_and_customer_class comment 'SAP订单类型客户类型关系
ZH0D	01
ZH0M	01
ZH0M	02
                                                      -&#';

/*==============================================================*/
/* Table: sap_payment_term_bidding_plan                         */
/*==============================================================*/
create table sap_payment_term_bidding_plan
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   is_payment_term      integer not null,
   primary key (code),
   key code_unique (code)
);

/*==============================================================*/
/* Table: sap_price_type                                        */
/*==============================================================*/
create table sap_price_type
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_price_type comment 'SAP价格类型
ZH01	零售价
ZH02	年采价 
ZH03	客户折扣
                                   -&#&';

/*==============================================================*/
/* Table: sap_receive_terms                                     */
/*==============================================================*/
create table sap_receive_terms
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_receive_terms comment '01	盖章接货
02	授权人签字
03	其它';

/*==============================================================*/
/* Table: sap_sales_group                                       */
/*==============================================================*/
create table sap_sales_group
(
   code                 varchar(3) not null,
   name                 varchar(64) not null,
   sap_sales_office_code varchar(4) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_sales_group comment '中心，SAP中心';

/*==============================================================*/
/* Table: sap_sales_office                                      */
/*==============================================================*/
create table sap_sales_office
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   sap_sales_type_code  varchar(2) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_sales_office comment '大区，SAP区域
S001	东区(经销商）	10
S002	南区	10
S003	西';

/*==============================================================*/
/* Table: sap_sales_type                                        */
/*==============================================================*/
create table sap_sales_type
(
   code                 varchar(2) not null,
   name                 varchar(32) not null,
   primary key (code),
   key idsap_sales_type_unique (code)
);

alter table sap_sales_type comment 'SAP销售类型
10	内销
20	出口
30	冷库';

/*==============================================================*/
/* Table: sap_shipping_type                                     */
/*==============================================================*/
create table sap_shipping_type
(
   code                 varchar(2) not null,
   name                 varchar(32) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_shipping_type comment '01	自提
05	非自提';

/*==============================================================*/
/* Table: sap_unit_of_measurement                               */
/*==============================================================*/
create table sap_unit_of_measurement
(
   code                 varchar(3) not null,
   name                 varchar(64) not null,
   primary key (code),
   key code_unique (code)
);

alter table sap_unit_of_measurement comment 'SAP计量单位
BOT	瓶
CM	厘米
EA	个/卷/件
G	克
                                            -&';
