Tangram JDO Example
===================

Example with Java Data Objects as the persistance layer using DataNucleus Access Plattform and Spring or Dinistiq for the  Application Wire-up

Setup system
------------

Build the example project

```bash
gradle build
```
(Optionally deploy the build\war directory to a JSP/Servlet Container of your choice)

Start this container of your choice or use integrated jetty

```bash
gradle jettyRunWar
```

First Input and Test
--------------------

Call the editor and log in

http://localhost:12380/example-jdo-dn/s/list

The user is "admin" and password is "admin" as you might find in the config files later - in default state the whole application is protected

Create an instance of type "org.tangram.nucleus.solution.RootTopic"

Create an instance of type Code with mime-type "text/html" and annotation "org.tangram.nucleus.solution.RootTopic"

Call with your browser the URL

http://localhost:12380/example-jdo-dn/s/id_RootTopic:1

Customize Database System to use
--------------------------------

Any Configuration for the database system is done through the tangram config files - not with a jdoconfig.xml - to jave the setup in one place. One for dinistiq and one for spring. You will find many examples for database setup and target plattforms like cloudbees there.

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
