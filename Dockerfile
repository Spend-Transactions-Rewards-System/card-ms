FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
VOLUME /tmp
COPY target/card-ms-*.jar app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
EXPOSE 8081