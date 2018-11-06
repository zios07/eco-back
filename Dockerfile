FROM openjdk:8-alpine

COPY target/*.jar ./app.jar

CMD [ "java", "-jar", "app.jar" ]