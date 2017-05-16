/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.giphy.sdk.core.network.api;

import android.net.Uri;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giphy.sdk.core.models.Category;
import com.giphy.sdk.core.models.enums.LangType;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.giphy.sdk.core.network.engine.DefaultNetworkSession;
import com.giphy.sdk.core.network.engine.NetworkSession;
import com.giphy.sdk.core.network.response.GenericResponse;
import com.giphy.sdk.core.network.response.ListCategoryResponse;
import com.giphy.sdk.core.network.response.ListMediaResponse;
import com.giphy.sdk.core.network.response.ListTermSuggestionResponse;
import com.giphy.sdk.core.network.response.MediaResponse;
import com.giphy.sdk.core.network.response.RandomGifResponse;
import com.giphy.sdk.core.threading.ApiTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GPHApiClient implements GPHApi {
    private static final String HTTP_GET = "GET";
    public static final String API_KEY = "api_key";

    private final NetworkSession networkSessionImpl;
    private final String apiKey;

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
        params.put(API_KEY, apiKey);
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
                String.format(Constants.Paths.SEARCH, mediaTypeToEndpoint(type)), HTTP_GET, ListMediaResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask trending(@Nullable MediaType type, @Nullable Integer limit,
                              @Nullable Integer offset, @Nullable RatingType rating,
                              @NonNull final CompletionHandler<ListMediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);
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
                String.format(Constants.Paths.TRENDING, mediaTypeToEndpoint(type)), HTTP_GET, ListMediaResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask translate(@NonNull String term, @Nullable MediaType type, @Nullable RatingType rating,
                               @Nullable LangType lang,
                               @NonNull final CompletionHandler<MediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);
        params.put("s", term);
        if (rating != null) {
            params.put("rating", rating.toString());
        }
        if (lang != null) {
            params.put("lang", lang.toString());
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.TRANSLATE, mediaTypeToEndpoint(type)), HTTP_GET, MediaResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask random(@NonNull String tag, @Nullable MediaType type, @Nullable RatingType rating,
                            @NonNull final CompletionHandler<MediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);
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
                String.format(Constants.Paths.RANDOM, mediaTypeToEndpoint(type)), HTTP_GET, RandomGifResponse.class, params,
                null, completionHandlerWrapper);
    }

    @Override
    @NonNull
    public AsyncTask categoriesForGifs(@Nullable Integer limit, @Nullable Integer offset,
                                       @Nullable String sort,
                                       @NonNull final CompletionHandler<ListCategoryResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        if (sort != null) {
            params.put("sort", sort);
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                Constants.Paths.CATEGORIES, HTTP_GET, ListCategoryResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask subCategoriesForGifs(@NonNull final String categoryEncodedName,
                                          @Nullable Integer limit, @Nullable Integer offset,
                                          @NonNull final CompletionHandler<ListCategoryResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        final CompletionHandler<ListCategoryResponse> completionHandlerWrapper = new CompletionHandler<ListCategoryResponse>() {
            @Override
            public void onComplete(ListCategoryResponse result, Throwable e) {
                if (result != null) {
                    if (result.getData() != null) {
                        for (Category subCategory : result.getData()) {
                            subCategory.setEncodedPath(categoryEncodedName + "/" + subCategory.getNameEncoded());
                        }
                    }
                    completionHandler.onComplete(result, null);
                } else {
                    completionHandler.onComplete(null, e);
                }
            }
        };

        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.SUBCATEGORIES, categoryEncodedName), HTTP_GET, ListCategoryResponse.class, params,
                null, completionHandlerWrapper);
    }

    @Override
    @NonNull
    public AsyncTask gifsByCategory(@NonNull String categoryEncodedName,
                                    @NonNull String subCategoryEncodedName,
                                    @Nullable Integer limit, @Nullable Integer offset,
                                    @NonNull final CompletionHandler<ListMediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);
        if (limit != null) {
            params.put("limit", limit.toString());
        }
        if (offset != null) {
            params.put("offset", offset.toString());
        }
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.GIFS_BY_CATEGORY, categoryEncodedName, subCategoryEncodedName), HTTP_GET, ListMediaResponse.class, params,
                null, completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask gifById(@NonNull String gifId,
                             @NonNull final CompletionHandler<MediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);
        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.GIF_BY_ID, gifId), HTTP_GET, MediaResponse.class, params, null,
                completionHandler);
    }

    @Override
    @NonNull
    public AsyncTask gifsByIds(@NonNull List<String> gifIds,
                               @NonNull final CompletionHandler<ListMediaResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);

        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < gifIds.size(); i ++) {
            str.append(gifIds.get(i));
            if (i < gifIds.size() - 1) {
                str.append(",");
            }
        }
        params.put("ids", str.toString());

        return queryStringConnectionWrapper(Constants.SERVER_URL,
                Constants.Paths.GIF_BY_IDS, HTTP_GET, ListMediaResponse.class, params, null,
                completionHandler);
    }

    @NonNull
    public AsyncTask termSuggestions(@NonNull String term,
                                     @NonNull final CompletionHandler<ListTermSuggestionResponse> completionHandler) {
        final Map<String, String> params = new HashMap<>();
        params.put(API_KEY, apiKey);

        return queryStringConnectionWrapper(Constants.SERVER_URL,
                String.format(Constants.Paths.TERM_SUGGESTIONS, term), HTTP_GET, ListTermSuggestionResponse.class, params, null,
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
