package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bogdantmm on 4/25/17.
 */

public class Category {
    private String name;
    @SerializedName("name_encoded")
    private String nameEncoded;
    private Media gif;
    @SerializedName("subcategories")
    private List<Category> subCategories;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameEncoded() {
        return nameEncoded;
    }

    public void setNameEncoded(String nameEncoded) {
        this.nameEncoded = nameEncoded;
    }

    public Media getGif() {
        return gif;
    }

    public void setGif(Media gif) {
        this.gif = gif;
    }

    public List<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<Category> subCategories) {
        this.subCategories = subCategories;
    }
}
