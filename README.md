Notification Server Project
============================

O projeto faz parte da arquitetura de desenvolvimento principal da empresa.
[Documentação](http://wiki.atende.info/display/wa)

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

To setup the default profile so you don't need *-Pprofile* option, edit the build.gradle file. Change the code below
    
    if(!hasProperty('profile')){
       apply from: 'weld-ee-embedded-profile.gradle' # change this line
    }else{
       apply from: profile +'-profile.gradle'
    }

To run in other profile use de command below. The profiles are the files with the suffix **-profile.gradle**

```
./gradlew test -Pprofile=glassfish-embedded
```
