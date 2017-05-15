package com.giphy.sdk.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Media implements Parcelable {
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

    public Media() {}

    public Media(Parcel in) {
        final int mediaTypeOrdinal = in.readInt();
        type = mediaTypeOrdinal != -1 ? MediaType.values()[mediaTypeOrdinal] : null;
        id = in.readString();
        slug = in.readString();
        url = in.readString();
        bitlyGifUrl = in.readString();
        bitlyUrl = in.readString();
        embedUrl = in.readString();
        source = in.readString();
        final int ratingOrdinal = in.readInt();
        rating = ratingOrdinal != -1 ? RatingType.values()[ratingOrdinal] : null;
        contentUrl = in.readString();
        tags = in.createStringArrayList();
        featuredTags = in.createStringArrayList();
        user = in.readParcelable(User.class.getClassLoader());
        images = in.readParcelable(Images.class.getClassLoader());
        sourceTld = in.readString();
        sourcePostUrl = in.readString();

        final long updateDateLong = in.readLong();
        updateDate = updateDateLong != -1 ? new Date(updateDateLong) : null;
        final long createDateLong = in.readLong();
        createDate = createDateLong != -1 ? new Date(createDateLong) : null;
        final long importDateLong = in.readLong();
        importDate = importDateLong != -1 ? new Date(importDateLong) : null;
        final long trendingDateLong = in.readLong();
        trendingDate = trendingDateLong != -1 ? new Date(trendingDateLong) : null;

        isHidden = in.readByte() != 0;
        isRemoved = in.readByte() != 0;
        isCommunity = in.readByte() != 0;
        isAnonymous = in.readByte() != 0;
        isFeatured = in.readByte() != 0;
        isRealtime = in.readByte() != 0;
        isIndexable = in.readByte() != 0;
        isSticker = in.readByte() != 0;
    }

    /**
     * @return media type. Can be gif or sticker
     */
    public MediaType getType() {
        return type;
    }

    public void setType(MediaType type) {
        this.type = type;
    }

    /**
     * @return id of the object
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return slug
     */
    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return bitly version of the url
     */
    public String getBitlyGifUrl() {
        return bitlyGifUrl;
    }

    public void setBitlyGifUrl(String bitlyGifUrl) {
        this.bitlyGifUrl = bitlyGifUrl;
    }

    /**
     * @return bitly version of the gif url
     */
    public String getBitlyUrl() {
        return bitlyUrl;
    }

    public void setBitlyUrl(String bitlyUrl) {
        this.bitlyUrl = bitlyUrl;
    }

    /**
     * @return embed url
     */
    public String getEmbedUrl() {
        return embedUrl;
    }

    public void setEmbedUrl(String embedUrl) {
        this.embedUrl = embedUrl;
    }

    /**
     * @return source
     */
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return rating of the gif
     */
    public RatingType getRating() {
        return rating;
    }

    public void setRating(RatingType rating) {
        this.rating = rating;
    }

    /**
     * @return content url
     */
    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    /**
     * @return tags associated with the gif
     */
    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    /**
     * @return featured tags
     */
    public List<String> getFeaturedTags() {
        return featuredTags;
    }

    public void setFeaturedTags(List<String> featuredTags) {
        this.featuredTags = featuredTags;
    }

    /**
     * @return user who uploaded the gif
     */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return images collection that contains all images types
     */
    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    /**
     * @return source tld
     */
    public String getSourceTld() {
        return sourceTld;
    }

    public void setSourceTld(String sourceTld) {
        this.sourceTld = sourceTld;
    }

    /**
     * @return source post url
     */
    public String getSourcePostUrl() {
        return sourcePostUrl;
    }

    public void setSourcePostUrl(String sourcePostUrl) {
        this.sourcePostUrl = sourcePostUrl;
    }

    /**
     * @return date when the gif was updated
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * @return date when the gif was created
     */
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return date when the gif was imported
     */
    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    /**
     * @return date when the gif was trending
     */
    public Date getTrendingDate() {
        return trendingDate;
    }

    public void setTrendingDate(Date trendingDate) {
        this.trendingDate = trendingDate;
    }

    /**
     * @return true if gif is hidden, false otherwise
     */
    public boolean getIsHidden() {
        return isHidden;
    }

    public void setIsHidden(boolean isHidden) {
        this.isHidden = isHidden;
    }

    /**
     * @return true if this gif was removed, false otherwise
     */
    public boolean getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(boolean isRemoved) {
        this.isRemoved = isRemoved;
    }

    /**
     * @return true if is comunity gif
     */
    public boolean getIsCommunity() {
        return isCommunity;
    }

    public void setIsCommunity(boolean isCommunity) {
        this.isCommunity = isCommunity;
    }

    /**
     * @return true if is anonymous
     */
    public boolean getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(boolean isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    /**
     * @return true if is featured, false otherwise
     */
    public boolean getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(boolean isFeatured) {
        this.isFeatured = isFeatured;
    }

    /**
     * @return true if realtime
     */
    public boolean getIsRealtime() {
        return isRealtime;
    }

    public void setIsRealtime(boolean isRealtime) {
        this.isRealtime = isRealtime;
    }

    /**
     * @return true if indexable
     */
    public boolean getIsIndexable() {
        return isIndexable;
    }

    public void setIsIndexable(boolean isIndexable) {
        this.isIndexable = isIndexable;
    }

    /**
     * @return true if sticker, false otherwise
     */
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

    public static final Creator<Media> CREATOR = new Creator<Media>() {
        @Override
        public Media createFromParcel(Parcel in) {
            return new Media(in);
        }

        @Override
        public Media[] newArray(int size) {
            return new Media[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(type != null ? type.ordinal() : -1);
        parcel.writeString(id);
        parcel.writeString(slug);
        parcel.writeString(url);
        parcel.writeString(bitlyGifUrl);
        parcel.writeString(bitlyUrl);
        parcel.writeString(embedUrl);
        parcel.writeString(source);
        parcel.writeInt(rating != null ? rating.ordinal() : -1);
        parcel.writeString(contentUrl);
        parcel.writeStringList(tags);
        parcel.writeStringList(featuredTags);
        parcel.writeParcelable(user, i);
        parcel.writeParcelable(images, i);
        parcel.writeString(sourceTld);
        parcel.writeString(sourcePostUrl);

        parcel.writeLong(updateDate != null ? updateDate.getTime() : -1);
        parcel.writeLong(createDate != null ? createDate.getTime() : -1);
        parcel.writeLong(importDate != null ? importDate.getTime() : -1);
        parcel.writeLong(trendingDate != null ? trendingDate.getTime() : -1);

        parcel.writeByte((byte) (isHidden ? 1 : 0));
        parcel.writeByte((byte) (isRemoved ? 1 : 0));
        parcel.writeByte((byte) (isCommunity ? 1 : 0));
        parcel.writeByte((byte) (isAnonymous ? 1 : 0));
        parcel.writeByte((byte) (isFeatured ? 1 : 0));
        parcel.writeByte((byte) (isRealtime ? 1 : 0));
        parcel.writeByte((byte) (isIndexable ? 1 : 0));
        parcel.writeByte((byte) (isSticker ? 1 : 0));
    }
}
