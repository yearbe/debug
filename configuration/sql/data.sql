# mysql
create database data;
create user 'data'@'%' IDENTIFIED BY '123456';
grant all on data.* to 'data'@'%';
flush privileges;

use data;
create table user (
    id bigint not null auto_increment primary key,
    username varchar(32) not null unique,
    password varchar(64) not null,
    nickname varchar(16) default ''
);