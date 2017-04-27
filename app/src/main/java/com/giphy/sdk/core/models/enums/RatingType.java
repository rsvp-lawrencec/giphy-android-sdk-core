package com.giphy.sdk.core.models.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/26/17.
 */

public enum RatingType {
    r("r"),
    y("y"),
    g("g"),
    pg("pg"),
    @SerializedName("pg-13")
    pg13("pg-13");

    private final String rating;

    private RatingType(String rating) {
        this.rating = rating;
    }

    public String toString() {
        return this.rating;
    }
}
