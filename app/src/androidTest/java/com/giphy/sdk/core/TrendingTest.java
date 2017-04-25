package com.giphy.sdk.core;

import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.MultipleGifsResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TrendingTest {
    GPHApiClient imp;

    @Before
    public void setUp() throws Exception {
        imp = new GPHApiClient("dc6zaTOxFJmzC");
    }

    /**
     * Test if trending without params returns 25 gifs and not exception.
     * @throws Exception
     */
    @Test
    public void testBase() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.trending("gifs", null, null, null, new CompletionHandler<MultipleGifsResponse>() {
            @Override
            public void onComplete(Throwable e, MultipleGifsResponse result) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.gifs.size() == 25);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if limit returns the exact amount of gifs
     * @throws Exception
     */
    @Test
    public void testLimit() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.trending("gifs", 13, null, null, new CompletionHandler<MultipleGifsResponse>() {
            @Override
            public void onComplete(Throwable e, MultipleGifsResponse result) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.gifs.size() == 13);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if rating returns gifs
     * @throws Exception
     */
    @Test
    public void testRating() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.trending("gifs", 20, null, "pg", new CompletionHandler<MultipleGifsResponse>() {
            @Override
            public void onComplete(Throwable e, MultipleGifsResponse result) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.gifs.size() == 20);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if the 11th gif from the request with offset 0 is the same as the first gif from the
     * request with offset 10
     * @throws Exception
     */
    @Test
    public void testOffset() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);

        imp.trending("gifs", 20, 0, "pg", new CompletionHandler<MultipleGifsResponse>() {
            @Override
            public void onComplete(Throwable e, final MultipleGifsResponse result1) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertTrue(result1.gifs.size() == 20);

                imp.trending("gifs", 20, 10, "pg", new CompletionHandler<MultipleGifsResponse>() {
                    @Override
                    public void onComplete(Throwable e, MultipleGifsResponse result2) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertTrue(result2.gifs.size() == 20);

                        Utils.checkOffsetWorks(result1.gifs, result2.gifs);

                        lock.countDown();
                    }
                });

                lock.countDown();
            }
        });
        lock.await(3000, TimeUnit.MILLISECONDS);
    }
}