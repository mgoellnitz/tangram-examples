/**
 * 
 * Copyright (C) 2011-2013 Martin Goellnitz
 * 
 * This work is licensed under the Creative Commons Attribution 3.0 
 * Unported License. To view a copy of this license, visit 
 * http://creativecommons.org/licenses/by/3.0/ or send a letter to 
 * Creative Commons, 444 Castro Street, Suite 900, Mountain View, 
 * California, 94041, USA.
 * 
 */
package org.tangram.mongo.solution;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

import org.tangram.mongo.Code;

@PersistenceCapable
public class RootTopic extends Topic {

    private List<String> bottomLinkIds;

    private List<String> cssIds;

    private List<String> jsIds;

    private ImageData logo;


    public List<Topic> getBottomLinks() {
        return getContents(Topic.class, bottomLinkIds);
    }


    public void setBottomLinks(List<Topic> bottomLinks) {
        bottomLinkIds = getIds(bottomLinks);
    }


    public List<Code> getCss() {
        return getContents(Code.class, cssIds);
    }


    public void setCss(List<Code> css) {
        cssIds = getIds(css);
    }


    public List<Code> getJs() {
        return getContents(Code.class, jsIds);
    }


    public void setJs(List<Code> js) {
        jsIds = getIds(js);
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
    } // getRootTopic()

} // RootTopic
