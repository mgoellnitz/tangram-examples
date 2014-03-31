Tangram Ebean Example
=====================

Very small tangram example application using the EBean ORM module.

To wire up the application you can either use the springframework or dinistiq.

As the security frameworks for the login - which should at least be active for the 
editing part of the application which is included with this example - you have the 
options of Apache Shiro and Spring Security.

The example is pre-configured to use an h2 database local to your development directory.
Be aware, that you need to switch of DDL generation and applying after the first start. 
Otherwise the Database will be wiped on every startup.
