FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ADD target/server-eureka.jar /server-eureka.jar
ENTRYPOINT ["sh","-c","java -jar /server-eureka.jar"]

## docker build
# mvn clean package dockerfile:build

## docker run
# docker run -p 5761:5761 -v /home/roncoo/dockers/logs/eureka:/home/roncoo/education/logs/eureka --name eureka -d roncoo-education-server-eureka:8.0.0-RELEASE
