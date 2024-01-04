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

CREATE TABLE state (
  id int PRIMARY KEY IDENTITY(1, 1),
  name varchar(255),
  country_id int
)

CREATE TABLE country (
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
  is_deleted bit
)

CREATE TABLE genres (
  id int PRIMARY KEY IDENTITY(1, 1),
  name varchar(255)
)

CREATE TABLE user_credentials (
  id int PRIMARY KEY IDENTITY(1, 1),
  user_id int,
  password_hash varchar(255)
)

ALTER TABLE addresses ADD FOREIGN KEY (id) REFERENCES suppliers (address_id)

ALTER TABLE contact_details ADD FOREIGN KEY (id) REFERENCES suppliers (contact_detail_id)

ALTER TABLE tax_information ADD FOREIGN KEY (id) REFERENCES suppliers (tax_information_id)

ALTER TABLE products ADD FOREIGN KEY (supplier_id) REFERENCES suppliers (id)

ALTER TABLE orders ADD FOREIGN KEY (supplier_id) REFERENCES suppliers (id)

ALTER TABLE order_details ADD FOREIGN KEY (product_id) REFERENCES products (id)

ALTER TABLE products ADD FOREIGN KEY (category_id) REFERENCES categories (id)

ALTER TABLE order_details ADD FOREIGN KEY (order_id) REFERENCES orders (id)

ALTER TABLE orders ADD FOREIGN KEY (status_id) REFERENCES statuses (id)

ALTER TABLE addresses ADD FOREIGN KEY (id) REFERENCES users (address_id)

ALTER TABLE users ADD FOREIGN KEY (genre_id) REFERENCES genres (id)

ALTER TABLE user_credentials ADD FOREIGN KEY (user_id) REFERENCES users (id)

ALTER TABLE addresses ADD FOREIGN KEY (state_id) REFERENCES state (id)

ALTER TABLE state ADD FOREIGN KEY (country_id) REFERENCES country (id)
