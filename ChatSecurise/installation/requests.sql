-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : Dim 30 mai 2021 à 23:03
-- Version du serveur :  10.4.17-MariaDB
-- Version de PHP : 8.0.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `securechat`
--

-- --------------------------------------------------------

--
-- Structure de la table `contacts`
--

CREATE USER 'secureChat'@'localhost' IDENTIFIED BY 't8AgECsq1Esqg98Fs3E/5qsdFb45dsv8B3c4CCp@';
GRANT USAGE ON *.* TO 'secureChat'@'localhost' REQUIRE NONE WITH MAX_QUERIES_PER_HOUR 0 MAX_CONNECTIONS_PER_HOUR 0 MAX_UPDATES_PER_HOUR 0 MAX_USER_CONNECTIONS 0;
CREATE DATABASE IF NOT EXISTS `secureChat`;
GRANT ALL PRIVILEGES ON `secureChat`.* TO 'secureChat'@'localhost';
REVOKE ALL PRIVILEGES ON `securechat`.* FROM 'secureChat'@'localhost';
GRANT SELECT, INSERT, UPDATE, DELETE ON `securechat`.* TO 'secureChat'@'localhost';
USE secureChat;

CREATE TABLE `contacts` (
                            `id` int(11) NOT NULL,
                            `mat_user1` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                            `mat_user2` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
                               `id` bigint(20) UNSIGNED NOT NULL,
                               `uuid` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                               `connection` text COLLATE utf8mb4_unicode_ci NOT NULL,
                               `queue` text COLLATE utf8mb4_unicode_ci NOT NULL,
                               `payload` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
                               `exception` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
                               `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `invitations`
--

CREATE TABLE `invitations` (
                               `id` int(11) NOT NULL,
                               `mat_sender` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                               `mat_recipient` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Structure de la table `messages`
--

CREATE TABLE `messages` (
                            `id` int(11) NOT NULL,
                            `mat_sender` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                            `mat_recipient` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
                            `message` blob NOT NULL,
                            `sended_at` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `messages`
--

INSERT INTO `messages` (`id`, `mat_sender`, `mat_recipient`, `message`, `sended_at`) VALUES
(6, '54627', 'mwa', 0x72573a3aedf5693857cf02eda6e97cb1, '2021-05-10 21:36:01'),
(7, 'mwa', '54627', 0x44f5c058e766f7f286edbd35c733fe26, '2021-05-10 21:36:09'),
(8, 'mwa', '54627', 0xbce530437f5e2c73e766a66b21b8873e67297793e174e55696021c29b4f506d1, '2021-05-10 21:36:17'),
(9, '54627', 'mwa', 0x84005ffa44abb0a7f4d92caf8c9db520, '2021-05-10 21:36:21'),
(10, 'mwa', '54627', 0xae06def27144b4f95aea740c8f9cc602, '2021-05-10 21:36:26'),
(11, 'mwa', '54627', 0xa5b3d0ea913d8c09d17984808e32c072, '2021-05-10 21:36:28'),
(12, 'mwa', '54627', 0xabdda7fde224a3f1d2ccb13dd3523c89, '2021-05-10 21:36:30'),
(13, 'mwa', '54627', 0x949fa060beed57a48a751a2841cf50a2, '2021-05-10 21:36:31');

-- --------------------------------------------------------

--
-- Structure de la table `migrations`
--

CREATE TABLE `migrations` (
                              `id` int(10) UNSIGNED NOT NULL,
                              `migration` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                              `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `password_resets`
--

CREATE TABLE `password_resets` (
                                   `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                   `token` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                                   `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

CREATE TABLE `users` (
                         `id` varchar(36) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `name` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `matricule` varchar(5) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `email_verified_at` timestamp NULL DEFAULT NULL,
                         `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
                         `remember_token` varchar(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
                         `created_at` timestamp NULL DEFAULT NULL,
                         `updated_at` timestamp NULL DEFAULT NULL,
                         `last_action` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `name`, `matricule`, `email`, `email_verified_at`, `password`, `remember_token`, `created_at`, `updated_at`, `last_action`) VALUES
('005c9099-cd00-4f8b-bdcf-bab02572bb75', 'Dylan BRICAR', '54027', '54027@etu.he2b.be', NULL, '$2y$12$XfFkbxeTOfVKMq7h5motcubIaT33nLtS4X1nJkc4zDfrkuTcx/.Pa', 'JuVPkaALInTam2PMXkxYYJfmi68O2dem8a1cYmV5Hkg6idbf5ZHfK5feWL0d', '2021-05-10 15:54:39', '2021-05-10 15:54:39', '2021-05-10 18:02:46'),
('28f0918a-a1b8-4f0e-a73a-b390c1a75d2d', 'Jeremie SESHIE', '54627', '54627@etu.he2b.be', NULL, '$2y$12$1PWXFcXrxLDRAYs26DPExePYWUZMrGdkW5L9onXZIj.L1bkNHVuhu', '4IKj95oSlD0PnCDPzzYOyflKAat23CwbD7N8TjHUPfGfJl0rAkD9fZ1uKife', '2021-05-10 15:55:41', '2021-05-10 15:55:41', '2021-05-10 21:37:12'),
('bb113a75-df52-4a7d-8db0-8eb35441bf4a', 'Moussa WAHID', 'mwa', 'mwahid@he2b.be', NULL, '$2y$12$FF8y.aMQqwjS51wIvBrc..THdETvLhdEubKnN2Kva/P.1nYPagnSC', 'DgAYU8qUz6YQOZSlPoMvcIE2VkYc5khWpmXPUojZzR3IW8CoLu7n0mDiHUJ0', '2021-05-10 15:56:02', '2021-05-10 15:56:02', '2021-05-10 21:40:32');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `contacts`
--
ALTER TABLE `contacts`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_mat_user1` (`mat_user1`),
  ADD KEY `fk_mat_user2` (`mat_user2`);

--
-- Index pour la table `failed_jobs`
--
ALTER TABLE `failed_jobs`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Index pour la table `invitations`
--
ALTER TABLE `invitations`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_mat_recipient3` (`mat_recipient`),
  ADD KEY `fk_mat_sender3` (`mat_sender`);

--
-- Index pour la table `messages`
--
ALTER TABLE `messages`
    ADD PRIMARY KEY (`id`),
  ADD KEY `fk_mat_sender` (`mat_sender`),
  ADD KEY `fk_mat_recipient` (`mat_recipient`);

--
-- Index pour la table `migrations`
--
ALTER TABLE `migrations`
    ADD PRIMARY KEY (`id`);

--
-- Index pour la table `password_resets`
--
ALTER TABLE `password_resets`
    ADD KEY `password_resets_email_index` (`email`);

--
-- Index pour la table `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`),
  ADD KEY `matricule` (`matricule`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `contacts`
--
ALTER TABLE `contacts`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT pour la table `failed_jobs`
--
ALTER TABLE `failed_jobs`
    MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `invitations`
--
ALTER TABLE `invitations`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `messages`
--
ALTER TABLE `messages`
    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT pour la table `migrations`
--
ALTER TABLE `migrations`
    MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `contacts`
--
ALTER TABLE `contacts`
    ADD CONSTRAINT `fk_mat_user1` FOREIGN KEY (`mat_user1`) REFERENCES `users` (`matricule`),
  ADD CONSTRAINT `fk_mat_user2` FOREIGN KEY (`mat_user2`) REFERENCES `users` (`matricule`);

--
-- Contraintes pour la table `invitations`
--
ALTER TABLE `invitations`
    ADD CONSTRAINT `fk_mat_recipient3` FOREIGN KEY (`mat_recipient`) REFERENCES `users` (`matricule`),
  ADD CONSTRAINT `fk_mat_sender3` FOREIGN KEY (`mat_sender`) REFERENCES `users` (`matricule`);

--
-- Contraintes pour la table `messages`
--
ALTER TABLE `messages`
    ADD CONSTRAINT `fk_mat_recipient` FOREIGN KEY (`mat_recipient`) REFERENCES `users` (`matricule`),
  ADD CONSTRAINT `fk_mat_sender` FOREIGN KEY (`mat_sender`) REFERENCES `users` (`matricule`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
