FROM eclipse-temurin:17-jdk-focal as build
ARG LOG_LEVEL
ENV LOG_LEVEL ${LOG_LEVEL}
RUN echo "DOCKER FILE ENV LOG LEVEL: $LOG_LEVEL"
WORKDIR /usr/src/app
COPY . /usr/src/app
RUN ./mvnw package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /usr/app
COPY --from=build /usr/src/app/target/card-ms-*.jar app.jar
EXPOSE 8081

ENTRYPOINT ["java","-jar","app.jar"]
