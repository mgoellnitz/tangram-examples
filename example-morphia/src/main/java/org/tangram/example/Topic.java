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
package org.tangram.example;

import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tangram.content.BeanFactory;
import org.tangram.content.BeanFactoryAware;
import org.tangram.content.Content;
import org.tangram.protection.ProtectedContent;


@Entity
public class Topic extends Linkable implements ProtectedContent, BeanFactoryAware<Query<?>> {

    private static final Logger LOG = LoggerFactory.getLogger(Topic.class);

    private List<Topic> subTopics;

    private List<Article> elements;

    private ImageData thumbnail;

    private char[] teaser;

    private List<Container> relatedContainers;

    @Transient
    private BeanFactory<Query<?>> beanFactory;

    @Transient
    private RootTopic rootTopic = null;

    @Transient
    private List<Topic> path;

    @Transient
    private List<Container> inheritedRelatedContainers;


    public List<Topic> getSubTopics() {
        return subTopics;
    }


    public void setSubTopics(List<Topic> subTopics) {
        this.subTopics = subTopics;
    }


    public List<Article> getElements() {
        return this.elements;
    }


    public void setElements(List<Article> elements) {
        this.elements = elements;
    }


    public ImageData getThumbnail() {
        return thumbnail;
    }


    public void setThumbnail(ImageData thumbnail) {
        this.thumbnail = thumbnail;
    }


    public char[] getTeaser() {
        return teaser;
    }


    public void setTeaser(char[] teaser) {
        this.teaser = teaser;
    }


    public List<Container> getRelatedContainers() {
        return relatedContainers;
    }


    public void setRelatedContainers(List<Container> relatedContainers) {
        this.relatedContainers = relatedContainers;
    }


    @Override
    public void setBeanFactory(BeanFactory<Query<?>> beanFactory) {
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
