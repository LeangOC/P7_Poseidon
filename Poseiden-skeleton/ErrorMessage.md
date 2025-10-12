## DEV2
Error executing DDL "alter table bidlist add column bid_list_id integer not null auto_increment" via JDBC
Hibernate: alter table bidlist modify column account  varchar(255)
Hibernate: alter table bidlist modify column ask  float(53)


# /trade/update
Invalid property 'id' of bean class => 'id' par tradeId


# rating/list.html, ( pour tous les list.html)
remplacer ${#httpServletRequest.remoteUser} par ${#authentication.name}

# /rating/add
Caused by: org.attoparser.ParseException: Error during execution of processor 
'org.thymeleaf.spring6.processor.SpringInputGeneralFieldTagProcessor' 
(template: "rating/add" - line 42, col 25) : 

<input type="text" th:field="*{order}" id="order" placeholder="FitchRating" class="col-4">

Invalid property 'order' of bean
=> order par orderNumber

# /rating/list
Caused by: org.attoparser.ParseException: Exception evaluating SpringEL expression:
"rating.order" (template: "rating/list" - line 46, col 10)
<td th:text="${rating.order}"></td> => rating.orderNumber

# /rating/update
Caused by: org.thymeleaf.exceptions.TemplateProcessingException: Error during execution of processor 
'org.thymeleaf.spring6.processor.SpringInputGeneralFieldTagProcessor' (template: "rating/update" - line 42, col 25)
Caused by: org.springframework.beans.NotReadablePropertyException: Invalid property 'order' of bean class 
=> order par orderNumber

# Refuser de supprimer rating
@GetMapping("/rating/delete/{id}") par @PostMapping
=> le service n'a pas le bon méthode : public void deleteById(Integer id) {ratingRepository.deleteById(id);}

# /ruleName/add, 
Invalid property 'sql' of bean class
<input type="text" th:field="*{sqlStr}" id="sql" placeholder="sqlStr" class="col-4"> => à remplacer par sqlStr
# /ruleName/list ( pour Edit)
<td th:text="${ruleName.sql}"></td> => <td th:text="${ruleName.sqlStr}"></td>

# /ruleName/update
<td th:text="${ruleName.sql}"></td> => <td th:text="${ruleName.sqlStr}"></td>

# classes Controller Test 
L'annotation @WithMockUser n'est pas reconnu : ajouter la dépendance
<artifactId>spring-security-test</artifactId> 
et suppression les numéros de version pour laisser au parent Spring Boot 3.1.0 injecte les bonnes versions
Commande permettant de savoir si la dépendance a été installée : 
$ mvn dependency:tree | grep "spring-security-test"
[INFO] +- org.springframework.security:spring-security-test:jar:6.1.0:test