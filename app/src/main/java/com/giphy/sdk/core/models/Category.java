package com.giphy.sdk.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bogdantmm on 4/25/17.
 */

public class Category implements Parcelable {
    private String name;
    @SerializedName("name_encoded")
    private String nameEncoded;
    private Media gif;
    @SerializedName("subcategories")
    private List<Category> subCategories;

    public Category() {}

    public Category(Parcel in) {
        name = in.readString();
        nameEncoded = in.readString();
        gif = in.readParcelable(Media.class.getClassLoader());
        subCategories = in.createTypedArrayList(Category.CREATOR);
    }

    public Category(String name, String nameEncoded) {
        this.name = name;
        this.nameEncoded = nameEncoded;
    }

    /**
     * @return category name
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return encoded category name, used for constructing the URL to fetch gifs
     */
    public String getNameEncoded() {
        return nameEncoded;
    }

    public void setNameEncoded(String nameEncoded) {
        this.nameEncoded = nameEncoded;
    }

    /**
     * @return preview gif of the category
     */
    public Media getGif() {
        return gif;
    }

    public void setGif(Media gif) {
        this.gif = gif;
    }

    /**
     * @return subcategories of the category
     */
    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(nameEncoded);
        parcel.writeParcelable(gif, i);
        parcel.writeTypedList(subCategories);
    }
}
