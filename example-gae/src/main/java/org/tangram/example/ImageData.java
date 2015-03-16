/**
 *
 * Copyright (C) 2011-2014 Martin Goellnitz
 *
 * This work is licensed under the Creative Commons Attribution 3.0
 * Unported License. To view a copy of this license, visit
 * http://creativecommons.org/licenses/by/3.0/ or send a letter to
 * Creative Commons, 444 Castro Street, Suite 900, Mountain View,
 * California, 94041, USA.
 *
 */
package org.tangram.example;

import com.google.appengine.api.datastore.Blob;
import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import org.tangram.content.blob.MimedBlob;


@PersistenceCapable
public class ImageData extends Linkable implements MimedBlob {

    @Persistent
    private Blob data;

    private String mimeType;

    private String width;

    private String height;

    @NotPersistent
    private byte[] bytes = null;


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


    @Override
    public byte[] getBytes() {
        if (bytes==null) {
            bytes = getData().getBytes();
        } // if
        return bytes;
    } // getBytes()

} // ImageData