-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jan 25, 2022 at 02:47 PM
-- Server version: 5.7.20-log
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `salle_sports`
--

-- --------------------------------------------------------

--
-- Table structure for table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(80) DEFAULT NULL,
  `date_entry` date DEFAULT NULL,
  `payment` float DEFAULT NULL,
  `type_sport` varchar(40) DEFAULT NULL,
  `image` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `clients`
--

INSERT INTO `clients` (`first_name`, `last_name`, `email`, `phone`, `address`, `date_entry`, `payment`, `type_sport`, `image`) VALUES
('Ezzaki', 'Hamza', 'ha@gmail.com', '0666887711', 'Hawara', '2022-01-02', 500, 'Karate', 'file:/D:/img/profile_yassin.jpg'),
('Karim', 'Wahid', 'K@gmail.com', '0777668811', 'Lastah Taroudant', '2021-12-29', 300, 'Karate', 'file:/D:/img/profil.jpg'),
('Oussama', 'Rami', 'g@gmail.com', '0777889900', 'Agadir', '2022-01-18', 700, 'Musculation', 'file:/D:/img/download%20(1).jpg'),
('Yassin', 'Zghrod', 'yassi11@gmail.com', '06661455', 'Taroudant ', '2022-01-27', 500, 'Box', 'file:/D:/img/profil-avatar-petit-enfant_18591-50926.jpg'),
('Hafid ', 'Ess', 'Ha@gmail.com', '0711100022', 'Agadir', '2022-01-16', 500, 'Musculation', 'file:/D:/img/EU2McT3XsAE2jEA.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `coachs`
--

DROP TABLE IF EXISTS `coachs`;
CREATE TABLE IF NOT EXISTS `coachs` (
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(80) DEFAULT NULL,
  `date_entry` date DEFAULT NULL,
  `salary` float DEFAULT NULL,
  `type_sport` varchar(40) DEFAULT NULL,
  `image` text
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `coachs`
--

INSERT INTO `coachs` (`first_name`, `last_name`, `email`, `phone`, `address`, `date_entry`, `salary`, `type_sport`, `image`) VALUES
('Lahcen', 'Ess', 'ess@gmail.com', '0666778899', 'Tr', '2021-12-30', 5000, 'Karate', 'file:/C:/Users/Sys/Desktop/IMG.jpg'),
('HHHHHHHHHH', 'pppppp', 'kkkkkkkkkkkk', '0000000000', 'jjjjjjj', '2022-02-03', 5000, 'Karate', 'file:/C:/Users/Sys/Desktop/Img/téléchargement.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `username` varchar(40) NOT NULL DEFAULT 'admin',
  `password` varchar(40) NOT NULL DEFAULT 'admin'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`username`, `password`) VALUES
('hh', 'hh'),
('bella', '123455');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
