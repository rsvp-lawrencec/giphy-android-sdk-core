package com.giphy.sdk.core.network.api;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.giphy.sdk.core.network.engine.DefaultNetworkSession;
import com.giphy.sdk.core.network.engine.NetworkSession;
import com.giphy.sdk.core.network.response.GenericResponse;
import com.giphy.sdk.core.network.response.MultipleGifsResponse;
import com.giphy.sdk.core.threading.ApiTask;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class GiphyApiImpl implements GiphyApi {
    private NetworkSession networkSessionImpl;
    private String apiKey;

    public GiphyApiImpl(String apiKey) {
        this(apiKey, new DefaultNetworkSession());
    }

    public GiphyApiImpl(String apiKey, NetworkSession session) {
        this.apiKey = apiKey;
        this.networkSessionImpl = session;
    }

    @Override
    @NonNull
    public AsyncTask search(@NonNull String type, @NonNull  String searchQuery, @Nullable Integer limit,
                            @Nullable Integer offset, @Nullable String rating,
                            @Nullable String lang,
                            @Nullable final CompletionHandler<MultipleGifsResponse> completionHandler) {

        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("q", searchQuery);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        if (rating != null) {
            params.put("rating", rating);
        }
        if (lang != null) {
            params.put("lang", lang);
        }

        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.SEARCH, type), "GET", MultipleGifsResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask trending(@NonNull String type, @Nullable Integer limit,
                              @Nullable Integer offset, @Nullable String rating,
                              @Nullable final CompletionHandler<MultipleGifsResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        if (rating != null) {
            params.put("rating", rating);
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.TRENDING, type), "GET", MultipleGifsResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask translate(@NonNull String type, @NonNull String term, @Nullable String rating,
                               @Nullable String lang,
                               @Nullable final CompletionHandler<GenericResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("s", term);
        if (rating != null) {
            params.put("rating", rating);
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.TRENDING, type), "GET", GenericResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask random(@NonNull String type, @NonNull String tag, @Nullable String rating,
                            @Nullable final CompletionHandler<GenericResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("tag", tag);
        if (rating != null) {
            params.put("rating", rating);
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.RANDOM, type), "GET", GenericResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask gifById(@NonNull String gifId,
                             @Nullable final CompletionHandler<GenericResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                Constants.Paths.GIF_BY_ID, "GET", GenericResponse.class, params, null,
                completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask gifByIds(@NonNull List<String> gifIds,
                              @Nullable final CompletionHandler<GenericResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);

        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < gifIds.size(); i ++) {
            str.append(gifIds.get(i));
            if (i < gifIds.size() - 1) {
                str.append(",");
            }
        }
        params.put("ids", str.toString());

        return queryStringConnectionWrapper(Constants.SERVER_URL,
                Constants.Paths.GIF_BY_IDS, "GET", GenericResponse.class, params, null,
                completionHandler);
    }

    // Helper function to provide callback access
    private <T extends GenericResponse> AsyncTask queryStringConnectionWrapper(@NonNull final Uri serverUrl,
                                        @NonNull final String path,
                                        @NonNull final String method,
                                        @NonNull final Class<T> responseClass,
                                        @Nullable final Map<String, String> queryStrings,
                                        @Nullable final Map<String, String> headers,
                                        @Nullable final CompletionHandler<T> completionHandler
                                ) {
        return networkSessionImpl.queryStringConnection(serverUrl, path, method, responseClass,
                queryStrings, headers).executeAsyncTask(new ApiTask.WrappedAsyncTask<T>() {
            @Override
            protected void onPostExecute(T genericResponse) {
                if (completionHandler != null) {
                    completionHandler.onComplete(null, genericResponse);
                }
            }

            @Override
            protected void onError(@NonNull Throwable error) {
                if (completionHandler != null) {
                    completionHandler.onComplete(error, null);
                }
            }
        });
    }
}
