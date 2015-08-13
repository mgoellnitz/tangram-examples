Tangram Ebean Example
=====================

Very small tangram example application using the EBean ORM module.

To wire up the application you can either use the springframework, Google Guice, or dinistiq as usual.

The example is pre-configured to use an h2 database local to your development directory.


Example Content
---------------

The example content is imported into the h2 database but it cannot be imported right away from the example-content.xml
file.

Custom Data Model
-----------------

Be aware, that you need to switch of DDL generation and applying after the first start. Otherwise the Database will be
wiped on every startup. - It is switched of by default not to wipe the exampe content. See e.g. serverConfig.properties
(using dinistiq)

