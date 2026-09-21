# Multi-stage: builds the jar inside Docker so `docker compose up --build`
# works without running ./mvnw package first.
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests package

FROM eclipse-temurin:25-jre-jammy
WORKDIR /app
COPY --from=build /app/target/GestorDeLogistica-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8091
ENTRYPOINT ["java", "-jar", "app.jar"]
