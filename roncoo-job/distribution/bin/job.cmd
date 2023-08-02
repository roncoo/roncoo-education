@echo off
set "JAVA=%JAVA_HOME%\bin\java.exe"
set SERVER=job
setlocal enabledelayedexpansion
set BASE_DIR=%~dp0
set BASE_DIR="%BASE_DIR:~0,-5%"
set CUSTOM_SEARCH_LOCATIONS=file:%BASE_DIR%/conf/

rem set jvm
set "JVM_OPTS=-Xms512m -Xmx512m -Xmn256m"

rem set spring config location
set "CONFIG_OPTS=--spring.config.additional-location=%CUSTOM_SEARCH_LOCATIONS%"

rem start command
set COMMAND="%JAVA%" %JVM_OPTS% -jar %BASE_DIR%\target\%SERVER%.jar %CONFIG_OPTS% roncoo.job %*
%COMMAND%
