CREATE DATABASE IF NOT EXISTS `order_board` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `order_board`;

CREATE TABLE IF NOT EXISTS `client` (
  `id`               BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email`            TEXT       NOT NULL,
  `address`          TEXT                DEFAULT NULL,
  `phone`            TEXT                DEFAULT NULL,
  `client_name`      TEXT       NOT NULL,
  `client_last_name` TEXT                DEFAULT NULL,
  `skype`            TEXT                DEFAULT NULL,
  `create_date`      TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `admin`            BOOLEAN             DEFAULT 0,
  `active`           BOOLEAN             DEFAULT 0,
  `moderator`        BOOLEAN             DEFAULT 0,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS `order` (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `title`       TEXT       NOT NULL,
  `note`        TEXT       NOT NULL,
  `price`       TEXT                DEFAULT NULL,
  `id_client`   BIGINT(20) NOT NULL,
  `location`    TEXT       NOT NULL,
  `create_date` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;