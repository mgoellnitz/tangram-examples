package org.tangram.gae.solution;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable
public class Container extends Linkable {

    private List<String> contentIds;


    public List<Topic> getContents() {
        return getContents(Topic.class, contentIds);
    }


    public void setContents(List<Topic> contents) {
        contentIds = getIds(contents);
    }

} // Container
