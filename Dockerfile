FROM amazoncorretto:25-alpine3.19
LABEL authors="EGGzin"

WORKDIR /app

EXPOSE 8082

ADD https://dtdg.co/latest-java-tracer /app/dd-java-agent.jar

ARG JAR_FILE=infrastructure/target/*.jar
COPY ${JAR_FILE} /app.jar

ENTRYPOINT ["java","-jar","/app.jar"]