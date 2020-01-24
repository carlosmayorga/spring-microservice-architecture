INSERT INTO `users` (username, lastname, password, enabled,  email) VALUES ('carlos', 'mayorga', '$2a$10$ba8p2tAUDLpWiAd/d0MVL.4/uX8Rgd3DraKCFPnuP6A26hf1wBH7W', 1, 'cmayorga@boss.com');
INSERT INTO `users` (username, lastname, password, enabled,  email) VALUES ('javier', 'mayorga', '$2a$10$Ta/ASHbjxSJqgPgUigT0yuzVHlnNQ8Tso619/aDM4gG8CDgfjsy3u', 1, 'javier@boss.com');

INSERT INTO `roles` (name) VALUES ('ROLE_PROFILE01');
INSERT INTO `roles` (name) VALUES ('ROLE_PROFILE05');

INSERT INTO `users_role` (user_id, role_id) VALUES (1,1)
INSERT INTO `users_role` (user_id, role_id) VALUES (1,2)
INSERT INTO `users_role` (user_id, role_id) VALUES (2,2)