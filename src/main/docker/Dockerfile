FROM java:8
MAINTAINER kurong

RUN mkdir -p /usr/data
WORKDIR /usr/data

ADD join-0.0.1-SNAPSHOT.jar join-0.0.1-SNAPSHOT.jar
EXPOSE 8888

CMD java -jar join-0.0.1-SNAPSHOT.jar --spring.profiles.active=pro
