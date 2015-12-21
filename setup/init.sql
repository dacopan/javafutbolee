-- MySQL dump 10.13  Distrib 5.6.27, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: jfee
-- ------------------------------------------------------
-- Server version	5.6.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `boleto`
--

LOCK TABLES `boleto` WRITE;
/*!40000 ALTER TABLE `boleto` DISABLE KEYS */;
/*!40000 ALTER TABLE `boleto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `equipo`
--

LOCK TABLES `equipo` WRITE;
/*!40000 ALTER TABLE `equipo` DISABLE KEYS */;
INSERT INTO `equipo` VALUES (1,'Liga de Quito','LDU');
/*!40000 ALTER TABLE `equipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `estadio`
--

LOCK TABLES `estadio` WRITE;
/*!40000 ALTER TABLE `estadio` DISABLE KEYS */;
INSERT INTO `estadio` VALUES (1,'Monumental Bco. Pchincha'),(2,'casa blanca'),(3,'casa blanca3');
/*!40000 ALTER TABLE `estadio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `forma_pago`
--

LOCK TABLES `forma_pago` WRITE;
/*!40000 ALTER TABLE `forma_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `forma_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `localidad`
--

LOCK TABLES `localidad` WRITE;
/*!40000 ALTER TABLE `localidad` DISABLE KEYS */;
INSERT INTO `localidad` VALUES (1,'General Norte',15000),(2,'General Sur',15000),(3,'Tribuna Oeste',12500),(4,'Tribuna Este',12500),(5,'Palco 1',1875),(6,'Palco 2',1875),(7,'Palco 3',1875),(8,'Palco 4',1875),(9,'Palco 5',1875),(10,'Palco 6',1875),(11,'Palco 7',1875),(12,'Palco 8',1875),(13,'Suite Oeste',5000),(14,'Suite Este',5000);
/*!40000 ALTER TABLE `localidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `pais`
--

LOCK TABLES `pais` WRITE;
/*!40000 ALTER TABLE `pais` DISABLE KEYS */;
/*!40000 ALTER TABLE `pais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `partido`
--

LOCK TABLES `partido` WRITE;
/*!40000 ALTER TABLE `partido` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `partido_precio`
--

LOCK TABLES `partido_precio` WRITE;
/*!40000 ALTER TABLE `partido_precio` DISABLE KEYS */;
/*!40000 ALTER TABLE `partido_precio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `personal`
--

LOCK TABLES `personal` WRITE;
/*!40000 ALTER TABLE `personal` DISABLE KEYS */;
INSERT INTO `personal` VALUES (28,28,'darwin','correa',0,'2015-12-19','4986489','4986489','tumbaco'),(29,29,'darwin','correa',0,'2015-12-20','4986489','4986489','');
/*!40000 ALTER TABLE `personal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'ROL_ADMIN'),(2,'ROL_ADMIN_SOCIO'),(3,'ROL_RRPP');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `rol_miembro`
--

LOCK TABLES `rol_miembro` WRITE;
/*!40000 ALTER TABLE `rol_miembro` DISABLE KEYS */;
INSERT INTO `rol_miembro` VALUES (25,28,3),(26,29,2);
/*!40000 ALTER TABLE `rol_miembro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `socio`
--

LOCK TABLES `socio` WRITE;
/*!40000 ALTER TABLE `socio` DISABLE KEYS */;
/*!40000 ALTER TABLE `socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `temporada`
--

LOCK TABLES `temporada` WRITE;
/*!40000 ALTER TABLE `temporada` DISABLE KEYS */;
INSERT INTO `temporada` VALUES (1,'2015-12-12','2015-12-21','Campeonato ecuatoriano de futbol 2015'),(2,'2015-12-20','2016-12-15','campeonato pilsener 2016');
/*!40000 ALTER TABLE `temporada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (28,'1719871327','$2a$10$Sx5o3CHkq3GyZF7kP5YCe.OGE/V6wsTDpPGpsHHt9awMUfgajzTcC','dacopan.bsc@gmail.com',1,NULL,'2015-12-19 05:27:02',NULL,NULL,NULL,NULL),(29,'1722590948','$2a$10$PPYYGGxHyUYvX.ziX/Y1AuA03w0G.oOE7C34HNpEqEFILgy3SGPgy','dacopan.bsc@gmail.com',1,NULL,'2015-12-20 13:58:20',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-20 18:48:48
