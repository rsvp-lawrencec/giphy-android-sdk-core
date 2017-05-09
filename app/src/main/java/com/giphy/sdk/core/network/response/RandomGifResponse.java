package com.giphy.sdk.core.network.response;

import com.giphy.sdk.core.models.Meta;
import com.giphy.sdk.core.models.RandomGif;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class RandomGifResponse implements GenericResponse {
    private RandomGif data;
    public Meta meta;

    public MediaResponse toGifResponse() {
        final MediaResponse mediaResponse = new MediaResponse();
        mediaResponse.setData(data.toGif());
        mediaResponse.setMeta(meta);
        return mediaResponse;
    }

    public RandomGif getData() {
        return data;
    }

    public void setData(RandomGif data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
