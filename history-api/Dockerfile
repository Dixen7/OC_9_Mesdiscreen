###########################
#History API
###########################
FROM gradle:7-jdk11
WORKDIR ./history-api
COPY . .
RUN gradle bootJar
ENTRYPOINT [ "java","-Dspring.data.mongodb.uri=mongodb://mongodb:27017/mediscreendb","-jar", "./build/libs/history-api-1.0.0.jar"]