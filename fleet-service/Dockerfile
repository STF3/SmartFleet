FROM openjdk:17-jdk-slim
WORKDIR /app

COPY gradlew settings.gradle build.gradle /app/
COPY gradle /app/gradle
COPY . /app

RUN chmod +x gradlew && ./gradlew :fleet-service:build -x test --no-daemon

EXPOSE 8082
ENTRYPOINT ["java","-jar","/app/fleet-service/build/libs/fleet-service-0.0.1-SNAPSHOT.jar"]
