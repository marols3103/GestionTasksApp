DROP DATABASE IF EXISTS gestTask;
CREATE DATABASE gestTask;
USE gestTask;

CREATE TABLE G_user(
    IdUser int(4) PRIMARY KEY AUTO_INCREMENT,
    Name  varchar(20) NOT NULL,
    FirstName  varchar(30) NOT NULL,
    Email varchar(30) NOT NULL,
    PassWord varchar(20) NOT NULL

)ENGINE = InnoDB;

INSERT INTO G_user(IdUser,Name,FirstName,Email,PassWord) VALUES (1,"Marolahy","Pierre","marolahy3103@gmail.com","root3103");

SELECT * FROM G_user;