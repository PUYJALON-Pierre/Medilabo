
# Diabete Assessment Microservice :

Rest API develop in Java, to make diabetes assessment for patient by his id, base on his data which are retrieve from 2 other microservices (patient-ms and doctor-note-ms)

Diabetes risk level scale -> None / Borderline / In Danger / Early onset

This microservice is part of Medilabo project which allows screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Maven dependencies : (Lombok / Spring Web / Jacoco / Actuator / Eureka / SpringCloud / Swagger / OpenFeign)

- Server port 8082 (http://localhost:8082)


-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

- Install Maven
- Install Java

-> Copy project from Github on your local machine

-> To launch application, be sure that all those microservices from Medilabo project are running :
     - eureka-server (to register microservice)
     - patient-ms (to get data about patients)
     - doctor-note-ms (to get data about notes from doctor concerning a patient)

-> Go to the root of the application and execute mvn spring-boot:run

-> When the server is running, you can use the assessment endpoint below

-> Tests can be run with Maven


-------------------------------------------------------------------------------------------------------------------------------------


## Endpoint :

- __GET__  http://localhost:8082/diabetesAssessment/{id} (get diabetes risk level of a patient by his id)

-------------------------------------------------------------------------------------------------------------------------------------

## API documentation with Swagger:

-While application in running on server, go to : "http://localhost:8082/swagger-ui.html" in order to see and test endpoints easily.

-------------------------------------------------------------------------------------------------------------------------------------


## Coverage:

![Coverage](src/main/resources/static/diabetes-assessmentCoverage.png)

