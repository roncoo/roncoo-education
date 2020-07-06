FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ADD target/server-eureka.jar /eureka.jar
ENTRYPOINT ["sh","-c","./eureka.jar"]

## docker
# mvn clean package dockerfile:build
# mvn dockerfile:push
