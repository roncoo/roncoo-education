FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ADD target/server-job.jar /server-job.jar
ENTRYPOINT ["sh","-c","java -jar /server-job.jar"]

## docker build
# mvn clean package dockerfile:build

## docker run
# docker run -p 5761:5761 -v /home/roncoo/dockers/logs/job:/home/roncoo/education/logs/job --name job -d roncoo-education-server-job:8.0.0-RELEASE
