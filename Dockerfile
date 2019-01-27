FROM openjdk:8-jdk-alpine
ADD ./build/libs/filenet-core.jar app.jar

RUN apk update
RUN apk add tzdata
RUN cp /usr/share/zoneinfo/America/Bogota /etc/localtime
RUN echo "America/Bogota" > /etc/timezone
RUN apk del tzdata

EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Xmx200m","-jar","/app.jar"]