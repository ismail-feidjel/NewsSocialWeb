-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 29, 2022 at 12:38 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 7.4.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `techwebdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `uname` varchar(50) DEFAULT NULL,
  `upass` varchar(50) DEFAULT NULL,
  `uemail` varchar(50) DEFAULT NULL,
  `umobile` varchar(50) DEFAULT NULL,
  `grade` varchar(20) NOT NULL,
  `statuss` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `uname`, `upass`, `uemail`, `umobile`, `grade`, `statuss`) VALUES
(11, 'isamil', '1', 'feid@b.r', '2233', 'member', 'online'),
(12, 'mohammed', 'azerty', 'ggggggg@gmail.com', '066656554', 'admin', 'offline'),
(13, 'salah', 'ben', 'sa20177@gmail.com', '2233', 'member', 'online'),
(14, 'hamid', 'azerty', 'mamid@gmail.comm', '2233', '', ''),
(15, 'MOODLE AUTH', 'h', 'feidjelismail@gmail.com', '', 'member', 'online'),
(16, 'ismoz', 'aze', 'ggggggg@gmail.com', '06365965295', 'member', 'blocked'),
(17, 'salim', 'aze', 'ggggggg@gmail.com', '668767676', 'member', 'blocked'),
(18, 'lilia', 'aze', 'ggggggg@gmail.com', '4564566', 'member', 'offline'),
(19, 'mariam', 'aze', 'ggggggg@gmail.com', '45664564', 'member', 'offline'),
(20, 'zakaria', 'aze', 'ggggggg@gmail.com', '46545666', 'member', 'online'),
(21, 'soousou', 'aze', 'ggggggg@gmail.com', '46656465', 'member', 'offline');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
