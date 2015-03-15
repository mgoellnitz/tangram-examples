Google App Engine example or starting point
===========================================

We only support Google App Engine (GAE) with the Java Data Object (JDO) persistence 
api. It would be perfectly possible to introduce a tangram module for the Java Persistence 
API (JPA) and to have common and separate code for both scenarios but since Google
doesn't support the Java part of the GAE very well with contemporary API levels and
versions we stopped our efforts and only support GAE for JDO to get a decent starting
point for migration to other flavours of tangram.

Prerequisites
-------------

* Java 7
* Gradle 2.0 and up
* Google App Engine SDK Java AND Python (for the bulkloader.py you definetely want to use)

CapeDwarf
---------

This project on the CapeDwarf Google App Engine Emulator with some limitations:

When building the example for CapeDwarf you will need to

- Use the Springframework as a DI environment
- Get rid of all META-INF/beans.xml in the tangram-*.jars in build/war/WEB-INF/lib manually
- Don't use the GAE internal login with google accounts.

Some of the elements in the servlet module of tangram trigger the Weld CDI container
beneath capedwarf. This renders dinistiq unusable while for Guice we deliberately
chose to only support the GAE internal login facility.

For some reason the internal login facility of the Google App Engine APIs doesn't 
work but tangram now provides generic mechanisms for the use of other login providers.

```xml
  <!-- don't use gae internal login -->
  <util:set id="loginProviders" value-type="java.lang.String">
    <value>form</value>
  </util:set>

  <util:set id="adminUsers" value-type="java.lang.String">
    <value>form:admin</value>
  </util:set>
  
  <!-- copy some username password mapping from one of the other examples -->
  <util:map id="usernamePasswordMapping" key-type="java.lang.String" value-type="java.lang.String">
    <entry key="user" value="04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb" />
    <entry key="admin" value="8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918" />
  </util:map>
  
  <!-- Don't use the example defaults:
  <util:set id="allowedUsers" value-type="java.lang.String">
    <value>gae:test@example.com</value>
    <value>form:admin</value>
  </util:set>

  <util:set id="adminUsers" value-type="java.lang.String">
    <value>gae:test@example.com</value>
    <value>form:admin</value>
    <value>form:user</value>
  </util:set>
  -->
```

After these preparation steps this example should be startable and you should be able
to use form based login as administrator with the username "admin".

Local Build
-----------

Build the example project.

```bash
gradle build
```

Start the development application server.

```bash
# dev_appserver -p 12380 build/war
```

Perhaps you want to leave out the update check on every startup.

```bash
dev_appserver --disable_update_check -p 12380 build/war
```

With HRD sematics needed for this example with the JDO 3 features you have to extend the call to

```bash
dev_appserver --jvm_flag=-Ddatastore.default_high_rep_job_policy_unapplied_job_pct=1  -p 12380 build/war
```

which we have a shortcut for in the build script in sync with the usual jettyRun or tomcatRun

```bash
gradle appserverRun
```

The HRD flags are mandatory with this very example but they actually depend on your data model.

If - for your later data model - you are definetely sure not to use HRD semantics, 
JDO 3.0, and so on you can set in your project's config properties file

src/main/webapp/WEB-INF/tangram/<application>.properties:
```
use.hdr.datastore=false
```

Deployment
----------

In Google App Engine create a new application with a app engine ID <aeid>. Leave 
the authentication set to "Google Accounts".

Enter the application <aeid> into appengine-web.xml, rebuild, and deploy.

```
gradle clean build
appcfg update build/war
```


Variant I - start with empty system
-----------------------------------

Call the editor and log in

http://localhost:12380/s/list

Click on the Typename org.tangram.gae.solution.RootTopic

Create an instance of class org.tangram.gae.solution.RootTopic

Create an instance of type "org.tangram.gae.Code" with annotation "org.tangram.gae.solution.Topic" and mime type text/html - enter some text as code 

Call with your browser the URL

List the RootTopic instances again and click "Web Ansicht" on your single RootTopic instance.


Variant II - use demo data
--------------------------

(Demo Data not available yet)

This is, what the python stuff from Google App Engine is used for.

Import data

```bash
bulkloader.py --restore --url=http://localhost:12380/remote_api --filename=gae-example.db
```

Better stop and start you webapp afterwards...

Call with your browser the URL

http://localhost:12380/s/
