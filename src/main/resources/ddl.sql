-- MySQL Script generated by MySQL Workbench
-- Sat May 20 11:58:05 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema cozinha-api
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `cozinha-api` ;

-- -----------------------------------------------------
-- Schema cozinha-api
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `cozinha-api` DEFAULT CHARACTER SET utf8 ;
USE `cozinha-api` ;

-- -----------------------------------------------------
-- Table `cozinha-api`.`pedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cozinha-api`.`pedido` ;

CREATE TABLE IF NOT EXISTS `cozinha-api`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cozinha-api`.`item_menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cozinha-api`.`item_menu` ;

CREATE TABLE IF NOT EXISTS `cozinha-api`.`item_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `descricao` MEDIUMTEXT NULL,
  `preco` DECIMAL(2) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cozinha-api`.`pedido_item_menu`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cozinha-api`.`pedido_item_menu` ;

CREATE TABLE IF NOT EXISTS `cozinha-api`.`pedido_item_menu` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_pedido` INT NULL,
  `id_item_menu` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `pedido_item_menu_idx` (`id_item_menu` ASC) VISIBLE,
  INDEX `pedido_item_menu_pedido_idx` (`id_pedido` ASC) VISIBLE,
  CONSTRAINT `pedido_item_menu_item_menu`
    FOREIGN KEY (`id_item_menu`)
    REFERENCES `cozinha-api`.`item_menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `pedido_item_menu_pedido`
    FOREIGN KEY (`id_pedido`)
    REFERENCES `cozinha-api`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cozinha-api`.`ingrediente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cozinha-api`.`ingrediente` ;

CREATE TABLE IF NOT EXISTS `cozinha-api`.`ingrediente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NULL,
  `quantidade` INT NULL,
  `unidade` VARCHAR(10) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `cozinha-api`.`item_menu_ingrediente`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cozinha-api`.`item_menu_ingrediente` ;

CREATE TABLE IF NOT EXISTS `cozinha-api`.`item_menu_ingrediente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `item_menu_id` INT NULL,
  `ingrediente_id` INT NULL,
  `quantidade_ingrediente` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `item_menu_ingrediente_item_menu_idx` (`item_menu_id` ASC) VISIBLE,
  INDEX `item_menu_ingrediente_ingrediente_idx` (`ingrediente_id` ASC) VISIBLE,
  CONSTRAINT `item_menu_ingrediente_item_menu`
    FOREIGN KEY (`item_menu_id`)
    REFERENCES `cozinha-api`.`item_menu` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `item_menu_ingrediente_ingrediente`
    FOREIGN KEY (`ingrediente_id`)
    REFERENCES `cozinha-api`.`ingrediente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
