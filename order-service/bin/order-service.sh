#!/bin/bash

APP_NAME=order-service

APP_JAR="${APP_NAME}.jar"

TRACE_OPTIONS=""
if [[ $1 == '--trace' ]]; then
  TRACE_OPTIONS="-javaagent:/app/skywalking/skywalking-agent.jar"
fi

JVM_OPTIONS="-XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/app/dumps/"

echo
echo "start command:"
echo "java ${JVM_OPTIONS} ${TRACE_OPTIONS} -jar ${APP_JAR}"
echo

java ${JVM_OPTIONS} ${TRACE_OPTIONS} -jar ${APP_JAR}