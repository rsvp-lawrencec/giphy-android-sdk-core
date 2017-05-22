/*
 * Created by Bogdan Tirca on 4/24/17.
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

import com.giphy.sdk.core.models.enums.LangType;
import com.giphy.sdk.core.models.enums.MediaType;
import com.giphy.sdk.core.models.enums.RatingType;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.MediaResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

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

        imp.translate("hungry", MediaType.gif, null, null, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(MediaResponse result, Throwable e) {
                lock.countDown();

                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.getData());

            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif is returned using the rating param
     * @throws Exception
     */
    @Test
    public void testRating() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.translate("hungry", MediaType.gif, RatingType.pg, null, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(MediaResponse result, Throwable e) {
                lock.countDown();

                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.getData());

            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif is returned using the lang param
     * @throws Exception
     */
    @Test
    public void testLang() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.translate("hungry", MediaType.gif, RatingType.pg, LangType.english, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(MediaResponse result, Throwable e) {
                lock.countDown();

                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertNotNull(result.getData());

            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif returned using two different lang params are different
     * @throws Exception
     */
    @Test
    public void testTwoLang() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);

        imp.translate("hungry", MediaType.gif, RatingType.pg13, LangType.english, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(final MediaResponse result1, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertNotNull(result1.getData());

                imp.translate("hungry", MediaType.gif, RatingType.pg13, LangType.chineseSimplified, new CompletionHandler<MediaResponse>() {
                    @Override
                    public void onComplete(MediaResponse result2, Throwable e) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertNotNull(result2.getData());

                        Assert.assertFalse(result2.getData().getId().equals(result1.getData().getId()));

                        lock.countDown();
                    }
                });

                lock.countDown();
            }
        });
        lock.await(Utils.MEDIUM_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if gif returned using two terms are different
     * @throws Exception
     */
    @Test
    public void testTwoTerms() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);

        imp.translate("people", MediaType.gif, RatingType.y, LangType.english, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(final MediaResponse result1, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertNotNull(result1.getData());

                imp.translate("cats and dogs", MediaType.gif, RatingType.y, LangType.english, new CompletionHandler<MediaResponse>() {
                    @Override
                    public void onComplete(MediaResponse result2, Throwable e) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertNotNull(result2.getData());

                        Assert.assertFalse(result2.getData().getId().equals(result1.getData().getId()));

                        lock.countDown();
                    }
                });

                lock.countDown();
            }
        });
        lock.await(Utils.MEDIUM_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test no results tag
     * @throws Exception
     */
    @Test
    public void testNoResult() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.translate("tttttttttt", MediaType.gif, RatingType.pg, LangType.english, new CompletionHandler<MediaResponse>() {
            @Override
            public void onComplete(MediaResponse result, Throwable e) {
                Assert.assertNull(result);
                Assert.assertNotNull(e);

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }
}
