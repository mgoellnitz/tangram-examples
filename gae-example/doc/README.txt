Prerequisites:

Java (preferred still Version 6 - 32bit because of upload tool in GAE)

Google App Engine SDK Java AND Python (for the bulkloader.py you definetely want to use)

Gradle 1.3


Setup system:

# In google app engine create a new application with a app engine ID <aeid>. Leave the authentication set to "Google Accounts"

# Enter <aeid> into appengine-web.xml

# build example project
gradle build

# start app server
# dev_appserver -p 12380 build\war
#
# with new HRD sematics needed for all future versions of this example and no update check for the local SDK
dev_appserver --jvm_flag=-Ddatastore.default_high_rep_job_policy_unapplied_job_pct=20 --disable_update_check -p 12380 build\war

# (optionally deploy war/ to google app engine system - you seem to have to use 32bit Java for this!)

# Variant I - start with empty system
#####################################

# call the editor and log in
http://localhost:12380/s/list?cms.editor.class.name=org.tangram.gae.solution.RootTopic

# create an instance of RootTopic

# create an instance of type "org.tangram.gae.Code" with annotation "org.tangram.gae.solution.Topic" and mime type text/html - enter some text as code 

# call
http://localhost:12380/s/id_RootTopic:1



# Variant II - use demo data
############################

# import data
bulkloader.py --restore --url=http://localhost:12380/remote_api --filename=gae-example.db

# call
http://localhost:12380/s/
