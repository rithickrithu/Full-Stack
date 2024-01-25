# Use the official OpenJDK base image
FROM openjdk:17-jdk

# Copy the JAR file into the container
COPY target/spring-boot-crud-0.0.1-SNAPSHOT.jar .

# Expose the port your application runs on
EXPOSE 8081

# Command to run your application
CMD ["java", "-jar", "spring-boot-crud-0.0.1-SNAPSHOT.jar"]
