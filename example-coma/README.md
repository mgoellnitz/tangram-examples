(Too) Simple CoreMedia Adaptor CoMA for Tangram
===============================================

This example illustrates the use of the CoreMedia Adaptor of Tangram 
(Now tested with CoreMedia 7) to view simple content models in a relatively direct 
manner - so with a very lean/little/no business logic.

Running this application is a little more complicated than compiling and starting 
like most of the other Tangram examples. This is due to the fact that we rely on 
your CoreMedia CMS Database and Content being in place. CoMA is  no replacement 
but an addendum to your existing CoreMedia CMS infrastructure in Versions CMS 2008 
and CoreMedia 7! (We left out testing with Version 6)

There is not much code and no templates in this directory since the application
we we is the very small example from CoreMedia, the MenuSite, which was last published 
with version CMS 2008 at

https://releases.coremedia.com/cmv/16/cms/5.2.1456/cap-examples.jar

Prerequisites
-------------

The main topic for this code to be assempled to a  complete application is access
to the CoreMedia repositories to access the example codes.

The build script expects a 

```
coremediaUsername=me
coremediaPassword=mypassword
```

in your personal gradle.properties located in your home folder or somewhere your
GRADLE_USER_HOME variable points to. These are your CoreMedia Partner/Customer Portal 
credentials you will have at hand if you are in into the matter. If in doubt ask
you friendly CoreMedia support staff.

A MenuSite database created with a CoreMedia Content Server. Any server role - Content 
Management Server, Master Live Server, or Replication Live Server will do.

You can prepare a server installation from any of the CoreMedia provided Apache 
Maven based workspaces and customize it with the needed MenuSite document type 
definitions and content download URL (s.a.).

The Gradle assembly demonstration for a CoreMedia Content Management Server at 
https://github.com/mgoellnitz/cm-cms-webapp in the branch 'menusite' will do the 
job. And the Gradle s/assembly demonstration for the CoreMedia Content Management
Server Tools at https://github.com/mgoellnitz/cm-cms-tools are automatically
armored with the content to be imported into the server.

Tangram Coma Preparation
------------------------

Check the db connection section in the example.properties file you are going to 
use - dinistiq based or with springframework - and modify the settings to point
to the aforementioned MenuSite database.

After having entered this, you can build the application with

```
gradle
```

which will execute Gradle's clean and build tasks. 

To change the backend to be used - dinistiq or springframework - you don't have
to change the build file gut can pass a property.

```
gradle -Pbackend=spring
```

The application can be started in place with the Gradle built in jetty with

```
gradle jettyRunWar
```

Despite the fact that we use the very old (read: obsolete) version of Jetty built
into the Gradle releases, the example  will work that way since our packaging was
"old style" with the templates not placed within JARs.


Detailed Server Database Preparation
------------------------------------

Create a Server 

Clone the repository at https://github.com/mgoellnitz/cm-cms-webapp, checkout
the MenuSite branch. And add a valid license file. Development licenses for
Partners and Customers are available from the friendly CoreMedia support staff.

```
git clone https://github.com/mgoellnitz/cm-cms-webapp
cd cm-cms-webapp
git checkout menusite
cp .../license.zip src/main/webapp/WEB-INF/properties/corem
# check sql.properties to point to your database (s.a.)
gradle
```

Copy the created web archive from build/libs to Tomcat container residing somewhere. 
No, the Tomcat plugin won't do due to some classpath loading defects. Note the
http port of the Tomcat or change it to the assumed default of 44441. Start your
new Content Management Server by starting the Tomcat container.

Clone the repository at https://github.com/mgoellnitz/cm-cms-tools and assemble the
Content Management Server Tools.

```
git clone https://github.com/mgoellnitz/cm-cms-tools
cd cm-cms-tools
gradle
cd build/tmp
unzip ../distribution/cm*.zip 
rmdir lib
mv cm-*/lib .
bin/cm systeminfo -u admin -p admin
```

The last command should present a server state message and no error. If your server
is not listening at the default port 44441 and on localhost you can pass a URL
parameter with every command or change the default in properties/corem/capclient.properties.

Now import the content included with the tools package with

```
bin/cm serverimport -u admin -p admin -r MenuSite
```

To check if the import was successful, use the dump tool (Or a CoreMedia SiteManager)

```
bin/cm dump -u admin -p admin -t /
```

You should now find a MenuSite folder in your repository. If this last step was
executed successfully, you can stop the server and use the created database for
the Tangram CoMA example as shown above.
