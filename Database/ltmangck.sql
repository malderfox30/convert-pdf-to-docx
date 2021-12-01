-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 30, 2021 lúc 01:49 PM
-- Phiên bản máy phục vụ: 10.4.14-MariaDB
-- Phiên bản PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ltmangck`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `username` text NOT NULL,
  `password` text NOT NULL,
  `fullName` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`id`, `username`, `password`, `fullName`) VALUES
(1, 'phatnguyen', '12345678', 'Nguyen Tan Phat'),
(2, 'minhnguyen', '12345678', 'Nguyen Chau Quang Minh'),
(3, 'pmtuan', '12345678', 'Pham Minh Tuan');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `history`
--

CREATE TABLE `history` (
  `id` varchar(50) NOT NULL,
  `userId` int(11) NOT NULL,
  `fileName` text NOT NULL,
  `status` text NOT NULL,
  `time` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `history`
--

INSERT INTO `history` (`id`, `userId`, `fileName`, `status`, `time`) VALUES
('13d2d9e3-ec95-4b27-985b-224e7dd9c7af', 1, 'dummy.pdf', 'success', '2021-11-30 06:03:31'),
('190d3591-8309-4c01-aeb0-c9d4331f8b71', 1, '08-Design-principles-v1.5.pdf', 'success', '2021-11-30 18:12:38'),
('26b82077-a27e-4b24-9fc8-f637587bd673', 2, '08-Design-principles-v1.5.pdf', 'success', '2021-11-30 18:22:56'),
('3de25574-6844-4515-b088-632a04e34bbb', 2, 'a.pdf', 'success', '2021-11-30 18:22:33'),
('4fffd3e0-e8da-4a50-9861-28e499e24254', 2, 'dummy.pdf', 'success', '2021-11-30 18:23:05'),
('5da8899d-7d72-433f-ae32-d849b9b1939e', 1, '08-Design-principles-v1.5.pdf', 'success', '2021-11-30 18:16:44'),
('7d48325d-93c8-468d-a6ad-a25fb89824a2', 2, 'dummy.pdf', 'success', '2021-11-30 18:22:38'),
('831a111d-a340-402c-a24e-73338c829480', 1, '08-Design-principles-v1.5.pdf', 'success', '2021-11-30 18:10:03'),
('8829fa5d-bc0c-4d7d-9369-6301e9b55fa1', 2, '102180036_NguyenTanPhat.pdf', 'success', '2021-11-30 18:27:21'),
('9222e65d-1c0f-463f-9681-8b2d90d4389f', 1, 'a.pdf', 'success', '2021-11-30 18:08:50'),
('d8f48be1-96b1-4b8f-9193-24c074873f30', 1, 'HÄ? sá»£i.pdf', 'success', '2021-11-30 18:40:00');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
