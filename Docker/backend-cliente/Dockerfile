FROM openjdk:17-jdk-buster
ENV APP_HOST=${APP_HOST}
ENV APP_PORT=${APP_PORT}
ENV APP_DB=${APP_DB}
ENV APP_USERNAME=${APP_USERNAME}
ENV APP_PASSWORD=${APP_PASSWORD}
WORKDIR /usr/src
ARG JAR_FILE
COPY ${JAR_FILE} app.jar
COPY app.sh app.sh
COPY application.properties application.properties
RUN sed -i 's/\r$//' app.sh
RUN chmod 755 app.sh
EXPOSE 8080
ENTRYPOINT ["/bin/bash","/usr/src/app.sh"]