/*
 * Created by Bogdan Tirca on 4/19/17.
 * Copyright (c) 2017 Giphy Inc.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.giphy.sdk.core.threading;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.giphy.sdk.core.network.api.CompletionHandler;

import java.io.InterruptedIOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Represents a top level task returned by API calls that can be ran async or immediately
 * with an ExecutorService.
 *
 * @see Future
 * @see ExecutorService
 */
public class ApiTask<V> {

    /**
     * HTTP Executor settings borrowed from Bolts-Android's AndroidExecutors.newCachedThreadPool
     * https://github.com/BoltsFramework/Bolts-Android/blob/master/bolts-tasks/src/main/java/bolts/AndroidExecutors.java
     */
    public static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    public static final int THREAD_POOL_CORE_SIZE = CPU_COUNT + 2;
    public static final int THREAD_POOL_MAX_SIZE = CPU_COUNT * 2 + 2;
    public static final long THREAD_POOL_KEEP_ALIVE_TIME = 1L;
    public static final ExecutorService THREAD_POOL_EXECUTOR_SERVICE = new ThreadPoolExecutor(
            THREAD_POOL_CORE_SIZE,
            THREAD_POOL_MAX_SIZE,
            THREAD_POOL_KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>()
    );

    public static final Handler MAIN_LOOP_HANDLER = new Handler(Looper.getMainLooper());

    private final Callable<V> callable;
    private final ExecutorService executorService;

    public ApiTask(Callable<V> callable) {
        this.callable = callable;
        this.executorService = THREAD_POOL_EXECUTOR_SERVICE;
    }

    public ApiTask(Callable<V> callable, ExecutorService executorService) {
        this.callable = callable;
        this.executorService = executorService;
    }

    /**
     * Resolves the task on a shared thread pool executor service and returns the result using the
     * completionHandler
     *
     * @param completionHandler
     * @return
     */
    public Future executeAsyncTask(final CompletionHandler<V> completionHandler) {
        return executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    final V value = callable.call();

                    // If thread was interrupted, throw error
                    if (Thread.currentThread().isInterrupted()) {
                        throw new InterruptedException();
                    }

                    MAIN_LOOP_HANDLER.post(new Runnable() {
                        @Override
                        public void run() {
                            completionHandler.onComplete(value, null);
                        }
                    });
                } catch (final ExecutionException e) {
                    Log.e(ApiTask.class.getName(), "Unable to perform async task, cancelling…", e);

                    MAIN_LOOP_HANDLER.post(new Runnable() {
                        @Override
                        public void run() {
                            completionHandler.onComplete(null, e);
                        }
                    });
                } catch (InterruptedIOException|InterruptedException e) { // interrupts will naturally occur from cancelling
                } catch (final Throwable e) {
                    MAIN_LOOP_HANDLER.post(new Runnable() {
                        @Override
                        public void run() {
                            completionHandler.onComplete(null, e);
                        }
                    });
                }
            }
        });
    }

    /**
     * Immediately resolves the task on the same thread.
     *
     * @return The resolved value from running the task.
     * @throws Exception
     */
    public V executeImmediately() throws Exception {
        return callable.call();
    }
}