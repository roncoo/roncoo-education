FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ENV PARAMS="--info.eureka.host=192.168.1.13"
ADD target/server-sba.jar /server-sba.jar
ENTRYPOINT ["sh","-c","java -jar /server-sba.jar $PARAMS"]

## docker build
# mvn clean package dockerfile:build

## docker run
# docker run -p 5761:5761 -v /home/roncoo/dockers/logs/sba:/home/roncoo/education/logs/sba --name sba -d roncoo-education-server-sba:8.0.0-RELEASE
