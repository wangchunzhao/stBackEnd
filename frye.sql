-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bohemian
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bohemian` ;

-- -----------------------------------------------------
-- Schema bohemian
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bohemian` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `bohemian` ;

-- -----------------------------------------------------
-- Table `bohemian`.`b_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_users` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_users` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_identity` VARCHAR(45) NOT NULL COMMENT 'the user id of domain in loccal LDAP',
  `user_mail` VARCHAR(45) NOT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `isActive` TINYINT(1) UNSIGNED ZEROFILL NOT NULL COMMENT 'boolean type, mean if user is able to use system',
  `tel` VARCHAR(16) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idb_Users_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `user_mail_UNIQUE` (`user_mail` ASC) VISIBLE,
  UNIQUE INDEX `user_identity_UNIQUE` (`user_identity` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_roles` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_roles` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` TEXT NOT NULL,
  `isActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_application_of_rolechange`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_application_of_rolechange` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_application_of_rolechange` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `creator` TEXT NOT NULL COMMENT 'domain id of create user',
  `create_time` DATETIME NOT NULL COMMENT 'create time of the application',
  `approver_required` TEXT NOT NULL COMMENT 'the domain id of approver ',
  `approver_fact` TEXT NULL DEFAULT NULL,
  `approval_time` DATETIME NULL DEFAULT NULL,
  `b_users_id` INT(10) UNSIGNED NOT NULL,
  `isActive` TINYINT(1) NOT NULL DEFAULT '1',
  `attached_code` CHAR(32) NULL DEFAULT NULL,
  `b_roles_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_b_applicaation_of_rolechange_b_users1_idx` (`b_users_id` ASC) VISIBLE,
  INDEX `fk_b_application_of_rolechange_b_roles1_idx` (`b_roles_id` ASC) VISIBLE,
  CONSTRAINT `fk_b_applicaation_of_rolechange_b_users1`
    FOREIGN KEY (`b_users_id`)
    REFERENCES `bohemian`.`b_users` (`id`),
  CONSTRAINT `fk_b_application_of_rolechange_b_roles1`
    FOREIGN KEY (`b_roles_id`)
    REFERENCES `bohemian`.`b_roles` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_province`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_province` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_province` (
  `code` VARCHAR(6) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_city`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_city` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_city` (
  `code` VARCHAR(6) NOT NULL,
  `name` TEXT NOT NULL,
  `b_province_code` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_b_city_b_province1_idx` (`b_province_code` ASC) VISIBLE,
  CONSTRAINT `fk_b_city_b_province1`
    FOREIGN KEY (`b_province_code`)
    REFERENCES `bohemian`.`b_province` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_area`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_area` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_area` (
  `code` VARCHAR(16) NOT NULL,
  `name` TEXT NOT NULL,
  `b_city_code` VARCHAR(6) NOT NULL,
  `price` DECIMAL(13,2) NOT NULL COMMENT '供应商<20m³单价',
  `price1` DECIMAL(13,2) NULL COMMENT '供应商<20m³送货费',
  `price2` DECIMAL(13,2) NULL COMMENT '供应商>20<50m³单价',
  `price3` DECIMAL(13,2) NULL COMMENT '供应商>20<50m³送货费',
  `price4` DECIMAL(13,2) NULL COMMENT '供应商>=50m³单价',
  `price5` DECIMAL(13,2) NULL COMMENT '供应商>50m³送货费',
  `price6` DECIMAL(13,2) NULL COMMENT '客户<=20m³单价',
  `price7` DECIMAL(13,2) NULL COMMENT '客户<=20m³送货费',
  `price8` DECIMAL(13,2) NULL COMMENT '客户>20<50m³单价',
  `price9` DECIMAL(13,2) NULL COMMENT '客户>20<50m³送货费',
  `price10` DECIMAL(13,2) NULL COMMENT '客户>=50m³单价',
  `price11` DECIMAL(13,2) NULL COMMENT '客户>=50m³送货费',
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_b_area_b_city1_idx` (`b_city_code` ASC) VISIBLE,
  CONSTRAINT `fk_b_area_b_city1`
    FOREIGN KEY (`b_city_code`)
    REFERENCES `bohemian`.`b_city` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_notify_infor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_notify_infor` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_notify_infor` (
  `id` INT UNSIGNED NOT NULL,
  `hasSend` TINYINT(1) UNSIGNED ZEROFILL NOT NULL,
  `msg_to` TEXT NOT NULL,
  `msg_from` TEXT NOT NULL,
  `message` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_operations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_operations` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_operations` (
  `id` CHAR(32) NOT NULL,
  `name` TEXT NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_operation2role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_operation2role` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_operation2role` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `opt_time` DATETIME NOT NULL,
  `isActive` TINYINT(1) NOT NULL,
  `b_operations_id` CHAR(4) NOT NULL,
  `b_roles_id` INT(10) UNSIGNED ZEROFILL NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_b_operation2role_b_operations1_idx` (`b_operations_id` ASC) VISIBLE,
  INDEX `fk_b_operation2role_b_roles1_idx` (`b_roles_id` ASC) VISIBLE,
  CONSTRAINT `fk_b_operation2role_b_operations1`
    FOREIGN KEY (`b_operations_id`)
    REFERENCES `bohemian`.`b_operations` (`id`),
  CONSTRAINT `fk_b_operation2role_b_roles1`
    FOREIGN KEY (`b_roles_id`)
    REFERENCES `bohemian`.`b_roles` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_settings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_settings` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_settings` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` CHAR(32) NOT NULL,
  `s_value` TEXT NOT NULL,
  `enable_date` DATE NOT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `operater` TEXT NOT NULL,
  `opt_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_account_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_account_group` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_account_group` (
  `code` CHAR(4) NOT NULL,
  `description` TEXT NULL DEFAULT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_characteristic`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_characteristic` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_characteristic` (
  `code` VARCHAR(30) NOT NULL,
  `name` TEXT NOT NULL,
  `is_optional` TINYINT(1) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_characteristic_value`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_characteristic_value` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_characteristic_value` (
  `code` VARCHAR(30) NOT NULL,
  `name` TEXT NOT NULL,
  `sap_characteristic_code` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `name_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_sap_characteristic_value_sap_characteristic1_idx` (`sap_characteristic_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_characteristic_value_sap_characteristic1`
    FOREIGN KEY (`sap_characteristic_code`)
    REFERENCES `bohemian`.`sap_characteristic` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_clazz`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_clazz` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_clazz` (
  `code` VARCHAR(18) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_clazz_and_character`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_clazz_and_character` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_clazz_and_character` (
  `sap_clazz_code` VARCHAR(18) NOT NULL,
  `sap_characteristic_code` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`sap_clazz_code`, `sap_characteristic_code`),
  INDEX `fk_sap_clazz_and_chart_sap_characteristic1_idx` (`sap_characteristic_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_clazz_and_chart_sap_characteristic1`
    FOREIGN KEY (`sap_characteristic_code`)
    REFERENCES `bohemian`.`sap_characteristic` (`code`),
  CONSTRAINT `fk_sap_clazz_and_chart_sap_clazz1`
    FOREIGN KEY (`sap_clazz_code`)
    REFERENCES `bohemian`.`sap_clazz` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_currency`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_currency` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_currency` (
  `code` CHAR(3) NOT NULL,
  `name` TEXT NOT NULL,
  `rate` DOUBLE(10,5) NOT NULL,
  `is_reserved` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_customer_class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_customer_class` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_customer_class` (
  `code` CHAR(2) NOT NULL COMMENT '01:直销\n02:经销商',
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) INVISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_industry_code`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_industry_code` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_industry_code` (
  `code` VARCHAR(10) NOT NULL COMMENT 'terminal shop level: incustry code',
  `name` TEXT NOT NULL,
  `is_forDealer` TINYINT(1) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_customer` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_customer` (
  `code` VARCHAR(10) NOT NULL,
  `change_date` DATE NOT NULL,
  `name` TEXT NOT NULL,
  `address` TEXT NULL DEFAULT NULL,
  `sap_customer_class_code` CHAR(2) NOT NULL,
  `sap_industry_code_code` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `number_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_sap_customer_sap_customer_class1_idx` (`sap_customer_class_code` ASC) VISIBLE,
  INDEX `fk_sap_customer_sap_industry_code1_idx` (`sap_industry_code_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_customer_sap_customer_class1`
    FOREIGN KEY (`sap_customer_class_code`)
    REFERENCES `bohemian`.`sap_customer_class` (`code`),
  CONSTRAINT `fk_sap_customer_sap_industry_code1`
    FOREIGN KEY (`sap_industry_code_code`)
    REFERENCES `bohemian`.`sap_industry_code` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_sales_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_sales_type` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_sales_type` (
  `code` CHAR(2) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `idsap_sales_type_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_incoterms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_incoterms` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_incoterms` (
  `code` CHAR(3) NOT NULL,
  `name` TEXT NOT NULL,
  `sap_sales_type_code` CHAR(2) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_sap_incoterms_sap_sales_type1_idx` (`sap_sales_type_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_incoterms_sap_sales_type1`
    FOREIGN KEY (`sap_sales_type_code`)
    REFERENCES `bohemian`.`sap_sales_type` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_industry`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_industry` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_industry` (
  `code` VARCHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_industry_and_customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_industry_and_customer` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_industry_and_customer` (
  `sap_customer_code` VARCHAR(16) NOT NULL,
  `sap_industry_code` VARCHAR(4) NOT NULL,
  PRIMARY KEY (`sap_customer_code`, `sap_industry_code`),
  UNIQUE INDEX `sap_customer_code_and_industry_code_UNIQUE` (`sap_customer_code` ASC, `sap_industry_code` ASC) VISIBLE,
  INDEX `fk_sap_industry_and_customer_sap_customer1_idx` (`sap_customer_code` ASC) VISIBLE,
  INDEX `fk_sap_industry_and_customer_sap_industry1_idx` (`sap_industry_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_industry_and_customer_sap_customer1`
    FOREIGN KEY (`sap_customer_code`)
    REFERENCES `bohemian`.`sap_customer` (`code`),
  CONSTRAINT `fk_sap_industry_and_customer_sap_industry1`
    FOREIGN KEY (`sap_industry_code`)
    REFERENCES `bohemian`.`sap_industry` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_last_updated`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_last_updated` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_last_updated` (
  `code` CHAR(32) NOT NULL,
  `name` TEXT NOT NULL,
  `update_date` DATETIME NOT NULL DEFAULT '2000-01-01 00:00:00',
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_unit_of_measurement`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_unit_of_measurement` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_unit_of_measurement` (
  `code` VARCHAR(3) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`b_material_group_order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_material_group_order` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_material_group_order` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `id_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_material_groups`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_material_groups` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_material_groups` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  `b_material_group_order_code` CHAR(4) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_sap_material_groups_b_material_group_order1_idx` (`b_material_group_order_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_material_groups_b_material_group_order1`
    FOREIGN KEY (`b_material_group_order_code`)
    REFERENCES `bohemian`.`b_material_group_order` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_materials`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_materials` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_materials` (
  `code` VARCHAR(18) NOT NULL,
  `description` TEXT NOT NULL,
  `is_configurable` TINYINT(1) NOT NULL,
  `is_purchased` TINYINT(1) NOT NULL,
  `stand_price` DECIMAL(13,2) NOT NULL COMMENT '标准价格moving_average_price',
  `opt_time` DATETIME NOT NULL,
  `sap_unit_of_measurement_code` VARCHAR(3) NOT NULL,
  `sap_material_groups_code` CHAR(4) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_sap_materials_sap_unit_of_measurement1_idx` (`sap_unit_of_measurement_code` ASC) VISIBLE,
  INDEX `fk_sap_materials_sap_material_groups1_idx` (`sap_material_groups_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_materials_sap_unit_of_measurement1`
    FOREIGN KEY (`sap_unit_of_measurement_code`)
    REFERENCES `bohemian`.`sap_unit_of_measurement` (`code`),
  CONSTRAINT `fk_sap_materials_sap_material_groups1`
    FOREIGN KEY (`sap_material_groups_code`)
    REFERENCES `bohemian`.`sap_material_groups` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_material_clazz`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_material_clazz` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_material_clazz` (
  `sap_clazz_code` VARCHAR(18) NOT NULL,
  `sap_materials_code` VARCHAR(18) NOT NULL,
  PRIMARY KEY (`sap_clazz_code`, `sap_materials_code`),
  INDEX `fk_sap_material_clazz_sap_clazz1_idx` (`sap_clazz_code` ASC) VISIBLE,
  INDEX `fk_sap_material_clazz_sap_materials1_idx` (`sap_materials_code` ASC) INVISIBLE,
  CONSTRAINT `fk_sap_material_clazz_sap_clazz1`
    FOREIGN KEY (`sap_clazz_code`)
    REFERENCES `bohemian`.`sap_clazz` (`code`),
  CONSTRAINT `fk_sap_material_clazz_sap_materials1`
    FOREIGN KEY (`sap_materials_code`)
    REFERENCES `bohemian`.`sap_materials` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_price_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_price_type` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_price_type` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_materials_price`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_materials_price` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_materials_price` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `price` DECIMAL(13,2) NOT NULL,
  `sap_price_type_code` CHAR(4) NOT NULL,
  `sap_materials_code` VARCHAR(18) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) INVISIBLE,
  INDEX `fk_sap_materials_price_sap_price_type1_idx` (`sap_price_type_code` ASC) VISIBLE,
  INDEX `fk_sap_materials_price_sap_materials1_idx` (`sap_materials_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_materials_price_sap_materials1`
    FOREIGN KEY (`sap_materials_code`)
    REFERENCES `bohemian`.`sap_materials` (`code`),
  CONSTRAINT `fk_sap_materials_price_sap_price_type1`
    FOREIGN KEY (`sap_price_type_code`)
    REFERENCES `bohemian`.`sap_price_type` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_order_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_order_type` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_order_type` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_sales_office`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_sales_office` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_sales_office` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  `sap_sales_type_code` CHAR(2) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_sap_sales_office_sap_sales_type1_idx` (`sap_sales_type_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_sales_office_sap_sales_type1`
    FOREIGN KEY (`sap_sales_type_code`)
    REFERENCES `bohemian`.`sap_sales_type` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_sales_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_sales_group` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_sales_group` (
  `code` CHAR(3) NOT NULL,
  `name` TEXT NOT NULL,
  `sap_sales_office_code` CHAR(4) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE,
  INDEX `fk_sap_sales_group_sap_sales_office1_idx` (`sap_sales_office_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_sales_group_sap_sales_office1`
    FOREIGN KEY (`sap_sales_office_code`)
    REFERENCES `bohemian`.`sap_sales_office` (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_shipping_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_shipping_type` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_shipping_type` (
  `code` CHAR(2) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_acceptance_approch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_acceptance_approch` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_acceptance_approch` (
  `id` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_receive_confirm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_receive_confirm` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_receive_confirm` (
  `id` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_orders` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_orders` (
  `id` CHAR(32) NOT NULL,
  `sequence_number` CHAR(12) NOT NULL COMMENT '序列号',
  `order_type_code` CHAR(4) NOT NULL COMMENT '//dealer or keyaccount or bulk',
  `create_time` DATETIME NOT NULL COMMENT '创建时间',
  `owner_domain_id` VARCHAR(128) NOT NULL COMMENT 'creator or changed owner/sale code',
  `owner_name` TEXT NOT NULL COMMENT '创建人员姓名',
  `sales_tel` VARCHAR(45) NULL COMMENT 'sales name',
  `contractor_code` VARCHAR(10) NOT NULL COMMENT 'contracter Code/ customer code',
  `contractor_name` TEXT NOT NULL,
  `contractor_class_code` CHAR(2) NOT NULL COMMENT 'sap customer class code: 01/02',
  `contractor_class_name` TEXT NOT NULL COMMENT '经销商/直签',
  `office_code` CHAR(4) NOT NULL COMMENT '销售员所属区域',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `sequence_number_UNIQUE` (`sequence_number` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '订单';


-- -----------------------------------------------------
-- Table `bohemian`.`k_order_version`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_order_version` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_order_version` (
  `id` CHAR(32) NOT NULL,
  `version` VARCHAR(45) NOT NULL COMMENT '版本名称',
  `status` TINYINT(2) NOT NULL COMMENT '0:saved\n1:draft:submit to headquater\n2.approving:BPM\n3.approved',
  `create_time` DATETIME NOT NULL COMMENT '版本创建时间',
  `k_orders_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  UNIQUE INDEX `version_UNIQUE` (`version` ASC) VISIBLE,
  INDEX `fk_k_order_version_k_orders1_idx` (`k_orders_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_order_version_k_orders1`
    FOREIGN KEY (`k_orders_id`)
    REFERENCES `bohemian`.`k_orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_contract`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_contract` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_contract` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sequence_number` CHAR(12) NOT NULL,
  `PartyA_code` CHAR(10) NOT NULL,
  `PartyA_name` TEXT NOT NULL,
  `partyA_mail` TEXT NULL DEFAULT NULL,
  `amount_on_contract` DECIMAL(10,2) NOT NULL,
  `delivery_days_after_prepay` SMALLINT(6) NULL DEFAULT NULL,
  `client_name` TEXT NOT NULL,
  `install_location` TEXT NOT NULL,
  `quality_stand` VARCHAR(45) NULL DEFAULT NULL,
  `k_receive_confirm_id` CHAR(4) NULL DEFAULT NULL,
  `k_acceptance_approch_id` CHAR(4) NULL DEFAULT NULL,
  `settlement` TEXT NULL DEFAULT NULL,
  `paryA_address` TEXT NULL DEFAULT NULL,
  `invoice_address` TEXT NULL DEFAULT NULL,
  `broker` TEXT NULL DEFAULT NULL,
  `invoice_receiver` TEXT NULL DEFAULT NULL,
  `invoice_tel` TEXT NULL DEFAULT NULL,
  `invoice_post_code` CHAR(6) NULL DEFAULT NULL,
  `company_tel` TEXT NULL DEFAULT NULL,
  `bank_name` TEXT NULL DEFAULT NULL,
  `account_number` TEXT NULL DEFAULT NULL,
  `k_order_version_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_contract_k_acceptance_approch1_idx` (`k_acceptance_approch_id` ASC) VISIBLE,
  INDEX `fk_k_contract_k_receive_confirm1_idx` (`k_receive_confirm_id` ASC) VISIBLE,
  INDEX `fk_k_contract_k_order_version1_idx` (`k_order_version_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_contract_k_acceptance_approch1`
    FOREIGN KEY (`k_acceptance_approch_id`)
    REFERENCES `bohemian`.`k_acceptance_approch` (`id`),
  CONSTRAINT `fk_k_contract_k_receive_confirm1`
    FOREIGN KEY (`k_receive_confirm_id`)
    REFERENCES `bohemian`.`k_receive_confirm` (`id`),
  CONSTRAINT `fk_k_contract_k_order_version1`
    FOREIGN KEY (`k_order_version_id`)
    REFERENCES `bohemian`.`k_order_version` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_contacter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_contacter` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_contacter` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `linkman` TEXT NOT NULL,
  `tel_number` TEXT NOT NULL,
  `id_number` VARCHAR(18) NULL DEFAULT NULL,
  `k_contract_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_contact_k_contract1_idx` (`k_contract_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_contact_k_contract1`
    FOREIGN KEY (`k_contract_id`)
    REFERENCES `bohemian`.`k_contract` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_order_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_order_info` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_order_info` (
  `id` CHAR(32) NOT NULL,
  `last_operator` VARCHAR(128) NOT NULL COMMENT '最后操作人',
  `last_opt_time` DATETIME NOT NULL COMMENT '最后操作时间',
  `customer_name` TEXT NOT NULL COMMENT '//店名 customer name',
  `is_reformed` TINYINT(1) NULL COMMENT '是否是改造店',
  `is_convenient_store` TINYINT(1) NULL COMMENT '是否是便利店',
  `is_new` TINYINT(1) NULL COMMENT '是不是新店',
  `terminal_industry_code` VARCHAR(10) NULL COMMENT '终端店面的insustray code',
  `terminal_industry_code_name` TEXT NULL COMMENT '终端店面的insustray code的名字',
  `body_discount` DOUBLE(3,3) NULL COMMENT '柜体折扣',
  `main_discount` DOUBLE(3,3) NULL COMMENT '机身折扣',
  `install_term_code` VARCHAR(4) NULL COMMENT '安装code',
  `install_term_name` TEXT NULL COMMENT '安装方式名称',
  `receive_term_code` VARCHAR(4) NULL COMMENT '接货方式名称code',
  `receive_term_name` TEXT NULL COMMENT '接货方式名称',
  `contactor_1_id` VARCHAR(18) NULL COMMENT '第一联系人身份证',
  `contactor_1_tel` VARCHAR(16) NULL COMMENT '第一联系人电话',
  `contactor_2_id` VARCHAR(18) NULL COMMENT '第二联系人身份证',
  `contactor_2_tel` VARCHAR(16) NULL COMMENT '第二联系人电话',
  `contactor_3_id` VARCHAR(18) NULL COMMENT '第三联系人身份证',
  `contactor_3_tel` VARCHAR(16) NULL COMMENT '第三联系人电话',
  `freight` DECIMAL(13,2) NULL COMMENT '运费合计',
  `warranty` INT NULL COMMENT '保修周期',
  `currency_code` VARCHAR(3) NULL COMMENT '外币code',
  `currency_name` TEXT NULL COMMENT '外币名称',
  `exchange` DOUBLE(10,5) NULL COMMENT '汇率',
  `contract_amount` DECIMAL(13,2) NULL COMMENT '原合同金额',
  `contract_rmb_amount` DECIMAL(13,2) NULL COMMENT '合同人民币金额',
  `sales_type` CHAR(2) NULL COMMENT '销售类型',
  `tax_rate` DOUBLE NULL COMMENT '税率',
  `incoterm_code` VARCHAR(45) NULL COMMENT '贸易条件code',
  `incoterm_name` TEXT NULL COMMENT '贸易条件名称',
  `incoterm_contect` TEXT NULL COMMENT '贸易条件',
  `office_code` VARCHAR(45) NULL COMMENT '表单里的大区code',
  `office_name` TEXT NULL COMMENT '大区名称',
  `group_code` VARCHAR(45) NULL COMMENT '中心code',
  `group_name` TEXT NULL COMMENT '中心名称',
  `transfer_type_code` VARCHAR(45) NULL COMMENT '运输类型代码',
  `transfer_type_name` TEXT NULL COMMENT '运输类型名称',
  `is_term1` TINYINT(1) NULL COMMENT '柜体控制阀门件是否甲供',
  `is_term2` TINYINT(1) NULL COMMENT '分体柜是否远程监控',
  `is_term3` TINYINT(1) NULL COMMENT '立体柜是否在地下室',
  `comments` TEXT NULL,
  PRIMARY KEY USING BTREE (`id`),
  UNIQUE INDEX `id_UNIQUE` USING BTREE (`id`) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
ROW_FORMAT = DYNAMIC;


-- -----------------------------------------------------
-- Table `bohemian`.`k_forms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_forms` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_forms` (
  `id` CHAR(32) NOT NULL,
  `earliest_delivery_date` DATE NULL COMMENT '最早交付时间',
  `earliest_product_date` DATE NULL COMMENT '最早生产时间',
  `comments` TEXT NULL COMMENT '备注',
  `operator` VARCHAR(128) NOT NULL COMMENT '最后操作人',
  `type` TINYINT(2) NOT NULL DEFAULT 0 COMMENT '最后的操作类型\n0:订单\n1：工程\n2：B2C',
  `opt_time` DATETIME NOT NULL COMMENT '最后操作时间',
  `k_order_info_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_forms_k_order_info1_idx` (`k_order_info_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_forms_k_order_info1`
    FOREIGN KEY (`k_order_info_id`)
    REFERENCES `bohemian`.`k_order_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '订单行项目总表';


-- -----------------------------------------------------
-- Table `bohemian`.`k_speical_order_application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_speical_order_application` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_speical_order_application` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `applyer` VARCHAR(128) NOT NULL,
  `approver` VARCHAR(128) NULL DEFAULT NULL,
  `apply_time` DATETIME NOT NULL,
  `approval_time` DATETIME NULL,
  `apply_status` TINYINT(2) NOT NULL COMMENT '0: 新建\n1：同意\n2：驳回',
  `receive_mail_time` TEXT NOT NULL,
  `contract_time` TEXT NOT NULL,
  `pay_advance_payment_time` TEXT NOT NULL,
  `remark` TEXT NULL,
  `enclosure_path` TEXT NULL,
  `enclosure_name` TEXT NULL,
  `k_order_version_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_speical_order_application_k_order_version1_idx` (`k_order_version_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_speical_order_application_k_order_version1`
    FOREIGN KEY (`k_order_version_id`)
    REFERENCES `bohemian`.`k_order_version` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_item_details`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_item_details` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_item_details` (
  `id` CHAR(32) NOT NULL,
  `row_number` INT NOT NULL COMMENT '行号',
  `material_code` VARCHAR(45) NOT NULL COMMENT '物料代码',
  `material_name` TEXT NOT NULL COMMENT '物料名称',
  `is_virtual` TINYINT(1) NOT NULL DEFAULT '0' COMMENT '0:由销售录入的行项目\n1: 非销售部门录入的行项目',
  `is_purchased` TINYINT(1) NOT NULL COMMENT '物料属性：1. 采购 0：生产',
  `quantity` INT NULL COMMENT '数量',
  `measure_unit_code` VARCHAR(3) NULL COMMENT '物料销售单位代码',
  `sale_amount` DECIMAL(13,2) NULL COMMENT '产品实卖金额',
  `transfter_price` DECIMAL(13,2) NULL COMMENT '产品转移价',
  `standard_price` DECIMAL(13,2) NOT NULL COMMENT '移动平均价，即成本价格',
  `b2c_estimation_amount` DECIMAL(13,2) NULL COMMENT 'bc2评估价成本',
  `b2c_estimation_cost` DECIMAL(13,2) NULL COMMENT 'bc2预估成本',
  `b2c_comments` TEXT NULL COMMENT 'B2C备注',
  `material_group_code` VARCHAR(45) NOT NULL COMMENT '物料类型代码\nsap_material_group',
  `material_group_name` TEXT NOT NULL COMMENT '物料类型代码\nsap_material_group',
  `discount` DOUBLE(3,3) NULL COMMENT '折扣',
  `item_category` VARCHAR(45) NOT NULL COMMENT '行项目类别',
  `item_requirement_plan` VARCHAR(45) NOT NULL COMMENT '需求计划',
  `k_forms_id` CHAR(32) NOT NULL,
  `address` TEXT NULL COMMENT '运输目的地',
  `volume_cube` DECIMAL(13,2) NULL COMMENT '体积',
  `freight` DECIMAL(13,2) NULL COMMENT '运费',
  `retail_price` DECIMAL(13,2) NULL COMMENT '零售价格',
  `delievery_date` DATE NULL COMMENT '发货日期日期',
  `comments` TEXT NULL COMMENT '备注，位于配置页面',
  `special_need` TEXT NULL COMMENT '特殊备注',
  `mosaic_image` TEXT NULL COMMENT '拼接图备注',
  `attached_image` TEXT NULL COMMENT '拼接图附件',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_item_details_k_forms1_idx` (`k_forms_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_item_details_k_forms1`
    FOREIGN KEY (`k_forms_id`)
    REFERENCES `bohemian`.`k_forms` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
COMMENT = '订单行项目表，每行记录代表一个行项目';


-- -----------------------------------------------------
-- Table `bohemian`.`sap_installation_terms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_installation_terms` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_installation_terms` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_receive_terms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_receive_terms` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_receive_terms` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_transfer_terms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_transfer_terms` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_transfer_terms` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_bpm_dicision`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_bpm_dicision` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_bpm_dicision` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `approved_main_diccount` DOUBLE(3,3) NOT NULL,
  `approved_body_discount` DOUBLE(3,3) NOT NULL,
  `is_passed` TINYINT(1) NOT NULL,
  `k_order_info_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_bpm_dicision_k_order_info1_idx` (`k_order_info_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_bpm_dicision_k_order_info1`
    FOREIGN KEY (`k_order_info_id`)
    REFERENCES `bohemian`.`k_order_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;


-- -----------------------------------------------------
-- Table `bohemian`.`k_parent_order_version`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_parent_order_version` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_parent_order_version` (
  `opt_time` DATETIME NOT NULL,
  `k_order_version_id` CHAR(32) NOT NULL COMMENT '当前版本',
  `k_order_version_id_parent` CHAR(32) NOT NULL COMMENT '前版本',
  `k_order_info_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`k_order_version_id`, `k_order_version_id_parent`),
  INDEX `fk_k_parent_order_version_k_order_version2_idx` (`k_order_version_id_parent` ASC) VISIBLE,
  INDEX `fk_k_parent_order_version_k_order_info1_idx` (`k_order_info_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_parent_order_version_k_order_version1`
    FOREIGN KEY (`k_order_version_id`)
    REFERENCES `bohemian`.`k_order_version` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_k_parent_order_version_k_order_version2`
    FOREIGN KEY (`k_order_version_id_parent`)
    REFERENCES `bohemian`.`k_order_version` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_k_parent_order_version_k_order_info1`
    FOREIGN KEY (`k_order_info_id`)
    REFERENCES `bohemian`.`k_order_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '订单版本';


-- -----------------------------------------------------
-- Table `bohemian`.`k_order_support_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_order_support_info` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_order_support_info` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `contract_number` VARCHAR(45) NOT NULL COMMENT '合同号',
  `opterator_domain_id` VARCHAR(128) NOT NULL COMMENT '支持经理id',
  `opt_time` DATETIME NOT NULL COMMENT '最后操作时间',
  `k_orders_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_order_support_info_k_orders1_idx` (`k_orders_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_order_support_info_k_orders1`
    FOREIGN KEY (`k_orders_id`)
    REFERENCES `bohemian`.`k_orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '支持经理给付项';


-- -----------------------------------------------------
-- Table `bohemian`.`k_item_b2c`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_item_b2c` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_item_b2c` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `cost` DECIMAL(13,2) NOT NULL COMMENT 'B2C成本',
  `opt_time` DATETIME NOT NULL COMMENT '最后修改时间',
  `k_item_details_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_item_b2c_k_item_details1_idx` (`k_item_details_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_item_b2c_k_item_details1`
    FOREIGN KEY (`k_item_details_id`)
    REFERENCES `bohemian`.`k_item_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = 'B2C人员填写的评估成本';


-- -----------------------------------------------------
-- Table `bohemian`.`k_attachment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_attachment` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_attachment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `file_name` TEXT NOT NULL,
  `k_order_info_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_attachment_k_order_info1_idx` (`k_order_info_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_attachment_k_order_info1`
    FOREIGN KEY (`k_order_info_id`)
    REFERENCES `bohemian`.`k_order_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '附件文件名称';


-- -----------------------------------------------------
-- Table `bohemian`.`k_delievery_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_delievery_address` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_delievery_address` (
  `id` CHAR(32) NOT NULL,
  `province_code` VARCHAR(6) NOT NULL,
  `province_name` TEXT NOT NULL,
  `city_code` VARCHAR(6) NOT NULL,
  `city_name` TEXT NOT NULL,
  `distinct_code` VARCHAR(6) NOT NULL,
  `distinct_name` TEXT NOT NULL,
  `address` TEXT NOT NULL,
  `k_order_info_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_delievery_address_k_order_info1_idx` (`k_order_info_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_delievery_address_k_order_info1`
    FOREIGN KEY (`k_order_info_id`)
    REFERENCES `bohemian`.`k_order_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '送达地址';


-- -----------------------------------------------------
-- Table `bohemian`.`k_characteristics`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_characteristics` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_characteristics` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `key_code` VARCHAR(45) NOT NULL COMMENT '选定的特征代码',
  `value_code` VARCHAR(45) NOT NULL COMMENT '选定的特征值的代码',
  `k_item_details_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_characteristics_k_item_details1_idx` (`k_item_details_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_characteristics_k_item_details1`
    FOREIGN KEY (`k_item_details_id`)
    REFERENCES `bohemian`.`k_item_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = '物料选定的特征';


-- -----------------------------------------------------
-- Table `bohemian`.`k_attached_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_attached_info` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_attached_info` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_date_of_production` DATE NULL COMMENT '最早生产开始日期',
  `date_of_on_store` DATE NULL COMMENT '入库时间',
  `lead_time` INT UNSIGNED NULL COMMENT '生产/采购周期',
  `opt_time` DATETIME NOT NULL,
  `k_item_details_id` CHAR(32) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  INDEX `fk_k_attached_info_k_item_details1_idx` (`k_item_details_id` ASC) VISIBLE,
  CONSTRAINT `fk_k_attached_info_k_item_details1`
    FOREIGN KEY (`k_item_details_id`)
    REFERENCES `bohemian`.`k_item_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin
COMMENT = 'sap返回的相关信息';


-- -----------------------------------------------------
-- Table `bohemian`.`sap_payment_term_bidding_plan`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_payment_term_bidding_plan` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_payment_term_bidding_plan` (
  `code` VARCHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  `is_payment_term` TINYINT(1) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_order_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_order_type` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_order_type` (
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_order_type_and_customer_class`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_order_type_and_customer_class` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_order_type_and_customer_class` (
  `sap_order_type_code` CHAR(4) NOT NULL,
  `sap_customer_class_code` CHAR(2) NOT NULL,
  INDEX `fk_sap_order_type_and_customer_class_sap_order_type1_idx` (`sap_order_type_code` ASC) VISIBLE,
  INDEX `fk_sap_order_type_and_customer_class_sap_customer_class1_idx` (`sap_customer_class_code` ASC) VISIBLE,
  PRIMARY KEY (`sap_order_type_code`, `sap_customer_class_code`),
  CONSTRAINT `fk_sap_order_type_and_customer_class_sap_order_type1`
    FOREIGN KEY (`sap_order_type_code`)
    REFERENCES `bohemian`.`sap_order_type` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sap_order_type_and_customer_class_sap_customer_class1`
    FOREIGN KEY (`sap_customer_class_code`)
    REFERENCES `bohemian`.`sap_customer_class` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_currency_sale_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_currency_sale_type` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_currency_sale_type` (
  `sap_sales_type_code` CHAR(2) NOT NULL,
  `sap_currency_code` CHAR(3) NOT NULL,
  PRIMARY KEY (`sap_sales_type_code`, `sap_currency_code`),
  INDEX `fk_sap_currency_sale_type_sap_currency1_idx` (`sap_currency_code` ASC) VISIBLE,
  UNIQUE INDEX `sap_sales_type_code_currency_code_UNIQUE` (`sap_sales_type_code` ASC, `sap_currency_code` ASC) VISIBLE,
  CONSTRAINT `fk_sap_currency_sale_type_sap_sales_type1`
    FOREIGN KEY (`sap_sales_type_code`)
    REFERENCES `bohemian`.`sap_sales_type` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sap_currency_sale_type_sap_currency1`
    FOREIGN KEY (`sap_currency_code`)
    REFERENCES `bohemian`.`sap_currency` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_industry_price`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_industry_price` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_industry_price` (
  `sap_industry_code` VARCHAR(4) NOT NULL,
  `sap_materials_price_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`sap_industry_code`, `sap_materials_price_id`),
  INDEX `fk_sap_industry_price_sap_materials_price1_idx` (`sap_materials_price_id` ASC) VISIBLE,
  CONSTRAINT `fk_sap_industry_price_sap_industry1`
    FOREIGN KEY (`sap_industry_code`)
    REFERENCES `bohemian`.`sap_industry` (`code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sap_industry_price_sap_materials_price1`
    FOREIGN KEY (`sap_materials_price_id`)
    REFERENCES `bohemian`.`sap_materials_price` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bohemian`.`k_configure_material`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_configure_material` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_configure_material` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `k_item_details_id` CHAR(32) NOT NULL,
  `material_code` VARCHAR(45) NOT NULL,
  `price` DECIMAL(13,2) NOT NULL,
  INDEX `fk_table1_k_item_details1_idx` (`k_item_details_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE,
  CONSTRAINT `fk_table1_k_item_details1`
    FOREIGN KEY (`k_item_details_id`)
    REFERENCES `bohemian`.`k_item_details` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `bohemian` ;

-- -----------------------------------------------------
-- Placeholder table for view `bohemian`.`user_operation_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bohemian`.`user_operation_view` (`id` INT, `user_id` INT, `user_mail` INT, `user_identity` INT, `user_isActive` INT, `role_id` INT, `role_name` INT, `attached_code` INT, `attached_name` INT, `operation_id` INT, `operation_name` INT);

-- -----------------------------------------------------
-- Placeholder table for view `bohemian`.`k_material_info_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bohemian`.`k_material_info_view` (`price_type_code` INT, `price_type_name` INT, `price` INT, `code` INT, `description` INT, `is_configurable` INT, `is_purchased` INT, `stand_price` INT, `sap_unit_of_measurement_code` INT, `unit_name` INT, `sap_material_groups_code` INT, `group_name` INT);

-- -----------------------------------------------------
-- View `bohemian`.`user_operation_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`user_operation_view`;
DROP VIEW IF EXISTS `bohemian`.`user_operation_view` ;
USE `bohemian`;
CREATE  OR REPLACE VIEW user_operation_view AS
    SELECT 
        CONCAT(user_id, '_', role_id, '_', operation_id) AS id,
        user_id,
        u.user_mail AS user_mail,
        u.user_identity AS user_identity,
        u.isActive AS user_isActive,
        role_id,
        role_name,
        attached_code,
        attached_name,
        operation_id,
        operation_name
    FROM
        (SELECT 
            bo.b_users_id AS user_id,
                bo.b_roles_id AS role_id,
                r.NAME AS role_name,
                bo.attached_code AS attached_code,
                bo.attached_name AS attached_name
        FROM
            (SELECT 
            b.id AS id,
                b.b_roles_id AS b_roles_id,
                b.b_users_id AS b_users_id,
                s.CODE AS attached_code,
                s.NAME AS attached_name
        FROM
            b_application_of_rolechange b
        LEFT JOIN sap_sales_office s ON b.attached_code = s.CODE) bo
        LEFT JOIN b_roles r ON bo.b_roles_id = r.id) ars,
        (SELECT 
            r1.id AS id,
                o1.id AS operation_id,
                o1.NAME AS operation_name
        FROM
            b_roles r1, b_operation2role x1, b_operations o1
        WHERE
            r1.id = x1.b_roles_id
                AND x1.b_operations_id = o1.id) rxo,
        b_users u
    WHERE
        ars.role_id = rxo.id
            AND ars.user_id = u.id
    ORDER BY user_id , role_id , operation_id ASC;

-- -----------------------------------------------------
-- View `bohemian`.`k_material_info_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_material_info_view`;
DROP VIEW IF EXISTS `bohemian`.`k_material_info_view` ;
USE `bohemian`;
CREATE  OR REPLACE VIEW `k_material_info_view` AS
    SELECT 
        t.code AS price_type_code,
        t.name AS price_type_name,
        p.price,
        m.code,
        m.description,
        m.is_configurable,
        m.is_purchased,
        m.stand_price,
        m.sap_unit_of_measurement_code,
        unit.name AS unit_name,
        m.sap_material_groups_code,
        g.name AS group_name
    FROM
        sap_materials m
            LEFT JOIN
        sap_material_groups g ON m.sap_material_groups_code = g.code
            LEFT JOIN
        sap_unit_of_measurement unit ON unit.code = m.sap_unit_of_measurement_code
            RIGHT JOIN
        sap_materials_price p ON p.sap_materials_code = m.code
            LEFT JOIN
        sap_price_type t ON t.code = p.sap_price_type_code;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_currency`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_currency` (`code`, `name`, `rate`, `is_reserved`) VALUES ('RMB', '人民币', 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_customer_class`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_customer_class` (`code`, `name`) VALUES ('01', '直销');
INSERT INTO `bohemian`.`sap_customer_class` (`code`, `name`) VALUES ('02', '经销商');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_industry_code`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_industry_code` (`code`, `name`, `is_forDealer`) VALUES ('unknow', '未知', 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_sales_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_sales_type` (`code`, `name`) VALUES ('10', '内销');
INSERT INTO `bohemian`.`sap_sales_type` (`code`, `name`) VALUES ('20', '出口');
INSERT INTO `bohemian`.`sap_sales_type` (`code`, `name`) VALUES ('30', '冷库');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_industry`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_industry` (`code`, `name`) VALUES ('1', '大润发');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_unit_of_measurement`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('EA', '个/卷/件');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('G', '克');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('KG', '公斤');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('TO', '吨');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('CM', '厘米');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('M', '米');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('KM', '千米');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('M2', '平方米');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('ML', '毫升');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('L', '升');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('M3', '立方米');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('ZA', '个/卷/件');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('SZ', '套');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('ROL', '卷');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('Q6', '扇');
INSERT INTO `bohemian`.`sap_unit_of_measurement` (`code`, `name`) VALUES ('BOT', '瓶');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`b_material_group_order`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`b_material_group_order` (`code`, `name`) VALUES ('T101', '机柜');
INSERT INTO `bohemian`.`b_material_group_order` (`code`, `name`) VALUES ('T102', '机组');
INSERT INTO `bohemian`.`b_material_group_order` (`code`, `name`) VALUES ('T103', '冷凝器');
INSERT INTO `bohemian`.`b_material_group_order` (`code`, `name`) VALUES ('T104', '冷风机');
INSERT INTO `bohemian`.`b_material_group_order` (`code`, `name`) VALUES ('T105', '散件');
INSERT INTO `bohemian`.`b_material_group_order` (`code`, `name`) VALUES ('T106', '其它');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_material_groups`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA01', '整机柜', 'T101');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA02', '分体柜', 'T101');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA03', '并联机组', 'T102');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA04', '其他机组（CDU）', 'T102');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA05', '冷风机', 'T103');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA06', '冷凝器', 'T103');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA07', '电控柜', 'T101');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA08', '冷库', 'T101');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA09', '侧板', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('FA11', '虚拟物料', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('SA', '总装', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('SB', '钣金', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('SC', '喷粉', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('SD', '发泡', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('SE', '辅料加工', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('SG', '配置类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('SH', '焊接件', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('SI', '半成品其它', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R01', '金属类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R02', '塑料类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R03', '橡胶、辅料类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R04', '电缆类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R15', '实木板类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R05', '包装印刷类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R06', '标准紧固类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R07', '系统类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R08', '系统、组件、部件类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R10', '外协类(外协加工费、熏蒸费）', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R14', '玻璃类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R40', '电器类', 'T104');
INSERT INTO `bohemian`.`sap_material_groups` (`code`, `name`, `b_material_group_order_code`) VALUES ('R83', '焊条', 'T104');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_price_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZH01', '零售价');
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZH02', '年采价 ');
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZH03', '客户折扣');
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZH05', '实卖价 ');
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZH06', '转移价 ');
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZH07', '转移加价百分比');
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZH10', '运费预估');
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZH11', '服务外包预估');
INSERT INTO `bohemian`.`sap_price_type` (`code`, `name`) VALUES ('ZHCS', '内部价格');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_order_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_order_type` (`code`, `name`) VALUES ('ZH0D', '经销商订单');
INSERT INTO `bohemian`.`sap_order_type` (`code`, `name`) VALUES ('ZH0T', '大客户订单');
INSERT INTO `bohemian`.`sap_order_type` (`code`, `name`) VALUES ('ZH0M', '备货订单');

COMMIT;


-- -----------------------------------------------------
-- Data for table `bohemian`.`sap_order_type_and_customer_class`
-- -----------------------------------------------------
START TRANSACTION;
USE `bohemian`;
INSERT INTO `bohemian`.`sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0D', '01');
INSERT INTO `bohemian`.`sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0T', '02');
INSERT INTO `bohemian`.`sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0M', '01');
INSERT INTO `bohemian`.`sap_order_type_and_customer_class` (`sap_order_type_code`, `sap_customer_class_code`) VALUES ('ZH0M', '02');

COMMIT;

