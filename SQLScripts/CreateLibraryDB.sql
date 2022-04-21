-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.7.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             11.3.0.6295
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for librarydb
DROP DATABASE IF EXISTS `librarydb`;
CREATE DATABASE IF NOT EXISTS `librarydb` /*!40100 DEFAULT CHARACTER SET utf8mb3 */;
USE `librarydb`;

-- Dumping structure for table librarydb.books
DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `price` double unsigned NOT NULL,
  `bcondition` enum('DAMAGED','GOOD') NOT NULL,
  `bstatus` enum('AVAILABLE','CHECKEDOUT','LOST') NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `author` varchar(512) NOT NULL,
  `category` varchar(512) NOT NULL,
  `img` varchar(512) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table librarydb.books: ~0 rows (approximately)
DELETE FROM `books`;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` (`title`, `price`, `bcondition`, `bstatus`, `author`, `category`, `img`) VALUES
	('Linux in easy steps', 16.99, 'Good', 'Available', 'Mike McGrath', 'Operating systems', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781840789379&issn=/MC.GIF'),
	('Java in easy steps', 15.99, 'Good', 'Available', 'Mike McGrath', 'Computer programming', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781840787535&issn=/MC.GIF'),
	('JavaScript in easy steps', 15.99, 'Good', 'Available', 'Mike McGrath', 'Computer programming', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=1140154076&isbn=9781840788778&issn=/MC.GIF'),
	('How to Cook Everything: Vegetarian', 35, 'Good', 'Available', 'Mark Bittman', 'Cooking, Food', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780764524837&issn=/MC.GIF'),
	('How to Cook Everything Fast: a better way to cook great food', 37, 'Good', 'Available', 'Mark Bittman', 'Cooking, Food', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=880705562&isbn=9780470936306&issn=/MC.GIF'),
	('American wholefoods cuisine : 1300 meatless wholesome recipes from short order to gourmet', 21.95, 'Good', 'Available', 'Nikki Goldbeck', 'Cooking, Food', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=978188610111&oclc=&isbn=9781886101111&issn=/MC.GIF'),
	('Mathematical Puzzles and Curiosities', 9.95, 'Good', 'Available', 'Barry R. Clarke', 'Mathematics', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780486315720&issn=/LC.JPG'),
	('What in the world? : fun-tastic photo puzzles for curious minds', 16.99, 'Good', 'Available', 'Julie Vosburgh Agnone', 'Optical illusions, puzzles', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781426315176&issn=/MC.GIF'),
	('Amazing rivers : 100+ waterways that will boggle your mind', 24, 'Good', 'Available', 'Julie Vosburgh Agnone', 'Rivers', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781912920266&issn=/MC.GIF'),
	('Origin of everyday things', 10.95, 'Good', 'Available', 'Johnny Acton', 'Encyclopedias', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781402743023&issn=/MC.GIF'),
	('International encyclopedia of women and sports', 495, 'Good', 'Available', 'Karen Christensen', 'Encyclopedias', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780028649542&issn=/MC.GIF'),
	('Thoughtful gardening', 17.99, 'Good', 'Available', 'Robin Lane Fox', 'Gardening', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780465021963&issn=/MC.GIF'),
	('Guide to Minnesota vegetable gardening', 13.45, 'Good', 'Available', 'James Alfred Fizzell', 'Gardening', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=166368305&isbn=9781591864035&issn=/LC.JPG'),
	('Fairy gardening : creating your own magical miniature garden', 16.95, 'Good', 'Available', 'Julie Bawden-Davis', 'Gardening', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781616088330&issn=/MC.GIF'),
	('Observatories in space', 4.75, 'Good', 'Available', 'Darlene R. Stille', 'Astronomy, Outer space', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9780716624981&issn=/MC.GIF'),
	('Astronomy lab for kids : 52 family-friendly activities', 24.99, 'Good', 'Available', 'Michelle Nichols', 'Astronomy', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781631591341&issn=/MC.GIF'),
	('Dark skies : a practical guide to astrotourism', 22.99, 'Good', 'Available', 'Valerie Stimac', 'Astronomy', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781788686198&issn=/MC.GIF'),
	('Space exploration : a history in 100 objects', 25, 'Good', 'Available', 'Sten F. Odenwald', 'Astronomy, Outer space', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781615196142&issn=/MC.GIF'),
	('Thinking better : the art of the shortcut in math and life', 26.99, 'Good', 'Available', 'Marcus Du Sautoy', 'Mathematics', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781541600362&issn=/MC.GIF'),
	('How mathematics happened : the first 50,000 years', 26, 'Good', 'Available', 'Peter Strom Rudman', 'Mathematics', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=&isbn=9781591024774&issn=/LC.JPG'),
	('The Storyteller: Tales of Life and Music', 29.99, 'Good', 'Available', 'Dave Grohl', 'Autobiography, Music', 'https://booksite-app.appspot.com/coverart/7295/9780063076099.jpg'),
	('These Precious Days: Essays', 18, 'Good', 'Available', 'Ann Patchett', 'Essays', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=1262750080&isbn=9780063136847&issn=/MC.GIF'),
	('How to be perfect : the correct answer to every moral question', 28.99, 'Good', 'Available', 'Michael Schur', 'Humor', 'https://secure.syndetics.com/index.aspx?type=xw12&client=wclip&upc=&oclc=1281791543&isbn=9781982159313&issn=/MC.GIF'),
	('Taste: My Life Through Food', 28, 'Good', 'Available', 'Stanley Tucci', 'Autobiography, Food', 'https://img1.od-cdn.com/ImageType-200/0439-1/%7BA37418F1-6792-467B-9C57-A9E2A94C9A18%7DImg200.jpg');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

-- Dumping structure for table librarydb.borrowedbooks
DROP TABLE IF EXISTS `borrowedbooks`;
CREATE TABLE IF NOT EXISTS `borrowedbooks` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(5) unsigned NOT NULL,
  `user_id` int(5) unsigned NOT NULL,
  `borrow_date` date NOT NULL,
  `due_date` date NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `bstatus` enum('CHECKEDOUT','RETURNED') NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_borrowedbooks_books` (`book_id`),
  KEY `FK_borrowedbooks_users` (`user_id`),
  CONSTRAINT `FK_borrowedbooks_books` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_borrowedbooks_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table librarydb.userbooks
DROP TABLE IF EXISTS `userbooks`;
CREATE TABLE IF NOT EXISTS `userbooks` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `book_id` int(5) unsigned NOT NULL,
  `user_id` int(5) unsigned NOT NULL,
  `borrow_date` date NOT NULL,
  `due_date` date NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_borrowedbooks_books` (`book_id`) USING BTREE,
  KEY `FK_borrowedbooks_users` (`user_id`) USING BTREE,
  CONSTRAINT `userbooks_ibfk_1` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userbooks_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 ROW_FORMAT=DYNAMIC;

-- Data exporting was unselected.

-- Dumping structure for table librarydb.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(5) unsigned NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `last_name` varchar(45) NOT NULL,
  `address_line1` varchar(100) NOT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `city` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `zip` varchar(5) NOT NULL,
  `phone` varchar(12) NOT NULL,
  `password` varchar(100) NOT NULL,
  `ustatus` enum('ACTIVE','INACTIVE') NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `gender` varchar(10) NOT NULL,
  `news` varchar(5) NOT NULL DEFAULT 'YES',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

-- Dumping structure for table librarydb.user_roles
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(5) unsigned NOT NULL,
  `user_role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_roles_users` (`user_id`),
  CONSTRAINT `FK_user_roles_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Data exporting was unselected.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
