FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/test-task-0.0.1-SNAPSHOT.jar /app/test-task-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "test-task-0.0.1-SNAPSHOT.jar"]