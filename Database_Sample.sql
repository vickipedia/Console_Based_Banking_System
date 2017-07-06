-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: bank
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `balance` float DEFAULT '10000',
  `cust_id` int(11) DEFAULT NULL,
  `acct_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`acct_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10184 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (10000,22506,10155),(10000,22506,10156),(14800,22508,10157),(2600,22509,10158),(8300,22510,10159),(9500,22516,10160),(14490,22509,10161),(7760,22509,10162),(10000,22517,10163),(10500,22518,10164),(9440,22518,10165),(10000,22518,10166),(3310,22519,10167),(10000,22519,10168),(15075,22519,10169),(15500,22520,10170),(3490,22520,10171),(10000,22520,10172),(10100,22521,10173),(9900,22521,10174),(8990,22522,10175),(9800,22522,10176),(41750,22523,10177),(21001,22524,10178),(10000,22525,10180),(36739,22526,10181),(175292,22527,10182),(1915,22529,10183);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `name` varchar(20) DEFAULT NULL,
  `chk` int(11) DEFAULT '0',
  `cust_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=22530 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES ('Jack',0,22505),('Mark',0,22507),('Vicknesh Selvamani',0,22511),('Tharun',1,22512),('Vicky',1,22513),('Tamil',0,22514),('Lokesh',0,22515),('Ashwin R',1,22520),('Bill Gates',1,22522),('Sachin T',1,22523),('Rahul Dravid',1,22524),('Donald Trump',1,22526),('Barack Obama',1,22527),('Vicknesh S',1,22528),('Hari',1,22529);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nowtime`
--

DROP TABLE IF EXISTS `nowtime`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `nowtime` (
  `present` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nowtime`
--

LOCK TABLES `nowtime` WRITE;
/*!40000 ALTER TABLE `nowtime` DISABLE KEYS */;
INSERT INTO `nowtime` VALUES ('2017-06-16 16:26:16');
/*!40000 ALTER TABLE `nowtime` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `password`
--

DROP TABLE IF EXISTS `password`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `password` (
  `cust_id` int(11) DEFAULT NULL,
  `epass` varchar(20) DEFAULT NULL,
  `pass_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`pass_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `password`
--

LOCK TABLES `password` WRITE;
/*!40000 ALTER TABLE `password` DISABLE KEYS */;
INSERT INTO `password` VALUES (22506,'qbttxpse',1),(22508,'joejb',2),(22509,'joejb',3),(22510,'joejb',4),(22512,'joejb',5),(22513,'joejb',6),(22513,'difoobj',7),(22516,'qbttxpse',8),(22516,'qbtt',9),(22516,'bcDE56A',10),(22509,'QBtt21',11),(22509,'QBtt21',12),(22509,'EIpo23',13),(22509,'EIpo23',14),(22509,'EIpo23',15),(22509,'QBtt21',16),(22517,'BCbc23',17),(22517,'BCbc23',18),(22517,'BCbc23',19),(22517,'bcBC23',20),(22517,'BCbc23',21),(22517,'BCbc23',22),(22517,'BcBc23',23),(22517,'BcBc23',24),(22509,'QBtt21',25),(22509,'QBtt21',26),(22509,'QBtt21',27),(22509,'QBtt21',28),(22509,'JOejb21',29),(22518,'QBtt21',30),(22518,'QBtt23',31),(22519,'esBW23',32),(22519,'esBW21',33),(22520,'23BCbc',34),(22521,'HBuf23',35),(22521,'cjMM23',36),(22521,'QBtt23',37),(22522,'QBtt23',38),(22523,'tbDI23',39),(22524,'ESbw23',40),(22525,'ntEI23',41),(22526,'VTvt23',42),(22527,'PCbn23',43),(22520,'23bcBC',44),(22524,'QBtt23',45),(22528,'QBtt23',46),(22529,'AxaRf0120',47),(22527,'CBSBDLpcbnb23',48);
/*!40000 ALTER TABLE `password` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transaction` (
  `trans_type` varchar(40) DEFAULT NULL,
  `amt` float DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `acct_no` int(11) DEFAULT NULL,
  `trans_id` int(11) NOT NULL AUTO_INCREMENT,
  `oflag` int(11) DEFAULT '0',
  `sflag` int(11) DEFAULT '0',
  PRIMARY KEY (`trans_id`)
) ENGINE=InnoDB AUTO_INCREMENT=240 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES ('Opening      ',10000,10000,10156,2,0,0),('Opening      ',10000,10000,10157,3,0,0),('Opening      ',10000,10000,10158,4,0,0),('TransferTo10157',5500,4500,10158,5,0,0),('TransferFrom10158',5500,15500,10157,6,0,0),('TransferTo10158',5800,9700,10157,7,0,0),('TransferFrom10157',5800,10300,10158,8,0,0),('TransferTo10157',5100,5200,10158,9,0,0),('TransferFrom10158',5100,14800,10157,10,0,0),('OperationalFee',10,5190,10158,11,1,0),('CashDeposit',1000,6190,10158,12,0,0),('CashDeposit',1000,7190,10158,13,0,0),('CashDeposit',1000,8190,10158,14,0,0),('CashDeposit',1000,9190,10158,15,0,0),('CashDeposit',1000,10190,10158,16,0,0),('CashDeposit',1000,11190,10158,17,0,0),('TransferTo10158',0,11190,10158,18,0,0),('TransferFrom10158',0,11190,10158,19,0,0),('CashDeposit',1000,12190,10158,20,0,0),('TransferTo10158',0,12190,10158,21,0,0),('TransferFrom10158',0,12190,10158,22,0,0),('CashDeposit',1000,13190,10158,23,0,0),('CashDeposit',1000,14190,10158,24,0,0),('ATMWithdrawal',1000,13190,10158,25,0,0),('CashDeposit',1000,14190,10158,26,0,0),('CashDeposit',1000,15190,10158,27,0,0),('Opening      ',10000,10000,10159,28,0,0),('CashDeposit',500,10500,10159,29,0,0),('ATMWithdrawal',200,10300,10159,30,0,0),('ATMWithdrawal',500,9800,10159,31,0,0),('ATMWithdrawal',500,9300,10159,32,0,0),('ATMWithdrawal',500,8800,10159,33,0,0),('ATMWithdrawal',500,8300,10159,34,0,0),('ATMWithdrawal',100,8200,10159,35,0,0),('ATMWithdrawal',50,8150,10159,36,0,0),('ATMWithdrawal',100,8150,10159,39,0,0),('MaintenanceFee',100,8050,10159,40,1,0),('ATMWithdrawal',50,8000,10159,41,0,0),('ATMWithdrawal',50,7950,10159,42,0,0),('ATMWithdrawal',50,7900,10159,43,0,0),('ATMWithdrawal',50,7850,10159,44,0,0),('ATMWithdrawal',50,7800,10159,45,0,0),('Opening      ',10000,10000,10160,46,0,0),('ATMWithdrawal',1000,9000,10160,47,0,0),('CashDeposit',1000,10000,10160,48,0,0),('TransferTo10159',500,9500,10160,49,0,0),('TransferFrom10160',500,8300,10159,50,0,0),('TransferTo22516',5500,9690,10158,51,0,0),('TransferFrom10158',5500,0,22516,52,0,0),('OperationalFee',10,9680,10158,53,1,0),('Opening      ',10000,10000,10161,54,0,0),('Opening      ',10000,10000,10162,55,0,0),('TransferTo10162',10000,0,10161,56,0,0),('TransferFrom10161',10000,20000,10162,57,0,0),('OperationalFee',10,-10,10161,58,1,0),('CashDeposit',1000,990,10161,59,0,0),('CashDeposit',1500,2490,10161,60,0,0),('TransferTo10161',1000,19000,10162,61,0,0),('TransferFrom10162',1000,3490,10161,62,0,0),('TransferTo10161',1000,18000,10162,63,0,0),('TransferFrom10162',1000,4490,10161,64,0,0),('TransferTo10161',1000,17000,10162,65,0,0),('TransferFrom10162',1000,5490,10161,66,0,0),('TransferTo10161',1000,16000,10162,67,0,0),('TransferFrom10162',1000,6490,10161,68,0,0),('TransferTo10161',1000,15000,10162,69,0,0),('TransferFrom10162',1000,7490,10161,70,0,0),('TransferTo10161',1000,14000,10162,71,0,0),('TransferFrom10162',1000,8490,10161,72,0,0),('TransferTo10161',1000,13000,10162,73,0,0),('TransferFrom10162',1000,9490,10161,74,0,0),('TransferTo10161',1000,12000,10162,75,0,0),('TransferFrom10162',1000,10490,10161,76,0,0),('TransferTo10161',1000,11000,10162,77,0,0),('TransferFrom10162',1000,11490,10161,78,0,0),('TransferTo10161',1000,10000,10162,79,0,0),('TransferFrom10162',1000,12490,10161,80,0,0),('TransferTo10161',1000,9000,10162,81,0,0),('TransferFrom10162',1000,13490,10161,82,0,0),('TransferTo10161',1000,8000,10162,83,0,0),('TransferFrom10162',1000,14490,10161,84,0,0),('ATMWithdrawal',7000,2680,10158,85,0,0),('TransferTo10162',10,2670,10158,86,0,0),('TransferFrom10158',10,8010,10162,87,0,0),('TransferTo10162',10,2660,10158,88,0,0),('TransferFrom10158',10,8020,10162,89,0,0),('TransferTo10162',10,2650,10158,90,0,0),('TransferFrom10158',10,8030,10162,91,0,0),('TransferTo10162',10,2640,10158,92,0,0),('TransferFrom10158',10,8040,10162,93,0,0),('TransferTo10162',10,2630,10158,94,0,0),('TransferFrom10158',10,8050,10162,95,0,0),('TransferTo10162',10,2620,10158,96,0,0),('TransferFrom10158',10,8060,10162,97,0,0),('TransferTo10162',10,2610,10158,98,0,0),('TransferFrom10158',10,8070,10162,99,0,0),('TransferTo10162',10,2600,10158,100,0,0),('TransferFrom10158',10,8080,10162,101,0,0),('MaintenanceFee',100,2500,10158,102,1,0),('TransferTo10158',100,7980,10162,103,0,0),('TransferFrom10162',100,2600,10158,104,0,1),('ATMWithdrawal',10,7970,10162,105,0,0),('ATMWithdrawal',10,7960,10162,106,0,0),('ATMWithdrawal',10,7950,10162,107,0,0),('Password changed',0,7950,10162,108,1,0),('Password changed',0,7950,10162,109,1,0),('Password changed',0,7950,10162,110,1,0),('ATMWithdrawal',10,7940,10162,111,0,0),('ATMWithdrawal',10,7930,10162,112,0,0),('ATMWithdrawal',10,7920,10162,113,0,0),('ATMWithdrawal',10,7910,10162,114,0,0),('MaintenanceFee',100,7810,10162,115,1,0),('ATMWithdrawal',10,7800,10162,116,0,0),('ATMWithdrawal',10,7790,10162,117,0,0),('ATMWithdrawal',10,7780,10162,118,0,0),('ATMWithdrawal',10,7770,10162,119,0,0),('ATMWithdrawal',10,7760,10162,120,0,0),('ForcePassChange',0,7760,10162,121,1,0),('Opening      ',10000,10000,10163,122,0,0),('Opening      ',10000,10000,10164,123,0,0),('Opening      ',10000,10000,10165,124,0,0),('Opening      ',10000,10000,10166,125,0,0),('TransferTo10165',5000,5000,10164,126,0,0),('TransferFrom10164',5000,15000,10165,127,0,1),('TransferTo10164',5500,9500,10165,128,0,0),('TransferFrom10165',5500,10500,10164,129,0,1),('OperationalFee',10,9490,10165,130,1,0),('ATMWithdrawal',10,9480,10165,131,0,0),('ATMWithdrawal',10,9470,10165,132,0,0),('ATMWithdrawal',10,9460,10165,133,0,0),('ATMWithdrawal',10,9450,10165,134,0,0),('ATMWithdrawal',10,9440,10165,135,0,0),('ForcePassChange',0,9440,10165,136,1,0),('Opening      ',10000,10000,10167,137,0,0),('Opening      ',10000,10000,10168,138,0,0),('Opening      ',10000,10000,10169,139,0,0),('CashDeposit',5005,15005,10167,140,0,0),('ATMWithdrawal',6000,9005,10167,141,0,0),('TransferTo10169',5075,3930,10167,142,0,0),('TransferFrom10167',5075,15075,10169,143,0,1),('OperationalFee',10,3920,10167,144,1,0),('ATMWithdrawal',100,3820,10167,145,0,0),('ATMWithdrawal',100,3720,10167,146,0,0),('ATMWithdrawal',100,3620,10167,147,0,0),('ATMWithdrawal',100,3520,10167,148,0,0),('ATMWithdrawal',100,3420,10167,149,0,0),('ForcePassChange',0,3420,10167,150,1,0),('ATMWithdrawal',10,3410,10167,151,0,0),('MaintenanceFee',100,3310,10167,152,1,0),('Opening      ',10000,10000,10170,153,0,0),('Opening      ',10000,10000,10171,154,0,0),('Opening      ',10000,10000,10172,155,0,0),('ATMWithdrawal',1000,9000,10171,156,0,0),('TransferTo10170',5500,3500,10171,157,0,0),('TransferFrom10171',5500,15500,10170,158,0,1),('OperationalFee',10,3490,10171,159,1,0),('Opening      ',10000,10000,10173,160,0,0),('Opening      ',10000,10000,10174,161,0,0),('CashDeposit',100,10100,10173,162,0,0),('CashDeposit',100,10200,10173,163,0,0),('CashDeposit',100,10300,10173,164,0,0),('CashDeposit',100,10400,10173,165,0,0),('CashDeposit',100,10500,10173,166,0,0),('ForcePassChange',0,10500,10173,167,1,0),('ATMWithdrawal',100,10400,10173,168,0,0),('ATMWithdrawal',100,9900,10174,169,0,0),('ATMWithdrawal',100,10300,10173,170,0,0),('ATMWithdrawal',100,10200,10173,171,0,0),('ATMWithdrawal',100,10100,10173,172,0,0),('ATMWithdrawal',100,10000,10173,173,0,0),('ATMWithdrawal',100,9900,10173,174,0,0),('ForcePassChange',0,9900,10173,175,1,0),('ATMWithdrawal',100,9800,10173,176,0,0),('ATMWithdrawal',100,9700,10173,177,0,0),('ATMWithdrawal',100,9600,10173,178,0,0),('CashDeposit',500,10100,10173,179,0,0),('Opening      ',10000,10000,10175,180,0,0),('Opening      ',10000,10000,10176,181,0,0),('ATMWithdrawal',100,9900,10175,182,0,0),('ATMWithdrawal',100,9800,10175,183,0,0),('ATMWithdrawal',100,9700,10175,184,0,0),('ATMWithdrawal',100,9600,10175,185,0,0),('ATMWithdrawal',100,9900,10176,186,0,0),('ATMWithdrawal',100,9500,10175,187,0,0),('ATMWithdrawal',100,9400,10175,188,0,0),('ATMWithdrawal',100,9300,10175,189,0,0),('ATMWithdrawal',100,9200,10175,190,0,0),('ATMWithdrawal',100,9800,10176,191,0,0),('ATMWithdrawal',100,9100,10175,192,0,0),('MaintenanceFee',100,9000,10175,193,1,0),('ATMWithdrawal',10,8990,10175,194,0,0),('Opening      ',10000,10000,10177,195,0,0),('CashDeposit',25000,35000,10177,196,0,0),('Opening      ',10000,10000,10178,197,0,0),('CashDeposit',12000,22000,10178,198,0,0),('Opening      ',10000,10000,10179,199,0,0),('Opening      ',10000,10000,10180,200,0,0),('Opening      ',10000,10000,10181,201,0,0),('CashDeposit',10000,20000,10181,202,0,0),('ATMWithdrawal',250,19750,10181,203,0,0),('CashDeposit',17500,37250,10181,204,0,0),('TransferTo10177',6750,30500,10181,205,0,0),('TransferFrom10181',6750,41750,10177,206,0,1),('OperationalFee',10,30490,10181,207,1,0),('TransferTo10178',1,30489,10181,208,0,0),('TransferFrom10181',1,22001,10178,209,0,1),('TransferTo10181',1000,21001,10178,210,0,0),('TransferFrom10178',1000,31489,10181,211,0,1),('Opening       ',10000,10000,10182,212,0,0),('CashDeposit',55626.8,65626.8,10182,213,0,0),('ATMWithdrawal',0.8,65626,10182,214,0,0),('ATMWithdrawal',100,65526,10182,215,0,0),('TransferTo10181',5250,60276,10182,216,0,0),('TransferFrom10182',5250,36739,10181,217,0,1),('OperationalFee',10,60266,10182,218,1,0),('Opening       ',10000,10000,10183,219,0,0),('ATMWithdrawal',8500,1500,10183,220,0,0),('TransferTo10182',100,1400,10183,221,0,0),('TransferFrom10183',100,60366,10182,222,0,1),('CashDeposit',8000,9400,10183,223,0,0),('TransferTo10182',8000,1400,10183,224,0,0),('TransferFrom10183',8000,68366,10182,225,0,1),('OperationalFee',10,1390,10183,226,1,0),('ATMWithdrawal',10,1380,10183,227,0,0),('ATMWithdrawal',100,1280,10183,228,0,0),('CashDeposit',800,2080,10183,229,0,0),('ATMWithdrawal',45,2035,10183,230,0,0),('ATMWithdrawal',10,2025,10183,231,0,0),('MaintenanceFee',100,1925,10183,232,1,0),('ATMWithdrawal',10,1915,10183,233,0,0),('CashDeposit',109726,178092,10182,234,0,0),('ATMWithdrawal',2500,175592,10182,235,0,0),('ATMWithdrawal',100,175492,10182,236,0,0),('ATMWithdrawal',100,175392,10182,237,0,0),('ATMWithdrawal',100,175292,10182,238,0,0),('ForcePassChange',0,175292,10182,239,1,0);
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-06-19 10:25:18
