DROP TABLE IF EXISTS `user`;

--
-- Table structure for table `publisher`
--
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

COMMIT;
