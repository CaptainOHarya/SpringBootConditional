FROM openjdk:17-jdk-alpine

EXPOSE 8081

ADD target/springBootHomework-0.0.1-SNAPSHOT.jar integretionTest.jar

CMD ["java", "-jar", "integretionTest.jar"]