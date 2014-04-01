Tangram JPA Test with Google App Engine
=======================================

This example is not yet completed, but it should be possible to build such a solution!

This will only be usable with the DataNucleus Access Platform since only this JPA implementation supports the 
Google App Engine storage backend. For now this is only a test how things should be assembled.

Customize JPA Implementation
----------------------------

The tangram jpa jar comes with NO dependency for the implementations OpenJPA, EclipseLink, 
or DataNucleus. 

Any application using the module will have to add these dependencies - DataNucleus in this case - itself.

Setup system
------------

(git clone this repository)

In Google App Engine create a new application with a app engine ID <aeid>. Leave the authentication set to "Google Accounts".

Enter the application <aeid> into appengine-web.xml.

Build the example project.

```bash
gradle build
```

Start the development application server.

```bash
# dev_appserver -p 12380 build\war
```

Perhaps you want to leave out the update check on every startup.

```bash
dev_appserver --disable_update_check -p 12380 build\war
```

With HRD sematics needed for this example with the JDO 3 features you have to extend the call to

```bash
dev_appserver --jvm_flag=-Ddatastore.default_high_rep_job_policy_unapplied_job_pct=1  -p 12380 build\war
```

which we have a shurtcut for in the build script

```bash
gradle runWar
```

The HRD flags are mandatory with this very example but they actually depend on your data model.

If for your later data model you are definetely sure not to use HRD semantics, JDO 3.0, and so on you can set in your projects's config properties file

src/main/webapp/WEB-INF/tangram/<application>.properties:
```
use.hdr.datastore=false
```

Deployment
----------

```
appcfg update build\war
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
