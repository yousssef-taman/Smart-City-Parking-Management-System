-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Driver` (
  `License` VARCHAR(20) NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `id`
    FOREIGN KEY (`id`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ParkingLot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ParkingLot` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `LotName` VARCHAR(45) NULL,
  `Location` VARCHAR(255) NULL,
  `capacity` INT NULL,
  `pricingStructure` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Spots`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Spots` (
  `LotID` INT NOT NULL,
  `SpotID` INT NOT NULL AUTO_INCREMENT,
  `Status` ENUM("occupied", "available", "reserved") NULL,
  `type` ENUM("regular", "disabled", "EV") NULL,
  `Price` FLOAT NULL,
  PRIMARY KEY (`SpotID`, `LotID`),
  CONSTRAINT `LotID`
    FOREIGN KEY (`LotID`)
    REFERENCES `mydb`.`ParkingLot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Reservation` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `SpotID` INT NOT NULL,
  `LotID` INT NOT NULL,
  `DriverID` INT NOT NULL,
  `ReservationStatus` ENUM("Accepted", "Rejected") NOT NULL,
  `ReservationHours` INT NOT NULL,
  `ReservationTime` DATETIME NOT NULL,
  PRIMARY KEY (`ID`),
  INDEX `Spot_idx` (`SpotID` ASC) VISIBLE,
  INDEX `Lot_idx` (`LotID` ASC) VISIBLE,
  INDEX `Driver_idx` (`DriverID` ASC) VISIBLE,
  CONSTRAINT `Spot`
    FOREIGN KEY (`SpotID`)
    REFERENCES `mydb`.`Spots` (`SpotID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Lot`
    FOREIGN KEY (`LotID`)
    REFERENCES `mydb`.`ParkingLot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Driver`
    FOREIGN KEY (`DriverID`)
    REFERENCES `mydb`.`Driver` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Penalities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Penalities` (
  `ReservationID` INT NOT NULL,
  `PenalityFee` FLOAT NULL,
  PRIMARY KEY (`ReservationID`),
  CONSTRAINT `ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `mydb`.`Reservation` (`ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
