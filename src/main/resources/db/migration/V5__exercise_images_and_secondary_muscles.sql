-- Image URLs (free-exercise-db, public domain) and secondary muscle groups
-- for the built-in exercise library seeded in V4.

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Barbell_Bench_Press_-_Medium_Grip/0.jpg' WHERE name = 'Bench Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Bench Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Bench Press (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dumbbell_Bench_Press/0.jpg' WHERE name = 'Bench Press (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Bench Press (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Bench Press (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Barbell_Incline_Bench_Press_-_Medium_Grip/0.jpg' WHERE name = 'Incline Bench Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Incline Bench Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Incline Bench Press (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Incline_Dumbbell_Press/0.jpg' WHERE name = 'Incline Bench Press (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Incline Bench Press (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Incline Bench Press (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Decline_Barbell_Bench_Press/0.jpg' WHERE name = 'Decline Bench Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Decline Bench Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Decline Bench Press (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Machine_Bench_Press/0.jpg' WHERE name = 'Chest Press (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Chest Press (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Chest Press (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dumbbell_Flyes/0.jpg' WHERE name = 'Chest Fly (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Chest Fly (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Flat_Bench_Cable_Flyes/0.jpg' WHERE name = 'Chest Fly (Cable)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Chest Fly (Cable)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Butterfly/0.jpg' WHERE name = 'Chest Fly (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Chest Fly (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Cable_Crossover/0.jpg' WHERE name = 'Cable Crossover' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Cable Crossover' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Pushups/0.jpg' WHERE name = 'Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Push Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Pushups/0.jpg' WHERE name = 'Push Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Push Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Push Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Push Up (Weighted)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Clock_Push-Up/0.jpg' WHERE name = 'Archer Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Archer Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Archer Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Archer Push Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dips_-_Chest_Version/0.jpg' WHERE name = 'Dip (Chest)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Dip (Chest)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Dip (Chest)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dips_-_Chest_Version/0.jpg' WHERE name = 'Dip (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Dip (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Dip (Weighted)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dips_-_Chest_Version/0.jpg' WHERE name = 'Dip (Assisted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Dip (Assisted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Dip (Assisted)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Barbell_Deadlift/0.jpg' WHERE name = 'Deadlift (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Deadlift (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Deadlift (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Deadlift (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Deadlift (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Deadlift (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Romanian_Deadlift/0.jpg' WHERE name = 'Romanian Deadlift (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Romanian Deadlift (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LOWER_BACK' FROM exercise WHERE name = 'Romanian Deadlift (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Romanian_Deadlift/0.jpg' WHERE name = 'Romanian Deadlift (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Romanian Deadlift (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LOWER_BACK' FROM exercise WHERE name = 'Romanian Deadlift (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Pullups/0.jpg' WHERE name = 'Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Pull Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Weighted_Pull_Ups/0.jpg' WHERE name = 'Pull Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Pull Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Pull Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Pull Up (Weighted)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Band_Assisted_Pull-Up/0.jpg' WHERE name = 'Pull Up (Assisted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Pull Up (Assisted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Pull Up (Assisted)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Pullups/0.jpg' WHERE name = 'Negative Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Negative Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Negative Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Negative Pull Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Chin-Up/0.jpg' WHERE name = 'Chin Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Chin Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Chin Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Chin Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Chin-Up/0.jpg' WHERE name = 'Chin Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Chin Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Chin Up (Weighted)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Chin Up (Weighted)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Wide-Grip_Lat_Pulldown/0.jpg' WHERE name = 'Lat Pulldown (Cable)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Lat Pulldown (Cable)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Lat Pulldown (Cable)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Full_Range-Of-Motion_Lat_Pulldown/0.jpg' WHERE name = 'Lat Pulldown (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Lat Pulldown (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Lat Pulldown (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Bent_Over_Barbell_Row/0.jpg' WHERE name = 'Bent Over Row (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Bent Over Row (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Bent Over Row (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LOWER_BACK' FROM exercise WHERE name = 'Bent Over Row (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Bent_Over_Two-Dumbbell_Row/0.jpg' WHERE name = 'Bent Over Row (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Bent Over Row (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Bent Over Row (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Seated_Cable_Rows/0.jpg' WHERE name = 'Seated Cable Row' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Seated Cable Row' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Seated Cable Row' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/T-Bar_Row_with_Handle/0.jpg' WHERE name = 'T-Bar Row' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'T-Bar Row' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'T-Bar Row' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Leverage_Iso_Row/0.jpg' WHERE name = 'Row (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Row (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Row (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Inverted_Row/0.jpg' WHERE name = 'Inverted Row' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Inverted Row' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Inverted Row' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Inverted Row' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Inverted_Row/0.jpg' WHERE name = 'Australian Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Australian Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Australian Pull Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Australian Pull Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Muscle_Up/0.jpg' WHERE name = 'Muscle Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Muscle Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Muscle Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Muscle Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Muscle Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Hyperextensions_Back_Extensions/0.jpg' WHERE name = 'Back Extension' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Back Extension' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Back Extension' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Good_Morning/0.jpg' WHERE name = 'Good Morning (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Good Morning (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Good Morning (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Barbell_Shrug/0.jpg' WHERE name = 'Shrug (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'NECK' FROM exercise WHERE name = 'Shrug (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dumbbell_Shrug/0.jpg' WHERE name = 'Shrug (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'NECK' FROM exercise WHERE name = 'Shrug (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Face_Pull/0.jpg' WHERE name = 'Face Pull (Cable)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Face Pull (Cable)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Face Pull (Cable)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Standing_Military_Press/0.jpg' WHERE name = 'Overhead Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Overhead Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Overhead Press (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Overhead Press (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dumbbell_Shoulder_Press/0.jpg' WHERE name = 'Overhead Press (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Overhead Press (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Overhead Press (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Machine_Shoulder_Military_Press/0.jpg' WHERE name = 'Shoulder Press (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Shoulder Press (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Arnold_Dumbbell_Press/0.jpg' WHERE name = 'Arnold Press (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Arnold Press (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Side_Lateral_Raise/0.jpg' WHERE name = 'Lateral Raise (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Lateral Raise (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Cable_Seated_Lateral_Raise/0.jpg' WHERE name = 'Lateral Raise (Cable)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Lateral Raise (Cable)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Front_Dumbbell_Raise/0.jpg' WHERE name = 'Front Raise (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Front Raise (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Seated_Bent-Over_Rear_Delt_Raise/0.jpg' WHERE name = 'Rear Delt Fly (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Rear Delt Fly (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Rear Delt Fly (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Reverse_Machine_Flyes/0.jpg' WHERE name = 'Rear Delt Fly (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Rear Delt Fly (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Rear Delt Fly (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Upright_Barbell_Row/0.jpg' WHERE name = 'Upright Row (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Upright Row (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'BICEPS' FROM exercise WHERE name = 'Upright Row (Barbell)' AND created_by_user_id IS NULL;

INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Pike Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Pike Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Pike Push Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Handstand_Push-Ups/0.jpg' WHERE name = 'Handstand Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Handstand Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Handstand Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Handstand Push Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Handstand_Push-Ups/0.jpg' WHERE name = 'Wall Handstand Hold' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRICEPS' FROM exercise WHERE name = 'Wall Handstand Hold' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Wall Handstand Hold' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Wall Handstand Hold' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Barbell_Curl/0.jpg' WHERE name = 'Bicep Curl (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Bicep Curl (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dumbbell_Bicep_Curl/0.jpg' WHERE name = 'Bicep Curl (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Bicep Curl (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Standing_Biceps_Cable_Curl/0.jpg' WHERE name = 'Bicep Curl (Cable)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Bicep Curl (Cable)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Hammer_Curls/0.jpg' WHERE name = 'Hammer Curl (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Hammer Curl (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Preacher_Curl/0.jpg' WHERE name = 'Preacher Curl (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Preacher Curl (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Concentration_Curls/0.jpg' WHERE name = 'Concentration Curl' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Concentration Curl' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/EZ-Bar_Curl/0.jpg' WHERE name = 'EZ Bar Curl' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'EZ Bar Curl' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Triceps_Pushdown/0.jpg' WHERE name = 'Triceps Pushdown (Cable)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Standing_Dumbbell_Triceps_Extension/0.jpg' WHERE name = 'Triceps Extension (Overhead)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Triceps Extension (Overhead)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/EZ-Bar_Skullcrusher/0.jpg' WHERE name = 'Skull Crusher (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Close-Grip_Barbell_Bench_Press/0.jpg' WHERE name = 'Close Grip Bench Press' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CHEST' FROM exercise WHERE name = 'Close Grip Bench Press' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Close Grip Bench Press' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dips_-_Triceps_Version/0.jpg' WHERE name = 'Dip (Triceps)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CHEST' FROM exercise WHERE name = 'Dip (Triceps)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Dip (Triceps)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Bench_Dips/0.jpg' WHERE name = 'Bench Dip' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CHEST' FROM exercise WHERE name = 'Bench Dip' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Bench Dip' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Push-Ups_-_Close_Triceps_Position/0.jpg' WHERE name = 'Diamond Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CHEST' FROM exercise WHERE name = 'Diamond Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Diamond Push Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Diamond Push Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Seated_Palm-Up_Barbell_Wrist_Curl/0.jpg' WHERE name = 'Wrist Curl (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Farmers_Walk/0.jpg' WHERE name = 'Farmer''s Walk' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Farmer''s Walk' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Farmer''s Walk' AND created_by_user_id IS NULL;

INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Dead Hang' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Dead Hang' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Barbell_Squat/0.jpg' WHERE name = 'Squat (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Squat (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Squat (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LOWER_BACK' FROM exercise WHERE name = 'Squat (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Squat (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Front_Barbell_Squat/0.jpg' WHERE name = 'Front Squat (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Front Squat (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Front Squat (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Front Squat (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Goblet_Squat/0.jpg' WHERE name = 'Goblet Squat (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Goblet Squat (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Goblet Squat (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Bodyweight_Squat/0.jpg' WHERE name = 'Squat (Bodyweight)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Squat (Bodyweight)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Squat (Bodyweight)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Squat (Bodyweight)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Kettlebell_Pistol_Squat/0.jpg' WHERE name = 'Pistol Squat' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Pistol Squat' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Pistol Squat' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CALVES' FROM exercise WHERE name = 'Pistol Squat' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Leg_Press/0.jpg' WHERE name = 'Leg Press (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Leg Press (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Leg Press (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Leg_Extensions/0.jpg' WHERE name = 'Leg Extension (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dumbbell_Lunges/0.jpg' WHERE name = 'Lunge (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Lunge (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Lunge (Dumbbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Lunge (Dumbbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Bodyweight_Walking_Lunge/0.jpg' WHERE name = 'Lunge (Bodyweight)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Lunge (Bodyweight)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Lunge (Bodyweight)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Lunge (Bodyweight)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Barbell_Walking_Lunge/0.jpg' WHERE name = 'Walking Lunge' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Walking Lunge' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Walking Lunge' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Walking Lunge' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Split_Squat_with_Dumbbells/0.jpg' WHERE name = 'Bulgarian Split Squat' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Bulgarian Split Squat' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Bulgarian Split Squat' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Bulgarian Split Squat' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Barbell_Hip_Thrust/0.jpg' WHERE name = 'Hip Thrust (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Hip Thrust (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Hip Thrust (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Butt_Lift_Bridge/0.jpg' WHERE name = 'Glute Bridge' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Glute Bridge' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Glute Bridge' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Thigh_Abductor/0.jpg' WHERE name = 'Hip Abduction (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/One-Legged_Cable_Kickback/0.jpg' WHERE name = 'Cable Kickback' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Cable Kickback' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Dumbbell_Step_Ups/0.jpg' WHERE name = 'Step Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Step Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Step Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Lying_Leg_Curls/0.jpg' WHERE name = 'Leg Curl (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CALVES' FROM exercise WHERE name = 'Leg Curl (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Natural_Glute_Ham_Raise/0.jpg' WHERE name = 'Nordic Hamstring Curl' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Nordic Hamstring Curl' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CALVES' FROM exercise WHERE name = 'Nordic Hamstring Curl' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Stiff-Legged_Barbell_Deadlift/0.jpg' WHERE name = 'Stiff Leg Deadlift' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Stiff Leg Deadlift' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LOWER_BACK' FROM exercise WHERE name = 'Stiff Leg Deadlift' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Standing_Calf_Raises/0.jpg' WHERE name = 'Calf Raise (Standing)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Seated_Calf_Raise/0.jpg' WHERE name = 'Calf Raise (Seated)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Standing_Calf_Raises/0.jpg' WHERE name = 'Calf Raise (Bodyweight)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Plank/0.jpg' WHERE name = 'Plank' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Plank' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LOWER_BACK' FROM exercise WHERE name = 'Plank' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Side_Bridge/0.jpg' WHERE name = 'Side Plank' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Side Plank' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Crunches/0.jpg' WHERE name = 'Crunch' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Cable_Crunch/0.jpg' WHERE name = 'Cable Crunch' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Sit-Up/0.jpg' WHERE name = 'Sit Up' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Hanging_Leg_Raise/0.jpg' WHERE name = 'Hanging Leg Raise' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Hanging Leg Raise' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Hanging Leg Raise' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Hanging_Leg_Raise/0.jpg' WHERE name = 'Hanging Knee Raise' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Hanging Knee Raise' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Hanging Knee Raise' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Hanging_Pike/0.jpg' WHERE name = 'Toes to Bar' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'FOREARMS' FROM exercise WHERE name = 'Toes to Bar' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Toes to Bar' AND created_by_user_id IS NULL;

INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'L-Sit Hold' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'L-Sit Hold' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Russian_Twist/0.jpg' WHERE name = 'Russian Twist' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Ab_Roller/0.jpg' WHERE name = 'Ab Wheel Rollout' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Ab Wheel Rollout' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Ab Wheel Rollout' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Mountain_Climbers/0.jpg' WHERE name = 'Mountain Climber' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Mountain Climber' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Mountain Climber' AND created_by_user_id IS NULL;

INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Dragon Flag' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Dragon Flag' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/One-Arm_Kettlebell_Swings/0.jpg' WHERE name = 'Kettlebell Swing' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Kettlebell Swing' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'HAMSTRINGS' FROM exercise WHERE name = 'Kettlebell Swing' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LOWER_BACK' FROM exercise WHERE name = 'Kettlebell Swing' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Kettlebell Swing' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Clean_and_Jerk/0.jpg' WHERE name = 'Clean and Jerk (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Clean and Jerk (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Clean and Jerk (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Clean and Jerk (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Clean and Jerk (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Clean and Jerk (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Snatch/0.jpg' WHERE name = 'Snatch (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Snatch (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Snatch (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Snatch (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'TRAPS' FROM exercise WHERE name = 'Snatch (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Snatch (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Push_Press/0.jpg' WHERE name = 'Thruster (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Thruster (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Thruster (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Thruster (Barbell)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Thruster (Barbell)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Kettlebell_Turkish_Get-Up_Lunge_style/0.jpg' WHERE name = 'Turkish Get Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Turkish Get Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Turkish Get Up' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Turkish Get Up' AND created_by_user_id IS NULL;

INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CHEST' FROM exercise WHERE name = 'Burpee' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Burpee' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Burpee' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Battling_Ropes/0.jpg' WHERE name = 'Battle Ropes' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Battle Ropes' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CORE' FROM exercise WHERE name = 'Battle Ropes' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Trail_Running_Walking/0.jpg' WHERE name = 'Running' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Running' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CALVES' FROM exercise WHERE name = 'Running' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Running_Treadmill/0.jpg' WHERE name = 'Treadmill Run' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Treadmill Run' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CALVES' FROM exercise WHERE name = 'Treadmill Run' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Bicycling/0.jpg' WHERE name = 'Cycling' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Cycling' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CALVES' FROM exercise WHERE name = 'Cycling' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Rowing_Stationary/0.jpg' WHERE name = 'Rowing (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'UPPER_BACK' FROM exercise WHERE name = 'Rowing (Machine)' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Rowing (Machine)' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Stairmaster/0.jpg' WHERE name = 'Stair Climber' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Stair Climber' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'GLUTES' FROM exercise WHERE name = 'Stair Climber' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CALVES' FROM exercise WHERE name = 'Stair Climber' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Elliptical_Trainer/0.jpg' WHERE name = 'Elliptical' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'QUADS' FROM exercise WHERE name = 'Elliptical' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Rope_Jumping/0.jpg' WHERE name = 'Jump Rope' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'CALVES' FROM exercise WHERE name = 'Jump Rope' AND created_by_user_id IS NULL;

INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'SHOULDERS' FROM exercise WHERE name = 'Swimming' AND created_by_user_id IS NULL;
INSERT INTO exercise_other_muscles (exercise_id, muscle_group) SELECT id, 'LATS' FROM exercise WHERE name = 'Swimming' AND created_by_user_id IS NULL;

UPDATE exercise SET image_url = 'https://raw.githubusercontent.com/yuhonas/free-exercise-db/main/exercises/Walking_Treadmill/0.jpg' WHERE name = 'Walking' AND created_by_user_id IS NULL;
