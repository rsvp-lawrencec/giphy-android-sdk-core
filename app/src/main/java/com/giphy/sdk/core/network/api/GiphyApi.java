package com.giphy.sdk.core.network.api;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import com.giphy.sdk.core.network.response.GenericResponse;
import com.giphy.sdk.core.network.response.GifResponse;
import com.giphy.sdk.core.network.response.MultipleGifsResponse;

/**
 * Created by bogdantmm on 4/19/17.
 */

public interface GiphyApi {
    /**
     * Search for gifs or stickers
     * @param type can be sticker or gif
     * @param searchQuery search query term or phrase
     * @param limit (optional) number of results to return, maximum 100. Default 25.
     * @param offset (optional) results offset, defaults to 0.
     * @param rating (optional) limit results to those rated (y,g, pg, pg-13 or r).
     * @param lang  (optional) specify default country for regional content; format is 2-letter ISO 639-1 country code.
     * @return
     */
    @NonNull
    public AsyncTask search(@NonNull String type, @NonNull  String searchQuery, @Nullable Integer limit,
                            @Nullable Integer offset, @Nullable String rating,
                            @Nullable String lang,
                            @Nullable final CompletionHandler<MultipleGifsResponse> completionHandler);

    /**
     * Get the trending gifs or stickers
     * @param type can be sticker or gif
     * @param limit  (optional) limits the number of results returned. By default returns 25 results.
     * @param offset  (optional) results offset, defaults to 0);
     * @param rating  (optional) limit results to those rated (y,g, pg, pg-13 or r).
     * @return
     */
    @NonNull
    public AsyncTask trending(@NonNull String type, @Nullable Integer limit,
                              @Nullable Integer offset, @Nullable String rating,
                              @Nullable final CompletionHandler<MultipleGifsResponse> completionHandler);

    /**
     * The translate API draws on search, but uses the Giphy "special sauce" to handle translating from one vocabulary to another.
     * @param type can be sticker or gif
     * @param term term or phrase to translate into a GIF
     * @param rating  (optional) limit results to those rated (y,g, pg, pg-13 or r).
     * @param lang  (optional) specify default country for regional content; format is 2-letter ISO 639-1 country code.
     * @return
     */
    @NonNull
    public AsyncTask translate(@NonNull String type, @NonNull String term, @Nullable String rating,
                               @Nullable String lang,
                               @Nullable final CompletionHandler<GenericResponse> completionHandler);

    /**
     * Returns a random GIF, limited by tag. Excluding the tag parameter will return a random GIF from the Giphy catalog.
     * @param type
     * @param tag the GIF tag to limit randomness by
     * @param rating limit results to those rated (y,g, pg, pg-13 or r).
     * @return
     */
    @NonNull
    public AsyncTask random(@NonNull String type, @NonNull String tag, @Nullable String rating,
                            @Nullable final CompletionHandler<GenericResponse> completionHandler);

    /**
     * Returns meta data about a GIF, by GIF id
     * @param gifId the id of the gif we want to return
     * @return
     */
    @NonNull
    public AsyncTask gifById(@NonNull String gifId,
                             @Nullable final CompletionHandler<GifResponse> completionHandler);

    /**
     * Returns meta data about multiple gifs
     * @param gifIds the list of ids of the gifs we want to return
     * @return
     */
    @NonNull
    public AsyncTask gifByIds(@NonNull List<String> gifIds,
                              @Nullable final CompletionHandler<MultipleGifsResponse> completionHandler);
}
