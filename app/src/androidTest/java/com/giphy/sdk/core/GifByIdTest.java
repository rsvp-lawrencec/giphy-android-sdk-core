package com.giphy.sdk.core;

import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApi;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.engine.ApiException;
import com.giphy.sdk.core.network.response.GifResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdantmm on 4/21/17.
 */

public class GifByIdTest {
    GPHApiClient imp;

    @Before
    public void setUp() throws Exception {
        imp = new GPHApiClient("dc6zaTOxFJmzC");
    }

    /**
     * Test if gif is returned using id
     * @throws Exception
     */
    @Test
    public void testBase() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.gifById("darAMUceRAs0w", new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(GifResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue("darAMUceRAs0w".equals(result.getGif().getId()));

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif is returned using id
     * @throws Exception
     */
    @Test
    public void testGifNotFound() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.gifById("darAMUceRAs0w_ttttttttt", new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(GifResponse result, Throwable e) {
                Assert.assertNull(result);
                Assert.assertNotNull(e);
                Assert.assertNotNull(e.getCause());
                Assert.assertTrue(e.getCause() instanceof ApiException);
                Assert.assertNotNull(((ApiException)e.getCause()).getErrorResponse());
                Assert.assertNotNull(((ApiException)e.getCause()).getErrorResponse().getMeta());
                Assert.assertEquals(((ApiException)e.getCause()).getErrorResponse().getMeta().getStatus(), HttpURLConnection.HTTP_NOT_FOUND);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if meta is returned.
     * @throws Exception
     */
    @Test
    public void testMeta() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.gifById("darAMUceRAs0w", new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(GifResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.getGif());

                Assert.assertNotNull(result.getMeta());
                Assert.assertTrue(result.getMeta().getStatus() == 200);
                Assert.assertEquals(result.getMeta().getMsg(), "OK");
                Assert.assertNotNull(result.getMeta().getResponseId());

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test invalid api key
     * @throws Exception
     */
    @Test
    public void testInvalidApiKey() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        final GPHApi client = new GPHApiClient("invalid_api_key");
        client.gifById("darAMUceRAs0w", new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(GifResponse result, Throwable e) {
                Assert.assertNull(result);
                Assert.assertNotNull(e);
                Assert.assertNotNull(e.getCause());
                Assert.assertTrue(e.getCause() instanceof ApiException);
                Assert.assertNotNull(((ApiException)e.getCause()).getErrorResponse());
                Assert.assertNotNull(((ApiException)e.getCause()).getErrorResponse().getMeta());
                Assert.assertEquals(((ApiException)e.getCause()).getErrorResponse().getMeta().getStatus(), HttpURLConnection.HTTP_UNAUTHORIZED);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }
}
