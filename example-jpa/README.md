Tangram JPA Example
===================

To be used with bytecode enhanced / woven or plain implementations for OpenJPA, 
EclipseLink and perhaps DataNucleus Access Plattform.

Setup system
------------

Build the example project

```bash
gradle build
```

(Optionally deploy the created war file to a JSP/Servlet Container of your choice)

Start this container of your choice or use integrated jetty

```bash
gradle jettyRunWar
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
http://localhost:12380/example-jpa/s/id_RootTopic:1
```

Customize JPA Implementation
----------------------------

The tangram jpa jar comes with NO dependency for the implementations OpenJPA, EclipseLink, 
or DataNucleus. 

Any application using the module will have to add these dependencies itself. This 
also holds true, when you select any of the variations of the JPA module with
byte-transformed/enhanced/woven (synonyms!) classes since they carry all the same
set of dependencies.


Customize Database System to use
--------------------------------

Most configuration for the database system is done through the tangram config 
files - not within the persistence.xml - to have the setup in one place as much 
as possible. 

One for dinistiq and one for spring. You will find many examples for database 
setup and target plattforms like cloudbees there.

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
