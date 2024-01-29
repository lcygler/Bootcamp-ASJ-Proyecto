-- Create Database
CREATE DATABASE IF NOT EXISTS project;
USE project;

-- Create Tables
-- Addresses
CREATE TABLE countries (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE states (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  country_id int NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE addresses (
  id int AUTO_INCREMENT PRIMARY KEY,
  street varchar(255) NOT NULL,
  number varchar(20) NOT NULL,
  postal_code varchar(20) NOT NULL,
  city varchar(255) NOT NULL,
  state_id int NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- Images
CREATE TABLE images (
  id int AUTO_INCREMENT PRIMARY KEY,
  url varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- Users
CREATE TABLE genres (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE roles (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE users (
  id int AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  dni varchar(255) UNIQUE NOT NULL,
  email varchar(255) UNIQUE NOT NULL,
  phone varchar(255) NOT NULL,
  genre_id int NOT NULL,
  address_id int NOT NULL,
  role_id int NOT NULL,
  is_deleted bit NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE user_credentials (
  id int AUTO_INCREMENT PRIMARY KEY,
  user_id int NOT NULL,
  password varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- Suppliers
CREATE TABLE industries (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE vat_conditions (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE tax_information (
  id int AUTO_INCREMENT PRIMARY KEY,
  cuit varchar(255) UNIQUE NOT NULL,
  vat_condition_id int NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE contact_details (
  id int AUTO_INCREMENT PRIMARY KEY,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  phone varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  role varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE suppliers (
  id int AUTO_INCREMENT PRIMARY KEY,
  code varchar(255) UNIQUE NOT NULL,
  business_name varchar(255) NOT NULL,
  industry_id int NOT NULL,
  website varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  phone varchar(255) NOT NULL,
  image_id int,
  address_id int NOT NULL,
  tax_information_id int NOT NULL,
  contact_detail_id int NOT NULL,
  is_deleted bit NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- Products
CREATE TABLE categories (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE products (
  id int AUTO_INCREMENT PRIMARY KEY,
  sku varchar(255) UNIQUE NOT NULL,
  name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  price float NOT NULL,
  image_id int,
  category_id int NOT NULL,
  supplier_id int NOT NULL,
  is_deleted bit NOT NULL DEFAULT 0,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- Orders
CREATE TABLE statuses (
  id int AUTO_INCREMENT PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE orders (
  id int AUTO_INCREMENT PRIMARY KEY,
  number varchar(255) UNIQUE NOT NULL,
  issue_date datetime DEFAULT (CURRENT_TIMESTAMP),
  delivery_date datetime NOT NULL,
  comments varchar(255) NOT NULL,
  total float NOT NULL,
  status_id int NOT NULL,
  supplier_id int NOT NULL,
  user_id int NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

CREATE TABLE order_details (
  id int AUTO_INCREMENT PRIMARY KEY,
  order_id int NOT NULL,
  product_id int NOT NULL,
  quantity int NOT NULL,
  price float NOT NULL,
  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
)

-- Create Relationships
-- Addresses
ALTER TABLE 
  addresses 
ADD 
  CONSTRAINT fk_addresses_states FOREIGN KEY (state_id) REFERENCES states (id);

-- States
ALTER TABLE 
  states 
ADD 
  CONSTRAINT fk_states_countries FOREIGN KEY (country_id) REFERENCES countries (id);

-- Users
ALTER TABLE 
  users 
ADD 
  CONSTRAINT fk_users_genres FOREIGN KEY (genre_id) REFERENCES genres (id);

ALTER TABLE 
  users 
ADD 
  CONSTRAINT fk_users_roles FOREIGN KEY (role_id) REFERENCES roles (id);

ALTER TABLE 
  users 
ADD 
  CONSTRAINT fk_users_addresses FOREIGN KEY (address_id) REFERENCES addresses (id);

-- User_Credentials
ALTER TABLE 
  user_credentials 
ADD 
  CONSTRAINT fk_user_credentials_users FOREIGN KEY (user_id) REFERENCES users (id);

-- Suppliers
ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_industries FOREIGN KEY (industry_id) REFERENCES industries (id);

ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_images FOREIGN KEY (image_id) REFERENCES images (id);

ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_addresses FOREIGN KEY (address_id) REFERENCES addresses (id);

ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_tax_information FOREIGN KEY (tax_information_id) REFERENCES tax_information (id);

ALTER TABLE 
  suppliers 
ADD 
  CONSTRAINT fk_suppliers_contact_details FOREIGN KEY (contact_detail_id) REFERENCES contact_details (id);

-- Tax_Information
ALTER TABLE 
  tax_information 
ADD 
  CONSTRAINT fk_tax_information_vat_conditions FOREIGN KEY (vat_condition_id) REFERENCES vat_conditions (id);

-- Products
ALTER TABLE 
  products 
ADD 
  CONSTRAINT fk_products_images FOREIGN KEY (image_id) REFERENCES images (id);

ALTER TABLE 
  products 
ADD 
  CONSTRAINT fk_products_categories FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE 
  products 
ADD 
  CONSTRAINT fk_products_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

-- Orders
ALTER TABLE 
  orders 
ADD 
  CONSTRAINT fk_orders_statuses FOREIGN KEY (status_id) REFERENCES statuses (id);

ALTER TABLE 
  orders 
ADD 
  CONSTRAINT fk_orders_suppliers FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

-- Order_Details
ALTER TABLE 
  order_details 
ADD 
  CONSTRAINT fk_order_details_orders FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE 
  order_details 
ADD 
  CONSTRAINT fk_order_details_products FOREIGN KEY (product_id) REFERENCES products (id);
