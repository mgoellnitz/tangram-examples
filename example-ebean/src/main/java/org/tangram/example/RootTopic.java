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
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import org.tangram.ebean.Code;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorValue("RootTopic")
public class RootTopic extends Topic {

    @ManyToMany
    @JoinTable(name = "bottomlinks", joinColumns = @JoinColumn(name="topic", referencedColumnName = "EBEAN_INTERNAL_ID"), inverseJoinColumns = @JoinColumn(name="link", referencedColumnName = "EBEAN_INTERNAL_ID"))
    private List<Topic> bottomLinks;

    @ManyToMany
    @JoinTable(name = "css")
    private List<Code> css;

    @ManyToMany
    @JoinTable(name = "js")
    private List<Code> js;

    private ImageData logo;


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
    } // getRootTopic()

} // RootTopic
