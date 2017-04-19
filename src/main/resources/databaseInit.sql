DROP TABLE IF EXISTS stats;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS url;


CREATE TABLE `users` (
	`userId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(100) NOT NULL,
	`surname` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL UNIQUE,
	`password` varchar(100) NOT NULL,
	`confirmToken` varchar(100),
	`isConfirmed` boolean,
	`isAdmin` boolean
);

CREATE TABLE `url` (
	`shortUrl` varchar(100) PRIMARY KEY,
	`longUrl` varchar(500) NOT NULL,
	`createDate` TIMESTAMP NOT NULL,
	`startDate` TIMESTAMP NULL,
	`expireDate` TIMESTAMP NULL,
	`password` varchar(100),
	`captcha` BOOLEAN,
	`nbClicks` INT,
	`userId` INT,
	`generic` BOOLEAN
);

CREATE TABLE `stats` (
	`idStat` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`shortUrl` varchar(100) NOT NULL,
	`ipAddress` varchar(15),
	`country` varchar(100) DEFAULT NULL,
	`visitDate` TIMESTAMP 
);

ALTER TABLE `url` ADD CONSTRAINT `url_fk0` FOREIGN KEY (`userId`) REFERENCES `users`(`userId`);

ALTER TABLE `stats` ADD CONSTRAINT `stats_fk0` FOREIGN KEY (`shortUrl`) REFERENCES `url`(`shortUrl`);

INSERT INTO users (name, surname, email, password, confirmToken, isConfirmed, isAdmin) VALUES ('Shamil', 'Gasanov', 'admin@gmail.com', 'c0d046f9f23ad7aa200b226f5d6d964d', null,true, true);
