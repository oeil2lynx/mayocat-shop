#!/bin/sh

DEBUG=""
DEBUG_PORT=5005

if [ "$1" == "debug" ]
then
  DEBUG="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=$DEBUG_PORT"
fi

export JAVA_OPTS="-server -Xms128m -Xmx512m -XX:MaxPermSize=192m -Dfile.encoding=utf-8 -Djava.awt.headless=true -XX:+UseParallelGC -XX:MaxGCPauseMillis=100"

java $JAVA_OPTS \
  $DEBUG \
  -classpath "../client/src/main/resources/assets/:../themes/src/main/resources/:./target/mayocat-shop-application-0.8-SNAPSHOT.jar" \
  org.mayocat.shop.application.MayocatShopService \
  server \
  mayocat.yml

