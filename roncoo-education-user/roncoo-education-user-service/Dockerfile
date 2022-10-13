FROM openjdk:8-jdk-oracle
MAINTAINER roncoo
ARG JAVA_OPTS="-Xmx128M"
ENV JAVA_OPTS=$JAVA_OPTS
ARG RUN_ARGS="--spring.profiles.active=prod"
ENV RUN_ARGS=$RUN_ARGS
ADD target/user.jar /user.jar
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /user.jar $RUN_ARGS"]
