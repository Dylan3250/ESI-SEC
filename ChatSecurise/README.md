# Création d'un chat sécurisé

## Introduction

Le but est de créer un chat dans lequel les utilisateurs peuvent communiquer de manière privée et où les informations
sont sécurisées. Ce projet comprend l'inscription, la connexion des utilisateurs ainsi qu'une gestion des contacts.

## Comment ça marche ?

Nous utilisons le framework extrêmement connu " Laravel ". Ce dernier jouit d'une très grande communauté remplie de
développeur. De ce fait, plusieurs optimisations sont déjà présentes et facilitent l'intégration des sécurités. De plus,
si une faille est trouvée elle est très rapidement corrigée et il suffira de se tenir au courant pour avoir une base de
site solide.

## Installation et prérequis :

L'installation est pareille que celle d'un projet standard Laravel et nécessite un serveur Apache/NGNIX, un serveur
MySQL, composer, NodejS et un terminal.

1) Clonez ce dépot et rentrez dans le dossier.
2) Importez les données du script " **installation/requests.sql** " (ce script crée automatiquement l'utilisateur, la base de
   données, crée les tables et peuple la table users des 3 comptes décris ci-dessous)
3) `chmod +x installation/install.sh && ./installation/install.sh`
4) `php artisan serv` (1)
5) Le site est accessible à l'adresse : http://localhost:8000

(1) : pour le lancer dans un réseau local, mais accessible aux autres machines du réseau, il faudra récupérer l'IP publique.

Sur MAC/Linux :

- Câblé : `ipconfig getifaddr en1`
- Wifi : `ipconfig getifaddr en0`

Sur Windows :

- `ipconfig` et prendre l'IPV4

Ensuite à la place de l'étape 4, il faut faire `php artisan serv --host=VOTRE_IP`

*Le script " **installation/install.sh** " execute les commandes d'installation de composer, nodes, donnes les droits nécessaires,
mais ne peux pas faire les autres étapes seul et considère que ces applications générales soient déjà installées.*

## Mot de passe :

Nous avons créé 3 comptes qui sont importés dans requests.sql, le mot de passe de chacun est " `aA1234567@` " :

- Dylan BRICAR, 54027@etu.he2b.be, aA1234567@
- Jeremie SESHIE, 54627@etu.he2b.be, aA1234567@
- Moussa WAHID, mwahid@he2b.be, aA1234567@

Les requêtes SQL créent un utilisateur " `secureChat` " dont le mot de passe
est " `t8AgECsq1Esqg98Fs3E/5qsdFb45dsv8B3c4CCp@` ".

## Informations sur la sécurité :

Toutes les informations liées à la sécurité se trouvent dans le rapport au format pdf trouvable dans **installation/rapport.pdf**.

# Informations :

Ce projet est fait par Dylan BRICAR (@54027) et Jeremie SESHIE (@54627) pour l'école Haute École Bruxelles-Brabant - HE2B ESI dans le cadre du cours du laboratoire de sécurité donné par Moussa WAHID.
