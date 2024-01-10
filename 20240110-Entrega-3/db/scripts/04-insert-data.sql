INSERT INTO addresses (street, number, postal_code, city, state_id) VALUES
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
('Juan', 'Gómez', '123456789', 'juan.gomez@gmail.com', '123456789', 1, 1, 1),
('Ana', 'Martínez', '987654328', 'ana.martinez@gmail.com', '987654321', 2, 2, 1),
('Carlos', 'López', '567890122', 'claudio.lopez@outlook.com', '567890123', 1, 3, 2),
('María', 'Rodríguez', '345678901', 'maria.rodriguez@gmail.com', '345678901', 2, 4, 2),
('Diego', 'Sánchez', '876543213', 'diego.sanchez@gmail.com', '876543210', 3, 5, 2),
('Laura', 'Fernández', '234567894', 'laura.fernandez@outlook.com', '234567890', 2, 6, 2),
('Pedro', 'García', '789012345', 'pedro.garcia@hotmail.com', '789012345', 1, 7, 2),
('Sofía', 'Pérez', '456789016', 'sofia.perez@gmail.com', '456789012', 2, 8, 2),
('Pablo', 'Hernández', '123890767', 'pablo.hernandez@yahoo.com', '123890765', 1, 9, 2),
('Marta', 'Díaz', '908712348', 'marta.diaz@gmail.com', '908712345', 2, 10, 2);

INSERT INTO user_credentials (user_id, password) VALUES
(1, '$2a$12$7GXFLr4yQ9y22ge5u8FpLOQAK2mcMvzlJFkZlCp5Vlmzvf4xrHwJy'),
(2, '$2a$12$J7RU5dTKJh/fcRrXOO06UeJb7ar2dFQ1E0KXyltx9msd7fC2aXZ3W'),
(3, '$2a$12$TQApwTDX3US3pf0h0D8qCuZ4uljbUuzot2Y2Z2PS8YlMpccduLw1e'),
(4, '$2a$12$Fg9jBDEjAZKX4F4OY9Vq0OKOFbHjTFotSpvTb5cNAlqZbUeBJS8FS'),
(5, '$2a$12$RPWeel8EJRQIXVig0e/AQOYqOD.Ln6S3rf6ZxjWlNL0pfZsZZr98q'),
(6, '$2a$12$l2rRW2iaIAYzP6OXcJWt5ePnmGUMTQbE9jVq9.ZllBxUde7cGH8fy'),
(7, '$2a$12$OcTZ5X7HrH6bwG.Sb5dkLe9o7W5zgBEbzzF3RwSNCOVktU02ioNjK'),
(8, '$2a$12$NZcm8h9sA3sFE0yMTfNe.uGvEetziYI9TQF94yA6DeODNSRBSwt0y'),
(9, '$2a$12$.XmJb.ZlUeZr1ptvl.wn2.Ka8DTwqGZLYYzvMjpv1LbemJDCg6D/i'),
(10, '$2a$12$KSa/4xwufTK1x9MF52vTbOqZnMjj2.QTRmouzGEojvC6YDwKo/fPK');

INSERT INTO industries (name) VALUES
('Tecnología'),
('Hogar y Muebles'),
('Herramientas'),
('Electrodomésticos'),
('Moda'),
('Deportes y Fitness'),
('Salud y Belleza'),
('Juguetes y Bebés'),
('Libros'),
('Otros');

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

INSERT INTO contact_details (first_name, last_name, phone, email, role) VALUES
('Luis', 'Fernández', '234567890', 'luis.fernandez@gmail.com', 'Gerente'),
('María', 'Martínez', '456789012', 'maria.martinez@gmail.com', 'Supervisor'),
('Carlos', 'Gómez', '567890123', 'carlos.gomez@hotmail.com', 'Empleado'),
('Laura', 'Rodríguez', '678901234', 'laura.rodriguez@yahoo.com', 'Empleado'),
('Diego', 'Sánchez', '789012345', 'diego.sanchez@gmail.com', 'Empleado'),
('Ana', 'Fernández', '890123456', 'ana.fernandez@outlook.com', 'Gerente'),
('Sofía', 'García', '901234567', 'sofia.garcia@gmail.com', 'Asistente'),
('Pablo', 'Pérez', '012345678', 'pablo.perez@gmail.com', 'Supervisor'),
('Marta', 'Hernández', '123456789', 'marta.hernandez@gmail.com', 'Empleado'),
('Lucas', 'Díaz', '234567890', 'lucas.diaz@gmail.com', 'Empleado');

INSERT INTO suppliers (code, business_name, industry_id, website, email, phone, address_id, tax_information_id, contact_detail_id) VALUES
('PROV-1', 'ElectroTech S.A.', 1, 'https://www.electrotech.com.ar', 'info@electrotech.com.ar', '+54 11 1234-5678', 1, 1, 1),
('PROV-2', 'Ideal Hogar', 2, 'https://www.idealhogar.com.ar', 'contacto@idealhogar.com.ar', '+54 351 9876-5432', 2, 2, 2),
('PROV-3', 'Wacker Herramientas', 3, 'https://www.wackerherramientas.com.ar', 'ventas@wacker.com.ar', '+54 341 5678-9012', 3, 3, 3),
('PROV-4', 'ElectroHogar S.R.L.', 4, 'https://www.electrohogar.com.ar', 'info@electrohogar.com.ar', '+54 261 2345-6789', 4, 4, 4),
('PROV-5', 'Distrito Moda', 5, 'https://www.distritomoda.com.ar', 'ventas@distritomoda.com.ar', '+54 381 8765-4321', 5, 5, 5),
('PROV-6', 'Punto Deportivo', 6, 'https://www.puntodeportivo.com.ar', 'info@puntodeportivo.com.ar', '+54 387 5432-1098', 6, 6, 6),
('PROV-7', 'Tienda Beauty', 7, 'https://www.tiendabeauty.com.ar', 'consultas@tiendabeauty.com.ar', '+54 299 1234-5678', 7, 7, 7),
('PROV-8', 'El Mundo del Juguete', 8, 'https://www.elmundodeljuguete.com.ar', 'ventas@elmundodeljuguete.com.ar', '+54 280 9876-5432', 8, 8, 8),
('PROV-9', 'Planeta Libros', 9, 'https://www.planetalibros.com.ar', 'info@planetalibros.com.ar', '+54 343 5678-9012', 9, 9, 9),
('PROV-10', 'Café La Bastilla', 10, 'https://www.tiendacafelabastilla.com.ar', 'ventas@labastilla.com.ar', '+54 11 2345-6789', 10, 10, 10);

INSERT INTO categories (name) VALUES
('Tecnología'),
('Hogar y Muebles'),
('Herramientas'),
('Electrodomésticos'),
('Moda'),
('Deportes y Fitness'),
('Salud y Belleza'),
('Juguetes y Bebés'),
('Libros'),
('Otros');

INSERT INTO products (sku, name, description, price, category_id, supplier_id) VALUES
('SKU-1', 'Teléfono Inteligente', 'Teléfono inteligente de última generación', 159999.99, 1, 1),
('SKU-2', 'Auriculares Inalámbricos', 'Auriculares con cancelación de ruido', 18999.99, 1, 1),
('SKU-3', 'Sofá Moderno', 'Sofá elegante para tu hogar', 255999.99, 2, 2),
('SKU-4', 'Mesa de Centro', 'Mesa de centro de diseño contemporáneo', 112999.99, 2, 2),
('SKU-5', 'Taladro Eléctrico', 'Taladro potente para proyectos de bricolaje', 78999.99, 3, 3),
('SKU-6', 'Juego de Destornilladores', 'Set completo de destornilladores profesionales', 20999.99, 3, 3),
('SKU-7', 'Lavadora de Ropa', 'Lavadora eficiente con múltiples funciones', 588999.99, 4, 4),
('SKU-8', 'Secadora de Ropa', 'Secadora de ropa con tecnología de secado rápido', 808999.99, 4, 4),
('SKU-9', 'Vestido de Noche Elegante', 'Vestido largo para ocasiones especiales', 59900.00, 5, 5),
('SKU-10', 'Zapatos de Tacón Alto', 'Zapatos elegantes para complementar tu look', 108499.99, 5, 5),
('SKU-11', 'Pelota de Fútbol', 'Pelota oficial para partidos de fútbol', 19999.99, 6, 6),
('SKU-12', 'Cinta de Correr Plegable', 'Cinta de correr para entrenamiento en casa', 314600.00, 6, 6),
('SKU-13', 'Kit de Maquillaje Profesional', 'Colección completa de maquillaje de alta calidad', 36100.00, 7, 7),
('SKU-14', 'Cepillo Alisador de Pelo', 'Cepillo que alisa el pelo de forma rápida y fácil', 34998.99, 7, 7),
('SKU-15', 'Juguetes Educativos para Bebés', 'Set de juguetes didácticos para bebés', 2999.99, 8, 8),
('SKU-16', 'Cuna Portátil', 'Cuna plegable y portátil para viajes', 227999.99, 8, 8),
('SKU-17', 'Colección de Novelas Best Sellers', 'Paquete con las mejores novelas del año', 29999.99, 9, 9),
('SKU-18', 'Libro de Cocina Gourmet', 'Libro con recetas gourmet y técnicas culinarias', 21870.00, 9, 9),
('SKU-19', 'Máquina de Café Automática', 'Máquina de café con múltiples opciones de preparación', 196549.99, 10, 10),
('SKU-20', 'Robot Aspirador Inteligente', 'Aspiradora robot con navegación inteligente', 314999.99, 10, 10);

INSERT INTO statuses (name) VALUES
('En Proceso'),
('En Camino'),
('Entregado'),
('Cancelado');

INSERT INTO orders (issue_date, delivery_date, comments, total, status_id, supplier_id, user_id) VALUES
('2023-06-01', '2023-06-10', 'Contactar con el encargado de logística.', 517999.95, 1, 1, 1),
('2023-06-15', '2023-06-25', 'Entregar en el almacén central.', 707999.95, 1, 2, 2),
('2023-07-05', '2023-07-15', 'Coordinar entrega con el departamento de compras.', 178999.97, 1, 3, 3),
('2023-08-01', '2023-08-11', 'Enviar a la sucursal de Mendoza.', 3384999.95, 2, 4, 4),
('2023-09-10', '2023-09-20', 'Prioridad en la entrega por necesidades urgentes.', 407999.99, 2, 5, 5),
('2023-10-01', '2023-10-11', 'Entregar antes de la fecha acordada.', 983799.98, 2, 6, 6),
('2023-11-05', '2023-11-15', 'Seguir protocolos de seguridad al entregar en planta.', 179398.99, 3, 7, 7),
('2023-12-01', '2023-12-11', 'Entrega rápida para eventos promocionales.', 464999.95, 3, 8, 8),
('2023-12-25', '2024-01-04', 'Coordinar entrega con el área de eventos.', 73739.99, 4, 9, 9),
('2024-01-01', '2024-01-10', 'Entregar productos antes del lanzamiento.', 1219649.95, 4, 10, 10);

INSERT INTO order_details (order_id, product_id, quantity, price) VALUES
(1, 1, 3, 159999.99),
(1, 2, 2, 18999.99),
(2, 3, 1, 255999.99),
(2, 4, 4, 112999.99),
(3, 5, 2, 78999.99),
(3, 6, 1, 20999.99),
(4, 7, 3, 588999.99),
(4, 8, 2, 808999.99),
(5, 9, 5, 59900.00),
(5, 10, 1, 108499.99),
(6, 11, 2, 19999.99),
(6, 12, 3, 314600.00),
(7, 13, 4, 36100.00),
(7, 14, 1, 34998.99),
(8, 15, 3, 2999.99),
(8, 16, 2, 227999.99),
(9, 17, 1, 29999.99),
(9, 18, 2, 21870.00),
(10, 19, 3, 196549.99),
(10, 20, 2, 314999.99);
