/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 10.1.21-MariaDB : Database - indotravel
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`indotravel` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `indotravel`;

/*Table structure for table `kota` */

DROP TABLE IF EXISTS `kota`;

CREATE TABLE `kota` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `provinsi_id` int(11) unsigned NOT NULL,
  `nama_kota` char(100) DEFAULT NULL,
  `keterangan` char(200) DEFAULT NULL,
  `sumber_gambar` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`,`provinsi_id`),
  KEY `FK_kota` (`provinsi_id`),
  CONSTRAINT `FK_kota` FOREIGN KEY (`provinsi_id`) REFERENCES `provinsi` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `kota` */

insert  into `kota`(`id`,`provinsi_id`,`nama_kota`,`keterangan`,`sumber_gambar`) values (1,1,'Kab. Blangpidie',NULL,'https://khaidhirsyarif.files.wordpress.com/2013/09/875ec-pantaicemaraindah.jpg'),(2,1,'Kab Meulaboh',NULL,'http://2.bp.blogspot.com/-BxIYDrkkAJo/VKpq_bM9RNI/AAAAAAAAAOA/IP99ZdDeXdc/s1600/Masjid%2BAgung%2BMeu'),(3,1,'Kota Jantho',NULL,'http://2.bp.blogspot.com/-BxIYDrkkAJo/VKpq_bM9RNI/AAAAAAAAAOA/IP99ZdDeXdc/s1600/Masjid%2BAgung%2BMeu'),(4,1,'Kab Jalang',NULL,'http://4.bp.blogspot.com/-O49mHb9j05U/VRax85Zjf0I/AAAAAAAAAfU/AB6kQBLGMC8/s1600/air-terjun-temam.jpg'),(5,1,'Kab Tapak Tuan',NULL,'http://1.bp.blogspot.com/-vJMq5bSsfY4/USzbwb7JgOI/AAAAAAAAAEQ/yapgHY_mp3E/s1600/11.jpg'),(6,1,'Kab Singkil',NULL,'https://seramoemekah.files.wordpress.com/2011/01/danau-tana-bara.jpg'),(7,1,'Kab Karang Baru',NULL,'http://www.mypangandaran.com/gambar/wisata-pantai-karang-tirta-7.jpg'),(8,2,'Asahan',NULL,'https://2.bp.blogspot.com/-Yl0nYdNW5HE/U3eyinnTTnI/AAAAAAAADlk/YDPnOmPtJuQ/s1600/paritohan+-+Sigura+'),(9,2,'Batu bara',NULL,'https://2.bp.blogspot.com/-4bM9_lkglCo/UZoihHCY00I/AAAAAAAACDw/sxrAJBqP4c8/s1600/DSC08745.JPG'),(10,2,'Binjai',NULL,'http://4.bp.blogspot.com/-X72REwjLYvw/VlCAL8OXz8I/AAAAAAAAASs/VzQjlcI8_Ys/s1600/Screenshot_13.png'),(11,2,'Kab. Sidikalang',NULL,'http://3.bp.blogspot.com/-GtYfQ1GfJJI/UDcPtbN7iVI/AAAAAAAAACk/yt5FOeWQ6AE/s1600/Taman+wisata+sidikal'),(12,2,'Toba Samosir',NULL,'http://assets.kompas.com/data/photo/2013/12/12/1540095sushii780x390.jpg'),(13,2,'Simalungun',NULL,'https://iasplussipirok.com/wp-content/uploads/2016/10/Taman-Simalem.jpg');

/*Table structure for table `migration` */

DROP TABLE IF EXISTS `migration`;

CREATE TABLE `migration` (
  `version` varchar(180) NOT NULL,
  `apply_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `migration` */

insert  into `migration`(`version`,`apply_time`) values ('m000000_000000_base',1495381353),('m130524_201442_init',1495381355);

/*Table structure for table `profil` */

DROP TABLE IF EXISTS `profil`;

CREATE TABLE `profil` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `Nama` char(50) DEFAULT NULL,
  `UUID` char(100) DEFAULT NULL,
  `email` char(50) DEFAULT NULL,
  `sumber_gambar` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profil` */

/*Table structure for table `provinsi` */

DROP TABLE IF EXISTS `provinsi`;

CREATE TABLE `provinsi` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nama_provinsi` char(50) DEFAULT NULL,
  `keterangan` char(200) DEFAULT NULL,
  `sumber_gambar` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;

/*Data for the table `provinsi` */

insert  into `provinsi`(`id`,`nama_provinsi`,`keterangan`,`sumber_gambar`) values (1,'Nanggroh Aceh Darussalam',NULL,'http://bisniswisata.co.id/wp-content/uploads/2015/06/Tempat-Wisata-di-Aceh-Masjid-Baiturrahman.jpg'),(2,'Sumatera Utara',NULL,'https://static.initempatwisata.com/mediafiles/2015/01/Danau-Toba-di-Sumatera-Utara.jpg'),(3,'Sumatera Selatan',NULL,'http://2.bp.blogspot.com/-swH1BNGHD1g/UUVTeX07dMI/AAAAAAAAAF0/VdczdHcR12c/s1600/Wisata_Jembatan_Ampe'),(4,'Sumatera Barat',NULL,'https://www.wisatania.com/wp-content/uploads/2015/02/padang.jpg'),(5,'Lampung',NULL,'http://www.satujam.com/wp-content/uploads/2016/01/potensi-wisata-bahari-lampung.jpg'),(6,'Riau',NULL,'http://infopku.com/wp-content/uploads/2014/11/e1a3b5b0dda407ab0f7c36x7h-2383.jpg'),(7,'Kepulauan Riau',NULL,'http://1.bp.blogspot.com/-NDQ4qcAlWWs/U5CPlMo0XCI/AAAAAAAAAEM/d1ilb4hYA-o/s1600/5.jpg'),(8,'Jambi',NULL,'http://www.wisataku.id/wp-content/uploads/2015/11/tango_rajo.jpg'),(9,'DKI Jakarta',NULL,'http://3.bp.blogspot.com/-lhFC2ZygxFg/Uwc2Dkr70VI/AAAAAAAAATQ/g80dilz8Oqk/s1600/monas_8.jpg'),(10,'Jawa Barat',NULL,'http://www.wisataku.id/wp-content/uploads/2015/11/gunung_pancar_bogor.jpg'),(11,'Jawa Timur',NULL,'http://3.bp.blogspot.com/-vG5zCGEyxYM/VTFN4aUeNfI/AAAAAAAABH4/a1iSwtFk-7M/s1600/pantai%2Bbalekambang'),(12,'Jawa Tengah',NULL,'http://1.bp.blogspot.com/-Cz-Fp-UBF50/VJpqjtMQMVI/AAAAAAAAAbQ/nTWvD7HPfI8/s1600/ketep-pass.jpg'),(13,'Yogyakarta',NULL,'http://pengendolan.com/wp-content/uploads/2013/12/obyek-wisata-borobudur-Jogja.jpg'),(14,'Bali',NULL,'http://anekatempatwisata.com/wp-content/uploads/2014/03/Danau-Beratan-Bedugul.jpg'),(15,'Nusa Tenggara Timur',NULL,'http://2.bp.blogspot.com/-XMBdq4Xwiwk/VHQlcci4bKI/AAAAAAAAEyQ/uQpD9vs7fc0/s1600/Pulau%2BKomodo.jpg'),(16,'Nusa Tenggara barat',NULL,'http://www.amabeltravel.com/wp-content/uploads/2016/03/gili-trawangan-3-gili-1000x530.jpeg'),(17,'Kalimantan Tengah',NULL,'http://www.amabeltravel.com/wp-content/uploads/2016/04/danau-tahai-kalimantan-tengah.jpg'),(18,'kalimantan Timur',NULL,'http://www.lihat.co.id/wp-content/uploads/2016/07/pulau-derawan-slider.jpg'),(19,'Kalimantan Barat',NULL,'http://lakeybanget.com/cms/statis/dinamis/detail/1883.jpg'),(20,'Sulawesi Utara',NULL,'https://natureadventure2016.files.wordpress.com/2016/04/b2662-wisata-pulau-bunaken-manado.jpg'),(21,'Sulawesi Tengah',NULL,'http://www.amabeltravel.com/wp-content/uploads/2016/05/Togean-Island.jpg'),(22,'Gorontalo',NULL,'http://mytrip123.com/wp-content/uploads/2016/03/Pulau-Diyonumo.jpg'),(23,'Papua Barat',NULL,'http://zonalibur.com/wp-content/uploads/2015/08/keindahan-kelupauan-raja-ampat-papua.jpg'),(24,'Sulawesi Selatan',NULL,'http://1.bp.blogspot.com/-ur4R3V64sOI/T35kJQXZiqI/AAAAAAAAAbk/hX46GBpSA7c/s1600/Samalona-Island-Spot'),(25,'Sulawesi Tenggara',NULL,'http://www.amabeltravel.com/wp-content/uploads/2016/05/bokori-1024x530.jpg'),(26,'Sulawesi Barat',NULL,'http://lh5.googleusercontent.com/-cxD-k4DJmDo/VJo4xVbBXfI/AAAAAAAAE0I/EucbNZBjVFU/s616/ilustrasi-pen'),(27,'Maluku',NULL,'https://cdn.brilio.net/news/2016/04/11/53947/232023-wisata-maluku.jpg'),(28,'Maluku Barat',NULL,'https://elshinta.com/upload/article/_9057522651.jpg'),(29,'Kalimatan Utara',NULL,'http://www.amabeltravel.com/wp-content/uploads/2016/04/pantai-amal.jpg'),(30,'Kepulauan Bangka Belitung',NULL,'https://static.initempatwisata.com/mediafiles/2014/09/Pantai-Matras-Pulau-Bangka.jpg'),(31,'Banten',NULL,'https://rifaiarvinofajar.files.wordpress.com/2013/01/tanjung-lesung-banten.jpg'),(32,'Bengkulu',NULL,'http://www.lihat.co.id/wp-content/uploads/2016/08/Pantai-Panjang-Bengkulu.jpg'),(33,'Papua',NULL,'http://cdn.bisnisukm.com/2011/11/wisata-alam.jpg');

/*Table structure for table `request` */

DROP TABLE IF EXISTS `request`;

CREATE TABLE `request` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `kota_id` int(11) unsigned DEFAULT NULL,
  `nama_tempat` varchar(100) DEFAULT NULL,
  `keterangan` text,
  `gambar` varchar(100) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `user` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `kota_id` (`kota_id`),
  CONSTRAINT `request_ibfk_1` FOREIGN KEY (`kota_id`) REFERENCES `kota` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `request` */

insert  into `request`(`id`,`kota_id`,`nama_tempat`,`keterangan`,`gambar`,`status`,`user`) values (1,12,'Lumban Binaga','Ntah apalah',NULL,1,'siregaraditya'),(2,1,'kajsdn','anbjsdbahas',NULL,0,'siregaraditya'),(3,1,'Bulbul','Nyantai',NULL,1,'siregaraditya');

/*Table structure for table `review` */

DROP TABLE IF EXISTS `review`;

CREATE TABLE `review` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rating` int(5) DEFAULT NULL,
  `profil_id` int(11) unsigned NOT NULL,
  `tanggal` date DEFAULT NULL,
  `review` text,
  `wisata_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`,`profil_id`,`wisata_id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK_review` (`wisata_id`),
  KEY `FK_review_profil` (`profil_id`),
  CONSTRAINT `FK_review` FOREIGN KEY (`wisata_id`) REFERENCES `wisata` (`id`),
  CONSTRAINT `FK_review_profil` FOREIGN KEY (`profil_id`) REFERENCES `profil` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `review` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `auth_key` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `password_hash` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password_reset_token` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `status` smallint(6) NOT NULL DEFAULT '10',
  `created_at` int(11) NOT NULL,
  `updated_at` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `password_reset_token` (`password_reset_token`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`auth_key`,`password_hash`,`password_reset_token`,`email`,`status`,`created_at`,`updated_at`) values (1,'admin','AwqfJv9MiczXgAXjaZyj-C74EhpJgDqR','$2y$13$NFflgq1Hwh9o2xEj7KXkZeKhlhI2hC.pUyi2oG.bnJ2md8PcN4lUq',NULL,'admin@gmail.com',10,1495383062,1495383062);

/*Table structure for table `wisata` */

DROP TABLE IF EXISTS `wisata`;

CREATE TABLE `wisata` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `kota_id` int(11) unsigned NOT NULL,
  `nama_tempat` char(100) DEFAULT NULL,
  `keterangan` text,
  `sumber_gambar` char(100) DEFAULT NULL,
  PRIMARY KEY (`id`,`kota_id`),
  KEY `FK_wisata` (`kota_id`),
  CONSTRAINT `FK_wisata` FOREIGN KEY (`kota_id`) REFERENCES `kota` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

/*Data for the table `wisata` */

insert  into `wisata`(`id`,`kota_id`,`nama_tempat`,`keterangan`,`sumber_gambar`) values (1,1,'Krueng Babahrot','aksjdbnaskjd asdasdkjbasdkjb asdbjkasdbaskjdb aksjdbakjsdbasjkd aksdjbaskjdbakjsd askjdbaskjdbasd aksjdbasd','https://khaidhirsyarif.files.wordpress.com/2013/09/b5155-kruengbabahrot.jpg'),(2,1,'Pantai Cemara Indah',NULL,'http://auliaoktavella.it.student.pens.ac.id/Aceh/img/Pariwisata/Pantai%20Cemara%20Indah%20ko1.jpg'),(3,1,'Pantai Kuala Kutang',NULL,'http://4.bp.blogspot.com/-VksGsQylSi4/VIKeZfTG6MI/AAAAAAAAE_E/hA7wmrcwggI/s1600/Kuala+Merisi.jpg'),(4,12,'Pantai Lumban Bul-Bul Balige','Pantai Lumban Bulbul, berlokasi di Desa Lumban Bulbul, Balige, Tobasamosir, Sumatera Utara, berbeda dengan pantai kebanyakan.\r\nPantai ini berair tawar yang berada di pinggir Danau Toba dan memiliki pasir putih di datarannya seperti pantai.\r\nPantai ini menjadi ikon pantai di tengah keindahan pemandangan Danau Toba yang kaya akan pemandangan perbukitan dan pepohonan.\r\nSelain karena bisa melihat pemandangan Danau Toba dari sudut berbeda, pantai ini juga menjadi satu-satunya pantai berpasir putih di kawasan Balige yang mulai dilirik wisatawan lokal dan luar kota.\r\nWisatawan juga tak perlu takut untuk bermain di pinggir pantai karena kedalamannya hanya sebatas pinggang dan cukup bersih.\r\nPantai Parbaba juga diminati karena lumayan dangkal dan aman untuk anak-anak.\r\nDi sana ada perosotan di area air dangkalnya sehingga anak-anak bisa meluncur langsung ke danau. Ada juga tangga lompat dan jembatan untuk spot foto tengah danau.\r\nJangan lewatkan juga menikmati sunset di sore hari yang begitu memukau. Objek wisata ini sangat cocok menjadi tempat liburan keluarga, komunitas traveller dna rombongan teman kerja untuk lebih menjalin silaturahmi.\r\nElfa Harahap, pengunjung asal Medan, menuturkan area pasir putihnya cukup luas dan menawarkan beberapa spot menarik.\r\n\"Ada spot bermain anak-anak yang lengkap dengan ayunan di pinggir pantai dan plosotan. Kemudian area tour keliling sampan plastik, dan adapula spot panggung untuk area hiburan, jadi kadang pengunjung bermain sambil mendengar musik,\" katanya.\r\nUniknya, tidak dipungut retribusi masuk dan uang parkir di tempat ini. Wisatawan yang hemat budget bisa datang membawa bontot dan memesan pondok yang hanya Rp 10 ribu. Bagi yang tidak membawa makanan bisa memesan di kantin yabg berada si pinggir pantai.\r\nArsal, pengunjung, menuturkan wisata Pantai Lumban Bulbul sangat terjangkau karena sewa pondoknya hanya Rp 10 ribu.\r\n\"Tempatnya nyaman dan tidak banyak sampah. Anak-anak tampak asyik bermain di air dan asyik membuat pernak-pernik dari pasir,\" jelasnya.','http://www.tripelaketoba.com/wp-content/uploads/2016/07/pantai-bulbul.jpg'),(5,13,'Bukit Indah Simarjarunjung','Dengan Menaiki Pohon Cinta di Bukit Indah Simarjarunjung, Anda Dapat Menikmati Pesona Danau Toba. Lokasi Bukit Indah Simarjarunjung (BIS) baru-baru ini muncul sebagai destinasi wisata baru yang hits di Desa Sipintu Angin, Kecamatan Dolok Pardamean, Kabupaten Simalungun, Sumatera Utara.\r\n\r\nTak heran, kondisi daratan di perbukitan ini menjadi lokasi bagi masyarakat luas khususnya kaum muda memajakan mata. Daerah ini, sudah banyak dikunjungi wisatawan dari berbagai daerah. Bahkan tidak jarang wisatawan mancanegara singgah di lokasi ini guna menyaksikan langsung pemandangan indah Danau Toba yang terlihat jelas di hadapan Anda saat di Bukit Indah Simarjarunjung.\r\n\r\nSambil merasakan kesejukan udara yang cukup dingin menerpa kulit, dan melihat burung-burung imigran datang dari Pulau Samosir di pagi hari, Anda dapat menaiki 4 rumah pohon cinta yang berbentuk love. Dari atas rumah pohon ini, pemandangan hamparan Danau Toba, Pulau Samosir, Desa Tiga Ras, Bukit Barisan nan hijau dan beberapa pulau di pinggiran danau vulkanis terbesar di dunia itu benar-benar menjadi sihir penenang hati dan pikiran.','http://www.dimedan.co/wp-content/uploads/2017/05/rumah-pohon-bukit-indah-simarjarunjung2.jpg'),(10,12,'Lumban Binaga','Ntah apalah','http://www.dimedan.co/wp-content/uploads/2017/05/rumah-pohon-bukit-indah-simarjarunjung2.jpg'),(11,1,'Bulbul','Nyantai',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
