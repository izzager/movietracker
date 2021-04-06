INSERT INTO users (id, password, username) VALUES (1, '$2a$10$brLB0Esz5PwyyEli5TytG.eCc/R.W5ojZXopSI6asgDMWCedWm8JS', 'admin');
INSERT INTO users (id, password, username) VALUES (2, '$2a$10$brLB0Esz5PwyyEli5TytG.eCc/R.W5ojZXopSI6asgDMWCedWm8JS', 'user1');

INSERT INTO movie (id, link, name, year) VALUES (1, 'http://movies.com/linkToFilm1', 'Avengers', 2012);
INSERT INTO movie (id, link, name, year) VALUES (2, 'http://movies.com/linkToFilm2', 'Iron man', 2008);
INSERT INTO movie (id, link, name, year) VALUES (3, 'http://movies.com/linkToFilm3', 'Thor', 2010);
INSERT INTO movie (id, link, name, year) VALUES (4, 'http://movies.com/linkToFilm4', 'Captain America: The First Avenger', 2011);

INSERT INTO review (id, comment, rating, movie_id, user_id, creation_date) VALUES (2, 'it''s terrible', 1, 1, 2, '2021-04-06 11:31:49.345799');
INSERT INTO review (id, comment, rating, movie_id, user_id, creation_date) VALUES (1, 'the best movie i''ve ever seen', 10, 1, 1, '2021-04-05 22:11:28.461313');

INSERT INTO watched (user_id, movie_id) VALUES (1, 1);
INSERT INTO watched (user_id, movie_id) VALUES (1, 3);
INSERT INTO watched (user_id, movie_id) VALUES (2, 1);

INSERT INTO wishlist (user_id, movie_id) VALUES (1, 2);
INSERT INTO wishlist (user_id, movie_id) VALUES (1, 4);
INSERT INTO wishlist (user_id, movie_id) VALUES (2, 3);
