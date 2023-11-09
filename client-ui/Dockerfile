
#Source image with :
FROM eclipse-temurin:17-jre-alpine

#Create and define working directory
WORKDIR /app

# Create Jar
COPY --chmod=744 mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests
# Copy the jar from local machine into image to build
COPY target/*.jar client-ui-0.0.1-SNAPSHOT.jar

#Port
EXPOSE 8083

# Define command to run java application
CMD ["java", "-jar", "client-ui-0.0.1-SNAPSHOT.jar"]