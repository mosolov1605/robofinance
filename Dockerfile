FROM adoptopenjdk/openjdk11:jre-11.0.3_7-alpine

ARG JAR_FILE=robofinance.jar
ARG APP_HOME=/opt/app

ENV APP_HOME $APP_HOME

WORKDIR ${APP_HOME}

USER ${user}

COPY build/libs/${JAR_FILE} ./app.jar

EXPOSE 8080

ENV JAVA_OPTS="-Xmx512m -Xms256m -Dfile.encoding=UTF-8"
ENV APP_OPTS=""

ENTRYPOINT java $JAVA_OPTS $APP_OPTS -jar app.jar
