FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ADD target/server-sba.jar /server-sba.jar
ENTRYPOINT ["sh","-c","java -jar /server-sba.jar"]

## docker build
# mvn clean package dockerfile:build

## docker run
# docker run -p 5761:5761 -v /home/roncoo/dockers/logs/sba:/home/roncoo/education/logs/sba --name sba -d roncoo-education-server-sba:8.0.0-RELEASE
