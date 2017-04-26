package com.giphy.sdk.core;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.giphy.sdk.core.models.Gif;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.engine.NetworkSession;
import com.giphy.sdk.core.network.response.GenericResponse;
import com.giphy.sdk.core.network.response.MultipleGifsResponse;
import com.giphy.sdk.core.threading.ApiTask;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by bogdantmm on 4/26/17.
 */

public class OkHttpIntegrationTest {
    GPHApiClient imp;

    @Before
    public void setUp() throws Exception {
        imp = new GPHApiClient("dc6zaTOxFJmzC", new OkHttptNetworkSession());
    }

    /**
     * Test if trending without params returns 25 gifs and not exception.
     * @throws Exception
     */
    @Test
    public void testTrending() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.trending("gifs", null, null, null, new CompletionHandler<MultipleGifsResponse>() {
            @Override
            public void onComplete(Throwable e, MultipleGifsResponse result) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.gifs);
                Assert.assertTrue(result.gifs.size() == 25);

                for (Gif gif : result.gifs) {
                    Assert.assertNotNull(gif.id);
                    Assert.assertNotNull(gif.images);
                }
                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if search without params returns 25 gifs and not exception.
     *
     * @throws Exception
     */
    @Test
    public void testSearch() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("gifs", "hack", null, null, null, null, new CompletionHandler<MultipleGifsResponse>() {
            @Override
            public void onComplete(Throwable e, MultipleGifsResponse result) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.gifs);
                Assert.assertTrue(result.gifs.size() == 25);

                for (Gif gif : result.gifs) {
                    Assert.assertNotNull(gif.id);
                    Assert.assertNotNull(gif.images);
                    Assert.assertNotNull(gif.type);
                }

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    static class OkHttptNetworkSession implements NetworkSession {
        private static final Gson GSON_INSTANCE = new Gson();

        @Override
        public <T extends GenericResponse> ApiTask<T> queryStringConnection(@NonNull final Uri serverUrl,
                                                                            @NonNull final String path,
                                                                            @NonNull final String method,
                                                                            @NonNull final Class<T> responseClass,
                                                                            @Nullable final Map<String, String> queryStrings,
                                                                            @Nullable final Map<String, String> headers) {
            return new ApiTask<>(new Callable<T>() {
                @Override
                public T call() throws Exception {
                    final Uri.Builder uriBuilder = serverUrl.buildUpon().appendEncodedPath(path);

                    if (queryStrings != null) {
                        for (Map.Entry<String, String> query : queryStrings.entrySet()) {
                            uriBuilder.appendQueryParameter(query.getKey(), query.getValue());
                        }
                    }

                    final URL url = new URL(uriBuilder.build().toString());

                    final Request.Builder requestBuilder = new Request.Builder()
                            .url(url);

                    if (headers != null) {
                        for (Map.Entry<String, String> header : headers.entrySet()) {
                            requestBuilder.addHeader(header.getKey(), header.getValue());
                        }
                    }

                    final Request request = requestBuilder.build();

                    final OkHttpClient client = new OkHttpClient();
                    final Response response = client.newCall(request).execute();
                    // Deserialize HTTP response to concrete type.

                    final ResponseBody body = response.body();
                    return GSON_INSTANCE.fromJson(body.string(), responseClass);
                }
            });
        }
    }
}
