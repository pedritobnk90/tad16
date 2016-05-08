-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 08-05-2016 a las 22:47:18
-- Versión del servidor: 5.6.25
-- Versión de PHP: 5.6.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tad16`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Clasificacion`
--

CREATE TABLE IF NOT EXISTS `Clasificacion` (
  `id` int(11) NOT NULL,
  `idLiga` int(11) NOT NULL,
  `idEquipo` int(11) NOT NULL,
  `posicion` int(11) NOT NULL,
  `puntos` int(11) NOT NULL,
  `golesFavor` int(11) NOT NULL,
  `golesContra` int(11) NOT NULL,
  `difGoles` int(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Clasificacion`
--

INSERT INTO `Clasificacion` (`id`, `idLiga`, `idEquipo`, `posicion`, `puntos`, `golesFavor`, `golesContra`, `difGoles`) VALUES
(1, 1, 17, 3, 3, 3, 2, 5),
(2, 1, 13, 2, 4, 4, 2, 4),
(3, 1, 20, 4, 3, 2, 2, 4),
(4, 1, 15, 5, 3, 2, 1, 3),
(5, 1, 1, 7, 3, 2, 1, 1),
(6, 1, 6, 1, 6, 2, 0, 2),
(7, 1, 11, 6, 3, 1, 2, 3),
(8, 1, 10, 12, 1, 1, 1, 0),
(9, 1, 9, 10, 2, 2, 2, 0),
(10, 1, 4, 11, 1, 1, 2, 1),
(11, 1, 3, 9, 2, 2, 2, 2),
(12, 1, 8, 13, 1, 0, 0, 0),
(13, 1, 7, 14, 1, 0, 0, 0),
(14, 1, 2, 15, 0, 1, 2, -1),
(15, 1, 12, 17, 0, 0, 1, -1),
(16, 1, 5, 16, 0, 0, 1, -1),
(17, 1, 16, 19, 0, 0, 2, -2),
(18, 1, 19, 8, 3, 1, 2, -1),
(19, 1, 14, 18, 0, 1, 3, -2),
(20, 1, 18, 20, 0, 0, 3, -3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Equipo`
--

CREATE TABLE IF NOT EXISTS `Equipo` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `liga` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Equipo`
--

INSERT INTO `Equipo` (`id`, `nombre`, `liga`) VALUES
(1, 'Real Madrid', 1),
(2, 'Barcelona', 1),
(3, 'Atlético Madrid', 1),
(4, 'Sevilla', 1),
(5, 'Betis', 1),
(6, 'Villareal', 1),
(7, 'Athletic Bilbao', 1),
(8, 'Levante', 1),
(9, 'Getafe', 1),
(10, 'Valencia', 1),
(11, 'Celta', 1),
(12, 'Sporting', 1),
(13, 'Las Palmas', 1),
(14, 'Deportivo', 1),
(15, 'Eibar', 1),
(16, 'Espanyol', 1),
(17, 'Rayo Vallecano', 1),
(18, 'Granada', 1),
(19, 'Real Sociedad', 1),
(20, 'Málaga', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Jornada`
--

CREATE TABLE IF NOT EXISTS `Jornada` (
  `id` int(11) NOT NULL,
  `idJornada` int(11) NOT NULL,
  `idEquipoLocal` int(11) NOT NULL,
  `idEquipoVisitante` int(11) NOT NULL,
  `golesEquipoLocal` int(11) DEFAULT NULL,
  `golesEquipoVisitante` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Jornada`
--

INSERT INTO `Jornada` (`id`, `idJornada`, `idEquipoLocal`, `idEquipoVisitante`, `golesEquipoLocal`, `golesEquipoVisitante`) VALUES
(1, 1, 1, 2, 2, 1),
(2, 1, 3, 4, 1, 1),
(3, 1, 5, 6, 0, 1),
(4, 1, 7, 8, 0, 0),
(5, 1, 9, 10, 2, 2),
(6, 1, 11, 12, 1, 0),
(7, 1, 13, 14, 3, 1),
(8, 1, 15, 16, 2, 0),
(9, 1, 17, 18, 3, 0),
(10, 1, 19, 20, 0, 2),
(11, 2, 20, 1, 0, 2),
(13, 2, 2, 3, 1, 1),
(14, 2, 4, 5, 0, 1),
(15, 2, 6, 7, 1, 0),
(16, 2, 8, 9, 0, 0),
(17, 2, 10, 11, 2, 0),
(18, 2, 12, 13, 1, 1),
(19, 2, 14, 15, 1, 0),
(20, 2, 16, 17, 2, 0),
(21, 2, 18, 19, 0, 1),
(22, 3, 1, 3, NULL, NULL),
(23, 3, 2, 4, NULL, NULL),
(24, 3, 5, 7, NULL, NULL),
(25, 3, 6, 8, NULL, NULL),
(26, 3, 9, 11, NULL, NULL),
(27, 3, 10, 12, NULL, NULL),
(28, 3, 13, 15, NULL, NULL),
(29, 3, 14, 16, NULL, NULL),
(30, 3, 17, 19, NULL, NULL),
(31, 3, 18, 20, NULL, NULL),
(32, 4, 1, 4, NULL, NULL),
(33, 4, 2, 5, NULL, NULL),
(34, 4, 18, 6, NULL, NULL),
(35, 4, 7, 10, NULL, NULL),
(36, 4, 8, 11, NULL, NULL),
(37, 4, 9, 12, NULL, NULL),
(38, 4, 13, 16, NULL, NULL),
(39, 4, 14, 17, NULL, NULL),
(40, 4, 15, 20, NULL, NULL),
(41, 4, 3, 19, NULL, NULL),
(42, 5, 2, 1, NULL, NULL),
(43, 5, 4, 3, NULL, NULL),
(44, 5, 6, 5, NULL, NULL),
(45, 5, 8, 7, NULL, NULL),
(46, 5, 10, 9, NULL, NULL),
(47, 5, 12, 11, NULL, NULL),
(48, 5, 14, 13, NULL, NULL),
(49, 5, 16, 15, NULL, NULL),
(50, 5, 18, 17, NULL, NULL),
(51, 5, 20, 19, NULL, NULL),
(52, 6, 20, 18, NULL, NULL),
(53, 6, 19, 17, NULL, NULL),
(54, 6, 16, 14, NULL, NULL),
(55, 6, 15, 13, NULL, NULL),
(56, 6, 12, 10, NULL, NULL),
(57, 6, 11, 9, NULL, NULL),
(58, 6, 8, 6, NULL, NULL),
(59, 6, 7, 5, NULL, NULL),
(60, 6, 4, 2, NULL, NULL),
(61, 6, 3, 1, NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Liga`
--

CREATE TABLE IF NOT EXISTS `Liga` (
  `id` int(11) NOT NULL,
  `idCategoria` int(11) NOT NULL,
  `desCategoria` varchar(50) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Liga`
--

INSERT INTO `Liga` (`id`, `idCategoria`, `desCategoria`) VALUES
(1, 1, 'La Liga');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE IF NOT EXISTS `Usuario` (
  `id` int(11) NOT NULL,
  `username` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `pass` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `apellidos` varchar(50) COLLATE utf8_spanish_ci NOT NULL,
  `equipoFavorito` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`id`, `username`, `pass`, `nombre`, `apellidos`, `equipoFavorito`) VALUES
(1, 'pedro', 'pedro16', 'Pedro', 'Madrigal', 5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Clasificacion`
--
ALTER TABLE `Clasificacion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Equipo`
--
ALTER TABLE `Equipo`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Jornada`
--
ALTER TABLE `Jornada`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Liga`
--
ALTER TABLE `Liga`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `idCategoria` (`idCategoria`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id` (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Clasificacion`
--
ALTER TABLE `Clasificacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT de la tabla `Equipo`
--
ALTER TABLE `Equipo`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT de la tabla `Jornada`
--
ALTER TABLE `Jornada`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=62;
--
-- AUTO_INCREMENT de la tabla `Liga`
--
ALTER TABLE `Liga`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
