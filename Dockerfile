FROM openjdk:21-jdk-slim
RUN apt update -y
RUN apt install net-tools curl procps -y
ARG JARFILE="target/*.jar"
#ARG PROFILE="stg"
COPY ${JARFILE} app.jar
#ENTRYPOINT ["java","-jar","-Dspring.profiles.active=${PROFILE}","/app.jar"]
EXPOSE 8081
#ENTRYPOINT ["java","-jar","/app.jar"]
