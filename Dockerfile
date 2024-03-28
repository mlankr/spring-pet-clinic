# Build
FROM maven:3.8.2-openjdk-17-slim AS build
LABEL authors="Milan"
WORKDIR /petclinicapp

# multi-module project
COPY pom.xml /petclinicapp/
COPY pet-clinic-data /petclinicapp/pet-clinic-data/
COPY pet-clinic-web /petclinicapp/pet-clinic-web/

RUN mvn clean package -DskipTests

# Package stage
FROM openjdk:17-jdk-slim
WORKDIR /petclinicapp
COPY --from=build /petclinicapp/pet-clinic-web/target/pet-clinic-web.jar pet-clinic-web.jar
VOLUME /app
ENTRYPOINT ["java","-jar","pet-clinic-web.jar"]
EXPOSE 8080
