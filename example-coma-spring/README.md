(Too) Simple CoreMedia Adaptor CoMA for Tangram
===============================================

This example brings together the CoreMedia Adaptor (Tested with Versions up to CMS 2008) and the spring integration of tangram to view simple content models in a relatively directory manner - so with a very lean business logic.

This example is a little more complicated than compiling and starting like most of the others. This is due to the fact that we rely on your CoreMedia CMS Database and Content being in place. Coma is  no replacement but an addendum to your existing CoreMedia CMS infrastructure!

For this very small example thus you need the very small example from coremedia, the menusite. We took Version CMS 2008 (5.2) just to avoid a maven build.

Prerequisites
=============

A menusite database create with a coremedia content (management) server.

This is achived by installing a content management server, copying license file and menusite doctypes to the installation and importing the example content. We used the postgresql database in this example but you are more or less free to use any dastabase supported by the CoreMedia CMS.

Tangram Coma Preparation
========================

Before building this example you need to copy the template directory of the menusite example to the src/main/webapp/WEB-INF folder. Then you can build the application with

```
gradle
```

which will execute gradle's clean and build tasks. The application can be started in place with the gradle built in jetty with

```
gradle jettyRunWar
```

Since we were using CoreMedia CMS 2008 we don't interfere with the very old release of jetty included with gradle.


Download-Source
===============

https://releases.coremedia.com/cmv/16/cms/5.2.1456/


DB preparation steno
====================

content management server:

* postgres menusite/menusite

* let the installer point to the correct licensefile 

* copy cap-examples\cae\menusite\misc\menu-doctypes.xml target\contentserver\properties\corem

* contentserver.properties:
cap.server.documentTypes=resourcecontainer:config/contentserver/framework/*-config.jar|/framework/doctypes/*.xml,properties/corem/menu-doctypes.xml

 copy postgresql-8.4-702.jdbc4.jar target\contentserver\lib

* target\contentserver\bin\cm serverimport -r -u admin -p admin cap-examples/cae/menusite/misc/data
