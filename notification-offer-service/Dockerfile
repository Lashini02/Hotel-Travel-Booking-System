FROM eclipse-temurin:17-jre
WORKDIR /app
COPY target/notification-offer-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
