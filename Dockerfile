# build app Maven image
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# build war
COPY . .
RUN mvn clean package -DskipTests

# build app from jdk
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# copy jar from previous stage
COPY --from=build /app/target/*.war app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.war"]
