drop database if exists online_store;
create database online_store;
use online_store;

create table store(
	id int primary key auto_increment,
	name varchar(255) not null,
	street varchar (255),
	city varchar(255),
	postal_code char(6)
);

create table customer(
	id int primary key auto_increment,
	email varchar(255) not null,
	passwrd varchar(255) not null
);

create table product(
	id int primary key auto_increment,
	name varchar(255),
	store_id int,
	aisle int,
	bay int,
	stock_quantity int,
	foreign key (store_id) references store(id) on delete cascade
);

create table price(
	id int primary key auto_increment,
	rate decimal(15, 2),
	product_id int,
	foreign key (product_id) references product(id) on delete cascade
);

create table stock_order(
	id int primary key auto_increment,
	customer_id int,
	foreign key (customer_id) references customer(id) on delete cascade
);

create table order_line(
	id int primary key auto_increment,
	-- save latest price update
	stock_order_id int,
	product_id int,
	quantity int,
	foreign key (product_id) references product(id) on delete cascade,
	foreign key (stock_order_id) references stock_order(id) on delete cascade
);

create table payment(
	id int primary key auto_increment,
	stock_order_id int,
	foreign key (stock_order_id) references stock_order(id) on delete cascade
);

create table shipment(
	id int primary key auto_increment,
	stock_order_id int,
	foreign key (stock_order_id) references stock_order(id) on delete cascade
);

create table cart(
	id int primary key auto_increment,
	customer_id int,
	foreign key (customer_id) references customer(id) on delete cascade
);

create table cart_line(
	id int primary key auto_increment,
	cart_id int,
	product_id int,
	quantity int,
	foreign key (product_id) references product(id) on delete cascade,
	foreign key (cart_id) references cart(id) on delete cascade
);

create table stock(
	id int primary key auto_increment
);

create table packaging_order(
	id int primary key auto_increment
);

create table tracking_number(
	id int primary key auto_increment
);

create table employee(
	id int primary key auto_increment
);

create table stock_employee(
	id int primary key auto_increment
);

create table manager(
	id int primary key auto_increment
);

INSERT INTO store (id, name, street, city, postal_code) VALUES (1, 'Test Store', 'Test Street', 'Test City', '123123');

INSERT INTO product (id, name, store_id, aisle, bay, stock_quantity) VALUES (1, 'Test Product 1', 1, 22, 3, 10);
INSERT INTO product (id, name, store_id, aisle, bay, stock_quantity) VALUES (2, 'Test Product 2', 1, 10, 1, 20);
INSERT INTO product (id, name, store_id, aisle, bay, stock_quantity) VALUES (3, 'Test Product 3', 1, 25, 5, 5);

insert into price (id, rate, product_id) values (1, 2.25, 1);
insert into price (id, rate, product_id) values (2, 3.75, 2);
insert into price (id, rate, product_id) values (3, 5.00, 3);

INSERT INTO customer (id, email, passwrd) VALUES (1, 'test1@email.com', 'test12345');
INSERT INTO customer (id, email, passwrd) VALUES (2, 'test2@email.com', 'test12345');

insert into cart (id, customer_id) values (1, 1);
insert into cart (id, customer_id) values (2, 2);
insert into cart (id, customer_id) values (3, 1);

insert into cart_line (id, cart_id, product_id, quantity) values (1, 1, 1, 1);
insert into cart_line (id, cart_id, product_id, quantity) values (2, 1, 2, 2);