package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.MediaType;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class RandomGif {
    public MediaType type;
    public String id;
    public String url;
    public String image_original_url;
    public String image_url;
    public String image_mp4_url;
    public String image_frames;
    public String image_width;
    public String image_height;
    public String fixed_height_downsampled_url;
    public String fixed_height_downsampled_width;
    public String fixed_height_downsampled_height;
    public String fixed_width_downsampled_url;
    public String fixed_width_downsampled_width;
    public String fixed_width_downsampled_height;
    public String fixed_height_small_url;
    public String fixed_height_small_still_url;
    public String fixed_height_small_width;
    public String fixed_height_small_height;
    public String fixed_width_small_url;
    public String fixed_width_small_still_url;
    public String fixed_width_small_width;
    public String fixed_width_small_height;
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
        gif.images.original.url = image_original_url;
        gif.images.original.mp4 = image_mp4_url;
        gif.images.original.frames = image_frames;
        gif.images.original.width = image_width;
        gif.images.original.height = image_height;

        gif.images.fixed_height_downsampled = new Image();
        gif.images.fixed_height_downsampled.url = fixed_height_downsampled_url;
        gif.images.fixed_height_downsampled.width = fixed_height_downsampled_width;
        gif.images.fixed_height_downsampled.height= fixed_height_downsampled_height;

        gif.images.fixed_width_downsampled = new Image();
        gif.images.fixed_width_downsampled.url = fixed_width_downsampled_url;
        gif.images.fixed_width_downsampled.width = fixed_width_downsampled_width;
        gif.images.fixed_width_downsampled.height= fixed_width_downsampled_height;

        gif.images.fixed_height_small = new Image();
        gif.images.fixed_height_small.url = fixed_height_small_url;
        gif.images.fixed_height_small.width = fixed_height_small_width;
        gif.images.fixed_height_small.height= fixed_height_small_height;

        gif.images.fixed_width_small = new Image();
        gif.images.fixed_width_small.url = fixed_width_small_url;
        gif.images.fixed_width_small.width = fixed_width_small_width;
        gif.images.fixed_width_small.height= fixed_width_small_height;

        gif.images.fixed_height_small_still = new Image();
        gif.images.fixed_height_small_still.url = fixed_height_small_still_url;

        gif.images.fixed_width_small_still = new Image();
        gif.images.fixed_width_small_still.url = fixed_width_small_still_url;

        return gif;
    }
}
