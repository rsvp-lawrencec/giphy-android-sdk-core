/*
 * Created by Bogdan Tirca on .
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

package com.giphy.sdk.core;

import android.os.AsyncTask;
import android.os.Parcel;

import com.giphy.sdk.core.models.Media;
import com.giphy.sdk.core.models.enums.LangType;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.ListMediaResponse;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdantmm on 4/21/17.
 */

public class SearchTest {
    GPHApiClient imp;

    @Before
    public void setUp() throws Exception {
        imp = new GPHApiClient("dc6zaTOxFJmzC");
    }

    /**
     * Test if search without params returns 25 gifs and not exception.
     *
     * @throws Exception
     */
    @Test
    public void testBase() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("hack", MediaType.gif, null, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 25);

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test a search that has no results.
     *
     * @throws Exception
     */
    @Test
    public void testNoResults() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("jjhjhhjhhhjjhhh", MediaType.gif, null, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 0);

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if limit returns the exact amount of gifs
     * @throws Exception
     */
    @Test
    public void testLimit() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("cats", MediaType.gif, 13, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 13);

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if rating returns gifs
     * @throws Exception
     */
    @Test
    public void testRating() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("cats", MediaType.gif, 20, null, RatingType.pg, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 20);

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if rating returns gifs
     * @throws Exception
     */
    @Test
    public void testRatingY() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("cars", MediaType.gif, 20, null, RatingType.y, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 20);

                Assert.assertTrue(result.getData().get(0).getRating() == RatingType.y);

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if languages returns gifs
     * @throws Exception
     */
    @Test
    public void testLang() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("cars", MediaType.gif, 20, null, null, LangType.chineseTraditional, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 20);

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if the 11th gif from the request with offset 0 is the same as the first gif from the
     * request with offset 10
     * @throws Exception
     */
    @Test
    public void testOffset() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);

        imp.search("cats", MediaType.gif, 30, 0, RatingType.pg, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(final ListMediaResponse result1, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertTrue(result1.getData().size() == 30);

                imp.search("cats", MediaType.gif, 30, 10, RatingType.pg, null, new CompletionHandler<ListMediaResponse>() {
                    @Override
                    public void onComplete(ListMediaResponse result2, Throwable e) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertTrue(result2.getData().size() == 30);

                        Utils.checkOffsetWorks(result1.getData(), result2.getData(), 1);

                        lock.countDown();
                    }
                });

                lock.countDown();
            }
        });
        lock.await(Utils.MEDIUM_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if pagination is returned.
     * @throws Exception
     */
    @Test
    public void testPagination() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("hack", MediaType.gif, 13, 12, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 13);

                Assert.assertNotNull(result.getPagination());
                Assert.assertTrue(result.getPagination().getCount() == 13);
                Assert.assertTrue(result.getPagination().getOffset() == 12);
                Assert.assertTrue(result.getPagination().getTotalCount() > 100);

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if meta is returned.
     * @throws Exception
     */
    @Test
    public void testMeta() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("test", MediaType.gif, null, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 25);

                Assert.assertNotNull(result.getMeta());
                Assert.assertTrue(result.getMeta().getStatus() == 200);
                Assert.assertEquals(result.getMeta().getMsg(), "OK");
                Assert.assertNotNull(result.getMeta().getResponseId());

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test cancelation
     *
     * @throws Exception
     */
    @Test
    public void testCancelation() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        final AsyncTask task = imp.search("hack", MediaType.gif, null, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                // If we get here, the test will fail, since it wasn't properly canceled
                Assert.assertNull(result);
                Assert.assertNull(e);

                lock.countDown();
            }
        });
        // Cancel imediately
        task.cancel(true);

        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test cancelation with some delay
     *
     * @throws Exception
     */
    @Test
    public void testCancelationWithDelay() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        final AsyncTask task = imp.search("hack", MediaType.gif, 2, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                // If we get here, the test will fail, since it wasn't properly canceled
                Assert.assertNull(result);
                Assert.assertNull(e);

                lock.countDown();
            }
        });

        // Cancel after a small period of time. Enough for the network request to start, but less
        // than it takes to complete
        lock.await(20, TimeUnit.MILLISECONDS);
        task.cancel(true);

        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if images have width & height & frames
     *
     * @throws Exception
     */
    @Test
    public void testWidthHeight() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("hack", MediaType.gif, null, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 25);

                for (Media media : result.getData()) {
                    Assert.assertTrue(media.getImages().getOriginal().getHeight() > 0);
                    Assert.assertTrue(media.getImages().getOriginal().getWidth() > 0);
                    Assert.assertTrue(media.getImages().getOriginal().getFrames() > 0);
                }

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if parcelable is implemeted correctly for the models
     *
     * @throws Exception
     */
    @Test
    public void testParcelable() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.search("hack", MediaType.gif, 100, null, null, null, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 100);

                Gson gson = new Gson();
                for (Media media : result.getData()) {
                    Parcel parcel = Parcel.obtain();
                    media.writeToParcel(parcel, 0);
                    parcel.setDataPosition(0);
                    Media parcelMedia = Media.CREATOR.createFromParcel(parcel);
                    // Compare the initial object with the one obtained from parcel
                    Assert.assertEquals(gson.toJson(parcelMedia), gson.toJson(media));
                }

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }
}