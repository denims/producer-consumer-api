#FROM maven:3.6.3-jdk-14 AS build
#COPY src /usr/src/app/src
#COPY pom.xml /usr/src/app
#RUN mvn -f /usr/src/app/pom.xml clean package

FROM openjdk:14
#COPY --from=build /usr/src/app/target/*.jar /usr/app/producer-consumer.jar
COPY /target/*.jar /usr/app/producer-consumer.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/usr/app/producer-consumer.jar"]