-- Addresses
INSERT INTO addresses (
  street, number, postal_code, city, 
  state_id, created_at, updated_at
) 
VALUES 
  (
    'Av. Corrientes', '456', '1001', 'CABA', 
    175, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Calle Independencia', '234', '5000', 
    'Córdoba', 176, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Santa Fe', '789', '3000', 'Rosario', 
    191, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. San Martín', '123', '5500', 
    'Mendoza', 183, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. 9 de Julio', '567', '4000', 'San Miguel de Tucumán', 
    194, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Belgrano', '890', '4400', 'Salta', 
    187, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Argentina', '321', '8300', 'Neuquén', 
    185, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Calle Rivadavia', '654', '9000', 
    'Comodoro Rivadavia', 174, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Urquiza', '987', '3100', 'Paraná', 
    178, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Calle Alvear', '210', '4600', 'San Salvador de Jujuy', 
    180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Pueyrredón', '123', '1425', 
    'CABA', 175, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Calle San Jerónimo', '567', '5001', 
    'Córdoba', 176, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Dorrego', '890', '3001', 'Rosario', 
    191, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Juan B. Justo', '321', '5501', 
    'Mendoza', 183, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Alem', '987', '4001', 'San Miguel de Tucumán', 
    194, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Güemes', '654', '4401', 'Salta', 
    187, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Calle Roca', '321', '8301', 'Neuquén', 
    185, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. San Martín', '654', '9001', 
    'Comodoro Rivadavia', 174, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Av. Entre Ríos', '210', '3101', 
    'Paraná', 178, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Calle Lavalle', '789', '4601', 'San Salvador de Jujuy', 
    180, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );


-- Images
INSERT INTO images (url, created_at, updated_at) 
VALUES 
  -- Suppliers
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704897863/asj/products/electrotech_i7lyp2.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898550/asj/products/ideal-hogar_n3gugt.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898551/asj/products/wacker-herramientas_uzlt80.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898549/asj/products/electrohogar_xbhyv5.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898552/asj/products/distrito-moda_huvyr4.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898550/asj/products/punto-deportivo_yj17k1.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898550/asj/products/tienda-beauty_t0ah6s.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898549/asj/products/el-mundo-del-juguete_nrxnci.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898550/asj/products/planeta-libros_tvtdao.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704898551/asj/products/cafe-la-bastilla_zquw3r.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  -- Products
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899571/asj/products/telefono_wnd3bk.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899558/asj/products/auriculares_shgb6v.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899558/asj/products/auriculares_shgb6v.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899578/asj/products/mesa_rvkier.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899579/asj/products/taladro_ok4hx5.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899570/asj/products/destornilladores_xnr76o.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899560/asj/products/lavarropas_fuj7vb.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899560/asj/products/lavarropas_fuj7vb.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899577/asj/products/vestido_serapp.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899557/asj/products/zapatos_hfdklt.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899575/asj/products/pelota_i4zfvi.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899575/asj/products/pelota_i4zfvi.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899572/asj/products/kit-maquillaje_gex3tq.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899560/asj/products/cepillo_mxivzl.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899570/asj/products/juguetes_naxrrw.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899565/asj/products/cuna_dqaqdb.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899576/asj/products/libros_ojpnep.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899582/asj/products/libro-cocina_cv9aag.png',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899580/asj/products/maquina-cafe_mjz8gp.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'https://res.cloudinary.com/dadsbmqwh/image/upload/v1704899557/asj/products/robot-aspirador_b9fw63.jpg',
    CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );

-- Users
INSERT INTO genres (name, created_at, updated_at) 
VALUES 
  ('Masculino', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Femenino', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Otros', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO roles (name, created_at, updated_at) 
VALUES 
  ('admin', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('user', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO users (
  first_name, last_name, dni, email, 
  phone, genre_id, address_id, role_id,
  created_at, updated_at
) 
VALUES 
  (
    'Juan', 'Gómez', '123456789', 'juan.gomez@gmail.com', 
    '123456789', 1, 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Ana', 'Martínez', '987654328', 'ana.martinez@gmail.com', 
    '987654321', 2, 2, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Carlos', 'López', '567890122', 'claudio.lopez@outlook.com', 
    '567890123', 1, 3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'María', 'Rodríguez', '345678901', 
    'maria.rodriguez@gmail.com', '345678901', 
    2, 4, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Diego', 'Sánchez', '876543213', 
    'diego.sanchez@gmail.com', '876543210', 
    3, 5, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Laura', 'Fernández', '234567894', 
    'laura.fernandez@outlook.com', 
    '234567890', 2, 6, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Pedro', 'García', '789012345', 'pedro.garcia@hotmail.com', 
    '789012345', 1, 7, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Sofía', 'Pérez', '456789016', 'sofia.perez@gmail.com', 
    '456789012', 2, 8, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Pablo', 'Hernández', '123890767', 
    'pablo.hernandez@yahoo.com', '123890765', 
    1, 9, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Marta', 'Díaz', '908712348', 'marta.diaz@gmail.com', 
    '908712345', 2, 10, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );

INSERT INTO user_credentials (user_id, password, created_at, updated_at) 
VALUES 
  (
    1, '$2a$12$7GXFLr4yQ9y22ge5u8FpLOQAK2mcMvzlJFkZlCp5Vlmzvf4xrHwJy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    2, '$2a$12$J7RU5dTKJh/fcRrXOO06UeJb7ar2dFQ1E0KXyltx9msd7fC2aXZ3W', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    3, '$2a$12$TQApwTDX3US3pf0h0D8qCuZ4uljbUuzot2Y2Z2PS8YlMpccduLw1e', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    4, '$2a$12$Fg9jBDEjAZKX4F4OY9Vq0OKOFbHjTFotSpvTb5cNAlqZbUeBJS8FS', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    5, '$2a$12$RPWeel8EJRQIXVig0e/AQOYqOD.Ln6S3rf6ZxjWlNL0pfZsZZr98q', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    6, '$2a$12$l2rRW2iaIAYzP6OXcJWt5ePnmGUMTQbE9jVq9.ZllBxUde7cGH8fy', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    7, '$2a$12$OcTZ5X7HrH6bwG.Sb5dkLe9o7W5zgBEbzzF3RwSNCOVktU02ioNjK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    8, '$2a$12$NZcm8h9sA3sFE0yMTfNe.uGvEetziYI9TQF94yA6DeODNSRBSwt0y', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    9, '$2a$12$.XmJb.ZlUeZr1ptvl.wn2.Ka8DTwqGZLYYzvMjpv1LbemJDCg6D/i', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    10, '$2a$12$KSa/4xwufTK1x9MF52vTbOqZnMjj2.QTRmouzGEojvC6YDwKo/fPK', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );

-- Suppliers
INSERT INTO industries (name, created_at, updated_at) 
VALUES 
  ('Tecnología', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Hogar y Muebles', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Herramientas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Electrodomésticos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Moda', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Deportes y Fitness', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Salud y Belleza', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Juguetes y Bebés', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Libros', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Otros', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO vat_conditions (name, created_at, updated_at) 
VALUES 
  ('IVA Responsable Inscripto', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('IVA Responsable no Inscripto', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('IVA no Responsable', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('IVA Sujeto Exento', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Consumidor Final', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Responsable Monotributo', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Sujeto no Categorizado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Proveedor del Exterior', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Cliente del Exterior', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (
    'IVA Liberado - Ley Nº 19.640', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'IVA Responsable Inscripto - Agente de Percepción', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Pequeño Contribuyente Eventual', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  ('Monotributista Social', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (
    'Pequeño Contribuyente Eventual Social', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );

INSERT INTO tax_information (cuit, vat_condition_id, created_at, updated_at) 
VALUES 
  ('45-67890123-4', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('56-78901234-5', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('67-89012345-6', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('78-90123456-7', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('89-01234567-8', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('90-12345678-9', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('01-23456789-0', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('12-34567890-1', 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('23-45678901-2', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('34-56789012-3', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO contact_details (
  first_name, last_name, phone, email, 
  role, created_at, updated_at
) 
VALUES 
  (
    'Luis', 'Fernández', '234567890', 
    'luis.fernandez@gmail.com', 'Gerente', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'María', 'Martínez', '456789012', 
    'maria.martinez@gmail.com', 'Supervisor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Carlos', 'Gómez', '567890123', 'carlos.gomez@hotmail.com', 
    'Empleado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Laura', 'Rodríguez', '678901234', 
    'laura.rodriguez@yahoo.com', 'Empleado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Diego', 'Sánchez', '789012345', 
    'diego.sanchez@gmail.com', 'Empleado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Ana', 'Fernández', '890123456', 
    'ana.fernandez@outlook.com', 'Gerente', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Sofía', 'García', '901234567', 
    'sofia.garcia@gmail.com', 'Asistente', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Pablo', 'Pérez', '012345678', 'pablo.perez@gmail.com', 
    'Supervisor', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Marta', 'Hernández', '123456789', 
    'marta.hernandez@gmail.com', 'Empleado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'Lucas', 'Díaz', '234567890', 'lucas.diaz@gmail.com', 
    'Empleado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );

INSERT INTO suppliers (
  code, business_name, industry_id, 
  website, email, phone, image_id, address_id, 
  tax_information_id, contact_detail_id, is_deleted, created_at, updated_at
) 
VALUES 
  (
    'PROV-1', 'ElectroTech S.A.', 1, 'https://www.electrotech.com.ar', 
    'info@electrotech.com.ar', '+54 11 1234-5678', 
    1, 1, 1, 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-2', 'Ideal Hogar S.A.', 2, 'https://www.idealhogar.com.ar', 
    'contacto@idealhogar.com.ar', '+54 351 9876-5432', 
    2, 2, 2, 2, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-3', 'Wacker Herramientas S.R.L.', 
    3, 'https://www.wackerherramientas.com.ar', 
    'ventas@wacker.com.ar', '+54 341 5678-9012', 
    3, 3, 3, 3, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-4', 'ElectroHogar S.R.L.', 
    4, 'https://www.electrohogar.com.ar', 
    'info@electrohogar.com.ar', '+54 261 2345-6789', 
    4, 4, 4, 4, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-5', 'Distrito Moda S.A.', 5, 
    'https://www.distritomoda.com.ar', 
    'ventas@distritomoda.com.ar', '+54 381 8765-4321', 
    5, 5, 5, 5, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-6', 'Punto Deportivo S.A.', 
    6, 'https://www.puntodeportivo.com.ar', 
    'info@puntodeportivo.com.ar', '+54 387 5432-1098', 
    6, 6, 6, 6, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-7', 'Tienda Beauty S.A.', 7, 
    'https://www.tiendabeauty.com.ar', 
    'consultas@tiendabeauty.com.ar', 
    '+54 299 1234-5678', 7, 7, 7, 7, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-8', 'El Mundo del Juguete S.R.L.', 
    8, 'https://www.elmundodeljuguete.com.ar', 
    'ventas@elmundodeljuguete.com.ar', 
    '+54 280 9876-5432', 8, 8, 8, 8, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-9', 'Planeta Libros S.A.', 
    9, 'https://www.planetalibros.com.ar', 
    'info@planetalibros.com.ar', '+54 343 5678-9012', 
    9, 9, 9, 9, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'PROV-10', 'Café La Bastilla S.A.', 
    10, 'https://www.tiendacafelabastilla.com.ar', 
    'ventas@labastilla.com.ar', '+54 11 2345-6789', 
    10, 10, 10, 10, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );

-- Products
INSERT INTO categories (name, created_at, updated_at) 
VALUES 
  ('Tecnología', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Hogar y Muebles', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Herramientas', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Electrodomésticos', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Moda', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Deportes y Fitness', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Salud y Belleza', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Juguetes y Bebés', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Libros', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Otros', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO products (
  sku, name, description, price, image_id, 
  category_id, supplier_id, is_deleted, created_at, updated_at
) 
VALUES 
  (
    'SKU-1', 'Teléfono Inteligente', 
    'Teléfono inteligente de última generación', 
    159999.99, 11, 1, 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-2', 'Auriculares Inalámbricos', 
    'Auriculares con cancelación de ruido', 
    18999.99, 12, 1, 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-3', 'Sofá Moderno', 'Sofá elegante para tu hogar', 
    255999.99, 13, 2, 2, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-4', 'Mesa de Centro', 'Mesa de centro de diseño contemporáneo', 
    112999.99, 14, 2, 2, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-5', 'Taladro Eléctrico', 'Taladro potente para proyectos de bricolaje', 
    78999.99, 15, 3, 3, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-6', 'Juego de Destornilladores', 
    'Set completo de destornilladores profesionales', 
    20999.99, 16, 3, 3, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-7', 'Lavadora de Ropa', 'Lavadora eficiente con múltiples funciones', 
    588999.99, 17, 4, 4, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-8', 'Secadora de Ropa', 'Secadora de ropa con tecnología de secado rápido', 
    808999.99, 18, 4, 4, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-9', 'Vestido de Noche Elegante', 
    'Vestido largo para ocasiones especiales', 
    59900.00, 19, 5, 5, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-10', 'Zapatos de Tacón Alto', 
    'Zapatos elegantes para complementar tu look', 
    108499.99, 20, 5, 5, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-11', 'Pelota de Fútbol', 'Pelota oficial para partidos de fútbol', 
    19999.99, 21, 6, 6, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-12', 'Cinta de Correr Plegable', 
    'Cinta de correr para entrenamiento en casa', 
    314600.00, 22, 6, 6, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-13', 'Kit de Maquillaje Profesional', 
    'Colección completa de maquillaje de alta calidad', 
    36100.00, 23, 7, 7, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-14', 'Cepillo Alisador de Pelo', 
    'Cepillo que alisa el pelo de forma rápida y fácil', 
    34998.99, 24, 7, 7, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-15', 'Juguetes Educativos para Bebés', 
    'Set de juguetes didácticos para bebés', 
    2999.99, 25, 8, 8, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-16', 'Cuna Portátil', 'Cuna plegable y portátil para viajes', 
    227999.99, 26, 8, 8, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-17', 'Colección de Novelas Best Sellers', 
    'Paquete con las mejores novelas del año', 
    29999.99, 27, 9, 9, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-18', 'Libro de Cocina Gourmet', 
    'Libro con recetas gourmet y técnicas culinarias', 
    21870.00, 28, 9, 9, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-19', 'Máquina de Café Automática', 
    'Máquina de café con múltiples opciones de preparación', 
    196549.99, 29, 10, 10, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'SKU-20', 'Robot Aspirador Inteligente', 
    'Aspiradora robot con navegación inteligente', 
    314999.99, 30, 10, 10, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );

-- Orders
INSERT INTO statuses (name, created_at, updated_at) 
VALUES 
  ('En Proceso', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('En Camino', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Entregado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  ('Cancelado', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO orders (
  number, issue_date, delivery_date, 
  comments, total, status_id, supplier_id, 
  user_id, is_deleted, created_at, updated_at
) 
VALUES 
  (
    'OC-1', '2023-06-01', '2023-06-10', 
    'Contactar con el encargado de logística.', 
    517999.95, 1, 1, 1, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-2', '2023-06-15', '2023-06-25', 
    'Entregar en el almacén central.', 
    707999.95, 1, 2, 2, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-3', '2023-07-05', '2023-07-15', 
    'Coordinar entrega con el departamento de compras.', 
    178999.97, 1, 3, 3, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-4', '2023-08-01', '2023-08-11', 
    'Enviar a la sucursal de Mendoza.', 
    3384999.95, 2, 4, 4, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-5', '2023-09-10', '2023-09-20', 
    'Prioridad en la entrega por necesidades urgentes.', 
    407999.99, 2, 5, 5, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-6', '2023-10-01', '2023-10-11', 
    'Entregar antes de la fecha acordada.', 
    983799.98, 2, 6, 6, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-7', '2023-11-05', '2023-11-15', 
    'Seguir protocolos de seguridad al entregar en planta.', 
    179398.99, 3, 7, 7, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-8', '2023-12-01', '2023-12-11', 
    'Entrega rápida para eventos promocionales.', 
    464999.95, 3, 8, 8, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-9', '2023-12-25', '2024-01-04', 
    'Coordinar entrega con el área de eventos.', 
    73739.99, 4, 9, 9, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  ), 
  (
    'OC-10', '2024-01-01', '2024-01-10', 
    'Entregar productos antes del lanzamiento.', 
    1219649.95, 4, 10, 10, false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP
  );
  
INSERT INTO order_details (
  order_id, product_id, quantity, price, created_at, updated_at
) 
VALUES 
  (1, 1, 3, 159999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (1, 2, 2, 18999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (2, 3, 1, 255999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (2, 4, 4, 112999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (3, 5, 2, 78999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (3, 6, 1, 20999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (4, 7, 3, 588999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (4, 8, 2, 808999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (5, 9, 5, 59900.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (5, 10, 1, 108499.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (6, 11, 2, 19999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (6, 12, 3, 314600.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (7, 13, 4, 36100.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (7, 14, 1, 34998.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (8, 15, 3, 2999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (8, 16, 2, 227999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (9, 17, 1, 29999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (9, 18, 2, 21870.00, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (10, 19, 3, 196549.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP), 
  (10, 20, 2, 314999.99, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
