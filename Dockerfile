#FROM gradle:7.3.3-jdk17-alpine AS build
#COPY  . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle assemble

FROM openjdk:17-alpine
EXPOSE 8080
RUN mkdir /app
COPY build/libs/jibber-jabber-posts-0.0.1-SNAPSHOT-plain.jar /app/JibberJabberPostsApplication.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production","/app/JibberJabberPostsApplication.jar"]