package com.giphy.sdk.core.network.api;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giphy.sdk.core.models.enums.LangType;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.giphy.sdk.core.network.engine.DefaultNetworkSession;
import com.giphy.sdk.core.network.engine.NetworkSession;
import com.giphy.sdk.core.network.response.ListCategoryResponse;
import com.giphy.sdk.core.network.response.GenericResponse;
import com.giphy.sdk.core.network.response.MediaResponse;
import com.giphy.sdk.core.network.response.ListMediaResponse;
import com.giphy.sdk.core.network.response.RandomGifResponse;
import com.giphy.sdk.core.network.response.ListTermSuggestionResponse;
import com.giphy.sdk.core.threading.ApiTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class GPHApiClient implements GPHApi {
    private NetworkSession networkSessionImpl;
    private String apiKey;

    public GPHApiClient(String apiKey) {
        this(apiKey, new DefaultNetworkSession());
    }

    public GPHApiClient(String apiKey, NetworkSession session) {
        this.apiKey = apiKey;
        this.networkSessionImpl = session;
    }

    @Override
    @NonNull
    public AsyncTask search(@NonNull String searchQuery, @Nullable MediaType type, @Nullable Integer limit,
                            @Nullable Integer offset, @Nullable RatingType rating,
                            @Nullable LangType lang,
                            @NonNull final CompletionHandler<ListMediaResponse> completionHandler) {

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
            params.put("rating", rating.toString());
        }
        if (lang != null) {
            params.put("lang", lang.toString());
        }

        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.SEARCH, mediaTypeToEndpoint(type)), "GET", ListMediaResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask trending(@Nullable MediaType type, @Nullable Integer limit,
                              @Nullable Integer offset, @Nullable RatingType rating,
                              @NonNull final CompletionHandler<ListMediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        if (rating != null) {
            params.put("rating", rating.toString());
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.TRENDING, mediaTypeToEndpoint(type)), "GET", ListMediaResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask translate(@NonNull String term, @Nullable MediaType type, @Nullable RatingType rating,
                               @Nullable LangType lang,
                               @NonNull final CompletionHandler<MediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("s", term);
        if (rating != null) {
            params.put("rating", rating.toString());
        }
        if (lang != null) {
            params.put("lang", lang.toString());
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.TRANSLATE, mediaTypeToEndpoint(type)), "GET", MediaResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask random(@NonNull String tag, @Nullable MediaType type, @Nullable RatingType rating,
                            @NonNull final CompletionHandler<MediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("tag", tag);
        if (rating != null) {
            params.put("rating", rating.toString());
        }

        final CompletionHandler<RandomGifResponse> completionHandlerWrapper = new CompletionHandler<RandomGifResponse>() {
            @Override
            public void onComplete(RandomGifResponse result, Throwable e) {
                if (result != null) {
                    completionHandler.onComplete(result.toGifResponse(), null);
                } else {
                    completionHandler.onComplete(null, e);
                }
            }
        };

        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.RANDOM, mediaTypeToEndpoint(type)), "GET", RandomGifResponse.class, params,
                null, completionHandlerWrapper);
    }

    @Override
    @NonNull
    public AsyncTask categoriesForGifs(@Nullable Integer limit, @Nullable Integer offset,
                                       @NonNull final CompletionHandler<ListCategoryResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                Constants.Paths.CATEGORIES, "GET", ListCategoryResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask subCategoriesForGifs(@NonNull String categoryEncodedName,
                                          @Nullable Integer limit, @Nullable Integer offset,
                                          @NonNull final CompletionHandler<ListCategoryResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.SUBCATEGORIES, categoryEncodedName), "GET", ListCategoryResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask gifsByCategory(@NonNull String categoryEncodedName,
                                    @NonNull String subCategoryEncodedName,
                                    @Nullable Integer limit, @Nullable Integer offset,
                                    @NonNull final CompletionHandler<ListMediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.GIFS_BY_CATEGORY, categoryEncodedName, subCategoryEncodedName), "GET", ListMediaResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask gifById(@NonNull String gifId,
                             @NonNull final CompletionHandler<MediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.GIF_BY_ID, gifId), "GET", MediaResponse.class, params, null,
                completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask gifByIds(@NonNull List<String> gifIds,
                              @NonNull final CompletionHandler<ListMediaResponse> completionHandler) {
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
                Constants.Paths.GIF_BY_IDS, "GET", ListMediaResponse.class, params, null,
                completionHandler);
    }

    @NonNull
    public AsyncTask termSuggestions(@NonNull String term,
                                     @NonNull final CompletionHandler<ListTermSuggestionResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put("api_key", apiKey);

        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.TERM_SUGGESTIONS, term), "GET", ListTermSuggestionResponse.class, params, null,
                completionHandler);
    }

    @NonNull
    private String mediaTypeToEndpoint(@NonNull  MediaType type) {
        if (type == MediaType.sticker) {
            return "stickers";
        } else {
            return "gifs";
        }
    }

    // Helper function to provide callback access
    private <T extends GenericResponse> AsyncTask queryStringConnectionWrapper(@NonNull final Uri serverUrl,
                                        @NonNull final String path,
                                        @NonNull final String method,
                                        @NonNull final Class<T> responseClass,
                                        @Nullable final Map<String, String> queryStrings,
                                        @Nullable final Map<String, String> headers,
                                        @NonNull final CompletionHandler<T> completionHandler
                                ) {
        return networkSessionImpl.queryStringConnection(serverUrl, path, method, responseClass,
                queryStrings, headers).executeAsyncTask(new ApiTask.WrappedAsyncTask<T>() {
            @Override
            protected void onPostExecute(T genericResponse) {
                completionHandler.onComplete(genericResponse, null);
            }

            @Override
            protected void onError(@NonNull Throwable error) {
                completionHandler.onComplete(null, error);
            }
        });
    }
}
