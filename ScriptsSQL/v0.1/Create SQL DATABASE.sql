-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ecommerce_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ecommerce_db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ecommerce_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `ecommerce_db` ;

-- -----------------------------------------------------
-- Table `ecommerce_db`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce_db`.`categoria` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecommerce_db`.`produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce_db`.`produto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `avaliacao` DOUBLE NULL DEFAULT NULL,
  `descricao` VARCHAR(2000) NULL DEFAULT NULL,
  `estado` INT NULL DEFAULT NULL,
  `imagem_principal` VARCHAR(255) NULL DEFAULT NULL,
  `marca` VARCHAR(200) NULL DEFAULT NULL,
  `nome` VARCHAR(200) NULL DEFAULT NULL,
  `preco` DECIMAL(19,2) NULL DEFAULT NULL,
  `quantidade` INT NULL DEFAULT NULL,
  `categoria_id` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `FKopu9jggwnamfv0c8k2ri3kx0a` (`categoria_id` ASC) VISIBLE,
  CONSTRAINT `FKopu9jggwnamfv0c8k2ri3kx0a`
    FOREIGN KEY (`categoria_id`)
    REFERENCES `ecommerce_db`.`categoria` (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecommerce_db`.`demais_imagens`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce_db`.`demais_imagens` (
  `produto_id` INT NOT NULL,
  `imagens` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FKj7t7dp76vm28lyq562g82ujsu` (`produto_id` ASC) VISIBLE,
  CONSTRAINT `FKj7t7dp76vm28lyq562g82ujsu`
    FOREIGN KEY (`produto_id`)
    REFERENCES `ecommerce_db`.`produto` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `ecommerce_db`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ecommerce_db`.`usuario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `senha` VARCHAR(255) NULL DEFAULT NULL,
  `tipo_usuario` INT NULL DEFAULT NULL,
  `nome` VARCHAR(255) NULL DEFAULT NULL,
  `cpf` VARCHAR(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
