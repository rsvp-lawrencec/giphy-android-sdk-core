/*
 * Created by Bogdan Tirca on 4/21/17.
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

import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.ListMediaResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GifsByIdsTest {
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

        final List<String> gifIds = new ArrayList<>();
        gifIds.add("GSotmi2t5hEA");
        gifIds.add("darAMUceRAs0w");
        gifIds.add("l4FGF1Lk3GibtKchO");

        imp.gifsByIds(gifIds, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == gifIds.size());

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if the gifs returned have the same id as the ones requested
     * @throws Exception
     */
    @Test
    public void testIdsMatch() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        final List<String> gifIds = new ArrayList<>();
        gifIds.add("GSotmi2t5hEA");
        gifIds.add("darAMUceRAs0w");
        gifIds.add("l4FGF1Lk3GibtKchO");

        imp.gifsByIds(gifIds, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == gifIds.size());
                for (int i = 0; i < gifIds.size(); i ++) {
                    Assert.assertEquals(result.getData().get(i).getId(), gifIds.get(i));
                }

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    /**
     * Test the response when some ids are not found
     * @throws Exception
     */
    @Test
    public void testGifNotFound() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        final List<String> gifIds = new ArrayList<>();
        gifIds.add("GSotmi2t5hEA");
        gifIds.add("darAMUceRAs0w_ttttttttt");
        gifIds.add("l4FGF1Lk3GibtKchO");

        imp.gifsByIds(gifIds, new CompletionHandler<ListMediaResponse>() {
            @Override
            public void onComplete(ListMediaResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == gifIds.size() - 1);
                Assert.assertEquals(result.getData().get(0).getId(), gifIds.get(0));
                Assert.assertEquals(result.getData().get(1).getId(), gifIds.get(2));

                lock.countDown();
            }
        });
        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }
}
