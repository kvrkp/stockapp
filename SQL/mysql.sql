-- Run this on the mysql prompt
-- source C:/usr/workspace/stockapp/SQL/mysql.sql

drop table users;
drop table stock;
drop table stock_price;
drop table stock_transaction;

 create table users (
  email varchar(40) primary key not null,
  password varchar(8) not null,
  first_name varchar(30),
  last_name varchar(30),
  balance decimal(12,2)
  );
  
  create table stock(
   stock_code varchar(7) primary key not null,
   stock_name varchar(50)
  );
  
  create table stock_price (
   stock_code varchar(7),
   buy_price decimal(12,2),
   sell_price decimal(12,2),
   date_from date,
   date_to date 
);

create table stock_transaction (
  id integer auto_increment primary key,
  email varchar(40),
  stock_code varchar(7),
  transaction_date date,
  buy_price decimal(12,2),
  sell_price decimal(12,2),
  quantity decimal(6)
);