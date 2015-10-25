Very small Examples for Tangram Usage
=====================================

[![Build Status](https://api.travis-ci.org/mgoellnitz/tangram-examples.svg?branch=master)](https://travis-ci.org/mgoellnitz/tangram-examples)

These are the example applications for the different usage flavours of tangram.

They are intended as a quick as possible demo to see something running. In the
comments of the build files you will additionally find the options to tweak the
application to use the full bandwidth of flavours:

* JPA, JDO, or EBean as Persistence APIs and automated Class-Files enhancing/weaving.
* RDBMS, Office Files, or MongoDB as the database.
* Springframework, Dinistiq, or Google Guice as DI environments.

The target platforms are

* Local/Custom server
* OpenShift

This would result in some 24 examples for every single one of the possible combinations.
So some of the switches can be found in the gradle build files to change a spring
example into a non-spring example. The ORM APIs all got their separate example
while the target platforms should be handled within the storage solution chosen.

These examples are also meant as a starting point for your own projects.

A

```bash
gradle build
```

in this directory should do the job.

Except for the Google App Engine one, all examples can be started via

```bash
gradle appRunWar
```

and are running as a default on port 12380 - unless you changed that. They don't
depend on anything else running on your system than tangram itself.

Example Content
---------------

If you decide to try an empty database, you can use the exported version of the 
content in example-content.xml. Use the generic tangram importer at http://localhost:12380/<app>/importer and select the XML file and import it.

Dependency Injection
--------------------

All of the examples have a switch in the build.gradle source code

```java
def tangram_backend =  .... 'spring' // spring, guicy, or dinistiq
```

This selected the dependency injection framework to use. All of the examples come
with working configurations for the Springframework, dinistiq, and Google Guice.
Google Guice needs some more generic mimik to be likewise runtie configurable like
with the Springframework and dinistiq so this flavour is called after the corresponding
tangram module guicy.

Build backend switches
----------------------

The build files for gradle are a little bit more complicated than necessary to allow for
the mentioned switches which are also build-time overridable by gradle properties. So you
can override the default backend to use 'guicy', 'dinistiq' or 'spring' by a simple

```bash
gradle -Pbackend=spring build
```

The JPA example has additional options since there are four supported libraries implementing
this API: OpenJPA, EclipseLink, Hibernate, and DataNucleus. So again you can override
the default (OpenJPA) from the build file by a simple

```bash
gradle -Pjpa=openjpa build
```
