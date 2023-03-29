FROM openjdk:11.0.11-9-jdk

ADD target/nlinfochallenge.war app.war

ENTRYPOINT ["java", "-jar", "/app.war"]

COPY ${JAR_FILE} /app.jar

EXPOSE 8080
