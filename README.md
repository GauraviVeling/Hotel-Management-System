# Hotel Management System

The Hotel Management System is a Java Swing-based DBMS project designed to manage core hotel operations such as room booking, employee management, customer check-in/check-out, and driver assignments. It uses MySQL as the backend and JDBC for database interaction.

## Features

- Secure login and dashboard navigation
- Add, view, and update employee records
- Add, view, and update room records
- Register customers and track check-in/check-out
- Search available rooms using filters
- Assign drivers for pickup services
- View manager and department information
- Centralized reception dashboard for streamlined operations

## Dataset Source (MySQL Schema)

The application uses a MySQL database named `hotel` with the following normalized tables:
- `employee`
- `room`
- `customer`
- `driver`
- `manager`
- `department`
These tables are managed using standard SQL queries via JDBC.

## DBMS Concepts Used

- Basic CRUD operations: SELECT, INSERT, UPDATE, DELETE
- Normalized relational schema with primary and foreign keys
- Real-time database access using JDBC
- Referential integrity between entities

## Tech Stack

- Java
- Java Swing (GUI)
- MySQL
- JDBC

## How to Run the Application

### 1. Clone the Repository
### 2. Set Up the MySQL Database
### 3. Create a database named hotelmanagementsystem
 Create the necessary tables:
 employee, room, customer, driver, manager, department
 Ensure the MySQL server is running
### 4. Update Database Credentials
In the conn.java file, update the credentials as follows:
 Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelmanagementsystem", "your_username", "your_password");
### 5. Compile the Java Files
 javac *.java
### 6. Run the Application
java HotelManagementSystem

## Output Example
 Once logged in, the user can navigate the dashboard to:
 Manage employee, room, and customer records
 Track customer check-ins and check-outs
 Assign drivers for transportation
 View manager and department details
 All data modifications are reflected in real time in the MySQL database.
