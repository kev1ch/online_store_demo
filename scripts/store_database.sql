drop database if exists online_store;
create database online_store;
use online_store;

create table store(
	id int primary key,
	name varchar(255) not null,
	street varchar (255),
	city varchar(255),
	postal_code char(6)
);

create table product(
	id int primary key,
	name varchar(255),
	store_id int,
	foreign key (store_id) references store(id) on delete cascade
);

INSERT INTO store (id, name, street, city, postal_code) VALUES (1, 'Test Store', 'Test Street', 'Test City', '123123');
INSERT INTO product (id, name, store_id) VALUES (1, 'Test Product 1', 1);
INSERT INTO product (id, name, store_id) VALUES (2, 'Test Product 2', 1);
INSERT INTO product (id, name, store_id) VALUES (3, 'Test Product 3', 1);