/*
SQLyog Community v13.3.0 (64 bit)
MySQL - 8.0.40 : Database - poslasticarnica
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`poslasticarnica` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `poslasticarnica`;

/*Table structure for table `kolac` */

DROP TABLE IF EXISTS `kolac`;

CREATE TABLE `kolac` (
  `sifraKolaca` bigint NOT NULL AUTO_INCREMENT,
  `naziv` varchar(255) NOT NULL,
  `opis` text NOT NULL,
  `cena` double NOT NULL,
  PRIMARY KEY (`sifraKolaca`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `kolac` */

insert  into `kolac`(`sifraKolaca`,`naziv`,`opis`,`cena`) values 
(1,'Čokoladna torta','Bogata torta sa čokoladnim prelivom i kremom.',450),
(2,'Voćna pita','Pita sa mešavinom svežeg sezonskog voća.',280),
(3,'Vanilice','Keksići sa filom od džema od kajsije.',200),
(4,'Limun torta','Osvežavajuća torta sa limunom i belom čokoladom',380),
(5,'Plazma torta','Torta sa plazma keksom i filom od čokolade',350),
(6,'Trileće','Kolač natopljen mlekom sa karamel prelivom',300),
(7,'Cheesecake','Kolač sa krem sirom, keks korom i voćem',400),
(8,'Tiramisu','Italijanski desert sa piškotama, kafom i mascarpone filom.',350),
(9,'Čoko mafin','Mekani kolač sa komadićima tamne čokolade.',220),
(10,'Moskva šnit','Elegantna torta od kora sa orasima i voćem.',480),
(13,'Dubai jagode','Jagode prelivene mlecnom cokoladom, sa pistac kremom i kadaifom.',250),
(15,'Jabuka kolac','sdhcsk',300);

/*Table structure for table `kupac` */

DROP TABLE IF EXISTS `kupac`;

CREATE TABLE `kupac` (
  `idKupca` bigint NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(255) NOT NULL,
  `mesto` bigint NOT NULL,
  PRIMARY KEY (`idKupca`),
  KEY `mesto` (`mesto`),
  CONSTRAINT `kupac_ibfk_1` FOREIGN KEY (`mesto`) REFERENCES `mesto` (`idMesta`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `kupac` */

insert  into `kupac`(`idKupca`,`imePrezime`,`mesto`) values 
(1,'Jovan Jovanovic',2),
(2,'Nikola Nikolic',3),
(3,'Jelena Jelic',6),
(4,'Mila Milic',11),
(5,'Djordje Djordjevic',5),
(6,'Uros Urosevic',11),
(7,'Milos Milosevic',1),
(16,'Nenad Nenadovic',13),
(17,'Una Ilic',14);

/*Table structure for table `mesto` */

DROP TABLE IF EXISTS `mesto`;

CREATE TABLE `mesto` (
  `idMesta` bigint NOT NULL AUTO_INCREMENT,
  `nazivMesta` varchar(255) NOT NULL,
  PRIMARY KEY (`idMesta`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `mesto` */

insert  into `mesto`(`idMesta`,`nazivMesta`) values 
(1,'Beograd'),
(2,'Novi Sad'),
(3,'Nis'),
(4,'Kragujevac'),
(5,'Subotica'),
(6,'Sombor'),
(7,'Pozarevac'),
(8,'Zajecar'),
(11,'Uzice'),
(12,'Vranje'),
(13,'Krusevac'),
(14,'London');

/*Table structure for table `poslasticar` */

DROP TABLE IF EXISTS `poslasticar`;

CREATE TABLE `poslasticar` (
  `idPoslasticar` bigint NOT NULL AUTO_INCREMENT,
  `imePrezime` varchar(255) NOT NULL,
  `datumRodjenja` timestamp NOT NULL,
  `korisnickoIme` varchar(100) NOT NULL,
  `sifra` varchar(100) NOT NULL,
  PRIMARY KEY (`idPoslasticar`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `poslasticar` */

insert  into `poslasticar`(`idPoslasticar`,`imePrezime`,`datumRodjenja`,`korisnickoIme`,`sifra`) values 
(1,'Marko Markovic','1985-04-12 00:00:00','marko85','pass123'),
(2,'Ivana Ivanovic','1990-09-25 00:00:00','ivana90','tajna456'),
(3,'Petar Petrovic','1982-12-01 00:00:00','petar82','sifra789');

/*Table structure for table `poslasticarsmena` */

DROP TABLE IF EXISTS `poslasticarsmena`;

CREATE TABLE `poslasticarsmena` (
  `poslasticar` bigint NOT NULL,
  `smena` bigint NOT NULL,
  `datumPoslasticarSmena` date NOT NULL,
  PRIMARY KEY (`poslasticar`,`smena`),
  KEY `smena` (`smena`),
  CONSTRAINT `poslasticarsmena_ibfk_1` FOREIGN KEY (`poslasticar`) REFERENCES `poslasticar` (`idPoslasticar`),
  CONSTRAINT `poslasticarsmena_ibfk_2` FOREIGN KEY (`smena`) REFERENCES `smena` (`rbSmene`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `poslasticarsmena` */

/*Table structure for table `racun` */

DROP TABLE IF EXISTS `racun`;

CREATE TABLE `racun` (
  `idRacuna` bigint NOT NULL AUTO_INCREMENT,
  `cena` double NOT NULL,
  `obradjen` tinyint(1) NOT NULL DEFAULT '0',
  `poslasticar` bigint NOT NULL,
  `kupac` bigint NOT NULL,
  PRIMARY KEY (`idRacuna`),
  KEY `poslasticar` (`poslasticar`),
  KEY `kupac` (`kupac`),
  CONSTRAINT `racun_ibfk_1` FOREIGN KEY (`poslasticar`) REFERENCES `poslasticar` (`idPoslasticar`),
  CONSTRAINT `racun_ibfk_2` FOREIGN KEY (`kupac`) REFERENCES `kupac` (`idKupca`),
  CONSTRAINT `racun_chk_1` CHECK ((`cena` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `racun` */

insert  into `racun`(`idRacuna`,`cena`,`obradjen`,`poslasticar`,`kupac`) values 
(14,350,0,2,4),
(16,2880,1,2,5),
(17,1410,0,2,1),
(18,1300,0,2,2),
(19,2000,0,2,3),
(20,960,1,2,6),
(21,1560,0,2,2),
(22,1650,0,2,3),
(23,1560,0,2,6),
(24,2540,1,2,2),
(25,2540,1,2,2),
(26,2540,1,2,2),
(27,1050,1,1,4),
(28,680,0,1,16),
(29,2480,0,2,5),
(30,2420,0,2,2),
(31,840,0,2,2);

/*Table structure for table `smena` */

DROP TABLE IF EXISTS `smena`;

CREATE TABLE `smena` (
  `rbSmene` bigint NOT NULL AUTO_INCREMENT,
  `nazivSmene` varchar(255) NOT NULL,
  `pocetakSmene` time NOT NULL,
  `krajSmene` time NOT NULL,
  PRIMARY KEY (`rbSmene`),
  CONSTRAINT `smena_chk_1` CHECK ((`pocetakSmene` < `krajSmene`))
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `smena` */

insert  into `smena`(`rbSmene`,`nazivSmene`,`pocetakSmene`,`krajSmene`) values 
(1,'Jutarnja','06:00:00','14:00:00'),
(2,'Popodnevna','14:00:00','22:00:00'),
(3,'Medju','12:00:00','16:00:00'),
(4,'Prva2','07:00:00','15:00:00'),
(5,'Smena1','11:00:00','22:00:00'),
(6,'Smena3','10:00:00','22:00:00');

/*Table structure for table `stavkaracuna` */

DROP TABLE IF EXISTS `stavkaracuna`;

CREATE TABLE `stavkaracuna` (
  `rbSR` bigint NOT NULL AUTO_INCREMENT,
  `kolicina` bigint NOT NULL,
  `cenaSR` double NOT NULL,
  `racun` bigint NOT NULL,
  `kolac` bigint NOT NULL,
  PRIMARY KEY (`rbSR`,`racun`),
  KEY `racun` (`racun`),
  KEY `kolac` (`kolac`),
  CONSTRAINT `stavkaracuna_ibfk_1` FOREIGN KEY (`racun`) REFERENCES `racun` (`idRacuna`),
  CONSTRAINT `stavkaracuna_ibfk_2` FOREIGN KEY (`kolac`) REFERENCES `kolac` (`sifraKolaca`),
  CONSTRAINT `stavkaracuna_chk_1` CHECK ((`cenaSR` > 0)),
  CONSTRAINT `stavkaracuna_chk_2` CHECK ((`kolicina` > 0))
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `stavkaracuna` */

insert  into `stavkaracuna`(`rbSR`,`kolicina`,`cenaSR`,`racun`,`kolac`) values 
(29,2,450,18,1),
(30,1,400,18,7),
(34,3,350,16,8),
(35,1,280,16,2),
(36,4,300,16,6),
(37,1,350,16,5),
(38,1,450,17,1),
(39,1,200,17,3),
(40,2,380,17,4),
(42,2,480,20,10),
(43,1,450,21,1),
(44,2,380,21,4),
(45,1,350,21,8),
(46,3,350,22,5),
(47,2,300,22,6),
(48,3,220,23,9),
(49,3,300,23,6),
(54,3,200,19,3),
(55,4,350,19,5),
(56,5,280,24,2),
(57,3,380,24,4),
(58,5,280,25,2),
(59,3,380,25,4),
(60,5,280,26,2),
(61,3,380,26,4),
(62,1,350,14,5),
(63,3,350,27,5),
(64,1,380,28,4),
(65,1,300,28,6),
(66,5,350,29,5),
(67,1,380,29,4),
(68,1,350,29,5),
(69,3,840,30,2),
(70,3,1140,30,4),
(71,2,440,30,9),
(72,3,840,31,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
