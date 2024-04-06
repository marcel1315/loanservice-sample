drop table if exists USER_INFO;
drop table if exists PRODUCT_INFO;
drop table if exists PRODUCT_LIST;

create table USER_INFO(
    id bigint not null auto_increment primary key,
    usr_key varchar(32) not null unique,
    usr_reg_num varchar(50) not null unique,
    usr_nm varchar(20) not null,
    usr_icm_amt bigint default 0 not null
);

create table PRODUCT_INFO(
    id bigint not null auto_increment primary key,
    org_cd varchar(5) not null,
    prod_cd varchar(3) not null,
    prod_nm varchar(100) not null,
    prod_min_intr double default 0.0 not null,
    prod_max_intr double default 0.0 not null
);

create table PRODUCT_LIST(
    id bigint not null auto_increment primary key,
    org_cd varchar(5) not null,
    prod_cd varchar(3) not null,
    unique (org_cd, prod_cd)
);