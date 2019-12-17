
LOCK TABLES `b_users` WRITE;
/*!40000 ALTER TABLE `b_users` DISABLE KEYS */;
INSERT INTO `b_users` VALUES (2,'st','st@dxc.com','st name',1,'12345678901'),(3,'sales','wang@dxc.com','sales name',1,'12345678901'),(4,'support','support@qhc.com','support name',1,'12345678901'),(5,'b2c','b2c@qhc.com','b2c name',1,'12345678901'),(6,'engineer','enginer@qhc.com','engineer name',1,'12345678901'),(7,'manger','manger@qhc.com','manager name',1,'12345678901'),(8,'admin','admin@qhc.com','admin name',1,'12345678901'),(9,'ads','ads@carrier.com','ads name',1,'');
/*!40000 ALTER TABLE `b_users` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `b_roles`
--

LOCK TABLES `b_roles` WRITE;
/*!40000 ALTER TABLE `b_roles` DISABLE KEYS */;
INSERT INTO `b_roles` VALUES (1,'系统管理员',1),(2,'客户经理',1),(3,'B2C',1),(4,'工程人员',1),(5,'支持经理',1),(6,'区域经理',1),(7,'领导组',1);
/*!40000 ALTER TABLE `b_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `b_application_of_rolechange`
--

LOCK TABLES `b_application_of_rolechange` WRITE;
/*!40000 ALTER TABLE `b_application_of_rolechange` DISABLE KEYS */;
INSERT INTO `b_application_of_rolechange` VALUES (1,'admin','2019-11-07 17:56:24','admin','admin','2019-11-07 17:56:35',2,1,'0841',2),(2,'admin','2019-11-07 17:56:24','admin','admin','2019-11-07 17:56:24',3,1,'0841',2),(3,'admin','2019-11-07 17:56:24','admin','admin','2019-11-07 17:56:24',4,1,'0841',5),(4,'admin','2019-11-07 17:56:24','admin','admin','2019-11-07 17:56:24',5,1,'0841',3),(5,'admin','2019-11-07 17:56:24','admin','admin','2019-11-07 17:56:24',6,1,'0841',4),(6,'admin','2019-11-07 17:56:24','admin','admin','2019-11-07 17:56:24',7,1,'0841',6),(7,'admin','2019-11-13 02:04:14','admin','admin','2019-11-13 02:04:14',9,1,'0841',3);
/*!40000 ALTER TABLE `b_application_of_rolechange` ENABLE KEYS */;
UNLOCK TABLES;

LOCK TABLES `b_operation2role` WRITE;
/*!40000 ALTER TABLE `b_operation2role` DISABLE KEYS */;
INSERT INTO `b_operation2role` VALUES (1,'2019-01-01 00:00:00',1,'1017',2),(2,'2019-01-01 00:00:00',1,'1002',2),(3,'2019-01-01 00:00:00',1,'1001',2),(4,'2019-01-01 00:00:00',1,'1003',2),(5,'2019-01-01 00:00:00',1,'1013',1),(6,'2019-01-01 00:00:00',1,'1010',1),(7,'2019-01-01 00:00:00',1,'1009',1),(8,'2019-01-01 00:00:00',1,'1008',1),(9,'2019-01-01 00:00:00',1,'1004',2),(10,'2019-01-01 00:00:00',1,'1005',2),(11,'2019-01-01 00:00:00',1,'1006',2),(13,'2019-11-11 11:26:05',1,'1016',5),(14,'2019-11-27 11:26:05',1,'1012',6),(15,'2019-11-27 11:26:05',1,'1015',4),(16,'2019-11-27 11:26:05',1,'1014',3),(17,'2019-11-27 15:26:05',1,'1010',7),(18,'2019-11-27 15:26:05',1,'1001',3),(19,'2019-11-27 15:26:05',1,'1011',3),(20,'2019-11-27 15:26:05',1,'1001',5),(21,'2019-11-27 15:26:05',1,'1002',5),(22,'2019-11-27 15:26:05',1,'1018',5),(23,'2019-11-27 15:26:05',1,'1017',5),(24,'2019-12-04 15:26:05',1,'1003',3),(25,'2019-12-04 15:26:05',1,'1003',5);
/*!40000 ALTER TABLE `b_operation2role` ENABLE KEYS */;
UNLOCK TABLES;