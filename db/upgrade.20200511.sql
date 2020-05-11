drop table if exists b_operate_log;

/*==============================================================*/
/* Table: b_operate_log                                         */
/*==============================================================*/
create table b_operate_log
(
   id                   integer not null auto_increment comment 'id',
   operator             varchar(32) comment '操作人',
   operate_time         datetime comment '操作时间',
   operate_type         varchar(32) comment '操作类型',
   object_name          varchar(32) comment '操作对象',
   object_key           varchar(32) comment '操作对象关键信息',
   remark               varchar(512) comment '操作描述',
   primary key (id)
);

alter table b_operate_log comment '操作日志';

ALTER TABLE `qhc2`.`k_bpm_dicision` 
ADD COLUMN `approved_time` DATETIME NULL COMMENT '审批时间' AFTER `approved_main_discount`,
ADD COLUMN `remark` VARCHAR(512) NULL AFTER `approved_time`,
CHANGE COLUMN `body_discount` `body_discount` DOUBLE(4,2) NULL COMMENT '申请柜体折扣' ,
CHANGE COLUMN `main_diccount` `main_diccount` DOUBLE(4,2) NULL COMMENT '申请机组折扣' ;