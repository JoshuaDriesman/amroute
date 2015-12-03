-- MySQL Script generated by MySQL Workbench
-- 12/03/15 14:14:10
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema amroute
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `amroute` ;

-- -----------------------------------------------------
-- Schema amroute
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `amroute` DEFAULT CHARACTER SET utf8 ;
USE `amroute` ;

-- -----------------------------------------------------
-- Table `amroute`.`Routes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amroute`.`Routes` ;

CREATE TABLE IF NOT EXISTS `amroute`.`Routes` (
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE INDEX `Name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amroute`.`Equipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amroute`.`Equipment` ;

CREATE TABLE IF NOT EXISTS `amroute`.`Equipment` (
  `idEquipment` INT NOT NULL AUTO_INCREMENT,
  `configuration` VARCHAR(45) NOT NULL,
  `series` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEquipment`),
  UNIQUE INDEX `idEquipment_UNIQUE` (`idEquipment` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amroute`.`RouteEquipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amroute`.`RouteEquipment` ;

CREATE TABLE IF NOT EXISTS `amroute`.`RouteEquipment` (
  `routeName` VARCHAR(45) NOT NULL,
  `equipmentId` INT NOT NULL,
  PRIMARY KEY (`routeName`, `equipmentId`),
  CONSTRAINT `route`
    FOREIGN KEY (`routeName`)
    REFERENCES `amroute`.`Routes` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `equipmentType`
    FOREIGN KEY (`equipmentId`)
    REFERENCES `amroute`.`Equipment` (`idEquipment`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amroute`.`Cities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amroute`.`Cities` ;

CREATE TABLE IF NOT EXISTS `amroute`.`Cities` (
  `idCities` INT NOT NULL AUTO_INCREMENT,
  `region` ENUM('northeast', 'midwest', 'west', 'southwest', 'southeast') NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCities`),
  UNIQUE INDEX `idCities_UNIQUE` (`idCities` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amroute`.`Schedule`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amroute`.`Schedule` ;

CREATE TABLE IF NOT EXISTS `amroute`.`Schedule` (
  `idSchedule` INT NOT NULL AUTO_INCREMENT,
  `origin` INT NOT NULL,
  `termination` INT NOT NULL,
  `originTime` TIME(0) NOT NULL,
  `termTime` TIME(0) NOT NULL,
  `route` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idSchedule`),
  UNIQUE INDEX `idSchedule_UNIQUE` (`idSchedule` ASC),
  INDEX `routeScheduled_idx` (`route` ASC),
  INDEX `originCity_idx` (`origin` ASC),
  INDEX `terminationCity_idx` (`termination` ASC),
  CONSTRAINT `routeScheduled`
    FOREIGN KEY (`route`)
    REFERENCES `amroute`.`Routes` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `originCity`
    FOREIGN KEY (`origin`)
    REFERENCES `amroute`.`Cities` (`idCities`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `terminationCity`
    FOREIGN KEY (`termination`)
    REFERENCES `amroute`.`Cities` (`idCities`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `amroute`.`ScheduleCities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `amroute`.`ScheduleCities` ;

CREATE TABLE IF NOT EXISTS `amroute`.`ScheduleCities` (
  `scheduleId` INT NOT NULL,
  `cityId` INT NOT NULL,
  `time` TIME(0) NOT NULL,
  PRIMARY KEY (`scheduleId`, `cityId`),
  CONSTRAINT `scheduleId`
    FOREIGN KEY (`scheduleId`)
    REFERENCES `amroute`.`Schedule` (`idSchedule`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `cityStop`
    FOREIGN KEY (`cityId`)
    REFERENCES `amroute`.`Cities` (`idCities`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
