-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3308
-- Généré le :  mar. 30 nov. 2021 à 16:36
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `ges_rdv_clinique_pjava`
--

-- --------------------------------------------------------

--
-- Structure de la table `constante`
--

DROP TABLE IF EXISTS `constante`;
CREATE TABLE IF NOT EXISTS `constante` (
  `id_constante` int(11) NOT NULL AUTO_INCREMENT,
  `Tention` varchar(255) NOT NULL,
  `Poids` int(11) NOT NULL,
  `Taille` int(11) NOT NULL,
  `Température` int(11) NOT NULL,
  `consultation_id` int(11) NOT NULL,
  PRIMARY KEY (`id_constante`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `constante`
--

INSERT INTO `constante` (`id_constante`, `Tention`, `Poids`, `Taille`, `Température`, `consultation_id`) VALUES
(7, '12/7', 65, 165, 38, 9),
(8, '15/7', 85, 193, 38, 10),
(9, '12/6', 80, 193, 35, 4);

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

DROP TABLE IF EXISTS `medicament`;
CREATE TABLE IF NOT EXISTS `medicament` (
  `id_medicament` int(11) NOT NULL AUTO_INCREMENT,
  `code` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  PRIMARY KEY (`id_medicament`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `medicament`
--

INSERT INTO `medicament` (`id_medicament`, `code`, `nom`) VALUES
(1, 7894, 'Doliprane '),
(2, 7946, 'Otrivine'),
(3, 7894, 'Aérius'),
(4, 7946, 'Norlevo');

-- --------------------------------------------------------

--
-- Structure de la table `ordmed`
--

DROP TABLE IF EXISTS `ordmed`;
CREATE TABLE IF NOT EXISTS `ordmed` (
  `id_ordMed` int(11) NOT NULL AUTO_INCREMENT,
  `posologie` varchar(255) NOT NULL,
  `nombre` int(11) NOT NULL,
  `ordonnance_id` int(11) NOT NULL,
  `medicament_id` int(11) NOT NULL,
  PRIMARY KEY (`id_ordMed`)
) ENGINE=MyISAM AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `ordmed`
--

INSERT INTO `ordmed` (`id_ordMed`, `posologie`, `nombre`, `ordonnance_id`, `medicament_id`) VALUES
(8, '1/j le soir au couché', 1, 4, 1),
(7, '2cp/j', 2, 4, 3),
(6, '2 goutes/narine matin et soir', 1, 4, 2),
(9, '2cp/j', 5, 5, 3),
(10, 'apres un rpsx', 1, 5, 4),
(11, '2gouttes / narine', 2, 6, 2);

-- --------------------------------------------------------

--
-- Structure de la table `ordonnance`
--

DROP TABLE IF EXISTS `ordonnance`;
CREATE TABLE IF NOT EXISTS `ordonnance` (
  `id_ordonnance` int(11) NOT NULL AUTO_INCREMENT,
  `consultation_id` int(11) NOT NULL,
  `patient_id` int(11) NOT NULL,
  PRIMARY KEY (`id_ordonnance`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `ordonnance`
--

INSERT INTO `ordonnance` (`id_ordonnance`, `consultation_id`, `patient_id`) VALUES
(1, 1, 2),
(4, 9, 7),
(5, 10, 8),
(6, 4, 8);

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

DROP TABLE IF EXISTS `rdv`;
CREATE TABLE IF NOT EXISTS `rdv` (
  `id_rdv` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `type_rdv` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `typepres_id` int(11) DEFAULT NULL,
  `etat` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'EN ATTENTE',
  `statut` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'NON FAIT',
  `patient_id` int(11) NOT NULL,
  `resultat` varchar(255) DEFAULT NULL,
  `ordonnance_id` int(11) DEFAULT NULL,
  `constante_id` int(11) DEFAULT NULL,
  `medecin_id` int(11) DEFAULT NULL,
  `specialite_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_rdv`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`id_rdv`, `date`, `type_rdv`, `typepres_id`, `etat`, `statut`, `patient_id`, `resultat`, `ordonnance_id`, `constante_id`, `medecin_id`, `specialite_id`) VALUES
(1, '2021-11-12', 'CONSULTATION', NULL, 'VALIDE', 'NON FAIT', 2, NULL, NULL, NULL, 9, 1),
(2, '2021-11-10', 'PRESTATION', 1, 'VALIDE', 'FAIT', 6, 'Aucun os cassé', NULL, NULL, NULL, NULL),
(4, '2021-11-17', 'CONSULTATION', NULL, 'VALIDE', 'FAIT', 8, NULL, 6, 9, 3, 4),
(5, '2021-11-15', 'PRESTATION', 3, 'VALIDE', 'FAIT', 8, 'Absence d\'appendicite ', NULL, NULL, NULL, NULL),
(6, '2021-11-27', 'PRESTATION', 2, 'VALIDE', 'FAIT', 10, 'Présence de paludisme', NULL, NULL, NULL, NULL),
(7, '2021-11-27', 'PRESTATION', 1, 'VALIDE', 'FAIT', 6, 'bras droit ', NULL, NULL, NULL, NULL),
(8, '2021-11-30', 'PRESTATION', 3, 'REFUSE', 'NON FAIT', 7, NULL, NULL, NULL, NULL, NULL),
(9, '2021-11-29', 'CONSULTATION', NULL, 'VALIDE', 'FAIT', 7, NULL, 4, 7, 12, 3),
(10, '2021-11-28', 'CONSULTATION', NULL, 'VALIDE', 'FAIT', 8, NULL, 5, 8, 12, 3),
(11, '2021-11-29', 'PRESTATION', 1, 'EN ATTENTE', 'NON FAIT', 8, NULL, NULL, NULL, NULL, NULL),
(12, '2021-11-30', 'CONSULTATION', NULL, 'VALIDE', 'NON FAIT', 6, NULL, NULL, NULL, 12, 3),
(13, '2021-11-29', 'PRESTATION', 1, 'VALIDE', 'NON FAIT', 8, NULL, NULL, NULL, NULL, NULL),
(14, '2021-11-30', 'CONSULTATION', NULL, 'EN ATTENTE', 'NON FAIT', 13, NULL, NULL, NULL, NULL, 4);

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `id_role` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id_role`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `role`
--

INSERT INTO `role` (`id_role`, `libelle`) VALUES
(1, 'ADMIN'),
(2, 'PATIENT'),
(3, 'RESPONSABLE PRESTATION'),
(4, 'SECRETAITRE'),
(5, 'MEDECIN');

-- --------------------------------------------------------

--
-- Structure de la table `specialite`
--

DROP TABLE IF EXISTS `specialite`;
CREATE TABLE IF NOT EXISTS `specialite` (
  `id_specialite` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id_specialite`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `specialite`
--

INSERT INTO `specialite` (`id_specialite`, `libelle`) VALUES
(1, 'Ophtalmologie '),
(2, 'Dentisterie'),
(3, 'Gynécologie '),
(4, 'Généraliste'),
(5, 'ORL');

-- --------------------------------------------------------

--
-- Structure de la table `typeprestation`
--

DROP TABLE IF EXISTS `typeprestation`;
CREATE TABLE IF NOT EXISTS `typeprestation` (
  `id_typePres` int(11) NOT NULL AUTO_INCREMENT,
  `libelle` varchar(255) NOT NULL,
  PRIMARY KEY (`id_typePres`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `typeprestation`
--

INSERT INTO `typeprestation` (`id_typePres`, `libelle`) VALUES
(1, 'Radio'),
(2, 'Analyse'),
(3, 'Échographie');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `code` int(11) DEFAULT NULL,
  `sexe` varchar(255) DEFAULT NULL,
  `antecedant` varchar(255) DEFAULT NULL,
  `role_id` int(11) NOT NULL,
  `specialite_id` int(11) DEFAULT NULL,
  `tel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `prenom`, `login`, `password`, `code`, `sexe`, `antecedant`, `role_id`, `specialite_id`, `tel`) VALUES
(1, 'Alapini', 'Mathis', 'admin', '1234', NULL, NULL, NULL, 1, NULL, NULL),
(2, 'Tossou', 'Christopher', 'rawycolb', '1907', 1234, 'M', 'Paludisme, Ulcère', 2, NULL, 123456789),
(3, 'ADUKE', 'Maelle', 'mao', '1110', NULL, NULL, NULL, 5, 2, NULL),
(4, 'Visionaruman', 'Uriel', 'visionaruman', '3004', NULL, NULL, NULL, 4, NULL, NULL),
(5, 'Kenane', 'Gael', 'login', '1234', NULL, NULL, NULL, 3, NULL, NULL),
(6, 'Sow', 'Fatima', 'fatimass', '1236', 7536, 'F', 'hypertension', 2, NULL, 123456987),
(7, 'Fassa', 'Alima', 'Alimass', '7598', 4587, 'F', 'Sinusite', 2, NULL, 789412365),
(8, 'Loemba', 'Ghires', 'ozazoba', '1234', 7891, 'M', 'Herpès ', 2, NULL, 775893209),
(9, 'Anaunau', 'Anaurel', 'bellegosse', '1234', NULL, NULL, NULL, 5, 1, NULL),
(10, 'play', 'no', 'dems', 'caca', 51, 'F', 'game', 2, NULL, 789456),
(11, 'kuliko', 'Jana', 'Amen', 'God', 12, 'M', 'Chants glorieux', 2, NULL, 11111),
(12, 'Maitre', 'Gims', 'gims', '12345', NULL, NULL, NULL, 5, 3, NULL),
(13, 'Brad', 'Perle', 'pbrad', '123456', 2809, 'F', 'genoux droit abimé', 2, NULL, 78954662),
(14, 'Nice', 'Day', 'nday', '1234', NULL, NULL, NULL, 5, 4, NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
