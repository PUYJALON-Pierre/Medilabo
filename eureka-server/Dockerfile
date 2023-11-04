
#Source image with :
FROM eclipse-temurin:17-jre-alpine

#Create and define working directory
WORKDIR /app

HEALTHCHECK --interval=10s --retries=10 --start-period=5s --timeout=10s CMD wget --no-verbose --tries=1 --spider localhost:9102 || exit 1

# Copy the jar from local machine into image to build
COPY target/*.jar eureka-server-0.0.1-SNAPSHOT.jar

#Port
EXPOSE 9102

# Define command to run java application
CMD ["java", "-jar", "eureka-server-0.0.1-SNAPSHOT.jar"]