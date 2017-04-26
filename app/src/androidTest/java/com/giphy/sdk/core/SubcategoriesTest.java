package com.giphy.sdk.core;

import com.giphy.sdk.core.models.Category;
import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.network.response.CategoriesResponse;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by bogdantmm on 4/21/17.
 */

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

        imp.subcategories("actions", null, null, new CompletionHandler<CategoriesResponse>() {
            @Override
            public void onComplete(CategoriesResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.categories.size() == 25);

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

        imp.subcategories("animals", 15, 0, new CompletionHandler<CategoriesResponse>() {
            @Override
            public void onComplete(final CategoriesResponse result1, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result1);
                Assert.assertTrue(result1.categories.size() == 15);

                imp.subcategories("animals", 15, 5, new CompletionHandler<CategoriesResponse>() {
                    @Override
                    public void onComplete(final CategoriesResponse result2, Throwable e) {
                        Assert.assertNull(e);
                        Assert.assertNotNull(result2);
                        Assert.assertTrue(result2.categories.size() == 15);

                        Assert.assertTrue(result2.categories.get(0).name.equals(result1.categories.get(5).name));
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
        final CountDownLatch lock = new CountDownLatch(2);

        imp.subcategories("animals", 15, 0, new CompletionHandler<CategoriesResponse>() {
            @Override
            public void onComplete(final CategoriesResponse result, Throwable e) {
                Assert.assertNull(e);
                Assert.assertNotNull(result);
                Assert.assertTrue(result.categories.size() == 15);

                Assert.assertNotNull(result.categories);

                for (Category category : result.categories) {
                    Assert.assertNotNull(category.name);
                    Assert.assertNotNull(category.name_encoded);
                    Assert.assertNotNull(category.gif);
                }

                lock.countDown();
            }
        });
        lock.await(3000, TimeUnit.MILLISECONDS);
    }

}