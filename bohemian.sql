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
-- -----------------------------------------------------
-- Schema kost
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `kost` ;

-- -----------------------------------------------------
-- Schema kost
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `kost` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
USE `bohemian` ;

-- -----------------------------------------------------
-- Table `bohemian`.`b_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_users` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_users` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_identity` VARCHAR(45) NOT NULL COMMENT 'the user id of domain in loccal LDAP',
  `user_mail` VARCHAR(45) NOT NULL,
  `isActive` TINYINT(1) ZEROFILL NOT NULL COMMENT 'boolean type, mean if user is able to use system',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `idb_Users_UNIQUE` ON `bohemian`.`b_users` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `user_mail_UNIQUE` ON `bohemian`.`b_users` (`user_mail` ASC) VISIBLE;

CREATE UNIQUE INDEX `user_identity_UNIQUE` ON `bohemian`.`b_users` (`user_identity` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`b_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_roles` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_roles` (
  `id` INT ZEROFILL UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` TEXT NOT NULL,
  `isActive` TINYINT(1) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`b_roles` (`id` ASC) VISIBLE;


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
  `isActive` TINYINT(1) NOT NULL,
  `attached_code` CHAR(32) NULL,
  `b_roles_id` INT ZEROFILL UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_b_applicaation_of_rolechange_b_users1`
    FOREIGN KEY (`b_users_id`)
    REFERENCES `bohemian`.`b_users` (`id`),
  CONSTRAINT `fk_b_application_of_rolechange_b_roles1`
    FOREIGN KEY (`b_roles_id`)
    REFERENCES `bohemian`.`b_roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`b_application_of_rolechange` (`id` ASC) VISIBLE;

CREATE INDEX `fk_b_applicaation_of_rolechange_b_users1_idx` ON `bohemian`.`b_application_of_rolechange` (`b_users_id` ASC) VISIBLE;

CREATE INDEX `fk_b_application_of_rolechange_b_roles1_idx` ON `bohemian`.`b_application_of_rolechange` (`b_roles_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`b_notify_infor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_notify_infor` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_notify_infor` (
  `id` BIGINT(20) UNSIGNED NOT NULL,
  `hasSend` TINYINT(1) UNSIGNED ZEROFILL NOT NULL,
  `msg_to` TEXT NOT NULL,
  `msg_from` TEXT NOT NULL,
  `message` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`b_notify_infor` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`b_operations`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_operations` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_operations` (
  `id` CHAR(32) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`b_operations` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`b_operation2role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_operation2role` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_operation2role` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `opt_time` DATETIME NOT NULL,
  `isActive` TINYINT(1) NOT NULL,
  `b_operations_id` CHAR(4) NOT NULL,
  `b_roles_id` INT ZEROFILL UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_b_operation2role_b_operations1`
    FOREIGN KEY (`b_operations_id`)
    REFERENCES `bohemian`.`b_operations` (`id`),
  CONSTRAINT `fk_b_operation2role_b_roles1`
    FOREIGN KEY (`b_roles_id`)
    REFERENCES `bohemian`.`b_roles` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`b_operation2role` (`id` ASC) VISIBLE;

CREATE INDEX `fk_b_operation2role_b_operations1_idx` ON `bohemian`.`b_operation2role` (`b_operations_id` ASC) VISIBLE;

CREATE INDEX `fk_b_operation2role_b_roles1_idx` ON `bohemian`.`b_operation2role` (`b_roles_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`b_settings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`b_settings` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`b_settings` (
  `id` CHAR(32) NOT NULL,
  `s_value` TEXT NOT NULL,
  `enable_date` DATE NOT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `operater` TEXT NOT NULL,
  `opt_time` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`b_settings` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_order_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_order_type` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_order_type` (
  `id` CHAR(4) NOT NULL,
  `code` CHAR(4) NOT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `reserved` TINYINT(1) NOT NULL DEFAULT '0',
  `isActive` TINYINT(1) UNSIGNED ZEROFILL NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `code_UNIQUE` ON `bohemian`.`sap_order_type` (`code` ASC) VISIBLE;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`sap_order_type` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_org_region`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_org_region` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_org_region` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`sap_org_region` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `code_UNIQUE` ON `bohemian`.`sap_org_region` (`code` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_org_location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_org_location` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_org_location` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `code` CHAR(4) NOT NULL,
  `name` TEXT NULL DEFAULT NULL,
  `sap_org_region_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sap_org_location_sap_org_region1`
    FOREIGN KEY (`sap_org_region_id`)
    REFERENCES `bohemian`.`sap_org_region` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`sap_org_location` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `code_UNIQUE` ON `bohemian`.`sap_org_location` (`code` ASC) VISIBLE;

CREATE INDEX `fk_sap_org_location_sap_org_region1_idx` ON `bohemian`.`sap_org_location` (`sap_org_region_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_sales_organization`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_sales_organization` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_sales_organization` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `emp_identify` TEXT NOT NULL,
  `unit_code` VARCHAR(45) NULL DEFAULT NULL,
  `sap_org_location_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_sap_employee_organization_sap_org_location1`
    FOREIGN KEY (`sap_org_location_id`)
    REFERENCES `bohemian`.`sap_org_location` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`sap_sales_organization` (`id` ASC) VISIBLE;

CREATE INDEX `fk_sap_employee_organization_sap_org_location1_idx` ON `bohemian`.`sap_sales_organization` (`sap_org_location_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`sap_update_history`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`sap_update_history` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`sap_update_history` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `start_time` DATETIME NOT NULL,
  `end_time` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`sap_update_history` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_orders`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_orders` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_orders` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `sequence_number` CHAR(12) NOT NULL,
  `create_time` DATETIME NOT NULL,
  `creator` VARCHAR(128) NOT NULL,
  `contract_number` CHAR(10) NULL DEFAULT NULL,
  `order_type` CHAR(4) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_orders` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `number_UNIQUE` ON `kost`.`k_orders` (`sequence_number` ASC) VISIBLE;

CREATE UNIQUE INDEX `order_type_UNIQUE` ON `kost`.`k_orders` (`order_type` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_order_version`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_order_version` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_order_version` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `version` CHAR(2) NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `last_opt_time` DATETIME NOT NULL,
  `k_orders_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_order_version_k_orders1`
    FOREIGN KEY (`k_orders_id`)
    REFERENCES `kost`.`k_orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_order_version` (`id` ASC) VISIBLE;

CREATE UNIQUE INDEX `version_UNIQUE` ON `kost`.`k_order_version` (`version` ASC) VISIBLE;

CREATE INDEX `fk_k_order_version_k_orders1_idx` ON `kost`.`k_order_version` (`k_orders_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`k_form`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_form` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_form` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `seq_num` TINYINT NOT NULL,
  `create_time` DATETIME NOT NULL,
  `k_order_version_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_form_k_order_version1`
    FOREIGN KEY (`k_order_version_id`)
    REFERENCES `kost`.`k_order_version` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`k_form` (`id` ASC) VISIBLE;

CREATE INDEX `fk_k_form_k_order_version1_idx` ON `bohemian`.`k_form` (`k_order_version_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_materials`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_materials` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_materials` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `k_form_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_materials_k_form1`
    FOREIGN KEY (`k_form_id`)
    REFERENCES `bohemian`.`k_form` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_materials` (`id` ASC) VISIBLE;

CREATE INDEX `fk_k_materials_k_form1_idx` ON `kost`.`k_materials` (`k_form_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`k_configurable`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_configurable` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_configurable` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `config_code` VARCHAR(45) NOT NULL,
  `k_materials_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_configurable_k_materials1`
    FOREIGN KEY (`k_materials_id`)
    REFERENCES `kost`.`k_materials` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`k_configurable` (`id` ASC) VISIBLE;

CREATE INDEX `fk_k_configurable_k_materials1_idx` ON `bohemian`.`k_configurable` (`k_materials_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`k_order_info`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_order_info` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_order_info` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`k_order_info` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `bohemian`.`k_order_version`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bohemian`.`k_order_version` ;

CREATE TABLE IF NOT EXISTS `bohemian`.`k_order_version` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `k_order_info_id` INT UNSIGNED NOT NULL,
  `k_order_version_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_order_version_k_order_info1`
    FOREIGN KEY (`k_order_info_id`)
    REFERENCES `bohemian`.`k_order_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_k_order_version_k_order_version1`
    FOREIGN KEY (`k_order_version_id`)
    REFERENCES `kost`.`k_order_version` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `id_UNIQUE` ON `bohemian`.`k_order_version` (`id` ASC) VISIBLE;

CREATE INDEX `fk_k_order_version_k_order_info1_idx` ON `bohemian`.`k_order_version` (`k_order_info_id` ASC) VISIBLE;

CREATE INDEX `fk_k_order_version_k_order_version1_idx` ON `bohemian`.`k_order_version` (`k_order_version_id` ASC) VISIBLE;

USE `kost` ;

-- -----------------------------------------------------
-- Table `kost`.`k_acceptance_approch`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_acceptance_approch` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_acceptance_approch` (
  `id` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_acceptance_approch` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_b2c`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_b2c` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_b2c` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `opterator` VARCHAR(128) NOT NULL,
  `opt_time` DATETIME NOT NULL,
  `statius` TINYINT(2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_b2c` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_receive_confirm`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_receive_confirm` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_receive_confirm` (
  `id` CHAR(4) NOT NULL,
  `name` TEXT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_receive_confirm` (`id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_contract`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_contract` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_contract` (
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
  `k_orders_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_contract_k_acceptance_approch1`
    FOREIGN KEY (`k_acceptance_approch_id`)
    REFERENCES `kost`.`k_acceptance_approch` (`id`),
  CONSTRAINT `fk_k_contract_k_receive_confirm1`
    FOREIGN KEY (`k_receive_confirm_id`)
    REFERENCES `kost`.`k_receive_confirm` (`id`),
  CONSTRAINT `fk_k_contract_k_orders1`
    FOREIGN KEY (`k_orders_id`)
    REFERENCES `kost`.`k_orders` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_contract` (`id` ASC) VISIBLE;

CREATE INDEX `fk_k_contract_k_receive_confirm1_idx` ON `kost`.`k_contract` (`k_receive_confirm_id` ASC) VISIBLE;

CREATE INDEX `fk_k_contract_k_acceptance_approch1_idx` ON `kost`.`k_contract` (`k_acceptance_approch_id` ASC) VISIBLE;

CREATE INDEX `fk_k_contract_k_orders1_idx` ON `kost`.`k_contract` (`k_orders_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_contact` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_contact` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `linkman` TEXT NOT NULL,
  `tel_number` TEXT NOT NULL,
  `id_number` VARCHAR(18) NULL DEFAULT NULL,
  `k_contract_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_contact_k_contract1`
    FOREIGN KEY (`k_contract_id`)
    REFERENCES `kost`.`k_contract` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_contact` (`id` ASC) VISIBLE;

CREATE INDEX `fk_k_contact_k_contract1_idx` ON `kost`.`k_contact` (`k_contract_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_engining`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_engining` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_engining` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `operator` VARCHAR(128) NOT NULL,
  `opt_time` DATETIME NOT NULL,
  `k_order_version_id` INT(10) UNSIGNED NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `cost` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_engining_k_order_version1`
    FOREIGN KEY (`k_order_version_id`)
    REFERENCES `kost`.`k_order_version` (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_engining` (`id` ASC) VISIBLE;

CREATE INDEX `fk_k_engining_k_order_version1_idx` ON `kost`.`k_engining` (`k_order_version_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_product_b2c`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_product_b2c` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_product_b2c` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `k_productions_id` INT(10) UNSIGNED NOT NULL,
  `k_b2c_id` INT(10) UNSIGNED NOT NULL,
  `cost` DECIMAL(10,2) NOT NULL,
  `comment` TEXT NULL DEFAULT NULL,
  `k_order_version_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_table1_k_b2c1`
    FOREIGN KEY (`k_b2c_id`)
    REFERENCES `kost`.`k_b2c` (`id`),
  CONSTRAINT `fk_k_product_b2c_k_order_version1`
    FOREIGN KEY (`k_order_version_id`)
    REFERENCES `kost`.`k_order_version` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_product_b2c` (`id` ASC) VISIBLE;

CREATE INDEX `fk_table1_k_b2c1_idx` ON `kost`.`k_product_b2c` (`k_b2c_id` ASC) VISIBLE;

CREATE INDEX `fk_k_product_b2c_k_order_version1_idx` ON `kost`.`k_product_b2c` (`k_order_version_id` ASC) VISIBLE;


-- -----------------------------------------------------
-- Table `kost`.`k_speical_order_application`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `kost`.`k_speical_order_application` ;

CREATE TABLE IF NOT EXISTS `kost`.`k_speical_order_application` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `applyer` VARCHAR(128) NOT NULL,
  `approver` VARCHAR(128) NULL DEFAULT NULL,
  `apply_time` DATETIME NOT NULL,
  `approval_time` DATETIME NULL DEFAULT NULL,
  `k_order_version_id` INT(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_k_speical_order_application_k_order_version1`
    FOREIGN KEY (`k_order_version_id`)
    REFERENCES `kost`.`k_order_version` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_bin;

CREATE UNIQUE INDEX `id_UNIQUE` ON `kost`.`k_speical_order_application` (`id` ASC) VISIBLE;

CREATE INDEX `fk_k_speical_order_application_k_order_version1_idx` ON `kost`.`k_speical_order_application` (`k_order_version_id` ASC) VISIBLE;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
