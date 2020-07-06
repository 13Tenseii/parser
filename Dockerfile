FROM openjdk:11.0.7
ARG JAR_FILE=core/target/core-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]