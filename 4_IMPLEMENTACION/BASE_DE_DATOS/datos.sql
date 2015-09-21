
-- ----------------------------
-- Records of categoria
-- ----------------------------
BEGIN;
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('1', 'teja', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('2', 'tabique', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('3', 'ladrillo', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('4', 'celosia', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('5', 'ovalin', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('6', 'lavadero', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('7', 'solera', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('8', 'adoquin', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('9', 'maceta', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('10', 'restaurador', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('11', 'sellador', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('12', 'recinto', 's');
INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('13', 'laja', 's');
COMMIT;

-- ----------------------------
-- Records of cliente
-- ----------------------------
BEGIN;
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('1', 'Default', 'Default', 'Default', '1', '1', '1', 'Default', 'Default', 'Domicilio desconocido.', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('2', 'Alvaro', 'Castillo', 'Gomez', '7', '2', '87', 'Francisco Zarco', 'Cordoba', 'en la casa de porton negro', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('3', 'Marcos', 'Carmona', 'Yepez', '1', '24', '92', 'El Barreal', 'Cordoba', 'contraesquina a a escuela', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('4', 'Leticia', 'Perez', 'Sanchez', '18', '21', '176', 'Lomas Pedregal', 'Cordoba', 'a un lado del parque', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('5', 'Oscar', 'Castillo', 'Merino', '9', '2', '65', 'Esperanza', 'Cordoba', 'a dos cuadras de la iglesia', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('6', 'Marina', 'Medina', 'Rojas', '2', '6', '12', 'Centro', 'Cordoba', 'frente a tienda "ilusion"', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('7', 'Gonzalo', 'Perez', 'Prado', '9', '7', '164', 'Solidaridad', 'Cordoba', 'Junto a casa verde con cafe', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('8', 'Alondra', 'Rodriguez', 'Rojas', '23', '12', '275', 'Castillo', 'Cordoba', 'adelante del puesto de verduras', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('9', 'Fernando', 'Arellano', 'Sierra', '12', '16', '294', 'Las Ruinas', 'Cordoba', 'En la casa que esta en construccion', 'n');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('10', 'Esteban', 'Flores', 'Pozos', '21', '32', '54', 'Nueva Esperanza', 'Cordoba', 'Contraesquina con el puesto de comida', 'n');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('11', 'Andrez', 'Martinez', 'Sanchez', '14', '31', '321', 'Pedregal', 'Cordoba', 'Frente a tienda de abarrotes', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('12', 'Alicia', 'Contreras', 'Olivares', '65', '13', '86', 'Nueva', 'Cordoba', 'Frente a casa verde', 'n');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('13', 'Martin', 'Castellano', 'Perez', '23', '43', '95', 'desconocida', 'Cordoba', 'casa con barda rosa', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('14', 'Carlos', 'Garcia', 'Rosas', '24', '54', '203', 'Alegria', 'Cordoba', 'casa azul con ventanas polarizadas', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('15', 'Maria', 'Castañeda', 'Pulido', '1', '4', '34', 'Centro', 'Cordoba', 'A un costado de la tienda de abarrotes', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('16', 'Martina', 'Murillo', 'Serrano', '3', '5', '57', 'Cerenidad', 'Cordoba', 'Frente a la casa beige', 's');
INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia", "activo") VALUES ('17', 'Martin', 'Martinez', 'Marin', '3', '3', '54', '5 de mayo', 'Cordoba', 'frente a casa de dos pisos azul.', 's');
COMMIT;

-- ----------------------------
-- Records of empleado
-- ----------------------------
BEGIN;
INSERT INTO "empleado" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "usuario", "password", "tipo", "activo") VALUES ('1', 'Alfredo', 'Flores', 'Hernandez', '2', '3', '34', 'Centro', 'Chocaman', '271-234-12-12', 'alfredo1', '9bd369648875fe7720e30fe1add80770', 'empleado', 's');
INSERT INTO "empleado" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "usuario", "password", "tipo", "activo") VALUES ('2', 'Angeles', 'Gonzalez', 'Blanco', '43', '119', '65', 'Fraternidad', 'Cordoba', '271-234-23-34', 'angeles1', '6ebe562941c5f5dea13fd12c0bf01dee', 'empleado', 's');
INSERT INTO "empleado" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "usuario", "password", "tipo", "activo") VALUES ('3', 'Daniel Isaac', 'Sosa', 'Rincon', '23', '21', '87', 'Los Filtros', 'Cordoba', '271-123-15-29', 'isaac12', '93e9a8a830ef81f187b2f9d0b91dc724', 'empleado', 's');
INSERT INTO "empleado" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "usuario", "password", "tipo", "activo") VALUES ('4', 'Alan', 'Ramirez', 'Garcia', '3', '4', '21', 'Guadalupe', 'Omealca', '271-234-23-24', 'alan111', '4431eab85e54d132632660f02d2b8b85', 'admin', 's');
INSERT INTO "empleado" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "usuario", "password", "tipo", "activo") VALUES ('5', 'Pedro', 'Martinez', 'Morales', '12', '23', '16', 'Evergreen', 'Orizaba', '271-707-57-97', 'pedro111', 'ed01257ccb8a0d91b4a9011d854a9e4b', 'empleado', 's');
INSERT INTO "empleado" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "usuario", "password", "tipo", "activo") VALUES ('6', 'Alberto', 'Carmona', 'Rosas', '1', '1', '2', 'Simple', 'Desconocido', '000-000-00-00', 'alberto1', '9fb747d7aca2d9b831285d05ad61ec66', 'empleado', 's');
INSERT INTO "empleado" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "usuario", "password", "tipo", "activo") VALUES ('7', 'Juanito', 'Bananas', 'Machuca', '2', '1', '54', 'Silver', 'Cordoba', '132-123-12-12', 'juanito11', '75c96249f401991caacb3c708bba72f0', 'empleado', 's');
COMMIT;

-- ----------------------------
-- Records of fechahora
-- ----------------------------
BEGIN;
INSERT INTO "fechahora" ("id", "fechahora") VALUES ('1', '2015-09-21 10:52:41.961');
COMMIT;

-- ----------------------------
-- Records of marca
-- ----------------------------
BEGIN;
INSERT INTO "marca" ("id", "nombre", "activo") VALUES ('1', 'Teja el aguila', 's');
INSERT INTO "marca" ("id", "nombre", "activo") VALUES ('2', 'Novaceramic', 's');
INSERT INTO "marca" ("id", "nombre", "activo") VALUES ('3', 'Materiales Import', 's');
INSERT INTO "marca" ("id", "nombre", "activo") VALUES ('4', 'Nova', 's');
INSERT INTO "marca" ("id", "nombre", "activo") VALUES ('5', 'novatec', 's');
COMMIT;

-- ----------------------------
-- Records of producto
-- ----------------------------
BEGIN;
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('1', '4', 'simple', '2', '27', '26', '100', '250', '50', 'interior', 'n');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('2', '1', 'vidriada', '1', '5', '4', '100', '250', '50', 'exterior', 'n');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('3', '1', 'barro prensada', '1', '6', '5', '100', '250', '50', 'exterior', 'n');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('4', '3', 'rojo', '2', '7', '6', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('5', '9', 'barro', '2', '50', '45', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('6', '7', 'hierro', '2', '150', '140', '100', '250', '50', 'exterior', 'n');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('7', '2', 'rosado', '2', '12', '11', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('8', '6', 'grande', '2', '400', '380', '100', '250', '50', 'interior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('9', '3', 'hueco', '2', '14', '13', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('12', '6', 'chico', '2', '300', '280', '100', '250', '50', 'interior', 'n');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('13', '1', 'barro media ala', '1', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('14', '1', 'barro tipo portuguesa', '1', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('15', '1', 'barro tipo guadalajara', '1', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('16', '1', 'barro Ala Mega', '1', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('17', '1', 'barro rústica', '1', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('18', '2', 'barro', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('19', '2', 'rojo', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('20', '2', 'rojo tipo solera', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('21', '2', 'rojo tipo rosa española', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('22', '2', 'rojo tipo cuadrado', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('23', '2', 'rojo tipo cintillailla', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('24', '2', 'rojo tipo sencillo', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('25', '2', 'rojo tipo media luna', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('26', '2', 'pecho de paloma', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('27', '2', 'rojo tipo octágono', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('28', '2', 'rojo tipo hacha', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('29', '2', 'rojo pecho de ganso', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('30', '4', 'decorativa de madera', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('31', '4', 'mixta', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('32', '4', 'fieltro', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('33', '4', 'flor', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('34', '4', 'arco', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('35', '4', 'madera', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('36', '5', 'mármol', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('37', '6', 'granito', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('38', '7', 'un octavo', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('39', '7', 'tres cuartos', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('40', '7', 'un cuarto', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('41', '7', 'media', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('42', '7', 'cinco octavos', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('43', '8', 'cantera natural', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('44', '8', 'cantera rosa', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('45', '8', 'cantera naranja', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('46', '8', 'cantera gris', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('47', '10', 'color para adoquin', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('48', '9', 'cuadrada terracota', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('49', '9', 'cemento', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('50', '9', 'barro pintada', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('51', '9', 'artesanal de barro', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('52', '9', 'patas de barro', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('53', '11', 'base de agua Wismar', '2', '25', '23', '100', '250', '50', 'interior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('54', '11', 'para mármol y granito', '2', '25', '23', '100', '250', '50', 'exterior', 's');
INSERT INTO "producto" ("id", "id_categoria", "descripcion", "id_marca", "precio1", "precio2", "stock", "stock_max", "stock_min", "tipo", "activo") VALUES ('55', '12', 'volcánico negro rústico', '2', '25', '23', '100', '250', '50', 'exterior', 's');
COMMIT;

-- ----------------------------
-- Records of proveedor
-- ----------------------------
BEGIN;
INSERT INTO "proveedor" ("id", "nombre", "apellido_paterno", "apellido_materno", "empresa", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "activo") VALUES ('1', 'Julio', 'Tejeda', 'Rodriguez', 'Materiales Tejeda', '2', '7', '23', 'Centro', 'Cordoba', '271-633-21-12', 's');
INSERT INTO "proveedor" ("id", "nombre", "apellido_paterno", "apellido_materno", "empresa", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "activo") VALUES ('2', 'Marcos', 'Gamboa', 'Merino', 'Materiales Game', '1', '5', '85', 'Centro', 'Cordoba', '271-234-12-12', 's');
INSERT INTO "proveedor" ("id", "nombre", "apellido_paterno", "apellido_materno", "empresa", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "activo") VALUES ('3', 'Gerardo', 'Ortiz', 'Cano', 'Materiales Ortiz', '8', '35', '98', '5 de mayo', 'Cordoba', '271-456-23-23', 's');
INSERT INTO "proveedor" ("id", "nombre", "apellido_paterno", "apellido_materno", "empresa", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "activo") VALUES ('4', 'Alvaro', 'Castillo', 'Benitez', 'Materiales El Castillo', '34', '12', '285', 'Reforma', 'Cordoba', '271-645-12-12', 's');
INSERT INTO "proveedor" ("id", "nombre", "apellido_paterno", "apellido_materno", "empresa", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "activo") VALUES ('5', 'Hilario', 'Medina', 'Cano', 'Materiales El Constructor', '12', '2', '234', 'Siempre viva', 'Cordoba', '271-243-45-45', 's');
INSERT INTO "proveedor" ("id", "nombre", "apellido_paterno", "apellido_materno", "empresa", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "activo") VALUES ('6', 'Martin', 'Suarez', 'Castillo', 'Construccion En Grande', '36', '22', '439', 'Desconocida', 'Cordoba', '000-000-00-00', 's');
INSERT INTO "proveedor" ("id", "nombre", "apellido_paterno", "apellido_materno", "empresa", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "activo") VALUES ('7', 'Hilario', 'Estandar', 'Mejia', 'Materiales Mejia', '12', '12', '12', 'Coste', 'Cuichapa', '217-234-23-54', 'n');
COMMIT;

-- ----------------------------
-- Records of ajusteinventario
-- ----------------------------
BEGIN;
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('1', '7', 'Devolvieron producto', '0', '10', '3', '2015-07-07 12:03:50.213');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('2', '7', 'Nueva existencia', '0', '2', '3', '2015-07-07 13:10:45.687');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('3', '12', 'Nueva existencia', '0', '5', '3', '2015-07-07 14:24:48.825');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('4', '1', 'actualizacion de producto', '300', '250', '1', '2015-07-30 23:29:55.188');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('5', '1', 'actualizacion de producto', '300', '250', '1', '2015-07-30 23:31:44.922');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('6', '1', 'actualizacion de producto', '300', '100', '1', '2015-07-30 23:32:31.435');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('7', '5', 'actualizacion', '0', '69', '3', '2015-08-06 00:51:34.31');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('8', '7', 'Actualizacion', '0', '200', '3', '2015-08-06 01:06:20.948');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('9', '9', 'se compro nuevo producto.', '310', '300', '3', '2015-08-08 10:43:04.941');
INSERT INTO "ajusteinventario" ("id", "id_producto", "causa", "existencia_actual", "nueva_existencia", "id_empleado", "fecha_hora") VALUES ('10', '9', 'se lo llevo juanito.', '300', '250', '3', '2015-08-08 10:43:48.364');
COMMIT;


-- ----------------------------
-- Records of compra
-- ----------------------------
BEGIN;
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('1', '2', '1000', '2015-05-28', '2015-08-06 01:40:35.118', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('2', '4', '500', '2015-05-28', '2015-08-10 22:43:02.02', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('3', '3', '2000', '2015-05-28', '2015-08-06 01:47:18.614', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('4', '5', '2500', '2015-05-28', '2015-08-06 01:47:22.846', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('5', '1', '1500', '2015-05-28', '2015-08-08 08:59:25.025', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('6', '2', '3000', '2015-05-28', '2015-05-29 18:21:46', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('7', '4', '2300', '2015-05-28', '2015-05-29 18:23:40', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('8', '2', '2200', '2015-05-28', '2015-08-06 01:47:28.373', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('9', '4', '1100', '2015-05-28', '2015-08-10 20:16:16.757', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('10', '5', '1200', '2015-05-28', '2015-08-06 01:37:48.828', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('12', '4', '1350', '2015-08-03', '2015-08-06 01:40:04.145', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('13', '3', '1050', '2015-08-03', '2015-08-06 01:24:31.444', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('14', '4', '110', '2015-08-06', '2015-08-06 01:45:41.035', '4');
INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido", "fecha_recepcion", "id_empleado") VALUES ('15', '5', '90', '2015-08-08', '2015-08-08 11:07:11.12', '4');
COMMIT;


-- ----------------------------
-- Records of detallecompra
-- ----------------------------
BEGIN;
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('1', '1', '1', '100', '4', '400', '6', '5');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('2', '3', '10', '120', '6', '720', '8', '7');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('3', '4', '6', '200', '14', '2800', '16', '15');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('4', '2', '8', '250', '12', '3000', '14', '13');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('5', '1', '3', '150', '4', '600', '6', '5');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('6', '2', '4', '120', '8', '960', '10', '9');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('7', '3', '7', '200', '6', '1200', '8', '7');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('8', '1', '9', '300', '12', '3600', '14', '13');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('9', '2', '2', '230', '9', '2070', '11', '10');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('10', '4', '5', '340', '8', '2720', '10', '9');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('11', '1', '1', '45', '25', '1125', '27', '26');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('12', '1', '1', '45', '25', '1125', '27', '26');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('13', '9', '12', '150', '9', '1350', '13', '11');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('14', '9', '13', '150', '7', '1050', '11', '9');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('15', '9', '14', '10', '11', '110', '14', '13');
INSERT INTO "detallecompra" ("id", "id_producto", "id_compra", "cantidad", "precio_compra", "total", "precio_venta1", "precio_venta2") VALUES ('16', '7', '15', '10', '9', '90', '12', '11');
COMMIT;


-- ----------------------------
-- Records of venta
-- ----------------------------
BEGIN;
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('1', '2', '3', '1000', '2015-05-29 18:44:21');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('2', '1', '4', '1500', '2015-05-29 18:44:40');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('3', '3', '6', '2000', '2015-05-29 18:45:42');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('4', '4', '2', '3400', '2015-05-29 18:46:01');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('5', '3', '3', '4500', '2015-05-29 18:46:26');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('6', '2', '5', '1600', '2015-05-29 18:46:54');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('7', '4', '17', '1000', '2015-05-29 18:47:17');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('8', '1', '2', '1900', '2015-05-29 18:47:35');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('9', '1', '3', '990', '2015-05-29 18:47:53');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('10', '2', '2', '2200', '2015-05-29 18:48:16');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('11', '1', '2', '3333', '2015-07-31 01:09:18.966');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('12', '1', '17', '1000', '2015-08-01 22:18:49.928');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('13', '1', '17', '1', '2015-08-02 16:23:36.183');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('14', '4', '17', '120', '2015-08-02 17:44:23.985');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('15', '4', '17', '120', '2015-08-02 17:52:37.086');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('16', '4', '2', '500', '2015-08-02 17:54:56.507');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('17', '4', '2', '84', '2015-08-02 23:47:21.645');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('18', '4', '3', '500', '2015-08-06 00:55:12.817');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('19', '4', '17', '214', '2015-08-08 11:02:12.343');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('20', '4', '7', '60', '2015-08-08 11:05:14.357');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('21', '4', '1', '500', '2015-08-10 22:27:59.806');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('22', '4', '1', '7', '2015-08-10 22:50:42.712');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('23', '4', '1', '12', '2015-08-11 07:26:43.144');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('24', '2', '8', '250', '2015-09-18 13:09:50.638');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('25', '2', '1', '250', '2015-09-18 13:12:52.167');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('26', '2', '1', '250', '2015-09-18 13:13:15.813');
INSERT INTO "venta" ("id", "id_vendedor", "id_cliente", "total", "fecha_hora") VALUES ('27', '6', '1', '850', '2015-09-18 13:16:15.002');
COMMIT;

-- ----------------------------
-- Records of detalleventa
-- ----------------------------
BEGIN;
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('1', '3', '3', '200', '600', '1');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('2', '2', '5', '400', '2000', '2');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('3', '4', '8', '300', '2400', '3');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('4', '3', '6', '100', '600', '4');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('5', '1', '9', '100', '900', '5');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('6', '2', '11', '80', '880', '6');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('7', '4', '9', '100', '900', '7');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('8', '3', '7', '100', '700', '8');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('9', '1', '5', '180', '900', '9');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('10', '2', '3', '200', '600', '10');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('11', '1', '45', '25', '1125', '1');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('12', '4', '7', '10', '70', '14');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('13', '2', '5', '10', '50', '14');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('14', '4', '7', '10', '70', '15');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('15', '2', '5', '10', '50', '15');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('16', '2', '5', '100', '500', '16');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('17', '4', '7', '12', '84', '17');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('18', '5', '50', '10', '500', '18');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('19', '4', '7', '12', '84', '19');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('20', '9', '13', '10', '130', '19');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('21', '7', '6', '10', '60', '20');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('22', '5', '50', '10', '500', '21');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('23', '4', '7', '1', '7', '22');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('24', '7', '12', '1', '12', '23');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('25', '13', '25', '10', '250', '24');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('26', '15', '25', '10', '250', '25');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('27', '20', '25', '10', '250', '26');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('28', '5', '50', '12', '600', '27');
INSERT INTO "detalleventa" ("id", "id_producto", "precio", "cantidad", "total", "id_venta") VALUES ('29', '14', '25', '10', '250', '27');
COMMIT;

