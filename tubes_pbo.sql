-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 24, 2023 at 05:58 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `tubes_pbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `pengaduan_masyarakat`
--

CREATE TABLE `pengaduan_masyarakat` (
  `id` int(11) NOT NULL,
  `nama_pelapor` varchar(50) DEFAULT NULL,
  `judul_laporan` varchar(20) DEFAULT NULL,
  `lokasi_kejadian` varchar(20) DEFAULT NULL,
  `tanggal_kejadian` date DEFAULT NULL,
  `deskripsi_kejadian` text DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pengaduan_masyarakat`
--

INSERT INTO `pengaduan_masyarakat` (`id`, `nama_pelapor`, `judul_laporan`, `lokasi_kejadian`, `tanggal_kejadian`, `deskripsi_kejadian`, `status`) VALUES
(10, 'garry', 'judul 3', 'lokasi 3', '2023-07-12', 'deskripsi 3', 'Diproses ta'),
(14, 'badruz', 'judul 4', 'lokasi 4', '2023-07-04', 'There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don\'t look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there', 'Menunggu');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pengaduan_masyarakat`
--
ALTER TABLE `pengaduan_masyarakat`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pengaduan_masyarakat`
--
ALTER TABLE `pengaduan_masyarakat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
