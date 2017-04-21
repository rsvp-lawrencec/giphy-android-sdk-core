package com.giphy.sdk.core.network.engine;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Map;

import com.giphy.sdk.core.network.response.GenericResponse;
import com.giphy.sdk.core.threading.ApiTask;

/**
 * Created by bogdantmm on 4/19/17.
 */

public interface NetworkSession {
    <T extends GenericResponse> ApiTask<T> queryStringConnection(@NonNull final Uri serverUrl,
                                                                        @NonNull final String path,
                                                                        @NonNull final String method,
                                                                        @NonNull final Class<T> responseClass,
                                                                        @Nullable final Map<String, String> queryStrings,
                                                                        @Nullable final Map<String, String> headers);
}
