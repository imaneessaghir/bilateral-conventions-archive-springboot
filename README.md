# 📂 Plateforme de gestion des accords bilatéraux

## 📌 Description
Cette application Spring Boot permet de gérer les accords bilatéraux, classés par pays et boîtes d’archives.  
Elle permet :
- D’ajouter, modifier, supprimer des accords.
- D’associer un fichier PDF à chaque accord.
- De rechercher les accords par intitulé ou pays.
- D’importer des accords depuis un fichier **Excel**.
- De visualiser les PDFs directement depuis l’interface.

## 🛠 Technologies utilisées
- **Java 17**
- **Spring Boot 3**
- **Spring Data JPA (Hibernate)**
- **MySQL**
- **Thymeleaf** (moteur de templates)
- **Bootstrap** (design)
- **Apache POI** (lecture de fichiers Excel)

---

## 📂 Structure du projet

---

## ⚙️ Installation et configuration

### 1️⃣ Prérequis
- **Java 17** installé ([Télécharger Java](https://adoptium.net/))
- **Maven** installé ([Installer Maven](https://maven.apache.org/))
- **MySQL** installé ([Télécharger MySQL](https://dev.mysql.com/downloads/))
- **phpMyAdmin** (optionnel, pour gérer la base de données plus facilement)

---

### 2️⃣ Importer la base de données
1. Ouvrir **phpMyAdmin**.
2. Créer une nouvelle base de données nommée `archives_db`.
3. Importer le fichier `database/archives_db.sql`.

---

### 3️⃣ Configurer la connexion MySQL
Modifier le fichier `src/main/resources/application.properties` :
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/archives_db
spring.datasource.username=root
spring.datasource.password=VOTRE_MOT_DE_PASSE
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
