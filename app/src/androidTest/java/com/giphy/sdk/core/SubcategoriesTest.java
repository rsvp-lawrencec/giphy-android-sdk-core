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

import android.os.Parcel;

import com.giphy.sdk.core.models.Category;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.ListCategoryResponse;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SubcategoriesTest {
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

        imp.subCategoriesForGifs("actions", null, null, new CompletionHandler<ListCategoryResponse>() {
            @Override
            public void onComplete(ListCategoryResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 25);

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if subcategories are returned using limit & offset
     *
     * @throws Exception
     */
    @Test
    public void testLimitOffset() throws Exception {
        final CountDownLatch lock = new CountDownLatch(2);

        imp.subCategoriesForGifs("animals", 15, 0, new CompletionHandler<ListCategoryResponse>() {
            @Override
            public void onComplete(final ListCategoryResponse result1, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertTrue(result1.getData().size() == 15);

                imp.subCategoriesForGifs("animals", 15, 5, new CompletionHandler<ListCategoryResponse>() {
                    @Override
                    public void onComplete(final ListCategoryResponse result2, Throwable e) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertTrue(result2.getData().size() == 15);

                        Assert.assertTrue(result2.getData().get(0).getName().equals(result1.getData().get(5).getName()));
                        lock.countDown();
                    }
                });

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if subcategories returned have the proper fields
     *
     * @throws Exception
     */
    @Test
    public void testFields() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.subCategoriesForGifs("animals", 15, 0, new CompletionHandler<ListCategoryResponse>() {
            @Override
            public void onComplete(final ListCategoryResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 15);

                Assert.assertNotNull(result.getData());

                for (Category category : result.getData()) {
                    Assert.assertNotNull(category.getName());
                    Assert.assertNotNull(category.getNameEncoded());
                    Assert.assertNotNull(category.getGif());
                    Assert.assertNotNull(category.getEncodedPath());
                    Assert.assertEquals(category.getEncodedPath(), "animals/" + category.getNameEncoded());
                }

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }

    /**
     * Test if parcelable is implemeted correctly for the models
     *
     * @throws Exception
     */
    @Test
    public void testParcelable() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        imp.subCategoriesForGifs("animals", 15, 0, new CompletionHandler<ListCategoryResponse>() {
            @Override
            public void onComplete(ListCategoryResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.getData().size() == 15);

                Gson gson = new Gson();
                for (Category category : result.getData()) {
                    Parcel parcel = Parcel.obtain();
                    category.writeToParcel(parcel, 0);
                    parcel.setDataPosition(0);
                    Category parcelCategory = Category.CREATOR.createFromParcel(parcel);
                    // Compare the initial object with the one obtained from parcel
                    Assert.assertEquals(gson.toJson(parcelCategory), gson.toJson(category));
                }

                lock.countDown();
            }
        });
        lock.await(2000, TimeUnit.MILLISECONDS);
    }
}