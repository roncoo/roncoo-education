FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ADD target/server-gateway.jar /server-gateway.jar
ENTRYPOINT ["sh","-c","java -jar /server-gateway.jar"]

## docker build
# mvn clean package dockerfile:build

## docker run
# docker run -p 5761:5761 -v /home/roncoo/dockers/logs/gateway:/home/roncoo/education/logs/gateway --name gateway -d roncoo-education-server-gateway:8.0.0-RELEASE
