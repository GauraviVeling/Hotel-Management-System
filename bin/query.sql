create database hotelmanagementsystem;
show databases;

use hotelmanagementsystem;
Show tables;

CREATE TABLE login (
    username VARCHAR(25),
    password VARCHAR(25)
);
INSERT INTO login VALUES('admin', '12345');
select * from login;

create table employee(name varchar(25),age varchar(10),gender varchar(15),job varchar(30),salary varchar(15),phone varchar(15),email varchar(40),aadhar varchar(20));
select * from employee;

CREATE TABLE room (
    room_number VARCHAR(10) PRIMARY KEY,
    availability VARCHAR(20),
    status VARCHAR(20),
    price VARCHAR(10),
    bed_type VARCHAR(20)
);
select * from room;

CREATE TABLE driver (
    name VARCHAR(25),
    age VARCHAR(10),
    gender VARCHAR(10),
    car_company VARCHAR(30),
    car_brand VARCHAR(30),
    availability VARCHAR(10),
    location VARCHAR(30)
);

SELECT * FROM driver;

CREATE TABLE customer (
    id_type VARCHAR(30),
    id_number VARCHAR(30),
    name VARCHAR(50),
    gender VARCHAR(10),
    country VARCHAR(50),
    room_number VARCHAR(10),
    checkin_status VARCHAR(20),
    deposit VARCHAR(20)
);
select * from customer;

CREATE TABLE department (
    department VARCHAR(50),
    budget VARCHAR(20)
);

INSERT INTO department (department, budget) VALUES ('Housekeeping', '50000'), ('Catering', '75000'), ('Maintenance', '60000'), ('Reception', '40000'), ('Security', '55000'), ('Room Service', '45000'), ('Management', '90000'), ('Accounting', '70000'), ('Human Resources', '65000'), ('IT Support', '58000');



