-- Roles
INSERT INTO public.roles (id, name) VALUES (1, 'ADMIN');
INSERT INTO public.roles (id, name) VALUES (2, 'USER');

-- Users
INSERT INTO public.users (user_id, password, username, role_id) VALUES (1, '$2a$12$gv9KgnI9t4PcJAFrvvoOV./pdHhqpLna5GiYi85b6qT0m22qYaQZ6', 'admin', 1);
INSERT INTO public.users (user_id, password, username, role_id) VALUES (2, '$2a$10$3Zk8uMMijr7dtkNlWLrGvedD2D4qNEDRKnXvEyyCqkHXxlFuaIgS6', 'test_user1', 2);
INSERT INTO public.users (user_id, password, username, role_id) VALUES (3, '$2a$10$1JyOvtoVRcTUswl7o6YcyefB995IkHIGSEF9rA4criU65UasQW9TG', 'test_user2', 2);
INSERT INTO public.users (user_id, password, username, role_id) VALUES (4, '$2a$10$LuvzAPGXllrPzdWYNT8Tzurmk91gOr56/v2Ffpuid7q1x0p8BQ7xy', 'test_user3', 2);

-- Users details
INSERT INTO public.user_details (user_id, email, profile_photo) VALUES (1, 'admin123@admin', 33065);
INSERT INTO public.user_details (user_id, email, profile_photo) VALUES (2, 'test@test.com', 33067);
INSERT INTO public.user_details (user_id, email, profile_photo) VALUES (3, 'test2@test.com', 33069);
INSERT INTO public.user_details (user_id, email, profile_photo) VALUES (4, 'test3@test.com', 33070);

-- Trainings
INSERT INTO public.trainings (id, description, dislikes, likes, title, user_user_id) VALUES (1, 'The best training for all! Try it!', 0, 3, 'Best training', 1);
INSERT INTO public.trainings (id, description, dislikes, likes, title, user_user_id) VALUES (2, 'This is FBW training for advanced people.', 0, 2, 'FBW training', 1);
INSERT INTO public.trainings (id, description, dislikes, likes, title, user_user_id) VALUES (5, 'This is the worst training!', 3, 0, 'Bad training', 3);
INSERT INTO public.trainings (id, description, dislikes, likes, title, user_user_id) VALUES (4, 'This is push pull training', 1, 1, 'Push - pull', 2);
INSERT INTO public.trainings (id, description, dislikes, likes, title, user_user_id) VALUES (6, 'Test description', 1, 1, 'Test title ', 4);
INSERT INTO public.trainings (id, description, dislikes, likes, title, user_user_id) VALUES (3, 'This is training for beginers.', 2, 0, 'Easy training', 2);

-- Training days
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (1, 1, 1);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (2, 2, 1);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (3, 3, 1);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (4, 4, 1);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (5, 5, 1);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (6, 6, 1);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (7, 1, 2);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (8, 2, 2);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (9, 3, 2);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (10, 4, 2);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (11, 5, 2);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (12, 6, 2);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (13, 7, 2);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (14, 1, 3);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (15, 2, 3);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (16, 3, 3);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (17, 4, 3);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (18, 1, 4);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (19, 2, 4);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (20, 3, 4);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (21, 4, 4);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (22, 1, 5);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (23, 2, 5);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (24, 3, 5);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (25, 4, 5);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (26, 1, 6);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (27, 2, 6);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (28, 3, 6);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (29, 4, 6);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (30, 5, 6);
INSERT INTO public.training_days (training_day_id, day_number, training_id) VALUES (31, 6, 6);

-- Exercises
INSERT INTO public.exercises (exercise_id, name) VALUES (1, 'Bench press');
INSERT INTO public.exercises (exercise_id, name) VALUES (2, 'Deadlift');
INSERT INTO public.exercises (exercise_id, name) VALUES (3, 'Squat');
INSERT INTO public.exercises (exercise_id, name) VALUES (4, 'Pull-ups');
INSERT INTO public.exercises (exercise_id, name) VALUES (5, 'Plank');
INSERT INTO public.exercises (exercise_id, name) VALUES (6, 'Pullover');
INSERT INTO public.exercises (exercise_id, name) VALUES (7, 'Dip');
INSERT INTO public.exercises (exercise_id, name) VALUES (8, 'Biceps curl');
INSERT INTO public.exercises (exercise_id, name) VALUES (9, 'Box jump');
INSERT INTO public.exercises (exercise_id, name) VALUES (10, 'Crunch');

-- Training sessions
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (3, 1, 8, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (7, 1, 5, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (10, 1, 10, 5);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (4, 1, 5, 5);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (2, 3, 4, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (9, 3, 10, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (8, 3, 11, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (5, 3, 1, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (1, 4, 2, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (2, 4, 2, 5);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (3, 4, 4, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (1, 6, 4, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (7, 6, 5, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (10, 6, 10, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (1, 7, 8, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (3, 7, 5, 5);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (5, 7, 1, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (4, 7, 12, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (8, 7, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (7, 9, 12, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (4, 9, 10, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (3, 9, 12, 5);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (9, 9, 10, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (2, 11, 4, 5);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (7, 11, 10, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (9, 11, 12, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (1, 13, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (2, 13, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (1, 14, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (2, 14, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (10, 14, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (8, 16, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (3, 16, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (10, 17, 10, 1);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (7, 17, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (1, 18, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (7, 18, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (3, 18, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (2, 20, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (4, 20, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (8, 20, 12, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (7, 22, 1, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (8, 23, 3, 12);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (4, 23, 2, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (3, 25, 4, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (3, 26, 1, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (2, 27, 1, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (2, 28, 1, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (7, 29, 1, 3);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (10, 30, 1, 4);
INSERT INTO public.training_sessions (exercise_id, training_day_id, reps, series) VALUES (1, 31, 2, 1);

-- User ratings
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (1, 1, 1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (2, 1, 1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (1, 2, 1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (5, 3, -1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (6, 4, 1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (1, 4, 1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (5, 4, -1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (4, 4, 1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (3, 4, -1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (2, 2, 1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (5, 2, -1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (4, 2, -1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (6, 2, -1);
INSERT INTO public.user_ratings (training_id, user_id, rating) VALUES (3, 2, -1);
