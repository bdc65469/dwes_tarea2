-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 07-11-2024 a las 13:30:14
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tarea2dwes`
--
CREATE DATABASE IF NOT EXISTS `tarea2dwes` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `tarea2dwes`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `credenciales`
--

CREATE TABLE `credenciales` (
  `id` int(11) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `idPersona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `credenciales`
--

INSERT INTO `credenciales` (`id`, `usuario`, `password`, `idPersona`) VALUES
(1, 'sara', 'sara123', 2),
(2, 'pepe', 'pepe123', 1),
(3, 'luis', 'luis123', 4),
(4, 'admin', '', 3),
(5, 'gerald', 'gerald123', 5),
(6, 'alberto', 'alberto123', 6),
(7, 'pepe1234', 'pepe12', 7),
(8, 'manuela123', 'manuela123456', 8),
(9, 'josean', 'qwert123', 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ejemplares`
--

CREATE TABLE `ejemplares` (
  `id` int(11) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `idplanta` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ejemplares`
--

INSERT INTO `ejemplares` (`id`, `nombre`, `idplanta`) VALUES
(1, 'GRSOL_1', 'GRSOL'),
(2, 'GRSOL_2', 'GRSOL'),
(3, 'MARGARITA_3', 'MARGARITA'),
(4, 'DL_4', 'DL'),
(5, 'LVD_5', 'LVD'),
(6, 'DL_6', 'DL'),
(7, 'TLP_7', 'TLP'),
(8, 'TLP_8', 'TLP'),
(9, 'TLP_9', 'TLP'),
(10, 'MNT_10', 'MNT'),
(11, 'LVD_11', 'LVD'),
(12, 'MARGARITA_12', 'MARGARITA'),
(13, 'MNT_13', 'MNT'),
(14, 'MNZLL_14', 'MNZLL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id` int(11) NOT NULL,
  `fechaHora` datetime NOT NULL,
  `mensaje` varchar(500) NOT NULL,
  `idEjemplar` int(11) NOT NULL,
  `idPersona` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id`, `fechaHora`, `mensaje`, `idEjemplar`, `idPersona`) VALUES
(2, '2024-11-04 10:16:45', 'nuevo girasol', 2, 5),
(3, '2024-11-04 10:17:25', 'nuevo mensaje para el ejemplar 1 de girasol', 1, 5),
(4, '2024-11-04 10:50:43', 'nuevo ejemplar de margaritas', 3, 2),
(5, '2024-11-04 10:54:51', 'nuevo mensaje para el diente de leon', 4, 3),
(6, '2024-11-04 10:55:30', 'nuevo mensaje para un ejemplar de la planta lavanda', 5, 4),
(9, '2024-11-05 13:09:32', 'nuevo mensaje a un ejemplar de tulipan', 7, 3),
(12, '2024-11-05 14:18:14', 'la menta esta creciendo', 10, 3),
(13, '2024-11-06 12:26:48', 'la margarita_3 ha crecido', 3, 3),
(14, '2024-11-06 12:30:49', 'Nuevo ejemplar de Lavanda creado.', 11, 3),
(15, '2024-11-06 12:31:49', 'este ejemplar ha sido regado', 6, 3),
(16, '2024-11-07 12:46:55', 'Nuevo ejemplar de Margarita creado.', 12, 3),
(17, '2024-11-07 12:48:17', 'este ejemplar ha crecido', 12, 3),
(18, '2024-11-07 12:50:50', 'Nuevo ejemplar de Menta creado.', 13, 2),
(19, '2024-11-07 12:51:36', 'este ejemplar necesita agua', 12, 2),
(20, '2024-11-07 13:25:55', 'Nuevo ejemplar de Manzanilla creado.', 14, 3),
(21, '2024-11-07 13:26:43', 'este es el primer ejemplar de manzanilla', 14, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE `personas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `personas`
--

INSERT INTO `personas` (`id`, `nombre`, `email`) VALUES
(1, 'pepe', 'pepe_lop@vivero.es'),
(2, 'sara', 'sara@vivero.es'),
(3, 'admin', 'admin@viviero.es'),
(4, 'luis', 'luis@viviero.es'),
(5, 'gerald', 'gerald@vivero.es'),
(6, 'alberto', 'alberto123@vivero.es'),
(7, 'pepe', 'pepe@vivero.es'),
(8, 'manuela', 'manuela@vivero.es'),
(9, 'Jose Antonio', 'jo_antonio@vivero.es');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plantas`
--

CREATE TABLE `plantas` (
  `codigo` varchar(50) NOT NULL,
  `nombrecomun` varchar(100) NOT NULL,
  `nombrecientifico` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `plantas`
--

INSERT INTO `plantas` (`codigo`, `nombrecomun`, `nombrecientifico`) VALUES
('DL', 'Diente de león', 'Taraxacum officinale'),
('GRSOL', 'Girasol', 'Helianthus annuus'),
('LVD', 'Lavanda', 'Lavandula angustifolia'),
('MARGARITA', 'Margarita', 'Margaritarium'),
('MNT', 'Menta', 'Mentha spp'),
('MNZLL', 'Manzanilla', 'Matricaria chamomilla'),
('ROSA', 'Rosa', 'Rosarium'),
('TLP', 'Tulipan', 'Tulipanius');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuario` (`usuario`),
  ADD KEY `fk_persona_credenciales` (`idPersona`);

--
-- Indices de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkplanta` (`idplanta`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fkejemplar_mensaje` (`idEjemplar`),
  ADD KEY `fkpersona_mensaje` (`idPersona`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `plantas`
--
ALTER TABLE `plantas`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `credenciales`
--
ALTER TABLE `credenciales`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `credenciales`
--
ALTER TABLE `credenciales`
  ADD CONSTRAINT `fk_persona_credenciales` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`id`);

--
-- Filtros para la tabla `ejemplares`
--
ALTER TABLE `ejemplares`
  ADD CONSTRAINT `fkplanta` FOREIGN KEY (`idplanta`) REFERENCES `plantas` (`codigo`);

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `fkejemplar_mensaje` FOREIGN KEY (`idEjemplar`) REFERENCES `ejemplares` (`id`),
  ADD CONSTRAINT `fkpersona_mensaje` FOREIGN KEY (`idPersona`) REFERENCES `personas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
