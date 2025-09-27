# Stage 1: Build com Maven
FROM maven:3.9.1-eclipse-temurin-11 AS build

COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Stage 2: WildFly
FROM jboss/wildfly:21.0.0.Final

# Copia WAR
COPY --from=build /app/target/*.war /opt/jboss/wildfly/standalone/deployments/

# Copia módulo Oracle
COPY docker/wildfly/modules/com/oracle/ojdbc/main /opt/jboss/wildfly/modules/com/oracle/ojdbc/main

# Copia standalone.xml customizado
COPY docker/wildfly/configuration/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml

# Ajuste JAVA_OPTS para Render Free (512MB RAM)
ENV JAVA_OPTS="-Xms64m -Xmx256m -XX:MetaspaceSize=64m -XX:MaxMetaspaceSize=128m -Djava.net.preferIPv4Stack=true -Djava.awt.headless=true"

EXPOSE 8080

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
