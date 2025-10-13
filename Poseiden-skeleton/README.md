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
1. Génération d'un mot de passe GenBcrypt : "$2a$10$iDGSGHH4FVlLOQkWghUnzu3T.Lqih1BgEhpLw0wcI7gpmpc7QCDZ."
2. Ajout un utilisateur 'dupond' dans la base
3. Ajoute la classe service CustomUserDetailsService

# dev4
1. User.java => DBUser.java  : Conflit avec User natif de Spring ( OC)
2. UserRepository.java => DBUserRepository.java
3. Modifier la classe LoginController pour tester la gestion des roles ADMIN et USER.
4. Tests Connexion avec le user dupond et la page /admin est lui est interdit :  
   <img width="241" height="144" alt="image" src="https://github.com/user-attachments/assets/559029be-1668-496f-b666-01fc4a1c6282" />
   <img width="230" height="57" alt="image" src="https://github.com/user-attachments/assets/4781e914-790d-4fa4-904d-d17b092c3f9c" />
   <img width="234" height="99" alt="image" src="https://github.com/user-attachments/assets/f5b136d1-2346-48e1-9173-c50a933ec28b" />

# solution1
1. Mettre en place la page login de la marquette.
2. Mettre en place les deux pages home_user.html et home_admin.html pour tester les roles ADMIN et USER.
   
    -Login avec l'utilisateur USER dupond:  
    http://localhost:8080/                  --------------------------->               http://localhost:8080/user/home_user  
    <img width="341" height="232" alt="image" src="https://github.com/user-attachments/assets/a48890ea-ae40-44f1-b4fc-79087a6997a2" />
    <img width="205" height="135" alt="image" src="https://github.com/user-attachments/assets/b5ea2481-3ce3-47bb-9973-62619d009e0b" />
   
    -Login avec l'utilisateur ADMIN admin :  
    http://localhost:8080/                  --------------------------->               http://localhost:8080/user/home_admin  
    <img width="312" height="227" alt="image" src="https://github.com/user-attachments/assets/730f631b-239b-4d95-bccb-f16f0aea1554" />
    <img width="227" height="73" alt="image" src="https://github.com/user-attachments/assets/fdec5cf0-3b26-4a4b-a1ae-9ecb26b824e8" />

# solution2
1. Mettre en place la page login /user/list et que seul le user avec le role ADMIN pourrait y accéder.
2. Implémenter la classe service UserService pour les fonctions de la page /user/list (Add New, Edit, Delete) 

# solution3
1. Implémentation des fonctionnalités de /bidlist

# solution4
1. Implémentation des fonctionnalités de /trade

# solution5
1. Implémentation des fonctionnalités de /rating

# solution6
1. Implémentation des fonctionnalités de /curvePoint

# solution7
1. Implémentation des fonctionnalités de /ruleName

# solution8
1. Ajustement des droits d'autorisation pour  les utilisateurs de type USER

# solution9  
1. Intégration le logo "poseidon_logo.png" dans chaque page 

# solution10
1. Modifier la page login pour qu'elle se rapproche de celle de la marquette.

# solution11 : javadoc
1. Ajout le plugin JavaDoc
2. DBUser,DBUserRepository,UserController et UserService
3. Pour générer : mvn javadoc:javadoc
   ou javadoc -d docs -sourcepath src/main/java -subpackages com.nnk.springboot
4. Emplacement de JavaDoc : target/site/apidocs/index.html

# solution12
1. Réfactorer les pages html pour qu'elles ressemblent à celles des marquettes.

# solution13
1. Remplacer Junit4 par Junit5
2. Ajouter le plugin JaCoco
3. Adapter les classes de Tests existants à Junit5
4. Test Couverture de code JaCoCo = 22%

# solution14
1. Implémentation des classes de Test Controlleur. 
2. Implémentation des classes de Test Service.
3. Test Couverture de code JaCoCo = 51%

# solution15
1. Nettoyage code : trade.java,HomeController,
2. Ajout des tests pour les classes Controlleur.








   








