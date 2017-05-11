package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.RenditionType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Image {
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

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getGifSize() {
        return gifSize;
    }

    public void setGifSize(int gifSize) {
        this.gifSize = gifSize;
    }

    public int getFrames() {
        return frames;
    }

    public void setFrames(int frames) {
        this.frames = frames;
    }

    public String getMp4Url() {
        return mp4Url;
    }

    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
    }

    public int getMp4Size() {
        return mp4Size;
    }

    public void setMp4Size(int mp4Size) {
        this.mp4Size = mp4Size;
    }

    public String getWebPUrl() {
        return webPUrl;
    }

    public void setWebPUrl(String webPUrl) {
        this.webPUrl = webPUrl;
    }

    public int getWebPSize() {
        return webPSize;
    }

    public void setWebPSize(int webPSize) {
        this.webPSize = webPSize;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public RenditionType getRenditionType() {
        return renditionType;
    }

    public void setRenditionType(RenditionType renditionType) {
        this.renditionType = renditionType;
    }
}
