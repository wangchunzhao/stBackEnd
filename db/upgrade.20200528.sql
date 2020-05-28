ALTER TABLE `k_order_info` 
ADD COLUMN `tax_code` VARCHAR(10) NULL DEFAULT '1' COMMENT '税率code' AFTER `sale_type`;

drop table if exists sap_tax;

/*==============================================================*/
/* Table: sap_tax                                               */
/*==============================================================*/
create table sap_tax
(
   code                 varchar(10) not null,
   tax                  decimal(5,2) not null,
   remark               varchar(64) not null default '2000-01-01 00:00:00',
   primary key (code)
);

alter table sap_tax comment '税率';

INSERT INTO `qhc2`.`sap_tax` (`code`, `tax`, `remark`) VALUES ('0', '0', 'Foreign');
INSERT INTO `qhc2`.`sap_tax` (`code`, `tax`, `remark`) VALUES ('1', '0.13', 'Domestic');
INSERT INTO `qhc2`.`sap_tax` (`code`, `tax`, `remark`) VALUES ('Z', '0.09', 'Sales tax');
