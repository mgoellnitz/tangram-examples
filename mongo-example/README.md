Limitations of the MongoDB Implementation
=========================================

While the MongoDB mapping of the datanucleus implementation we use here happily stores byte[] as blobs and char[] as longer text elements, it files on reading them.

So for now we internally use String for text and return it as char[] in the getters to the tangram framework and do similar stuff to the byte[] sort of things.

We will investigate this issue since we up to now think this is a datanucleus bug.

Setup system
=========

First set up the database system

Update the jdoconfig.xml to reflect this:

```xml
<property name="javax.jdo.option.ConnectionURL" value="jdbc:postgresql://localhost:5432/postgres"/>
<property name="javax.jdo.option.ConnectionUserName" value="tex" />
<property name="javax.jdo.option.ConnectionPassword" value="tex" />
```

Build the example project

```bash
gradle build
```

(Optionally deploy thw build\war directory to a JSP/Servlet Container of your choice)

Start this container of your choice or use integrated jetty

```bash
gradle jettyRunWar
```

Call the editor and log in

http://localhost:12380/mongo-example/s/list

The user is "admin" and password is "admin" as you might find in the config files later - in default state the whole application is protected

Create an instance of type "org.tangram.rdbms.solution.RootTopic"

Create an instance of type Code with mime-type "text/html" and annotation "org.tangram.rdbms.solution.RootTopic"

Call with your browser the URL

http://localhost:12380/s/id_RootTopic:1

A user not allowed to use the editor but to view the site is called user also with the password user.
