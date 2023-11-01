
#Source image with :
FROM eclipse-temurin:17-jre-alpine

#Create and define working directory
WORKDIR /app

# Copy the jar from local machine into image to build
COPY target/*.jar diabetes-assessment-ms-0.0.1-SNAPSHOT.jar

#Port
EXPOSE 8082

# Define command to run java application
CMD ["java", "-jar", "diabetes-assessment-ms-0.0.1-SNAPSHOT.jar"]