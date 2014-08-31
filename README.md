Java EE Starter Project
========================

The best thing about this project, is that you don't need nothing besides the Java SDK to start developing Java Enterprise applications. All dependencies are downloaded including the build software (thanks to gradle wrapper).

This project is different from forge

## Features

* Setup Minimal Java EE Project with Gradle
* Integration Test With Arquillian and profiles
* Default Integration with Wildfly
* Download and setup wildfly to run test use task **unzipWildfly**
* Dependency for UserType to correct save Hibernate Java 8 and JodaTime Date and Time. [http://jadira.sourceforge.net/usertype-userguide.html](http://jadira.sourceforge.net/usertype-userguide.html)

## Usage

To run the tests in wildfly

```
./gradlew unzipWildfly # Need only once
./gradlew test
```

To run in other profile use de command below. The profiles are the files with the suffix **-profile.gradle**

```
./gradlew test -Pprofile=weld-ee-embedded
```


