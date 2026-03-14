# Use Maven + JDK 17 to build the app
FROM maven:3.9.0-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom and source
COPY pom.xml .
COPY src ./src

# Build the WAR
RUN mvn clean package -DskipTests

# Use Tomcat base image to run the WAR
FROM tomcat:10.1-jdk17
# Remove default webapps
RUN rm -rf /usr/local/tomcat/webapps/*
# Copy WAR built from Maven
COPY --from=build /app/target/catalog-service.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]
