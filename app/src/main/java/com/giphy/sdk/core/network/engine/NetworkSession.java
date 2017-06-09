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

package com.giphy.sdk.core.network.engine;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.response.GenericResponse;
import com.giphy.sdk.core.threading.ApiTask;

import java.util.Map;
import java.util.concurrent.Future;

/**
 * A generic interface that describes all the params of a low level GET request.
 */
public interface NetworkSession {
    <T extends GenericResponse> ApiTask<T> queryStringConnection(@NonNull final Uri serverUrl,
                                                                @NonNull final String path,
                                                                @NonNull final String method,
                                                                @NonNull final Class<T> responseClass,
                                                                @Nullable final Map<String, String> queryStrings,
                                                                @Nullable final Map<String, String> headers);
}
