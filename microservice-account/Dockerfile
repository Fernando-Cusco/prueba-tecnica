FROM maven:3.9-amazoncorretto-21 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21
EXPOSE 8081
COPY --from=build /target/microservice-account-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]