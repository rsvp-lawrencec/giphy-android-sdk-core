package com.giphy.sdk.core.models;

import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Media {
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
    private Date updateDate;
    @SerializedName("create_datetime")
    private Date createDate;
    @SerializedName("import_datetime")
    private Date importDate;
    @SerializedName("trending_datetime")
    private Date trendingDate;

    @SerializedName("is_hidden")
    private boolean isHidden;
    @SerializedName("is_removed")
    private boolean isRemoved;
    @SerializedName("is_community")
    private boolean isCommunity;
    @SerializedName("is_anonymous")
    private boolean isAnonymous;
    @SerializedName("is_featured")
    private boolean isFeatured;
    @SerializedName("is_realtime")
    private boolean isRealtime;
    @SerializedName("is_indexable")
    private boolean isIndexable;
    @SerializedName("is_sticker")
    private boolean isSticker;

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

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Date getTrendingDate() {
        return trendingDate;
    }

    public void setTrendingDate(Date trendingDate) {
        this.trendingDate = trendingDate;
    }

    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    public boolean getIsCommunity() {
        return isCommunity;
    }

    public void setIsCommunity(boolean isCommunity) {
        this.isCommunity = isCommunity;
    }

    public boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    public boolean getIsRealtime() {
        return isRealtime;
    }

    public void setIsRealtime(boolean isRealtime) {
        this.isRealtime = isRealtime;
    }

    public boolean getIsIndexable() {
        return isIndexable;
    }

    public void setIsIndexable(boolean isIndexable) {
        this.isIndexable = isIndexable;
    }

    public boolean getIsSticker() {
        return isSticker;
    }

    public void setIsSticker(boolean isSticker) {
        this.isSticker = isSticker;
    }

    /**
     * Passed down the media id to the @images field and call postProcess function on @images field
     */
    public void postProcess() {
        if (images != null) {
            images.setMediaId(id);
            images.postProcess();
        }
    }
}
