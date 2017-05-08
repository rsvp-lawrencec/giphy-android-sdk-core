package com.giphy.sdk.core.network.response;

import com.giphy.sdk.core.models.Meta;
import com.giphy.sdk.core.models.Pagination;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.giphy.sdk.core.models.Gif;

/**
 * Created by bogdantmm on 4/20/17.
 */

public class MultipleGifsResponse implements GenericResponse {
    @SerializedName("data")
    private List<Gif> gifs;
    public Pagination pagination;
    public Meta meta;

    public List<Gif> getGifs() {
        return gifs;
    }

    public void setGifs(List<Gif> gifs) {
        this.gifs = gifs;
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
