package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Image {
    public String url;
    public String width;
    public String height;
    public String size;
    public String frames;
    public String mp4;
    @SerializedName("mp4_size")
    public String mp4Size;
    public String webp;
    @SerializedName("webp_size")
    public String webpSize;
}
