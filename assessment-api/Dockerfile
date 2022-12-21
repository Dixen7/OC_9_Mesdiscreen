###########################
#Assessment API
###########################
FROM gradle:7-jdk11
WORKDIR ./assessment-api
COPY . .
RUN gradle bootJar
ENTRYPOINT [ "java","-jar", "./build/libs/assessment-api-1.0.0.jar"]