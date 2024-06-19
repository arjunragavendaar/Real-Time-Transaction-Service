

FROM maven:3.8.3-openjdk-17 AS setupprocess
WORKDIR /app 
VOLUME /tmp
COPY src /app/src
COPY pom.xml /app   
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17
WORKDIR /app
COPY --from=setupprocess /app/target/CodeScreen_mwpw6bqj-1.0.0.jar /app
EXPOSE 8080
CMD ["java", "-jar", "CodeScreen_mwpw6bqj-1.0.0.jar"]




