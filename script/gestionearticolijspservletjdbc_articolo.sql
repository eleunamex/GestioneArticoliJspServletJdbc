CREATE DATABASE  IF NOT EXISTS `gestionearticolijspservletjdbc` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `gestionearticolijspservletjdbc`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: gestionearticolijspservletjdbc
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `articolo`
--

DROP TABLE IF EXISTS `articolo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `articolo` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `CODICE` varchar(45) NOT NULL,
  `DESCRIZIONE` varchar(45) NOT NULL,
  `PREZZO` int NOT NULL,
  `CATEGORIA_FK` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `categoria_fk_idx` (`CATEGORIA_FK`),
  CONSTRAINT `CATEGORIA_FK` FOREIGN KEY (`CATEGORIA_FK`) REFERENCES `categoria` (`ID_CATEGORIA`) ON DELETE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `articolo`
--

LOCK TABLES `articolo` WRITE;
/*!40000 ALTER TABLE `articolo` DISABLE KEYS */;
INSERT INTO `articolo` VALUES (32,'SM60OH172','Smart TV Curved Samsung 60\'\'',1280,2),(46,'LG40DEH','Smart TV Oled LG 40\"',970,2),(47,'DEH-3900BT','Autoradio bluetooth Pioneer',145,2),(49,'IO092831','Iphone 12 Pro',1450,2),(50,'SM092832','Samsung Galaxy Note 10',890,2),(54,'89345703481','Lavastoviglie Samsung',1200,1),(55,'9285937295','Tubolare da 20',12,8),(58,'35853152','Motosega elettrica',130,4),(59,'895703802','Trattore New Holland',62300,6),(60,'9219321','Kit utensili coltivazione',100,6),(61,'124981924','Frullatore',15,1),(62,'98508889','Forno a microonde',30,1),(63,'5857805721','Caldaia',970,8),(64,'87379052','Tubo pvc',16,8),(65,'325232','Zappa',25,6),(66,'32555312','Tagliaerba',450,4),(67,'805189125','Tubo irrigazione',22,4),(81,'562389089','Mouse Razer ',90,2);
/*!40000 ALTER TABLE `articolo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-12 10:01:59
