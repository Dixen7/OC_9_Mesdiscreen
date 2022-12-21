#UI API
FROM gradle:7-jdk11
WORKDIR ./ui-api
COPY . .
RUN gradle bootJar
ENTRYPOINT [ "java","-jar", "./build/libs/ui-api-1.0.0.jar"]