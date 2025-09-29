# Base Tomcat 10 com JDK 11
FROM tomcat:9.0-jdk11-openjdk-slim

# Limpar apps padrão
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar WAR
COPY target/Lumina.war /usr/local/tomcat/webapps/Lumina.war

# Copiar driver Oracle
COPY docker/tomcat/modules/com/oracle/ojdbc/main/ojdbc11.jar /usr/local/tomcat/lib/ojdbc11.jar

# Copiar entrypoint
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Definir limites de memória Java
ENV JAVA_OPTS="-Xms128m -Xmx300m"

# Rodar Tomcat
ENTRYPOINT ["/entrypoint.sh"]

