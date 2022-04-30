create table if not exists USER (
    id int not null auto_increment,
    username varchar(25) unique not null,
    password varchar(255) not null,
    email varchar(255) not null,
    name varchar(255),
    address varchar(255),
    birthday date,
    PRIMARY KEY (id)
);

create table if not exists ROLE (
    id int not null auto_increment,
    name varchar(50) not null,
    PRIMARY KEY (id)
);

create table if not exists USER_ROLE(
    user_id int not null,
    role_id int not null,
    FOREIGN KEY (user_id) REFERENCES USER (id),
    FOREIGN KEY (role_id) REFERENCES ROLE (id)
);

create table if not exists QUESTION(
	question_id int not null auto_increment,
    user_id int not null,
    title varchar(255) not null,
    description varchar(255) not null,
    PRIMARY KEY (question_id),
    FOREIGN KEY (user_id) REFERENCES USER(id)
);

create table if not exists PERSISTENT_LOGINS(
	username varchar(64) not null,
	series varchar(64) not null, 
	token varchar(64) not null,
	last_used timestamp not null,
	PRIMARY KEY (series)
);