
# Client-UI (Front-end MicroService for Medilabo application):

Web interface develop in Java.

Use of Thymeleaf to expose view wih data retrieving from other services, by communicating with Feign.

This microservice is part of Medilabo project which allow screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Maven dependencies : (Lombok / Spring Web / Spring Data JPA / Jacoco / Actuator / Eureka / SpringCloud & SpringCloudConfig / Swagger)

- Properties files located on distant repository with : https://github.com/PUYJALON-Pierre/Medilabo-Config-Server-Repo

- Server port 8083 (http://localhost:8083)

-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

-> Copy project from Github on your local machine

-> To launch application, be sure that all those microservices from Medilabo project are running :
     - config-server (in order to fetch properties)
     - eureka-server (to register microservice)
     - gateway-server (to call microservices)
     - patient-ms (to get data about patients)
     - doctor-note-ms (to get data about notes from doctor concerning a patient)
     - assessment-ms (to get risk of diabete of a patient)

-> Then you can run client-ui project and go to "" in order to use view generating by this micro-service trough gateway.


-------------------------------------------------------------------------------------------------------------------------------------

## API documentation with Swagger:

-While application in running on server, go to : "http://localhost:8080/swagger-ui.html" in order to see and test endpoints easily.

-------------------------------------------------------------------------------------------------------------------------------------

image des 2 vues :
-
-




