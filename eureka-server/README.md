
# Eureka Server :

Application develop in Java, that let microservices registring into Eureka server in order to hold their informations.

This allow to locate instances of microservices and make them communicate, without themselves directly knowing eachother.


This microservice is part of Medilabo project which allow screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Maven dependencies : (Actuator / Eureka / SpringCloud & SpringCloudConfig )

- Properties files located on distant repository with : https://github.com/PUYJALON-Pierre/Medilabo-Config-Server-Repo

- Server port 9102 (http://localhost:9102)


-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

- Install Maven
- Install Java

-> Copy project from Github on your local machine

-> Go to the root of the application and execute mvn spring-boot:run

-> When the server is running, you can run microservices to let them register on Eureka server (microservices need to have eureka-client dependencies in their pom.xml and also the url adrress of the Eureka server in their properties).

-> You can access to http://localhost:9102/ in order to see eureka server inofrmations


-------------------------------------------------------------------------------------------------------------------------------------




