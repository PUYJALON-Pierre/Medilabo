
# Client-UI (Front-end MicroService for Medilabo application):

Web interface develop in Java.

Use Thymeleaf to expose view wih data retrieving from other services, communicating by Feign.

This microservice is part of Medilabo project which allows screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Boostrap
- Maven dependencies : (Lombok / Spring Web / Jacoco / Actuator / Eureka / SpringCloud / OpenFeign / ThymeLeaf...)

- Server port 8083 (http://localhost:8083)

-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

- Install Maven
- Install Java

-> Copy project from Github on your local machine

-> To launch application, be sure that all those microservices from Medilabo project are running :
     - eureka-server (to register microservice)
     - gateway-server (to call microservices)
     - patient-ms (to get data about patients)
     - doctor-note-ms (to get data about notes from doctor concerning a patient)
     - assessment-ms (to get risk of diabete of a patient)

-> Then you can run client-ui project from your IDE or by using "mvn spring-boot:run" at the root of microservice

-> Then go to "http://localhost:8083/client" in order to get view generating by micro-services trough the gateway-server (security authentication is already made by FeignConfig class with username "user" and password "password")

-> Tests can be run with Maven

-------------------------------------------------------------------------------------------------------------------------------------

## Coverage:

![Coverage](src/main/resources/static/images/client-uiCoverage.png)