Notification Server Project
============================
[![Build Status](https://api.shippable.com/projects/540e84d43479c5ea8f9f0513/badge?branchName=master)](https://app.shippable.com/projects/540e84d43479c5ea8f9f0513/builds/latest)

This project is part of a central architecture when several applications should send email, and use this as a service.

[Documentação](http://wiki.atende.info/display/wa)

## Technology

* Spring Boot
* PostgreSQL Database

## Configuration of email

Use this configuration on file. In production this can be override by ENV variables ou command line. See [Spring Boot Config]

    mail.from=no-reply@test.com
    spring.mail.host=localhost
    spring.mail.port=3025
    spring.mail.protocol=smtp

Gmail Example:

    spring.mail.host = smtp.gmail.com
    spring.mail.username = *****@gmail.com
    spring.mail.password = ****
    spring.mail.properties.mail.smtp.auth = true
    spring.mail.properties.mail.smtp.socketFactory.port = 465
    spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
    spring.mail.properties.mail.smtp.socketFactory.fallback = false

You can configure that on the IDE to override for dev. Don't edit **-dev.properties** because it is checked on version control

![Email Config IDEA](images/config_email.png "IDEA Email Config Spring")


## Usage from a dev perspective

The application is preconfigured to use the credentials on *dev* and *test* spring profiles:

| User  | Password | Host         |
|-------|----------| ------------ |
| super | 1234     | docker.local |
|       |          |              |

The **dev** profile is the default when running the application, it changes to test when running on tests because
of `@SpringTestProfile` annotation

**dev** connects to a **notification** database and **test** connect to a **notification_test** database

Using docker and docker compose you can bring that to life:

    docker-compose up

Alter your **/etc/hosts** file pointing to the real docker ip. If you are on linux point to *127.0.0.1* if you
use **docker-machine** point to `docker-machine ip default` # default is the name of machine


    sudo echo "127.0.0.1 docker.local" >> /etc/hosts

## Running Tests

```
./gradlew test
```

[Spring Boot Config]:https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html