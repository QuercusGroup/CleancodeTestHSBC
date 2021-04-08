FROM  adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY target/*.jar app.jar
CMD [ "sh", "-c", "java -jar /app.jar" ]