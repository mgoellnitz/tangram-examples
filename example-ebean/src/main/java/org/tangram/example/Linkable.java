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

import javax.persistence.MappedSuperclass;
import org.tangram.content.Content;
import org.tangram.ebean.EContent;


@MappedSuperclass
public abstract class Linkable extends EContent {

    private String title;

    private String shortTitle;

    private String keywords;


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getShortTitle() {
        return shortTitle;
    }


    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }


    public String getKeywords() {
        return keywords;
    }


    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }


    @Override
    public int compareTo(Content o) {
        return (o instanceof Linkable) ? ((getTitle()==null||((Linkable) o).getTitle()==null) ? 0 : getTitle()
                .compareTo(((Linkable) o).getTitle())) : super.compareTo(o);
    } // compareTo()

} // Linkable
