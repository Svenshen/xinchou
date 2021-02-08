# To change this license header, choose License Headers in Project Properties.
# To change this template file, choose Tools | Templates
# and open the template in the editor.
# @autoor szh
#FROM openjdk:8-jdk-alpine
FROM openjdk:11-oraclelinux8
VOLUME /tmp
#ARG JAR_FILE
#RUN apk add -U tzdata
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
RUN echo "Asia/Shanghai" > /etc/timezone
#COPY ${JAR_FILE} app.jar
RUN mkdir /app
COPY app.jar /app/app.jar
RUN yum update
RUN curl -sL https://rpm.nodesource.com/setup_10.x | bash -
RUN yum install -y nodejs
EXPOSE 8080
ENV LANG=en_US.UTF-8
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]

