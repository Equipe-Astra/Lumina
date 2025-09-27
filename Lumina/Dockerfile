# Stage 1: Build com Maven
FROM maven:3.9.1-eclipse-temurin-11 AS build

COPY . /app
WORKDIR /app

RUN mvn clean package -DskipTests

# Stage 2: Payara Micro
FROM eclipse-temurin:11-jre

WORKDIR /opt/app

# Baixa Payara Micro
RUN curl -L -o payara-micro.jar https://search.maven.org/remotecontent?filepath=fish/payara/extras/payara-micro/6.2023.9/payara-micro-6.2023.9.jar

# Copia WAR do build
COPY --from=build /app/target/*.war ./Lumina.war

# Copia driver Oracle
COPY docker/wildfly/modules/com/oracle/ojdbc/main/ojdbc11.jar ./ojdbc11.jar

# Variáveis de conexão Oracle
ENV ORACLE_URL=jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL
ENV ORACLE_USER=rm552352
ENV ORACLE_PASSWORD=080103

# Porta padrão Payara Micro
EXPOSE 8080

# Inicializa Payara Micro com datasource Oracle
CMD sh -c 'java -jar payara-micro.jar --deploy Lumina.war --jvmOptions "-Djdbc.LuminaDS=javax.sql.DataSource" --property "jdbc/LuminaDS=$ORACLE_URL,$ORACLE_USER,$ORACLE_PASSWORD" --port 8080'
