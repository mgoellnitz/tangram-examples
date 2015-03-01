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
package org.tangram.gae.solution;

import com.google.appengine.api.datastore.Text;
import java.util.ArrayList;
import java.util.List;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tangram.content.BeanFactory;
import org.tangram.content.BeanFactoryAware;
import org.tangram.content.Content;
import org.tangram.protection.ProtectedContent;


@PersistenceCapable
public class Topic extends Linkable implements ProtectedContent, BeanFactoryAware {

    private static final Logger LOG = LoggerFactory.getLogger(Topic.class);

    private List<Topic> subTopics;

    private List<Topic> contents;

    private ImageData thumbnail;

    @Persistent
    private Text teaser;

    private List<Container> relatedContainers;

    @NotPersistent
    private BeanFactory beanFactory;

    @NotPersistent
    private RootTopic rootTopic = null;

    @NotPersistent
    private List<Topic> path;

    @NotPersistent
    private List<Container> inheritedRelatedContainers;


    public List<Topic> getSubTopics() {
        return subTopics;
    }


    public void setSubTopics(List<Topic> subTopics) {
        this.subTopics = subTopics;
    }


    public List<Topic> getContents() {
        return contents;
    }


    public void setContents(List<Topic> contents) {
        this.contents = contents;
    }


    public ImageData getThumbnail() {
        return thumbnail;
    }


    public void setThumbnail(ImageData thumbnail) {
        this.thumbnail = thumbnail;
    }


    public Text getTeaser() {
        return teaser;
    }


    public void setTeaser(Text teaser) {
        this.teaser = teaser;
    }


    public List<Container> getRelatedContainers() {
        return relatedContainers;
    }


    public void setRelatedContainers(List<Container> relatedContainers) {
        this.relatedContainers = relatedContainers;
    }


    public void setBeanFactory(BeanFactory beanFactory) {
        // The super-call is in fact only necessary if you want to use the deprecated methods in GaeContent
        super.setBeanFactory(beanFactory);
        this.beanFactory = beanFactory;
    }


    /*
     * *********************************
     */
    public RootTopic getRootTopic() {
        if (rootTopic==null) {
            List<RootTopic> rootTopics = beanFactory.listBeans(RootTopic.class, null);
            if ((rootTopics!=null)&&(rootTopics.size()>0)) {
                rootTopic = rootTopics.get(0);
            } // if
        } // if
        return rootTopic;
    } // getRootTopic()


    public List<Topic> getPathRecursive(Topic t) {
        List<Topic> result = null;
        if (equals(t)) {
            result = new ArrayList<>();
            result.add(t);
        } else {
            List<Topic> subs = t.getSubTopics();
            if (subs!=null) {
                for (Topic x : subs) {
                    if (x!=null) {
                        List<Topic> p = getPathRecursive(x);
                        if (p!=null) {
                            p.add(0, t);
                            return p;
                        } // if
                    } else {
                        LOG.error("getPathRecursive() "+t.getId()+" has null pointer subtopic");
                    } // if
                } // for
            } // if
        } // if

        return result;
    } // getPathRecursive()


    public List<Topic> getPath() {
        LOG.debug("{}.getPath({}) {}", getClass().getName(), getId(), getRootTopic().getClass().getName());
        if (path==null) {
            path = getPathRecursive(getRootTopic());
            if (path==null) {
                path = new ArrayList<>();
                path.add(this);
            } // if
        } // if
        return path;
    } // getPath()


    public List<Container> getInheritedRelatedContainers() {
        List<Container> result = new ArrayList<>();
        if (inheritedRelatedContainers==null) {
            List<Topic> p = getPath();
            int i = p.size();
            while ((i>0)&&(result.isEmpty())) {
                i--;
                result = p.get(i).getRelatedContainers();
            } // while
            inheritedRelatedContainers = result;
        } else {
            result = inheritedRelatedContainers;
        } // if
        return result;
    } // getInheritedRelatedContainers


    /*
     * ** Protections **
     */
    @Override
    public List<? extends Content> getProtectionPath() {
        return getPath();
    } // getProtectionPath()

} // Topic
