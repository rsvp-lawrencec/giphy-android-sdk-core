package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class User {
    private String id;
    @SerializedName("avatar_url")
    private String avatarUrl;
    @SerializedName("banner_url")
    private String bannerUrl;
    @SerializedName("profile_url")
    private String profileUrl;
    private String username;
    @SerializedName("display_name")
    private String displayName;
    private String twitter;
    @SerializedName("is_public")
    private boolean isPublic;
    @SerializedName("attribution_display_name")
    private String attributionDisplayName;
    private String name;
    private String description;
    @SerializedName("facebook_url")
    private String facebookUrl;
    @SerializedName("twitter_url")
    private String twitterUrl;
    @SerializedName("instagram_url")
    private String instagramUrl;
    @SerializedName("tumblr_url")
    private String tumblrUrl;
    @SerializedName("suppress_chrome")
    private boolean suppressChrome;
    @SerializedName("website_url")
    private String websiteUrl;
    @SerializedName("website_display_url")
    private String websiteDisplayUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public String getAttributionDisplayName() {
        return attributionDisplayName;
    }

    public void setAttributionDisplayName(String attributionDisplayName) {
        this.attributionDisplayName = attributionDisplayName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    public String getTumblrUrl() {
        return tumblrUrl;
    }

    public void setTumblrUrl(String tumblrUrl) {
        this.tumblrUrl = tumblrUrl;
    }

    public boolean isSuppressChrome() {
        return suppressChrome;
    }

    public void setSuppressChrome(boolean suppressChrome) {
        this.suppressChrome = suppressChrome;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getWebsiteDisplayUrl() {
        return websiteDisplayUrl;
    }

    public void setWebsiteDisplayUrl(String websiteDisplayUrl) {
        this.websiteDisplayUrl = websiteDisplayUrl;
    }
}
