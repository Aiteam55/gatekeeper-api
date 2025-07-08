# Use an official Eclipse Temurin Java 21 JDK image
FROM eclipse-temurin:21-jdk-alpine
LABEL authors="Mitia"

# Set working directory
WORKDIR /app

# Copy the built JAR into the container
COPY target/*.jar gatekeeper-api.jar

# Expose the port your Spring Boot app listens on
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "gatekeeper-api.jar"]
