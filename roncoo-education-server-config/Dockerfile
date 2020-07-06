FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ENV PARAMS="--info.eureka.host=192.168.1.13"
ADD target/server-config.jar /server-config.jar
ENTRYPOINT ["sh","-c","java -jar /server-config.jar $PARAMS"]

## docker build
# mvn clean package dockerfile:build

## docker run
# docker run -p 5741:5741 -v /home/roncoo/dockers/logs/config:/home/roncoo/education/logs/config --name config -d roncoo-education-server-config:8.0.0-RELEASE
