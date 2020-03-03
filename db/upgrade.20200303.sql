ALTER TABLE `k_item` 
ADD COLUMN `b2c_estimated_cost` DECIMAL(13,2) NULL COMMENT 'B2C评估成本' AFTER `b2c_estimated_price`;

drop table if exists b_province_map;

/*==============================================================*/
/* Table: b_province_map                                        */
/*==============================================================*/
create table b_province_map
(
   st_province          varchar(32) not null comment '销售工具省code',
   sap_province         varchar(32) not null comment 'sap省code',
   primary key (st_province)
);

alter table b_province_map comment '销售工具与SAP省code映射关系';
