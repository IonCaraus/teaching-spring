CREATE DATABASE IF NOT EXISTS mydatabase;
USE mydatabase;

CREATE TABLE IF NOT EXISTS  PERSONS(
                      ID int not null AUTO_INCREMENT,
                      FIRST_NAME varchar(100) not null,
                      LAST_NAME varchar(100) not null,
                      PERSON_NUMBER varchar(100) not null,
                      EMAIL_ADDRESS varchar(100) not null,
                      PRIMARY KEY ( ID )
);

INSERT INTO PERSONS (FIRST_NAME,LAST_NAME,PERSON_NUMBER,EMAIL_ADDRESS)
VALUES ('Paul','Mill','36457364','paul.mill@yahoo.com');
INSERT INTO PERSONS (FIRST_NAME,LAST_NAME,PERSON_NUMBER,EMAIL_ADDRESS)
VALUES ('John','White','36457367','john.white@yahoo.com');
INSERT INTO PERSONS (FIRST_NAME,LAST_NAME,PERSON_NUMBER,EMAIL_ADDRESS)
VALUES ('Kevin','Black','56457364','kevin.black@mail.com');