# Stage 1: Build the Spring Boot app using Gradle
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Copy everything at once (simplifies and avoids mainClass error)
COPY . .

# Build the application (skip tests to save time)
RUN gradle clean build -x test --no-daemon

# Stage 2: Run the built Spring Boot JAR
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy only the generated JAR from the build stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose application port (update if your app uses another port)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
