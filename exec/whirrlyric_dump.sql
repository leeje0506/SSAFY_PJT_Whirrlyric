-- MySQL dump 10.13  Distrib 8.3.0, for Linux (x86_64)
--
-- Host: localhost    Database: whirrlyric
-- ------------------------------------------------------
-- Server version	8.3.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `mainsong`
--

DROP TABLE IF EXISTS `mainsong`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mainsong` (
  `id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  `song_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKodstxfe1ly9quqfa1glkex87a` (`member_id`),
  KEY `FKcfkhcrn0cspomnhlxk5fg63wx` (`song_id`),
  CONSTRAINT `FKcfkhcrn0cspomnhlxk5fg63wx` FOREIGN KEY (`song_id`) REFERENCES `song` (`song_id`),
  CONSTRAINT `FKodstxfe1ly9quqfa1glkex87a` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mainsong`
--

LOCK TABLES `mainsong` WRITE;
/*!40000 ALTER TABLE `mainsong` DISABLE KEYS */;
INSERT INTO `mainsong` VALUES (1,'2024-05-16 15:03:09.501044','2024-05-16 15:06:12.818099',4,3),(2,'2024-05-16 15:16:43.879279','2024-05-16 15:24:03.869784',5,8);
/*!40000 ALTER TABLE `mainsong` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `oauth_server_id` varchar(255) NOT NULL,
  `provider` enum('KAKAO') NOT NULL,
  `profile_image` int DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_id`),
  UNIQUE KEY `oauth_id_unique` (`oauth_server_id`,`provider`),
  UNIQUE KEY `UK_hh9kg6jti4n1eoiertn2k6qsc` (`nickname`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2024-05-16 13:34:07.366717','2024-05-16 13:34:07.366717',_binary '\0','piie3207','3479741526','KAKAO',7,'user'),(2,'2024-05-16 13:34:19.244457','2024-05-16 13:34:19.244457',_binary '\0','jpqh6304','3479566130','KAKAO',4,'user'),(3,'2024-05-16 13:39:18.321890','2024-05-16 13:39:18.321890',_binary '\0','xvbl8542','3475604670','KAKAO',8,'user'),(4,'2024-05-16 14:02:43.448467','2024-05-16 15:07:15.256235',_binary '\0','프로듀서안수진','3480142082','KAKAO',9,'user'),(5,'2024-05-16 15:12:55.832417','2024-05-16 15:17:25.449675',_binary '\0','초성안돼','3484439826','KAKAO',4,'user'),(6,'2024-05-16 16:10:21.344429','2024-05-16 16:10:21.344429',_binary '\0','gbje0708','3478679741','KAKAO',6,'user'),(7,'2024-05-16 16:49:21.362623','2024-05-16 16:49:21.362623',_binary '\0','tkoq4075','3484037343','KAKAO',1,'user'),(8,'2024-05-16 16:51:12.993701','2024-05-16 16:51:12.993701',_binary '\0','evvg7689','3481601887','KAKAO',2,'user');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `song` (
  `song_id` int NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `modified_at` datetime(6) DEFAULT NULL,
  `genre` char(2) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `lyrics` text,
  `play_count` int DEFAULT NULL,
  `song_url` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `member_id` int DEFAULT NULL,
  PRIMARY KEY (`song_id`),
  KEY `FKln77pl0kiwceje9wi2qn9jj4c` (`member_id`),
  CONSTRAINT `FKln77pl0kiwceje9wi2qn9jj4c` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES (2,'2024-05-16 14:21:43.446410','2024-05-16 15:13:32.973187','00','cdn1.suno.ai/image_f274550a-8aaf-42bd-bfb7-5d88b8fc0882.png','[INTRO]\n너와 나, 시작의 순간을 향해\n불꽃처럼 타오르는 이 밤\n[VERSE1]\n걸어가 너와 나의 시간 속으로\n깜박이는 별빛 아래 춤을 춰\n망설임은 이제 그만, 손을 잡아\n세상이 우리를 부를 때까지\n[VERSE2]\n눈부신 너의 미소에 빠져서\n세상 모든 걸 잊고 싶어\n꿈꾸는 것처럼, 달콤하게\n너와 내가 만들어갈 Story\n[CHORUS]\n우리 함께라면 뭐든지 할 수 있어\n별들 사이로 날아 올라\n종일 웃고 노래하며\n너의 이름을 외칠 거야, Oh yeah\n[BRIDGE]\n조금씩 다가가 이제 서로의 마음을 확인해\n두려움 없이 말해줘, 너도 나와 같다면\n시간이 멈춘 듯, 너와 나만의 세계\n[OUTRO]\n오늘을 기억해, 우리가 함께 꿈꾼 밤을\n항상 너를 응원할게, 끝없는 별빛처럼',4,'cdn1.suno.ai/f274550a-8aaf-42bd-bfb7-5d88b8fc0882.mp3','별빛 여행 (Starlight Journey)',4),(3,'2024-05-16 14:44:01.451504','2024-05-16 15:19:23.386987','07','cdn1.suno.ai/image_51a636c8-7410-4499-a35f-6a43dda2cfdc.png','[INTRO]\n오늘 밤을 느껴, 전율로 가득 차\n시작해, 이 순간을 타고\n[VERSE1]\n너와 나, 이 도시의 불빛 사이로\n질주해, 심장의 리듬을 타고\n손을 뻗어 별을 잡자, 시간은 우릴 기다려주지 않아\n바람과 함께 춤을 춰, 자유로운 영혼을 깨워\n[VERSE2]\n빛나는 눈빛, 너의 그 모든 것이\n나를 이끌어 더 깊은 곳으로\n우린 마법 같은 밤을 만들 거야\n모든 걱정은 잊고, 오직 이 노래에 몸을 맡겨\n[CHORUS]\n높이 날아올라, 꿈과 같은 이 밤에\n심장의 비트가 뜨거워질 때까지\n다 함께 소리쳐, 모든 순간을 다\n이 밤이 끝나기 전에, 모두 다 함께\n[BRIDGE]\n멈추지 마, 계속 이 느낌을 따라가\n세상의 끝까지, 너와 함께라면\n이 순간을 영원히, 간직할 수 있어\n[OUTRO]\n기억해, 이 밤의 마법을\n너와 나, 영원히 이 순간을 새겨놓아\n함께한 모든 밤을, 잊지 않을 거야',1,'cdn1.suno.ai/51a636c8-7410-4499-a35f-6a43dda2cfdc.mp3','밤의 마법',4),(4,'2024-05-16 14:52:50.375419','2024-05-16 15:19:45.214798','02','cdn1.suno.ai/image_9b57e949-8488-45b1-8c69-af8d76bded62.png','[INTRO]\n천둥 같은 이 밤을 깨워\n시작의 불길을 타고 올라\n[VERSE1]\n길거리의 불빛 사이로, 우린 질주해\n어둠을 밝히는 기타 소리에 맞춰\n누구도 우리를 막을 수 없어\n이 도시의 규칙들을 모두 뒤집어\n[VERSE2]\n모든 것을 걸고 네게 달려가\n네 손을 잡고 저 하늘을 넘어\n태양을 향해 소리쳐, 끝까지 가보자고\n두려움은 이제 우리를 따라잡지 못해\n[CHORUS]\n우리의 목소리가 세상을 뒤흔들 때까지\n멈추지 마, 계속해서 더 높이\n함께라면 무엇이든 이겨낼 수 있어\n이 밤이 우리를 위해 만들어졌어\n[BRIDGE]\n숨가쁘게 이어지는 이 밤을 느껴\n너와 내가 만들어낸 자유의 노래\n함께 부르며, 모든 걸 뒤집어\n[OUTRO]\n이 밤의 여왕과 왕으로\n우리는 영원히 기억될 이야기를 만들어\n오늘밤을 잊지 마, 우리가 살아있음을',2,'cdn1.suno.ai/9b57e949-8488-45b1-8c69-af8d76bded62.mp3','밤의 왕과 여왕',4),(5,'2024-05-16 15:15:44.118217','2024-05-16 15:15:44.118217','13','cdn1.suno.ai/image_1e833188-e86f-4af9-b87d-089bad41ddc8.png','[INTRO]\n오늘 밤에 떠나볼까, 달빛 아래서\n시간이 멈춘 듯, 너와 나만의 춤을\n[VERSE1]\n오래된 라디오에서 흘러나오는 그 노래\n모든 걸 잊게 해주는 달콤한 멜로디\n이 마음, 설레임 가득한 너를 향해\n가슴 뛰는 이 밤, 너와 함께 걸어가\n[VERSE2]\n버스커의 아코디언, 저 멀리서 들려오는 웃음소리\n반짝이는 너의 눈빛, 날 이끄는 희망의 빛\n함께라면 쓸쓸함도 두렵지 않아\n이 거리 위에서, 우리 둘의 축제야\n[CHORUS]\n너의 손을 잡고 돌고 돌아 춤을 추며\n한잔의 소주에 우리 이야기를 담아\n헤어질 생각 마, 오늘밤은 끝이 없어\n너와 나, 이 밤을 채우는 영원한 노래\n[BRIDGE]\n기다려온 순간, 마음을 열어\n두려움 없이 내게 다가와\n이 밤의 끝에서, 우리만의 약속을\n[OUTRO]\n오랜 시간 속에서도 변치 않을 우리의 밤\n추억으로 남을, 너와 나의 멜로디',0,'cdn1.suno.ai/1e833188-e86f-4af9-b87d-089bad41ddc8.mp3','밤새도록 춤을',5),(6,'2024-05-16 15:21:58.715111','2024-05-16 15:21:58.715111','00','cdn1.suno.ai/image_74d6b945-5935-4f4e-b864-3c453563d8fd.png','[INTRO]\n\n[VERSE1]\n\n[VERSE2]\n\n[CHORUS]\n\n[BRIDGE]\n\n[OUTRO]\n',0,'cdn1.suno.ai/74d6b945-5935-4f4e-b864-3c453563d8fd.mp3','',5),(7,'2024-05-16 15:21:59.317752','2024-05-16 15:21:59.317752','00','cdn1.suno.ai/image_2e110f75-3f0d-471d-b044-2280a5c84582.png','[INTRO]\n\n[VERSE1]\n\n[VERSE2]\n\n[CHORUS]\n\n[BRIDGE]\n\n[OUTRO]\n',0,'cdn1.suno.ai/2e110f75-3f0d-471d-b044-2280a5c84582.mp3','',5),(8,'2024-05-16 15:22:55.787748','2024-05-16 15:22:55.787748','11','cdn1.suno.ai/image_950662b4-61df-4c29-ac54-bd488ca9f84e.png','[INTRO]\nㄴㅇㄹㄴㅇㄹ\n[VERSE1]\nㄴㅇㄹㄴㅇㄹ\n[VERSE2]\nㄴㅇㄹㄴㅇㄹ\n[CHORUS]\n\n[BRIDGE]\n\n[OUTRO]\n',0,'cdn1.suno.ai/950662b4-61df-4c29-ac54-bd488ca9f84e.mp3','ㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹㄴㅇㄹ',5),(9,'2024-05-16 16:49:14.775888','2024-05-16 16:49:14.775888','03','cdn1.suno.ai/image_62698dfb-aa43-4002-ac44-9a40081d7406.png','[INTRO]\n나는 이지은이다 \n[VERSE1]\n나는 이지은이다 나는 이지은이다 나는 이지은이다 나는 이지은이다 나는 이지은이다 \n[VERSE2]\n나는 이지은이다나는 이지은이다 나는 이지은이다 나는 이지은이다 나는 이지은이다 나는 이지은이다 나는 이지은이다 나는 이지은이다 나는 이지은이다  \n[CHORUS]\n나는 이지은이다 \n[BRIDGE]\n나는 이지은이다 \n[OUTRO]\n나는 이지은이다 ',0,'cdn1.suno.ai/62698dfb-aa43-4002-ac44-9a40081d7406.mp3','나는 이지은이다 ',6);
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-17 17:29:21
