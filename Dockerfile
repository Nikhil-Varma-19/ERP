# same as we create typescript file
FROM gradle:8.8-jdk21 AS build

WORKDIR /build

# same as copy package.*.json
COPY build.gradle settings.gradle gradlew ./
COPY gradle ./gradle

# same as npm i
RUN ./gradlew dependencies --no-daemon

COPY src ./src

RUN ./gradlew clean build -x test --no-daemon

FROM eclipse-temurin:21-jre

WORKDIR /app

RUN groupadd -r spring && useradd -r -g spring spring

RUN mkdir -p /app/uploads && chown -R spring:spring /app/uploads

COPY --from=build /build/build/libs/*.jar app.jar

RUN chown spring:spring app.jar

USER spring

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
