package org.tangram.gae.solution;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

import org.tangram.gae.Code;

@PersistenceCapable
public class RootTopic extends Topic {

    private List<String> bottomLinkIds;

    private List<String> cssIds;

    private List<String> jsIds;

    private String logoId;


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
        return getContent(ImageData.class, logoId);
    }


    public void setLogo(ImageData logo) {
        this.logoId = logo.getId();
    }


    @Override
    public RootTopic getRootTopic() {
        return this;
    } // getRootTopic()

} // RootTopic
