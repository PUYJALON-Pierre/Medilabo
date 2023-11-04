
# Spring Cloud API Gateway :

API Gateway develop in Java, that serve as entry point of Medilabo application. It allow to dispatch, modify and filter requests.

Also, it manage security of the application


This service is part of Medilabo project which allow screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Maven dependencies : (Spring Cloud Gateway / Spring Webflux / Spring Security / Actuator / Eureka)

- Server port 9103 (http://localhost:9103)

- To pass through security gateway, you can use as user:"user" and password:"password" while accessing different endpoints (be sure that eureka-server and all microservices from Medilabo project are running)

- BCRYPT to encode password in database






