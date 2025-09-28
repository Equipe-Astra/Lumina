# Base Tomcat 10 com JDK 11
FROM tomcat:9.0-jdk11 

# Limpar apps padrão
RUN rm -rf /usr/local/tomcat/webapps/*

# Copiar WAR
COPY target/Lumina.war /usr/local/tomcat/webapps/Lumina.war

# Copiar driver Oracle
COPY docker/tomcat/modules/com/oracle/ojdbc/main/ojdbc11.jar /usr/local/tomcat/lib/ojdbc11.jar

# Porta
EXPOSE 8080

# Rodar Tomcat
CMD ["catalina.sh", "run"]