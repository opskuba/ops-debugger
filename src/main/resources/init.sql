CREATE TABLE `im_protocol_audit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bytes` int(11) DEFAULT NULL,
  `channel` tinyint(4) DEFAULT NULL,
  `flag` tinyint(4) DEFAULT NULL,
  `origins` text,
  `session_id` bigint(20) DEFAULT NULL,
  `tag` tinyint(4) DEFAULT NULL,
  `ts` datetime DEFAULT NULL,
  `type` smallint(6) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4