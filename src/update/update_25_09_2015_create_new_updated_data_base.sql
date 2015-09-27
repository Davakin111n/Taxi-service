DROP DATABASE order_board;
CREATE DATABASE IF NOT EXISTS `jean_taxi_service`;
USE `jean_taxi_service`;

CREATE TABLE `client` (
  `id`               BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_grant`         BIGINT(20) NOT NULL,
  `email`            TEXT       NOT NULL,
  `address`          TEXT,
  `phone`            TEXT,
  `second_phone`     TEXT,
  `third_phone`      TEXT,
  `client_name`      TEXT       NOT NULL,
  `client_last_name` TEXT,
  `skype`            TEXT,
  `create_date`      TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `client_grant` (
  `id`        BIGINT(20) NOT NULL,
  `id_client` BIGINT(20) NOT NULL,
  `admin`     TINYINT(1) DEFAULT '0',
  `active`    TINYINT(1) DEFAULT '0',
  `moderator` TINYINT(1) DEFAULT '0',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `order` (
  `id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_client`      BIGINT(20) NOT NULL,
  `id_address`     BIGINT(20) NOT NULL,
  `title`          TEXT                DEFAULT NULL,
  `note`           TEXT       NOT NULL,
  `price`          TEXT,
  `create_date`    TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active`         TINYINT(1)          DEFAULT '0',
  `begin_address`  TEXT       NOT NULL,
  `house_number`   TEXT,
  `porch_number`   INT(11)             DEFAULT NULL,
  `on_performance` TINYINT(1)          DEFAULT '0',
  `accomplished`   TINYINT(1)          DEFAULT '0',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `order_address` (
  `id`                       BIGINT(20) NOT NULL,
  `id_order`                 BIGINT(20) NOT NULL,
  `destination_address`      TEXT       NOT NULL,
  `destination_date`         TIMESTAMP  NOT NULL,
  `destination_house_number` TEXT,
  `destination_porch_number` TEXT,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `review` (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_client`   BIGINT(20) NOT NULL,
  `note`        TEXT       NOT NULL,
  `create_date` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `discount` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_client` BIGINT(20) NOT NULL,
  `type`      TEXT       NOT NULL,
  `name`      TEXT       NOT NULL,
  `note`      TEXT       NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;