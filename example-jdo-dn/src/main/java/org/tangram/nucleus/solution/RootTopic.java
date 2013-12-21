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
package org.tangram.nucleus.solution;

import java.util.List;

import org.tangram.nucleus.Code;

import javax.jdo.annotations.Join;
import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class RootTopic extends Topic {

    @Join
    private List<Topic> bottomLinks;

    @Join
    private List<Code> css;

    @Join
    private List<Code> js;

    private ImageData logo;


    public List<Topic> getBottomLinks() {
        return bottomLinks;
    }


    public void setBottomLinks(List<Topic> bottomLinks) {
        this.bottomLinks = bottomLinks;
    }


    public List<Code> getCss() {
        return css;
    }


    public void setCss(List<Code> css) {
        this.css = css;
    }


    public List<Code> getJs() {
        return js;
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
