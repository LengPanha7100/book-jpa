# BUILD STAGE

# Use Gradle with JDK 21 for the build stage
FROM gradle:jdk-21-and-22-jammy AS build

# Set the working directory inside the container
WORKDIR /app

# Copy Gradle wrapper and build scripts
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src ./src

# Make the Gradle wrapper script executable
RUN chmod +x gradlew

# Clean and build the project
RUN ./gradlew clean bootJar

# RUN STAGE

# Use JRE 21 for the final runtime stage
FROM alpine/java:21-jdk

# Copy the executable JAR file from the build stage
COPY --from=build /app/build/libs/*.jar /app/app.jar

# Set the command to run the Spring Boot application
CMD ["java", "-jar", "/app/app.jar"]