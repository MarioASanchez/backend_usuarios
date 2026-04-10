FROM maven:3.9.11-eclipse-temurin-21 AS builder
WORKDIR /app

COPY pom.xml ./
COPY .mvn .mvn
COPY mvnw ./
COPY mvnw.cmd ./
COPY src src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre
WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

ENV JAVA_OPTS=""

CMD ["sh", "-c", "java $JAVA_OPTS -Dserver.port=${PORT:-8080} -jar /app/app.jar"]
