# --- Build stage ---
FROM eclipse-temurin:17-jdk-jammy AS builder
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN ./mvnw dependency:go-offline -q

COPY src src
RUN ./mvnw package -DskipTests -q

RUN java -Djarmode=layertools -jar target/QuiLAI-0.0.1-SNAPSHOT.jar extract --destination target/extracted

# --- Run stage ---
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app

COPY --from=builder /app/target/extracted/dependencies ./
COPY --from=builder /app/target/extracted/spring-boot-loader ./
COPY --from=builder /app/target/extracted/snapshot-dependencies ./
COPY --from=builder /app/target/extracted/application ./

EXPOSE 8080
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
