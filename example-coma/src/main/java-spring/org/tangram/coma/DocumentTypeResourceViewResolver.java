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
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;
import org.tangram.spring.view.ModelAwareInternalResourceViewResolver;
import org.tangram.view.TemplateResolver;


/**
 * View resolver avoiding the creation of a bean layer by using the document type and dummy package names.
 */
public class DocumentTypeResourceViewResolver extends ModelAwareInternalResourceViewResolver {

    private static final Logger LOG = LoggerFactory.getLogger(DocumentTypeResourceViewResolver.class);

    @Inject
    private AbstractComaBeanFactory beanFactory;

    private String packageName;

    private Map<String, String> packages = new HashMap<>();


    public DocumentTypeResourceViewResolver() {
        // Due to cache key creation this resolver does not work with caching of template entries
        setActivateCaching(false);
    } // DocumentTypeResourceViewResolver()


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
    protected View lookupView(String viewName, Locale locale, Object content, String key) throws IOException {
        View view = null;
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
    public int compareTo(TemplateResolver<View> o) {
        return -1;
    } // compareTo

} // DocumentTypeResourceViewResolver
