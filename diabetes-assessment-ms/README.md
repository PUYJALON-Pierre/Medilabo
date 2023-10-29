
# Diabete Assessment Microservice :

Rest API develop in Java, to make diabetes assessment for patient by his id, base on his data which are retrieve from 2 other microservices (patient-ms and doctor-note-ms)

Diabete risk level scale -> None / Borderline / In Danger / Early onset


This microservice is part of Medilabo project which allow screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Maven dependencies : (Lombok / Spring Web / Jacoco / Actuator / Eureka / SpringCloud & SpringCloudConfig / Swagger)

- Properties files located on distant repository with : https://github.com/PUYJALON-Pierre/Medilabo-Config-Server-Repo

- Server port 8082 (http://localhost:8082)


-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

- Install Maven
- Install Java

-> Copy project from Github on your local machine

-> Go to the root of the application and execute mvn spring-boot:run

(Eureka and Config server present in global project must be running in order to fetch properties on distant repository)

-> You also need to run doctor-note-ms and patient-ms in order to retrieve patient data

-> When the server is running, you can use the assessment endpoint

-> Tests can be run with Maven


-------------------------------------------------------------------------------------------------------------------------------------


## Endpoint :

- __GET__  http://localhost:8082/diabetesAssessment/{id} (get diabete risk level of a patient by his id)

-------------------------------------------------------------------------------------------------------------------------------------

## API documentation with Swagger:

-While application in running on server, go to : "http://localhost:8082/swagger-ui.html" in order to see and test endpoints easily.

-------------------------------------------------------------------------------------------------------------------------------------




