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

CREATE TABLE IF NOT EXISTS  SECURITY_USERS(
           ID int not null AUTO_INCREMENT,
           USERNAME varchar(100) not null,
           PASSWORD varchar(100) not null,
           AUTHORITIES varchar(300) not null,
           PRIMARY KEY ( ID )
);
--password is "pass"
INSERT INTO SECURITY_USERS (USERNAME,PASSWORD,AUTHORITIES)
VALUES ('listUser','$2a$10$4UV94x0xALcjY2Uy2imkIeLNEqrbIhXyHRtz9TnuvsjGZd/LcSFyO','LIST_PERSONS');
INSERT INTO SECURITY_USERS (USERNAME,PASSWORD,AUTHORITIES)
VALUES ('showPerson','$2a$10$4UV94x0xALcjY2Uy2imkIeLNEqrbIhXyHRtz9TnuvsjGZd/LcSFyO','SHOW_PERSON');
INSERT INTO SECURITY_USERS (USERNAME,PASSWORD,AUTHORITIES)
VALUES ('createPerson','$2a$10$4UV94x0xALcjY2Uy2imkIeLNEqrbIhXyHRtz9TnuvsjGZd/LcSFyO','CREATE_PERSON');
INSERT INTO SECURITY_USERS (USERNAME,PASSWORD,AUTHORITIES)
VALUES ('admin','$2a$10$4UV94x0xALcjY2Uy2imkIeLNEqrbIhXyHRtz9TnuvsjGZd/LcSFyO','LIST_PERSONS,SHOW_PERSON,CREATE_PERSON');
