FROM openjdk:10.0.2-13-jdk-slim
LABEL maintainer="Gary A. Stafford <garystafford@rochester.rr.com>"
ENV REFRESHED_AT 2018-09-08
EXPOSE 8080
WORKDIR /tmp
COPY /build/libs/*.jar app.jar
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=gcp", "app.jar"]
