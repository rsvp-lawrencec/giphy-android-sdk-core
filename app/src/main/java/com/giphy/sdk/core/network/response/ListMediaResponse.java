package com.giphy.sdk.core.network.response;

import com.giphy.sdk.core.models.Meta;
import com.giphy.sdk.core.models.Pagination;

import java.util.List;

import com.giphy.sdk.core.models.Media;

/**
 * Created by bogdantmm on 4/20/17.
 */

public class ListMediaResponse implements GenericResponse {
    private List<Media> data;
    public Pagination pagination;
    public Meta meta;

    public List<Media> getData() {
        return data;
    }

    public void setData(List<Media> data) {
        this.data = data;
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
