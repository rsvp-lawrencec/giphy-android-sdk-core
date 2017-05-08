package com.giphy.sdk.core.network.response;

import com.giphy.sdk.core.models.Meta;
import com.giphy.sdk.core.models.RandomGif;
import com.google.gson.annotations.SerializedName;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class RandomGifResponse implements GenericResponse {
    @SerializedName("data")
    private RandomGif randomGif;
    public Meta meta;

    public GifResponse toGifResponse() {
        final GifResponse gifResponse = new GifResponse();
        gifResponse.setGif(randomGif.toGif());
        gifResponse.setMeta(meta);
        return gifResponse;
    }

    public RandomGif getRandomGif() {
        return randomGif;
    }

    public void setRandomGif(RandomGif randomGif) {
        this.randomGif = randomGif;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
