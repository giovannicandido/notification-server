Java EE Starter Project
========================

The best thing about this project, is that you don't need nothing besides the Java SDK to start developing Java Enterprise applications. All dependencies are downloaded including the build software (thanks to gradle wrapper).

This project is different from [Jboss Forge], this is a static Skeleton that builds from Gradle, Forge is a plugglabe configuration for maven projects. I recommend use Forge if you need easy control over what to setup. 

And a recommend this skeleton if you want start quick, and as me, prefer ultimate control and undestand what you do.

## Features

* Setup Minimal Java EE Project with Gradle
* Integration Test With Arquillian and profiles
* Default Integration with Wildfly
* Download and setup wildfly to run test use task **unzipWildfly**
* Dependency for UserType to correct save Hibernate Java 8 and JodaTime Date and Time. [http://jadira.sourceforge.net/usertype-userguide.html](http://jadira.sourceforge.net/usertype-userguide.html)
* Setup a _provided_ dependency configuration for Gradle (there is not a default concept of provided) and configure the classpath for Intellij IDEA

## Usage

The project came with an example of integration test that runs in the weld container. You just need run it

```
./gradlew test
```

To run the tests in wildfly. When you project contain more complex tests, like tests that use a database, you probally will need to run in a full Java EE container. The best way is to run in Jboss Wildfly, the gradle build contains a profile to run the tests, all you need is download wildfly **8.1.0.Final** and unzip in the **build** directory. Don't worry this automatic made by the __gradle task unzipWildfly__

```
./gradlew unzipWildfly # Need only once
./gradlew test -Pprofile=wildfly-managed
```

To setup de default profile so you don't need *-Pprofile* option, edit the build.gradle file. Change the code below
    
    if(!hasProperty('profile')){
       apply from: 'weld-ee-embedded-profile.gradle' # change this line
    }else{
       apply from: profile +'-profile.gradle'
    }

To run in other profile use de command below. The profiles are the files with the suffix **-profile.gradle**

```
./gradlew test -Pprofile=glassfish-embedded
```
[Jboss Forge]:(http://forge.jboss.org)