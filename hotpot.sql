# Host: localhost  (Version: 5.5.53)
# Date: 2021-06-05 13:45:23
# Generator: MySQL-Front 5.3  (Build 4.234)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "category"
#

CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `store_id` int(11) DEFAULT NULL COMMENT '店铺id',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `valid` tinyint(4) DEFAULT NULL COMMENT '是否使用',
  `sort` tinyint(4) DEFAULT NULL COMMENT '排序',
  `type` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='菜品目录';

#
# Data for table "category"
#

INSERT INTO `category` VALUES (1,1,'牛羊肉',1,1,0),(2,1,'水产',1,2,0),(3,1,'蔬菜',0,4,0),(4,1,'豆制品',1,5,0),(5,1,'主食',1,7,0),(6,1,'火锅',1,8,0),(7,1,'内脏',1,3,0),(12,1,'菌类',1,6,0),(13,1,'外卖套餐',1,1,1),(14,1,'加菜',1,2,1);

#
# Structure for table "chat"
#

CREATE TABLE `chat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `type` tinyint(4) DEFAULT NULL COMMENT '发送方',
  `valid` tinyint(4) DEFAULT NULL COMMENT '是否已读',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='聊天记录';

#
# Data for table "chat"
#

INSERT INTO `chat` VALUES (1,1,'老板还送外卖吗？',1,1,'2021-02-02 00:00:00');

#
# Structure for table "comment"
#

CREATE TABLE `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(300) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `hide` tinyint(3) DEFAULT '0' COMMENT '是否匿名',
  `star` tinyint(3) DEFAULT '5' COMMENT '评分',
  `type` varchar(100) DEFAULT NULL COMMENT '评价类型',
  `back` varchar(300) DEFAULT NULL,
  `status` tinyint(3) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='评论';

#
# Data for table "comment"
#

INSERT INTO `comment` VALUES (1,'这家店真心不错长度测试----长度测试啊实打实大苏打实打实的阿三大苏打','2020-02-01 00:00:00',10002,0,4,'1;5;','长度测试阿三大苏打啊实打实打算啊实打实打算阿三大苏打',1),(2,'这家店真心不错','2020-02-03 00:00:00',10004,0,4,'2;4;',NULL,0),(3,'这家店真心不错','2020-02-05 00:00:00',10003,0,3,'1;2;3;','fsdfsdfsdf',1),(7,'这家店真心不错','2020-02-05 00:00:00',10004,0,3,'1;4;',NULL,0),(8,'这家店真心不错','2020-02-05 00:00:00',10005,0,2,'1;','阿三大苏打',1),(9,'这家店真心不错','2020-02-05 00:00:00',10005,0,4,'1;2;',NULL,0),(10,'这家店真心不错','2020-02-05 00:00:00',10005,0,4,'1;',NULL,0),(11,'这家店真心不错','2020-02-05 00:00:00',10003,0,5,'1;',NULL,0),(12,'我正则评价中','2021-05-30 15:12:05',10002,NULL,5,'1;',NULL,0),(19,'asdasda','2021-05-30 15:25:27',10002,0,5,'1;',NULL,0);

#
# Structure for table "food"
#

CREATE TABLE `food` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` varchar(100) DEFAULT NULL COMMENT '分类id',
  `name` varchar(50) DEFAULT NULL,
  `size` int(11) DEFAULT NULL COMMENT '份量',
  `img_url` varchar(100) DEFAULT NULL COMMENT '图片地址',
  `valid` tinyint(4) DEFAULT NULL COMMENT '是否有效',
  `unit` char(5) DEFAULT NULL COMMENT '计量单位',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `type` tinyint(3) DEFAULT NULL COMMENT '现场or外卖',
  `number` int(11) DEFAULT '0' COMMENT '销量',
  `tj` tinyint(3) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=84 DEFAULT CHARSET=utf8 COMMENT='菜品';

#
# Data for table "food"
#

INSERT INTO `food` VALUES (1,'1','肥牛',800,'http://localhost:8080/hg/image/get?path=001.jpg',0,'克',28,0,1000,0),(2,'1','鲜滑牛肉',800,'http://localhost:8080/hg/image/get?path=002.jpg',1,'克',28,0,890,1),(3,'1','有料牛肉卷',800,'http://localhost:8080/hg/image/get?path=003.jpg',1,'克',28,0,777,1),(4,'1','神户牛肉',800,'http://localhost:8080/hg/image/get?path=004.jpg',1,'克',28,0,668,0),(5,'1','羊仔肉',800,'http://localhost:8080/hg/image/get?path=005.jpg',1,'克',28,0,559,1),(6,'2','大花虾',800,'http://localhost:8080/hg/image/get?path=006.jpg',1,'克',28,0,678,1),(7,'2','大花蟹',800,'http://localhost:8080/hg/image/get?path=007.jpg',1,'克',28,0,123,1),(8,'2','巴沙鱼片',800,'http://localhost:8080/hg/image/get?path=008.jpg',1,'克',28,0,457,0),(9,'2','手打鱼丸',800,'http://localhost:8080/hg/image/get?path=009.jpg',1,'克',28,0,989,0),(10,'3','菠菜',800,'http://localhost:8080/hg/image/get?path=010.jpg',1,'克',28,0,786,0),(11,'3','蕃薯苗',800,'http://localhost:8080/hg/image/get?path=011.jpg',1,'克',12,0,0,0),(12,'3','菜心',800,'http://localhost:8080/hg/image/get?path=012.jpg',1,'克',12,0,111,0),(13,'3','芥兰',800,'http://localhost:8080/hg/image/get?path=013.jpg',1,'克',12,0,0,0),(14,'3','小棠菜',800,'http://localhost:8080/hg/image/get?path=014.jpg',1,'克',12,0,111,0),(15,'3','西洋菜',800,'http://localhost:8080/hg/image/get?path=015.jpg',1,'克',12,0,0,0),(16,'3','油蜜菜',800,'http://localhost:8080/hg/image/get?path=016.jpg',0,'克',12,0,111,0),(17,'3','白菜仔',800,'http://localhost:8080/hg/image/get?path=017.jpg',1,'克',12,0,0,0),(18,'3','大豆芽',800,'http://localhost:8080/hg/image/get?path=018.jpg',1,'克',12,0,0,0),(19,'3','海带',800,'http://localhost:8080/hg/image/get?path=019.jpg',1,'克',12,0,111,0),(20,'3','冬瓜',800,'http://localhost:8080/hg/image/get?path=020.jpg',1,'克',12,0,0,0),(21,'3','蕃茄',800,'http://localhost:8080/hg/image/get?path=021.jpg',1,'克',12,0,0,0),(22,'3','莲藕片',800,'http://localhost:8080/hg/image/get?path=022.jpg',1,'克',12,0,0,0),(23,'3','萝卜',800,'http://localhost:8080/hg/image/get?path=023.jpg',1,'克',12,0,111,0),(24,'3','鲜淮山',800,'http://localhost:8080/hg/image/get?path=024.jpg',1,'克',12,0,111,0),(25,'3','鲜露笋',800,'http://localhost:8080/hg/image/get?path=025.jpg',1,'克',12,0,0,0),(26,'3','土豆',800,'http://localhost:8080/hg/image/get?path=026.jpg',1,'克',12,0,111,0),(27,'3','玉米',800,'http://localhost:8080/hg/image/get?path=027.jpg',1,'克',12,0,0,0),(28,'8','金针菇',800,'http://localhost:8080/hg/image/get?path=028.jpg',1,'克',12,0,0,0),(29,'8','秀珍菇',800,'http://localhost:8080/hg/image/get?path=029.jpg',1,'克',12,0,111,0),(30,'8','百灵菇',800,'http://localhost:8080/hg/image/get?path=030.jpg',1,'克',12,0,111,0),(31,'8','杏鲍菇',800,'http://localhost:8080/hg/image/get?path=031.jpg',1,'克',12,0,111,0),(32,'8','海鲜菇',800,'http://localhost:8080/hg/image/get?path=032.jpg',1,'克',12,0,0,0),(33,'7','毛肚',800,'http://localhost:8080/hg/image/get?path=033.jpg',1,'克',30,0,111,0),(34,'7','鸭肠',800,'http://localhost:8080/hg/image/get?path=034.jpg',1,'克',30,0,0,0),(35,'7','黄喉',800,'http://localhost:8080/hg/image/get?path=035.jpg',1,'克',30,0,111,0),(36,'7','猪脑花',800,'http://localhost:8080/hg/image/get?path=036.jpg',1,'克',30,0,0,0),(37,'7','腰片',800,'http://localhost:8080/hg/image/get?path=037.jpg',1,'克',30,0,0,0),(38,'7','千层肚',800,'http://localhost:8080/hg/image/get?path=038.jpg',1,'克',30,0,111,0),(39,'7','大肠',800,'http://localhost:8080/hg/image/get?path=039.jpg',1,'克',12,0,0,0),(40,'4','豆腐',800,'http://localhost:8080/hg/image/get?path=040.jpg',1,'克',8,0,111,0),(41,'4','豆腐干',800,'http://localhost:8080/hg/image/get?path=041.jpg',1,'克',8,0,0,0),(42,'4','豆皮',800,'http://localhost:8080/hg/image/get?path=042.jpg',1,'克',8,0,111,0),(43,'4','油豆腐',800,'http://localhost:8080/hg/image/get?path=043.jpg',1,'克',8,0,111,0),(44,'4','腐竹',800,'http://localhost:8080/hg/image/get?path=044.jpg',1,'克',8,0,0,0),(45,'5','火锅面',800,'http://localhost:8080/hg/image/get?path=045.jpg',1,'克',8,0,111,0),(46,'5','红薯粉',800,'http://localhost:8080/hg/image/get?path=046.jpg',1,'克',8,0,111,0),(47,'5','宽粉',800,'http://localhost:8080/hg/image/get?path=047.jpg',1,'克',8,0,0,0),(48,'5','粉丝',800,'http://localhost:8080/hg/image/get?path=048.jpg',1,'克',8,0,0,0),(49,'6','红锅',1500,'http://localhost:8080/hg/image/get?path=049.jpg',1,'克',38,0,0,0),(50,'6','白锅',1500,'http://localhost:8080/hg/image/get?path=050.jpg',1,'克',36,0,0,0),(51,'6','鸳鸯锅',1500,'http://localhost:8080/hg/image/get?path=051.jpg',1,'克',42,0,0,0),(53,'13','单人豪华套装',800,'http://localhost:8080/hg/image/get?path=tz1.jpg',1,'克',20,1,1,0),(81,'13','单人朴素套餐',800,'http://localhost:8080/hg/image/get?path=1622347988170_239.jpg',1,'克',15,1,0,0),(82,'13','双人套餐',2000,'http://localhost:8080/hg/image/get?path=1622348047300_403.jpg',1,'克',40,1,1,0),(83,'14','土豆片',300,'http://localhost:8080/hg/image/get?path=1622352552163_936.jpg',1,'克',3,1,1,0);

#
# Structure for table "food_order"
#

CREATE TABLE `food_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `food_id` int(11) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `price` decimal(5,2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `food_id` (`food_id`),
  CONSTRAINT `food_order_ibfk_1` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8 COMMENT='订单菜单';

#
# Data for table "food_order"
#

INSERT INTO `food_order` VALUES (1,2,5,1,28.00),(7,3,4,1,28.00),(8,4,3,2,28.00),(9,7,6,2,28.00),(10,11,6,1,28.00),(12,12,8,1,28.00),(13,8,9,1,28.00),(14,6,6,1,28.00),(15,7,7,1,28.00),(16,8,9,1,28.00),(17,9,6,1,28.00),(18,13,6,1,28.00),(19,14,1,1,28.00),(20,15,2,1,28.00),(21,16,9,1,28.00),(22,17,6,1,28.00),(23,18,8,1,28.00),(24,19,7,1,28.00),(25,20,6,1,28.00),(26,21,5,1,28.00),(27,22,4,1,28.00),(28,23,3,1,28.00),(29,27,53,1,20.00),(31,26,53,1,20.00),(32,28,53,1,20.00),(33,25,53,1,20.00),(34,29,53,1,20.00),(35,30,82,1,40.00),(36,31,82,1,40.00),(39,7,7,1,28.00),(40,10,7,1,28.00),(41,9,7,1,28.00),(42,24,82,1,40.00),(43,32,82,1,40.00),(44,33,82,1,40.00),(47,1,2,1,28.00),(48,1,2,1,28.00),(49,36,5,1,28.00),(50,36,4,1,28.00),(51,37,53,1,20.00),(52,38,5,1,28.00),(53,39,5,1,28.00),(54,40,4,1,28.00),(55,41,83,1,3.00),(56,41,82,1,40.00);

#
# Structure for table "location"
#

CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `content` varchar(100) DEFAULT NULL,
  `telephone` char(11) DEFAULT NULL,
  `name` char(30) DEFAULT NULL,
  `sex` tinyint(3) DEFAULT '0',
  `valid` tinyint(3) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='用户地址';

#
# Data for table "location"
#

INSERT INTO `location` VALUES (1,10002,'四川省成都市双流','15748156466','小张',0,0),(2,10002,'四川省成都市郫县','15946561123','小城',1,0),(14,10002,'四川省成都市双流区顺城街27号','23123123','ttt',NULL,1);

#
# Structure for table "orders"
#

CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `money` decimal(9,4) DEFAULT NULL COMMENT '价格',
  `bz` varchar(300) DEFAULT NULL COMMENT '备注',
  `location_id` int(11) DEFAULT NULL COMMENT '地址',
  `type` tinyint(4) DEFAULT NULL COMMENT '现场/外卖',
  `table_number` int(11) DEFAULT NULL COMMENT '桌号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(11) DEFAULT '1',
  `psy` varchar(50) DEFAULT NULL,
  `psyPhone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='订单';

#
# Data for table "orders"
#

INSERT INTO `orders` VALUES (2,10005,28.0000,NULL,1,1,2,'2021-04-10 16:20:33',3,NULL,NULL),(3,10005,28.0000,NULL,1,1,1,'2021-04-10 16:21:31',2,NULL,NULL),(6,10003,28.0000,'',1,1,1,'2021-04-10 22:14:27',2,NULL,NULL),(7,10004,28.0000,'',1,1,1,'2021-04-10 22:14:27',1,NULL,NULL),(8,10004,28.0000,'',1,1,2,'2021-04-10 22:14:27',3,NULL,NULL),(9,10003,28.0000,'',1,1,3,'2021-01-10 16:22:45',4,NULL,NULL),(10,10003,28.0000,'',1,1,4,'2021-04-10 22:14:27',3,NULL,NULL),(11,10003,28.0000,'问起我',1,1,5,'2021-04-10 22:14:27',2,NULL,NULL),(12,10003,28.0000,'',1,1,6,'2021-05-03 16:22:45',1,NULL,NULL),(13,10004,28.0000,'大苏打实打实',1,1,7,'2021-04-10 22:14:27',3,NULL,NULL),(14,10004,28.0000,'',1,1,8,'2021-04-10 22:14:27',1,NULL,NULL),(15,10004,28.0000,'啊实打实大苏打',1,1,9,'2021-05-04 16:22:45',2,NULL,NULL),(16,10004,28.0000,'啊实打实大苏打',1,1,11,'2021-04-10 22:14:27',3,NULL,NULL),(17,10004,28.0000,'去恶趣味',1,1,12,'2021-04-10 22:14:27',4,NULL,NULL),(18,10004,28.0000,'啊实打实大苏打',1,1,13,'2021-05-01 16:22:45',2,NULL,NULL),(19,10004,28.0000,'',1,1,1,'2021-04-10 22:14:27',0,NULL,NULL),(20,10003,28.0000,'',1,1,1,'2021-04-10 22:14:27',4,NULL,NULL),(21,10003,28.0000,'',1,1,1,'2021-04-10 22:14:27',4,NULL,NULL),(22,10005,28.0000,'',1,2,NULL,'2021-05-15 16:22:45',3,'测试','15848952632'),(23,10005,28.0000,'',1,2,NULL,'2021-04-10 16:22:45',0,NULL,NULL),(24,10005,28.0000,'',1,2,NULL,'2021-03-10 16:22:45',1,NULL,NULL),(25,10005,28.0000,'',1,2,NULL,'2021-04-10 16:22:45',4,'测试','15848952632'),(26,10003,28.0000,'',1,2,NULL,'2021-04-10 16:22:45',4,'测试','15848952632'),(27,10005,28.0000,'',1,2,NULL,'2021-01-10 16:22:45',3,'测试','15848952632'),(28,10005,28.0000,'',1,2,NULL,'2021-04-10 16:22:45',1,NULL,NULL),(29,10003,28.0000,'',1,2,NULL,'2021-04-10 16:22:45',1,NULL,NULL),(30,10003,28.0000,'',1,2,NULL,'2021-01-10 16:22:45',1,'',NULL),(31,10003,28.0000,'',1,2,NULL,'2021-04-10 16:22:45',2,'测试','15848952632'),(32,10003,28.0000,'',1,2,NULL,'2021-04-10 16:22:45',2,'测试','15848952632'),(33,10005,28.0000,'',1,2,NULL,'2021-05-10 16:22:45',2,'测试','15848952632'),(39,10002,28.0000,NULL,NULL,1,2,'2021-05-30 14:31:44',0,NULL,NULL),(40,10002,28.0000,NULL,NULL,1,3,'2021-05-30 14:32:52',4,'',''),(41,10002,43.0000,NULL,1,2,NULL,'2021-05-30 14:47:36',2,'asdas','asd');

#
# Structure for table "picture_index"
#

CREATE TABLE `picture_index` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `location` varchar(200) DEFAULT NULL,
  `type` tinyint(3) DEFAULT NULL,
  `sort` tinyint(3) DEFAULT NULL,
  `valid` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "picture_index"
#

INSERT INTO `picture_index` VALUES (1,'第一张图','http://localhost:8080/hg/image/get?path=1622350339962_217.png',0,2,1),(2,'第二张图','http://localhost:8080/hg/image/get?path=1622350355237_330.png',0,1,1),(3,'第三张图','http://localhost:8080/hg/image/get?path=1622350373488_204.png',0,3,1),(7,'中间图2','http://localhost:8080/hg/image/get?path=1622350379130_423.jpg',1,4,1);

#
# Structure for table "store"
#

CREATE TABLE `store` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '店名',
  `location` varchar(100) DEFAULT NULL COMMENT '店铺地址',
  `js` varchar(300) DEFAULT NULL COMMENT '简介',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='店家';

#
# Data for table "store"
#

INSERT INTO `store` VALUES (1,'小天鹅火锅店','郫县红光西华大学西门','十年老店，值得信赖');

#
# Structure for table "table_info"
#

CREATE TABLE `table_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL,
  `size` tinyint(3) DEFAULT NULL,
  `type` tinyint(3) DEFAULT NULL,
  `location` varchar(100) DEFAULT NULL,
  `status` tinyint(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Data for table "table_info"
#

INSERT INTO `table_info` VALUES (1,1,2,1,'一楼',1),(2,2,6,1,'一楼',1);

#
# Structure for table "user"
#

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openId` varchar(50) DEFAULT NULL,
  `authority` tinyint(1) DEFAULT NULL COMMENT '权限',
  `name` varchar(100) DEFAULT NULL,
  `url` varchar(300) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10006 DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "user"
#

INSERT INTO `user` VALUES (1,NULL,0,NULL,NULL,NULL),(10002,'oEOUu5A8NXAHkmQfZwCsl3GwcVcY',1,'肉你-测试','http://localhost:8080/hg/image/get?path=tx1.jpg','123456'),(10003,'123',0,'柯哀-测试','http://localhost:8080/hg/image/get?path=tx2.jpg','123456'),(10004,'123',0,'阿松-测试','http://localhost:8080/hg/image/get?path=tx3.jpg','123456'),(10005,'123',0,'欧啊-测试','http://localhost:8080/hg/image/get?path=tx4.jpg','123456');
