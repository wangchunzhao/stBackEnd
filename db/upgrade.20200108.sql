drop table if exists k_billing_plan;

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
