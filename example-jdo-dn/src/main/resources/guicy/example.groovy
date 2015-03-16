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

module.bindConstant().annotatedWith(Names.named("shiro.loginUrl")).to("/shiro/login.jsp")
module.bindConstant().annotatedWith(Names.named("shiro.successUrl")).to(dispatcherPath+"/list")

log.info "configuring persistent restart cache"
module.bind(PersistentRestartCache.class).toInstance(new DummyRestartCache())

log.info("configuring name password mapping")
Map<String,String> mapping = new HashMap<>()
mapping.put('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918')
mapping.put('user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb')
module.bind(module.stringStringMap).annotatedWith(Names.named("usernamePasswordMapping")).toInstance(mapping)

log.info("configuring authentication clients")
YahooOpenIdClient yahooOpenIdClient = new YahooOpenIdClient()
yahooOpenIdClient.name='yahoo'
module.addClient(yahooOpenIdClient)

// optional
//log.info("configuring controller hooks")
//ControllerHook uniqueHost = new UniqueHostHook()
//uniqueHost.setHost('example.org')
//module.addControllerHook(uniqueHost)

log.info("configureServlets() measure time filter for {}", dispatcherPath)
module.filter(dispatcherPath+"/*").through(new MeasureTimeFilter())

log.info "done."
println "example done."
