INSERT INTO addresses (street, number, postal_code, city, state_id) VALUES
-- Users
('Av. Corrientes', '456', '1001', 'CABA', 175),
('Calle Independencia', '234', '5000', 'Córdoba', 176),
('Av. Santa Fe', '789', '3000', 'Rosario', 191),
('Av. San Martín', '123', '5500', 'Mendoza', 183),
('Av. 9 de Julio', '567', '4000', 'San Miguel de Tucumán', 194),
('Av. Belgrano', '890', '4400', 'Salta', 187),
('Av. Argentina', '321', '8300', 'Neuquén', 185),
('Calle Rivadavia', '654', '9000', 'Comodoro Rivadavia', 174),
('Av. Urquiza', '987', '3100', 'Paraná', 178),
('Calle Alvear', '210', '4600', 'San Salvador de Jujuy', 180),
-- Suppliers
('Av. Pueyrredón', '123', '1425', 'CABA', 175),
('Calle San Jerónimo', '567', '5001', 'Córdoba', 176),
('Av. Dorrego', '890', '3001', 'Rosario', 191),
('Av. Juan B. Justo', '321', '5501', 'Mendoza', 183),
('Av. Alem', '987', '4001', 'San Miguel de Tucumán', 194),
('Av. Güemes', '654', '4401', 'Salta', 187),
('Calle Roca', '321', '8301', 'Neuquén', 185),
('Av. San Martín', '654', '9001', 'Comodoro Rivadavia', 174),
('Av. Entre Ríos', '210', '3101', 'Paraná', 178),
('Calle Lavalle', '789', '4601', 'San Salvador de Jujuy', 180);

INSERT INTO genres (name) VALUES
('Masculino'),
('Femenino'),
('Otros');

INSERT INTO roles (name) VALUES
('admin'),
('user');

INSERT INTO users (first_name, last_name, dni, email, phone, genre_id, address_id, role_id) VALUES
-- Admins
('Juan', 'Gómez', '123456789', 'juan.gomez@gmail.com', '123456789', 1, 1, 1),
('Ana', 'Martínez', '987654328', 'ana.martinez@gmail.com', '987654321', 2, 2, 1),
-- Users
('Carlos', 'López', '567890122', 'claudio.lopez@outlook.com', '567890123', 1, 3, 2),
('María', 'Rodríguez', '345678901', 'maria.rodriguez@gmail.com', '345678901', 2, 4, 2),
('Diego', 'Sánchez', '876543213', 'diego.sanchez@gmail.com', '876543210', 3, 5, 2),
('Laura', 'Fernández', '234567894', 'laura.fernandez@outlook.com', '234567890', 2, 6, 2),
('Pedro', 'García', '789012345', 'pedro.garcia@hotmail.com', '789012345', 1, 7, 2),
('Sofía', 'Pérez', '456789016', 'sofia.perez@gmail.com', '456789012', 2, 8, 2),
('Pablo', 'Hernández', '123890767', 'pablo.hernandez@yahoo.com', '123890765', 1, 9, 2),
('Marta', 'Díaz', '908712348', 'marta.diaz@gmail.com', '908712345', 2, 10, 2);

INSERT INTO user_credentials (user_id, password) VALUES
(1, 'password_1'),
(2, 'password_2'),
(3, 'password_3'),
(4, 'password_4'),
(5, 'password_5'),
(6, 'password_6'),
(7, 'password_7'),
(8, 'password_8'),
(9, 'password_9'),
(10, 'password_10');

INSERT INTO contact_details (first_name, last_name, phone, email, role) VALUES
('Luis', 'Fernández', '234567890', 'luis.fernandez@gmail.com', 'Gerente'),
('María', 'Martínez', '456789012', 'maria.martinez@gmail.com', 'Supervisor'),
('Carlos', 'Gómez', '567890123', 'carlos.gomez@hotmail.com', 'Empleado'),
('Laura', 'Rodríguez', '678901234', 'laura.rodriguez@yahoo.com', 'Empleado'),
('Diego', 'Sánchez', '789012345', 'diego.sanchez@gmail.com', 'Empleado'),
('Ana', 'Fernández', '890123456', 'ana.fernandez@outlook.com', 'Gerente'),
('Sofía', 'García', '901234567', 'sofia.garcia@gmail.com', 'Empleado'),
('Pablo', 'Pérez', '012345678', 'pablo.perez@gmail.com', 'Supervisor'),
('Marta', 'Hernández', '123456789', 'marta.hernandez@gmail.com', 'Empleado'),
('Lucas', 'Díaz', '234567890', 'lucas.diaz@gmail.com', 'Empleado');

INSERT INTO vat_conditions (name) VALUES
('IVA Responsable Inscripto'),
('IVA Responsable no Inscripto'),
('IVA no Responsable'),
('IVA Sujeto Exento'),
('Consumidor Final'),
('Responsable Monotributo'),
('Sujeto no Categorizado'),
('Proveedor del Exterior'),
('Cliente del Exterior'),
('IVA Liberado - Ley Nº 19.640'),
('IVA Responsable Inscripto - Agente de Percepción'),
('Pequeño Contribuyente Eventual'),
('Monotributista Social'),
('Pequeño Contribuyente Eventual Social');

INSERT INTO tax_information (cuit, vat_condition_id) VALUES
('45678901234', 1),
('56789012345', 2),
('67890123456', 1),
('78901234567', 1),
('89012345678', 2),
('90123456789', 2),
('01234567890', 6),
('12345678901', 6),
('23456789012', 1),
('34567890123', 2);

INSERT INTO categories (name) VALUES
('Electrónicos'),
('Muebles'),
('Herramientas'),
('Alimentos'),
('Ropa Deportiva'),
('Productos Químicos'),
('Energía Renovable'),
('Servicios de Salud'),
('Tecnología Educativa'),
('Otros');

INSERT INTO industries (name) VALUES
('Electrónica'),
('Muebles de Oficina'),
('Ingeniería Civil'),
('Automotriz'),
('Alimentos Orgánicos'),
('Ropa Deportiva'),
('Productos Químicos'),
('Energía Renovable'),
('Servicios de Salud'),
('Tecnología Educativa');

INSERT INTO suppliers (code, business_name, industry_id, website, email, phone, address_id, tax_information_id, contact_detail_id) VALUES
('PROV-1', 'ElectroTech Solutions', 1, 'http://www.electrotechsolutions.com', 'info@electrotechsolutions.com', '+1234567890', 1, 1, 1),
('PROV-2', 'ErgoFurn', 2, 'http://www.ergofurn.com', 'info@ergofurn.com', '+9876543210', 2, 2, 2),
('PROV-3', 'ConstructoBuild', 3, 'http://www.constructobuild.com', 'info@constructobuild.com', '+6543210987', 3, 3, 3),
('PROV-4', 'Organic Shop', 1, 'http://www.organicshop.com', 'info@organicshop.com', '+5490001111', 4, 4, 4),
('PROV-5', 'GreenHarvest Organics', 5, 'http://www.greenharvestorganics.com', 'info@greenharvestorganics.com', '+1112223333', 5, 5, 5),
('PROV-6', 'ActiveWear Pro', 6, 'http://www.activewearpro.com', 'info@activewearpro.com', '+4445556666', 6, 6, 6),
('PROV-7', 'ChemicalInno Solutions', 7, 'http://www.chemicalinnosolutions.com', 'info@chemicalinnosolutions.com', '+7778889999', 7, 7, 7),
('PROV-8', 'EcoPower Solutions', 8, 'http://www.ecopowersolutions.com', 'info@ecopowersolutions.com', '+3334445555', 8, 8, 8),
('PROV-9', 'WellCare Services', 9, 'http://www.wellcareservices.com', 'info@wellcareservices.com', '+5556667777', 9, 9, 9),
('PROV-10', 'EduTech Innovations', 10, 'http://www.edutechinnovations.com', 'info@edutechinnovations.com', '+9990001111', 10, 10, 10);

INSERT INTO products (sku, name, description, price, category_id, supplier_id) VALUES
('SKU-1', 'Smartphone X1', 'Último modelo de smartphone con características avanzadas.', 599.99, 1, 1),
('SKU-2', 'ErgoChair Deluxe', 'Silla ergonómica con ajustes personalizados para mayor comodidad.', 299.99, 2, 2),
('SKU-3', 'ConstructoBuild Toolbox', 'Juego de herramientas completo para proyectos de construcción.', 129.99, 3, 3),
('SKU-4', 'Organic Superfood Pack', 'Paquete de superalimentos orgánicos para una alimentación saludable.', 49.99, 4, 4),
('SKU-5', 'ProFit Athletic Shoes', 'Zapatillas deportivas de alta calidad para entrenamientos intensivos.', 79.99, 5, 5),
('SKU-6', 'ChemicalInno Lab Kit', 'Kit de laboratorio con productos químicos para experimentos científicos.', 89.99, 6, 6),
('SKU-7', 'EcoPower Solar Panels', 'Paneles solares eficientes para generación de energía renovable.', 299.99, 7, 7),
('SKU-8', 'WellCare Health Checkup', 'Paquete de servicios de chequeo de salud completo.', 149.99, 8, 8),
('SKU-9', 'EduTech Interactive Board', 'Pizarra interactiva para experiencias educativas innovadoras.', 499.99, 9, 9),
('SKU-10', 'Miscellaneous Gizmo', 'Gizmo variado para propósitos diversos.', 29.99, 10, 10),
('SKU-11', 'Smartwatch Z2', 'Reloj inteligente con funciones avanzadas de monitoreo.', 149.99, 1, 1),
('SKU-12', 'Office Desk Pro', 'Escritorio de oficina con espacio amplio y diseño moderno.', 249.99, 2, 2),
('SKU-13', 'ProTool Power Drill', 'Taladro eléctrico de alta potencia para proyectos de bricolaje.', 79.99, 3, 3),
('SKU-14', 'Organic Green Tea Set', 'Set de té verde orgánico con variedad de sabores.', 19.99, 4, 4),
('SKU-15', 'Running Pro Jogging Shoes', 'Zapatillas para correr profesionales con tecnología de amortiguación.', 129.99, 5, 5),
('SKU-16', 'ScienceLab Advanced Kit', 'Kit avanzado de laboratorio para experimentos científicos.', 159.99, 6, 6),
('SKU-17', 'SolarCharge Portable Charger', 'Cargador portátil con energía solar para dispositivos móviles.', 49.99, 7, 7),
('SKU-18', 'Wellness Checkup Package', 'Paquete integral de servicios de bienestar y chequeo de salud.', 299.99, 8, 8),
('SKU-19', 'EduTech Learning Tablet', 'Tableta educativa para el aprendizaje interactivo.', 199.99, 9, 9),
('SKU-20', 'Multi-Functional Gizmo X', 'Gizmo multifuncional con características avanzadas.', 39.99, 10, 10);

INSERT INTO statuses (name) VALUES
('En Proceso'),
('En Camino'),
('Entregado'),
('Cancelado');

INSERT INTO orders (issue_date, delivery_date, comments, total, status_id, supplier_id, user_id) VALUES
('2024-01-15', '2024-01-20', 'Dejar en recepción.', 1349.97, 1, 1, 1),
('2024-02-01', '2024-02-10', 'Llamar al llegar a la empresa.', 549.98, 1, 2, 2),
('2024-03-08', '2024-03-15', 'Entregar en la bodega.', 209.98, 2, 3, 3),
('2024-04-05', '2024-04-12', 'Entregar en el almacén central.', 69.98, 1, 4, 4),
('2024-05-12', '2024-05-20', 'Contactar con el encargado de logística.', 209.98, 2, 5, 5),
('2024-06-18', '2024-06-25', 'No hay comentarios adicionales.', 249.98, 1, 6, 6),
('2024-07-25', '2024-08-01', 'Entregar en la oficina principal.', 349.98, 3, 7, 7),
('2024-08-30', '2024-09-05', 'Dejar en el depósito designado.', 299.98, 3, 8, 8),
('2024-09-10', '2024-09-18', 'Entregar en el área de recepción.', 699.98, 4, 9, 9),
('2024-10-22', '2024-10-30', 'Dejar en el vestíbulo.', 69.98, 4, 10, 10);

INSERT INTO order_details (order_id, product_id, quantity, price) VALUES
(1, 1, 2, 599.99),
(1, 11, 1, 149.99),
(2, 2, 1, 299.99),
(2, 12, 1, 249.99),
(3, 3, 1, 129.99),
(3, 13, 1, 79.99),
(4, 4, 1, 49.99),
(4, 14, 1, 19.99),
(5, 5, 1, 79.99),
(5, 15, 1, 129.99),
(6, 6, 1, 89.99),
(6, 16, 1, 159.99),
(7, 7, 1, 299.99),
(7, 17, 1, 49.99),
(8, 8, 1, 149.99),
(8, 18, 1, 299.99),
(9, 9, 1, 499.99),
(9, 19, 1, 199.99),
(10, 10, 1, 29.99),
(10, 20, 1, 39.99);
