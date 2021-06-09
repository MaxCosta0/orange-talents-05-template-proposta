FROM openjdk:11
MAINTAINER Maxley Costa
ARG JAR_FILE=target/PropostaApp.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]