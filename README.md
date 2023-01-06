
<div align="center">

<h1 align="center">MediScreen</h1>

</div>

## About The Project

Mediscreen is a springBoot application using a multi-service architecture. This application allow doctors/practitioners to fetch personals information on patients. Depending the informations, the application can generate a diabetes report.


### Built With


* [SpringBoot 2.5.6](https://spring.io/projects/spring-boot)
* [Java 11](https://www.java.com/fr/)
* [Gradle](https://gradle.org/)
* [Docker & DockerCompose](https://www.docker.com/)
* [MySql](https://www.mysql.com/fr/)
* [MongoDB](https://www.mongodb.com/)
* [Thymeleaf](https://www.thymeleaf.org/)

### Architecture

Mediscreen use 4 different micro-services :

* **UI-API**: this microservice handles the user interface part of the application, it receives all HTTP requests from the user, send http request to others services, get the responses and modify the view.
* **PATIENT-API**: this microservice allows patient information management, like add, update and delete patient information. This service use MySQL database to store information.
* **HISTORY-API**: this microservice handles the history of each patient. It allows practitioners/doctors to add, update, delete notes about patient. Notes are stored in a MongoDb database.
* **ASSESSMENT-API**: this part of the application is used to gather all information about a patient (personal and history) in order to calculate the risk level to have diabetes.

<!-- GETTING STARTED -->
## Getting Started

### Installation

1. Clone the project using:

```bash
git clone https://github.com/Dixen7/OC_9_Mesdiscreen.git
```

2. Use the following command to run the app:

```bash
 docker-compose up -d --build 
```

3.  For the first time, execute the following CURL command to fill-in the mongodb database with few notes (find those here "./resources/curlAddNote.txt").

```
curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestNone Practitioner's notes/recommendations: Patient states that they are 'feeling terrific' Weight at or below recommended level\",  \"patientId\": 1}"

curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestBorderline Practitioner's notes/recommendations: Patient states that they are feeling a great deal of stress at work Patient also complains that their hearing seems Abnormal as of late\",  \"patientId\": 2}"

curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestBorderline Practitioner's notes/recommendations: Patient states that they have had a Reaction to medication within last 3 months Patient also complains that their hearing continues to be problematic\",  \"patientId\": 2}"

curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestInDanger Practitioner's notes/recommendations: Patient states that they are short term Smoker\",  \"patientId\": 3}"

curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestInDanger Practitioner's notes/recommendations: Patient states that they quit within last year Patient also complains that of Abnormal breathing spells Lab reports Cholesterol LDL high\",  \"patientId\": 3}"

curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestEarlyOnset Practitioner's notes/recommendations: Patient states that walking up stairs has become difficult Patient also complains that they are having shortness of breath Lab results indicate Antibodies present elevated Reaction to medication\",  \"patientId\": 4}"

curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestEarlyOnset Practitioner's notes/recommendations: Patient states that they are experiencing back pain when seated for a long time\",  \"patientId\": 4}"

curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestEarlyOnset Practitioner's notes/recommendations: Patient states that they are a short term Smoker Hemoglobin A1C above recommended level\",  \"patientId\": 4}"

curl -X POST "http://localhost:8082/history/validate" -H  "Content-Type: application/json" -d "{  \"note\": \"Patient: TestEarlyOnset Practitioner's notes/recommendations: Patient states that Body Height, Body Weight, Cholesterol, Dizziness and Reaction\",  \"patientId\": 4}"
```

5. Stop the app using :

 ```bash
 docker-compose down
```

### Run the tests

Use the following command to run tests in assessment-api directory, or history-api directory, or patient-api directory, or ui-api directory:

 ```bash
 gradle test
```

<!-- ENDPOINTS -->
## Endpoints

Use the following url after starting the application to see the Swagger API documentation.

* [ui-api - Swagger](http://localhost:8080/swagger-ui.html#/)
* [patient-api - Swagger](http://localhost:8081/swagger-ui.html#/)
* [history-api - Swagger](http://localhost:8082/swagger-ui.html#/)
* [assessment-api - Swagger](http://localhost:8083/swagger-ui.html#/)
