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
