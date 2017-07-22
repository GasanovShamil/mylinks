# mylinks

Pré-requis :
	- Tomcat 9.0.0.M17
	- MySQL 5 ou plus

Configuration
	- Utilisateur MySQL : root
	- Mot de passe MySQL : root
	- Lancer le script init.sql

Compte d'admin existant
	- Email : admin@mail.com
	- Mot de passe : admin

Déployer l'archive mylinks.war sur le serveur.

Pour builder le code source avec maven 3.3.9
	- ouvrir terminal dans le repertoire du projet.
	- execute la commande "mvn clean install"
	- puis dans le repertoire "target" recupere le fichier mylinks.war
