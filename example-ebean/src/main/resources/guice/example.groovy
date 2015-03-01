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
import com.google.inject.name.Names;
import org.tangram.PersistentRestartCache
import org.tangram.controller.ControllerHook
import org.tangram.controller.UniqueHostHook
import org.tangram.servlet.MeasureTimeFilter
import org.tangram.servlet.PasswordFilter
import org.tangram.util.DummyRestartCache
import org.pac4j.core.client.Client
import org.pac4j.openid.client.YahooOpenIdClient

log.info "starting"
String dispatcherPath = config.getProperty("dispatcherPath", "/s")

log.info "configuring persistent restart cache"
module.bind(PersistentRestartCache.class).toInstance(new DummyRestartCache())

log.info("configuring name password mapping")
Map<String,String> mapping = new HashMap<>()
mapping.put('admin', 'admin')
mapping.put('user', 'user')
module.bind(module.stringStringMap).annotatedWith(Names.named("usernamePasswordMapping")).toInstance(mapping)

log.info("configuring authentication clients")
YahooOpenIdClient yahooOpenIdClient = new YahooOpenIdClient()
yahooOpenIdClient.name='yahoo'
module.addClient(yahooOpenIdClient)

log.info("configuring controller hooks")
ControllerHook uniqueHost = new UniqueHostHook()
// set host
module.addControllerHook(uniqueHost)

log.info("configureServlets() measure time filter for {}", dispatcherPath)
module.filter(dispatcherPath+"/*").through(new MeasureTimeFilter())

log.info "done."
println "example done."
