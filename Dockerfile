FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /opt/app
COPY ./target/stackoverflow.jar stackoverflow.jar
ENTRYPOINT ["java", "-jar", "stackoverflow.jar"]
