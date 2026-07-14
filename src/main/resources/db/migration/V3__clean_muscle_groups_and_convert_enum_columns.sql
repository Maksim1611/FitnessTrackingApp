UPDATE exercise SET primary_muscle_group = 'CORE' WHERE primary_muscle_group = 'ABDOMINAL';
UPDATE exercise SET primary_muscle_group = 'QUADS' WHERE primary_muscle_group = 'QUADRICEPS';
UPDATE exercise_other_muscles SET muscle_group = 'CORE' WHERE muscle_group = 'ABDOMINAL';
UPDATE exercise_other_muscles SET muscle_group = 'QUADS' WHERE muscle_group = 'QUADRICEPS';
UPDATE user_badge SET muscle_group = 'CORE' WHERE muscle_group = 'ABDOMINAL';
UPDATE user_badge SET muscle_group = 'QUADS' WHERE muscle_group = 'QUADRICEPS';

ALTER TABLE exercise MODIFY COLUMN primary_muscle_group VARCHAR(30);
ALTER TABLE exercise_other_muscles MODIFY COLUMN muscle_group VARCHAR(30);
ALTER TABLE routine_set_target MODIFY COLUMN set_type VARCHAR(20);
ALTER TABLE workout_set MODIFY COLUMN set_type VARCHAR(20);