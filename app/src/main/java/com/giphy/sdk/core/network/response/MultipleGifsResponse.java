package com.giphy.sdk.core.network.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import com.giphy.sdk.core.models.Gif;

/**
 * Created by bogdantmm on 4/20/17.
 */

public class MultipleGifsResponse implements GenericResponse {
    @SerializedName("data")
    public List<Gif> gifs;
}
