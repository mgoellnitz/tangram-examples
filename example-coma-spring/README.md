(Too) Simple CoreMedia Adaptor CoMA for Tangram
===============================================

This example brings together the CoreMedia Adaptor (Tested with Versions CMS 2008
and CoreMedia 7) and the spring integration of tangram to view simple content models 
in a relatively direct manner - so with a very lean/little/no business logic.

This example is a little more complicated than compiling and starting like most 
of the others. This is due to the fact that we rely on your CoreMedia CMS Database 
and Content being in place. Coma is  no replacement but an addendum to your existing 
CoreMedia CMS infrastructure in Versions CMS 2008 and CoreMedia 7! (We left out
testing with Version 6)

For this very small example thus you need the very small example from CoreMedia, 
the MenuSite, which was last published with version CMS 2008 at

https://releases.coremedia.com/cmv/16/cms/5.2.1456/cap-examples.jar

Prerequisites
-------------

A MenuSite database created with a CoreMedia Content Server. Any server role - Content 
Management Server, Master Live Server, or Replication Live Server will do.

The Gradle assembly demonstration at https://github.com/mgoellnitz/cm-cms-webapp
in the branch 'menusite' will do the job.

Otherwise you can prepare a server installation from any of the CoreMedia provided
Apache Maven based workspaces and customize it with the needed MenuSite document 
type definitions and content download URL (s.a.).

Tangram Coma Preparation
------------------------

Before building this example with the templates directly takes from the CoreMedia
example, you need your CoreMedia Partner/Customer Portal credentions at hand. If 
you are in into the matter, you will already have those.

The Tangram CoMA example expects those credentials in your gradle.properties from
the gradle home directory in the variables coremediaUsername and coremediaPassword.

After having entered this, you can build the application with

```
gradle
```

which will execute Gradle's clean and build tasks. The application can be started 
in place with the Gradle built in jetty with

```
gradle jettyRunWar
```

Despite the fact that we use the very old (read: obsolete) version of Jetty built
into the Gradle releases, the example  will work that way since our packaging was
"old style" with the templates not placed within JARs.


DB preparation steno
--------------------

Content Server:

* Add a license file 

* Use the document types from the branche 'menusite' from the cm-cms-webapp demonstration.

Content Server Tools:

* Import the content from the cap-examples.jar 
