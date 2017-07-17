DROP TABLE IF EXISTS stats;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS url;

CREATE TABLE `users` (
	`userId` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	`name` varchar(100) NOT NULL,
	`surname` varchar(100) NOT NULL,
	`email` varchar(100) NOT NULL UNIQUE,
	`accountType` varchar(100) NOT NULL,
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
	`nbClicks` INT NULL,
	`totalClicks` INT NULL,
	`userId` INT,
	`generic` BOOLEAN
);

CREATE TABLE `clicks` (
	`clickId` INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	`shortUrl` varchar(100) NOT NULL,
	`ipAddress` varchar(15),
	`country` varchar(100) DEFAULT NULL,
	`visitDate` DATE NOT NULL,
	`browser` varchar(100) DEFAULT NULL,
	`os` varchar(100) DEFAULT NULL
);

ALTER TABLE `url` ADD CONSTRAINT `url_fk0` FOREIGN KEY (`userId`) REFERENCES `users`(`userId`);

ALTER TABLE `clicks` ADD CONSTRAINT `clicks_fk0` FOREIGN KEY (`shortUrl`) REFERENCES `url`(`shortUrl`);

INSERT INTO `users` (`name`, `surname`, `email`, `accountType`, `password`, `confirmToken`, `isConfirmed`, `isAdmin`) VALUES 
('Shamil', 'Gasanov', 'admin@gmail.com', 'particular','c0d046f9f23ad7aa200b226f5d6d964d', '1234',TRUE, TRUE);

INSERT INTO `url`(`shortUrl`, `longUrl`, `createDate`, `startDate`, `expireDate`, `password`, `captcha`, `nbClicks`, `totalClicks`, `userId`, `generic`) VALUES 
('exemple', 'http://google.com', '2017-01-01 00:00:00', NULL, NULL, 'test', FALSE, 7, 20, 1, FALSE);

INSERT INTO `clicks`(`shortUrl`, `ipAddress`, `country`, `visitDate`, `browser`, `os`) VALUES 
('exemple', '33.33.33.1', 'France', '2017-01-07', 'Mozilla', 'Windows'),
('exemple', '52.52.52.1', 'Italy', '2017-02-04', 'Chrome', 'Windows'),
('exemple', '33.33.33.2', 'France', '2017-03-18', 'Mozilla', 'Windows'),
('exemple', '33.33.33.3', 'France', '2017-03-18', 'Safari', 'Windows'),
('exemple', '33.33.33.4', 'France', '2017-04-16', 'Chrome', 'Windows'),
('exemple', '49.49.49.1', 'Germany', '2017-04-16', 'Chrome', 'Windows'),
('exemple', '52.52.52.2', 'Italy', '2017-06-28', 'Safari', 'Windows');