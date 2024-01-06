INSERT INTO countries (name) VALUES
('Estados Unidos'),
('España'),
('Australia');

INSERT INTO states (name, country_id) VALUES
('California', 1),
('Madrid', 2),
('Queensland', 3);

INSERT INTO addresses (street, number, postal_code, city, state_id) VALUES
('123 Main St', 'Suite 45', '12345', 'Los Ángeles', 1),
('Calle Gran Vía', '78', '28013', 'Madrid', 2),
('Brisbane St', 'Apto 22', '4000', 'Brisbane', 3);

INSERT INTO genres (name) VALUES
('Masculino'),
('Femenino'),
('Otros');

INSERT INTO roles (name) VALUES
('Admin'),
('Usuario'),
('Cliente');

INSERT INTO users (first_name, last_name, dni, email, phone, genre_id, address_id, role_id) VALUES
('Juan', 'Gómez', '12345678A', 'juan@gmail.com', '123456789', 1, 1, 1),
('Ana', 'Martínez', '98765432B', 'ana@gmail.com', '987654321', 2, 2, 2),
('Carlos', 'López', '56789012C', 'carlos@gmail.com', '567890123', 3, 3, 3);

INSERT INTO user_credentials (user_id, password_hash) VALUES
(1, 'hashed_password_1'),
(2, 'hashed_password_2'),
(3, 'hashed_password_3');

INSERT INTO contact_details (first_name, last_name, phone, email, role) VALUES
('Juan', 'Pérez', '123456789', 'juan.perez@gmail.com', 'Gerente'),
('Ana', 'González', '987654321', 'ana.gonzalez@gmail.com', 'Supervisor'),
('Carlos', 'López', '567890123', 'carlos.lopez@gmail.com', 'Empleado');

INSERT INTO vat_conditions (name) VALUES
('Responsable Inscripto'),
('Responsable No Inscripto'),
('Exento');

INSERT INTO tax_information (cuit, vat_condition_id) VALUES
('12345678901', 1),
('23456789012', 2),
('34567890123', 3);

INSERT INTO categories (name) VALUES
('Electrónica'),
('Ropa'),
('Hogar');

INSERT INTO industries (name) VALUES
('Electrónica'),
('Mueblería'),
('Construcción');

INSERT INTO suppliers (code, business_name, industry_id, website, email, phone, address_id, tax_information_id, contact_detail_id) VALUES
('PROV001', 'ElectroSuministros S.A.', 1, 'www.electrosuministros.com', 'info@electrosuministros.com', '123456789', 1, 1, 1),
('PROV002', 'Muebles Modernos Ltda.', 2, 'www.mueblesmodernos.com', 'ventas@mueblesmodernos.com', '987654321', 2, 2, 2),
('PROV003', 'Herramientas y Construcción', 3, 'www.herramientasyconstruccion.com', 'info@herramientasyconstruccion.com', '567890123', 3, 3, 3);

INSERT INTO products (sku, name, description, price, category_id, supplier_id) VALUES
('SKU001', 'Laptop', 'Portátil de última generación', 1000.00, 1, 1),
('SKU002', 'Sofá', 'Sofá cómodo y elegante', 500.00, 2, 1),
('SKU003', 'Herramienta', 'Herramienta versátil para múltiples usos', 150.00, 3, 3);

INSERT INTO statuses (name) VALUES
('En Proceso'),
('En Camino'),
('Entregado');

INSERT INTO orders (issue_date, delivery_date, comments, total, status_id, supplier_id, user_id) VALUES
('2024-01-01', '2024-01-15', 'Comentario1', 200.00, 1, 1, 1),
('2024-01-02', '2024-01-20', 'Comentario2', 100.00, 2, 2, 2),
('2024-01-03', '2024-01-25', 'Comentario3', 150.00, 3, 3, 3);

INSERT INTO order_details (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 200.00),
(2, 2, 1, 50.00),
(3, 3, 2, 150.00);
