FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} demoApp.jar
ENTRYPOINT ["java", "-jar", "/demoApp.jar"]