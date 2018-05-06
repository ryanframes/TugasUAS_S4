-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: db_perpustakaan
-- ------------------------------------------------------
-- Server version	5.5.5-10.1.25-MariaDB

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
-- Table structure for table `t_buku`
--

DROP TABLE IF EXISTS `t_buku`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_buku` (
  `kd_buku` varchar(8) NOT NULL,
  `nm_buku` varchar(25) NOT NULL,
  `th_penerbit` int(4) NOT NULL,
  `nm_penerbit` varchar(25) NOT NULL,
  `nm_penulis` varchar(20) NOT NULL,
  `deskripsi` text NOT NULL,
  `stok_buku` int(11) NOT NULL,
  PRIMARY KEY (`kd_buku`),
  KEY `kd_buku` (`kd_buku`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_buku`
--

LOCK TABLES `t_buku` WRITE;
/*!40000 ALTER TABLE `t_buku` DISABLE KEYS */;
INSERT INTO `t_buku` VALUES ('BK01','LTM Pertemuan 1',2001,'BSI','Faris Junaedi','Jawaban untuk LTM Pertemuan ke - 1 dan penjelasannya',3);
/*!40000 ALTER TABLE `t_buku` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_member`
--

DROP TABLE IF EXISTS `t_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_member` (
  `id_member` int(11) NOT NULL,
  `nama` varchar(35) NOT NULL,
  `no_hp` varchar(15) NOT NULL,
  `tgl_join` date NOT NULL,
  `j_kelamin` varchar(8) NOT NULL,
  PRIMARY KEY (`id_member`),
  KEY `id_member` (`id_member`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_member`
--

LOCK TABLES `t_member` WRITE;
/*!40000 ALTER TABLE `t_member` DISABLE KEYS */;
INSERT INTO `t_member` VALUES (2121,'Roy Suryo','+62897456498','2018-04-11','Pria'),(65798413,'Larry Deriano','+6246789138','2018-04-11','Pria');
/*!40000 ALTER TABLE `t_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pinjam`
--

DROP TABLE IF EXISTS `t_pinjam`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_pinjam` (
  `no_dok` varchar(10) NOT NULL COMMENT 'Contoh penomoran : 0001/PB/18 (PB = Pinjam Buku)',
  `tgl_pinjam` date NOT NULL,
  `id_member` int(11) NOT NULL,
  `tgl_kembali` date NOT NULL,
  `jml_buku` int(11) NOT NULL,
  `nm_user` varchar(15) NOT NULL,
  PRIMARY KEY (`no_dok`),
  KEY `no_dok` (`no_dok`),
  KEY `nm_user` (`nm_user`),
  KEY `id_member` (`id_member`),
  CONSTRAINT `t_pinjam_ibfk_1` FOREIGN KEY (`id_member`) REFERENCES `t_member` (`id_member`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_pinjam_ibfk_2` FOREIGN KEY (`nm_user`) REFERENCES `t_user` (`nm_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pinjam`
--

LOCK TABLES `t_pinjam` WRITE;
/*!40000 ALTER TABLE `t_pinjam` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pinjam` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_pinjam_det`
--

DROP TABLE IF EXISTS `t_pinjam_det`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_pinjam_det` (
  `no_dok` varchar(10) NOT NULL,
  `kd_buku` varchar(8) NOT NULL,
  KEY `no_dok` (`no_dok`),
  KEY `kd_buku` (`kd_buku`),
  CONSTRAINT `t_pinjam_det_ibfk_1` FOREIGN KEY (`kd_buku`) REFERENCES `t_buku` (`kd_buku`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `t_pinjam_det_ibfk_2` FOREIGN KEY (`no_dok`) REFERENCES `t_pinjam` (`no_dok`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_pinjam_det`
--

LOCK TABLES `t_pinjam_det` WRITE;
/*!40000 ALTER TABLE `t_pinjam_det` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_pinjam_det` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `nm_user` varchar(15) NOT NULL,
  `password` varchar(10) NOT NULL,
  `level` tinyint(1) NOT NULL,
  PRIMARY KEY (`nm_user`),
  KEY `nm_user` (`nm_user`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES ('ADMIN','ADMIN',1),('STAFF','STAFF',2);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_perpustakaan'
--

--
-- Dumping routines for database 'db_perpustakaan'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-05-06 22:09:47
