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

import javax.persistence.Column;
import javax.persistence.Entity;
import org.tangram.content.blob.MimedBlob;


@Entity
public class ImageData extends Linkable implements MimedBlob {

    @Column(length = 3000000)
    private byte[] data;

    private String mimeType;

    private String width;

    private String height;


    public byte[] getData() {
        return data;
    }


    public void setData(byte[] data) {
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
        return getData();
    } // getBytes()

} // ImageData
