package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class User {
    public String id;
    @SerializedName("avatar_url")
    public String avatarUrl;
    @SerializedName("banner_url")
    public String bannerUrl;
    @SerializedName("profile_url")
    public String profileUrl;
    public String username;
    @SerializedName("display_name")
    public String displayName;
    public String twitter;
    @SerializedName("is_public")
    public int isPublic;
    @SerializedName("attribution_display_name")
    public String attributionDisplayName;
    public String name;
    public String description;
    @SerializedName("facebook_url")
    public String facebookUrl;
    @SerializedName("twitter_url")
    public String twitterUrl;
    @SerializedName("instagram_url")
    public String instagramUrl;
    @SerializedName("tumblr_url")
    public String tumblrUrl;
    @SerializedName("suppress_chrome")
    public boolean suppressChrome;
    @SerializedName("website_url")
    public String websiteUrl;
    @SerializedName("website_display_url")
    public String websiteDisplayUrl;
}
