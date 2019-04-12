/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 10.1.8-MariaDB-log : Database - test1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test1` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test1`;

/*Table structure for table `cell_phone` */

DROP TABLE IF EXISTS `cell_phone`;

CREATE TABLE `cell_phone` (
  `cellphone_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `brand_name` varchar(128) DEFAULT NULL,
  `serial_str` varchar(64) DEFAULT NULL,
  `factory_name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`cellphone_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `cell_phone` */

insert  into `cell_phone`(`cellphone_id`,`user_id`,`brand_name`,`serial_str`,`factory_name`) values (3,4,'华为Meta20','abcdefghijklmn','华为'),(4,4,'华为Meta20','abcdefghijklmn','华为');

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (11);

/*Table structure for table `id_card` */

DROP TABLE IF EXISTS `id_card`;

CREATE TABLE `id_card` (
  `card_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `idstr` varchar(64) DEFAULT NULL,
  `addr` varchar(512) DEFAULT NULL,
  `birthday` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`card_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `id_card` */

insert  into `id_card`(`card_id`,`user_id`,`idstr`,`addr`,`birthday`) values (3,4,'1234567890','渔港路85号','1990-01-01 00:01:00');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(64) DEFAULT NULL,
  `nick_name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`user_id`,`user_name`,`password`,`nick_name`) values (4,'damoqiongqiu','12345678','momosang222'),(5,'damoqiongqiu','12345678','大漠穷秋'),(7,'damoqiongqiu','12345678','大漠穷秋');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
