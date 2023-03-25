-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Czas generowania: 04 Sty 2023, 13:01
-- Wersja serwera: 10.4.24-MariaDB
-- Wersja PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Baza danych: `app`
--

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `appointments`
--

CREATE TABLE `appointments` (
  `ID` int(11) NOT NULL,
  `date` datetime DEFAULT NULL,
  `doctor` int(11) DEFAULT NULL,
  `patienlocalizationt` int(11) DEFAULT NULL,
  `localization` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `appointments`
--

INSERT INTO `appointments` (`ID`, `date`, `doctor`, `patient`, `localization`) VALUES
(1, '2023-02-01 08:00:00', 1, NULL, 5),
(2, '2023-02-01 09:00:00', 1, NULL, 5),
(3, '2023-02-01 10:00:00', 1, NULL, 5),
(4, '2023-02-01 11:00:00', 1, NULL, 5),
(5, '2023-02-01 12:00:00', 1, NULL, 5),
(6, '2023-02-01 13:00:00', 1, NULL, 5),
(7, '2023-02-01 14:00:00', 1, NULL, 5),
(8, '2023-02-01 15:00:00', 1, NULL, 5),
(9, '2023-02-01 08:00:00', 2, NULL, 4),
(10, '2023-02-01 09:00:00', 2, NULL, 4),
(11, '2023-02-01 10:00:00', 2, NULL, 4),
(12, '2023-02-01 11:00:00', 2, NULL, 4),
(13, '2023-02-01 12:00:00', 2, NULL, 4),
(14, '2023-02-01 13:00:00', 2, NULL, 4),
(15, '2023-02-01 14:00:00', 2, NULL, 4),
(16, '2023-02-01 15:00:00', 2, NULL, 4),
(17, '2023-02-01 08:00:00', 3, NULL, 7),
(18, '2023-02-01 09:00:00', 3, NULL, 7),
(19, '2023-02-01 10:00:00', 3, NULL, 7),
(20, '2023-02-01 11:00:00', 3, NULL, 7),
(21, '2023-02-01 12:00:00', 3, NULL, 7),
(22, '2023-02-01 13:00:00', 3, NULL, 7),
(23, '2023-02-01 14:00:00', 3, NULL, 7),
(24, '2023-02-01 15:00:00', 3, NULL, 7),
(25, '2023-02-01 08:00:00', 4, NULL, 4),
(26, '2023-02-01 09:00:00', 4, NULL, 4),
(27, '2023-02-01 10:00:00', 4, NULL, 4),
(28, '2023-02-01 11:00:00', 4, NULL, 4),
(29, '2023-02-01 12:00:00', 4, NULL, 4),
(30, '2023-02-01 13:00:00', 4, NULL, 4),
(31, '2023-02-01 14:00:00', 4, NULL, 4),
(32, '2023-02-01 15:00:00', 4, NULL, 4),
(33, '2023-02-01 08:00:00', 5, NULL, 1),
(34, '2023-02-01 09:00:00', 5, NULL, 1),
(35, '2023-02-01 10:00:00', 5, NULL, 1),
(36, '2023-02-01 11:00:00', 5, NULL, 1),
(37, '2023-02-01 12:00:00', 5, NULL, 1),
(38, '2023-02-01 13:00:00', 5, NULL, 1),
(39, '2023-02-01 14:00:00', 5, NULL, 1),
(40, '2023-02-01 15:00:00', 5, NULL, 1),
(41, '2023-02-01 08:00:00', 6, NULL, 5),
(42, '2023-02-01 09:00:00', 6, NULL, 5),
(43, '2023-02-01 10:00:00', 6, NULL, 5),
(44, '2023-02-01 11:00:00', 6, NULL, 5),
(45, '2023-02-01 12:00:00', 6, NULL, 5),
(46, '2023-02-01 13:00:00', 6, NULL, 5),
(47, '2023-02-01 14:00:00', 6, NULL, 5),
(48, '2023-02-01 15:00:00', 6, NULL, 5),
(49, '2023-02-01 08:00:00', 7, NULL, 3),
(50, '2023-02-01 09:00:00', 7, NULL, 3),
(51, '2023-02-01 10:00:00', 7, NULL, 3),
(52, '2023-02-01 11:00:00', 7, NULL, 3),
(53, '2023-02-01 12:00:00', 7, NULL, 3),
(54, '2023-02-01 13:00:00', 7, NULL, 3),
(55, '2023-02-01 14:00:00', 7, NULL, 3),
(56, '2023-02-01 15:00:00', 7, NULL, 3),
(57, '2023-02-01 08:00:00', 8, NULL, 1),
(58, '2023-02-01 09:00:00', 8, NULL, 1),
(59, '2023-02-01 10:00:00', 8, NULL, 1),
(60, '2023-02-01 11:00:00', 8, NULL, 1),
(61, '2023-02-01 12:00:00', 8, NULL, 1),
(62, '2023-02-01 13:00:00', 8, NULL, 1),
(63, '2023-02-01 14:00:00', 8, NULL, 1),
(64, '2023-02-01 15:00:00', 8, NULL, 1),
(65, '2023-02-01 08:00:00', 9, NULL, 4),
(66, '2023-02-01 09:00:00', 9, NULL, 4),
(67, '2023-02-01 10:00:00', 9, NULL, 4),
(68, '2023-02-01 11:00:00', 9, NULL, 4),
(69, '2023-02-01 12:00:00', 9, NULL, 4),
(70, '2023-02-01 13:00:00', 9, NULL, 4),
(71, '2023-02-01 14:00:00', 9, NULL, 4),
(72, '2023-02-01 15:00:00', 9, NULL, 4),
(73, '2023-02-01 08:00:00', 10, NULL, 3),
(74, '2023-02-01 09:00:00', 10, NULL, 3),
(75, '2023-02-01 10:00:00', 10, NULL, 3),
(76, '2023-02-01 11:00:00', 10, NULL, 3),
(77, '2023-02-01 12:00:00', 10, NULL, 3),
(78, '2023-02-01 13:00:00', 10, NULL, 3),
(79, '2023-02-01 14:00:00', 10, NULL, 3),
(80, '2023-02-01 15:00:00', 10, NULL, 3),
(81, '2023-02-01 08:00:00', 11, NULL, 7),
(82, '2023-02-01 09:00:00', 11, NULL, 7),
(83, '2023-02-01 10:00:00', 11, NULL, 7),
(84, '2023-02-01 11:00:00', 11, NULL, 7),
(85, '2023-02-01 12:00:00', 11, NULL, 7),
(86, '2023-02-01 13:00:00', 11, NULL, 7),
(87, '2023-02-01 14:00:00', 11, NULL, 7),
(88, '2023-02-01 15:00:00', 11, NULL, 7),
(89, '2023-02-01 08:00:00', 12, NULL, 5),
(90, '2023-02-01 09:00:00', 12, NULL, 5),
(91, '2023-02-01 10:00:00', 12, NULL, 5),
(92, '2023-02-01 11:00:00', 12, NULL, 5),
(93, '2023-02-01 12:00:00', 12, NULL, 5),
(94, '2023-02-01 13:00:00', 12, NULL, 5),
(95, '2023-02-01 14:00:00', 12, NULL, 5),
(96, '2023-02-01 15:00:00', 12, NULL, 5),
(97, '2023-02-01 08:00:00', 13, NULL, 6),
(98, '2023-02-01 09:00:00', 13, NULL, 6),
(99, '2023-02-01 10:00:00', 13, NULL, 6),
(100, '2023-02-01 11:00:00', 13, NULL, 6),
(101, '2023-02-01 12:00:00', 13, NULL, 6),
(102, '2023-02-01 13:00:00', 13, NULL, 6),
(103, '2023-02-01 14:00:00', 13, NULL, 6),
(104, '2023-02-01 15:00:00', 13, NULL, 6),
(105, '2023-02-01 08:00:00', 14, NULL, 2),
(106, '2023-02-01 09:00:00', 14, NULL, 2),
(107, '2023-02-01 10:00:00', 14, NULL, 2),
(108, '2023-02-01 11:00:00', 14, NULL, 2),
(109, '2023-02-01 12:00:00', 14, NULL, 2),
(110, '2023-02-01 13:00:00', 14, NULL, 2),
(111, '2023-02-01 14:00:00', 14, NULL, 2),
(112, '2023-02-01 15:00:00', 14, NULL, 2),
(113, '2023-02-01 08:00:00', 15, NULL, 4),
(114, '2023-02-01 09:00:00', 15, NULL, 4),
(115, '2023-02-01 10:00:00', 15, NULL, 4),
(116, '2023-02-01 11:00:00', 15, NULL, 4),
(117, '2023-02-01 12:00:00', 15, NULL, 4),
(118, '2023-02-01 13:00:00', 15, NULL, 4),
(119, '2023-02-01 14:00:00', 15, NULL, 4),
(120, '2023-02-01 15:00:00', 15, NULL, 4),
(121, '2023-02-01 08:00:00', 16, NULL, 3),
(122, '2023-02-01 09:00:00', 16, NULL, 3),
(123, '2023-02-01 10:00:00', 16, NULL, 3),
(124, '2023-02-01 11:00:00', 16, NULL, 3),
(125, '2023-02-01 12:00:00', 16, NULL, 3),
(126, '2023-02-01 13:00:00', 16, NULL, 3),
(127, '2023-02-01 14:00:00', 16, NULL, 3),
(128, '2023-02-01 15:00:00', 16, NULL, 3),
(129, '2023-02-01 08:00:00', 17, NULL, 5),
(130, '2023-02-01 09:00:00', 17, NULL, 5),
(131, '2023-02-01 10:00:00', 17, NULL, 5),
(132, '2023-02-01 11:00:00', 17, NULL, 5),
(133, '2023-02-01 12:00:00', 17, NULL, 5),
(134, '2023-02-01 13:00:00', 17, NULL, 5),
(135, '2023-02-01 14:00:00', 17, NULL, 5),
(136, '2023-02-01 15:00:00', 17, NULL, 5),
(137, '2023-02-01 08:00:00', 18, NULL, 1),
(138, '2023-02-01 09:00:00', 18, NULL, 1),
(139, '2023-02-01 10:00:00', 18, NULL, 1),
(140, '2023-02-01 11:00:00', 18, NULL, 1),
(141, '2023-02-01 12:00:00', 18, NULL, 1),
(142, '2023-02-01 13:00:00', 18, NULL, 1),
(143, '2023-02-01 14:00:00', 18, NULL, 1),
(144, '2023-02-01 15:00:00', 18, NULL, 1);

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `doctors`
--

CREATE TABLE `doctors` (
  `ID` int(11) NOT NULL,
  `firstName` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `lastName` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `specialization` int(11) DEFAULT NULL,
  `gender` char(1) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `doctors`
--

INSERT INTO `doctors` (`ID`, `firstName`, `lastName`, `specialization`, `gender`) VALUES
(1, 'Adam', 'Nowak', 1, 'm'),
(2, 'Anna', 'Kochanowska', 2, 'f'),
(3, 'Eryk', 'Urbański', 3, 'm'),
(4, 'Damian', 'Krajewski', 4, 'm'),
(5, 'Alex', 'Pawlak', 5, 'm'),
(6, 'Aleksy', 'Nowak', 6, 'm'),
(7, 'Kajetan', 'Kucharski', 7, 'm'),
(8, 'Diego', 'Andrzejewski', 1, 'm'),
(9, 'Łukasz', 'Woźniak', 2, 'm'),
(10, 'Błażej', 'Rutkowski', 3, 'm'),
(11, 'Stefania', 'Urbańska', 4, 'm'),
(12, 'Milena', 'Kamińska', 5, 'm'),
(13, 'Adela', 'Ziółkowska', 6, 'm'),
(14, 'Joanna', 'Kowalska', 7, 'm'),
(15, 'Agnieszka', 'Brzezińska', 1, 'm'),
(16, 'Kinga', 'Kubiak', 2, 'm'),
(17, 'Hortensja', 'Błaszczyk', 3, 'm'),
(18, 'Judyta', 'Nowak', 4, 'm');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `localizations`
--

CREATE TABLE `localizations` (
  `ID` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `localizations`
--

INSERT INTO `localizations` (`ID`, `name`) VALUES
(1, 'Kraków Chabrowa'),
(2, 'Rybnik Słowiańska'),
(3, 'Ostrołęka Piastowska'),
(4, 'Biała Podlaska Zamkowa'),
(5, 'Przemyśl Rzemieniewicka'),
(6, 'Opole Urocza'),
(7, 'Konin Słowiańska');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `patients`
--

CREATE TABLE `patients` (
  `ID` int(11) NOT NULL,
  `firstName` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `lastName` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `birthDate` date DEFAULT NULL,
  `gender` char(1) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `patients`
--

INSERT INTO `patients` (`ID`, `firstName`, `lastName`, `birthDate`, `gender`) VALUES
(1, 'Adam', 'Kowalski', '2001-12-01', 'm'),
(2, 'Ewa', 'Kowalska', '1999-11-05', 'f');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `shifts`
--

CREATE TABLE `shifts` (
  `ID` int(11) NOT NULL,
  `doctor` int(11) DEFAULT NULL,
  `localization` int(11) DEFAULT NULL,
  `dayOfTheWeek` int(11) DEFAULT NULL,
  `shiftStart` time DEFAULT NULL,
  `shiftEnd` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `shifts`
--

INSERT INTO `shifts` (`ID`, `doctor`, `localization`, `dayOfTheWeek`, `shiftStart`, `shiftEnd`) VALUES
(1, 1, 5, 1, '08:00:00', '16:00:00'),
(2, 1, 5, 2, '08:00:00', '16:00:00'),
(3, 1, 5, 3, '08:00:00', '16:00:00'),
(4, 1, 2, 4, '08:00:00', '16:00:00'),
(5, 1, 1, 5, '08:00:00', '16:00:00'),
(6, 1, 6, 6, '08:00:00', '16:00:00'),
(7, 2, 7, 1, '08:00:00', '16:00:00'),
(8, 2, 1, 2, '08:00:00', '16:00:00'),
(9, 2, 4, 3, '08:00:00', '16:00:00'),
(10, 2, 6, 4, '08:00:00', '16:00:00'),
(11, 2, 1, 5, '08:00:00', '16:00:00'),
(12, 2, 7, 6, '08:00:00', '16:00:00'),
(13, 3, 5, 1, '08:00:00', '16:00:00'),
(14, 3, 5, 2, '08:00:00', '16:00:00'),
(15, 3, 7, 3, '08:00:00', '16:00:00'),
(16, 3, 3, 4, '08:00:00', '16:00:00'),
(17, 3, 7, 5, '08:00:00', '16:00:00'),
(18, 3, 2, 6, '08:00:00', '16:00:00'),
(19, 4, 5, 1, '08:00:00', '16:00:00'),
(20, 4, 7, 2, '08:00:00', '16:00:00'),
(21, 4, 4, 3, '08:00:00', '16:00:00'),
(22, 4, 4, 4, '08:00:00', '16:00:00'),
(23, 4, 7, 5, '08:00:00', '16:00:00'),
(24, 4, 6, 6, '08:00:00', '16:00:00'),
(25, 5, 1, 1, '08:00:00', '16:00:00'),
(26, 5, 7, 2, '08:00:00', '16:00:00'),
(27, 5, 1, 3, '08:00:00', '16:00:00'),
(28, 5, 1, 4, '08:00:00', '16:00:00'),
(29, 5, 5, 5, '08:00:00', '16:00:00'),
(30, 5, 6, 6, '08:00:00', '16:00:00'),
(31, 6, 1, 1, '08:00:00', '16:00:00'),
(32, 6, 4, 2, '08:00:00', '16:00:00'),
(33, 6, 5, 3, '08:00:00', '16:00:00'),
(34, 6, 3, 4, '08:00:00', '16:00:00'),
(35, 6, 7, 5, '08:00:00', '16:00:00'),
(36, 6, 2, 6, '08:00:00', '16:00:00'),
(37, 7, 2, 1, '08:00:00', '16:00:00'),
(38, 7, 1, 2, '08:00:00', '16:00:00'),
(39, 7, 3, 3, '08:00:00', '16:00:00'),
(40, 7, 4, 4, '08:00:00', '16:00:00'),
(41, 7, 3, 5, '08:00:00', '16:00:00'),
(42, 7, 5, 6, '08:00:00', '16:00:00'),
(43, 8, 1, 1, '08:00:00', '16:00:00'),
(44, 8, 4, 2, '08:00:00', '16:00:00'),
(45, 8, 1, 3, '08:00:00', '16:00:00'),
(46, 8, 4, 4, '08:00:00', '16:00:00'),
(47, 8, 5, 5, '08:00:00', '16:00:00'),
(48, 8, 6, 6, '08:00:00', '16:00:00'),
(49, 9, 5, 1, '08:00:00', '16:00:00'),
(50, 9, 7, 2, '08:00:00', '16:00:00'),
(51, 9, 4, 3, '08:00:00', '16:00:00'),
(52, 9, 2, 4, '08:00:00', '16:00:00'),
(53, 9, 2, 5, '08:00:00', '16:00:00'),
(54, 9, 3, 6, '08:00:00', '16:00:00'),
(55, 10, 2, 1, '08:00:00', '16:00:00'),
(56, 10, 5, 2, '08:00:00', '16:00:00'),
(57, 10, 3, 3, '08:00:00', '16:00:00'),
(58, 10, 3, 4, '08:00:00', '16:00:00'),
(59, 10, 2, 5, '08:00:00', '16:00:00'),
(60, 10, 5, 6, '08:00:00', '16:00:00'),
(61, 11, 1, 1, '08:00:00', '16:00:00'),
(62, 11, 1, 2, '08:00:00', '16:00:00'),
(63, 11, 7, 3, '08:00:00', '16:00:00'),
(64, 11, 6, 4, '08:00:00', '16:00:00'),
(65, 11, 4, 5, '08:00:00', '16:00:00'),
(66, 11, 6, 6, '08:00:00', '16:00:00'),
(67, 12, 5, 1, '08:00:00', '16:00:00'),
(68, 12, 7, 2, '08:00:00', '16:00:00'),
(69, 12, 5, 3, '08:00:00', '16:00:00'),
(70, 12, 3, 4, '08:00:00', '16:00:00'),
(71, 12, 7, 5, '08:00:00', '16:00:00'),
(72, 12, 3, 6, '08:00:00', '16:00:00'),
(73, 13, 4, 1, '08:00:00', '16:00:00'),
(74, 13, 4, 2, '08:00:00', '16:00:00'),
(75, 13, 6, 3, '08:00:00', '16:00:00'),
(76, 13, 6, 4, '08:00:00', '16:00:00'),
(77, 13, 1, 5, '08:00:00', '16:00:00'),
(78, 13, 5, 6, '08:00:00', '16:00:00'),
(79, 14, 7, 1, '08:00:00', '16:00:00'),
(80, 14, 5, 2, '08:00:00', '16:00:00'),
(81, 14, 2, 3, '08:00:00', '16:00:00'),
(82, 14, 4, 4, '08:00:00', '16:00:00'),
(83, 14, 5, 5, '08:00:00', '16:00:00'),
(84, 14, 7, 6, '08:00:00', '16:00:00'),
(85, 15, 7, 1, '08:00:00', '16:00:00'),
(86, 15, 1, 2, '08:00:00', '16:00:00'),
(87, 15, 4, 3, '08:00:00', '16:00:00'),
(88, 15, 3, 4, '08:00:00', '16:00:00'),
(89, 15, 1, 5, '08:00:00', '16:00:00'),
(90, 15, 2, 6, '08:00:00', '16:00:00'),
(91, 16, 3, 1, '08:00:00', '16:00:00'),
(92, 16, 5, 2, '08:00:00', '16:00:00'),
(93, 16, 3, 3, '08:00:00', '16:00:00'),
(94, 16, 7, 4, '08:00:00', '16:00:00'),
(95, 16, 7, 5, '08:00:00', '16:00:00'),
(96, 16, 4, 6, '08:00:00', '16:00:00'),
(97, 17, 5, 1, '08:00:00', '16:00:00'),
(98, 17, 7, 2, '08:00:00', '16:00:00'),
(99, 17, 5, 3, '08:00:00', '16:00:00'),
(100, 17, 7, 4, '08:00:00', '16:00:00'),
(101, 17, 5, 5, '08:00:00', '16:00:00'),
(102, 17, 3, 6, '08:00:00', '16:00:00'),
(103, 18, 1, 1, '08:00:00', '16:00:00'),
(104, 18, 3, 2, '08:00:00', '16:00:00'),
(105, 18, 1, 3, '08:00:00', '16:00:00'),
(106, 18, 3, 4, '08:00:00', '16:00:00'),
(107, 18, 4, 5, '08:00:00', '16:00:00'),
(108, 18, 3, 6, '08:00:00', '16:00:00');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `specializations`
--

CREATE TABLE `specializations` (
  `ID` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `specializations`
--

INSERT INTO `specializations` (`ID`, `name`) VALUES
(1, 'Pediatra'),
(2, 'Internista'),
(3, 'Kardiolog'),
(4, 'Laryngolog'),
(5, 'Ginekolog'),
(6, 'Dermatolog'),
(7, 'Ortopeda');

-- --------------------------------------------------------

--
-- Struktura tabeli dla tabeli `users`
--

CREATE TABLE `users` (
  `ID` int(11) NOT NULL,
  `login` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_polish_ci DEFAULT NULL,
  `active` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_polish_ci;

--
-- Zrzut danych tabeli `users`
--

INSERT INTO `users` (`ID`, `login`, `password`, `active`) VALUES
(1, 'test', '1234', 1),
(2, 'tset', '4321', 1);

--
-- Indeksy dla zrzutów tabel
--

--
-- Indeksy dla tabeli `appointments`
--
ALTER TABLE `appointments`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `doctor` (`doctor`),
  ADD KEY `patient` (`patient`),
  ADD KEY `localization` (`localization`);

--
-- Indeksy dla tabeli `doctors`
--
ALTER TABLE `doctors`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `specialization` (`specialization`);

--
-- Indeksy dla tabeli `localizations`
--
ALTER TABLE `localizations`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `patients`
--
ALTER TABLE `patients`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `shifts`
--
ALTER TABLE `shifts`
  ADD PRIMARY KEY (`ID`),
  ADD KEY `doctor` (`doctor`),
  ADD KEY `localization` (`localization`);

--
-- Indeksy dla tabeli `specializations`
--
ALTER TABLE `specializations`
  ADD PRIMARY KEY (`ID`);

--
-- Indeksy dla tabeli `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `login` (`login`);

--
-- AUTO_INCREMENT dla zrzuconych tabel
--

--
-- AUTO_INCREMENT dla tabeli `appointments`
--
ALTER TABLE `appointments`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=145;

--
-- AUTO_INCREMENT dla tabeli `doctors`
--
ALTER TABLE `doctors`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT dla tabeli `localizations`
--
ALTER TABLE `localizations`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `shifts`
--
ALTER TABLE `shifts`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=109;

--
-- AUTO_INCREMENT dla tabeli `specializations`
--
ALTER TABLE `specializations`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT dla tabeli `users`
--
ALTER TABLE `users`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Ograniczenia dla zrzutów tabel
--

--
-- Ograniczenia dla tabeli `appointments`
--
ALTER TABLE `appointments`
  ADD CONSTRAINT `appointments_ibfk_1` FOREIGN KEY (`doctor`) REFERENCES `doctors` (`ID`),
  ADD CONSTRAINT `appointments_ibfk_2` FOREIGN KEY (`patient`) REFERENCES `patients` (`ID`),
  ADD CONSTRAINT `appointments_ibfk_3` FOREIGN KEY (`localization`) REFERENCES `localizations` (`ID`);

--
-- Ograniczenia dla tabeli `doctors`
--
ALTER TABLE `doctors`
  ADD CONSTRAINT `doctors_ibfk_1` FOREIGN KEY (`specialization`) REFERENCES `specializations` (`ID`);

--
-- Ograniczenia dla tabeli `patients`
--
ALTER TABLE `patients`
  ADD CONSTRAINT `patients_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `users` (`ID`);

--
-- Ograniczenia dla tabeli `shifts`
--
ALTER TABLE `shifts`
  ADD CONSTRAINT `shifts_ibfk_1` FOREIGN KEY (`doctor`) REFERENCES `doctors` (`ID`),
  ADD CONSTRAINT `shifts_ibfk_2` FOREIGN KEY (`localization`) REFERENCES `localizations` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
