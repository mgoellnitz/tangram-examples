Setup system:

# In google app engine create a new application with a app engine ID <aeid>. Leave the authentication set to "Google Accounts"

# Enter <aeid> into appengine-web.xml

# build example project
gradle

# start app server
dev_appserver -p 12380 build\war
# with new HRD sematics needed for all future versions of this example and no update check for the local SDK
dev_appserver --hvm_flag=-Ddatastore.default_high_rep_job_policy_unapplied_job_pct=20 --disable_update_check -p 12380 build\war

# (optionally deploy war/ to google app engine system - you seem to have to use 32bit Java for this!)

# Variant I - use demo data

# import data
bulkloader.py --restore --url=http://localhost:12380/remote_api --filename=gae-example.db

# call
http://localhost:12380/s/


# Variant II - start with empty system

# call the editor and log in
http://localhost:12380/s/list?cms.editor.class.name=org.tangram.gae.Code
http://localhost:12380/s/list?cms.editor.class.name=org.tangram.gae.solution.RootTopic

# create an instance of type "RootTopic"

# create an instance of type Code with mime-type "text/html" and annotation "org.tangram.gae.solution.RootTopic"

# call
http://localhost:12380/s/
