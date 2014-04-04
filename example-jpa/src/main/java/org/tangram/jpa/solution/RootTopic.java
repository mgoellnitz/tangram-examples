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
package org.tangram.jpa.solution;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.tangram.jpa.Code;

@Entity
// Annotation needed for OpenJPA
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RootTopic extends Topic {

    // Annotation needed for OpenJPA
    @OneToMany
    // Otherwise Eclipselink builds one Join Table for Topic.elements and RootTopic.bottomLinks alike with non-null attributes - br
    @JoinTable(name = "RootTopicBottomLinks")
    private List<Topic> bottomLinks;

    // Annotation needed for OpenJPA
    @OneToMany
    // Otherwise Eclipselink builds one Join Table for CSS and JS alike with non-null attributes - br
    @JoinTable(name = "RootTopicCSS")
    private List<Code> css;

    // Annotation needed for OpenJPA
    @OneToMany
    // Otherwise Eclipselink builds one Join Table for CSS and JS alike with non-null attributes - br
    @JoinTable(name = "RootTopicJS")
    private List<Code> js;

    // Annotation needed for OpenJPA
    @OneToOne
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
