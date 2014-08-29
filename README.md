Java EE Starter Project
========================

## Features

* Setup Minimal Java EE Project with Gradle
* Integration Test With Arquillian and profiles
* Default Integration with Wildfly
* Download and setup wildfly to run test use task **unzipWildfly**

To run the tests in wildfly

```
./gradlew unzipWildfly # Need only once
./gradlew test
```
