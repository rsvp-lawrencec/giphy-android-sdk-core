package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.RenditionType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Image {
    @SerializedName("url")
    private String gifUrl;
    private String width;
    private String height;
    @SerializedName("size")
    private String gifSize;
    private String frames;
    @SerializedName("mp4")
    private String mp4Url;
    @SerializedName("mp4_size")
    private String mp4Size;
    @SerializedName("webp")
    private String webPUrl;
    @SerializedName("webp_size")
    private String webPSize;

    private String mediaId;
    private RenditionType renditionType;

    public String getGifUrl() {
        return gifUrl;
    }

    public void setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
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

    public String getGifSize() {
        return gifSize;
    }

    public void setGifSize(String gifSize) {
        this.gifSize = gifSize;
    }

    public String getFrames() {
        return frames;
    }

    public void setFrames(String frames) {
        this.frames = frames;
    }

    public String getMp4Url() {
        return mp4Url;
    }

    public void setMp4Url(String mp4Url) {
        this.mp4Url = mp4Url;
    }

    public String getMp4Size() {
        return mp4Size;
    }

    public void setMp4Size(String mp4Size) {
        this.mp4Size = mp4Size;
    }

    public String getWebPUrl() {
        return webPUrl;
    }

    public void setWebPUrl(String webPUrl) {
        this.webPUrl = webPUrl;
    }

    public String getWebPSize() {
        return webPSize;
    }

    public void setWebPSize(String webPSize) {
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
