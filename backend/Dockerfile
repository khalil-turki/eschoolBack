FROM openjdk:19

# Set the working directory within the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/timesheetdevops.jar timesheetdevops.jar

# Expose the port your application is listening on (if necessary)
EXPOSE 8087

# Define the entry point
ENTRYPOINT ["java", "-jar", "timesheetdevops.jar"]