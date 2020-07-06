FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ADD target/server-config.jar /server-config.jar
ENTRYPOINT ["sh","-c","java -jar /server-config.jar"]

## docker build
# mvn clean package dockerfile:build

## docker run
# docker run -p 5761:5761 -v /home/roncoo/dockers/logs/config:/home/roncoo/education/logs/config --name config -d roncoo-education-server-config:8.0.0-RELEASE
