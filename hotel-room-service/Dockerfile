# Option 1: Pre-built Jar / Standard Docker Runtime Stage
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy built JAR artifact from local target directory or build stage
COPY target/hotel-room-service-1.0.0.jar app.jar

EXPOSE 8082
ENTRYPOINT ["java", "-jar", "app.jar"]
