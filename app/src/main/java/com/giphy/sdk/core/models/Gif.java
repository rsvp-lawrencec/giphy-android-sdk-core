package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Gif {
    public MediaType type;
    public String id;
    public String slug;
    public String url;
    @SerializedName("bitly_gif_url")
    public String bitlyGifUrl;
    @SerializedName("bitly_url")
    public String bitlyUrl;
    @SerializedName("embed_url")
    public String embedUrl;
    public String source;
    public RatingType rating;
    @SerializedName("content_url")
    public String contentUrl;
    public List<String> tags;
    @SerializedName("featured_tags")
    public List<String> featuredTags;
    public User user;
    public Images images;

    @SerializedName("source_tld")
    public String sourceTld;
    @SerializedName("source_post_url")
    public String sourcePostUrl;

    @SerializedName("update_datetime")
    public String updateDatetime;
    @SerializedName("create_datetime")
    public String createDatetime;
    @SerializedName("import_datetime")
    public String importDatetime;
    @SerializedName("trending_datetime")
    public String trendingDatetime;

    @SerializedName("is_hidden")
    public int isHidden;
    @SerializedName("is_removed")
    public int isRemoved;
    @SerializedName("is_community")
    public int isCommunity;
    @SerializedName("is_anonymous")
    public int isAnonymous;
    @SerializedName("is_featured")
    public int isFeatured;
    @SerializedName("is_realtime")
    public int isRealtime;
    @SerializedName("is_indexable")
    public int isIndexable;
    @SerializedName("is_sticker")
    public int isSticker;
}
