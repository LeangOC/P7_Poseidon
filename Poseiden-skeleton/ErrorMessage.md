## DEV2
Error executing DDL "alter table bidlist add column bid_list_id integer not null auto_increment" via JDBC
Hibernate: alter table bidlist modify column account  varchar(255)
Hibernate: alter table bidlist modify column ask  float(53)


# solution4,5

rating/list.html, 
remplacer ${#httpServletRequest.remoteUser} par ${#authentication.name}

# solution 5 /rating/add
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

# rating/update
Caused by: org.thymeleaf.exceptions.TemplateProcessingException: Error during execution of processor 
'org.thymeleaf.spring6.processor.SpringInputGeneralFieldTagProcessor' (template: "rating/update" - line 42, col 25)
Caused by: org.springframework.beans.NotReadablePropertyException: Invalid property 'order' of bean class 
=> order par orderNumber

# Refuser de supprimer rating
@GetMapping("/rating/delete/{id}") par @PostMapping
=> le service n'a pas le bon méthode : public void deleteById(Integer id) {ratingRepository.deleteById(id);}