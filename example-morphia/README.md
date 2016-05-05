# Tangram Morphia Example

Example with MongoDB's Morphia as the persistence layer using Spring, Google 
Guice, or Dinistiq for the  Application Wire-up.

## Database Preparation

This application cannot be given in a self contained form as the other 
examples, since we cannot provide a "local" MongoDB in the workspace.

You will have to prepare an external MongoDB database a fill in the 
configuration for at least one of the Dependency Injection options.

## Setup system

Build the example project

```bash
gradle build
```

(Optionally deploy the created war file to a JSP/Servlet Container of your choice)

Start the container of your choice or use integrated gretty plugin

```bash
gradle appRun
```

# First Input and Test

Call the editor and log in

```
http://localhost:12380/example-morphia/s/list
```

The user is "admin" and password is "admin" as you might find in the config files
later - in default state the whole application is protected

Create an instance of type "org.tangram.nucleus.solution.RootTopic"

Create an instance of type Code with mime-type "text/html" and annotation
"org.tangram.nucleus.solution.RootTopic"

Call with your browser the URL

```
http://localhost:12380/example-morphia/s/id_RootTopic:1
```

# Example Content

For the Morphia example you will have to import  the examples content into your
prepared database. (s.a.)

Use the generic content importer

```
http://localhost:12380/example-morphia/s/tools
```

for this purpose.
