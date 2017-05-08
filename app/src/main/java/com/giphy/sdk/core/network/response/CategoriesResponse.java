package com.giphy.sdk.core.network.response;

import com.giphy.sdk.core.models.Category;
import com.giphy.sdk.core.models.Meta;
import com.giphy.sdk.core.models.Pagination;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by bogdantmm on 4/25/17.
 */

public class CategoriesResponse implements GenericResponse {
    @SerializedName("data")
    private List<Category> categories;

    public Pagination pagination;
    public Meta meta;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
