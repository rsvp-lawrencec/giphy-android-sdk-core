package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.MediaType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class RandomGif {
    public MediaType type;
    public String id;
    public String url;
    @SerializedName("image_original_url")
    public String imageOriginalUrl;
    @SerializedName("image_url")
    public String imageUrl;
    @SerializedName("image_mp4_url")
    public String imageMp4Url;
    @SerializedName("image_frames")
    public String imageFrames;
    @SerializedName("image_width")
    public String imageWidth;
    @SerializedName("image_height")
    public String imageHeight;
    @SerializedName("fixed_height_downsampled_url")
    public String fixedHeightDownsampledUrl;
    @SerializedName("fixed_height_downsampled_width")
    public String fixedHeightDownsampledWidth;
    @SerializedName("fixed_height_downsampled_height")
    public String fixedHeightDownsampledHeight;
    @SerializedName("fixed_width_downsampled_url")
    public String fixedWidthDownsampledUrl;
    @SerializedName("fixed_width_downsampled_width")
    public String fixedWidthDownsampledWidth;
    @SerializedName("fixed_width_downsampled_height")
    public String fixedWidthDownsampledHeight;
    @SerializedName("fixed_height_small_url")
    public String fixedHeightSmallUrl;
    @SerializedName("fixed_height_small_still_url")
    public String fixedHeightSmallStillUrl;
    @SerializedName("fixed_height_small_width")
    public String fixedHeightSmallWidth;
    @SerializedName("fixed_height_small_height")
    public String fixedHeightSmallHeight;
    @SerializedName("fixed_width_small_url")
    public String fixedWidthSmallUrl;
    @SerializedName("fixed_width_small_still_url")
    public String fixedWidthSmallStillUrl;
    @SerializedName("fixed_width_small_width")
    public String fixedWidthSmallWidth;
    @SerializedName("fixed_width_small_height")
    public String fixedWidthSmallHeight;
    public String username;
    public String caption;

    public Gif toGif() {
        final Gif gif = new Gif();
        gif.id = id;
        gif.type = type;
        gif.url = url;
        gif.images = new Images();

        gif.user = new User();
        gif.user.username = username;

        gif.images.original = new Image();
        gif.images.original.url = imageOriginalUrl;
        gif.images.original.mp4 = imageMp4Url;
        gif.images.original.frames = imageFrames;
        gif.images.original.width = imageWidth;
        gif.images.original.height = imageHeight;

        gif.images.fixedHeightDownsampled = new Image();
        gif.images.fixedHeightDownsampled.url = fixedHeightDownsampledUrl;
        gif.images.fixedHeightDownsampled.width = fixedHeightDownsampledWidth;
        gif.images.fixedHeightDownsampled.height= fixedHeightDownsampledHeight;

        gif.images.fixedWidthDownsampled = new Image();
        gif.images.fixedWidthDownsampled.url = fixedWidthDownsampledUrl;
        gif.images.fixedWidthDownsampled.width = fixedWidthDownsampledWidth;
        gif.images.fixedWidthDownsampled.height= fixedWidthDownsampledHeight;

        gif.images.fixedHeightSmall = new Image();
        gif.images.fixedHeightSmall.url = fixedHeightSmallUrl;
        gif.images.fixedHeightSmall.width = fixedHeightSmallWidth;
        gif.images.fixedHeightSmall.height= fixedHeightSmallHeight;

        gif.images.fixedWidthSmall = new Image();
        gif.images.fixedWidthSmall.url = fixedWidthSmallUrl;
        gif.images.fixedWidthSmall.width = fixedWidthSmallWidth;
        gif.images.fixedWidthSmall.height= fixedWidthSmallHeight;

        gif.images.fixedHeightSmallStill = new Image();
        gif.images.fixedHeightSmallStill.url = fixedHeightSmallStillUrl;

        gif.images.fixedWidthSmallStill = new Image();
        gif.images.fixedWidthSmallStill.url = fixedWidthSmallStillUrl;

        return gif;
    }
}
