FROM openjdk:17-jdk-buster
ENV APP_AWS_ACCESSKEY=SUB_APP_AWS_ACCESSKEY
ENV APP_AWS_SECRETKEY=SUB_APP_AWS_SECRETKEY
WORKDIR /usr/src
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
COPY app.sh app.sh
COPY application.properties application.properties
RUN sed -i 's/\r$//' app.sh
RUN chmod 755 app.sh
EXPOSE 8080
ENTRYPOINT ["/bin/bash","/usr/src/app.sh"]