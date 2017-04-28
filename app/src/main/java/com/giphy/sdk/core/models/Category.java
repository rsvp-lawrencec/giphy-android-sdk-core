package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bogdantmm on 4/25/17.
 */

public class Category {
    public String name;
    @SerializedName("name_encoded")
    public String nameEncoded;
    public Gif gif;
    public List<Category> subcategories;
}
