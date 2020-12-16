FROM openjdk:8
ADD target/kafka-0.0.1-SNAPSHOT.jar kafka-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "kafka-0.0.1-SNAPSHOT.jar"]
