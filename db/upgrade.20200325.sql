drop table if exists sap_order;

/*==============================================================*/
/* Table: sap_order                                             */
/*==============================================================*/
create table sap_order
(
   contract_number      varchar(32) not null comment 'sap存量合同号'
);

alter table sap_order comment 'sap订单存量信息，合同号/订单号等';
