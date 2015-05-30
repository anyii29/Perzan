
-- ----------------------------
-- Table structure for administrador
-- ----------------------------
CREATE TABLE "administrador" (
"id" serial NOT NULL,
"administrador" varchar(50) COLLATE "default" NOT NULL,
"password" varchar(50) COLLATE "default" NOT NULL
)
;
-- ----------------------------
-- Table structure for categoria
-- ----------------------------
CREATE TABLE "categoria" (
"id" serial NOT NULL,
"nombre" varchar(50) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- Table structure for cliente
-- ----------------------------
CREATE TABLE "cliente" (
"id" serial NOT NULL,
"nombre" varchar(30) COLLATE "default" NOT NULL,
"apellido_paterno" varchar(25) COLLATE "default" NOT NULL,
"apellido_materno" varchar(25) COLLATE "default" NOT NULL,
"calle" int4 NOT NULL,
"avenida" int4 NOT NULL,
"numero" int4 NOT NULL,
"colonia" varchar(25) COLLATE "default" NOT NULL,
"municipio" varchar(25) COLLATE "default" NOT NULL,
"referencia" varchar(255) COLLATE "default" NOT NULL
)
;
-- ----------------------------
-- Table structure for compra
-- ----------------------------
CREATE TABLE "compra" (
"id" serial NOT NULL,
"id_proveedor" int4 NOT NULL,
"total" float4 NOT NULL,
"fecha" timestamp(6) NOT NULL
)
;
-- ----------------------------
-- Table structure for detallecompra
-- ----------------------------
CREATE TABLE "detallecompra" (
"id" serial NOT NULL,
"id_producto" int4 NOT NULL,
"id_proveedor" int4 NOT NULL,
"id_compra" int4 NOT NULL,
"cantidad" int4 NOT NULL,
"precio" float4 NOT NULL
)
;
-- ----------------------------
-- Table structure for detalleventa
-- ----------------------------
CREATE TABLE "detalleventa" (
"id" serial NOT NULL,
"id_producto" int4 NOT NULL,
"cantidad" int4 NOT NULL,
"id_venta" int4 NOT NULL
)
;----------------
-- Table structure for empleado
-- ----------------------------
CREATE TABLE "empleado" (
"id" serial NOT NULL,
"nombre" varchar(50) COLLATE "default" NOT NULL,
"apellido_paterno" varchar(50) COLLATE "default" NOT NULL,
"apellido_materno" varchar(50) COLLATE "default" NOT NULL,
"calle" int4 NOT NULL,
"avenida" int4 NOT NULL,
"numero" int4 NOT NULL,
"colonia" varchar(25) COLLATE "default" NOT NULL,
"municipio" varchar(25) COLLATE "default" NOT NULL,
"telefono" varchar(14) COLLATE "default" DEFAULT '000-000-00-00'::character varying NOT NULL,
"usuario" varchar(50) COLLATE "default" NOT NULL,
"password" varchar(50) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- ----------------------------
-- Table structure for marca
-- ----------------------------
CREATE TABLE "marca" (
"id" serial NOT NULL,
"nombre" varchar(50) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- ----------------------------
-- Table structure for producto
-- ----------------------------
CREATE TABLE "producto" (
"id" serial NOT NULL,
"nombre" varchar(25) COLLATE "default" NOT NULL,
"id_categoria" int4 NOT NULL,
"id_marca" int4 NOT NULL,
"precio" float4 NOT NULL,
"precio2" float4 NOT NULL,
"stock" int4 NOT NULL,
"stock_max" int4 NOT NULL,
"stock_min" int4 NOT NULL,
"tipos" varchar(30) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- ----------------------------
-- Table structure for proveedor
-- ----------------------------
CREATE TABLE "proveedor" (
"id" serial NOT NULL,
"nombre" varchar(50) COLLATE "default" NOT NULL,
"apellido_paterno" varchar(50) COLLATE "default" NOT NULL,
"apellido_materno" varchar(50) COLLATE "default" NOT NULL,
"empresa" varchar(50) COLLATE "default" NOT NULL,
"calle" int4 NOT NULL,
"avenida" int4 NOT NULL,
"numero" int4 NOT NULL,
"colonia" varchar(50) COLLATE "default" NOT NULL,
"municipio" varchar(50) COLLATE "default" NOT NULL,
"telefono" varchar(14) COLLATE "default" DEFAULT '000-000-00-00'::character varying NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- ----------------------------
-- Table structure for venta
-- ----------------------------
CREATE TABLE "venta" (
"id" serial NOT NULL,
"id_vendedor" int4 NOT NULL,
"id_cliente" int4 NOT NULL,
"importe" float4 NOT NULL,
"fecha" timestamp(6) NOT NULL
)
;

-- ----------------------------
-- Uniques structure for table administrador
-- ----------------------------
ALTER TABLE "administrador" ADD UNIQUE ("administrador");

-- ----------------------------
-- Checks structure for table administrador
-- ----------------------------
ALTER TABLE "administrador" ADD CHECK (length(rtrim(ltrim((administrador)::text))) > 6);
ALTER TABLE "administrador" ADD CHECK (length(rtrim(ltrim((password)::text))) > 6);
ALTER TABLE "administrador" ADD CHECK (id > 0);
ALTER TABLE "administrador" ADD CHECK ((password)::text <> (administrador)::text);

-- ----------------------------
-- Primary Key structure for table administrador
-- ----------------------------
ALTER TABLE "administrador" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table categoria
-- ----------------------------
ALTER TABLE "categoria" ADD UNIQUE ("nombre");

-- ----------------------------
-- Checks structure for table categoria
-- ----------------------------
ALTER TABLE "categoria" ADD CHECK (activo = ANY (ARRAY['s'::bpchar, 'n'::bpchar]));
ALTER TABLE "categoria" ADD CHECK (id > 0);
ALTER TABLE "categoria" ADD CHECK (length(rtrim(ltrim((nombre)::text))) > 3);
ALTER TABLE "categoria" ADD CHECK (rtrim(ltrim((nombre)::text)) = (nombre)::text);

-- ----------------------------
-- Primary Key structure for table categoria
-- ----------------------------
ALTER TABLE "categoria" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table cliente
-- ----------------------------
ALTER TABLE "cliente" ADD CHECK ((length(rtrim(ltrim((nombre)::text))) > 2) AND (rtrim(ltrim((nombre)::text)) = (nombre)::text));
ALTER TABLE "cliente" ADD CHECK ((length(rtrim(ltrim((apellido_materno)::text))) > 2) AND (rtrim(ltrim((apellido_materno)::text)) = (apellido_materno)::text));
ALTER TABLE "cliente" ADD CHECK ((length(rtrim(ltrim((municipio)::text))) > 4) AND (rtrim(ltrim((municipio)::text)) = (municipio)::text));
ALTER TABLE "cliente" ADD CHECK ((length(rtrim(ltrim((referencia)::text))) > 18) AND (rtrim(ltrim((referencia)::text)) = (referencia)::text));
ALTER TABLE "cliente" ADD CHECK ((length(rtrim(ltrim((colonia)::text))) > 4) AND (rtrim(ltrim((colonia)::text)) = (colonia)::text));
ALTER TABLE "cliente" ADD CHECK (numero > 0);
ALTER TABLE "cliente" ADD CHECK (avenida > 0);
ALTER TABLE "cliente" ADD CHECK (calle > 0);
ALTER TABLE "cliente" ADD CHECK (id > 0);
ALTER TABLE "cliente" ADD CHECK ((length(rtrim(ltrim((apellido_paterno)::text))) > 2) AND (rtrim(ltrim((apellido_paterno)::text)) = (apellido_paterno)::text));

-- ----------------------------
-- Primary Key structure for table cliente
-- ----------------------------
ALTER TABLE "cliente" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table compra
-- ----------------------------
ALTER TABLE "compra" ADD CHECK (id_proveedor > 0);
ALTER TABLE "compra" ADD CHECK (id > 0);
ALTER TABLE "compra" ADD CHECK (total > (0)::double precision);

-- ----------------------------
-- Primary Key structure for table compra
-- ----------------------------
ALTER TABLE "compra" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table detallecompra
-- ----------------------------
ALTER TABLE "detallecompra" ADD CHECK (id > 0);
ALTER TABLE "detallecompra" ADD CHECK (id_producto > 0);
ALTER TABLE "detallecompra" ADD CHECK (id_proveedor > 0);
ALTER TABLE "detallecompra" ADD CHECK (precio > (0)::double precision);
ALTER TABLE "detallecompra" ADD CHECK (cantidad > 0);
ALTER TABLE "detallecompra" ADD CHECK (id_compra > 0);

-- ----------------------------
-- Primary Key structure for table detallecompra
-- ----------------------------
ALTER TABLE "detallecompra" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table detalleventa
-- ----------------------------
ALTER TABLE "detalleventa" ADD CHECK (id > 0);
ALTER TABLE "detalleventa" ADD CHECK (id_producto > 0);
ALTER TABLE "detalleventa" ADD CHECK (cantidad > 0);
ALTER TABLE "detalleventa" ADD CHECK (id_venta > 0);

-- ----------------------------
-- Primary Key structure for table detalleventa
-- ----------------------------
ALTER TABLE "detalleventa" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table empleado
-- ----------------------------
ALTER TABLE "empleado" ADD UNIQUE ("usuario", "password");

-- ----------------------------
-- Checks structure for table empleado
-- ----------------------------
ALTER TABLE "empleado" ADD CHECK ((length(rtrim(ltrim((apellido_paterno)::text))) > 2) AND (rtrim(ltrim((apellido_paterno)::text)) = (apellido_paterno)::text));
ALTER TABLE "empleado" ADD CHECK ((length(rtrim(ltrim((usuario)::text))) > 6) AND (rtrim(ltrim((usuario)::text)) = (usuario)::text));
ALTER TABLE "empleado" ADD CHECK (numero > 0);
ALTER TABLE "empleado" ADD CHECK ((length(rtrim(ltrim((colonia)::text))) > 4) AND (rtrim(ltrim((colonia)::text)) = (colonia)::text));
ALTER TABLE "empleado" ADD CHECK ((length(rtrim(ltrim((telefono)::text))) = 13) AND (rtrim(ltrim((telefono)::text)) = (telefono)::text));
ALTER TABLE "empleado" ADD CHECK ((length(rtrim(ltrim((municipio)::text))) > 4) AND (rtrim(ltrim((municipio)::text)) = (municipio)::text));
ALTER TABLE "empleado" ADD CHECK (activo = ANY (ARRAY['s'::bpchar, 'n'::bpchar]));
ALTER TABLE "empleado" ADD CHECK ((length(rtrim(ltrim((nombre)::text))) > 2) AND (rtrim(ltrim((nombre)::text)) = (nombre)::text));
ALTER TABLE "empleado" ADD CHECK (id > 0);
ALTER TABLE "empleado" ADD CHECK ((length(rtrim(ltrim((apellido_materno)::text))) > 2) AND (rtrim(ltrim((apellido_materno)::text)) = (apellido_materno)::text));
ALTER TABLE "empleado" ADD CHECK ((length(rtrim(ltrim((password)::text))) > 6) AND (rtrim(ltrim((password)::text)) = (password)::text));
ALTER TABLE "empleado" ADD CHECK (calle > 0);
ALTER TABLE "empleado" ADD CHECK (avenida > 0);

-- ----------------------------
-- Primary Key structure for table empleado
-- ----------------------------
ALTER TABLE "empleado" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table marca
-- ----------------------------
ALTER TABLE "marca" ADD UNIQUE ("nombre");

-- ----------------------------
-- Checks structure for table marca
-- ----------------------------
ALTER TABLE "marca" ADD CHECK (activo = ANY (ARRAY['s'::bpchar, 'n'::bpchar]));
ALTER TABLE "marca" ADD CHECK (id > 0);
ALTER TABLE "marca" ADD CHECK ((length(rtrim(ltrim((nombre)::text))) > 3) AND (rtrim(ltrim((nombre)::text)) = (nombre)::text));

-- ----------------------------
-- Primary Key structure for table marca
-- ----------------------------
ALTER TABLE "marca" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table producto
-- ----------------------------
ALTER TABLE "producto" ADD CHECK ((length(rtrim(ltrim((nombre)::text))) > 3) AND (rtrim(ltrim((nombre)::text)) = (nombre)::text));
ALTER TABLE "producto" ADD CHECK (id_categoria > 0);
ALTER TABLE "producto" ADD CHECK (id_marca > 0);
ALTER TABLE "producto" ADD CHECK (precio > (0)::double precision);
ALTER TABLE "producto" ADD CHECK (precio2 > (0)::double precision);
ALTER TABLE "producto" ADD CHECK ((stock_max > 0) AND (stock_max > stock_min));
ALTER TABLE "producto" ADD CHECK ((stock_min > 0) AND (stock_min < stock_max));
ALTER TABLE "producto" ADD CHECK ((tipos)::text = ANY (ARRAY[('interior'::character varying)::text, ('exterior'::character varying)::text]));
ALTER TABLE "producto" ADD CHECK (id > 0);
ALTER TABLE "producto" ADD CHECK (activo = ANY (ARRAY['s'::bpchar, 'n'::bpchar]));

-- ----------------------------
-- Primary Key structure for table producto
-- ----------------------------
ALTER TABLE "producto" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table proveedor
-- ----------------------------
ALTER TABLE "proveedor" ADD CHECK (avenida > 0);
ALTER TABLE "proveedor" ADD CHECK ((length(rtrim(ltrim((municipio)::text))) > 4) AND (rtrim(ltrim((municipio)::text)) = (municipio)::text));
ALTER TABLE "proveedor" ADD CHECK (activo = ANY (ARRAY['s'::bpchar, 'n'::bpchar]));
ALTER TABLE "proveedor" ADD CHECK ((length(rtrim(ltrim((telefono)::text))) = 13) AND (rtrim(ltrim((telefono)::text)) = (telefono)::text));
ALTER TABLE "proveedor" ADD CHECK (id > 0);
ALTER TABLE "proveedor" ADD CHECK ((length(rtrim(ltrim((nombre)::text))) > 2) AND (rtrim(ltrim((nombre)::text)) = (nombre)::text));
ALTER TABLE "proveedor" ADD CHECK ((length(rtrim(ltrim((apellido_paterno)::text))) > 2) AND (rtrim(ltrim((apellido_paterno)::text)) = (apellido_paterno)::text));
ALTER TABLE "proveedor" ADD CHECK ((length(rtrim(ltrim((apellido_materno)::text))) > 2) AND (rtrim(ltrim((apellido_materno)::text)) = (apellido_materno)::text));
ALTER TABLE "proveedor" ADD CHECK ((length(rtrim(ltrim((empresa)::text))) > 4) AND (rtrim(ltrim((empresa)::text)) = (empresa)::text));
ALTER TABLE "proveedor" ADD CHECK (calle > 0);
ALTER TABLE "proveedor" ADD CHECK (numero > 0);
ALTER TABLE "proveedor" ADD CHECK ((length(rtrim(ltrim((colonia)::text))) > 4) AND (rtrim(ltrim((colonia)::text)) = (colonia)::text));

-- ----------------------------
-- Primary Key structure for table proveedor
-- ----------------------------
ALTER TABLE "proveedor" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table venta
-- ----------------------------
ALTER TABLE "venta" ADD CHECK (id_cliente > 0);
ALTER TABLE "venta" ADD CHECK (id_vendedor > 0);
ALTER TABLE "venta" ADD CHECK (id > 0);
ALTER TABLE "venta" ADD CHECK (importe > (0)::double precision);

-- ----------------------------
-- Primary Key structure for table venta
-- ----------------------------
ALTER TABLE "venta" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "compra"
-- ----------------------------
ALTER TABLE "compra" ADD FOREIGN KEY ("id_proveedor") REFERENCES "proveedor" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "detallecompra"
-- ----------------------------
ALTER TABLE "detallecompra" ADD FOREIGN KEY ("id_proveedor") REFERENCES "proveedor" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "detallecompra" ADD FOREIGN KEY ("id_compra") REFERENCES "compra" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "detallecompra" ADD FOREIGN KEY ("id_producto") REFERENCES "producto" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "detalleventa"
-- ----------------------------
ALTER TABLE "detalleventa" ADD FOREIGN KEY ("id_producto") REFERENCES "producto" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "detalleventa" ADD FOREIGN KEY ("id_venta") REFERENCES "venta" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "producto"
-- ----------------------------
ALTER TABLE "producto" ADD FOREIGN KEY ("id_categoria") REFERENCES "categoria" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "producto" ADD FOREIGN KEY ("id_marca") REFERENCES "marca" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "venta"
-- ----------------------------
ALTER TABLE "venta" ADD FOREIGN KEY ("id_cliente") REFERENCES "cliente" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "venta" ADD FOREIGN KEY ("id_vendedor") REFERENCES "empleado" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;
