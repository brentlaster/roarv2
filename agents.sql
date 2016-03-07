
--
-- Table structure for table `agents`
--
DROP DATABASE IF EXISTS `registry`;
CREATE DATABASE `registry`;
/* DROP TABLE IF EXISTS `agents`; */

USE `registry`;

CREATE TABLE `agents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(30) NOT NULL,
  `SPECIES` varchar(100) NOT NULL,
  `SRVSTART` date NOT NULL,
  `SRVLAST` date DEFAULT NULL,
  `ADVNAME` varchar(40) NOT NULL,
  `ADVTECH` varchar(400) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


--
-- Initial data for table `agents`
--

LOCK TABLES `agents` WRITE;

INSERT INTO `agents` VALUES (1,'Road Runner','bird','1955-01-20','1995-02-15','Wile E. Coyote','ACME product du jour');
INSERT INTO `agents` VALUES (2,'Scooby','dog','1969-05-19','2000-02-11','fake ghosts','mask');
INSERT INTO `agents` VALUES (3,'Perry','platypus','2013-01-20','2015-04-09','H. Doofensmirtz','...inator');
INSERT INTO `agents` VALUES (4,'Mr. Krabs','crab','2010-06-17','2014-07-07','Plankton','various');
INSERT INTO `agents` VALUES (5,'Bugs Bunny','rabbit','1966-05-22','1988-04-15','E. Fudd','wabbit gun');

UNLOCK TABLES;

