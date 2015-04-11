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
import javax.persistence.ManyToMany;


@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorValue("Container")
public class Container extends Linkable {

    @ManyToMany
    private List<Topic> contents;


    public List<Topic> getContents() {
        return this.contents;
    }


    public void setContents(List<Topic> contents) {
        this.contents = contents;
    }

} // Container
