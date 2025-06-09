# Stage 1: Build the project using Gradle
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Copy only necessary files for dependency resolution first
COPY build.gradle settings.gradle gradle.properties* /app/
COPY gradle /app/gradle

# Download dependencies (will be cached unless build.gradle changes)
RUN gradle build -x test --no-daemon || return 0

# Now copy the rest of the source code
COPY . /app

# Build the application (skip tests to speed it up)
RUN gradle clean build -x test --no-daemon

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app

# Copy built jar from builder
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port (adjust according to your application)
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
