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
-- Table structure for table `boleto`
--

DROP TABLE IF EXISTS `boleto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `boleto` (
  `BOL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FRM_PAG_ID` int(11) DEFAULT NULL,
  `SOC_ID` int(11) DEFAULT NULL,
  `PRT_PRE_ID` int(11) DEFAULT NULL,
  `BOL_ENTREGADO` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`BOL_ID`),
  KEY `FK_RELATIONSHIP_17` (`PRT_PRE_ID`),
  KEY `FK_RELATIONSHIP_19` (`FRM_PAG_ID`),
  KEY `FK_RELATIONSHIP_21` (`SOC_ID`),
  CONSTRAINT `FK_RELATIONSHIP_17` FOREIGN KEY (`PRT_PRE_ID`) REFERENCES `partido_precio` (`PRT_PRE_ID`),
  CONSTRAINT `FK_RELATIONSHIP_19` FOREIGN KEY (`FRM_PAG_ID`) REFERENCES `forma_pago` (`FRM_PAG_ID`),
  CONSTRAINT `FK_RELATIONSHIP_21` FOREIGN KEY (`SOC_ID`) REFERENCES `socio` (`SOC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ciudad` (
  `CIU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAI_ID` int(11) DEFAULT NULL,
  `CIU_NOMBRE` text,
  PRIMARY KEY (`CIU_ID`),
  KEY `FK_RELATIONSHIP_13` (`PAI_ID`),
  CONSTRAINT `FK_RELATIONSHIP_13` FOREIGN KEY (`PAI_ID`) REFERENCES `pais` (`PAI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=681 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `equipo`
--

DROP TABLE IF EXISTS `equipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipo` (
  `EQP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EQP_NOMBRE` text,
  `EQP_ABBR` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`EQP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `estadio`
--

DROP TABLE IF EXISTS `estadio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `estadio` (
  `EST_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EST_NOMBRE` text,
  PRIMARY KEY (`EST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `forma_pago`
--

DROP TABLE IF EXISTS `forma_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `forma_pago` (
  `FRM_PAG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `FRM_PAG_NOMBRE` text,
  `FRM_PAG_PORCENTAJE` double DEFAULT NULL,
  PRIMARY KEY (`FRM_PAG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `localidad`
--

DROP TABLE IF EXISTS `localidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `localidad` (
  `LOC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOC_NOMBRE` text,
  `LOCA_CAPACIDAD` int(11) DEFAULT NULL,
  PRIMARY KEY (`LOC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pago` (
  `PAG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLN_ID` int(11) DEFAULT NULL,
  `SOC_ID2` int(11) DEFAULT NULL,
  `PAG_FECHA` datetime DEFAULT NULL,
  `PAG_MONTO` decimal(10,0) DEFAULT NULL,
  `PAG_ANIO` int(11) DEFAULT NULL,
  `PAG_ESTADO` int(11) DEFAULT NULL,
  PRIMARY KEY (`PAG_ID`),
  KEY `FK_RELATIONSHIP_22` (`SOC_ID2`),
  KEY `FK_RELATIONSHIP_8` (`PLN_ID`),
  CONSTRAINT `FK_RELATIONSHIP_22` FOREIGN KEY (`SOC_ID2`) REFERENCES `socio` (`SOC_ID`),
  CONSTRAINT `FK_RELATIONSHIP_8` FOREIGN KEY (`PLN_ID`) REFERENCES `plan` (`PLN_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pais`
--

DROP TABLE IF EXISTS `pais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pais` (
  `PAI_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PAI_CODE` varchar(5) DEFAULT NULL,
  `PAI_NOMBRE` varchar(65) DEFAULT NULL,
  PRIMARY KEY (`PAI_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=247 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partido`
--

DROP TABLE IF EXISTS `partido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido` (
  `PRT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `EST_ID` int(11) DEFAULT NULL,
  `EQP_ID` int(11) DEFAULT NULL,
  `TPD_ID` int(11) DEFAULT NULL,
  `PRT_FECHA` datetime DEFAULT NULL,
  `PRT_LOCAL` tinyint(1) DEFAULT NULL,
  `PRT_GOL` int(11) DEFAULT NULL,
  `PRT_GOL_RIVAL` int(11) DEFAULT NULL,
  PRIMARY KEY (`PRT_ID`),
  KEY `FK_RELATIONSHIP_10` (`EST_ID`),
  KEY `FK_RELATIONSHIP_11` (`EQP_ID`),
  KEY `FK_RELATIONSHIP_12` (`TPD_ID`),
  CONSTRAINT `FK_RELATIONSHIP_10` FOREIGN KEY (`EST_ID`) REFERENCES `estadio` (`EST_ID`),
  CONSTRAINT `FK_RELATIONSHIP_11` FOREIGN KEY (`EQP_ID`) REFERENCES `equipo` (`EQP_ID`),
  CONSTRAINT `FK_RELATIONSHIP_12` FOREIGN KEY (`TPD_ID`) REFERENCES `temporada` (`TPD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partido_precio`
--

DROP TABLE IF EXISTS `partido_precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `partido_precio` (
  `PRT_ID` int(11) DEFAULT NULL,
  `LOC_ID` int(11) DEFAULT NULL,
  `PRT_PRE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PRT_PRE_MONTO` double DEFAULT NULL,
  PRIMARY KEY (`PRT_PRE_ID`),
  KEY `FK_RELATIONSHIP_15` (`PRT_ID`),
  KEY `FK_RELATIONSHIP_16` (`LOC_ID`),
  CONSTRAINT `FK_RELATIONSHIP_15` FOREIGN KEY (`PRT_ID`) REFERENCES `partido` (`PRT_ID`),
  CONSTRAINT `FK_RELATIONSHIP_16` FOREIGN KEY (`LOC_ID`) REFERENCES `localidad` (`LOC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personal`
--

DROP TABLE IF EXISTS `personal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personal` (
  `PSN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USR_ID` int(11) DEFAULT NULL,
  `PSN_NOMBRE` text,
  `PSN_APELLIDO` text,
  `PSN_GENERO` int(11) DEFAULT NULL,
  `PSN_FECHA_NAC` date DEFAULT NULL,
  `PSN_TELEFONO` text,
  `PSN_CELULAR` text,
  `PSN_DIRECCION` text,
  PRIMARY KEY (`PSN_ID`),
  KEY `FK_RELATIONSHIP_23` (`USR_ID`),
  CONSTRAINT `FK_RELATIONSHIP_23` FOREIGN KEY (`USR_ID`) REFERENCES `usuario` (`USR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `PLN_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LOC_ID` int(11) DEFAULT NULL,
  `PLN_NOMBRE` text,
  `PLN_COSTO` double DEFAULT NULL,
  `PLN_ACTIVO` tinyint(1) DEFAULT NULL,
  `PLN_EDAD` int(11) DEFAULT NULL,
  PRIMARY KEY (`PLN_ID`),
  KEY `FK_RELATIONSHIP_6` (`LOC_ID`),
  CONSTRAINT `FK_RELATIONSHIP_6` FOREIGN KEY (`LOC_ID`) REFERENCES `localidad` (`LOC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol` (
  `ROL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROL_Nombre` text NOT NULL,
  PRIMARY KEY (`ROL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rol_miembro`
--

DROP TABLE IF EXISTS `rol_miembro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rol_miembro` (
  `ROL_MEM_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USR_ID` int(11) DEFAULT NULL,
  `ROL_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ROL_MEM_ID`),
  KEY `FK_RELATIONSHIP_4` (`USR_ID`),
  KEY `FK_RELATIONSHIP_5` (`ROL_ID`),
  CONSTRAINT `FK_RELATIONSHIP_4` FOREIGN KEY (`USR_ID`) REFERENCES `usuario` (`USR_ID`),
  CONSTRAINT `FK_RELATIONSHIP_5` FOREIGN KEY (`ROL_ID`) REFERENCES `rol` (`ROL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `socio`
--

DROP TABLE IF EXISTS `socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `socio` (
  `SOC_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLN_ID` int(11) DEFAULT NULL,
  `CIU_ID` int(11) DEFAULT NULL,
  `FRM_PAG_ID` int(11) DEFAULT NULL,
  `USR_ID` int(11) DEFAULT NULL,
  `SOC_NOMBRE` text,
  `SOC_APELLIDO` text,
  `SOC_FECHA_NAC` date DEFAULT NULL,
  `SOC_GENERO` int(11) DEFAULT NULL,
  `SOC_LUGAR_NAC` text,
  `SOC_TIPO_SANGRE` text,
  `SOC_NUM_HIJOS` int(11) DEFAULT NULL,
  `SOC_TRABAJA` tinyint(1) DEFAULT NULL,
  `SOC_DIRECCION` text,
  `SOC_DIRECCION_REF` text,
  `SOC_TELEFONO` text,
  `SOC_CELULAR` text,
  `SOC_NUM` text COMMENT 'numero unico de socio',
  `SOC_ESTADO` int(11) DEFAULT NULL COMMENT '1 activo\n            2 pasivo\n            3 desafiliado',
  PRIMARY KEY (`SOC_ID`),
  KEY `FK_RELATIONSHIP_14` (`CIU_ID`),
  KEY `FK_RELATIONSHIP_18` (`USR_ID`),
  KEY `FK_RELATIONSHIP_20` (`FRM_PAG_ID`),
  KEY `FK_RELATIONSHIP_9` (`PLN_ID`),
  CONSTRAINT `FK_RELATIONSHIP_14` FOREIGN KEY (`CIU_ID`) REFERENCES `ciudad` (`CIU_ID`),
  CONSTRAINT `FK_RELATIONSHIP_18` FOREIGN KEY (`USR_ID`) REFERENCES `usuario` (`USR_ID`),
  CONSTRAINT `FK_RELATIONSHIP_20` FOREIGN KEY (`FRM_PAG_ID`) REFERENCES `forma_pago` (`FRM_PAG_ID`),
  CONSTRAINT `FK_RELATIONSHIP_9` FOREIGN KEY (`PLN_ID`) REFERENCES `plan` (`PLN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temporada`
--

DROP TABLE IF EXISTS `temporada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporada` (
  `TPD_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TPD_INICIO` date DEFAULT NULL,
  `TPD_FIN` date DEFAULT NULL,
  `TPD_NOMBRE` varchar(85) DEFAULT NULL,
  PRIMARY KEY (`TPD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `USR_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USR_CI` text,
  `USR_PASSWORD` text,
  `USR_EMAIL` text,
  `USR_ACTIVE` tinyint(1) DEFAULT NULL,
  `USR_FAILED_LOGIN` int(11) DEFAULT NULL,
  `USR_CREATION_TIMESTAMP` datetime DEFAULT NULL,
  `USR_LAST_FAILED_LOGIN` datetime DEFAULT NULL,
  `USR_ACTIVATION_HASH` text,
  `USR_PASSWORD_RESET_HASH` text,
  `USR_AVATAR` text,
  PRIMARY KEY (`USR_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-29 19:30:00
