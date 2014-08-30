Google App Engine example or starting point
===========================================

Prerequisites
-------------

* Java 7
* Gradle 1.8 and up (Including 2.0)
* Google App Engine SDK Java AND Python (for the bulkloader.py you definetely want to use)

CapeDwarf
---------

This project is known not to work with the CapeDward Google App Engine Emulator.

To give it a try you will need to compile tangram from a special CapeDwarf branch
which gets rid of the already present Java EE CDI configurations. These interfere
with the underlying Java EE server. (Don't forget to add mavenLocal() as a repository
when building the example.)

With the Springframework in use it is startable but the login fails (The UserService 
API returns null despite the successful login)

With dinistiq we use Servlet Filters which depend on injections which are in turn 
interpreted by the CDI module in the underlying Java EE server regardless of other 
options we might try to avoid this.

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
