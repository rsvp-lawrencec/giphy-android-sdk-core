package com.giphy.sdk.core.network.response;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class ErrorResponse implements GenericResponse {
    @NonNull
    private final String serverStatus;

    @Nullable
    private final String message;

    public ErrorResponse(@NonNull String serverStatus, @Nullable String message) {
        this.serverStatus = serverStatus;
        this.message = message;
    }
}
