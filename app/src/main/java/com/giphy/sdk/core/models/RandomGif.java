package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.MediaType;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class RandomGif {
    private MediaType type;
    private String id;
    private String url;
    @SerializedName("image_original_url")
    private String imageOriginalUrl;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("image_mp4_url")
    private String imageMp4Url;
    @SerializedName("image_frames")
    private String imageFrames;
    @SerializedName("image_width")
    private String imageWidth;
    @SerializedName("image_height")
    private String imageHeight;
    @SerializedName("fixed_height_downsampled_url")
    private String fixedHeightDownsampledUrl;
    @SerializedName("fixed_height_downsampled_width")
    private String fixedHeightDownsampledWidth;
    @SerializedName("fixed_height_downsampled_height")
    private String fixedHeightDownsampledHeight;
    @SerializedName("fixed_width_downsampled_url")
    private String fixedWidthDownsampledUrl;
    @SerializedName("fixed_width_downsampled_width")
    private String fixedWidthDownsampledWidth;
    @SerializedName("fixed_width_downsampled_height")
    private String fixedWidthDownsampledHeight;
    @SerializedName("fixed_height_small_url")
    private String fixedHeightSmallUrl;
    @SerializedName("fixed_height_small_still_url")
    private String fixedHeightSmallStillUrl;
    @SerializedName("fixed_height_small_width")
    private String fixedHeightSmallWidth;
    @SerializedName("fixed_height_small_height")
    private String fixedHeightSmallHeight;
    @SerializedName("fixed_width_small_url")
    private String fixedWidthSmallUrl;
    @SerializedName("fixed_width_small_still_url")
    private String fixedWidthSmallStillUrl;
    @SerializedName("fixed_width_small_width")
    private String fixedWidthSmallWidth;
    @SerializedName("fixed_width_small_height")
    private String fixedWidthSmallHeight;
    private String username;
    private String caption;

    public Gif toGif() {
        final Gif gif = new Gif();
        gif.setId(id);
        gif.setType(type);
        gif.setUrl(url);

        gif.setUser(new User());
        gif.getUser().setUsername(username);
        
        gif.setImages(new Images());

        gif.getImages().setOriginal(new Image());
        gif.getImages().getOriginal().setUrl(imageOriginalUrl);
        gif.getImages().getOriginal().setMp4(imageMp4Url);
        gif.getImages().getOriginal().setFrames(imageFrames);
        gif.getImages().getOriginal().setWidth(imageWidth);
        gif.getImages().getOriginal().setHeight(imageHeight);

        gif.getImages().setFixedHeightDownsampled(new Image());
        gif.getImages().getFixedHeightDownsampled().setUrl(fixedHeightDownsampledUrl);
        gif.getImages().getFixedHeightDownsampled().setWidth(fixedHeightDownsampledWidth);
        gif.getImages().getFixedHeightDownsampled().setHeight(fixedHeightDownsampledHeight);

        gif.getImages().setFixedWidthDownsampled(new Image());
        gif.getImages().getFixedWidthDownsampled().setUrl(fixedWidthDownsampledUrl);
        gif.getImages().getFixedWidthDownsampled().setWidth(fixedWidthDownsampledWidth);
        gif.getImages().getFixedWidthDownsampled().setHeight(fixedWidthDownsampledHeight);

        gif.getImages().setFixedHeightSmall(new Image());
        gif.getImages().getFixedHeightSmall().setUrl(fixedHeightSmallUrl);
        gif.getImages().getFixedHeightSmall().setWidth(fixedHeightSmallWidth);
        gif.getImages().getFixedHeightSmall().setHeight(fixedHeightSmallHeight);

        gif.getImages().setFixedWidthSmall(new Image());
        gif.getImages().getFixedWidthSmall().setUrl(fixedWidthSmallUrl);
        gif.getImages().getFixedWidthSmall().setWidth(fixedWidthSmallWidth);
        gif.getImages().getFixedWidthSmall().setHeight(fixedWidthSmallHeight);

        gif.getImages().setFixedHeightSmallStill(new Image());
        gif.getImages().getFixedHeightSmallStill().setUrl(fixedHeightSmallStillUrl);

        gif.getImages().setFixedWidthSmallStill(new Image());
        gif.getImages().getFixedWidthSmallStill().setUrl(fixedWidthSmallStillUrl);

        return gif;
    }
}
