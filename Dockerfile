FROM maven:3.5-jdk-8 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean package -D skipTests

FROM java:8-jre
COPY --from=build /usr/src/app/target/favourite-api-0.0.1-SNAPSHOT.jar /usr/src/favourite-api-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src
CMD ["java","-jar","/usr/src/favourite-api-0.0.1-SNAPSHOT.jar"]