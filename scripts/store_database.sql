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
