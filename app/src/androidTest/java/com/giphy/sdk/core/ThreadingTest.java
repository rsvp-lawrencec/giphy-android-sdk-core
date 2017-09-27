/*
 * Created by Bogdan Tirca.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core;


import android.os.Looper;

import com.giphy.sdk.core.network.api.CompletionHandler;
import com.giphy.sdk.core.network.api.GPHApiClient;
import com.giphy.sdk.core.threading.ApiTask;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class ThreadingTest {
    GPHApiClient imp;

    @Before
    public void setUp() throws Exception {
        imp = new GPHApiClient("dc6zaTOxFJmzC");
    }

    @Test
    public void testAsync() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        new ApiTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Assert.assertTrue(Looper.getMainLooper().getThread() != Thread.currentThread());
                return "test";
            }
        }).executeAsyncTask(new CompletionHandler<String>() {
            @Override
            public void onComplete(String result, Throwable e) {
                Assert.assertTrue(Looper.getMainLooper().getThread() == Thread.currentThread());
                Assert.assertNotNull(result);
                Assert.assertNull(e);

                Assert.assertEquals(result, "test");
                lock.countDown();
            }
        });

        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testAsyncError() throws Exception {
        final CountDownLatch lock = new CountDownLatch(1);

        new ApiTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                throw new Exception("Test error");
            }
        }).executeAsyncTask(new CompletionHandler<String>() {
            @Override
            public void onComplete(String result, Throwable e) {
                Assert.assertTrue(Looper.getMainLooper().getThread() == Thread.currentThread());
                Assert.assertNotNull(e);
                Assert.assertNull(result);

                Assert.assertEquals(e.getMessage(), "Test error");

                lock.countDown();
            }
        });

        lock.await(Utils.SMALL_DELAY, TimeUnit.MILLISECONDS);
    }

    @Test
    public void testSync() throws Exception {
        String test = new ApiTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Assert.assertTrue(Looper.getMainLooper().getThread() != Thread.currentThread());
                return "test";
            }
        }).executeImmediately();
        Assert.assertEquals(test, "test");
    }

    @Test
    public void testSyncError() throws Exception {
        try {
            String test = new ApiTask<String>(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    throw new Exception("Test error");
                }
            }).executeImmediately();
        } catch (Exception e) {
            Assert.assertEquals(e.getMessage(), "Test error");
            return;
        }
        // If code reaches here, force fail
        Assert.assertTrue(false);
    }
}
