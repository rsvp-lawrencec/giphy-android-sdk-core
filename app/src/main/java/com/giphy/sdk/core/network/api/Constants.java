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
