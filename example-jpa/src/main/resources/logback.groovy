/**
 *
 * Copyright (C) 2015 Martin Goellnitz
 *
 * This work is licensed under the Creative Commons Attribution 3.0
 * Unported License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by/3.0/ or send a letter to
 * Creative Commons, 444 Castro Street, Suite 900, Mountain View,
 * California, 94041, USA.
 *
 */
scan '45 seconds'

String encoderPattern = '%-9date{HH:mm:ss} %-5level %logger{35}.%msg%n'
String logDir = '@logdir@'
String logFileName = "tangram-webapp"

def appenders = []
appender('FILE', RollingFileAppender) {
  file = "${logDir}/${logFileName}.log"
  append = true
  rollingPolicy(TimeBasedRollingPolicy) {
    fileNamePattern = "${logDir}/${logFileName}-%d{yyyy-MM-dd_HH}.log"
    maxHistory = 7
  }
  encoder(PatternLayoutEncoder) {
    pattern = encoderPattern
  }
}
appenders.add('FILE')

/*
appender('CONSOLE', ConsoleAppender) {
  encoder(PatternLayoutEncoder) {
    pattern = encoderPattern
  }
}
appenders.add('CONSOLE')
*/

root WARN, appenders
logger "guicy", INFO, appenders, false
logger "dinistiq", WARN, appenders, false
logger "org.tangram", DEBUG, appenders, false
logger "org.springframework", WARN, appenders, false
logger "org.springframework.beans", WARN, appenders, false
logger "org.springframework.core.io.support.PathMatchingResourcePatternResolver", INFO, appenders, false
logger "org.springframework.jmx", WARN, appenders, false
logger "org.springframework.security", WARN, appenders, false
logger "org.springframework.security.ldap", INFO, appenders, false
logger "org.springframework.web.context.ContextLoader", INFO, appenders, false
logger "org.springframework.web.filter.CommonsRequestLoggingFilter", INFO, appenders, false
