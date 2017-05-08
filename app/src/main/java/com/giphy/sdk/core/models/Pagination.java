package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 5/4/17.
 */

public class Pagination {
    @SerializedName("total_count")
    private int totalCount;
    private int count;
    private int offset;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}
