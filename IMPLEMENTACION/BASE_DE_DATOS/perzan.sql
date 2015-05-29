/*
Navicat MySQL Data Transfer

Source Server         : Mysqlserver
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : perzan1

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2015-05-18 12:42:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `administrador`
-- ----------------------------
DROP TABLE IF EXISTS `administrador`;
CREATE TABLE `administrador` (
`id`  int(3) NOT NULL AUTO_INCREMENT ,
`administrador`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `Índice 2` (`administrador`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of administrador
-- ----------------------------
BEGIN;
INSERT INTO `administrador` VALUES ('1', 'admin1', 'admin1'), ('2', 'admin2', 'admin2');
COMMIT;

-- ----------------------------
-- Table structure for `ajusteinventario`
-- ----------------------------
DROP TABLE IF EXISTS `ajusteinventario`;
CREATE TABLE `ajusteinventario` (
`id`  int(4) NOT NULL AUTO_INCREMENT ,
`idProducto`  int(3) NOT NULL DEFAULT 0 ,
PRIMARY KEY (`id`),
FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
INDEX `FK_ajusteinventario_producto` (`idProducto`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of ajusteinventario
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `categoria`
-- ----------------------------
DROP TABLE IF EXISTS `categoria`;
CREATE TABLE `categoria` (
`id`  int(3) NOT NULL ,
`nombre`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of categoria
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `cliente`
-- ----------------------------
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
`id`  int(4) NOT NULL AUTO_INCREMENT ,
`nombre`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`apellidoPaterno`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`apellidoMaterno`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`calle`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`avenida`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`numero`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`colonia`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`municipio`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of cliente
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `compra`
-- ----------------------------
DROP TABLE IF EXISTS `compra`;
CREATE TABLE `compra` (
`id`  int(4) NOT NULL ,
`idProveedor`  int(4) NOT NULL ,
`total`  float NOT NULL ,
`fecha`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`),
FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
INDEX `FK_compra_proveedor` (`idProveedor`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of compra
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `detallecompra`
-- ----------------------------
DROP TABLE IF EXISTS `detallecompra`;
CREATE TABLE `detallecompra` (
`id`  int(11) NOT NULL ,
`idProducto`  int(11) NOT NULL ,
`idProveedor`  int(11) NOT NULL ,
`idCompra`  int(11) NOT NULL ,
`cantidad`  int(11) NOT NULL ,
`precio`  float NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`idCompra`) REFERENCES `compra` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (`idProveedor`) REFERENCES `proveedor` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
INDEX `FKProducto` (`idProducto`) USING BTREE ,
INDEX `FKProveedor` (`idProveedor`) USING BTREE ,
INDEX `FKCompra` (`idCompra`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of detallecompra
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `detalleventa`
-- ----------------------------
DROP TABLE IF EXISTS `detalleventa`;
CREATE TABLE `detalleventa` (
`id`  int(11) NOT NULL ,
`idProducto`  int(11) NOT NULL ,
`cantidad`  int(11) NOT NULL ,
`idVenta`  int(11) NOT NULL ,
PRIMARY KEY (`id`),
FOREIGN KEY (`idProducto`) REFERENCES `producto` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (`idVenta`) REFERENCES `venta` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
INDEX `FKVenta` (`idVenta`) USING BTREE ,
INDEX `FKProduct` (`idProducto`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of detalleventa
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `empleado`
-- ----------------------------
DROP TABLE IF EXISTS `empleado`;
CREATE TABLE `empleado` (
`id`  int(3) NOT NULL AUTO_INCREMENT ,
`nombre`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`apellidoPaterno`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`apellidoMaterno`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`calle`  varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`avenida`  varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`numero`  varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`colonia`  varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`municipio`  varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`telefono`  varchar(14) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`usuario`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`password`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`activo`  set('true','false') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'true' ,
PRIMARY KEY (`id`),
UNIQUE INDEX `Índice 2` (`usuario`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of empleado
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `marca`
-- ----------------------------
DROP TABLE IF EXISTS `marca`;
CREATE TABLE `marca` (
`id`  int(3) NOT NULL ,
`nombre`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci

;

-- ----------------------------
-- Records of marca
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `producto`
-- ----------------------------
DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
`id`  int(3) NOT NULL AUTO_INCREMENT ,
`nombre`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`idCategoria`  int(3) NOT NULL ,
`idMarca`  int(4) NOT NULL ,
`precio`  float NOT NULL ,
`stock`  int(4) NOT NULL ,
`stockMax`  int(4) NOT NULL ,
`stockMin`  int(4) NOT NULL ,
`tipos`  set('interior','exterior') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL ,
`activo`  set('true','false') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'true' ,
PRIMARY KEY (`id`),
FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (`idMarca`) REFERENCES `marca` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
UNIQUE INDEX `Índice 2` (`nombre`) USING BTREE ,
INDEX `FKCategoria` (`idCategoria`) USING BTREE ,
INDEX `FKMarca` (`idMarca`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of producto
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `proveedor`
-- ----------------------------
DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
`id`  int(4) NOT NULL AUTO_INCREMENT ,
`nombre`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`apellidoPaterno`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`apellidoMaterno`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`empresa`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`calle`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`avenida`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`numero`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`colonia`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`municipio`  varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`telefono`  varchar(13) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' ,
`activo`  set('true','false') CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'true' ,
PRIMARY KEY (`id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of proveedor
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for `venta`
-- ----------------------------
DROP TABLE IF EXISTS `venta`;
CREATE TABLE `venta` (
`id`  int(3) NOT NULL AUTO_INCREMENT ,
`idVendedor`  int(4) NOT NULL ,
`idCliente`  int(11) NOT NULL ,
`importe`  float NOT NULL ,
`fecha`  timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ,
PRIMARY KEY (`id`),
FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
FOREIGN KEY (`idVendedor`) REFERENCES `empleado` (`id`) ON DELETE RESTRICT ON UPDATE CASCADE,
INDEX `FKVendedor` (`idVendedor`) USING BTREE ,
INDEX `FKCliente` (`idCliente`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
AUTO_INCREMENT=1

;

-- ----------------------------
-- Records of venta
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Auto increment value for `administrador`
-- ----------------------------
ALTER TABLE `administrador` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for `ajusteinventario`
-- ----------------------------
ALTER TABLE `ajusteinventario` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `cliente`
-- ----------------------------
ALTER TABLE `cliente` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `empleado`
-- ----------------------------
ALTER TABLE `empleado` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `producto`
-- ----------------------------
ALTER TABLE `producto` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `proveedor`
-- ----------------------------
ALTER TABLE `proveedor` AUTO_INCREMENT=1;

-- ----------------------------
-- Auto increment value for `venta`
-- ----------------------------
ALTER TABLE `venta` AUTO_INCREMENT=1;
