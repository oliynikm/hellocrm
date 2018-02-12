INSERT INTO role (id, name, description) VALUES (1, 'STANDARD_USER', 'Standard User - Has no admin rights');
INSERT INTO role (id, name, description) VALUES (2, 'ADMIN_USER', 'Admin User - Has permission to perform admin tasks');

-- USER
-- non-encrypted password: jwtpass
INSERT INTO user (id, first_name, last_name, password, login) VALUES (1, 'Demo', 'User', 'bc09e511b196f91d3525b9ea4b283a7818d4655d544a0b765e853e9bd0db8f3e', 'demo.user');
INSERT INTO user (id, first_name, last_name, password, login) VALUES (2, 'Demo', 'Admin', 'bc09e511b196f91d3525b9ea4b283a7818d4655d544a0b765e853e9bd0db8f3e', 'demo.admin');


INSERT INTO user_role(user_id, role_id) VALUES(1,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,1);
INSERT INTO user_role(user_id, role_id) VALUES(2,2);