#
# Build stage
#
FROM maven:3.8.7-amazoncorretto-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM amazoncorretto:17-alpine3.16-full
COPY --from=build /home/app/target/*.jar /usr/local/lib/codewars-leaderboard-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","/usr/local/lib/codewars-leaderboard-backend.jar"]