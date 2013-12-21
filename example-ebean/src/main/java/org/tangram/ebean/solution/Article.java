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
package org.tangram.ebean.solution;

import javax.persistence.Entity;

@Entity
public class Article extends Linkable {

    private char[] text;


    public char[] getText() {
        return text;
    }


    public void setText(char[] text) {
        this.text = text;
    }

} // Article