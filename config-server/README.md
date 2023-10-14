
# Spring Cloud Config Service :

Service develop in Java, to access a distant repository on GitHub in order to retrieve externalize properties.


This service is part of Medilabo project which allow screening of type 2 diabetes for patients by using their personnals informations and doctor's notes left during consultations.

Available for all platforms (PC, tablets, phones)

-------------------------------------------------------------------------------------------------------------------------------------

## API Configuration :

- Java 17 
- Maven 3.8.7 
- Spring Boot 3.1.4
- Maven dependencies : (SpringCloudConfig)

- Properties files located on distant repository with : https://github.com/PUYJALON-Pierre/Medilabo-Config-Server-Repo

- Server port 9101 (http://localhost:9101)

-------------------------------------------------------------------------------------------------------------------------------------

## Getting Started :

- Install Maven
- Install Java

-> Copy project from Github on your local machine

-> Go to the root of the application and execute mvn spring-boot:run

-> When the server is running, you can run other application that need their properties to be fetch from the distant repository : https://github.com/PUYJALON-Pierre/Medilabo-Config-Server-Repo


-------------------------------------------------------------------------------------------------------------------------------------
