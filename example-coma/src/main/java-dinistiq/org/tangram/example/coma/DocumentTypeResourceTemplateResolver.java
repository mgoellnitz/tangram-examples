/**
 *
 * Copyright (C) 2011-2015 Martin Goellnitz
 *
 * This work is licensed under the Creative Commons Attribution 3.0
 * Unported License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by/3.0/ or send a letter to
 * Creative Commons, 444 Castro Street, Suite 900, Mountain View,
 * California, 94041, USA.
 *
 */
package org.tangram.example.coma;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tangram.coma.AbstractComaBeanFactory;
import org.tangram.coma.ComaContent;
import org.tangram.servlet.JspTemplateResolver;
import org.tangram.view.TemplateResolver;


/**
 * View resolver avoiding the creation of a bean layer by using the document type and dummy package names.
 */
public class DocumentTypeResourceTemplateResolver extends JspTemplateResolver {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentTypeResourceTemplateResolver.class);

    @Inject
    private AbstractComaBeanFactory beanFactory;

    private String packageName;

    private Map<String, String> packages = new HashMap<>();


    public DocumentTypeResourceTemplateResolver() {
        // Due to cache key creation this resolver does not work with caching of template entries
        setActivateCaching(false);
    } // DocumentTypeResourceTemplateResolver()


    public String getPackageName() {
        return packageName;
    }


    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }


    public Map<String, String> getPackages() {
        return packages;
    }


    public void setPackages(Map<String, String> packages) {
        this.packages = packages;
    }


    @Override
    protected String lookupView(String viewName, Locale locale, Object content, String key) throws IOException {
        String view = null;
        LOG.debug("lookupView() checking for coma content: {}", content);
        if (content instanceof ComaContent) {
            ComaContent cc = (ComaContent) content;
            LOG.info("lookupView() have coma content {} :{}", cc.getId(), cc.getDocumentType());
            String documentType = cc.getDocumentType();
            while ((view==null)&&(documentType!=null)) {
                String packName = packages.get(documentType);
                if (packName==null) {
                    packName = this.packageName;
                } // if
                LOG.info("lookupView() checking {}/{}#{}", packName, documentType, viewName);
                view = checkView(viewName, packName, documentType, key, locale);
                LOG.info("lookupView() result {}", view);
                // TODO: How about fake interfaces?
                // if (view==null) {
                // for (Class<? extends Object> c : cls.getInterfaces()) {
                // view = checkView(viewName, c.getPackage().getName(), c.getSimpleName(), key, locale);
                // if (view!=null) {
                // break;
                // } // if
                // } // for
                // } // if
                documentType = beanFactory.getParents().get(documentType);
            } // while
        } // if
        return view;
    } // lookupView()


    @Override
    public int compareTo(TemplateResolver<String> o) {
        return -1;
    } // compareTo

} // DocumentTypeResourceTemplateResolver
