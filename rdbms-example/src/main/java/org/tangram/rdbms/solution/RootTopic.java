/**
 * 
 * Copyright 2011-2013 Martin Goellnitz
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
package org.tangram.rdbms.solution;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;

import org.tangram.rdbms.Code;

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
