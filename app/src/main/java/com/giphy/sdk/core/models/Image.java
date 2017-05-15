/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.giphy.sdk.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.giphy.sdk.core.models.enums.RenditionType;
import com.google.gson.annotations.SerializedName;

public class Image implements Parcelable {
    @SerializedName("url")
    private String gifUrl;
    private int width;
    private int height;
    @SerializedName("size")
    private int gifSize;
    private int frames;
    @SerializedName("mp4")
    private String mp4Url;
    @SerializedName("mp4_size")
    private int mp4Size;
    @SerializedName("webp")
    private String webPUrl;
    @SerializedName("webp_size")
    private int webPSize;

    private String mediaId;
    private RenditionType renditionType;

    protected Image() {
    }

    protected Image(Parcel in) {
        gifUrl = in.readString();
        width = in.readInt();
        height = in.readInt();
        gifSize = in.readInt();
        frames = in.readInt();
        mp4Url = in.readString();
        mp4Size = in.readInt();
        webPUrl = in.readString();
        webPSize = in.readInt();
        mediaId = in.readString();
        final int renditionOrdinal = in.readInt();
        renditionType = renditionOrdinal != -1 ? RenditionType.values()[renditionOrdinal] : null;
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    /**
     * @return ID of the Represented Object
     */
    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    /**
     * @return width of the image
     */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return height of the image
     */
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return Gif file size in bytes
     */
    public int getGifSize() {
        return gifSize;
    }

    public void setGifSize(int gifSize) {
        this.gifSize = gifSize;
    }

    /**
     * @return number of frames
     */
    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    /**
     * @return URL of the mp4 file
     */
    public String getMp4Url() {
        return mp4Url;
    }

    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
    }

    /**
     * @return mp4 file size in bytes
     */
    public int getMp4Size() {
        return mp4Size;
    }

    public void setMp4Size(int mp4Size) {
        this.mp4Size = mp4Size;
    }

    /**
     * @return URL of the webP file
     */
    public String getWebPUrl() {
        return webPUrl;
    }

    public void setWebPUrl(String webPUrl) {
        this.webPUrl = webPUrl;
    }

    /**
     * @return webP file size in bytes
     */
    public int getWebPSize() {
        return webPSize;
    }

    public void setWebPSize(int webPSize) {
        this.webPSize = webPSize;
    }

    /**
     * @return ID of the represented Media object
     */
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    /**
     * @return Rendition type of the represented Media object
     */
    public RenditionType getRenditionType() {
        return renditionType;
    }

    public void setRenditionType(RenditionType renditionType) {
        this.renditionType = renditionType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(gifUrl);
        parcel.writeInt(width);
        parcel.writeInt(height);
        parcel.writeInt(gifSize);
        parcel.writeInt(frames);
        parcel.writeString(mp4Url);
        parcel.writeInt(mp4Size);
        parcel.writeString(webPUrl);
        parcel.writeInt(webPSize);
        parcel.writeString(mediaId);
        parcel.writeInt(renditionType != null ? renditionType.ordinal() : -1);
    }
}
