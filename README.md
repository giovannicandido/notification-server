Java EE Starter Project
========================

## Features

* Setup Minimal Java EE Project with Gradle
* Integration Test With Arquillian and profiles
* Default Integration with Wildfly
* Download and setup wildfly to run test use task **unzipWildfly**
* Dependency for UserType to correct save Hibernate Java 8 and JodaTime Date and Time. [http://jadira.sourceforge.net/usertype-userguide.html](http://jadira.sourceforge.net/usertype-userguide.html)

To run the tests in wildfly

```
./gradlew unzipWildfly # Need only once
./gradlew test
```
