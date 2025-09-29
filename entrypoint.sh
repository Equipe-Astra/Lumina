#!/bin/bash

# Substitui a porta apenas se a variável $PORT estiver definida
if [ -n "$PORT" ]; then
  echo ">> Alterando porta do Tomcat para $PORT..."
  sed -i "s/port=\"8080\"/port=\"$PORT\"/" /usr/local/tomcat/conf/server.xml
fi

# Executa o Tomcat
exec catalina.sh run "$JAVA_OPTS"