Very small Examples for Tangram Usage
=====================================

These are the example applications for the different usage flavours of tangram.

They are intended as a quick as possible demo to see something running, but in the comments you will find the options to tweak this to use:

* JPA or JDO as Persisntence Interfaces
* RDBMS, Office Files, or MongoDB as the database.
* Spring or non-spring environments

The target platforms are

* Local/Custom server
* Cloudbees
* OpenShift
* Google App Engine

And any application intended to be movable accross any oh these combinations.

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
