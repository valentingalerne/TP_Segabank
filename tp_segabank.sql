-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           5.7.23 - MySQL Community Server (GPL)
-- SE du serveur:                Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Listage de la structure de la base pour tp_segabank
DROP DATABASE IF EXISTS `tp_segabank`;
CREATE DATABASE IF NOT EXISTS `tp_segabank` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `tp_segabank`;

-- Listage de la structure de la table tp_segabank. agence
DROP TABLE IF EXISTS `agence`;
CREATE TABLE IF NOT EXISTS `agence` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(30) NOT NULL DEFAULT '',
  `adresse` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Listage des données de la table tp_segabank.agence : 5 rows
/*!40000 ALTER TABLE `agence` DISABLE KEYS */;
INSERT INTO `agence` (`id`, `code`, `adresse`) VALUES
	(1, 'CA123', '14 chemin des prés'),
	(2, 'CA147', '18 rue des impasses'),
	(3, 'CA359', '2 avenue du champs'),
	(4, 'CA587', '141 boulevard du roseau'),
	(5, 'CA620', '5 route des étangs');
/*!40000 ALTER TABLE `agence` ENABLE KEYS */;

-- Listage de la structure de la table tp_segabank. compte
DROP TABLE IF EXISTS `compte`;
CREATE TABLE IF NOT EXISTS `compte` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `solde` float NOT NULL,
  `decouvert` float DEFAULT NULL,
  `taux_interet` float DEFAULT NULL,
  `type` int(1) NOT NULL,
  `id_agence` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

-- Listage des données de la table tp_segabank.compte : 9 rows
/*!40000 ALTER TABLE `compte` DISABLE KEYS */;
INSERT INTO `compte` (`id`, `solde`, `decouvert`, `taux_interet`, `type`, `id_agence`) VALUES
	(1, 1450, NULL, 5, 1, 2),
	(5, 745.14, 260, NULL, 2, 1),
	(3, 1310.5, 250, NULL, 2, 2),
	(4, 2475, NULL, NULL, 3, 2),
	(6, 67500, 5000, NULL, 2, 5),
	(7, 675, NULL, 15, 1, 3),
	(8, -200, 500, NULL, 2, 4),
	(9, 5000, NULL, NULL, 3, 4),
	(10, 1529140, NULL, NULL, 3, 3);
/*!40000 ALTER TABLE `compte` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
