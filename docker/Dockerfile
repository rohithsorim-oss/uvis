FROM maven:3.9.9-eclipse-temurin-21 AS builder
RUN mkdir /app
COPY app /app
WORKDIR /app
RUN @MVN_GOAL@


FROM eclipse-temurin:21-jdk-jammy
RUN mkdir /app
WORKDIR /app
COPY --from=builder /app/target/uvis-*-SNAPSHOT.jar /app/uvis.jar
EXPOSE @DOCKER_APP_PORT@
WORKDIR /app
ENTRYPOINT java -jar uvis.jar
