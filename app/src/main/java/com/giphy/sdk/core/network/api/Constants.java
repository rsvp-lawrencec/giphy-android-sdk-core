package com.giphy.sdk.core.network.api;

import android.net.Uri;

/**
 * Created by bogdantmm on 4/19/17.
 */

public class Constants {
    public static final Uri SERVER_URL = Uri.parse("https://api.giphy.com");

    public static final String API_KEY = "api_key";

    public static class Paths {
        public static final String SEARCH = "v1/%s/search";
        public static final String TRENDING = "v1/%s/trending";
        public static final String RANDOM = "v1/%s/random";
        public static final String TRANSLATE = "v1/%s/translate";
        public static final String CATEGORIES = "v1/gifs/categories";
        public static final String SUBCATEGORIES = "v1/gifs/categories/%s";
        public static final String GIFS_BY_CATEGORY = "v1/gifs/categories/%s/%s";
        public static final String GIF_BY_ID= "v1/gifs/%s";
        public static final String GIF_BY_IDS= "v1/gifs";
        public static final String TERM_SUGGESTIONS= "v1/queries/suggest/%s";
    }
}
