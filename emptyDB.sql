SET FOREIGN_KEY_CHECKS=0;
TRUNCATE champgen.championship;
TRUNCATE game;
TRUNCATE matchday;
TRUNCATE user;
TRUNCATE team;
TRUNCATE user_group;
SET FOREIGN_KEY_CHECKS=1;
INSERT INTO user(password, username) VALUES("admin", "admin");
INSERT INTO user_group(groupName, user_id) VALUES("ADMIN", 1);