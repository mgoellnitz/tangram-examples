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
package org.tangram.gae.solution;

import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Blob;

import org.tangram.jdo.util.MimedBlob;

@PersistenceCapable
@Inheritance(customStrategy = "complete-table")
public class ImageData extends Linkable implements MimedBlob {

    @Persistent
    private Blob data;
    
    private String mimeType;

    private String width;

    private String height;


    public Blob getData() {
        return data;
    }


    public void setData(Blob data) {
        this.data = data;
    }


    @Override
    public String getMimeType() {
        return mimeType;
    }


    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }


    public String getWidth() {
        return width;
    }


    public void setWidth(String width) {
        this.width = width;
    }


    public String getHeight() {
        return height;
    }


    public void setHeight(String height) {
        this.height = height;
    }

    @NotPersistent
    byte[] bytes = null;

    @Override
    public byte[] getBytes() {
        if (bytes == null) {
            bytes = getData().getBytes();
        } // if
        return bytes;
    } // getBytes()

} // ImageData
