-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: almacen
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(20) NOT NULL,
  `identificacion` bigint NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `tipo_cliente` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `fecha_nacimiento` varchar(45) NOT NULL,
  `nit` varchar(45) NOT NULL,
  PRIMARY KEY (`identificacion`),
  UNIQUE KEY `identificacion_UNIQUE` (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES ('Paula','Lopez',451256778,'Barcelona','3164654996','Jurídico','No Requiere','No Requiere','451256778-5'),('Juan Manuel ','Tunubala Larrota',1005091535,'San José','3233747435','Jurídico','No Requiere','No Requiere','1005091535-9'),('Juan Emanuel ','Rodríguez Rodríguez',1097488859,'La Isabela','3136494987','Natural','juane.rodriguezr@gmail.com','24-06-2004','No Requiere'),('Nicolás ','Ramírez Ríos',1112218376,'centro','12345678','Jurídico','No Requiere','No Requiere','1112218376-3'),('Pepito','Perez',1234567123,'Las Castillas','313633667','Natural','pepito@gmail.com','13-02-2000','No Requiere');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_venta` (
  `codigo_venta` int NOT NULL,
  `cantidad_productos` int NOT NULL,
  `codigo_producto_vendido` int NOT NULL,
  `sub_total` double NOT NULL,
  KEY `fk_detalle_venta_ventas` (`codigo_venta`),
  KEY `fk_detalle_venta_producto` (`codigo_producto_vendido`),
  CONSTRAINT `fk_detalle_venta_producto` FOREIGN KEY (`codigo_producto_vendido`) REFERENCES `producto` (`codigo`),
  CONSTRAINT `fk_detalle_venta_ventas` FOREIGN KEY (`codigo_venta`) REFERENCES `ventas` (`codigo_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_venta`
--

LOCK TABLES `detalle_venta` WRITE;
/*!40000 ALTER TABLE `detalle_venta` DISABLE KEYS */;
INSERT INTO `detalle_venta` VALUES (1,3,30124,16500),(1,3,12123,7500),(2,4,12345,28000),(2,4,54321,12000),(2,2,12123,5000),(2,4,12345,28000),(2,4,54321,12000),(2,2,12123,5000),(2,4,12345,28000),(2,4,54321,12000),(2,2,12123,5000),(2,4,12345,28000),(2,4,54321,12000),(2,2,12123,5000),(3,5,12345,30000),(3,3,12123,7500),(3,10,54321,30000),(4,3,12345,18000),(4,5,12123,12500),(4,2,54321,6000),(6,5,12345,30000),(6,6,30124,33000),(7,6,30124,33000),(7,5,12345,30000),(8,2,30124,11000),(8,2,30125,5000),(8,2,12123,5000);
/*!40000 ALTER TABLE `detalle_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `codigo` int NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `valor_unitario` double NOT NULL,
  `cantidad_existente` int NOT NULL,
  `tipo_producto` varchar(40) NOT NULL,
  PRIMARY KEY (`codigo`),
  UNIQUE KEY `codigo_UNIQUE` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (12123,'Gaseosa','Coca-Ina',2500,28,'Envasado'),(12345,'Queso','Queso Cuajada',6000,25,'Perecedero'),(30124,'Pan Mariquiteño','Pan Mariquiteño de 500 gr con queso',5500,15,'Perecedero'),(30125,'Salchichón ','Salchichón Cervecero Colanta',2500,13,'Perecedero'),(54321,'Yugur','Yougurt Fresa',3000,40,'Refrigerado');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_envasado`
--

DROP TABLE IF EXISTS `producto_envasado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_envasado` (
  `codigo` int NOT NULL,
  `fecha_envasado` varchar(45) NOT NULL,
  `peso_envase` double NOT NULL,
  `pais_origen` varchar(20) NOT NULL,
  KEY `fk_producto_envasado_producto` (`codigo`),
  CONSTRAINT `fk_producto_envasado_producto` FOREIGN KEY (`codigo`) REFERENCES `producto` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_envasado`
--

LOCK TABLES `producto_envasado` WRITE;
/*!40000 ALTER TABLE `producto_envasado` DISABLE KEYS */;
INSERT INTO `producto_envasado` VALUES (12123,'23-08-2022',3,'Colombia');
/*!40000 ALTER TABLE `producto_envasado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_perecedero`
--

DROP TABLE IF EXISTS `producto_perecedero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_perecedero` (
  `codigo` int NOT NULL,
  `fecha_vencimiento` varchar(45) NOT NULL,
  KEY `fk_dproducto_perecedero_producto` (`codigo`),
  CONSTRAINT `fk_dproducto_perecedero_producto` FOREIGN KEY (`codigo`) REFERENCES `producto` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_perecedero`
--

LOCK TABLES `producto_perecedero` WRITE;
/*!40000 ALTER TABLE `producto_perecedero` DISABLE KEYS */;
INSERT INTO `producto_perecedero` VALUES (12345,'23-11-2024'),(30124,'4-09-2022'),(30125,'19-10-2022');
/*!40000 ALTER TABLE `producto_perecedero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto_refrigerado`
--

DROP TABLE IF EXISTS `producto_refrigerado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto_refrigerado` (
  `codigo` int NOT NULL,
  `codigo_aprobacion` varchar(45) NOT NULL,
  `temperatura_recomendada` double NOT NULL,
  KEY `fk_producto_refrigerado_producto` (`codigo`),
  CONSTRAINT `fk_producto_refrigerado_producto` FOREIGN KEY (`codigo`) REFERENCES `producto` (`codigo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto_refrigerado`
--

LOCK TABLES `producto_refrigerado` WRITE;
/*!40000 ALTER TABLE `producto_refrigerado` DISABLE KEYS */;
INSERT INTO `producto_refrigerado` VALUES (54321,'234',3.5);
/*!40000 ALTER TABLE `producto_refrigerado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ventas`
--

DROP TABLE IF EXISTS `ventas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ventas` (
  `codigo_venta` int NOT NULL,
  `fecha_venta` varchar(30) NOT NULL,
  `identificacion_cliente` bigint NOT NULL,
  `total` double NOT NULL,
  `iva` double NOT NULL,
  PRIMARY KEY (`codigo_venta`),
  KEY `fk_ventas_cliente` (`identificacion_cliente`),
  CONSTRAINT `fk_ventas_cliente` FOREIGN KEY (`identificacion_cliente`) REFERENCES `cliente` (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ventas`
--

LOCK TABLES `ventas` WRITE;
/*!40000 ALTER TABLE `ventas` DISABLE KEYS */;
INSERT INTO `ventas` VALUES (1,'23-8-2022',1005091535,28560,4560),(2,'23-8-2022',1097488859,53550,8550),(3,'23-8-2022',1097488859,80325,12825),(4,'23-8-2022',1234567123,43435,6935),(5,'18-9-2022',1005091535,74970,11970),(6,'18-9-2022',1005091535,74970,11970),(7,'18-9-2022',1005091535,74970,11970),(8,'19-9-2022',1005091535,24990,3990);
/*!40000 ALTER TABLE `ventas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-09-19 13:03:28
