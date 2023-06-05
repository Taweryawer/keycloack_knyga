#Create jar
FROM maven:3.6.3-openjdk-11 AS package
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean package

#Run jar
FROM tomcat:9.0.41-jdk11-corretto
COPY --from=package /app/target/ROOT.war /usr/local/tomcat/webapps/
