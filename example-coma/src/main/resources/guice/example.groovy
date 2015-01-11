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
import com.google.inject.multibindings.Multibinder
import java.util.Collection
import java.util.HashSet
import org.tangram.Constants
import org.tangram.util.SetupUtils
import org.tangram.content.BeanFactory
import org.tangram.coma.AbstractComaBeanFactory
import org.tangram.coma.ComaBeanFactory
import org.tangram.coma.ComaBeanPopulator
import org.tangram.example.menusite.MenuSiteBeanPopulator
import org.tangram.example.coma.DocumentTypeResourceTemplateResolver

log.info "starting"

log.info "configuring bean populator"
Multibinder<ComaBeanPopulator> populatorBinder = Multibinder.newSetBinder(module.binder(), ComaBeanPopulator.class);;
ComaBeanPopulator beanPopulator = new MenuSiteBeanPopulator()
populatorBinder.addBinding().toInstance(beanPopulator)

log.info "configuring document type parent relationship"
Map<String,String> parents = new HashMap<>()
parents.put("Dish", "Document")
parents.put("Picture", "Document")
parents.put("Page", "Document")
parents.put("Category", "Document")
parents.put("ContentRule", "Document")

log.info "configuring template resolver"
DocumentTypeResourceTemplateResolver comaTemplateResolver = new DocumentTypeResourceTemplateResolver()
comaTemplateResolver.setName('CM-JSP')
comaTemplateResolver.setPackageName('com.coremedia.examples.cae.beans')
module.addTemplateResolver(comaTemplateResolver)

log.info "configuring bean factory"
Set<String> basePackages = SetupUtils.stringSetFromParameterString(config.getProperty("basePackages", "org.tangram"))
ComaBeanFactory beanFactory = new ComaBeanFactory()
beanFactory.setParents(parents)
beanFactory.setDbDriver(config.getProperty("coma.db.driver", "com.mysql.jdbc.Driver"))
beanFactory.setDbUrl(config.getProperty("coma.db.url", "jdbc:mysql://localhost:3306/cm7replication"))
beanFactory.setDbUser(config.getProperty("coma.db.user", "cm7replication"))
beanFactory.setDbPassword(config.getProperty("coma.db.password", "cm7replication"))

module.getServletContext().setAttribute(Constants.ATTRIBUTE_BEAN_FACTORY, beanFactory)
module.bind(BeanFactory.class).toInstance(beanFactory)
module.bind(AbstractComaBeanFactory.class).toInstance(beanFactory)
module.bind(ComaBeanFactory.class).toInstance(beanFactory)

log.info "done."
println "example done."
