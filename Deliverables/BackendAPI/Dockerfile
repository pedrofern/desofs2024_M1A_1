# Use uma imagem base do Maven para construir a aplicação
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app

# Copie o arquivo pom.xml e baixe as dependências
COPY pom.xml .
RUN mvn dependency:go-offline

# Copie o código fonte e compile a aplicação
COPY src ./src
RUN mvn package -DskipTests

# Use uma imagem base do OpenJDK para rodar a aplicação
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copie o JAR construído do estágio de build anterior
COPY --from=build /app/target/*.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
