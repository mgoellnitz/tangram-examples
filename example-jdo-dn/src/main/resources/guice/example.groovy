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
import org.tangram.util.SetupUtils
import org.tangram.components.ProtectionHook
import org.tangram.controller.ControllerHook
import org.tangram.controller.UniqueHostHook
import org.tangram.security.GenericLoginSupport
import org.tangram.security.LoginSupport
import org.tangram.servlet.MeasureTimeFilter
import org.tangram.servlet.PasswordFilter
import org.tangram.util.DummyRestartCache

log.info "starting"
String dispatcherPath = config.getProperty("dispatcherPath", "/s")

module.bindConstant().annotatedWith(Names.named("shiro.loginUrl")).to("/shiro/login.jsp")
module.bindConstant().annotatedWith(Names.named("shiro.successUrl")).to(dispatcherPath+"/list")

log.info "configuring persistent restart cache"
module.bind(PersistentRestartCache.class).toInstance(new DummyRestartCache())

log.info("configuring login support")
LoginSupport loginSupport = new GenericLoginSupport()
module.bind(LoginSupport.class).toInstance(loginSupport)

log.info("configuring controller hooks")
ControllerHook uniqueHost = new UniqueHostHook()
module.addControllerHook(uniqueHost)
ControllerHook protectionHook = new ProtectionHook()
module.addControllerHook(protectionHook)

PasswordFilter passwordFilter = new PasswordFilter()
log.info("configureServlets() password filter {} for {}", passwordFilter, dispatcherPath)
String admins = config.getProperty("adminUsers", "")
Set<String> adminUsers = SetupUtils.stringSetFromParameterString(admins)
passwordFilter.setAdminUsers(adminUsers)
passwordFilter.setLoginSupport(loginSupport)
module.filter(dispatcherPath+"/*").through(passwordFilter)

log.info("configureServlets() measure time filter for {}", dispatcherPath)
module.filter(dispatcherPath+"/*").through(new MeasureTimeFilter())

log.info "done."
println "example done."
