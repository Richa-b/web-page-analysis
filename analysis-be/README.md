#### Introduction
* This is the backend module built on Java and Spring Boot framework.

#### Installation Dependencies
* Java 8
* Gradle

#### How to run

### IDE
* Run `main` method in `com.challenge.analysis.HTMLAnalysisApplication` class

OR

* Change into the working directory **analyse-be**, and run `gradle bootRun`.

### JAR

* Change into the working directory **analyse-be**, and run `gradle clean build`. 
* Jar would be created in `${WorkingDirectory}/build/libs`
* Run jar using following command

```
java -jar build/libs/analysis-be-1.0.jar
```

* Application will run at 8080 by default. Can be changed in /resources/application.properties. 
**In case port is changed, Make sure that is also changed in env.js of frontend module**

#### Unit Test Coverage
* Jacoco plugin has been used in gradle file.
* Run `gradle test jacocoTestReport` from the working directory **analyse-be**.
* Report will be generated at `${WorkingDirectory}/build/reports/coverage/index.html`

#### Local Test url:
* http://localhost:8080/api/v1/analyse?url=https://github.com/login
