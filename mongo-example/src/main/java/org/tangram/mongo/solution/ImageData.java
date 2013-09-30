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
package org.tangram.mongo.solution;

import javax.jdo.annotations.PersistenceCapable;

import org.tangram.jdo.util.MimedBlob;

@PersistenceCapable
public class ImageData extends Linkable implements MimedBlob {

    private String data;

    private String mimeType;

    private String width;

    private String height;


    public byte[] getData() {
        return stringToByteArray(data);
    }


    public void setData(byte[] data) {
        this.data = byteArraytoString(data);
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