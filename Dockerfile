# Use the Eclipse Temurin JRE 21 Alpine image as the base image
FROM eclipse-temurin:21-jre-alpine

# Set the maintainer label
MAINTAINER Jawad Zaarour

# Define a build argument for the JAR file location
ARG JAR_FILE=target/clp-jar-with-dependencies.jar

# Copy the JAR file from the build context to the container
COPY ${JAR_FILE} clp.jar

# Copy the test resource file into the container
COPY src/test/resources/cookie_log.csv /app/cookie_log.csv

# Specify the entry point for the container to run the JAR file
ENTRYPOINT ["java","-jar","/clp.jar"]
