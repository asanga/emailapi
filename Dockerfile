FROM amazoncorretto:17
EXPOSE 8080
WORKDIR /spring-boot
ARG JAR_FILE=./target/emailapi-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]
