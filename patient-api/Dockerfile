###########################
#Patient API
###########################

FROM gradle:7-jdk11
WORKDIR ./patient-api
COPY . .
RUN gradle bootJar
ENTRYPOINT [ "java","-jar", "./build/libs/patient-api-1.0.0.jar"]