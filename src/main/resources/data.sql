insert into ROLE (id, name)
values 	(1, 'ADMIN'),
		(2, 'USER');
		
insert into USER (username, password, email, name)
values 	('nguyen', '$2a$10$zUFvzLFIuTGO6gO1cmbk0e4GMFAl0GTWYv79aAjCv8h4q5VjoJfcK', 'nguyen@gmail.com', 'Vũ Nguyên');

insert into USER_ROLE(user_id, role_id)
values 	(1, 1),
		(1, 2);

insert into QUESTION (user_id, title, description)
values 	(1, 'Nguyên có béo không ?' , 'Thực sự thì với cân nặng là 63kg thì các chuyên gia đã khẳng định Nguyên không béo!');