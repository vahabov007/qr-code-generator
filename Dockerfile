FROM maven:3.9.6-sapmachine-17 AS build

WORKDIR /app


COPY pom.xml .
COPY src ./src


RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]