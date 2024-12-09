-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 20, 2024 at 11:07 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_apotik_rifkah`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_alternatif`
--

CREATE TABLE `tbl_alternatif` (
  `id` int(11) NOT NULL,
  `kode` varchar(20) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `merek` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `tbl_alternatif`
--

INSERT INTO `tbl_alternatif` (`id`, `kode`, `nama`, `merek`) VALUES
(1, 'A1', 'OBH Combi Batuk Berdahak', 'OBH Combi'),
(2, 'A2', 'Woods Cough Syrup', 'Woods'),
(3, 'A3', 'Konidin Antihistamin', 'Konidin');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kriteria`
--

CREATE TABLE `tbl_kriteria` (
  `id` int(11) NOT NULL,
  `kode` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `bobot` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `tbl_kriteria`
--

INSERT INTO `tbl_kriteria` (`id`, `kode`, `nama`, `bobot`) VALUES
(1, 'C1', 'Usia', '35'),
(2, 'C2', 'Harga', '30'),
(3, 'C3', 'Tipe Obat', '20'),
(4, 'C4', 'Efek Samping', '15');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_nilai_alternatif`
--

CREATE TABLE `tbl_nilai_alternatif` (
  `id` int(11) NOT NULL,
  `alternatif_id` int(11) DEFAULT NULL,
  `kriteria_id` int(11) DEFAULT NULL,
  `sub_kriteria_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `tbl_nilai_alternatif`
--

INSERT INTO `tbl_nilai_alternatif` (`id`, `alternatif_id`, `kriteria_id`, `sub_kriteria_id`) VALUES
(1, 1, 1, 1),
(2, 1, 2, 6),
(3, 1, 3, 7),
(4, 1, 4, 11),
(5, 2, 1, 2),
(6, 2, 2, 5),
(7, 2, 3, 8),
(8, 2, 4, 10),
(9, 3, 1, 3),
(10, 3, 2, 4),
(11, 3, 3, 9),
(12, 3, 4, 12);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_peringkat`
--

CREATE TABLE `tbl_peringkat` (
  `id` int(11) NOT NULL,
  `id_alternatif` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `merek` varchar(50) NOT NULL,
  `nilai` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `tbl_peringkat`
--

INSERT INTO `tbl_peringkat` (`id`, `id_alternatif`, `nama`, `merek`, `nilai`) VALUES
(37, 3, 'Konidin Antihistamin', 'Konidin', 59),
(38, 1, 'OBH Combi Batuk Berdahak', 'OBH Combi', 79),
(39, 2, 'Woods Cough Syrup', 'Woods', 76.5);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_sub_kriteria`
--

CREATE TABLE `tbl_sub_kriteria` (
  `id` int(11) NOT NULL,
  `kriteria_id` int(11) DEFAULT NULL,
  `sub_nama` varchar(50) NOT NULL,
  `nilai_rating` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `tbl_sub_kriteria`
--

INSERT INTO `tbl_sub_kriteria` (`id`, `kriteria_id`, `sub_nama`, `nilai_rating`) VALUES
(1, 1, 'Dewasa', 100),
(2, 1, 'Anak-anak', 70),
(3, 1, 'Bayi', 40),
(4, 2, 'Murah', 100),
(5, 2, 'Terjangkau', 70),
(6, 2, 'Mahal', 40),
(7, 3, 'Berdahak', 100),
(8, 3, 'Kering', 80),
(9, 3, 'Antihistamin', 60),
(10, 4, 'Ringan', 100),
(11, 4, 'Sedang', 80),
(12, 4, 'Berat', 20);

-- --------------------------------------------------------

--
-- Table structure for table `tbl_user`
--

CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Dumping data for table `tbl_user`
--

INSERT INTO `tbl_user` (`id`, `nama`, `username`, `password`) VALUES
(1, 'Admin', 'admin', 'admin'),
(2, 'AlFathah', 'alfathah', '123');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_alternatif`
--
ALTER TABLE `tbl_alternatif`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_kriteria`
--
ALTER TABLE `tbl_kriteria`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_nilai_alternatif`
--
ALTER TABLE `tbl_nilai_alternatif`
  ADD PRIMARY KEY (`id`),
  ADD KEY `alternatif_id` (`alternatif_id`),
  ADD KEY `kriteria_id` (`kriteria_id`),
  ADD KEY `sub_kriteria_id` (`sub_kriteria_id`);

--
-- Indexes for table `tbl_peringkat`
--
ALTER TABLE `tbl_peringkat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tbl_sub_kriteria`
--
ALTER TABLE `tbl_sub_kriteria`
  ADD PRIMARY KEY (`id`),
  ADD KEY `kriteria_id` (`kriteria_id`);

--
-- Indexes for table `tbl_user`
--
ALTER TABLE `tbl_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_alternatif`
--
ALTER TABLE `tbl_alternatif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tbl_kriteria`
--
ALTER TABLE `tbl_kriteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tbl_nilai_alternatif`
--
ALTER TABLE `tbl_nilai_alternatif`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_peringkat`
--
ALTER TABLE `tbl_peringkat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;

--
-- AUTO_INCREMENT for table `tbl_sub_kriteria`
--
ALTER TABLE `tbl_sub_kriteria`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `tbl_user`
--
ALTER TABLE `tbl_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_nilai_alternatif`
--
ALTER TABLE `tbl_nilai_alternatif`
  ADD CONSTRAINT `tbl_nilai_alternatif_ibfk_1` FOREIGN KEY (`alternatif_id`) REFERENCES `tbl_alternatif` (`id`),
  ADD CONSTRAINT `tbl_nilai_alternatif_ibfk_2` FOREIGN KEY (`kriteria_id`) REFERENCES `tbl_kriteria` (`id`),
  ADD CONSTRAINT `tbl_nilai_alternatif_ibfk_3` FOREIGN KEY (`sub_kriteria_id`) REFERENCES `tbl_sub_kriteria` (`id`);

--
-- Constraints for table `tbl_sub_kriteria`
--
ALTER TABLE `tbl_sub_kriteria`
  ADD CONSTRAINT `tbl_sub_kriteria_ibfk_1` FOREIGN KEY (`kriteria_id`) REFERENCES `tbl_kriteria` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
