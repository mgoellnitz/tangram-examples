/**
 *
 * Copyright (C) 2011-2014 Martin Goellnitz
 *
 * This work is licensed under the Creative Commons Attribution 3.0
 * Unported License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by/3.0/ or send a letter to
 * Creative Commons, 444 Castro Street, Suite 900, Mountain View,
 * California, 94041, USA.
 *
 */
package org.tangram.nucleus.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tangram.content.BeanFactory;
import org.tangram.content.BeanFactoryAware;
import org.tangram.content.Content;
import org.tangram.feature.protection.ProtectedContent;


@PersistenceCapable
public class Topic extends Linkable implements ProtectedContent, BeanFactoryAware {

    private static final Logger LOG = LoggerFactory.getLogger(Topic.class);

    @Join
    private List<Topic> subTopics;

    @Join
    private List<Article> elements;

    private ImageData thumbnail;

    private char[] teaser;

    @Join
    private List<Container> relatedContainers;

    @NotPersistent
    private BeanFactory beanFactory;


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


    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }


    /**
     * ********************************
     */

    @NotPersistent
    RootTopic rootTopic = null;


    public RootTopic getRootTopic() {
        if (rootTopic==null) {
            List<RootTopic> rootTopics = beanFactory.listBeans(RootTopic.class, null);
            if (rootTopics!=null) {
                if (rootTopics.size()>0) {
                    rootTopic = rootTopics.get(0);
                } // if
            } // if
        } // if
        return rootTopic;
    } // getRootTopic()


    public List<Topic> getPathRecursive(Topic t) {
        List<Topic> result = null;
        if (equals(t)) {
            result = new ArrayList<Topic>();
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
    } // getPath()


    @Override
    public String toString() {
        return "Topic [subTopics="+getSubTopics()+", elements="+getElements()+", thumbnail="+thumbnail+", teaser="
                +Arrays.toString(getTeaser())+", relatedContainers="+getRelatedContainers()+", rootTopic="+rootTopic
                +", inheritedRelatedContainers="+inheritedRelatedContainers+"]";
    } // toString()

    @NotPersistent
    private List<Topic> path;


    public List<Topic> getPath() {
        if (LOG.isDebugEnabled()) {
            LOG.debug(getClass().getName()+".getPath("+getId()+") "+getRootTopic().getClass().getName());
        } // if
        if (path==null) {
            path = getPathRecursive(getRootTopic());
            if (path==null) {
                path = new ArrayList<Topic>();
                // path.add(getRootTopic());
                path.add(this);
            } // if
        } // if
        return path;
    } // getPath()

    @NotPersistent
    private List<Container> inheritedRelatedContainers;


    public List<Container> getInheritedRelatedContainers() {
        List<Container> result = new ArrayList<Container>();
        if (inheritedRelatedContainers==null) {
            List<Topic> p = getPath();
            int i = p.size();
            while ((--i>=0)&&(result.size()==0)) {
                result = p.get(i).getRelatedContainers();
            } // while
            inheritedRelatedContainers = result;
        } else {
            result = inheritedRelatedContainers;
        } // if
        return result;
    } // getInheritedRelatedContainers


    /**
     * ** Protections **
     */

    @Override
    public List<? extends Content> getProtectionPath() {
        return getPath();
    } // getProtectionPath()

} // Topic
