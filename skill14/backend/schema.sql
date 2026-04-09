-- Create the database
CREATE DATABASE IF NOT EXISTS authdb;
USE authdb;

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    full_name VARCHAR(255),
    email VARCHAR(255)
);

-- Show the tables and structure
SHOW TABLES;
DESCRIBE users;
