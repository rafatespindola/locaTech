## Build stage
FROM eclipse-temurin:21-jdk AS build

WORKDIR /workspace

# Copia só o necessário para cache de dependências
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw -q -DskipTests dependency:go-offline

# Agora copia o restante do código e compila
COPY src/ src/
RUN ./mvnw -q -DskipTests package

## Runtime stage
FROM eclipse-temurin:21-jre

WORKDIR /app

# Porta padrão do Spring Boot
EXPOSE 8080

# Copia o JAR gerado (assume apenas 1 jar "final" em target/)
COPY --from=build /workspace/target/*.jar /app/app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]
