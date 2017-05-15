/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.giphy.sdk.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class User implements Parcelable {
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

    public User() {}

    public User(final Parcel in) {
        id = in.readString();
        avatarUrl = in.readString();
        bannerUrl = in.readString();
        profileUrl = in.readString();
        username = in.readString();
        displayName = in.readString();
        twitter = in.readString();
        isPublic = in.readByte() != 0;
        attributionDisplayName = in.readString();
        name = in.readString();
        description = in.readString();
        facebookUrl = in.readString();
        twitterUrl = in.readString();
        instagramUrl = in.readString();
        tumblrUrl = in.readString();
        suppressChrome = in.readByte() != 0;
        websiteUrl = in.readString();
        websiteDisplayUrl = in.readString();
    }

    /**
     * @return user id
     */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return avatar url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return banner url
     */
    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    /**
     * @return profile url
     */
    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return display name
     */
    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return twitter handle
     */
    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return true if the user is public, false otherwise
     */
    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    /**
     * @return attribution display name
     */
    public String getAttributionDisplayName() {
        return attributionDisplayName;
    }

    public void setAttributionDisplayName(String attributionDisplayName) {
        this.attributionDisplayName = attributionDisplayName;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return facebook url
     */
    public String getFacebookUrl() {
        return facebookUrl;
    }

    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    /**
     * @return twitter url
     */
    public String getTwitterUrl() {
        return twitterUrl;
    }

    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    /**
     * @return instagram url
     */
    public String getInstagramUrl() {
        return instagramUrl;
    }

    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
    }

    /**
     * @return tumblr url
     */
    public String getTumblrUrl() {
        return tumblrUrl;
    }

    public void setTumblrUrl(String tumblrUrl) {
        this.tumblrUrl = tumblrUrl;
    }

    /**
     * @return supress chrome
     */
    public boolean isSuppressChrome() {
        return suppressChrome;
    }

    public void setSuppressChrome(boolean suppressChrome) {
        this.suppressChrome = suppressChrome;
    }

    /**
     * @return website url
     */
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * @return displayable url of the website
     */
    public String getWebsiteDisplayUrl() {
        return websiteDisplayUrl;
    }

    public void setWebsiteDisplayUrl(String websiteDisplayUrl) {
        this.websiteDisplayUrl = websiteDisplayUrl;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(avatarUrl);
        parcel.writeString(bannerUrl);
        parcel.writeString(profileUrl);
        parcel.writeString(username);
        parcel.writeString(displayName);
        parcel.writeString(twitter);
        parcel.writeByte((byte) (isPublic ? 1 : 0));
        parcel.writeString(attributionDisplayName);
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(facebookUrl);
        parcel.writeString(twitterUrl);
        parcel.writeString(instagramUrl);
        parcel.writeString(tumblrUrl);
        parcel.writeByte((byte) (suppressChrome ? 1 : 0));
        parcel.writeString(websiteUrl);
        parcel.writeString(websiteDisplayUrl);
    }
}
