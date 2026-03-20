FROM gradle:8-jdk21-alpine AS build
WORKDIR /home/gradle/project

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .

COPY src src

RUN ./gradlew shadowJar -x test

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

EXPOSE 8080

COPY --from=build /home/gradle/project/build/libs/*-all.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]