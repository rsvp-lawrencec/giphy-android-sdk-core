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
 * Created by bogdantmm on 4/24/17.
 */

public class TranslateTest {
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

        imp.translate("gifs", "hungry", null, null, new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(Throwable e, GifResponse result) {
                lock.countDown();

                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.gif);

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

        imp.translate("gifs", "hungry", "pg", null, new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(Throwable e, GifResponse result) {
                lock.countDown();

                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.gif);

            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif is returned using the lang param
     * @throws Exception
     */
    @Test
    public void testLang() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.translate("gifs", "hungry", "pg", "en", new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(Throwable e, GifResponse result) {
                lock.countDown();

                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.gif);

            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif returned using two different lang params are different
     * @throws Exception
     */
    @Test
    public void testTwoLang() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);

        imp.translate("gifs", "hungry", "pg", "en", new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(Throwable e, final GifResponse result1) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertNotNull(result1.gif);

                imp.translate("gifs", "hungry", "pg", "ro", new CompletionHandler<GifResponse>() {
                    @Override
                    public void onComplete(Throwable e, GifResponse result2) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertNotNull(result2.gif);

                        Assert.assertFalse(result2.gif.id.equals(result1.gif.id));

                        lock.countDown();
                    }
                });

                lock.countDown();
            }
        });
        lock.await(3000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif returned using two terms are different
     * @throws Exception
     */
    @Test
    public void testTwoTerms() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);

        imp.translate("gifs", "people", "pg", "en", new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(Throwable e, final GifResponse result1) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertNotNull(result1.gif);

                imp.translate("gifs", "cats and dogs", "pg", "ro", new CompletionHandler<GifResponse>() {
                    @Override
                    public void onComplete(Throwable e, GifResponse result2) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertNotNull(result2.gif);

                        Assert.assertFalse(result2.gif.id.equals(result1.gif.id));

                        lock.countDown();
                    }
                });

                lock.countDown();
            }
        });
        lock.await(3000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test no results tag
     * @throws Exception
     */
    @Test
    public void testNoResult() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.translate("gifs", "tttttttttt", "pg", "en", new CompletionHandler<GifResponse>() {
            @Override
            public void onComplete(Throwable e, GifResponse result) {
                Assert.assertNull(result);
                Assert.assertNotNull(e);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }
}
