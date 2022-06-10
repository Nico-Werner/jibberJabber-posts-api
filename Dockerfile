#FROM gradle:7.3.3-jdk17-alpine AS build
#COPY  . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle assemble

#FROM openjdk:17-alpine
#EXPOSE 8080
#ARG JAR_FILE=build/libs/jibber-jabber-posts-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production","/app.jar"]

FROM gradle:7.3.3-jdk17-alpine AS build
COPY  . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle assemble

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production","/app/spring-boot-application.jar"]