Tangram JPA Example
===================

To be used with bytecode enhanced / woven or plain implementations for OpenJPA,
EclipseLink, Hibernate or DataNucleus Access Plattform.

Setup system
------------

Build the example project

```bash
gradle build
```

(Optionally deploy the created war file to a JSP/Servlet Container of your choice)

Start this container of your choice or use integrated jetty

```bash
gradle appRunWar
```

Since the Tangram JPA module supports several implementations of JPA, it is possible
to select the implementation via the "jpa" property whith the possible values
"openjpa", "eclipselink", "hibernate", and "datanucleus". E.g.

```bash
gradle -Pbackend=guicy -Pjpa=eclipselink clean build jettyRunWar
```

Also the implementations allow or even require a byte code enhancement after compilation
for the implementation of the active record pattern. While DataNucleus requires this, it
can be switched off optionally for EclipseLink, Hibernate, and OpenJPA.

The byte code transformation - for most JPA systems called enhancing - can be switched off
through the command line on building as well.

```bash
gradle -Pbackend=dinistiq -Pjpa=openjpa -Pnoenhance clean build jettyRunWar
```

First Input and Test
--------------------

Call the editor and log in

```
http://localhost:12380/example-jpa/s/list
```

The user is "admin" and password is "admin" as you might find in the config files
later - in default state the whole application is protected

Create an instance of type "org.tangram.jpa.solution.RootTopic"

Create an instance of type Code with mime-type "text/html" and annotation
"org.tangram.nucleus.solution.RootTopic"

Call with your browser the URL

```
http://localhost:12380/example-jpa/s/id_RootTopic:xy
```

where xy is the numeric ID of the root topic.

Customize JPA Implementation
----------------------------

The tangram jpa jar comes with NO dependency for the implementations OpenJPA,
EclipseLink, or DataNucleus.

Any application using the module will have to add these dependencies itself. This
also holds true, when you select any of the variations of the JPA module with
byte-transformed/enhanced/woven (synonyms!) classes since they carry all the same
set of dependencies.

When changing the JPA backend, you have to prepare two things. The byte-code
transformation is implementation specific and carries different names as well.
The persistence.xml despite the few things which need to be set here for tangram
(to have everything in one place most of the stuff - as with jdo - moved to
a jpaConfigOverride element in the application configuration) it has to be
specific to the used implementation implementation as well. This cannot be achieved
by different persistence units since elements required by one implementation interfere
with the persistence.xml reading of the other.

The example has a property "jpa_backend" which tries to switch everything needed
in one place. This again hides the feature that you can have different persistence.xml
files for the byte-code transformation and the packaged product. - See details in
build.gradle and make it fit your needs.

Customize Database System to use
--------------------------------

Most configuration for the database system is done through the tangram config
files - not within the persistence.xml - to have the setup in one place as much
as possible.

For spring the "jpaConfigOverrides" are placed in the example.xml bean configuration
for the example application. For dinisiq the same set of values can be configured in
the separate jpaConfigOverrides.properties.

You will find many examples for database setup and target plattforms like cloudbees there.

With hsqldb this tool might be helpfull:

```bash
#hsqldb tool:
java -cp build/war/WEB-INF/lib/hsqldb-1.8.0.10.jar org.hsqldb.util.DatabaseManager
```

After shutting down hsqldb based systems you might want to compact the DB:

```bash
# issue shutdown to compress database again
java -cp build/war/WEB-INF/lib/hsqldb-1.8.0.10.jar org.hsqldb.util.SqlTool --inlineRc url=jdbc:hsqldb:file:tangram-rdbms,password=,user=sa --sql "shutdown;"
```
