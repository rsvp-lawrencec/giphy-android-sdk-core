package com.giphy.sdk.core;

import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.GifResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdantmm on 4/24/17.
 */

public class RandomTest {
    GPHApiClient imp;

    @Before
    public void setUp() throws Exception {
        imp = new GPHApiClient("dc6zaTOxFJmzC");
    }

    /**
     * Test if gif is returned
     * @throws Exception
     */
    @Test
    public void testBase() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.random("cats dogs", MediaType.gif, null, new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(GifResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.gif);

                lock.countDown();

            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif is returned using the rating param
     * @throws Exception
     */
    @Test
    public void testRating() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.random("cats dogs", MediaType.gif, RatingType.pg, new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(GifResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.gif);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test no results tag
     * @throws Exception
     */
    @Test
    public void testNoResult() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.random("cats_ttttt", MediaType.gif, RatingType.pg, new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(GifResponse result, Throwable e) {
                Assert.assertNull(result);
                Assert.assertNotNull(e);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test gif fields
     * @throws Exception
     */
    @Test
    public void testFields() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.random("cats dogs", MediaType.gif, null, new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(GifResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);

                Assert.assertNotNull(result.gif);
                Assert.assertNotNull(result.gif.id);

                Assert.assertNotNull(result.gif.images);

                Assert.assertNotNull(result.gif.images.original);
                Assert.assertNotNull(result.gif.images.original.url);

                Assert.assertNotNull(result.gif.images.fixed_height_downsampled);
                Assert.assertNotNull(result.gif.images.fixed_height_downsampled.url);

                Assert.assertNotNull(result.gif.images.fixed_width_downsampled);
                Assert.assertNotNull(result.gif.images.fixed_width_downsampled.url);

                Assert.assertNotNull(result.gif.images.fixed_height_small);
                Assert.assertNotNull(result.gif.images.fixed_height_small.url);

                Assert.assertNotNull(result.gif.images.fixed_width_small);
                Assert.assertNotNull(result.gif.images.fixed_width_small.url);

                Assert.assertNotNull(result.gif.images.fixed_height_small_still);
                Assert.assertNotNull(result.gif.images.fixed_height_small_still.url);

                Assert.assertNotNull(result.gif.images.fixed_width_small_still);
                Assert.assertNotNull(result.gif.images.fixed_width_small_still.url);

                lock.countDown();

            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if 2 consecutive requests return different gifs
     * @throws Exception
     */
    @Test
    public void testDifferentResults() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);

        imp.random("cats dogs", MediaType.gif, RatingType.pg, new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(final GifResponse result1, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertNotNull(result1.gif);

                imp.random("cats dogs", MediaType.gif, RatingType.pg, new CompletionHandler<GifResponse>() {
                    @Override
                    public void onComplete(GifResponse result2, Throwable e) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertNotNull(result2.gif);

                        // The two gifs should be different
                        Assert.assertFalse(result1.gif.id.equals(result2.gif.id));
                        lock.countDown();
                    }
                });
                lock.countDown();

            }
        });
        lock.await(3000, TimeUnit.MILLISECONDS);
    }
}
