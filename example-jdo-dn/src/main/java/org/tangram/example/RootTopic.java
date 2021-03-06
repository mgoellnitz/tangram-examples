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
import org.tangram.nucleus.Code;


@PersistenceCapable
public class RootTopic extends AbstractTopic {

    @Join
    private List<Topic> subTopics;

    @Join
    private List<Article> elements;

    @Join
    private List<Container> relatedContainers;

    @Join
    private List<Topic> bottomLinks;

    @Join
    private List<Code> css;

    @Join
    private List<Code> js;

    private ImageData logo;


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


    public List<Topic> getBottomLinks() {
        return this.bottomLinks;
    }


    public void setBottomLinks(List<Topic> bottomLinks) {
        this.bottomLinks = bottomLinks;
    }


    public List<Code> getCss() {
        return this.css;
    }


    public void setCss(List<Code> css) {
        this.css = css;
    }


    public List<Code> getJs() {
        return this.js;
    }


    public void setJs(List<Code> js) {
        this.js = js;
    }


    public ImageData getLogo() {
        return logo;
    }


    public void setLogo(ImageData logo) {
        this.logo = logo;
    }


    @Override
    public RootTopic getRootTopic() {
        return this;
    }

} // RootTopic
