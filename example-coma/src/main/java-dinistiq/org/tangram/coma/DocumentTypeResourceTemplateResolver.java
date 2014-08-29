/**
 *
 * Copyright 2011-2014 Martin Goellnitz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package org.tangram.coma;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Map<String, String> packages = new HashMap<String, String>();


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
        if (content instanceof ComaContent) {
            ComaContent cc = (ComaContent) content;
            if (LOG.isWarnEnabled()) {
                LOG.warn("lookupView() have coma content "+cc.getId()+" :"+cc.getDocumentType());
            } // if
            String documentType = cc.getDocumentType();
            while ((view==null)&&(documentType!=null)) {
                String packName = packages.get(documentType);
                if (packName==null) {
                    packName = this.packageName;
                } // if
                if (LOG.isWarnEnabled()) {
                    LOG.warn("lookupView() checking "+packName+"/"+documentType+"#"+viewName);
                } // if
                view = checkView(viewName, packName, documentType, key, locale);
                if (LOG.isWarnEnabled()) {
                    LOG.warn("lookupView() result "+view);
                } // if
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
