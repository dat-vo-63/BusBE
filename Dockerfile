#
#
#
##
## Build stage
##
## syntax = docker/dockerfile:1
##
## Package stage
##
#FROM openjdk:oraclelinux8
#WORKDIR /app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
##EXPOSE 8080
##ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]
#RUN ./mvnw dependency:go-offline
#COPY src ./src
#CMD ["./mvnw","spring-boot:run"]
FROM maven:3.8.5-openjdk-17-slim AS build
COPY . .
RUN     mvn clean verify

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/spring-docker.jar spring-docker.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","spring-docker.jar"]
#FROM openjdk:12
#ADD target/*.jar  spring-boot-docker.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "spring-boot-docker.jar"]