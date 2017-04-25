package com.giphy.sdk.core;

import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.GifResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

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
            public void onComplete(Throwable e, GifResponse result) {
                lock.countDown();

                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue("darAMUceRAs0w".equals(result.gif.id));
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
            public void onComplete(Throwable e, GifResponse result) {
                lock.countDown();

                Assert.assertNull(result);
                Assert.assertNotNull(e);
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }
}
