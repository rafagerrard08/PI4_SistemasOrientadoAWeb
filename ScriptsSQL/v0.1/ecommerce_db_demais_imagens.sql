-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: 127.0.0.1    Database: ecommerce_db
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
-- Table structure for table `demais_imagens`
--

DROP TABLE IF EXISTS `demais_imagens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demais_imagens` (
  `produto_id` int NOT NULL,
  `imagens` varchar(255) DEFAULT NULL,
  KEY `FKj7t7dp76vm28lyq562g82ujsu` (`produto_id`),
  CONSTRAINT `FKj7t7dp76vm28lyq562g82ujsu` FOREIGN KEY (`produto_id`) REFERENCES `produto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demais_imagens`
--

LOCK TABLES `demais_imagens` WRITE;
/*!40000 ALTER TABLE `demais_imagens` DISABLE KEYS */;
INSERT INTO `demais_imagens` VALUES (1,'/produtos/img/1/img2.jpg'),(1,'/produtos/img/1/img3.jpg'),(1,'/produtos/img/1/img1.jpg'),(2,'/produtos/img/2/img3.jpg'),(2,'/produtos/img/2/img1.jpg'),(2,'/produtos/img/2/img2.jpg'),(3,'/produtos/img/3/img2.jpg'),(3,'/produtos/img/3/img1.jpg'),(3,'/produtos/img/3/img3.jpg'),(4,'/produtos/img/4/img1.jpg'),(4,'/produtos/img/4/img3.jpg'),(4,'/produtos/img/4/img2.jpg'),(5,'/produtos/img/5/img1.jpg'),(5,'/produtos/img/5/img2.jpg'),(6,'/produtos/img/6/img1.jpg'),(6,'/produtos/img/6/img2.jpg');
/*!40000 ALTER TABLE `demais_imagens` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-05 21:31:08
