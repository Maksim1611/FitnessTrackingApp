
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
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `body_measurement` (
  `id` binary(16) NOT NULL,
  `biceps` double DEFAULT NULL,
  `body_fat_percentage` double DEFAULT NULL,
  `calves` double DEFAULT NULL,
  `chest` double DEFAULT NULL,
  `hips` double DEFAULT NULL,
  `neck` double DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `recorded_at` datetime(6) NOT NULL,
  `shoulders` double DEFAULT NULL,
  `thighs` double DEFAULT NULL,
  `waist` double DEFAULT NULL,
  `weight` double NOT NULL,
  `user_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmbhffocmdmyxcaubgj6ovjqtk` (`user_id`),
  CONSTRAINT `FKmbhffocmdmyxcaubgj6ovjqtk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise` (
  `id` binary(16) NOT NULL,
  `equipment` enum('BARBELL','BODYWEIGHT','CABLE','DUMBBELL','KETTLEBELL','MACHINE','OTHER','PLATE','RESISTANCE_BAND') DEFAULT NULL,
  `exercise_type` enum('ASSISTED_BODYWEIGHT','BODYWEIGHT','DISTANCE','DURATION','REPS_ONLY','WEIGHTED_BODYWEIGHT','WEIGHT_DURATION','WEIGHT_REPS') DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `primary_muscle_group` enum('ABDOMINAL','BACK','BICEPS','CALVES','CARDIO','CHEST','CORE','FOREARMS','FULL_BODY','GLUTES','HAMSTRINGS','LATS','LOWER_BACK','NECK','OTHER','QUADRICEPS','QUADS','SHOULDERS','TRAPS','TRICEPS','UPPER_BACK') DEFAULT NULL,
  `created_by_user_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKidmgwhego5p6xt5uur9ri59h5` (`name`,`created_by_user_id`),
  KEY `FKb3365kja1u9c17qxfikushu61` (`created_by_user_id`),
  CONSTRAINT `FKb3365kja1u9c17qxfikushu61` FOREIGN KEY (`created_by_user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exercise_other_muscles` (
  `exercise_id` binary(16) NOT NULL,
  `muscle_group` enum('ABDOMINAL','BACK','BICEPS','CALVES','CARDIO','CHEST','CORE','FOREARMS','FULL_BODY','GLUTES','HAMSTRINGS','LATS','LOWER_BACK','NECK','OTHER','QUADRICEPS','QUADS','SHOULDERS','TRAPS','TRICEPS','UPPER_BACK') DEFAULT NULL,
  UNIQUE KEY `UK352ynx9vplsopclnc39649vvh` (`exercise_id`,`muscle_group`),
  CONSTRAINT `FKa6vdv831jn2icqjuqynifshg7` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follow` (
  `id` binary(16) NOT NULL,
  `created_at` datetime(6) NOT NULL,
  `followed_id` binary(16) NOT NULL,
  `follower_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK2nmykh3xviqhkkudaafoik969` (`follower_id`,`followed_id`),
  KEY `FK3gu03ktcbxq8nm9rf4ll2glrr` (`followed_id`),
  CONSTRAINT `FK3gu03ktcbxq8nm9rf4ll2glrr` FOREIGN KEY (`followed_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKmow2qk674plvwyb4wqln37svv` FOREIGN KEY (`follower_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `refresh_token` (
  `id` binary(16) NOT NULL,
  `expired_at` datetime(6) DEFAULT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(512) NOT NULL,
  `user_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKr4k4edos30bx9neoq81mdvwph` (`token`),
  KEY `FKfgk1klcib7i15utalmcqo7krt` (`user_id`),
  CONSTRAINT `FKfgk1klcib7i15utalmcqo7krt` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routine` (
  `id` binary(16) NOT NULL,
  `folder_order` int DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `notes` varchar(255) DEFAULT NULL,
  `user_id` binary(16) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrnd1817o40yyif3tnuw9medf8` (`user_id`),
  CONSTRAINT `FKrnd1817o40yyif3tnuw9medf8` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routine_exercise` (
  `id` binary(16) NOT NULL,
  `exercise_note` varchar(255) DEFAULT NULL,
  `exercise_order` int DEFAULT NULL,
  `superset_group_id` int DEFAULT NULL,
  `exercise_id` binary(16) DEFAULT NULL,
  `routine_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKsn88y4ra5dickg7y7x3ouhykf` (`exercise_id`),
  KEY `FKcbc9ikf3pspchwepg7x4lqm9s` (`routine_id`),
  CONSTRAINT `FKcbc9ikf3pspchwepg7x4lqm9s` FOREIGN KEY (`routine_id`) REFERENCES `routine` (`id`),
  CONSTRAINT `FKsn88y4ra5dickg7y7x3ouhykf` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routine_set_target` (
  `id` binary(16) NOT NULL,
  `set_number` int DEFAULT NULL,
  `set_type` enum('DROPSET','FAILURE','NORMAL','WARMUP','WORKING') DEFAULT NULL,
  `target_duration_seconds` int DEFAULT NULL,
  `target_reps_max` int DEFAULT NULL,
  `target_reps_min` int DEFAULT NULL,
  `target_weight` double DEFAULT NULL,
  `routine_exercise_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjhbgmr3wpopby8efwmhlrqmix` (`routine_exercise_id`),
  CONSTRAINT `FKjhbgmr3wpopby8efwmhlrqmix` FOREIGN KEY (`routine_exercise_id`) REFERENCES `routine_exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` binary(16) NOT NULL,
  `created_on` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') NOT NULL,
  `updated_on` datetime(6) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKob8kqyqqgmefl0aco34akdtpe` (`email`),
  UNIQUE KEY `UKgj2fy3dcix7ph7k8684gka40c` (`name`),
  UNIQUE KEY `UKsb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout` (
  `id` binary(16) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `routine_id` binary(16) DEFAULT NULL,
  `user_id` binary(16) NOT NULL,
  `finished_at` datetime(6) DEFAULT NULL,
  `started_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKru5yvnlk4i6n5xid8vmywacrj` (`routine_id`),
  KEY `FKfd6lahc24vib7n7vw2ekecn00` (`user_id`),
  CONSTRAINT `FKfd6lahc24vib7n7vw2ekecn00` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKru5yvnlk4i6n5xid8vmywacrj` FOREIGN KEY (`routine_id`) REFERENCES `routine` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `workout_set` (
  `id` binary(16) NOT NULL,
  `completed` bit(1) NOT NULL,
  `completed_at` datetime(6) DEFAULT NULL,
  `duration_seconds` int DEFAULT NULL,
  `exercise_order` int DEFAULT NULL,
  `reps` int DEFAULT NULL,
  `rest_seconds` int DEFAULT NULL,
  `rpe` double DEFAULT NULL,
  `set_number` int DEFAULT NULL,
  `set_type` enum('DROPSET','FAILURE','NORMAL','WARMUP','WORKING') DEFAULT NULL,
  `superset_group_id` int DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `exercise_id` binary(16) NOT NULL,
  `workout_id` binary(16) NOT NULL,
  `is_personal_record` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhg1c36fv3o7wlsv0w31m7un18` (`exercise_id`),
  KEY `FKbp9h9o2y44v0viusc9jakdsxl` (`workout_id`),
  CONSTRAINT `FKbp9h9o2y44v0viusc9jakdsxl` FOREIGN KEY (`workout_id`) REFERENCES `workout` (`id`),
  CONSTRAINT `FKhg1c36fv3o7wlsv0w31m7un18` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

