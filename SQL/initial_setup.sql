-- Run this on the mysql prompt
-- source C:/usr/workspace/stockapp/SQL/initial_setup.sql

delete from users;
delete from stock;
delete from stock_price;
delete from stock_transaction;

insert into users(email, password, first_name, last_name, balance) values ('guest@anonymous.com', 'password', 'First', 'User', 100000.00);

insert into stock(stock_code, stock_name) values ('GOOG', 'GOOGLE INC');
insert into stock(stock_code, stock_name) values ('MSFT', 'MICROSOFT');
insert into stock(stock_code, stock_name) values ('AAPL', 'APPLE');

insert into stock_price(stock_code, buy_price, sell_price) values ('GOOG', 463.97, 462.97);
insert into stock_price(stock_code, buy_price, sell_price) values ('MSFT', 24.78, 23.78);
insert into stock_price(stock_code, buy_price, sell_price) values ('AAPL', 171.00, 170.00);

insert into stock_transaction(email, stock_code, transaction_date, buy_price, sell_price, quantity) values ('akshayamahesh@hotmail.com', 'GOOG', '2009-01-01', 240.00, 250.00, 100);
insert into stock_transaction(email, stock_code, transaction_date, buy_price, sell_price, quantity) values ('akshayamahesh@hotmail.com', 'IBM', '2009-01-01', 220.00, 250.00, 100);
insert into stock_transaction(email, stock_code, transaction_date, buy_price, sell_price, quantity) values ('akshayamahesh@hotmail.com', 'MSFT', '2009-01-01', 210.00, 250.00, 100);
insert into stock_transaction(email, stock_code, transaction_date, buy_price, sell_price, quantity) values ('akshayamahesh@hotmail.com', 'DB', '2009-01-01', 120.00, 250.00, 100);
insert into stock_transaction(email, stock_code, transaction_date, buy_price, sell_price, quantity) values ('akshayamahesh@hotmail.com', 'FORD', '2009-01-01', 120.00, 250.00, 100);

commit;
