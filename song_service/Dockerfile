FROM maven:3.6-openjdk-17-slim AS build
WORKDIR /app1
COPY . .

RUN mvn clean package
FROM openjdk:17-jdk-alpine
WORKDIR /app1
COPY --from=build /app1/target/song_service-0.0.1-SNAPSHOT.jar /usr/local/lib/song_service.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/song_service.jar"]
