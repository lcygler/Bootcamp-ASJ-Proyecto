-- Create Tables
CREATE TABLE countries (
  id int IDENTITY(1, 1) PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE states (
  id int IDENTITY(1, 1) PRIMARY KEY,
  name varchar(255) NOT NULL,
  country_id int NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE addresses (
  id int IDENTITY(1, 1) PRIMARY KEY,
  street varchar(255) NOT NULL,
  number varchar(20) NOT NULL,
  postal_code varchar(20) NOT NULL,
  city varchar(255) NOT NULL,
  state_id int NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE industries (
  id int IDENTITY(1, 1) PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE vat_conditions (
  id int IDENTITY(1, 1) PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE tax_information (
  id int IDENTITY(1, 1) PRIMARY KEY,
  cuit varchar(255) UNIQUE NOT NULL,
  vat_condition_id int NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE contact_details (
  id int IDENTITY(1, 1) PRIMARY KEY,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  phone varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  role varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE suppliers (
  id int IDENTITY(1, 1) PRIMARY KEY,
  code varchar(255) UNIQUE NOT NULL,
  business_name varchar(255) NOT NULL,
  industry_id int NOT NULL,
  website varchar(255) NOT NULL,
  email varchar(255) NOT NULL,
  phone varchar(255) NOT NULL,
  logo varchar(255),
  address_id int NOT NULL,
  tax_information_id int NOT NULL,
  contact_detail_id int NOT NULL,
  is_deleted bit NOT NULL DEFAULT 0,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE categories (
  id int IDENTITY(1, 1) PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE products (
  id int IDENTITY(1, 1) PRIMARY KEY,
  sku varchar(255) UNIQUE NOT NULL,
  name varchar(255) NOT NULL,
  description varchar(255) NOT NULL,
  image varchar(255),
  price float NOT NULL,
  category_id int NOT NULL,
  supplier_id int NOT NULL,
  is_deleted bit NOT NULL DEFAULT 0,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE statuses (
  id int IDENTITY(1, 1) PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE orders (
  id int IDENTITY(1, 1) PRIMARY KEY,
  issue_date datetime DEFAULT (GETDATE()),
  delivery_date datetime NOT NULL,
  comments varchar(255) NOT NULL,
  total float NOT NULL,
  status_id int NOT NULL,
  supplier_id int NOT NULL,
  user_id int NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE order_details (
  id int IDENTITY(1, 1) PRIMARY KEY,
  order_id int NOT NULL,
  product_id int NOT NULL,
  quantity int NOT NULL,
  price float NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE genres (
  id int IDENTITY(1, 1) PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE roles (
  id int IDENTITY(1, 1) PRIMARY KEY,
  name varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE users (
  id int IDENTITY(1, 1) PRIMARY KEY,
  first_name varchar(255) NOT NULL,
  last_name varchar(255) NOT NULL,
  dni varchar(255) UNIQUE NOT NULL,
  email varchar(255) UNIQUE NOT NULL,
  phone varchar(255) NOT NULL,
  genre_id int NOT NULL,
  address_id int NOT NULL,
  role_id int NOT NULL,
  is_deleted bit NOT NULL DEFAULT 0,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

CREATE TABLE user_credentials (
  id int IDENTITY(1, 1) PRIMARY KEY,
  user_id int NOT NULL,
  password varchar(255) NOT NULL,
  created_at datetime NOT NULL DEFAULT GETDATE(),
  updated_at datetime NOT NULL DEFAULT GETDATE()
)

-- Create Relationships
-- Suppliers
ALTER TABLE suppliers
ADD CONSTRAINT fk_suppliers_industries
FOREIGN KEY (industry_id) REFERENCES industries (id);

ALTER TABLE suppliers
ADD CONSTRAINT fk_suppliers_addresses
FOREIGN KEY (address_id) REFERENCES addresses (id);

ALTER TABLE suppliers
ADD CONSTRAINT fk_suppliers_tax_information
FOREIGN KEY (tax_information_id) REFERENCES tax_information (id);

ALTER TABLE suppliers
ADD CONSTRAINT fk_suppliers_contact_details
FOREIGN KEY (contact_detail_id) REFERENCES contact_details (id);

-- Tax_Information
ALTER TABLE tax_information
ADD CONSTRAINT fk_tax_information_vat_conditions
FOREIGN KEY (vat_condition_id) REFERENCES vat_conditions (id);

-- Products
ALTER TABLE products
ADD CONSTRAINT fk_products_category
FOREIGN KEY (category_id) REFERENCES categories (id);

ALTER TABLE products
ADD CONSTRAINT fk_products_supplier
FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

-- Orders
ALTER TABLE orders
ADD CONSTRAINT fk_orders_status
FOREIGN KEY (status_id) REFERENCES statuses (id);

ALTER TABLE orders
ADD CONSTRAINT fk_orders_supplier
FOREIGN KEY (supplier_id) REFERENCES suppliers (id);

-- Order_Details
ALTER TABLE order_details
ADD CONSTRAINT fk_order_details_order
FOREIGN KEY (order_id) REFERENCES orders (id);

ALTER TABLE order_details
ADD CONSTRAINT fk_order_details_product
FOREIGN KEY (product_id) REFERENCES products (id);

-- Users
ALTER TABLE users
ADD CONSTRAINT fk_users_genres
FOREIGN KEY (genre_id) REFERENCES genres (id);

ALTER TABLE users
ADD CONSTRAINT fk_users_addresses
FOREIGN KEY (address_id) REFERENCES addresses (id);

ALTER TABLE users
ADD CONSTRAINT fk_users_roles
FOREIGN KEY (role_id) REFERENCES roles (id);

-- User_Credentials
ALTER TABLE user_credentials
ADD CONSTRAINT fk_user_credentials_users
FOREIGN KEY (user_id) REFERENCES users (id);

-- Addresses
ALTER TABLE addresses
ADD CONSTRAINT fk_addresses_states
FOREIGN KEY (state_id) REFERENCES states (id);

-- States
ALTER TABLE states
ADD CONSTRAINT fk_states_countries
FOREIGN KEY (country_id) REFERENCES countries (id);
