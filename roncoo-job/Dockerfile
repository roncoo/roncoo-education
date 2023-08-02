FROM openjdk:8-jre-slim
MAINTAINER xuxueli

ENV PARAMS=""

ENV TZ=PRC
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

ADD target/job.jar /job.jar

ENTRYPOINT ["sh","-c","java -jar $JAVA_OPTS /job.jar $PARAMS"]
