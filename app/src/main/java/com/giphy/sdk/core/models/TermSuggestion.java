package com.giphy.sdk.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 5/8/17.
 */

public class TermSuggestion implements Parcelable {
    @SerializedName("name")
    private String term;

    public TermSuggestion() {}

    public TermSuggestion(Parcel in) {
        term = in.readString();
    }

    /**
     * @return term suggestion
     */
    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public static final Creator<TermSuggestion> CREATOR = new Creator<TermSuggestion>() {
        @Override
        public TermSuggestion createFromParcel(Parcel in) {
            return new TermSuggestion(in);
        }

        @Override
        public TermSuggestion[] newArray(int size) {
            return new TermSuggestion[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(term);
    }
}
