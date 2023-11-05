
# Patient Microservice :

Rest API develop in Java, to manage CRUD operations on patients informations


This microservice is part of Médilabo project which allows screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Maven dependencies : (Lombok / Spring Web / Spring Data JPA / Spring Security / MySQL Driver / Jacoco / Actuator / Eureka / SpringCloud / Swagger)

- SQL script for creating database in : src/main/resources/data.sql

- Server port 8080 (http://localhost:8080)

-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

- Install Maven
- Install Java
- Install My SQL

-> Copy project from Github on your local machine

-> Create a MySQL server on your local machine with a user (username = pierredb + password = a627a158-1cd5-4ab1-a439-0d4873785246) and give all credentials

-> Execute data.sql in order to create database with informations 

-> Go to the root of the application and execute mvn spring-boot:run

-> When the server is running, you can use different endpoints from http://localhost:8080

-> Tests can be run with Maven


-------------------------------------------------------------------------------------------------------------------------------------

## JSON Body :

 *-> (Patient)*

{
    "id": 1,
    "firstName": "Test",
    "lastName": "TestNone",
    "birthdate": "1966-12-31",
    "gender": "F",
    "postalAddress": "1 Brookside St",
    "phoneNumber": "100-222-3333"
}

-------------------------------------------------------------------------------------------------------------------------------------

## CRUD Endpoints  :

- __GET__  http://localhost:8080/patient/allPatients (get all patients)

- __GET__  http://localhost:8080/patient/{id} (get patient by his id)

- __GET__  http://localhost:8080/patient/firstname/{firstname} (get patient by his firstName)

- __GET__  http://localhost:8080/patient/lastname/{lastname} (get patient by his lastName)

- __POST__  http://localhost:8080/patient (add a patient)

- __PUT__  http://localhost:8080/patient (update a patient)

- __DELETE__  http://localhost:8080/patient/{id} (delete a patient by his id)


-------------------------------------------------------------------------------------------------------------------------------------

## API documentation with Swagger:

-While application in running on server, go to : "http://localhost:8080/swagger-ui.html" in order to see and test endpoints easily.

-You can use as paramater for necessary endpoints: 
    - "1" for id, 
    - "Test" for firstName, 
    - "TestNone" for lastName, 
    - Example of body for adding and update ->       {
                                                    "id": 5,
                                                    "firstName": "Test5 ",
                                                    "lastName": "TestNone5",
                                                    "birthdate": "1916-12-11",
                                                    "gender": "F",
                                                    "postalAddress": "1 Paris St",
                                                    "phoneNumber": "140-224-3313"
                                                }


-------------------------------------------------------------------------------------------------------------------------------------

## Coverage:

![Coverage](src/main/resources/static/patientCoverage.png)

