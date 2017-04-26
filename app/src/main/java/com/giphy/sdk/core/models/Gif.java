package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.MediaType;

import java.util.List;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Gif {
    public MediaType type;
    public String id;
    public String slug;
    public String url;
    public String bitly_gif_url;
    public String bitly_url;
    public String embed_url;
    public String source;
    public String rating;
    public String content_url;
    public List<String> tags;
    public List<String> featured_tags;
    public User user;
    public Images images;

    public String source_tld;
    public String source_post_url;

    public String update_datetime;
    public String create_datetime;
    public String import_datetime;
    public String trending_datetime;

    public int is_hidden;
    public int is_removed;
    public int is_community;
    public int is_anonymous;
    public int is_featured;
    public int is_realtime;
    public int is_indexable;
    public int is_sticker;


}
