FROM openjdk:17.0.2-jdk-slim-buster
COPY build/libs/user-service.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]