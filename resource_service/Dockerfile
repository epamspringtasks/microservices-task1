FROM maven:3.8.5-openjdk-17

WORKDIR /resource_service
COPY . .
RUN mvn clean package
ARG JAR_FILE=target/resource_service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} resource_service.jar
ENTRYPOINT ["java", "-jar", "resource_service.jar"]
