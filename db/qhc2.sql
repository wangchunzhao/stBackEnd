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

drop table if exists b_province;

drop table if exists b_province_map;

drop table if exists b_role;

drop table if exists b_role_operation;

drop table if exists b_settings;

drop table if exists b_user;

drop table if exists b_user_role;

drop table if exists k_attachment;

drop table if exists k_billing_plan;

drop table if exists k_bpm_dicision;

drop table if exists k_characteristics;

drop table if exists k_contract;

drop table if exists k_delivery_address;

drop table if exists k_engining_term;

drop table if exists k_item;

drop table if exists k_item_attachment;

drop table if exists k_item_color;

drop table if exists k_order;

drop table if exists k_order_info;

drop table if exists k_special_attachment;

drop table if exists k_special_order_application;

drop table if exists sap_characteristic;

drop table if exists sap_characteristic_value;

drop table if exists sap_clazz;

drop table if exists sap_clazz_and_character;

drop table if exists sap_color_class;

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

drop table if exists sap_material_product_class;

drop table if exists sap_materials;

drop table if exists sap_materials_price;

drop table if exists sap_materials_price_temp;

drop table if exists sap_order;

drop table if exists sap_order_type;

drop table if exists sap_order_type_and_customer_class;

drop table if exists sap_painting_class;

drop table if exists sap_payment_term_bidding_plan;

drop table if exists sap_price_type;

drop table if exists sap_product_class;

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
   price                decimal(13,2) not null comment '供应商<20m3单价',
   price1               decimal(13,2) comment '供应商<20m3送货费',
   price2               decimal(13,2) comment '供应商>20<50m3单价',
   price3               decimal(13,2) comment '供应商>20<50m3送货费',
   price4               decimal(13,2) comment '供应商>=50m3单价',
   price5               decimal(13,2) comment '供应商>50m3送货费',
   price6               decimal(13,2) comment '客户<=20m3单价',
   price7               decimal(13,2) comment '客户<=20m3送货费',
   price8               decimal(13,2) comment '客户>20<50m3单价',
   price9               decimal(13,2) comment '客户>20<50m3送货费',
   price10              decimal(13,2) comment '客户>=50m3单价',
   price11              decimal(13,2) comment '客户>=50m3送货费',
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
   key id_UNIQUE (code)
);

alter table b_material_group_order comment '物料分组，销售工具的物料分组';

/*==============================================================*/
/* Table: b_notify_infor                                        */
/*==============================================================*/
create table b_notify_infor
(
   id                   integer unsigned not null,
   hasSend              integer unsigned zerofill not null,
   msg_to               varchar(64) not null,
   msg_from             varchar(64) not null,
   message              varchar(64) not null,
   primary key (id),
   key id_UNIQUE (id)
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
/* Table: b_province_map                                        */
/*==============================================================*/
create table b_province_map
(
   st_province          varchar(32) not null comment '销售工具省code',
   sap_province         varchar(32) not null comment 'sap省code',
   name                 varchar(64) not null,
   primary key (st_province)
);

alter table b_province_map comment '销售工具与SAP省code映射关系';

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
   office_code          varchar(32) comment '销售办公室',
   tel                  varchar(16),
   primary key (id),
   key user_mail_UNIQUE (user_mail)
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
/* Table: k_attachment                                          */
/*==============================================================*/
create table k_attachment
(
   id                   integer unsigned not null auto_increment,
   order_info_id        integer not null,
   file_name            varchar(64) not null,
   file_url             varchar(128),
   primary key (id),
   key id_UNIQUE (id)
);

alter table k_attachment comment '附件文件名称';

/*==============================================================*/
/* Table: k_billing_plan                                        */
/*==============================================================*/
create table k_billing_plan
(
   id                   integer unsigned not null auto_increment,
   order_info_id        integer not null comment '订单详情ID',
   code                 varchar(10) not null comment '回款条款code',
   pay_date             date not null comment '回款起始日期',
   percentage           decimal(5,3) comment '回款比例',
   amount               decimal(13,2) comment '预算回款原币金额',
   rmb_amount           decimal(13,2) comment '预算回款金额',
   reason               varchar(64) comment '备注',
   primary key (id),
   key id_UNIQUE (id)
);

alter table k_billing_plan comment '付款条件或billing plan';

/*==============================================================*/
/* Table: k_bpm_dicision                                        */
/*==============================================================*/
create table k_bpm_dicision
(
   id                   integer unsigned not null auto_increment,
   order_info_id        integer not null,
   is_passed            integer not null,
   body_discount        double(4,2) not null comment '申请柜体折扣',
   approved_body_discount double(4,2) comment '批准柜体折扣',
   main_diccount        double(4,2) not null comment '申请机组折扣',
   approved_main_discount double(4,2) comment '批准机组折扣',
   primary key (id)
);

alter table k_bpm_dicision comment 'BPM审批结果';

/*==============================================================*/
/* Table: k_characteristics                                     */
/*==============================================================*/
create table k_characteristics
(
   id                   integer unsigned not null auto_increment,
   item_id              integer not null,
   key_code             varchar(45) not null comment '选定的特征代码',
   value_code           varchar(45) comment '选定的特征值的代码',
   is_configurable      integer comment '可配置，无用',
   primary key (id),
   key character_unique (key_code)
);

alter table k_characteristics comment '物料选定的特征';

/*==============================================================*/
/* Table: k_contract                                            */
/*==============================================================*/
create table k_contract
(
   id                   integer unsigned not null auto_increment,
   order_info_id        integer not null,
   delivery_days        integer comment '收到预付款后发货时间',
   client_name          varchar(64) not null comment '终端用户店名',
   install_location     varchar(64) not null comment '安装地点',
   acceptance_criteria_code varchar(4) not null comment '验收标准
            1001	需方负责安装调试
            1002	供方负责安装调试',
   customer_email       varchar(64) comment '签约单位邮箱',
   invoice_address      varchar(256) comment '发票地址',
   invoice_post_code    varchar(6) comment '邮政编码',
   invoice_receiver     varchar(64) comment '发票接收人',
   invoice_tel          varchar(64) comment '联系电话',
   broker               varchar(64) comment '委托代理人',
   company_tel          varchar(64) comment '公司电话',
   bank_name            varchar(64) comment '银行名称',
   account_number       varchar(64) comment '银行账号',
   company_address      varchar(64) comment '单位地址',
   company_post_code    varchar(6) comment '单位邮政编码',
   status               varchar(10) comment '合同状态
            01 已制作
            02 已发送
            03 已发送
            04 已上传
            05 客户已签署
            06 已签署',
   creater              varchar(32) comment '合同制作人',
   create_time          datetime not null comment '合同制作时间',
   sender               varchar(32) comment '合同发送人',
   send_time            datetime comment '合同发送时间',
   signer               varchar(32) comment '合同签署人',
   sign_time            datetime comment '合同签署时间',
   file_hashcode        varchar(256) comment '合同文档Hash值',
   sign_contractid      varchar(64) comment '电子签约中合同Id，存放上上签中的contractId',
   primary key (id)
);

alter table k_contract comment '合同';

/*==============================================================*/
/* Table: k_delivery_address                                    */
/*==============================================================*/
create table k_delivery_address
(
   id                   integer not null auto_increment,
   order_info_id        integer not null comment '订单详情id',
   seq                  integer comment '序号，每个订单详情从1开始，每增加一条取本订单的最大值加一',
   province_code        varchar(32) comment '省',
   city_code            varchar(32) comment '市',
   district_code        varchar(32) comment '区',
   address              varchar(128) comment '地址',
   primary key (id)
);

alter table k_delivery_address comment '送达地址';

/*==============================================================*/
/* Table: k_engining_term                                       */
/*==============================================================*/
create table k_engining_term
(
   code                 varchar(18) not null,
   name                 varchar(64) not null,
   primary key (code)
);

alter table k_engining_term comment '工程成本费用类型
''BG1GDA00000-X'', ''安装费''
''BG1GDB0000';

/*==============================================================*/
/* Table: k_item                                                */
/*==============================================================*/
create table k_item
(
   id                   integer not null auto_increment,
   order_info_id        integer not null,
   row_num              integer not null comment '行号',
   material_code        varchar(45) not null comment '物料代码',
   quantity             double(13,2) not null comment '数量',
   item_category        varchar(45) not null comment '行项目类别',
   item_requirement_plan varchar(45) not null comment '需求计划',
   b2c_estimated_price  decimal(13,2) comment 'B2C评估价',
   b2c_estimated_cost   decimal(13,2) comment 'B2C评估成本单价',
   delivery_address_seq integer comment '发货地址序号',
   delivery_address_id  integer comment '发货地址ID，这个字段可以不用',
   standard_price       decimal(13,2) comment '移动平均价，即成本价格',
   retail_price         decimal(13,2) comment '零售价格',
   actual_price         decimal(13,2) comment '产品实卖价',
   transaction_price    decimal(13,2) comment '产品转移价',
   optional_standard_price decimal(13,2) comment '可选项标准价差',
   optional_retail_price decimal(13,2) comment '可选项零售价差',
   optional_actual_price decimal(13,2) comment '可选项实卖价差',
   optional_transaction_price decimal(13,2) comment '可选项转移价差',
   discount             double(5,2) comment '折扣',
   produce_date         date comment '生产开始时间',
   on_store_date        date comment '入库时间',
   delivery_date        date comment '最早交货时间',
   shipp_date           date comment '要求发货时间',
   period               integer unsigned comment '生产/采购周期',
   b2c_comments         varchar(64) comment 'B2C备注',
   special_comments     varchar(64) comment '特殊备注',
   volume_cube          decimal(13,2) comment '体积',
   freight              decimal(13,2) comment '运费，未使用，订单中保存合计运费',
   is_virtual           integer not null default 0 comment '工程虚拟物料
            0:由销售录入的行项目
            1: 非销售部门录入的行项目',
   color_comments       varchar(64) comment '颜色备注',
   color_options        varchar(512) comment '最终颜色可选项数据格式：喷粉部位:颜色选项,   P01:1,P06:1,P07:1',
   mosaic_image         varchar(512) comment '拼接图备注',
   attached_image       varchar(512) comment '拼接图方式',
   config_comments      varchar(512) comment '配置表备注(配置表页面)，待定',
   request_brand        varchar(64) comment '，待定',
   request_package      varchar(64) comment '，待定',
   request_nameplate    varchar(64) comment '，待定',
   request_circult      varchar(64) comment '，待定',
   config_transfer_price decimal(13,2) comment '，待定',
   config_retail_price  decimal(13,2) comment '，待定',
   is_configurable      integer not null comment '是否是可配置物料，1/0，来自物料信息',
   clazz_code           varchar(45) comment '物料分类代码，sap_clazz',
   comments             varchar(64) comment '备注，位于配置页面',
   item_status          varchar(10) comment '行项目状态，00 草稿，10 下推SAP，Z2 取消
            Z7 订单关闭',
   primary key (id)
);

alter table k_item comment '订单行项目表，每行记录代表一个行项目';

/*==============================================================*/
/* Table: k_item_attachment                                     */
/*==============================================================*/
create table k_item_attachment
(
   id                   integer unsigned not null auto_increment,
   order_info_id        integer not null,
   item_id              integer not null,
   file_name            varchar(64) not null,
   file_url             varchar(128),
   primary key (id),
   key id_UNIQUE (id)
);

alter table k_item_attachment comment '行项目调研表附件';

/*==============================================================*/
/* Table: k_item_color                                          */
/*==============================================================*/
create table k_item_color
(
   id                   integer unsigned not null auto_increment,
   item_id              integer not null comment '行项目ID',
   product_class        varchar(10) not null comment '产品颜色系列号',
   painting_class       varchar(10) not null comment '喷粉分组',
   color_code           varchar(10) comment '颜色编码',
   primary key (id)
);

alter table k_item_color comment '物料颜色选项结果';

/*==============================================================*/
/* Table: k_order                                               */
/*==============================================================*/
create table k_order
(
   id                   integer not null auto_increment,
   order_type           varchar(4) not null comment '订单类型
            ZH0D	经销商订单
            ZH0M	备货订单
            ZH0T	大客户订单',
   sequence_number      varchar(32) not null comment '序列号',
   customer_code        varchar(10) not null comment '签约单位/ 客户',
   customer_clazz       varchar(10) comment '性质分类，经销商/直签',
   sales_code           varchar(32) not null comment '客户经理，如果是创建人就不需要',
   st_order_type        varchar(10) comment '销售工具订单类型
            1 经销商标准折扣下单
            2 经销商非标准折扣下单
            3 直签客户投标报价
            4 直签客户下定单
            5 备货 ',
   quote_status         varchar(10) comment '报价单状态
            00 报价单
            01 已中标
            02 已下单',
   primary key (id),
   key sequence_number_UNIQUE (sequence_number)
);

alter table k_order comment '订单';

/*==============================================================*/
/* Table: k_order_info                                          */
/*==============================================================*/
create table k_order_info
(
   id                   integer not null auto_increment,
   order_id             integer not null,
   version              varchar(45) not null comment '版本名称',
   version_num          integer comment '版本序号，每次累加1',
   status               varchar(4) not null comment '订单状态
            00   草稿
            01   客户经理提交，待B2C审批
            02   客户经理提交，待工程人员审批
            03   工程人员提交，待支持经理审批
            04   支持经理提交到BPM，待BPM审批
            05   BPM审批通过
            06   订单变更BPM审批通过
            07   不用
            08   不用
            09   已下推SAP
            10   Selling Tool驳回
            11   BPM驳回',
   is_active            integer comment '最新版本，新的版本生成后旧的版本变为0， 新的版本为1
            0 非最新，1 最新',
   terminal_type        varchar(10) comment '终端客户性质
            01 连锁百强
            02 国际大连锁
            03 散户',
   shop_name            varchar(64) not null comment '店名 shop name',
   record_code          varchar(45) comment '项目报备编号',
   sales_tel            varchar(45) comment '客户（销售）经理电话',
   is_convenient_store  integer comment '是否是便利店',
   is_reformed          integer comment '是否是改造店',
   is_new               integer comment '是不是新店',
   contract_number      varchar(45) comment '合同号，结算号',
   sale_type            varchar(2) comment '销售类型, sap_sales_type
            10	内销
            20	出口
            30	冷库',
   tax_rate             double comment '税率',
   incoterm             varchar(45) comment '国际贸易条件code',
   incoterm_contect     varchar(64) comment '国际贸易条件内容',
   contract_value       decimal(13,2) comment '原合同金额',
   contract_rmb_value   decimal(13,2) comment '合同人民币金额',
   currency             varchar(3) comment '外币code',
   currency_exchange    double(10,5) comment '汇率',
   items_amount         decimal(13,2) comment '购销明细金额合计',
   contract_manager     varchar(45) comment '支持经理，合同管理员',
   office_code          varchar(45) comment '表单里的大区, sap_sales_office',
   group_code           varchar(45) comment '中心, sap_sales_group',
   warranty             integer comment '保修年限',
   install_type         varchar(10) comment '安装, sap_installation_terms
            10	招标
            20	自装自提',
   receive_type         varchar(10) comment '收货方式, sap_receive_terms
            01	盖章接货
            02	授权人签字
            03	其它',
   transfer_type        varchar(45) comment '运输类型代码, sap_shipping_type
            01	非自提
            02	自提',
   freight              decimal(13,2) comment '运费合计',
   contactor1_id        varchar(64) comment '第一联系人身份证',
   contactor1_tel       varchar(16) comment '第一联系人电话',
   contactor2_id        varchar(64) comment '第二联系人身份证',
   contactor2_tel       varchar(16) comment '第二联系人电话',
   contactor3_id        varchar(64) comment '第三联系人身份证',
   contactor3_tel       varchar(16) comment '第三联系人电话',
   body_discount        double(4,2) comment '柜体折扣',
   approved_body_discount double(4,2) comment '批准的柜体折扣',
   main_discount        double(4,2) comment '机组折扣',
   approved_main_discount double(4,2) comment '批准的机组折扣',
   discount             double(4,2) comment '合并折扣',
   is_longterm          integer default 0 comment '是否为长期折扣，1 长期折扣， 0 非长期折扣',
   is_special           integer default 0 comment '是否特批折扣/非标折扣，1 非标折扣， 0 标准折扣',
   is_special_order     integer default 0 comment '是否特批下单，1 特批下单， 0 正常下单',
   payment_type         varchar(512) comment '结算方式，经销商',
   is_term1             integer comment '柜体控制阀门件是否甲供',
   is_term2             integer comment '分体柜是否远程监控',
   is_term3             integer comment '立体柜是否在地下室',
   install_fee          decimal(13,2) comment '工程安装费',
   material_fee         decimal(13,2) comment '工程材料费',
   electrical_fee       decimal(13,2) comment '工程电气费',
   refrigeratory_fee    decimal(13,2) comment '工程冷库费',
   maintenance_fee      decimal(13,2) comment '工程维保费',
   additional_freight   decimal(13,2) comment '追加运费',
   earliest_delivery_date date comment '要求发货时间,最早交付时间',
   earliest_product_date date comment '工厂最早交货时间,最早生产时间',
   is_b2c               integer comment '是否有B2C备注',
   is_bulk_cargo        integer comment '是否全部为散件',
   unpredictable        varchar(128) comment '不可预估项',
   is_urgent_delivery   integer comment '是否特批发货/紧急发货',
   gross_profit_margin  text comment '毛利率，每次修改保存时自动计算订单毛利率并保存到此字段',
   comments             varchar(64) comment '备注 Remark',
   submit_time          datetime comment '最后一次提交时间，会有被驳回然后提交的时间',
   bpm_submit_time      datetime comment '提交bpm审批的时间，会有被驳回然后提交的时间',
   creater              varchar(32) comment '创建人',
   create_time          datetime not null comment '创建时间',
   updater              varchar(32) not null comment '最后操作人',
   update_time          datetime not null comment '最后操作时间',
   primary key (id),
   key id_UNIQUE (id)
);

alter table k_order_info comment '订单详情';

/*==============================================================*/
/* Table: k_special_attachment                                  */
/*==============================================================*/
create table k_special_attachment
(
   id                   integer unsigned not null auto_increment,
   order_info_id        integer not null,
   special_id           integer not null,
   file_name            varchar(64) not null,
   file_url             varchar(128),
   primary key (id),
   key id_UNIQUE (id)
);

alter table k_special_attachment comment '特批发货/紧急发货附件';

/*==============================================================*/
/* Table: k_special_order_application                           */
/*==============================================================*/
create table k_special_order_application
(
   id                   integer unsigned not null auto_increment,
   order_info_id        integer not null,
   applyer              varchar(128) not null comment '申请人',
   approver             varchar(128) comment '审批人',
   apply_time           datetime not null comment '申请时间',
   approval_time        datetime comment '审批时间',
   apply_status         integer not null comment '0: 新建
            1：提交bpm
            2：同意
            3：驳回',
   receive_mail_time    varchar(64) not null comment '收到邮件时间',
   contract_time        varchar(64) not null,
   pay_advance_payment_time varchar(64) not null,
   remark               varchar(64),
   enclosure_path       varchar(64),
   enclosure_name       varchar(64),
   primary key (id)
);

alter table k_special_order_application comment '特批发货';

/*==============================================================*/
/* Table: sap_characteristic                                    */
/*==============================================================*/
create table sap_characteristic
(
   code                 varchar(30) not null,
   name                 varchar(64) not null,
   is_optional          TINYINT(1) not null comment '可选、必选',
   primary key (code),
   key code_UNIQUE (code)
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
   key id_UNIQUE (id)
);

alter table sap_characteristic_value comment 'SAP物料特征值';

/*==============================================================*/
/* Table: sap_clazz                                             */
/*==============================================================*/
create table sap_clazz
(
   code                 varchar(18) not null,
   name                 varchar(64) not null,
   is_reserved          TINYINT(1) not null comment '保留字段，非可配置类',
   primary key (code),
   key code_UNIQUE (code)
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

/*==============================================================*/
/* Table: sap_currency                                          */
/*==============================================================*/
create table sap_currency
(
   code                 varchar(3) not null,
   name                 varchar(64) not null,
   rate                 double(10,5) not null,
   is_reserved          TINYINT(1) not null default 0,
   primary key (code),
   key code_UNIQUE (code)
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
   key sap_sales_type_code_currency_code_UNIQUE (sap_sales_type_code, sap_currency_code)
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
   key number_UNIQUE (code)
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
   key code_UNIQUE (code)
);

alter table sap_customer_class comment 'SAP客户类型
10	直销
20	经销商';

/*==============================================================*/
/* Table: sap_incoterms                                         */
/*==============================================================*/
create table sap_incoterms
(
   code                 varchar(3) not null,
   name                 varchar(64) not null,
   sap_sales_type_code  varchar(2) not null comment '销售类型',
   primary key (code),
   key code_UNIQUE (code)
);

alter table sap_incoterms comment '国际条款';

/*==============================================================*/
/* Table: sap_industry                                          */
/*==============================================================*/
create table sap_industry
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   is_reserved          TINYINT(1) not null default 0,
   primary key (code),
   key code_UNIQUE (code)
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
   key sap_customer_code_and_industry_code_UNIQUE (sap_customer_code, sap_industry_code)
);

alter table sap_industry_and_customer comment '客户隶属关系';

/*==============================================================*/
/* Table: sap_industry_code                                     */
/*==============================================================*/
create table sap_industry_code
(
   code                 varchar(10) not null comment 'terminal shop level: incustry code',
   name                 varchar(64) not null,
   is_fordealer         TINYINT(1) not null comment '适用经销商',
   primary key (code),
   key code_UNIQUE (code)
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
   key code_UNIQUE (code)
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
   key code_UNIQUE (code)
);

/*==============================================================*/
/* Table: sap_material_default_characteristic                   */
/*==============================================================*/
create table sap_material_default_characteristic
(
   sap_materials_code   varchar(18) not null,
   sap_characteristic_code varchar(30) not null,
   sap_characteristic_value_id VARCHAR(10),
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
   isenable             TINYINT(1) not null default 1,
   primary key (code),
   key code_UNIQUE (code)
);

alter table sap_material_groups comment 'SAP物料分组';

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

/*==============================================================*/
/* Table: sap_materials                                         */
/*==============================================================*/
create table sap_materials
(
   code                 varchar(18) not null comment 'code',
   description          varchar(64) not null comment '描述，规格型号',
   is_configurable      TINYINT(1) not null comment '是否可配置，1是可配置，0是不可配置',
   is_purchased         TINYINT(1) not null comment '物料属性，1是自制生产 0是外部采购',
   stand_price          decimal(13,2) not null comment '标准价格moving_average_price',
   opt_time             datetime not null comment '操作时间',
   material_size        double(13,3) not null comment '物料体积',
   material_type        varchar(10) comment '物料类型',
   sap_unit_of_measurement_code varchar(3) not null comment '计量单位',
   sap_material_groups_code varchar(4) not null comment '物料分组',
   sap_clazz_code       varchar(18) not null comment '特征分组',
   primary key (code),
   key code_UNIQUE (code)
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
/* Table: sap_materials_price_temp                              */
/*==============================================================*/
create table sap_materials_price_temp
(
   sap_price_type_code  varchar(4) not null,
   sap_materials_code   varchar(18) not null,
   sap_industry_code    varchar(4) not null,
   price                decimal(13,2) not null,
   primary key (sap_materials_code, sap_industry_code, sap_price_type_code)
);

alter table sap_materials_price_temp comment 'SAP物料价格临时表';

/*==============================================================*/
/* Table: sap_order                                             */
/*==============================================================*/
create table sap_order
(
   contract_number      varchar(32) not null comment 'sap存量合同号'
);

alter table sap_order comment 'sap订单存量信息，合同号/订单号等';

/*==============================================================*/
/* Table: sap_order_type                                        */
/*==============================================================*/
create table sap_order_type
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   primary key (code),
   key code_UNIQUE (code)
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
/* Table: sap_painting_class                                    */
/*==============================================================*/
create table sap_painting_class
(
   painting_class       varchar(10) not null comment '喷粉部位编码',
   painting_parts       varchar(64) not null comment '喷粉部位描述',
   primary key (painting_class)
);

alter table sap_painting_class comment '产品喷粉部位';

/*==============================================================*/
/* Table: sap_payment_term_bidding_plan                         */
/*==============================================================*/
create table sap_payment_term_bidding_plan
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   is_payment_term      TINYINT(1) not null,
   primary key (code),
   key code_UNIQUE (code)
);

/*==============================================================*/
/* Table: sap_price_type                                        */
/*==============================================================*/
create table sap_price_type
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   primary key (code),
   key code_UNIQUE (code)
);

alter table sap_price_type comment 'SAP价格类型
ZH01	零售价
ZH02	年采价 
ZH03	客户折扣
                                   -&#&';

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

/*==============================================================*/
/* Table: sap_receive_terms                                     */
/*==============================================================*/
create table sap_receive_terms
(
   code                 varchar(4) not null,
   name                 varchar(64) not null,
   primary key (code),
   key code_UNIQUE (code)
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
   key code_UNIQUE (code)
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
   key code_UNIQUE (code)
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
   key idsap_sales_type_UNIQUE (code)
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
   key code_UNIQUE (code)
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
   key code_UNIQUE (code)
);

alter table sap_unit_of_measurement comment 'SAP计量单位
BOT	瓶
CM	厘米
EA	个/卷/件
G	克
                                            -&';
