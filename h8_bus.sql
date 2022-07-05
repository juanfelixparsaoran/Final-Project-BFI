-- Adminer 4.8.1 MySQL 10.4.24-MariaDB dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

SET NAMES utf8mb4;

DROP TABLE IF EXISTS `agency`;
CREATE TABLE `agency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `details` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `owner_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8y56hykf78k5u3wmutny52wcf` (`owner_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `agency` (`id`, `code`, `details`, `name`, `owner_user_id`) VALUES
(1,	'BDL',	'PT Angkasa Muda',	'Angkot Karang',	1);

DROP TABLE IF EXISTS `bus`;
CREATE TABLE `bus` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `capacity` int(11) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `make` varchar(255) DEFAULT NULL,
  `agency_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK64nil2xxuhqde813s57dp1n9t` (`agency_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `bus` (`id`, `capacity`, `code`, `make`, `agency_id`) VALUES
(1,	20,	'BDL01',	'20',	1),
(2,	15,	'BDL02',	'15',	1);

DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) DEFAULT NULL,
  `description` varchar(200) NOT NULL,
  `type` varchar(20) NOT NULL,
  `script` varchar(1000) NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT current_timestamp(),
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `flyway_schema_history` (`installed_rank`, `version`, `description`, `type`, `script`, `checksum`, `installed_by`, `installed_on`, `execution_time`, `success`) VALUES
(1,	'1',	'<< Flyway Baseline >>',	'BASELINE',	'<< Flyway Baseline >>',	NULL,	'root',	'2022-07-05 14:24:45',	0,	1),
(2,	'1.1',	'insert agency',	'SQL',	'V1.1__insert_agency.sql',	492839345,	'root',	'2022-07-05 14:24:46',	5,	1),
(3,	'1.2',	'insert stop',	'SQL',	'V1.2__insert_stop.sql',	-297623919,	'root',	'2022-07-05 14:24:46',	4,	1),
(4,	'1.3',	'insert trip',	'SQL',	'V1.3__insert_trip.sql',	937919157,	'root',	'2022-07-05 14:24:46',	5,	1),
(5,	'1.4',	'insert role',	'SQL',	'V1.4__insert_role.sql',	-555065487,	'root',	'2022-07-05 14:24:46',	5,	1),
(6,	'1.5',	'insert tripschedule',	'SQL',	'V1.5__insert_tripschedule.sql',	-2032994882,	'root',	'2022-07-05 14:24:46',	4,	1),
(7,	'1.6',	'insert bus',	'SQL',	'V1.6__insert_bus.sql',	-323711555,	'root',	'2022-07-05 14:24:46',	4,	1);

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `roles` (`id`, `name`) VALUES
(1,	'ROLE_USER'),
(2,	'ROLE_ADMIN');

DROP TABLE IF EXISTS `stop`;
CREATE TABLE `stop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `stop` (`id`, `code`, `detail`, `name`) VALUES
(1,	'1-2',	'Teluk',	'Karang'),
(2,	'1-8',	'Cimeng',	'Sukaraja 1-8'),
(3,	'1-3',	'Raja BasaI',	'Kedaton 1-3'),
(4,	'1-2',	'Sukabumi',	'Sukarame 1-2'),
(5,	'1-10',	'Tanjung Senang',	'Jati Agung 1-10');

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cancellable` bit(1) DEFAULT NULL,
  `journey_date` varchar(255) DEFAULT NULL,
  `seat_number` int(11) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `trip_schedule_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdvt57mcco3ogsosi97odw563o` (`user_id`),
  KEY `FKfhudhsxbslgtmbrbkd3uak4ha` (`trip_schedule_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `ticket` (`id`, `cancellable`, `journey_date`, `seat_number`, `user_id`, `trip_schedule_id`) VALUES
(1,	CONV('1', 2, 10) + 0,	'2022-07-06',	2,	2,	3);

DROP TABLE IF EXISTS `trip`;
CREATE TABLE `trip` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `fare` int(11) NOT NULL,
  `journey_time` int(11) NOT NULL,
  `agency_id` bigint(20) DEFAULT NULL,
  `bus_id` bigint(20) DEFAULT NULL,
  `dest_stop_id` bigint(20) DEFAULT NULL,
  `source_stop_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKab03gksoo5t3lo12ga3ixnykk` (`agency_id`),
  KEY `FKptvi61dd1hao1yig3in0gvcjs` (`bus_id`),
  KEY `FK1evbxrekvflflkfscj2i0fwv0` (`dest_stop_id`),
  KEY `FK5ln8w8n974euslk976dh6x7k5` (`source_stop_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `trip` (`id`, `fare`, `journey_time`, `agency_id`, `bus_id`, `dest_stop_id`, `source_stop_id`) VALUES
(1,	15000,	15,	1,	1,	4,	1),
(2,	15000,	2,	1,	1,	4,	2),
(3,	15000,	3,	1,	1,	2,	3),
(4,	15000,	5,	1,	1,	4,	4),
(5,	15000,	4,	1,	1,	2,	5),
(6,	15000,	7,	1,	1,	3,	6);

DROP TABLE IF EXISTS `trip_schedule`;
CREATE TABLE `trip_schedule` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `available_seats` int(11) NOT NULL,
  `trip_date` varchar(255) DEFAULT NULL,
  `trip_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKaqjflucdvoypakmjxtb7n2mmm` (`trip_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `trip_schedule` (`id`, `available_seats`, `trip_date`, `trip_id`) VALUES
(1,	10,	'2022-07-05',	1),
(2,	20,	'2022-07-06',	2),
(3,	15,	'2022-07-04',	3);

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) DEFAULT NULL,
  `first_name` varchar(120) DEFAULT NULL,
  `last_name` varchar(120) DEFAULT NULL,
  `mobile_number` varchar(120) DEFAULT NULL,
  `password` varchar(120) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `user` (`id`, `email`, `first_name`, `last_name`, `mobile_number`, `password`, `username`) VALUES
(1,	'juan@gmail.com',	'Juas',	'Felx',	'086728391029',	'$2a$10$GVw9X/kYLmormpIoFcyzbus2V.H6pCH9HkuFYzAdDA8QgbG2g5JVi',	'wipeqy8'),
(2,	'user@gmail.com',	'User',	'Dummy',	'086728391029',	'$2a$10$XUJwMEoA57gMYZGI2OKHaOZ/ELi2iAh/qll0rBKBbzQern8L0cmg.',	'user1');

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` bigint(20) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKh8ciramu9cc9q3qcqiv4ue8a6` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

INSERT INTO `user_roles` (`user_id`, `role_id`) VALUES
(1,	2),
(2,	1);

-- 2022-07-05 14:28:26
