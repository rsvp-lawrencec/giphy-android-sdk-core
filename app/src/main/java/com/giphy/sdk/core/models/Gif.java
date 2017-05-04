package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Gif {
    private MediaType type;
    private String id;
    private String slug;
    private String url;
    @SerializedName("bitly_gif_url")
    private String bitlyGifUrl;
    @SerializedName("bitly_url")
    private String bitlyUrl;
    @SerializedName("embed_url")
    private String embedUrl;
    private String source;
    private RatingType rating;
    @SerializedName("content_url")
    private String contentUrl;
    private List<String> tags;
    @SerializedName("featured_tags")
    private List<String> featuredTags;
    private User user;
    private Images images;

    @SerializedName("source_tld")
    private String sourceTld;
    @SerializedName("source_post_url")
    private String sourcePostUrl;

    @SerializedName("update_datetime")
    private String updateDatetime;
    @SerializedName("create_datetime")
    private String createDatetime;
    @SerializedName("import_datetime")
    private String importDatetime;
    @SerializedName("trending_datetime")
    private String trendingDatetime;

    @SerializedName("is_hidden")
    private int isHidden;
    @SerializedName("is_removed")
    private int isRemoved;
    @SerializedName("is_community")
    private int isCommunity;
    @SerializedName("is_anonymous")
    private int isAnonymous;
    @SerializedName("is_featured")
    private int isFeatured;
    @SerializedName("is_realtime")
    private int isRealtime;
    @SerializedName("is_indexable")
    private int isIndexable;
    @SerializedName("is_sticker")
    private int isSticker;

    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBitlyGifUrl() {
        return bitlyGifUrl;
    }

    public void setBitlyGifUrl(String bitlyGifUrl) {
        this.bitlyGifUrl = bitlyGifUrl;
    }

    public String getBitlyUrl() {
        return bitlyUrl;
    }

    public void setBitlyUrl(String bitlyUrl) {
        this.bitlyUrl = bitlyUrl;
    }

    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public RatingType getRating() {
        return rating;
    }

    public void setRating(RatingType rating) {
        this.rating = rating;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getFeaturedTags() {
        return featuredTags;
    }

    public void setFeaturedTags(List<String> featuredTags) {
        this.featuredTags = featuredTags;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public String getSourceTld() {
        return sourceTld;
    }

    public void setSourceTld(String sourceTld) {
        this.sourceTld = sourceTld;
    }

    public String getSourcePostUrl() {
        return sourcePostUrl;
    }

    public void setSourcePostUrl(String sourcePostUrl) {
        this.sourcePostUrl = sourcePostUrl;
    }

    public String getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(String updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public String getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(String createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getImportDatetime() {
        return importDatetime;
    }

    public void setImportDatetime(String importDatetime) {
        this.importDatetime = importDatetime;
    }

    public String getTrendingDatetime() {
        return trendingDatetime;
    }

    public void setTrendingDatetime(String trendingDatetime) {
        this.trendingDatetime = trendingDatetime;
    }

    public int getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(int isHidden) {
        this.isHidden = isHidden;
    }

    public int getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(int isRemoved) {
        this.isRemoved = isRemoved;
    }

    public int getIsCommunity() {
        return isCommunity;
    }

    public void setIsCommunity(int isCommunity) {
        this.isCommunity = isCommunity;
    }

    public int getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(int isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public int getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(int isFeatured) {
        this.isFeatured = isFeatured;
    }

    public int getIsRealtime() {
        return isRealtime;
    }

    public void setIsRealtime(int isRealtime) {
        this.isRealtime = isRealtime;
    }

    public int getIsIndexable() {
        return isIndexable;
    }

    public void setIsIndexable(int isIndexable) {
        this.isIndexable = isIndexable;
    }

    public int getIsSticker() {
        return isSticker;
    }

    public void setIsSticker(int isSticker) {
        this.isSticker = isSticker;
    }
}
