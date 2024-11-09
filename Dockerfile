FROM openjdk:17-oracle
VOLUME /tmp
COPY ./target/aws_project01.jar /app/aws_project01.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "aws_project01.jar"]
EXPOSE 8080