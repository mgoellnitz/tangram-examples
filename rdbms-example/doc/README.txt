Setup system:

# build example project
gradle build

# optionally deploy war/webapp directory to a JSP/Servlet Container of your choice

# start webapp container or use integrated jetty
gradle jettyRunWar

# Variant I - use demo data
###########################

# import data
# data is ready to use with pre-configured hsqldb

# call
http://localhost:12380/s/

# user is admin and password is opal - in default state the whole application is protected

# hsqldb tool:
java -cp build/war/WEB-INF/lib/hsqldb-1.8.0.10.jar org.hsqldb.util.DatabaseManager

# issue shutdown to compress database again

# or directly like
java -cp build/war/WEB-INF/lib/hsqldb-1.8.0.10.jar org.hsqldb.util.SqlTool --inlineRc url=jdbc:hsqldb:file:tangram-rdbms,password=,user=sa --sql "shutdown;"



# Variant II - start with empty system
######################################

# call the editor and log in
http://localhost:12380/rdbms-example/s/list?cms.editor.class.name=org.tangram.rdbms.solution.RootTopic

# user is admin and password is opal - in default state the whole application is protected

# create an instance of type "RootTopic"

# create an instance of type Code with mime-type "text/html" and annotation "org.tangram.rdbms.solution.RootTopic"

# call
http://localhost:12380/s/id_RootTopic:1



# Customize Database System to use
##################################

# Setup database user with schema and sufficient write persmissions

# PostgreSQL example:

psql -U postgres

create user tex password 'tex';
create schema tex authorization tex;
\q

# update jdoconfig.xml to reflect this

<property name="javax.jdo.option.ConnectionDriverName" value="org.postgresql.Driver" />
<property name="javax.jdo.option.ConnectionURL" value="jdbc:postgresql://localhost:5432/postgres"/>
<property name="javax.jdo.option.ConnectionUserName" value="tex" />
<property name="javax.jdo.option.ConnectionPassword" value="tex" />
<property name="javax.jdo.option.Mapping" value="postgres" />

# update build.gradle to include db driver in project
compile "postgresql:postgresql:8.4-702.jdbc4"

