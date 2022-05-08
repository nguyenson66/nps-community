insert into ROLE (id, name)
values 	(1, 'ROLE_ADMIN'),
		(2, 'ROLE_USER');
		
insert into USER (username, password, email, name, avatar, address, birthday, created_at, updated_at)
values 	('nguyen', '$2a$10$zUFvzLFIuTGO6gO1cmbk0e4GMFAl0GTWYv79aAjCv8h4q5VjoJfcK', 'nguyen@gmail.com', 'Vũ Nguyên', 'https://bit.ly/3McoJN1', NULL, '2022-05-03 22:04:35', '2022-05-03 22:04:35', '2022-05-03 22:04:35'),
		('son', '$2a$10$uH5C3BbGZyL4EJBk5EvXp.qYlvCIcbFAtpFHietjnbb6FI3XN38lO', 'nguyenson@gmail.com', 'Nguyễn Sơn', 'https://bit.ly/3McoJN1', NULL, '2022-05-03 22:27:48', '2022-05-03 22:27:48', '2022-05-03 22:27:48'),
		('phuc', '$2a$10$alpA.tbQoTLB2BS7oeBReugfLvRrBnQ5yGsh5uwvFcLlCiAc/Qlj.', 'hongphuc@gmail.com', 'Hồng Phúc', 'https://bit.ly/3McoJN1', NULL, '2022-05-03 22:28:03', '2022-05-03 22:28:03', '2022-05-03 22:28:03');

insert into CATEGORY (name)
values ('Đời sống'), ('Công nghệ');

insert into USER_ROLE(user_id, role_id)
values 	(1, 1),
		(1, 2);

insert into QUESTION (user_id, category_id,title, content)
values 	(1, 1, 'Nguyên có béo không ?' , 'Thực sự thì với cân nặng là 63kg thì các chuyên gia đã khẳng định Nguyên không béo!');


insert into QUESTION_CATEGORY(question_id, category_id)
values 	(1,1),
		(1,2);