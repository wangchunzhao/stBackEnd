drop table if exists k_special_attachment;

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

drop table if exists k_item_attachment;

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
