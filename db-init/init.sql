INSERT INTO cast_me.user_roles (id, label, user_role) VALUES (1, 'actor', 'ROLE_ACTOR');
INSERT INTO cast_me.user_roles (id, label, user_role) VALUES (2, 'director', 'ROLE_CASTING_DIRECTOR');
INSERT INTO cast_me.user_roles (id, label, user_role) VALUES (3, 'agent', 'ROLE_AGENT');

INSERT INTO cast_me.users (id, email, first_name, last_name, password, phone_number, user_role_id) VALUES (1, 'john.doe@mail.com', 'John', 'Doe', '$2a$10$VLxPVQeqSnh7Z30uXEzH3OCXL/I72iGzHhlKqWUjbnhJWIcoaE9me', '123123123', 1);
INSERT INTO cast_me.users (id, email, first_name, last_name, password, phone_number, user_role_id) VALUES (2, 'jane.doe@mail.com', 'Jane', 'Doe', '$2a$10$VLxPVQeqSnh7Z30uXEzH3OCXL/I72iGzHhlKqWUjbnhJWIcoaE9me', '123123123', 2);
INSERT INTO cast_me.users (id, email, first_name, last_name, password, phone_number, user_role_id) VALUES (3, 'ann.smith@mail.com', 'Ann', 'Smith', '$2a$10$VLxPVQeqSnh7Z30uXEzH3OCXL/I72iGzHhlKqWUjbnhJWIcoaE9me', '123123123', 1);
INSERT INTO cast_me.users (id, email, first_name, last_name, password, phone_number, user_role_id) VALUES (4, 'george.nowak@mail.com', 'George', 'Nowak', '$2a$10$VLxPVQeqSnh7Z30uXEzH3OCXL/I72iGzHhlKqWUjbnhJWIcoaE9me', '123123123', 1);
INSERT INTO cast_me.users (id, email, first_name, last_name, password, phone_number, user_role_id) VALUES (5, 'marie.brown@mail.com', 'Marie', 'Brown', '$2a$10$VLxPVQeqSnh7Z30uXEzH3OCXL/I72iGzHhlKqWUjbnhJWIcoaE9me', '123123123', 1);

INSERT INTO cast_me.feature_set (id, age_from, age_to, eye_color, figure, gender, hair_color, hair_length, height) VALUES (1, 25, 30, 'blue', 'athletic', 'female', 'blonde', 'short', 'short');
INSERT INTO cast_me.feature_set (id, age_from, age_to, eye_color, figure, gender, hair_color, hair_length, height) VALUES (2, 18, 25, 'blue', 'curvy', 'male', 'blonde', 'medium', 'medium');
INSERT INTO cast_me.feature_set (id, age_from, age_to, eye_color, figure, gender, hair_color, hair_length, height) VALUES (3, 30, 39, 'grey', 'athletic', 'female', 'ginger', 'long', 'tall');
INSERT INTO cast_me.feature_set (id, age_from, age_to, eye_color, figure, gender, hair_color, hair_length, height) VALUES (4, 34, 38, 'brown', 'slim', 'male', 'brown', 'short', 'tall');
INSERT INTO cast_me.feature_set (id, age_from, age_to, eye_color, figure, gender, hair_color, hair_length, height) VALUES (5, 0, 0, '', '', 'male', '', '', '');
INSERT INTO cast_me.feature_set (id, age_from, age_to, eye_color, figure, gender, hair_color, hair_length, height) VALUES (6, 0, 0, '', '', 'female', '', '', '');

INSERT INTO cast_me.casting_directors (id, user_id) VALUES (1, 2);
INSERT INTO cast_me.actors (id, education, agency_id, feature_set_id, user_id) VALUES (1, 'National Academy of Dramatic Arts', null, 1, 5);
INSERT INTO cast_me.actors (id, education, agency_id, feature_set_id, user_id) VALUES (2, 'National Academy of Dramatic Arts', null, 2, 1);
INSERT INTO cast_me.actors (id, education, agency_id, feature_set_id, user_id) VALUES (3, 'National Academy of Dramatic Arts', null, 3, 3);
INSERT INTO cast_me.actors (id, education, agency_id, feature_set_id, user_id) VALUES (4, 'National Academy of Dramatic Arts', null, 4, 4);

INSERT INTO cast_me.skills (id, name) VALUES (1, 'Horse riding');
INSERT INTO cast_me.skills (id, name) VALUES (2, 'Dancing');
INSERT INTO cast_me.skills (id, name) VALUES (3, 'Singing');
INSERT INTO cast_me.skills (id, name) VALUES (4, 'Driver\'s license');
INSERT INTO cast_me.skills (id, name) VALUES (5, 'Running really fast');
INSERT INTO cast_me.skills (id, name) VALUES (6, 'Jumping');
INSERT INTO cast_me.skills (id, name) VALUES (7, 'Melting stuff');

INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (1, 1);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (1, 2);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (1, 3);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (1, 4);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (2, 1);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (2, 3);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (2, 5);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (3, 1);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (3, 3);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (3, 6);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (4, 4);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (4, 5);
INSERT INTO cast_me.actors_skills (actor_id, skills_id) VALUES (4, 7);

INSERT INTO cast_me.castings (id, created_on, deadline, description, is_active, title, casting_director_id) VALUES (1, '2025-07-27', '2025-08-15', 'We are seeking diverse individuals of all ages, backgrounds, and experience levels! Any accent, look, or personality – diversity is key!', true, 'Actors & Actresses of All Ages Wanted for Upcoming Production!', 1);

INSERT INTO cast_me.roles (id, description, title, feature_set_id, casting_id) VALUES (1, 'Bright-eyed, imaginative, and always asking questions. Loves animals and carries a notebook everywhere to write down "important discoveries." Must be expressive and comfortable speaking on camera.', 'Charles', 5, 1);
INSERT INTO cast_me.roles (id, description, title, feature_set_id, casting_id) VALUES (2, 'Introverted and intelligent, with a mysterious side. Carries the weight of something unspoken. Strong non-verbal acting skills required to convey emotion through subtlety.', 'Emma', 6, 1);

INSERT INTO cast_me.actors_roles (id, actor_id, role_id) VALUES (1, 2, 1);
INSERT INTO cast_me.actors_roles (id, actor_id, role_id) VALUES (2, 4, 1);
INSERT INTO cast_me.actors_roles (id, actor_id, role_id) VALUES (3, 1, 2);
INSERT INTO cast_me.actors_roles (id, actor_id, role_id) VALUES (4, 3, 2);