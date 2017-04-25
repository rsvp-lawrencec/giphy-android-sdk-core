package com.giphy.sdk.core.network.response;

import com.giphy.sdk.core.models.RandomGif;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class RandomGifResponse implements GenericResponse {
    @SerializedName("data")
    public RandomGif randomGif;

    public GifResponse toGifResponse() {
        final GifResponse gifResponse = new GifResponse();
        gifResponse.gif = randomGif.toGif();
        return gifResponse;
    }
}
