SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS category; 
DROP TABLE IF EXISTS books; 
DROP TABLE IF EXISTS application_users;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE category(
id BIGINT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(30) NOT NULL,
PRIMARY KEY (id)
);

INSERT INTO category (`name`) 
VALUES ("It"),
("Ohjelmointi"),
("Tietokannat"),
("Pilvipalvelut");


CREATE TABLE books(
id BIGINT NOT NULL AUTO_INCREMENT,
title VARCHAR(300) NOT NULL,
author VARCHAR(100) NOT NULL,
isbn VARCHAR(20) NOT NULL,
publication_year BIGINT,
price DECIMAL(10,2),
categoryid BIGINT,
PRIMARY KEY (id), 
FOREIGN KEY(categoryid) REFERENCES category(id)
);

INSERT INTO books (title, author, isbn, publication_year, price, categoryid) 
VALUES ("Koodaajan käsikirja", "Hilda Koodinkirjoittaja", "132-312-123", 2000, 19.90, 2), 
("Tämän haluat tietää pilvipalveluiden käyttöönotosta", "Pekka Palvelin", "401-404-403-201", 2010, 46.90, 4),
("SQL-perusteet", "Teppo Tietokanta", "10-20-30-40", 2012, 34.90, 3),
("Python3", "Douglas Django, Ethan Enum", "123-456-789-10", 2020, 54.90, 2);






CREATE TABLE application_users
(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
firstname VARCHAR(100) NOT NULL,
lastname VARCHAR(100) NOT NULL,
username VARCHAR(250) NOT NULL,
password_hash VARCHAR(250) NOT NULL,
`role`  VARCHAR(100) NOT NULL
);

INSERT INTO application_users (firstname, lastname, username, password_hash, `role`) 
VALUES ("", "", "user","$2a$10$RIqlxElPXzQKJayHKJwSNOxDMnMh.j.OHwQvOoPj0gld.sbXsqqgK" ,"USER"),
("", "", "admin", "$2a$10$aGjp6jEUEspwUkQrCbGAWuKScc9DRHTQ6LXMRX2TAM5A6tzHdy8/6", "ADMIN"), 
("", "", "huolto", "$2a$10$lDtQP3VTBBHPocsCga.a6.iqXrQq2S3.nlaWJieniRwYlOUjpttUS", "ADMIN");