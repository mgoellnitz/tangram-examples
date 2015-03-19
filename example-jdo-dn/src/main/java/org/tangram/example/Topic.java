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

import java.util.List;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;


@PersistenceCapable
public class Topic extends AbstractTopic {

    @Join
    private List<Topic> subTopics;

    @Join
    private List<Article> elements;

    @Join
    private List<Container> relatedContainers;


    @Override
    public List<Topic> getSubTopics() {
        return subTopics;
    }


    public void setSubTopics(List<Topic> subTopics) {
        this.subTopics = subTopics;
    }


    @Override
    public List<Article> getElements() {
        return this.elements;
    }


    public void setElements(List<Article> elements) {
        this.elements = elements;
    }


    @Override
    public List<Container> getRelatedContainers() {
        return relatedContainers;
    }


    public void setRelatedContainers(List<Container> relatedContainers) {
        this.relatedContainers = relatedContainers;
    }

} // Topic
