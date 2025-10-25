# Use lightweight OpenJDK 17 image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and build files
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Copy the entire project so Gradle can resolve dependencies
COPY . .

# Build only fleet-service
RUN ./gradlew :fleet-service:build -x test --no-daemon

# Expose fleet-service port
EXPOSE 8082

# Run the Spring Boot jar
ENTRYPOINT ["java","-jar","/app/fleet-service/build/libs/fleet-service-0.0.1-SNAPSHOT.jar"]
