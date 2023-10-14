
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
- Maven dependencies : (Spring Cloud Gateway / Spring Webflux / Spring (Cloud?) Security /Actuator / Eureka / SpringCloud & SpringCloudConfig)

- Properties files located on distant repository with : https://github.com/PUYJALON-Pierre/Medilabo-Config-Server-Repo

- Server port 9103 (http://localhost:9103)


-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

- Install Maven
- Install Java

-> Copy project from Github on your local machine

-> Go to the root of the application and execute mvn spring-boot:run

-> When the server is running, you can login as user:"user" and password:"password" while accessing different endpoints (be sure that all microservices from Medilabo project are running)





