-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 18 Des 2025 pada 18.02
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `super_celluler`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_aksesoris`
--

CREATE TABLE `tb_aksesoris` (
  `id_aksesoris` varchar(10) NOT NULL,
  `nama_aksesoris` varchar(64) NOT NULL,
  `harga_beliAk` int(25) NOT NULL,
  `harga_jualAk` int(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_aksesoris`
--

INSERT INTO `tb_aksesoris` (`id_aksesoris`, `nama_aksesoris`, `harga_beliAk`, `harga_jualAk`) VALUES
('A001', 'Power Bank Robot 10.000 mAh', 100000, 130000),
('A002', 'Power Bank Robot 20.000 mAh', 220000, 280000),
('A003', 'Kabel Data Type C Xiaomi', 50000, 80000),
('A004', 'Kepala Charger Xiaomi 33 W', 120000, 150000),
('A005', 'Kabel Data Type Micro USB Xiaomi', 35000, 55000),
('A006', 'Softcase Xiaomi Redmi 10', 35000, 60000),
('A007', 'Softcase Xiaomi Redmi 10 5G', 30000, 55000),
('A008', 'Hardcase Xiaomi Redmi 10', 45000, 65000),
('A009', 'Hardcase Xiaomi Redmi 10 5G', 50000, 75000),
('A010', 'Earphone Wired Baseus', 75000, 110000),
('A011', 'Earphone Wired Ugreen', 50000, 80000),
('A012', 'Tempered Glass Xiaomi 10', 15000, 25000),
('A013', 'Tempered Glass Xiaomi 10 5G', 25000, 45000);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_kasir`
--

CREATE TABLE `tb_kasir` (
  `id_kasir` char(15) NOT NULL,
  `sandi` char(15) NOT NULL,
  `nama` char(25) NOT NULL,
  `jenkel` char(15) NOT NULL,
  `hp` char(15) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_kasir`
--

INSERT INTO `tb_kasir` (`id_kasir`, `sandi`, `nama`, `jenkel`, `hp`, `alamat`) VALUES
('AD01', '123', 'Ica', 'Perempuan', '081312341234', 'Jakarta Selatan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_nota`
--

CREATE TABLE `tb_nota` (
  `id_nota` varchar(10) NOT NULL,
  `tanggal` date NOT NULL,
  `id_kasir` varchar(15) NOT NULL,
  `id_pelanggan` varchar(15) NOT NULL,
  `id_teknisi` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_nota`
--

INSERT INTO `tb_nota` (`id_nota`, `tanggal`, `id_kasir`, `id_pelanggan`, `id_teknisi`) VALUES
('IN0001', '2025-12-18', 'AD01', 'P001', 'T01'),
('IN0002', '2025-12-18', 'AD01', 'P002', 'T03'),
('IN0003', '2025-12-18', 'AD01', 'P003', 'T02'),
('IN0004', '2025-12-18', 'AD01', 'P004', 'T03'),
('IN0005', '2025-12-18', 'AD01', 'P005', 'T01'),
('IN0006', '2025-12-18', 'AD01', 'P007', 'T03'),
('IN0007', '2025-12-18', 'AD01', 'P008', 'T02');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_nota_detail`
--

CREATE TABLE `tb_nota_detail` (
  `id_nota` varchar(10) NOT NULL,
  `id_item` varchar(10) NOT NULL,
  `nama_item` varchar(50) NOT NULL,
  `harga_beli` int(100) NOT NULL,
  `harga_jual` int(100) NOT NULL,
  `kuantitas` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_nota_detail`
--

INSERT INTO `tb_nota_detail` (`id_nota`, `id_item`, `nama_item`, `harga_beli`, `harga_jual`, `kuantitas`) VALUES
('IN0001', 'S001', 'Layar LCD', 0, 100000, 1),
('IN0001', 'SP001', 'Xiaomi Redmi 10', 250000, 350000, 1),
('IN0001', 'S002', 'Baterai', 0, 100000, 1),
('IN0001', 'SP015', 'Xiaomi Redmi 10', 100000, 150000, 1),
('IN0001', 'A006', 'Softcase Xiaomi Redmi 10', 35000, 60000, 1),
('IN0002', 'S012', 'Software', 0, 0, 1),
('IN0002', 'SP030', 'Virus/Malware Removal Xiaomi/Redmi', 0, 150000, 1),
('IN0002', 'S012', 'Software', 0, 0, 1),
('IN0002', 'SP028', 'Unlock Password/Pola Xiaomi/Redmi', 0, 150000, 1),
('IN0002', 'A001', 'Power Bank Robot 10.000 mAh', 100000, 130000, 1),
('IN0003', 'S002', 'Baterai', 0, 100000, 1),
('IN0003', 'SP019', 'Xiaomi Redmi Note 10', 180000, 250000, 1),
('IN0003', 'A003', 'Kabel Data Type C Xiaomi', 50000, 80000, 1),
('IN0004', 'S012', 'Software', 0, 0, 1),
('IN0004', 'SP030', 'Virus/Malware Removal Xiaomi/Redmi', 0, 150000, 1),
('IN0004', 'A008', 'Hardcase Xiaomi Redmi 10', 45000, 65000, 1),
('IN0005', 'S001', 'Layar LCD', 0, 100000, 1),
('IN0005', 'SP001', 'Xiaomi Redmi 10', 250000, 350000, 1),
('IN0006', 'S012', 'Software', 0, 0, 1),
('IN0006', 'SP029', 'Upgrade/Downgrade OS Xiaomi/Redmi', 0, 100000, 1),
('IN0007', 'S001', 'Layar LCD', 0, 100000, 1),
('IN0007', 'SP005', 'Xiaomi Redmi 10C', 250000, 320000, 1),
('IN0007', 'A009', 'Hardcase Xiaomi Redmi 10 5G', 50000, 75000, 1);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_pelanggan`
--

CREATE TABLE `tb_pelanggan` (
  `id_pelanggan` char(15) NOT NULL,
  `nama_pelanggan` char(25) NOT NULL,
  `hp_pelanggan` char(15) NOT NULL,
  `jk_pelanggan` char(25) NOT NULL,
  `almt_pelanggan` text NOT NULL,
  `tanggal_pelanggan` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_pelanggan`
--

INSERT INTO `tb_pelanggan` (`id_pelanggan`, `nama_pelanggan`, `hp_pelanggan`, `jk_pelanggan`, `almt_pelanggan`, `tanggal_pelanggan`) VALUES
('P001', 'Martin Aron Samuel', '202243501642', 'Laki-Laki', 'Bekasi', '2025-11-15'),
('P002', 'Amelia Putri Saepudin', '202243501673', 'Perempuan', 'Jakarta', '2025-11-16'),
('P003', 'Ahmad Nur Latif Prayoga', '202243501659', 'Laki-Laki', 'Jakarta Selatan', '2025-12-16'),
('P004', 'Nuryanda Sunanta', '202243501652', 'Laki-Laki', 'Depok', '2025-12-16'),
('P005', 'Andrian Yogi Saputra', '202243501639', 'Laki-Laki', 'Jakarta Selatan', '2025-12-16'),
('P006', 'Naafira Aliyssa Putri', '202243501656', 'Perempuan', 'Bogor', '2025-12-16'),
('P007', 'Achmad Rifky Meishandy', '202243501674', 'Laki-Laki', 'Jakarta Selatan', '2025-12-16'),
('P008', 'Naufal Rafif', '202243501684', 'Laki-Laki', 'Bojong', '2025-12-16');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_service`
--

CREATE TABLE `tb_service` (
  `id_service` varchar(10) NOT NULL,
  `jenis_service` varchar(200) NOT NULL,
  `biaya_service` int(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_service`
--

INSERT INTO `tb_service` (`id_service`, `jenis_service`, `biaya_service`) VALUES
('S001', 'Layar LCD', 100000),
('S002', 'Baterai', 100000),
('S003', 'Modul kamera depan/belakang', 150000),
('S004', 'Speaker dan mikrofon', 100000),
('S005', 'IC power', 200000),
('S006', 'IC charger dan konektor charger', 150000),
('S007', 'IC sinyal', 120000),
('S008', 'Port headset', 75000),
('S009', 'Tombol power', 50000),
('S010', 'Konektor baterai', 100000),
('S011', 'Backglass', 75000),
('S012', 'Software', 0);

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_sparepart`
--

CREATE TABLE `tb_sparepart` (
  `kd_barang` varchar(10) NOT NULL,
  `nama_barang` varchar(200) NOT NULL,
  `harga_beli` int(100) NOT NULL,
  `harga_jual` int(100) NOT NULL,
  `jenis_barang` varchar(200) NOT NULL,
  `merk_barang` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_sparepart`
--

INSERT INTO `tb_sparepart` (`kd_barang`, `nama_barang`, `harga_beli`, `harga_jual`, `jenis_barang`, `merk_barang`) VALUES
('SP001', 'Xiaomi Redmi 10', 250000, 350000, 'Layar LCD', 'Xiaomi'),
('SP002', 'Xiaomi Redmi 10 2022', 300000, 400000, 'Layar LCD', 'Xiaomi'),
('SP003', 'Xiaomi Redmi 10 5G', 350000, 420000, 'Layar LCD', 'Xiaomi'),
('SP004', 'Xiaomi Redmi 10A', 200000, 300000, 'Layar LCD', 'Xiaomi'),
('SP005', 'Xiaomi Redmi 10C', 250000, 320000, 'Layar LCD', 'Xiaomi'),
('SP006', 'Xiaomi Redmi Note 10', 300000, 400000, 'Layar LCD', 'Xiaomi'),
('SP007', 'Xiaomi Redmi Note 10 5G', 300000, 400000, 'Layar LCD', 'Xiaomi'),
('SP008', 'OPPO Reno6 5G', 150000, 200000, 'Layar LCD', 'OPPO'),
('SP009', 'OPPO Reno7 5G', 200000, 280000, 'Layar LCD', 'OPPO'),
('SP010', 'OPPO Reno8 T', 300000, 350000, 'Layar LCD', 'OPPO'),
('SP011', 'Infinix Hot 4 Pro', 200000, 280000, 'Layar LCD', 'Infinix'),
('SP012', 'Infinix GT 10 Pro', 350000, 400000, 'Layar LCD', 'Infinix'),
('SP013', 'Infinix GT 20 Pro 5G', 300000, 380000, 'Layar LCD', 'Infinix'),
('SP014', 'Infinix GT 30', 350000, 420000, 'Layar LCD', 'Infinix'),
('SP015', 'Xiaomi Redmi 10', 100000, 150000, 'Baterai', 'Xiaomi'),
('SP016', 'Xiaomi Redmi 10 2022', 150000, 180000, 'Baterai', 'Xiaomi'),
('SP017', 'Xiaomi Redmi 10 5G', 200000, 250000, 'Baterai', 'Xiaomi'),
('SP018', 'Xiaomi Redmi 10A', 150000, 200000, 'Baterai', 'Xiaomi'),
('SP019', 'Xiaomi Redmi Note 10', 180000, 250000, 'Baterai', 'Xiaomi'),
('SP020', 'OPPO Reno6 5G', 100000, 150000, 'Baterai', 'OPPO'),
('SP021', 'OPPO Reno7 5G', 150000, 200000, 'Baterai', 'OPPO'),
('SP022', 'OPPO Reno8 T', 180000, 250000, 'Baterai', 'OPPO'),
('SP023', 'Infinix GT 10 Pro', 200000, 250000, 'Baterai', 'Infinix'),
('SP024', 'Infinix GT 20 Pro 5G', 250000, 350000, 'Baterai', 'Infinix'),
('SP025', 'Infinix GT 30', 350000, 420000, 'Baterai', 'Infinix'),
('SP026', 'Install ulang OS Xiaomi/Redmi', 0, 100000, 'Software', 'Xiaomi/Redmi'),
('SP027', 'Repair IMEI Xiaomi/Redmi', 0, 100000, 'Software', 'Xiaomi/Redmi'),
('SP028', 'Unlock Password/Pola Xiaomi/Redmi', 0, 150000, 'Software', 'Xiaomi/Redmi'),
('SP029', 'Upgrade/Downgrade OS Xiaomi/Redmi', 0, 100000, 'Software', 'Xiaomi/Redmi'),
('SP030', 'Virus/Malware Removal Xiaomi/Redmi', 0, 150000, 'Software', 'Xiaomi/Redmi');

-- --------------------------------------------------------

--
-- Struktur dari tabel `tb_teknisi`
--

CREATE TABLE `tb_teknisi` (
  `id_teknisi` varchar(15) NOT NULL,
  `nama_teknisi` varchar(35) NOT NULL,
  `hp_teknisi` char(15) NOT NULL,
  `jk_teknisi` varchar(15) NOT NULL,
  `almt_teknisi` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `tb_teknisi`
--

INSERT INTO `tb_teknisi` (`id_teknisi`, `nama_teknisi`, `hp_teknisi`, `jk_teknisi`, `almt_teknisi`) VALUES
('T01', 'Fadli [Hardware]', '085812341234', 'Laki-Laki', 'Depok'),
('T02', 'Leo [Hardware]', '081212341234', 'Laki-Laki', 'Jakarta'),
('T03', 'Dimas [Software]', '081212341111', 'Laki-Laki', 'Depok'),
('T04', 'Samsir [Software]', '081112341234', 'Laki-Laki', 'Jakarta');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `tb_aksesoris`
--
ALTER TABLE `tb_aksesoris`
  ADD PRIMARY KEY (`id_aksesoris`);

--
-- Indeks untuk tabel `tb_kasir`
--
ALTER TABLE `tb_kasir`
  ADD PRIMARY KEY (`id_kasir`);

--
-- Indeks untuk tabel `tb_nota`
--
ALTER TABLE `tb_nota`
  ADD PRIMARY KEY (`id_nota`);

--
-- Indeks untuk tabel `tb_pelanggan`
--
ALTER TABLE `tb_pelanggan`
  ADD PRIMARY KEY (`id_pelanggan`);

--
-- Indeks untuk tabel `tb_service`
--
ALTER TABLE `tb_service`
  ADD PRIMARY KEY (`id_service`);

--
-- Indeks untuk tabel `tb_sparepart`
--
ALTER TABLE `tb_sparepart`
  ADD PRIMARY KEY (`kd_barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
