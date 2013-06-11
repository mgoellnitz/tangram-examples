package org.tangram.examples.menusite;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.tangram.coma.ComaBeanFactory;
import org.tangram.coma.ComaBeanPopulator;
import org.tangram.coma.ComaContent;

public class MenuSiteBeanPopulator implements ComaBeanPopulator {

    @SuppressWarnings("unused")
    private static Log log = LogFactory.getLog(MenuSiteBeanPopulator.class);

    @Autowired
    private ComaBeanFactory beanFactory;

    @Override
    @SuppressWarnings("unchecked")
    public void populate(ComaContent content) {
        Collection<String> idCollection = beanFactory.listIds("Page", null, "id_", true);
        if (idCollection.size() > 0) {
            String id = idCollection.iterator().next();
            content.put("page", beanFactory.getBean(id));
        } // if
        // Why on earth did they call it that way?
        if ("Page".equals(content.getDocumentType())) {
            content.put("content_", content.get("content"));
        } // if
        if ("Category".equals(content.getDocumentType())) {
            String folderId = beanFactory.getChildId("/MenuSite/"+content.get("name"));
            idCollection = beanFactory.getChildrenIds(folderId, "Dish", null);
            @SuppressWarnings("rawtypes")
            Collection beanCollection = new ArrayList<ComaContent>();
            for (String id : idCollection) {
                beanCollection.add(beanFactory.getBean(id));
            } // for
            content.put("dishes", beanCollection);
        } // if
    } // populate()

} // MenuSiteBeanPopulator