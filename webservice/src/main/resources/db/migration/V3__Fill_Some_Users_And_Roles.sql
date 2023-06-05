INSERT INTO role (id, name) values (NEXTVAL('role_sequence'), 'New');
INSERT INTO role (id, name) values (NEXTVAL('role_sequence'), 'Admin');
INSERT INTO usr (id, login, password, email, firstname, lastname, birthday, role_id)
values (NEXTVAL('user_sequence'), 'user', 'user', 'user1@gmail.com', 'FirstName1', 'LastName1', to_date('12:22', 'HH24:MI'), 1);
INSERT INTO usr (id, login, password, email, firstname, lastname, birthday, role_id)
values (NEXTVAL('user_sequence'), 'admin', 'admin', 'user2@gmail.com', 'FirstName2', 'LastName2', to_date('12:22', 'HH24:MI'), 2);