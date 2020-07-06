FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ADD target/course-service.jar /course-service.jar
ENTRYPOINT ["sh","-c","java -jar /course-service.jar"]

## docker build
# mvn clean package dockerfile:build

## docker run
# docker run -p 5761:5761 -v /home/roncoo/dockers/logs/course:/home/roncoo/education/logs/course --name course -d roncoo-education-course-service:8.0.0-RELEASE
