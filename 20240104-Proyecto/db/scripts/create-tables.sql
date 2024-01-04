CREATE TABLE suppliers (
  id int PRIMARY KEY IDENTITY(1, 1),
  code varchar(255) UNIQUE,
  businessName varchar(255) UNIQUE,
  industry varchar(255),
  website varchar(255),
  email varchar(255),
  phone varchar(255),
  address_id int,
  tax_information_id int,
  contact_detail_id int,
  is_deleted bit
)

CREATE TABLE addresses (
  id int PRIMARY KEY IDENTITY(1, 1),
  street varchar(255),
  number varchar(255),
  postal_code varchar(255),
  city varchar(255),
  state_id int
)

CREATE TABLE states (
  id int PRIMARY KEY IDENTITY(1, 1),
  name varchar(255),
  country_id int
)

CREATE TABLE countries (
  id int PRIMARY KEY IDENTITY(1, 1),
  name varchar(255)
)

CREATE TABLE contact_details (
  id int PRIMARY KEY IDENTITY(1, 1),
  first_name varchar(255),
  last_name varchar(255),
  phone varchar(255),
  email varchar(255),
  role varchar(255)
)

CREATE TABLE tax_information (
  id int PRIMARY KEY IDENTITY(1, 1),
  cuit varchar(255) UNIQUE,
  vat_condition varchar(255)
)

CREATE TABLE products (
  id int PRIMARY KEY IDENTITY(1, 1),
  sku varchar(255) UNIQUE,
  name varchar(255),
  description varchar(255),
  price float,
  category_id int,
  supplier_id int,
  is_deleted bit
)

CREATE TABLE categories (
  id int PRIMARY KEY IDENTITY(1, 1),
  name varchar(255)
)

CREATE TABLE orders (
  id int PRIMARY KEY IDENTITY(1, 1),
  issue_date datetime  DEFAULT (GETDATE()),
  delivery_date date,
  comments varchar(255),
  total float,
  status_id int,
  supplier_id int,
  is_deleted bit
)

CREATE TABLE statuses (
  id int PRIMARY KEY IDENTITY(1, 1),
  name varchar(255)
)

CREATE TABLE order_details (
  id int PRIMARY KEY IDENTITY(1, 1),
  order_id int,
  product_id int,
  quantity int,
  subtotal float
)

CREATE TABLE users (
  id int PRIMARY KEY IDENTITY(1, 1),
  first_name varchar(255),
  last_name varchar(255),
  dni varchar(255) UNIQUE,
  email varchar(255) UNIQUE,
  phone varchar(255),
  genre_id int,
  address_id int,
  role_id int,
  is_deleted bit
)

CREATE TABLE genres (
  id int PRIMARY KEY IDENTITY(1, 1),
  name varchar(255)
)

CREATE TABLE roles (
  id int PRIMARY KEY IDENTITY(1, 1),
  name varchar(255)
)

CREATE TABLE user_credentials (
  id int PRIMARY KEY IDENTITY(1, 1),
  user_id int,
  password_hash varchar(255)
)

-- Suppliers
ALTER TABLE suppliers
ADD CONSTRAINT fk_suppliers_addresses
FOREIGN KEY (address_id) REFERENCES addresses (id);

ALTER TABLE suppliers
ADD CONSTRAINT fk_suppliers_tax_information
FOREIGN KEY (tax_information_id) REFERENCES tax_information (id);

ALTER TABLE suppliers
ADD CONSTRAINT fk_suppliers_contact_details
FOREIGN KEY (contact_detail_id) REFERENCES contact_details (id);

-- Addresses
ALTER TABLE addresses
ADD CONSTRAINT fk_addresses_states
FOREIGN KEY (state_id) REFERENCES states (id);

-- States
ALTER TABLE states
ADD CONSTRAINT fk_states_countries
FOREIGN KEY (country_id) REFERENCES countries (id);

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
