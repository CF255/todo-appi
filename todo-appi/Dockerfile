FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/todo-appi-0.0.1.jar
COPY ${JAR_FILE} app_TodoApi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_TodoApi.jar"]