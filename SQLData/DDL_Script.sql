-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SmartParking
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SmartParking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SmartParking` DEFAULT CHARACTER SET utf8 ;
USE `SmartParking` ;

-- -----------------------------------------------------
-- Table `SmartParking`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SmartParking`.`Users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(255) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `role` ENUM("admin", "driver", "manager") NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `userName_UNIQUE` (`userName` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SmartParking`.`Driver`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SmartParking`.`Driver` (
  `License` VARCHAR(20) NOT NULL,
  `id` INT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `License_UNIQUE` (`License` ASC) VISIBLE,
  CONSTRAINT `id`
    FOREIGN KEY (`id`)
    REFERENCES `SmartParking`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SmartParking`.`ParkingLot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SmartParking`.`ParkingLot` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `managerid` INT NULL,
  `lotname` VARCHAR(45) NOT NULL,
  `location` VARCHAR(255) NOT NULL,
  `capacity` INT NOT NULL,
  `pricingstructure` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `ManagerLotID_idx` (`managerid` ASC) VISIBLE,
  CONSTRAINT `ManagerLotID`
    FOREIGN KEY (`managerid`)
    REFERENCES `SmartParking`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SmartParking`.`Spot`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SmartParking`.`Spot` (
  `LotID` INT NOT NULL,
  `SpotID` INT NOT NULL AUTO_INCREMENT,
  `Status` ENUM("occupied", "available", "reserved") NOT NULL,
  `type` ENUM("regular", "disabled", "EV") NOT NULL,
  `Price` FLOAT NOT NULL,
  PRIMARY KEY (`SpotID`, `LotID`),
  CONSTRAINT `LotID`
    FOREIGN KEY (`LotID`)
    REFERENCES `SmartParking`.`ParkingLot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SmartParking`.`Reservation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SmartParking`.`Reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `spotid` INT NOT NULL,
  `lotid` INT NOT NULL,
  `driverid` INT NOT NULL,
  `reservationstatus` ENUM("ACCEPTED", "PENDING", "REJECTED") NOT NULL,
  `reservationhours` INT NOT NULL,
  `reservationtime` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `Spot_idx` (`spotid` ASC) VISIBLE,
  INDEX `Lot_idx` (`lotid` ASC) VISIBLE,
  INDEX `Driver_idx` (`driverid` ASC) VISIBLE,
  CONSTRAINT `Spot`
    FOREIGN KEY (`spotid`)
    REFERENCES `SmartParking`.`Spot` (`SpotID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Lot`
    FOREIGN KEY (`lotid`)
    REFERENCES `SmartParking`.`ParkingLot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Driver`
    FOREIGN KEY (`driverid`)
    REFERENCES `SmartParking`.`Driver` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SmartParking`.`Penalities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SmartParking`.`Penalities` (
  `ReservationID` INT NOT NULL,
  `PenalityFee` FLOAT NULL,
  PRIMARY KEY (`ReservationID`),
  CONSTRAINT `ReservationID`
    FOREIGN KEY (`ReservationID`)
    REFERENCES `SmartParking`.`Reservation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
