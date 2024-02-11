FROM maven:3.9.6-openjdk-20 AS build
COPY . .
RUN mvn clean package -OskipTests

FROM openjdk:20
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} food_orders-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/food_orders-0.0.1-SNAPSHOT.jar"]

