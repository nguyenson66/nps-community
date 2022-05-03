create table if not exists USER (
    id int not null auto_increment,
    username varchar(25) unique not null unique,
    password varchar(255) not null,
    email varchar(255) not null unique,
    name varchar(255),
    avatar varchar(255) default "https://bit.ly/3McoJN1",
    address varchar(255),
    birthday timestamp default current_timestamp,
    created_at timestamp default current_timestamp,
	updated_at timestamp default current_timestamp,
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
	id int not null auto_increment,
    category_id int not null,
    user_id int not null,
    title varchar(255) not null,
    content varchar(255) not null,
    viewed int default 0,
    vote_up int default 0,
    vote_down int default 0,
	created_at timestamp default current_timestamp,
	updated_at timestamp default current_timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES USER(id)
);

create table if not exists REPLY(
	id int not null auto_increment,
    question_id int not null,
    user_id int not null,
    content varchar(255) not null,
    vote_up int default 0,
    vote_down int default 0,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES USER(id),
	FOREIGN KEY (question_id) REFERENCES QUESTION(id)
);

create table if not exists CATEGORY(
	id int not null auto_increment,
    name varchar(255) not null,
    primary key (id)
);

create table if not exists PERSISTENT_LOGINS(
	username varchar(64) not null,
	series varchar(64) not null, 
	token varchar(64) not null,
	last_used timestamp not null,
	PRIMARY KEY (series)
);