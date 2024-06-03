FROM maven:3.6-openjdk-17-slim AS build
WORKDIR /app
COPY . .

RUN mvn clean package
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/resource_service-0.0.1-SNAPSHOT.jar /usr/local/lib/resource_service.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/resource_service.jar"]
