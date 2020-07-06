FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ENV PARAMS="--info.eureka.host=192.168.1.13"
ADD target/server-gateway.jar /server-gateway.jar
ENTRYPOINT ["sh","-c","java -jar /server-gateway.jar $PARAMS"]

## docker build
# mvn clean package -pl roncoo-education-server-gateway -am dockerfile:build

## docker run
# docker run -p 5840:5840 -v /home/roncoo/dockers/logs/gateway:/home/roncoo/education/logs/gateway --name gateway -d roncoo-education-server-gateway:8.0.0-RELEASE
