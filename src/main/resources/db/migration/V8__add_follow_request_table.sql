CREATE TABLE `follow_request` (
                                  `id` binary(16) NOT NULL,
                                  `created_at` datetime(6) NOT NULL,
                                  `target_id` binary(16) NOT NULL,
                                  `requester_id` binary(16) NOT NULL,
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_follow_request` (`requester_id`,`target_id`),
                                  KEY `idx_follow_request_target` (`target_id`),
                                  CONSTRAINT `fk_follow_request_target` FOREIGN KEY (`target_id`) REFERENCES `user` (`id`),
                                  CONSTRAINT `fk_follow_request_requester` FOREIGN KEY (`requester_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;