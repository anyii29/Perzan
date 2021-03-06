-- ----------------------------
-- Table structure for categoria
-- ----------------------------
CREATE TABLE "categoria" (
"id" int4 NOT NULL,
"nombre" varchar(50) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- Table structure for cliente
-- ----------------------------
CREATE TABLE "cliente" (
"id" int4 NOT NULL,
"nombre" varchar(30) COLLATE "default" NOT NULL,
"apellido_paterno" varchar(25) COLLATE "default" NOT NULL,
"apellido_materno" varchar(25) COLLATE "default" NOT NULL,
"calle" int4,
"avenida" int4,
"numero" int4,
"colonia" varchar(25) COLLATE "default" NOT NULL,
"municipio" varchar(25) COLLATE "default" NOT NULL,
"referencia" varchar(255) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- ----------------------------
-- Table structure for compra
-- ----------------------------
CREATE TABLE "compra" (
"id" int4 NOT NULL,
"id_empleado" int4 NOT NULL,
"id_proveedor" int4 NOT NULL,
"total" float4 NOT NULL,
"fecha_pedido" date DEFAULT now()  NOT NULL,
"fecha_recepcion" timestamp(6) DEFAULT now()  NOT NULL
)
;
-- ----------------------------
-- Table structure for detallecompra
-- ----------------------------
CREATE TABLE "detallecompra" (
"id" int4 NOT NULL,
"id_producto" int4 NOT NULL,
"id_compra" int4 NOT NULL,
"cantidad" int4 NOT NULL,
"precio_compra" float4 NOT NULL,
"total" float4 NOT NULL,
"precio_venta1" float4 NOT NULL,
"precio_venta2" float4 NOT NULL
)
;
-- ----------------------------
-- Table structure for detalleventa
-- ----------------------------
CREATE TABLE "detalleventa" (
"id" int4 NOT NULL,
"id_producto" int4 NOT NULL,
"precio" float4 NOT NULL,
"cantidad" int4 NOT NULL,
"total" float4 NOT NULL,
"id_venta" int4 NOT NULL
)
;----------------
-- Table structure for empleado
-- ----------------------------
CREATE TABLE "empleado" (
"id" int4 NOT NULL,
"nombre" varchar(50) COLLATE "default" NOT NULL,
"apellido_paterno" varchar(50) COLLATE "default" NOT NULL,
"apellido_materno" varchar(50) COLLATE "default" NOT NULL,
"calle" int4,
"avenida" int4,
"numero" int4,
"colonia" varchar(25) COLLATE "default" NOT NULL,
"municipio" varchar(25) COLLATE "default" NOT NULL,
"telefono" varchar(14) COLLATE "default" DEFAULT '000-000-00-00'::character varying NOT NULL,
"usuario" varchar(50) COLLATE "default" NOT NULL,
"password" varchar(50) COLLATE "default" NOT NULL,
"tipo" varchar(50) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- ----------------------------
-- Table structure for marca
-- ----------------------------
CREATE TABLE "marca" (
"id" int4 NOT NULL,
"nombre" varchar(50) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- ----------------------------
-- Table structure for producto
-- ----------------------------
CREATE TABLE "producto" (
"id" int4 NOT NULL,
"id_categoria" int4 NOT NULL,
"descripcion" varchar(25) COLLATE "default" NOT NULL,
"id_marca" int4 NOT NULL,
"precio1" float4 NOT NULL,
"precio2" float4 NOT NULL,
"stock" int4 NOT NULL,
"stock_max" int4 NOT NULL,
"stock_min" int4 NOT NULL,
"tipo" varchar(30) COLLATE "default" NOT NULL,
"activo" char(1) COLLATE "default" DEFAULT 's'::bpchar NOT NULL
)
;
-- ----------------------------
-- Table structure for proveedor
-- ----------------------------
CREATE TABLE "proveedor" (
"id" int4 NOT NULL,
"nombre" varchar(50) COLLATE "default" NOT NULL,
"apellido_paterno" varchar(50) COLLATE "default" NOT NULL,
"apellido_materno" varchar(50) COLLATE "default" NOT NULL,
"empresa" varchar(50) COLLATE "default" NOT NULL,
"calle" int4,
"avenida" int4,
"numero" int4,
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
"id" int4 NOT NULL,
"id_vendedor" int4 NOT NULL,
"id_cliente" int4 NOT NULL,
"total" float4 NOT NULL,
"fecha_hora" timestamp(6) DEFAULT now()  NOT NULL
)
;

CREATE TABLE "ajusteinventario" (
"id" int4 NOT NULL,
"id_producto" int4 not null,
"causa" character varying(255)  not null,
"existencia_actual" int4 not null,
"nueva_existencia" int4 not null,
"id_empleado" int4 not null,
"fecha_hora" timestamp default now() not null
)
;

create table fechahora(
id integer not null,
fechahora timestamp not null default now()
);

ALTER TABLE "ajusteinventario" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table ajusteinventario
-- ----------------------------
alter table ajusteinventario add check(id > 0);
alter table ajusteinventario add check(id_producto > 0);
alter table ajusteinventario add check(length(rtrim(ltrim(causa))) > 5 and rtrim(ltrim(causa)) = causa);
alter table ajusteinventario add check(existencia_actual >= 0);
alter table ajusteinventario add check(nueva_existencia >= 0);
alter table ajusteinventario add check(id_empleado > 0);

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
ALTER TABLE "cliente" ADD UNIQUE (nombre,apellido_paterno,apellido_materno,colonia);
-- ----------------------------
-- Primary Key structure for table cliente
-- ----------------------------
ALTER TABLE "cliente" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table compra
-- ----------------------------
ALTER TABLE "compra" ADD CHECK (id_empleado > 0);
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
ALTER TABLE "detallecompra" ADD CHECK (id_compra > 0);
ALTER TABLE "detallecompra" ADD CHECK (cantidad > 0);
ALTER TABLE "detallecompra" ADD CHECK (precio_compra > (0)::double precision);
ALTER TABLE "detallecompra" ADD CHECK (precio_venta1 > (0)::double precision);
ALTER TABLE "detallecompra" ADD CHECK (precio_venta2 > (0)::double precision);
ALTER TABLE "detallecompra" ADD CHECK (precio_venta1 > precio_venta2);




-- ----------------------------
-- Primary Key structure for table detallecompra
-- ----------------------------
ALTER TABLE "detallecompra" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table detalleventa
-- ----------------------------
ALTER TABLE "detalleventa" ADD CHECK (id > 0);
ALTER TABLE "detalleventa" ADD CHECK (id_producto > 0);
ALTER TABLE "detalleventa" ADD CHECK (precio > (0)::double precision);
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
ALTER TABLE "empleado" ADD UNIQUE ("usuario","tipo");
alter table empleado 
add unique (usuario);

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
ALTER TABLE "empleado" ADD CHECK ((tipo)::text = ANY (ARRAY[('admin'::character varying)::text, ('empleado'::character varying)::text]));
ALTER TABLE "empleado" ADD CHECK (calle > 0);
ALTER TABLE "empleado" ADD CHECK (avenida > 0);

-- ----------------------------
-- Primary Key structure for table empleado
-- ----------------------------
ALTER TABLE "empleado" ADD PRIMARY KEY ("id");
alter table empleado add unique (usuario);


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
ALTER TABLE "producto" ADD CHECK ((length(rtrim(ltrim((descripcion)::text))) > 3) AND (rtrim(ltrim((descripcion)::text)) = (descripcion)::text));
ALTER TABLE "producto" ADD CHECK (id_categoria > 0);
ALTER TABLE "producto" ADD CHECK (id_marca > 0);
ALTER TABLE "producto" ADD CHECK (precio1 > (0)::double precision);
ALTER TABLE "producto" ADD CHECK (precio2 > (0)::double precision);
ALTER TABLE "producto" ADD CHECK ((stock_max > 0) AND (stock_max > stock_min));
ALTER TABLE "producto" ADD CHECK ((stock_min > 0) AND (stock_min < stock_max));
ALTER TABLE "producto" ADD CHECK ((tipo)::text = ANY (ARRAY[('interior'::character varying)::text, ('exterior'::character varying)::text]));
ALTER TABLE "producto" ADD CHECK (id > 0);
ALTER TABLE "producto" ADD CHECK (activo = ANY (ARRAY['s'::bpchar, 'n'::bpchar]));

ALTER TABLE "producto" ADD UNIQUE (id_categoria,descripcion);

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
ALTER TABLE "proveedor" ADD UNIQUE (empresa);

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

-- ----------------------------
-- Primary Key structure for table venta
-- ----------------------------
ALTER TABLE "venta" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "compra"
-- ----------------------------
ALTER TABLE "compra" ADD FOREIGN KEY ("id_empleado") REFERENCES "empleado" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "compra" ADD FOREIGN KEY ("id_proveedor") REFERENCES "proveedor" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- ----------------------------
-- Foreign Key structure for table "detallecompra"
-- ----------------------------
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



ALTER TABLE "ajusteinventario" ADD FOREIGN KEY ("id_producto") REFERENCES "producto" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "ajusteinventario" ADD FOREIGN KEY ("id_empleado") REFERENCES "empleado" ("id") ON DELETE RESTRICT ON UPDATE CASCADE;

---------------------------
----funciones---------------
---------------------------

--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_login(fnusuario character varying(25), fnpassword character varying(32),fntipo character varying(8))
returns table(fusuario character varying(25), fpassword character varying(32), ftipo character varying(8), fnombre text, fid integer) as $$
begin
return query SELECT usuario, password, tipo, concat(nombre,' ',apellido_paterno,' ',apellido_materno), id 
from empleado where usuario = fnusuario and password = fnpassword and (tipo = 'admin' or tipo = fntipo) and activo = 's';
end
$$ language plpgsql;
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_seleccionarcategoria()
returns table(fid integer, fnombre character varying(25)) as $$
begin
return query SELECT id, nombre FROM categoria where activo = 's';
end
$$ language plpgsql;
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_seleccionarmarca()
returns table(fid integer, fnombre character varying(25)) as $$
begin
return query SELECT id, nombre FROM marca where activo = 's';
end
$$ language plpgsql;
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregarcategoria(nombre character varying(50))
returns void as $$ 
declare maxid integer;
declare total integer;
begin 
select count(id) into total from categoria;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from categoria;
end if;
insert into categoria(id, nombre) values ( maxid, nombre);
end
$$ language plpgsql;
--*****************************************************************************************************************************************************
create or replace function fn_modificarcategoria(fid integer, fnombre character varying(25))
returns void as $$
begin
UPDATE categoria set id = fid , nombre = fnombre WHERE id = fid;
end
$$ language plpgsql;
--*****************************************************************************************************************************************************
create or replace function fn_eliminarcategoria(fid integer)
returns void as $$
begin
UPDATE categoria SET activo = 'n' WHERE id = fid;
end
$$ language plpgsql; 
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregarmarca(nombre character varying(50))
returns void as $$ 
declare maxid integer;
declare total integer;
begin 
select count(id) into total from marca;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from marca;
end if;
insert into marca(id, nombre) values ( maxid, nombre);
end
$$ language plpgsql; 
--*****************************************************************************************************************************************************
create or replace function fn_modificarmarca(fid integer, fnombre character varying(25))
returns void as $$
begin
UPDATE marca set id = fid , nombre = fnombre WHERE id = fid;
end
$$ language plpgsql; 
--*****************************************************************************************************************************************************
create or replace function fn_eliminarmarca(fid integer)
returns void as $$
begin
UPDATE marca SET activo = 'n' WHERE id = fid;
end
$$ language plpgsql; 
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregarempleado(fnombre character varying(50), fapellido_paterno character varying(50),
fapellido_materno character varying(50), fcalle integer, favenida integer, fnumero integer,
fcolonia character varying(50), fmunicipio character varying(50), ftelefono character varying(13),
fusuario character varying(50), fpassword character varying(50), ftipo character varying(8)) 
returns void as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from empleado;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from empleado;
end if;
insert into empleado(id, nombre, apellido_paterno, apellido_materno,calle, avenida, numero, 
colonia, municipio, telefono, usuario, password, tipo) values ( maxid, fnombre, fapellido_paterno,
fapellido_materno, fcalle, favenida, fnumero, fcolonia, fmunicipio, ftelefono, fusuario, md5(fpassword),
ftipo);
end
$$ language plpgsql; 

--*******************************************************************************************************************************************************

create or replace function fn_modificarempleado(fid integer, fnombre character varying(50), fapellido_paterno character varying(50),
fapellido_materno character varying(50), fcalle integer, favenida integer, fnumero integer,
fcolonia character varying(50), fmunicipio character varying(50), ftelefono character varying(13),
fusuario character varying(50), fpassword character varying(50), ftipo character varying(8)) 
returns void as $$
begin 
update empleado set id = fid, nombre = fnombre, apellido_paterno = fapellido_paterno, 
apellido_materno = fapellido_materno, calle = fcalle, avenida = favenida, numero = numero, 
colonia = fcolonia, municipio = fmunicipio, telefono = ftelefono, usuario = fusuario,
 password = md5(fpassword), tipo = ftipo where id = fid;
end
$$ language plpgsql; 

--*******************************************************************************************************************************************************
create or replace function fn_eliminarempleado(fid integer) 
returns void as $$
begin 
update empleado set activo = 'n' where id = fid ;
end
$$ language plpgsql; 
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_agregarcliente(fnombre character varying(50), fapellido_paterno character varying(50),
fapellido_materno character varying(50), fcalle integer, favenida integer, fnumero integer,
fcolonia character varying(50), fmunicipio character varying(50), freferencia character varying(255)) 
returns void as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from cliente;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from cliente;
end if;
insert into cliente(id, nombre, apellido_paterno, apellido_materno,calle, avenida, numero, 
colonia, municipio, referencia) values ( maxid, fnombre, fapellido_paterno,
fapellido_materno, fcalle, favenida, fnumero, fcolonia, fmunicipio, freferencia);
end
$$ language plpgsql; 

--*****************************************************************************************************************************************************
create or replace function fn_modificarcliente(fid integer, fnombre character varying(50), fapellido_paterno character varying(50),
fapellido_materno character varying(50), fcalle integer, favenida integer, fnumero integer,
fcolonia character varying(50), fmunicipio character varying(50), freferencia character varying(255)) 
returns void as $$
begin 
update cliente set id = fid, nombre = fnombre, apellido_paterno = fapellido_paterno,
 apellido_materno = fapellido_materno, calle = fcalle, avenida = favenida, numero =  fnumero, 
colonia = fcolonia, municipio = fmunicipio, referencia = freferencia where id = fid;
end
$$ language plpgsql; 
--*****************************************************************************************************************************************************
create or replace function fn_eliminarcliente(fid integer) 
returns void as $$
begin 
update cliente set activo = 'n' where id = fid;
end
$$ language plpgsql; 
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregarcompra(fid_empleado integer, fid_proveedor integer, ftotal real) 
returns integer as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from compra;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from compra;
end if;
insert into compra(id, id_empleado, id_proveedor, total,fecha_pedido, fecha_recepcion) values ( maxid, fid_empleado, fid_proveedor,
ftotal,default, default);
return maxid;
end
$$ language plpgsql;
--*****************************************************************************************************************************************************
create or replace function fn_modificarcompra(fid integer)
returns void as $$ 
begin 
update compra set fecha_recepcion = now() where id = fid;
end
$$ language plpgsql; 
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregardetallecompra(fid_producto integer,fid_compra integer,
fcantidad integer, fprecio_compra real, fprecio_venta1 real, fprecio_venta2 real) 
returns void as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from detallecompra;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from detallecompra;
end if;
insert into detallecompra(id, id_producto, id_compra, cantidad, precio_compra, total, precio_venta1,
precio_venta2) values ( maxid, fid_producto, fid_compra, fcantidad, fprecio_compra, fcantidad * fprecio_compra, fprecio_venta1,
fprecio_venta2);
end
$$ language plpgsql; 
--*****************************************************************************************************************************************************
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregardetalleventa(fid_producto integer,fprecio real,
fcantidad integer, fid_venta integer) 
returns void as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from detalleventa;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from detalleventa;
end if;
insert into detalleventa(id, id_producto, precio, cantidad, total, id_venta) values ( maxid, fid_producto,
fprecio, fcantidad, fprecio * fcantidad, fid_venta);
end
$$ language plpgsql; 
--*****************************************************************************************************************************************************
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregarproveedor(fnombre character varying(50), fapellido_paterno character varying(50),
fapellido_materno character varying(50), fempresa character varying(50), fcalle integer, favenida integer, fnumero integer,
fcolonia character varying(50), fmunicipio character varying(50), ftelefono character varying(13)) 
returns void as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from proveedor;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from proveedor;
end if;
insert into proveedor(id, nombre, apellido_paterno, apellido_materno,empresa, calle, avenida, numero, 
colonia, municipio, telefono) values ( maxid, fnombre, fapellido_paterno,
fapellido_materno, fempresa, fcalle, favenida, fnumero, fcolonia, fmunicipio, ftelefono);
end
$$ language plpgsql ;

--*******************************************************************************************************************************************************

create or replace function fn_modificaproveedor(fid integer, fnombre character varying(50), fapellido_paterno character varying(50),
fapellido_materno character varying(50), fempresa character varying(50), fcalle integer, favenida integer, fnumero integer,
fcolonia character varying(50), fmunicipio character varying(50), ftelefono character varying(13)) 
returns void as $$
begin 
update proveedor set id = fid, nombre = fnombre, apellido_paterno = fapellido_paterno, 
apellido_materno = fapellido_materno, empresa = fempresa, calle = fcalle, avenida = favenida, numero = numero, 
colonia = fcolonia, municipio = fmunicipio, telefono = ftelefono where id = fid;
end
$$ language plpgsql ;


--*******************************************************************************************************************************************************

CREATE OR REPLACE FUNCTION "fn_modificaradminpassword"(fadmin varchar, fpassword varchar)
  RETURNS "pg_catalog"."void" AS $BODY$
begin
UPDATE empleado SET usuario = fadmin, password = fpassword where password = fpassword;
end
$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
;

--*******************************************************************************************************************************************************
create or replace function fn_eliminaproveedor(fid integer) 
returns void as $$
begin 
update proveedor set activo = 'n' where id = fid ;
end
$$ language plpgsql ;
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregarproducto(fid_categoria integer, fdescripcion character varying(25),
fid_marca integer, fprecio1 real, fprecio2 real, fstock integer, fstock_max integer, fstock_min integer,
ftipo character varying(8)) 
returns void as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from producto;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from producto;
end if;
insert into producto(id, id_categoria, descripcion, id_marca, precio1, precio2, stock, stock_max,
stock_min, tipo) values ( maxid, fid_categoria, fdescripcion, fid_marca, fprecio1, fprecio2, fstock, 
fstock_max, fstock_min, ftipo);
end
$$ language plpgsql ;

--*******************************************************************************************************************************************************

create or replace function fn_modificaproducto(fid integer, fid_categoria integer, fdescripcion character varying(25),
fid_marca integer, fprecio1 real, fprecio2 real, fstock integer, fstock_max integer, fstock_min integer,
ftipo character varying(8)) 
returns void as $$
begin 
update producto set id = fid, id_categoria = fid_categoria, descripcion = fdescripcion,
id_marca = fid_marca, precio1 = fprecio1, precio2 = fprecio2, stock = fstock, stock_max = fstock_max,
stock_min = fstock_min, tipo = ftipo where id = fid;
end
$$ language plpgsql ;

--*******************************************************************************************************************************************************
create or replace function fn_eliminaproducto(fid integer) 
returns void as $$
begin 
update producto set activo = 'n' where id = fid ;
end
$$ language plpgsql ;
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregarventa(fid_vendedor integer, fid_cliente integer, ftotal real) 
returns integer as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from venta;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from venta;
end if;
insert into venta(id, id_vendedor, id_cliente, total, fecha_hora) values ( maxid, fid_vendedor, fid_cliente, ftotal, 
now());
return maxid;
end
$$ language plpgsql ;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_agregarventaproducto(fid integer, fstock integer)
returns void as $$
begin
UPDATE producto SET stock=(stock - fstock) WHERE  id=fid;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_adminpassword(fpassword character varying(32))
returns character varying(32) as $$
declare pass character varying(32);
begin
select password into pass from empleado where password = fpassword and tipo = 'admin';
return pass;
end
$$ language plpgsql;

create or replace function fn_modificarpassword(fpassword character varying(32))
returns void as $$
begin
UPDATE empleado SET password = fpassword where password = fpassword and tipo = 'admin';
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
 create or replace function fn_seleccionarclientes()
returns table(fid integer, fnombre character varying(30), fapellido_paterno character varying(30), fapellido_materno character varying(30),
 fcalle integer, favenida integer, fnumero integer, fcolonia character varying(50), fmunicipio character varying(30), freferencia character varying(255))
 as $$
begin
return query SELECT id, nombre, apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio, referencia FROM cliente 
where activo = 's';
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarultimocliente()
returns table(fid integer, fnombre character varying(30), fapellido_paterno character varying(30), fapellido_materno character varying(30),
 fcalle integer, favenida integer, fnumero integer, fcolonia character varying(50), fmunicipio character varying(30), freferencia character varying(255))
 as $$
begin
return query SELECT id, nombre, apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio, referencia FROM cliente 
order by id desc limit 1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarcompras()
returns table(id integer, empresa character varying(50),empleado text, total real, fecha_pedido date, fecha_recepcion timestamp) as $$
begin
return query SELECT compra.id as id, proveedor.empresa as empresa,concat(empleado.nombre,' ', empleado.apellido_paterno,' ',empleado.apellido_materno) as empleado,
compra.total as total, compra.fecha_pedido as fecha_pedido,
 compra.fecha_recepcion as fecha_recepcion FROM compra inner JOIN proveedor ON proveedor.id = compra.id_proveedor
 inner join empleado on empleado.id = compra.id_empleado order by compra.fecha_pedido desc;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarultimacompra()
returns table(id integer, empresa character varying(50), empleado text, total real, fecha_pedido date, fecha_recepcion timestamp) as $$
begin
return query SELECT compra.id as id, proveedor.empresa as empresa,concat(empleado.nombre,' ', empleado.apellido_paterno,' ',empleado.apellido_materno) as empleado,
compra.total as total, compra.fecha_pedido as fecha_pedido,
 compra.fecha_recepcion as fecha_recepcion FROM compra inner JOIN proveedor ON proveedor.id = compra.id_proveedor
 inner join empleado on empleado.id = compra.id_empleado ORDER BY id DESC LIMIT 1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************

create or replace function fn_seleccionardetallecompras()
returns table(id integer, producto text, cantidad integer, precio_compra real,
total double precision, precio_venta1 real, precio_venta2 real) as $$
begin
return query SELECT detallecompra.id_compra as id, concat(categoria.nombre,' ', producto.descripcion)
 as producto, detallecompra.cantidad as cantidad, detallecompra.precio_compra as precio_compra,
 detallecompra.cantidad * detallecompra.precio_compra AS total,
  detallecompra.precio_venta1 as precio_venta1, detallecompra.precio_venta2 as precio_venta2
   FROM detallecompra
    inner JOIN producto ON producto.id = detallecompra.id_producto
     inner JOIN categoria ON categoria.id = producto.id_categoria;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionardetalleventas()
returns table(id integer,producto text,precio real, cantidad integer, total double precision) as $$
begin
return query SELECT detalleventa.id_venta as id,
concat(categoria.nombre, ' ', producto.descripcion) as producto,
 detalleventa.precio as precio,    detalleventa.cantidad as cantidad,
  detalleventa.precio * detalleventa.cantidad AS total FROM detalleventa
   JOIN producto ON producto.id = detalleventa.id_producto
    JOIN categoria ON categoria.id = producto.id_categoria;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarempleados()
returns table( fid integer,fnombre character varying(25), fapellido_paterno character varying(30),
fapellido_materno character varying(30), fcalle integer, favenida integer, fnumero integer,
fcolonia character varying(30), fmunicipio character varying(30),ftelefono character varying(13),
fusuario character varying(20), fpassword character varying(32), ftipo character varying(8)) as $$
begin
return query SELECT id, nombre,
 apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio,
 telefono, usuario, password, tipo FROM empleado WHERE activo = 's' and tipo = 'empleado';
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarultimoempleado()
returns table( fid integer,fnombre character varying(25), fapellido_paterno character varying(30),
fapellido_materno character varying(30), fcalle integer, favenida integer, fnumero integer,
fcolonia character varying(30), fmunicipio character varying(30),ftelefono character varying(13),
fusuario character varying(20), fpassword character varying(32), ftipo character varying(8)) as $$
begin
return query SELECT id, nombre,
 apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio,
  telefono, usuario, password, tipo FROM empleado WHERE activo = 's' order by id desc limit  1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- ----------------------------
-- Function structure for "fn_seleccionarexistencia"
-- ----------------------------
CREATE OR REPLACE FUNCTION "fn_seleccionarexistencia"(IN fid int4)
  RETURNS SETOF "pg_catalog"."record" AS $BODY$
begin 
return query select concat(categoria.nombre,' ',producto,descripcion) as descripcion, producto.stock from producto
inner join categoria on producto.id_categoria = categoria
where producto.id = fid;
end
$BODY$
  LANGUAGE 'plpgsql' VOLATILE COST 100
 ROWS 1000
;
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarproductos()
returns table( fid integer,fid_categoria integer, fcategoria character varying(25), fdescripcion character varying(25),
fid_marca integer, fmarca character varying(20), fprecio1 real, fprecio2 real, fstock integer, fstock_max integer,
fstock_min integer, ftipo character varying(8)) as $$
begin
return query SELECT producto.id,
 categoria.id as id_categoria, categoria.nombre AS categoria,
  producto.descripcion, marca.id as id_marca, marca.nombre AS marca,
   producto.precio1, producto.precio2, producto.stock, producto.stock_max, 
    producto.stock_min, producto.tipo FROM producto
     inner JOIN categoria ON categoria.id = producto.id_categoria
      inner JOIN marca ON marca.id = producto.id_marca where producto.activo = 's';
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarultimoproducto()
returns table( fid integer,fid_categoria integer, fcategoria character varying(25), fdescripcion character varying(25),
fid_marca integer, fmarca character varying(20), fprecio1 real, fprecio2 real, fstock integer, fstock_max integer,
fstock_min integer, ftipo character varying(8)) as $$
begin
return query SELECT producto.id,
 categoria.id as id_categoria, categoria.nombre AS categoria,
  producto.descripcion, marca.id as id_marca, marca.nombre AS marca,
   producto.precio1, producto.precio2, producto.stock, producto.stock_max,
    producto.stock_min, producto.tipo FROM producto
     inner JOIN categoria ON categoria.id = producto.id_categoria
      inner JOIN marca ON marca.id = producto.id_marca WHERE producto.activo = 's' ORDER BY id DESC LIMIT 1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarproducto(fnid integer)
returns table( fid integer,fid_categoria integer, fcategoria character varying(25), fdescripcion character varying(25),
fid_marca integer, fmarca character varying(20), fprecio1 real, fprecio2 real, fstock integer, fstock_max integer,
fstock_min integer, ftipo character varying(8)) as $$
begin
return query SELECT producto.id,
 categoria.id as id_categoria, categoria.nombre AS categoria,
  producto.descripcion, marca.id as id_marca, marca.nombre AS marca,
   producto.precio1, producto.precio2, producto.stock, producto.stock_max,
    producto.stock_min, producto.tipo FROM producto
     inner JOIN categoria ON categoria.id = producto.id_categoria
      inner JOIN marca ON marca.id = producto.id_marca WHERE producto.activo = 's' and producto.id = fnid 
      ORDER BY id DESC LIMIT 1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarproveedor()
returns table( fid integer,fnombre character varying(25),fapellido_paterno character varying(25), fapellido_materno character varying(25),
 fempresa character varying(25), fcalle integer, favenida integer, fnumero integer, fcolonia  character varying(25),
 fmunicipio  character varying(25), ftelefono  character varying(13) ) as $$
begin
return query SELECT id, nombre, apellido_paterno, apellido_materno, empresa, calle,
avenida, numero, colonia, municipio, telefono FROM proveedor 
where activo = 's' ORDER BY id DESC LIMIT 1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarproveedores()
returns table( fid integer,fnombre character varying(25),fapellido_paterno character varying(25), fapellido_materno character varying(25),
 fempresa character varying(25), fcalle integer, favenida integer, fnumero integer, fcolonia  character varying(25),
 fmunicipio  character varying(25), ftelefono  character varying(13) ) as $$
begin
return query SELECT id, nombre, apellido_paterno, apellido_materno, empresa, calle, 
avenida, numero, colonia, municipio, telefono FROM proveedor where activo = 's';
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarventas()
returns table( fid integer,fvendedor text,fcliente text, ftotal real, fecha_hora timestamp) as $$
begin
return query select venta.id as id,
 concat(empleado.nombre, ' ', empleado.apellido_paterno, ' ', empleado.apellido_materno) as vendedor,
  concat(cliente.nombre,' ', cliente.apellido_paterno, ' ',cliente.apellido_materno) as cliente,
  venta.total as total,
   venta.fecha_hora as fecha_hora from venta
    inner join empleado on empleado.id = venta.id_vendedor
     inner join cliente on cliente.id = venta.id_cliente order by venta.fecha_hora desc;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarultimaventa()
returns table( fid integer,fvendedor text,fcliente text, ftotal real, fecha_hora timestamp) as $$
begin
return query select venta.id as id,
 concat(empleado.nombre, ' ', empleado.apellido_paterno, ' ', empleado.apellido_materno) as vendedor,
  concat(cliente.nombre,' ', cliente.apellido_paterno, ' ',cliente.apellido_materno) as cliente,
  venta.total as total,
   venta.fecha_hora as fecha_hora from venta
    inner join empleado on empleado.id = venta.id_vendedor
     inner join cliente on cliente.id = venta.id_cliente ORDER BY id DESC LIMIT 1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_ajusteinventario(fid_producto integer, fcausa character varying(255), fexistencia_actual integer, fnueva_existencia integer, fid_empleado integer)
returns void as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from ajusteinventario;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from ajusteinventario;
end if;
insert into ajusteinventario(id, id_producto, causa, existencia_actual, nueva_existencia, id_empleado, fecha_hora)
 values ( maxid, fid_producto, fcausa, fexistencia_actual, fnueva_existencia, fid_empleado, now());
end
$$ language plpgsql;

create or replace function fn_fechahorasistema()
returns boolean as $$
declare res boolean;
begin 
select fechahora < now() into res from fechahora;
return res;
end
$$ language plpgsql;

create or replace function fn_seleccionardetcompras()
returns table(id integer, empresa character varying(50), empleado text, producto text, cantidad integer, precio real, total real, fecha_pedido date, fecha_recepcion timestamp) as $$
begin
return query select compra.id,proveedor.empresa,concat(empleado.nombre,' ',empleado.apellido_paterno,' ',empleado.apellido_materno), concat(categoria.nombre,' ',producto.descripcion),
detallecompra.cantidad, detallecompra.precio_compra, detallecompra.total,compra.fecha_pedido, compra.fecha_recepcion from compra
inner join detallecompra on compra.id = detallecompra.id_compra
inner join proveedor on proveedor.id = compra.id_proveedor
inner join empleado on empleado.id = compra.id_empleado
inner join producto on producto.id = detallecompra.id_producto
inner join categoria on producto.id_categoria = categoria.id order by compra.fecha_pedido desc;
end
$$ language plpgsql;

create or replace function fn_seleccionardetventas()
returns table(id integer, empleado text, cliente text, producto text, cantidad integer, precio real, total real, fecha_hora timestamp) as $$
begin
return query select venta.id, concat(empleado.nombre,' ',empleado.apellido_paterno,' ',empleado.apellido_materno), concat(cliente.nombre,' ',cliente.apellido_paterno,' ',cliente.apellido_materno),
concat(categoria.nombre,' ',producto.descripcion), detalleventa.cantidad, detalleventa.precio, detalleventa.total, venta.fecha_hora from venta
inner join detalleventa on detalleventa.id_venta = venta.id
inner join producto on producto.id = detalleventa.id_producto
inner join categoria on categoria.id = producto.id_categoria
inner join empleado on empleado.id = venta.id_vendedor
inner join cliente on cliente.id = venta.id_cliente order by venta.fecha_hora desc;
end
$$ language plpgsql;

create or replace function fn_seleccionarhistorialstock()
returns table(id_producto integer, causa character varying(200), usuario character varying(30), stock integer,fecha_hora timestamp) as $$
begin
return query select ajusteinventario.id_producto,ajusteinventario.causa, empleado.usuario, ajusteinventario.nueva_existencia,ajusteinventario.fecha_hora
		from ajusteinventario 
		inner join empleado on ajusteinventario.id_empleado = empleado.id;
end
$$ language plpgsql;
--===============================================================================================================================================================

CREATE OR REPLACE FUNCTION fn_seleccionareliminadocategoria()
  RETURNS TABLE(fid integer, fnombre character varying) AS
$$
begin
return query SELECT id, nombre FROM categoria where activo = 'n';
end
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_modificareliminadocategoria(fid integer)
  RETURNS void AS $$ 
begin 
update categoria set activo = 's' where id = fid;
end
$$ LANGUAGE plpgsql;
--===============================================================================================================================================================
CREATE OR REPLACE FUNCTION fn_seleccionareliminadocliente()
  RETURNS TABLE(fid integer, fnombre character varying, fapellido_paterno character varying, fapellido_materno character varying, fcalle integer, favenida integer, fnumero integer, fcolonia character varying, fmunicipio character varying, freferencia character varying) AS
$$
begin
return query SELECT id, nombre, apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio, referencia FROM cliente 
where activo = 'n';
end
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION fn_modificareliminadocliente(fid integer)
  RETURNS void AS $$ 
begin 
update cliente set activo = 's' where id = fid;
end
$$ LANGUAGE plpgsql;
--===============================================================================================================================================================
CREATE OR REPLACE FUNCTION fn_seleccionareliminadoempleado()
  RETURNS TABLE(fid integer, fnombre character varying, fapellido_paterno character varying, fapellido_materno character varying, fcalle integer, favenida integer, fnumero integer, fcolonia character varying, fmunicipio character varying, ftelefono character varying, fusuario character varying, fpassword character varying, ftipo character varying) AS
$$
begin
return query SELECT id, nombre,
 apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio,
 telefono, usuario, password, tipo FROM empleado WHERE activo = 'n';
end
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION fn_modificareliminadoempleado(fid integer)
  RETURNS void AS $$ 
begin 
update empleado set activo = 's' where id = fid;
end
$$ LANGUAGE plpgsql;
--===============================================================================================================================================================
CREATE OR REPLACE FUNCTION fn_seleccionareliminadomarca()
 RETURNS TABLE(fid integer, fnombre character varying) AS
$$
begin
return query SELECT id, nombre FROM marca where activo = 'n';
end
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION fn_modificareliminadomarca(fid integer)
  RETURNS void AS $$ 
begin 
update marca set activo = 's' where id = fid;
end
$$ LANGUAGE plpgsql;
--===============================================================================================================================================================
CREATE OR REPLACE FUNCTION fn_seleccionareliminadoproducto()
  RETURNS TABLE(fid integer, fid_categoria integer, fcategoria character varying, fdescripcion character varying, fid_marca integer, fmarca character varying, fprecio1 real, fprecio2 real, fstock integer, fstock_max integer, fstock_min integer, ftipo character varying) AS
$$
begin
return query SELECT producto.id,
 categoria.id as id_categoria, categoria.nombre AS categoria,
  producto.descripcion, marca.id as id_marca, marca.nombre AS marca,
   producto.precio1, producto.precio2, producto.stock, producto.stock_max, 
    producto.stock_min, producto.tipo FROM producto
     inner JOIN categoria ON categoria.id = producto.id_categoria
      inner JOIN marca ON marca.id = producto.id_marca where producto.activo = 'n';
end
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION fn_modificareliminadoproducto(fid integer)
  RETURNS void AS $$ 
begin 
update producto set activo = 's' where id = fid;
end
$$ LANGUAGE plpgsql;
--===============================================================================================================================================================
CREATE OR REPLACE FUNCTION fn_seleccionareliminadoproveedor()
   RETURNS TABLE(fid integer, fnombre character varying, fapellido_paterno character varying, fapellido_materno character varying, fempresa character varying, fcalle integer, favenida integer, fnumero integer, fcolonia character varying, fmunicipio character varying, ftelefono character varying) AS
$$
begin
return query SELECT id, nombre, apellido_paterno, apellido_materno, empresa, calle, 
avenida, numero, colonia, municipio, telefono FROM proveedor where activo = 'n';
end
$$ LANGUAGE plpgsql;
CREATE OR REPLACE FUNCTION fn_modificareliminadoproveedor(fid integer)
  RETURNS void AS $$ 
begin 
update proveedor set activo = 's' where id = fid;
end
$$ LANGUAGE plpgsql;
--===============================================================================================================================================================
--===============================================================================================================================================================
--===============================================================================================================================================================


--===============================================================================================================================================================

--triggers

CREATE OR REPLACE FUNCTION fn_nuevoinventario()
RETURNS TRIGGER AS $$
 BEGIN IF (TG_OP = 'INSERT')
  THEN Update producto set stock = new.nueva_existencia
  where id = new.id_producto;
   END IF;
    RETURN NULL;END;
      $$ LANGUAGE  plpgsql;

CREATE TRIGGER tg_nuevoinventario AFTER INSERT
ON ajusteinventario
FOR EACH ROW
EXECUTE PROCEDURE fn_nuevoinventario();

CREATE OR REPLACE FUNCTION fn_decrementoStock()
RETURNS TRIGGER AS $$
 BEGIN IF (TG_OP = 'INSERT')
  THEN Update producto set stock = stock - new.cantidad
  where id = new.id_producto;
   END IF;
    RETURN NULL;END;
      $$ LANGUAGE  plpgsql;
      
CREATE TRIGGER tg_decrementostock AFTER INSERT
ON detalleventa
FOR EACH ROW
EXECUTE PROCEDURE fn_decrementostock();

CREATE OR REPLACE FUNCTION fn_incrementoStock()
RETURNS TRIGGER AS $$
 BEGIN IF (TG_OP = 'INSERT')
  THEN Update producto set stock = stock + new.cantidad, precio1 = new.precio_venta1,
  precio2 = new.precio_venta2
  where id = new.id_producto;
   END IF;
    RETURN NULL;END;
      $$ LANGUAGE  plpgsql;
      
CREATE TRIGGER tg_incrementostock AFTER INSERT
ON detallecompra
FOR EACH ROW
EXECUTE PROCEDURE fn_incrementostock();

CREATE OR REPLACE FUNCTION fn_fechahora()
RETURNS TRIGGER AS $$
 BEGIN IF (TG_OP = 'INSERT')
  THEN Update fechahora set fechahora = now()
  where id = 1;
   END IF;
   IF (TG_OP = 'UPDATE')
  THEN Update fechahora set fechahora = now()
  where id = 1;
   END IF;
   IF (TG_OP = 'DELETE')
  THEN Update fechahora set fechahora = now()
  where id = 1;
   END IF;
    RETURN NULL;END;
      $$ LANGUAGE  plpgsql;
      
      
CREATE  TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON ajusteinventario
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON categoria
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON cliente
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON compra
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON detallecompra
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON detalleventa
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON empleado
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON marca
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON producto
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON proveedor
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();

CREATE TRIGGER tg_fechahora AFTER INSERT OR UPDATE OR DELETE
ON venta
FOR EACH ROW
EXECUTE PROCEDURE fn_fechahora();