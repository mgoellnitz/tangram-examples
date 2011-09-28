package org.tangram.gae.solution;

import javax.jdo.annotations.NotPersistent;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Blob;

@PersistenceCapable
public class ImageData extends Linkable {

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


    public byte[] getBytes() {
        if (bytes==null) {
            bytes = getData().getBytes();
        } // if
        return bytes;
    } // getBytes()

} // Image
