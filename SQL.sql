DROP DATABASE if EXISTS mini_mart_java;
CREATE DATABASE mini_mart_java;
USE mini_mart_java;
-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3306
-- Thời gian đã tạo: Th5 15, 2025 lúc 02:25 PM
-- Phiên bản máy phục vụ: 8.4.3
-- Phiên bản PHP: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `mini_mart_java`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `bill`
--

CREATE TABLE `bill` (
  `id` int NOT NULL,
  `idStaff` int DEFAULT NULL,
  `idOfferBill` int DEFAULT NULL,
  `idCustomer` int DEFAULT NULL,
  `dateCreate` date DEFAULT NULL,
  `price` double DEFAULT NULL,
  `state` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Chưa thanh toán'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `bill`
--

INSERT INTO `bill` (`id`, `idStaff`, `idOfferBill`, `idCustomer`, `dateCreate`, `price`, `state`) VALUES
(8, 51, NULL, NULL, '2025-05-08', 265000, 'Đã thanh toán'),
(11, 51, NULL, NULL, '2025-05-10', 72862, 'Đã thanh toán'),
(13, 51, 16, NULL, '2025-05-14', 38760, 'Đã thanh toán'),
(14, 51, 16, NULL, '2025-05-15', 115600, 'Đã thanh toán'),
(15, 51, NULL, NULL, '2025-05-15', 35000, 'Đã thanh toán'),
(22, 51, NULL, NULL, '2025-05-15', NULL, 'Chưa thanh toán');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `billinfo`
--

CREATE TABLE `billinfo` (
  `id` int NOT NULL,
  `idBill` int DEFAULT NULL,
  `idProduct` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `discount` int DEFAULT NULL,
  `total` double DEFAULT NULL,
  `unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nameProduct` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `billinfo`
--

INSERT INTO `billinfo` (`id`, `idBill`, `idProduct`, `price`, `quantity`, `discount`, `total`, `unit`, `nameProduct`) VALUES
(8, 8, 23, 15000, 1, 0, 15000, 'Hũ', 'Muối ớt Chinsu'),
(9, 8, 1, 5000, 50, 0, 250000, 'Gói', 'Mì gói Hảo Hảo'),
(14, 11, 6, 60000, 1, 0, 60000, 'Khay', 'Thịt ba chỉ'),
(15, 11, 1, 5000, 1, 99, 50, 'Gói', 'Mì gói Hảo Hảo'),
(16, 11, 52, 2100, 1, 0, 2100, 'kg', 'éhột '),
(17, 11, 9, 45000, 1, 0, 45000, 'Túi', 'Táo Mỹ'),
(19, 13, 19, 45000, 1, 0, 45000, 'Cuộn', 'Giấy vệ sinh Pulppy'),
(20, 13, 22, 12000, 1, 0, 12000, 'Chai', 'Coca-Cola'),
(21, 14, 12, 20000, 1, 0, 20000, 'Cái', 'Bàn chải đánh răng'),
(22, 14, 18, 120000, 1, 0, 120000, 'Can', 'Nước giặt OMO'),
(23, 14, 20, 30000, 1, 0, 30000, 'Hộp', 'Khăn giấy ướt'),
(24, 15, 3, 35000, 1, 0, 35000, 'Chai', 'Nước mắm Nam Ngư'),
(35, 22, 1, 5000, 1, 0, 5000, 'Gói', 'Mì gói Hảo Hảo'),
(36, 22, 6, 60000, 1, 99, 600, 'Khay', 'Thịt ba chỉ'),
(37, 22, 41, 45000, 1, 0, 45000, 'Gói', 'Mì ống Barilla');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `id` int NOT NULL,
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lastName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `firstName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `state` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Đang hoạt động',
  `gender` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`id`, `phone`, `lastName`, `firstName`, `address`, `state`, `gender`) VALUES
(1, '0905123456', 'Nguyễn', 'Anh', '123 Đường Láng, Đống Đa, Hà Nội', 'Đang hoạt động', 'Nam'),
(2, '0987654321', 'Trần Thị', 'Mai', '45 Nguyễn Huệ, Quận 1, TP.HCM', 'Đang hoạt động', 'Nữ'),
(3, '0912345678', 'Lê Văn', 'Tuấn', '78 Trần Phú, Nha Trang, Khánh Hòa', 'Đang hoạt động', 'Nam'),
(4, '0938765432', 'Phạm', 'Lan', '12 Lê Lợi, Huế, Thừa Thiên Huế', 'Đang hoạt động', 'Nữ'),
(5, '0971234567', 'Hoàng Minh', 'Hùng', '56 Hùng Vương, Đà Nẵng', 'Đang hoạt động', 'Nam'),
(6, '0945678901', 'Đỗ Thị', 'Hồng', '89 Nguyễn Văn Linh, Hải Phòng', 'Đang hoạt động', 'Nữ'),
(7, '0967890123', 'Bùi', 'Nam', '34 Kim Mã, Ba Đình, Hà Nội', 'Đang hoạt động', 'Nam'),
(8, '0923456789', 'Vũ Thị', 'Ngọc', '67 Pasteur, Quận 3, TP.HCM', 'Đang hoạt động', 'Nữ'),
(9, '0956789012', 'Ngô', 'Khang', '23 Bạch Đằng, Đà Lạt, Lâm Đồng', 'Đang hoạt động', 'Nam'),
(10, '0990123456', 'Trương Thị', 'Thu', '15 Trần Hưng Đạo, Cần Thơ', 'Đang hoạt động', 'Nữ'),
(11, '0909876543', 'Phan Văn', 'Long', '90 Lê Đại Hành, Vinh, Nghệ An', 'Đang hoạt động', 'Nam'),
(12, '0934567890', 'Đặng', 'Hà', '27 Nguyễn Trãi, Thanh Hóa', 'Đang hoạt động', 'Nữ'),
(13, '0916789012', 'Lý Quốc', 'Hùng', '56 Phạm Ngũ Lão, Quy Nhơn, Bình Định', 'Đang hoạt động', 'Nam'),
(14, '0989012345', 'Hà Thị', 'Linh', '78 Nguyễn Thị Minh Khai, Vũng Tàu', 'Đang hoạt động', 'Nữ'),
(15, '0941234567', 'Mai', 'Tâm', '34 Lê Văn Sỹ, Tân Bình, TP.HCM', 'Đang hoạt động', 'Nam'),
(16, '0963456789', 'Nguyễn Thị', 'Duyên', '12 Nguyễn Đình Chiểu, Hà Nội', 'Đang hoạt động', 'Nữ'),
(17, '0927890123', 'Trần', 'Đức', '67 Trần Quốc Toàn, Đà Nẵng', 'Đang hoạt động', 'Nam'),
(18, '0950123456', 'Lê Thị', 'Thảo', '23 Lý Thường Kiệt, Hải Phòng', 'Đang hoạt động', 'Nữ'),
(19, '0975678901', 'Phạm Văn', 'Hòa', '89 Hùng Vương, Cần Thơ', 'Đang hoạt động', 'Nam'),
(20, '0992345678', 'Hoàng', 'Yến', '45 Lê Hồng Phong, Vinh, Nghệ An', 'Đang hoạt động', 'Nữ'),
(21, '0906789012', 'Đỗ Minh', 'Quang', '56 Nguyễn Huệ, Huế', 'Đang hoạt động', 'Nam'),
(22, '0939012345', 'Bùi Thị', 'Hương', '78 Trần Phú, Nha Trang', 'Đang hoạt động', 'Nữ'),
(23, '0913456789', 'Vũ', 'Tài', '34 Nguyễn Văn Cừ, Đà Lạt', 'Đang hoạt động', 'Nam'),
(24, '0985678901', 'Ngô Thị', 'Bích', '12 Bà Triệu, Vũng Tàu', 'Đang hoạt động', 'Nữ'),
(25, '0947890123', 'Trương', 'Nhật', '67 Lê Lợi, Quy Nhơn', 'Đang hoạt động', 'Nam'),
(26, '0960123456', 'Phan Thị', 'Ánh', '23 Nguyễn Trãi, Thanh Hóa', 'Đang hoạt động', 'Nữ'),
(27, '0922345678', 'Đặng Văn', 'Phúc', '89 Kim Đồng, Hà Nội', 'Đang hoạt động', 'Nam'),
(28, '0954567890', 'Lý', 'Nhung', '45 Võ Thị Sáu, TP.HCM', 'Đang hoạt động', 'Nữ'),
(29, '0976789012', 'Hà Minh', 'Hoàng', '78 Nguyễn Văn Linh, Đà Nẵng', 'Đang hoạt động', 'Nam'),
(30, '0998901234', 'Mai Thị', 'Kim', '12 Lê Đại Hành, Hải Phòng', 'Đang hoạt động', 'Nữ'),
(31, '0901234567', 'Nguyễn Văn', 'Bình', '56 Hùng Vương, Cần Thơ', 'Đang hoạt động', 'Nam'),
(32, '0933456789', 'Trần', 'Oanh', '34 Nguyễn Thị Minh Khai, Vũng Tàu', 'Đang hoạt động', 'Nữ'),
(33, '0915678901', 'Lê Quốc', 'Bảo', '67 Trần Hưng Đạo, Quy Nhơn', 'Đang hoạt động', 'Nam'),
(34, '0987890123', 'Phạm Thị', 'Vân', '23 Lê Văn Tám, Đà Lạt', 'Đang hoạt động', 'Nữ'),
(35, '0949012345', 'Hoàng Văn', 'Dũng', '89 Bạch Đằng, Huế', 'Đang hoạt động', 'Nam'),
(36, '0961234567', 'Đỗ', 'Tuyết', '45 Nguyễn Huệ, Nha Trang', 'Đang hoạt động', 'Nữ'),
(37, '0924567890', 'Bùi Minh', 'Thắng', '12 Lê Hồng Phong, Vinh', 'Đang hoạt động', 'Nam'),
(38, '0956789012', 'Vũ Thị', 'Hạnh', '78 Nguyễn Trãi, Thanh Hóa', 'Đang hoạt động', 'Nữ'),
(39, '0978901234', 'Ngô', 'Kiên', '56 Kim Mã, Hà Nội', 'Đang hoạt động', 'Nam'),
(40, '0990123456', 'Trương Thị', 'Lệ', '34 Pasteur, TP.HCM', 'Đang hoạt động', 'Nữ'),
(41, '0902345678', 'Phan', 'Trí', '67 Trần Quốc Toàn, Đà Nẵng', 'Đang hoạt động', 'Nam'),
(42, '0934567890', 'Đặng Thị', 'Mỹ', '23 Lý Thường Kiệt, Hải Phòng', 'Đang hoạt động', 'Nữ'),
(43, '0916789012', 'Lý Văn', 'Sơn', '89 Hùng Vương, Cần Thơ', 'Đang hoạt động', 'Nam'),
(44, '0989012345', 'Hà', 'Phương', '45 Lê Lợi, Vũng Tàu', 'Đang hoạt động', 'Nữ'),
(45, '0941234567', 'Mai Minh', 'Vũ', '12 Trần Phú, Quy Nhơn', 'Đang hoạt động', 'Nam'),
(46, '0963456789', 'Nguyễn', 'Hoa', '78 Nguyễn Văn Cừ, Đà Lạt', 'Đang hoạt động', 'Nữ'),
(47, '0925678901', 'Trần Văn', 'Lâm', '56 Bạch Đằng, Huế', 'Đang hoạt động', 'Nam'),
(48, '0957890123', 'Lê Thị', 'Quyên', '34 Nguyễn Huệ, Nha Trang', 'Đang hoạt động', 'Nữ'),
(49, '0979012345', 'Phạm', 'Hải', '67 Lê Hồng Phong, Vinh', 'Đang hoạt động', 'Nam'),
(50, '0991234567', 'Hoàng Thị', 'Tâm', '23 Nguyễn Trãi, Thanh Hóa', 'Đang hoạt động', 'Nữ'),
(51, '0928442444', 'Nguyen ', 'Nanh', 'Da new doc', 'Đang hoạt động', 'Nam');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `importorder`
--

CREATE TABLE `importorder` (
  `id` int NOT NULL,
  `idStaff` int DEFAULT NULL,
  `idProvider` int DEFAULT NULL,
  `dateCreate` date NOT NULL,
  `total` double DEFAULT NULL,
  `state` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'Chưa thanh toán'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `importorder`
--

INSERT INTO `importorder` (`id`, `idStaff`, `idProvider`, `dateCreate`, `total`, `state`) VALUES
(1, 1, 1, '2024-03-15', 2420000, 'Đã thanh toán'),
(2, 5, 3, '2024-06-22', 1848000, 'Đã thanh toán'),
(3, 10, 7, '2024-09-10', 5610000, 'Đã thanh toán'),
(4, 15, 2, '2024-11-05', 2227500, 'Đã thanh toán'),
(5, 20, 5, '2025-01-12', 3107500, 'Đã thanh toán'),
(6, 25, 9, '2024-02-28', 1870000, 'Đã thanh toán'),
(7, 30, 12, '2024-07-19', 2915000, 'Đã thanh toán'),
(8, 35, 15, '2024-10-03', 1567500, 'Đã thanh toán'),
(9, 40, 18, '2025-03-01', 4290000, 'Đã thanh toán'),
(10, 45, 20, '2024-04-17', 1925000, 'Đã thanh toán'),
(11, 2, 4, '2024-08-11', 1265000, 'Đã thanh toán'),
(12, 7, 6, '2024-12-25', 5060000, 'Đã thanh toán'),
(13, 12, 8, '2025-02-14', 2832500, 'Đã thanh toán'),
(14, 17, 10, '2024-05-30', 2310000, 'Đã thanh toán'),
(15, 22, 13, '2024-09-27', 3170000, 'Đã thanh toán'),
(16, 27, 16, '2024-01-20', 1782000, 'Đã thanh toán'),
(17, 32, 19, '2024-06-08', 1980000, 'Đã thanh toán'),
(18, 37, 1, '2024-11-15', 2750000, 'Đã thanh toán'),
(19, 42, 3, '2025-04-10', 2222000, 'Đã thanh toán'),
(20, 47, 5, '2024-03-02', 1815000, 'Đã thanh toán'),
(21, 3, 7, '2024-07-04', 4015000, 'Đã thanh toán'),
(22, 8, 9, '2024-10-20', 1881000, 'Đã thanh toán'),
(23, 13, 11, '2025-01-08', 1925000, 'Đã thanh toán'),
(24, 18, 14, '2024-04-25', 1320000, 'Đã thanh toán'),
(25, 23, 17, '2024-08-16', 2255000, 'Đã thanh toán'),
(26, 28, 20, '2024-12-10', 5115000, 'Đã thanh toán'),
(27, 33, 2, '2025-03-15', 1540000, 'Đã thanh toán'),
(28, 38, 4, '2024-02-10', 1881000, 'Đã thanh toán'),
(29, 43, 6, '2024-06-28', 2255000, 'Đã thanh toán'),
(30, 48, 8, '2024-09-05', 1188000, 'Đã thanh toán'),
(31, 4, 10, '2024-11-22', 3300000, 'Đã thanh toán'),
(32, 9, 12, '2025-02-20', 3465000, 'Đã thanh toán'),
(33, 14, 15, '2024-05-12', 3410000, 'Đã thanh toán'),
(34, 19, 18, '2024-08-03', 3520000, 'Đã thanh toán'),
(35, 24, 1, '2024-12-28', 2117500, 'Đã thanh toán'),
(36, 29, 3, '2025-04-05', 1540000, 'Đã thanh toán'),
(37, 34, 5, '2024-03-25', 2805000, 'Đã thanh toán'),
(38, 39, 7, '2024-07-15', 3190000, 'Đã thanh toán'),
(39, 44, 9, '2024-10-08', 2530000, 'Đã thanh toán'),
(40, 49, 11, '2025-01-25', 2365000, 'Đã thanh toán'),
(41, 6, 13, '2024-04-10', 1540000, 'Đã thanh toán'),
(42, 11, 16, '2024-08-22', 2915000, 'Đã thanh toán'),
(43, 16, 19, '2024-11-10', 5115000, 'Đã thanh toán'),
(44, 21, 2, '2025-03-08', 1980000, 'Đã thanh toán'),
(45, 26, 4, '2024-02-15', 3300000, 'Đã thanh toán'),
(46, 31, 6, '2024-06-05', 1804000, 'Đã thanh toán'),
(47, 36, 8, '2024-09-18', 3905000, 'Đã thanh toán'),
(48, 41, 10, '2024-12-15', 4097500, 'Đã thanh toán'),
(49, 46, 12, '2025-02-05', 3685000, 'Đã thanh toán'),
(50, 50, 14, '2024-05-20', 4158000, 'Đã thanh toán'),
(51, 51, 8, '2025-04-25', 6850000, 'Đã thanh toán'),
(53, 51, 4, '2025-04-25', 65000, 'Đã thanh toán'),
(54, 51, 1, '2025-04-25', 5100000, 'Đã thanh toán'),
(55, 51, 1, '2025-04-25', 5500000, 'Đã thanh toán'),
(57, 51, 11, '2025-05-10', 95000, 'Đã thanh toán'),
(58, 51, 2, '2025-05-10', 222000, 'Đã thanh toán'),
(59, 51, 2, '2025-05-10', 50400, 'Đã thanh toán'),
(60, 51, 4, '2025-05-12', 221300, 'Đã thanh toán'),
(61, 51, 7, '2025-05-14', 2700, 'Đã thanh toán');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `importorderdetail`
--

CREATE TABLE `importorderdetail` (
  `id` int NOT NULL,
  `idProduct` int NOT NULL,
  `idImportOrder` int NOT NULL,
  `quantity` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `nameProduct` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `importorderdetail`
--

INSERT INTO `importorderdetail` (`id`, `idProduct`, `idImportOrder`, `quantity`, `price`, `unit`, `nameProduct`) VALUES
(1, 5, 1, 50, 27500, 'Hộp', 'Sữa tươi Vinamilk'),
(2, 1, 1, 100, 5500, 'Gói', 'Mì gói Hảo Hảo'),
(3, 11, 1, 30, 16500, 'Gói', 'Kẹo socola KitKat'),
(4, 2, 2, 40, 13200, 'Chai', 'Nước ngọt Pepsi'),
(5, 25, 2, 60, 22000, 'Hộp', 'Sữa chua Vinamilk'),
(6, 14, 3, 20, 71500, 'Chai', 'Sữa tắm Lifebuoy'),
(7, 18, 3, 15, 132000, 'Can', 'Nước giặt OMO'),
(8, 39, 3, 50, 44000, 'Cuộn', 'Giấy vệ sinh Bless You'),
(9, 9, 4, 25, 49500, 'Túi', 'Táo Mỹ'),
(10, 29, 4, 30, 33000, 'Túi', 'Cam sành'),
(11, 6, 5, 10, 66000, 'Khay', 'Thịt ba chỉ'),
(12, 26, 5, 15, 60500, 'Khay', 'Thịt heo xay'),
(13, 46, 5, 20, 77000, 'Khay', 'Cá basa tươi'),
(14, 4, 6, 80, 11000, 'Gói', 'Snack Oishi'),
(15, 44, 6, 60, 16500, 'Gói', 'Khoai chiên Lay’s'),
(16, 17, 7, 50, 27500, 'Hộp', 'Cá ngừ đóng hộp'),
(17, 37, 7, 40, 38500, 'Hộp', 'Thịt bò hộp'),
(18, 13, 8, 10, 99000, 'Bộ', 'Bộ dao nhà bếp'),
(19, 33, 8, 15, 38500, 'Cái', 'Thớt nhựa'),
(20, 19, 9, 60, 49500, 'Cuộn', 'Giấy vệ sinh Pulppy'),
(21, 20, 9, 40, 33000, 'Hộp', 'Khăn giấy ướt'),
(22, 8, 10, 70, 11000, 'Túi', 'Rau muống'),
(23, 28, 10, 50, 13200, 'Túi', 'Cải xanh'),
(24, 48, 10, 30, 16500, 'Túi', 'Cà rốt'),
(25, 3, 11, 20, 38500, 'Chai', 'Nước mắm Nam Ngư'),
(26, 23, 11, 30, 16500, 'Hũ', 'Muối ớt Chinsu'),
(27, 7, 12, 10, 165000, 'Túi', 'Tôm sú đông lạnh'),
(28, 27, 12, 15, 132000, 'Túi', 'Mực ống đông lạnh'),
(29, 47, 12, 10, 143000, 'Túi', 'Mực lá đông lạnh'),
(30, 15, 13, 25, 60500, 'Tuýp', 'Kem dưỡng Nivea'),
(31, 35, 13, 40, 33000, 'Tuýp', 'Kem đánh răng Colgate'),
(32, 10, 14, 15, 88000, 'Hộp', 'Gà rán đông lạnh'),
(33, 30, 14, 10, 99000, 'Hộp', 'Pizza đông lạnh'),
(34, 16, 15, 50, 33000, 'Kg', 'Gạo ST25'),
(35, 36, 15, 40, 38000, 'Kg', 'Gạo tám thơm'),
(36, 21, 16, 90, 8800, 'Gói', 'Mì Omachi'),
(37, 41, 16, 20, 49500, 'Gói', 'Mì ống Barilla'),
(38, 24, 17, 30, 38500, 'Gói', 'Bò khô Jack'),
(39, 44, 17, 50, 16500, 'Gói', 'Khoai chiên Lay’s'),
(40, 5, 18, 40, 27500, 'Hộp', 'Sữa tươi Vinamilk'),
(41, 45, 18, 50, 33000, 'Hộp', 'Sữa chua uống Yakult'),
(42, 2, 19, 60, 13200, 'Chai', 'Nước ngọt Pepsi'),
(43, 22, 19, 50, 13200, 'Chai', 'Coca-Cola'),
(44, 42, 19, 70, 11000, 'Chai', 'Nước ép C2'),
(45, 9, 20, 20, 49500, 'Túi', 'Táo Mỹ'),
(46, 49, 20, 15, 55000, 'Túi', 'Táo Fuji'),
(47, 14, 21, 30, 71500, 'Chai', 'Sữa tắm Lifebuoy'),
(48, 34, 21, 20, 93500, 'Chai', 'Dầu gội Head & Shoulders'),
(49, 6, 22, 12, 66000, 'Khay', 'Thịt ba chỉ'),
(50, 26, 22, 18, 60500, 'Khay', 'Thịt heo xay'),
(51, 11, 23, 50, 16500, 'Gói', 'Kẹo socola KitKat'),
(52, 31, 23, 40, 27500, 'Gói', 'Kẹo dẻo Haribo'),
(53, 8, 24, 60, 11000, 'Túi', 'Rau muống'),
(54, 28, 24, 50, 13200, 'Túi', 'Cải xanh'),
(55, 17, 25, 40, 27500, 'Hộp', 'Cá ngừ đóng hộp'),
(56, 37, 25, 30, 38500, 'Hộp', 'Thịt bò hộp'),
(57, 19, 26, 50, 49500, 'Cuộn', 'Giấy vệ sinh Pulppy'),
(58, 39, 26, 60, 44000, 'Cuộn', 'Giấy vệ sinh Bless You'),
(59, 4, 27, 70, 11000, 'Gói', 'Snack Oishi'),
(60, 24, 27, 20, 38500, 'Gói', 'Bò khô Jack'),
(61, 13, 28, 12, 99000, 'Bộ', 'Bộ dao nhà bếp'),
(62, 33, 28, 18, 38500, 'Cái', 'Thớt nhựa'),
(63, 5, 29, 50, 27500, 'Hộp', 'Sữa tươi Vinamilk'),
(64, 25, 29, 40, 22000, 'Hộp', 'Sữa chua Vinamilk'),
(65, 2, 30, 50, 13200, 'Chai', 'Nước ngọt Pepsi'),
(66, 22, 30, 40, 13200, 'Chai', 'Coca-Cola'),
(67, 7, 31, 12, 165000, 'Túi', 'Tôm sú đông lạnh'),
(68, 27, 31, 10, 132000, 'Túi', 'Mực ống đông lạnh'),
(69, 15, 32, 30, 60500, 'Tuýp', 'Kem dưỡng Nivea'),
(70, 35, 32, 50, 33000, 'Tuýp', 'Kem đánh răng Colgate'),
(71, 10, 33, 20, 88000, 'Hộp', 'Gà rán đông lạnh'),
(72, 50, 33, 25, 66000, 'Hộp', 'Chả cá đông lạnh'),
(73, 16, 34, 60, 33000, 'Kg', 'Gạo ST25'),
(74, 36, 34, 50, 30800, 'Kg', 'Gạo tám thơm'),
(75, 21, 35, 100, 8800, 'Gói', 'Mì Omachi'),
(76, 41, 35, 25, 49500, 'Gói', 'Mì ống Barilla'),
(77, 3, 36, 25, 38500, 'Chai', 'Nước mắm Nam Ngư'),
(78, 23, 36, 35, 16500, 'Hũ', 'Muối ớt Chinsu'),
(79, 9, 37, 30, 49500, 'Túi', 'Táo Mỹ'),
(80, 29, 37, 40, 33000, 'Túi', 'Cam sành'),
(81, 14, 38, 25, 71500, 'Chai', 'Sữa tắm Lifebuoy'),
(82, 34, 38, 15, 93500, 'Chai', 'Dầu gội Head & Shoulders'),
(83, 6, 39, 15, 66000, 'Khay', 'Thịt ba chỉ'),
(84, 46, 39, 20, 77000, 'Khay', 'Cá basa tươi'),
(85, 11, 40, 60, 16500, 'Gói', 'Kẹo socola KitKat'),
(86, 31, 40, 50, 27500, 'Gói', 'Kẹo dẻo Haribo'),
(87, 8, 41, 80, 11000, 'Túi', 'Rau muống'),
(88, 48, 41, 40, 16500, 'Túi', 'Cà rốt'),
(89, 17, 42, 50, 27500, 'Hộp', 'Cá ngừ đóng hộp'),
(90, 37, 42, 40, 38500, 'Hộp', 'Thịt bò hộp'),
(91, 19, 43, 70, 49500, 'Cuộn', 'Giấy vệ sinh Pulppy'),
(92, 20, 43, 50, 33000, 'Hộp', 'Khăn giấy ướt'),
(93, 4, 44, 90, 11000, 'Gói', 'Snack Oishi'),
(94, 44, 44, 60, 16500, 'Gói', 'Khoai chiên Lay’s'),
(95, 5, 45, 60, 27500, 'Hộp', 'Sữa tươi Vinamilk'),
(96, 45, 45, 50, 33000, 'Hộp', 'Sữa chua uống Yakult'),
(97, 2, 46, 70, 13200, 'Chai', 'Nước ngọt Pepsi'),
(98, 42, 46, 80, 11000, 'Chai', 'Nước ép C2'),
(99, 7, 47, 15, 165000, 'Túi', 'Tôm sú đông lạnh'),
(100, 47, 47, 10, 143000, 'Túi', 'Mực lá đông lạnh'),
(101, 15, 48, 35, 60500, 'Tuýp', 'Kem dưỡng Nivea'),
(102, 35, 48, 60, 33000, 'Tuýp', 'Kem đánh răng Colgate'),
(103, 10, 49, 25, 88000, 'Hộp', 'Gà rán đông lạnh'),
(104, 30, 49, 15, 99000, 'Hộp', 'Pizza đông lạnh'),
(105, 16, 50, 70, 33000, 'Kg', 'Gạo ST25'),
(106, 36, 50, 60, 30800, 'Kg', 'Gạo tám thơm'),
(107, 8, 51, 1, 10000, 'Túi', 'Rau muống'),
(108, 13, 51, 76, 90000, 'Bộ', 'Bộ dao nhà bếp'),
(111, 14, 53, 1, 65000, 'Chai', 'Sữa tắm Lifebuoy'),
(112, 7, 54, 34, 150000, 'Túi', 'Tôm sú đông lạnh'),
(113, 15, 55, 100, 55000, 'Tuýp', 'Kem dưỡng Nivea'),
(116, 1, 57, 19, 5000, 'Gói', 'Mì gói Hảo Hảo'),
(117, 51, 58, 1, 222000, 'Gói', ' cay mệt '),
(118, 52, 59, 24, 2100, 'kg', 'éhột '),
(119, 4, 60, 1, 10000, 'Gói', 'Snack Oishi'),
(120, 57, 60, 13, 100, 'r', 'sua'),
(121, 42, 60, 21, 10000, 'Chai', 'Nước ép C2'),
(122, 57, 61, 27, 100, 'r', 'sua');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `offer`
--

CREATE TABLE `offer` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `endDate` date DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `value` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `offer`
--

INSERT INTO `offer` (`id`, `name`, `startDate`, `endDate`, `category`, `value`) VALUES
(14, 'Mừng 30/4', '2025-05-07', '2025-05-09', 'Giảm giá sản phẩm', 88),
(15, 'Black Friday', '2025-05-08', '2025-05-31', 'Giảm giá sản phẩm', 99),
(16, 'Kĩ niệm 30 năm thành lập', '2025-05-10', '2025-06-06', 'Giảm giá hóa đơn', 32),
(17, 'Mừng khai trương', '2025-05-10', '2025-05-10', 'Giảm giá sản phẩm', 45);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `id` int NOT NULL,
  `idProductType` int DEFAULT NULL,
  `idProductDetail` int DEFAULT NULL,
  `idOfferProduct` int DEFAULT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `price` double DEFAULT NULL,
  `unit` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `quantity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`id`, `idProductType`, `idProductDetail`, `idOfferProduct`, `name`, `price`, `unit`, `quantity`) VALUES
(1, 1, 1, 14, 'Mì gói Hảo Hảo', 5000, 'Gói', 68),
(2, 2, 2, 15, 'Nước ngọt Pepsi', 12000, 'Chai', 50),
(3, 3, 3, 15, 'Nước mắm Nam Ngư', 35000, 'Chai', 29),
(4, 4, 4, 15, 'Snack Oishi', 10000, 'Gói', 81),
(5, 5, 5, 15, 'Sữa tươi Vinamilk', 25000, 'Hộp', 40),
(6, 6, 6, 15, 'Thịt ba chỉ', 60000, 'Khay', 19),
(7, 7, 7, 15, 'Tôm sú đông lạnh', 150000, 'Túi', 48),
(8, 8, 8, 15, 'Rau muống', 10000, 'Túi', 49),
(9, 9, 9, 15, 'Táo Mỹ', 45000, 'Túi', 24),
(10, 10, 10, 15, 'Gà rán đông lạnh', 80000, 'Hộp', 7),
(11, 11, 11, NULL, 'Kẹo socola KitKat', 15000, 'Gói', 66),
(12, 12, 12, NULL, 'Bàn chải đánh răng', 20000, 'Cái', 42),
(13, 13, 13, NULL, 'Bộ dao nhà bếp', 90000, 'Bộ', 84),
(14, 14, 14, NULL, 'Sữa tắm Lifebuoy', 65000, 'Chai', 29),
(15, 15, 15, NULL, 'Kem dưỡng Nivea', 55000, 'Tuýp', 100),
(16, 16, 16, NULL, 'Gạo ST25', 30000, 'Kg', 35),
(17, 17, 17, NULL, 'Cá ngừ đóng hộp', 25000, 'Hộp', 58),
(18, 18, 18, NULL, 'Nước giặt OMO', 120000, 'Can', 7),
(19, 19, 19, NULL, 'Giấy vệ sinh Pulppy', 45000, 'Cuộn', 47),
(20, 20, 20, NULL, 'Khăn giấy ướt', 30000, 'Hộp', 31),
(21, 1, 21, NULL, 'Mì Omachi', 8000, 'Gói', 87),
(22, 2, 22, NULL, 'Coca-Cola', 12000, 'Chai', 44),
(23, 3, 23, NULL, 'Muối ớt Chinsu', 15000, 'Hũ', 39),
(24, 4, 24, NULL, 'Bò khô Jack', 35000, 'Gói', 30),
(25, 5, 25, NULL, 'Sữa chua Vinamilk', 20000, 'Hộp', 50),
(26, 6, 26, NULL, 'Thịt heo xay', 55000, 'Khay', 25),
(27, 7, 27, NULL, 'Mực ống đông lạnh', 120000, 'Túi', 10),
(28, 8, 28, NULL, 'Cải xanh', 12000, 'Túi', 55),
(29, 9, 29, NULL, 'Cam sành', 30000, 'Túi', 30),
(30, 10, 30, NULL, 'Pizza đông lạnh', 90000, 'Hộp', 15),
(31, 11, 31, NULL, 'Kẹo dẻo Haribo', 25000, 'Gói', 60),
(32, 12, 32, NULL, 'Bàn chải tre', 15000, 'Cái', 40),
(33, 13, 33, NULL, 'Thớt nhựa', 35000, 'Cái', 20),
(34, 14, 34, NULL, 'Dầu gội Head & Shoulders', 85000, 'Chai', 25),
(35, 15, 35, NULL, 'Kem đánh răng Colgate', 30000, 'Tuýp', 50),
(36, 16, 36, 17, 'Gạo tám thơm', 28000, 'Kg', 35),
(37, 17, 37, 17, 'Thịt bò hộp', 35000, 'Hộp', 45),
(38, 18, 38, 17, 'Nước xả Downy', 100000, 'Can', 15),
(39, 19, 39, 17, 'Giấy vệ sinh Bless You', 40000, 'Cuộn', 60),
(40, 20, 40, 17, 'Khăn giấy lụa', 25000, 'Hộp', 40),
(41, 1, 41, 17, 'Mì ống Barilla', 45000, 'Gói', 20),
(42, 2, 42, NULL, 'Nước ép C2', 10000, 'Chai', 91),
(43, 3, 43, NULL, 'Tiêu đen Visaco', 20000, 'Hũ', 35),
(44, 4, 44, NULL, 'Khoai chiên Lay’s', 15000, 'Gói', 80),
(45, 5, 45, NULL, 'Sữa chua uống Yakult', 30000, 'Hộp', 50),
(46, 6, 46, NULL, 'Cá basa tươi', 70000, 'Khay', 15),
(47, 7, 47, NULL, 'Mực lá đông lạnh', 130000, 'Túi', 10),
(48, 8, 48, NULL, 'Cà rốt', 15000, 'Túi', 40),
(49, 9, 49, NULL, 'Táo Fuji', 50000, 'Túi', 20),
(50, 10, 50, NULL, 'Chả cá đông lạnh', 60000, 'Hộp', 25),
(51, 1, 51, NULL, ' cay mệt ', 222000, 'Gói', 1),
(52, 3, 52, NULL, 'éhột ', 2100, 'kg', 23),
(57, 1, 57, 15, 'sua', 100, 'r', 40);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `productdetail`
--

CREATE TABLE `productdetail` (
  `id` int NOT NULL,
  `detailInfo` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `productdetail`
--

INSERT INTO `productdetail` (`id`, `detailInfo`) VALUES
(1, 'Gói 500g, hạn sử dụng 12 tháng'),
(2, 'Chai 1L, không đường'),
(3, 'Hũ 200g, cay nhẹ'),
(4, 'Gói 100g, vị phô mai'),
(5, 'Hộp 1L, có đường'),
(6, 'Khay 300g, tươi mới'),
(7, 'Túi 500g, đông lạnh'),
(8, 'Túi 1kg, sạch'),
(9, 'Túi 500g, nhập khẩu'),
(10, 'Hộp 400g, đông lạnh'),
(11, 'Gói 200g, socola'),
(12, 'Cái, lông mềm'),
(13, 'Bộ 3 cái, thép không gỉ'),
(14, 'Tuýp 150ml, mùi hoa'),
(15, 'Chai 250ml, dưỡng ẩm'),
(16, 'Gói 1kg, nguyên hạt'),
(17, 'Hộp 300g, cá ngừ'),
(18, 'Can 2kg, mùi lavender'),
(19, 'Cuộn 10 tờ, 3 lớp'),
(20, 'Hộp 200 tờ, mềm mại'),
(21, 'Gói 400g, hạn sử dụng 6 tháng'),
(22, 'Chai 500ml, có ga'),
(23, 'Hũ 100g, mặn'),
(24, 'Gói 50g, vị bò'),
(25, 'Hộp 500ml, ít béo'),
(26, 'Khay 200g, thịt heo'),
(27, 'Túi 300g, tôm sú'),
(28, 'Túi 800g, cải xanh'),
(29, 'Túi 1kg, táo'),
(30, 'Hộp 500g, gà chiên'),
(31, 'Gói 150g, kẹo dẻo'),
(32, 'Cái, bàn chải cứng'),
(33, 'Cái, thớt nhựa'),
(34, 'Chai 500ml, dầu gội'),
(35, 'Tuýp 100g, kem đánh răng'),
(36, 'Gói 2kg, gạo thơm'),
(37, 'Hộp 200g, thịt bò'),
(38, 'Can 1.5kg, nước xả'),
(39, 'Cuộn 8 tờ, 2 lớp'),
(40, 'Hộp 150 tờ, ướt'),
(41, 'Gói 300g, mì ống'),
(42, 'Chai 330ml, nước trái cây'),
(43, 'Hũ 50g, tiêu đen'),
(44, 'Gói 80g, khoai chiên'),
(45, 'Hộp 800ml, sữa chua'),
(46, 'Khay 400g, cá tươi'),
(47, 'Túi 200g, mực'),
(48, 'Túi 600g, cà rốt'),
(49, 'Túi 700g, cam'),
(50, 'Hộp 600g, pizza đông lạnh'),
(51, ''),
(52, ''),
(57, '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `producttype`
--

CREATE TABLE `producttype` (
  `id` int NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `producttype`
--

INSERT INTO `producttype` (`id`, `name`) VALUES
(1, 'Thực phẩm đóng gói'),
(2, 'Đồ uống'),
(3, 'Gia vị'),
(4, 'Đồ ăn vặt'),
(5, 'Sữa và chế phẩm'),
(6, 'Thịt tươi'),
(7, 'Hải sản'),
(8, 'Rau củ'),
(9, 'Trái cây'),
(10, 'Đồ đông lạnh'),
(11, 'Bánh kẹo'),
(12, 'Dụng cụ vệ sinh'),
(13, 'Đồ dùng nhà bếp'),
(14, 'Hóa mỹ phẩm'),
(15, 'Chăm sóc cá nhân'),
(16, 'Thực phẩm khô'),
(17, 'Đồ hộp'),
(18, 'Nước giặt'),
(19, 'Giấy vệ sinh'),
(20, 'Khăn giấy');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `provider`
--

CREATE TABLE `provider` (
  `id` int NOT NULL,
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `provider`
--

INSERT INTO `provider` (`id`, `name`, `phone`, `address`, `email`) VALUES
(1, 'Vinamilk', '02854155555', '10 Tân Trào, P. Tân Phú, Q. 7, TP. HCM', 'contact@vinamilk.com.vn'),
(2, 'Nestlé Việt Nam', '02873008345', '5 Đường 17A, P. Bình Trị Đông B, Q. Bình Tân, TP. HCM', 'support@vn.nestle.com'),
(3, 'TH True Milk', '02462691111', '166 Nguyễn Thái Học, Ba Đình, Hà Nội', 'info@thmilk.vn'),
(4, 'Coca-Cola Việt Nam', '02838920017', '485 Xa Lộ Hà Nội, P. Linh Trung, Thủ Đức, TP. HCM', 'contact@coca-cola.com.vn'),
(5, 'PepsiCo Việt Nam', '02838233545', '88 Hàm Nghi, Q.1, TP. HCM', 'support@pepsico.vn'),
(6, 'Masan Consumer', '02837578899', '12 Nguyễn Bỉnh Khiêm, P. Đa Kao, Q.1, TP. HCM', 'info@masanconsumer.com'),
(7, 'Unilever Việt Nam', '02838238338', '156 Nguyễn Lương Bằng, Q.7, TP. HCM', 'customer@unilever.com'),
(8, 'P&G Việt Nam', '02838219999', '128 Trần Hưng Đạo, Q.1, TP. HCM', 'contact@pg.com'),
(9, 'Vissan', '02838555310', '420 Nơ Trang Long, P.13, Bình Thạnh, TP. HCM', 'support@vissan.com.vn'),
(10, 'CP Việt Nam', '02513836300', 'KCN Biên Hòa 2, Đồng Nai', 'info@cpvietnam.com'),
(11, 'Ba Huân', '02837593939', '9/1 Lê Thị Hồng, P.17, Gò Vấp, TP. HCM', 'contact@bahuan.vn'),
(12, 'San Hà', '02838580000', '229 Nguyễn Văn Cừ, Q.5, TP. HCM', 'info@sanha.vn'),
(13, 'Acecook Việt Nam', '02838152822', 'KCN Tân Bình, P. Tây Thạnh, Q. Tân Phú, TP. HCM', 'support@acecookvietnam.vn'),
(14, 'Miwon Việt Nam', '02838372626', 'KCN Biên Hòa 1, Đồng Nai', 'info@miwon.vn'),
(15, 'Ajinomoto Việt Nam', '02838151515', 'KCN Long Thành, Đồng Nai', 'contact@ajinomoto.com.vn'),
(16, 'Kinh Đô', '02837401565', '26 VSIP, Thuận An, Bình Dương', 'info@kinhdo.vn'),
(17, 'Orion Việt Nam', '02838984111', 'KCN Mỹ Phước, Bến Cát, Bình Dương', 'support@orion.vn'),
(18, 'Perfetti Van Melle', '02838993388', 'Số 10, Đường số 3, KCN Sóng Thần, Bình Dương', 'info@perfettivanmelle.vn'),
(19, 'Hòa Phát', '02462848866', '39 Nguyễn Đình Chiểu, Hai Bà Trưng, Hà Nội', 'contact@hoaphat.com.vn'),
(20, 'Sanaky Việt Nam', '02838569999', '48/9B Xa Lộ Hà Nội, Q.9, TP. HCM', 'support@sanaky.vn');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `staff`
--

CREATE TABLE `staff` (
  `id` int NOT NULL,
  `phone` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `firstName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lastName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `salary` double NOT NULL,
  `role` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `state` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'Đang hoạt động',
  `gender` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Đang đổ dữ liệu cho bảng `staff`
--

INSERT INTO `staff` (`id`, `phone`, `password`, `firstName`, `lastName`, `address`, `salary`, `role`, `state`, `gender`) VALUES
(1, '0905123456', 'matkhau123', 'Hùng', 'Nguyễn', '123 Lê Lợi, TP. Huế', 7000000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nam'),
(2, '0912345678', 'abc123', 'Lan', 'Trần Thị', '45 Nguyễn Huệ, Hà Nội', 8000000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(3, '0935678901', 'pass456', 'Minh', 'Phạm Văn', '78 Trần Phú, TP. HCM', 6500000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(4, '0987654321', 'xyz789', 'Hương', 'Lê Thị', '12 Hùng Vương, Đà Nẵng', 7500000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(5, '0941234567', '123456', 'Tuấn', 'Đỗ Anh', '56 Phạm Ngũ Lão, Nha Trang', 9000000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(6, '0978765432', 'password1', 'Mai', 'Ngô Thị', '89 Nguyễn Trãi, Cần Thơ', 7200000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(7, '0923456789', 'uygsdff', 'Dũng', 'Hoàng', '34 Lê Đại Hành, Vinh', 6800000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(8, '0967890123', 'mk12345', 'Thảo', 'Vũ Thị', '67 Trần Hưng Đạo, Hải Phòng', 7800000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(9, '0918765432', 'pass789', 'Kiên', 'Trương', '23 Nguyễn Văn Cừ, Quy Nhơn', 8500000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(10, '0932145678', '123abc', 'Linh', 'Bùi Thị', '90 Phạm Văn Đồng, Đà Lạt', 7000000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(11, '0909876543', 'xyz123', 'Nam', 'Đặng Văn', '15 Lê Thánh Tôn, Vũng Tàu', 6700000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(12, '0945678901', 'pass321', 'Ngọc', 'Phan Thị', '48 Hùng Vương, Buôn Ma Thuột', 7600000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(13, '0971234567', 'abc456', 'Phong', 'Lương', '72 Trần Phú, Pleiku', 8200000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(14, '0926789012', 'mk45678', 'Yến', 'Hà Thị', '19 Nguyễn Đình Chiểu, Hà Tĩnh', 6900000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(15, '0965432109', '123xyz', 'Trung', 'Nguyễn Văn', '33 Lê Lai, Thanh Hóa', 6600000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(16, '0913456789', 'pass654', 'Hạnh', 'Trần Thị', '57 Nguyễn Thị Minh Khai, TP. HCM', 7700000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(17, '0989012345', 'abc789', 'Bình', 'Phạm', '81 Lê Duẩn, Hà Nội', 8800000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(18, '0934567890', 'xyz456', 'Thủy', 'Lê Thị', '24 Trần Quốc Toản, Đà Nẵng', 7100000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(19, '0906789012', 'pass987', 'Hải', 'Hoàng Văn', '68 Nguyễn Huệ, Nha Trang', 6400000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(20, '0943210987', '123def', 'Trang', 'Ngô Thị', '11 Phạm Hồng Thái, Cần Thơ', 7900000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(21, '0978901234', 'abc321', 'Việt', 'Đỗ', '35 Lê Văn Sỹ, TP. HCM', 8300000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(22, '0921098765', 'xyz654', 'Hoa', 'Vũ Thị', '59 Trần Đại Nghĩa, Hà Nội', 7300000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(23, '0964321098', 'pass123', 'Long', 'Trương Văn', '83 Hùng Vương, Hải Phòng', 6700000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(24, '0917890123', 'abc654', 'Thu', 'Bùi Thị', '27 Nguyễn Văn Linh, Đà Nẵng', 7800000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(25, '0980123456', 'xyz987', 'Quang', 'Phan', '51 Lê Lợi, Vinh', 8600000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(26, '0938901234', '123ghi', 'Tâm', 'Hà Thị', '75 Trần Phú, Quy Nhơn', 7000000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(27, '0902345678', 'pass456', 'Khang', 'Nguyễn', '99 Nguyễn Trãi, TP. HCM', 6800000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(28, '0946789012', 'abc987', 'Oanh', 'Trần Thị', '14 Lê Đại Hành, Hà Nội', 7600000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(29, '0973456789', 'xyz321', 'Đạt', 'Phạm Văn', '38 Hùng Vương, Đà Lạt', 8400000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(30, '0929012345', 'pass789', 'Hiền', 'Lê Thị', '62 Nguyễn Huệ, Nha Trang', 7200000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(31, '0961234567', '123jkl', 'Sơn', 'Hoàng', '86 Trần Hưng Đạo, Cần Thơ', 6500000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(32, '0915678901', 'abc123', 'Phương', 'Ngô Thị', '20 Phạm Ngũ Lão, Hải Phòng', 7700000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(33, '0984321098', 'xyz456', 'Thắng', 'Đỗ Văn', '44 Lê Lai, TP. HCM', 8700000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(34, '0931234567', 'pass321', 'Nhi', 'Vũ Thị', '68 Nguyễn Đình Chiểu, Hà Nội', 7100000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(35, '0908901234', 'abc789', 'Hòa', 'Trương', '92 Trần Quốc Toản, Đà Nẵng', 6900000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(36, '0944567890', 'xyz987', 'Vy', 'Bùi Thị', '16 Lê Thánh Tôn, Vũng Tàu', 7800000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(37, '0970123456', '123mno', 'Khoa', 'Phan Văn', '40 Nguyễn Văn Cừ, Quy Nhơn', 8500000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(38, '0926789012', 'pass654', 'Tuyết', 'Hà Thị', '64 Phạm Văn Đồng, Đà Lạt', 7300000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(39, '0963456789', 'abc321', 'Cường', 'Nguyễn', '88 Lê Duẩn, TP. HCM', 6700000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(40, '0919012345', 'xyz654', 'Đào', 'Trần Thị', '22 Nguyễn Thị Minh Khai, Hà Nội', 7600000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(41, '0985678901', 'pass987', 'Tín', 'Phạm', '46 Hùng Vương, Nha Trang', 8800000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(42, '0932345678', '123pqr', 'Nhung', 'Lê Thị', '70 Trần Phú, Cần Thơ', 7000000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(43, '0901234567', 'abc456', 'Hiếu', 'Hoàng Văn', '94 Nguyễn Huệ, Hải Phòng', 6800000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(44, '0947890123', 'xyz789', 'Bích', 'Ngô Thị', '18 Lê Lợi, Vinh', 7700000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(45, '0974567890', 'pass123', 'Duy', 'Đỗ', '42 Trần Hưng Đạo, TP. HCM', 8600000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(46, '0920123456', 'abc987', 'Thúy', 'Vũ Thị', '66 Nguyễn Trãi, Hà Nội', 7200000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(47, '0968901234', 'xyz321', 'Lộc', 'Trương Văn', '90 Phạm Ngũ Lão, Đà Nẵng', 6500000, 'Nhân viên kho', 'Đang hoạt động', 'Nam'),
(48, '0916789012', 'pass456', 'Hồng', 'Bùi Thị', '14 Nguyễn Văn Linh, Nha Trang', 7800000, 'Thu ngân', 'Đang hoạt động', 'Nữ'),
(49, '0983456789', '123stu', 'Vũ', 'Phan', '38 Lê Đại Hành, Cần Thơ', 8700000, 'Quản lý', 'Đang hoạt động', 'Nam'),
(50, '0939012345', 'abc654', 'Ánh', 'Hà Thị', '62 Trần Quốc Toản, Hải Phòng', 7100000, 'Nhân viên bán hàng', 'Đang hoạt động', 'Nữ'),
(51, '0397969307', 'admin', 'Cường', 'Lê Mạnh', 'Huỳnh Thị Na, Hóc Môn, TP. HCM', 20000000, 'Quản trị viên', 'Đang hoạt động', 'Nam');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idCustomer` (`idCustomer`),
  ADD KEY `idOfferBill` (`idOfferBill`),
  ADD KEY `idOfferBill_2` (`idOfferBill`),
  ADD KEY `idStaff` (`idStaff`);

--
-- Chỉ mục cho bảng `billinfo`
--
ALTER TABLE `billinfo`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idBill` (`idBill`),
  ADD KEY `idProduct` (`idProduct`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `importorder`
--
ALTER TABLE `importorder`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idStaff` (`idStaff`),
  ADD KEY `idProvider` (`idProvider`);

--
-- Chỉ mục cho bảng `importorderdetail`
--
ALTER TABLE `importorderdetail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idProduct` (`idProduct`),
  ADD KEY `idImportOrder` (`idImportOrder`);

--
-- Chỉ mục cho bảng `offer`
--
ALTER TABLE `offer`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idProductDetail` (`idProductDetail`),
  ADD KEY `idProductType` (`idProductType`),
  ADD KEY `fk_product_offer` (`idOfferProduct`);

--
-- Chỉ mục cho bảng `productdetail`
--
ALTER TABLE `productdetail`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `producttype`
--
ALTER TABLE `producttype`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `provider`
--
ALTER TABLE `provider`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `bill`
--
ALTER TABLE `bill`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT cho bảng `billinfo`
--
ALTER TABLE `billinfo`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT cho bảng `importorder`
--
ALTER TABLE `importorder`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT cho bảng `importorderdetail`
--
ALTER TABLE `importorderdetail`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=124;

--
-- AUTO_INCREMENT cho bảng `offer`
--
ALTER TABLE `offer`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT cho bảng `product`
--
ALTER TABLE `product`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT cho bảng `productdetail`
--
ALTER TABLE `productdetail`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=58;

--
-- AUTO_INCREMENT cho bảng `producttype`
--
ALTER TABLE `producttype`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `provider`
--
ALTER TABLE `provider`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT cho bảng `staff`
--
ALTER TABLE `staff`
  MODIFY `id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=53;

--
-- Ràng buộc đối với các bảng kết xuất
--

--
-- Ràng buộc cho bảng `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`idCustomer`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `fk_bill_staff` FOREIGN KEY (`idStaff`) REFERENCES `staff` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_offer_bill` FOREIGN KEY (`idOfferBill`) REFERENCES `offer` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Ràng buộc cho bảng `billinfo`
--
ALTER TABLE `billinfo`
  ADD CONSTRAINT `billinfo_ibfk_1` FOREIGN KEY (`idBill`) REFERENCES `bill` (`id`),
  ADD CONSTRAINT `billinfo_ibfk_2` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`);

--
-- Ràng buộc cho bảng `importorder`
--
ALTER TABLE `importorder`
  ADD CONSTRAINT `importorder_ibfk_1` FOREIGN KEY (`idStaff`) REFERENCES `staff` (`id`),
  ADD CONSTRAINT `importorder_ibfk_2` FOREIGN KEY (`idProvider`) REFERENCES `provider` (`id`);

--
-- Ràng buộc cho bảng `importorderdetail`
--
ALTER TABLE `importorderdetail`
  ADD CONSTRAINT `importorderdetail_ibfk_1` FOREIGN KEY (`idProduct`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `importorderdetail_ibfk_2` FOREIGN KEY (`idImportOrder`) REFERENCES `importorder` (`id`);

--
-- Ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_product_offer` FOREIGN KEY (`idOfferProduct`) REFERENCES `offer` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `product_ibfk_2` FOREIGN KEY (`idProductDetail`) REFERENCES `productdetail` (`id`),
  ADD CONSTRAINT `product_ibfk_3` FOREIGN KEY (`idProductType`) REFERENCES `producttype` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
