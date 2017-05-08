package com.giphy.sdk.core.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 5/8/17.
 */

public class TermSuggestion {
    @SerializedName("name")
    private String term;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
