SET autocommit=0;
START TRANSACTION;
#Table for student entity
CREATE TABLE IF NOT EXISTS user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    grade DOUBLE(10 , 3 ),
    enabled BOOLEAN
);
#Table for questions
CREATE TABLE IF NOT EXISTS question (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category varchar(100),
    question VARCHAR(300) NOT NULL,
    correct_answer VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS question_options (
    id INT PRIMARY KEY AUTO_INCREMENT,
    question_id INT NOT NULL,
    answer VARCHAR(200),
    FOREIGN KEY (question_id)
        REFERENCES question(id)
        ON DELETE CASCADE
);

#Student questions and provided answers
CREATE TABLE IF NOT EXISTS user_question (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    question_id INT,
    answer VARCHAR(50),
    FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON DELETE CASCADE,
    FOREIGN KEY (question_id)
        REFERENCES question (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS test (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100),
    duration INT
);

CREATE TABLE IF NOT EXISTS test_questions (
    id INT PRIMARY KEY AUTO_INCREMENT,
    test_id INT,
    question_id INT,
    FOREIGN KEY (test_id)
        REFERENCES test (id)
        ON DELETE CASCADE,
    FOREIGN KEY (question_id)
        REFERENCES question (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS authorities (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    authority VARCHAR(50),
    FOREIGN KEY (user_id)
        REFERENCES user (id)
        ON DELETE CASCADE
);

CREATE TABLE SPRING_SESSION (
	PRIMARY_ID CHAR(36) NOT NULL,
	SESSION_ID CHAR(36) NOT NULL,
	CREATION_TIME BIGINT NOT NULL,
	LAST_ACCESS_TIME BIGINT NOT NULL,
	MAX_INACTIVE_INTERVAL INT NOT NULL,
	EXPIRY_TIME BIGINT NOT NULL,
	PRINCIPAL_NAME VARCHAR(100),
	CONSTRAINT SPRING_SESSION_PK PRIMARY KEY (PRIMARY_ID)
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

CREATE UNIQUE INDEX SPRING_SESSION_IX1 ON SPRING_SESSION (SESSION_ID);
CREATE INDEX SPRING_SESSION_IX2 ON SPRING_SESSION (EXPIRY_TIME);
CREATE INDEX SPRING_SESSION_IX3 ON SPRING_SESSION (PRINCIPAL_NAME);

CREATE TABLE SPRING_SESSION_ATTRIBUTES (
	SESSION_PRIMARY_ID CHAR(36) NOT NULL,
	ATTRIBUTE_NAME VARCHAR(200) NOT NULL,
	ATTRIBUTE_BYTES BLOB NOT NULL,
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_PK PRIMARY KEY (SESSION_PRIMARY_ID, ATTRIBUTE_NAME),
	CONSTRAINT SPRING_SESSION_ATTRIBUTES_FK FOREIGN KEY (SESSION_PRIMARY_ID) REFERENCES SPRING_SESSION(PRIMARY_ID) ON DELETE CASCADE
) ENGINE=InnoDB ROW_FORMAT=DYNAMIC;

insert into user (name, password, grade, enabled) values ("Test1", "test", 0, true);
insert into authorities (user_id, authority) values (1, "ROLE_STUDENT");

insert into user (name, password, grade, enabled) values ("Test2", "test", 0, true);
insert into authorities (user_id, authority) values (2, "ROLE_STUDENT");

insert into question(category, question, correct_answer) 
values ("slq", "Just choose answer c", "c"),
("data", "Here you need to chose answer d", "d");

insert into question_options (question_id, answer) values
(1, "This is not right answer"),
(1, "This answer also is not right"),
(1, "Choose tihs answer"),
(2, "This is not correct answer"),
(2, "Remind you to choose answer d"),
(2, "Right answer is next answer"),
(2, "Choose me!");

COMMIT;
SET autocommit=1;