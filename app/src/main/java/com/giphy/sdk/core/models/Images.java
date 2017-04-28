package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Images {
    @SerializedName("fixed_height")
    public Image fixedHeight;
    @SerializedName("fixed_height_still")
    public Image fixedHeightStill;
    @SerializedName("fixed_height_downsampled")
    public Image fixedHeightDownsampled;
    @SerializedName("fixed_width")
    public Image fixedWidth;
    @SerializedName("fixed_width_still")
    public Image fixedWidthStill;
    @SerializedName("fixed_width_downsampled")
    public Image fixedWidthDownsampled;
    @SerializedName("fixed_height_small")
    public Image fixedHeightSmall;
    @SerializedName("fixed_height_small_still")
    public Image fixedHeightSmallStill;
    @SerializedName("fixed_width_small")
    public Image fixedWidthSmall;
    @SerializedName("fixed_width_small_still")
    public Image fixedWidthSmallStill;
    public Image downsized;
    @SerializedName("downsized_still")
    public Image downsizedStill;
    @SerializedName("downsized_large")
    public Image downsizedLarge;
    @SerializedName("downsized_medium")
    public Image downsizedMedium;
    public Image original;
    @SerializedName("original_still")
    public Image originalStill;
    public Image looping;
    @SerializedName("original_mp4")
    public Image originalMp4;
    public Image preview;
    @SerializedName("downsized_small")
    public Image downsizedSmall;
    @SerializedName("preview_gif")
    public Image previewGif;
    @SerializedName("preview_webp")
    public Image previewWebp;
}
