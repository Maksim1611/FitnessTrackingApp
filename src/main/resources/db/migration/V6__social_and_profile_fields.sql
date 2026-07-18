-- Profile fields
ALTER TABLE `user` ADD COLUMN `birth_date` date DEFAULT NULL;
ALTER TABLE `user` ADD COLUMN `sex` varchar(10) DEFAULT NULL;
ALTER TABLE `user` ADD COLUMN `bio` varchar(500) DEFAULT NULL;

-- Likes on workouts
CREATE TABLE `workout_like` (
  `id` binary(16) NOT NULL,
  `user_id` binary(16) NOT NULL,
  `workout_id` binary(16) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_like_user_workout` (`user_id`, `workout_id`),
  KEY `idx_like_workout` (`workout_id`),
  CONSTRAINT `fk_like_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_like_workout` FOREIGN KEY (`workout_id`) REFERENCES `workout` (`id`) ON DELETE CASCADE
);

-- Comments on workouts
CREATE TABLE `workout_comment` (
  `id` binary(16) NOT NULL,
  `user_id` binary(16) NOT NULL,
  `workout_id` binary(16) NOT NULL,
  `text` varchar(500) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_comment_workout` (`workout_id`),
  CONSTRAINT `fk_comment_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_comment_workout` FOREIGN KEY (`workout_id`) REFERENCES `workout` (`id`) ON DELETE CASCADE
);

-- Notifications (recipient user_id, triggered by actor_id)
CREATE TABLE `notification` (
  `id` binary(16) NOT NULL,
  `user_id` binary(16) NOT NULL,
  `actor_id` binary(16) DEFAULT NULL,
  `type` varchar(20) NOT NULL,
  `reference_id` binary(16) DEFAULT NULL,
  `is_read` bit(1) NOT NULL DEFAULT b'0',
  `created_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_notification_user` (`user_id`),
  CONSTRAINT `fk_notification_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_notification_actor` FOREIGN KEY (`actor_id`) REFERENCES `user` (`id`)
);
