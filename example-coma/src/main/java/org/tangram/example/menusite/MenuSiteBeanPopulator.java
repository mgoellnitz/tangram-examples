/**
 *
 * Copyright (C) 2011-2017 Martin Goellnitz
 *
 * This work is licensed under the Creative Commons Attribution 3.0
 * Unported License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by/3.0/ or send a letter to
 * Creative Commons, 444 Castro Street, Suite 900, Mountain View,
 * California, 94041, USA.
 *
 */
package org.tangram.example.menusite;

import java.util.ArrayList;
import java.util.Collection;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.tangram.coma.ComaBeanFactory;
import org.tangram.coma.ComaBeanPopulator;
import org.tangram.coma.ComaContent;
import org.tangram.content.Content;

@Named
@Singleton
public class MenuSiteBeanPopulator implements ComaBeanPopulator {

    @Inject
    private ComaBeanFactory beanFactory;

    @Override
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
            Collection<Content> beanCollection = new ArrayList<>();
            for (String id : idCollection) {
                beanCollection.add(beanFactory.getBean(id));
            } // for
            content.put("dishes", beanCollection);
        } // if
    } // populate()

} // MenuSiteBeanPopulator
