#!/bin/bash
# Copyright 2016-现在 LingKe, Co., Ltd.

if [ -z "$JAVA_HOME" ]; then
  echo "Please set the JAVA_HOME variable in your environment"
  exit 1
fi
export JAVA="${JAVA_HOME}/bin/java"
export SERVER="course"
export BASE_DIR=$(cd $(dirname "$0")/..; pwd)
export CONFIG_LOCATION=file:${BASE_DIR}/conf/

# JVM Configuration
JAVA_OPT="${JAVA_OPT} -Xms256m -Xmx256m"
JAVA_OPT="${JAVA_OPT} -jar ${BASE_DIR}/target/${SERVER}.jar"
JAVA_OPT="${JAVA_OPT} --spring.config.additional-location=${CONFIG_LOCATION}"

function start()
{
  pid=$(ps ax | grep -i roncoo.${SERVER} | grep java | grep -v grep | awk '{print $1}')
  if [ x"$pid" != x"" ] ; then
    echo "${SERVER} is running..."
  else
    nohup ${JAVA} ${JAVA_OPT} roncoo.${SERVER} >/dev/null 2>&1 &
    echo "${SERVER} start success"
  fi
}

function stop()
{
  pid=""
  query(){
    pid=$(ps ax | grep -i roncoo.${SERVER} | grep java | grep -v grep | awk '{print $1}')
  }
  query
  sums=1
  if [ x"$pid" != x"" ] ; then
    kill "${pid}"
    echo "${SERVER} stopping···"
    while [ x"$pid" != x"" ]
    do
      if [ $sums -gt 10 ]; then
        kill -9 "${pid}"
      fi
      sleep 1
      ((sums++))
      query
    done
    echo "${SERVER} stop success"
  else
    echo "${SERVER} is stop"
  fi
}

case "$1" in
  start)
    start;;
  stop)
    stop;;
  *)
    $0 stop
    sleep 1
    $0 start
    ;;
esac
