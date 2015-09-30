CREATE DATABASE IF NOT EXISTS `jean_taxi_service` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `jean_taxi_service`;
-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: jean_taxi_service
-- ------------------------------------------------------
-- Server version	5.6.25-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id`               BIGINT(20) NOT NULL AUTO_INCREMENT,
  `email`            TEXT       NOT NULL,
  `address`          TEXT,
  `phone`            TEXT,
  `second_phone`     TEXT,
  `third_phone`      TEXT,
  `client_name`      TEXT       NOT NULL,
  `client_last_name` TEXT,
  `skype`            TEXT,
  `create_date`      TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `password`         TEXT       NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client_grant`
--

DROP TABLE IF EXISTS `client_grant`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client_grant` (
  `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_client` BIGINT(20) NOT NULL,
  `admin`     TINYINT(1)          DEFAULT '0',
  `active`    TINYINT(1)          DEFAULT '0',
  `moderator` TINYINT(1)          DEFAULT '0',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_client`) REFERENCES `client` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client_grant`
--

LOCK TABLES `client_grant` WRITE;
/*!40000 ALTER TABLE `client_grant` DISABLE KEYS */;
/*!40000 ALTER TABLE `client_grant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discount`
--

DROP TABLE IF EXISTS `discount`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discount`
--

LOCK TABLES `discount` WRITE;
/*!40000 ALTER TABLE `discount` DISABLE KEYS */;
/*!40000 ALTER TABLE `discount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order` (
  `id`             BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_client`      BIGINT(20) NOT NULL,
  `title`          TEXT,
  `note`           TEXT       NOT NULL,
  `price`          TEXT,
  `create_date`    TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `active`         TINYINT(1)          DEFAULT '0',
  `begin_address`  TEXT       NOT NULL,
  `house_number`   TEXT,
  `porch_number`   INT(11)             DEFAULT NULL,
  `on_performance` TINYINT(1)          DEFAULT '0',
  `accomplished`   TINYINT(1)          DEFAULT '0',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_client`) REFERENCES `client` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_address`
--

DROP TABLE IF EXISTS `order_address`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_address` (
  `id`                       BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_order`                 BIGINT(20) NOT NULL,
  `destination_address`      TEXT       NOT NULL,
  `destination_date`         TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `destination_house_number` TEXT,
  `destination_porch_number` TEXT,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_order`) REFERENCES `order` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_address`
--

LOCK TABLES `order_address` WRITE;
/*!40000 ALTER TABLE `order_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id`          BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_client`   BIGINT(20) NOT NULL,
  `note`        TEXT       NOT NULL,
  `create_date` TIMESTAMP  NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `client_name` TEXT       NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`),
  FOREIGN KEY (`id_client`) REFERENCES `client` (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'jean_taxi_service'
--

--
-- Dumping routines for database 'jean_taxi_service'
--
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2015-09-30 10:24:51
