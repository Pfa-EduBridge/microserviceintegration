FROM openjdk:17
EXPOSE 8080
ADD target/Course-0.0.1-SNAPSHOT.jar Course.jar
RUN mkdir -p /uploads && chmod 777 /uploads
ENTRYPOINT ["java", "-jar", "Course.jar"]
