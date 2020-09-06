FROM daocloud.io/library/java:8-jre-alpine
MAINTAINER roncoo
ARG JAVA_OPTS="-Xmx128M"
ENV JAVA_OPTS=$JAVA_OPTS
ARG RUN_ARGS="--spring.profiles.active=prod"
ENV RUN_ARGS=$RUN_ARGS
ADD target/app-job.jar /app-job.jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app-job.jar $RUN_ARGS"]
