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
"referencia" varchar(255) COLLATE "default" NOT NULL
)
;
-- ----------------------------
-- Table structure for compra
-- ----------------------------
CREATE TABLE "compra" (
"id" int4 NOT NULL,
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
"causa" text  not null,
"existencia_actual" int4 not null,
"nueva_existencia" int4 not null,
"id_empleado" int4 not null,
"fecha_hora" timestamp default now() not null
)
;

ALTER TABLE "ajusteinventario" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Checks structure for table ajusteinventario
-- ----------------------------
alter table ajusteinventario add check(id > 0);
alter table ajusteinventario add check(id_producto > 0);
alter table ajusteinventario add check(length(rtrim(ltrim(causa))) > 5 and rtrim(ltrim(causa)) = causa);
alter table ajusteinventario add check(existencia_actual > 0);
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
returns table(fusuario character varying(25), fpassword character varying(32), ftipo character varying(8)) as $$
begin
return query SELECT usuario, password, tipo 
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

select fn_modificarmarca(1000, 'ALAN');
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
 password = fpassword, tipo = ftipo where id = fid;
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
colonia = fcolonia, municipio = fmunicipio, referencia = freferencia where id = id;
end
$$ language plpgsql; 
--*****************************************************************************************************************************************************
create or replace function fn_eliminarcliente(fid integer) 
returns void as $$
begin 
update cliente set activo = 'n';
end
$$ language plpgsql; 
--++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*****************************************************************************************************************************************************
create or replace function fn_agregarcompra(fid_proveedor integer, ftotal real) 
returns void as $$
declare maxid integer;
declare total integer;
begin 
select count(id) into total from compra;
if total = 0 then
maxid := 1;
else
select (max(id)+1) into maxid from compra;
end if;
insert into compra(id, id_proveedor, total,fecha_pedido, fecha_recepcion) values ( maxid, fid_proveedor,
ftotal,default, default);
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
insert into detallecompra(id, id_producto, id_compra, cantidad, precio_compra, precio_venta1,
precio_venta2) values ( maxid, fid_producto, fid_compra, fcantidad, fprecio_compra, fprecio_venta1,
fprecio_venta2);
update producto set precio1 = fprecio_venta1, precio2 = fprecio_venta2 where id = fid_producto;
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
insert into detalleventa(id, id_producto, precio, cantidad, id_venta) values ( maxid, fid_producto,
fprecio, fcantidad, fid_venta);
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
returns void as $$
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
returns setof cliente as $$
begin
return query SELECT id, nombre, apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio, referencia FROM cliente;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarultimocliente()()
returns setof cliente as $$
begin
return query SELECT id, nombre, apellido_paterno, apellido_materno, calle, avenida, numero, colonia, municipio, referencia FROM cliente 
order by id desc limit 1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarcompras()
returns table(id integer, empresa character varying(50), total real, fecha_pedido date, fecha_recepcion timestamp) as $$
begin
return query SELECT compra.id as id, proveedor.empresa as empresa, compra.total as total, compra.fecha_pedido as fecha_pedido, compra.fecha_recepcion as fecha_recepcion FROM compra JOIN proveedor ON proveedor.id = compra.id_proveedor;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************
create or replace function fn_seleccionarultimacompra()
returns table(id integer, empresa character varying(50), total real, fecha_pedido date, fecha_recepcion timestamp) as $$
begin
return query SELECT compra.id as id, proveedor.empresa as empresa, compra.total as total, compra.fecha_pedido as fecha_pedido, compra.fecha_recepcion as fecha_recepcion FROM compra JOIN proveedor ON proveedor.id = compra.id_proveedor ORDER BY id DESC LIMIT 1;
end
$$ language plpgsql;
--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
--*******************************************************************************************************************************************************

create or replace function fn_seleccionardetallecompras()
returns table(id integer, producto text, cantidad integer, precio_compra real,
total double precision, precio_venta1 real, precio_venta2 real) as $$
begin
return query SELECT detallecompra.id as id, concat(categoria.nombre,' ', producto.descripcion)
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
return query SELECT detalleventa.id as id,
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
 telefono, usuario, password, tipo FROM empleado WHERE activo = 's';
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
     inner join cliente on cliente.id = venta.id_cliente;
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
create or replace function fn_ajusteinventario(fid_producto integer, fcausa text, fexistencia_actual integer, fnueva_existencia integer, fid_empleado integer)
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
 values ( maxid, id_producto, causa, existencia_actual, nueva_existencia, id_empleado, now());
end
$$ language plpgsql;

--+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

-- ---------------------
-- -------datos---------
-- ---------------------

INSERT INTO "categoria" ("id", "nombre", "activo") VALUES ('1', 'teja', 's'),
('2', 'tabique', 's'),
('3', 'ladrillo', 's'),
('4', 'celosia', 's'), 
('5', 'ovalin', 's'), 
('6', 'lavadero', 's'), 
('7', 'solera', 's'), 
('8', 'adoquin', 's'), 
('9', 'maceta', 's');

INSERT INTO "marca" ("id","nombre","activo") VALUES ('1', 'Teja el aguila', 's'),
('2', 'Novaceramic', 's');

INSERT INTO "cliente" ("id", "nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "referencia") 
VALUES ('1', 'Martin', 'Martinez', 'Marin', '3', '3', '54', '5 de mayo', 'Cordoba', 'frente a casa de dos pisos azul.'), 
('2', 'Alvaro', 'Castillo', 'Gomez', '7', '2', '87', 'Francisco Zarco', 'Cordoba', 'en la casa de porton negro'), 
('3', 'Marcos', 'Carmona', 'Yepez', '1', '24', '92', 'El Barreal', 'Cordoba', 'contraesquina a a escuela'), 
('4', 'Leticia', 'Perez', 'Sanchez', '18', '21', '176', 'Lomas Pedregal', 'Cordoba', 'a un lado del parque'), 
('5', 'Oscar', 'Castillo', 'Merino', '9', '2', '65', 'Esperanza', 'Cordoba', 'a dos cuadras de la iglesia'), 
('6', 'Marina', 'Medina', 'Rojas', '2', '6', '12', 'Centro', 'Cordoba', 'frente a tienda "ilusion"'), 
('7', 'Gonzalo', 'Perez', 'Prado', '9', '7', '164', 'Solidaridad', 'Cordoba', 'Junto a casa verde con cafe'), 
('8', 'Alondra', 'Rodriguez', 'Rojas', '23', '12', '275', 'Castillo', 'Cordoba', 'adelante del puesto de verduras'), 
('9', 'Fernando', 'Arellano', 'Sierra', '12', '16', '294', 'Las Ruinas', 'Cordoba', 'En la casa que esta en construccion'), 
('10', 'Esteban', 'Flores', 'Pozos', '21', '32', '54', 'Nueva Esperanza', 'Cordoba', 'Contraesquina con el puesto de comida'), 
('11', 'Andrez', 'Martinez', 'Sanchez', '14', '31', '321', 'Pedregal', 'Cordoba', 'Frente a tienda de abarrotes'), 
('12', 'Alicia', 'Contreras', 'Olivares', '65', '13', '86', 'Nueva', 'Cordoba', 'Frente a casa verde'), 
('13', 'Martin', 'Castellano', 'Perez', '23', '43', '95', 'desconocida', 'Cordoba', 'casa con barda rosa'), 
('14', 'Carlos', 'Garcia', 'Rosas', '24', '54', '203', 'Alegria', 'Cordoba', 'casa azul con ventanas polarizadas'), 
('15', 'Maria', 'Castañeda', 'Pulido', '1', '4', '34', 'Centro', 'Cordoba', 'A un costado de la tienda de abarrotes'), 
('16', 'Martina', 'Murillo', 'Serrano', '3', '5', '57', 'Cerenidad', 'Cordoba', 'Frente a la casa beige');

INSERT INTO "empleado" ("id","nombre", "apellido_paterno", "apellido_materno", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "usuario", "password", "tipo", "activo") 
VALUES ('1', 'Alfredo', 'Flores', 'Hernandez', '2', '3', '34','Centro', 'Chocaman','271-234-12-12','alfredo1',md5('Alfredo1'),'empleado', 's'), 
('2', 'Angeles', 'Gonzalez', 'Blanco', '43', '119', '65', 'Fraternidad', 'Cordoba', '271-234-23-34', 'angeles1', md5('Angeles1'),'empleado', 's'), 
('3', 'Isaac', 'Sosa', 'Rincon', '23', '21', '87', 'Los Filtros', 'Cordoba', '271-234-25-23', 'isaac12', md5('Isaac12'),'empleado', 's'), 
('4', 'Alan', 'Ramirez', 'Garcia', '3', '4', '21', 'Guadalupe', 'Omealca', '271-234-23-23', 'alan111', md5('Alan111'),'admin', 's');

INSERT INTO "proveedor" ("id","nombre","apellido_paterno","apellido_materno", "empresa", "calle", "avenida", "numero", "colonia", "municipio", "telefono", "activo") 
VALUES ('1', 'Julio', 'Tejeda', 'Rodriguez', 'Materiales Tejeda', '2', '6', '23', 'Centro', 'Cordoba', '271-633-21-12', 's'),
('2', 'Marcos', 'Gamboa', 'Merino', 'Materiales Game', '1', '5', '85', 'Centro', 'Cordoba', '271-234-12-12', 's'), 
('3', 'Gerardo', 'Ortiz', 'Cano', 'Materiales Ortiz', '8', '35', '98', '5 de mayo', 'Cordoba', '271-456-23-23', 's'), 
('4', 'Alvaro', 'Castillo', 'Benitez', 'Materiales El Castillo', '34', '12', '285', 'Reforma', 'Cordoba', '271-645-12-12', 's'), 
('5', 'Hilario', 'Medina', 'Cano', 'Materiales El Constructor', '12', '2', '234', 'Siempre viva', 'Cordoba', '271-243-45-45', 's'), 
('6', 'Martin', 'Suarez', 'Castillo', 'Construccion En Grande', '36', '22', '439', 'Desconocida', 'Cordoba', '000-000-00-00', 's');

INSERT INTO "producto" ("id","id_categoria","descripcion","id_marca","precio1","precio2","stock","stock_max","stock_min","tipo","activo") 
VALUES ('1', '1', 'vidriada', '1', '5', '4', '300', '600', '50', 'exterior', 's'), 
('2', '1', 'vidriada', '1', '5', '4', '300', '600', '50', 'exterior', 's'), 
('3', '1', 'barro prensada', '1', '6', '5', '200', '500', '100', 'exterior', 's'), 
('4', '3', 'rojo', '1', '7', '6', '400', '500', '50', 'exterior', 's');

INSERT INTO "compra" ("id", "id_proveedor", "total", "fecha_pedido","fecha_recepcion") 
VALUES ('1', '2', '1000', '2015-05-28', '2015-05-29 18:19:20'), 
('2', '4', '500', '2015-05-28', '2015-05-29 18:19:49'), 
('3', '3', '2000', '2015-05-28', '2015-05-29 18:20:52'), 
('4', '5', '2500', '2015-05-28', '2015-05-29 18:21:14'), 
('5', '1', '1500', '2015-05-28', '2015-05-29 18:21:32'), 
('6', '2', '3000', '2015-05-28', '2015-05-29 18:21:46'),
('7', '4', '2300', '2015-05-28', '2015-05-29 18:23:40'), 
('8', '2', '2200', '2015-05-28', '2015-05-29 18:24:00'), 
('9', '4', '1100', '2015-05-28', '2015-05-29 18:24:26'), 
('10', '5', '1200', '2015-05-28', '2015-05-29 18:24:42');

INSERT INTO "detallecompra" ("id","id_producto","id_compra","cantidad","precio_compra", "total", "precio_venta1","precio_venta2") VALUES ('1', '1', '1', '100', '4','400','6','5'),
('2', '3', '10', '120', '6', '720','8','7'),
('3', '4', '6', '200', '14','2800 ','16','15'),
('4', '2', '8', '250', '12','3000','14','13'),
('5', '1', '3', '150', '4','600','6','5'),
('6', '2', '4', '120', '8','960','10','9'),
('7', '3', '7', '200', '6','1200','8','7'),
('8', '1', '9', '300', '12','3600','14','13'),
('9', '2', '2', '230', '9','2070','11','10'),
('10', '4', '5', '340', '8','2720','10','9');

INSERT INTO "venta" ("id","id_vendedor","id_cliente", "total" ,"fecha_hora") VALUES ('1', '2', '3', '1000','2015-05-29 18:44:21'),
('2', '1', '4', '1500','2015-05-29 18:44:40'),
('3', '3', '6', '2000','2015-05-29 18:45:42'),
('4', '4', '2', '3400','2015-05-29 18:46:01'),
('5', '3', '3', '4500','2015-05-29 18:46:26'),
('6', '2', '5', '1600','2015-05-29 18:46:54'), 
('7', '4', '1', '1000','2015-05-29 18:47:17'),
('8', '1', '2', '1900','2015-05-29 18:47:35'),
('9', '1', '3', '990','2015-05-29 18:47:53'),
('10', '2', '2', '2200','2015-05-29 18:48:16');

INSERT INTO "detalleventa" ("id","id_producto","precio","cantidad","total","id_venta") VALUES ('1', '3', '3', '200', '600', '1'),
('2', '2', '5', '400', '2000', '2'),
('3', '4', '8', '300', '2400', '3'),
('4', '3', '6', '100', '600', '4'),
('5', '1', '9', '100', '900','5'),
('6', '2', '11', '80', '880','6'),
('7', '4', '9', '100', '900', '7'),
('8', '3', '7', '100', '700', '8'),
('9', '1', '5', '180', '900', '9'),
('10', '2', '3', '200', '600','10');


