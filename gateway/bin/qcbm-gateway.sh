#!/bin/bash

#======================================================================
# 项目启动 shell 脚本
# author: guance
# date: 2021-03-16
#======================================================================
APP_NAME=gateway

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