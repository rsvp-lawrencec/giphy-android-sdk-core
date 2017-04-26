package com.giphy.sdk.core.network.response;

import com.giphy.sdk.core.models.Category;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bogdantmm on 4/25/17.
 */

public class CategoriesResponse implements GenericResponse {
    @SerializedName("data")
    public List<Category> categories;
}
