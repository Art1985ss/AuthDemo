#Table for student entity
CREATE TABLE IF NOT EXISTS student (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) UNIQUE,
    password VARCHAR(50),
    grade DOUBLE(10 , 3 ),
    enabled BOOLEAN
);
#Table for test data
CREATE TABLE IF NOT EXISTS student_data (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    data_text VARCHAR(500) NOT NULL,
    FOREIGN KEY (student_id)
        REFERENCES student (id)
        ON DELETE CASCADE
);
#Table for questions
CREATE TABLE IF NOT EXISTS question (
    id INT PRIMARY KEY AUTO_INCREMENT,
    category varchar(100),
    question VARCHAR(200) NOT NULL,
    answer_a VARCHAR(100),
    answer_b VARCHAR(100),
    answer_c VARCHAR(100),
    answer_d VARCHAR(100),
    correct_answer VARCHAR(100)
);

#Student questions and provided answers
CREATE TABLE IF NOT EXISTS student_question (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    question_id INT,
    answer VARCHAR(50),
    FOREIGN KEY (student_id)
        REFERENCES student (id)
        ON DELETE CASCADE,
    FOREIGN KEY (question_id)
        REFERENCES question (id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS authorities (
    id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT NOT NULL,
    authority VARCHAR(50),
    FOREIGN KEY (student_id)
        REFERENCES student (id)
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

insert into student (name, password, grade, enabled) values ("Test1", "test", 0, true);
insert into student_data (student_id, data_text) values (1, "This is just test data for user 1");
insert into student_data (student_id, data_text) values (1, "This is just second test data for user 1");
insert into authorities (student_id, authority) values (1, "ROLE_STUDENT");

insert into student (name, password, grade, enabled) values ("Test2", "test", 0, true);
insert into student_data (student_id, data_text) values (2, "This is test data for user 2");
insert into student_data (student_id, data_text) values (2, "This is second test data for user 2");
insert into authorities (student_id, authority) values (2, "ROLE_STUDENT");

insert into question(category, question, answer_a, answer_b, answer_c, answer_d, correct_answer) 
values ("slq", "Just choose answer c", "This is not right answer", "This answer also is not right", "Choose tihs answer", "How many times I should tell you that you should choose answer c?", "c"),
("data", "Here you need to chose answer d", "This is not correct answer", "Remind you to choose answer d", "Right answer is next answer", "Choose me!", "d");
