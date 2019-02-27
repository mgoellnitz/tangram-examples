Tangram Ebean Example
=====================

Very small tangram example application using the EBean ORM module.

To wire up the application you can either use the springframework, Google Guice, or dinistiq as usual.

The example is pre-configured to use an h2 database local to your development directory.


Setup system
------------

Build the example project

```bash
gradle build
```

(Optionally deploy the created war file to a JSP/Servlet Container of your choice)

Start this container of your choice or use integrated gretty plugin

```bash
gradle appRun
```

Example Content
---------------

After importing the example content (example-content.xml) it may be necessary to add 
the subtopic items to the root topic instance.

Custom Data Model
-----------------

Be aware, that you need to switch of DDL generation and applying after the first start.
Otherwise the Database will be wiped on every startup. - It is switched off by default 
not to wipe the exampe content. See e.g. serverConfig.properties (using dinistiq)

