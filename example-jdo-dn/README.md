Setup system
============

Build the example project

```bash
gradle build
```
(Optionally deploy the build\war directory to a JSP/Servlet Container of your choice)

Start this container of your choice or use integrated jetty

```bash
gradle jettyRunWar
```

Variant I - use demo data
=========================

Import data - which with the default case of hsqldb means "don't do anything" since te database files are ready to use in the workspace

Call with your browser the URL

```
http://localhost:12380/s/
```

The user is "admin" and password is "admin" as you might find in the config files later - in default state the whole application is protected

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



Variant II - start with empty system
====================================

Call the editor and log in

http://localhost:12380/rdbms-example/s/list

The user is "admin" and password is "admin" as you might find in the config files later - in default state the whole application is protected

Create an instance of type "org.tangram.rdbms.solution.RootTopic"

Create an instance of type Code with mime-type "text/html" and annotation "org.tangram.rdbms.solution.RootTopic"

Call with your browser the URL

http://localhost:12380/s/id_RootTopic:1



Customize Database System to use
================================

Setup database user with schema and sufficient write persmissions

CloudBees MySQL Example:
------------------------

Create a Database <DBNAME> in the DBs section of cloudbees and connect it to the application with the bees toolkit.

bees app:bind -db <DBNAME> -a <APPID> -as tangramdb

Modify src/main/resources/META-INF/jdoconfig.xml to reflect this.

```xml
<persistence-manager-factory name="transactions-optional">
    <property name="javax.jdo.PersistenceManagerFactoryClass"
              value="org.datanucleus.api.jdo.JDOPersistenceManagerFactory"/>

    <property name="datanucleus.ConnectionFactoryName" value="java:comp/env/jdbc/tangramdb" />

    <!-- common stuff -->
    <property name="datanucleus.autoCreateSchema" value="true" />
    <property name="datanucleus.validateTables" value="true" />
    <property name="datanucleus.manageRelationships" value="true" />
    <property name="datanucleus.validateConstraints" value="true" />
</persistence-manager-factory>
```


PostgreSQL example:
-------------------

Create a database

```bash
psql -U postgres

create user tex password 'tex';
create schema tex authorization tex;
\q
```bash

Update src/main/resources/META-INF/jdoconfig.xml to reflect this:

```xml
<property name="javax.jdo.option.ConnectionDriverName" value="org.postgresql.Driver" />
<property name="javax.jdo.option.ConnectionURL" value="jdbc:postgresql://localhost:5432/postgres"/>
<property name="javax.jdo.option.ConnectionUserName" value="tex" />
<property name="javax.jdo.option.ConnectionPassword" value="tex" />
<property name="javax.jdo.option.Mapping" value="postgres" />
```

Update your dependencies in build.gradle to include the DB driver

```
compile "postgresql:postgresql:8.4-702.jdbc4"
```
