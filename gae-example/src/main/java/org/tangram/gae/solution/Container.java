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
package org.tangram.gae.solution;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Container extends Linkable {

    private List<Linkable> contents;


    public List<Linkable> getContents() {
        return contents;
    }


    public void setContents(List<Linkable> contents) {
        this.contents = contents;
    }

} // Container
