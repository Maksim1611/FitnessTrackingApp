CREATE TABLE user_badge (
                            id BINARY(16) NOT NULL,
                            user_id BINARY(16) NOT NULL,
                            category VARCHAR(30) NOT NULL,
                            tier VARCHAR(20) NOT NULL,
                            muscle_group VARCHAR(30) DEFAULT NULL,
                            earned_at DATETIME(6) NOT NULL,
                            PRIMARY KEY (id),
                            UNIQUE KEY uk_user_badge (user_id, category, muscle_group, tier),
                            CONSTRAINT fk_user_badge_user FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;