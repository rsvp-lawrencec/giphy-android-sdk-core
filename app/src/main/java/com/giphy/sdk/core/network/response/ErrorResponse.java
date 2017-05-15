package com.giphy.sdk.core.network.response;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giphy.sdk.core.models.Meta;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class ErrorResponse implements GenericResponse {
    private final Meta meta;

    public ErrorResponse(@NonNull int serverStatus, @Nullable String message) {
        meta = new Meta(serverStatus, message);
    }

    public Meta getMeta() {
        return meta;
    }
}
