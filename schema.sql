CREATE DATABASE test;
USE test;

CREATE TABLE IF NOT EXISTS `project` (
  `id` int(6) unsigned NOT NULL,
  `title` varchar(200) NOT NULL,
  `description` varchar(200) NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `employee` (
  `id` int(6) unsigned NOT NULL,
  `firstname` varchar(200) NOT NULL,
  `lastname` varchar(200) NOT NULL,
  `position_id` int(6) NOT NULL,
  `contact_info` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8; 

CREATE TABLE IF NOT EXISTS `position` (
  `id` int(6) unsigned NOT NULL,
  `title` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `projectemployee` (
  `id` int(6) unsigned NOT NULL,
  `project_id` int(6) unsigned NOT NULL,
  `employee_id` int(6) unsigned NOT NULL,
  `position_id` int(6) unsigned NOT NULL,
  `start` date NOT NULL,
  `end` date NOT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;

INSERT INTO `project` (`id`, `title`, `description`, `start`, `end`) VALUES
  ('1', 'test1', 'Earth wind and fire', '2018-05-01','2018-05-31'),
  ('2', 'test2', 'Raindow', '2018-01-01','2018-03-31'),
  ('3', 'test3', 'spectrum ZX 80', '2017-05-01','2019-05-31'),
  ('4', 'test4', 'blablacar', '2018-04-01','2018-07-31'); 

INSERT INTO `employee` (`id`, `firstname`, `lastname`, `position_id`, `contact_info`) VALUES
  ('1', 'John', 'Dow', 1,'cell phone 888888888'),
  ('2', 'Jane', 'Dow', 3,'cell phone 777777777'),
  ('3', 'Rob', 'Born', 1,'cell phone 999999998'),
  ('4', 'Kale', 'Bow', 2,'cell phone 123456789'),
  ('5', 'Bob', 'Lobb', 2,'cell phone 000000001'),
  ('6', 'Todd', 'Willson', 3,'cell phone 010203040'),
  ('7', 'Tiger', 'Woods', 3,'cell phone 506070809'),
  ('8', 'Janny', 'Panny', 3,'cell phone 192634875'),
  ('9', 'Manny', 'Hart', 2,'cell phone 753248619'),
  ('10', 'Gil', 'Orlow', 2,'cell phone 915738246'),
  ('11', 'Glen', 'Werymon', 4,'cell phone 863214795'),
  ('12', 'Flat', 'Man', 3,'cell phone 486217935');

INSERT INTO `position`(`id`, `title`) VALUES
  ('1', 'PM'),
  ('2', 'JavaDev'),
  ('3', 'QASpecialist'),
  ('4', 'DatabaseDev'); 

INSERT INTO `projectemployee` (`id`, `project_id`, `employee_id`, `position_id`, `start`, `end`) VALUES
  ('1', 1, 1, 1, '2018-05-01','2018-05-31'),
  ('2', 1, 2, 3, '2018-05-01','2018-05-31'),
  ('3', 1, 4, 2, '2018-05-01','2018-05-31'),
  ('4', 1, 5, 2, '2018-05-01','2018-05-31'),
  ('5', 1, 10, 2, '2018-05-01','2018-05-31'),
  ('6', 1, 7, 3, '2018-05-01','2018-05-31'),
  ('7', 2, 3, 1, '2018-01-01','2018-01-31'),
  ('8', 2, 5, 2, '2018-01-01','2018-01-31'),
  ('9', 2, 10, 2, '2018-01-01','2018-01-31'),
  ('10', 2, 9, 2, '2018-01-01','2018-01-31'),
  ('11', 2, 11, 2, '2018-01-01','2018-01-31'),
  ('12', 2, 4, 2, '2018-01-01','2018-01-31'), 
  ('13', 2, 12, 3, '2018-01-01','2018-01-31'),
  ('14', 2, 1, 1, '2018-02-01','2018-03-31'),
  ('15', 2, 12, 3, '2018-02-01','2018-03-31'),
  ('16', 2, 10, 3, '2018-02-01','2018-03-31'),
  ('17', 2, 7, 3, '2018-02-01','2018-03-31'),
  ('18', 3, 1, 1, '2018-04-01','2018-04-30'),
  ('19', 3, 4, 2, '2018-04-01','2018-04-30'),
  ('20', 3, 5, 2, '2018-04-01','2018-04-30'),
  ('21', 3, 10, 2, '2018-04-01','2018-04-30'),
  ('22', 3, 11, 2, '2018-04-01','2018-04-30'),
  ('23', 3, 8, 3, '2018-04-01','2018-05-31'),
  ('24', 3, 6, 3, '2018-04-01','2018-05-31'),
  ('25', 3, 2, 3, '2018-04-01','2018-04-30'),
  ('26', 4, 3, 1, '2017-07-01','2018-05-31'),
  ('27', 4, 4, 2, '2017-07-01','2017-12-31'),
  ('28', 4, 5, 2, '2017-07-01','2017-12-31'),
  ('29', 4, 6, 3, '2017-07-01','2017-12-31'),
  ('30', 4, 7, 3, '2017-07-01','2017-12-31');

 

