# Stage 1: Build the JAR binary file using JDK 21
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build
WORKDIR /app
COPY pom.xml .
# Cache your project dependencies cleanly
RUN mvc_dep_cache=1 mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the high-performance runtime image
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
# Copy the compiled executable binary from Stage 1
COPY --from=build /app/target/project-0.0.1-SNAPSHOT.jar app.jar
# Copy your Firebase key securely into the runner environment
COPY src/main/resources/firebase-service-account.json src/main/resources/firebase-service-account.json

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]