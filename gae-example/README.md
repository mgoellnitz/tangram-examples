Prerequisite
=========

Java (preferred still Version 6 - 32bit because of upload tool in GAE)

Google App Engine SDK Java AND Python (for the bulkloader.py you definetely want to use)

Gradle 1.3


Setup system
=========

(git clone this repository)

In google app engine create a new application with a app engine ID <aeid>. Leave the authentication set to "Google Accounts"

Enter the applcation <aeid> into appengine-web.xml

Build the example project

```bash
gradle build
```

Start the development application server

```bash
# dev_appserver -p 12380 build\war
```

Leave out the update check on every startup

```bash
dev_appserver --disable_update_check -p 12380 build\war
```

With new HRD sematics needed for all future versions of this example you have to extend the call to

```bash
dev_appserver --jvm_flag=-Ddatastore.default_high_rep_job_policy_unapplied_job_pct=1  -p 12380 build\war
```

which we have a shurtcut for in the build script

```bash
gradle runWar
```

The HRD flags are mandatory with this very example but they actually depend on your data model.

(optionally deploy war/ to google app engine system - you seem to have to use 32bit Java for this!)

If for your later data model you are definetely sure not to use HRD semantics, JDO 3.0, and so on you can set in your projects's config properties file


```
use.hdr.datastore=false
```


Variant I - start with empty system
=======================

Call the editor and log in

http://localhost:12380/s/list

Click on the Typename org.tangram.gae.solution.RootTopic

(Or directly call http://localhost:12380/s/list?cms.editor.class.name=org.tangram.gae.solution.RootTopic)

Create an instance of class org.tangram.gae.solution.RootTopic

Create an instance of type "org.tangram.gae.Code" with annotation "org.tangram.gae.solution.Topic" and mime type text/html - enter some text as code 

Call with your browser the URL

http://localhost:12380/s/id_RootTopic:1



Variant II - use demo data
==================

Import data

```bash
bulkloader.py --restore --url=http://localhost:12380/remote_api --filename=gae-example.db
```

Call with your browser the URL

http://localhost:12380/s/
