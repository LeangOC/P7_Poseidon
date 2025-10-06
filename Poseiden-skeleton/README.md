# spring-boot
## Technical:

1. Spring Boot 3.1.0
2. Java 17
3. Thymeleaf
4. Bootstrap v.4.3.1


## Setup with Intellij IDE
1. Create project from Initializr: File > New > project > Spring Initializr
2. Add lib repository into pom.xml
3. Add folders
    - Source root: src/main/java
    - View: src/main/resources
    - Static: src/main/resource/static
4. Create database with name "demo" as configuration in application.properties
5. Run sql script to create table doc/data.sql

## Implement a Feature
1. Create mapping domain class and place in package com.nnk.springboot.domain
2. Create repository class and place in package com.nnk.springboot.repositories
3. Create controller class and place in package com.nnk.springboot.controllers

## Security
1. Create user service to load user from  database and place in package com.nnk.springboot.services
2. Add configuration class and place in package com.nnk.springboot.config

## DEV1
1. Reprise du projet
2. Impossible de démarrer l'application telle qu'elle est :
   erreur de dépendance Mysql : "[ERROR] 'dependencies.dependency.version' for mysql:mysql-connector-java:jar is missing.@ line 60, column 15@"
   =>L'ancien groupId ( mysql) est remplacé par le nouveau standard officiel com.mysql maintenu par Oracle et présent dan Maven Central.
   =>Et l'ancien artifact (mysql-connector-java) par le nouveau mysql-connector-j
   => La version a été omise car gérée automatiquement par Spring-boot.
3. remplacer javax.persistence par  jakarta.persistence dans les classes.
4. import jakarta.validation.Valid => maven pour la spring-boot-starter-validation
5. dépendance junit dans le fichier pom.xml pour les tests
6. // TODO: Map columns in data tables(BidList,CurvePoint,Rating,Trade en s'inspirant de User)  with corresponding java fields 
   => champs, Constructeurs , getters et setters
7. Conclusion : mvn spring-boot:run => Compilation OK mais l'application ne démarre pas car base de donnée n'est pas opérationelle.

## DEV2
1. Adapter le fichier application.preperties au nouvel nom de la base P7_Poseidon.
2. Ajout de la création P7_Poseidon de database  le fichier data.sql
3. Suppression de la dépendance H2
4. Passer de update à none : spring.jpa.hibernate.ddl-auto=none => pour ne pas des erreurs : Error executing DDL "alter table trade add column trade_id integer not null auto_increment" 
=> http://localhost:8080/login accessible avec login "user"

Page de connexion par défaut  
<img width="172" height="135" alt="image" src="https://github.com/user-attachments/assets/97f5cf6e-6b7f-43b8-a538-1d93cd8832d0" />

Et on arrive à la page : http://localhost:8080/?continue  
<img width="270" height="96" alt="image" src="https://github.com/user-attachments/assets/17fe7593-96d8-4526-a853-92bf410dc542" />

Et si on clique sur le lien "User management" http://localhost:8080/user/list   
<img width="460" height="122" alt="image" src="https://github.com/user-attachments/assets/d8fdc424-c3df-4269-b5fb-7563691366a1" />

# dev3
Réfactorer les deux classes modèles BitList et Trade sur les colonnes en bid_list_id et trade_id.

# dev3T
Ajout login perso :
1. Ajout fichier SecurityConfig pour autoriser l'accès à certains fichiers.
2. Ajout fichier login.html et login.css





