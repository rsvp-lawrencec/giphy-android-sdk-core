package com.giphy.sdk.core.network.response;

import com.giphy.sdk.core.models.Gif;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/21/17.
 */

public class GifResponse implements GenericResponse {
    @SerializedName("data")
    private Gif gif;

    public Gif getGif() {
        return gif;
    }

    public void setGif(Gif gif) {
        this.gif = gif;
    }
}
