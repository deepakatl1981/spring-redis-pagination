FROM openjdk:11-jre-slim
VOLUME /tmp
ARG JAR_FILE=build/libs/elk-product-1.0.0-SNAPSHOT.jar
ADD ${JAR_FILE} elklogging.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/elklogging.jar"]