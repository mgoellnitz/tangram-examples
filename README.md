Very small Examples for Tangram Usage
=====================================

[![Build Status](https://api.travis-ci.org/mgoellnitz/tangram-examples.svg?branch=master)](https://travis-ci.org/mgoellnitz/tangram-examples)

These are the example applications for the different usage flavours of tangram.

They are intended as a quick as possible demo to see something running. In the 
comments of the build files you will additionally find the options to tweak the 
application to use the full bandwidth of flavours:

* JPA, JDO, or EBean as Persistence APIs and automated Class-Files enhancing/weaving.
* RDBMS, Office Files, Google App Engine, or MongoDB as the database.
* Springframework, Dinistiq, or Google Guice as DI environments.

The target platforms are

* Local/Custom server
* OpenShift
* Google App Engine

This would result in some 36 examples every single one of the possible combinations 
so some of the switches can be found in the gradle build files to change a spring 
example into a non-spring example. The storage backends all got their separate example 
while the target platforms should be handled within the storage solution chosen. - 
Always with the exception of the Google App Engine, which is very special in many 
respects.

The examples are also meant as a starting point for your own projects.

A 

```bash
gradle build
```

in this directory should do the job. Optionally you can use the property "backend"
to switch the Dependency Injection framework used. Like with

```bash
gradle -Pbackend=spring build
```

to use the Spring Framework instead of the default "dinistiq". Another option
would be Google Guice through the guicy tangram module.

Except for the Google App Engine one all examples can be started via

```bash
gradle jettyRunWar
```

and are running as a default on port 12380 - unless you changed that. They don't 
depend on anything else running on your system than tangram itself.

Spring or not Spring
--------------------

Many of the examples have a switch 

```java
def tangram_backend = 'spring' // spring or dinistiq
```

This switches from the use of the spring framework for the component configuration 
done with dependency injection to the dinistiq way to do this.

With this switch changed the files in WEB-INF/tangram/ are not used anymore but 
the resources from dinistiq/.

Since it would then be rather strange to use spring security for the authentication
we also provide small examples for the use of Apache Shiro with tangram. Just change
the comments in the dependencies so than spring security is not included but the
shiro block.

```java
  /* Spring security for springframework version
  // this is more like a runtime dependency since we for now only use it by configuration
  compile "org.springframework.security:spring-security-config:$versions.springsecurity"
  compile "org.springframework.security:spring-security-web:$versions.springsecurity"
   */
  
  /* Apache Shiro most likely to be used together with dinistiq
  */
  compile "org.apache.shiro:shiro-core:$versions.shiro"
  compile "org.apache.shiro:shiro-web:$versions.shiro"
```

The files in shiro/ and the shiro.ini are only used in this setup and the files 
WEB-INF/security-*.xml are only used by spring security.

Build backend switches
----------------------

The build files for gradle are a little bit more complicated than necessary to allow for
the mentioned switches which are also build-time overridable by gradle properties. So you 
can override the default backend to use 'dinistiq' or 'spring' by a simple

```bash
gradle -Pbackend=spring build
```

This also changes the security dependencies between Spring Security and Apache Shiro.

The JPA example has additional options since there are three supported libraries implementing
this API: OpenJPA, EclipseLink, and DataNucleus. So again you can override the default from
the build file by a simple

```bash
gradle -Pjpa=openjpa build
```

Also note that OpenJPA is fully supported with unenhanced beans while the support is - due
to the setup of EclipseLink itself - limitted for EclipseLink.