Very small Examples for Tangram Usage
=====================================

These are the example applications for the different usage flavours of tangram.

They are intended as a quick as possible demo to see something running, but in the 
comments  you will find the options to tweak this to use:

* JPA or JDO as Persistence Interfaces
* RDBMS, Office Files, or MongoDB as the database.
* Spring or non-spring environments

The target platforms are

* Local/Custom server
* Cloudbees
* OpenShift
* Google App Engine

This would result in some 20 examples for any of the combinations so some of the switches 
can be found in the gradle build files to change a spring example into a non-spring example. 
The storage backends all got their separate example while the target platforms should be 
handled within the storage solution chosen. - Always with the exception of the Google App 
Engine, which is very special in many respects.

The examples are also meant as a starting point for your own projects.

A 

```bash
gradle build
```

in this directory should do the job. Except the Google App Engine one, all examples
can be started via

```bash
gradle jettyRunWar
```

and are running as a default on port 12380 - unless you changed that. They don't 
depend on anything else running on your system than tangram itself.

Spring or not Spring
--------------------

Many of the examples have a switch 

```java
ext.tangram_backend = 'spring' // spring or dinistiq
```

This switches from the use of the spring framework for the component configuration 
done with dependency injection to the dinistiq way to so this.

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
  // avoid dependencies to spring 3.0.7
  compile ("org.springframework.security:spring-security-web:$versions.springsecurity") {
    exclude module: 'spring-jdbc'
    exclude module: 'spring-tx'
  }
   */
  
  /* Apache Shiro most likely to be used together with dinistiq
  */
  compile "org.apache.shiro:shiro-core:$versions.shiro"
  compile "org.apache.shiro:shiro-web:$versions.shiro"
  compile "org.slf4j:slf4j-api:1.6.6"
  compile "org.slf4j:slf4j-log4j12:1.6.6"
```

The files in shiro/ and the shiro.ini are only used in this setup and the files 
WEB-INF/security-*.xml are only used by spring security.
