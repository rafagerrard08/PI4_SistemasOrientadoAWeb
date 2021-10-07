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
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` int NOT NULL AUTO_INCREMENT,
  `avaliacao` double DEFAULT NULL,
  `descricao` varchar(2000) DEFAULT NULL,
  `estado` int DEFAULT NULL,
  `imagem_principal` varchar(255) DEFAULT NULL,
  `marca` varchar(200) DEFAULT NULL,
  `nome` varchar(200) DEFAULT NULL,
  `preco` decimal(19,2) DEFAULT NULL,
  `quantidade` int DEFAULT NULL,
  `categoria_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKopu9jggwnamfv0c8k2ri3kx0a` (`categoria_id`),
  CONSTRAINT `FKopu9jggwnamfv0c8k2ri3kx0a` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (
  1,
  4,
  'Micro-ondas CM020 Consul Espelhado 20 litros. Compacto e moderno, ele possui o Menu Uso Fácil e receitas pré-programadas que descomplicam o seu dia-a-dia. Descongelar a carne do almoço, preparar o lanche das crianças, aquecer o jantar. Com ele você faz tudo isso e muito mais em questão de minutos. É tão fácil que até parece mágica: com apenas um toque você consegue preparar massas, pipoca e até vegetais. Possui ainda porta espelhada com puxador, 10 opções de potência para você preparar sua refeição na medida certa, opção desliga visor que proporciona uma economia de até 12% de energia desligando o visor luminoso e comando cancelar que além de interromper o preparo, também serve para travar o teclado e deixar sua cozinha mais segura.',
  1,
  '/produtos/img/1/principal.jpg',
  'Electrolux',
  'Micro-ondas 20L CM020',
  678.30,
  42,
  1
),
(
  2,
  5,
  'Não precisa mais lavar suas roupas favoritas à mão para que elas não fiquem com fiapos. A máquina de lavar LAC13 possui um volume de filtragem seis vezes maior do que as outras lavadoras, o que evita que você passe o fim de semana tirando pelos das peças.A tecnologia Jet & Clean te livra de ter que ficar limpando o dispenser após cada ciclo e você ainda fica seguro com a garantia de dez anos do cesto de propileno da sua máquina de lavar Electrolux.',1,'/produtos/img/2/principal.jpg','Electrolux','Lavadora Tecnologia Jet & Clean 13 Kg',
  1456.27,
  7,
  1
),
(3,4.5,'iPhone 12 Pro. Tela Super Retina XDR brilhante de 6,1 polegadas*. Ceramic Shield, que é quatro vezes mais resistente a quedas**. Fotos surpreendentes em pouca luz com o novo sistema de câmera Pro e alcance de zoom óptico de 4x. Reprodução, edição e gravação de vídeos em Dolby Vision com qualidade de cinema. Retratos com modo Noite e experiências ainda melhores em realidade aumentada com o Scanner LiDAR. Poderoso chip A14 Bionic. Compatível com 5G***.',1,'/produtos/img/3/principal.jpg','Apple','iPhone 12 Pro 128GB Azul-Pacífico 6,1”',7199.10,50,2),(4,3.5,'Experimente o melhor da tecnologia com a Smart PRO 32 polegadas HD da LG, oferece tela de LED e resolução HD de 1366x768 pixels obtendo uma incrível experiência de imagem, possui três entradas HDMI e duas USB, proporcionando mais possibilidades de conexão. Acompanha compatibilidade com o sistema de Inteligência Artificial ThinQ Al, conta com Bluetooth, sistema operacional webOS 4.5, Music Player, LG Content Store, Modo Hotel, painel de controle, galeria, conteúdos VR 360, miracast overlay, HDR e potência de som de 10W. Acesse aos aplicativos Netflix e Youtube, graças a conexão Smart, navegue na internet conectando-se diretamente a rede Wi-Fi. Além disso, seu design arrojado e diversos modos predefinidos de imagem fazem da Smart 32LM621CBSB.AWZ uma TV elegante e eficiente.',1,'/produtos/img/4/principal.jpg','LG','Smart TV LED PRO 32\'\' HD',1431.27,73,3)
(
  5,
  null,
  'Tenha uma solução para o seu dia a dia sem deixar nada para trás com o Galaxy A32 da Samsung. Realize fotos especiais e únicas com o conjunto de 4 câmeras na parte de trás do aparelho. Divirta-se e compartilhe seus momentos especiais ao fotografar com a câmera de selfie de 20MP. A tela de 6,4" com resolução FHD+ deste smartphone oferece uma experiência de visualização imersiva, seja ao ver seus vídeos, fotos ou simplesmente acessar as redes sociais.',
  1,
  '/produtos/img/5/principal.jpg',
  'Samsung',
  'Galaxy A32 128GB Preto 4G',
  1678.30,
  42,
  3
),(
  6,
  null,
  'O carregador USB-C de 20W Apple é rápido e eficiente, ideal para usar em casa, no trabalho ou onde você estiver. Ele é compatível com qualquer aparelho com porta USB-C, mas recomendamos o uso com o iPad Pro de 11 polegadas ou iPad Pro de 12,9 polegadas (3a geração) para ter o melhor rendimento. Você também pode conectá-lo ao iPhone 8 ou posterior para aproveitar o recurso de carregamento rápido.',
  1,
  '/produtos/img/6/principal.jpg',
  'Apple',
  'Carregador USB-C de 20W',
  178.30,
  42,
  3
);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
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
