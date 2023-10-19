
# DoctorNote Microservice :

Rest API develop in Java, to manage CRUD operations on doctor's notes about specific patients


This microservice is part of Medilabo project which allow screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Maven dependencies : (Lombok / Spring Web / Spring Data MongoDB / Jacoco / Actuator / Eureka / SpringCloud & SpringCloudConfig / Swagger)

- Json script for inserting database informations in : src/main/resources/doctornotes.doctor_notes.json

- Properties files located on distant repository with : https://github.com/PUYJALON-Pierre/Medilabo-Config-Server-Repo

- Server port 8081 (http://localhost:8081)


-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

- Install Maven
- Install Java
- Install MongoDB

-> Copy project from Github on your local machine

-> Create a MongoDB connection at "mongodb://localhost:27017" and create a database doctornotes with a collection doctor_notes in it

-> Import doctornotes.doctor_notes.json in order to insert informations in database

-> Go to the root of the application and execute mvn spring-boot:run

(Eureka and Config server present in global project must be running in order to fetch properties on distant repository)

-> When the server is running, you can use different endpoints

-> Tests can be run with Maven


-------------------------------------------------------------------------------------------------------------------------------------


## CRUD Endpoints  :

- __GET__  http://localhost:8081/doctorNote/patient/{patientId} (get all doctor notes for patient by their patientId)

- __GET__  http://localhost:8081/doctorNote/note/{noteId} (get a doctor note by her noteId)

- __POST__  http://localhost:8081/doctorNote (add a doctor note)

- __PUT__  http://localhost:8081/doctorNote (update a doctor note)

- __DELETE__  http://localhost:8081/doctorNote/{noteId} (delete a doctor note by her id)


-------------------------------------------------------------------------------------------------------------------------------------

## API documentation with Swagger:

-While application in running on server, go to : "http://localhost:8081/swagger-ui.html" in order to see and test endpoints easily.

-------------------------------------------------------------------------------------------------------------------------------------

## JSON Body :

 *-> (DoctorNote)*

{
    "noteId": "6531860eed04401a2d5ac0aa",
    "patientId": 5,
    "noteContent": "Test",
    "date": "2023-10-19"
}

-------------------------------------------------------------------------------------------------------------------------------------



