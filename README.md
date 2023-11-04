# Medilabo project

![Logo](client-ui/src/main/resources/static/images/medilaboLogo.png)


Application that allow screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

It is also a tool to see or manage patient informations and see or add notes for practionner.


Diabete risk level scale -> None / Borderline / In Danger / Early onset


Available for all platforms (PC, tablets, phones)


-------------------------------------------------------------------------------------------------------------------------------------

## Project Structure :

This project is compose of 6 microservice: 

-> Front-end
     - client-ui (Web interface)

-> Edge microservices:
     - eureka-server (to register other microservices)
     - gateway-server (to dispatch, filter requests from the view, and also manage security of application)

-> Back-end microservices:
     - patient-ms (to manage data about patients)
     - doctor-note-ms (to manage data about notes from doctor concerning a patient)
     - assessment-ms (to get risk of diabetes for a patient)
  

-------------------------------------------------------------------------------------------------------------------------------------

## Architectural Model :

![Architectural Model](client-ui/src/main/resources/static/images/schema.png)


Request from view pass through gateway and microservices are communicating each other by Feign

-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

### Prerequisites :
- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- MySQL 8.0
- MongoDB
- Docker 


### Running with Docker :

-> Docker needs to be install (link for installation -> https://docs.docker.com/compose/install/) 

-> Clone this project from Github on your local machine

-> For each of 6 microservices, you have to run the command "mvn clean install" at the root of the application in order to create .jar (all microservices need to be launch while doing mvn clean install in order to build correctly, especially for tests)

-> Then you can run "docker-compose build" at the root of medilabo project and then "docker-compose up -d"

-> Please wait until that all containers are started and finish running (databases are also dockerize)

-> You can see logs with command : "docker logs -f<name of microservice you want to see>", to be sure that everything is well started (can take a few minutes because some of them require healthcheck to make an order)

-> Then go to "http://localhost:8083/client" to get view of application
(security authentication is already made by a FeignConfig class with username "user" and password "password")

-> To stop application you can run command : docker-compose down -v


### Or else :

-> Clone this project from Github on your local machine

-> Create a MySQL server on your local machine with a user (username=pierredb + password=a627a158-1cd5-4ab1-a439-0d4873785246) and give all credentials

-> Then run sql script located in patient-ms (patient-ms/src/main/resources/data.sql) to create database

-> Create a MongoDB database "doctornotes" with a collection "doctor_notes" and import data from doctornotes.doctor_notes.json (located in doctor-note-ms/src/main/resources/doctornotes.doctor_notes.json)

-> Then run microservice in this order, from your IDE or the root services by running "mvn spring-boot:run" :
-eureka-server
-gateway-server
-patient-ms
-doctor-note-ms
-diabetes-assessment-ms
-client-ui

-> Then go to "http://localhost:8083/client" to get view of application
(security authentication is already made by a FeignConfig class with username "user" and password "password")


-------------------------------------------------------------------------------------------------------------------------------------

## Coverage of Medilabo microservices:

Tests can de run with maven for each microservice

- patient-ms :

![Coverage1](patient-ms/src/main/resources/static/patientCoverage.png)

- doctor-note-ms :

![Coverage2](doctor-note-ms/src/main/resources/static/doctor-noteCoverage.png)

- diabetes-assessment-ms :

![Coverage3](diabetes-assessment-ms/src/main/resources/static/diabetes-assessmentCoverage.png)

- client-ui :

![Coverage4](client-ui/src/main/resources/static/images/client-uiCoverage.png)


-------------------------------------------------------------------------------------------------------------------------------------

## API documentation with Swagger:

- In order too see and test endpoints easily, while application in running on server, go to : 

- "http://localhost:8080/swagger-ui.html" for patient-ms
- "http://localhost:8081/swagger-ui.html" for doctor-note-ms
- "http://localhost:8082/swagger-ui.html" for diabetes-assessment-ms


-------------------------------------------------------------------------------------------------------------------------------------

## Green Code Recommendations:

-
-
-
-
-
-
-
-
-


